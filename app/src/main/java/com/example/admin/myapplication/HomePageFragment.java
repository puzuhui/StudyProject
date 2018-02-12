package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class HomePageFragment extends Fragment {
    ListView listView;
    private View view;
    private List<Map<String,Object>> list;
    int[] item = {R.id.textview};
    String[] itemtext = {"name"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homepage,null);
        listView = view.findViewById(R.id.listview);

        list= new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Map<String,Object> map = new HashMap();
            map.put("name",""+i);
            list.add(map);
        }

        SimpleAdapter arrayAdapter = new SimpleAdapter(getContext(),list,R.layout.fragment_mine,itemtext,item);
        listView.setAdapter(arrayAdapter);
        return view;
    }

}
