package com.jxkj.fit_5a.conpoment.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.util.Log;
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
    ShoppingFlowLayout mSflGuigeYs;
    ShoppingFlowLayout mSflGuigeCm;
    public static int currentNum1 = 0;
    public static int currentNum2 = 0;
    TextView tv_price,tv_sales,tv_ok;
    List<ProductDetailsBean.SpecsLisBean> specsLis;
    List<ProductDetailsBean.SkuListBean> skuList;
    public DialogChoicePackage(Activity mContext, List<ProductDetailsBean.SpecsLisBean> specsLis,
                               List<ProductDetailsBean.SkuListBean> skuList, String imgUrl,OnChoicePackageDialogListener onChoicePackageDialogListener) {

        this.onChoicePackageDialogListener = onChoicePackageDialogListener;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.contentView = this.mInflater.inflate(R.layout.dialog_choice_package_layout, null);
        this.specsLis = specsLis;
        this.skuList = skuList;
        initDialog();
        findViewById(imgUrl);
    }

    private void findViewById(String imgUrl){
        iv = contentView.findViewById(R.id.dialog_choice_package_layout_shopping_img_iv);
        tv_price = contentView.findViewById(R.id.tv_price);
        tv_sales = contentView.findViewById(R.id.tv_sales);
        tv_ok = contentView.findViewById(R.id.tv_ok);
        GlideImageUtils.setGlideImage(mContext,imgUrl,iv);
        mSflGuigeYs = contentView.findViewById(R.id.sfl_guige_ys);
        mSflGuigeCm = contentView.findViewById(R.id.sfl_guige_cm);
        setFlowLayoutYs();
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChoicePackageDialogListener.btn_Ok();
            }
        });
    }
    boolean isY = false;

    private void setFlowLayoutYs() {
        mSflGuigeYs.removeAllViews();
        isY = false;
        List<ProductDetailsBean.SpecsLisBean.ChildrenBean> ys = specsLis.get(0).getChildren();
        for (int i = 0; i < ys.size(); i++) {
            LinearLayout mLinearLayout = (LinearLayout) View.inflate(mContext, R.layout.adatper_dialog_set_meal_layout, null);
            TextView textView = mLinearLayout.findViewById(R.id.adatper_dialog_set_meal2_layout_name_tv);
            LinearLayout linearLayout = mLinearLayout.findViewById(R.id.adatper_dialog_set_meal1_layout_ll);
            for(int j = 0;j<skuList.size();j++){
                String specsLisId = skuList.get(j).getSpecIds();
                linearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.shap_f5f5f5_5));
                textView.setTextColor(mContext.getResources().getColor(R.color.color_ffffff));
                if(specsLisId.contains(ys.get(i).getId())){
                    textView.setTextColor(mContext.getResources().getColor(R.color.color_666666));
                    if(i==currentNum1){
                        onChoicePackageDialogListener.addListener(j,skuList.get(j).getSpecText());
                        linearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.shap_fef6de_5));
                        setFlowLayoutCm(ys.get(i).getId());
                    }else{
                        linearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.shap_f5f5f5_5));
                        if(currentNum1==0 && i!=0 && !isY){
                            onChoicePackageDialogListener.addListener(j,skuList.get(j).getSpecText());
                            linearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.shap_fef6de_5));
                        }
                    }
                    isY = true;
                    int finalI = i;
                    mLinearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentNum1 = finalI;
                            setFlowLayoutYs();
                        }
                    });
                    break;
                }
            }
            textView.setText(ys.get(i).getName());
            mSflGuigeYs.addView(mLinearLayout);
        }
    }

    private void setFlowLayoutCm(String id) {
        currentNum2 = 0;
        isY = false;
        mSflGuigeCm.removeAllViews();
        List<ProductDetailsBean.SpecsLisBean.ChildrenBean> cm = specsLis.get(1).getChildren();
        for (int i = 0; i < cm.size(); i++) {
            LinearLayout mLinearLayout = (LinearLayout) View.inflate(mContext, R.layout.adatper_dialog_set_meal_layout, null);
            TextView textView = mLinearLayout.findViewById(R.id.adatper_dialog_set_meal2_layout_name_tv);
            LinearLayout linearLayout = mLinearLayout.findViewById(R.id.adatper_dialog_set_meal1_layout_ll);
            for(int j = 0;j<skuList.size();j++){
                String specsLisId = skuList.get(j).getSpecIds();
                linearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.shap_f5f5f5_5));
                textView.setTextColor(mContext.getResources().getColor(R.color.color_ffffff));
                if(specsLisId.equals(id+","+cm.get(i).getId())){
                    textView.setTextColor(mContext.getResources().getColor(R.color.color_666666));
                    if(i==currentNum2){
                        linearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.shap_fef6de_5));
                        GlideImageUtils.setGlideImage(mContext,skuList.get(j).getImgUrl(),iv);
                        onChoicePackageDialogListener.addListener(j,skuList.get(j).getSpecText());
                        tv_price.setText(skuList.get(j).getDeductIntegral());
                        tv_sales.setText("￥ "+skuList.get(j).getDisPrice());
                    }else{
                        linearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.shap_f5f5f5_5));
                        if(currentNum2==0 && i!=0 && !isY){
                            onChoicePackageDialogListener.addListener(j,skuList.get(j).getSpecText());
                            linearLayout.setBackground(mContext.getResources().getDrawable(R.drawable.shap_fef6de_5));
                        }
                    }
                    isY = true;
                    int finalI = i;
                    mLinearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentNum2 = finalI;
                            setFlowLayoutCm(id);
                        }
                    });
                    break;
                }
            }
            textView.setText(cm.get(i).getName());
            mSflGuigeCm.addView(mLinearLayout);
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

    public interface OnChoicePackageDialogListener {
        void addListener(int pos, String text);

        void btn_Ok();
    }
}

