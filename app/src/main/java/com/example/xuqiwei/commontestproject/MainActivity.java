package com.example.xuqiwei.commontestproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xuqiwei.commontestproject.retrofit.RetrofitActivity;
import com.example.xuqiwei.commontestproject.test.activities.TestActivity;
import com.example.xuqiwei.commontestproject.xt.activity.XTActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.testbfk_textviewe)
    TextView testbfkTextviewe;
    private TextView toRetrofitActivity;
    private TextView toxttest_textviewe;
    private TextView tomodeltest_textviewe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
//        testbfkTextviewe.setText("hello111");
        toRetrofitActivity = (TextView) findViewById(R.id.toRetrofitActivity);
        toRetrofitActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RetrofitActivity.class));
            }
        });
        toxttest_textviewe = (TextView) findViewById(R.id.toxttest_textviewe);
        toxttest_textviewe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, XTActivity.class));
            }
        });
        tomodeltest_textviewe = (TextView) findViewById(R.id.tomodeltest_textviewe);
        tomodeltest_textviewe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });
    }

    @OnClick(R.id.testbfk_textviewe)
    public void onClick() {
        Toast.makeText(MainActivity.this, "1111111", Toast.LENGTH_SHORT).show();
    }
}
