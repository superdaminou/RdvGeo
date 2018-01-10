package com.example.damien.rdvgeo;

import android.provider.BaseColumns;

/**
 * Created by damien on 10/01/2018.
 */

public final class RdvGeoContract {

    private RdvGeoContract(){};


    public static class User implements BaseColumns{
        public static final String TABLE_USER= "user";
        public static final String COLUMN_USERNAME ="username";

    }

}
