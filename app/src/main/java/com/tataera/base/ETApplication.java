package com.tataera.base;

import android.content.Context;

import java.lang.ref.WeakReference;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */
public class ETApplication {
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static Context getInstance() {
        return mContext;
    }
}
