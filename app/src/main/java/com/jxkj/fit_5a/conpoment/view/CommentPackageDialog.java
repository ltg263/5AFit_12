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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.OrderInfoData;
import com.jxkj.fit_5a.conpoment.utils.SDUIUtil;
import com.jxkj.fit_5a.view.adapter.MineCommentAdapter;
import com.jxkj.fit_5a.view.adapter.OrderShoppingDetailsAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * author : LiuJie
 * date   : 2020/6/514:57
 */
public class CommentPackageDialog {
    private View contentView;
    private Dialog dialog;
    private Activity mContext;
    private LayoutInflater mInflater;
    public CommentPackageDialog(Activity mContext) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.contentView = this.mInflater.inflate(R.layout.dialog_comment_package_layout, null);
        initDialog();
        findViewById();
    }

    private void findViewById(){
        RecyclerView mRvList = contentView.findViewById(R.id.rv_list);

        mRvList.setLayoutManager(new LinearLayoutManager(mContext));
        List<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        MineCommentAdapter mMineCommentAdapter = new MineCommentAdapter(data);//item.getProducts()
        mRvList.setAdapter(mMineCommentAdapter);

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


    private OnCommentPackageDialogListener onCommentPackageDialogListener;

    public OnCommentPackageDialogListener getOnCommentPackageDialogListener() {
        return onCommentPackageDialogListener;
    }

    public void setOnCommentPackageDialogListener(OnCommentPackageDialogListener onCommentPackageDialogListener) {
        this.onCommentPackageDialogListener = onCommentPackageDialogListener;
    }

    public interface OnCommentPackageDialogListener {
        void addListener(String skuId, int num);

        void buyListener(String skuId, int num);
    }
}

