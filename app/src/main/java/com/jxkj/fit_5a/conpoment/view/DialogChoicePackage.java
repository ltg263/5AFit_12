package com.jxkj.fit_5a.conpoment.view;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.SDUIUtil;
import com.jxkj.fit_5a.entity.ProductDetailsBean;

import java.util.ArrayList;
import java.util.List;


/**
 * author : LiuJie
 * date   : 2020/6/514:57
 */
public class DialogChoicePackage {
    private View contentView;
    private Dialog dialog;
    private Activity mContext;
    private LayoutInflater mInflater;
    private ImageView iv;
    public static int currentNum = 0;
    List<ProductDetailsBean.SpecsLisBean> specsLis;
    public DialogChoicePackage(Activity mContext, List<ProductDetailsBean.SpecsLisBean> specsLis, String imgUrl) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.contentView = this.mInflater.inflate(R.layout.dialog_choice_package_layout, null);
        this.specsLis = specsLis;
        initDialog();
        findViewById(imgUrl);
    }

    private void findViewById(String imgUrl){
        iv = contentView.findViewById(R.id.dialog_choice_package_layout_shopping_img_iv);
        ShoppingFlowLayout mSflGuigeYs = contentView.findViewById(R.id.sfl_guige_ys);
        ShoppingFlowLayout sfl_guige_cm = contentView.findViewById(R.id.sfl_guige_cm);
        GlideImageUtils.setGlideImage(mContext,imgUrl,iv);
        setFlowLayout(mSflGuigeYs,sfl_guige_cm);
    }

    private void setFlowLayout(ShoppingFlowLayout mSflGuigeYs,ShoppingFlowLayout sfl_guige_cm) {
        mSflGuigeYs.removeAllViews();
        for (int i = 0; i < specsLis.size(); i++) {
            LinearLayout mLinearLayout = (LinearLayout) View.inflate(mContext, R.layout.adatper_dialog_set_meal_layout, null);
            TextView textView = mLinearLayout.findViewById(R.id.adatper_dialog_set_meal2_layout_name_tv);
            LinearLayout linearLayout = mLinearLayout.findViewById(R.id.adatper_dialog_set_meal1_layout_ll);
            if(i==currentNum){
                linearLayout.setBackground(mContext.getDrawable(R.drawable.shap_fef6de_5));
                setFlowLayout(sfl_guige_cm,0);
            }else{
                linearLayout.setBackground(mContext.getDrawable(R.drawable.shap_f5f5f5_5));
            }
            textView.setText(specsLis.get(i).getName());
            int finalI = i;
            mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentNum = finalI;
                    setFlowLayout(mSflGuigeYs,sfl_guige_cm);
                }
            });
            mSflGuigeYs.addView(mLinearLayout);
        }
    }

    private void setFlowLayout(ShoppingFlowLayout sfl_guige_cm,int pos) {
        sfl_guige_cm.removeAllViews();
        for (int i = 0; i < specsLis.get(currentNum).getChildren().size(); i++) {
            LinearLayout mLinearLayout = (LinearLayout) View.inflate(mContext, R.layout.adatper_dialog_set_meal_layout, null);
            TextView textView = mLinearLayout.findViewById(R.id.adatper_dialog_set_meal2_layout_name_tv);
            LinearLayout linearLayout = mLinearLayout.findViewById(R.id.adatper_dialog_set_meal1_layout_ll);
            if(i==pos){
                linearLayout.setBackground(mContext.getDrawable(R.drawable.shap_fef6de_5));
            }else{
                linearLayout.setBackground(mContext.getDrawable(R.drawable.shap_f5f5f5_5));
            }
            textView.setText(specsLis.get(currentNum).getChildren().get(i).getName());
            int finalI = i;
            mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setFlowLayout(sfl_guige_cm, finalI);

                }
            });
            sfl_guige_cm.addView(mLinearLayout);
        }
    }


    private void initDialog() {
        dialog = new Dialog(mContext, R.style.MyDialog);
        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setWindowAnimations(R.style.menushow);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        lp.width = SDUIUtil.getScreenWidth(mContext); // 宽度设置为屏幕的
//        lp.height = SDUIUtil.getScreenHeight(mContext);
        dialogWindow.setAttributes(lp);
    }

    public void showDialog() {
        if (dialog != null)
            dialog.show();
    }


    private OnChoicePackageDialogListener onChoicePackageDialogListener;

    public OnChoicePackageDialogListener getOnChoicePackageDialogListener() {
        return onChoicePackageDialogListener;
    }

    public void setOnChoicePackageDialogListener(OnChoicePackageDialogListener onChoicePackageDialogListener) {
        this.onChoicePackageDialogListener = onChoicePackageDialogListener;
    }

    public interface OnChoicePackageDialogListener {
        void addListener(String skuId, int num);

        void buyListener(String skuId, int num);
    }
}

