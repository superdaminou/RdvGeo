package com.example.damien.rdvgeo;

import android.provider.BaseColumns;

/**
 * Created by damien on 10/01/2018.
 */

public final class RdvGeoContract {

    private RdvGeoContract() {
    }

    ;

    public static class CONSTANT implements BaseColumns{
        public static final String DATABASE_NAME= "rdvgeo.db";
        public static final int TABLE_VERSION =  1 ;

    }

}
