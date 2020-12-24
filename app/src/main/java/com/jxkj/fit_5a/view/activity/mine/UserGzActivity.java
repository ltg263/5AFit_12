package com.jxkj.fit_5a.view.activity.mine;

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
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.entity.FollowFansList;
import com.jxkj.fit_5a.view.adapter.UserGzAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserGzActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private String userId;
    private UserGzAdapter mUserGzAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_user_gz;
    }

    @Override
    protected void initViews() {
        userId = getIntent().getStringExtra("userId");
        mTvTitle.setText("Ta关注的人");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mUserGzAdapter = new UserGzAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mUserGzAdapter);

        mUserGzAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                UserHomeActivity.startActivity(UserGzActivity.this,mUserGzAdapter.getData().get(position).getUser().getUserId()+"");
            }
        });
        mUserGzAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                FollowFansList.DataBean data = mUserGzAdapter.getData().get(position);

                if(view.getId()==R.id.tv_wgz){
                    show();
                    HttpRequestUtils.postfollow(data.getUser().getUserId() + "", new HttpRequestUtils.LoginInterface() {
                        @Override
                        public void succeed(String path) {
                            dismiss();
                            if(path.equals("0")){
                                data.getUser().setRelation(1);
                                mUserGzAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                }else if(view.getId()==R.id.tv_ygz){
                    show();
                    HttpRequestUtils.postfollowCancel(data.getUser().getUserId() + "", new HttpRequestUtils.LoginInterface() {
                        @Override
                        public void succeed(String path) {
                            dismiss();
                            if(path.equals("1")){
                                data.getUser().setRelation(0);
                                mUserGzAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
            }
        });
        getFollowFollowers();
    }


    @OnClick(R.id.ll_back)
    public void onViewClicked() {
        finish();
    }

    private int totalPage;
    int page = 1;
    private void getFollowFollowers(){
        RetrofitUtil.getInstance().apiService()
                .getFollowFollowers(userId,0,10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<FollowFansList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowFansList result) {
                        if (result.getCode()==0) {
                            List<FollowFansList.DataBean> data = result.getData();
                            mUserGzAdapter.setNewData(data);
//                            totalPage = StringUtil.getTotalPage(result.getData(), 10);
                            page++;
                            if(totalPage <= page){
                                page = 1;
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
    public static void startActivity(Context mContext, String userId) {
        Intent intent = new Intent(mContext, UserGzActivity.class);
        intent.putExtra("userId", userId);
        mContext.startActivity(intent);
    }
}
