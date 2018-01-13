package com.example.damien.rdvgeo.Service;

import android.content.Context;

import com.example.damien.rdvgeo.entities.RendezVous;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by damien on 13/01/2018.
 */

public abstract class ValorisationService  {

    public static void valorisationRdv(Context context){
        List<RendezVous> listRdv = new ArrayList<>();
        listRdv.add(new RendezVous("Patrick", -12.5, 12.5,  new Date(12,12,12), "en attente" ));
        listRdv.add(new RendezVous("John", 12.5, -12.5,  new Date(11,12,23), "accepte" ));
        listRdv.add(new RendezVous("Yolo", 110, -112,  new Date(95,1,1), "en attente" ));

        for(RendezVous rdv : listRdv){
            rdv.createRdv(context,rdv);
        }
    }

}
