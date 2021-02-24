package com.jxkj.fit_5a.view.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.view.DialogCommentPackage;
import com.jxkj.fit_5a.conpoment.view.MyVideoPlayer;
import com.jxkj.fit_5a.entity.CommentMomentBean;
import com.jxkj.fit_5a.entity.MomentDetailsBean;

import java.util.List;

public class ListVideoAdapter extends VideoBaseAdapter<String, ListVideoAdapter.VideoViewHolder> {
    MomentDetailsBean data;
    String imageUrl;
    VideoInterface videoInterface;
    public ListVideoAdapter(List<String> list,VideoInterface videoInterface) {
        super(list);
        this.videoInterface = videoInterface;
    }

    public void setData(MomentDetailsBean data, String imageUrl){
        this.data = data;
        this.imageUrl = imageUrl;
    }

    public interface VideoInterface {
        /**
         * 确定
         */
        public void btnLiuYan(MomentDetailsBean data);
    }
    @Override
    public void onHolder(VideoViewHolder holder, String bean, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        Glide.with(context).load(data.getUser().getAvatar()).into(holder.iv_head);

        holder.mp_video.setUp(bean, data.getContent(), MyVideoPlayer.STATE_NORMAL);
        if (position == 0) {
            holder.mp_video.startVideo();
        }
        Glide.with(context).load(imageUrl).into(holder.mp_video.thumbImageView);
        holder.tv_name.setText(data.getUser().getNickName());
        holder.tv_content.setText(data.getContent());
        holder.tv_xihuan.setText(data.getLikeCount()+"");
        holder.tv_liuyan.setText(data.getCommentCount()+"");
        holder.tv_shoucang.setText(data.getFavoriteCount()+"");

        GlideImgLoader.loadImageViewRadius(context,data.getUser().getAvatar(),holder.iv_head);
        holder.iv_shoucang.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_share_sc_d));
        if(data.isIsFavorite()){
            holder.iv_shoucang.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_share_sc_dx));
        }
        holder.iv_xihuan.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_xin_99_d));
        if(data.isIsLike()){
            holder.iv_xihuan.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_celect_xh_yes));
        }
        if(holder.mp_video.bottomProgressBar.getProgress()!=0){
            holder.bottom_progress.setProgress(holder.mp_video.bottomProgressBar.getProgress());
        }

        holder.tv_liuyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoInterface.btnLiuYan(data);
            }
        });
    }


    @Override
    public VideoViewHolder onCreateHolder() {
        return new VideoViewHolder(getViewByRes(R.layout.item_page2));

    }

    public class VideoViewHolder extends VideoBaseViewHolder {
        public View rootView;
        public MyVideoPlayer mp_video;
        public TextView tv_name;
        public TextView tv_liuyan,tv_content,tv_xihuan,tv_shoucang;
        public ProgressBar bottom_progress;
        public ImageView iv_head,iv_xihuan,iv_shoucang;
        public VideoViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mp_video = rootView.findViewById(R.id.mp_video);
            this.tv_content = rootView.findViewById(R.id.tv_content);
            this.tv_name = rootView.findViewById(R.id.tv_name);
            this.iv_head = rootView.findViewById(R.id.iv_head);
            this.iv_xihuan = rootView.findViewById(R.id.iv_xihuan);
            this.iv_shoucang = rootView.findViewById(R.id.iv_shoucang);
            this.bottom_progress = rootView.findViewById(R.id.bottom_progress);
            this.tv_liuyan = rootView.findViewById(R.id.tv_liuyan);
            this.tv_xihuan = rootView.findViewById(R.id.tv_xihuan);
            this.tv_shoucang = rootView.findViewById(R.id.tv_shoucang);
        }

    }

}