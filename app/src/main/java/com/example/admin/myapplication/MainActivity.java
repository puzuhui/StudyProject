package com.example.admin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myapplication.api.MainApi;
import com.example.admin.myapplication.interfaces.GetResultCallBack;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/1/2.
 * 作者：蒲祖辉
 * 电话：15823903420
 */

public class MainActivity extends Activity {
    Toolbar toolbar;
    TextView testview;
    @BindView(R.id.wbutton)
    Button button;
    @BindView(R.id.coodlayout)
    Button coodlayout;
    @BindView(R.id.coodlayout_t)
    Button coodlayout_t;
    IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        api= WXAPIFactory.createWXAPI(this, Constants.APP_ID);

        toolbar = findViewById(R.id.toolbar);
        testview = findViewById(R.id.testview);

        //toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("中国");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        //toolbar.setSubtitle("厉害");
        toolbar.setNavigationIcon(R.mipmap.back_left);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("====", "点击 " );
            }
        });

        testview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Test.class);
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.wbutton,R.id.coodlayout,R.id.coodlayout_t})
    public void setClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.wbutton:
//                intent = new Intent(MainActivity.this, WXPayEntryActivity.class);
//                startActivity(intent);
                Toast.makeText(MainActivity.this, "获取订单中...", Toast.LENGTH_SHORT).show();
                sendPayRequest();
                break;
            case R.id.coodlayout:
                intent = new Intent(MainActivity.this, TablayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.coodlayout_t:
                intent = new Intent(MainActivity.this, CoordinatorLayoutActivity.class);
                startActivity(intent);
                break;
        }
    }


    private void sendPayRequest(){
        MainApi.getInstance(this).searchdefaultaddressApi(new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                //api= WXAPIFactory.createWXAPI(MainActivity.this, Constants.APP_ID);
                if(result == null ){
                    Toast.makeText(MainActivity.this, "没有正常调起支付", Toast.LENGTH_SHORT).show();
                }

//                PayReq req = new PayReq();
//                PayModel payModel = new PayModel();
//                api.registerApp(payModel.getAppid());
//                req.appId = payModel.getAppid();
//                req.partnerId = payModel.getPartnerid();
//                req.prepayId = payModel.getPrepayid();
//                req.nonceStr = payModel.getNoncestr();
//                req.timeStamp = payModel.getTimestamp();
//                req.packageValue = "Sign=WXPay";
//                req.sign = payModel.getSign();
//                Toast.makeText(MainActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
//                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
//                //3.调用微信支付sdk支付方法
//                api.sendReq(req);
            }
        });
    }


}
