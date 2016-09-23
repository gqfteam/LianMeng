package com.hkd.lianmeng.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.adapter.InitialSigninShowClassAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class InitiateSigninActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<HashMap<String,String>> classList;
    InitialSigninShowClassAdapter showClassAdapter;
    ListView showclass_listview;
    TextView back_textview,cancel_btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiate_signin);
        classList=new ArrayList<HashMap<String,String>>();
        HashMap<String,String> map=new HashMap<String,String>();
        map.put("年级","软工131班");
        map.put("人数","32人");
        classList.add(map);

        map=new HashMap<String,String>();
        map.put("年级","软工132班");
        map.put("人数","35人");
        classList.add(map);

        map=new HashMap<String,String>();
        map.put("年级","软工133班");
        map.put("人数","33人");
        classList.add(map);

        map=new HashMap<String,String>();
        map.put("年级","软工134班");
        map.put("人数","30人");
        classList.add(map);

        map=new HashMap<String,String>();
        map.put("年级","软件135班");
        map.put("人数","28人");
        classList.add(map);

        init();
        showClassAdapter=new InitialSigninShowClassAdapter(this,classList);
        showclass_listview.setAdapter(showClassAdapter);
        addListener();
    }

    /**
     * 初始化组件
     */
    public void init(){
        showclass_listview= (ListView) findViewById(R.id.initialsignin_bottom_showclass_listview);
        back_textview= (TextView) findViewById(R.id.initialsignin_top_back_textview);
        cancel_btn= (TextView) findViewById(R.id.initialsignin_bottom_cancel_btn);

    }


    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.initialsignin_bottom_cancel_btn:
                finish();
                break;

            case R.id.initialsignin_top_back_textview:
                finish();
                break;
        }

    }

    /**
     * 给组件添加点击事件
     */
    public void addListener(){
        back_textview.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
    }

}
