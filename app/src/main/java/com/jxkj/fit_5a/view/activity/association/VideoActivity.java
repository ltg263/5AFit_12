package com.jxkj.fit_5a.view.activity.association;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.view.MyVideoPlayer;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;
import com.jxkj.fit_5a.view.adapter.ListVideoAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class VideoActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvPage2;

    private PagerSnapHelper snapHelper;
    private ListVideoAdapter videoAdapter;
    private LinearLayoutManager layoutManager;
    private int currentPosition;

    @Override
    protected int getContentView() {
        return R.layout.activity_video;
    }

    @Override
    protected void initViews() {

        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4");
        urlList.add("http://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4");
        urlList.add("http://vfx.mtime.cn/Video/2019/03/17/mp4/190317150237409904.mp4");
        urlList.add("http://vfx.mtime.cn/Video/2019/03/14/mp4/190314102306987969.mp4");
        urlList.add("http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4");
        urlList.add("http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4");
        urlList.add("http://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4");
        urlList.add("http://vfx.mtime.cn/Video/2019/03/17/mp4/190317150237409904.mp4");
        urlList.add("http://vfx.mtime.cn/Video/2019/03/14/mp4/190314102306987969.mp4");
        urlList.add("http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4");

        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvPage2);


        videoAdapter = new ListVideoAdapter(urlList);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvPage2.setLayoutManager(layoutManager);
        rvPage2.setAdapter(videoAdapter);
        addListener();
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
    @Override
    public void onPause() {
        super.onPause();
        MyVideoPlayer.releaseAllVideos();
    }

    public static void startActivity(Context mContext, String userId) {
        Intent intent = new Intent(mContext, VideoActivity.class);
        intent.putExtra("userId", userId);
        mContext.startActivity(intent);
    }
}
