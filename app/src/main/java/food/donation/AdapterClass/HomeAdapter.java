package food.donation.AdapterClass;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import food.donation.FragmentClass.NewsFeedFragment;
import food.donation.FragmentClass.ViewDonateFoodFragment;

/**
 * Created by admin on 14-11-2016.
 */
public class HomeAdapter extends FragmentStatePagerAdapter {
    int tabCount;
    public static int count = 2;

    //Constructor to the class
    public HomeAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "View Donate Food";
            case 1:
                return "News Feed";
            }
        return null;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                ViewDonateFoodFragment viewDonateFoodFragment = new ViewDonateFoodFragment();
                return viewDonateFoodFragment;
            case 1:
                NewsFeedFragment newsFeedFragment = new NewsFeedFragment();
                return newsFeedFragment;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return count;
    }
}
