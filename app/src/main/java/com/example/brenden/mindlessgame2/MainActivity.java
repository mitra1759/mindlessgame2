package com.example.brenden.mindlessgame2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.os.CountDownTimer;
import android.app.Application;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {
    //    private Button start;
//    private ImageView pendulum;
    private void moverPendulum(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        start= findViewById(R.id.button2);
//        pendulum= findViewById(R.id.imageView1);
        setContentView(R.layout.activity_main);
        final Button start = findViewById(R.id.button2);
        final ImageView pendulum = findViewById(R.id.imageView1);
        final MediaPlayer ding = MediaPlayer.create(this, R.raw.ding);
//        start.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                System.out.println("dwhsgd");
//                pendulum.setImageResource(R.drawable.pendulum);
//            }
//        });

        pendulum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pendulum.getRotation()<20&&pendulum.getRotation()>-20){
                    ding.start();
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



                pendulum.animate().rotation(pendulum.getRotation() + 100).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(500).start();
                new CountDownTimer(700, 700) {

                    public void onTick(long millisUntilFinished) {
//                        mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        pendulum.animate().rotation(pendulum.getRotation() - 100).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(500).start();
                    }
                }.start();
                new CountDownTimer(700, 700) {

                    public void onTick(long millisUntilFinished) {
//                        mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {

                    }
                }.start();

//
//                pendulum.animate().rotation(pendulum.getRotation()+180).start();


//                pendulum.setImageResource(R.drawable.pendulum);
            }
        });
    }
}
