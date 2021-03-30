package com.jxkj.fit_5a.conpoment.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.entity.MapDetailsBean;

import java.util.List;


/**
 * Created by AuthorName on Mar 23, 2021.
 * Copyright  2021 CompanyName. All rights reserved.
 * <p>
 * Generated by PaintCode
 * http://www.paintcodeapp.com
 *
 * @author AuthorName
 */
public class StyleKitName {


    public static PathMeasure mPathMeasure;
    public static float[] mCurrentPosition;

    private static class CacheFor_1100900Canvas {
        private static Paint paint = new Paint();
        private static Path _2Path = new Path();
        private static Path _3Path = new Path();
    }

    public static void draw_1100900Canvas(Canvas canvas, List<List<Float>> info, MapDetailsBean.ParamBean param, List<List<Float>> infoJg) {
        float bl = (float) (param.getReferenceWidth()/param.getReferenceHeight());
//        Log.w("--->>>>>", "屏幕宽:" + canvas.getWidth());//1920 1852*700 900*600
//        Log.w("--->>>>>", "屏幕高:" + canvas.getHeight());//1080
////
//        Log.w("--->>>>>", "矩形范围宽:" + canvas.getHeight() * bl);//1920
//        Log.w("--->>>>>", "矩形范围高:" + canvas.getHeight());//1080
//
//        Log.w("--->>>>>", "矩形居中X起止:" + (canvas.getWidth() - canvas.getHeight() * bl) / 2);//1080
//        Log.w("--->>>>>", "矩形居中X终止:" + ((canvas.getWidth() - canvas.getHeight() * bl) / 2 + canvas.getHeight() * bl));//1080

        //矩形范围宽:1620
        RectF resizedFrame = new RectF((canvas.getWidth() - canvas.getHeight() * bl) / 2, 0f, (canvas.getWidth() - canvas.getHeight() * bl) / 2 + canvas.getHeight() * bl,canvas.getHeight());
        canvas.translate(resizedFrame.left, resizedFrame.top);
        CacheFor_1100900Canvas._2Path.reset();

        CacheFor_1100900Canvas._2Path.moveTo(info.get(0).get(0) * bl, info.get(0).get(1)  * bl);
        for (int i = 0; i < info.size(); i++) {
            CacheFor_1100900Canvas._2Path.lineTo(info.get(i).get(0)  * bl, info.get(i).get(1) * bl);
        }
        CacheFor_1100900Canvas._2Path.lineTo(info.get(0).get(0)  * bl, info.get(0).get(1)  * bl);
//        CacheFor_1100900Canvas._2Path.close();
        CacheFor_1100900Canvas.paint.reset();
        CacheFor_1100900Canvas.paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        CacheFor_1100900Canvas.paint.setStrokeWidth(5f);
        canvas.save();
        CacheFor_1100900Canvas.paint.setStyle(Paint.Style.STROKE);
        CacheFor_1100900Canvas.paint.setColor(MainApplication.getContext().getResources().getColor(R.color.color_text_theme));
        canvas.drawPath(CacheFor_1100900Canvas._2Path, CacheFor_1100900Canvas.paint);
//        infoJg = info;
        if (infoJg != null && infoJg.size() > 0) {
            CacheFor_1100900Canvas._3Path.reset();
            CacheFor_1100900Canvas._3Path.moveTo(infoJg.get(infoJg.size() - 1).get(0), infoJg.get(infoJg.size() - 1).get(1));
            for (int i = 0; i < infoJg.size(); i++) {
                CacheFor_1100900Canvas._3Path.lineTo(infoJg.get(i).get(0), infoJg.get(i).get(1));
            }

            CacheFor_1100900Canvas.paint.reset();
            CacheFor_1100900Canvas.paint.setFlags(Paint.ANTI_ALIAS_FLAG);
            CacheFor_1100900Canvas.paint.setStrokeWidth(5f);
            canvas.save();
            CacheFor_1100900Canvas.paint.setStyle(Paint.Style.STROKE);
            CacheFor_1100900Canvas.paint.setColor(MainApplication.getContext().getResources().getColor(R.color.red40));
//            canvas.drawPath(CacheFor_1100900Canvas._3Path, CacheFor_1100900Canvas.paint);
        }

        if (mCurrentPosition == null) {
            mCurrentPosition = new float[2];
            mCurrentPosition[0] = info.get(0).get(0) * bl;
            mCurrentPosition[1] = info.get(0).get(1) * bl;
        }

        Bitmap bitmap = ((BitmapDrawable) MainApplication.getContext().getResources().getDrawable(R.drawable.ic_d_red)).getBitmap();
        bitmap = StringUtil.zoomImage(bitmap, 30, 50);
        canvas.drawBitmap(bitmap, mCurrentPosition[0] - 15, mCurrentPosition[1] - 50, CacheFor_1100900Canvas.paint);

        if (mPathMeasure == null) {
            mPathMeasure = new PathMeasure(CacheFor_1100900Canvas._2Path, true);
        }

        canvas.restore();
    }


}