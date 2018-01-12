package com.example.damien.rdvgeo;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.ArrayList;

abstract class SmsSend extends Activity {

    String strPhone;
    String strMessage;

    public SmsSend(String strPhone, String strMessage) {
        this.strPhone = strPhone;
        this.strMessage = strMessage;
    }


    public void sendSms() throws Exception {

        strPhone = "XXXXXXXXXXX";

        strMessage = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890TEST";

        SmsManager sms = SmsManager.getDefault();

        ArrayList<String> messageParts = sms.divideMessage(strMessage);

    /* ---- Preparing Intents To Check While Sms Sent & Delivered ---- */

        Context curContext = this.getApplicationContext();

        int partsCount = messageParts.size();

        ArrayList<PendingIntent> sentPendings = new ArrayList<PendingIntent>(partsCount);

        ArrayList<PendingIntent> deliveredPendings = new ArrayList<PendingIntent>(partsCount);

        for (int i = 0; i < partsCount; i++) {

        /* Adding Sent PendingIntent For Message Part */

            PendingIntent sentPending = PendingIntent.getBroadcast(curContext,
                    0, new Intent("SENT"), 0);

            curContext.registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context arg0, Intent arg1) {
                    switch (getResultCode()) {
                        case Activity.RESULT_OK:
                            Toast.makeText(getBaseContext(), "Sent.",
                                    Toast.LENGTH_LONG).show();
                            break;
                        case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                            Toast.makeText(getBaseContext(),
                                    "Not Sent: Generic failure.", Toast.LENGTH_LONG)
                                    .show();
                            break;
                        case SmsManager.RESULT_ERROR_NO_SERVICE:
                            Toast.makeText(
                                    getBaseContext(),
                                    "Not Sent: No service (possibly, no SIM-card).",
                                    Toast.LENGTH_LONG).show();
                            break;
                        case SmsManager.RESULT_ERROR_NULL_PDU:
                            Toast.makeText(getBaseContext(), "Not Sent: Null PDU.",
                                    Toast.LENGTH_LONG).show();
                            break;
                        case SmsManager.RESULT_ERROR_RADIO_OFF:
                            Toast.makeText(
                                    getBaseContext(),
                                    "Not Sent: Radio off (possibly, Airplane mode enabled in Settings).",
                                    Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }, new IntentFilter("SENT"));

            sentPendings.add(sentPending);

    /* Adding Delivered PendingIntent For Message Part */

            PendingIntent deliveredPending = PendingIntent.getBroadcast(
                    curContext, 0, new Intent("DELIVERED"), 0);

            curContext.registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context arg0, Intent arg1) {
                    switch (getResultCode()) {
                        case Activity.RESULT_OK:
                            Toast.makeText(getBaseContext(), "Delivered.",
                                    Toast.LENGTH_LONG).show();
                            break;
                        case Activity.RESULT_CANCELED:
                            Toast.makeText(getBaseContext(),
                                    "Not Delivered: Canceled.", Toast.LENGTH_LONG)
                                    .show();
                            break;
                    }
                }
            }, new IntentFilter("DELIVERED"));

            deliveredPendings.add(deliveredPending);
        }

/* --------------------------------------------------------------- */

        sms.sendMultipartTextMessage(strPhone, null, messageParts, sentPendings, deliveredPendings);
    }
}
