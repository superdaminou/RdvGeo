package com.example.damien.rdvgeo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.damien.rdvgeo.MapsActivity;
import com.example.damien.rdvgeo.MySQLiteHelper;
import com.example.damien.rdvgeo.PageListeContact;
import com.example.damien.rdvgeo.PageNumero;
import com.example.damien.rdvgeo.R;
import com.example.damien.rdvgeo.RdvGeoContract;
import com.example.damien.rdvgeo.RendezVousAdapter;
import com.example.damien.rdvgeo.entities.RendezVous;
import com.example.damien.rdvgeo.entities.User;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {



    TextView lieu;
    ListView mListRdv;
    List<RendezVous> mesRdv = new ArrayList<RendezVous>();
    private Button mPasserelle = null;
    private Button addNotificationBtn;
    private Button deleteNotificationBtn;
    MySQLiteHelper mySQLiteHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mySQLiteHelper = new MySQLiteHelper(this);

        RendezVous rdv = new RendezVous();
        rdv.createRdv(this, "test", 1.2, 1.3, "test");

        setContentView(R.layout.activity_main);
        afficherListRdv();
        addOnClickListener();

        mPasserelle = findViewById(R.id.contact);
        mPasserelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Le premier paramètre est le nom de l'activité actuelle
                // Le second est le nom de l'activité de destination
                Intent secondeActivite = new Intent(MainActivity.this, PageListeContact.class);

                // Puis on lance l'intent !
                startActivity(secondeActivite);
            }
        });

        mPasserelle = findViewById(R.id.numero);
        mPasserelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Le premier paramètre est le nom de l'activité actuelle
                // Le second est le nom de l'activité de destination
                Intent secondeActivite = new Intent(MainActivity.this, PageNumero.class);

                // Puis on lance l'intent !
                startActivity(secondeActivite);
            }
        });

        addNotificationBtn = findViewById(R.id.add_notification);
        addNotificationBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent secondeActivite = new Intent(MainActivity.this, Notifications.class);

                // Puis on lance l'intent !
                startActivity(secondeActivite);
                //Toast.makeText(getBaseContext(), "Ajout d'une notification", Toast.LENGTH_SHORT).show();
            }
        });

        deleteNotificationBtn = findViewById(R.id.delete_notification);
        deleteNotificationBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getBaseContext(), "Suppression d'une notification", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_acceuil à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_acceuil, menu);
        return true;
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


    private List<RendezVous> genererRdv() {
        List<RendezVous> rdvs = new ArrayList<RendezVous>();
        rdvs.add(new RendezVous("Florent", "Mon premier tweet !"));
        rdvs.add(new RendezVous("Anne", "Mon premier tweet !"));
        rdvs.add(new RendezVous("Pat", "Mon premier tweet !"));
        rdvs.add(new RendezVous("John", "Mon premier tweet !"));
        return rdvs;
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
}



