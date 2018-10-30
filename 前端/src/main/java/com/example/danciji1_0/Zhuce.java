package com.example.danciji1_0;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.MobSDK;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static com.example.danciji1_0.UserState.*;

public class Zhuce extends AppCompatActivity  implements View.OnClickListener {

    private static final String TAG = "SmsYanzheng";
    EditText mEditTextPhoneNumber;
    EditText mEditTextCode;
    Button mButtonGetCode;
    Button mButtonLogin;
    TextView Tzhanghao;
    TextView Tmima1;
    TextView Tmima2;
    TextView Tnumber;
    TextView Tciku;
    EventHandler eventHandler;
    String strPhoneNumber;                  //



    EditText zhanghao;
    EditText mima1;
    EditText mima2;
//    EditText number;
    private RadioGroup zu;
    private RadioButton siji;
    private RadioButton liuji;
  //  Button denglu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        mEditTextPhoneNumber = (EditText) findViewById(R.id.ZC_number);
        mEditTextCode = (EditText) findViewById(R.id.verification_code);
        mButtonGetCode = (Button) findViewById(R.id.button_send_verification_code);
        mButtonLogin = (Button) findViewById(R.id.ZC_denglu);

        mButtonGetCode.setOnClickListener(this);
        mButtonLogin.setOnClickListener(this);
        mButtonLogin.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        siji=(RadioButton)findViewById(R.id.ZC_siji);
        liuji=(RadioButton)findViewById(R.id.ZC_liuji);
        Tzhanghao = (TextView) findViewById(R.id.ZC_Tzhanghao);
        Tmima1 = (TextView) findViewById(R.id.ZC_Tmima1);
        Tmima2 = (TextView) findViewById(R.id.ZC_Tmima2);
        Tnumber = (TextView) findViewById(R.id.ZC_Tnumber);
        Tciku = (TextView) findViewById(R.id.ZC_Tciku);
        Tzhanghao.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        mButtonLogin.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        Tmima1.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        Tmima2.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        Tnumber.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        mEditTextCode.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        mButtonGetCode.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        siji.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        liuji.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));
        Tciku.setTypeface(Typeface.createFromAsset(getAssets(),"fonnts/boyangkaiti7000.TTF"));


        MobSDK.init(this, "26672e21a1906", "e1e267f99b223a96a94d60eb7017915d"); //使用你申请的key 和 secret

        eventHandler = new EventHandler() {

            /**
             * 在操作之后被触发
             *
             * @param event  参数1
             * @param result 参数2 SMSSDK.RESULT_COMPLETE表示操作成功，为SMSSDK.RESULT_ERROR表示操作失败
             * @param data   事件操作的结果
             */

            @Override
            public void afterEvent(int event, int result, Object data) {
                Message message = myHandler.obtainMessage(0x00);
                message.arg1 = event;
                message.arg2 = result;
                message.obj = data;
                myHandler.sendMessage(message);
            }
        };

        SMSSDK.registerEventHandler(eventHandler);



        zhanghao=(EditText)findViewById(R.id.ZC_zhanghao);
        mima1=(EditText)findViewById(R.id.ZC_mima1);
        mima2=(EditText)findViewById(R.id.ZC_mima2);
  //      number=(EditText)findViewById(R.id.ZC_number);
  //      denglu=(Button)findViewById(R.id.ZC_denglu);
        zu=(RadioGroup)findViewById(R.id.ZC_zu) ;

        siji.setChecked(true);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ZC_denglu) {
            String strCode = mEditTextCode.getText().toString();
            if (null != strCode && strCode.length() == 4) {
                Log.d(TAG, mEditTextCode.getText().toString());
                SMSSDK.submitVerificationCode("86", strPhoneNumber, mEditTextCode.getText().toString());   //  ??????
            }
            else if(null == strPhoneNumber){
                Toast.makeText(this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "验证码不正确！", Toast.LENGTH_SHORT).show();
            }



        } else if (view.getId() == R.id.button_send_verification_code) {
            strPhoneNumber = mEditTextPhoneNumber.getText().toString();
            if (null == strPhoneNumber || "".equals(strPhoneNumber) || strPhoneNumber.length() != 11) {
                Toast.makeText(this, "电话号码输入有误", Toast.LENGTH_SHORT).show();
                return;
            }else if(zhanghao.getText().toString()==null||zhanghao.getText().toString().equals("")){
                Toast.makeText(Zhuce.this,"账号不能为空！",Toast.LENGTH_SHORT).show();
                return;
            }else if(mima1.getText().toString()==null||mima1.getText().toString().equals("")){
                Toast.makeText(Zhuce.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                return;
            }else if(zhanghao.getText().toString().length()<5||zhanghao.getText().toString().length()>13){
                Toast.makeText(Zhuce.this,"账号长度应在5-13位！",Toast.LENGTH_SHORT).show();
                return;
            }else if(mima1.getText().toString().length()<5||mima1.getText().toString().length()>13){
                Toast.makeText(Zhuce.this,"密码长度应在5-13位！",Toast.LENGTH_SHORT).show();
                return;
            }else if(!mima1.getText().toString().equals(mima2.getText().toString())){
                Log.w("Hyx",mima1.getText().toString()+"+"+mima2.getText().toString());
                Toast.makeText(Zhuce.this,"两次密码应相同！",Toast.LENGTH_SHORT).show();
                return;
            }





            SMSSDK.getVerificationCode("86", strPhoneNumber);
            mButtonGetCode.setClickable(false);
            //开启线程去更新button的text
            new Thread() {
                @Override
                public void run() {
                    int totalTime = 60;
                    for (int i = 0; i < totalTime; i++) {
                        Message message = myHandler.obtainMessage(0x01);
                        message.arg1 = totalTime - i;
                        myHandler.sendMessage(message);
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    myHandler.sendEmptyMessage(0x02);
                }
            }.start();
        }
    }

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x00:
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    Log.e(TAG, "result : " + result + ", event: " + event + ", data : " + data);
                    if (result == SMSSDK.RESULT_COMPLETE) { //回调  当返回的结果是complete
                        if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) { //获取验证码
                            Toast.makeText(Zhuce.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "get verification code successful.");
                        } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码
                            Log.d(TAG, "submit code successful");
                    //        Toast.makeText(Zhuce.this, "验证码right", Toast.LENGTH_SHORT).show();

                            zhuce();

                        }
                        else {
                            Log.d(TAG, data.toString());
                        }
                    } else { //进行操作出错，通过下面的信息区分析错误原因
                        //   Toast.makeText(MainActivity.this, "验证码wrong", Toast.LENGTH_SHORT).show();
                        try {
                            Throwable throwable = (Throwable) data;
                            throwable.printStackTrace();
                            JSONObject object = new JSONObject(throwable.getMessage());
                            String des = object.optString("detail");//错误描述
                            int status = object.optInt("status");//错误代码
                            //错误代码：  http://wiki.mob.com/android-api-%E9%94%99%E8%AF%AF%E7%A0%81%E5%8F%82%E8%80%83/
                            Log.e(TAG, "status: " + status + ", detail: " + des);
                            if (status > 0 && !TextUtils.isEmpty(des)) {
                                Toast.makeText(Zhuce.this, des, Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 0x01:
                    mButtonGetCode.setText("重新发送(" + msg.arg1 + ")");
                    break;
                case 0x02:
                    mButtonGetCode.setText("获取验证码");
                    mButtonGetCode.setClickable(true);
                    break;
            }
        }
    };



    public void zhuce(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url=HttpUtilsHttpURLConnection.BASE_URL+"/Zhuce";/////这个后面的/conn也要对应的改
                Map<String, String> params = new HashMap<String, String>();
                //你的代码放这下面
                String name=zhanghao.getText().toString();
                String password1=mima1.getText().toString();
                String password2=mima2.getText().toString();
                String number1=mEditTextPhoneNumber.getText().toString();
                String ciku="4";
                if(zu.getCheckedRadioButtonId()==R.id.ZC_siji){
                    ciku="4";
                }
                else if(zu.getCheckedRadioButtonId()==R.id.ZC_liuji){
                    ciku="6";
                }
                params.put("name",name);
                params.put("Number",number1);
                params.put("password1",password1);
                params.put("password2",password2);
                params.put("ciku",ciku);
                //你的代码放这上面

                String result = HttpUtilsHttpURLConnection.getContextByHttp(url,params);

                Message msg = new Message();
                msg.what=0x12;
                Bundle data=new Bundle();
                data.putString("Bigresult",result);
                data.putString("name",name);
                msg.setData(data);


                hander.sendMessage(msg);
            }

            Handler hander = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what==0x12){
                        Bundle data = msg.getData();
                        String key = data.getString("Bigresult");
                        String name=data.getString("name");//得到json返回的json
                        //   Toast.makeText(Denglu.this,key,Toast.LENGTH_LONG).show();
                        try {
                            JSONObject json= new JSONObject(key);
                            String result = (String) json.get("result");

                            //你的代码放这下面

                            if ("success".equals(result)){
                                Toast.makeText(Zhuce.this,"注册成功",Toast.LENGTH_SHORT).show();
                                Intent intent =new Intent(Zhuce.this,Denglu.class);
                                startActivity(intent);
                            }else if("namenull".equals(result)){
                                Toast.makeText(Zhuce.this,"账号不能为空！",Toast.LENGTH_SHORT).show();
                            }else if("passwordnull".equals(result)){
                                Toast.makeText(Zhuce.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                            }else if("Numbernull".equals(result)){
                                Toast.makeText(Zhuce.this,"手机号不能为空！",Toast.LENGTH_SHORT).show();
                            } else if("Numbererror".equals(result)){
                                Toast.makeText(Zhuce.this,"请输入正确的手机号码！",Toast.LENGTH_SHORT).show();
                            }else if("nameerror".equals(result)){
                                Toast.makeText(Zhuce.this,"账号长度应在5-13位！",Toast.LENGTH_SHORT).show();
                            }else if("passworderror".equals(result)){
                                Toast.makeText(Zhuce.this,"密码长度应在5-13位！",Toast.LENGTH_SHORT).show();
                            }else if("passwordsame".equals(result)){
                                Toast.makeText(Zhuce.this,"两次密码应相同！",Toast.LENGTH_SHORT).show();
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
