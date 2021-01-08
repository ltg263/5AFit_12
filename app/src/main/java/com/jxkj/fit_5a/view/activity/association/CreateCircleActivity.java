package com.jxkj.fit_5a.view.activity.association;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.MatisseUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PickerViewUtils;
import com.jxkj.fit_5a.entity.TaskCircleQueryBean;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateCircleActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_lefttext)
    TextView mTvLefttext;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.iv_rightimg_two)
    ImageView mIvRightimgTwo;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.iv_bgImg)
    ImageView mIvBgImg;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.et1)
    EditText mEt1;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.et2)
    EditText mEt2;
    @BindView(R.id.tv3)
    TextView mTv3;
    @BindView(R.id.tv_sb_type)
    TextView mTvSbType;
    @BindView(R.id.tv_sb_name)
    TextView mTvSbName;
    @BindView(R.id.tv4)
    TextView mTv4;
    @BindView(R.id.tv_mblx)
    TextView mTvMblx;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.tv_zqsc)
    TextView mTvZqsc;
    @BindView(R.id.tv5)
    TextView mTv5;
    @BindView(R.id.tv_v)
    TextView mTvV;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.tv_go_find)
    TextView mTvGoFind;
    int deviceType = -1;
    int circleTargetId = 0;
    private List<DeviceTypeData.ListBean> dataSbType;

    private List<String> zqscLists = new ArrayList<>();
    private String rewardJson;//任务说明

    @Override
    protected int getContentView() {
        return R.layout.activity_create_circle;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("创建圈子");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        queryDeviceTypeLists();
    }


    @OnClick({R.id.ll_back, R.id.tv_go_find,R.id.tv_sb_name,R.id.iv_bgImg,R.id.tv_mblx,R.id.tv_zqsc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_go_find:
                getCircleCreate();
                break;
            case R.id.tv_sb_name:
                if(dataSbType==null || dataSbType.size()==0){
                    return;
                }
                List <String> list = new ArrayList<>();
                for(int i =0;i<dataSbType.size();i++){
                    list.add(dataSbType.get(i).getName());
                }
                PickerViewUtils.selectorCustom(this, list, "设备类型", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mTvSbName.setText(dataSbType.get(pos).getName());
                        deviceType = dataSbType.get(pos).getId();
                    }
                });
                break;
            case R.id.tv_zqsc:
                if(zqscLists==null || zqscLists.size()==0){
                    return;
                }
                PickerViewUtils.selectorCustom(this, zqscLists, "选择周期",mTvZqsc);
                break;
            case R.id.iv_bgImg:
                MatisseUtils.gotoSelectPhoto(this, 1, true);
                break;
            case R.id.tv_mblx:
                getTaskCircleQuery();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList.size() > 0) {
                        postImg(selectList.get(0).getCompressPath());
                    }
                    break;
            }
        }
    }

    String avatar = null;
    private void postImg(final String listPath) {
        show();
        HttpRequestUtils.uploadFiles(listPath, new HttpRequestUtils.UploadFileInterface() {
            @Override
            public void succeed(String path) {
                dismiss();
                avatar = path;
                GlideImgLoader.loadImageViewRadius(CreateCircleActivity.this,avatar,10,mIvBgImg);
            }

            @Override
            public void failure() {
                dismiss();
            }
        });
    }

    private void getTaskCircleQuery() {
        RetrofitUtil.getInstance().apiService()
                .getTaskCircleQuery(deviceType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<TaskCircleQueryBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<TaskCircleQueryBean> result) {
                        if (isDataInfoSucceed(result)) {
                            List<TaskCircleQueryBean.ListBean> data = result.getData().getList();

                            List <String> listMblx = new ArrayList<>();
                            for(int i =0;i<data.size();i++){
                                listMblx.add(data.get(i).getDeviceTypeStr()+"");
                            }
                            PickerViewUtils.selectorCustom(CreateCircleActivity.this, listMblx, "目标类型",
                                    new PickerViewUtils.ConditionInterfacd() {
                                @Override
                                public void setIndex(int pos) {
                                    circleTargetId = data.get(pos).getId();
                                    mTvMblx.setText(data.get(pos).getDeviceTypeStr());
                                    zqscLists.clear();
                                    rewardJson = data.get(pos).getReward();
                                    for(int i=data.get(pos).getMinCycle();i<data.get(pos).getMaxCycle()+1;i++){
                                        zqscLists.add(i+"");
                                    }
                                    Log.w("zqscLists:","circleTargetId"+circleTargetId);
                                }
                            });
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
    private void queryDeviceTypeLists() {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceTypeLists()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DeviceTypeData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DeviceTypeData> result) {
                        if (isDataInfoSucceed(result)) {
                            dataSbType = result.getData().getList();
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

    private void getCircleCreate() {
        if(StringUtil.isBlank(mEt1.getText().toString())
                || StringUtil.isBlank(mEt2.getText().toString())
                || StringUtil.isBlank(mTvZqsc.getText().toString())){
            ToastUtils.showShort("信息不完整");
            return;
        }
        show();
        PostUser.CreateCircle postUser = new PostUser.CreateCircle();
        avatar = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1091405991,859863778&fm=26&gp=0.jpg";
        postUser.setBgImg(avatar);
        postUser.setDeviceType(deviceType);
        postUser.setName(mEt1.getText().toString());
        postUser.setExplain(mEt2.getText().toString());
        postUser.setImgUrl("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3387716082,1768697998&fm=26&gp=0.jpg");
        postUser.setInterestId(0);
        PostUser.CreateCircle.TaskCircleFormDTOBean dtoBean = new PostUser.CreateCircle.TaskCircleFormDTOBean();
        dtoBean.setCircleTargetId(circleTargetId);
        dtoBean.setCycle(Integer.valueOf(mTvZqsc.getText().toString()));//mTvZqsc.getText().toString()
        try {
            JSONObject object = new JSONObject(rewardJson);
            dtoBean.setImgUrl(object.getString("imgUrl"));
            dtoBean.setExplain(object.getString("explain"));
        } catch (JSONException e) {
            dismiss();
            e.printStackTrace();
        }
        postUser.setTaskCircleFormDTO(dtoBean);

        RetrofitUtil.getInstance().apiService()
                .getCircleCreate(postUser)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            DialogUtils.showDialogCgCircle(CreateCircleActivity.this,
                                    "创建圈子权限", 1, new DialogUtils.DialogLyInterface() {
                                @Override
                                public void btnConfirm() {
                                    CreateCircleActivity.this.finish();
                                }
                            });
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
}
