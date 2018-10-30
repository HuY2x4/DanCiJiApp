package com.example.danciji1_0;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.danciji1_0.UserState.USERCIKU;

public class PaiHB extends AppCompatActivity {
    Button fanhui;
    Button qian,hou;
    TextView leibie;
    TextView biaoti;
    TextView name_1,name_2,name_3,name_4,name_5,name_6,name_7,name_8,name_9,name_10;
    TextView zf_1,zf_2,zf_3,zf_4,zf_5,zf_6,zf_7,zf_8,zf_9,zf_10;
    TextView _1,_2,_3,_4,_5,_6,_7,_8,_9,_10;
    String leibienow="";
    List<TextView> listName = new ArrayList<TextView>();
    List<TextView> listZF = new ArrayList<TextView>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paihangb);
        setfind();
        gaiziti();
        Log.w("Hyx",Integer.toString(USERCIKU) );
        if(USERCIKU==4){
            leibie.setText("四级看词选意");
            leibienow="四级看词选意";
            chaPM();
        }
        else if (USERCIKU==6){
            leibie.setText("六级看词选意");
            leibienow="六级看词选意";
            chaPM();
        }


        if(leibie.getText().toString().equals("四级看词选意")){
            qian.setVisibility(View.INVISIBLE);
        }

        if(leibie.getText().toString().equals("六级看意选词")){
            hou.setVisibility(View.INVISIBLE);
        }


        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(PaiHB.this,Zhuye.class);
                startActivity(intent);
            }
        });

        qian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leibie.getText().toString().equals("四级看意选词")){     // 四级看词选意  四级看意选词  六级看词选意  六级看意选词
                    leibie.setText("四级看词选意");
                    leibienow="四级看词选意";
                    qian.setVisibility(View.INVISIBLE);
                    chaPM();
                }
                else if(leibie.getText().toString().equals("六级看词选意")){
                    leibie.setText("四级看意选词");
                    leibienow="四级看意选词";
                    chaPM();
                }
                else if(leibie.getText().toString().equals("六级看意选词")){
                    leibie.setText("六级看词选意");
                    leibienow="六级看词选意";
                    hou.setVisibility(View.VISIBLE);
                    chaPM();
                }

            }
        });


        hou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leibie.getText().toString().equals("四级看词选意")){     // 四级看词选意  四级看意选词  六级看词选意  六级看意选词
                    leibie.setText("四级看意选词");
                    leibienow="四级看意选词";
                    qian.setVisibility(View.VISIBLE);
                    chaPM();
                }
                else if(leibie.getText().toString().equals("四级看意选词")){
                    leibie.setText("六级看词选意");
                    leibienow="六级看词选意";
                    chaPM();
                }
                else if(leibie.getText().toString().equals("六级看词选意")){
                    leibie.setText("六级看意选词");
                    leibienow="六级看意选词";
                    hou.setVisibility(View.INVISIBLE);
                    chaPM();
                }

            }
        });


    }

    public void setfind(){
        biaoti=(TextView)findViewById(R.id.PH_biaoti);
        fanhui=(Button)findViewById(R.id.PH_fanhui);
        qian=(Button)findViewById(R.id.PH_qian);
        hou=(Button)findViewById(R.id.PH_hou);
        leibie=(TextView)findViewById(R.id.PH_leibie);
        name_1=(TextView)findViewById(R.id.PH_1_name);
        name_2=(TextView)findViewById(R.id.PH_2_name);
        name_3=(TextView)findViewById(R.id.PH_3_name);
        name_4=(TextView)findViewById(R.id.PH_4_name);
        name_5=(TextView)findViewById(R.id.PH_5_name);
        name_6=(TextView)findViewById(R.id.PH_6_name);
        name_7=(TextView)findViewById(R.id.PH_7_name);
        name_8=(TextView)findViewById(R.id.PH_8_name);
        name_9=(TextView)findViewById(R.id.PH_9_name);
        name_10=(TextView)findViewById(R.id.PH_10_name);
        zf_1=(TextView)findViewById(R.id.PH_1_zf);
        zf_2=(TextView)findViewById(R.id.PH_2_zf);
        zf_3=(TextView)findViewById(R.id.PH_3_zf);
        zf_4=(TextView)findViewById(R.id.PH_4_zf);
        zf_5=(TextView)findViewById(R.id.PH_5_zf);
        zf_6=(TextView)findViewById(R.id.PH_6_zf);
        zf_7=(TextView)findViewById(R.id.PH_7_zf);
        zf_8=(TextView)findViewById(R.id.PH_8_zf);
        zf_9=(TextView)findViewById(R.id.PH_9_zf);
        zf_10=(TextView)findViewById(R.id.PH_10_zf);
        _1=(TextView)findViewById(R.id.PH_1);
        _2=(TextView)findViewById(R.id.PH_2);
        _3=(TextView)findViewById(R.id.PH_3);
        _4=(TextView)findViewById(R.id.PH_4);
        _5=(TextView)findViewById(R.id.PH_5);
        _6=(TextView)findViewById(R.id.PH_6);
        _7=(TextView)findViewById(R.id.PH_7);
        _8=(TextView)findViewById(R.id.PH_8);
        _9=(TextView)findViewById(R.id.PH_9);
        _10=(TextView)findViewById(R.id.PH_10);
        listName.add(name_1);
        listName.add(name_2);
        listName.add(name_3);
        listName.add(name_4);
        listName.add(name_5);
        listName.add(name_6);
        listName.add(name_7);
        listName.add(name_8);
        listName.add(name_9);
        listName.add(name_10);
        listZF.add(zf_1);
        listZF.add(zf_2);
        listZF.add(zf_3);
        listZF.add(zf_4);
        listZF.add(zf_5);
        listZF.add(zf_6);
        listZF.add(zf_7);
        listZF.add(zf_8);
        listZF.add(zf_9);
        listZF.add(zf_10);
    }

    public void gaiziti(){
        biaoti.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        leibie.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        name_1.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        name_2.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        name_3.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        name_4.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        name_5.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        name_6.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        name_7.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        name_8.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        name_9.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        name_10.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        _1.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        _2.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        _3.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        _4.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        _5.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        _6.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        _7.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        _8.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        _9.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        _10.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));

    }

    public void chaPM(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/chaZFpm";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面

                params.put("leibie",leibienow);
                //你的代码放这上面

                String result = HttpUtilsHttpURLConnection.getContextByHttp(url,params);
                Log.w("Hyx",result );
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
                            String result = (String) json.get("result");
                            //你的代码放这下面
                            String[] zhong=new String[300];
                            String[] name=new String[300];
                            String[] zf=new String[300];
                            String[] namezf=result.split("#");
                            for(int i=0;i<namezf.length;i++){
                                zhong=namezf[i].split(";");
                                name[i]=zhong[0];
                                zf[i]=zhong[1];
                            }

                            for(int i=0; i<10; i++){
                                TextView Name = listName.get(i);
                                TextView ZF = listZF.get(i);
                                Name.setText(name[i]);
                                ZF.setText(zf[i]);
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
