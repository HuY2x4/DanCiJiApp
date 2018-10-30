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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class updDC extends AppCompatActivity {
    Button fanhui;
    Button tijiao;
    EditText danci;
    EditText danciyisi;
    EditText danciliju;
    RadioGroup zu;
    RadioButton siji;
    RadioButton liuji;
    TextView Tdanci;
    TextView Tyisi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upddanci);
        fanhui=(Button)findViewById(R.id.UDC_fanhui);
        danci=(EditText)findViewById(R.id.UDC_danci);
        tijiao=(Button)findViewById(R.id.UDC_xiugai);
        zu=(RadioGroup)findViewById(R.id.UDC_zu);
        siji=(RadioButton)findViewById(R.id.UDC_siji);
        liuji=(RadioButton)findViewById(R.id.UDC_liuji);
        danciyisi=(EditText)findViewById(R.id.UDC_yisi);
    //    danciliju=(EditText)findViewById(R.id.UDC_liju);
        Tdanci=(TextView) findViewById(R.id.UDC_Tdanci);
        Tyisi=(TextView) findViewById(R.id.UDC_Tyisi);
        Tdanci.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        Tyisi.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        danci.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        siji.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        danciyisi.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        tijiao.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        liuji.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        siji.setChecked(true);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updDC.this, Guanliyuan.class);
                startActivity(intent);
            }
        });


        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url=HttpUtilsHttpURLConnection.BASE_URL+"/updDanCi";/////这个后面的/conn也要对应的改
                        Map<String, String> params = new HashMap<String, String>();
                        //你的代码放这下面
                        String DC=danci.getText().toString();
                        String DCyisi=danciyisi.getText().toString();
              //          String DCliju=danciliju.getText().toString();
                        String DCciku="4";
                        if(zu.getCheckedRadioButtonId()==R.id.UDC_siji){
                            DCciku="4";
                        }
                        else if(zu.getCheckedRadioButtonId()==R.id.UDC_liuji){
                            DCciku="6";
                        }
                        params.put("DC",DC);
                        params.put("DCyisi",DCyisi);
         //               params.put("DCliju",DCliju);
                        params.put("DCciku",DCciku);
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
                                        Toast.makeText(updDC.this,"添加成功！",Toast.LENGTH_SHORT).show();
                                        Intent intent =new Intent(updDC.this,Guanliyuan.class);
                                        startActivity(intent);
                                    }else if("error".equals(result)){
                                        Toast.makeText(updDC.this,"添加失败！",Toast.LENGTH_SHORT).show();
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
