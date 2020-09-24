package com.jxkj.fit_5a.view.adapter;

import android.content.Context;
import android.text.Html;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.OrderInfoData;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2615:09
 * 305.2+76.30+1920
 *
 */
public class OrderAffirmAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    Context activity;
    public OrderAffirmAdapter(Context activity, @Nullable List<String> data) {
        super(R.layout.item_order_affirm, data);
        this.activity = activity;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {


    }


}
