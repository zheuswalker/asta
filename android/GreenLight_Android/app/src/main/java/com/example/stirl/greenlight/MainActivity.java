package com.example.stirl.greenlight;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent i = getIntent();
//        String id = i.getStringExtra("id");
//        Bundle bundle = new Bundle();
//        bundle.putString("id",id.toString());

        tabLayout = (TabLayout) findViewById(R.id.tlMain);
        viewPager = (ViewPager) findViewById(R.id.vpMain);
        adapter = new ViewPageAdapter(getSupportFragmentManager());
        FragmentProfile profile = new FragmentProfile();
        FragmentSubmit submission = new FragmentSubmit();
//        submission.setArguments(bundle);
//        profile.setArguments(bundle);
        //Add fragments here
        adapter.AddFragment(new FragmentHome(),"");
        adapter.AddFragment(new FragmentOnline(),"");
        adapter.AddFragment(submission,"");
//        adapter.AddFragment(new FragmentNotif(),"");
        adapter.AddFragment(profile,"");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.searchwhite);
        tabLayout.getTabAt(2).setIcon(R.drawable.resume);
//        tabLayout.getTabAt(3).setIcon(R.drawable.ic_notifications);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_menu);

    }
}
