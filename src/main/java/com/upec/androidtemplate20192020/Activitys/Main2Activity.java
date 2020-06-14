package com.upec.androidtemplate20192020.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.upec.androidtemplate20192020.Database.DatabaseHelper;
import com.upec.androidtemplate20192020.Model.Etudiant;
import com.upec.androidtemplate20192020.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    ListView vue;

    DatabaseHelper data = null ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       Intent intent = new Intent();
       String group = (String) getIntent().getSerializableExtra(Form3Activity.GROUP);
       String anne =(String) getIntent().getSerializableExtra(Form3Activity.ANNEE);
       String filiere = (String) getIntent().getSerializableExtra(Form3Activity.FILIERE);
        vue = (ListView) findViewById(R.id.liste);
        List<Etudiant> et = null;
        data = new DatabaseHelper(this);
        data.createEtudiant(new Etudiant("n24222","med","ou","1","Informatique","L1"));
        data.createEtudiant(new Etudiant("n155455","mbed2","ou2","1","Informatique","L1"));
        data.createEtudiant(new Etudiant("n1554m55","mend2","ou2","1","Informatique","L1"));
        data.createEtudiant(new Etudiant("n15545k5","med2","ou2","1","Informatique","L1"));
        data.createEtudiant(new Etudiant("n1554515","med2","ou2","1","Informatique","L1"));
        try {

             et = data.getEtudiant(group,filiere,anne);


//            et = data.getEtudiant();
            List<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();

            HashMap<String, String> element;
            for(Etudiant etu:et) {
                element = new HashMap<String, String>();
                element.put("text1", "CNE :"+etu.getCNE());
                element.put("text2", "Nom et Prenom : "+etu.getNom()+" "+etu.getPrenom());
                liste.add(element);
            }

            ListAdapter adapter = new SimpleAdapter(this,
                    liste,
                    android.R.layout.simple_list_item_2,
                    new String[] {"text1", "text2"},
                    new int[] {android.R.id.text1, android.R.id.text2 });
            vue.setAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
