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
    double multiplier=1;
    int swing=2;
    int previousSwing;
    int bonus=1;
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
        final Button restart = findViewById(R.id.restart);
        final ImageView pendulum = findViewById(R.id.imageView1);
        final ImageView leftIndicator1A = findViewById(R.id.leftIndicator1A);
        final ImageView rightIndicator1A = findViewById(R.id.rightIndicator1A);
        final ImageView leftIndicator2A = findViewById(R.id.leftIndicator2A);
        final ImageView rightIndicator2A = findViewById(R.id.rightIndicator2A);
        final ImageView middleIndicatorA = findViewById(R.id.middleIndicatorA);
        final ImageView endScreen = findViewById(R.id.endScreen);
        final ImageView clickView = findViewById(R.id.clickView);
        final TextView score = findViewById(R.id.textView);
        final TextView gameOver = findViewById(R.id.gameOver);
        final TextView multiplierView = findViewById(R.id.multiplier);
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
                    bonus=1;
                    leftIndicator1A.setVisibility(View.VISIBLE);
                    rightIndicator1A.setVisibility(View.VISIBLE);
                    new CountDownTimer(400, 400) {

                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            leftIndicator1A.setVisibility(View.INVISIBLE);
                            rightIndicator1A.setVisibility(View.INVISIBLE);

                        }
                    }.start();
                    if(pendulum.getRotation() < 10 && pendulum.getRotation() > -10){
                        bonus=4;
                        leftIndicator2A.setVisibility(View.VISIBLE);
                        rightIndicator2A.setVisibility(View.VISIBLE);
                        new CountDownTimer(400, 400) {

                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                leftIndicator2A.setVisibility(View.INVISIBLE);
                                rightIndicator2A.setVisibility(View.INVISIBLE);

                            }
                        }.start();
                        if(pendulum.getRotation() < 3 && pendulum.getRotation() > -3){
                            middleIndicatorA.setVisibility(View.VISIBLE);
                            new CountDownTimer(400, 400) {

                                public void onTick(long millisUntilFinished) {
                                }

                                public void onFinish() {
                                    middleIndicatorA.setVisibility(View.INVISIBLE);

                                }
                            }.start();
                            bonus=10;
                        }
                    }
                    ding.start();
                    if(swing-1==previousSwing){
                        multiplier+=.25;
                        multiplierView.setText("x"+multiplier);
                    }
                    else {
                        multiplier=1;
                    }
                    previousSwing=swing;
                    points += (100*speed/1000*multiplier*bonus);
                    points /= 10;
                    points *= 10;
                    score.setText("Score:" + points);
                    multiplierView.setText("x"+multiplier);
                    speed *= 1.05;
                } else {
                    endScreen.setVisibility(View.VISIBLE);
                    gameOver.setVisibility(View.VISIBLE);
                    gameOver.setText("GAME OVER You finished with "+points+" points");
                    restart.setVisibility(View.VISIBLE);
                    multiplier=1;
                    speed = 1000;
                    points = 0;
                    score.setText("Score:" + points);
                    multiplierView.setText("x"+multiplier);
                }

            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart.setVisibility(View.INVISIBLE);
                gameOver.setVisibility(View.INVISIBLE);
                endScreen.setVisibility(View.INVISIBLE);
                points=0;
                speed=1000;
                score.setText("Score:" + points);
                multiplierView.setText("x"+multiplier);
                multiplier=1;
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                start.setVisibility(View.INVISIBLE);
                pendulum.animate().rotation(pendulum.getRotation() + 100).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(1500000 / speed).start();
                swing++;
                if(swing-2>=previousSwing){
                    multiplierView.setText("x1.0");
                }
                new CountDownTimer(1800000 / speed, 600000 / speed) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        pendulum.animate().rotation(pendulum.getRotation() - 100).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(1500000 / speed).start();
                        swing++;
                        if(swing-2>=previousSwing){
                            multiplierView.setText("x1.0");
                        }
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
