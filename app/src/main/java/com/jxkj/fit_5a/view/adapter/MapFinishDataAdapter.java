package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.BpmDataBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MapFinishDataAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    BpmDataBean.BpmTopData stats;
    public MapFinishDataAdapter(@Nullable List<String> data) {
        super(R.layout.item_home_top, data);
    }
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name,item);
        if(item.equals("总卡路里")){
            helper.setText(R.id.tv_v,stats.getCalories()+"cal")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.ic_home_top_1))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fde5e5);
        }else if(item.equals("运动里程")){
            helper.setText(R.id.tv_v,stats.getDistance()+"km")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.ic_home_top_2))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_1091e7);
        }else if(item.equals("运动时间")){
            helper.setText(R.id.tv_v,stats.getDuration()+"s")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.ic_home_top_4))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fdf7e5);
        }else if(item.equals("功率")){
            helper.setText(R.id.tv_v, "--W")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.ic_home_top_4))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fdf7e5);
        }else if(item.equals("段位")){
            helper.setText(R.id.tv_v, "--W")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.ic_home_top_4))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fdf7e5);
        }else if(item.equals("平均速度")){
            helper.setText(R.id.tv_v,stats.getPjDuration()+"km/h")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.ic_home_top_2))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_1091e7);
        }else if(item.equals("最快速度")){
            helper.setText(R.id.tv_v,"--km/h")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.ic_home_top_2))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_1091e7);
        }else if(item.equals("平均心跳")){
            helper.setText(R.id.tv_v,stats.getHeartRate()+"次/分钟")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.ic_home_top_4))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fde5e5);
        }else if(item.equals("心率区间")){
            helper.setText(R.id.tv_v,"--次/分钟")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.ic_home_top_4))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fde5e5);
        }else if(item.equals("BAI")){
            helper.setText(R.id.tv_v,"--BAI")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.ic_home_top_4))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fde5e5);
        }
    }

    public void setNewData(List<String> list,BpmDataBean.BpmTopData stats) {
        this.stats = stats;
        setNewData(list);
    }
}
