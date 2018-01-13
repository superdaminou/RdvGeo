package com.example.damien.rdvgeo.Activities;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.damien.rdvgeo.Notifications;
import com.example.damien.rdvgeo.R;

public class NotificationActivity extends Activity {

    TextView nomNotif;
    TextView prenomNotif;
    TextView dateNotif;
    TextView lieuNotif;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        nomNotif = findViewById(R.id.nomNotif);
        prenomNotif = findViewById(R.id.prenomNotif);
        dateNotif = findViewById(R.id.dateNotif);
        lieuNotif = findViewById(R.id.lieuNotif);

        Intent i = getIntent();
        //On supprime la notification de la liste de notification comme dans la méthode cancelNotify de l'Activity principale
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(Notifications.ID_NOTIFICATION);

        //On affiche les données de la notif
        nomNotif.setText("");

        // On récupère nos deux boutons créés en XML grâce à leur id
        Button boutonAcceptNotif = findViewById(R.id.AcceptNotif);
        Button boutonClearNotif = findViewById(R.id.ClearNotif);

        //On applique un écouteur d'évènement à notre bouton "Créer une notification"
        boutonAcceptNotif.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO
            }
        });

        //On applique un écouteur d'évènement à notre bouton "Supprimer la notification"
        boutonClearNotif.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               //TODO

            }
        });

    }
}