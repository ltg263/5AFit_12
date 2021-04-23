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
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.conpoment.utils.StyleKitName;
import com.jxkj.fit_5a.conpoment.utils.ToastUtil;
import com.jxkj.fit_5a.entity.MapDetailsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: document your custom view class.
 */
@SuppressLint("AppCompatCustomView")
public class RobotView extends ImageView {

    private Canvas mCanvas;
    List<List<Float>> info;
    List<List<Float>> infoJg;
    MapDetailsBean.ParamBean param;

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

    public void setData(List<List<Float>> info, MapDetailsBean.ParamBean param) {
        this.info = info;
        this.param = param;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCanvas = canvas;
        if (info != null) {
            StyleKitName.draw_1100900Canvas(mCanvas, info, param,infoJg);
        }
    }

    ValueAnimator valueAnimator;
    private float lastAnimtionValue = 0;
//
    long resetDuration = 0;
    int quanNum = 1;
    public void setRed(long resetDuration,int quanNum) {
        Log.w("--->>>","时间准备改变:"+resetDuration);//392111
        if(resetDuration<4000 || this.resetDuration == resetDuration || StyleKitName.mPathMeasure==null){
            return;
        }
//        ToastUtil.showLongStrToast(MainApplication.getContext(),"时间改变了:"+resetDuration);//392111
        if(infoJg == null){//
            infoJg = new ArrayList<>();
        }
        if(valueAnimator!=null){
            if(this.quanNum < quanNum){
                lastAnimtionValue = 0;
            }
            valueAnimator.cancel();
            valueAnimator = null;
        }
        this.quanNum = quanNum;
        this.resetDuration = resetDuration;

        valueAnimator = ValueAnimator.ofFloat(lastAnimtionValue, StyleKitName.mPathMeasure.getLength());
        valueAnimator.setDuration(resetDuration < 1000 ? 1000 : resetDuration);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.removeAllUpdateListeners();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                lastAnimtionValue = value;
//                Log.e("onAnimationUpdate：", "lastAnimtionValue: " + lastAnimtionValue);
                invalidateByValue(value);
            }
        });
        valueAnimator.start();
    }
    public void setState(int state){
        if(valueAnimator==null){
            return;
        }
        if(state==1){
            if( valueAnimator.isRunning()){
                valueAnimator.pause();
            }
        }else{
            if(!valueAnimator.isRunning()){
                valueAnimator.resume();
            }
        }
    }

    public void setCancel(){
        if(valueAnimator!=null) {
            valueAnimator.cancel();
            valueAnimator = null;
        }
    }

    // 开启路径动画
    public void startPathAnim(long currentDuration) {
        infoJg = new ArrayList<>();
        // 0 － getLength()
        valueAnimator = ValueAnimator.ofFloat(0, StyleKitName.mPathMeasure.getLength());
        valueAnimator.setDuration(currentDuration);
        // 减速插值器
//        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                lastAnimtionValue = value;
//                Log.e("onAnimationUpdate：", "lastAnimtionValue: " + lastAnimtionValue);
                invalidateByValue(value);
            }
        });
        valueAnimator.start();
    }

    private void invalidateByValue(float value) {
        StyleKitName.mPathMeasure.getPosTan(value, StyleKitName.mCurrentPosition, null);
        StyleKitName.mCurrentPosition[0] = StyleKitName.mCurrentPosition[0];
        StyleKitName.mCurrentPosition[1] = StyleKitName.mCurrentPosition[1];
        List<Float> floats = new ArrayList<>();
        floats.add(StyleKitName.mCurrentPosition[0]);
        floats.add(StyleKitName.mCurrentPosition[1]);
        infoJg.add(floats);
        postInvalidate();
    }

}
