package com.jxkj.fit_5a.view.activity.association;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.view.DialogCommentPackage;
import com.jxkj.fit_5a.conpoment.view.MyVideoPlayer;
import com.jxkj.fit_5a.entity.CommentMomentBean;
import com.jxkj.fit_5a.entity.MomentDetailsBean;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.view.adapter.ListVideoAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VideoActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvPage2;
    @BindView(R.id.iv)
    ImageView mImageView;

    private PagerSnapHelper snapHelper;
    private ListVideoAdapter videoAdapter;
    private LinearLayoutManager layoutManager;
    private int currentPosition;
    ArrayList<String> urlList = new ArrayList<>();
    String circleId = "0";
    int type = 1;
    @Override
    protected int getContentView() {
        return R.layout.activity_video;
    }

    @Override
    protected void initViews() {
        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvPage2);
        videoAdapter = new ListVideoAdapter(null, new ListVideoAdapter.VideoInterface() {
            @Override
            public void btnLiuYan(MomentDetailsBean data) {
                ShowCommentPackageDialog(data);
            }
        });
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvPage2.setLayoutManager(layoutManager);
        rvPage2.setAdapter(videoAdapter);
        addListener();
        type = getIntent().getIntExtra("type",0);
        if(type==2){
            circleId = getIntent().getStringExtra("circleId");
        }
        if(type==1){
            getMomentDetails();
        }else if(type==2){
            getMomentDetailsCircle();
        }
    }

    private void getMomentDetails() {
        RetrofitUtil.getInstance().apiService()
                .getMomentDetails(getIntent().getStringExtra("momentId")
                        , getIntent().getStringExtra("publisherId"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MomentDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MomentDetailsBean> result) {
                        if (isDataInfoSucceed(result)) {
                            String media = result.getData().getMedia();
                            try {
                                JSONArray jsonArray = new JSONArray(media);
                                getPlay_info(result.getData(), jsonArray.getJSONObject(0).getString("vedioId")
                                        , jsonArray.getJSONObject(0).getString("imageUrl"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
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
                            String media = result.getData().getMedia();
                            try {
                                JSONArray jsonArray = new JSONArray(media);
                                getPlay_info(result.getData(), jsonArray.getJSONObject(0).getString("vedioId")
                                        , jsonArray.getJSONObject(0).getString("imageUrl"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }

    private void getPlay_info(MomentDetailsBean data, String videoId, String imageUrl) {
        Glide.with(this).load(imageUrl).into(mImageView);
        RetrofitUtil.getInstance().apiService()
                .getPlay_info(null, videoId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<VideoPlayInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<VideoPlayInfoBean> result) {
                        if (isDataInfoSucceed(result)) {
                            for (int i = 0; i < 5; i++) {
                                urlList.add(result.getData().getPlayInfoList().get(0).getPlayURL());
                                videoAdapter.setData(data, imageUrl);
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

    private void ShowCommentPackageDialog(MomentDetailsBean data) {
        DialogCommentPackage choicePackageDialog = new DialogCommentPackage(this,circleId);
        HttpRequestUtils.getCommentMoment1(circleId,data.getMomentId() + "", data.getPublisherId() + "",1,100,
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
                HttpRequestUtils.postCommentMoment1(circleId,context, data.getMomentId()+"", data.getPublisherId()+"",
                        commentId, new HttpRequestUtils.LoginInterface() {
                            @Override
                            public void succeed(String path) {
                                dismiss();
                                HttpRequestUtils.getCommentMoment1(circleId,data.getMomentId() + "", data.getPublisherId() + "",1,100,
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

    @Override
    public void onPause() {
        super.onPause();
        MyVideoPlayer.releaseAllVideos();
    }

    /**
     * 获取动态信息
     */
    public static void startActivity(Context mContext, String publisherId, String momentId) {
        Intent intent = new Intent(mContext, VideoActivity.class);
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

    @OnClick({R.id.iv_back, R.id.tv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_share:
                break;
        }
    }
}
