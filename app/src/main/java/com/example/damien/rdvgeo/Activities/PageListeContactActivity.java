package com.example.damien.rdvgeo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.damien.rdvgeo.R;


public class PageListeContactActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_liste_contact);

        // On récupère l'intent qui a lancé cette activité
        Intent i = getIntent();

        // Puis on récupère l'âge donné dans l'autre activité, ou 0 si cet extra n'est pas dans l'intent
        //int age = i.getIntExtra(MainActivity.AGE, 0);

    }
}
