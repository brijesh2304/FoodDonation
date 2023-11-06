package food.donation.FragmentClass;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import food.donation.AdapterClass.NewsFeedAdapter;
import food.donation.ListClass.NewsFeedList;
import food.donation.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends Fragment {

    TextView title1,title2,title3,title4,title5,title6,title7,title8,title9,title10,no1,no2
            ,no3,no4,no5,no6,no7,no8,no9,no10;

    ViewPager mViewPager = null;
    NewsFeedAdapter newsFeedAdapter;
    ArrayList<NewsFeedList> newsFeedList;
    int count = 0;
    Timer timer;
    Animation a;
    int[] image = {R.drawable.slider1, R.drawable.slider2};


    public NewsFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_feed, container, false);
        title1 = (TextView)view. findViewById(R.id.news_feed_title1);
        title2 = (TextView)view. findViewById(R.id.news_feed_title2);
        title3 = (TextView)view. findViewById(R.id.news_feed_title3);
        title4 = (TextView)view. findViewById(R.id.news_feed_title4);
        title5 = (TextView)view. findViewById(R.id.news_feed_title5);
        title6 = (TextView)view. findViewById(R.id.news_feed_title6);
        title7 = (TextView)view. findViewById(R.id.news_feed_title7);
        title8 = (TextView)view. findViewById(R.id.news_feed_title8);
        title9 = (TextView)view. findViewById(R.id.news_feed_title9);
        title10 = (TextView)view. findViewById(R.id.news_feed_title10);
        no1 = (TextView)view. findViewById(R.id.news_feed_no1);
        no2 = (TextView)view. findViewById(R.id.news_feed_no2);
        no3 = (TextView)view. findViewById(R.id.news_feed_no3);
        no4 = (TextView)view. findViewById(R.id.news_feed_no4);
        no5 = (TextView)view. findViewById(R.id.news_feed_no5);
        no6 = (TextView)view. findViewById(R.id.news_feed_no6);
        no7 = (TextView)view. findViewById(R.id.news_feed_no7);
        no8 = (TextView)view. findViewById(R.id.news_feed_no8);
        no9 = (TextView)view. findViewById(R.id.news_feed_no9);
        no10 = (TextView)view. findViewById(R.id.news_feed_no10);

        mViewPager = (ViewPager)view. findViewById(R.id.news_feed_pager);
        mViewPager.setCurrentItem(0);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count <= newsFeedList.size()) {
                            mViewPager.setCurrentItem(count);
                            for (int i = 0; i <= newsFeedList.size(); i++) {
                              /*  a = AnimationUtils.loadAnimation(getActivity(), R.anim.banner_animation);
                                mViewPager.startAnimation(a);*/
                            }
                            count++;
                        } else {
                            count = 0;
                            mViewPager.setCurrentItem(count);
                        }
                    }
                });

            }
        }, 3000, 3000);

        newsFeedList = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            NewsFeedList list = new NewsFeedList();
            list.setImage(image[i]);
            newsFeedList.add(list);
        }
        newsFeedAdapter = new NewsFeedAdapter(getActivity(), newsFeedList);
        mViewPager.setAdapter(newsFeedAdapter);

        return view;
    }

}
