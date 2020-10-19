package com.tataera.base.http.Generator;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */

import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public abstract class BaseUrlGenerator {
    private static final String IFA_PREFIX = "ifa:";
    private static final String SHA_PREFIX = "sha:";
    private Map<String, String> keyValues;

    /* access modifiers changed from: protected */
    public void addParam(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            this.keyValues.put(str, Uri.encode(str2));
        }
    }

    public abstract void generateParams();

    public List<NameValuePair> getValuePairs() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : this.keyValues.entrySet()) {
            arrayList.add(new BasicNameValuePair((String) next.getKey(), (String) next.getValue()));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void initUrlString() {
        this.keyValues = new HashMap();
    }

    /* access modifiers changed from: protected */
    public void setAUid(String str) {
        addParam("auid", str);
    }

    /* access modifiers changed from: protected */
    public void setApiVersion(String str) {
        addParam("v", str);
    }

    /* access modifiers changed from: protected */
    public void setAppVersion(String str) {
        addParam("av", str);
    }

    /* access modifiers changed from: protected */
    public void setDeviceInfo(String... strArr) {
        StringBuilder sb = new StringBuilder();
        if (strArr != null && strArr.length >= 1) {
            for (int i = 0; i < strArr.length - 1; i++) {
                sb.append(strArr[i]);
                sb.append(",");
            }
            sb.append(strArr[strArr.length - 1]);
            addParam("dn", sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void setDoNotTrack(boolean z) {
        if (z) {
            addParam("dnt", "1");
        }
    }

    /* access modifiers changed from: protected */
    public void setExternalStoragePermission(boolean z) {
        addParam("aest", z ? "1" : "0");
    }

    /* access modifiers changed from: protected */
    public void setUdid(String str) {
        addParam("udid", str);
    }

    public String toParamsString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : this.keyValues.entrySet()) {
            sb.append((String) next.getKey());
            sb.append("=");
            sb.append((String) next.getValue());
            sb.append("&");
        }
        return sb.toString();
    }
}
