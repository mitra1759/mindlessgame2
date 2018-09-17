package com.example.brenden.mindlessgame2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.app.Application;
import android.media.MediaPlayer;
import java.lang.Object;


public class MainActivity extends AppCompatActivity {
    int points=0;
    int speed=1000;
    private final static int MAX_VOLUME = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        start= findViewById(R.id.button2);
//        pendulum= findViewById(R.id.imageView1);
        setContentView(R.layout.activity_main);
        final Button start = findViewById(R.id.button2);
        final ImageView pendulum = findViewById(R.id.imageView1);
        final TextView score = findViewById(R.id.textView);
        final MediaPlayer ding = MediaPlayer.create(this, R.raw.ding);
        final MediaPlayer music = MediaPlayer.create(this, R.raw.background);
        music.setLooping(true);
        final float volume = (float) (1 - (Math.log(MAX_VOLUME - 13) / Math.log(MAX_VOLUME)));
        music.setVolume(volume, volume);
        music.start();
        final RotateAnimation rotate = new RotateAnimation(-50, 1200, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_PARENT, 0f);
        rotate.setDuration(700);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate.setRepeatCount(0);
        final RotateAnimation rotate1 = new RotateAnimation(50, -50, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_PARENT, 0f);
        rotate1.setDuration(700);
        rotate1.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate1.setRepeatCount(0);


        pendulum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pendulum.getRotation()<20&&pendulum.getRotation()>-20){
                    ding.start();
                    points+=100;
                    points*=1.3;
                    points/=10;
                    points*=10;
                    score.setText("Score:"+points);
                    speed*=1.03;
                }
                else {
                    speed=1000;
                    points=0;
                    score.setText("Score:"+points);
                }
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Animation rotate = new RotateAnimation(60f, -60f,
//                        Animation.RELATIVE_TO_SELF, .0f, Animation.RELATIVE_TO_SELF,
//                        .0f);
//                rotate.setRepeatCount(0);
//                rotate.setDuration(500);
//                rotate.getFillBefore();
//                rotate.setFillAfter(true);
//                rotate.setInterpolator(new AccelerateDecelerateInterpolator());
//                pendulum.startAnimation(rotate);
//                Animation rotate1 = new RotateAnimation(105.0f, 0.0f,
//                        Animation.RELATIVE_TO_SELF, .0f, Animation.RELATIVE_TO_PARENT,
//                        .15f);
//                rotate.setRepeatCount(0);
//                rotate.setDuration(500);
//
//                rotate.setFillAfter(true);
//                rotate.setInterpolator(new AccelerateDecelerateInterpolator());
//                pendulum.startAnimation(rotate1);


//                pendulum.startAnimation(rotate1);


                    pendulum.animate().rotation(pendulum.getRotation() + 100).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(1500000/speed).start();
                    new CountDownTimer(1800000/speed, 600000/speed) {

                        public void onTick(long millisUntilFinished) {
//                        mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                        }

                        public void onFinish() {
                            pendulum.animate().rotation(pendulum.getRotation() - 100).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(1500000/speed).start();
//                            pendulum.RotateAnimation(pendulum.getRotation() ,pendulum.getRotation(),pendulum.getRotation(),pendulum.getRotation()).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(500).start();
//                            pendulum.startAnimation(rotate);
                            new CountDownTimer(1800000/speed, 600000/speed) {

                                public void onTick(long millisUntilFinished) {
//                        mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                                }

                                public void onFinish() {
                                    onClick(pendulum);
                                }
                            }.start();
                        }
                    }.start();




//
//                pendulum.animate().rotation(pendulum.getRotation()+180).start();


//                pendulum.setImageResource(R.drawable.pendulum);
            }
        });
    }
}
