package com.example.homeworkdemo.bean;

import java.io.Serializable;
import java.util.List;

/*
 *   Created by Dullyoung on 2020/10/20 0020
 */
public class SearchWapper implements  Serializable{
    private String traceID;
    private boolean lock;
    private List<SearchInfo> list;

    public String getTraceID() {
        return traceID;
    }

    public void setTraceID(String traceID) {
        this.traceID = traceID;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public List<SearchInfo> getList() {
        return list;
    }

    public void setList(List<SearchInfo> list) {
        this.list = list;
    }

    public class SearchInfo implements Serializable {

        private String bookVersion;
        private String code;
        private String coverURL;
        private String createTime;
        private String grade;
        private int hasLogo;
        private boolean hasVip;
        private String id;
        private String publisher;
        private String subject;
        private String title;
        private String updateTime;
        private String uploaderName;
        private boolean vip;
        private String volumn;
        private int year;

        public void setBookVersion(String bookVersion) {
            this.bookVersion = bookVersion;
        }

        public String getBookVersion() {
            return bookVersion;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public void setCoverURL(String coverURL) {
            this.coverURL = coverURL;
        }

        public String getCoverURL() {
            return coverURL;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getGrade() {
            return grade;
        }

        public void setHasLogo(int hasLogo) {
            this.hasLogo = hasLogo;
        }

        public int getHasLogo() {
            return hasLogo;
        }

        public void setHasVip(boolean hasVip) {
            this.hasVip = hasVip;
        }

        public boolean getHasVip() {
            return hasVip;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getSubject() {
            return subject;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUploaderName(String uploaderName) {
            this.uploaderName = uploaderName;
        }

        public String getUploaderName() {
            return uploaderName;
        }

        public void setVip(boolean vip) {
            this.vip = vip;
        }

        public boolean getVip() {
            return vip;
        }

        public void setVolumn(String volumn) {
            this.volumn = volumn;
        }

        public String getVolumn() {
            return volumn;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getYear() {
            return year;
        }


    }
}
