package food.donation.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import food.donation.R;
import food.donation.ActivityClass.ViewProfileActivity;
import food.donation.ListClass.ViewProfileList;

import java.util.ArrayList;

/**
 * Created by admin on 18-11-2016.
 */
public class ViewProfileAdapter extends PagerAdapter {

    Context context;
    ArrayList<ViewProfileList> viewProfileList;
    LayoutInflater inflater;

    public ViewProfileAdapter(ViewProfileActivity viewProfile, ArrayList<ViewProfileList> viewProfileList) {
        this.context = viewProfile;
        this.viewProfileList = viewProfileList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return viewProfileList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.cust_view_profile, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.cust_view_profile_iv);
        imageView.setImageResource(viewProfileList.get(position).getImage());
        container.addView(view);
        return view;
    }
}


