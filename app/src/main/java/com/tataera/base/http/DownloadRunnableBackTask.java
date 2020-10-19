package com.tataera.base.http;



import org.apache.http.client.methods.HttpPost;

public class DownloadRunnableBackTask extends Thread {
    private HttpPost httpPost;
    private final DownloadTaskListener mDownloadTaskListener;
    private String mUrl;

    public interface DownloadTaskListener {
        void onComplete(String str, DownloadResponse downloadResponse);
    }

    public DownloadRunnableBackTask(DownloadTaskListener downloadTaskListener, HttpPost httpPost2) {
        this.httpPost = httpPost2;
        this.mDownloadTaskListener = downloadTaskListener;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r4 = this;
            r1 = 0
            org.apache.http.client.methods.HttpPost r0 = r4.httpPost
            java.net.URI r0 = r0.getURI()
            java.lang.String r0 = r0.toString()
            r4.mUrl = r0
            android.net.http.AndroidHttpClient r1 = com.tataera.base.http.HttpClient.getHttpClient()     // Catch:{ Exception -> 0x0029, all -> 0x003a }
            com.tataera.base.http.DownloadResponse r0 = new com.tataera.base.http.DownloadResponse     // Catch:{ Exception -> 0x0041 }
            org.apache.http.client.methods.HttpPost r2 = r4.httpPost     // Catch:{ Exception -> 0x0041 }
            org.apache.http.HttpResponse r2 = r1.execute(r2)     // Catch:{ Exception -> 0x0041 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0041 }
            com.tataera.base.http.DownloadRunnableBackTask$DownloadTaskListener r2 = r4.mDownloadTaskListener     // Catch:{ Exception -> 0x0041 }
            java.lang.String r3 = r4.mUrl     // Catch:{ Exception -> 0x0041 }
            r2.onComplete(r3, r0)     // Catch:{ Exception -> 0x0041 }
            if (r1 == 0) goto L_0x0028
        L_0x0025:
            r1.close()
        L_0x0028:
            return
        L_0x0029:
            r0 = move-exception
        L_0x002a:
            java.lang.String r2 = "Download task 2 threw an internal exception"
            com.tataera.base.util.TTLog.d(r2, r0)     // Catch:{ all -> 0x0043 }
            com.tataera.base.http.DownloadRunnableBackTask$DownloadTaskListener r0 = r4.mDownloadTaskListener     // Catch:{ all -> 0x0043 }
            java.lang.String r2 = r4.mUrl     // Catch:{ all -> 0x0043 }
            r3 = 0
            r0.onComplete(r2, r3)     // Catch:{ all -> 0x0043 }
            if (r1 == 0) goto L_0x0028
            goto L_0x0025
        L_0x003a:
            r0 = move-exception
        L_0x003b:
            if (r1 == 0) goto L_0x0040
            r1.close()
        L_0x0040:
            throw r0
        L_0x0041:
            r0 = move-exception
            goto L_0x002a
        L_0x0043:
            r0 = move-exception
            goto L_0x003b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tataera.base.http.DownloadRunnableBackTask.run():void");
    }
}