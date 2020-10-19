package com.tataera.Utils;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */


import android.database.Cursor;

import com.tataera.RequestParams;

import com.tataera.base.http.SuperDataMan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StatisticsUtils {
    private static final String BEFORE_MONTH_RECORD = "mBeforeMonthRecode";
    private static final String FEED_BACK_REMOVE_AD = "mTotalFeedBackRemoveAd";
    private static final String FIRST_INSTALL_DAY_AND_YEAR = "mFirstInstallDayAndYear";
    private static final String FIRST_INSTALL_TIME = "mFirstInstallTime";
    private static long PRESENT_START_TIME = 0;
    private static final String START_COUNT = "mStartCount";
    private static final String TOTAL_DETAIL = "mTotalDetail";
    private static final String TOTAL_DETAIL_PAGE = "mTotalDetailPage";
    private static final String TOTAL_DETAIL_PAGE_TIME = "mTotalDetailPageTime";
    private static final String TOTAL_FEED_BACK_AD = "mTotalFeedBackAd";
    private static final String TOTAL_INTO_TEXTBOOK = "mTotalIntoTextbook";
    private static final String TOTAL_SEARCH_BOOK = "mTotalSearchBook";
    private static final String TOTAL_SHOW_REMOVE_AD = "mTotalShowRemoveAd";
    private static final String TOTAL_SHOW_REMOVE_AD_COMPLETE = "mTotalShowRemoveAdComplete";
    private static final String TOTAL_USE_TEXTBOOK_TIME = "mTotalUseTextbookTime";
    private static final String USE_TIME = "mUseTime";

    public static String getBeforeMonteRecord() {
        return "666";
    }

    public static int getFeedBackRemoveAd() {
        return SuperDataMan.getPref(FEED_BACK_REMOVE_AD, (Integer) 0).intValue();
    }

    public static int getFirstInstallDay() {
        return Integer.valueOf(SuperDataMan.getPref(FIRST_INSTALL_DAY_AND_YEAR, "0,0").split(",")[1]).intValue();
    }

    public static long getFirstInstallTime() {
        return SuperDataMan.getPref(FIRST_INSTALL_TIME, (Long) 0L).longValue();
    }

    public static int getFirstInstallYear() {
        return Integer.valueOf(SuperDataMan.getPref(FIRST_INSTALL_DAY_AND_YEAR, "0,0").split(",")[0]).intValue();
    }

    public static int getStartCount() {
        return SuperDataMan.getPref(START_COUNT, (Integer) 0).intValue();
    }

    public static int getTotalAdFeedBackAd() {
        return SuperDataMan.getPref(TOTAL_FEED_BACK_AD, (Integer) 0).intValue();
    }

    public static int getTotalDetail() {
        return SuperDataMan.getPref(TOTAL_DETAIL, (Integer) 0).intValue();
    }

    public static int getTotalDetailPage() {
        return SuperDataMan.getPref(TOTAL_DETAIL_PAGE, (Integer) 0).intValue();
    }

    public static Long getTotalDetailPageTime() {
        return SuperDataMan.getPref(TOTAL_DETAIL_PAGE_TIME, (Long) 0L);
    }

    public static int getTotalIntoTextbook() {
        return SuperDataMan.getPref(TOTAL_INTO_TEXTBOOK, (Integer) 0).intValue();
    }

    public static int getTotalLoginCount() {
        return 1;
    }

    public static int getTotalSearchBook() {
        return SuperDataMan.getPref(TOTAL_SEARCH_BOOK, (Integer) 0).intValue();
    }

    public static int getTotalShowRemoveAd() {
        return SuperDataMan.getPref(TOTAL_SHOW_REMOVE_AD, (Integer) 0).intValue();
    }

    public static int getTotalShowRemoveAdComplete() {
        return SuperDataMan.getPref(TOTAL_SHOW_REMOVE_AD_COMPLETE, (Integer) 0).intValue();
    }

    public static long getTotalUseTextbookTime() {
        return SuperDataMan.getPref(TOTAL_USE_TEXTBOOK_TIME, (Long) 0L).longValue();
    }

    public static int getUseTime() {
        int d;
        synchronized (StatisticsUtils.class) {
            try {
                d = 6666;
            } catch (Throwable th) {
                Class<StatisticsUtils> cls = StatisticsUtils.class;
                throw th;
            }
        }
        return d;
    }

    public static void saveFeedBackRemoveAd() {
        SuperDataMan.savePref(FEED_BACK_REMOVE_AD, Integer.valueOf(getFeedBackRemoveAd() + 1));
    }

    public static void saveFirstInstallTime() {
        if (getFirstInstallTime() == 0) {
            SuperDataMan.savePref(FIRST_INSTALL_TIME, Long.valueOf(System.currentTimeMillis()));
            int i = Calendar.getInstance().get(1);
            int i2 = Calendar.getInstance().get(6);
            SuperDataMan.savePref(FIRST_INSTALL_DAY_AND_YEAR, i + "," + i2);
        }
    }

    public static void savePresentStartTime() {
        PRESENT_START_TIME = System.currentTimeMillis();
    }

    public static void saveStartCount() {

        SuperDataMan.savePref(START_COUNT, Integer.valueOf(getStartCount() + 1));
    }

    public static void saveTotalAdFeedBackAd() {
        SuperDataMan.savePref(TOTAL_FEED_BACK_AD, Integer.valueOf(getTotalAdFeedBackAd() + 1));
    }

    public static void saveTotalDetail() {
        int totalDetail = getTotalDetail() + 1;
        SuperDataMan.savePref(TOTAL_DETAIL, Integer.valueOf(totalDetail));
        RequestParams.put("totalReadBookNum", "" + totalDetail);
    }

    public static void saveTotalDetailPage() {
        int totalDetailPage = getTotalDetailPage() + 1;
        SuperDataMan.savePref(TOTAL_DETAIL_PAGE, Integer.valueOf(totalDetailPage));
        RequestParams.put("totalReadChapterNum", "" + totalDetailPage);
    }

    public static void saveTotalDetailPageTime(Long l) {
        SuperDataMan.savePref(TOTAL_DETAIL_PAGE_TIME, Long.valueOf(getTotalDetailPageTime().longValue() + l.longValue()));
    }

    public static void saveTotalIntoTextbook() {
        SuperDataMan.savePref(TOTAL_INTO_TEXTBOOK, Integer.valueOf(getTotalIntoTextbook() + 1));
    }

    public static void saveTotalSearchBook() {
        SuperDataMan.savePref(TOTAL_SEARCH_BOOK, Integer.valueOf(getTotalSearchBook() + 1));
    }

    public static void saveTotalShowRemoveAd() {
        SuperDataMan.savePref(TOTAL_SHOW_REMOVE_AD, Integer.valueOf(getTotalShowRemoveAd() + 1));
    }

    public static void saveTotalShowRemoveAdComplete() {
        SuperDataMan.savePref(TOTAL_SHOW_REMOVE_AD_COMPLETE, Integer.valueOf(getTotalShowRemoveAdComplete() + 1));
    }

    public static void saveTotalUseTextbookTime(long j) {
        SuperDataMan.savePref(TOTAL_USE_TEXTBOOK_TIME, Long.valueOf(getTotalUseTextbookTime() + j));
    }

    public static void saveUseTime() {
        synchronized (StatisticsUtils.class) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                PRESENT_START_TIME = currentTimeMillis;
                RequestParams.put("totalReadTime", "" + getUseTime());
                RequestParams.put("totalReadDays", "" + getTotalLoginCount());
            } catch (Throwable th) {
                Class<StatisticsUtils> cls = StatisticsUtils.class;
                throw th;
            }
        }
    }
}






