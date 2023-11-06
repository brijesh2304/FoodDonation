package food.donation.ActivityClass;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import food.donation.AdapterClass.ViewProfileAdapter;
import food.donation.ListClass.ViewProfileList;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import food.donation.R;

public class ViewProfileActivity extends AppCompatActivity {

    TextView fname, lname, email, cno, gender, hint;
    Button update_btn;
    private SQLiteDatabase db;

    ViewPager mViewPager = null;
    ViewProfileAdapter viewProfileAdapter;
    ArrayList<ViewProfileList> viewProfileList;
    int count = 0;
    Timer timer;
    Animation a;
    int[] image = {R.drawable.slider1, R.drawable.slider2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        db = openOrCreateDatabase("FoodDonation", Context.MODE_PRIVATE, null);
        String register_Query = "create table if not exists register(id integer primary key autoincrement, fname text,lname text,email text,pass text,cno number,gender text,hint text)";
        db.execSQL(register_Query);

        String food_Query = "create table if not exists food(id integer primary key autoincrement, fname text,lname text,cno number,address text,foodType text,qty text)";
        db.execSQL(food_Query);

        fname = (TextView) findViewById(R.id.view_fname);
        lname = (TextView) findViewById(R.id.view_lname);
        email = (TextView) findViewById(R.id.view_email);
        cno = (TextView) findViewById(R.id.view_contact_nomber);
        hint = (TextView) findViewById(R.id.view_hint);
        gender = (TextView) findViewById(R.id.view_gender);
        update_btn = (Button) findViewById(R.id.view_btn_update);

        mViewPager = (ViewPager) findViewById(R.id.view_profile_pager);
        mViewPager.setCurrentItem(0);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count <= viewProfileList.size()) {
                            mViewPager.setCurrentItem(count);
                            for (int i = 0; i <= viewProfileList.size(); i++) {
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

        viewProfileList = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            ViewProfileList list = new ViewProfileList();
            list.setImage(image[i]);
            viewProfileList.add(list);
        }
        viewProfileAdapter = new ViewProfileAdapter(ViewProfileActivity.this, viewProfileList);
        mViewPager.setAdapter(viewProfileAdapter);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewProfileActivity.this, UpdateActivity.class);
                startActivity(i);
            }
        });

        String QueryDisplay = "select * from register";
        Cursor c = db.rawQuery(QueryDisplay, null);
        StringBuilder sb = new StringBuilder();
        if (c.moveToFirst()) {
            do {
                String sfname = c.getString(1);
                String slname = c.getString(2);
                String semail = c.getString(3);
                String scno = c.getString(5);
                String sgender = c.getString(6);
                String shint = c.getString(7);

                fname.setText(sfname);
                lname.setText(slname);
                email.setText(semail);
                cno.setText(scno);
                gender.setText(sgender);
                hint.setText(shint);

            } while (c.moveToNext());
            //Toast.makeText(ViewProfileActivity.this, "" + sb, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ViewProfileActivity.this, "data not found..", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ViewProfileActivity.this, HomeActivity.class));
    }
}
