package com.example.johe.lianmengdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.mA_txt)
    TextView mATxt;

    @OnClick(R.id.mA_txt)
    public void onClick() {
        //添加一个FragmentTransaction的实例
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //用add()方法加上Fragment的对象rightFragment
        MeFragment meFragment = new MeFragment();
        transaction.add(R.id.mA_test_fragment, meFragment);

        //调用commit()方法使得FragmentTransaction实例的改变生效
        transaction.commit();
    }

    private static class B {
        private static MainActivity m = new MainActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    public static MainActivity create() {

        return B.m;
    }
}
