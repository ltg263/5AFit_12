package com.jxkj.fit_5a.conpoment.view;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.view.adapter.PopupWindwSbAdapter;
import com.jxkj.fit_5a.view.adapter.TopicListTAdapter;

import java.util.ArrayList;
import java.util.List;


public class PopupWindowSb extends PopupWindow {

    private final PopupWindwSbAdapter mPopupWindwSbAdapter;
    Context mContext;

    @SuppressLint("ClickableViewAccessibility")
    public PopupWindowSb(Activity context, List<DeviceTypeData.ListBean> dataSbType, GiveDialogInterface dialogInterface) {
        super(context);
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.popup_window_sb, null);

        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ActionBar.LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点�?
        this.setFocusable(false);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.transparent));
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        RecyclerView mRvList = view.findViewById(R.id.rv_list);
        mPopupWindwSbAdapter = new PopupWindwSbAdapter(dataSbType);
        mRvList.setLayoutManager(new LinearLayoutManager(context));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mPopupWindwSbAdapter);

        mPopupWindwSbAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dialogInterface.btnConfirm(dataSbType.get(position).getId(),dataSbType.get(position).getName());
            }
        });
    }



    public interface GiveDialogInterface {
        /**
         * 确定
         */
        void btnConfirm(int topicId, String str);
    }


}
