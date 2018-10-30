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

import com.example.danciji1_0.zhuye_caidan.ButtonData;
import com.example.danciji1_0.zhuye_caidan.ButtonEventListener;
import com.example.danciji1_0.zhuye_caidan.SectorMenuButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.danciji1_0.UserState.GONGNENGNOW;
import static com.example.danciji1_0.UserState.USERCIKU;
import static com.example.danciji1_0.UserState.USERNAME;
import static com.example.danciji1_0.UserState.USERPASSWORD;
import static com.example.danciji1_0.UserState.ZF4_1;
import static com.example.danciji1_0.UserState.ZF4_2;
import static com.example.danciji1_0.UserState.ZF6_1;
import static com.example.danciji1_0.UserState.ZF6_2;


public class Zhuye extends AppCompatActivity {
Button gonggao;
Button shezhi;
Button bangzhu;
Button kuaisujiyi;
Button kancixuanyi;
Button kanyixuanci;
Button gerenzhongxin;
Button paihangbang;
TextView danciji;
    List<TextView> list = new ArrayList<TextView>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuye);
        danciji=(TextView)findViewById(R.id.ZY_danciji);
        kuaisujiyi=(Button)findViewById(R.id.ZY_kuaisujiyi);
        kancixuanyi=(Button)findViewById(R.id.ZY_kancixuanyi);
        kanyixuanci=(Button)findViewById(R.id.ZY_kanyixuanci);
     /*   gonggao=(Button)findViewById(R.id.ZY_gonggao);
        shezhi=(Button)findViewById(R.id.ZY_shezhi);
        bangzhu=(Button)findViewById(R.id.ZY_bangzhu);*/
        paihangbang=(Button)findViewById(R.id.ZY_paihang);
        gerenzhongxin=(Button)findViewById(R.id.ZY_gerenzhongxin);

        initRightSectorMenuButton();

        danciji.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        kuaisujiyi.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        kancixuanyi.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        kanyixuanci.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        paihangbang.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));

        kuaisujiyi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent =new Intent(Zhuye.this,Kuaisujiyi_ku.class);
                startActivity(intent);
            }
        });

        kancixuanyi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent =new Intent(Zhuye.this,Kancixuanyi_ku.class);
                startActivity(intent);
            }
        });

        kanyixuanci.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent =new Intent(Zhuye.this,Kanyixuanci_ku.class);
                startActivity(intent);
            }
        });

      /*  gonggao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent =new Intent(Zhuye.this,Gonggao.class);
                startActivity(intent);
            }
        });

        shezhi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent =new Intent(Zhuye.this,Shezhi.class);
                startActivity(intent);
            }
    });

        bangzhu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent =new Intent(Zhuye.this,Bangzhu.class);
                startActivity(intent);
            }
        });*/

        paihangbang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent =new Intent(Zhuye.this,PaiHB.class);
                startActivity(intent);
            }
        });

        gerenzhongxin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!USERNAME.equals("")){
                    Log.w("Hyx", "6-12：主页-进了");
                    Intent intent =new Intent(Zhuye.this,Gerenzhongxin.class);
                    startActivity(intent);
                }
                else{
                    USERNAME="";
                    USERPASSWORD="";
                    USERCIKU=4;
                    Toast.makeText(Zhuye.this,"请先登录！",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(Zhuye.this,Denglu.class);
                    startActivity(intent);
                }

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/chaUsersInf";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                Log.w("Hyx", "6-12：主页"+USERNAME);
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
                            String mima1 = (String) json.get("mima");
                            String ciku1 = (String) json.get("ciku");
                            //你的代码放这下面

                            USERPASSWORD=mima1;
                            USERCIKU=Integer.parseInt(ciku1);
                            Log.w("hyx","前"+USERCIKU);
                            //      Toast.makeText(MainActivity.this,(String) json.get("result1"),Toast.LENGTH_LONG).show();
                            if(!USERNAME.equals("")){

                                chaZF();

                            }
                            //你的代码放这上面
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }).start();
        Log.w("Hyx", "Username"+USERNAME);


}



    public void chaZF(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/chaChenJi";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                String name=USERNAME;
                String gongneng="12";
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
                            String[] both=result.split("M");

                            int zongfen1=Integer.parseInt(both[0]);
                            int zongfen2=Integer.parseInt(both[1]);

                            if(USERCIKU==4){
                                ZF4_1=zongfen1;
                                ZF4_2=zongfen2;
                            }
                            else if (USERCIKU==6){
                                ZF6_1=zongfen1;
                                ZF6_2=zongfen2;
                            }
                            GONGNENGNOW=0;




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

    private void initRightSectorMenuButton() {
        SectorMenuButton sectorMenuButton = (SectorMenuButton) findViewById(R.id.right_sector_menu);
        final List<ButtonData> buttonDatas = new ArrayList<>();
        int[] drawable = {R.drawable.ic_expand_more_black_24dp, R.drawable.notice,
                R.drawable.help, R.drawable.set};
        for (int i = 0; i < 4; i++) {
            ButtonData buttonData = ButtonData.buildIconButton(this, drawable[i], 0);
            buttonData.setBackgroundColorId(this, R.color.colorAccenthui);
            buttonDatas.add(buttonData);
        }
        sectorMenuButton.setButtonDatas(buttonDatas);
        setListener(sectorMenuButton);
    }

    private void setListener(final SectorMenuButton button) {
        button.setButtonEventListener(new ButtonEventListener() {
            @Override
            public void onButtonClicked(int index) {
                if(index==1){
                    Intent intent =new Intent(Zhuye.this,Gonggao.class);
                    startActivity(intent);
                }
                else if(index==2){
                    Intent intent =new Intent(Zhuye.this,Bangzhu.class);
                    startActivity(intent);
                }
                else if(index==3){
                    Intent intent =new Intent(Zhuye.this,Shezhi.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onExpand() {
               // showToast("onExpand");
            }

            @Override
            public void onCollapse() {
              //  showToast("onCollapse");
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(Zhuye.this, text, Toast.LENGTH_SHORT).show();
    }


}
