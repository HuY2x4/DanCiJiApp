package com.example.danciji1_0;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import me.wangyuwei.particleview.ParticleView;

public class MainActivity extends AppCompatActivity {
    ParticleView mPvGithub;
    ImageButton jinru;
    private ParticleView mPv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPv1 = (ParticleView) findViewById(R.id.pv_1);

        mPv1.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                try {
                    Thread.sleep(1000);                 //1000 毫秒，也就是1秒.
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                Intent intent =new Intent(MainActivity.this,Denglu.class);
                startActivity(intent);
              //  Toast.makeText(MainActivity.this, "Animation is End", Toast.LENGTH_SHORT).show();
            }
        });

        mPv1.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPv1.startAnim();

            }
        }, 200);
        /*jinru=(ImageButton)findViewById(R.id.jinru);
        jinru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,Denglu.class);
                startActivity(intent);
            }
        });*/
    }
}
