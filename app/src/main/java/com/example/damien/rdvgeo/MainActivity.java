package com.example.damien.rdvgeo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {



    ListView mListRdv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListRdv = (ListView) findViewById(R.id.listRdv);

        afficherListRdv();
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
        List<RendezVous> rdvs = genererRdv();

        RendezVousAdapter adapter  = new RendezVousAdapter(MainActivity.this, rdvs);
        mListRdv.setAdapter(adapter);

    }
}
