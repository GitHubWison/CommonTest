package com.example.xuqiwei.commontestproject.retrofit;


import android.support.v4.app.Fragment;

import android.view.View;
import android.widget.TextView;

import com.example.commonlibrary.fragment.CommonAbstractFragment;
import com.example.xuqiwei.commontestproject.R;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class RetrofitFragment extends CommonAbstractFragment {
    private TextView getweather_textview;
    private TextView getsimple_textview;
    private TextView getmission_textview;

    public RetrofitFragment() {
        // Required empty public constructor
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_retrofit;
    }

//    @Override
//    public void initDatas() {
//
//    }
//
//    @Override
//    public void initViews() {
//        getweather_textview = (TextView)getView().findViewById(R.id.getweather_textview);
//        getsimple_textview = (TextView)getView().findViewById(R.id.getsimple_textview);
//        getmission_textview = (TextView)getView().findViewById(R.id.getmission_textview);
//    }
 /*
    @Override
    public void initEvents() {


        getmission_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit laughRetrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .baseUrl("http://xiaohua.hao.360.cn")
                        .build();
                LaughModel.LaughService service = laughRetrofit.create(LaughModel.LaughService.class);
                service.getlaugh()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<JsonObject>() {
                            @Override
                            public void onCompleted() {
                                printLog("onCompleted");

                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onNext(JsonObject jsonObject) {
                                printLog("simpleClasses="+jsonObject.toString());
                            }
                        });
            }
        });


        getsimple_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit simpleRetrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .baseUrl("https://api.github.com")
                        .build();
                SimpleClass.SimpleService simpleService = simpleRetrofit.create(SimpleClass.SimpleService.class);
                simpleService.contributors("square", "retrofit")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<JSONArray>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(JSONArray simpleClasses) {
//                                for (SimpleClass contributor : simpleClasses) {
//                                        printLog(contributor.getLogin() + " (" + contributor.getContributions() + ")");
////                                    System.out.println(contributor.login + " (" + contributor.contributions + ")");
//                                }
                                printLog("simpleClasses="+simpleClasses.length());
                            }
                        });
            }
        });

        getweather_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                请求天气
                Retrofit weatherRetrofit = new Retrofit
                        .Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .baseUrl("http://api.map.baidu.com")
                        .build();
                AllWeatherModel.WeatherAPI weatherAPI = weatherRetrofit.create(AllWeatherModel.WeatherAPI.class);
//                final Call<AllWeatherModel> weatherModelCall = weatherAPI.weather();
//                printLog(weatherAPI.);
                weatherAPI.weather()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<FailureModel>() {

                            @Override
                            public void onCompleted() {

                                printLog("onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
//                                printLog("Throwable=",e.printStackTrace());
                            }

                            @Override
                            public void onNext(FailureModel allWeatherModel) {
                                printLog("message= "+allWeatherModel.getMessage()+"**status="+allWeatherModel.getStatus());

                            }
                        });
//                weatherAPI.ge

//                            AllWeatherModel allWeatherModel = weatherModelCall.execute().body();
//                            printLog("allWeatherModel== ",allWeatherModel.getDate());





            }
        });

    }
*/

}
