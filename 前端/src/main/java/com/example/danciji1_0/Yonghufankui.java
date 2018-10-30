package com.example.danciji1_0;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Yonghufankui extends AppCompatActivity {
    Button fanhui;
    Button tijiao;
    EditText fankuiinf;
    TextView biaoti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yonghufankui);
        tijiao=(Button)findViewById(R.id.YHFK_tijiao);
        fankuiinf=(EditText)findViewById(R.id.YHFK_fankuiinf);
        biaoti=(TextView) findViewById(R.id.YHFK_biaoti);
        fanhui=(Button)findViewById(R.id.YHFK_fanhui);
        fankuiinf.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        biaoti.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        tijiao.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Yonghufankui.this,Shezhi.class);
                startActivity(intent);
            }
        });


        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url=HttpUtilsHttpURLConnection.BASE_URL+"/addUserFanKui";/////这个后面的/conn也要对应的改
                        Map<String, String> params = new HashMap<String, String>();
                        //你的代码放这下面

                        String fkinf=fankuiinf.getText().toString();
                        params.put("fkinf",fkinf);
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
                                String key = data.getString("Bigresult");
                               //得到json返回的json
                                //   Toast.makeText(Denglu.this,key,Toast.LENGTH_LONG).show();
                                try {
                                    JSONObject json= new JSONObject(key);
                                    String result = (String) json.get("result");

                                    //你的代码放这下面

                                    if ("success".equals(result)){
                                        Toast.makeText(Yonghufankui.this,"提交成功",Toast.LENGTH_SHORT).show();
                                        Intent intent =new Intent(Yonghufankui.this,Shezhi.class);
                                        startActivity(intent);
                                    }else if("error".equals(result)){
                                        Toast.makeText(Yonghufankui.this,"提交失败",Toast.LENGTH_SHORT).show();
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
                Intent intent =new Intent(Yonghufankui.this,Shezhi.class);
                startActivity(intent);
            }
        });




    }
}
