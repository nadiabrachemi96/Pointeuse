package com.upec.androidtemplate20192020.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.upec.androidtemplate20192020.Database.DatabaseHelper;
import com.upec.androidtemplate20192020.Model.Etudiant;
import com.upec.androidtemplate20192020.R;

import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Liste_Etudiant extends AppCompatActivity {

    public static final String FILEPATH="filepath";
    private static final int STORAGE_CODE=1000;
    private List<String>  exemple = new ArrayList<>();;
    private ArrayAdapter<String> adapter;
    private Button scanner, envoyer;
    private String mFilePath = null ;
    private String filiere = null;
    private String annee = null;
    private String group = null;
    private DatabaseHelper data_base = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste__etudiant);

        data_base = new DatabaseHelper(this);
        data_base.createEtudiant(new Etudiant("U21999477","med","ou","1","Informatique","L1"));

        // on recupere les inforamtions de groupe concerne
        group = (String) getIntent().getSerializableExtra(Form3Activity.GROUP);
        annee =(String) getIntent().getSerializableExtra(Form3Activity.ANNEE);
        filiere = (String) getIntent().getSerializableExtra(Form3Activity.FILIERE);

        envoyer =(Button)findViewById(R.id.pdf);
        scanner = (Button) findViewById(R.id.button2);

        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondeActivity=new Intent ( Liste_Etudiant.this , MainActivity3.class );
                startActivityForResult ( secondeActivity , 1 );

            }
        } );


        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //nous devons exécuter l'autorisation de temps
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    if (checkSelfPermission ( Manifest.permission.WRITE_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_DENIED) {
                        //l'autorisation n'a pas été accordée, l'a demandée
                        String[] permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions ( permissions , STORAGE_CODE );
                    } else {
                        //autorisation déjà garantie, appelez la méthode save pdf pour générer le pdf
                        savePdf ();
                    }
                } else {
                    //système OS <Marshmallow pas nécessaire pour vérifier l'exécution
                    savePdf ();
                }
                Intent intent = new Intent(Liste_Etudiant.this,MailActivity.class);
                intent.putExtra(FILEPATH,mFilePath);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data) {
        super.onActivityResult ( requestCode , resultCode , data );
        if (data != null) {
            ListView list=findViewById ( R.id.liste );
            //recuperer les numéros de la carte scannée
          String cne_etudiant = data.getStringExtra(MainActivity3.CODE_BARRE);

            try {
                // eviter la redondance dans la liste
                List<Etudiant> etudiants = data_base.getEtudiant(cne_etudiant);
                if ( etudiants.size()!=0&&!exemple.contains(cne_etudiant)){
             //       if (etudiants.get(0).getIdClasse() != group ||etudiants.get(0).getFiliere() != filiere ||etudiants.get(0).getAnnee() != annee)
               //         Toast.makeText ( this , etudiants.get(0).getIdClasse()+etudiants.get(0).getFiliere() +etudiants.get(0).getAnnee(), Toast.LENGTH_SHORT ).show ();
                 //   else {
                        exemple.add(cne_etudiant);
                        Toast.makeText(this, " etudiant est   enregister dans la liste ", Toast.LENGTH_SHORT).show();
                   // }
                }else{
                    if(exemple.contains(cne_etudiant))
                        Toast.makeText ( this , " etudiant est deja  enregister  " , Toast.LENGTH_SHORT ).show ();
                    else
                        Toast.makeText ( this , " etudiant n`est pas  enregister dans la base donnees  " , Toast.LENGTH_SHORT ).show ();


                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


            //afficher ma liste d'etudiants
            adapter=new ArrayAdapter<> ( this , android.R.layout.simple_expandable_list_item_1 , exemple );
            list.setAdapter ( adapter );

        }

    }

    private void savePdf() {

        Document pdf=new Document ();
        String mFileName=new SimpleDateFormat( "yyyyMMDD_HHmmss" , Locale.getDefault () ).format ( System.currentTimeMillis () );
        //pdfFile path
         mFilePath = Environment.getExternalStorageDirectory () + "/" + mFileName + ".pdf";
        try {
            PdfWriter.getInstance ( pdf , new FileOutputStream( mFilePath ) );
            pdf.open ();
            pdf.addAuthor ( "Author" );

            pdf.add(new Paragraph("                                      Liste des etudiants presences                     "));

            pdf.add(new Paragraph("           CNE                    "+"               Nom  "+"                  Prenom  "));
            for (String etudiants :exemple){
            List<Etudiant> etudiant = data_base.getEtudiant(etudiants);
            pdf.add(new Paragraph("      "+etudiant.get(0).getCNE()+"                             "+etudiant.get(0).getNom()+"                      "+etudiant.get(0).getPrenom()));
            }

            pdf.close ();
            Toast.makeText ( this , mFileName + ".pdf\nis saved to \n" + mFilePath , Toast.LENGTH_SHORT ).show ();
        } catch (Exception e) {
            //si quelque chose se passe mal provoquant une exception, obtenir et affichez l'exception
            Toast.makeText ( this , e.getMessage () + "jjjj" , Toast.LENGTH_SHORT ).show ();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode , @NonNull String[] permissions , @NonNull int[] grantResults) {
        if (requestCode == STORAGE_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //permission was garanted from popup
                savePdf ();
            } else {
                //permission was danied from popup,show error message
                Toast.makeText ( this , "permission denied...!" , Toast.LENGTH_SHORT ).show ();
            }
        }
    }


    }

