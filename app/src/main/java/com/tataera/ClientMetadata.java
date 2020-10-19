package com.tataera;


import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;


public class ClientMetadata {
    private static final String DEVICE_ORIENTATION_LANDSCAPE = "l";
    private static final String DEVICE_ORIENTATION_PORTRAIT = "p";
    private static final String DEVICE_ORIENTATION_SQUARE = "s";
    private static final String DEVICE_ORIENTATION_UNKNOWN = "u";
    private static final int TYPE_ETHERNET = 9;
    private static final int UNKNOWN_NETWORK = -1;
    private static volatile ClientMetadata sInstance;
    private String mAUid;
    private final String mAppVersion = "1.2.8";
    private final Context mContext;
    private final String mDeviceManufacturer = Build.MANUFACTURER;
    private final String mDeviceModel = Build.MODEL;
    private final String mDeviceProduct = Build.PRODUCT;
    private String mIsoCountryCode;
    private String mNetworkOperator;
    private String mNetworkOperatorName;
    private final String packageName = "com.tataera.daquanhomework";

    public enum TTNetworkType {
        UNKNOWN(0),
        ETHERNET(1),
        WIFI(2),
        MOBILE(3);

        private final int mId;

        private TTNetworkType(int i) {
            this.mId = i;
        }

        /* access modifiers changed from: private */
        public static TTNetworkType fromAndroidNetworkType(int i) {
            if (i == 9) {
                return ETHERNET;
            }
            switch (i) {
                case 0:
                case 2:
                case 3:
                case 4:
                case 5:
                    return MOBILE;
                case 1:
                    return WIFI;
                default:
                    return UNKNOWN;
            }
        }

        public String toString() {
            return Integer.toString(this.mId);
        }
    }

    private ClientMetadata(Context context) {
        this.mContext = context;
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService(Context.TELEPHONY_SERVICE);
        this.mNetworkOperator = telephonyManager.getNetworkOperator();
        if (telephonyManager.getPhoneType() == 2) {
            this.mNetworkOperator = telephonyManager.getSimOperator();
        }
        this.mIsoCountryCode = telephonyManager.getNetworkCountryIso();
        try {
            this.mNetworkOperatorName = telephonyManager.getNetworkOperatorName();
        } catch (SecurityException e) {
            this.mNetworkOperatorName = null;
        }
        this.mAUid = Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    private static String getAppPackageNameFromContext(Context context) {
        try {
            return context.getPackageName();
        } catch (Exception e) {
            return null;
        }
    }

    private static String getAppVersionFromContext(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {

            return null;
        }
    }

    public int getActiveDetailNetworkType() {
        return 1;
    }


    public static ClientMetadata getInstance() {
        ClientMetadata clientMetadata = sInstance;
        if (clientMetadata == null) {
            synchronized (ClientMetadata.class) {
                try {
                    clientMetadata = sInstance;
                } catch (Throwable th) {
                    Class<ClientMetadata> cls = ClientMetadata.class;
                    throw th;
                }
            }
        }
        return clientMetadata;
    }

    public static ClientMetadata getInstance(Context context) {
        ClientMetadata clientMetadata = sInstance;
        if (clientMetadata == null) {
            synchronized (ClientMetadata.class) {
                try {
                    clientMetadata = sInstance;
                    if (clientMetadata == null) {
                        clientMetadata = new ClientMetadata(context);
                        sInstance = clientMetadata;
                    }
                } catch (Throwable th) {
                    Class<ClientMetadata> cls = ClientMetadata.class;
                    throw th;
                }
            }
        }
        return clientMetadata;
    }


    public String getAppVersion() {
        return this.mAppVersion;
    }

    public String getAuid() {
        return this.mAUid;
    }

    public float getDensity() {
        return this.mContext.getResources().getDisplayMetrics().density;
    }

    public String getDeviceManufacturer() {
        return this.mDeviceManufacturer;
    }

    public String getDeviceModel() {
        return this.mDeviceModel;
    }

    public String getDeviceProduct() {
        return this.mDeviceProduct;
    }

    public String getImei() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService(Context.TELEPHONY_SERVICE);
            if (telephonyManager != null) {
                return telephonyManager.getDeviceId();
            }
        } catch (SecurityException e) {
        }
        return "";
    }

    public String getIsoCountryCode() {
        return this.mIsoCountryCode;
    }

    public String getNetworkOperator() {
        return this.mNetworkOperator;
    }

    public String getNetworkOperatorName() {
        return this.mNetworkOperatorName;
    }

    public String getOrientationString() {
        int i = this.mContext.getResources().getConfiguration().orientation;
        return i == 1 ? "p" : i == 2 ? "l" : i == 3 ? DEVICE_ORIENTATION_SQUARE : DEVICE_ORIENTATION_UNKNOWN;
    }

    public String getPackageName() {
        return this.packageName;
    }
}
