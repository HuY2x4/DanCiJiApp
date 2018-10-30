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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Guanliyuan extends AppCompatActivity {
    Button fanhui;
    Button addDC;
    Button updDC;
    Button delDC;
    Button addGG;
    Button inFK;
    TextView inFK_count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanliyuan);
        fanhui=(Button)findViewById(R.id.GLY_fanhui);
        addDC=(Button)findViewById(R.id.GLY_tianjiadanci);
        updDC=(Button)findViewById(R.id.GLY_xiugaidanci);
        delDC=(Button)findViewById(R.id.GLY_shanchudanci);
        addGG=(Button)findViewById(R.id.GLY_fabugonggao);
        inFK=(Button)findViewById(R.id.GLY_fankuixinxi);
        inFK_count=(TextView)findViewById(R.id.GLY_count);
        addDC.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        updDC.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        delDC.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        addGG.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        inFK.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        countFK();

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Guanliyuan.this,Shezhi.class);
                startActivity(intent);
            }
        });

        delDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Guanliyuan.this,DelDC.class);
                startActivity(intent);
            }
        });

        addDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Guanliyuan.this,AddDC.class);
                startActivity(intent);
            }
        });

        updDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Guanliyuan.this,updDC.class);
                startActivity(intent);
            }
        });

        delDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Guanliyuan.this,DelDC.class);
                startActivity(intent);
            }
        });

        addGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Guanliyuan.this,FaGG.class);
                startActivity(intent);
            }
        });

        inFK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                countFK_clear();
                Intent intent =new Intent(Guanliyuan.this,InFKinf.class);
                startActivity(intent);
            }
        });


    }


    public  void countFK(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/countUserFanKui";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面


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
                            String count = (String) json.get("count");

                            if(count.equals("0")){
                                inFK_count.setVisibility(View.INVISIBLE);
                            }
                            else{
                                inFK_count.setVisibility(View.VISIBLE);
                                inFK_count.setText(count);
                            }
                            //你的代码放这下面

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


    public  void countFK_clear(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/clearUserFanKui";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面


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
                            if(result.equals("success")){

                            }
                            else{

                            }
                            //你的代码放这下面

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




}
