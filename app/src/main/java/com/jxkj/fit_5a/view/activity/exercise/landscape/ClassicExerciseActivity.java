package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTopicUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassicExerciseActivity extends Activity {
    @BindView(R.id.tv_1)
    TextView mTv1;
    @BindView(R.id.tv_2)
    TextView mTv2;
    @BindView(R.id.iv_jieshu)
    ImageView iv_jieshu;
    @BindView(R.id.ll)
    LinearLayout mLl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape_classic_exercise);
        ButterKnife.bind(this);
        initViews();
    }
    PopupWindowTopicUtils window;
    private void initViews() {
        mTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(window!=null && window.isShowing()){
                    return;
                }
                window = new PopupWindowTopicUtils(ClassicExerciseActivity.this, (topicId, str) -> {
                });
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                window.showAtLocation(mTv1, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
            }
        });
        mTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLl.setVisibility(View.VISIBLE);
                if(window!=null && window.isShowing()){
                    window.dismiss();
                    // 向右边移出
                }
            }
        });
        mLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        iv_jieshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.showDialogOutRoom(ClassicExerciseActivity.this,    new DialogUtils.DialogLyInterface() {
                    @Override
                    public void btnConfirm() {
                    }
                });
            }
        });
    }

    public static void intentStartActivity(Context mContext) {
        mContext.startActivity(new Intent(mContext, ClassicExerciseActivity.class));
    }

}
