package com.example.damien.rdvgeo.Activities;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.damien.rdvgeo.R;
import com.example.damien.rdvgeo.Service.SmsSendService;
import com.example.damien.rdvgeo.Service.SmsReceiveService;
import com.example.damien.rdvgeo.entities.RendezVous;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationActivity extends Activity {

    TextView nomNotif;
    TextView dateNotif;
    TextView lieuNotif;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        nomNotif = findViewById(R.id.nomNotif);
        dateNotif = findViewById(R.id.dateNotif);
        lieuNotif = findViewById(R.id.lieuNotif);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        final String message = bundle.getString("texteNotification");


        final RendezVous rdv = getRendezVousFromNotif(message);


        //On supprime la notification de la liste de notification comme dans la méthode cancelNotify de l'Activity principale
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(SmsReceiveService.ID_NOTIFICATION);

        //On affiche les données de la notif
        nomNotif.setText(rdv.getNom().toString());

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
               //L'utilisateur refuse le rdv on renvoi un message d'annulation
                retourNotif(rdv.getNom().toString(),"Non", message);
            }
        });

    }

    public RendezVous getRendezVousFromNotif(String notif){
        String[] carac = notif.split("/");

        return new RendezVous(carac[1], Double.valueOf(carac[2]), Double.valueOf(carac[3]), getDate(carac[4]), "en attente" );
    }


    private Date getDate(String dateStr){
        try{
            SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
            return parser.parse(dateStr);
            //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            //String formattedDate = formatter.format(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    public void retourNotif(String nom, String notif, String message){
        String[] id =  message.split("/");
        String retour = id[0]+"/"+notif;


        Intent smsActivite = new Intent(this, SmsSendService.class);
        smsActivite.putExtra("num", nom);
        smsActivite.putExtra("message", retour);
        startService(smsActivite);
    }
}
