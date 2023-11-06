package food.donation.AdapterClass;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import food.donation.ActivityClass.GuestActivity;
import food.donation.ListClass.GuestList;
import food.donation.R;

import java.util.List;

/*
 * Created by admin on 16-11-2016.
*/
public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.MyViewHolder> {

    List<GuestList> guestList;
    Context c;

    public GuestAdapter(GuestActivity guestActivity, List<GuestList> guestList) {

        this.c = guestActivity;
        this.guestList = guestList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView guestfname, guestlname, guestcno, guestaddress, guesttype, guestqty;

        public MyViewHolder(View view) {
            super(view);
            guestfname = (TextView) view.findViewById(R.id.cust_guest_fname);
            guestlname = (TextView) view.findViewById(R.id.cust_guest_lname);
            guestcno = (TextView) view.findViewById(R.id.cust_guest_cno);
            guestaddress = (TextView) view.findViewById(R.id.cust_guest_address);
            guesttype = (TextView) view.findViewById(R.id.cust_guest_food_type);
            guestqty = (TextView) view.findViewById(R.id.cust_guest_qty);
        }
    }

    @Override
    public GuestAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_guest, parent, false);
        return new GuestAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GuestAdapter.MyViewHolder holder, final int position) {
        final GuestList guestL = guestList.get(position);
        holder.guestfname.setText(guestList.get(position).getGuestfname());
        holder.guestlname.setText(guestList.get(position).getGuestlname());
        holder.guestcno.setText(guestList.get(position).getGuestcno());
        holder.guestaddress.setText(guestList.get(position).getGuestaddress());
        holder.guesttype.setText(guestList.get(position).getGuesttypefood());
        holder.guestqty.setText(guestList.get(position).getGuestqty());
    }

    @Override
    public int getItemCount() {
        Log.d("hit", "size : " + guestList.size());
        return guestList.size();
    }
}