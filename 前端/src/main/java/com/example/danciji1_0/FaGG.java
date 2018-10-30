package com.example.danciji1_0;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.danciji1_0.UserState.USERNAME;

public class FaGG extends AppCompatActivity {
    Button fanhui;
    Button tijiao;
    EditText gg;
    TextView biaoti;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgonggao);
        fanhui = (Button) findViewById(R.id.AGG_fanhui);
        tijiao = (Button) findViewById(R.id.AGG_tijiao);
        gg = (EditText) findViewById(R.id.AGG_Gonggao);
        biaoti = (TextView) findViewById(R.id.AGG_biaoti);
        gg.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        biaoti.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FaGG.this, Guanliyuan.class);
                startActivity(intent);
            }
        });

        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url=HttpUtilsHttpURLConnection.BASE_URL+"/updGongGao";/////这个后面的/conn也要对应的改
                        Map<String, String> params = new HashMap<String, String>();
                        //你的代码放这下面

                        String gonggao=gg.getText().toString();
                        params.put("gonggao",gonggao);
                        //你的代码放这上面

                        String result = HttpUtilsHttpURLConnection.getContextByHttp(url,params);

                        Message msg = new Message();
                        msg.what=0x12;
                        Bundle data=new Bundle();
                        data.putString("Bigresult",result);
                        msg.setData(data);


                        hander.sendMessage(msg);
                    }

                    Handler hander = new Handler(){
                        @Override
                        public void handleMessage(Message msg) {
                            if (msg.what==0x12){
                                Bundle data = msg.getData();
                                String key = data.getString("Bigresult");
                                //得到json返回的json
                                //   Toast.makeText(Denglu.this,key,Toast.LENGTH_LONG).show();
                                try {
                                    JSONObject json= new JSONObject(key);
                                    String result = (String) json.get("result");

                                    //你的代码放这下面

                                    if ("success".equals(result)){
                                        Toast.makeText(FaGG.this,"发布成功",Toast.LENGTH_SHORT).show();
                                        Intent intent =new Intent(FaGG.this,Guanliyuan.class);
                                        startActivity(intent);
                                    }else if("error".equals(result)){
                                        Toast.makeText(FaGG.this,"发布失败",Toast.LENGTH_SHORT).show();
                                    }
                                    //      Toast.makeText(MainActivity.this,(String) json.get("result1"),Toast.LENGTH_LONG).show();

                                    //你的代码放这上面
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                }).start();
            }
        });
    }
}
