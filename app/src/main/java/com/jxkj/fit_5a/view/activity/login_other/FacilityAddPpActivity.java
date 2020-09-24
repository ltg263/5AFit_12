package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.view.adapter.ImgAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FacilityAddPpActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_all_list)
    RecyclerView mRvAllList;
    @BindView(R.id.video_view)
    VideoView mVideoView;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.iv_d)
    ImageView mIvD;
    private ImgAdapter mImgAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_facility_add_pp;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("设备名称");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        initRvUi();
        initVvUi();
    }

    private void initVvUi() {

        /***
         * 将播放器关联上一个音频或者视频文件
         * videoView.setVideoURI(Uri uri)
         * videoView.setVideoPath(String path)
         * 以上两个方法都可以。
         */
        mVideoView.setVideoPath("http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4");

        /**
         * w为其提供一个控制器，控制其暂停、播放……等功能
         */
        mVideoView.setMediaController(new MediaController(this));

        /**
         * 视频或者音频到结尾时触发的方法
         */
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i("通知", "完成");
            }
        });

        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {

            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.i("通知", "播放中出现错误");
                return false;
            }
        });

    }

    private void initRvUi() {

        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        mImgAdapter = new ImgAdapter(list);

        mRvAllList.setLayoutManager(new GridLayoutManager(this, 3));
        mRvAllList.setHasFixedSize(true);
        mRvAllList.setAdapter(mImgAdapter);

        mImgAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });
    }


    @OnClick({R.id.ll_back, R.id.ll_connect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_connect:
                goConnect();
                break;
        }
    }
    AnimationDrawable animationDrawable;
    private void goConnect() {
        mIv.setVisibility(View.GONE);
        mTv.setVisibility(View.GONE);
        mIvD.setVisibility(View.VISIBLE);
        animationDrawable = (AnimationDrawable) mIvD.getBackground();
        animationDrawable.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showDialogUi();
                    }
                });
            }
        }).start();
    }

    private void showDialogUi() {
        mIv.setVisibility(View.VISIBLE);
        mTv.setVisibility(View.VISIBLE);
        mIvD.setVisibility(View.GONE);
        animationDrawable.stop();
        DialogUtils.showDialogLyState(FacilityAddPpActivity.this,  "这是一个标题", 1, new DialogUtils.DialogLyInterface() {
            @Override
            public void btnConfirm() {
                startActivity(new Intent(FacilityAddPpActivity.this, InterestActivity.class));
            }
        });
    }

}
