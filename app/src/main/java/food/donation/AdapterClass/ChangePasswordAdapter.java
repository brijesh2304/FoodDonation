package food.donation.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import food.donation.ActivityClass.ChangePasswordActivity;
import food.donation.ListClass.ChangePasswordList;
import food.donation.R;

import java.util.ArrayList;

/**
 * Created by admin on 18-11-2016.
 */
public class ChangePasswordAdapter extends PagerAdapter {

    Context context;
    ArrayList<ChangePasswordList> changePasswordList;
    LayoutInflater inflater;

    public ChangePasswordAdapter(ChangePasswordActivity changePassword, ArrayList<ChangePasswordList> changePasswordList) {
        this.context = changePassword;
        this.changePasswordList = changePasswordList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return changePasswordList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.cust_change_password, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.cust_change_password_iv);
        imageView.setImageResource(changePasswordList.get(position).getImage());
        container.addView(view);
        return view;
    }
}

