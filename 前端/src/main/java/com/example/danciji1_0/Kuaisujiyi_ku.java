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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.danciji1_0.UserState.GONGNENGNOW;
import static com.example.danciji1_0.UserState.USERCIKU;
import static com.example.danciji1_0.UserState.USERNAME;

public class Kuaisujiyi_ku extends AppCompatActivity implements View.OnClickListener {
    Button fanhui;
    Button shang;
    Button xia;
    Button pointck;
    Button ck1,ck2,ck3,ck4,ck5,ck6,ck7,ck8,ck9,ck10,ck11,ck12,ck13,ck14,ck15,ck16,ck17,ck18,ck19,ck20;
    TextView ks_count;
    TextView biaoti;
    List<Button> list = new ArrayList<Button>();
    int siliuji=USERCIKU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuaisujiyi_ku);
        setfind();
        ckset();
        gaiyanse();
        Log.w("Hyx", Integer.toString(USERCIKU));
        countPoDC();
        biaoti.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        pointck.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        shang.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        xia.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));


        if(USERNAME.equals("")){
            pointck.setVisibility(View.INVISIBLE);
            ks_count.setVisibility(View.INVISIBLE);
        }
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(Kuaisujiyi_ku.this,Zhuye.class);
                startActivity(intent);
            }
        });



        pointck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countPoDC_clear();
                Log.w("Hyx", "进重点词库1");
                Intent intentP =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
                Log.w("Hyx", "进重点词库2");
                intentP.putExtra("if_point","1");
                intentP.putExtra("num","1");
                Log.w("Hyx", "进重点词库3");
                startActivity(intentP);
            }
        });


        xia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String num=ck1.getText().toString();
            String maxnum4="141";
            String maxnum6="261";
            if(siliuji==4){
                if(num.equals(maxnum4)){
                    Toast.makeText(Kuaisujiyi_ku.this,"最后一页！",Toast.LENGTH_SHORT).show();
                }
                else{
                    next();
                }
            }
            else if(siliuji==6){
                if(num.equals(maxnum6)){
                    Toast.makeText(Kuaisujiyi_ku.this,"最后一页！",Toast.LENGTH_SHORT).show();
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
                String num=ck1.getText().toString();
                if(num.equals("1")){
                    Toast.makeText(Kuaisujiyi_ku.this,"已经是第一页！",Toast.LENGTH_SHORT).show();
                }
                else{
                    xianShi();
                    last();

                }

            }
        });

    }

    public void setfind(){
        biaoti=(TextView) findViewById(R.id.fastXCK_biaoti);
        ks_count=(TextView) findViewById(R.id.fastXCK_count);
        fanhui=(Button)findViewById(R.id.fastXCK_fanhui);
        shang=(Button)findViewById(R.id.fastXCK_shang);
        xia=(Button)findViewById(R.id.fastXCK_xia);
        pointck=(Button)findViewById(R.id.fastXCK_point);
        ck1=(Button)findViewById(R.id.fastXCK_ck1);
        ck2=(Button)findViewById(R.id.fastXCK_ck2);
        ck3=(Button)findViewById(R.id.fastXCK_ck3);
        ck4=(Button)findViewById(R.id.fastXCK_ck4);
        ck5=(Button)findViewById(R.id.fastXCK_ck5);
        ck6=(Button)findViewById(R.id.fastXCK_ck6);
        ck7=(Button)findViewById(R.id.fastXCK_ck7);
        ck8=(Button)findViewById(R.id.fastXCK_ck8);
        ck9=(Button)findViewById(R.id.fastXCK_ck9);
        ck10=(Button)findViewById(R.id.fastXCK_ck10);
        ck11=(Button)findViewById(R.id.fastXCK_ck11);
        ck12=(Button)findViewById(R.id.fastXCK_ck12);
        ck13=(Button)findViewById(R.id.fastXCK_ck13);
        ck14=(Button)findViewById(R.id.fastXCK_ck14);
        ck15=(Button)findViewById(R.id.fastXCK_ck15);
        ck16=(Button)findViewById(R.id.fastXCK_ck16);
        ck17=(Button)findViewById(R.id.fastXCK_ck17);
        ck18=(Button)findViewById(R.id.fastXCK_ck18);
        ck19=(Button)findViewById(R.id.fastXCK_ck19);
        ck20=(Button)findViewById(R.id.fastXCK_ck20);
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

    public void next(){
        if(siliuji==4) {

            if (Integer.parseInt(ck1.getText().toString())<121) {//倒数第二页//4级共7页+10库//6级13  4
                Log.w("Hyx",ck1.getText().toString() );
                Log.w("Hyx", "前页");
                for(int i=0; i<list.size(); i++){
                    Button btn = list.get(i);
                    btn.setText(Integer.toString(Integer.parseInt(btn.getText().toString()) + 20));
                }
                gaiyanse();

            } else {
                Log.w("Hyx", "最后一页");
                for(int i=0; i<list.size(); i++){
                    Button btn = list.get(i);
                    btn.setText(Integer.toString(Integer.parseInt(btn.getText().toString()) + 20));
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

                gaiyanse();

            }
        }
        else if(siliuji==6){
            if (Integer.parseInt(ck1.getText().toString())<241) {//倒数第二页
                for(int i=0; i<list.size(); i++){
                    Button btn = list.get(i);
                    btn.setText(Integer.toString(Integer.parseInt(btn.getText().toString()) + 20));

                    gaiyanse();
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

                gaiyanse();
            }
        }

    }

    public void last(){
        for(int i=0; i<list.size(); i++){
            Button btn = list.get(i);
            btn.setText(Integer.toString(Integer.parseInt(btn.getText().toString()) - 20));

            gaiyanse();
        }
    }

    public void gaiyanse(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/chaHasDanCi";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                String name=USERNAME;
                String gongneng="3";
                String siliuji=Integer.toString(USERCIKU);
                params.put("name",name);
                params.put("gongneng",gongneng);
                params.put("siliuji",siliuji);
                //你的代码放这上面
                Log.w("Hyx", "qian");
                String result = HttpUtilsHttpURLConnection.getContextByHttp(url,params);
                Log.w("Hyx", "hou");
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
                            JSONObject json= new JSONObject(key);
                            String result = (String) json.get("result");
                            Log.w("Hyx", "1111111");
                            Log.w("Hyx", result);
                            //你的代码放这下面

                            String[] cikuhas=result.split(";");

                            for(int i=0; i<list.size(); i++){
                                Button btn = list.get(i);
                                int num=Integer.parseInt(btn.getText().toString());
                                if(cikuhas[num-1].equals("0")){
                                    btn.setBackgroundColor((Color.parseColor("#ffffff")));
                                }else if(cikuhas[num-1].equals("1")){
                                    btn.setBackgroundColor((Color.parseColor("#aba1a1")));
                                }else if(cikuhas[num-1].equals("2")){
                                    btn.setBackgroundColor((Color.parseColor("#2eca66")));
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




    public void ckset() {
        ck1.setOnClickListener(this);
        ck2.setOnClickListener(this);
        ck3.setOnClickListener(this);
        ck4.setOnClickListener(this);
        ck5.setOnClickListener(this);
        ck6.setOnClickListener(this);
        ck7.setOnClickListener(this);
        ck8.setOnClickListener(this);
        ck9.setOnClickListener(this);
        ck10.setOnClickListener(this);
        ck11.setOnClickListener(this);
        ck12.setOnClickListener(this);
        ck13.setOnClickListener(this);
        ck14.setOnClickListener(this);
        ck15.setOnClickListener(this);
        ck16.setOnClickListener(this);
        ck17.setOnClickListener(this);
        ck18.setOnClickListener(this);
        ck19.setOnClickListener(this);
        ck20.setOnClickListener(this);
        ck1.setTag(1);
        ck2.setTag(2);
        ck3.setTag(3);
        ck4.setTag(4);
        ck5.setTag(5);
        ck6.setTag(6);
        ck7.setTag(7);
        ck8.setTag(8);
        ck9.setTag(9);
        ck10.setTag(10);
        ck11.setTag(11);
        ck12.setTag(12);
        ck13.setTag(13);
        ck14.setTag(14);
        ck15.setTag(15);
        ck16.setTag(16);
        ck17.setTag(17);
        ck18.setTag(18);
        ck19.setTag(19);
        ck20.setTag(20);
    }









    @Override
    public void onClick(View v) {
       int tag=(Integer) v.getTag();
       switch (tag){
           case 1:
               String num1=ck1.getText().toString();
               Intent intent1 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent1.putExtra("num",num1);
               intent1.putExtra("if_point","0");
               startActivity(intent1);
               break;
           case 2:
               String num2=ck2.getText().toString();
               Intent intent2 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent2.putExtra("num",num2);
               intent2.putExtra("if_point","0");
               startActivity(intent2);
               break;
           case 3:
               String num3=ck3.getText().toString();
               Intent intent3 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent3.putExtra("num",num3);
               intent3.putExtra("if_point","0");
               startActivity(intent3);
               break;
           case 4:
               String num4=ck4.getText().toString();
               Intent intent4 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent4.putExtra("num",num4);
               intent4.putExtra("if_point","0");
               startActivity(intent4);
               break;
           case 5:
               String num5=ck5.getText().toString();
               Intent intent5 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent5.putExtra("num",num5);
               intent5.putExtra("if_point","0");
               startActivity(intent5);
               break;
           case 6:
               String num6=ck6.getText().toString();
               Intent intent6 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent6.putExtra("num",num6);
               intent6.putExtra("if_point","0");
               startActivity(intent6);
               break;
           case 7:
               String num7=ck7.getText().toString();
               Intent intent7 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent7.putExtra("num",num7);
               intent7.putExtra("if_point","0");
               startActivity(intent7);
               break;
           case 8:
               String num8=ck8.getText().toString();
               Intent intent8 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent8.putExtra("num",num8);
               intent8.putExtra("if_point","0");
               startActivity(intent8);
               break;
           case 9:
               String num9=ck9.getText().toString();
               Intent intent9 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent9.putExtra("num",num9);
               intent9.putExtra("if_point","0");
               startActivity(intent9);
               break;
           case 10:
               String num10=ck10.getText().toString();
               Intent intent10 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent10.putExtra("num",num10);
               intent10.putExtra("if_point","0");
               startActivity(intent10);
               break;
           case 11:
               String num11=ck11.getText().toString();
               Intent intent11 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent11.putExtra("num",num11);
               intent11.putExtra("if_point","0");
               startActivity(intent11);
               break;
           case 12:
               String num12=ck12.getText().toString();
               Intent intent12 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent12.putExtra("num",num12);
               intent12.putExtra("if_point","0");
               startActivity(intent12);
               break;
           case 13:
               String num13=ck13.getText().toString();
               Intent intent13 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent13.putExtra("num",num13);
               intent13.putExtra("if_point","0");
               startActivity(intent13);
               break;
           case 14:
               String num14=ck14.getText().toString();
               Intent intent14 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent14.putExtra("num",num14);
               intent14.putExtra("if_point","0");
               startActivity(intent14);
               break;
           case 15:
               String num15=ck15.getText().toString();
               Intent intent15 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent15.putExtra("num",num15);
               intent15.putExtra("if_point","0");
               startActivity(intent15);
               break;
           case 16:
               String num16=ck16.getText().toString();
               Intent intent16 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent16.putExtra("num",num16);
               intent16.putExtra("if_point","0");
               startActivity(intent16);
               break;
           case 17:
               String num17=ck17.getText().toString();
               Intent intent17 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent17.putExtra("num",num17);
               intent17.putExtra("if_point","0");
               startActivity(intent17);
               break;
           case 18:
               String num18=ck18.getText().toString();
               Intent intent18 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent18.putExtra("num",num18);
               intent18.putExtra("if_point","0");
               startActivity(intent18);
               break;
           case 19:
               String num19=ck19.getText().toString();
               Intent intent19 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent19.putExtra("num",num19);
               intent19.putExtra("if_point","0");
               startActivity(intent19);
               break;
           case 20:
               String num20=ck20.getText().toString();
               Intent intent20 =new Intent(Kuaisujiyi_ku.this,Kuaisujiyi.class);
               intent20.putExtra("num",num20);
               intent20.putExtra("if_point","0");
               startActivity(intent20);
               break;

           default:
               break;


       }










            /*Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            System.out.println(bundle.getInt("test1"));*///另一边取数据的代码



}

    public void xianShi(){
        for(int i=0; i<list.size(); i++){
            Button btn = list.get(i);
            btn.setVisibility(View.VISIBLE);

        }
    }

    public  void countPoDC(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/countPoDanCi";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面

                params.put("name",USERNAME);
                params.put("DCciku",Integer.toString(USERCIKU));
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
                            String count = (String) json.get("count");

                            if(count.equals("0")){
                                ks_count.setVisibility(View.INVISIBLE);
                            }
                            else{
                                if(!USERNAME.equals("")){
                                    ks_count.setVisibility(View.VISIBLE);
                                    ks_count.setText(count);
                                }

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


    public  void countPoDC_clear(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/clearPoDanCi";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                params.put("name",USERNAME);
                params.put("DCciku",Integer.toString(USERCIKU));

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

                            }
                            else{

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



}
