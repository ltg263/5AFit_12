package com.jxkj.fit_5a.view.activity.association;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.MatisseUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PickerViewUtils;
import com.jxkj.fit_5a.view.adapter.SpPhotoAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TopicAddActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.tv_gk)
    TextView mTvGk;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.rv_list_zp)
    RecyclerView mRvListZp;
    private SpPhotoAdapter mSpPhotoAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_topic_add;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("话题动态");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mTvRighttext.setText("发布");
        mTvRighttext.setTextColor(getResources().getColor(R.color.color_text_theme));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.icon_fabu));
        initRvXq();
    }
    private void initRvXq() {
        mRvListZp.setLayoutManager(new GridLayoutManager(this, 4));
        mRvListZp.setHasFixedSize(true);
        mSpPhotoAdapter = new SpPhotoAdapter(null);
        mRvListZp.setAdapter(mSpPhotoAdapter);
        List<LocalMedia> list = new ArrayList<>();
        LocalMedia mLocalMedia = new LocalMedia();
        mLocalMedia.setPath("-1");
        list.add(mLocalMedia);
        mSpPhotoAdapter.setNewData(list);
        mSpPhotoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mSpPhotoAdapter.getData().get(position).getPath().equals("-1")) {
                    MatisseUtils.gotoSelectPhoto(TopicAddActivity.this, 10 - mSpPhotoAdapter.getData().size(),false);
                }
            }
        });

        mSpPhotoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mSpPhotoAdapter.remove(position);
//                mPicList.remove(position);
                LocalMedia mLocalMedia = new LocalMedia();
                mLocalMedia.setPath("-1");
                if (!mSpPhotoAdapter.getData().contains(mLocalMedia)) {
                    mSpPhotoAdapter.addData(mLocalMedia);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    mSpPhotoAdapter.addData(mSpPhotoAdapter.getData().size() - 1, selectList);
                    if (mSpPhotoAdapter.getData().size() > 6 && mSpPhotoAdapter.getData().contains("-1")) {
                        mSpPhotoAdapter.remove(mSpPhotoAdapter.getData().size() - 1);
                    }
                    mSpPhotoAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }


    private List<String> mFeedTypeList = new ArrayList<>();
    @OnClick({R.id.ll_back, R.id.tv_righttext, R.id.iv_rightimg,R.id.tv_gk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                break;
            case R.id.tv_righttext:

                break;
            case R.id.tv_gk:
                mFeedTypeList.clear();
                mFeedTypeList.add("公开可见");
                mFeedTypeList.add("私密不可见");
                mFeedTypeList.add("仅圈子可见");
                mFeedTypeList.add("关注的人可见");
                PickerViewUtils.selectorCustom(this, mFeedTypeList, "", mTvGk);
                break;
            case R.id.iv_rightimg:
                DialogUtils.showDialogCgCircle(this,  "创建圈子权限", 1, new DialogUtils.DialogLyInterface() {
                    @Override
                    public void btnConfirm() {

                    }
                });
                break;
        }
    }

}
