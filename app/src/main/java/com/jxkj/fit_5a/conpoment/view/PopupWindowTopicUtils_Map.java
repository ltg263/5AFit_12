package com.jxkj.fit_5a.conpoment.view;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jxkj.fit_5a.R;


public class PopupWindowTopicUtils_Map extends PopupWindow {

    Context mContext;
    TextView tv_Distance, tv_speed, tv_time, tv_Calories, tv_Watt, tv_Pulse;
    ImageView mIv;
    @SuppressLint("ClickableViewAccessibility")
    public PopupWindowTopicUtils_Map(Activity context, GiveDialogInterface dialogInterface) {
        super(context);
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.dialog_exercise_layout_map, null);
        mIv = view.findViewById(R.id.iv);
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
        RelativeLayout rl = view.findViewById(R.id.rl);
        LinearLayout mLlBottom = view.findViewById(R.id.ll_bottom);
        tv_Distance = view.findViewById(R.id.tv_Distance);
        tv_speed = view.findViewById(R.id.tv_speed);
        tv_time = view.findViewById(R.id.tv_time);
        tv_Calories = view.findViewById(R.id.tv_Calories);
        tv_Watt = view.findViewById(R.id.tv_Watt);
        tv_Pulse = view.findViewById(R.id.tv_Pulse);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.findViewById(R.id.iv_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSuo = true;
                mLlBottom.setVisibility(View.GONE);
//                mLlBottom.setAnimation(AnimationUtil.makeOutAnimation(mContext,true));
            }
        });
        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIv.isSelected()) {
                    dialogInterface.btnConfirm(0);
                    mIv.setSelected(false);
                } else {
                    dialogInterface.btnConfirm(1);
                    mIv.setSelected(true);
                }
            }
        });
        rl.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // 按下
                    case MotionEvent.ACTION_DOWN:
                        mPosX = event.getX();
                        mPosY = event.getY();
                        break;
                    // 移动
                    case MotionEvent.ACTION_MOVE:
                        mCurrentPosX = event.getX();
                        mCurrentPosY = event.getY();
                        if (mCurrentPosX - mPosX > 0 && Math.abs(mCurrentPosY - mPosY) < 10)
                            Log.e("", "向右");
                        else if (mCurrentPosX - mPosX < 0 && Math.abs(mCurrentPosY - mPosY) < 10)
                            Log.e("", "向左");
                        else if (mCurrentPosY - mPosY > 0 && Math.abs(mCurrentPosX - mPosX) < 10){
                            Log.e("", "向下");
//                            if(!isSuo){
//                                isSuo = true;
//                                mLlBottom.setVisibility(View.GONE);
//                                mLlBottom.setAnimation(AnimationUtil.makeOutAnimation(mContext,false));
//                            }
                        }else if (mCurrentPosY - mPosY < 0 && Math.abs(mCurrentPosX - mPosX) < 10){
                            Log.e("", "向上");
                            if(isSuo){
                                isSuo = false;
                                mLlBottom.setVisibility(View.VISIBLE);
//                                mLlBottom.setAnimation(AnimationUtil.makeOutAnimation(mContext,true));
                            }
                        }
                        break;
                    // 拿起
                    case MotionEvent.ACTION_UP:

                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
    public void setIvSelect(boolean isSelect){
        mIv.setSelected(isSelect);
    }

    boolean isSuo = false;
    private float mPosX;
    private float mPosY;
    private float mCurrentPosX;
    private float mCurrentPosY;
    public void setTextViewStr(String tv_Distance, String tv_speed, String tv_time, String tv_Calories, String tv_Watt, String tv_Pulse) {
        this.tv_Distance.setText(tv_Distance);
        this.tv_speed.setText(tv_speed);
        this.tv_time.setText(tv_time);
        this.tv_Calories.setText(tv_Calories);
        this.tv_Watt.setText(tv_Watt);
        this.tv_Pulse.setText(tv_Pulse);
    }


    public interface GiveDialogInterface {
        /**
         * 确定
         */
        void btnConfirm(int type);
    }


}
