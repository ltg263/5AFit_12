package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.TaskListBase;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineRwzxAdapter extends BaseQuickAdapter<TaskListBase.ListBean, BaseViewHolder> {
    public MineRwzxAdapter(@Nullable List<TaskListBase.ListBean> data) {
        super(R.layout.item_mine_rwzx, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TaskListBase.ListBean item) {
        GlideImageUtils.setGlideImage(mContext,item.getImg(),helper.getView(R.id.iv));
        helper.setText(R.id.tv1,item.getName());

        if(item.getRewards()!=null && item.getRewards().size()>0){
            String detail = item.getRewards().get(0).getDetail();
            String object="";
            try {
                object = new JSONObject(detail).getString("incrementValue");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            helper.setText(R.id.tv3,object);
        }

        if(item.getSpeeds()!=null && item.getSpeeds().size()>0){
            helper.setText(R.id.tv2,"完成"+item.getSpeeds().get(0).getSpeed()+"/"+item.getSpeeds().get(0).getTarget()+item.getSpeeds().get(0).getUnit());
        }

    }

}
