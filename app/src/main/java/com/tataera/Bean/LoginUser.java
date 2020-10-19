package com.tataera.Bean;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */
public class LoginUser {
    private String loginType;
    private String nickName;
    private String openId;
    private String userheadImgUrl;

    public String getLoginType() {
        return this.loginType;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getOpenId() {
        return this.openId;
    }

    public String getUserheadImgUrl() {
        return this.userheadImgUrl;
    }

    public void setLoginType(String str) {
        this.loginType = str;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public void setOpenId(String str) {
        this.openId = str;
    }

    public void setUserheadImgUrl(String str) {
        this.userheadImgUrl = str;
    }
}
