package com.hkd.lianmeng.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hkd.lianmeng.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddUserGoodsActivity extends Activity {

    @Bind(R.id.User_addgoods_top_back_btn)
    Button UserAddgoodsTopBackBtn;
    @Bind(R.id.User_addgoods_top_title_txt)
    TextView UserAddgoodsTopTitleTxt;
    @Bind(R.id.addgoods_goodsname_edi)
    EditText addgoodsGoodsnameEdi;
    @Bind(R.id.addgoods_addImg_txt)
    TextView addgoodsAddImgTxt;
    @Bind(R.id.addgoods_deleteimg_txt)
    TextView addgoodsDeleteimgTxt;
    @Bind(R.id.addgoods_goodstxt_edi)
    EditText addgoodsGoodstxtEdi;
    @Bind(R.id.addgoods_phonenumber_edi)
    EditText addgoodsPhonenumberEdi;
    @Bind(R.id.User_addgoods_ScrollView)
    ScrollView UserAddgoodsScrollView;
    @Bind(R.id.addgoods_addBtn_txt)
    TextView addgoodsAddBtnTxt;
    @Bind(R.id.addgoods_goodsimg_img1)
    ImageView addgoodsGoodsimgImg1;
    @Bind(R.id.addgoods_goodsimg_img2)
    ImageView addgoodsGoodsimgImg2;
    @Bind(R.id.addgoods_goodsimg_img3)
    ImageView addgoodsGoodsimgImg3;

    String goodsname;
    String goodstxt;
    String goodsphonenum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_goods);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.User_addgoods_top_back_btn, R.id.addgoods_goodsname_edi, R.id.addgoods_addImg_txt, R.id.addgoods_deleteimg_txt, R.id.addgoods_goodstxt_edi, R.id.addgoods_phonenumber_edi, R.id.addgoods_addBtn_txt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.User_addgoods_top_back_btn:
                finish();
                break;
            case R.id.addgoods_goodsname_edi:
                //商品名称

                break;
            case R.id.addgoods_addImg_txt:
                //添加图片

                break;
            case R.id.addgoods_deleteimg_txt:
                //删除图片

                break;
            case R.id.addgoods_goodstxt_edi:
                //商品详情

                break;
            case R.id.addgoods_phonenumber_edi:
                //电话号码

                break;
            case R.id.addgoods_addBtn_txt:
                //商品上架

                break;
        }
    }
}
