package com.tataera.Utils;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */


import com.tataera.base.ETMan;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReflectionUtil {
    public static List fillMapByReflect(Class cls, Map<String, Object> map) {
        new HashMap();
        ArrayList arrayList = new ArrayList();
        try {
            Object obj = map.get("topDatas");
            if (obj == null) {
                obj = map.get("datas");
            }
            if (obj instanceof List) {
                for (Map fillObjectByReflect : (List<Map>) obj) {
                    arrayList.add(fillObjectByReflect(cls, (Map<String, Object>) fillObjectByReflect));
                }
            }
            return arrayList;
        } catch (Exception e) {

          return arrayList;
        }
    }

    public static Map<String, Object> fillMapByReflect(Class cls, JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        if (jSONObject == null) {
            return hashMap;
        }
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject.get(next);
                if (obj instanceof Long) {
                    hashMap.put(next, (Long) obj);
                } else if (obj instanceof Integer) {
                    hashMap.put(next, (Integer) obj);
                } else if (obj instanceof String) {
                    hashMap.put(next, (String) obj);
                }
                if ("datas".equalsIgnoreCase(next) && (obj instanceof JSONArray)) {
                    JSONArray jSONArray = (JSONArray) obj;
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Object obj2 = jSONArray.get(i);
                        if (obj2 instanceof JSONObject) {
                            arrayList.add(fillObjectByReflect(cls, (JSONObject) obj2));
                        } else {
                            arrayList.add(obj2);
                        }
                    }
                    hashMap.put("datas", arrayList);
                }
                if ("headdatas".equalsIgnoreCase(next) && (obj instanceof JSONArray)) {
                    JSONArray jSONArray2 = (JSONArray) obj;
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        Object obj3 = jSONArray2.get(i2);
                        if (obj3 instanceof JSONObject) {
                            arrayList2.add(fillObjectByReflect(cls, (JSONObject) obj3));
                        } else {
                            arrayList2.add(obj3);
                        }
                    }
                    hashMap.put("headdatas", arrayList2);
                }
            }
        } catch (Exception e) {

        }
        return hashMap;
    }

    public static Object fillObjectByReflect(Class cls, Map<String, Object> map) {
        try {
            return ETMan.getMananger().getGson().fromJson(ETMan.getMananger().getGson().toJson((Object) map), cls);
        } catch (Exception e) {

            return null;
        }
    }

    public static Object fillObjectByReflect(Class cls, JSONObject jSONObject) {
        Object obj;
        Class[] parameterTypes;
        Object newInstance = null;
        try {
            newInstance = cls.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        jSONObject.keys();
        try {
            for (Method method : cls.getMethods()) {
                String name = method.getName();
                if (name.startsWith("set")) {
                    String str = name.substring(3, 4).toLowerCase() + name.substring(4);
                    if (!jSONObject.isNull(str) && (obj = jSONObject.get(str)) != null && (parameterTypes = method.getParameterTypes()) != null && parameterTypes.length == 1) {
                        if (parameterTypes[0].isAssignableFrom(Long.class)) {
                            obj = Long.valueOf(Long.parseLong(String.valueOf(obj)));
                        } else if (parameterTypes[0].isAssignableFrom(Integer.class)) {
                            obj = Integer.valueOf(Integer.parseInt(String.valueOf(obj)));
                        }
                        method.invoke(newInstance, new Object[]{obj});
                    }
                }
            }
        } catch (Exception e) {

        }
        return newInstance;
    }

    public static List<NameValuePair> getPostUrlDataByReflect(Object obj) {
        ArrayList arrayList = new ArrayList();
        try {
            for (Method method : obj.getClass().getMethods()) {
                String name = method.getName();
                if (name.startsWith("get") && name.length() > 3) {
                    String str = name.substring(3, 4).toLowerCase() + name.substring(4);
                    Object invoke = method.invoke(obj, new Object[0]);
                    if (invoke != null && !(invoke instanceof Class)) {
                        arrayList.add(new BasicNameValuePair(str, URLEncoder.encode(String.valueOf(invoke), "utf-8")));
                    }
                }
            }
        } catch (Exception e) {

        }
        return arrayList;
    }

    public static String getUrlDataByReflect(Object obj) {
        StringBuilder sb = new StringBuilder("");
        try {
            for (Method method : obj.getClass().getMethods()) {
                String name = method.getName();
                if (name.startsWith("get")) {
                    String str = name.substring(3, 4).toLowerCase() + name.substring(4);
                    Object invoke = method.invoke(obj, new Object[0]);
                    if (invoke != null && !(invoke instanceof Class)) {
                        sb.append(str);
                        sb.append("=");
                        sb.append(URLEncoder.encode(String.valueOf(invoke), "utf-8"));
                        sb.append("&");
                    }
                }
            }
        } catch (Exception e) {

        }
        return sb.toString();
    }
}
