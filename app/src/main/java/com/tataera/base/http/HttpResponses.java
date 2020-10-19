package com.tataera.base.http;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class HttpResponses {
    private HttpResponses() {
    }

    public static Bitmap asBitmap(DownloadResponse downloadResponse) {
        if (downloadResponse == null) {
            return null;
        }
        byte[] byteArray = downloadResponse.getByteArray();
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    public static JSONObject asJsonObject(DownloadResponse downloadResponse) {
        if (downloadResponse == null) {
            return null;
        }
        try {
            return new JSONObject(new JSONTokener(asResponseString(downloadResponse)));
        } catch (Exception e) {
            return null;
        }
    }

    public static String asResponseString(DownloadResponse downloadResponse) {
        if (downloadResponse == null) {
            return null;
        }
        if (downloadResponse.isGzipContent()) {
            return getGzipContent(downloadResponse.getByteArray());
        } else {
            try {
                return new String(downloadResponse.getByteArray(), "UTF-8");
            } catch (Exception e2) {
                return null;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private static String getGzipContent(byte[] bArr) {
        GZIPInputStream gZIPInputStream = null;
        try {
            gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(gZIPInputStream, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append("\r\n");
                } else {
                    String sb2 = sb.toString();
                    bufferedReader.close();
                    gZIPInputStream.close();
                    return sb2;
                }
            } catch (Exception e) {

                try {
                    bufferedReader.close();
                    gZIPInputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                return null;
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                    gZIPInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                throw th;
            }
        }
    }
}
