/*
 * Copyright (C) 2016 venshine.cn@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jxkj.fit_5a.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.wx.wheelview.adapter.BaseWheelAdapter;
import com.wx.wheelview.widget.WheelItem;

/**
 * 滚轮文本适配器
 *
 * @author venshine
 */
public class ArrayWheelAdapter extends BaseWheelAdapter<String> {

    private Context mContext;

    public ArrayWheelAdapter(Context context) {
        mContext = context;
    }


    @Override
    protected View bindView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_sv, null);
            viewHolder.textView = convertView.findViewById(R.id.item_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(mList.get(position));
        viewHolder.textView.setTextColor(mContext.getResources().getColor(R.color.color_666666));
        if(parent.isSelected()){
            viewHolder.textView.setTextColor(mContext.getResources().getColor(R.color.color_666666));
        }
        return convertView;
    }

    static class ViewHolder {
        TextView textView;
    }

}
