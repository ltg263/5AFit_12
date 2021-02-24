package com.jxkj.fit_5a.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.view.MyVideoPlayer;
import com.jxkj.fit_5a.entity.MomentDetailsBean;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;

import java.util.List;

public class ListVideoAdapter extends VideoBaseAdapter<VideoPlayInfoBean.PlayInfoListBean, ListVideoAdapter.VideoViewHolder> {
    MomentDetailsBean data;
    VideoInterface videoInterface;
    public ListVideoAdapter(List<VideoPlayInfoBean.PlayInfoListBean> list,VideoInterface videoInterface) {
        super(list);
        this.videoInterface = videoInterface;
    }


    public interface VideoInterface {
        /**
         * 留言
         */
        public void btnLiuYan(MomentDetailsBean data);

        public void position(int position);
    }
    @Override
    public void onHolder(VideoViewHolder holder, VideoPlayInfoBean.PlayInfoListBean bean, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        data = bean.getData();
        Glide.with(context).load(data.getUser().getAvatar()).into(holder.iv_head);

        holder.mp_video.setUp(bean.getPlayURL(), data.getContent(), MyVideoPlayer.STATE_NORMAL);
        if (position == 0) {
            holder.mp_video.startVideo();
        }
        videoInterface.position(position);
        Glide.with(context).load(bean.getImageUrl()).into(holder.mp_video.thumbImageView);
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
        holder.tv_fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoInterface.btnLiuYan(data);
            }
        });
        holder.iv_xihuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xihuan(holder);
            }
        });
        holder.iv_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoucang(holder);
            }
        });
    }
    private void xihuan(VideoViewHolder holder){
        if(data.isIsLike()){
            HttpRequestUtils.postLikeCancel1("0",data.getMomentId()+"", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")){
                        holder.tv_xihuan.setText((data.getLikeCount()-1)+"");
                        holder.iv_xihuan.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_xin_99_d));
                        data.setIsLike(false);
                        data.setLikeCount(data.getLikeCount()-1);
//                                        mAssociationListAdapter.notifyDataSetChanged();
                    }
                }
            });
        }else{
            HttpRequestUtils.postLike1("0",data.getMomentId()+"", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")) {
                        holder.tv_xihuan.setText((data.getLikeCount()+1)+"");
                        holder.iv_xihuan.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_celect_xh_yes));
                        data.setIsLike(true);
                        data.setLikeCount(data.getLikeCount() + 1);
//                                        mAssociationListAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    private void shoucang(VideoViewHolder holder){
        if(data.isIsFavorite()){
            HttpRequestUtils.postFavoritCancel("0", data.getMomentId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")){
                        holder.tv_shoucang.setText((data.getFavoriteCount()-1)+"");
                        holder.iv_shoucang.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_share_sc_d));
                        data.setIsFavorite(false);
                        data.setFavoriteCount(data.getFavoriteCount()-1);
//                                                mAssociationListAdapter.notifyDataSetChanged();
                    }
                }
            });
        }else {
            HttpRequestUtils.postFavorit("0", data.getMomentId() + "",
                    data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                        @Override
                        public void succeed(String path) {
                            if(path.equals("0")) {
                                holder.tv_shoucang.setText((data.getFavoriteCount()+1)+"");
                                holder.iv_shoucang.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_share_sc_dx));
                                data.setIsFavorite(true);
                                data.setFavoriteCount(data.getFavoriteCount() + 1);
//                                                mAssociationListAdapter.notifyDataSetChanged();
                            }
                        }
                    });
        }
    }


    @Override
    public VideoViewHolder onCreateHolder() {
        return new VideoViewHolder(getViewByRes(R.layout.item_page2));

    }

    public class VideoViewHolder extends VideoBaseViewHolder {
        public View rootView;
        public MyVideoPlayer mp_video;
        public TextView tv_name;
        public TextView tv_liuyan,tv_content,tv_xihuan,tv_shoucang,tv_fenxiang;
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
            this.tv_fenxiang = rootView.findViewById(R.id.tv_fenxiang);
        }

    }

}