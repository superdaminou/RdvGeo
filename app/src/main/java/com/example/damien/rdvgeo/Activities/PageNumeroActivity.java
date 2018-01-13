package com.example.damien.rdvgeo.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.damien.rdvgeo.R;
import com.example.damien.rdvgeo.RdvGeoContract;
import com.example.damien.rdvgeo.RendezVousAdapter;
import com.example.damien.rdvgeo.Service.SmsSendService;
import com.example.damien.rdvgeo.entities.RendezVous;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;



public class PageNumeroActivity extends AppCompatActivity {


    EditText longi;
    EditText lat;
    EditText numero;
    EditText dateRdv;
    Button send;
    Button contacts;
    LocationManager locationManager;
    List<String> listContatcs;
    SimpleDateFormat df;

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
        contacts = findViewById(R.id.contacts);


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(contactPickerIntent,1);

            }
            });





        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 df = new SimpleDateFormat("dd/MM/YYYY");



                if (numero.getText() != null && !dateRdv.getText().toString().isEmpty()&& !lat.getText().toString().isEmpty()&& !longi.getText().toString().isEmpty()){

                    if(numero.getText().length()>=3){
                        sendToOne(numero.getText().toString(), lat.getText().toString(), longi.getText().toString(), dateRdv.getText().toString());
                    }else{
                        for(String numero : listContatcs){
                        sendToOne(numero, lat.getText().toString(), longi.getText().toString(), dateRdv.getText().toString());
                        }

                    }


                }else if(numero.getText() != null && !dateRdv.getText().toString().isEmpty() && (lat.getText().toString().isEmpty()|| longi.getText().toString().isEmpty())){
                    try{

                        ActivityCompat.requestPermissions(PageNumeroActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                        if (ContextCompat.checkSelfPermission( PageNumeroActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED) {
                            String locationProvider = LocationManager.GPS_PROVIDER;
                            Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
                        if(numero.getText().length()>=3){
                            sendToOne(numero.getText().toString(), String.valueOf(lastKnownLocation.getLatitude()), String.valueOf(lastKnownLocation.getLongitude()), dateRdv.getText().toString());
                        }else{
                            for(String numero : listContatcs){
                                sendToOne(numero, String.valueOf(lastKnownLocation.getLatitude()), String.valueOf(lastKnownLocation.getLongitude()), dateRdv.getText().toString());
                                }
                            }
                        }


                    }catch (Exception e){

                    }


                }

            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1 :
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    ContentResolver cr = getContentResolver();
                    Cursor cur = cr.query(contactData, null, null, null, null);
                    if (cur.getCount() > 0) {// thats mean some resutl has been found
                        if(cur.moveToNext()) {
                            String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                            String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            Log.e("Names", name);
                            if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                            {
                                // Query phone here. Covered next
                                Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
                                while (phones.moveToNext()) {
                                    listContatcs.add( phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                }
                                phones.close();
                            }

                        }
                    }
                    cur.close();
                }
                break;
        }

    }

    public void sendToOne(String numero, String lat, String longi, String date){
        try{
            Date myDate = df.parse(date);
            RendezVous rdv= new RendezVous(numero,
                    Double.valueOf(lat),
                    Double.valueOf(longi),
                    myDate,
                    "en attente");

            Long id = rdv.createRdv(this,rdv);

            String message = new String();
            message = id.toString()+"/"+getMyPhoneNO()+"/"+lat+"/"+longi+"/"+date+"/";


            Intent smsActivite = new Intent(this, SmsSendService.class);
            smsActivite.putExtra("num", getMyPhoneNO());
            smsActivite.putExtra("message", message);
            startService(smsActivite);

        }catch (ParseException e){
            e.printStackTrace();
        }
    }


    private String getMyPhoneNO() {

        String mPhoneNumber= "00000000";

        ActivityCompat.requestPermissions(PageNumeroActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);



        if (ContextCompat.checkSelfPermission( PageNumeroActivity.this, android.Manifest.permission.READ_PHONE_STATE ) == PackageManager.PERMISSION_GRANTED){
            TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            mPhoneNumber = tMgr.getLine1Number();
            return mPhoneNumber;
        }
        return mPhoneNumber;
    }
}
