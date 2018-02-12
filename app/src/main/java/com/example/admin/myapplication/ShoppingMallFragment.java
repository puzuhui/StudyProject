package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class ShoppingMallFragment extends Fragment {
    private View view;
    @BindView(R.id.tv)
    TextView textView;
    @BindView(R.id.cs)
    TextView cs;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopping_mall,null);
        unbinder = ButterKnife.bind(this,view);

        return view;
    }

    @OnClick(R.id.cs)
    public void ceshi(View view){
        Toast.makeText(getContext(),"点击测试",Toast.LENGTH_SHORT).show();

        //test();
        testRetrofid();
    }

    public interface BlogSersive{
        @GET("shoppinglist?product_type=31")
        retrofit2.Call<PayModel.ResultBean> getblog();
    }

    private void testRetrofid() {
//        Gson mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.188.122/huaji/index.php/Home/index/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.e("======",""+retrofit.toString());
        BlogSersive blogSersive = retrofit.create(BlogSersive.class);
        retrofit2.Call<PayModel.ResultBean> call = blogSersive.getblog();
        call.enqueue(new retrofit2.Callback<PayModel.ResultBean>() {
            @Override
            public void onResponse(retrofit2.Call<PayModel.ResultBean> call, final retrofit2.Response<PayModel.ResultBean> response) {
                final PayModel.ResultBean resultBean = response.body();
                Log.e("testRetrofid", ":====== "+response.body());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(resultBean.toString());
                    }
                });

            }

            @Override
            public void onFailure(retrofit2.Call<PayModel.ResultBean> call, Throwable t) {
                System.out.println("连接失败");
            }
        });
    }

    private void test() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://192.168.188.122/huaji/index.php/Home/index/shoppinglist?product_type=31").build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("onFailure", ":===== "+request );
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.e("onResponse", ":====== "+response.body().string() );
                final String s= response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(s);
                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
