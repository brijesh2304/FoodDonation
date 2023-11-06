package food.donation.ActivityClass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import food.donation.AdapterClass.LoginAdapter;
import food.donation.Constans;
import food.donation.ListClass.LoginList;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import food.donation.R;
public class LoginActivity extends AppCompatActivity {

    TextView txtForgetPass, txtSignup;
    EditText edtemail, edtpass;
    Button btnlogin;
    SharedPreferences sp;
    SQLiteDatabase db;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ViewPager mViewPager = null;
    LoginAdapter loginAdapter;
    ArrayList<LoginList> loginList;
    int count = 0;
    Timer timer;
    Animation a;
    int[] image = {R.drawable.slider1, R.drawable.slider2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        overridePendingTransition(R.anim.in_animation, R.anim.out_animation);
        db = openOrCreateDatabase("FoodDonation", Context.MODE_PRIVATE, null);
        String register_Query = "create table if not exists register(id integer primary key autoincrement, fname text,lname text,email text,pass text,cno number,gender text,hint text)";
        db.execSQL(register_Query);

        String food_Query = "create table if not exists food(id integer primary key autoincrement, fname text,lname text,cno number,address text,foodType text,qty text)";
        db.execSQL(food_Query);

        txtForgetPass = (TextView) findViewById(R.id.login_txt_forget_pass);
        txtSignup = (TextView) findViewById(R.id.login_txt_new_user);
        edtemail = (EditText) findViewById(R.id.login_email);
        edtpass = (EditText) findViewById(R.id.login_pass);
        btnlogin = (Button) findViewById(R.id.login_btn);

        mViewPager = (ViewPager) findViewById(R.id.login_pager);
        mViewPager.setCurrentItem(0);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count <= loginList.size()) {
                            mViewPager.setCurrentItem(count);
                            for (int i = 0; i <= loginList.size(); i++) {
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

        loginList = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            LoginList list = new LoginList();
            list.setImage(image[i]);
            loginList.add(list);
        }
        loginAdapter = new LoginAdapter(LoginActivity.this, loginList);
        mViewPager.setAdapter(loginAdapter);

        sp = getSharedPreferences(Constans.PREF, Context.MODE_PRIVATE);

        edtemail.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if(edtemail.getText().toString().trim().matches(emailPattern) && s.length()>0){
                    edtemail.setBackgroundResource(R.color.green);
                }
                else{
                    edtemail.setBackgroundResource(R.color.red);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtemail.getText().toString().equalsIgnoreCase("")) {

                    edtemail.setError("Enter Email ID");
                }
                String email = edtemail.getText().toString();
                if (email.matches(emailPattern)) {
                } else {
                    Toast.makeText(LoginActivity.this, "In Valid Email", Toast.LENGTH_SHORT).show();
                }
                if (edtpass.getText().toString().equalsIgnoreCase("")) {
                    edtpass.setError("Enter Password");
                } else {

                    Cursor c = db.rawQuery("select * from register where email='"
                            + edtemail.getText().toString() + "' and pass='"
                            + edtpass.getText().toString() + "'", null);

                    if (c.getCount()>0) {
                        String name = edtemail.getText().toString();
                        sp.edit().putString(Constans.USERNAME, name).commit();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        clear();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Unsuccessfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        txtForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgetPassActivity.class));
            }
        });
        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });
    }

    private void clear() {
        edtemail.setText("");
        edtpass.setText("");
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }
}