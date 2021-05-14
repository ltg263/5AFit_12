package com.jxkj.fit_5a.view.search;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;
import com.jxkj.fit_5a.view.adapter.CircleDynamicAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author : LiuJie
 * date   : 2020/6/116:57
 */
public class HomeSearchDpListFragment extends BaseFragment {
    @BindView(R.id.rv_shopping_cart)
    RecyclerView mRvDtList;
    @BindView(R.id.lv_not)
    LinearLayout lv_not;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private Bundle bundle;
    private String search = "";
    private CircleDynamicAdapter mCircleDynamicAdapter;


    @Override
    protected int getContentView() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initViews() {
        bundle = getArguments();
        if (bundle != null) {
            search = bundle.getString("search");
        }
        getData();
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableRefresh(false);
    }

    @Override
    public void initImmersionBar() {

    }
//内容类型(1:文本;2:图文;3:视频
    private void getData() {
        RetrofitUtil.getInstance().apiService()
                .getQuery_by_keyword(search, "2",1, 100)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if (result.getData() != null && result.getData().size() > 0) {
                                lv_not.setVisibility(View.GONE);
                                mRefreshLayout.setVisibility(View.VISIBLE);
                                initList(result.getData());
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

    private void initList(List<QueryPopularBean> list) {
        mCircleDynamicAdapter = new CircleDynamicAdapter(list);
        mRvDtList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvDtList.setHasFixedSize(true);
        mRvDtList.setAdapter(mCircleDynamicAdapter);

        mCircleDynamicAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                AssociationActivity.startActivity(getActivity(),
                        mCircleDynamicAdapter.getData().get(position).getPublisherId(),
                        mCircleDynamicAdapter.getData().get(position).getMomentId());
            }
        });;
        mCircleDynamicAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                QueryPopularBean data = mCircleDynamicAdapter.getData().get(position);
                switch (view.getId()){
                    case R.id.iv_head_img:
                    case R.id.tv_name:
                    case R.id.tv_time:
                        UserHomeActivity.startActivity(getActivity(),data.getUser().getUserId()+"");
                        break;
                    case R.id.tv_wgz:
                        HttpRequestUtils.postfollowCancel(data.getUser().getUserId() + "", new HttpRequestUtils.LoginInterface() {
                            @Override
                            public void succeed(String path) {
                                if(path.equals("1")){
                                    data.getUser().setRelation(0);
                                    mCircleDynamicAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                        break;
                    case R.id.ll_xihuan:
                        if(data.isIsLike()){
                            HttpRequestUtils.postLikeCancel1("0",data.getMomentId()+"", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                                @Override
                                public void succeed(String path) {
                                    if(path.equals("0")){
                                        data.setIsLike(false);
                                        data.setLikeCount((Integer.parseInt(data.getLikeCount())-1)+"");
                                        mCircleDynamicAdapter.notifyDataSetChanged();
                                    }
                                }
                            });
                        }else{
                            HttpRequestUtils.postLike1("0",data.getMomentId()+"", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                                @Override
                                public void succeed(String path) {
                                    if(path.equals("0")) {
                                        data.setIsLike(true);
                                        data.setLikeCount((Integer.parseInt(data.getLikeCount())+1)+"");
                                        mCircleDynamicAdapter.notifyDataSetChanged();
                                    }
                                }
                            });
                        }
                        break;
                }
            }
        });
    }
}
