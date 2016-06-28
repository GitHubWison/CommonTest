package com.example.commonlibrary.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.commonlibrary.R;
import com.example.commonlibrary.dialog.adapter.CommonSpinnerAdapter;
import com.example.commonlibrary.model.DictionaryItem;
import com.example.commonlibrary.tools.Tool;
import com.github.promeg.pinyinhelper.Pinyin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuqiwei on 16-6-27.
 */
public class CommonSinnerPopWindow extends PopupWindow {
    private Context context;
    private View contentView;
//    private View parentView;
    private CommonSinnerPopWindow commonSinnerPopWindow;
    private ListView commonspinner_listview;
    private List<DictionaryItem>dictionaryItems;
    private List<DictionaryItem>findDictionaryitems;
    private CommonSinnerListViewListener commonSinnerListViewListener;
    public CommonSinnerPopWindow(final Context context,List<DictionaryItem>dictionaryItems) {
        super(context);
        this.context = context;
        this.dictionaryItems = dictionaryItems;
//        this.parentView = parentView;
        commonSinnerPopWindow = this;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.pop_layout, null);
        commonSinnerPopWindow.setContentView(contentView);

        commonSinnerPopWindow.setWidth(800);
        commonSinnerPopWindow.setHeight(200);
        commonSinnerPopWindow.setFocusable(false);
        commonSinnerPopWindow.setOutsideTouchable(true);
        initDatas();
        initViews();
        initEvents();
        ColorDrawable colorDrawable = new ColorDrawable(0000000000);
        this.setBackgroundDrawable(colorDrawable);



    }

    private void initEvents() {
        commonspinner_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (commonSinnerListViewListener!=null)
                {
                    commonSinnerListViewListener.onListItemClicked(findDictionaryitems.get(i));
                }
            }
        });
    }

    private void initDatas() {

    }

    private void initViews() {

        commonspinner_listview = (ListView) contentView.findViewById(R.id.commonspinner_listview);

        setDrugList(dictionaryItems);

    }

    public CommonSinnerListViewListener getCommonSinnerListViewListener() {
        return commonSinnerListViewListener;
    }

    public void setCommonSinnerListViewListener(CommonSinnerListViewListener commonSinnerListViewListener) {
        this.commonSinnerListViewListener = commonSinnerListViewListener;
    }

    private void setDrugList(List<DictionaryItem> Items)
    {
        CommonSpinnerAdapter commonSpinnerAdapter = new CommonSpinnerAdapter(Items,context);
        commonspinner_listview.setAdapter(commonSpinnerAdapter);
        int height = ((int)context.getResources().getDimension(R.dimen.input_title_height))*Items.size();
        int size = Items.size();
        Tool.printDebugLog(context,size+"",""+height);
        commonSinnerPopWindow.setHeight(LayoutParams.WRAP_CONTENT);
        commonSinnerPopWindow.setWidth(LayoutParams.WRAP_CONTENT);
        commonSinnerPopWindow.update();
//        commonSinnerPopWindow.showPopupWindow();
    }
    public void findItemByPy(String pinYin)
    {
       findDictionaryitems = new ArrayList<>();
        for (int i=0;i<dictionaryItems.size();i++)
        {
            DictionaryItem tempDic = dictionaryItems.get(i);
            char[] drugsName = tempDic.getDicName().toCharArray();
            String drugPinYin = "";
            for (int j=0;j<drugsName.length;j++)
            {
                drugPinYin+=Pinyin.toPinyin(drugsName[j]).substring(0,1);
            }
            if (drugPinYin.contains(pinYin.toUpperCase()))
            {
                findDictionaryitems.add(tempDic);
            }

        }
        setDrugList(findDictionaryitems);

    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
//            parent.getLayoutParams().width / 2
            this.showAsDropDown(parent, 0, 0);
        } else {
            this.dismiss();
        }
    }
    public interface CommonSinnerListViewListener
    {
        void onListItemClicked(DictionaryItem dictionaryItem);
    }
}