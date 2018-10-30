package com.example.danciji1_0;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.danciji1_0.UserState.GONGNENGNOW;
import static com.example.danciji1_0.UserState.USERCIKU;
import static com.example.danciji1_0.UserState.USERNAME;

public class Chenji  extends AppCompatActivity {
    Button fanhui;
    Button shang;
    Button xia;
    TextView biaoti;
    TextView ck1,ck2,ck3,ck4,ck5,ck6,ck7,ck8,ck9,ck10,ck11,ck12,ck13,ck14,ck15,ck16,ck17,ck18,ck19,ck20;
    List<TextView> list = new ArrayList<TextView>();
    int siliuji=USERCIKU;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chenji);
        setfind();
        chaCJ();
        biaoti.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        shang.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        xia.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GONGNENGNOW==1){
                    Intent intent =new Intent(Chenji.this,Kancixuanyi_ku.class);
                    startActivity(intent);
                }
                else{
                    Intent intent =new Intent(Chenji.this,Kanyixuanci_ku.class);
                    startActivity(intent);
                }


            }
        });

        xia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num=ck1.getText().toString().substring(0,ck1.getText().toString().indexOf("."));
                Log.w("chenji", num);
                String maxnum4="141";
                String maxnum6="261";
                if(siliuji==4){
                    if(num.equals(maxnum4)){
                        Toast.makeText(Chenji.this,"最后一页！",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        next();
                    }
                }
                else if(siliuji==6){
                    if(num.equals(maxnum6)){
                        Toast.makeText(Chenji.this,"最后一页！",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        next();
                    }
                }


            }
        });





        shang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num=ck1.getText().toString().substring(0,ck1.getText().toString().indexOf("."));
                if(num.equals("1")){
                    Toast.makeText(Chenji.this,"已经是第一页！",Toast.LENGTH_SHORT).show();
                }
                else{
                    xianShi();
                    last();

                }

            }
        });

    }


    public void setfind(){
        biaoti=(TextView)findViewById(R.id.chenji_biaoti);
        fanhui=(Button)findViewById(R.id.chenji_fanhui);
        shang=(Button)findViewById(R.id.chenji_shang);
        xia=(Button)findViewById(R.id.chenji_xia);
        ck1=(TextView)findViewById(R.id.chenji_1);
        ck2=(TextView)findViewById(R.id.chenji_2);
        ck3=(TextView)findViewById(R.id.chenji_3);
        ck4=(TextView)findViewById(R.id.chenji_4);
        ck5=(TextView)findViewById(R.id.chenji_5);
        ck6=(TextView)findViewById(R.id.chenji_6);
        ck7=(TextView)findViewById(R.id.chenji_7);
        ck8=(TextView)findViewById(R.id.chenji_8);
        ck9=(TextView)findViewById(R.id.chenji_9);
        ck10=(TextView)findViewById(R.id.chenji_10);
        ck11=(TextView)findViewById(R.id.chenji_11);
        ck12=(TextView)findViewById(R.id.chenji_12);
        ck13=(TextView)findViewById(R.id.chenji_13);
        ck14=(TextView)findViewById(R.id.chenji_14);
        ck15=(TextView)findViewById(R.id.chenji_15);
        ck16=(TextView)findViewById(R.id.chenji_16);
        ck17=(TextView)findViewById(R.id.chenji_17);
        ck18=(TextView)findViewById(R.id.chenji_18);
        ck19=(TextView)findViewById(R.id.chenji_19);
        ck20=(TextView)findViewById(R.id.chenji_20);
        list.add(ck1);
        list.add(ck2);
        list.add(ck3);
        list.add(ck4);
        list.add(ck5);
        list.add(ck6);
        list.add(ck7);
        list.add(ck8);
        list.add(ck9);
        list.add(ck10);
        list.add(ck11);
        list.add(ck12);
        list.add(ck13);
        list.add(ck14);
        list.add(ck15);
        list.add(ck16);
        list.add(ck17);
        list.add(ck18);
        list.add(ck19);
        list.add(ck20);
    }


    public void chaCJ(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/chaChenJi";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                String name=USERNAME;
                String gongneng=Integer.toString(GONGNENGNOW);
                String siliuji=Integer.toString(USERCIKU);
                params.put("name",name);
                params.put("gongneng",gongneng);
                params.put("siliuji",siliuji);
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

                            String[] cikuhas=result.split(";");

                            for(int i=0; i<list.size(); i++){
                                TextView btn = list.get(i);

                                int num=Integer.parseInt(btn.getText().toString().substring(0,btn.getText().toString().indexOf(".")));
                                String fenshu=cikuhas[num-1];
                                String text=Integer.toString(num)+".\n"+fenshu;
                                btn.setText(text);
                                if(Integer.parseInt(fenshu)<60){
                                    btn.setBackgroundColor((Color.parseColor("#bcb6b6")));//灰色
                                }else if(Integer.parseInt(fenshu)>=60&&Integer.parseInt(fenshu)<90){
                                    btn.setBackgroundColor((Color.parseColor("#86a352")));//深绿色
                                }else if(Integer.parseInt(fenshu)>=90){
                                    btn.setBackgroundColor((Color.parseColor("#55e126")));//绿色
                                }

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

    public void next(){
        int ck1num=Integer.parseInt(ck1.getText().toString().substring(0,ck1.getText().toString().indexOf(".")));
        if(siliuji==4) {

            if (ck1num<121) {//倒数第二页//4级共7页+10库//6级13  4

                for(int i=0; i<list.size(); i++){
                    TextView btn = list.get(i);
                    String newnum=Integer.toString(Integer.parseInt(btn.getText().toString().substring(0,btn.getText().toString().indexOf(".")))+20);
                    String oldtext=btn.getText().toString().substring(btn.getText().toString().indexOf("."));
                    btn.setText(newnum+oldtext);
                }
                chaCJ();

            } else {

                for(int i=0; i<list.size(); i++){
                    TextView btn = list.get(i);
                    String newnum=Integer.toString(Integer.parseInt(btn.getText().toString().substring(0,btn.getText().toString().indexOf(".")))+20);
                    String oldtext=btn.getText().toString().substring(btn.getText().toString().indexOf("."));
                    btn.setText(newnum+oldtext);
                }
                ck11.setVisibility(View.INVISIBLE);
                ck12.setVisibility(View.INVISIBLE);
                ck13.setVisibility(View.INVISIBLE);
                ck14.setVisibility(View.INVISIBLE);
                ck15.setVisibility(View.INVISIBLE);
                ck16.setVisibility(View.INVISIBLE);
                ck17.setVisibility(View.INVISIBLE);
                ck18.setVisibility(View.INVISIBLE);
                ck19.setVisibility(View.INVISIBLE);
                ck20.setVisibility(View.INVISIBLE);

                chaCJ();

            }
        }
        else if(siliuji==6){
            if (ck1num<241) {//倒数第二页
                for(int i=0; i<list.size(); i++){
                    TextView btn = list.get(i);
                    String newnum=Integer.toString(Integer.parseInt(btn.getText().toString().substring(0,btn.getText().toString().indexOf(".")))+20);
                    String oldtext=btn.getText().toString().substring(btn.getText().toString().indexOf("."));
                    btn.setText(newnum+oldtext);

                    chaCJ();
                }
            } else {
                ck1.setText(Integer.toString(Integer.parseInt(ck1.getText().toString()) + 20));
                ck2.setText(Integer.toString(Integer.parseInt(ck2.getText().toString()) + 20));
                ck3.setText(Integer.toString(Integer.parseInt(ck3.getText().toString()) + 20));
                ck4.setText(Integer.toString(Integer.parseInt(ck4.getText().toString()) + 20));
                ck5.setVisibility(View.INVISIBLE);
                ck6.setVisibility(View.INVISIBLE);
                ck7.setVisibility(View.INVISIBLE);
                ck8.setVisibility(View.INVISIBLE);
                ck9.setVisibility(View.INVISIBLE);
                ck10.setVisibility(View.INVISIBLE);
                ck11.setVisibility(View.INVISIBLE);
                ck12.setVisibility(View.INVISIBLE);
                ck13.setVisibility(View.INVISIBLE);
                ck14.setVisibility(View.INVISIBLE);
                ck15.setVisibility(View.INVISIBLE);
                ck16.setVisibility(View.INVISIBLE);
                ck17.setVisibility(View.INVISIBLE);
                ck18.setVisibility(View.INVISIBLE);
                ck19.setVisibility(View.INVISIBLE);
                ck20.setVisibility(View.INVISIBLE);

                chaCJ();
            }
        }

    }

    public void last(){
        for(int i=0; i<list.size(); i++){
            TextView btn = list.get(i);
            String newnum=Integer.toString(Integer.parseInt(btn.getText().toString().substring(0,btn.getText().toString().indexOf(".")))-20);
            String oldtext=btn.getText().toString().substring(btn.getText().toString().indexOf("."));
            btn.setText(newnum+oldtext);

            chaCJ();
        }
    }

    public void xianShi(){
        for(int i=0; i<list.size(); i++){
            TextView btn = list.get(i);
            btn.setVisibility(View.VISIBLE);

        }
    }



}



