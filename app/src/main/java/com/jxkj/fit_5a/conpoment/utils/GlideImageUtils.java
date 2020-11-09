package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jxkj.fit_5a.R;

public class GlideImageUtils {

    public static void setGlideImage(Context mContext, String imgUrl, ImageView imageView){
        RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.icon_app_logo).centerCrop();
        Glide.with(mContext).load(imgUrl).apply(requestOptions).into(imageView);
    }
}
