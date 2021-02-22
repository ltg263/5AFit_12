package com.jxkj.fit_5a.view.activity.mine.order;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.MatisseUtils;
import com.jxkj.fit_5a.conpoment.view.RatingBar;
import com.jxkj.fit_5a.conpoment.view.SquareImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MineOrderEvaluateGoodsActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_lefttext)
    TextView mTvLefttext;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.iv_rightimg_two)
    ImageView mIvRightimgTwo;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.iv_img)
    ImageView mIvImg;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_spec1)
    TextView mTvSpec1;
    @BindView(R.id.tv_spec2)
    TextView mTvSpec2;
    @BindView(R.id.ratingbar)
    RatingBar mRatingbar;
    @BindView(R.id.et_con)
    EditText mEtCon;
    @BindView(R.id.rv_photo)
    RecyclerView mRvPhoto;
    @BindView(R.id.iv_nm)
    ImageView mIvNm;
    @BindView(R.id.ratingbar1)
    RatingBar mRatingbar1;
    @BindView(R.id.ratingbar2)
    RatingBar mRatingbar2;
    @BindView(R.id.tv_fabu)
    TextView mTvFabu;
    @BindView(R.id.tv_r)
    TextView mTvR;
    @BindView(R.id.tv_r1)
    TextView mTvR1;
    @BindView(R.id.tv_r2)
    TextView mTvR2;
    private int rank1 = 5;
    private int rank2 = 5;
    private int rank3 = 5;
    private SpPhotoAdapter mSpPhotoAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_evaluate_goods;
    }

    private void initTitle() {
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mTvTitle.setText("评价");
    }

    @Override
    protected void initViews() {
        initTitle();

        initRvXq();

        mRatingbar.setStar(5);
        mRatingbar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                rank1 = (int) ratingCount;
                setTvEvaluatel(rank1, mTvR);
            }
        });
        mRatingbar1.setStar(5);
        mRatingbar1.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                rank1 = (int) ratingCount;
                setTvEvaluatel(rank2, mTvR1);
            }
        });
        mRatingbar2.setStar(5);
        mRatingbar2.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                rank1 = (int) ratingCount;
                setTvEvaluatel(rank3, mTvR2);
            }
        });

    }


    private void setTvEvaluatel(int ratingCount, TextView tv_evaluate) {
        switch (ratingCount) {
            case 5:
                tv_evaluate.setText("非常满意");
                break;
            case 4:
                tv_evaluate.setText("满意");
                break;
            case 3:
                tv_evaluate.setText("一般");
                break;
            case 2:
                tv_evaluate.setText("差");
                break;
            case 1:
            case 0:
                tv_evaluate.setText("非常差");
                break;
            default:
                break;
        }
    }

    private void initRvXq() {
        mRvPhoto.setLayoutManager(new GridLayoutManager(this, 3));
        mRvPhoto.setHasFixedSize(true);
        mSpPhotoAdapter = new SpPhotoAdapter(null);
        mRvPhoto.setAdapter(mSpPhotoAdapter);
        List<String> list = new ArrayList<>();
        list.add("-1");
        mSpPhotoAdapter.setNewData(list);
        mSpPhotoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mSpPhotoAdapter.getData().get(position).equals("-1")) {
                    MatisseUtils.gotoSelectPhoto(MineOrderEvaluateGoodsActivity.this, 7 - mSpPhotoAdapter.getData().size(), false);
                }
            }
        });
        mSpPhotoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mSpPhotoAdapter.remove(position);
//                mPicList.remove(position);
                if (!mSpPhotoAdapter.getData().contains("-1")) {
                    mSpPhotoAdapter.addData("-1");
                }
            }
        });
    }

    class SpPhotoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        SpPhotoAdapter(@Nullable List<String> data) {
            super(R.layout.item_photo, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

            if (item.equals("-1")) {
                helper.setVisible(R.id.iv_close, false).setVisible(R.id.iv_icon_add, true).setVisible(R.id.iv_icon, false);
            } else {
                Glide.with(mContext)
                        .load(item).into((SquareImageView) helper.getView(R.id.iv_icon));
                helper.setVisible(R.id.iv_close, true).setVisible(R.id.iv_icon_add, false).setVisible(R.id.iv_icon, true);
                helper.addOnClickListener(R.id.iv_close);
            }
        }
    }
}
