package com.example.xuqiwei.commontestproject.tasklist.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.databinding.library.baseAdapters.BR;
import com.example.xuqiwei.commontestproject.R;
import com.example.xuqiwei.commontestproject.databinding.ItemTasklistBinding;
import com.example.xuqiwei.commontestproject.tasklist.models.PatientModel;

import java.util.List;

/**
 * Created by xuqiwei on 16-6-23.
 */
public class TaskListAdapter extends BaseAdapter{
    private List<PatientModel> patients;
    private Context context;
    private ItemTasklistBinding itemTasklistBinding;

    public TaskListAdapter(List<PatientModel> patients, Context context) {
        this.patients = patients;
        this.context = context;
    }

    @Override
    public int getCount() {
        return patients.size();
    }

    @Override
    public Object getItem(int i) {
        return patients.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            itemTasklistBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_tasklist, parent, false);
            convertView = itemTasklistBinding.getRoot();
            convertView.setTag(itemTasklistBinding);
        }else
        {
            itemTasklistBinding = (ItemTasklistBinding) convertView.getTag();
        }
        itemTasklistBinding.setVariable(BR.patient, patients.get(position));
        itemTasklistBinding.setAdapter(this);
        return convertView;
    }
}
