package com.tataera.base;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ETMan {
    private static ETMan xiaoyouMan = new ETMan();
    private Gson mGson;

    private ETMan() {
    }

    public static ETMan getMananger() {
        return xiaoyouMan;
    }

    public Gson getGson() {
        Gson gson;
        synchronized (this) {
            if (this.mGson == null) {
                this.mGson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            }
            gson = this.mGson;
        }
        return gson;
    }
}
