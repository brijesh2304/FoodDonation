package food.donation.ActivityClass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import food.donation.Constans;
import food.donation.R;

public class SplashScreen extends AppCompatActivity {

    ImageView iv;
    private static int Splash_Time = 3000;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        iv = (ImageView) findViewById(R.id.iv);
        sp = getSharedPreferences(Constans.PREF, Context.MODE_PRIVATE);
        boolean handler = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(Splash_Time);
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));

                    if (sp.getString(Constans.USERNAME, "").equalsIgnoreCase("")) {
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
                        startActivity(intent);
                    }
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },Splash_Time);
        AlphaAnimation animation = new AlphaAnimation(0,1);
        animation.setDuration(2000);
        iv.startAnimation(animation);
    }
}
