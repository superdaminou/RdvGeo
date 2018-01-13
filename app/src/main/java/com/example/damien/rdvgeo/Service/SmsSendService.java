package com.example.damien.rdvgeo.Service;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.app.IntentService;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.net.Uri;
import java.util.ArrayList;

public class SmsSendService extends IntentService {

    /** Called when the activity is first created. */

    private final static String TAG = "IntentServiceExample";

    public SmsSendService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String num = intent.getStringExtra("num");
        String message = intent.getStringExtra("message");
        Log.d("Send sms:","J'ai bien recu la demande ");

        try {

            PackageManager pm = this.getPackageManager();

            if (!pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY) &&
                    !pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_CDMA)) {
                Toast.makeText(this, "Sorry, your device probably can't send SMS...", Toast.LENGTH_SHORT).show();

            }

            enovyerMessage(num, message);

        } catch (Exception ex) {
            Toast.makeText(this,
                    "Error in MainActivity.onCreate: " + ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void enovyerMessage(String strPhone, String strMessage){
        SmsManager sms;

        sms = SmsManager.getDefault();

        ArrayList<String> messageParts = sms.divideMessage(strMessage);
        Log.d("EnvoyerSms:","j'envoi le message");
        sms.sendMultipartTextMessage(strPhone,
                null, messageParts,
                null, null);

        Toast.makeText(this, "Sent.", Toast.LENGTH_SHORT).show();
    }
}
