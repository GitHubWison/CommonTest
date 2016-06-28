package com.example.xuqiwei.commontestproject.xt.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.commonlibrary.fragment.CommonAbstractFragment;
import com.example.commonlibrary.staticstring.CacheName;
import com.example.commonlibrary.tools.Tool;
import com.example.xuqiwei.commontestproject.R;
import com.example.xuqiwei.commontestproject.eventbus.HttpEvent;
import com.example.xuqiwei.commontestproject.eventbus.TestEvent;
import com.example.xuqiwei.commontestproject.http.APIService;
import com.example.xuqiwei.commontestproject.http.APIServiceImp;
//import com.example.xuqiwei.commontestproject.mvp.activities.MVPActivity;
import com.example.xuqiwei.commontestproject.loginmvp.view.activities.LoginActivity;
import com.example.xuqiwei.commontestproject.tasklist.activities.TaskListAcitiviy;
import com.example.xuqiwei.commontestproject.xt.model.UserInfo;
import com.example.xuqiwei.commontestproject.zuhe.activities.ZuHeActivity;

import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class XTFragment extends CommonAbstractFragment {

    @BindView(R.id.bf01_textview)
    TextView bf01Textview;
    @BindView(R.id.bf02_textview)
    TextView bf02Textview;
    @BindView(R.id.savedomain_textview)
    TextView savedomainTextview;
    @BindView(R.id.testnet_textview)
    TextView testnetTextview;
    @BindView(R.id.login_textview)
    TextView loginTextview;
    @BindView(R.id.loadbeancachetest_textview)
    TextView loadbeancachetestTextview;
    @BindView(R.id.zuhetest_textview)
    TextView zuhetestTextview;
    @BindView(R.id.mvp_textview)
    TextView mvpTextview;

    private APIService apiService;


    public XTFragment() {
        // Required empty public constructor
    }


    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_xt;
    }

    @Override
    public void initDatas() {
        apiService = new APIServiceImp();
    }


    @OnClick(R.id.savedomain_textview)
    public void saveDomain(View view) {
        saveStringOrIntCache(CacheName.domain_String, "http://192.168.191.1:4500");
        toast("保存成功");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        printLog("XTFragment销毁");

    }


    @Subscribe
    public void onEventMainThread(final TestEvent event) {
        String msg = "onEventMainThread收到了消息：" + event.getMsg().toString();
//        printLog(msg);
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
        Observable.create(new Observable.OnSubscribe<JSONObject>() {

            @Override
            public void call(Subscriber<? super JSONObject> subscriber) {
                subscriber.onNext(event.getMsg());

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(JSONObject jsonObject) {
                        savedomainTextview.setText(jsonObject.toString());

                    }
                });
    }


    @Subscribe
    public void onEventMainThread(HttpEvent event) {

        if (event.getMethodString().equals(APIService.login)) {
            JSONObject userInfoJson = event.getJsonObject();
            UserInfo userInfo = JSON.parseObject(userInfoJson.toString(), UserInfo.class);
//            将信息缓存起来
            Tool.saveJsonCache(getContext(), CacheName.userinfo_json, userInfoJson);
            Toast.makeText(getContext(), userInfo.getAccessFuncInfoList().size() + "", Toast.LENGTH_SHORT).show();
            toActivity(TaskListAcitiviy.class);

        }
    }

    @OnClick(R.id.testnet_textview)
    public void onClick() {
        new APIServiceImp().connectTest(getContext());
    }


    @OnClick(R.id.login_textview)
    public void login() {
//        执行登陆的操作
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("LoginName", "fe0a591");
            jsonObject.put("Password", Tool.getMD5("123"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        apiService.login(getContext(), jsonObject);

    }


    @OnClick(R.id.loadbeancachetest_textview)
    public void loadBeanCache() {
        UserInfo userInfo = Tool.getBeanCache(getContext(), CacheName.userinfo_json, UserInfo.class);
        toast(userInfo.getHospitalName());
    }


    @OnClick(R.id.zuhetest_textview)
    public void zuheTest() {
        startActivity(new Intent(getActivity(), ZuHeActivity.class));

    }


    @OnClick(R.id.mvp_textview)
    public void mvpTest() {
//        startActivity(new Intent(getActivity(), MVPActivity.class));
        toActivity(LoginActivity.class);
    }
}
