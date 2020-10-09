package com.jxkj.fit_5a.conpoment.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.jxkj.fit_5a.R;

public class DialogUtils {

    public static AlertDialog cancelDialog(Context context, String title, String content, DialogInterface.OnClickListener listener) {

        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton("确定",listener)
                .setNegativeButton("取消",null)
                .create();

    }
    public static AlertDialog donelDialog(Context context, String title, String content) {

        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton("确定",null)
                .create();

    }
    @SuppressLint("ClickableViewAccessibility")
    public static void showDialogStartYd(Activity context, final DialogInterfaceS dialogInterface) {

        final Dialog dialog = new Dialog(context, R.style.Dialog_Fullscreen);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_start_sop, null);

        LinearLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(context);
        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,10)));
        ImageView tv = view.findViewById(R.id.iv_1);
        ImageView tv1 = view.findViewById(R.id.iv_2);
        StepArcView_Btn mSv = view.findViewById(R.id.sv);

        tv.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                dialogInterface.btnType(1);
                dialog.dismiss();
            }
        });
        tv1.setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_UP){
                mSv.setStopAnimator();
            }
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                mSv.setCurrentCount(100, 100, new StepArcView_Btn.CurrentAngleInterface() {
                    @Override
                    public void complete() {
                        dialog.dismiss();
                        dialogInterface.btnType(2);
                    }
                });
            }
            return true;
        });

        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }

    /**
     * 链接蓝牙
     * @param context
     * @param title
     * @param state
     * @param dialogLyInterface
     */
    public static void showDialogLyState(Activity context,String title, int state, final DialogLyInterface dialogLyInterface) {

        final Dialog dialog = new Dialog(context, R.style.Dialog_Fullscreen);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_lanyan_state, null);

        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(context);
        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));

        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_state =  view.findViewById(R.id.tv_state);
        TextView tv_ok =  view.findViewById(R.id.tv_ok);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        tv_name.setText(title);
//        Glide.with(context).load(imgUrl).into(iv_icon);
        if(state==1){
            tv_state.setText("蓝牙连接成功");
            tv_ok.setText("OK");
        }else{
            tv_state.setText("蓝牙连接失败");
            tv_ok.setText("重新连接");
        }
        iv_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogLyInterface.btnConfirm();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }

    /**
     * 创建圈子成功
     * @param context
     * @param title
     * @param state
     * @param dialogLyInterface
     */
    public static void showDialogCgCircle(Activity context,String title, int state, final DialogLyInterface dialogLyInterface) {

        final Dialog dialog = new Dialog(context, R.style.Dialog_Fullscreen);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_not_circle_cg, null);

        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(context);
        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));

        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_state =  view.findViewById(R.id.tv_state);
        TextView tv_ok =  view.findViewById(R.id.tv_ok);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogLyInterface.btnConfirm();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }
    /**
     * 退出房间
     * @param context
     */
    public static void showDialogOutRoom(Activity context,final DialogLyInterface dialogLyInterface) {

        final Dialog dialog = new Dialog(context, R.style.Dialog_Fullscreen);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_out_room, null);

        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(context);
        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));

        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_state =  view.findViewById(R.id.tv_state);
        TextView tv_ok =  view.findViewById(R.id.tv_ok);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogLyInterface.btnConfirm();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }
    /**
     * 创建圈子权限
     * @param context
     * @param title
     * @param state
     * @param dialogLyInterface
     */
    public static void showDialogNoCircle(Activity context,String title, int state, final DialogLyInterface dialogLyInterface) {

        final Dialog dialog = new Dialog(context, R.style.Dialog_Fullscreen);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_not_circle_qx, null);

        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(context);
        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));

        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_state =  view.findViewById(R.id.tv_state);
        TextView tv_ok =  view.findViewById(R.id.tv_ok);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogLyInterface.btnConfirm();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }
    /**
     * 当前的奖杯
     * @param context
     * @param state
     * @param dialogLyInterface
     */
    public static void showDialogClass(Activity context,int state, final DialogLyInterface dialogLyInterface) {

        final Dialog dialog = new Dialog(context, R.style.Dialog_Fullscreen);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_class, null);
        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(context);
        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));

        ImageView iv = view.findViewById(R.id.iv_1);
        Button bntOk =  view.findViewById(R.id.tv_ok);
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        bntOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogLyInterface.btnConfirm();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }

    public interface DialogInterfaceS {
        /**
         * 确定
         */
        public void btnType(int pos);
    }
    public interface DialogLyInterface {
        /**
         * 确定
         */
        public void btnConfirm();
    }
    // 图片缩放比例(即模糊度)
    private static final float BITMAP_SCALE = 0.1f;

    public static Bitmap blurBitmap(Activity activity,Bitmap image, float blurRadius) {
        // 计算图片缩小后的长宽
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        // 将缩小后的图片做为预渲染的图片
        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        // 创建一张渲染后的输出图片
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(activity);
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(blurRadius);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);
        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }

    public static Bitmap screenShotWithoutStatusBar(Activity activity) {
        //通过window的源码可以看出：检索顶层窗口的装饰视图，可以作为一个窗口添加到窗口管理器
        View view = activity.getWindow().getDecorView();
        //启用或禁用绘图缓存
        view.setDrawingCacheEnabled(true);
        //创建绘图缓存
        view.buildDrawingCache();
        //拿到绘图缓存
        Bitmap bitmap = view.getDrawingCache();

        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

        //状态栏高度
        int statusBarHeight = frame.top;
        int width = getWindowWidth(activity);
        int height = getWindowHeight(activity);

        Bitmap bp = null;
        bp = Bitmap.createBitmap(bitmap, 0, 0, width, height );
        view.destroyDrawingCache();
        return bp;
    }
    /** 获取屏幕宽度
     * @return */

    public static int getWindowWidth(Activity activity) {
        WindowManager wm = (WindowManager) activity.getSystemService(
                Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }
    /** 获取屏幕宽度
     * @return */

    public static int getWindowHeight(Activity activity) {
        WindowManager wm = (WindowManager) activity.getSystemService(
                Context.WINDOW_SERVICE);

        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

}
