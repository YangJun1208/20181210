package com.bwei.yangjun;

import android.content.Context;
import android.util.Log;

public class UnCachHandler implements Thread.UncaughtExceptionHandler {

    private static UnCachHandler unCachHandler;
    private Context mContext;

    public UnCachHandler(Context context) {
        init(context);
    }

    public static UnCachHandler getInstance(Context context){
        if(unCachHandler==null){
            synchronized (UnCachHandler.class){
                unCachHandler=new UnCachHandler(context);
            }
        }
            return unCachHandler;
    }

    public void init(Context context){
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext=context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {
            Log.i("TAG",e.getLocalizedMessage());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
