package com.hkd.lianmeng.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hkd.lianmeng.R;
import com.hkd.lianmeng.adapter.AddGoodsChooseListAdapter;
import com.hkd.lianmeng.tools.ReadJson;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hkd.lianmeng.R.id.popu_RadioGroup;
import static com.hkd.lianmeng.R.id.top_view_rollline;

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
    //    @Bind(R.id.addgoods_addBtn_txt)
    //    TextView addgoodsAddBtnTxt;
    @Bind(R.id.addgoods_goodsimg_img2)
    ImageView addgoodsGoodsimgImg2;
    @Bind(R.id.addgoods_goodsimg_img3)
    ImageView addgoodsGoodsimgImg3;

    Bitmap addgoodsGoodsimg1;
    Bitmap addgoodsGoodsimg2;
    Bitmap addgoodsGoodsimg3;

    String goodsname;
    String goodstxt;
    String goodsphonenum;
    ArrayList<String> ListDatas;
    ArrayList<String> DialogDatas;
    AddGoodsChooseListAdapter mAddGoodsChooseListAdapter;
    ReadJson rj;
    @Bind(R.id.addgoods_goodsimg_img1)
    ImageView addgoodsGoodsimgImg1;
    @Bind(R.id.addgoods_goodsaddress_lin)
    LinearLayout addgoodsGoodsaddressLin;
    @Bind(R.id.addgoods_goodsclassification_lin)
    LinearLayout addgoodsGoodsclassificationLin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_goods);
        ButterKnife.bind(this);
        mContext=this;
    }

    private void initEdi() {

    }
    public ArrayList<String> readJson(int position) {
        String json = null;
        ArrayList<String> data = new ArrayList<String>();
        if (position == 0 || position == 1) {
            json = this.getString(R.string.city_json);
        } else if (position == 2) {
            json = this.getString(R.string.university_json);
        }
        rj = ReadJson.getInstance();
        if (position == 0 || position == 2) {
            for (int i = 0; i < rj.readSaleTopChooseJson(json).size(); i++) {
                data.add(rj.readSaleTopChooseJson(json).get(i).getName());
            }
        } else {
            data = rj.readSaleTopChooseJson(json).get(17).getHave();
        }

        return data;
    }

    @OnClick({R.id.User_addgoods_top_back_btn, R.id.addgoods_goodsaddress_lin, R.id.addgoods_goodsclassification_lin, R.id.addgoods_goodsname_edi, R.id.addgoods_addImg_txt, R.id.addgoods_deleteimg_txt, R.id.addgoods_goodstxt_edi, R.id.addgoods_phonenumber_edi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.User_addgoods_top_back_btn:
                finish();
                break;
            case R.id.addgoods_addImg_txt:
                //添加图片
                if (imgIndex < 3) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_CODE);
                } else {
                    Toast.makeText(AddUserGoodsActivity.this, "最多上传三张图片", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.addgoods_deleteimg_txt:
                //删除图片

                break;
            case R.id.addgoods_goodsaddress_lin:
                //选择地址
                showPopwindow();
                updateAddressPopuList();
                break;
            case R.id.addgoods_goodsclassification_lin:
                //选择分类

                break;
        }
    }
    int AddressPopuListIndex=0;
    int chooseIndes=0;
    public void updateAddressPopuList(){
        mAddGoodsChooseListAdapter=new AddGoodsChooseListAdapter(this,readJson(chooseIndes));
        popupList.setAdapter(mAddGoodsChooseListAdapter);
        popupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(chooseIndes==0){
                    radioProvince.setText(mAddGoodsChooseListAdapter.getItem(position).toString());
                    chooseIndes=1;
                }else if(chooseIndes==1){
                    radioCity.setText(mAddGoodsChooseListAdapter.getItem(position).toString());
                    chooseIndes=2;
                }else if(chooseIndes==2){
                    radioDistrict.setText(mAddGoodsChooseListAdapter.getItem(position).toString());
                    chooseIndes=3;
                }else if(chooseIndes==3){
                    chooseIndes=4;

                }
                changeRid(mAddGoodsChooseListAdapter.getItem(position).toString());
                mAddGoodsChooseListAdapter.update(readJson(chooseIndes));
            }
        });
    }
    public ArrayList<String> getHttpChoose(){

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

        return null;
    }
    public void changeRid(String choose){
        RadioButton r;
        //点击后读取点击项地址信息
        r=(RadioButton) popuRadioGroup.getChildAt(chooseIndes-1);
        r.setTextColor(this.getResources().getColor(R.color.black));
        r.setText(choose);
        //设置第二个button可点击
        popuRadioGroup.getChildAt(chooseIndes).setClickable(true);
        popuRadioGroup.getChildAt(chooseIndes).setVisibility(View.VISIBLE);
        r=(RadioButton) popuRadioGroup.getChildAt(chooseIndes);
        r.setText("请选择");
        r.setTextColor(this.getResources().getColor(R.color.blueColor));
        initrollview(r.getWidth());
        for(int m=chooseIndes+1;m<popuRadioGroup.getChildCount();m++){
            popuRadioGroup.getChildAt(m).setVisibility(View.INVISIBLE);
        }
    }
    public void initrollview(int w) {
        RelativeLayout.LayoutParams layoutParams;
        layoutParams = (LayoutParams) topViewRollline.getLayoutParams();
        layoutParams.width=w;
        layoutParams.leftMargin=chooseIndes*60+chooseIndes*w;
        topViewRollline.setLayoutParams(layoutParams);
    }
    RadioButton radioProvince;
    RadioButton radioCity;
    RadioButton radioDistrict;
    RadioButton radioCompus;
    RadioGroup popuRadioGroup;
    TextView topViewRollline;
    ListView popupList;
    Context mContext;
    /**
     * 显示popupWindow
     */
    PopupWindow window;
    public void changeRadColor(){
        RadioButton r;
        for(int i=0;i<4;i++){
            r = (RadioButton) popuRadioGroup.getChildAt(i);
            if(i==chooseIndes) {
                r.setTextColor(mContext.getResources().getColor(R.color.blueColor));
            }else{
                r.setTextColor(mContext.getResources().getColor(R.color.black));
            }
            initrollview(r.getWidth());
        }
    }
    private void showPopwindow() {

        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popwindow, null);
        popupList=(ListView)view.findViewById(R.id.popup_list);
        topViewRollline=(TextView)view.findViewById(top_view_rollline);
        popuRadioGroup=(RadioGroup)view.findViewById(popu_RadioGroup);
        radioCompus=(RadioButton) view.findViewById(R.id.radio_compus);
        radioDistrict=(RadioButton) view.findViewById(R.id.radio_district);
        radioCity=(RadioButton) view.findViewById(R.id.radio_city);
        radioProvince=(RadioButton) view.findViewById(R.id.radio_province);
        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        radioProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseIndes=0;
                mAddGoodsChooseListAdapter.update(readJson(chooseIndes));
                changeRadColor();
            }
        });
        radioCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseIndes=1;
                mAddGoodsChooseListAdapter.update(readJson(chooseIndes));
                changeRadColor();
            }
        });
        radioDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseIndes=2;
                mAddGoodsChooseListAdapter.update(readJson(chooseIndes));
                changeRadColor();
            }
        });
        radioCompus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddGoodsChooseListAdapter.update(readJson(chooseIndes));
            }
        });
        window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);

        // 必须要给调用这个方法，否则点击popWindow以外部分，popWindow不会消失
        window.setBackgroundDrawable(new BitmapDrawable());

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);

        // 在参照的View控件下方显示
        // window.showAsDropDown(MainActivity.this.findViewById(R.id.start));
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(AddUserGoodsActivity.this.findViewById(R.id.User_allLin_lin),
                Gravity.BOTTOM, 0, 0);

        // popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
                AddressPopuListIndex=0;
                chooseIndes=0;
            }
        });
    }

    private static final int PICK_CODE = 0;
    private static final int CAMERA_CODE = 1;
    private String currentphoto;
    private Bitmap mPhotoimg = null;
    private Paint mPaint;
    private File mPhotoFile;
    private String mPhotoPath;
    private int imgIndex = 0;

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent intent) {
        // TODO Auto-generated method stub
        if (arg0 == PICK_CODE) {
            if (intent != null) {
                Uri uri = intent.getData();
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                currentphoto = cursor.getString(idx);
                cursor.close();
                reSizephoto();
            }
        }
        if (arg0 == CAMERA_CODE) {

            BitmapFactory.Options op = new BitmapFactory.Options();
            op.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(mPhotoPath, op);
            op.inSampleSize = Math.max(1, (int) Math.ceil(Math.max((double) op.outWidth / 1024f, (double) op.outHeight / 1024f)));
            op.inJustDecodeBounds = false;
            mPhotoimg = BitmapFactory.decodeFile(mPhotoPath, op);
        }
        if (mPhotoimg != null) {
            if (imgIndex == 0) {
                addgoodsGoodsimgImg1.setImageBitmap(mPhotoimg);
                addgoodsGoodsimg1 = mPhotoimg;
                imgIndex = 1;
            } else if (imgIndex == 1) {
                addgoodsGoodsimgImg2.setImageBitmap(mPhotoimg);
                addgoodsGoodsimg2 = mPhotoimg;
                imgIndex = 2;
            } else if (imgIndex == 2) {
                addgoodsGoodsimgImg3.setImageBitmap(mPhotoimg);
                addgoodsGoodsimg3 = mPhotoimg;
                imgIndex = 3;
            }
            mPhotoimg = null;
        }

        super.onActivityResult(arg0, arg1, intent);
    }

    private void reSizephoto() {
        // TODO Auto-generated method stub
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(currentphoto, op);
        op.inSampleSize = Math.max(1, (int) Math.ceil(Math.max((double) op.outWidth / 1024f, (double) op.outHeight / 1024f)));
        op.inJustDecodeBounds = false;
        mPhotoimg = BitmapFactory.decodeFile(currentphoto, op);
    }


}













