package com.tataera.user;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */


import android.util.Base64;

import com.tataera.Bean.LoginUser;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class UserConfig {
    public static int APP_LOGO_RES = 0;
    public static String APP_LOGO_TEXT = "学习英语更轻松";
    public static String APP_NAME = "英语百科";
    public static String APP_UPDATE_HANDLER = "";
    public static String CHANNEL = "tt";
    public static String COMPANY_NAME = "塔塔时代";
    public static String FEED_TIP_TAIL = "";
    public static String QQ_APP_ID = "1105441371";
    public static String QQ_APP_KEY = "Qej42RQ40YvwwTV5";
    public static String QQ_SCOPE = "all";
    public static boolean TATA_MENU_FAVOR_SUPPORT = true;
    public static boolean TATA_SUPPORT_TATA_SHARE = true;
    public static boolean TATA_SUPPORT_THREE_LOGIN = true;
    public static String WEIBO_APPKEY = "1361293014";
    public static String WEIBO_CALLBACK = "http://etata.tatatimes.com/callback";
    public static String WX_APP_ID = "";
    public static String WX_SECRET_KEY = "";
    private static LoginUser loginUser = new LoginUser();
    private static Map<String, String> mLocationInfos = new HashMap();
    public static String product = "tata";
    public static String verCode = "v1";

    private static String _getLocationInfo() {
        if (mLocationInfos == null) {
            return "{}";
        }
        try {
            return new JSONObject(mLocationInfos).toString();
        } catch (Exception e) {
            return "{}";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
        r0 = mLocationInfos.get("city");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getCity() {
        /*
            java.util.Map<java.lang.String, java.lang.String> r0 = mLocationInfos
            if (r0 != 0) goto L_0x0007
            java.lang.String r0 = ""
        L_0x0006:
            return r0
        L_0x0007:
            java.util.Map<java.lang.String, java.lang.String> r0 = mLocationInfos
            java.lang.String r1 = "city"
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x0006
            java.lang.String r0 = ""
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tataera.base.UserConfig.getCity():java.lang.String");
    }

    public static String getLocationInfo() {
        return new StringBuffer(Base64.encodeToString(_getLocationInfo().getBytes(), 0)).reverse().toString();
    }

    public static LoginUser getLoginUser() {
        return loginUser;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
        r0 = mLocationInfos.get("prov");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getProvince() {
        /*
            java.util.Map<java.lang.String, java.lang.String> r0 = mLocationInfos
            if (r0 != 0) goto L_0x0007
            java.lang.String r0 = ""
        L_0x0006:
            return r0
        L_0x0007:
            java.util.Map<java.lang.String, java.lang.String> r0 = mLocationInfos
            java.lang.String r1 = "prov"
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x0006
            java.lang.String r0 = ""
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tataera.base.UserConfig.getProvince():java.lang.String");
    }

    public static void putLocations(Map<String, String> map) {
        mLocationInfos = map;
    }

    public static void setLoginUser(String str, String str2, String str3, String str4) {
        loginUser.setLoginType(str2);
        loginUser.setNickName(str3);
        loginUser.setOpenId(str);
        loginUser.setUserheadImgUrl(str4);
    }
}
