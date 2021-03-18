package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;

import java.lang.reflect.Field;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetUserXbActivity extends BaseActivity {
    @BindView(R.id.iv_select_nan)
    ImageView mIvSelectNan;
    @BindView(R.id.iv_select_nv)
    ImageView mIvSelectNv;
    @BindView(R.id.date_picker)
    DatePicker mDatePicker;


    int sbType = 1;//1男
    String csrq = "";
    @BindView(R.id.rl_nan)
    RelativeLayout mRlNan;
    @BindView(R.id.rl_nv)
    RelativeLayout mRlNv;
    public static int type =0;

    @Override
    protected int getContentView() {
        isShowTitle();
        return R.layout.activity_set_user_xb;
    }

    @Override
    protected void initViews() {
        MainApplication.getContext().addActivity(this);
        setDatePickerDividerColor(mDatePicker);
        type = getIntent().getIntExtra("type",0);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        mDatePicker.init(year, monthOfYear, dayOfMonth, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
//                Toast.makeText(SetUserXbActivity.this,"您选择的日期是："+year+"年"+(month+1)+"月"+day+"日!",Toast
//                        .LENGTH_SHORT).show();

                csrq = year + "-" + StringUtil.getDayMonth7(month + 1) + "-" + StringUtil.getDayMonth7(day) + " 00:00:00";
            }
        });
    }


    @OnClick({R.id.rl_nan, R.id.rl_nv, R.id.tv_go_xyb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_nan:
                if (sbType == 2) {
                    sbType = 1;
                    mIvSelectNan.setImageDrawable(getResources().getDrawable(R.drawable.icon_select_yd_yse));
                    mIvSelectNv.setImageDrawable(getResources().getDrawable(R.drawable.icon_select_yd_no));
                    mRlNv.setBackgroundResource(R.drawable.bj_shape_line_66_6);
                    mRlNan.setBackgroundResource(R.drawable.bj_shape_line_theme_6);
                }
                break;
            case R.id.rl_nv:
                if (sbType == 1) {
                    sbType = 2;
                    mIvSelectNan.setImageDrawable(getResources().getDrawable(R.drawable.icon_select_yd_no));
                    mIvSelectNv.setImageDrawable(getResources().getDrawable(R.drawable.icon_select_yd_yse));
                    mRlNan.setBackgroundResource(R.drawable.bj_shape_line_66_6);
                    mRlNv.setBackgroundResource(R.drawable.bj_shape_line_theme_6);
                }
                break;
            case R.id.tv_go_xyb:
                if (StringUtil.isBlank(csrq)) {
                    ToastUtils.showShort("请确认出生日期");
                    return;
                }
                Intent intent = new Intent(SetUserXbActivity.this, SetUserSgActivity.class);
                intent.putExtra("sbType", sbType);
                intent.putExtra("csrq", csrq);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(type!=0){
            super.onBackPressed();
        }
    }

    /**
     * 设置时间选择器的分割线颜色
     *
     * @param datePicker
     */
    private void setDatePickerDividerColor(DatePicker datePicker) {
        // 获取 mSpinners
        LinearLayout llFirst = (LinearLayout) datePicker.getChildAt(0);
        // 获取 NumberPicker
        LinearLayout mSpinners = (LinearLayout) llFirst.getChildAt(0);
        for (int i = 0; i < mSpinners.getChildCount(); i++) {
            NumberPicker picker = (NumberPicker) mSpinners.getChildAt(i);

            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    try {
                        pf.set(picker, new ColorDrawable(Color.parseColor("#EEEEEE")));//设置分割线颜色
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }
}
