package com.jxkj.fit_5a.view.activity.association;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.view.MyVideoPlayer;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.view.adapter.ListVideoAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VideoActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvPage2;

    private PagerSnapHelper snapHelper;
    private ListVideoAdapter videoAdapter;
    private LinearLayoutManager layoutManager;
    private int currentPosition;
    //    String videoId;
    ArrayList<String> urlList = new ArrayList<>();
    @Override
    protected int getContentView() {
        return R.layout.activity_video;
    }

    @Override
    protected void initViews() {
//        videoId = getIntent().getStringExtra("videoId");
        getQueryByPublisher();
//
//        urlList.add("http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4");
//        urlList.add("http://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4");
//        urlList.add("http://vfx.mtime.cn/Video/2019/03/17/mp4/190317150237409904.mp4");
//        urlList.add("http://vfx.mtime.cn/Video/2019/03/14/mp4/190314102306987969.mp4");
//        urlList.add("http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4");
//        urlList.add("http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4");
//        urlList.add("http://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4");
//        urlList.add("http://vfx.mtime.cn/Video/2019/03/17/mp4/190317150237409904.mp4");
//        urlList.add("http://vfx.mtime.cn/Video/2019/03/14/mp4/190314102306987969.mp4");
//        urlList.add("http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4");

        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvPage2);


        videoAdapter = new ListVideoAdapter(null);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvPage2.setLayoutManager(layoutManager);
        rvPage2.setAdapter(videoAdapter);
        addListener();
    }

    private void getPlay_info(String videoId){
        RetrofitUtil.getInstance().apiService()
                .getPlay_info(null,videoId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<VideoPlayInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<VideoPlayInfoBean> result) {
                        if(isDataInfoSucceed(result)) {
                            for(int i =0;i<5;i++){
                                urlList.add(result.getData().getPlayInfoList().get(0).getPlayURL());
                                videoAdapter.setNewData(urlList);
                                videoAdapter.notifyDataSetChanged();
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


    private void addListener() {

        rvPage2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {


            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE://停止滚动
                        View view = snapHelper.findSnapView(layoutManager);

                        //当前固定后的item position
                        int position = recyclerView.getChildAdapterPosition(view);
                        if (currentPosition != position) {
                            //如果当前position 和 上一次固定后的position 相同, 说明是同一个, 只不过滑动了一点点, 然后又释放了
                            MyVideoPlayer.releaseAllVideos();
                            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
                            if (viewHolder != null && viewHolder instanceof ListVideoAdapter.VideoViewHolder) {
                                ((ListVideoAdapter.VideoViewHolder) viewHolder).mp_video.startVideo();
                            }
                        }
                        currentPosition = position;
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING://拖动
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                        break;
                }

            }
        });
    }

    private void getQueryByPublisher() {
        RetrofitUtil.getInstance().apiService()
                .getQueryByPublisherOwn(0, 3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {
                        if (result.getCode() == 0) {
//                            for(int i=0;i<result.getData().size();i++){
                            String[] strArr = result.getData().get(0).getMedia().split(",");
                            getPlay_info(strArr[1]);
//                            }
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
    public void onPause() {
        super.onPause();
        MyVideoPlayer.releaseAllVideos();
    }

    public static void startActivity(Context mContext, String videoId) {
        Intent intent = new Intent(mContext, VideoActivity.class);
        intent.putExtra("videoId", videoId);
        mContext.startActivity(intent);
    }
}
