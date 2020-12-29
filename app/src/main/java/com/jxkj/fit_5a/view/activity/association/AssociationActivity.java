package com.jxkj.fit_5a.view.activity.association;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.OrderInfoData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.view.DialogCommentPackage;
import com.jxkj.fit_5a.entity.CommentMomentBean;
import com.jxkj.fit_5a.entity.MomentDetailsBean;
import com.jxkj.fit_5a.view.activity.mine.UserFsActivity;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;
import com.jxkj.fit_5a.view.activity.mine.UserScActivity;
import com.jxkj.fit_5a.view.adapter.AssociationListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AssociationActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    int type;
    String circleId = "0";
    private AssociationListAdapter mAssociationListAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_association;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("社 区");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mTvRighttext.setText("动态");
        mTvRighttext.setTextColor(getResources().getColor(R.color.color_666666));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.ic_bianji));
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        type = getIntent().getIntExtra("type",0);
        if(type==2){
            circleId = getIntent().getStringExtra("circleId");
        }
        initRv();
    }

    private void initRv() {
        mAssociationListAdapter = new AssociationListAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mAssociationListAdapter);
        mAssociationListAdapter.setCircleId(circleId);
        mAssociationListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MomentDetailsBean data = mAssociationListAdapter.getData().get(position);
                switch (view.getId()){
                    case R.id.iv_head_img:
                        UserHomeActivity.startActivity(AssociationActivity.this,data.getPublisherId()+"");
                        break;
                    case R.id.ll_xihuan:
                        if(data.isIsLike()){
                            HttpRequestUtils.postLikeCancel1(circleId,data.getMomentId()+"", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                                @Override
                                public void succeed(String path) {
                                    dismiss();
                                    if(path.equals("0")){
                                        data.setIsLike(false);
                                        data.setLikeCount(data.getLikeCount()-1);
                                        mAssociationListAdapter.notifyDataSetChanged();
                                    }
                                }
                            });
                        }else{
                            HttpRequestUtils.postLike1(circleId,data.getMomentId()+"", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                                @Override
                                public void succeed(String path) {
                                    dismiss();
                                    if(path.equals("0")) {
                                        data.setIsLike(true);
                                        data.setLikeCount(data.getLikeCount() + 1);
                                        mAssociationListAdapter.notifyDataSetChanged();
                                    }
                                }
                            });
                        }
                        break;
                    case R.id.tv_fenxiang:

                        break;
                    case R.id.ll_shoucang:
                        show();
                        if(data.isIsFavorite()){
                            HttpRequestUtils.postFavoritCancel(circleId, data.getMomentId() + "", new HttpRequestUtils.LoginInterface() {
                                        @Override
                                        public void succeed(String path) {
                                            dismiss();
                                            if(path.equals("0")){
                                                data.setIsFavorite(false);
                                                data.setFavoriteCount(data.getFavoriteCount()-1);
                                                mAssociationListAdapter.notifyDataSetChanged();
                                            }
                                        }
                                    });
                        }else {
                            HttpRequestUtils.postFavorit(circleId, data.getMomentId() + "",
                                    data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                                        @Override
                                        public void succeed(String path) {
                                            dismiss();
                                            if(path.equals("0")) {
                                                data.setIsFavorite(true);
                                                data.setFavoriteCount(data.getFavoriteCount() + 1);
                                                mAssociationListAdapter.notifyDataSetChanged();
                                            }
                                        }
                                    });
                        }
                        break;
                    case R.id.tv_wgz:
                        show();
                        HttpRequestUtils.postfollow(data.getUser().getUserId() + "", new HttpRequestUtils.LoginInterface() {
                            @Override
                            public void succeed(String path) {
                                dismiss();
                                if(path.equals("0")){
                                    data.getUser().setRelation(1);
                                    mAssociationListAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                        break;
                    case R.id.tv_ygz:
                        show();
                        HttpRequestUtils.postfollowCancel(data.getUser().getUserId() + "", new HttpRequestUtils.LoginInterface() {
                            @Override
                            public void succeed(String path) {
                                dismiss();
                                if(path.equals("1")){
                                    data.getUser().setRelation(0);
                                    mAssociationListAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                        break;
                    case R.id.tv_liuyan:
                    case R.id.rl_all_comment:
                        ShowCommentPackageDialog(data);
                        break;
                }
            }
        });
        if(type==1){
            getMomentDetails();
        }else if(type==2){
            getMomentDetailsCircle();
        }
    }
    private void ShowCommentPackageDialog(MomentDetailsBean data) {
        DialogCommentPackage choicePackageDialog = new DialogCommentPackage(this,circleId);
        HttpRequestUtils.getCommentMoment1(circleId,data.getMomentId() + "", data.getPublisherId() + "",1,100,
                new HttpRequestUtils.ResultInterface() {
            @Override
            public void succeed(ResultList<CommentMomentBean> result) {
                isDataInfoSucceed(result);
                choicePackageDialog.setNewData(result.getData(),data.getCommentCount()+"");
            }
        });
        choicePackageDialog.setOnCommentPackageDialogListener(new DialogCommentPackage.OnCommentPackageDialogListener() {
            @Override
            public void addListener(String context, String commentId) {
                show();
                HttpRequestUtils.postCommentMoment1(circleId,context, data.getMomentId()+"", data.getPublisherId()+"",
                        commentId, new HttpRequestUtils.LoginInterface() {
                    @Override
                    public void succeed(String path) {
                        dismiss();
                    }
                });
            }

            @Override
            public void buyListener(String skuId, int num) {

            }

        });
        choicePackageDialog.showDialog();
    }
    @OnClick({R.id.ll_back, R.id.tv_righttext, R.id.iv_rightimg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_righttext:
            case R.id.iv_rightimg:
                startActivity(new Intent(this, AssociationAddActivity.class));
                break;
        }
    }
    List<MomentDetailsBean> mMomentDetailsBeans = new ArrayList<>();
    private void getMomentDetails(){
        RetrofitUtil.getInstance().apiService()
                .getMomentDetails(getIntent().getStringExtra("momentId")
                        ,getIntent().getStringExtra("publisherId"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MomentDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result<MomentDetailsBean> result) {
                        if(isDataInfoSucceed(result)){
                            mMomentDetailsBeans.add(result.getData());
                            mAssociationListAdapter.setNewData(mMomentDetailsBeans);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }

    private void getMomentDetailsCircle(){
        RetrofitUtil.getInstance().apiService()
                .getMomentDetailsCircle(getIntent().getStringExtra("circleId"),getIntent().getStringExtra("momentId")
                        ,getIntent().getStringExtra("publisherId"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MomentDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result<MomentDetailsBean> result) {
                        if(isDataInfoSucceed(result)){
                            mMomentDetailsBeans.add(result.getData());
                            mAssociationListAdapter.setNewData(mMomentDetailsBeans);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }

    /**
     * 获取动态信息 社群
     */
    public static void startActivity(Context mContext, String publisherId, String momentId) {
        Intent intent = new Intent(mContext, AssociationActivity.class);
        intent.putExtra("publisherId", publisherId);
        intent.putExtra("momentId", momentId);
        intent.putExtra("type", 1);
        mContext.startActivity(intent);
    }

    /**
     * 获取动态信息 圈子
     */
    public static void startActivity(Context mContext, String circleId,String publisherId, String momentId) {
        Intent intent = new Intent(mContext, AssociationActivity.class);
        intent.putExtra("circleId", circleId);
        intent.putExtra("publisherId", publisherId);
        intent.putExtra("momentId", momentId);
        intent.putExtra("type", 2);
        mContext.startActivity(intent);
    }
}
