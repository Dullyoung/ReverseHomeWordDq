package com.tataera.base.http;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */


import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.tataera.ClientMetadata;


import com.tataera.Bean.LoginUser;
import com.tataera.Utils.AndroidUtils;
import com.tataera.Utils.ReflectionUtil;
import com.tataera.Utils.StatisticsUtils;
import com.tataera.Utils.TimeUtil;
import com.tataera.base.ETApplication;
import com.tataera.base.ETMan;


import com.tataera.base.http.Generator.SwUrlGenerator;
import com.tataera.sign.c;
import com.tataera.user.UserConfig;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SuperDataMan {
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    public static int MARKET_REMIND_TIMES = 10;
    public static String SETTING_2G = "setting_2G";
    public static String SETTING_PLAY_BACKGROUND = "setting_play_background";
    public static String SETTING_PLAY_CLOSE = "setting_play_close";
    public static String SETTING_PUSH = "setting_push";

    public static void addStartTimes() {
        savePref("config_start_times", Integer.valueOf(getStartTimes() + 1));
    }

    public static boolean canToMarketScores() {
        addStartTimes();
        return !isToMarketScores() && getStartTimes() % MARKET_REMIND_TIMES == MARKET_REMIND_TIMES + -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String genHMAC(String str, String str2) {
        byte[] bArr;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), HMAC_SHA1_ALGORITHM);
            Mac instance = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            instance.init(secretKeySpec);
            bArr = c.b(instance.doFinal(str.getBytes()));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            System.err.println(e.getMessage());
            bArr = null;
        }
        if (bArr != null) {
            return new String(bArr);
        }
        return null;
    }

    private String generateSign(List<NameValuePair> list) {
        String str;
        TreeMap<String,String> treeMap = new TreeMap();
        for (NameValuePair next : list) {
            String name = next.getName();
            try {
                str = URLDecoder.decode(next.getValue(), "utf-8");
            } catch (UnsupportedEncodingException e) {

                str = null;
            }
            if (name != null && str != null && !"".equals(name.trim()) && !"".equals(str.trim())) {
                treeMap.put(name, str);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : treeMap.entrySet()) {
            sb.append((String) entry.getKey());
            sb.append("=");
            sb.append((String) entry.getValue());
            sb.append("&");
        }
        return genHMAC(sb.substring(0, sb.length() - 1), "adkjffdkajkjkjnm");

    }

    public static String getAdSdkPref(String str, String str2) {
        return SharedPreferencesHelper.getAdSdkSharedPreferences(ETApplication.getInstance()).getString(str, str2);
    }

    public static Integer getPref(String str, Integer num) {
        Integer valueOf = Integer.valueOf(SharedPreferencesHelper.getSharedPreferences(ETApplication.getInstance()).getInt(str, num.intValue()));
        return valueOf == num ? Integer.valueOf(SharedPreferencesHelper.getOldSharedPreferences(ETApplication.getInstance()).getInt(str, num.intValue())) : valueOf;
    }

    public static Long getPref(String str, Long l) {
        return Long.valueOf(SharedPreferencesHelper.getSharedPreferences(ETApplication.getInstance()).getLong(str, l.longValue()));
    }

    public static String getPref(String str, String str2) {
        String string = SharedPreferencesHelper.getSharedPreferences(ETApplication.getInstance()).getString(str, str2);
        return (TextUtils.isEmpty(string) || string.equals(str2)) ? SharedPreferencesHelper.getOldSharedPreferences(ETApplication.getInstance()).getString(str, str2) : string;
    }

    public static boolean getPref(String str, boolean z) {
        return SharedPreferencesHelper.getSharedPreferences(ETApplication.getInstance()).getBoolean(str, z);
    }

    public static int getStartTimes() {
        return getPref("config_start_times", (Integer) 0).intValue();
    }

    public static boolean isToMarketScores() {
        return "true".equals(getPref("config_marketscores_done", "false"));
    }

    public static boolean isUpdateRemindToday() {
        return TimeUtil.getDate(System.currentTimeMillis()).equals(getPref("config_update_check_today", ""));
    }

    public static void removePref(String str) {
        SharedPreferences.Editor edit = SharedPreferencesHelper.getSharedPreferences(ETApplication.getInstance()).edit();
        edit.remove(str);
        edit.commit();
        SharedPreferences.Editor edit2 = SharedPreferencesHelper.getOldSharedPreferences(ETApplication.getInstance()).edit();
        edit2.remove(str);
        edit2.commit();
    }

    public static void saveAdSdkPref(String str, String str2) {
        SharedPreferences.Editor edit = SharedPreferencesHelper.getAdSdkSharedPreferences(ETApplication.getInstance()).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static void savePref(String str, Integer num) {
        SharedPreferences.Editor edit = SharedPreferencesHelper.getSharedPreferences(ETApplication.getInstance()).edit();
        edit.putInt(str, num.intValue());
        edit.commit();
    }

    public static void savePref(String str, Long l) {
        SharedPreferences.Editor edit = SharedPreferencesHelper.getSharedPreferences(ETApplication.getInstance()).edit();
        edit.putLong(str, l.longValue());
        edit.commit();
    }

    public static void savePref(String str, String str2) {
        SharedPreferences.Editor edit = SharedPreferencesHelper.getSharedPreferences(ETApplication.getInstance()).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static void savePref(String str, boolean z) {
        SharedPreferences.Editor edit = SharedPreferencesHelper.getSharedPreferences(ETApplication.getInstance()).edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public static void savePref(HashMap<String, String> hashMap) {
        SharedPreferences.Editor edit = SharedPreferencesHelper.getSharedPreferences(ETApplication.getInstance()).edit();
        for (Map.Entry next : hashMap.entrySet()) {
            edit.putString((String) next.getKey(), (String) next.getValue());
        }
        edit.commit();
    }

    public static void setToMarketScores() {
        savePref("config_marketscores_done", "true");
    }

    private void statisticsParams(List<NameValuePair> list) {
        try {
            list.add(new BasicNameValuePair("firstInstallTime", URLEncoder.encode(TimeUtil.getTime(StatisticsUtils.getFirstInstallTime()), "utf-8")));
            list.add(new BasicNameValuePair("startCount", URLEncoder.encode(String.valueOf(StatisticsUtils.getStartCount()), "utf-8")));
            list.add(new BasicNameValuePair("totalDetailPage", URLEncoder.encode(String.valueOf(StatisticsUtils.getTotalDetailPage()), "utf-8")));
            list.add(new BasicNameValuePair("totalSearchBook", URLEncoder.encode(String.valueOf(StatisticsUtils.getTotalSearchBook()), "utf-8")));
            list.add(new BasicNameValuePair("totalAdFeedBack", URLEncoder.encode(String.valueOf(StatisticsUtils.getTotalAdFeedBackAd()), "utf-8")));
            list.add(new BasicNameValuePair("useTime", URLEncoder.encode(String.valueOf(((long) StatisticsUtils.getUseTime()) / 1000), "utf-8")));
            list.add(new BasicNameValuePair("totalDetail", URLEncoder.encode(String.valueOf(StatisticsUtils.getTotalDetail()), "utf-8")));
            String beforeMonteRecord = StatisticsUtils.getBeforeMonteRecord();
            if (!TextUtils.isEmpty(beforeMonteRecord)) {
                list.add(new BasicNameValuePair("beforeRecord", URLEncoder.encode(beforeMonteRecord, "utf-8")));
            }
            list.add(new BasicNameValuePair("totalLoginCount", URLEncoder.encode(String.valueOf((long) StatisticsUtils.getTotalLoginCount()), "utf-8")));
            list.add(new BasicNameValuePair("totalDetailPageTime", URLEncoder.encode(String.valueOf(StatisticsUtils.getTotalDetailPageTime().longValue() / 1000), "utf-8")));
        } catch (Exception e) {
        }
    }

    public static void updateRemindToday() {
        savePref("config_update_check_today", TimeUtil.getDate(System.currentTimeMillis()));
    }

    public String fullRequestParams() {
        ClientMetadata instance = ClientMetadata.getInstance(ETApplication.getInstance());
        String imei = instance.getImei();
        String auid = instance.getAuid();
        String packageName = instance.getPackageName();
        StringBuilder sb = new StringBuilder("");
        try {
            sb.append("imei=");
            sb.append(URLEncoder.encode(imei, "utf-8"));
            sb.append("&");
            if (auid != null) {
                sb.append("aid=");
                sb.append(URLEncoder.encode(auid, "utf-8"));
                sb.append("&");
            }
            if (packageName != null) {
                sb.append("pkn=");
                sb.append(URLEncoder.encode(packageName, "utf-8"));
                sb.append("&");
            }
            if (UserConfig.CHANNEL != null) {
                sb.append("channel=");
                sb.append(URLEncoder.encode(UserConfig.CHANNEL, "utf-8"));
                sb.append("&");
            }
            if (UserConfig.product != null) {
                sb.append("product=");
                sb.append(URLEncoder.encode(UserConfig.product, "utf-8"));
                sb.append("&");
            }
            String city = UserConfig.getCity();
            if (city != null) {
                sb.append("city=");
                sb.append(URLEncoder.encode(city, "utf-8"));
                sb.append("&");
            }
            String province = UserConfig.getProvince();
            if (province != null) {
                sb.append("prov=");
                sb.append(URLEncoder.encode(province, "utf-8"));
                sb.append("&");
            }
            String locationInfo = UserConfig.getLocationInfo();
            if (locationInfo != null) {
                sb.append("loc=");
                sb.append(URLEncoder.encode(locationInfo, "utf-8"));
                sb.append("&");
            }
        } catch (Exception e) {
        }
        return sb.toString();
    }

    public void fullRequestParams(List<NameValuePair> list) {
        ClientMetadata instance = ClientMetadata.getInstance(ETApplication.getInstance());
        String imei = instance.getImei();
        String auid = instance.getAuid();
        String packageName = instance.getPackageName();
        String channel = "baidu";
        try {
            LoginUser loginUser = UserConfig.getLoginUser();
            if (!TextUtils.isEmpty(channel)) {
                list.add(new BasicNameValuePair("market", URLEncoder.encode(channel, "utf-8")));
            }
            if (loginUser != null) {
                if (!TextUtils.isEmpty(loginUser.getOpenId())) {
                    list.add(new BasicNameValuePair("openId", URLEncoder.encode(loginUser.getOpenId(), "utf-8")));
                }
                if (!TextUtils.isEmpty(loginUser.getNickName())) {
                    list.add(new BasicNameValuePair("username", URLEncoder.encode(loginUser.getNickName(), "utf-8")));
                }
                if (!TextUtils.isEmpty(loginUser.getLoginType())) {
                    list.add(new BasicNameValuePair("loginType", URLEncoder.encode(loginUser.getLoginType(), "utf-8")));
                }
            }
            if (!TextUtils.isEmpty(UserConfig.product)) {
                list.add(new BasicNameValuePair("product", URLEncoder.encode(UserConfig.product, "utf-8")));
            }
            if (!TextUtils.isEmpty(UserConfig.CHANNEL)) {
                list.add(new BasicNameValuePair("channel", URLEncoder.encode(UserConfig.CHANNEL, "utf-8")));
            }
            list.add(new BasicNameValuePair("agent", "1"));
            list.add(new BasicNameValuePair("ts", "" + System.currentTimeMillis()));
            if (!TextUtils.isEmpty(imei)) {
                list.add(new BasicNameValuePair("imei", URLEncoder.encode(imei, "utf-8")));
            }
            if (!TextUtils.isEmpty(auid)) {
                list.add(new BasicNameValuePair("aid", URLEncoder.encode(auid, "utf-8")));
            }
            if (!TextUtils.isEmpty(packageName)) {
                list.add(new BasicNameValuePair("pkn", URLEncoder.encode(packageName, "utf-8")));
            }
            try {
                String city = UserConfig.getCity();
                if (city != null) {
                    list.add(new BasicNameValuePair("city", URLEncoder.encode(city, "utf-8")));
                }
                String province = UserConfig.getProvince();
                if (province != null) {
                    list.add(new BasicNameValuePair("prov", URLEncoder.encode(province, "utf-8")));
                }
                String locationInfo = UserConfig.getLocationInfo();
                if (locationInfo != null) {
                    list.add(new BasicNameValuePair("loc", URLEncoder.encode(locationInfo, "utf-8")));
                }
                String adSdkPref = getAdSdkPref("ttsdk_oaid", "");
                if (!TextUtils.isEmpty(adSdkPref)) {
                    list.add(new BasicNameValuePair("oaid", URLEncoder.encode(adSdkPref, "utf-8")));
                }
                String adSdkPref2 = getAdSdkPref("ttsdk_aaid", "");
                if (!TextUtils.isEmpty(adSdkPref)) {
                    list.add(new BasicNameValuePair("aaid", URLEncoder.encode(adSdkPref2, "utf-8")));
                }
            } catch (Exception e) {
            }
            statisticsParams(list);
            list.add(new BasicNameValuePair("sign", URLEncoder.encode(generateSign(list), "utf-8")));
        } catch (Exception e2) {
        }
    }

    /* access modifiers changed from: protected */
    public void handle(Context context, String str, String str2, final Object obj, final HttpModuleHandleListener httpModuleHandleListener, final IHttpJsonConvert iHttpJsonConvert) {
        handleRequest(str2, obj, new HttpHandleListener() {
            public void onComplete(String str, DownloadResponse downloadResponse, String str2) {
                Object convert = iHttpJsonConvert.convert(str2);
                if (convert != null) {
                    httpModuleHandleListener.onComplete(obj, convert);
                } else {
                    httpModuleHandleListener.onFail(obj, "result is null");
                }
            }

            public void onFail(String str, String str2) {
                httpModuleHandleListener.onFail(obj, str2);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void handle(String str, final Object obj, final HttpModuleHandleListener httpModuleHandleListener, final IHttpJsonConvert iHttpJsonConvert) {
        handleRequest(str, obj, new HttpHandleListener() {
            public void onComplete(String str, DownloadResponse downloadResponse, String str2) {
                Object convert = iHttpJsonConvert.convert(str2);
                if (convert != null) {
                    httpModuleHandleListener.onComplete(obj, convert);
                } else {
                    httpModuleHandleListener.onFail(obj, "result is null");
                }
            }

            public void onFail(String str, String str2) {
                httpModuleHandleListener.onFail(obj, str2);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void handle(String str, Map<String, String> map, HttpModuleHandleListener httpModuleHandleListener) {
        handle(str, map, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                return (Map) ETMan.getMananger().getGson().fromJson(str, HashMap.class);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void handleBackRequest(String str, Object obj, final HttpHandleListener httpHandleListener) {
        if (!AndroidUtils.isNetworkConnected(ETApplication.getInstance())) {
            httpHandleListener.onFail("", "");
            return;
        }

        ArrayList arrayList = new ArrayList();
        if (obj instanceof List) {
            arrayList.addAll((List) obj);
        } else {
            arrayList.addAll(ReflectionUtil.getPostUrlDataByReflect(obj));
        }
        SwUrlGenerator swUrlGenerator = new SwUrlGenerator(ETApplication.getInstance());
        swUrlGenerator.generateParams();
        arrayList.addAll(swUrlGenerator.getValuePairs());
        fullRequestParams(arrayList);
        try {
            HttpPost httpPost = new HttpPost(str);
            httpPost.setHeader("CONTENT_TYPE", "application/x-www-form-urlencoded; charset=utf-8");
            httpPost.setEntity(new UrlEncodedFormEntity(arrayList));
            new DownloadRunnableBackTask(new DownloadRunnableBackTask.DownloadTaskListener() {
                public void onComplete(String str, DownloadResponse downloadResponse) {
                    if (downloadResponse == null) {
                        httpHandleListener.onFail(str, "data is null");
                        return;
                    }
                    httpHandleListener.onComplete(str, downloadResponse, HttpResponses.asResponseString(downloadResponse));
                }
            }, httpPost).start();
        } catch (Exception e) {

            httpHandleListener.onFail(str, e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void handleBackThread(String str, final Object obj, final HttpModuleHandleListener httpModuleHandleListener, final IHttpJsonConvert iHttpJsonConvert) {
        handleBackRequest(str, obj, new HttpHandleListener() {
            public void onComplete(String str, DownloadResponse downloadResponse, String str2) {
                Object convert = iHttpJsonConvert.convert(str2);
                if (convert != null) {
                    httpModuleHandleListener.onComplete(obj, convert);
                } else {
                    httpModuleHandleListener.onFail(obj, "result is null");
                }
            }

            public void onFail(String str, String str2) {
                httpModuleHandleListener.onFail(obj, str2);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void handleRequest(String str, Object obj, final HttpHandleListener httpHandleListener) {
        if (!AndroidUtils.isNetworkConnected(ETApplication.getInstance())) {
            httpHandleListener.onFail("", "");
        }   else {

            ArrayList arrayList = new ArrayList();
            if (obj instanceof List) {
                arrayList.addAll((List) obj);
            } else {
                arrayList.addAll(ReflectionUtil.getPostUrlDataByReflect(obj));
            }
            SwUrlGenerator swUrlGenerator = new SwUrlGenerator(ETApplication.getInstance());
            swUrlGenerator.generateParams();
            arrayList.addAll(swUrlGenerator.getValuePairs());
            fullRequestParams(arrayList);
            try {
                HttpPost httpPost = new HttpPost(str);
                httpPost.setHeader("Accept-Encoding", "gzip");
                httpPost.setHeader("CONTENT_TYPE", "application/x-www-form-urlencoded; charset=utf-8");
                httpPost.setEntity(new UrlEncodedFormEntity(arrayList));
                new DownloadRunnableTask(new DownloadRunnableTask.DownloadTaskListener() {
                    public void onComplete(String str, DownloadResponse downloadResponse) {
                        if (downloadResponse == null) {
                            httpHandleListener.onFail(str, "data is null");
                            return;
                        }
                        httpHandleListener.onComplete(str, downloadResponse, HttpResponses.asResponseString(downloadResponse));
                    }
                }, httpPost).start();
            } catch (Exception e) {

                httpHandleListener.onFail(str, e.getMessage());
            }
        }
    }
}
