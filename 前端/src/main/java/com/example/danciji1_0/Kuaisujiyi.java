package com.example.danciji1_0;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.danciji1_0.UserState.USERCIKU;
import static com.example.danciji1_0.UserState.USERNAME;

public class Kuaisujiyi extends AppCompatActivity {
Button zhuye;
Button shang;
Button xia;
Button mark;
TextView number;
TextView danci;
TextView danciyisi;
TextView danciliju;
int num;
int dcid;
int if_point;
int dcxh;
String DCnow="";

    private Button voice;    //播放按钮
    private KqwSpeechCompound kqwSpeechCompound;    //创建对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuaisujiyi);

        Intent intentR = getIntent();
        Bundle bundle = intentR.getExtras();
        if_point=Integer.parseInt(bundle.getString("if_point"));
        num=Integer.parseInt(bundle.getString("num"));
        dcid=(num-1)*20+1;
        dcxh=1;
        setfind();
        number.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        zhuye.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        shang.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        xia.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        danci.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        danciyisi.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        if(USERNAME.equals("")){
            mark.setVisibility(View.INVISIBLE);
        }

        if(if_point==0){
            chaDC();
        }
        else if(if_point==1){
            PochaDC();
        }


        SpeechUtility.createUtility( Kuaisujiyi.this, SpeechConstant.APPID + "=5b1d13aa"); //初始化
        danci= (TextView) findViewById(R.id.fastGN_danci);
        voice= (Button) findViewById(R.id.fastGN_voice);
        kqwSpeechCompound=new KqwSpeechCompound(this);
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kqwSpeechCompound.setParam();
                kqwSpeechCompound.speaking(danci.getText().toString().trim());
            }
        });        //合成语音




        zhuye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnZhuye();//改颜色
                Intent intent =new Intent(Kuaisujiyi.this,Zhuye.class);
                startActivity(intent);
            }
        });


        mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mark.getText().toString().equals("mark")){
                    markDC();
                }
                else if(mark.getText().toString().equals("unmark")){
                    unmarkDC();
                }
            }
        });



        xia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(if_point==0){
                    if(dcxh==20){
                        Toast.makeText(Kuaisujiyi.this,"该词库全部记忆完成",Toast.LENGTH_LONG).show();
                        allDoneGaiyanse();
                        Intent intent =new Intent(Kuaisujiyi.this,Zhuye.class);
                        startActivity(intent);
                    }
                    else if(dcxh<20){
                        Log.w("Hyx", Integer.toString(dcxh));
                        dcxh=dcxh+1;
                        dcid=dcid+1;
                        Log.w("Hyx", Integer.toString(dcxh));
                        chaDC();
                    }

                }
                else if(if_point==1){
                    dcxh=dcxh+1;
                    PochaDC();
                }

            }
        });




        shang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(if_point==0){
                    if(dcxh==1){
                        Toast.makeText(Kuaisujiyi.this,"当前是第一页！",Toast.LENGTH_LONG).show();

                    }
                    else if(dcxh<20){
                        dcxh=dcxh-1;
                        dcid=dcid-1;
                        chaDC();
                    }

                }
                else if(if_point==1){
                    if(dcxh==1){
                        Toast.makeText(Kuaisujiyi.this,"当前是第一页！",Toast.LENGTH_LONG).show();
                    }
                    else{
                        PochaDC();
                    }

                }

            }
        });






    }

    public void setfind(){
        zhuye=(Button)findViewById(R.id.fastGN_zhuye);
        shang=(Button)findViewById(R.id.fastGN_shang);
        xia=(Button)findViewById(R.id.fastGN_xia);
        mark=(Button)findViewById(R.id.fastGN_mark);
        number=(TextView)findViewById(R.id.fastGN_number);
        danci=(TextView)findViewById(R.id.fastGN_danci);
        danciyisi=(TextView)findViewById(R.id.fastGN_danciyisi);
        danciliju=(TextView)findViewById(R.id.fastGN_danciliju);

    }

    public  void returnZhuye(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/updHasDanCi";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                String gongneng="3";
                String siliujistr=Integer.toString(USERCIKU);
                String yanse="1";
                params.put("xiaociku",Integer.toString(num));
                params.put("name",USERNAME);
                params.put("gongneng",gongneng);
                params.put("siliuji",siliujistr);
                params.put("yanse",yanse);
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
                            if(result.equals("fail")){
                                Toast.makeText(Kuaisujiyi.this,"颜色记录失败",Toast.LENGTH_LONG).show();
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


    public  void allDoneGaiyanse(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/updHasDanCi";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                String gongneng="3";
                String siliujistr=Integer.toString(USERCIKU);
                String yanse="2";
                params.put("xiaociku",Integer.toString(num));
                params.put("name",USERNAME);
                params.put("gongneng",gongneng);
                params.put("siliuji",siliujistr);
                params.put("yanse",yanse);
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
                            if(result.equals("fail")){
                                Toast.makeText(Kuaisujiyi.this,"颜色记录失败",Toast.LENGTH_LONG).show();
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


    public void chaDC(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/chaDanCi";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                String DCid=Integer.toString(dcid);
                String DCciku=Integer.toString(USERCIKU);
                params.put("DCciku",DCciku);
                params.put("DCid",DCid);

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
                            String DC1 = (String) json.get("DC1");
                            String DCyisi = (String) json.get("DCyisi");
                            String DCyinbiao = (String) json.get("DCyinbiao");
                            String DC2 = (String) json.get("DC2");
                            String DC3 = (String) json.get("DC3");
                            String DC4 = (String) json.get("DC4");
                            String DCyisi2 = (String) json.get("DCyisi2");
                            String DCyisi3 = (String) json.get("DCyisi3");
                            String DCyisi4 = (String) json.get("DCyisi4");
                            String strxuhao=Integer.toString(dcxh);
                            if(DC1.equals("1")){
                                Toast.makeText(Kuaisujiyi.this,"该词库全部记忆完成",Toast.LENGTH_LONG).show();
                                allDoneGaiyanse();
                                Intent intent =new Intent(Kuaisujiyi.this,Zhuye.class);
                                startActivity(intent);
                            }
                            number.setText(strxuhao);
                            danci.setText(DC1);
                            danciyisi.setText(DCyisi);
                            danciliju.setText(DCyinbiao);
                            DCnow=DC1;
                            if_mark();
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


    public void PochaDC(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/chaPoDanCi";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                String DCxh=Integer.toString(dcxh);
                String DCciku=Integer.toString(USERCIKU);
                params.put("name",USERNAME);
                params.put("DCciku",DCciku);
                params.put("DCxh",DCxh);

                //你的代码放这上面
                Log.w("Hyx", "!!好烦");
                String result = HttpUtilsHttpURLConnection.getContextByHttp(url,params);
                Log.w("Hyx", result);
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
                            Log.w("Hyx", "心累");
                            JSONObject json= new JSONObject(key);
                            Log.w("Hyx", "!");
                            String DC1 = (String) json.get("DC1");
                            String result = (String) json.get("result");
                            Log.w("Hyx", result);
                            String DCyisi = (String) json.get("DCyisi");
                            Log.w("Hyx", "!!");
                            String DCyinbiao = (String) json.get("DCyinbiao");
                            String DC2 = (String) json.get("DC2");
                            String DC3 = (String) json.get("DC3");
                            String DC4 = (String) json.get("DC4");
                            String DCyisi2 = (String) json.get("DCyisi2");
                            String DCyisi3 = (String) json.get("DCyisi3");
                            String DCyisi4 = (String) json.get("DCyisi4");
                            String DCxh=Integer.toString(dcxh);
                            if(result.equals("fail")){
                                Log.w("Hyx", "好累");
                                Toast.makeText(Kuaisujiyi.this,"该词库全部记忆完成",Toast.LENGTH_LONG).show();
                                Intent intent =new Intent(Kuaisujiyi.this,Zhuye.class);
                                startActivity(intent);
                            }

                            number.setText(DCxh);
                            danci.setText(DC1);
                            danciyisi.setText(DCyisi);
                            danciliju.setText(DCyinbiao);
                            DCnow=DC1;
                            Log.w("Hyx", "!!mark调用前"+mark.getText().toString());
                            if_mark();
                            Log.w("Hyx", "!!mark调用后"+mark.getText().toString());
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


    public void markDC(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/addPoDanCi";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                String DCid=Integer.toString((num-1)*20+dcxh);
                String DCciku=Integer.toString(USERCIKU);
                String DC=DCnow;
                params.put("DC",DC);
                params.put("DCciku",DCciku);
                params.put("DCid",DCid);
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
                            if(result.equals("success")){
                                mark.setBackgroundColor(Color.parseColor("#ff0000"));
                                mark.setText("unmark");
                            }
                            else{
                                Toast.makeText(Kuaisujiyi.this,"已存在于重点词库中",Toast.LENGTH_SHORT).show();
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



    public void unmarkDC(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/delPoDanCi";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                String DC=danci.getText().toString();
                String DCciku=Integer.toString(USERCIKU);
                params.put("DCciku",DCciku);
                params.put("DC",DC);
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
                            if(result.equals("success")){
                                mark.setBackgroundColor(Color.parseColor("#aca4a4"));
                                mark.setText("mark");
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

    public void if_mark(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/hasPoDanCi";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                Log.w("Hyx", "6:12 =1");
                String name=USERNAME;
                String dcnow=DCnow;
                Log.w("Hyx", "6:12 =2"+DCnow);
                String siliuji=Integer.toString(USERCIKU);
                params.put("name",name);
                params.put("DC",dcnow);
                params.put("DCciku",siliuji);
                //你的代码放这上面

                String result = HttpUtilsHttpURLConnection.getContextByHttp(url,params);
                Log.w("Hyx", "6:12 =3"+result);
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
                            if(result.equals("success")){
                                mark.setText("unmark");
                                mark.setBackgroundColor(Color.parseColor("#ff0000"));
                            }
                            else if(result.equals("fail")){
                                mark.setText("mark");
                                mark.setBackgroundColor(Color.parseColor("#aca4a4"));
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
