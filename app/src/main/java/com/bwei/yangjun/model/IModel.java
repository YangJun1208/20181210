package com.bwei.yangjun.model;

import com.bwei.yangjun.callback.MyCallBack;

import java.util.Map;

public interface IModel{
    //get
    //void getRequest(String dataUrl, Class clazz, MyCallBack callBack);

    //post

    void getRequest(String url, Map<String, String> params, Class clazz, MyCallBack callBack);
}
