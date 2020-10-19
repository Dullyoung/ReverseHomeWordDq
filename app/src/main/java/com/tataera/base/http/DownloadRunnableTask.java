package com.tataera.base.http;

import android.net.http.AndroidHttpClient;
import android.os.Handler;
import android.util.Log;

import com.tataera.base.http.DownloadResponse;

 
import org.apache.http.client.methods.HttpPost;

public class DownloadRunnableTask extends Thread {
    private Handler handler = new Handler();
    private HttpPost httpPost;
    /* access modifiers changed from: private */
    public final DownloadTaskListener mDownloadTaskListener;
    private int mTimeout = -1;
    /* access modifiers changed from: private */
    public String mUrl;

    public interface DownloadTaskListener {
        void onComplete(String str, DownloadResponse downloadResponse);
    }

    public DownloadRunnableTask(DownloadTaskListener downloadTaskListener, HttpPost httpPost2) {
        this.httpPost = httpPost2;
        this.mDownloadTaskListener = downloadTaskListener;
    }

    public DownloadRunnableTask(DownloadTaskListener downloadTaskListener, HttpPost httpPost2, int i) {
        this.httpPost = httpPost2;
        this.mDownloadTaskListener = downloadTaskListener;
        this.mTimeout = i;
    }

     @Override
    public void run() {
         Throwable th;
         AndroidHttpClient androidHttpClient = null;
         AndroidHttpClient androidHttpClient2 = null;
         this.mUrl = this.httpPost.getURI().toString();
         try {
             androidHttpClient = this.mTimeout < 0 ? HttpClient.getLongHttpClient() : HttpClient.getHttpClient(this.mTimeout);
             try {
                 final DownloadResponse downloadResponse = new DownloadResponse(androidHttpClient.execute(this.httpPost));
                 this.handler.post(new Runnable() {
                     public void run() {
                         DownloadRunnableTask.this.mDownloadTaskListener.onComplete(DownloadRunnableTask.this.mUrl, downloadResponse);
                     }
                 });
                 if (androidHttpClient == null) {
                     return;
                 }
             } catch (Exception e) {

                 try {
                     Log.i("aaaa","Download task threw an internal exception", e);
                     this.handler.post(new Runnable() {
                         public void run() {
                             DownloadRunnableTask.this.mDownloadTaskListener.onComplete(DownloadRunnableTask.this.mUrl, (DownloadResponse) null);
                         }
                     });
                     if (androidHttpClient == null) {
                         return;
                     }
                     androidHttpClient.close();
                 } catch (Throwable th2) {
                     th = th2;
                     androidHttpClient2 = androidHttpClient;
                     if (androidHttpClient2 != null) {
                     }
                     throw th;
                 }
             }
         } catch (Exception e2) {
             
             androidHttpClient = null;
         } catch (Throwable th3) {
             th = th3;
             if (androidHttpClient2 != null) {
                 androidHttpClient2.close();
             }
             
         }
         androidHttpClient.close();


     }
}
