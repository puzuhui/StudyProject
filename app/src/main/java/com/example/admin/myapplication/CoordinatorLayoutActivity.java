package com.example.admin.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 2018/1/8.
 * 作者：蒲祖辉
 * 电话：15823903420
 * 公司：铭轩科技
 */

public class CoordinatorLayoutActivity extends FragmentActivity {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();
    private HomePageFragment homePageFragment;
    private MineFragment mineFragment;
    private RechargeFragment rechargeFragment;
    private ShoppingMallFragment shoppingFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_t);
        ButterKnife.bind(this);

        //设置TabLayout的模式
        tablayout.setTabMode(TabLayout.MODE_FIXED);

        list.add("重庆大学");
        list.add("重庆师范大学");
        list.add("西南大学");
        list.add("中国传媒大学");

        fragmentArrayList = new ArrayList<>();
        homePageFragment = new HomePageFragment();
        mineFragment = new MineFragment();
        rechargeFragment = new RechargeFragment();
        shoppingFragment = new ShoppingMallFragment();
        fragmentArrayList.add(homePageFragment);
        fragmentArrayList.add(shoppingFragment);
        fragmentArrayList.add(rechargeFragment);
        fragmentArrayList.add(mineFragment);

        tablayout.addTab(tablayout.newTab().setText(list.get(0)));
        tablayout.addTab(tablayout.newTab().setText(list.get(1)));
        tablayout.addTab(tablayout.newTab().setText(list.get(2)));
        tablayout.addTab(tablayout.newTab().setText(list.get(3)));

        HuaJiPagerAdapter huaJiPagerAdapter = new HuaJiPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(huaJiPagerAdapter);

        tablayout.setupWithViewPager(viewpager);
    }

    //    重写FragmentPagerAdapter方法
    public class HuaJiPagerAdapter extends FragmentPagerAdapter {
        public HuaJiPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }
    }
}
