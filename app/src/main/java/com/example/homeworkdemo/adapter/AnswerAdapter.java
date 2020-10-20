package com.example.homeworkdemo.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.homeworkdemo.R;
import com.example.homeworkdemo.bean.AnswerInfo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/*
 *   Created by Dullyoung on 2020/10/20 0020
 */
public class AnswerAdapter extends BaseQuickAdapter<AnswerInfo, BaseViewHolder> {
    public AnswerAdapter(@Nullable List<AnswerInfo> data) {
        super(R.layout.item_answer, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, AnswerInfo answerInfo) {
        holder.setText(R.id.tv_idx, "第" + answerInfo.getIdx() + "张");
        Glide.with(getContext())
                .load(answerInfo.getAnswerURL())
                .into((ImageView) holder.getView(R.id.iv_answer));
    }
}
