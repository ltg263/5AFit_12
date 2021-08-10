package com.jxkj.fit_5a.view.activity.mine;

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
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.entity.AnnouncementList;
import com.jxkj.fit_5a.entity.LastUnreadMessageBeanList;
import com.jxkj.fit_5a.entity.MessageSubtypeBean;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.activity.association.MineCircleActivity;
import com.jxkj.fit_5a.view.activity.home.WebViewActivity;
import com.jxkj.fit_5a.view.adapter.MineMessageSubTypeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineMessageSubTypeActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    String subType;
    private MineMessageSubTypeAdapter mMineMessageSubTypeAdapter;


    @OnClick({R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRv();
    }

    @Override
    protected void initViews() {
        //子消息类型(2,动态评论;3,动态或评论点赞;4,新粉丝;6,圈子解散)
        subType = getIntent().getStringExtra("subType");
        mTvTitle.setText("消 息");
//        switch (subType){
//            case "2":
//                mTvTitle.setText("动态消息");
//                break;
//            case "3":
//                mTvTitle.setText("消 息");
//                break;
//            case "4":
//                mTvTitle.setText("消 息");
//                break;
//            case "6":
//                mTvTitle.setText("消 息");
//                break;
//        }
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mLvNot.setVisibility(View.GONE);
        mRvList.setVisibility(View.VISIBLE);
        mMineMessageSubTypeAdapter = new MineMessageSubTypeAdapter(null);
        mRvList.setAdapter(mMineMessageSubTypeAdapter);
        mMineMessageSubTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MessageSubtypeBean.LastUnreadMessageBean data = mMineMessageSubTypeAdapter.getData().get(position);
                //子消息类型(2,动态评论;3,动态或评论点赞;4,新粉丝;6,圈子解散)
                getMessagSetRead(data.getId());
                switch (data.getSubType()){
                    case "2":
                        MessageSubtypeBean.LastUnreadMessageBean.ContentCommentBean mData_2 = data.getContentComment();
                        if(mData_2.getReplyCommentType().equals("1")){//(1,回复动态的评论;2,回复评论的评论)
                            if(mData_2.getReplyMomentParam().getCircleId().equals("0")){
                                AssociationActivity.startActivity(MineMessageSubTypeActivity.this,
                                        mData_2.getReplyMomentParam().getMomentPublisherId(),
                                        mData_2.getReplyMomentParam().getMomentId());
                            }else{
                                AssociationActivity.startActivity(MineMessageSubTypeActivity.this,
                                        mData_2.getReplyMomentParam().getCircleId(),
                                        mData_2.getReplyMomentParam().getMomentPublisherId(),
                                        mData_2.getReplyMomentParam().getMomentId());
                            }
                        }else if(mData_2.getReplyCommentType().equals("2")){
                            if(mData_2.getReplyCommentParam().getCircleId().equals("0")){
                                AssociationActivity.startActivity(MineMessageSubTypeActivity.this,
                                        mData_2.getReplyCommentParam().getMomentPublisherId(),
                                        mData_2.getReplyCommentParam().getMomentId());
                            }else{
                                AssociationActivity.startActivity(MineMessageSubTypeActivity.this,
                                        mData_2.getReplyCommentParam().getCircleId(),
                                        mData_2.getReplyCommentParam().getMomentPublisherId(),
                                        mData_2.getReplyCommentParam().getMomentId());
                            }
                        }

                        data.getContentComment();
                        break;
                    case "3":
                        MessageSubtypeBean.LastUnreadMessageBean.ContentLikeBean  mData = data.getContentLike();
                        if(mData.getLikeType().equals("1")){//(1,点赞动态;2,点赞评论)
                            if(mData.getMomentLikeParam().getCircleId().equals("0")){
                                AssociationActivity.startActivity(MineMessageSubTypeActivity.this,
                                        mData.getMomentLikeParam().getMomentPublisherId(),
                                        mData.getMomentLikeParam().getMomentId());
                            }else{
                                AssociationActivity.startActivity(MineMessageSubTypeActivity.this,
                                        mData.getMomentLikeParam().getCircleId(),
                                        mData.getMomentLikeParam().getMomentPublisherId(),
                                        mData.getMomentLikeParam().getMomentId());
                            }
                        }else if(mData.getLikeType().equals("2")){
                            if(mData.getCommentLikeParam().getCircleId().equals("0")){
                                AssociationActivity.startActivity(MineMessageSubTypeActivity.this,
                                        mData.getCommentLikeParam().getMomentPublisherId(),
                                        mData.getCommentLikeParam().getMomentId());
                            }else{
                                AssociationActivity.startActivity(MineMessageSubTypeActivity.this,
                                        mData.getCommentLikeParam().getCircleId(),
                                        mData.getCommentLikeParam().getMomentPublisherId(),
                                        mData.getCommentLikeParam().getMomentId());
                            }
                        }

                        break;
                    case "4":
                        UserHomeActivity.startActivity(MineMessageSubTypeActivity.this,
                                data.getContentNewFans().getNewFansParam().getUserId());
                        break;
                    case "6":
                        data.getContentCircleDismiss();
                        break;
                }
            }
        });
    }

    private void initRv() {
        RetrofitUtil.getInstance().apiService()
                .getMessageList(subType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<LastUnreadMessageBeanList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<LastUnreadMessageBeanList> result) {
                        if (isDataInfoSucceed(result)) {
                            if(mMineMessageSubTypeAdapter!=null){
                                mMineMessageSubTypeAdapter.setNewData(result.getData().getList());
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
    private void getMessagSetRead(String id) {
        RetrofitUtil.getInstance().apiService()
                .getMessagSetRead(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {

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

}
