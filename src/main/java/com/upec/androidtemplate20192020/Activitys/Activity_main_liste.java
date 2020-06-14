package com.upec.androidtemplate20192020.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.upec.androidtemplate20192020.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Activity_main_liste extends AppCompatActivity {
    //Liste etudiants
    List<String> exemple=new ArrayList<> ();
    // liste des etudiants sans doublons(numéro etudiant est unique)
    List<String> numeroCarte=new ArrayList<> ();
    ArrayAdapter<String> adapter;
    //pourtant pour revenir à l'activite qui enregistre les PDF
    Button pdf=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main_liste );

        pdf=findViewById ( R.id.pdf );
        pdf.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
  /*              //apres avoir récuper la liste des etudiant on l'envoie vers l'activité FormatActivity qui crée le pdf
                Intent intent=new Intent (Activity_main_liste.this , FormActivity.class );
                for (int i=0; i < numeroCarte.size (); i++) {
                    //valeur représente la clef pour recupérer notre liste dans l'autre activité
                    intent.putExtra ( String.valueOf ( i ), numeroCarte.get ( i ) );
                }
                startActivity ( intent );
    */        }
        } );

        Button scanner=findViewById ( R.id.button2 );
        scanner.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
         //       Intent secondeActivity=new Intent ( Activity_main_liste.this , MainActivity3.class );
           //     startActivityForResult ( secondeActivity , 1 );
            }
        } );

    }
/*
    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data) {
        super.onActivityResult ( requestCode , resultCode , data );
        if (data != null) {
            ListView list=findViewById ( R.id.liste );
            //recuperer les numéros de la carte scannée
            exemple.add ( data.getStringExtra ( MainActivity3.CODE_BARRE ) );
            Set<String> mySet=new HashSet<> ( exemple );
            // Pour éviter d'avoir des doublons dans ma liste
            numeroCarte=new ArrayList<> ( mySet );
            //afficher ma liste d'etudiants
            adapter=new ArrayAdapter<> ( this , android.R.layout.simple_expandable_list_item_1 , numeroCarte );
            list.setAdapter ( adapter );

        }

    }
*/}
