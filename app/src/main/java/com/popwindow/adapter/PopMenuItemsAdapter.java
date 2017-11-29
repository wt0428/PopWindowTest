package com.popwindow.adapter;

/**
 * Created by nova on 2017/11/19.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.popwindow.R;
import com.popwindow.bean.PopDataBean;

import java.util.List;

/**
 * 处理结果查询Adapter
 */
public class PopMenuItemsAdapter extends BaseAdapter {

    private Context context;

    private List<PopDataBean> list;
    /**
     * 设置被选中结果
     */
    private int selectedId=-1;

    public PopMenuItemsAdapter(Context context) {

        this.context = context;


    }

    /**
     * 设置数据
     * @param list
     */
    public void setDatas(List<PopDataBean> list) {
        this.list = list;
    }

    /**
     * 设置选中
     * @param selectedItem
     */
    public void setSelected(int selectedItem){
        selectedId=selectedItem;
    }
    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.pop_menu_item, null);
            holder = new ViewHolder();

            convertView.setTag(holder);
            holder.groupItem = (TextView) convertView.findViewById(R.id.groupItem);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(selectedId!=-1){
            if(selectedId==position){
                holder.groupItem.setTextColor(context.getResources().getColor(R.color.blue));
            }else{
                holder.groupItem.setTextColor(context.getResources().getColor(R.color.public_text_black_color));
            }
        }else{
            holder.groupItem.setTextColor(context.getResources().getColor(R.color.public_text_black_color));
        }
        holder.groupItem.setText(list.get(position).getName());

        return convertView;
    }


    static class ViewHolder {
        TextView groupItem;
    }

}

