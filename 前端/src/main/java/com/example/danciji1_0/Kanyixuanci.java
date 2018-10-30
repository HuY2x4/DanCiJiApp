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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.example.danciji1_0.UserState.GONGNENGNOW;
import static com.example.danciji1_0.UserState.USERCIKU;
import static com.example.danciji1_0.UserState.USERNAME;

public class Kanyixuanci extends AppCompatActivity {

    Button zhuye;
    Button shang;
    Button xia;
    Button mark;
    TextView number;
    TextView ZhuDC;
    Button A;
    Button B;
    Button C;
    Button D;
    TextView A_jieshi;
    TextView B_jieshi;
    TextView C_jieshi;
    TextView D_jieshi;
    int num;
    int dcid;
    int if_point;
    int dcxh;
    String xiaociku="1";
    String DCnow="";
    int zongfen=0;
    int qianhou=0;//查单词的时候用到，1代表下一页时调用查单词，-1代表上一页调用查单词
    int[] fenshu=new int[100];//重点词库超过100会报错
    int[] haslook=new int[100];

    private Button voice;    //播放按钮
    private KqwSpeechCompound kqwSpeechCompound;    //创建对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanyixuanci);
        Log.w("Hyx","6-25:-1");
        Intent intentR = getIntent();
        Bundle bundle = intentR.getExtras();
        if_point=Integer.parseInt(bundle.getString("if_point"));
        num=Integer.parseInt(bundle.getString("num"));
        xiaociku=Integer.toString(num);
        dcid=(num-1)*20+1;
        dcxh=1;
        setfind();
        Log.w("Hyx","6-25:-2");
        number.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        zhuye.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        shang.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        xia.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        ZhuDC.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        A_jieshi.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        B_jieshi.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        C_jieshi.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        D_jieshi.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        A.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        B.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        C.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        D.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        if(USERNAME.equals("")){
            mark.setVisibility(View.INVISIBLE);

        }
        Log.w("Hyx","6-25:-3");
        voice= (Button) findViewById(R.id.kanyiGN_voice);
        if(if_point==0){
            chaDC();
            yincangJS();
        }
        else if(if_point==1){
            PochaDC();
            yincangJS();
        }
        SpeechUtility.createUtility( Kanyixuanci.this, SpeechConstant.APPID + "=5b1d13aa"); //初始化
        Log.w("Hyx","6-25:1");

