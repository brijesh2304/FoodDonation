package food.donation.ActivityClass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import food.donation.AdapterClass.HomeAdapter;
import food.donation.Constans;
import food.donation.R;
public class HomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton floatingbtn;
    HomeAdapter homeAdapter;
    SharedPreferences sp;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sp = getSharedPreferences(Constans.PREF, Context.MODE_PRIVATE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        floatingbtn = (FloatingActionButton) findViewById(R.id.home_floating_btn);
        floatingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DonateFoodActivity.class);
                startActivity(intent);
            }
        });

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.home_tabLayout);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.home_pager);

        //Creating our pager adapter
        homeAdapter = new HomeAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(homeAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.menu_view_profile){
            startActivity(new Intent(HomeActivity.this,ViewProfileActivity.class));
        }
        if(id==R.id.menu_change_pass){
            startActivity(new Intent(HomeActivity.this,ChangePasswordActivity.class));
        }
        if(id==R.id.menu_logout){
            sp.edit().clear().commit();
            Toast.makeText(HomeActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomeActivity.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
