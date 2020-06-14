package com.upec.androidtemplate20192020.Activitys;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.upec.androidtemplate20192020.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity3 extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    public static final String CODE_BARRE = "code barre";
    private ZXingScannerView scannerView;

    private TextView txtResultat;

    private String code;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main31);

            //


            scannerView = (ZXingScannerView)findViewById(R.id.zxcan);
            txtResultat = (TextView)findViewById(R.id.txt_result);

            Dexter.withActivity(this)
                    .withPermission(Manifest.permission.CAMERA)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {

                           scannerView.setResultHandler(MainActivity3.this);
                           scannerView.startCamera();
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {

                            Toast.makeText(MainActivity3.this, "you must acceppt this permission", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                        }
                    })
                    .check();
        }


    @Override
    protected void onDestroy() {
          scannerView.stopCamera();
            super.onDestroy();
    }

    @Override
    public void handleResult(Result rawResult) {
        // ici on recois le resultat

        code = rawResult.getText();
        txtResultat.setText(rawResult.getText());

        Intent it = new Intent();
        it.putExtra(CODE_BARRE, code);
        setResult(Liste_Etudiant.RESULT_OK, it);
        finish();

    }
}







