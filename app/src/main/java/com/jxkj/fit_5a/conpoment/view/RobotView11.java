package com.jxkj.fit_5a.conpoment.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.entity.MapDetailsBean;

import java.util.List;

/**
 * TODO: document your custom view class.
 */
@SuppressLint("AppCompatCustomView")
public class RobotView11 extends View {
    List<List<Float>> info;

    private static class CacheFor_1100900Canvas {
        private static Paint paint = new Paint();
        private static Path _2Path = new Path();
        private static Path _3Path = new Path();
    }
    public RobotView11(Context context) {
        super(context);
        init(null, 0);
    }

    public RobotView11(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public RobotView11(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.RobotView, defStyle, 0);
        a.recycle();
    }

    public void setData(List<List<Float>> info, MapDetailsBean.ParamBean param) {
        this.info = info;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(info==null){
            return;
        }
        float bl = 0.1f;
        Log.w("--->>>>>", "屏幕宽:" + getWidth());//1920 1852*700 900*600
//        Log.w("--->>>>>", "屏幕高:" + canvas.getHeight());//1080
////
//        Log.w("--->>>>>", "矩形范围宽:" + canvas.getHeight() * bl);//1920
//        Log.w("--->>>>>", "矩形范围高:" + canvas.getHeight());//1080
//
//        Log.w("--->>>>>", "矩形居中X起止:" + (canvas.getWidth() - canvas.getHeight() * bl) / 2);//1080
//        Log.w("--->>>>>", "矩形居中X终止:" + ((canvas.getWidth() - canvas.getHeight() * bl) / 2 + canvas.getHeight() * bl));//1080

//       1620.0  1080.0
        //矩形范围宽:1620
        RectF resizedFrame = new RectF(20,10,getWidth(),getHeight());
        canvas.translate(resizedFrame.left, resizedFrame.top);
////        Log.w("--->>>>>","resizedFrame.left:"+resizedFrame.left);
////        Log.w("--->>>>>","resizedFrame.top:"+resizedFrame.top);
////        Log.w("--->>>>>","resizedFrame.height():"+resizedFrame.height());
////        Log.w("--->>>>>","resizedFrame.width():"+resizedFrame.width());
////        Log.w("--->>>>>","SDUIUtil.px2dp(MainApplication.getContext(),param.getReferenceWidth():"+SDUIUtil.px2dp(MainApplication.getContext(),param.getReferenceWidth()));
        CacheFor_1100900Canvas._2Path.reset();
//        float blx = resizedFrame.width() / param.getReferenceWidth();
//        float bly = resizedFrame.height() / param.getReferenceHeight();
//        Log.w("--->>>>>","blx:"+blx);
//        Log.w("--->>>>>","bly:"+bly);

        CacheFor_1100900Canvas._2Path.moveTo(info.get(0).get(0) * (bl + 0.05f), info.get(0).get(1) * (bl + 0.05f));
        for (int i = 0; i < info.size(); i++) {
            CacheFor_1100900Canvas._2Path.lineTo(info.get(i).get(0) * (bl + 0.05f), info.get(i).get(1) * (bl + 0.05f));
        }
        CacheFor_1100900Canvas._2Path.lineTo(info.get(0).get(0) * (bl + 0.05f), info.get(0).get(1) * (bl + 0.05f));
//        CacheFor_1100900Canvas._2Path.close();
        CacheFor_1100900Canvas.paint.reset();
        CacheFor_1100900Canvas.paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        CacheFor_1100900Canvas.paint.setStrokeWidth(5f);
        canvas.save();
        CacheFor_1100900Canvas.paint.setStyle(Paint.Style.STROKE);
        CacheFor_1100900Canvas.paint.setColor(MainApplication.getContext().getResources().getColor(R.color.color_text_theme));
        canvas.drawPath(CacheFor_1100900Canvas._2Path, CacheFor_1100900Canvas.paint);

//        if (mCurrentPosition == null) {
//            mCurrentPosition = new float[2];
//            mCurrentPosition[0] = info.get(0).get(0) * (bl + 0.05f);
//            mCurrentPosition[1] = info.get(0).get(1) * (bl + 0.05f);
//        }

//        Bitmap bitmap = ((BitmapDrawable) MainApplication.getContext().getResources().getDrawable(R.drawable.ic_d_red)).getBitmap();
//        bitmap = StringUtil.zoomImage(bitmap, 30, 50);
//        canvas.drawBitmap(bitmap, mCurrentPosition[0] - 15, mCurrentPosition[1] - 50, CacheFor_1100900Canvas.paint);
//
//        if (mPathMeasure == null) {
//            mPathMeasure = new PathMeasure(CacheFor_1100900Canvas._2Path, true);
//        }

        canvas.restore();
    }
}
