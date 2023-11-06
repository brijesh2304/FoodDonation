package food.donation.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import food.donation.ActivityClass.ChangePass;
import food.donation.ListClass.ChangePassList;
import food.donation.R;

import java.util.ArrayList;

/**
 * Created by admin on 18-11-2016.
 */
public class ChangePassAdapter extends PagerAdapter {

    Context context;
    ArrayList<ChangePassList> changePassList;
    LayoutInflater inflater;

    public ChangePassAdapter(ChangePass changePass, ArrayList<ChangePassList> changePassList) {
        this.context = changePass;
        this.changePassList = changePassList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return changePassList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.cust_change_pass, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.cust_change_pass_iv);
        imageView.setImageResource(changePassList.get(position).getImage());
        container.addView(view);
        return view;
    }
}
