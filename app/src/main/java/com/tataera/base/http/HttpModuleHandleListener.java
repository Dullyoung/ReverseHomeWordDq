package com.tataera.base.http;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */
public interface HttpModuleHandleListener {
    void onComplete(Object obj, Object obj2);

    void onFail(Object obj, String str);

}
