package com.bwei.yangjun.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.yangjun.Apis;
import com.bwei.yangjun.R;
import com.bwei.yangjun.bean.DengBean;
import com.bwei.yangjun.persenter.IPersenterImpl;
import com.bwei.yangjun.view.IView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {

    private Button button_deng;
    private EditText edit_phone,edit_pwd;
    private TextView text_zhuce;
    private ImageView image_QQ;
    private IPersenterImpl iPersenter;
    //private String dataUrl="";

    private final int TYPE_LOGIN=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_deng=findViewById(R.id.button_deng);
        edit_phone=findViewById(R.id.edit_phone);
        edit_pwd=findViewById(R.id.edit_pwd);
        text_zhuce=findViewById(R.id.zhuce);
        image_QQ=findViewById(R.id.QQ);
        iPersenter=new IPersenterImpl(this);
        text_zhuce.setOnClickListener(this);
        button_deng.setOnClickListener(this);
        image_QQ.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuce:
                Intent intent = new Intent(MainActivity.this, ZhuCeActivity.class);
                startActivity(intent);
                break;
            case R.id.button_deng:
                Map<String, String> params = new HashMap<>();

                String phone = edit_phone.getText().toString();
                String pwd = edit_pwd.getText().toString();
                params.put("mobile",phone);
                params.put("password",pwd);
                iPersenter.getRequest(Apis.URL_LOGIN,params,DengBean.class);
                break;

            case R.id.QQ:
                UMShareAPI shareAPI = UMShareAPI.get(MainActivity.this);
                UMShareConfig config=new UMShareConfig();
                config.isNeedAuthOnGetUserInfo(true);
                UMShareAPI.get(MainActivity.this).setShareConfig(config);
                shareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.i("TAG","onStart");
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Log.i("TAG",map+"");
                        String imageUrl = map.get("profile_image_url");
                        String screen_name = map.get("screen_name");
                        ImageLoader.getInstance().displayImage(imageUrl,image_QQ);
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        Log.i("TAG","onError");
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        Log.i("TAG","onCancel");
                    }
                });
                break;
        }
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof DengBean){
            DengBean dengBean= (DengBean) data;
            if(dengBean.getCode().equals("0")){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
