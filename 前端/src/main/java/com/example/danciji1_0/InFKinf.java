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

public class InFKinf extends AppCompatActivity {
    Button fanhui;
    Button shang;
    Button xia;
    Button delete;
    TextView fk;
    TextView num;
    TextView name;
    TextView biaoti;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infankuiinf);
        delete = (Button) findViewById(R.id.IFK_delete);
        fanhui = (Button) findViewById(R.id.IFK_fanhui);
        shang = (Button) findViewById(R.id.IFK_shang);
        xia = (Button) findViewById(R.id.IFK_xia);
        fk = (TextView) findViewById(R.id.IFK_fkinf);
        num=(TextView)findViewById(R.id.IFK_num);
        name=(TextView)findViewById(R.id.IFK_fkname);
        biaoti = (TextView) findViewById(R.id.IFK_biaoti);
        shang.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        biaoti.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        xia.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        delete.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        name.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        fk.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        num.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/chaUserFanKui";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面

                String fkinfid=num.getText().toString();
                params.put("fkinfid",fkinfid);
                //你的代码放这上面

                String fkinf = HttpUtilsHttpURLConnection.getContextByHttp(url,params);

                Message msg = new Message();
                msg.what=0x12;
                Bundle data=new Bundle();
                data.putString("Bigresult",fkinf);
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
                            String fkinf = (String) json.get("fkinf");
                            String namestr=(String) json.get("name");
                            //你的代码放这下面
                            name.setText(namestr);
                            fk.setText(fkinf);
                            //      Toast.makeText(MainActivity.this,(String) json.get("result1"),Toast.LENGTH_LONG).show();

                            //你的代码放这上面
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }).start();

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InFKinf.this, Guanliyuan.class);
                startActivity(intent);
            }
        });

        xia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url=HttpUtilsHttpURLConnection.BASE_URL+"/chaUserFanKui";/////这个后面的/conn也要对应的改
                        Map<String, String> params = new HashMap<String, String>();
                        //你的代码放这下
                        String number=num.getText().toString();
                        int numberint=Integer.parseInt(number);
                        numberint=numberint+1;
                        String numberstr=Integer.toString(numberint);

                        params.put("fkinfid",numberstr);
                        //你的代码放这上面

                        String result = HttpUtilsHttpURLConnection.getContextByHttp(url,params);

                        Message msg = new Message();
                        msg.what=0x12;
                        Bundle data=new Bundle();
                        data.putString("Bigresult",result);
                        data.putString("numberstr",numberstr);
                        msg.setData(data);


                        hander.sendMessage(msg);
                    }

                    Handler hander = new Handler(){
                        @Override
                        public void handleMessage(Message msg) {
                            if (msg.what==0x12){
                                Bundle data = msg.getData();
                                String key = data.getString("Bigresult");
                                String numberstr = data.getString("numberstr");

                                //得到json返回的json
                                //   Toast.makeText(Denglu.this,key,Toast.LENGTH_LONG).show();
                                try {
                                    JSONObject json= new JSONObject(key);
                                    String fkinf = (String) json.get("fkinf");

                                    //你的代码放这下面

                                    if ("fail".equals(fkinf)){
                                        Toast.makeText(InFKinf.this,"最后一页！",Toast.LENGTH_SHORT).show();

                                    }else {
                                        fk.setText(fkinf);
                                        num.setText(numberstr);
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

        shang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url=HttpUtilsHttpURLConnection.BASE_URL+"/chaUserFanKui";/////这个后面的/conn也要对应的改
                        Map<String, String> params = new HashMap<String, String>();
                        //你的代码放这下
                        String number=num.getText().toString();
                        int numberint=Integer.parseInt(number);
                        numberint=numberint-1;
                        String numberstr=Integer.toString(numberint);

                        params.put("fkinfid",numberstr);
                        //你的代码放这上面

                        String result = HttpUtilsHttpURLConnection.getContextByHttp(url,params);

                        Message msg = new Message();
                        msg.what=0x12;
                        Bundle data=new Bundle();
                        data.putString("Bigresult",result);
                        data.putString("numberstr",numberstr);
                        msg.setData(data);


                        hander.sendMessage(msg);
                    }

                    Handler hander = new Handler(){
                        @Override
                        public void handleMessage(Message msg) {
                            if (msg.what==0x12){
                                Bundle data = msg.getData();
                                String key = data.getString("Bigresult");
                                String numberstr = data.getString("numberstr");

                                //得到json返回的json
                                //   Toast.makeText(Denglu.this,key,Toast.LENGTH_LONG).show();
                                try {
                                    JSONObject json= new JSONObject(key);
                                    String fkinf = (String) json.get("fkinf");

                                    //你的代码放这下面

                                    if ("fail".equals(fkinf)){
                                        Toast.makeText(InFKinf.this,"已经是第一页！",Toast.LENGTH_SHORT).show();

                                    }else {
                                        fk.setText(fkinf);
                                        num.setText(numberstr);
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


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url=HttpUtilsHttpURLConnection.BASE_URL+"/delUserFanKui";/////这个后面的/conn也要对应的改
                        Map<String, String> params = new HashMap<String, String>();
                        //你的代码放这下
                        String number=num.getText().toString();
                        String fkinfid=num.getText().toString();

                        params.put("fkinfid",fkinfid);
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
                                        Toast.makeText(InFKinf.this,"删除成功！",Toast.LENGTH_SHORT).show();

                                    }else {
                                        Toast.makeText(InFKinf.this,"删除失败！",Toast.LENGTH_SHORT).show();
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
