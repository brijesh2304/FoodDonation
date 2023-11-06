package food.donation.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import food.donation.ActivityClass.ForgetPassActivity;
import food.donation.ListClass.ForgetPassList;
import food.donation.R;

import java.util.ArrayList;

/**
 * Created by admin on 18-11-2016.
 */
public class ForgetPassAdapter extends PagerAdapter {

    Context context;
    ArrayList<ForgetPassList> forgetPassList;
    LayoutInflater inflater;

    public ForgetPassAdapter(ForgetPassActivity forgetPass, ArrayList<ForgetPassList> forgetPassList) {
        this.context = forgetPass;
        this.forgetPassList = forgetPassList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return forgetPassList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.cust_forget_pass, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.cust_forget_pass_iv);
        imageView.setImageResource(forgetPassList.get(position).getImage());
        container.addView(view);
        return view;
    }
}