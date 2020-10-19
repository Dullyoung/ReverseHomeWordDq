package com.tataera.base.http;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */
public interface HttpHandleListener {
    void onComplete(String str, DownloadResponse downloadResponse, String str2);

    void onFail(String str, String str2);

}
