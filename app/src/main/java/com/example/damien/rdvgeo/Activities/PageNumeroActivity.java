package com.example.damien.rdvgeo.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.damien.rdvgeo.R;
import com.example.damien.rdvgeo.RendezVousAdapter;
import com.example.damien.rdvgeo.entities.RendezVous;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Nicolas on 10/01/2018.
 */

public class PageNumeroActivity extends AppCompatActivity {


    EditText longi;
    EditText lat;
    EditText numero;
    EditText dateRdv;
    Button send;
    LocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_numero);

        // On récupère l'intent qui a lancé cette activité
        Intent i = getIntent();

        longi = findViewById(R.id.longi);
        lat = findViewById(R.id.lat);
        send = findViewById(R.id.send);
        numero = findViewById(R.id.numeroEnvoi);
        dateRdv = findViewById(R.id.dateRdv);


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");
                java.util.Date myDate;



                if (numero.getText() != null && dateRdv.getText() != null && lat.getText() != null && longi.getText()!= null){
                    try{
                        RendezVous rdv= new RendezVous(numero.toString(),
                                Double.valueOf(lat.toString()),
                                Double.valueOf(longi.toString()),
                                df.parse(dateRdv.toString()),
                                "en attente");

                    }catch (ParseException e){
                    }
                }else if(numero.getText() != null && dateRdv.getText() != null && (lat.getText() == null || longi.getText()== null)){
                    ActivityCompat.requestPermissions(PageNumeroActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    if (ContextCompat.checkSelfPermission( PageNumeroActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED){
                        String locationProvider = LocationManager.NETWORK_PROVIDER;
                        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);

                    }


                }

            }
        });



        // Puis on récupère l'âge donné dans l'autre activité, ou 0 si cet extra n'est pas dans l'intent
        //int age = i.getIntExtra(MainActivity.AGE, 0);

    }
}
