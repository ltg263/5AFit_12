package com.jxkj.fit_5a.view.activity.mine;

import android.widget.ImageView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.ChartHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RZA extends BaseActivity {
    @BindView(R.id.line_chart_1)
    LineChart mLineChart1;
    @BindView(R.id.iv_img)
    ImageView mImg;

    private List<Entry> mData1 = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.rza;
    }

    @Override
    protected void initViews() {
//        Glide.with(this).load("file:///drawable/ic_path_img.svg").into(mImg);
        ChartHelper.initChart(mLineChart1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ChartHelper.addEntry(mData1, mLineChart1, (float) (200*Math.random()),"#FFB300");
                        }
                    });
                }
            }
        }).start();

    }
}
