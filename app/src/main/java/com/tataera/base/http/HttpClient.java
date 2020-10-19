package com.tataera.base.http;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */


import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebView;


import com.tataera.Utils.DeviceUtils;
import com.tataera.Utils.ResponseHeader;
import com.tataera.base.ETApplication;

import java.util.Arrays;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class HttpClient {
    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int SOCKET_TIMEOUT = 10000;
    private static String sWebViewUserAgent;

    public static AndroidHttpClient getHttpClient() {
        AndroidHttpClient newInstance = AndroidHttpClient.newInstance(DeviceUtils.getUserAgent());
        HttpParams params = newInstance.getParams();
        HttpConnectionParams.setConnectionTimeout(params, 10000);
        HttpConnectionParams.setSoTimeout(params, 10000);
        HttpClientParams.setRedirecting(params, true);
        return newInstance;
    }

    public static AndroidHttpClient getHttpClient(int i) {
        AndroidHttpClient newInstance = AndroidHttpClient.newInstance(DeviceUtils.getUserAgent());
        HttpParams params = newInstance.getParams();
        HttpConnectionParams.setConnectionTimeout(params, i);
        HttpConnectionParams.setSoTimeout(params, i);
        HttpClientParams.setRedirecting(params, true);
        return newInstance;
    }

    public static AndroidHttpClient getLongHttpClient() {
        AndroidHttpClient newInstance = AndroidHttpClient.newInstance(DeviceUtils.getUserAgent());
        HttpParams params = newInstance.getParams();
        HttpConnectionParams.setConnectionTimeout(params, 600000);
        HttpConnectionParams.setSoTimeout(params, 600000);
        HttpClientParams.setRedirecting(params, true);
        return newInstance;
    }

    public static String getWebViewUserAgent() {
        String str;
        synchronized (HttpClient.class) {
            try {
                str = sWebViewUserAgent;
            } catch (Throwable th) {
                Class<HttpClient> cls = HttpClient.class;
                throw th;
            }
        }
        return str;
    }

    public static HttpGet initializeHttpGet(String str, Context context) {
        HttpGet httpGet = new HttpGet(str);
        if (getWebViewUserAgent() == null && context != null) {
            setWebViewUserAgent(new WebView(context).getSettings().getUserAgentString());
        }
        String webViewUserAgent = getWebViewUserAgent();
        if (webViewUserAgent != null) {
            httpGet.addHeader(ResponseHeader.USER_AGENT.getKey(), webViewUserAgent);
        }
        return httpGet;
    }

//    public static void makeTrackingHttpRequest(final Iterable<String> iterable, Context context) {
//        if (iterable != null && context != null) {
//            final AnonymousClass1 r0 = new DownloadTask.DownloadTaskListener() {
//                public void onComplete(String str, DownloadResponse downloadResponse) {
//                    if (downloadResponse == null || downloadResponse.getStatusCode() != 200) {
//                        Log.i("aaaa","Failed to hit tracking endpoint: " + str);
//                    } else if (HttpResponses.asResponseString(downloadResponse) != null) {
//                        Log.i("aaaa","Successfully hit tracking endpoint: " + str);
//                    } else {
//                        Log.i("aaaa","Failed to hit tracking endpoint: " + str);
//                    }
//                }
//            };
//            final Context origApplicationContext = ETApplication.getInstance();
//            new Handler(Looper.getMainLooper()).post(new Runnable() {
//                public void run() {
//                    for (String str : iterable) {
//                        try {
//                            HttpUriRequest initializeHttpGet = HttpClient.initializeHttpGet(str, origApplicationContext);
//                            AsyncTasks.safeExecuteOnExecutor(new DownloadTask(r0), initializeHttpGet);
//                        } catch (Exception e) {
//                            Log.i("aaaa","Failed to hit tracking endpoint: " + str);
//                        }
//                    }
//                }
//            });
//        }
//    }
//
//    public static void makeTrackingHttpRequest(String str, Context context) {
//        makeTrackingHttpRequest((Iterable<String>) Arrays.asList(new String[]{str}), context);
//    }

    public static void setWebViewUserAgent(String str) {
        synchronized (HttpClient.class) {
            try {
                sWebViewUserAgent = str;
            } catch (Throwable th) {
                Class<HttpClient> cls = HttpClient.class;
                throw th;
            }
        }
    }
}
