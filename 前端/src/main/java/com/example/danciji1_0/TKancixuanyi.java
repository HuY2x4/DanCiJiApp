package com.example.danciji1_0;

import android.os.Bundle;
import android.os.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hyx on 2018/6/26.
 */

public class TKancixuanyi {
    public void kancixuanyi(String dc){

        int max=101000;
        int num=0;
        for(int i=1;i<max;i++){
            num+=i;


        }
        for(int i=1;i<max;i++){
            num+=i;


        }
        for(int i=1;i<max;i++){
            num+=i;


        }


        new Thread(new Runnable() {
            @Override
            public void run() {

                String url=HttpUtilsHttpURLConnection.BASE_URL+"/Denglu";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面

                params.put("name","Hyxzucc");
                params.put("password","123456");
                //你的代码放这上面

                String result = HttpUtilsHttpURLConnection.getContextByHttp(url,params);

                Message msg = new Message();
                msg.what=0x12;
                Bundle data=new Bundle();
                data.putString("Bigresult",result);

                msg.setData(data);


            }

        }).start();

    }



    }

