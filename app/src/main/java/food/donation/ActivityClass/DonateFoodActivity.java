package food.donation.ActivityClass;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import food.donation.AdapterClass.DonateFoodAdapter;
import food.donation.ListClass.DonateFoodList;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import food.donation.R;

public class DonateFoodActivity extends AppCompatActivity {

    EditText fname, lname, cno, address, qty;
    Button sendBtn;
    String sfname, slname, scno, saddress, sqty,str;
    Spinner sp;
    ArrayAdapter adapter;
    String[] type = {"Vegetable","Nonveg","Vegetable & Nonveg"};

    ViewPager mViewPager = null;
    DonateFoodAdapter donateFoodAdapter;
    ArrayList<DonateFoodList> donateFoodList;
    int count = 0;
    Timer timer;
    Animation a;
    int[] image = {R.drawable.slider1, R.drawable.slider2};

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_food);

        db = openOrCreateDatabase("FoodDonation", Context.MODE_PRIVATE, null);
        String register_Query = "create table if not exists register(id integer primary key autoincrement, fname text,lname text,email text,pass text,cno number,gender text,hint text)";
        db.execSQL(register_Query);

        String food_Query = "create table if not exists food(id integer primary key autoincrement, fname text,lname text,cno number,address text,foodType text,qty text)";
        db.execSQL(food_Query);

        fname = (EditText) findViewById(R.id.donate_food_fname);
        lname = (EditText) findViewById(R.id.donate_food_lname);
        cno = (EditText) findViewById(R.id.donate_food_cno);
        address = (EditText) findViewById(R.id.donate_food_address);
        qty = (EditText) findViewById(R.id.donate_food_qty);
        sp = (Spinner) findViewById(R.id.donate_food_sp);
        adapter = new ArrayAdapter(DonateFoodActivity.this,android.R.layout.simple_expandable_list_item_1,type);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str = (String) parent.getItemAtPosition(position);
                //Toast.makeText(DonateFoodActivity.this, str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mViewPager = (ViewPager) findViewById(R.id.donate_food_pager);
        mViewPager.setCurrentItem(0);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count <= donateFoodList.size()) {
                            mViewPager.setCurrentItem(count);
                            for (int i = 0; i <= donateFoodList.size(); i++) {
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

        donateFoodList = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            DonateFoodList list = new DonateFoodList();
            list.setImage(image[i]);
            donateFoodList.add(list);
        }
        donateFoodAdapter = new DonateFoodAdapter(DonateFoodActivity.this, donateFoodList);
        mViewPager.setAdapter(donateFoodAdapter);

        sendBtn = (Button) findViewById(R.id.donate_food_send);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation();
            }

            private void Validation() {
                if (fname.getText().toString().trim().equals("")) {
                    fname.setError("Enter First Name");
                } else if (lname.getText().toString().trim().equals("")) {
                    lname.setError("Enter Last Name");
                } else if (cno.length() < 10 || cno.length() > 10) {
                    cno.setError("Enter Contact Number");
                } else if (address.getText().toString().trim().equals("")) {
                    address.setError("Enter Address");
                } else if (qty.getText().toString().trim().equals("")) {
                    qty.setError("Enter Quantity");
                }
                else {
                    /*if (new ConnectionDetector(DonateFoodActivity.this).isConnectingToInternet()) {
                        setData();
                        new AddFoodData().execute();
                    } else {
                        new ConnectionDetector(DonateFoodActivity.this).connectiondetect();
                    }*/
                    setData();
                }
            }
        });
    }

    private void clear() {
        fname.setText("");
        lname.setText("");
        cno.setText("");
        address.setText("");
        qty.setText("");
    }

    private void setData() {
        sfname = fname.getText().toString();
        slname = lname.getText().toString();
        scno = cno.getText().toString();
        saddress = address.getText().toString();
        sqty = qty.getText().toString();

        String insertQuery = "INSERT INTO food VALUES (NULL,'"+sfname+"','"+slname+"','"+scno+"','"+saddress+"','"+str+"','"+sqty+"')";
        db.execSQL(insertQuery);

        Intent i = new Intent(DonateFoodActivity.this, HomeActivity.class);
        startActivity(i);
        Toast.makeText(DonateFoodActivity.this, "Record Added Successfully", Toast.LENGTH_SHORT).show();

        clear();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(DonateFoodActivity.this, HomeActivity.class));
    }
}
