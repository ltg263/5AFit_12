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
import com.jxkj.fit_5a.entity.MapDetailsBean;
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
    private MapListSposrt.ListBean data;

    @Override
    protected int getContentView() {
        return R.layout.fragment_select_map;
    }

    @Override
    protected void initViews() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            data = (MapListSposrt.ListBean) bundle.getSerializable("data");
        }
        getSportMapDetails();

    }

    private void getSportMapDetails() {

        mTvName.setText(data.getName());
        GlideImgLoader.loadImageViewRadius(getActivity(),data.getImgUrl(),15,mIv);
        mTvGo2.setText(data.getDistance()+"km");
    }

    @Override
    public void initImmersionBar() {
    }
}
