package com.example.damien.rdvgeo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends AppCompatActivity   {


    TextView lieu;
    ListView mListRdv;
    List<RendezVous> mesRdv= new ArrayList<RendezVous>();
    private Button mPasserelle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_acceuil à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_acceuil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_send_demand:
                return true;
            case R.id.action_check_demand:
                Intent map = new Intent(this, MapsActivity.class);
                startActivity(map);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private List<RendezVous> genererRdv(){
        List<RendezVous> rdvs = new ArrayList<RendezVous>();
        rdvs.add(new RendezVous("Florent", "Mon premier tweet !"));
        rdvs.add(new RendezVous("Anne", "Mon premier tweet !"));
        rdvs.add(new RendezVous("Pat", "Mon premier tweet !"));
        rdvs.add(new RendezVous("John", "Mon premier tweet !"));
        return rdvs;
    }

    private void afficherListRdv(){
        mListRdv = (ListView) findViewById(R.id.listRdv);
        mesRdv = genererRdv();

        RendezVousAdapter adapter = new RendezVousAdapter(this, mesRdv);
        mListRdv.setAdapter(adapter);

    }

    private void ouvrirCarte(){
        Intent map= new Intent(this, MapsActivity.class);
        startActivity(map);
    }


    private void addOnClickListener() {
        mListRdv = (ListView) findViewById(R.id.listRdv);


        mListRdv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View itemView = view;

                ouvrirCarte();
            }
        });





    }

}



