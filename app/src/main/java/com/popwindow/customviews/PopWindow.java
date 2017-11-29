package com.popwindow.customviews;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;


import com.popwindow.R;
import com.popwindow.adapter.PopMenuItemsAdapter;
import com.popwindow.bean.PopDataBean;
import com.popwindow.util.LUtils;

import java.util.ArrayList;

/**
 * Created by nova on 2017/11/26.
 */

public class PopWindow extends PopupWindow implements AdapterView.OnItemClickListener {
    // 根视图
    private View mRootView;
    private LayoutInflater mInflater;

    // ArrayList数组，listview原始数据
    private ArrayList<PopDataBean> mArrayList=new ArrayList<>();

    // 数据接口
    OnGetData mOnGetData;

    // listview适配器
    PopMenuItemsAdapter groupAdapter;
    ListView listItemsView=null;
    private int mnSeclectItem = -1;

    public PopWindow(Activity context) {
        super(context);

        initData(context);
        initUI();
    }


    // 数据接口抽象方法
    public interface OnGetData {
         // 赋值
        abstract ArrayList<PopDataBean> onArrayList();

        //获取被选中的值
        abstract int onSeclectItem();

        //外放点击事件
        abstract void onDataCallBack(int nSectlect, ArrayList<PopDataBean> mArrayList);
    }


    // 数据接口设置,数据源接口传入
    public void setOnData(OnGetData sd) {
        mOnGetData = sd;
        mArrayList = new ArrayList<PopDataBean>();
        if (mOnGetData != null) {
            //执行赋值操作
            mArrayList = mOnGetData.onArrayList();
            //获取上次被选中的值
            mnSeclectItem = mOnGetData.onSeclectItem();
            //给listview设置数值
            groupAdapter.setDatas(mArrayList);
            //给listview设置被选中内容
            groupAdapter.setSelected(mnSeclectItem);
            //更新listview数据展示
            groupAdapter.notifyDataSetChanged();
        }
    }

    private void initData(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = mInflater.inflate(R.layout.popuplayout_items, null);
        setContentView(mRootView);

        this.setWidth((int) (3 * LUtils.getWidthPixels(context) / 4));
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置动画
        setAnimationStyle(R.style.popup_window_anim);
        // 设置PopUpWindow弹出的相关属性
        setTouchable(true);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable(context.getResources()));
        update();

        getContentView().setFocusableInTouchMode(true);
        getContentView().setFocusable(true);
    }

    private void initUI() {
        listItemsView = (ListView) mRootView.findViewById(R.id.listItems);
        groupAdapter = new PopMenuItemsAdapter(mRootView.getContext());
        listItemsView.setAdapter(groupAdapter);
        listItemsView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mOnGetData.onDataCallBack(position, mArrayList);
        dismiss();
    }
}
