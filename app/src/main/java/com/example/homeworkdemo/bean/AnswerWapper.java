package com.example.homeworkdemo.bean;

import java.sql.ClientInfoStatus;
import java.util.List;

/*
 *   Created by Dullyoung on 2020/10/20 0020
 */
public class AnswerWapper {
    private List<AnswerInfo> adss;
    private String answer;
    private List<AnswerInfo> details;

    public List getAdss() {
        return adss;
    }


    public void setAdss(List<AnswerInfo> adss) {
        this.adss = adss;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<AnswerInfo> getDetails() {
        return details;
    }

    public void setDetails(List<AnswerInfo> details) {
        this.details = details;
    }
}
