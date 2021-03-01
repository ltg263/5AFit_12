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
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.DeviceData;
import com.jxkj.fit_5a.base.DeviceDrandData;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TimeThreadUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.adapter.FacilityAddAdapter;

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
    @BindView(R.id.video_view)
    VideoView mVideoView;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.iv_d)
    ImageView mIvD;
    String type;
    String id = "";
    private FacilityAddAdapter mFacilityAddAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_facility_add_pp;
    }

    @Override
    protected void initViews() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        type = bundle.getString("id");//设备类型id
        ConstValues_Ly.DEVICE_TYPE_ID = type;
        mTvTitle.setText(bundle.getString("name"));
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        queryDeviceBrandLists();
        initVvUi();


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

    private void queryDeviceBrandLists() {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceBrandLists()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DeviceDrandData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DeviceDrandData> result) {
                        if(isDataInfoSucceed(result)){
                            initRvUi(result.getData().getList());
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
    private void queryDeviceLists(int id) {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceLists(String.valueOf(id),type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DeviceData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DeviceData> result) {
                        if(isDataInfoSucceed(result)){

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


    private void initVvUi() {

        /***
         * 将播放器关联上一个音频或者视频文件
         * videoView.setVideoURI(Uri uri)
         * videoView.setVideoPath(String path)
         * 以上两个方法都可以。
         */
        mVideoView.setVideoPath("http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4");

        /**
         * w为其提供一个控制器，控制其暂停、播放……等功能
         */
        mVideoView.setMediaController(new MediaController(this));

        /**
         * 视频或者音频到结尾时触发的方法
         */
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i("通知", "完成");
            }
        });

        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {

            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.i("通知", "播放中出现错误");
                return false;
            }
        });

    }

    private void initRvUi(List<DeviceDrandData.ListBean> list) {
        if(list.size()==0){
            return;
        }
        id = list.get(0).getId()+"";
        mFacilityAddAdapter = new FacilityAddAdapter(list);
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
                id = list.get(position).getId()+"";
                ConstValues_Ly.BRAND_ID = id;
//                queryDeviceLists(list.get(position).getId());
            }
        });
    }
    @OnClick({R.id.ll_back, R.id.ll_connect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_connect:
//                startActivity(new Intent(this,MainActivity2.class));
//                goConnect();

                if(StringUtil.isBlank(ConstValues_Ly.BRAND_ID)){
                    ToastUtils.showShort("请选择设备品牌");
                    return;
                }
                initPopupWindw();
                break;
        }
    }

    private void goConnect() {
        mIv.setVisibility(View.GONE);
        mTv.setVisibility(View.GONE);
        mIvD.setVisibility(View.VISIBLE);

        PostUser.DeviceFormDTO deviceFormDTO= new PostUser.DeviceFormDTO();
        deviceFormDTO.setDeviceId("0");
        deviceFormDTO.setDeviceNo("");
        deviceFormDTO.setId(id);
        deviceFormDTO.setUserId(SharedUtils.getUserId()+"");
        show();
        RetrofitUtil.getInstance().apiService()
                .userDeviceAdd(deviceFormDTO)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
//                            showDialogUi();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });


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
