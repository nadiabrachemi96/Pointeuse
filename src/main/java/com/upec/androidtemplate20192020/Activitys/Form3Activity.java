package com.upec.androidtemplate20192020.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.upec.androidtemplate20192020.Database.ConnectDB;
import com.upec.androidtemplate20192020.R;

import java.util.ArrayList;
import java.util.List;

public class Form3Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String GROUP = "idgroupe";
    public static final String FILIERE = "filiere";
    public static final String ANNEE = "annee";
    public String group = "1";
    public String annee1 = "L1";
    public String filiere1 = "Informatique";
    Spinner spinner_groupe = null ;
    Spinner spinner_filiere = null ;
    Spinner spinner_annee = null ;
    Button scanner = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3);

        new ConnectDB(this).execute();
        spinner_groupe = (Spinner) findViewById(R.id.spinner1);
        spinner_annee  = (Spinner) findViewById(R.id.spinner2);
        spinner_filiere  = (Spinner) findViewById(R.id.spinner3);

        spinner_annee.setOnItemSelectedListener(this);

        spinner_groupe.setOnItemSelectedListener(this);

        spinner_filiere.setOnItemSelectedListener(this);



        scanner = (Button) findViewById(R.id.button);


        List<String> groupe = new ArrayList<String>();
        groupe.add("Groupe 1");
        groupe.add("Groupe 2");
        groupe.add("Groupe 3");
        groupe.add("Groupe 4");
        groupe.add("Groupe 5");
        groupe.add("Groupe 6");

        ArrayAdapter<String> adapter_groupe = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groupe);
        //Le layout par défaut est android.R.layout.simple_spinner_dropdown_item
        adapter_groupe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_groupe.setAdapter(adapter_groupe);



        final List<String> annee = new ArrayList<String>();
        annee.add("Annee : L1");
        annee.add("Annee : L2");
        annee.add("Annee : L3");
        annee.add("Annee : M1");
        annee.add("Annee : M2");


        ArrayAdapter<String> adapter_annee = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, annee);
        //Le layout par défaut est android.R.layout.simple_spinner_dropdown_item
        adapter_annee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_annee.setAdapter(adapter_annee);


        final List<String> filiere = new ArrayList<String>();
        filiere.add("Filiere : Informatique");
        filiere.add("Filiere : Mathematique");
        filiere.add("Filiere : Physique");
        filiere.add("Filiere : Chimie");



        ArrayAdapter<String> adapter_filire = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, filiere);
        //Le layout par défaut est android.R.layout.simple_spinner_dropdown_item
        adapter_filire.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_filiere.setAdapter(adapter_filire);


        scanner.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(Form3Activity.this,Main2Activity.class);
                       intent.putExtra(GROUP,group);
                       intent.putExtra(ANNEE,annee1);
                       intent.putExtra(FILIERE,filiere1);
                        startActivity(intent);


                    }
                }
        );


    }

    // adapterView.getAdapter().toString()
    // Groupe : 2131230964
    // Annee  :  2131230965
    // filiere : 2131230966
    @SuppressLint("ResourceType")
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText ( this, String.valueOf(adapterView.getId())
                ,Toast.LENGTH_SHORT ).show ();

     switch (String.valueOf(adapterView.getItemAtPosition(i))){
                case "Groupe 1": group = "1"; break;
                case "Groupe 2": group = "2"; break;
                case "Groupe 3": group = "3"; break;
                case "Groupe 4": group = "4"; break;
                case "Groupe 5": group = "5"; break;
                case "Groupe 6": group = "6"; break;
                case "Annee : L1": annee1 = "L1"; break;
                case "Annee : L2": annee1 = "L2"; break;
                case "Annee : L3": annee1 = "L3"; break;
                case "Annee : M1": annee1 = "M1"; break;
                case "Annee : M2": annee1 = "M2"; break;
                case "Filiere : Informatique": filiere1 = "Informatique"; break;
                case "Filiere : Mathematique": filiere1 = "Mathematique"; break;
                case "Filiere : Physique": filiere1 = "Physique"; break;
                case "Filiere : Chimie": filiere1 = "Chimie"; break;
            }



/*
        if(adapterView.getId() ==  213123072)
        {
            switch (String.valueOf(adapterView.getItemAtPosition(i))){
                case "Annee : L1": annee1 = "L1"; break;
                case "Annee : L2": annee1 = "L2"; break;
                case "Annee : L3": annee1 = "L3"; break;
                case "Annee : M1": annee1 = "M1"; break;
                case "Annee : M2": annee1 = "M2"; break;

            }

            Toast.makeText ( this,  annee1
                    ,Toast.LENGTH_SHORT ).show ();

        }

        if(adapterView.getId() ==  2131230973)
        {
            switch (String.valueOf(adapterView.getItemAtPosition(i))){
                case "Filiere : Informatique": filiere1 = "Informatique"; break;
                case "Filiere : Mathematique": filiere1 = "Mathematique"; break;
                case "Filiere : Physique": filiere1 = "Physique"; break;
                case "Filiere : Chimie": filiere1 = "Chimie"; break;

            }
            Toast.makeText ( this, filiere1
                    ,Toast.LENGTH_SHORT ).show ();

        }
  */  }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
