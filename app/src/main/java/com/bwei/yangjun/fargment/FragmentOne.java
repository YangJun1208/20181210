package com.bwei.yangjun.fargment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bwei.yangjun.Apis;
import com.bwei.yangjun.R;
import com.bwei.yangjun.activity.ThreeActivity;
import com.bwei.yangjun.bean.LuBoBean;
import com.bwei.yangjun.persenter.IPersenterImpl;
import com.bwei.yangjun.view.IView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FragmentOne extends Fragment implements IView {

    private Banner banner;
    private IPersenterImpl iPersenter;
    private Button button;
    //private String dataUrl="http://www.zhaoapi.cn/home/getHome";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentone,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        banner=view.findViewById(R.id.banner);

        button=view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ThreeActivity.class);
                startActivity(intent);
            }
        });
        iPersenter=new IPersenterImpl(this);
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.setImageLoader(new ImageLoaderInterface<ImageView>() {

            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                LuBoBean.DataBean.BannerBean bannerBean= (LuBoBean.DataBean.BannerBean) path;
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(bannerBean.replace(),imageView);
            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView=new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }
        });
        loadData();
    }

    private void loadData() {
        iPersenter.getRequest(Apis.URL_LOGIN_IMG,new HashMap<String, String>(),LuBoBean.class);
    }

    @Override
    public void onSuccess(Object data) {
        LuBoBean luBoBean= (LuBoBean) data;
        banner.setImages(luBoBean.getData().getBanner());
        banner.start();
    }
}
