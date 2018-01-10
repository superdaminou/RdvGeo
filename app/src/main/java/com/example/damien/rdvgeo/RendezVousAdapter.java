package com.example.damien.rdvgeo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by damien on 07/12/2017.
 */

public class RendezVousAdapter extends ArrayAdapter<RendezVous>  {

        //tweets est la liste des models à afficher
        public RendezVousAdapter(Context context, List<RendezVous> rdvs) {
            super(context, 0, rdvs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.rendez_vous,parent, false);
            }

            RdvViewHolder viewHolder = (RdvViewHolder) convertView.getTag();
            if(viewHolder == null){
                viewHolder = new RdvViewHolder();
                viewHolder.nom = convertView.findViewById(R.id.nom);
                viewHolder.etat = convertView.findViewById(R.id.etat);
                convertView.setTag(viewHolder);
            }

            //getItem(position) va récupérer l'item [position] de la List<RendezVous> rendezvous
            RendezVous rdv = getItem(position);

            //il ne reste plus qu'à remplir notre vue
            viewHolder.nom.setText(rdv.getNom());
            viewHolder.etat.setText(rdv.getEtat());

            return convertView;
        }

        private class RdvViewHolder{
            public TextView nom;
            public TextView etat;

        }



}
