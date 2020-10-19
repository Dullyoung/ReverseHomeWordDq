package com.tataera;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */


import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class RequestParams {
    private static Map<String, String> requestInfos = new HashMap();

    public static void fillRequestInfo(List<NameValuePair> list) {
        for (Map.Entry next : requestInfos.entrySet()) {
            try {
                list.add(new BasicNameValuePair((String) next.getKey(), URLEncoder.encode((String) next.getValue(), "utf-8")));
            } catch (Exception e) {
            }
        }
    }

    public static Map<String, String> getRequestInfos() {
        return requestInfos;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r0 = requestInfos.get(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getValue(java.lang.String r1) {
        /*
            if (r1 != 0) goto L_0x0005
            java.lang.String r0 = ""
        L_0x0004:
            return r0
        L_0x0005:
            java.util.Map<java.lang.String, java.lang.String> r0 = requestInfos
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x0004
            java.lang.String r0 = ""
            goto L_0x0004
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tataera.sdk.extra.common.RequestParams.getValue(java.lang.String):java.lang.String");
    }

    public static void put(String str, String str2) {
        if (str != null && str2 != null) {
            requestInfos.put(str, str2);
        }
    }
}
