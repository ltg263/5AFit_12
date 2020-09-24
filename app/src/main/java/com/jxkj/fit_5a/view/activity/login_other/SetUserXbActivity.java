package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;

import java.lang.reflect.Field;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class SetUserXbActivity extends BaseActivity {
    @BindView(R.id.iv_select_nan)
    ImageView mIvSelectNan;
    @BindView(R.id.iv_select_nv)
    ImageView mIvSelectNv;
    @BindView(R.id.date_picker)
    DatePicker mDatePicker;

    int sbType = 1;//1男

    @Override
    protected int getContentView() {
        isShowTitle();
        return R.layout.activity_set_user_xb;
    }

    @Override
    protected void initViews() {
        setDatePickerDividerColor(mDatePicker);
        Calendar calendar = Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int monthOfYear=calendar.get(Calendar.MONTH);
        int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
        mDatePicker.init(year, monthOfYear, dayOfMonth, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                Toast.makeText(SetUserXbActivity.this,"您选择的日期是："+year+"年"+(month+1)+"月"+day+"日!",Toast
                        .LENGTH_SHORT).show();
            }
        });
    }


    @OnClick({R.id.iv_select_nan, R.id.iv_select_nv, R.id.tv_go_xyb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_select_nan:
                if(sbType == 2){
                    sbType = 1;
                    mIvSelectNan.setImageDrawable(getResources().getDrawable(R.drawable.icon_select_yd_yse));
                    mIvSelectNv.setImageDrawable(getResources().getDrawable(R.drawable.icon_select_yd_no));
                }
                break;
            case R.id.iv_select_nv:
                if(sbType == 1){
                    sbType = 2;
                    mIvSelectNan.setImageDrawable(getResources().getDrawable(R.drawable.icon_select_yd_no));
                    mIvSelectNv.setImageDrawable(getResources().getDrawable(R.drawable.icon_select_yd_yse));
                }
                break;
            case R.id.tv_go_xyb:
                startActivity(new Intent(SetUserXbActivity.this,SetUserSgActivity.class));
                break;
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
                        pf.set(picker, new ColorDrawable(Color.parseColor("#E6E6E6")));//设置分割线颜色
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
