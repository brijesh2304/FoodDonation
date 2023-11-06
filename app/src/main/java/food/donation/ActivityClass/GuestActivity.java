package food.donation.ActivityClass;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import food.donation.AdapterClass.GuestAdapter;
import food.donation.ListClass.GuestList;

import java.util.ArrayList;
import java.util.List;

import food.donation.R;

public class GuestActivity extends AppCompatActivity {

    List<GuestList> guestList;
    GuestAdapter guestAdapter;
    RecyclerView recyclerView;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        db = openOrCreateDatabase("FoodDonation", Context.MODE_PRIVATE, null);
        String register_Query = "create table if not exists register(id integer primary key autoincrement, fname text,lname text,email text,pass text,cno number,gender text,hint text)";
        db.execSQL(register_Query);

        String food_Query = "create table if not exists food(id integer primary key autoincrement, fname text,lname text,cno number,address text,foodType text,qty text)";
        db.execSQL(food_Query);

        recyclerView = (RecyclerView) findViewById(R.id.guest_recycle_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(GuestActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        guestList = new ArrayList<>();
        String selectQuery = "SELECT * FROM food";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                GuestList donate = new GuestList();
                donate.setGuestfname(cursor.getString(1));
                donate.setGuestlname(cursor.getString(2));
                donate.setGuestcno(cursor.getString(3));
                donate.setGuestaddress(cursor.getString(4));
                donate.setGuesttypefood(cursor.getString(5));
                donate.setGuestqty(cursor.getString(6));
                guestList.add(donate);
            }
            guestAdapter = new GuestAdapter(GuestActivity.this, guestList);
            recyclerView.setAdapter(guestAdapter);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(GuestActivity.this, MainActivity.class));
    }
}
