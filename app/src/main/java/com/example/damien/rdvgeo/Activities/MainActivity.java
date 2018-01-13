package com.example.damien.rdvgeo.Activities;

import android.Manifest;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.damien.rdvgeo.Service.ValorisationService;
import com.example.damien.rdvgeo.dao.MySQLiteHelper;
import com.example.damien.rdvgeo.R;
import com.example.damien.rdvgeo.dao.RdvGeoContract;
import com.example.damien.rdvgeo.entities.RendezVousAdapter;
import com.example.damien.rdvgeo.Service.SmsSendService;
import com.example.damien.rdvgeo.entities.RendezVous;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {



    TextView lieu;
    ListView mListRdv;
    List<RendezVous> mesRdv = new ArrayList<RendezVous>();
    private Button mPasserelle = null;
    MySQLiteHelper mySQLiteHelper;
    private SQLiteDatabase db;
    ValorisationService valoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mySQLiteHelper = new MySQLiteHelper(this);
        setContentView(R.layout.activity_main);
        afficherListRdv();
        addOnClickListener();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);

        mySQLiteHelper = new MySQLiteHelper(this, RdvGeoContract.CONSTANT.DATABASE_NAME, null,
                RdvGeoContract.CONSTANT.TABLE_VERSION);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if(bundle != null){
            String[] message = bundle.getString("texteNotification").split("/");
            Long id = Long.valueOf(message[0]);
            RendezVous rdv = new RendezVous();
            rdv.accepterRendezVous(this, id);
        }



        mPasserelle = findViewById(R.id.numero);
        mPasserelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Le premier paramètre est le nom de l'activité actuelle
                // Le second est le nom de l'activité de destination
                Intent secondeActivite = new Intent(MainActivity.this, PageNumeroActivity.class);

                // Puis on lance l'intent !
                startActivity(secondeActivite);
            }
        });

    }

    public void envoisms(String num, String message){
        Intent smsActivite = new Intent(MainActivity.this, SmsSendService.class);
        smsActivite.putExtra("num", num);
        smsActivite.putExtra("message", message);
        startService(smsActivite);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_send_demand:
                return true;
            case R.id.action_check_demand:
                Intent map = new Intent(this, MapsActivity.class);
                startActivity(map);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void afficherListRdv() {
        mListRdv = (ListView) findViewById(R.id.listRdv);
        mesRdv =RendezVous.getAll(this);

        RendezVousAdapter adapter = new RendezVousAdapter(this, mesRdv);
        mListRdv.setAdapter(adapter);

    }

    private void ouvrirCarte(RendezVous rdv) {
        Intent map = new Intent(this, MapsActivity.class);
        map.putExtra("lat", rdv.getCoordonneeX());
        map.putExtra("longi",rdv.getCoordonneeY());
        startActivity(map);
    }


    private void addOnClickListener() {
        mListRdv = findViewById(R.id.listRdv);


        mListRdv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View itemView = view;
                RendezVous rdv = (RendezVous) parent.getAdapter().getItem(position);
                ouvrirCarte(rdv);
            }
        });


    }

 /*
    public static final int NOTIFICATION_ID = 42;

    public void createNotification() {
        //Récupération du notification Manager
        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        final Intent launchNotifiactionIntent = new Intent(this, MainActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, launchNotifiactionIntent, PendingIntent.FLAG_ONE_SHOT);

        Notification.Builder builder = new Notification.Builder(this)
                .setWhen(System.currentTimeMillis())
                //.setTicker(notificationTitle)
                //.setSmallIcon(R.drawable.notification)
                .setContentTitle(getResources().getString(R.string.notification_title))
                .setContentText(getResources().getString(R.string.notification_desc))
                .setContentIntent(pendingIntent);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
    */
}



