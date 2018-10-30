package com.example.danciji1_0;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.danciji1_0.UserState.USERCIKU;
import static com.example.danciji1_0.UserState.USERNAME;
import static com.example.danciji1_0.UserState.USERPASSWORD;
import static com.example.danciji1_0.UserState.ZF4_1;
import static com.example.danciji1_0.UserState.ZF4_2;
import static com.example.danciji1_0.UserState.ZF6_1;
import static com.example.danciji1_0.UserState.ZF6_2;

public class Gerenzhongxin extends AppCompatActivity {
Button gaimima;
Button tijiao;
Button fanhui;
Button zhuxiao;
TextView zhanghao;
TextView Tzhanghao;
TextView Tnumber;
TextView Tciku;
TextView TkanciZF;
TextView TkanyiZF;
TextView Tbiaoti;
EditText number;
RadioGroup zu;
RadioButton siji;
RadioButton liuji;
TextView kanciZF,kanyiZF;
    private SharedPreferences sp=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenzhongxin);
        Tbiaoti=(TextView)findViewById(R.id.GRZX_biaoti);
        zhanghao=(TextView)findViewById(R.id.GRZX_zhanghao);
        number=(EditText)findViewById(R.id.GRZX_number);
        zu=(RadioGroup)findViewById(R.id.GRZX_zu);
        siji=(RadioButton)findViewById(R.id.GRZX_siji);
        liuji=(RadioButton)findViewById(R.id.GRZX_liuji);
        fanhui=(Button)findViewById(R.id.GRZX_fanhui);
        zhuxiao=(Button)findViewById(R.id.GRZX_zhuxiao);
        tijiao=(Button)findViewById(R.id.GRZX_tijiao);
        gaimima=(Button)findViewById(R.id.GRZX_gaimima);
        kanciZF=(TextView) findViewById(R.id.GRZX_kanciZF);
        kanyiZF=(TextView)findViewById(R.id.GRZX_kanyiZF);
        Tzhanghao=(TextView) findViewById(R.id.GRZX_Tzhanghao);
        Tnumber=(TextView)findViewById(R.id.GRZX_Tnumber);
        Tciku=(TextView) findViewById(R.id.GRZX_Tciku);
        TkanciZF=(TextView)findViewById(R.id.GRZX_TkanciZF);
        TkanyiZF=(TextView) findViewById(R.id.GRZX_TkanyiZF);
        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        Tbiaoti.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        zhanghao.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        number.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        siji.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        liuji.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        tijiao.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        gaimima.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        Tzhanghao.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        Tnumber.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        Tciku.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        TkanciZF.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        TkanyiZF.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        zhuxiao.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
//y页面加载时从数据库导入信息
        new Thread(new Runnable() {
            @Override
            public void run() {

                String url=HttpUtilsHttpURLConnection.BASE_URL+"/chaUsersInf";/////这个后面的/conn也要对应的改

                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面

                params.put("name",USERNAME);
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
                        String key = data.getString("Bigresult");//得到json返回的json
                        //   Toast.makeText(Denglu.this,key,Toast.LENGTH_LONG).show();
                        try {

                            JSONObject json= new JSONObject(key);
                            String Number1 = (String) json.get("Number");

                            String ZongFen4_1 = (String) json.get("ZongFen41");

                            String ZongFen4_2 = (String) json.get("ZongFen42");

                            String ZongFen6_1 = (String) json.get("ZongFen61");

                            String ZongFen6_2 = (String) json.get("ZongFen62");

                            String mima1 = (String) json.get("mima");

                            String ciku1 = (String) json.get("ciku");

                            //你的代码放这下面
                            zhanghao.setText(USERNAME);
                            number.setText(Number1);

                            if(ciku1.equals("4")){
                                siji.setChecked(true);
                                kanciZF.setText(Integer.toString(ZF4_1));
                                kanyiZF.setText(Integer.toString(ZF4_2));
                            }
                            else if(ciku1.equals("6")){
                                liuji.setChecked(true);
                                kanciZF.setText(Integer.toString(ZF6_1));
                                kanyiZF.setText(Integer.toString(ZF6_2));
                            }

                            USERPASSWORD=mima1;
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
                Intent intent =new Intent(Gerenzhongxin.this,Zhuye.class);
                startActivity(intent);
            }
        });


        zhuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USERNAME="";
                USERPASSWORD="";
                USERCIKU=4;
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("USER_NAME", USERNAME);
                editor.putString("PASSWORD","");
                editor.putBoolean("ISCHECK", false);
                editor.putBoolean("AUTO_ISCHECK",false);
                editor.apply();
                Log.w("Hyx","个人中心 "+sp.getString("USER_NAME", ""));
                Log.w("Hyx","个人中心 "+sp.getString("PASSWORD", ""));
                Intent intent =new Intent(Gerenzhongxin.this,Denglu.class);
                startActivity(intent);
            }
        });

        gaimima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(Gerenzhongxin.this,Gaimima.class);//换成改密码的界面
                startActivity(intent);
            }
        });

        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String url=HttpUtilsHttpURLConnection.BASE_URL+"/updUserInf";/////这个后面的/conn也要对应的改
                            Map<String, String> params = new HashMap<String, String>();
                            //你的代码放这下面
                            String name=zhanghao.getText().toString();
                            String number1=number.getText().toString();
                            String ciku="4";
                            if(zu.getCheckedRadioButtonId()==R.id.GRZX_siji){
                                ciku="4";
                            }
                            if(zu.getCheckedRadioButtonId()==R.id.GRZX_liuji){
                                ciku="6";
                            }

                            params.put("name",name);
                            params.put("Number",number1);
                            params.put("ciku",ciku);
                            //你的代码放这上面

                            String result = HttpUtilsHttpURLConnection.getContextByHttp(url,params);

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

                                        if ("Numbernull".equals(result)){
                                            Toast.makeText(Gerenzhongxin.this,"手机号码不能为空！",Toast.LENGTH_SHORT).show();

                                        }else if("Numbererror".equals(result)){
                                            Toast.makeText(Gerenzhongxin.this,"请输入正确的手机号码！",Toast.LENGTH_SHORT).show();
                                        }else  if("success".equals(result)){
                                            Toast.makeText(Gerenzhongxin.this,"修改成功！",Toast.LENGTH_SHORT).show();
                                            Intent intent =new Intent(Gerenzhongxin.this,Zhuye.class);//换成改密码的界面
                                            startActivity(intent);
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
