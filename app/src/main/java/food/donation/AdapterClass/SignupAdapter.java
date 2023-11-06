package food.donation.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import food.donation.R;
import food.donation.ActivityClass.SignupActivity;
import food.donation.ListClass.SignupList;

import java.util.ArrayList;

/**
 * Created by admin on 17-11-2016.
 */
public class SignupAdapter extends PagerAdapter {

    Context context;
    ArrayList<SignupList> signupList;
    LayoutInflater inflater;

    public SignupAdapter(SignupActivity signupActivity, ArrayList<SignupList> signupList) {
        this.context = signupActivity;
        this.signupList = signupList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return signupList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.cust_signup, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.cust_signup_iv);
        imageView.setImageResource(signupList.get(position).getImage());
        container.addView(view);
        return view;
    }
}

