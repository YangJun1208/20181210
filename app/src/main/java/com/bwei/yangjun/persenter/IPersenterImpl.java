package com.bwei.yangjun.persenter;

import android.content.Context;

import com.bwei.yangjun.callback.MyCallBack;
import com.bwei.yangjun.model.IModel;
import com.bwei.yangjun.model.IModelImpl;
import com.bwei.yangjun.view.IView;

import java.util.Map;

public class IPersenterImpl implements Ipersener {

    private IModelImpl iModel;
    private IView iView;

    public IPersenterImpl(IView mIView){
        iView=mIView;
        iModel=new IModelImpl();
    }
    /*@Override
    public void getRequest(String dataUrl, Class clazz) {
        iModel.getRequest(dataUrl, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                iView.onSuccess(data);
            }
        });
    }*/



    public void deteach(){
        iModel=null;
        iView=null;
    }


    @Override
    public void getRequest(String dataUrl, Map<String, String> map, Class clazz) {
        iModel.getRequest(dataUrl, map, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                iView.onSuccess(data);
            }
        });
    }
}
