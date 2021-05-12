package com.jxkj.fit_5a.view.activity.association;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.DialogCommentPackage;
import com.jxkj.fit_5a.conpoment.view.MyVideoPlayer;
import com.jxkj.fit_5a.entity.CommentMomentBean;
import com.jxkj.fit_5a.entity.MomentDetailsBean;
import com.jxkj.fit_5a.entity.MomentDetailsBean_X;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.view.adapter.ListVideoAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
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
    String circleId = "0";
    int type = 1;
    String nextParam = null;
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

            @Override
            public void position(int position) {
                Log.w("ListVideoAdapter","ListVideoAdapter"+position);
                if(position==infoList.size()-1 && jobId.equals(infoList.get(infoList.size()-1).getJobId())){
                    jobId = "";
                    getQuery_next_graphic(videoAdapter.getData().get(position).getData().getMomentId());
                }

            }
        });
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvPage2.setLayoutManager(layoutManager);
        rvPage2.setAdapter(videoAdapter);
        addListener();
        type = getIntent().getIntExtra("type",0);
        if(type==2){
        }
        if(type==1){
            getMomentDetails();
        }else if(type==2){
            circleId = getIntent().getStringExtra("circleId");
            getMomentDetailsCircle();
        }
        postBrows();
    }


    private void postBrows(){
        RetrofitUtil.getInstance().apiService()
                .postBrows(circleId,getIntent().getStringExtra("momentId")
                        ,getIntent().getStringExtra("publisherId"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result result) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
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
                                nextParam = null;
                                JSONArray jsonArray = new JSONArray(media);
                                getPlay_infos(result.getData(),jsonArray.getJSONObject(0).getString("vedioId")
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
                                getPlay_infos(result.getData(),jsonArray.getJSONObject(0).getString("vedioId")
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


    private void getQuery_next_graphic(String momentId){
        RetrofitUtil.getInstance().apiService()
                .query_next_simple_video(momentId+"",nextParam)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MomentDetailsBean_X>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result<MomentDetailsBean_X> result) {
                        if(isDataInfoSucceed(result)){
                            nextParam = result.getData().getNextParam();
                            List<MomentDetailsBean> lists = result.getData().getList();
                            for(int i=0;i<lists.size();i++){
                                String media = lists.get(i).getMedia();
                                try {
                                    JSONArray jsonArray = new JSONArray(media);
                                    getPlay_infos(lists.get(i),jsonArray.getJSONObject(0).getString("vedioId")
                                            , jsonArray.getJSONObject(0).getString("imageUrl"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            if(lists.size()==0){
                                nextParam = null;
                                getQuery_next_graphic(getIntent().getStringExtra("momentId"));
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
    String jobId = "";
    List<VideoPlayInfoBean.PlayInfoListBean> infoList = new ArrayList<>();
    private void getPlay_infos(MomentDetailsBean data,String videoId, String imageUrl) {
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
                            if(StringUtil.isBlank(nextParam)){
//                                getQuery_next_graphic(data.getMomentId());
                            }
                            List<VideoPlayInfoBean.PlayInfoListBean> info = result.getData().getPlayInfoList();
                            info.get(0).setImageUrl(imageUrl);
                            info.get(0).setData(data);
                            jobId = info.get(0).getJobId();
                            infoList.addAll(info);
                            videoAdapter.setNewData(infoList);
                            videoAdapter.notifyDataSetChanged();
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
