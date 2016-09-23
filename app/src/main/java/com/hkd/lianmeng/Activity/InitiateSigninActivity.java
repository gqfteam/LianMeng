package com.hkd.lianmeng.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.johe.lianmengdemo.R;
import com.hkd.lianmeng.adapter.InitialSigninShowClassAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class InitiateSigninActivity extends AppCompatActivity {
    ArrayList<HashMap<String,String>> classList;
    InitialSigninShowClassAdapter showClassAdapter;
    ListView showclass_listview;



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

    }
    public void init(){
        showclass_listview= (ListView) findViewById(R.id.initialsignin_bottom_showclass_listview);

    }


}
