package com.jxkj.fit_5a.conpoment.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.StyleKitName;

import java.util.List;

/**
 * TODO: document your custom view class.
 */
@SuppressLint("AppCompatCustomView")
public class RobotView extends ImageView {

    private Canvas mCanvas;
    List<List<Double>> info;
    public RobotView(Context context) {
        super(context);
        init(null, 0);
    }

    public RobotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public RobotView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.RobotView, defStyle, 0);
        a.recycle();
    }

    public void setData(List<List<Double>> info){
        this.info = info;
        Log.w("setData","setData:"+mCanvas);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.w("setData","onDraw:"+mCanvas);
        this.mCanvas = canvas;
        if(info!=null){
            StyleKitName.draw_1100900Canvas(mCanvas,info);
        }
//        RobotStyleKit.drawRobot(canvas,
//                new RectF(0, 0, getWidth(), getHeight()),
//                RobotStyleKit.ResizingBehavior.AspectFit,
//                Color.rgb(mRed, 198, 57),
//                mAngle);
        //StyleKitName.draw_1100900Canvas(canvas, new RectF(0f, 0f, getWidth(), getHeight()), StyleKitName.ResizingBehavior.AspectFill);

    }

    public void start(long time) {
        startPathAnim(time);
    }

    ValueAnimator valueAnimator;

    public void setRed(int red) {
//
        if(red==0){
            valueAnimator.end();//继续
//            valueAnimator.setDuration(5000);
        }else{
//            valueAnimator.resume();
            startPathAnim(1000);
        }
//        valueAnimator.setDuration(1000);
//        valueAnimator.resume();//继续
//        valueAnimator.start();//开始
//        valueAnimator.end();//结束
//        valueAnimator.cancel();//取消
    }

    // 开启路径动画
    public void startPathAnim(long duration) {
        // 0 － getLength()
        valueAnimator = ValueAnimator.ofFloat(0, StyleKitName.mPathMeasure.getLength());
        Log.w("startPathAnim", "measure length = " + StyleKitName.mPathMeasure.getLength());
        valueAnimator.setDuration(duration);
        // 减速插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
//        valueAnimator.setInterpolator(new AnticipateOvershootInterpolator(5f));
//     总长度  总距离   速度    用时

        //1    5000   100    50

        //0.8  4000   200    20

        //0.6  2400   300    8

        //0.2  480    40     12


        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                StyleKitName.mPathMeasure.getPosTan(value, StyleKitName.mCurrentPosition, null);

                postInvalidate();
            }
        });
        valueAnimator.start();

    }
}
