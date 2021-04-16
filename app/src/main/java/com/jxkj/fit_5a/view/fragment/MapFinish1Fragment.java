package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.entity.SportLogStatsBean;
import com.jxkj.fit_5a.view.adapter.HomeTopAdapter;
import com.jxkj.fit_5a.view.adapter.MapFinishDataAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MapFinish1Fragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private MapFinishDataAdapter mMapFinishDataAdapter;

    private ArrayList<BpmDataBean> mBpmDataBeans;
    BpmDataBean.BpmTopData mBpmTopData;
    @Override
    protected int getContentView() {
        return R.layout.fragment_map_finish_1;
    }

    @Override
    protected void initViews() {

        Bundle bundle = getArguments();
        if (bundle != null) {
            mBpmDataBeans = bundle.getParcelableArrayList("mBpmDataBeans");
            if(mBpmDataBeans!=null){
                mBpmTopData = mBpmDataBeans.get(0).getBpmTopData();
            }
        }
        initRv();

    }

    private void initRv() {

        mMapFinishDataAdapter = new MapFinishDataAdapter(null);
        mRvList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mMapFinishDataAdapter);

        mMapFinishDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        List<String> list = new ArrayList<>();
        list.add("总卡路里");
        list.add("运动里程");
        list.add("运动时间");
        list.add("功率");
        list.add("段位");
        list.add("平均速度");
        list.add("最快速度");
        list.add("平均心跳");
        list.add("心率区间");
        list.add("BAI");

        mMapFinishDataAdapter.setNewData(list, mBpmTopData);
    }

    @Override
    public void initImmersionBar() {

    }
}
//使用微信登入會顯示appid不能為空，錯誤碼:10012。
//        無法使用連結QQ、微博、iConsole+登入。
//        使用電話號碼登入，首次登入成功會閃退。√
//        設備配對成功後，會跳回運動首頁藍芽斷線，無法正常開始運動。
//        設備管理會顯示與電子錶連線，實際上電子錶是斷線狀態，按下開始後，畫面停滯狀態。
//        心率控制的左上角，上一頁功能無法使用。√
//        設備管理藍芽配對完成後，按下課程選擇會跳回上一頁，並且有發生crash。
//        Android首頁多了鼓勵的話，iOS首頁沒有，是否要一致。
//        Android首頁少了"排行榜獎品"和"查看排行"功能。
//        設備管理-暫未連接設備-android=顯示h:m，iOS=顯示m:s。
//        設備管理-卡路里排名-android=顯示日周月，iOS=顯示月周日。
//        社群-android="加入的圈子"，iOS="熱門圈子"，2者名稱不一致。
//        個人資料跟iOS對照，少了一個基本訊息修改。
//        歷史設備無法保存之前配對過的機器。