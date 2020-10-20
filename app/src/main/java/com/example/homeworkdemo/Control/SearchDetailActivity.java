package com.example.homeworkdemo.Control;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.homeworkdemo.R;
import com.example.homeworkdemo.adapter.AnswerAdapter;
import com.example.homeworkdemo.bean.AnswerInfo;
import com.example.homeworkdemo.bean.AnswerWapper;
import com.example.homeworkdemo.bean.ResultBean;
import com.example.homeworkdemo.bean.SearchWapper;
import com.tataera.ClientMetadata;
import com.tataera.Utils.StatisticsUtils;
import com.tataera.base.ETApplication;
import com.tataera.base.http.HttpModuleHandleListener;
import com.tataera.base.http.IHttpJsonConvert;
import com.tataera.base.http.SuperDataMan;

import org.apache.http.message.BasicNameValuePair;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchDetailActivity extends BaseActivity {


    @BindView(R.id.rv_answer)
    RecyclerView mRvAnswer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_detail;
    }

    AnswerAdapter mAnswerAdapter;

    @Override
    protected void initViews() {
        mAnswerAdapter = new AnswerAdapter(null);
        mRvAnswer.setLayoutManager(new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false));
        mRvAnswer.setAdapter(mAnswerAdapter);

        mAnswerAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(this, BigImageActivity.class);
            intent.putExtra("url", ((AnswerInfo) adapter.getData().get(position)).getAnswerURL());
            intent.putExtra("idx", ((AnswerInfo) adapter.getData().get(position)).getIdx() + "");
            startActivity(intent);
        });


        SearchWapper.SearchInfo searchInfo = (SearchWapper.SearchInfo) getIntent().getSerializableExtra("data");

        search(searchInfo.getId(), getIntent().getStringExtra("traceID"), 1,
                new HttpModuleHandleListener() {
                    @Override
                    public void onComplete(Object obj, Object obj2) {
                        List<AnswerInfo> infos = (List<AnswerInfo>) obj2;
                        mAnswerAdapter.setNewInstance(infos);
                    }

                    @Override
                    public void onFail(Object obj, String str) {

                    }
                });
    }


    public static final String md5Id(String str) {

        StringBuffer hexString = new StringBuffer();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(str.getBytes());
        byte[] hash = md.digest();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }


    public void search(String id, String traceID, int targetIdx, HttpModuleHandleListener httpModuleHandleListener) {
        StatisticsUtils.saveTotalDetail();
        String str6 = System.currentTimeMillis() + "";
        String a2 = md5Id(id + ":" + "&&*%$dkeunk0*!@^*&%nnc<scvqw" + ":" + str6);
        ArrayList arrayList = new ArrayList();
        String openId = "";
        try {
            ClientMetadata instance = ClientMetadata.getInstance(ETApplication.getInstance());
            String imei = instance.getImei();
            if (TextUtils.isEmpty(imei)) {
                openId = "auid:" + instance.getAuid();
            } else {
                openId = "imei:" + imei;
            }

            arrayList.add(new BasicNameValuePair("source", URLEncoder.encode("book", "utf-8")));
            arrayList.add(new BasicNameValuePair("openID", URLEncoder.encode(openId, "utf-8")));
            arrayList.add(new BasicNameValuePair("answerID", URLEncoder.encode(id, "utf-8")));
            arrayList.add(new BasicNameValuePair("sourceType", URLEncoder.encode("book", "utf-8")));
            arrayList.add(new BasicNameValuePair("t", URLEncoder.encode(str6, "utf-8")));
            arrayList.add(new BasicNameValuePair("k", URLEncoder.encode(a2, "utf-8")));
            if (!TextUtils.isEmpty(traceID)) {
                arrayList.add(new BasicNameValuePair("traceID", URLEncoder.encode(traceID, "utf-8")));
            }
            if (targetIdx != -1) {
                arrayList.add(new BasicNameValuePair("targetIdx", URLEncoder.encode(String.valueOf(targetIdx), "utf-8")));
            }
        } catch (Exception e) {
        }
        SuperDataMan superDataMan = new SuperDataMan();
        superDataMan.handle("http://zuoyeapi.tatatimes.com/homeworkapi/api.s?h=ZYAnswerDetailHandler", arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                Log.i("aaaa", "convert: " + str);
                ResultBean<AnswerWapper> resultBean = JSONObject.parseObject(str, new TypeReference<ResultBean<AnswerWapper>>() {
                }.getType());

                return resultBean.getDatas().getDetails();
            }

        });
    }



}