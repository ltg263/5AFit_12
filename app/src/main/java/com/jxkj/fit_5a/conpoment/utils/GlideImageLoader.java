package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Log.w("--->>","path:"+path);
        GlideImgLoader.loadImageViewRadius(context,(String) path,1,imageView);
    }
}