        Log.w("Hyx","6-25:2");
        kqwSpeechCompound=new KqwSpeechCompound(this);
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kqwSpeechCompound.setParam();
                kqwSpeechCompound.speaking(DCnow.trim());
            }
        });        //合成语音
        Log.w("Hyx","6-25:3");
        voice.setVisibility(View.INVISIBLE);
        Log.w("Hyx","6-25:4");


        zhuye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnZhuye();//改颜色
                Intent intent =new Intent(Kanyixuanci.this,Zhuye.class);
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
                        Toast.makeText(Kanyixuanci.this,"该词库全部记忆完成",Toast.LENGTH_LONG).show();
                        for(int i=0;i<20;i++){
                            zongfen+=fenshu[i];
                        }

                        updFenshu();
                        Intent intent =new Intent(Kanyixuanci.this,Zhuye.class);
                        allDoneGaiyanse();
                        startActivity(intent);
                    }
                    else if(dcxh<20){
                        dcxh=dcxh+1;
                        dcid=dcid+1;
                        haslook[dcxh-2]=1;
                        if(haslook[dcxh-1]==1){
                            qianhou=1;
                        }
                        Log.w("Hyx", "qianhou-1:"+qianhou);
                        chaDC();
                        if(haslook[dcxh-1]==1){
                            xianshiJS();
                            setEnanbleF();
                        }
                        else{
                            yincangJS();
                        }


                    }

                }
                else if(if_point==1){
                    dcxh=dcxh+1;
                    PochaDC();
                    yincangJS();
                }
                setEnanbleT();
            }
        });




        shang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qianhou=-1;
                if(if_point==0){
                    if(dcxh==1){
                        Toast.makeText(Kanyixuanci.this,"当前是第一页！",Toast.LENGTH_LONG).show();

                    }
                    else if(dcxh<20){
                        dcxh=dcxh-1;
                        dcid=dcid+1;
                        chaDC();
                        xianshiJS();
                    }

                }
                else if(if_point==1){
                    if(dcxh==1){
                        Toast.makeText(Kanyixuanci.this,"当前是第一页！",Toast.LENGTH_LONG).show();
                    }
                    else{
                        PochaDC();
                        xianshiJS();

                    }

                }
                setEnanbleF();
                /*if(A_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                    A.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                }
                else if(B_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                    B.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                }
                else if(C_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                    C.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                }
                else if(C_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                    C.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                }*/
            }
        });


        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(A_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                    xianshiJS();
                    A.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                    fenshu[dcxh-1]=5;



                }
                else{
                    if(mark.getText().toString().equals("mark")){
                        markDC();
                    }

                    xianshiJS();
                    A.setBackgroundColor(Color.parseColor("#8c8585"));//灰色
                    if(B_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                        B.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                    }
                    else if(C_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                        C.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                    }
                    else if(D_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                        D.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                    }


                }

                setEnanbleF();
            }
        });


        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(B_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                    xianshiJS();
                    B.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                    fenshu[dcxh-1]=5;
                }
                else{
                    if(mark.getText().toString().equals("mark")){
                        markDC();
                    }

                    xianshiJS();
                    B.setBackgroundColor(Color.parseColor("#8c8585"));//灰色
                    if(A_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                        A.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                    }
                    else if(C_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                        C.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                    }
                    else if(D_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                        D.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                    }


                }

                setEnanbleF();
            }
        });


        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(C_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                    xianshiJS();
                    C.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                    fenshu[dcxh-1]=5;
                }
                else{
                    if(mark.getText().toString().equals("mark")){
                      markDC();
                    }
                    xianshiJS();
                    C.setBackgroundColor(Color.parseColor("#8c8585"));//灰色
                    if(A_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                        A.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                    }
                    else if(B_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                        B.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                    }
                    else if(D_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                        D.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                    }


                }
                setEnanbleF();
            }
        });

        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(D_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                    xianshiJS();
                    D.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                    fenshu[dcxh-1]=5;
                }
                else{
                    if(mark.getText().toString().equals("mark")){
                        markDC();
                    }

                    xianshiJS();
                    D.setBackgroundColor(Color.parseColor("#8c8585"));//灰色
                    if(B_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                        B.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                    }
                    else if(C_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                        C.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                    }
                    else if(A_jieshi.getText().toString().equals(ZhuDC.getText().toString())){
                        A.setBackgroundColor(Color.parseColor("#33c930"));//绿色
                    }


                }
                setEnanbleF();
            }
        });







    }

    public void setfind(){
        zhuye=(Button)findViewById(R.id.kanyiGN_zhuye);
        shang=(Button)findViewById(R.id.kanyiGN_shang);
        xia=(Button)findViewById(R.id.kanyiGN_xia);
        mark=(Button)findViewById(R.id.kanyiGN_mark);
        number=(TextView)findViewById(R.id.kanyiGN_number);
        ZhuDC=(TextView)findViewById(R.id.kanyiGN_danciyisi);
        A=(Button)findViewById(R.id.kanyiGN_A);
        B=(Button)findViewById(R.id.kanyiGN_B);
        C=(Button)findViewById(R.id.kanyiGN_C);
        D=(Button)findViewById(R.id.kanyiGN_D);
        A_jieshi=(TextView)findViewById(R.id.kanyiGN_A_jieshi);
        B_jieshi=(TextView)findViewById(R.id.kanyiGN_B_jieshi);
        C_jieshi=(TextView)findViewById(R.id.kanyiGN_C_jieshi);
        D_jieshi=(TextView)findViewById(R.id.kanyiGN_D_jieshi);

    }

    public  void returnZhuye(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/updHasDanCi";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                String gongneng="2";
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
                                Toast.makeText(Kanyixuanci.this,"颜色记录失败",Toast.LENGTH_LONG).show();
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
                String gongneng="2";
                String siliujistr=Integer.toString(USERCIKU);
                String yanse="2";
                params.put("name",USERNAME);
                params.put("xiaociku",Integer.toString(num));
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
                                Toast.makeText(Kanyixuanci.this,"颜色记录失败",Toast.LENGTH_LONG).show();
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
                            if(DC1.equals("1")){
                                Toast.makeText(Kanyixuanci.this,"该词库全部记忆完成",Toast.LENGTH_LONG).show();
                                allDoneGaiyanse();
                                Intent intent =new Intent(Kanyixuanci.this,Zhuye.class);
                                startActivity(intent);
                            }
                            String DCyisi1 = (String) json.get("DCyisi");

                            String DCyinbiao = (String) json.get("DCyinbiao");
                            String DC2 = (String) json.get("DC2");
                            String DC3 = (String) json.get("DC3");
                            String DC4 = (String) json.get("DC4");
                            String DCyisi2 = (String) json.get("DCyisi2");
                            String DCyisi3 = (String) json.get("DCyisi3");
                            String DCyisi4 = (String) json.get("DCyisi4");
                            String strxuhao=Integer.toString(dcxh);
                            number.setText(strxuhao);
                            ZhuDC.setText(DCyisi1);
                            DCnow=DC1;
                            List<String> lst = new ArrayList<String>();
                            lst.add(DC1+"?"+DCyisi1);
                            lst.add(DC2+"?"+DCyisi2);
                            lst.add(DC3+"?"+DCyisi3);
                            lst.add(DC4+"?"+DCyisi4);
                            Collections.shuffle(lst);
                            int j=1;
                            for (int i = 0; i < lst.size(); i++) {
                                if(i==0){
                                    A.setText(lst.get(i).substring(0,lst.get(i).indexOf("?")));
                                    A_jieshi.setText(lst.get(i).substring(lst.get(i).indexOf("?")+1));
                                }
                                else if(i==1){
                                    B.setText(lst.get(i).substring(0,lst.get(i).indexOf("?")));
                                    B_jieshi.setText(lst.get(i).substring(lst.get(i).indexOf("?")+1));
                                }
                                else if(i==2){
                                    C.setText(lst.get(i).substring(0,lst.get(i).indexOf("?")));
                                    C_jieshi.setText(lst.get(i).substring(lst.get(i).indexOf("?")+1));
                                }
                                else if(i==3){
                                    D.setText(lst.get(i).substring(0,lst.get(i).indexOf("?")));
                                    D_jieshi.setText(lst.get(i).substring(lst.get(i).indexOf("?")+1));
                                }
                            }




                            A.setBackgroundColor(Color.parseColor("#ffffff"));
                            B.setBackgroundColor(Color.parseColor("#ffffff"));
                            C.setBackgroundColor(Color.parseColor("#ffffff"));
                            D.setBackgroundColor(Color.parseColor("#ffffff"));
                            Log.w("Hyx", "qianhou0:"+qianhou);
                            if(qianhou==-1||qianhou==1){
                                xianshiTA();
                                qianhou=0;
                            }
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
                params.put("DCciku",DCciku);
                params.put("DCxh",DCxh);
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
                            String DC1 = (String) json.get("DC1");
                            String DCyisi1 = (String) json.get("DCyisi");
                            String result = (String) json.get("result");
                            String DCyinbiao = (String) json.get("DCyinbiao");
                            String DC2 = (String) json.get("DC2");
                            String DC3 = (String) json.get("DC3");
                            String DC4 = (String) json.get("DC4");
                            String DCyisi2 = (String) json.get("DCyisi2");
                            String DCyisi3 = (String) json.get("DCyisi3");
                            String DCyisi4 = (String) json.get("DCyisi4");
                            String strxuhao=Integer.toString(dcxh);
                            if(result.equals("fail")){
                                Toast.makeText(Kanyixuanci.this,"该词库全部记忆完成",Toast.LENGTH_LONG).show();
                                Intent intent =new Intent(Kanyixuanci.this,Zhuye.class);
                                startActivity(intent);
                            }
                            number.setText(strxuhao);
                            ZhuDC.setText(DCyisi1);
                            DCnow=DC1;
                            Log.w("Hyx","6-25DC1:"+DC1);
                            List<String> lst = new ArrayList<String>();
                            lst.add(DC1+"?"+DCyisi1);
                            lst.add(DC2+"?"+DCyisi2);
                            lst.add(DC3+"?"+DCyisi3);
                            lst.add(DC4+"?"+DCyisi4);
                            Collections.shuffle(lst);
                            int j=1;
                            for (int i = 0; i < lst.size(); i++) {
                                if(i==0){
                                    A.setText(lst.get(i).substring(0,lst.get(i).indexOf("?")));
                                    A_jieshi.setText(lst.get(i).substring(lst.get(i).indexOf("?")+1));
                                }
                                else if(i==1){
                                    B.setText(lst.get(i).substring(0,lst.get(i).indexOf("?")));
                                    B_jieshi.setText(lst.get(i).substring(lst.get(i).indexOf("?")+1));
                                }
                                else if(i==2){
                                    C.setText(lst.get(i).substring(0,lst.get(i).indexOf("?")));
                                    C_jieshi.setText(lst.get(i).substring(lst.get(i).indexOf("?")+1));
                                }
                                else if(i==3){
                                    D.setText(lst.get(i).substring(0,lst.get(i).indexOf("?")));
                                    D_jieshi.setText(lst.get(i).substring(lst.get(i).indexOf("?")+1));
                                }
                            }
                  /*          A_jieshi.setVisibility(View.INVISIBLE);
                            B_jieshi.setVisibility(View.INVISIBLE);
                            C_jieshi.setVisibility(View.INVISIBLE);
                            D_jieshi.setVisibility(View.INVISIBLE);*/
                            A.setBackgroundColor(Color.parseColor("#ffffff"));
                            B.setBackgroundColor(Color.parseColor("#ffffff"));
                            C.setBackgroundColor(Color.parseColor("#ffffff"));
                            D.setBackgroundColor(Color.parseColor("#ffffff"));
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
                                Toast.makeText(Kanyixuanci.this,"已存在于重点词库中",Toast.LENGTH_SHORT).show();
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
                String DC=ZhuDC.getText().toString();
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


    public  void setEnanbleF(){
        A.setEnabled(false);
        B.setEnabled(false);
        C.setEnabled(false);
        D.setEnabled(false);
    }

    public  void setEnanbleT(){
        A.setEnabled(true);
        B.setEnabled(true);
        C.setEnabled(true);
        D.setEnabled(true);
    }

    public void updFenshu( ){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/updChenJi";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                String name=USERNAME;
                String gongneng="2";
                String siliuji=Integer.toString(USERCIKU);
                params.put("name",name);
                params.put("gongneng",gongneng);
                params.put("siliuji",siliuji);
                params.put("xiaociku",xiaociku);
                Log.w("Hyx", "所得总分为："+zongfen);
                params.put("fenshu",Integer.toString(zongfen));
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

    public void xianshiJS(){
        A_jieshi.setVisibility(View.VISIBLE);
        B_jieshi.setVisibility(View.VISIBLE);
        C_jieshi.setVisibility(View.VISIBLE);
        D_jieshi.setVisibility(View.VISIBLE);
        voice.setVisibility(View.VISIBLE);
    }

    public void yincangJS(){
        A_jieshi.setVisibility(View.INVISIBLE);
        B_jieshi.setVisibility(View.INVISIBLE);
        C_jieshi.setVisibility(View.INVISIBLE);
        D_jieshi.setVisibility(View.INVISIBLE);
        voice.setVisibility(View.INVISIBLE);
    }

    public void xianshiTA(){//显示正确的答案

        if(A_jieshi.getText().toString().equals(DCnow)){
            Log.w("Hyx", "正确答案A");
            A.setBackgroundColor(Color.parseColor("#33c930"));//绿色
        }
        else if(B_jieshi.getText().toString().equals(DCnow)){
            Log.w("Hyx", "正确答案B");
            B.setBackgroundColor(Color.parseColor("#33c930"));//绿色
        }
        else if(C_jieshi.getText().toString().equals(DCnow)){
            Log.w("Hyx", "正确答案C");
            C.setBackgroundColor(Color.parseColor("#33c930"));//绿色
        }
        else if(D_jieshi.getText().toString().equals(DCnow)){
            Log.w("Hyx", "正确答案D");
            D.setBackgroundColor(Color.parseColor("#33c930"));//绿色
        }
    }

    public void if_mark(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/hasPoDanCi";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                String name=USERNAME;
                String dcnow=DCnow;
                String siliuji=Integer.toString(USERCIKU);
                params.put("name",name);
                params.put("DC",dcnow);
                params.put("DCciku",siliuji);
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
