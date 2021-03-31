package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.DeviceData;
import com.jxkj.fit_5a.base.DeviceDrandData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TimeThreadUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.adapter.FacilityAddAdapter;
import com.jxkj.fit_5a.view.adapter.FacilitySbAddAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;



public class FacilityAddPpActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_all_list)
    RecyclerView mRvAllList;
    @BindView(R.id.rv_sbxh_list)
    RecyclerView mRvSbxhList;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.tv_show)
    TextView tv_show;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.iv_d)
    ImageView mIvD;
    private FacilityAddAdapter mFacilityAddAdapter;
    private FacilitySbAddAdapter mFacilitySbAddAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_facility_add_pp;
    }

    @Override
    protected void initViews() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        ConstValues_Ly.DEVICE_TYPE_ID_URL =  bundle.getString("id");//设备类型id
        mTvTitle.setText(bundle.getString("name"));
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        queryDeviceBrandLists();
        initRvUiXh();
    }
    PopupWindowLanYan window;
    private void initPopupWindw() {
        if(window==null){
            window = new PopupWindowLanYan(this, new PopupWindowLanYan.GiveDialogInterface() {
                @Override
                public void btnConfirm(String str) {
                    if(str.equals("连接设备中")){
                        show();
                        return;
                    }
                    dismiss();
                    showDialogUi(str);
                }
            });
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        window.showAtLocation(mTv, Gravity.BOTTOM, 0, 0); // 设置layout在PopupWindow中显示的位置
    }
    List<DeviceDrandData.ListBean> list;
    private void queryDeviceBrandLists() {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceBrandLists("1","1000")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DeviceDrandData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DeviceDrandData> result) {
                        if(isDataInfoSucceed(result)){
                            list = result.getData().getList();
                            initRvUi();
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
    private void queryDeviceModelLists(int id) {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceModelLists(String.valueOf(id),ConstValues_Ly.DEVICE_TYPE_ID_URL)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DeviceData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DeviceData> result) {
                        if(isDataInfoSucceed(result)){
                            if(result.getData()!=null && result.getData().getList()!=null && result.getData().getList().size()>0){
                                result.getData().getList().get(0).setSelect(true);
                                ConstValues_Ly.DEVICE_Model_ID_URL = result.getData().getList().get(0).getId()+"";
                                mFacilitySbAddAdapter.setNewData(result.getData().getList());
                            }
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

    private void initRvUiXh() {
        mFacilitySbAddAdapter = new FacilitySbAddAdapter(null);
        mRvSbxhList.setLayoutManager(new LinearLayoutManager(this));
        mRvSbxhList.setHasFixedSize(true);
        mRvSbxhList.setAdapter(mFacilitySbAddAdapter);

        mFacilitySbAddAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<DeviceData.ListBean> data = mFacilitySbAddAdapter.getData();
                for(int i = 0; i< data.size(); i++){
                    data.get(i).setSelect(false);
                }
                data.get(position).setSelect(true);
                ConstValues_Ly.DEVICE_Model_ID_URL = data.get(position).getId()+"";
                mFacilitySbAddAdapter.notifyDataSetChanged();
            }
        });
    }


    private void initRvUi() {
        if(list==null || list.size()==0){
            return;
        }

        queryDeviceModelLists(list.get(0).getId());
        tv_name.setText("("+list.get(0).getName()+")");
        int length = list.size()<9?list.size():9;
        mFacilityAddAdapter = new FacilityAddAdapter(list.subList(0,length));
        mRvAllList.setLayoutManager(new GridLayoutManager(this, 3));
        mRvAllList.setHasFixedSize(true);
        mRvAllList.setAdapter(mFacilityAddAdapter);

        mFacilityAddAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for(int i= 0;i<list.size();i++){
                    list.get(i).setSelect(false);
                }
                list.get(position).setSelect(true);
                mFacilityAddAdapter.notifyDataSetChanged();
                tv_name.setText("("+list.get(position).getName()+")");
                ConstValues_Ly.BRAND_ID = list.get(position).getId()+"";
                queryDeviceModelLists(list.get(position).getId());
            }
        });
    }
    @OnClick({R.id.ll_back, R.id.ll_connect,R.id.tv_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_show:
                int length = list.size()<9?list.size():9;
                if("收起全部".equals(tv_show.getText().toString())){
                    tv_show.setText("展示全部");
                    mFacilityAddAdapter.setNewData(list.subList(0,length));
                }else{
                    mFacilityAddAdapter.setNewData(list);
                    tv_show.setText("收起全部");
                }
                break;
            case R.id.ll_connect:
                if(StringUtil.isBlank(ConstValues_Ly.BRAND_ID)){
                    ToastUtils.showShort("请选择设备品牌");
                    return;
                }
                initPopupWindw();
                break;
        }
    }

    private void showDialogUi(String str) {
        mIv.setVisibility(View.VISIBLE);
        mTv.setVisibility(View.VISIBLE);
        mIvD.setVisibility(View.GONE);
        if(str.equals("连接成功")){
            TimeThreadUtils.sendDataA2();
        }
        DialogUtils.showDialogLyState(FacilityAddPpActivity.this, PopupWindowLanYan.BleName, str, new DialogUtils.DialogLyInterface() {
            @Override
            public void btnConfirm() {
                if(str.equals("连接成功")){
                    dismiss();
                    startActivity(new Intent(FacilityAddPpActivity.this, MainActivity.class));
                }
            }
        });
    }
}
