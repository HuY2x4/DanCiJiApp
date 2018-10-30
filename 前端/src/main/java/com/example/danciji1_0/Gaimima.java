package com.example.danciji1_0;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
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

import static com.example.danciji1_0.UserState.USERNAME;
import static com.example.danciji1_0.UserState.USERPASSWORD;

public class Gaimima extends AppCompatActivity {
    Button tijiao;
    Button fanhui;
    EditText mima1;
    EditText mima2;
    EditText mima3;
    String mima11;
    String mima12;
    String mima13;
    TextView Tmima1,Tmima2,Tmima3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaimima);
        tijiao=(Button)findViewById(R.id.GMM_tijiao);
        fanhui=(Button)findViewById(R.id.GMM_fanhui);
        mima1=(EditText)findViewById(R.id.GMM_mima1);
        mima2=(EditText)findViewById(R.id.GMM_mima2);
        mima3=(EditText)findViewById(R.id.GMM_mima3);
        Tmima1=(TextView) findViewById(R.id.GMM_Tmima1);
        Tmima2=(TextView) findViewById(R.id.GMM_Tmima2);
        Tmima3=(TextView) findViewById(R.id.GMM_Tmima3);
//y页面加载时从数据库导入信息
        tijiao.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        Tmima1.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        Tmima2.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        Tmima3.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));


        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Gaimima.this,Gerenzhongxin.class);
                startActivity(intent);
            }
        });



        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mima11=mima1.getText().toString();
                mima12=mima2.getText().toString();
                mima13=mima3.getText().toString();
                Log.w("Hyx", USERPASSWORD);
                if(!mima11.equals(USERPASSWORD)){
                    Toast.makeText(Gaimima.this,"原始密码不正确！",Toast.LENGTH_LONG).show();
                }
                else if(!mima12.equals(mima13)){
                    Toast.makeText(Gaimima.this,"两次密码应相同！",Toast.LENGTH_LONG).show();
                }
                else if(mima12.length()>13||mima12.length()<5){
                    Toast.makeText(Gaimima.this,"新密码长度应在5-13位！",Toast.LENGTH_LONG).show();
                }
                else {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String url = HttpUtilsHttpURLConnection.BASE_URL + "/updUserPassword";/////这个后面的/conn也要对应的改
                            Map<String, String> params = new HashMap<String, String>();
                            //你的代码放这下面

                            String name = USERNAME;
                            String password=mima12;
                            params.put("name", name);
                            params.put("password", password);

                            //你的代码放这上面

                            String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);

                            Message msg = new Message();
                            msg.what = 0x12;
                            Bundle data = new Bundle();
                            data.putString("Bigresult", result);
                            data.putString("name", name);
                            msg.setData(data);


                            hander.sendMessage(msg);
                        }

                        Handler hander = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                if (msg.what == 0x12) {
                                    Bundle data = msg.getData();
                                    String key = data.getString("Bigresult");
                                    String name = data.getString("name");//得到json返回的json
                                    //   Toast.makeText(Denglu.this,key,Toast.LENGTH_LONG).show();
                                    try {
                                        JSONObject json = new JSONObject(key);
                                        String result = (String) json.get("result");

                                        //你的代码放这下面
                                        if ("success".equals(result)) {
                                            Toast.makeText(Gaimima.this, "修改成功！", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Gaimima.this, Gerenzhongxin.class);//换成改密码的界面
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
            }
        });


    }

}
