package com.example.damien.rdvgeo.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.example.damien.rdvgeo.Activities.NotificationActivity;
import com.example.damien.rdvgeo.R;


public class SmsReceiveService extends BroadcastReceiver {
    public static final int ID_NOTIFICATION = 19;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();

        if (intentExtras != null) {
            /* Get Messages */
            Object[] sms = (Object[]) intentExtras.get("pdus");

            for (int i = 0; i < sms.length; ++i) {
            	/* Parse Each Message */
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String phone = smsMessage.getOriginatingAddress();
                String message = smsMessage.getMessageBody().toString();

                //Toast.makeText(context, phone + ": " + message, Toast.LENGTH_SHORT).show();
                createNotify(context, phone, message);
            }
        }
    }

    //Méthode qui crée la notification
    private void createNotify(Context context, String titreNotification, String texteNotification ){
        //On crée un "gestionnaire de notification"
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //On crée la notification
        //Avec son icône et son texte défilant (optionnel si l'on ne veut pas de texte défilant on met cet argument à null)
        Notification notification = new Notification(R.drawable.icon, "Toc toc, c'est une notification !", System.currentTimeMillis());

        //Le PendingIntent c'est ce qui va nous permettre d'atteindre notre deuxième Activity
        //NotificationActivity sera donc le nom de notre seconde Activity
        Intent i = new Intent(context, NotificationActivity.class);
        i.putExtra("titreNotification", titreNotification);
        i.putExtra("texteNotification", texteNotification);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i , 0);
        //On définit le titre de la notification
        //titreNotification = "C'est moi la notification !";
        //On définit le texte qui caractérise la notification
        //texteNotification = "Je suis une belle notification...";

        //On configure notre notification avec tous les paramètres que l'on vient de créer
        Notification.Builder builder = new Notification.Builder(context)
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

}
