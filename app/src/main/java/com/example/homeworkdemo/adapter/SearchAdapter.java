package com.example.homeworkdemo.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.homeworkdemo.R;
import com.example.homeworkdemo.bean.SearchWapper;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/*
 *   Created by Dullyoung on 2020/10/20 0020
 */
public class SearchAdapter extends BaseQuickAdapter<SearchWapper.SearchInfo, BaseViewHolder> implements LoadMoreModule {
    public SearchAdapter(@Nullable List<SearchWapper.SearchInfo> data) {
        super(R.layout.item_search, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, SearchWapper.SearchInfo searchInfo) {
        holder.setText(R.id.tv_title, searchInfo.getTitle());
        String desp = "学科：" + searchInfo.getSubject() + " 版本：" + searchInfo.getBookVersion() + " 年级：" + searchInfo.getGrade();
        holder.setText(R.id.tv_desp, desp);
        Glide.with(getContext())
                .load(searchInfo.getCoverURL())
                .into((ImageView) holder.getView(R.id.iv_cover));
    }
}
