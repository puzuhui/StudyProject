package com.example.admin.myapplication.api;

import android.content.Context;


import com.example.admin.myapplication.interfaces.GetResultCallBack;
import com.example.admin.myapplication.url.BaseUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/2.
 */
public class MainApi extends BaseApi {

    private static MainApi api;

    public static MainApi getInstance(Context context) {
        synchronized (MainApi.class) {
            if (api == null) {
                api = new MainApi(context);
            }
        }
        return api;
    }

    public MainApi(Context context) {
        super(context);
    }


    /**
     * 查询有无默认地址
     */
    public void searchdefaultaddressApi( GetResultCallBack callBack){
        Map<String, String> map = new HashMap<>();
        getLoad(BaseUrl.baseurl, map, callBack);
    }
}