package com.example.danciji1_0;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.danciji1_0.UserState.USERCIKU;
import static com.example.danciji1_0.UserState.USERNAME;

public class Shezhi extends AppCompatActivity {
Button fanhui;
Button yonghufankui;
Button banbengengxin;
Button chongzhiyiyoudanci;
Button chongzhizhongdiandanci;
Button guanliyuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);
        fanhui=(Button)findViewById(R.id.SZ_fanhui);
        guanliyuan=(Button)findViewById(R.id.SZ_guanliyuan);
        yonghufankui=(Button)findViewById(R.id.SZ_yonghufankui);
        banbengengxin=(Button)findViewById(R.id.SZ_banbengengxin);
        chongzhiyiyoudanci=(Button)findViewById(R.id.SZ_chongzhiyiyoudanci);
        chongzhizhongdiandanci=(Button)findViewById(R.id.SZ_chongzhizhongdiandanci);

        yonghufankui.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        banbengengxin.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        chongzhiyiyoudanci.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        chongzhizhongdiandanci.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Shezhi.this,Zhuye.class);
                startActivity(intent);
            }
        });


        if(!("admin".equals(USERNAME)||"Hyxzucc".equals(USERNAME)||"ckshishen".equals(USERNAME)||"holley".equals(USERNAME))){
        guanliyuan.setVisibility(View.INVISIBLE);
        }
        guanliyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Shezhi.this,Guanliyuan.class);
                startActivity(intent);
            }
        });



        yonghufankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (USERNAME.equals("")) {
                    Toast.makeText(Shezhi.this, "请先登录！", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(Shezhi.this, Yonghufankui.class);
                    startActivity(intent);
                }
            }
        });


        banbengengxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Shezhi.this,"已经是最新版本！",Toast.LENGTH_LONG).show();
            }
        });



        chongzhiyiyoudanci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(USERNAME.equals("")){
                    Toast.makeText(Shezhi.this,"请先登录！",Toast.LENGTH_LONG).show();
                }
                else {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String url = HttpUtilsHttpURLConnection.BASE_URL + "/resHasDanCi";/////这个后面的/conn也要对应的改
                            Map<String, String> params = new HashMap<String, String>();
                            //你的代码放这下面
                            params.put("name", USERNAME);
                            //你的代码放这上面

                            String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);

                            Message msg = new Message();
                            msg.what = 0x12;
                            Bundle data = new Bundle();
                            data.putString("Bigresult", result);

                            msg.setData(data);


                            hander.sendMessage(msg);
                        }

                        Handler hander = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                if (msg.what == 0x12) {
                                    Bundle data = msg.getData();
                                    String key = data.getString("Bigresult");
                                    //得到json返回的json
                                    //   Toast.makeText(Denglu.this,key,Toast.LENGTH_LONG).show();
                                    try {
                                        JSONObject json = new JSONObject(key);
                                        String result = (String) json.get("result");

                                        //你的代码放这下面

                                        if ("success".equals(result)) {
                                            Toast.makeText(Shezhi.this, "重置成功！", Toast.LENGTH_SHORT).show();

                                        } else if ("error".equals(result)) {
                                            Toast.makeText(Shezhi.this, "重置失败！", Toast.LENGTH_SHORT).show();
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
            }
        });


        chongzhizhongdiandanci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(USERNAME.equals("")){
                    Toast.makeText(Shezhi.this,"请先登录！",Toast.LENGTH_LONG).show();
                }
                else {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String url = HttpUtilsHttpURLConnection.BASE_URL + "/resPoDanCi";/////这个后面的/conn也要对应的改
                            Map<String, String> params = new HashMap<String, String>();
                            //你的代码放这下面
                            params.put("name", USERNAME);
                            params.put("UserSelect", Integer.toString(USERCIKU));
                            //你的代码放这上面

                            String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);

                            Message msg = new Message();
                            msg.what = 0x12;
                            Bundle data = new Bundle();
                            data.putString("Bigresult", result);

                            msg.setData(data);


                            hander.sendMessage(msg);
                        }

                        Handler hander = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                if (msg.what == 0x12) {
                                    Bundle data = msg.getData();
                                    String key = data.getString("Bigresult");
                                    //得到json返回的json
                                    //   Toast.makeText(Denglu.this,key,Toast.LENGTH_LONG).show();
                                    try {
                                        JSONObject json = new JSONObject(key);
                                        String result = (String) json.get("result");

                                        //你的代码放这下面

                                        if ("success".equals(result)) {
                                            Toast.makeText(Shezhi.this, "重置成功！", Toast.LENGTH_SHORT).show();

                                        } else if ("fail".equals(result)) {
                                            Toast.makeText(Shezhi.this, "重置失败！", Toast.LENGTH_SHORT).show();
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
            }
        });


    }
}
