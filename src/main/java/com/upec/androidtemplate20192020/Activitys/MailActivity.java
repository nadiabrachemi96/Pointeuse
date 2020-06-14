package com.upec.androidtemplate20192020.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.upec.androidtemplate20192020.R;


public class MailActivity extends AppCompatActivity {

    Button annuler=null, envoyer = null;
    EditText adresse = null,objet = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        adresse = (EditText) findViewById(R.id.editText);
        objet = (EditText) findViewById(R.id.editText2);

        final String group = (String) getIntent().getSerializableExtra(Liste_Etudiant.FILEPATH);
        envoyer = (Button) findViewById(R.id.button4);

        annuler = (Button)findViewById(R.id.button3);

        envoyer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                     Intent intent = new Intent(Intent.ACTION_SEND);

                     String ad = adresse.getText().toString();
                     String ob = objet.getText().toString();
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String []{ad});
                        intent.putExtra(Intent.EXTRA_SUBJECT,ob);
                        intent.putExtra(Intent.EXTRA_TEXT,group);
                        intent.putExtra(Intent.EXTRA_STREAM,Uri.parse("file://"+group));
                        startActivity(Intent.createChooser(intent,"chooser an email client"));
                    }
                }
        );


        annuler.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        startActivity(new Intent(MailActivity.this,Liste_Etudiant.class));
                    }
                }
        );




    }
}
