package food.donation.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import food.donation.R;
import food.donation.ActivityClass.UpdateActivity;
import food.donation.ListClass.UpdateList;

import java.util.ArrayList;

/**
 * Created by admin on 18-11-2016.
 */
public class UpdateAdapter extends PagerAdapter {

    Context context;
    ArrayList<UpdateList> updateList;
    LayoutInflater inflater;

    public UpdateAdapter(UpdateActivity update, ArrayList<UpdateList> updateList) {
        this.context = update;
        this.updateList = updateList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return updateList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.cust_update, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.cust_update_iv);
        imageView.setImageResource(updateList.get(position).getImage());
        container.addView(view);
        return view;
    }
}