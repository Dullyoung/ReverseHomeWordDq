package com.example.homeworkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.homeworkdemo.Control.BaseActivity;
import com.example.homeworkdemo.Control.SearchDetailActivity;
import com.example.homeworkdemo.adapter.SearchAdapter;
import com.example.homeworkdemo.bean.ResultBean;
import com.example.homeworkdemo.bean.SearchWapper;
import com.tataera.Config;
import com.tataera.base.http.HttpModuleHandleListener;
import com.tataera.base.http.IHttpJsonConvert;
import com.tataera.base.http.SuperDataMan;

import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.et_word)
    EditText mEtWord;
    @BindView(R.id.rv_result)
    RecyclerView mRvResult;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private SearchAdapter mSearchAdapter;

    @Override
    protected void initViews() {
        mSearchAdapter = new SearchAdapter(null);
        mRvResult.setAdapter(mSearchAdapter);
        mRvResult.setLayoutManager(new LinearLayoutManager(this));
        mRvResult.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mSearchAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(this, SearchDetailActivity.class);
            intent.putExtra("keyword", mEtWord.getText().toString());
            if (!TextUtils.isEmpty(traceID)) {
                intent.putExtra("traceID", traceID);
            }
            SearchWapper.SearchInfo searchInfo = (SearchWapper.SearchInfo) adapter.getData().get(position);
            intent.putExtra("data", searchInfo);
            startActivity(intent);
        });

        mSearchAdapter.getLoadMoreModule().setOnLoadMoreListener(() -> {
            page++;
            doSearch();
        });

        mEtWord.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        mEtWord.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                page = 1;
                doSearch();
                return true;
            } else {
                return false;
            }

        });

    }

    String traceID = "";

    SuperDataMan mSuperDataMan = new SuperDataMan();

    int page = 1;


    public void search(String str, HttpModuleHandleListener httpModuleHandleListener) {
        ArrayList arrayList = new ArrayList();

        try {
            arrayList.add(new BasicNameValuePair("keyword", URLEncoder.encode(str, "utf-8")));
            arrayList.add(new BasicNameValuePair("pageNo", URLEncoder.encode("" + page, "utf-8")));
            arrayList.add(new BasicNameValuePair("pageSize", URLEncoder.encode("10", "utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mSuperDataMan.handle(Config.SEARCH_URL, arrayList, httpModuleHandleListener, new IHttpJsonConvert() {
            public Object convert(String str) {
                Log.i("aaaa", "返回数据: " + str);
                ResultBean<SearchWapper> resultBean =
                        JSONObject.parseObject(str, new TypeReference<ResultBean<SearchWapper>>() {
                        }.getType());
                if (resultBean.getDatas() == null) {
                    mSearchAdapter.getLoadMoreModule().loadMoreEnd();
                    return null;
                }
                traceID = resultBean.getDatas().getTraceID();
                return resultBean.getDatas().getList();
            }
        });
    }

    private void doSearch() {
        search(mEtWord.getText().toString(), new HttpModuleHandleListener() {
            @Override
            public void onComplete(Object obj, Object obj2) {
                Log.i("aaaa", "onComplete:" + obj + "\n" + obj2);
                if (obj2 == null) {
                    return;
                }

                List<SearchWapper.SearchInfo> searchInfos = (List<SearchWapper.SearchInfo>) obj2;
                if (page == 1) {
                    mSearchAdapter.setNewInstance(null);
                    mSearchAdapter.setNewInstance(searchInfos);
                } else {
                    mSearchAdapter.addData(searchInfos);
                }

                if (searchInfos.size() < 10) {
                    mSearchAdapter.getLoadMoreModule().loadMoreEnd();
                } else {
                    mSearchAdapter.getLoadMoreModule().loadMoreComplete();
                }
            }

            @Override
            public void onFail(Object obj, String str) {
                if (mSearchAdapter.getData().size() == 0) {
                    mSearchAdapter.setEmptyView(null);
                    mSearchAdapter.setEmptyView(R.layout.view_nodata);
                } else {
                    mSearchAdapter.getLoadMoreModule().loadMoreEnd();
                }
            }
        });
    }

    @OnClick(R.id.btn_search)
    public void onViewClicked() {
        page = 1;
        doSearch();
    }
}