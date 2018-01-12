package com.example.damien.rdvgeo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Notifications extends Activity {

    // On définit une variable global qui sera
    // l'id unique correspondant à notre notification (bon moi j'ai choisi ma date de naissance)
    public static final int ID_NOTIFICATION = 1988;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        // On récupère nos deux boutons créés en XML grâce à leur id
        Button boutonCreateNotif = (Button) findViewById(R.id.CreateNotif);
        Button boutonClearNotif = (Button) findViewById(R.id.ClearNotif);

        //On applique un écouteur d'évènement à notre bouton "Créer une notification"
        boutonCreateNotif.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //on lance la méthode createNotify (qui comme son nom l'indique créera la notification)
                createNotify();
            }
        });

        //On applique un écouteur d'évènement à notre bouton "Supprimer la notification"
        boutonClearNotif.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //on lance la méthode cancelNotify (qui supprimera la notification de la liste des notifications)
                cancelNotify();
            }
        });

    }

    //Méthode qui crée la notification
    private void createNotify(){
        //On crée un "gestionnaire de notification"
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        //On crée la notification
        //Avec son icône et son texte défilant (optionnel si l'on ne veut pas de texte défilant on met cet argument à null)
        Notification notification = new Notification(R.drawable.icon, "Toc toc, c'est une notification !", System.currentTimeMillis());

        //Le PendingIntent c'est ce qui va nous permettre d'atteindre notre deuxième Activity
        //ActivityNotification sera donc le nom de notre seconde Activity
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, ActivityNotification.class), 0);
        //On définit le titre de la notification
        String titreNotification = "C'est moi la notification !";
        //On définit le texte qui caractérise la notification
        String texteNotification = "Je suis une belle notification...";

        //On configure notre notification avec tous les paramètres que l'on vient de créer
        Notification.Builder builder = new Notification.Builder(this)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(titreNotification)
                .setContentText(texteNotification)
                .setContentIntent(pendingIntent);

        //On ajoute un style de vibration à notre notification
        //L'utilisateur est donc également averti par les vibrations de son téléphone
        //Ici les chiffres correspondent à 0sec de pause, 0.2sec de vibration, 0.1sec de pause, 0.2sec de vibration, 0.1sec de pause, 0.2sec de vibration
        //Vous pouvez bien entendu modifier ces valeurs à votre convenance
        notification.vibrate = new long[] {0,200,100,200,100,200};

        //Enfin on ajoute notre notification et son ID à notre gestionnaire de notification
        notificationManager.notify(ID_NOTIFICATION, builder.build());
    }

    //Méthode pour supprimer de la liste de notification la notification que l'on vient de créer
    private void cancelNotify(){
        //On crée notre gestionnaire de notification
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        //on supprime la notification grâce à son ID
        notificationManager.cancel(ID_NOTIFICATION);
    }
}