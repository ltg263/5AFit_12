package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.entity.MapDetails;
import com.jxkj.fit_5a.entity.MapListSposrt;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */
public class SelectMapFragment extends BaseFragment {
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_go_2)
    TextView mTvGo2;
    private String id;

    @Override
    protected int getContentView() {
        return R.layout.fragment_select_map;
    }

    @Override
    protected void initViews() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getString("id","");
        }
        getSportMapDetails();

    }

    private void getSportMapDetails() {
        RetrofitUtil.getInstance().apiService()
                .getSportMapDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MapDetails>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MapDetails> result) {
                        if (isDataInfoSucceed(result)) {
                            mTvName.setText(result.getData().getName());
                            GlideImgLoader.loadImageViewRadius(getActivity(),result.getData().getImgUrl(),15,mIv);
                            mTvGo2.setText(result.getData().getDistance()+"km");
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
    public void initImmersionBar() {
    }
}
