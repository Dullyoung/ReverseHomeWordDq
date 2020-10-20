package com.example.homeworkdemo.Control;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.homeworkdemo.R;
import com.example.homeworkdemo.widgets.PinchImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BigImageActivity extends BaseActivity {

    @BindView(R.id.piv)
    PinchImageView mPiv;
    @BindView(R.id.tv_idx)
    TextView mTvIdx;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_big_image;
    }

    @Override
    protected void initViews() {

        String url = getIntent().getStringExtra("url");
        String idx = getIntent().getStringExtra("idx");
        Glide.with(this)
                .load(url)
                .into(mPiv);
        mTvIdx.setText("第" + idx + "张");
        mPiv.setOnClickListener(v -> {
            finish();
        });
    }


}