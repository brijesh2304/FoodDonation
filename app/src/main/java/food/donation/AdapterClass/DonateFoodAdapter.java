package food.donation.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import food.donation.ActivityClass.DonateFoodActivity;
import food.donation.ListClass.DonateFoodList;
import food.donation.R;

import java.util.ArrayList;

/**
 * Created by admin on 18-11-2016.
 */
public class DonateFoodAdapter extends PagerAdapter {

    Context context;
    ArrayList<DonateFoodList> donateFoodList;
    LayoutInflater inflater;

    public DonateFoodAdapter(DonateFoodActivity donateFood, ArrayList<DonateFoodList> donateFoodList) {
        this.context = donateFood;
        this.donateFoodList = donateFoodList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return donateFoodList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.cust_donate_food, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.cust_donate_food_iv);
        imageView.setImageResource(donateFoodList.get(position).getImage());
        container.addView(view);
        return view;
    }
}

