package com.example.homeworkdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tataera.Config;
import com.tataera.base.ETApplication;
import com.tataera.base.ETMan;
import com.tataera.base.http.HttpModuleHandleListener;
import com.tataera.base.http.IHttpJsonConvert;
import com.tataera.base.http.SuperDataMan;

import org.apache.http.message.BasicNameValuePair;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tv_desp;
    private EditText et_word;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ETApplication.init(this);
        initView();
        //懒得弄申请权限 自己去设置赋予一下~~~
    }

    SuperDataMan mSuperDataMan = new SuperDataMan();


    public void h(String str, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(new BasicNameValuePair("keyword", URLEncoder.encode(str, "utf-8")));
        } catch (Exception e) {
        }
        mSuperDataMan.handle(Config.SEARCH_URL, arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                try {
//                    Map map = (Map) ETMan.getMananger().getGson().fromJson(str, HashMap.class);
//                    List list = (List) map.get("datas");
                    Log.i("aaaa", "convert: "+str);
                return str;
                } catch (Exception e) {
                }
                return null;
            }
        });
    }


    private void initView() {
        tv_desp = (TextView) findViewById(R.id.tv_desp);
        et_word = (EditText) findViewById(R.id.et_word);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                h(et_word.getText().toString(), new HttpModuleHandleListener() {
                    @Override
                    public void onComplete(Object obj, Object obj2) {
                        tv_desp.setText(obj+"``````\n"+obj2);
                        Log.i("aaaa", "onComplete: "+obj+"``````\n"+obj2);
                    }

                    @Override
                    public void onFail(Object obj, String str) {
                        tv_desp.setText(obj+"``````\n"+str);
                        Log.i("aaaa", "onFail: "+obj+"``````\n"+str);
                    }
                });
                break;
        }
    }


}