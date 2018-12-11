package com.bwei.yangjun.persenter;

import java.util.HashMap;
import java.util.Map;

public interface Ipersener {
    //void getRequest(String dataUrl,Class clazz);

    void getRequest(String dataUrl, Map<String, String> map, Class clazz);
}
