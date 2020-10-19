package com.tataera.base.http.Generator;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

import androidx.core.app.ActivityCompat;

import com.tataera.ClientMetadata;
import com.tataera.base.ETApplication;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */
public abstract class AdUrlGenerator extends BaseUrlGenerator {
    public static final String MCC_CMCC = "00";
    public static final String MCC_CTCC = "03";
    public static final String MCC_CUCC = "01";
    protected Context mContext;
    protected TelephonyManager mTelephonyManager;
    protected WifiManager mWifiManager;

    public AdUrlGenerator(Context context) {
        this.mContext = context;
        this.mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        mTelephonyManager = ((TelephonyManager) this.mContext.getSystemService(Context.TELEPHONY_SERVICE));
    }

    private void addParam(String str, ClientMetadata.TTNetworkType tTNetworkType) {
        addParam(str, tTNetworkType.toString());
    }

    private int mncPortionLength(String str) {
        return Math.min(3, str.length());
    }

    /* access modifiers changed from: protected */
    public String getNetworkOperator() {
        String networkOperator = this.mTelephonyManager.getNetworkOperator();
        //  return (this.mTelephonyManager.getPhoneType() == 2) ? this.mTelephonyManager.getSimOperator() : networkOperator;
        return "GSM";
    }

    /* access modifiers changed from: protected */
    public void setCarrierName(String str) {
        addParam("cn", str);
    }

    public void setCid() {
        GsmCellLocation gsmCellLocation;
        CdmaCellLocation cdmaCellLocation;
        try {
            String networkOperator = getNetworkOperator();
            String substring = networkOperator == null ? "" : networkOperator.substring(mncPortionLength(networkOperator));
            if (MCC_CMCC.equalsIgnoreCase(substring) || MCC_CUCC.equalsIgnoreCase(substring)) {
                if (ActivityCompat.checkSelfPermission(ETApplication.getInstance(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                if ((this.mTelephonyManager.getCellLocation() instanceof GsmCellLocation) && (gsmCellLocation = (GsmCellLocation) this.mTelephonyManager.getCellLocation()) != null) {
                    int lac = gsmCellLocation.getLac();
                    int cid = gsmCellLocation.getCid();
                    addParam("lac", String.valueOf(lac));
                    addParam("cid", String.valueOf(cid));
                }
            } else if ((this.mTelephonyManager.getCellLocation() instanceof CdmaCellLocation) && (cdmaCellLocation = (CdmaCellLocation) this.mTelephonyManager.getCellLocation()) != null) {
                addParam("lac", String.valueOf(cdmaCellLocation.getNetworkId()));
                addParam("cid", String.valueOf(cdmaCellLocation.getBaseStationId() / 16));
            }
        } catch (Exception e) {

        }
    }

    /* access modifiers changed from: protected */
    public void setDensity(float f) {
        addParam("sc_a", "" + f);
    }

    /* access modifiers changed from: protected */
    public void setDetailNetworkType(int i) {
        addParam("dct", String.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public void setImei(String str) {
        addParam("imei", str);
    }

    /* access modifiers changed from: protected */
    public void setIsoCountryCode(String str) {
        addParam("iso", str);
    }

    /* access modifiers changed from: protected */
    public void setKeywords(String str) {
        addParam("q", str);
    }

    /* access modifiers changed from: protected */
    public void setLocation(Location location) {
        if (location != null) {
            addParam("ll", location.getLatitude() + "," + location.getLongitude());
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append((int) location.getAccuracy());
            addParam("lla", sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void setMccCode(String str) {
        addParam("mcc", str == null ? "" : str.substring(0, mncPortionLength(str)));
    }

    /* access modifiers changed from: protected */
    public void setMncCode(String str) {
        addParam("mnc", str == null ? "" : str.substring(mncPortionLength(str)));
    }

    /* access modifiers changed from: protected */
    public void setMraidFlag(boolean z) {
        if (z) {
            addParam("mr", "1");
        }
    }

    /* access modifiers changed from: protected */
    public void setNetworkType(ClientMetadata.TTNetworkType tTNetworkType) {
        addParam("ct", tTNetworkType);
    }

    /* access modifiers changed from: protected */
    public void setOrientation(String str) {
        addParam("o", str);
    }

    public void setPackage(String str) {
        addParam("pkn", str);
    }

    /* access modifiers changed from: protected */
    public void setSdkVersion(String str) {
        addParam("nv", str);
    }

    /* access modifiers changed from: protected */
    public void setTimezone(String str) {
        addParam("z", str);
    }

    public void setWifi() {
        WifiInfo connectionInfo;
        if (this.mWifiManager != null && (connectionInfo = this.mWifiManager.getConnectionInfo()) != null) {
            StringBuilder sb = new StringBuilder("");
            sb.append(connectionInfo.getMacAddress());
            sb.append(",");
            sb.append(connectionInfo.getSSID() == null ? "" : connectionInfo.getSSID());
            addParam("wifi", sb.toString());
        }
    }

}
