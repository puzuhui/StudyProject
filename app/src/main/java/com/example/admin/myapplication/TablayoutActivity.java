package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 2018/1/9.
 * 作者：蒲祖辉
 * 电话：15823903420
 * 公司：铭轩科技
 */

public class TablayoutActivity extends FragmentActivity {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    List<String> list;
    private ArrayList<Fragment> fragmentArrayList;
    private HomePageFragment homePageFragment;
    private MineFragment mineFragment;
    private RechargeFragment rechargeFragment;
    private ShoppingMallFragment shoppingFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        ButterKnife.bind(this);

        list = new ArrayList<>();

        list.add("热门推荐");
        list.add("热门收藏");
        list.add("本月热榜");
        list.add("今日热榜");

        fragmentArrayList = new ArrayList<>();
        homePageFragment = new HomePageFragment();
        mineFragment = new MineFragment();
        rechargeFragment = new RechargeFragment();
        shoppingFragment = new ShoppingMallFragment();
        fragmentArrayList.add(homePageFragment);
        fragmentArrayList.add(shoppingFragment);
        fragmentArrayList.add(rechargeFragment);
        fragmentArrayList.add(mineFragment);

        //设置TabLayout的模式
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.addTab(tablayout.newTab().setText(list.get(0)));
        tablayout.addTab(tablayout.newTab().setText(list.get(1)));
        tablayout.addTab(tablayout.newTab().setText(list.get(2)));
        tablayout.addTab(tablayout.newTab().setText(list.get(3)));

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(TablayoutActivity.this,"点击"+tab.getText(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中tab的逻辑
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        HuaJiPagerAdapter instantaneousPagerAdapter = new HuaJiPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(instantaneousPagerAdapter);
        viewpager.setCurrentItem(2);//设置默认显示
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
