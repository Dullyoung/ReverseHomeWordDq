package com.tataera.base.http.Generator;

import android.content.Context;
import android.text.TextUtils;

import com.tataera.ClientMetadata;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */
public class SwUrlGenerator extends AdUrlGenerator{
    private String mSequenceNumber;

    public SwUrlGenerator(Context context) {
        super(context);
    }

    private void setSequenceNumber() {
        if (!TextUtils.isEmpty(this.mSequenceNumber)) {
            addParam("mg", this.mSequenceNumber);
        }
    }

    public void generateParams() {
        initUrlString();
        ClientMetadata instance = ClientMetadata.getInstance(this.mContext);
        setDeviceInfo(instance.getDeviceManufacturer(), instance.getDeviceModel(), instance.getDeviceProduct());
        setAUid(instance.getAuid());
        setOrientation(instance.getOrientationString());
        setDensity(instance.getDensity());
        String networkOperator = instance.getNetworkOperator();
        setMccCode(networkOperator);
        setMncCode(networkOperator);
        setIsoCountryCode(instance.getIsoCountryCode());
        setCarrierName(instance.getNetworkOperatorName());
        setDetailNetworkType(instance.getActiveDetailNetworkType());
        setAppVersion(instance.getAppVersion());
        setSequenceNumber();
        setWifi();
        setImei(instance.getImei());
    }

    /* access modifiers changed from: protected */
    public void setSdkVersion(String str) {
        addParam("nsv", str);
    }

}
