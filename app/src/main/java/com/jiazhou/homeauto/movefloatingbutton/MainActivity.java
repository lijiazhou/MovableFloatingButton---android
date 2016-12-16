package com.jiazhou.homeauto.movefloatingbutton;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.app.Activity;
import android.graphics.Rect;

public class MainActivity extends AppCompatActivity {

    private ImageButton ib;
    private RelativeLayout rl;
    private int statusBarHeight = 0;
    private static int screenWidth;
    private static int screenHeight;

    private Timer timer = new Timer();
    private TimerTask task;
    private int time = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        rl = (RelativeLayout) findViewById(R.id.relative);

        ib = new ImageButton(this);
         //ib.setImageResource(R.drawable.xuanfu);
        // ib.setBackgroundColor(0x00000000);
        ib.setBackgroundResource(R.drawable.xuanfu);
        rl.addView(ib);

        ib.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "ƒ„µ„µΩŒ“¡À", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        ib.setOnTouchListener(touch);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        statusBarHeight = frame.top;
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - statusBarHeight;
        Log.e("chengxl___◊¥Ã¨¿∏∏ﬂ∂»", statusBarHeight + ",øÌ     " + screenWidth
                + ",∏ﬂ     " + screenHeight + ",∞¥≈•øÌ     " + ib.getWidth()
                + ",∞¥≈•∏ﬂ    " + ib.getHeight());
    }

    OnTouchListener touch = new OnTouchListener() {
        float xd = 0;
        float yd = 0;
        private int xm = 0;
        private int ym = 0;
        private float movex = 0;
        private float movey = 0;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:// ∞¥œ¬ ¬º˛£¨º«¬º∞¥œ¬ ± ÷÷∏‘⁄–¸∏°¥∞µƒXY◊¯±Í÷µ
                    startTask();
                    xd = event.getRawX();
                    yd = event.getRawY();
                    movex = xd;
                    movey = yd;
                    break;
                case MotionEvent.ACTION_MOVE:
                    xm = (int) (event.getRawX() - xd);
                    ym = (int) (event.getRawY() - yd);
                    int l = v.getLeft() + xm;
                    int b = v.getBottom() + ym;
                    int r = v.getRight() + xm;
                    int t = v.getTop() + ym;

                    // œ¬√Ê≈–∂œ“∆∂Ø «∑Ò≥¨≥ˆ∆¡ƒª
                    if (l < 0) {
                        l = 0;
                        r = l + v.getWidth();
                    }

                    if (t < 0) {
                        t = 0;
                        b = t + v.getHeight();
                    }

                    if (r > screenWidth) {
                        r = screenWidth;
                        l = r - v.getWidth();
                    }

                    if (b > screenHeight) {
                        b = screenHeight;
                        t = b - v.getHeight();
                    }
                    v.layout(l, t, r, b);
                    xd = event.getRawX();
                    yd = event.getRawY();
                    v.postInvalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    if ((int) (event.getRawX() - movex) != 0
                            || (int) (event.getRawY() - movey) != 0) {
                        return true;
                    }
                    break;
                default:
                    break;
            }
            return false;
        }

    };

    private void startTask() {
//        if (task == null) {
//            time = 3;
//            task = new TimerTask() {
//                @Override
//                public void run() {
//
//                    runOnUiThread(new Runnable() { // UI thread
//                        @Override
//                        public void run() {
//                            if (time <= 0) {
//                                ib.layout(0, 0, ib.getWidth(), ib.getHeight());
//                                ib.postInvalidate();
//                                task.cancel();
//                                task = null;
//                            }
//                            time--;
//                        }
//                    });
//                }
//            };
//            timer.schedule(task, 0, 1000);
//            Log.e("chengxl", "»ŒŒÒø™∆Ù");
//        } else {
//            time = 3;
//            Log.e("chengxl", "»ŒŒÒ÷ÿ÷√");
//        }
    }

}
