package com.yzhang.juhuabaodian;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.yzhang.juhuabaodian.Demos.DemosMainActivity;

import static com.yzhang.juhuabaodian.Demos.OtherModule.Hook.HookDemoActivity.attachContext;

public class MainActivity extends AppCompatActivity {

    TextView text, text1, text2, text3, text4;
    TextView sample_text, sample_text1, sample_text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        try {
            attachContext();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Example of a call to a native method
        sample_text = (TextView) findViewById(R.id.sample_text);
        sample_text1 = (TextView) findViewById(R.id.sample_text1);
        sample_text2 = (TextView) findViewById(R.id.sample_text2);
        text = (TextView) findViewById(R.id.text);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);
        text.getPaint().setFakeBoldText(true);
        text1.getPaint().setFakeBoldText(true);
        text2.getPaint().setFakeBoldText(true);
        text3.getPaint().setFakeBoldText(true);
        text4.getPaint().setFakeBoldText(true);
//        tv.setText(stringFromJNI());
    }

    public void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        }

        startActivity(new Intent(MainActivity.this, DemosMainActivity.class));
        finish();

        /*ZdropAnim(text1);
        text1.setVisibility(View.VISIBLE);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ZdropAnim(text2);
                text2.setVisibility(View.VISIBLE);
            }
        }, 1000);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ZdropAnim(text3);
                text3.setVisibility(View.VISIBLE);
            }
        }, 2000);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ZdropAnim(text4);
                text4.setVisibility(View.VISIBLE);
            }
        }, 3000);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textChangeAnim();
            }
        }, 4000);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textChangeTransparency(sample_text);
                sample_text.setVisibility(View.VISIBLE);
            }
        }, 5000);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textChangeTransparency(sample_text1);
                sample_text1.setVisibility(View.VISIBLE);
            }
        }, 6000);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textChangeTransparency(sample_text2);
                sample_text2.setVisibility(View.VISIBLE);
            }
        }, 7000);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO: 2017/2/10
                startActivity(new Intent(MainActivity.this,DemosMainActivity.class));
                finish();
            }
        }, 8000);*/
    }

    protected void onPause() {
        super.onPause();
        sample_text.setVisibility(View.INVISIBLE);
        sample_text1.setVisibility(View.INVISIBLE);
        sample_text2.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);
        text1.setVisibility(View.INVISIBLE);
        text2.setVisibility(View.INVISIBLE);
        text3.setVisibility(View.INVISIBLE);
        text4.setVisibility(View.INVISIBLE);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private void ZdropAnim(View view) {
        ScaleAnimation animation = new ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        view.startAnimation(animation);
    }

    private void textChangeAnim() {
        TranslateAnimation animation1 = new TranslateAnimation(-150, 0, 0, 0);
        animation1.setDuration(1000);
        text.startAnimation(animation1);
        text.setVisibility(View.VISIBLE);
        TranslateAnimation animation2 = new TranslateAnimation(0, 150, 0, 0);
        animation2.setDuration(1000);
        animation2.setStartOffset(600);
//        RotateAnimation animation3 = new RotateAnimation(0f, 45f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
//        animation3.setDuration(500);
//        AnimationSet set = new AnimationSet(true);
//        set.addAnimation(animation2);
//        set.addAnimation(animation3);
//        set.setStartOffset(500);
        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                text1.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        text1.startAnimation(animation2);
    }

    private void textChangeTransparency(View view) {
        AlphaAnimation transformation = new AlphaAnimation(0, 100);
        transformation.setDuration(1000);
        view.startAnimation(transformation);
    }

    Handler mHandler = new Handler() {

    };
}
