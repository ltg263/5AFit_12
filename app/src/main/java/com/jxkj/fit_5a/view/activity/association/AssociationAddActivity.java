package com.jxkj.fit_5a.view.activity.association;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.MatisseUtils;
import com.jxkj.fit_5a.conpoment.utils.PictureUtil;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PickerViewUtils;
import com.jxkj.fit_5a.entity.TopicAllBean;
import com.jxkj.fit_5a.view.adapter.SpPhotoAdapter;
import com.jxkj.fit_5a.view.map.LocationSelectActivity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AssociationAddActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.tv_gk)
    TextView mTvGk;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.rv_list_zp)
    RecyclerView mRvListZp;
    @BindView(R.id.tv_lefttext)
    TextView mTvLefttext;
    @BindView(R.id.iv_rightimg_two)
    ImageView mIvRightimgTwo;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.tv_topics)
    TextView mTvTopics;
    @BindView(R.id.tv_position)
    TextView mTvPosition;
    private SpPhotoAdapter mSpPhotoAdapter;
    int shareType = 1;
    @Override
    protected int getContentView() {
        return R.layout.activity_community_add;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("社区动态");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mTvRighttext.setText("发布");
        mTvRighttext.setTextColor(getResources().getColor(R.color.color_text_theme));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.icon_fabu));
        initRvXq();
    }

    private void initRvXq() {
        mRvListZp.setLayoutManager(new GridLayoutManager(this, 4));
        mRvListZp.setHasFixedSize(true);
        mSpPhotoAdapter = new SpPhotoAdapter(null);
        mRvListZp.setAdapter(mSpPhotoAdapter);
        List<LocalMedia> list = new ArrayList<>();
        LocalMedia mLocalMedia = new LocalMedia();
        mLocalMedia.setPath("-1");
        list.add(mLocalMedia);
        mSpPhotoAdapter.setNewData(list);
        mSpPhotoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mSpPhotoAdapter.getData().get(position).getPath().equals("-1")) {
                    MatisseUtils.gotoSelectPhoto(AssociationAddActivity.this, 10 - mSpPhotoAdapter.getData().size(), false);
                }
            }
        });

        mSpPhotoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mSpPhotoAdapter.remove(position);
                if (!mSpPhotoAdapter.getData().get(mSpPhotoAdapter.getData().size() - 1).getPath().equals("-1")) {
                    LocalMedia mLocalMedia = new LocalMedia();
                    mLocalMedia.setPath("-1");
                    listUrls.remove(position);
                    mSpPhotoAdapter.addData(mLocalMedia);
                }
            }
        });
    }
    String position = null;
    String location = null;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    setIMaaa(selectList);
                    break;
                case 2:
                    double latitude=data.getDoubleExtra("latitude",0.0);
                    double longitude=data.getDoubleExtra("longitude",0.0);
                    location = "["+longitude+","+latitude+"]";
                    position=data.getStringExtra("address");
                    mTvPosition.setText(position);
//                    mTvPosition.setText("详细地址："+address+"\n经度："+longitude+"\n纬度："+latitude);
//                    Success/storage/emulated/0/DCIM/Camera/20210111_100058.jpg
                    break;
            }
        }
    }
    List <String> listUrls= new ArrayList<>();

    private void setIMaaa(List<LocalMedia> selectList) {
        HttpRequestUtils.postOSSFile(2,new HttpRequestUtils.OSSClientInterface() {
            @Override
            public void succeed(double pos) {
                if(pos==0){
                    ToastUtils.showShort("获取oss信息错误");
                    return;
                }
                if(selectList.size()>0){
                    postImg(selectList,0);
                }
            }
        });
    }

    private void postImg(List<LocalMedia> selectList, int i) {
        String path = PictureUtil.compressBmpFileToTargetSize(new File(selectList.get(i).getPath()),1024*1024).getPath();
//        String path = selectList.get(i).getPath();
        String fileName = StringUtil.stringToMD5(path)+".jpg";
        i++;
        int finalI = i;
        HttpRequestUtils.initOSSClient(AssociationAddActivity.this, fileName,path, new HttpRequestUtils.OSSClientInterface() {
            @Override
            public void succeed(double pos) {
                if(pos==101){
                    String urlpath= SharedUtils.singleton().get(ConstValues.host,"")
                            +"/"+SharedUtils.singleton().get(ConstValues.dir,"")+"/"+fileName;
                    listUrls.add(urlpath);
                    mSpPhotoAdapter.addData(mSpPhotoAdapter.getData().size() - 1, selectList.get(finalI-1));
                    if (mSpPhotoAdapter.getData().size() > 6 && mSpPhotoAdapter.getData().contains("-1")) {
                        mSpPhotoAdapter.remove(mSpPhotoAdapter.getData().size() - 1);
                    }
                    mSpPhotoAdapter.notifyDataSetChanged();
                    if(selectList.size()>finalI){
                        postImg(selectList, finalI);
                    }else{
                        Log.w("listUrls","listUrls:"+listUrls.toString());
                    }
                }
            }
        });
    }


    private List<String> mFeedTypeList = new ArrayList<>();

    @OnClick({R.id.ll_back, R.id.tv_righttext, R.id.iv_rightimg, R.id.tv_gk,R.id.tv_topics,R.id.tv_position})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                break;
            case R.id.iv_rightimg:
            case R.id.tv_righttext:
                postPublishMoment();
                break;
            case R.id.tv_topics:
                startActivityForResult(new Intent(AssociationAddActivity.this, TopicAllActivity.class),2);
                break;
            case R.id.tv_position:
                startActivityForResult(new Intent(AssociationAddActivity.this, LocationSelectActivity.class),2);
                break;
            case R.id.tv_gk:
                mFeedTypeList.clear();
                mFeedTypeList.add("公开可见");
                mFeedTypeList.add("私密不可见");
                mFeedTypeList.add("仅圈子可见");
                mFeedTypeList.add("关注的人可见");
                PickerViewUtils.selectorCustom(this, mFeedTypeList, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mTvGk.setText(mFeedTypeList.get(pos));
                        if(pos ==0){
                            shareType = 1;
                        }else if(pos ==1){
                            shareType = 3;
                        }else if(pos ==2){
                            shareType = 2;
                        }else if(pos ==3){
                            shareType = 2;
                        }
                    }
                });
                break;
        }
    }

    private void getTopicAll(){
        RetrofitUtil.getInstance().apiService()
                .getTopicAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<TopicAllBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<TopicAllBean> result) {
                        if (isDataInfoSucceed(result)) {
                            result.getData().getChildren();
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
    private void postPublishMoment() {
        String content = mEtContent.getText().toString();
        mTvPosition.getText().toString();
        if (StringUtil.isBlank(content)) {
            ToastUtils.showShort("内容不能为空");
        }
        String imgs = listUrls.toString().replace("[","").replace("]","");
        show();
        RetrofitUtil.getInstance().apiService()
                .postPublishMoment(content,"2",shareType+"",
                        imgs,
                        position,location,null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            DialogUtils.showDialogCgCircle(AssociationAddActivity.this, "发布成功", 1,
                                    new DialogUtils.DialogLyInterface() {
                                @Override
                                public void btnConfirm() {
                                    AssociationAddActivity.this.finish();
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
