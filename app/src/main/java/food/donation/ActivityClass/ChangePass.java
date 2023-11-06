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

import food.donation.AdapterClass.ChangePassAdapter;
import food.donation.ListClass.ChangePassList;
import food.donation.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ChangePass extends AppCompatActivity {

    EditText edtoldpass, edtnewpass, edtnewcpass;
    Button btnsave;
    SQLiteDatabase db;

    ViewPager mViewPager = null;
    ChangePassAdapter changePassAdapter;
    ArrayList<ChangePassList> changePassList;
    int count = 0;
    Timer timer;
    Animation a;
    int[] image = {R.drawable.slider1, R.drawable.slider2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        db = openOrCreateDatabase("FoodDonation", Context.MODE_PRIVATE, null);
        String register_Query = "create table if not exists register(id integer primary key autoincrement, fname text,lname text,email text,pass text,cno number,gender text,hint text)";
        db.execSQL(register_Query);

        String food_Query = "create table if not exists food(id integer primary key autoincrement, fname text,lname text,cno number,address text,foodType text,qty text)";
        db.execSQL(food_Query);

        edtoldpass = (EditText) findViewById(R.id.change_pass_edt_old_pass);
        edtnewpass = (EditText) findViewById(R.id.change_pass_edt_new_pass);
        edtnewcpass = (EditText) findViewById(R.id.change_pass_edt_cnew_pass);
        btnsave = (Button) findViewById(R.id.change_pass_btn);

        mViewPager = (ViewPager) findViewById(R.id.change_pass_pager);
        mViewPager.setCurrentItem(0);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count <= changePassList.size()) {
                            mViewPager.setCurrentItem(count);
                            for (int i = 0; i <= changePassList.size(); i++) {
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

        changePassList = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            ChangePassList list = new ChangePassList();
            list.setImage(image[i]);
            changePassList.add(list);
        }
        changePassAdapter = new ChangePassAdapter(ChangePass.this, changePassList);
        mViewPager.setAdapter(changePassAdapter);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pass_str = edtnewpass.getText().toString();
                String cpass_str = edtnewcpass.getText().toString();
                if (pass_str.matches(cpass_str)) {

                } else {
                    Toast.makeText(ChangePass.this, "Password Is Not Match", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (edtoldpass.getText().toString().equalsIgnoreCase("")) {
                    edtoldpass.setError("Enter Old Password");
                }
                if (edtnewpass.getText().toString().equalsIgnoreCase("")) {
                    edtnewpass.setError("Enter New Password");
                } else {

                    Cursor c = db.rawQuery("select * from register where pass='"
                            + edtoldpass.getText().toString() + "'", null);

                    if (c.moveToFirst()) {

                    } else {
                        Toast.makeText(getApplicationContext(), "Old Password Is Wrong", Toast.LENGTH_SHORT).show();
                    }
                    String QuerySelect = "select * from register where pass='" + edtoldpass.getText().toString() +"'";
                    Cursor c1 = db.rawQuery(QuerySelect, null);
                    if (c1.moveToFirst()) {
                        do {
                            String QueryUpdate = "update register set pass='" + edtnewpass.getText().toString() + "' where pass='" + edtoldpass.getText().toString() + "'";
                            db.execSQL(QueryUpdate);
                            Toast.makeText(getApplicationContext(), "Password Change Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ChangePass.this,LoginActivity.class));
                        } while (c1.moveToNext());
                    } else {
                        Toast.makeText(getApplicationContext(), "Record not found..", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
