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

import androidx.annotation.Nullable;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTopicUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassicExerciseActivity extends Activity {
    @BindView(R.id.iv_1)
    ImageView mIv1;
    @BindView(R.id.iv_2)
    ImageView mIv2;
    @BindView(R.id.iv_3)
    ImageView mIv3;
    @BindView(R.id.iv_4)
    ImageView mIv4;
    @BindView(R.id.ll)
    LinearLayout mLl;
    boolean isSuo = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape_classic_exercise);
        ButterKnife.bind(this);
        initViews();
    }
    PopupWindowTopicUtils window;
    private void initViews() {
//        DialogUtils.showDialogClass(ClassicExerciseActivity.this, 1, new DialogUtils.DialogLyInterface() {
//            @Override
//            public void btnConfirm() {
//
//            }
//        });
        mIv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIv3.getVisibility() == View.VISIBLE){
                    mIv3.setVisibility(View.GONE);
                    mIv4.setVisibility(View.GONE);
                }else{
                    mIv3.setVisibility(View.VISIBLE);
                    mIv4.setVisibility(View.VISIBLE);
                }
            }
        });
        mIv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSuo){
                    isSuo = false;
                    mIv2.setImageDrawable(ClassicExerciseActivity.this.getResources().getDrawable(R.mipmap.ic_hp_yd_9));
                    if(window!=null && window.isShowing()){
                        return;
                    }
                    window = new PopupWindowTopicUtils(ClassicExerciseActivity.this, (topicId, str) -> {
                    });
                    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                    window.showAtLocation(mLl, Gravity.BOTTOM, 0, 0); // 设置layout在PopupWindow中显示的位置
                }else{
                    isSuo = true;
                    mIv2.setImageDrawable(ClassicExerciseActivity.this.getResources().getDrawable(R.mipmap.ic_hp_yd_99));
                    if(window!=null && window.isShowing()){
                        window.dismiss();
                    }
                }
            }
        });
        mIv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        mIv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.showDialogOutRoom(ClassicExerciseActivity.this, new DialogUtils.DialogLyInterface() {
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
