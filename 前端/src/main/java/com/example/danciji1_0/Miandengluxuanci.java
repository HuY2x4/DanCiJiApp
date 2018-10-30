package com.example.danciji1_0;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static  com.example.danciji1_0.UserState.USERCIKU;

public class Miandengluxuanci extends AppCompatActivity {
    Button siji;
    Button liuji;
    TextView biaoti;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miandengluxuanci);
        siji=(Button)findViewById(R.id.MDL_siji);
        liuji=(Button)findViewById(R.id.MDL_liuji);
        biaoti=(TextView) findViewById(R.id.MDL_biaoti);
        siji.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        liuji.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        biaoti.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        siji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USERCIKU=4;
                Intent intent =new Intent(Miandengluxuanci.this,Zhuye.class);
                startActivity(intent);
            }
        });


        liuji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USERCIKU=6;
                Intent intent =new Intent(Miandengluxuanci.this,Zhuye.class);
                startActivity(intent);
            }
        });
    }
}
