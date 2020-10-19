package com.tataera.base.http;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */
import android.content.Context;
import android.content.SharedPreferences;

public final class SharedPreferencesHelper {
    public static final String OLD_PREFERENCE_NAME = "mopubSettings";
    public static final String SHARE_PREFERENCE_NAME = "shareadsdk";

    private SharedPreferencesHelper() {
    }

    public static SharedPreferences getAdSdkSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARE_PREFERENCE_NAME, 0);
    }

    public static SharedPreferences getOldSharedPreferences(Context context) {
        return context.getSharedPreferences(OLD_PREFERENCE_NAME, 0);
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        String packageName = context.getPackageName();
        return context.getSharedPreferences(packageName.substring(packageName.lastIndexOf(".") + 1), 0);
    }
}
