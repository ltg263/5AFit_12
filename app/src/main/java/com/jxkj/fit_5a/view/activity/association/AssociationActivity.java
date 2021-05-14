package com.jxkj.fit_5a.view.activity.association;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.view.DialogCommentPackage;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTy;
import com.jxkj.fit_5a.entity.CommentMomentBean;
import com.jxkj.fit_5a.entity.MomentDetailsBean;
import com.jxkj.fit_5a.entity.MomentDetailsBean_X;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;
import com.jxkj.fit_5a.view.adapter.AssociationListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    int type;
    String circleId = "0";
    String nextParam = null;
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
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if(type==1){
                    getMomentDetails();
                }else if(type==2){
                    getMomentDetailsCircle();
                }
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
                if(type==1){
                    getQuery_next_graphic(mMomentDetailsBeans.get(mMomentDetailsBeans.size()-1).getMomentId());
                }else if(type==2){
                    getQuery_next_graphic_circle(mMomentDetailsBeans.get(mMomentDetailsBeans.size()-1).getMomentId());
                }
            }
        });
        refreshLayout.setEnableScrollContentWhenLoaded(false);
        type = getIntent().getIntExtra("type",0);
        if(type==2){
            circleId = getIntent().getStringExtra("circleId");
        }
        postBrows();
        initRv();
    }

    private void postBrows(){
        RetrofitUtil.getInstance().apiService()
                .postBrows(circleId,getIntent().getStringExtra("momentId")
                        ,getIntent().getStringExtra("publisherId"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result result) {
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
    LinearLayoutManager manager;
    private void initRv() {
        mAssociationListAdapter = new AssociationListAdapter(null);
        manager = new LinearLayoutManager(this);
        mRvList.setLayoutManager(manager);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(mRvList);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mAssociationListAdapter);
        mRvList.setNestedScrollingEnabled(true);
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
                        show();
                        if(data.isIsLike()){
                            HttpRequestUtils.postLikeCancel1(circleId,data.getMomentId(), data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                                @Override
                                public void succeed(String path) {
                                    dismiss();
                                    if(path.equals("0")){
                                        ((TextView)view.findViewById(R.id.tv_xihuan)).setText((data.getLikeCount() - 1)+"");
                                        ((ImageView)view.findViewById(R.id.iv_xihuan)).setImageDrawable(getResources().getDrawable(R.drawable.icon_xin_99_d));
                                        data.setIsLike(false);
                                        data.setLikeCount(data.getLikeCount()-1);
//                                        mAssociationListAdapter.notifyDataSetChanged();
                                    }
                                }
                            });
                        }else{
                            HttpRequestUtils.postLike1(circleId,data.getMomentId(), data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                                @Override
                                public void succeed(String path) {
                                    dismiss();
                                    if(path.equals("0")) {
                                        ((TextView)view.findViewById(R.id.tv_xihuan)).setText((data.getLikeCount() + 1)+"");
                                        ((ImageView)view.findViewById(R.id.iv_xihuan)).setImageDrawable(getResources().getDrawable(R.drawable.ic_celect_xh_yes));
                                        data.setIsLike(true);
                                        data.setLikeCount(data.getLikeCount() + 1);
//                                        mAssociationListAdapter.notifyDataSetChanged();
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
                            HttpRequestUtils.postFavoritCancel(circleId, data.getMomentId(), new HttpRequestUtils.LoginInterface() {
                                        @Override
                                        public void succeed(String path) {
                                            dismiss();
                                            if(path.equals("0")){
                                                ((TextView)view.findViewById(R.id.tv_shoucang)).setText((data.getFavoriteCount() - 1)+"");
                                                ((ImageView)view.findViewById(R.id.iv_shoucang)).setImageDrawable(getResources().getDrawable(R.drawable.icon_share_sc_d));
                                                data.setIsFavorite(false);
                                                data.setFavoriteCount(data.getFavoriteCount()-1);
//                                                mAssociationListAdapter.notifyDataSetChanged();
                                            }
                                        }
                                    });
                        }else {
                            HttpRequestUtils.postFavorit(circleId, data.getMomentId(),
                                    data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                                        @Override
                                        public void succeed(String path) {
                                            dismiss();
                                            if(path.equals("0")) {
                                                ((TextView)view.findViewById(R.id.tv_shoucang)).setText((data.getFavoriteCount() + 1)+"");
                                                ((ImageView)view.findViewById(R.id.iv_shoucang)).setImageDrawable(getResources().getDrawable(R.drawable.icon_share_sc_dx));
                                                data.setIsFavorite(true);
                                                data.setFavoriteCount(data.getFavoriteCount() + 1);
//                                                mAssociationListAdapter.notifyDataSetChanged();
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
        HttpRequestUtils.getCommentMoment1(circleId,data.getMomentId(), data.getPublisherId() + "",1,100,
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
                HttpRequestUtils.postCommentMoment1(circleId,context, data.getMomentId(), data.getPublisherId()+"",
                        commentId, new HttpRequestUtils.LoginInterface() {
                    @Override
                    public void succeed(String path) {
                        dismiss();
                        HttpRequestUtils.getCommentMoment1(circleId,data.getMomentId(), data.getPublisherId() + "",1,100,
                                new HttpRequestUtils.ResultInterface() {
                                    @Override
                                    public void succeed(ResultList<CommentMomentBean> result) {
                                        isDataInfoSucceed(result);
                                        data.setCommentCount(data.getCommentCount()+1);
                                        choicePackageDialog.setNewData(result.getData(),data.getCommentCount()+"");
                                    }
                                });
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
                initPopupWindow();
                break;
        }
    }

    PopupWindowTy window;

    List<String> list = new ArrayList<>();
    private void initPopupWindow() {
        list.clear();
        list.add("相册");
        list.add("视频");
        if (window == null) {
            window = new PopupWindowTy(AssociationActivity.this, list, new PopupWindowTy.GiveDialogInterface() {
                @Override
                public void btnConfirm(int position) {
                    int type = 3;
                    if (position == 0) {
                        type = 2;
                    }
                    IntentUtils.getInstence().intent(AssociationActivity.this, AssociationAddActivity.class,"type",type);
                }
            });

            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }

        window.showAtLocation(mTvTitle, Gravity.BOTTOM, 0, 0); // 设置layout在PopupWindow中显示的位置10464.66
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
                            nextParam=null;
                            mMomentDetailsBeans.clear();
                            mMomentDetailsBeans.add(result.getData());
                            if(type==1){
                                getQuery_next_graphic(mMomentDetailsBeans.get(mMomentDetailsBeans.size()-1).getMomentId());
                            }else if(type==2){
                                getQuery_next_graphic_circle(mMomentDetailsBeans.get(mMomentDetailsBeans.size()-1).getMomentId());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        refreshLayout.finishRefresh();

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
                            nextParam=null;
                            mMomentDetailsBeans.clear();
                            mMomentDetailsBeans.add(result.getData());
                            if(type==1){
                                getQuery_next_graphic(mMomentDetailsBeans.get(mMomentDetailsBeans.size()-1).getMomentId());
                            }else if(type==2){
                                getQuery_next_graphic_circle(mMomentDetailsBeans.get(mMomentDetailsBeans.size()-1).getMomentId());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        refreshLayout.finishRefresh();
                        dismiss();
                    }
                });
    }

    private void getQuery_next_graphic_circle(String momentId){
        RetrofitUtil.getInstance().apiService()
                .getQuery_next_graphic_circle(circleId,momentId,nextParam)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MomentDetailsBean_X>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result<MomentDetailsBean_X> result) {
                        if(isDataInfoSucceed(result)){
                            nextParam = result.getData().getNextParam();
                            mMomentDetailsBeans.addAll(result.getData().getList());
                            mAssociationListAdapter.setNewData(mMomentDetailsBeans);
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
    private void getQuery_next_graphic(String momentId){
        RetrofitUtil.getInstance().apiService()
                .getQuery_next_graphic(momentId,nextParam)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MomentDetailsBean_X>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result<MomentDetailsBean_X> result) {
                        if(isDataInfoSucceed(result)){
                            nextParam = result.getData().getNextParam();
                            mMomentDetailsBeans.addAll(result.getData().getList());
                            mAssociationListAdapter.setNewData(mMomentDetailsBeans);
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
