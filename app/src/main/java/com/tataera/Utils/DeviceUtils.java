package com.tataera.Utils;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */


import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.os.StatFs;
import android.provider.Settings;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Iterator;
import org.apache.http.conn.util.InetAddressUtils;

public class DeviceUtils {
    private static final int MAX_DISK_CACHE_SIZE = 104857600;
    private static final int MAX_MEMORY_CACHE_SIZE = 31457280;
    private static final int MIN_DISK_CACHE_SIZE = 31457280;

    public enum IP {
        IPv4,
        IPv6;

        /* access modifiers changed from: private */
        public boolean matches(String str) {
            switch (this) {
                case IPv4:
                    return InetAddressUtils.isIPv4Address(str);
                case IPv6:
                    return InetAddressUtils.isIPv6Address(str);
                default:
                    return false;
            }
        }

        /* access modifiers changed from: private */
        public String toString(String str) {
            switch (this) {
                case IPv4:
                    return str;
                case IPv6:
                    return str.split("%")[0];
                default:
                    return null;
            }
        }
    }

    private DeviceUtils() {
    }

    public static long diskCacheSizeBytes(File file) {
        long j;
        try {
            StatFs statFs = new StatFs(file.getAbsolutePath());
            j = (((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount())) / 50;
        } catch (IllegalArgumentException e) {

            j = 31457280;
        }
        return Math.max(Math.min(j, 104857600), 31457280);
    }



    public static String getIpAddress(IP ip) {
        Iterator<NetworkInterface> it = null;
        try {
            it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (it.hasNext()) {
            Iterator<InetAddress> it2 = Collections.list(((NetworkInterface) it.next()).getInetAddresses()).iterator();
            while (true) {
                if (it2.hasNext()) {
                    InetAddress inetAddress = (InetAddress) it2.next();
                    if (!inetAddress.isLoopbackAddress()) {
                        String upperCase = inetAddress.getHostAddress().toUpperCase();
                        if (ip.matches(upperCase)) {
                            return ip.toString(upperCase);
                        }
                    }
                }
            }
        }
        return null;
    }

    public static String getUserAgent() {
        return System.getProperty("http.agent");
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }


        try {
            return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo().isConnected();
        } catch (NullPointerException e) {
            return false;
        }
    }


}