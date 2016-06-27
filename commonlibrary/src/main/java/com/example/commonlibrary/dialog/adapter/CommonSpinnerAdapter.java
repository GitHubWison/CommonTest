package com.example.commonlibrary.dialog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.commonlibrary.R;
import com.example.commonlibrary.model.DictionaryItem;

import java.util.List;

/**
 * Created by xuqiwei on 16-6-27.
 */
public class CommonSpinnerAdapter extends BaseAdapter {
    private List<DictionaryItem>dictionaryItems;
    private Context context;
    private ViewHolder viewHolder;

    public CommonSpinnerAdapter(List<DictionaryItem> dictionaryItems, Context context) {
        this.dictionaryItems = dictionaryItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dictionaryItems.size();
    }

    @Override
    public Object getItem(int i) {
        return dictionaryItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.adapteritem_commonspinner, null);
            viewHolder = new ViewHolder();
            viewHolder.dictionaryitem_textview = (TextView) convertView.findViewById(R.id.dictionaryitem_textview);
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.dictionaryitem_textview.setText(dictionaryItems.get(position).getDicName());
        return convertView;
    }
    public class ViewHolder
    {
        TextView dictionaryitem_textview;
    }

}
