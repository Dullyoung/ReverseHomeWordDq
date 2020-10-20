package com.tataera;

/*
 *   Created by Dullyoung on 2020/10/20 0020
 */


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.tataera.base.http.HttpModuleHandleListener;
import com.tataera.base.http.IHttpJsonConvert;
import com.tataera.base.http.SuperDataMan;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import org.json.JSONObject;

public class Config extends SuperDataMan {

    public static final String SEARCH_URL="http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYSearchAnswerHandler2";
    public static final String AUTO_SEARCH_URL="http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYSuggestAnswerHandler";

    /*
    private static e a;
    private static SuperDataMan b = new SuperDataMan();

    public a c;

    public interface a {
        void a();
    }

    private e() {
    }

    public static e a() {
        e eVar;
        synchronized (e.class) {
            try {
                if (a == null) {
                    a = new e();
                }
                eVar = a;
            } catch (Throwable th) {
                Class<e> cls = e.class;
                throw th;
            }
        }
        return eVar;
    }


    public void a(AlbumResultBean albumResultBean, String str) {
        String json = ETMan.getMananger().getGson().toJson((Object) albumResultBean);
        savePref("book_detail_cache" + str, json);
    }


    public void a(List<BookInfo> list, String str) {
        String json = ETMan.getMananger().getGson().toJson((Object) list);
        savePref("user_book_cache" + str, json);
    }


    public void b(List<BookInfo> list, String str) {
        String json = ETMan.getMananger().getGson().toJson((Object) list);
        savePref("recom_book_cache" + str, json);
    }

    static final     Object f(String str) {
        return str;
    }

    public String a(int i) {
        return i == 0 ? getPref("history", "") : i == 1 ? getPref("FindWordHistory", "") : "";
    }

    public void a(int i, String str) {
        StringBuilder sb = new StringBuilder();
        String[] split = a(i).split(VoiceWakeuperAidl.PARAMS_SEPARATE);
        ArrayList arrayList = new ArrayList();
        int i2 = -1;
        for (int i3 = 0; i3 < split.length; i3++) {
            if (split[i3].equals(str)) {
                i2 = i3;
            } else {
                arrayList.add(split[i3]);
            }
        }
        if (i2 != -1) {
            arrayList.add(split[i2]);
        } else {
            arrayList.add(str);
        }
        for (int size = arrayList.size() - 1; size > -1; size--) {
            if (size == arrayList.size() - 1) {
                sb.append((String) arrayList.get(size));
            } else {
                sb.append(VoiceWakeuperAidl.PARAMS_SEPARATE + ((String) arrayList.get(size)));
            }
        }
        String sb2 = sb.toString();
        if (i == 0) {
            savePref("history", sb2);
        } else if (i == 1) {
            savePref("FindWordHistory", sb2);
        }
    }

    public void a(int i, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        String[] split = str.split(VoiceWakeuperAidl.PARAMS_SEPARATE);
        ArrayList arrayList = new ArrayList();
        int i2 = -1;
        for (int i3 = 0; i3 < split.length; i3++) {
            if (split[i3].equals(str2)) {
                i2 = i3;
            } else {
                arrayList.add(split[i3]);
            }
        }
        if (i2 != -1) {
            arrayList.add(split[i2]);
        } else {
            arrayList.add(str2);
        }
        for (int size = arrayList.size() - 1; size > -1; size--) {
            if (size == arrayList.size() - 1) {
                sb.append((String) arrayList.get(size));
            } else {
                sb.append(VoiceWakeuperAidl.PARAMS_SEPARATE + ((String) arrayList.get(size)));
            }
        }
        String sb2 = sb.toString();
        if (i == 0) {
            savePref("history", sb2);
        } else if (i == 1) {
            savePref("FindWordHistory", sb2);
        }
    }

    public void a(Activity activity, String str, String str2, String str3, String str4, String str5, String str6, String str7, File[] fileArr, HttpModuleHandleListener httpModuleHandleListener) {
        StringBuilder sb = new StringBuilder("http://zuoyeapi.tatatimes.com/homeworkapi/upload.s?h=ZYFeedBackHander&");
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("appType", "zuoyezhushou"));
        if (str != null) {
            try {
                arrayList.add(new BasicNameValuePair("answerID", URLEncoder.encode(str, "utf-8")));
            } catch (UnsupportedEncodingException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        if (str5 != null) {
            arrayList.add(new BasicNameValuePair("contact", str5));
        }
        if (!TextUtils.isEmpty(str6)) {
            arrayList.add(new BasicNameValuePair("volumn", str6));
        }
        if (!TextUtils.isEmpty(str7)) {
            arrayList.add(new BasicNameValuePair("year", str7));
        }
        if (str2 != null) {
            arrayList.add(new BasicNameValuePair("code", str2));
        }
        if (str3 != null) {
            arrayList.add(new BasicNameValuePair("type", str3));
        }
        if (str4 != null) {
            try {
                arrayList.add(new BasicNameValuePair("content", URLEncoder.encode(str4, "utf-8")));
            } catch (UnsupportedEncodingException e2) {
                ThrowableExtension.printStackTrace(e2);
            }
        }
        if (fileArr == null) {
            fileArr = new File[1];
        }
        b.fullRequestParams(arrayList);
        int i = 0;
        Iterator it = arrayList.iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                NameValuePair nameValuePair = (NameValuePair) it.next();
                if (i2 > 0) {
                    sb.append("&");
                }
                sb.append(nameValuePair.getName());
                sb.append("=");
                sb.append(nameValuePair.getValue());
                i = i2 + 1;
            } else {
                try {
                    break;
                } catch (Exception e3) {
                    Log.i("error: ", e3.getMessage());
                    ThrowableExtension.printStackTrace(e3);
                    return;
                }
            }
        }
        final JSONObject jSONObject = new JSONObject(FileUploadUtil.uploadSubmit(sb.toString(), hashMap, fileArr));
        final String optString = jSONObject.optString("code");
        if (activity != null && !activity.isFinishing()) {
            final Activity activity2 = activity;
            final HttpModuleHandleListener httpModuleHandleListener2 = httpModuleHandleListener;
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    if (optString.equals("200")) {
                        ToastUtils.show((Context) activity2, "反馈成功");
                        if (httpModuleHandleListener2 != null) {
                            httpModuleHandleListener2.onComplete((Object) null, optString);
                            return;
                        }
                        return;
                    }
                    String optString = jSONObject.optString("msg");
                    Activity activity = activity2;
                    ToastUtils.show((Context) activity, "反馈失败" + optString);
                    if (httpModuleHandleListener2 != null) {
                        httpModuleHandleListener2.onFail((Object) null, optString);
                    }
                }
            });
        }
    }

    public void a(Activity activity, String str, String str2, String str3, String str4, String str5, File[] fileArr, HttpModuleHandleListener httpModuleHandleListener) {
        a(activity, str, str2, str3, str4, str5, (String) null, (String) null, fileArr, httpModuleHandleListener);
    }

    public void a(HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(new BasicNameValuePair("pos", URLEncoder.encode("intoTextbook", "utf-8")));
            arrayList.add(new BasicNameValuePair("suc", URLEncoder.encode("1", "utf-8")));
            arrayList.add(new BasicNameValuePair(AdMgr.SOURCE_POSITION_KEY, URLEncoder.encode("intoTextbook", "utf-8")));
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYLogCountHander", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                return str;
            }
        });
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(String str) {
        d(str, new HttpModuleHandleListener() {
            public void onComplete(Object obj, Object obj2) {
            }

            public void onFail(Object obj, String str) {
            }
        });
    }

    public void a(String str, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            arrayList.add(new BasicNameValuePair("digest", Md5Util.MD5(currentTimeMillis + str + "2017")));
            arrayList.add(new BasicNameValuePair("mobile", str));
            arrayList.add(new BasicNameValuePair("salt", String.valueOf(currentTimeMillis)));
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=SendValidCodeUpdateHandler", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    return jSONObject.getInt("code") == 200 ? "ok" : jSONObject.getString("msg");
                } catch (Exception e) {
                    return "发送验证码失败";
                }
            }
        });
    }

    public void a(String str, e.d dVar, String str2, e.b bVar, String str3, String str4, String str5, String str6, HttpModuleHandleListener httpModuleHandleListener) {
        final StringBuilder sb = new StringBuilder("http://zuoyeapi.tatatimes.com/homeworkapi/upload.s?h=ZYAdsFeedBackHander&");
        final HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        try {
            if (!TextUtils.isEmpty(str3)) {
                arrayList.add(new BasicNameValuePair("adID", URLEncoder.encode(str3, "utf-8")));
            }
            if (!TextUtils.isEmpty(str4)) {
                arrayList.add(new BasicNameValuePair("imgURL", URLEncoder.encode(str4, "utf-8")));
            }
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(new BasicNameValuePair("reportType", URLEncoder.encode(str, "utf-8")));
            }
            if (!TextUtils.isEmpty(dVar.name())) {
                arrayList.add(new BasicNameValuePair("adsSource", URLEncoder.encode(dVar.name(), "utf-8")));
            }
            if (!TextUtils.isEmpty(str2)) {
                arrayList.add(new BasicNameValuePair("adsType", URLEncoder.encode(str2, "utf-8")));
            }
            if (!TextUtils.isEmpty(bVar.name())) {
                arrayList.add(new BasicNameValuePair("adsPostion", URLEncoder.encode(bVar.name(), "utf-8")));
            }
            if (!TextUtils.isEmpty(str5)) {
                arrayList.add(new BasicNameValuePair("adsTitle", URLEncoder.encode(str5, "utf-8")));
            }
            if (!TextUtils.isEmpty(str6)) {
                arrayList.add(new BasicNameValuePair("adscontent", URLEncoder.encode(str6, "utf-8")));
            }
        } catch (Exception e) {
        }
        b.fullRequestParams(arrayList);
        int i = 0;
        Iterator it = arrayList.iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                NameValuePair nameValuePair = (NameValuePair) it.next();
                if (i2 > 0) {
                    sb.append("&");
                }
                sb.append(nameValuePair.getName());
                sb.append("=");
                sb.append(nameValuePair.getValue());
                i = i2 + 1;
            } else {
                final File[] fileArr = new File[1];
                final HttpModuleHandleListener httpModuleHandleListener2 = httpModuleHandleListener;
                ThreadHelper.run(new ThreadHelper.BackThreadListener() {
                    public void background() {
                        try {
                            JSONObject jSONObject = new JSONObject(FileUploadUtil.uploadSubmit(sb.toString(), hashMap, fileArr));
                            final String optString = jSONObject.optString("code");
                            final String optString2 = jSONObject.optString("msg");
                            e.this.a(fileArr);
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    if (optString.equals("200")) {
                                        if (httpModuleHandleListener2 != null) {
                                            httpModuleHandleListener2.onComplete((Object) null, "ok");
                                            ToastUtils.show("反馈成功");
                                        }
                                    } else if (httpModuleHandleListener2 != null) {
                                        HttpModuleHandleListener httpModuleHandleListener = httpModuleHandleListener2;
                                        httpModuleHandleListener.onFail((Object) null, optString + optString2);
                                        ToastUtils.show("反馈失败" + optString + optString2);
                                    }
                                }
                            });
                        } catch (Exception e2) {
                        }
                    }
                });
                return;
            }
        }
    }

    public void a(String str, String str2, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(new BasicNameValuePair("id", URLEncoder.encode(str, "utf-8")));
            arrayList.add(new BasicNameValuePair("imgURLs", URLEncoder.encode(str2, "utf-8")));
        } catch (Exception e) {
        }
        handleBackThread("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYUploadAnswerPicURLHandler", arrayList, httpModuleHandleListener, new f(this));
    }

    public void a(String str, String str2, String str3, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(new BasicNameValuePair("pos", URLEncoder.encode(str, "utf-8")));
            arrayList.add(new BasicNameValuePair("suc", URLEncoder.encode(str2, "utf-8")));
            arrayList.add(new BasicNameValuePair(AdMgr.SOURCE_POSITION_KEY, URLEncoder.encode(str3, "utf-8")));
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYLogAdsHander", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                return str;
            }
        });
    }

    public void a(String str, String str2, String str3, String str4, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(new BasicNameValuePair("openID", URLEncoder.encode(str2, "utf-8")));
            arrayList.add(new BasicNameValuePair("mobile", URLEncoder.encode(str, "utf-8")));
            if (!TextUtils.isEmpty(str3)) {
                arrayList.add(new BasicNameValuePair("id", URLEncoder.encode(str3, "utf-8")));
            }
            if (!TextUtils.isEmpty(str4)) {
                arrayList.add(new BasicNameValuePair("validCode", URLEncoder.encode(str4, "utf-8")));
            }
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYUpdateMobileHandler", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    return ETMan.getMananger().getGson().fromJson(str, new TypeToken<BaseBean<String>>() {
                    }.getType());
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    public void a(String str, final String str2, String str3, String str4, String str5, int i, HttpModuleHandleListener httpModuleHandleListener) {
        StatisticsUtils.saveTotalDetail();
        String str6 = System.currentTimeMillis() + "";
        String a2 = z.a(str2 + ":" + "&&*%$dkeunk0*!@^*&%nnc<scvqw" + ":" + str6);
        ArrayList arrayList = new ArrayList();
        try {
            if (!TextUtils.isEmpty(str3)) {
                arrayList.add(new BasicNameValuePair(AdMgr.SOURCE_POSITION_KEY, URLEncoder.encode(str3, "utf-8")));
            }
            arrayList.add(new BasicNameValuePair("openID", URLEncoder.encode(str, "utf-8")));
            arrayList.add(new BasicNameValuePair("answerID", URLEncoder.encode(str2, "utf-8")));
            arrayList.add(new BasicNameValuePair("sourceType", URLEncoder.encode(str4, "utf-8")));
            arrayList.add(new BasicNameValuePair("t", URLEncoder.encode(str6, "utf-8")));
            arrayList.add(new BasicNameValuePair("k", URLEncoder.encode(a2, "utf-8")));
            if (!TextUtils.isEmpty(str5)) {
                arrayList.add(new BasicNameValuePair("traceID", URLEncoder.encode(str5, "utf-8")));
            }
            if (i != -1) {
                arrayList.add(new BasicNameValuePair("targetIdx", URLEncoder.encode(String.valueOf(i), "utf-8")));
            }
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYAnswerDetailHandler", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    final AlbumResultBean albumResultBean = (AlbumResultBean) ETMan.getMananger().getGson().fromJson(str, AlbumResultBean.class);
                    ThreadHelper.run(new ThreadHelper.BackThreadListener() {
                        public void background() {
                            e.this.a(albumResultBean, str2);
                        }
                    });
                    albumResultBean.getCode().equals("200.0");
                    return albumResultBean;
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    public void a(String str, String str2, String str3, String str4, String str5, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(new BasicNameValuePair("openID", URLEncoder.encode(str, "utf-8")));
            arrayList.add(new BasicNameValuePair("answerID", URLEncoder.encode(str2, "utf-8")));
            arrayList.add(new BasicNameValuePair("idx", URLEncoder.encode(str3, "utf-8")));
            arrayList.add(new BasicNameValuePair("preIdx", URLEncoder.encode(str4, "utf-8")));
            arrayList.add(new BasicNameValuePair("viewSeq", URLEncoder.encode(str5, "utf-8")));
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYUpdateUserAnswerIdx", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    return new JSONObject(str).getInt("code") == 200 ? "ok" : "error";
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, HttpModuleHandleListener httpModuleHandleListener) {
        StatisticsUtils.saveTotalSearchBook();
        ArrayList arrayList = new ArrayList();
        try {
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(new BasicNameValuePair("keyword", URLEncoder.encode(str, "utf-8")));
            }
            if (!TextUtils.isEmpty(str2)) {
                arrayList.add(new BasicNameValuePair("grade", URLEncoder.encode(str2, "utf-8")));
            }
            if (str3 != null) {
                arrayList.add(new BasicNameValuePair(SpeechConstant.SUBJECT, URLEncoder.encode(str3, "utf-8")));
            }
            if (str4 != null) {
                arrayList.add(new BasicNameValuePair("year", URLEncoder.encode(str4, "utf-8")));
            }
            if (str5 != null) {
                arrayList.add(new BasicNameValuePair("bookVersion", URLEncoder.encode(str5, "utf-8")));
            }
            if (str6 != null) {
                arrayList.add(new BasicNameValuePair("volumn", URLEncoder.encode(str6, "utf-8")));
            }
            if (str7 != null) {
                arrayList.add(new BasicNameValuePair("pageNo", URLEncoder.encode(str7, "utf-8")));
                if (!str7.equals("1") && !TextUtils.isEmpty(str9)) {
                    arrayList.add(new BasicNameValuePair("traceID", URLEncoder.encode(str9, "utf-8")));
                }
            }
            if (str8 != null) {
                arrayList.add(new BasicNameValuePair("pageSize", URLEncoder.encode(str8, "utf-8")));
            }
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYSearchAnswerHandler2", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    Log.e("gll", "listSearchResult" + str);
                    SearchResultBean searchResultBean = (SearchResultBean) ETMan.getMananger().getGson().fromJson(str, SearchResultBean.class);
                    if (searchResultBean.getCode() == 400) {
                        ToastUtils.show("请输入关键字");
                    }
                    return searchResultBean.getDatas();
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    public void a(String str, List<BookInfo> list, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                break;
            }
            if (i2 == 0) {
                sb.append(list.get(i2).getId().trim());
            } else {
                sb.append("," + list.get(i2).getId().trim());
            }
            i = i2 + 1;
        }
        String sb2 = sb.toString();
        try {
            arrayList.add(new BasicNameValuePair("openID", URLEncoder.encode(str, "utf-8")));
            arrayList.add(new BasicNameValuePair("answerIDs", URLEncoder.encode(sb2, "utf-8")));
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYDelUserLikeHandler", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    return jSONObject.getInt("code") == 200 ? "ok" : jSONObject.getString("msg");
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    public void a(String str, File[] fileArr) {
        StringBuilder sb = new StringBuilder("http://zuoyeapi.tatatimes.com/homeworkapi/upload.s?h=ZYUploadCommonPicHandler&");
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("appType", "zuoyezhushou"));
        arrayList.add(new BasicNameValuePair("type", "answer"));
        if (str != null) {
            try {
                arrayList.add(new BasicNameValuePair("id", URLEncoder.encode(str, "utf-8")));
            } catch (UnsupportedEncodingException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        b.fullRequestParams(arrayList);
        int i = 0;
        Iterator it = arrayList.iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                NameValuePair nameValuePair = (NameValuePair) it.next();
                if (i2 > 0) {
                    sb.append("&");
                }
                sb.append(nameValuePair.getName());
                sb.append("=");
                sb.append(nameValuePair.getValue());
                i = i2 + 1;
            } else {
                try {
                    break;
                } catch (Exception e2) {
                    ThrowableExtension.printStackTrace(e2);
                    c.a().c("uploadFail");
                    return;
                }
            }
        }
        BaseBean baseBean = (BaseBean) ETMan.getMananger().getGson().fromJson(FileUploadUtil.uploadSubmit(sb.toString(), hashMap, fileArr), new TypeToken<BaseBean<List<String>>>() {
        }.getType());
        if (baseBean.getCode() == 200) {
            c.a().c(baseBean);
            return;
        }
        new Handler(Looper.getMainLooper()).post(new g(baseBean));
        c.a().c("uploadFail");
    }

    public void a(final File[] fileArr) {
        try {
            ThreadHelper.run(new ThreadHelper.BackThreadListener() {
                public void background() {
                    if (!TextUtils.isEmpty(fileArr[0].getAbsolutePath())) {
                        o.c(fileArr[0].getAbsolutePath());
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    public List<BookInfo> b(String str) {
        String pref = getPref("user_book_cache" + str, "");
        return TextUtils.isEmpty(pref) ? new ArrayList() : (List) ETMan.getMananger().getGson().fromJson(pref, new TypeToken<List<BookInfo>>() {
        }.getType());
    }

    public void b() {
        a((HttpModuleHandleListener) new HttpModuleHandleListener() {
            public void onComplete(Object obj, Object obj2) {
            }

            public void onFail(Object obj, String str) {
            }
        });
    }

    public void b(HttpModuleHandleListener httpModuleHandleListener) {
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYSearchHotWordsHandler", new ArrayList(), httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    Map map = (Map) ETMan.getMananger().getGson().fromJson(str, HashMap.class);
                    List list = (List) map.get("datas");
                    if (map.get("code").toString().equals("200.0")) {
                        return list;
                    }
                    ToastUtils.show(map.get("msg").toString());
                    return list;
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    public void b(String str, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(new BasicNameValuePair("mobile", URLEncoder.encode(str, "utf-8")));
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYMobileBingdingHandler", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    return ETMan.getMananger().getGson().fromJson(str, new TypeToken<BaseBean<UserInfoBean>>() {
                    }.getType());
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    public void b(String str, String str2, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        BookInfo bookInfo = new BookInfo();
        bookInfo.setId(str2);
        arrayList.add(bookInfo);
        a(str, (List<BookInfo>) arrayList, httpModuleHandleListener);
    }

    public void b(String str, String str2, String str3, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            if (!TextUtils.isEmpty(str3)) {
                arrayList.add(new BasicNameValuePair("adUpdateTime", URLEncoder.encode(str3, "utf-8")));
            }
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(new BasicNameValuePair("openID", URLEncoder.encode(str, "utf-8")));
            }
            if (!TextUtils.isEmpty(str2)) {
                arrayList.add(new BasicNameValuePair("sourceType", URLEncoder.encode("umengandroid", "utf-8")));
                arrayList.add(new BasicNameValuePair("token", URLEncoder.encode(str2, "utf-8")));
            }
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYInitHandler", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    return (InitConfigBean) ETMan.getMananger().getGson().fromJson(new JSONObject(str).getString("datas"), InitConfigBean.class);
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                    return null;
                }
            }
        });
    }

    public void b(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(new BasicNameValuePair("openID", URLEncoder.encode(str, "utf-8")));
            arrayList.add(new BasicNameValuePair("userName", URLEncoder.encode(str2, "utf-8")));
            arrayList.add(new BasicNameValuePair(TataNativeBrowser.DESTINATION_URL_TITLE, URLEncoder.encode(str3, "utf-8")));
            arrayList.add(new BasicNameValuePair("grade", URLEncoder.encode(str4, "utf-8")));
            arrayList.add(new BasicNameValuePair(SpeechConstant.SUBJECT, URLEncoder.encode(str5, "utf-8")));
            arrayList.add(new BasicNameValuePair("volumn", URLEncoder.encode(str7, "utf-8")));
            arrayList.add(new BasicNameValuePair("bookVersion", URLEncoder.encode(str6, "utf-8")));
            arrayList.add(new BasicNameValuePair("year", URLEncoder.encode(str8, "utf-8")));
            arrayList.add(new BasicNameValuePair("code", URLEncoder.encode(str9, "utf-8")));
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYUploadAnswerBaseInfoHandler", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    return ETMan.getMananger().getGson().fromJson(str, new TypeToken<BaseBean<BookInfo>>() {
                    }.getType());
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    public void b(String str, List<BookInfo> list, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                break;
            }
            if (i2 == 0) {
                sb.append(list.get(i2).getId().trim());
            } else {
                sb.append("," + list.get(i2).getId().trim());
            }
            i = i2 + 1;
        }
        String sb2 = sb.toString();
        try {
            arrayList.add(new BasicNameValuePair("openID", URLEncoder.encode(str, "utf-8")));
            arrayList.add(new BasicNameValuePair("answerIDs", URLEncoder.encode(sb2, "utf-8")));
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYCopyUserLikeHandler", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    return new JSONObject(str).getInt("code") == 200 ? "ok" : "error";
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    public void b(String str, File[] fileArr) {
        StringBuilder sb = new StringBuilder("http://zuoyeapi.tatatimes.com/homeworkapi/upload.s?h=ZYUploadAnswerPicHandler&");
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("appType", "zuoyezhushou"));
        if (str != null) {
            try {
                arrayList.add(new BasicNameValuePair("id", URLEncoder.encode(str, "utf-8")));
            } catch (UnsupportedEncodingException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        b.fullRequestParams(arrayList);
        int i = 0;
        Iterator it = arrayList.iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                NameValuePair nameValuePair = (NameValuePair) it.next();
                if (i2 > 0) {
                    sb.append("&");
                }
                sb.append(nameValuePair.getName());
                sb.append("=");
                sb.append(nameValuePair.getValue());
                i = i2 + 1;
            } else {
                try {
                    final String optString = new JSONObject(FileUploadUtil.uploadSubmit(sb.toString(), hashMap, fileArr)).optString("code");
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            if (optString.equals("200")) {
                                e.this.c.a();
                                ToastUtils.show("上传成功");
                                return;
                            }
                            ToastUtils.show("上传失败，可能是网络问题");
                        }
                    });
                    return;
                } catch (Exception e2) {
                    Log.i("error: ", e2.getMessage());
                    ThrowableExtension.printStackTrace(e2);
                    return;
                }
            }
        }
    }

    public String c() {
        return getPref("tempId", "");
    }

    public List<BookInfo> c(String str) {
        String pref = getPref("recom_book_cache" + str, "");
        return TextUtils.isEmpty(pref) ? new ArrayList() : (List) ETMan.getMananger().getGson().fromJson(pref, new TypeToken<List<BookInfo>>() {
        }.getType());
    }

    public void c(String str, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(new BasicNameValuePair("openID", URLEncoder.encode(str, "utf-8")));
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYCheckMoblieBoundHandler", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    return ETMan.getMananger().getGson().fromJson(str, new TypeToken<BaseBean<String>>() {
                    }.getType());
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    public void c(String str, String str2, String str3, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(new BasicNameValuePair("openID", URLEncoder.encode(str, "utf-8")));
            arrayList.add(new BasicNameValuePair("answerID", URLEncoder.encode(str2, "utf-8")));
            arrayList.add(new BasicNameValuePair("sourceType", URLEncoder.encode(str3, "utf-8")));
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYUserLikeHandler", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    return jSONObject.getInt("code") == 200 ? "ok" : jSONObject.getString("msg");
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    public AlbumResultBean d(String str) {
        String pref = getPref("book_detail_cache" + str, "");
        return TextUtils.isEmpty(pref) ? new AlbumResultBean() : (AlbumResultBean) ETMan.getMananger().getGson().fromJson(pref, AlbumResultBean.class);
    }

    public void d(String str, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            int intValue = getPref("group_test", (Integer) -1).intValue();
            arrayList.add(new BasicNameValuePair("pos", URLEncoder.encode(str, "utf-8")));
            arrayList.add(new BasicNameValuePair("suc", URLEncoder.encode("1", "utf-8")));
            arrayList.add(new BasicNameValuePair(AdMgr.SOURCE_POSITION_KEY, URLEncoder.encode(String.valueOf(intValue), "utf-8")));
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYLogCountHander", arrayList, httpModuleHandleListener, h.a);
    }

    public void e(String str) {
        savePref("tempId", str);
    }

    public void e(String str, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(new BasicNameValuePair("openID", URLEncoder.encode(str, "utf-8")));
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYUserLogoutHander", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    return ETMan.getMananger().getGson().fromJson(str, new TypeToken<BaseBean<String>>() {
                    }.getType());
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    public void f(String str, HttpModuleHandleListener httpModuleHandleListener) {
        InputStream inputStream;
        BufferedReader bufferedReader;
        Throwable th;
        HttpGet httpGet = new HttpGet(str);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpClientUtils.setTimeout((HttpClient) defaultHttpClient, 100000);
        try {
            HttpResponse execute = defaultHttpClient.execute(httpGet);
            int statusCode = execute.getStatusLine().getStatusCode();
            StringBuffer stringBuffer = new StringBuffer();
            if (statusCode == 200 || statusCode == 206) {
                HttpEntity entity = execute.getEntity();
                if (entity != null) {
                    inputStream = entity.getContent();
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        while (true) {
                            try {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                stringBuffer.append(readLine);
                            } catch (IOException e) {
                                e = e;
                                try {
                                    ThrowableExtension.printStackTrace(e);
                                    if (bufferedReader != null) {
                                    }
                                    if (inputStream != null) {
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (Exception e2) {
                                            ThrowableExtension.printStackTrace(e2);
                                            throw th;
                                        }
                                    }
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                th = th;
                                if (bufferedReader != null) {
                                }
                                if (inputStream != null) {
                                }
                                throw th;
                            }
                        }
                    } catch (IOException e3) {
                        e = e3;
                        bufferedReader = null;
                        ThrowableExtension.printStackTrace(e);
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e4) {
                                ThrowableExtension.printStackTrace(e4);
                                return;
                            }
                        }
                        if (inputStream != null) {
                            inputStream.close();
                            return;
                        }
                        return;
                    } catch (Throwable th4) {
                        th = th4;
                        bufferedReader = null;
                        th = th;
                        if (bufferedReader != null) {
                        }
                        if (inputStream != null) {
                        }
                        throw th;
                    }
                } else {
                    inputStream = null;
                    bufferedReader = null;
                }
                if (httpModuleHandleListener != null) {
                    try {
                        httpModuleHandleListener.onComplete("", stringBuffer.toString());
                    } catch (IOException e5) {
                        e = e5;
                    } catch (Throwable th5) {
                        th = th5;
                        if (bufferedReader != null) {
                        }
                        if (inputStream != null) {
                        }
                        throw th;
                    }
                }
            } else {
                if (httpModuleHandleListener != null) {
                    httpModuleHandleListener.onFail("", String.valueOf(statusCode));
                }
                inputStream = null;
                bufferedReader = null;
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e6) {
            e = e6;
            inputStream = null;
            bufferedReader = null;
            ThrowableExtension.printStackTrace(e);
            if (bufferedReader != null) {
            }
            if (inputStream != null) {
            }
        } catch (Throwable th6) {
            inputStream = null;
            th = th6;
            bufferedReader = null;
            if (bufferedReader != null) {
            }
            if (inputStream != null) {
            }
            throw th;
        }
    }




    public void g(String str, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(new BasicNameValuePair("ads", URLEncoder.encode(str, "utf-8")));
            }
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYAdsCheckMultipleHandler", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                return str;
            }
        });
    }

    public void h(String str, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(new BasicNameValuePair("word", URLEncoder.encode(str, "utf-8")));
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYSuggestAnswerHandler", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    Map map = (Map) ETMan.getMananger().getGson().fromJson(str, HashMap.class);
                    List list = (List) map.get("datas");
                    if (map.get("code").toString().equals("200.0")) {
                        return list;
                    }
                    ToastUtils.show(map.get("msg").toString());
                    return list;
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    public void i(final String str, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(new BasicNameValuePair("openID", URLEncoder.encode(str, "utf-8")));
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYListUserLikeHandler", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    Map map = (Map) ETMan.getMananger().getGson().fromJson(str, HashMap.class);
                    final List fillMapByReflect = ReflectionUtil.fillMapByReflect(BookInfo.class, (Map<String, Object>) map);
                    ThreadHelper.run(new ThreadHelper.BackThreadListener() {
                        public void background() {
                            e.this.a((List<BookInfo>) fillMapByReflect, str);
                        }
                    });
                    if (!map.get("code").toString().equals("200.0")) {
                        ToastUtils.show(map.get("msg").toString());
                    }
                    return fillMapByReflect;
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    public void j(final String str, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(new BasicNameValuePair("openID", URLEncoder.encode(str, "utf-8")));
        } catch (Exception e) {
        }
        handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYRecHandler", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
                    Map map = (Map) ETMan.getMananger().getGson().fromJson(str, HashMap.class);
                    final List fillMapByReflect = ReflectionUtil.fillMapByReflect(BookInfo.class, (Map<String, Object>) map);
                    ThreadHelper.run(new ThreadHelper.BackThreadListener() {
                        public void background() {
                            e.this.b((List<BookInfo>) fillMapByReflect, str);
                        }
                    });
                    if (!map.get("code").toString().equals("200.0")) {
                        ToastUtils.show(map.get("msg").toString());
                    }
                    return fillMapByReflect;
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }




    */
}
