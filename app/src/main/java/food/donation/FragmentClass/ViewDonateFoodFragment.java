package food.donation.FragmentClass;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import food.donation.AdapterClass.ViewDonateAdapter;
import food.donation.ListClass.ViewDonateList;
import food.donation.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewDonateFoodFragment extends Fragment {

    List<ViewDonateList> donateList;
    ViewDonateAdapter donateAdapter;
    RecyclerView recyclerView;

    SQLiteDatabase db;

    public ViewDonateFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_donate_food, container, false);

        db = getActivity().openOrCreateDatabase("FoodDonation", Context.MODE_PRIVATE, null);
        String register_Query = "create table if not exists register(id integer primary key autoincrement, fname text,lname text,email text,pass text,cno number,gender text,hint text)";
        db.execSQL(register_Query);

        String food_Query = "create table if not exists food(id integer primary key autoincrement, fname text,lname text,cno number,address text,foodType text,qty text)";
        db.execSQL(food_Query);

        recyclerView = (RecyclerView)view. findViewById(R.id.view_donate_recycle_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        donateList = new ArrayList<>();

        String selectQuery = "SELECT * FROM food";
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                ViewDonateList donate = new ViewDonateList();
                donate.setFoodfname(cursor.getString(1));
                donate.setFoodlname(cursor.getString(2));
                donate.setFoodcno(cursor.getString(3));
                donate.setFoodaddress(cursor.getString(4));
                donate.setFoodType(cursor.getString(5));
                donate.setFoodqty(cursor.getString(6));
                donateList.add(donate);
            }
            donateAdapter = new ViewDonateAdapter(getActivity(), donateList);
            recyclerView.setAdapter(donateAdapter);
        }
        return view;
    }
}
