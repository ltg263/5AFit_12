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
import com.jxkj.fit_5a.view.activity.home.WebViewActivity;
import com.jxkj.fit_5a.view.activity.login_other.FacilityAddPpActivity;
import com.jxkj.fit_5a.view.adapter.MineMessageAdapter;
import com.jxkj.fit_5a.view.adapter.MineMessageAnnouncementAdapter;
import com.xiaosu.view.text.DataSetAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineMessageAnnouncementActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    private MineMessageAnnouncementAdapter mMineMessageAnnouncementAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("消 息");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mLvNot.setVisibility(View.GONE);
        mRvList.setVisibility(View.VISIBLE);
        mMineMessageAnnouncementAdapter = new MineMessageAnnouncementAdapter(null);
        mRvList.setAdapter(mMineMessageAnnouncementAdapter);
        mMineMessageAnnouncementAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                getAnnouncementUrl(mMineMessageAnnouncementAdapter.getData().get(position).getId(),mMineMessageAnnouncementAdapter.getData().get(position).getTitle());
            }
        });
        initRv();
    }

    private void initRv() {
        RetrofitUtil.getInstance().apiService()
                .getAnnouncementList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AnnouncementList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AnnouncementList> result) {
                        if (isDataInfoSucceed(result)) {
                            mMineMessageAnnouncementAdapter.setNewData(result.getData().getList());
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
    private void getAnnouncementUrl(String id, String title) {
        RetrofitUtil.getInstance().apiService()
                .getAnnouncementUrl(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<String> result) {
                        if (isDataInfoSucceed(result)) {

                            IntentUtils.getInstence()
                                    .intent(MineMessageAnnouncementActivity.this, WebViewActivity.class,
                                            "url",result.getData(),"type",title);
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
