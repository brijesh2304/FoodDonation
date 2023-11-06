package food.donation.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import food.donation.ActivityClass.LoginActivity;
import food.donation.ListClass.LoginList;
import food.donation.R;

import java.util.ArrayList;

/**
 * Created by admin on 17-11-2016.
 */
public class LoginAdapter extends PagerAdapter {

    Context context;
    ArrayList<LoginList> loginList;
    LayoutInflater inflater;

    public LoginAdapter(LoginActivity loginActivity, ArrayList<LoginList> loginList) {
        this.context = loginActivity;
        this.loginList = loginList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return loginList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.cust_login, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.cust_login_iv);
        imageView.setImageResource(loginList.get(position).getImage());
        container.addView(view);
        return view;
    }

}
