package com.bwei.yangjun.model;

import com.bwei.yangjun.callback.MyCallBack;
import com.bwei.yangjun.okClient.ICallBack;
import com.bwei.yangjun.okClient.OkHttpUtils;

import java.util.Map;

public class IModelImpl implements IModel {

    //post

    @Override
    public void getRequest(String url, Map<String, String> params, Class clazz, final MyCallBack callBack) {
        OkHttpUtils.getmInstance().postEnqueue(url, params, clazz, new ICallBack() {
            @Override
            public void onsuccess(Object obj) {
                callBack.onSuccess(obj);
            }

            @Override
            public void onfail(Exception e) {
                callBack.onSuccess(e.getMessage());
            }
        });
    }


    // @Override
   // public void getRequest(String dataUrl, final Class clazz, final MyCallBack callBack) {
        //get请求
        /*OkHttpUtils.getmInstance().getEnqueue(dataUrl,new ICallBack() {
            @Override
            public void onsuccess(Object obj) {
                callBack.onSuccess(obj);
            }

            @Override
            public void onfail(Exception e) {
                callBack.onSuccess(e.getMessage());
            }
        },clazz);*/
        //post请求



   // }
}
