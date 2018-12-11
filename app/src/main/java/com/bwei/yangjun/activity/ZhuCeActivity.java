package com.bwei.yangjun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.yangjun.Apis;
import com.bwei.yangjun.R;
import com.bwei.yangjun.bean.ZhuCeBean;
import com.bwei.yangjun.persenter.IPersenterImpl;
import com.bwei.yangjun.view.IView;

import java.util.HashMap;
import java.util.Map;

public class ZhuCeActivity extends AppCompatActivity implements View.OnClickListener,IView {

    private EditText edit_phone,edit_pwd;
    private Button button;
    private IPersenterImpl iPersenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        edit_phone=findViewById(R.id.zhu_phone);
        edit_pwd=findViewById(R.id.zhu_pwd);
        button=findViewById(R.id.button_zhu);
        iPersenter=new IPersenterImpl(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_zhu:
                Map<String, String> params = new HashMap<>();

                String phone = edit_phone.getText().toString();
                String pwd = edit_pwd.getText().toString();
                params.put("mobile",phone);
                params.put("password",pwd);
                iPersenter.getRequest(Apis.URL_ZHUCE,params,ZhuCeBean.class);
                break;
        }
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof ZhuCeBean){
            ZhuCeBean zhuCeBean= (ZhuCeBean) data;
            if(zhuCeBean.getCode().equals("0")){
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ZhuCeActivity.this, MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "天呢！用户已注册", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
