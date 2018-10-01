package com.cristal_inca.friebase_ashor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.cristal_inca.friebase_ashor.fragment.JoinFragment;
import com.cristal_inca.friebase_ashor.fragment.LoginFragment;
import com.cristal_inca.friebase_ashor.fragment.StartFragment;

public class MainActivity extends AppCompatActivity {


    ViewPager startViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startViewPager = findViewById(R.id.start_view_pager);
        startViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new StartFragment();
            }

            @Override
            public int getCount() {
                return 1;
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.start_navigation_home:
                    return true;
                case R.id.start_navigation_join:
                    startViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                        @Override
                        public Fragment getItem(int position) {
                            return new JoinFragment();
                        }

                        @Override
                        public int getCount() {
                            return 1;
                        }
                    });
                    return true;
                case R.id.start_navigation_login:
                    startViewPager.setAdapter(
                            new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                        @Override
                        public Fragment getItem(int position) {
                            return new LoginFragment();
                        }

                        @Override
                        public int getCount() {
                            return 1;
                        }
                    });
                    return true;
            }
            return false;
        }
    };

}