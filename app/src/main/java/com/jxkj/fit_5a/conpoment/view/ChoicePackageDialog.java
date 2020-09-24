package com.jxkj.fit_5a.conpoment.view;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.SDUIUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * author : LiuJie
 * date   : 2020/6/514:57
 */
public class ChoicePackageDialog {
    private View contentView;
    private Dialog dialog;
    private Activity mContext;
    private LayoutInflater mInflater;
    public static int currentNum = 0;
    public ChoicePackageDialog(Activity mContext) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.contentView = this.mInflater.inflate(R.layout.dialog_choice_package_layout, null);
        initDialog();
        findViewById();
    }

    private void findViewById(){
        ShoppingFlowLayout mSflGuigeYs = contentView.findViewById(R.id.sfl_guige_ys);
        ShoppingFlowLayout sfl_guige_cm = contentView.findViewById(R.id.sfl_guige_cm);
        setFlowLayout(mSflGuigeYs);
        setFlowLayout(sfl_guige_cm);
    }

    private void setFlowLayout(ShoppingFlowLayout mSflGuigeYs) {
        mSflGuigeYs.removeAllViews();
        List<String> list = new ArrayList<>();
        list.add("黑色");
        list.add("红色");
        list.add("黄色");
        for (int i = 0; i < list.size(); i++) {
            LinearLayout mLinearLayout = (LinearLayout) View.inflate(mContext, R.layout.adatper_dialog_set_meal_layout, null);
            TextView textView = mLinearLayout.findViewById(R.id.adatper_dialog_set_meal2_layout_name_tv);
            LinearLayout linearLayout = mLinearLayout.findViewById(R.id.adatper_dialog_set_meal1_layout_ll);
            if(i==currentNum){
                linearLayout.setBackground(mContext.getDrawable(R.drawable.shap_fef6de_5));
            }else{
                linearLayout.setBackground(mContext.getDrawable(R.drawable.shap_f5f5f5_5));
            }
            textView.setText(list.get(i));
            int finalI = i;
            mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentNum = finalI;
                }
            });
            mSflGuigeYs.addView(mLinearLayout);
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

