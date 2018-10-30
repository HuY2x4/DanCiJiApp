package com.example.danciji1_0;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.type;
import static com.example.danciji1_0.UserState.USERNAME;
import static com.example.danciji1_0.UserState.USERPASSWORD;

public class Denglu extends AppCompatActivity {
    EditText zhanghao;
    EditText mima;
    Button denglu;
    Button zhuce;
    Button miandenglu;


    private CheckBox rem_pw, auto_login;

    private SharedPreferences sp=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_denglu);
        Log.w("Hyx","1");
        zhanghao=(EditText)findViewById(R.id.DL_name);
        mima=(EditText)findViewById(R.id.DL_mima);
        denglu=(Button)findViewById(R.id.DL_denglu);
        zhuce=(Button)findViewById(R.id.DL_zhuce);
        miandenglu=(Button)findViewById(R.id.DL_miandenglu);

        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        Log.w("Hyx","2");
        rem_pw = (CheckBox) findViewById(R.id.remember_key);
        auto_login = (CheckBox) findViewById(R.id.automatic_login);
        Log.w("Hyx","3");

       // zhanghao.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
       // mima.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        rem_pw.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        auto_login.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        zhuce.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        denglu.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        miandenglu.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));


        //判断记住密码多选框的状态
        if(sp.getBoolean("ISCHECK", false))
        {
            //设置默认是记录密码状态
            Log.w("Hyx","4");
            rem_pw.setChecked(true);
            Log.w("Hyx","4.5");
            Log.w("Hyx","4.51 "+sp.getString("USER_NAME", ""));
            Log.w("Hyx","4.51 "+sp.getString("PASSWORD", ""));
            String zhanghaostr=sp.getString("USER_NAME", "");
            String mimastr=sp.getString("PASSWORD", "");
            zhanghao.setText(zhanghaostr);
            Log.w("Hyx","4.6");
            mima.setText(mimastr);
            Log.w("Hyx","5");
            //判断自动登陆多选框状态
            if(sp.getBoolean("AUTO_ISCHECK", false))
            {
                Log.w("Hyx","6");
                //设置默认是自动登录状态
                auto_login.setChecked(true);
                //跳转界面

                    denglulu();

            }
        }


        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Denglu.this,Zhuce.class);
                startActivity(intent);
            }
        });

        denglu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                denglulu();

            }
        });

        miandenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Denglu.this,Miandengluxuanci.class);
                startActivity(intent);
            }
        });
        //监听记住密码多选框按钮事件
        rem_pw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (rem_pw.isChecked()) {
                    Log.w("Hyx","记住密码已选中");

                    sp.edit().putBoolean("ISCHECK", true).commit();

                }else {
                    Log.w("Hyx","记住密码没有选中");
                    sp.edit().putBoolean("ISCHECK", false).commit();
                    if(auto_login.isChecked()){
                        auto_login.setChecked(false);
                        sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                    }

                }

            }
        });
        //监听自动登录多选框事件
        auto_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (auto_login.isChecked()) {
                    System.out.println("自动登录已选中");
                    rem_pw.setChecked(true);
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();
                    sp.edit().putBoolean("ISCHECK", true).commit();


                } else {
                    System.out.println("自动登录没有选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                }
            }
        });
    }

    public void denglulu(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                String url=HttpUtilsHttpURLConnection.BASE_URL+"/Denglu";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                String name=zhanghao.getText().toString();
                String password=mima.getText().toString();
                params.put("name",name);
                params.put("password",password);
                //你的代码放这上面

                String result = HttpUtilsHttpURLConnection.getContextByHttp(url,params);
                Log.w("Hyx", result);
                Message msg = new Message();
                msg.what=0x12;
                Bundle data=new Bundle();
                data.putString("Bigresult",result);
                data.putString("name",name);
                msg.setData(data);


                hander.sendMessage(msg);
            }

            Handler hander = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what==0x12){
                        Bundle data = msg.getData();
                        String key = data.getString("Bigresult");
                        String name=data.getString("name");//得到json返回的json
                        //   Toast.makeText(Denglu.this,key,Toast.LENGTH_LONG).show();
                        try {
                            JSONObject json= new JSONObject(key);
                            String result = (String) json.get("result");

                            //你的代码放这下面
                            Log.w("Hyx", "7");
                            if(zhanghao.getText().toString().equals("")||zhanghao.getText().toString().equals(null)||mima.getText().toString().equals("")||mima.getText().toString().equals(null)){
                                Toast.makeText(Denglu.this,"账号密码不能为空！",Toast.LENGTH_SHORT).show();
                            }
                            else if ("success".equals(result)){
                                Log.w("Hyx","8");
                                Toast.makeText(Denglu.this,"登录成功",Toast.LENGTH_SHORT).show();
                                USERNAME=name;
                                USERPASSWORD=mima.getText().toString();
                                if(rem_pw.isChecked())
                                {
                                    //记住用户名、密码、
                                    Log.w("Hyx","9");
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putString("USER_NAME", USERNAME);
                                    editor.putString("PASSWORD",USERPASSWORD);
                                    editor.apply();
                                    Log.w("Hyx","10");
                                    Log.w("Hyx","101"+sp.getString("USER_NAME", ""));
                                    Log.w("Hyx","101"+sp.getString("PASSWORD", ""));
                                }
                                Intent intent =new Intent(Denglu.this,Zhuye.class);
                                startActivity(intent);
                            }else if("fail".equals(result)){
                                Toast.makeText(Denglu.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
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
