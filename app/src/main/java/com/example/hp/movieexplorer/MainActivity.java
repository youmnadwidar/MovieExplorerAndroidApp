package com.example.hp.movieexplorer;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.movie_VP)
    ViewPager movieVP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SetupViewPager(movieVP);
        tabLayout.setupWithViewPager(movieVP);

    }

    private void SetupViewPager(ViewPager movieVP) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PostersFragment() , "TopRated");
        adapter.addFragment(new PostersFragment1() , "Popular");
        movieVP.setAdapter(adapter);
    }

     class ViewPagerAdapter extends FragmentPagerAdapter{
         private final List<Fragment> fragments = new ArrayList<>();
        private  final List<String> names = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        public  void addFragment (Fragment fragment , String name ){
          fragments.add(fragment);
            names.add(name);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return names.get(position);
        }
    }
}
