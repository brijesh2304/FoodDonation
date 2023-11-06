package food.donation.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;

import food.donation.ListClass.NewsFeedList;
import food.donation.R;

import java.util.ArrayList;

/**
 * Created by admin on 18-11-2016.
 */
public class NewsFeedAdapter extends PagerAdapter {

    FragmentActivity context;
    ArrayList<NewsFeedList> newsFeedList;
    LayoutInflater inflater;

    public NewsFeedAdapter(FragmentActivity fragment, ArrayList<NewsFeedList> newsFeedList) {
        this.context = fragment;
        this.newsFeedList = newsFeedList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return newsFeedList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.cust_news_feed, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.cust_news_feed_iv);
        imageView.setImageResource(newsFeedList.get(position).getImage());
        container.addView(view);
        return view;
    }
}