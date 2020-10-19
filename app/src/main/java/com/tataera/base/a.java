package com.tataera.base;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */


import android.content.Context;
import android.net.Proxy;
import android.os.Build;
import android.text.TextUtils;
import com.tataera.base.http.SuperDataMan;

public class a {
    private static boolean a;

    static {
        boolean z = true;
        if (SuperDataMan.getPref("agent_forbidden", (Integer) 0).intValue() != 1) {
            z = false;
        }
        a = z;
    }

    public static void a() {
        if (c()) {
            b();
            d();
        }
    }

    public static boolean a(Context context) {
        return a && b(context);
    }

    public static void b() {
        SuperDataMan.savePref("agent_forbidden_first", (Integer) 1);
    }

    private static boolean b(Context context) {
        String host;
        int port;
        if (Build.VERSION.SDK_INT >= 14) {
            host = System.getProperty("http.proxyHost");
            String property = System.getProperty("http.proxyPort");
            if (property == null) {
                property = "-1";
            }
            port = Integer.parseInt(property);
        } else {
            host = Proxy.getHost(context);
            port = Proxy.getPort(context);
        }
        return !TextUtils.isEmpty(host) && port != -1;
    }

    public static boolean c() {
        return SuperDataMan.getPref("agent_forbidden_first", (Integer) 0).intValue() == 0;
    }

    public static void d() {
        a = true;
        SuperDataMan.savePref("agent_forbidden", (Integer) 1);
    }
}
