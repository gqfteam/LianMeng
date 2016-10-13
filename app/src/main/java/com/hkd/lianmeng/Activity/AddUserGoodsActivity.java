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
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hkd.lianmeng.R;
import com.hkd.lianmeng.adapter.AddGoodsChooseListAdapter;
import com.hkd.lianmeng.base.BaseApplication;
import com.hkd.lianmeng.tools.ReadJson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
    @Bind(R.id.addgoods_goodsaddress_txt)
    TextView addgoodsGoodsaddressTxt;
    @Bind(R.id.addgoods_goodsclassification_txt)
    TextView addgoodsGoodsclassificationTxt;
    @Bind(R.id.addgoods_goodsprice_edi)
    EditText addgoodsGoodspriceEdi;

    String goodsname;
    String goodsdetails;
    String userphonenum;
    String phone = "15670702651";
    String goodsprice;
    long shelvestime;
    String goodscity;

    String college;
    String campus;
    String classification;
    String species;
    @Bind(R.id.add_goods_txt)
    TextView addGoodsTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_goods);
        ButterKnife.bind(this);
        mContext = this;
        initEdi();
    }

    private void initEdi() {
        addgoodsGoodspriceEdi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                goodsprice=addgoodsGoodspriceEdi.getText().toString();
                detection();
            }
        });
        addgoodsPhonenumberEdi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                userphonenum=addgoodsPhonenumberEdi.getText().toString();
                detection();
            }
        });
        addgoodsGoodsnameEdi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                goodsname=addgoodsGoodsnameEdi.getText().toString();
                detection();
            }
        });
        addgoodsGoodstxtEdi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                goodsdetails=addgoodsGoodstxtEdi.getText().toString();
                detection();
            }
        });
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

    @OnClick({R.id.add_goods_txt, R.id.User_addgoods_top_back_btn, R.id.addgoods_goodsaddress_lin, R.id.addgoods_goodsclassification_lin, R.id.addgoods_goodsname_edi, R.id.addgoods_addImg_txt, R.id.addgoods_deleteimg_txt, R.id.addgoods_goodstxt_edi, R.id.addgoods_phonenumber_edi})
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
                showPopwindow();
                updateClassificationPopuList();
                break;
            case R.id.add_goods_txt:

                if(detection()) {
                    Log.i("gqf","上架中");
                    addGoodsToServer(phone,goodsname,goodsdetails,userphonenum,Double.parseDouble(goodsprice),goodscity,college,campus,classification,species);
                }else{
                    Toast.makeText(this, "请完善您的商品信息", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    String goodsJson;
    public void addGoodsToServer(String phone,String goodsname,String goodsdetails,String userphonenum,Double goodsprice,String goodscity,String collegeName,String campusname,String goodsclassification,String speciesname) {
        //{"goodsname":"笔记本","goodsdetails":"gqf","userphonenum":"15670702651","goodsprice":"4000","goodscity":"洛阳市","collegeName":"河南科技大学","campusname":"西苑校区","goodsclassification":"电子产品","speciesname":"电脑"}
        final String url =  BaseApplication.HTTPCLIENTADDRESS+"goodsinfo_addGoodsInfo?params={%22phone%22:" +
                "" +"%22"+ "15670702651" +"%22"+","+"%22goodsname%22:" +
        "" +"%22"+ goodsname +"%22"+","+"%22goodsdetails%22:" +
                "" +"%22"+ goodsdetails +"%22"+","+"%22userphonenum%22:" +
                "" +"%22"+ userphonenum +"%22"+","+"%22goodsprice%22:" +
                "" +"%22"+goodsprice+"%22"+","+"%22goodscity%22:" +
                "" +"%22"+ goodscity +"%22"+","+"%22collegeName%22:" +
                "" +"%22"+ collegeName +"%22"+","+"%22campusname%22:" +
                "" +"%22"+ campusname +"%22"+","+"%22goodsclassification%22:" +
                "" +"%22"+ goodsclassification +"%22"+","+"%22speciesname%22:" +
                "" +"%22"+ speciesname +"%22"+
                "}";
        Log.i("gqf",url);

        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    goodsJson=post(url,"");
                    Log.i("gqf",goodsJson);
                    Message message = new Message();
                    message.what = 3;
                    message.obj=goodsJson;
                    myHandler.sendMessage(message);
                }catch (IOException e){
                    Log.i("gqf",e.getMessage().toString());
                }
            }
        });
        t.start();

    }
    int chooseIndes = 0;

    public void updateClassificationPopuList() {
        radioProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseIndes = 0;
                getJson("", 1);
                changeRadColor();
            }
        });
        radioCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseIndes = 1;
                getJson(radioProvince.getText().toString(), 2);
                changeRadColor();
            }
        });
        getJson("", 1);
    }

    public void updateAddressPopuList() {
        radioProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseIndes = 0;
                mAddGoodsChooseListAdapter.update(readJson(chooseIndes));
                changeRadColor();
            }
        });
        radioCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseIndes = 1;
                mAddGoodsChooseListAdapter.update(readJson(chooseIndes));
                changeRadColor();
            }
        });
        radioDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseIndes = 2;
                mAddGoodsChooseListAdapter.update(readJson(chooseIndes));
                changeRadColor();
            }
        });
        radioCompus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseIndes = 3;
                getJson(radioDistrict.getText().toString(), 0);
                changeRadColor();
            }
        });
        mAddGoodsChooseListAdapter = new AddGoodsChooseListAdapter(this, readJson(chooseIndes));
        popupList.setAdapter(mAddGoodsChooseListAdapter);
        popupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (chooseIndes == 0) {
                    radioProvince.setText(mAddGoodsChooseListAdapter.getItem(position).toString());
                    chooseIndes = 1;

                } else if (chooseIndes == 1) {
                    radioCity.setText(mAddGoodsChooseListAdapter.getItem(position).toString());
                    chooseIndes = 2;
                } else if (chooseIndes == 2) {
                    radioDistrict.setText(mAddGoodsChooseListAdapter.getItem(position).toString());
                    chooseIndes = 3;
                    getJson(mAddGoodsChooseListAdapter.getItem(position).toString(), 0);
                }
                if (chooseIndes < 3) {
                    changeRid(mAddGoodsChooseListAdapter.getItem(position).toString());
                    mAddGoodsChooseListAdapter.update(readJson(chooseIndes));
                } else if (chooseIndes == 4) {
                    chooseIndes = 0;
                    addgoodsGoodsaddressTxt.setText(radioProvince.getText() + " " + radioCity.getText() +
                            " " + radioDistrict.getText() + " " + mAddGoodsChooseListAdapter.getItem(position).toString());
                    goodscity = radioCity.getText().toString();
                    college = radioDistrict.getText().toString();
                    campus = mAddGoodsChooseListAdapter.getItem(position).toString();
                    detection();
                    window.dismiss();
                } else if (chooseIndes == 3) {
                    chooseIndes = 4;
                }
            }
        });
    }

    private boolean detection() {
        if (goodscity != null && college != null && campus != null && classification != null && species != null && goodsname != null && goodsprice != null && userphonenum != null) {
            //提交按钮可用
            Log.i("gqf","可以上架");
            addGoodsTxt.setTextColor(this.getResources().getColor(R.color.blueColor));
            return true;
        } else {
            addGoodsTxt.setTextColor(this.getResources().getColor(R.color.darkgray));
            return false;
        }
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String json = msg.obj.toString();
                    rj = ReadJson.getInstance();
                    ArrayList<String> campusdate = new ArrayList<>();
                    for (int i = 0; i < rj.readCampusJson(json).size(); i++) {
                        campusdate.add(rj.readCampusJson(json).get(i).getCampusname());
                    }
                    mAddGoodsChooseListAdapter.update(campusdate);
                    break;
                case 1:
                    //解析json
                    String classificationjson = msg.obj.toString();
                    rj = ReadJson.getInstance();
                    ArrayList<String> classificationdate = new ArrayList<>();
                    for (int i = 0; i < rj.readclassificationJson(classificationjson).size(); i++) {
                        classificationdate.add(rj.readclassificationJson(classificationjson).get(i).getGoodsclassification());
                    }
                    //初始化list

                    mAddGoodsChooseListAdapter = new AddGoodsChooseListAdapter(mContext, classificationdate);
                    popupList.setAdapter(mAddGoodsChooseListAdapter);
                    popupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if (chooseIndes == 0) {
                                chooseIndes = 1;
                                changeRid(mAddGoodsChooseListAdapter.getItem(position).toString());
                                getJson(mAddGoodsChooseListAdapter.getItem(position).toString(), 2);
                            } else if (chooseIndes == 1) {
                                chooseIndes = 0;
                                addgoodsGoodsclassificationTxt.setText("商品分类:" + radioProvince.getText() + "/" + mAddGoodsChooseListAdapter.getItem(position).toString());
                                classification = radioProvince.getText().toString();
                                species = mAddGoodsChooseListAdapter.getItem(position).toString();
                                detection();
                                window.dismiss();
                            }
                        }
                    });


                    break;
                case 2:
                    //解析json
                    String sprcisjson = msg.obj.toString();
                    rj = ReadJson.getInstance();
                    ArrayList<String> specisdate = new ArrayList<>();
                    for (int i = 0; i < rj.readSpecisJson(sprcisjson).size(); i++) {
                        specisdate.add(rj.readSpecisJson(sprcisjson).get(i).getSpeciesname());
                    }
                    mAddGoodsChooseListAdapter.update(specisdate);
                    break;

                case 3:
                    //获得服务端返回结果
                    if(msg.obj.toString().contains("false")||msg.obj.toString().contains("Failed")){
                        Toast.makeText(mContext,"网络问题，查询数据失败！",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    Log.i("gqf",msg.obj.toString());
                    finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    String Searchurl;

    public void getJson(String name, final int index) {

        if (index == 0) {
            Searchurl = BaseApplication.HTTPCLIENTADDRESS + "goodsinfo_getCampusByCollege?params=" + name + "";
        } else if (index == 1) {
            Searchurl = BaseApplication.HTTPCLIENTADDRESS + "goodsinfo_getAllClassification";
        } else if (index == 2) {
            Searchurl = BaseApplication.HTTPCLIENTADDRESS + "goodsinfo_getSpeciesByClassification?params=" + name;
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = post(Searchurl, "");
                    Log.i("gqf", json);
                    Message message = new Message();
                    message.what = index;
                    message.obj = json;
                    myHandler.sendMessage(message);
                } catch (IOException e) {
                    Log.i("gqf", e.getMessage().toString());
                }
            }
        });
        t.start();

    }

    OkHttpClient client;

    String post(String url, String json) throws IOException {
        client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                //.url("http://192.168.1.136:8080/MFace/userinfo_getUserInfo?params={%22phone%22:%2218860316546%22,%22passWord%22:%22123456%22}")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public void changeRid(String choose) {
        RadioButton r;
        //点击后读取点击项地址信息
        r = (RadioButton) popuRadioGroup.getChildAt(chooseIndes - 1);
        r.setTextColor(this.getResources().getColor(R.color.black));
        r.setText(choose);

        if (chooseIndes < 4) {
            //设置第二个button可点击
            popuRadioGroup.getChildAt(chooseIndes).setClickable(true);
            popuRadioGroup.getChildAt(chooseIndes).setVisibility(View.VISIBLE);
            r = (RadioButton) popuRadioGroup.getChildAt(chooseIndes);
            r.setText("请选择");
            r.setTextColor(this.getResources().getColor(R.color.blueColor));
            initrollview(r.getWidth());
            for (int m = chooseIndes + 1; m < popuRadioGroup.getChildCount(); m++) {
                popuRadioGroup.getChildAt(m).setVisibility(View.INVISIBLE);
            }
        }

    }

    public void initrollview(int w) {
        LayoutParams layoutParams;
        layoutParams = (LayoutParams) topViewRollline.getLayoutParams();
        layoutParams.width = w;
        layoutParams.leftMargin = chooseIndes * 60 + chooseIndes * w;
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

    public void changeRadColor() {
        RadioButton r;
        for (int i = 0; i < 4; i++) {
            r = (RadioButton) popuRadioGroup.getChildAt(i);
            if (i == chooseIndes) {
                r.setTextColor(mContext.getResources().getColor(R.color.blueColor));
            } else {
                r.setTextColor(mContext.getResources().getColor(R.color.black));
            }
            initrollview(r.getWidth());
        }
    }

    private void showPopwindow() {

        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popwindow, null);
        popupList = (ListView) view.findViewById(R.id.popup_list);
        topViewRollline = (TextView) view.findViewById(top_view_rollline);
        popuRadioGroup = (RadioGroup) view.findViewById(popu_RadioGroup);
        radioCompus = (RadioButton) view.findViewById(R.id.radio_compus);
        radioDistrict = (RadioButton) view.findViewById(R.id.radio_district);
        radioCity = (RadioButton) view.findViewById(R.id.radio_city);
        radioProvince = (RadioButton) view.findViewById(R.id.radio_province);
        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

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
                mAddGoodsChooseListAdapter = null;
                chooseIndes = 0;
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













