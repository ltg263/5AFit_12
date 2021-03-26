package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTopicUtils_Map;
import com.jxkj.fit_5a.conpoment.view.RobotView;
import com.jxkj.fit_5a.entity.MapDetailsBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MapExerciseActivity extends Activity {
    @BindView(R.id.iv_1)
    ImageView mIv1;
    @BindView(R.id.iv_2)
    ImageView mIv2;
    @BindView(R.id.iv_3)
    ImageView mIv3;
    @BindView(R.id.iv_4)
    ImageView mIv4;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.iv_img)
    RobotView iv_img;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.tv_distance)
    TextView mTvDistance;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    boolean isSuo = false;
    String mapId;
    String boxId;


    public static void intentStartActivity(Context mContext, String mapId) {
        Intent intent = new Intent(mContext, MapExerciseActivity.class);
        intent.putExtra("mapId", mapId);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape_map_exercise);
        ButterKnife.bind(this);
        initViews();
    }


    PopupWindowTopicUtils_Map window;

    private void initViews() {
        mapId = getIntent().getStringExtra("mapId");
        getMapDetails();

        if(window==null){
            window = new PopupWindowTopicUtils_Map(MapExerciseActivity.this, type -> {
                if(type ==0){
                }else if(type==1){
                }else if(type==2){
                }

            });
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
//        DialogUtils.showDialogClass(ClassicExerciseActivity.this, 1, new DialogUtils.DialogLyInterface() {
//            @Override
//            public void btnConfirm() {
//1100/5
//            }
//        });

    }

    @OnClick({R.id.iv_1, R.id.iv_2, R.id.iv_3, R.id.iv_4, R.id.iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_1:
//                if (mIv3.getVisibility() == View.VISIBLE) {
//                    mIv3.setVisibility(View.GONE);
//                    mIv4.setVisibility(View.GONE);
//                } else {
//                    mIv3.setVisibility(View.VISIBLE);
//                    mIv4.setVisibility(View.VISIBLE);
//                }
                iv_img.setAngle(1);

                break;
            case R.id.iv_2:
                if (isSuo) {
                    isSuo = false;
                    mIv2.setImageDrawable(MapExerciseActivity.this.getResources().getDrawable(R.mipmap.ic_hp_yd_9));
                    window.showAtLocation(mLl, Gravity.BOTTOM, 0, 0); // 设置layout在PopupWindow中显示的位置
                    mTvTime.setVisibility(View.GONE);
                } else {
                    isSuo = true;
                    mTvTime.setVisibility(View.VISIBLE);
                    mIv2.setImageDrawable(MapExerciseActivity.this.getResources().getDrawable(R.mipmap.ic_hp_yd_99));
                    if (window != null && window.isShowing()) {
                        window.dismiss();
                    }
                }
                break;
            case R.id.iv_3:
                iv_img.setRed(0);
                break;
            case R.id.iv_4:
                iv_img.setRed(1);
//                outRoom();
                break;
            case R.id.iv:
                if (StringUtil.isNotBlank(boxId)) {

                }
                break;
        }
    }


    private void getMapDetails() {
        RetrofitUtil.getInstance().apiService()
                .getMapDetails(mapId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MapDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MapDetailsBean> result) {
                        if (result.getCode() == 0 && result.getData() != null) {
                            String distance = result.getData().getDistance();
                            mTvDistance.setText(distance + "m");
                            if (Double.valueOf(result.getData().getDistance()) > 1000) {
                                mTvDistance.setText(Double.valueOf(distance) / 1000d + "km");
                            }
                            GlideImgLoader.loadImage(MapExerciseActivity.this,result.getData().getImgUrl(),iv_img);
                            if(result.getData().getInfo()!=null && result.getData().getInfo().size()>0){
                                iv_img.setData(result.getData().getInfo());
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        outRoom();
        return super.onKeyDown(keyCode, event);
    }

    private void outRoom() {
        DialogUtils.showDialogOutRoom(MapExerciseActivity.this, new DialogUtils.DialogLyInterface() {
            @Override
            public void btnConfirm() {
                finish();
            }
        });
    }

}
