package food.donation.AdapterClass;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import food.donation.R;
import food.donation.ListClass.ViewDonateList;

import java.util.List;

/**
 * Created by admin on 16-11-2016.
 */
public class ViewDonateAdapter extends RecyclerView.Adapter<ViewDonateAdapter.MyViewHolder> {

    List<ViewDonateList> donateList;
    Context c;

    public ViewDonateAdapter(FragmentActivity fragment, List<ViewDonateList> donateList) {

        this.c = fragment;
        this.donateList = donateList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView foodfname, foodlname, foodcno, foodaddress,foodtype,foodqty;

        public MyViewHolder(View view) {
            super(view);
            foodfname = (TextView) view.findViewById(R.id.cust_view_donate_fname);
            foodlname = (TextView) view.findViewById(R.id.cust_view_donate_lname);
            foodcno = (TextView) view.findViewById(R.id.cust_view_donate_cno);
            foodaddress = (TextView) view.findViewById(R.id.cust_view_donate_address);
            foodtype = (TextView) view.findViewById(R.id.cust_view_donate_food_type);
            foodqty = (TextView) view.findViewById(R.id.cust_view_donate_qty);
            }
    }

    @Override
    public ViewDonateAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_view_donate_food, parent, false);
        return new ViewDonateAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewDonateAdapter.MyViewHolder holder, final int position) {
        final ViewDonateList donateL = donateList.get(position);
        holder.foodfname.setText(donateList.get(position).getFoodfname());
        holder.foodlname.setText(donateList.get(position).getFoodlname());
        holder.foodcno.setText(donateList.get(position).getFoodcno());
        holder.foodaddress.setText(donateList.get(position).getFoodaddress());
        holder.foodtype.setText(donateList.get(position).getFoodType());
        holder.foodqty.setText(donateList.get(position).getFoodqty());
    }

    @Override
    public int getItemCount() {
        Log.d("hit", "size : " + donateList.size());
        return donateList.size();
    }
}