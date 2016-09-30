package com.hkd.lianmeng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.model.Goods;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by johe on 2016/9/19.
 * gqf
 * 义卖界面商品显示listadapter
 */
public class SaleListAdapter extends BaseAdapter {
    private ArrayList<Goods> datas;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ViewHolder mHolder;

    public SaleListAdapter(Context context, ArrayList<Goods> datas) {
        this.mContext = context;
        this.datas = datas;
        this.layoutInflater = LayoutInflater.from(context);

    }

    public void update(ArrayList<Goods> datas) {
        this.datas = datas;
        this.notifyDataSetChanged();
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

            arg1 = layoutInflater.inflate(R.layout.sale_list_item,
                    null);// inflate(context,
            // R.layout.list_item,
            // null);
            mHolder = new ViewHolder(arg1);
            arg1.setTag(mHolder);

        } else {
            mHolder = (ViewHolder) arg1.getTag();
        }
        mHolder.itemsName.setText(datas.get(arg0).getGoodsname());
        mHolder.itemsClassification.setText(datas.get(arg0).getGoodsclassification() + "/" + datas.get(arg0).getGoodsspecies());

        mHolder.itemsMaster.setText("联系人" + datas.get(arg0).getPhone());
        mHolder.itemsPhonenumber.setText("联系电话:" + datas.get(arg0).getUserphonenum());
        mHolder.saleListItemImg.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_launcher));

        return arg1;
    }


    static class ViewHolder {
        @Bind(R.id.sale_list_item_img)
        ImageView saleListItemImg;
        @Bind(R.id.items_name)
        TextView itemsName;
        @Bind(R.id.items_classification)
        TextView itemsClassification;
        @Bind(R.id.items_master)
        TextView itemsMaster;
        @Bind(R.id.items_phonenumber)
        TextView itemsPhonenumber;
        @Bind(R.id.button)
        CheckBox button;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
