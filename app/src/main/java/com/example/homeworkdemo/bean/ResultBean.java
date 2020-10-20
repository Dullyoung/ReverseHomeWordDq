package com.example.homeworkdemo.bean;

import java.util.List;

/*
 *   Created by Dullyoung on 2020/10/20 0020
 */
public  class ResultBean<T> {
    private int code;
    private T datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }
}
