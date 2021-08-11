package com.jxkj.fit_5a.view.activity.mine;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.CustomPopWindow;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.view.activity.association.AssociationAddActivity;
import com.jxkj.fit_5a.view.adapter.MineJinDouAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineJinDouActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.tv_jifen_num)
    TextView mTvJifenNum;
    @BindView(R.id.tv_logPay)
    TextView tv_logPay;
    @BindView(R.id.tv_time)
    TextView tv_time;
    private MineJinDouAdapter mMineJinDouAdapter;
    private CustomPopWindow distancePopWindow;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_jindou;
    }

    @Override
    protected void initViews() {
        initRv();
    }
    private void initRv() {
        mTvJifenNum.setText(SharedUtils.singleton().get(ConstValues.MY_BALANCE,""));
        tv_time.setText(StringUtil.getTimeToYMD(System.currentTimeMillis(),"yyyy.MM"));
        mMineJinDouAdapter = new MineJinDouAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mMineJinDouAdapter);

        mMineJinDouAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_right_text,R.id.tv_cz,R.id.tv_logPay,R.id.iv_1,R.id.iv_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right_text:
                break;
            case R.id.iv_1:
                tv_time.setText(getLastMonth(-1));
                break;
            case R.id.iv_2:
                tv_time.setText(getLastMonth(1));
                break;
            case R.id.tv_logPay:
                if(distancePopWindow!=null){
                    distancePopWindow.showAsDropDown(tv_logPay, -100, 0);
                    return;
                }
                initPopupWindow();
                break;
            case R.id.tv_cz:
                startActivity(new Intent(this,MineJinDouCzActivity.class));
                break;
        }
    }
    public String getLastMonth(int x) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM");
        Date date = null;
        try {
            date = format.parse(tv_time.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + x); // 设置为上一个月
        return format.format(calendar.getTime());
    }

    private void initPopupWindow() {
        View view = getLayoutInflater().inflate(R.layout.popup_window_cqxc, null, false);
        TextView tv_tp= view.findViewById(R.id.tv_tp);
        TextView tv_sp= view.findViewById(R.id.tv_sp);
        tv_tp.setText("支出记录");
        tv_sp.setText("收入记录");
        tv_tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_logPay.setText("支出记录");
                distancePopWindow.dissmiss();

            }
        });
        tv_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_logPay.setText("收入记录");
                distancePopWindow.dissmiss();
            }
        });
        //创建并显示popWindow
        distancePopWindow = new CustomPopWindow.PopupWindowBuilder(MineJinDouActivity.this)
                .setView(view)
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
//                        rbDistance.setChecked(false);
                    }
                })
                .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                .size(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()//创建PopupWindow
                .showAsDropDown(tv_logPay, -100, 0);//显示PopupWindow
    }
}
