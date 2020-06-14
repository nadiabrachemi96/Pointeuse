package com.upec.androidtemplate20192020.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

import com.upec.androidtemplate20192020.Database.ConnectDB;
import com.upec.androidtemplate20192020.R;


public class MainActivity extends AppCompatActivity {


    Button listeEtudiant = null;
    ImageButton scanne = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new ConnectDB(this).execute();
        listeEtudiant = (Button) findViewById(R.id.button2);
        scanne = (ImageButton) findViewById(R.id.imageButton) ;

        scanne.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this,FormActivity.class));
                //        startActivity(new Intent(MainActivity.this,MailActivity.class));


                    }
                }
        );
        listeEtudiant.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        startActivity(new Intent(MainActivity.this,Form3Activity.class));


                    }
                }
        );



    }

}
