package com.jxkj.fit_5a.view.activity.home;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.GiftLogListData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.SignLogData;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.view.adapter.HomeSignHdrwAdapter;
import com.jxkj.fit_5a.view.adapter.HomeSignRcrwAdapter;
import com.jxkj.fit_5a.view.adapter.HomeSignRlAdapter;
import com.jxkj.fit_5a.view.adapter.HomeSignTopAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TaskSignActivity extends BaseActivity {
    @BindView(R.id.tv_jifen_num)
    TextView mTvJifenNum;
    @BindView(R.id.iv_show_type)
    ImageView mIvShowType;
    @BindView(R.id.rv_rl_list)
    RecyclerView mRvRlList;
    @BindView(R.id.rv_top_list)
    RecyclerView mRvTopList;
    @BindView(R.id.tv_go_sign)
    TextView mTvGoSign;
    @BindView(R.id.rv_rcrw_list)
    RecyclerView mRvRcrwList;
    @BindView(R.id.rv_hdrw_list)
    RecyclerView mRvHdrwList;
    @BindView(R.id.ll_rl)
    LinearLayout mLlRl;
    int type = 1;//周的形式

    @Override
    protected int getContentView() {
        return R.layout.activity_task_sign;
    }

    @Override
    protected void initViews() {
        getUserSignLog();

        initRv();

    }
    int year;
    int month;
    private void getUserSignLog() {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        //2020-11-01 01:00:00
        String beginCreateTime = year+"-"+month+"-01 00:00:00";
        String endCreateTime = year+"-"+month+"-"+StringUtil.getCurrentMonthDay()+" 00:00:00";
        RetrofitUtil.getInstance().apiService()
                .getUserSignLog(beginCreateTime,endCreateTime)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<SignLogData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<SignLogData> result) {
                        if(isDataInfoSucceed(result)){
                            initTop(result.getData().getList());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initTop(List<SignLogData.ListBean> listData) {
        List<SignLogData.ListBean> listRl = new ArrayList<>();
        int currentMaxDays = StringUtil.getCurrentMonthDay();//当月天数
        int pos = StringUtil.getPos();//星期几开始
        for(int i=0;i<pos;i++){
            listRl.add(null);
        }
        for(int i=0;i<currentMaxDays;i++){
            SignLogData.ListBean listBean = new SignLogData.ListBean();
            for (int j= 0;j<listData.size();j++){
                String str = (i+1)+"";
                if((i+1)<10){
                    str = "0"+(i+1);
                }
                if((listData.get(j).getSignDate()+"").equals(String.valueOf(year)+String.valueOf(month)+str)){
                    listBean = listData.get(j);
                    listBean.setSig(true);
                }
                listBean.setSj(""+(i+1));
            }

            listRl.add(listBean);
        }
        HomeSignRlAdapter mHomeSignRlAdapter = new HomeSignRlAdapter(listRl);
        mRvRlList.setLayoutManager(new GridLayoutManager(this,7));
        mRvRlList.setHasFixedSize(true);
        mRvRlList.setAdapter(mHomeSignRlAdapter);

        mHomeSignRlAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

        List<String> list = new ArrayList<>();
        for(int i = 0;i<7;i++){
            list.add("");
        }
        HomeSignTopAdapter mHomeSignTopAdapter = new HomeSignTopAdapter(list);
        mRvTopList.setLayoutManager(new GridLayoutManager(this,7));
        mRvTopList.setHasFixedSize(true);
        mRvTopList.setAdapter(mHomeSignTopAdapter);

        mHomeSignTopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });
    }

    private void initRv() {
        List<String> list = new ArrayList<>();
        List<String> listRl = new ArrayList<>();
        for(int i = 0;i<7;i++){
            list.add("");
        }


//        int currentMaxDays = StringUtil.getCurrentMonthDay();//当月天数
//        int pos = StringUtil.getPos();//星期几开始
//        for(int i=0;i<pos;i++){
//            listRl.add("");
//        }
//        for(int i=0;i<currentMaxDays;i++){
//            listRl.add(""+(i+1));
//        }
//        HomeSignRlAdapter mHomeSignRlAdapter = new HomeSignRlAdapter(listRl);
//        mRvRlList.setLayoutManager(new GridLayoutManager(this,7));
//        mRvRlList.setHasFixedSize(true);
//        mRvRlList.setAdapter(mHomeSignRlAdapter);
//
//        mHomeSignRlAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
////                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
//            }
//        });
//
//
//        HomeSignTopAdapter mHomeSignTopAdapter = new HomeSignTopAdapter(list);
//        mRvTopList.setLayoutManager(new GridLayoutManager(this,7));
//        mRvTopList.setHasFixedSize(true);
//        mRvTopList.setAdapter(mHomeSignTopAdapter);
//
//        mHomeSignTopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
////                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
//            }
//        });

        HomeSignRcrwAdapter mHomeSignRcrwAdapter = new HomeSignRcrwAdapter(list);
        mRvRcrwList.setLayoutManager(new LinearLayoutManager(this));
        mRvRcrwList.setHasFixedSize(true);
        mRvRcrwList.setAdapter(mHomeSignRcrwAdapter);

        mHomeSignRcrwAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

        HomeSignHdrwAdapter mHomeSignHdrwAdapter = new HomeSignHdrwAdapter(list);
        mRvHdrwList.setLayoutManager(new LinearLayoutManager(this));
        mRvHdrwList.setHasFixedSize(true);
        mRvHdrwList.setAdapter(mHomeSignHdrwAdapter);

        mHomeSignHdrwAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });
    }


    @OnClick({R.id.iv_back, R.id.iv_show_type, R.id.tv_go_sign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_show_type:
                if(type==1){
                    type = 2;
                    mLlRl.setVisibility(View.VISIBLE);
                    mRvRlList.setVisibility(View.VISIBLE);
                    mRvTopList.setVisibility(View.GONE);
                    mIvShowType.setImageDrawable(this.getResources().getDrawable(R.drawable.icon_sign_jgg1));
                }else{
                    type = 1;
                    mLlRl.setVisibility(View.GONE);
                    mRvRlList.setVisibility(View.GONE);
                    mRvTopList.setVisibility(View.VISIBLE);
                    mIvShowType.setImageDrawable(this.getResources().getDrawable(R.drawable.icon_sign_jgg));
                }
                break;
            case R.id.tv_go_sign:
                break;
        }
    }
}
