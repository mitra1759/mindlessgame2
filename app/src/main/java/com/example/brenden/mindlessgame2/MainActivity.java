package com.example.brenden.mindlessgame2;

import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
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
import android.view.WindowManager;
import android.media.MediaPlayer;


public class MainActivity extends AppCompatActivity {
    int points = 0;
    int speed = 1000;
    private final static int MAX_VOLUME = 100;
    ConstraintLayout myLayout;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        myLayout = (ConstraintLayout) findViewById(R.id.myLayout);
        animationDrawable = (AnimationDrawable) myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();
        final Button start = findViewById(R.id.button2);
        final ImageView pendulum = findViewById(R.id.imageView1);
        final ImageView clickView = findViewById(R.id.clickView);
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


        clickView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (pendulum.getRotation() < 20 && pendulum.getRotation() > -20) {
                    ding.start();
                    points += 100;
                    points *= 1.3;
                    points /= 10;
                    points *= 10;
                    score.setText("Score:" + points);
                    speed *= 1.03;
                } else {
                    speed = 1000;
                    points = 0;
                    score.setText("Score:" + points);
                }
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                start.setVisibility(View.INVISIBLE);
                pendulum.animate().rotation(pendulum.getRotation() + 100).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(1500000 / speed).start();
                new CountDownTimer(1800000 / speed, 600000 / speed) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        pendulum.animate().rotation(pendulum.getRotation() - 100).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(1500000 / speed).start();
                        new CountDownTimer(1800000 / speed, 600000 / speed) {

                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                onClick(pendulum);
                            }
                        }.start();
                    }
                }.start();
            }
        });
    }
}
