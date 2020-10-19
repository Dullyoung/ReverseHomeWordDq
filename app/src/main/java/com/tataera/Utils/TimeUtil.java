package com.tataera.Utils;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {
    public static Date dayBegin(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        return instance.getTime();
    }

    public static Date dayEnd(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(11, 23);
        instance.set(12, 59);
        instance.set(13, 59);
        instance.set(14, 999);
        return instance.getTime();
    }

    public static String getChinatime(long j) {
        if (j < 60) {
            return j + "秒";
        } else if (j < 3600) {
            return (j / 60) + "分" + getChinatime(j % 60);
        } else if (j < 86400) {
            return (j / 3600) + "小时" + getChinatime(j % 3600);
        } else {
            return (j / 86400) + "天" + getChinatime(j % 86400);
        }
    }

    public static long getDate(String str) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
        } catch (ParseException e) {

            date = null;
        }
        return date.getTime();
    }

    public static String getDate(long j) {
        return new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(j));
    }

    public static String getDateMD(long j) {
        return new SimpleDateFormat("MM-dd").format(Long.valueOf(j));
    }

    public static String getDateStr(long j) {
        Date date = new Date(j);
        Date date2 = new Date();
        if (isTheDay(date, date2)) {
            return "今天 " + getDayTimeMinute(j);
        } else if (isTheDay(86400000 + j, date2)) {
            return "昨天 " + getDayTimeMinute(j);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            return simpleDateFormat.format(date) + " " + getDayTimeMinute(j);
        }
    }

    public static long getDateTime(String str, String str2) {
        try {
            return new SimpleDateFormat(str2).parse(str).getTime();
        } catch (ParseException e) {
            return Long.parseLong(str);
        }
    }

    public static String getDayTimeMinute(long j) {
        return new SimpleDateFormat("HH:mm").format(Long.valueOf(j));
    }

    public static String getDuractionTime(long j, long j2) {
        return getChinatime((j2 - j) / 1000);
    }

    public static int getHourSeconds(String str) {
        try {
            Date parse = new SimpleDateFormat("HH:mm:ss").parse(str);
            Calendar instance = Calendar.getInstance();
            instance.setTime(parse);
            int i = instance.get(12);
            return ((i * 60) + 0 + instance.get(13) + (instance.get(10) * 3600)) * 1000;
        } catch (ParseException e) {
            return 0;
        }
    }

    public static long getLastDay(int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(5, -i);
        return instance.getTime().getTime();
    }

    public static String getLeftTime(int i) {
        Object valueOf;
        Object valueOf2;
        Object valueOf3;
        Object valueOf4;
        int i2 = i / 3600000;
        int i3 = i / 1000;
        int i4 = i3 % 60;
        int i5 = (i3 / 60) % 60;
        if (i2 > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("0");
            sb.append(i2);
            sb.append(":");
            if (i5 < 10) {
                valueOf3 = "0" + i5;
            } else {
                valueOf3 = Integer.valueOf(i5);
            }
            sb.append(valueOf3);
            sb.append(":");
            if (i4 < 10) {
                valueOf4 = "0" + i4;
            } else {
                valueOf4 = Integer.valueOf(i4);
            }
            sb.append(valueOf4);
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        if (i5 < 10) {
            valueOf = "0" + i5;
        } else {
            valueOf = Integer.valueOf(i5);
        }
        sb2.append(valueOf);
        sb2.append(":");
        if (i4 < 10) {
            valueOf2 = "0" + i4;
        } else {
            valueOf2 = Integer.valueOf(i4);
        }
        sb2.append(valueOf2);
        return sb2.toString();
    }

    public static String getSimpleIntervalTime(long j, long j2) {
        return getDate(j2);
    }

    public static long getTime(String str) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {

            date = null;
        }
        return date.getTime();
    }

    public static long getTime(String str, String str2) {
        Date date;
        try {
            date = new SimpleDateFormat(str2).parse(str);
        } catch (ParseException e) {

            date = null;
        }
        return date.getTime();
    }

    public static String getTime(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        date.setTime(j);
        return simpleDateFormat.format(date);
    }

    public static String getTime(long j, String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        Date date = new Date();
        date.setTime(j);
        return simpleDateFormat.format(date);
    }

    public static String getTimestr(long j, long j2) {
        if (j2 < 0) {
            j2 += 1000000;
            j--;
        }
        if (j < 60) {
            return j + " 秒" + j2 + "微秒";
        } else if (j < 3600) {
            return (j / 60) + "分" + getTimestr(j % 60, j2);
        } else if (j < 86400) {
            return (j / 3600) + "时" + getTimestr(j % 3600, j2);
        } else {
            return (j / 86400) + "天" + getTimestr(j % 86400, j2);
        }
    }

    public static String getTimestr(Integer num) {
        if (num.intValue() < 60) {
            return num + " 秒";
        }
        return (num.intValue() / 60) + "分" + getTimestr(Integer.valueOf(num.intValue() % 60));
    }

    public static int getToday() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        return Integer.parseInt(simpleDateFormat.format(date));
    }

    public static String getVideoTimestr(Integer num) {
        if (num == null) {
            num = 0;
        }
        Integer valueOf = Integer.valueOf(num.intValue() / 1000);
        int intValue = valueOf.intValue() / 3600;
        int intValue2 = (valueOf.intValue() % 3600) / 60;
        int intValue3 = valueOf.intValue() % 60;
        String str = "" + intValue2;
        String str2 = "" + intValue3;
        if (intValue2 < 10) {
            str = "0" + intValue2;
        }
        if (intValue3 < 10) {
            str2 = "0" + intValue3;
        }
        if (valueOf.intValue() < 60) {
            return str2 + "''";
        } else if (valueOf.intValue() < 3600) {
            return str + "' " + str2 + "''";
        } else {
            return intValue + "'" + str + "' " + str2 + "''";
        }
    }

    public static boolean isTheDay(long j, Date date) {
        return j >= dayBegin(date).getTime() && j <= dayEnd(date).getTime();
    }

    public static boolean isTheDay(Date date, Date date2) {
        return date.getTime() >= dayBegin(date2).getTime() && date.getTime() <= dayEnd(date2).getTime();
    }

    public static String lastDayYYMMDD(String str, int i) {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            instance.setTime(simpleDateFormat.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        instance.add(5, i);
        return simpleDateFormat.format(instance.getTime());
    }

    public static String lastMonthYYMMDD(String str, int i) {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        try {
            instance.setTime(simpleDateFormat.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        instance.add(2, i);
        return simpleDateFormat.format(instance.getTime());
    }

    public static String lastWeekYYMMDD(String str, int i) {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            instance.setTime(simpleDateFormat.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        instance.set(3, i);
        return simpleDateFormat.format(instance.getTime());
    }

    public static String lastYearYYMMDD(String str, int i) {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        try {
            instance.setTime(simpleDateFormat.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        instance.add(1, i);
        return simpleDateFormat.format(instance.getTime());
    }
}
