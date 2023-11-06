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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import food.donation.AdapterClass.UpdateAdapter;
import food.donation.ListClass.UpdateList;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import food.donation.R;

public class UpdateActivity extends AppCompatActivity {

    EditText edtfname, edtlname, edtcno,edthint;
    TextView email;
    RadioGroup rg;
    RadioButton rb,male_rb,female_rb;
    Button save_btn;
    private SQLiteDatabase db;

    ViewPager mViewPager = null;
    UpdateAdapter updateAdapter;
    ArrayList<UpdateList> updateList;
    int count = 0;
    Timer timer;
    Animation a;
    int[] image = {R.drawable.slider1, R.drawable.slider2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db = openOrCreateDatabase("FoodDonation", Context.MODE_PRIVATE, null);
        String register_Query = "create table if not exists register(id integer primary key autoincrement, fname text,lname text,email text,pass text,cno number,gender text,hint text)";
        db.execSQL(register_Query);

        String food_Query = "create table if not exists food(id integer primary key autoincrement, fname text,lname text,cno number,address text,foodType text,qty text)";
        db.execSQL(food_Query);
        edtfname = (EditText) findViewById(R.id.update_fname);
        edtlname = (EditText) findViewById(R.id.update_lname);
        email = (TextView) findViewById(R.id.update_email);
        edtcno = (EditText) findViewById(R.id.update_contact_nomber);
        edthint = (EditText) findViewById(R.id.update_hint);
        rg = (RadioGroup) findViewById(R.id.update_rg);
        male_rb = (RadioButton) findViewById(R.id.update_male_rb);
        female_rb = (RadioButton) findViewById(R.id.update_female_rb);
        save_btn = (Button) findViewById(R.id.update_btn_save);

        mViewPager = (ViewPager) findViewById(R.id.update_pager);
        mViewPager.setCurrentItem(0);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count <= updateList.size()) {
                            mViewPager.setCurrentItem(count);
                            for (int i = 0; i <= updateList.size(); i++) {
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

        updateList = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            UpdateList list = new UpdateList();
            list.setImage(image[i]);
            updateList.add(list);
        }
        updateAdapter = new UpdateAdapter(UpdateActivity.this, updateList);
        mViewPager.setAdapter(updateAdapter);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = rg.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(id);
            }
        });

        final String QueryDisplay = "select * from register";
        final Cursor c = db.rawQuery(QueryDisplay, null);
        StringBuilder sb = new StringBuilder();
        if (c.moveToFirst()) {
            do {
                String sedtfname = c.getString(1);
                String sedtlname = c.getString(2);
                String sedtemail = c.getString(3);
                String sedtcno = c.getString(5);
                String sedthint = c.getString(7);

                edtfname.setText(sedtfname);
                edtlname.setText(sedtlname);
                email.setText(sedtemail);
                edtcno.setText(sedtcno);
                edthint.setText(sedthint);

                        String m = (c.getString(6));
                        if(m.equals("Male")){
                            rg.check(R.id.update_male_rb);
                        }
                        else{
                            rg.check(R.id.update_female_rb);
                        }

            } while (c.moveToNext());
            Toast.makeText(UpdateActivity.this, "" + sb, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(UpdateActivity.this, "data not found..", Toast.LENGTH_SHORT).show();
        }

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation();
            }

            private void Validation() {
                if (edtfname.getText().toString().equals("")) {
                    edtfname.setError("Enter First Name");
                }
                if (edtlname.getText().toString().equals("")) {
                    edtlname.setError("Enter Last Name");
                }
                if (edtcno.length() < 10 || edtcno.length() > 10) {
                    edtcno.setError("Enter Contact Number");
                    return;
                }
                if (edthint.getText().toString().equals("")) {
                    edthint.setError("Enter Hint");
                } else {
                    //String QuerySelect = "select * from register where email='" + txtemail.getText().toString() + "'" ;
                    Cursor c1 = db.rawQuery(QueryDisplay, null);
                    if (c1.moveToFirst()) {
                        do {
                            String QueryUpdate = "update register set fname='" + edtfname.getText().toString() + "',lname='" + edtlname.getText().toString() + "',cno='" + edtcno.getText().toString() + "',gender='" + rb.getText().toString() + "',hint='" + edthint.getText().toString() + "'where email='" + email.getText().toString() + "'";
                            db.execSQL(QueryUpdate);
                            Toast.makeText(getApplicationContext(), "Update Successfull", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(UpdateActivity.this, HomeActivity.class);
                            startActivity(i);
                        } while (c1.moveToNext());
                    } else {
                        Toast.makeText(getApplicationContext(), "Record not found..", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UpdateActivity.this,ViewProfileActivity.class));
    }
}
