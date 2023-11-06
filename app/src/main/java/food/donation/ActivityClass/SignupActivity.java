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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import food.donation.AdapterClass.SignupAdapter;
import food.donation.Constans;
import food.donation.ListClass.SignupList;
import food.donation.MyMessage;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import food.donation.R;
public class SignupActivity extends AppCompatActivity {

    EditText edtfname, edtlname, edtemail, edtpass, edtcpass, edtcno,edthint;
    RadioGroup rg_signup;
    RadioButton rb,rb_male, rb_female;
    Button btnsignup;
    Context context;
    SharedPreferences sp;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SQLiteDatabase db;
    Cursor c;
    MyMessage message;
    String email;

    ViewPager mViewPager = null;
    SignupAdapter signupAdapter;
    ArrayList<SignupList> signupList;
    int count = 0;
    Timer timer;
    Animation a;
    int[] image = {R.drawable.slider1, R.drawable.slider2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db = openOrCreateDatabase("FoodDonation", Context.MODE_PRIVATE, null);
        String register_Query = "create table if not exists register(id integer primary key autoincrement, fname text,lname text,email text,pass text,cno number,gender text,hint text)";
        db.execSQL(register_Query);

        String food_Query = "create table if not exists food(id integer primary key autoincrement, fname text,lname text,cno number,address text,foodType text,qty text)";
        db.execSQL(food_Query);

        edtfname = (EditText) findViewById(R.id.signup_fname);
        edtlname = (EditText) findViewById(R.id.signup_lname);
        edtemail = (EditText) findViewById(R.id.signup_email);
        edtpass = (EditText) findViewById(R.id.signup_pass);
        edtcpass = (EditText) findViewById(R.id.signup_confirm_pass);
        edtcno = (EditText) findViewById(R.id.signup_contact_nomber);
        edthint = (EditText) findViewById(R.id.signup_hint);

        rg_signup = (RadioGroup) findViewById(R.id.signup_rg);

        rb_male = (RadioButton) findViewById(R.id.signup_male_rb);
        rb_female = (RadioButton) findViewById(R.id.signup_female_rb);

        btnsignup = (Button) findViewById(R.id.signup_btn);

        mViewPager = (ViewPager) findViewById(R.id.signup_pager);
        mViewPager.setCurrentItem(0);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count <= signupList.size()) {
                            mViewPager.setCurrentItem(count);
                            for (int i = 0; i <= signupList.size(); i++) {
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
        }, 5000, 5000);

        signupList = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            SignupList list = new SignupList();
            list.setImage(image[i]);
            signupList.add(list);
        }
        signupAdapter = new SignupAdapter(SignupActivity.this, signupList);
        mViewPager.setAdapter(signupAdapter);

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

        rg_signup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = rg_signup.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(id);
                if (rb_male.isChecked()) {
                    Toast.makeText(SignupActivity.this, rb_male.getText(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(SignupActivity.this, rb_female.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        message = new MyMessage(SignupActivity.this);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation();
                email = edtemail.getText().toString();
            }

            private void Validation() {

                if (edtemail.getText().toString().equals("")) {
                    edtemail.setError("Enter Email ID");
                }

                String email = edtemail.getText().toString();
                if (email.matches(emailPattern)) {
                } else {
                    Toast.makeText(SignupActivity.this, "In Valid Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (edtpass.getText().toString().equals("") && edtpass.getText().toString().length() <= 10) {
                    edtpass.setError("Enter Your Password");
                    Toast.makeText(SignupActivity.this, "10 Digit Require", Toast.LENGTH_SHORT).show();
                }

                String pwd = edtpass.getText().toString();
                String pwd1 = edtcpass.getText().toString();
                if (pwd.equals(pwd1)) {
                    //Toast.makeText(getActivity(), "match", Toast.LENGTH_SHORT).show();
                } else {
                    message.myMsg("Oops!", "Password Does Not Match");
                    //Toast.makeText(getActivity(), "not match", Toast.LENGTH_SHORT).show();
                    return;
                }

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
                    String insert_Query = "insert into register values (?, '" + edtfname.getText().toString() + "','" + edtlname.getText().toString() + "','" + edtemail.getText().toString() + "','" + edtpass.getText().toString() + "','" + edtcno.getText().toString() + "','" + rb.getText().toString() + "','" + edthint.getText().toString() + "')";
                    db.execSQL(insert_Query);

                    Intent i = new Intent(SignupActivity.this, HomeActivity.class);
                    String email_sp = edtemail.getText().toString();
                    sp.edit().putString(Constans.USERNAME, email_sp).commit();
                    startActivity(i);
                    Toast.makeText(SignupActivity.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                    clear();
                }
            }
        });
    }


    private void clear() {
        edtfname.setText("");
        edtlname.setText("");
        edtemail.setText("");
        edtpass.setText("");
        edtcpass.setText("");
        edtcno.setText("");
        edthint.setText("");
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SignupActivity.this,LoginActivity.class));
    }
}