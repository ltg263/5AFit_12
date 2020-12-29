package com.jxkj.fit_5a.view.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.view.DialogCommentPackage;
import com.jxkj.fit_5a.conpoment.view.MyVideoPlayer;

import java.util.List;

public class ListVideoAdapter extends VideoBaseAdapter<String, ListVideoAdapter.VideoViewHolder> {


    public ListVideoAdapter(List<String> list) {
        super(list);
    }

    @Override
    public void onHolder(VideoViewHolder holder, String bean, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

        holder.mp_video.setUp(bean, "第" + position + "个视频", MyVideoPlayer.STATE_NORMAL);
        if (position == 0) {
            holder.mp_video.startVideo();
        }
        Glide.with(context).load(bean).into(holder.mp_video.thumbImageView);
        holder.tv_name.setText("第" + position + "个视频");
        if(holder.mp_video.bottomProgressBar.getProgress()!=0){
            holder.bottom_progress.setProgress(holder.mp_video.bottomProgressBar.getProgress());
        }
        holder.tv_liuyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowChoicePackageDialog();
            }
        });
    }

    private void ShowChoicePackageDialog() {
        DialogCommentPackage choicePackageDialog = new DialogCommentPackage((Activity) context,"");
        choicePackageDialog.setOnCommentPackageDialogListener(new DialogCommentPackage.OnCommentPackageDialogListener() {
            @Override
            public void addListener(String skuId, String num) {

            }

            @Override
            public void buyListener(String skuId, int num) {

            }

        });
        choicePackageDialog.showDialog();
    }
    @Override
    public VideoViewHolder onCreateHolder() {
        return new VideoViewHolder(getViewByRes(R.layout.item_page2));

    }

    public class VideoViewHolder extends VideoBaseViewHolder {
        public View rootView;
        public MyVideoPlayer mp_video;
        public TextView tv_name;
        public TextView tv_liuyan;
        public ProgressBar bottom_progress;

        public VideoViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mp_video = rootView.findViewById(R.id.mp_video);
            this.tv_name = rootView.findViewById(R.id.tv_name);
            this.bottom_progress = rootView.findViewById(R.id.bottom_progress);
            this.tv_liuyan = rootView.findViewById(R.id.tv_liuyan);
        }

    }

}