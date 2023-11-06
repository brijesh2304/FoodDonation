package food.donation.ActivityClass;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import food.donation.AdapterClass.ForgetPassAdapter;
import food.donation.ListClass.ForgetPassList;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import food.donation.R;
public class ForgetPassActivity extends AppCompatActivity {

    EditText edthint;
    Button btnnext;
    SQLiteDatabase db;

    ViewPager mViewPager = null;
    ForgetPassAdapter forgetPassAdapter;
    ArrayList<ForgetPassList> forgetPassList;
    int count = 0;
    Timer timer;
    Animation a;
    int[] image = {R.drawable.slider1, R.drawable.slider2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        db = openOrCreateDatabase("FoodDonation", Context.MODE_PRIVATE, null);
        String register_Query = "create table if not exists register(id integer primary key autoincrement, fname text,lname text,email text,pass text,cno number,gender text,hint text)";
        db.execSQL(register_Query);

        String food_Query = "create table if not exists food(id integer primary key autoincrement, fname text,lname text,cno number,address text,foodType text,qty text)";
        db.execSQL(food_Query);

        edthint = (EditText) findViewById(R.id.forget_pass_edt_hint);
        btnnext = (Button) findViewById(R.id.forget_pass_btn);

        mViewPager = (ViewPager) findViewById(R.id.forget_pass_pager);
        mViewPager.setCurrentItem(0);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count <= forgetPassList.size()) {
                            mViewPager.setCurrentItem(count);
                            for (int i = 0; i <= forgetPassList.size(); i++) {
                              /*  a = AnimationUtils.loadAnimation(getActivity(), R.anim.banner_animation);
                                mViewPager.startAnimation(a);*/
                            }
                            count++;
                        } else {
                            count = 0;
                            mViewPager.setCurrentItem(count);
                        }
                    }
                });

            }
        }, 3000, 3000);

        forgetPassList = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            ForgetPassList list = new ForgetPassList();
            list.setImage(image[i]);
            forgetPassList.add(list);
        }
        forgetPassAdapter = new ForgetPassAdapter(ForgetPassActivity.this, forgetPassList);
        mViewPager.setAdapter(forgetPassAdapter);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edthint.getText().toString().equals("")){
                    edthint.setError("Enter Hint");
                }
                else{

                    Cursor c = db.rawQuery("select * from register where hint='"
                            + edthint.getText().toString() + "'", null);

                    if (c.moveToFirst()) {
                        startActivity(new Intent(ForgetPassActivity.this,ChangePass.class));
                    }
                    else{
                        Toast.makeText(ForgetPassActivity.this, "Hint Does Not Match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ForgetPassActivity.this,LoginActivity.class));
    }
}
