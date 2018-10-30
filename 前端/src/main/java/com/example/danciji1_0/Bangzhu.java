package com.example.danciji1_0;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Bangzhu extends AppCompatActivity {
    Button fanhui;
    TextView biaoti;
    TextView inf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangzhu);
        fanhui=(Button)findViewById(R.id.BZ_fanhui);
        biaoti=(TextView) findViewById(R.id.BZ_biaoti);
        inf=(TextView) findViewById(R.id.BZ_inf);
        biaoti.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        inf.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Bangzhu.this,Zhuye.class);
                startActivity(intent);
            }
        });
    }
}
