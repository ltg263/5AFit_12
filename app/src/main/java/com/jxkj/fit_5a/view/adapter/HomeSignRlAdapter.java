package com.jxkj.fit_5a.view.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;

import java.util.Date;
import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeSignRlAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HomeSignRlAdapter(@Nullable List<String> data) {
        super(R.layout.item_home_sign_rl, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

        if(StringUtil.isBlank(item)){
            helper.setGone(R.id.ll,false);
            return;
        }
        helper.setGone(R.id.ll,true).setText(R.id.tv_rq,item);

        helper.setVisible(R.id.view,false).setVisible(R.id.img,false)
                .setVisible(R.id.tv_rq,true).setVisible(R.id.view_dot,false)
                .setTextColor(R.id.tv_rq,mContext.getResources().getColor(R.color.color_333333));

        if(StringUtil.getDay(new Date())==Integer.valueOf(item)){//当前日期选中
            helper.setVisible(R.id.view,true).setTextColor(R.id.tv_rq,mContext.getResources().getColor(R.color.color_ffffff));
        }

        if(Integer.valueOf(item)==6){//已签到日期先点
            helper.setVisible(R.id.view_dot,true);
        }
        if(Integer.valueOf(item)==8 ||Integer.valueOf(item)==10 ||Integer.valueOf(item)==3){//有礼物的日期
            helper.setVisible(R.id.img,true).setVisible(R.id.tv_rq,false);
        }

    }

}
