package com.bwei.yangjun.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import com.bwei.yangjun.R;
import com.bwei.yangjun.fargment.FragmentOne;
import com.bwei.yangjun.fargment.FragmentTwo;
import com.bwei.yangjun.view.IView;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private List<Fragment> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewPager=findViewById(R.id.viewPager);
        radioGroup=findViewById(R.id.radio);
        list=new ArrayList<>();
        list.add(new FragmentOne());
        list.add(new FragmentTwo());
        list.add(new FragmentTwo());
        list.add(new FragmentTwo());
        list.add(new FragmentTwo());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.but1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.but2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.but3:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.but4:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.but5:
                        viewPager.setCurrentItem(4);
                        break;
                }
            }
        });
    }
}
