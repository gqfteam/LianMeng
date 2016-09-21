package com.hkd.lianmeng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.model.User;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by johe on 2016/9/21.
 */
public class FriendsListAdapter extends BaseAdapter {
    private ArrayList<User> datas;//数据源
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ViewHolder mHolder;


    public void update(ArrayList<User> datas) {
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    public FriendsListAdapter(Context context, ArrayList<User> datas) {
        this.mContext = context;
        this.datas = datas;
        this.layoutInflater = LayoutInflater.from(context);

    }

    public int getCount() {
        if (datas == null) {
            return 0;
        }
        return datas.size();
    }

    public Object getItem(int arg0) {
        return datas.get(arg0);
    }

    public long getItemId(int arg0) {
        return arg0;
    }


    public View getView(int arg0, View arg1, ViewGroup arg2) {

        if (arg1 == null) {

            arg1 = layoutInflater.inflate(R.layout.friends_list_item,
                    null);// inflate(context,
            // R.layout.list_item,
            // null);
            mHolder = new ViewHolder(arg1);
            arg1.setTag(mHolder);

        } else {
            mHolder = (ViewHolder) arg1.getTag();
        }

        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(arg0);

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if(arg0 == getPositionForSection(section)){
            mHolder.friendsListItemChart.setVisibility(View.VISIBLE);
            mHolder.friendsListItemChart.setText(datas.get(arg0).getSortLetters());
        }else{
            mHolder.friendsListItemChart.setVisibility(View.GONE);
        }

        mHolder.friendsListItemName.setText(datas.get(arg0).getUserName());
        return arg1;
    }


    static class ViewHolder {
        @Bind(R.id.friends_list_item_chart)
        TextView friendsListItemChart;
        @Bind(R.id.friends_list_item_img)
        ImageView friendsListItemImg;
        @Bind(R.id.friends_list_item_name)
        TextView friendsListItemName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return datas.get(position).getSortLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = datas.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 提取英文的首字母，非英文字母用#代替。
     *
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        String  sortStr = str.trim().substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    public Object[] getSections() {
        return null;
    }
}
