package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class MineFragment extends Fragment {

    @BindView(R.id.textview)
    TextView textview;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);


        return view;
    }


    @OnClick(R.id.textview)
    public void setOnClic(View view) {
        Log.e("====", "点击");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())//指定了被观察者执行的线程环境
                .observeOn(Schedulers.io())//将接下来执行的线程环境指定为io线程
                .filter(new Predicate<Integer>() {//过滤
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer != 0;   //等于0的过滤掉
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("", "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer s) {
                        Log.e("onNext===", "" + s);
                        textview.setText("" + s);
                        textview.setTextColor(getResources().getColor(R.color.colorPrimary));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("", "complete");
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}