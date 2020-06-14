package com.upec.androidtemplate20192020.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.upec.androidtemplate20192020.Model.Etudiant;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DatabaseHelper     extends OrmLiteSqliteOpenHelper {

    // le nom de la base de donnees
    private static final String DATABASE_NAME = "pointeuse.db";
    private static final int DATABASE_VERSION = 1;
    private Dao<Etudiant,Integer> d = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            // Creer le table etudiant
            TableUtils.createTable(connectionSource, Etudiant.class);
        } catch (SQLException | java.sql.SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
    try {
        // synchronosation
        TableUtils.dropTable(connectionSource,Etudiant.class, true);
        onCreate(database,connectionSource);
    } catch (java.sql.SQLException e) {
        e.printStackTrace();
    }
    }

    public Dao.CreateOrUpdateStatus createOrUpdateEtudiant(Etudiant obj) throws SQLException, java.sql.SQLException {
        Dao<Etudiant, ?> dao = (Dao<Etudiant, ?>) getDao(obj.getClass());
        return dao.createOrUpdate(obj);
    }

    public String createEtudiant(Etudiant etudiant) {
        try {

            List<Etudiant> etu = getEtudiant(etudiant.getCNE());
            if(etu.size()==0)
            this.createOrUpdateEtudiant(etudiant);

            return etudiant.getCNE();
        } catch (SQLException | java.sql.SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Etudiant> getEtudiant() throws java.sql.SQLException {

        Dao<Etudiant,?> etudiants = getDao(Etudiant.class);

        return etudiants.queryForAll();
    }


    public List<Etudiant> getEtudiant(String cne) throws java.sql.SQLException {

        Dao<Etudiant,?> etudiants = getDao(Etudiant.class);

        return etudiants.queryForEq("CNE",cne);
    }



    public List<Etudiant> getEtudiant(String idgroup,String filiere,String anne) throws java.sql.SQLException {

        Dao<Etudiant,?> etudiants = getDao(Etudiant.class);
        Map<String,Object> map = new HashMap<>();

        map.put("idClasse",idgroup);

        map.put("filiere",filiere);


        map.put("annee",anne);


        return etudiants.queryForFieldValues(map);
    }




















}
