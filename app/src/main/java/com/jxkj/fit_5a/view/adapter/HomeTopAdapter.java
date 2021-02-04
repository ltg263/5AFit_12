package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.entity.SportLogStatsBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeTopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    SportLogStatsBean.StatsBean stats;
    public HomeTopAdapter(@Nullable List<String> data) {
        super(R.layout.item_home_top, data);
    }

//                            list.add("卡路里");
//                            list.add("总里程");
//                            list.add("总计时间");
//                            list.add("平均时间");
    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name,item);
        if(item.equals("卡路里")){
            helper.setText(R.id.tv_v,stats.getTotalCalories()+"cal")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.icon_task_2));
        }else if(item.equals("总里程")){
            helper.setText(R.id.tv_v,stats.getTotalDistance()+"km")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.icon_task_3));
        }else if(item.equals("总计时间")){
            helper.setText(R.id.tv_v,stats.getTotalCalories()+"min")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.icon_task_4));
        }else if(item.equals("平均时间")){
            helper.setText(R.id.tv_v,stats.getTotalDuration()+"min")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.icon_task_4));
        }
    }

    public void setNewData(List<String> list, SportLogStatsBean.StatsBean stats) {
        this.stats = stats;
        setNewData(list);
    }
}
