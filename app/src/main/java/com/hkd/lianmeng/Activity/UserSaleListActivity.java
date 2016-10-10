package com.hkd.lianmeng.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hkd.lianmeng.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by johe on 2016/10/9.
 */

public class UserSaleListActivity extends Activity {


    @Bind(R.id.User_salelist_top_back_btn)
    Button UserSalelistTopBackBtn;
    @Bind(R.id.User_salelist_top_add_btn)
    Button UserSalelistTopAddBtn;
    @Bind(R.id.User_salelist_top_delete_btn)
    Button UserSalelistTopDeleteBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sale_list);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.User_salelist_top_back_btn, R.id.User_salelist_top_add_btn, R.id.User_salelist_top_delete_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.User_salelist_top_back_btn:
                finish();
                break;
            case R.id.User_salelist_top_add_btn:
                //跳转页面，增加新的商品
                startActivity(new Intent(UserSaleListActivity.this, AddUserGoodsActivity.class));

                break;
            case R.id.User_salelist_top_delete_btn:
                //删除当前选中商品


                break;
        }
    }
}
