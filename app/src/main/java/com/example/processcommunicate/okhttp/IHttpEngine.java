package com.example.processcommunicate.okhttp;

import android.content.Context;

import java.util.Map;

import okhttp3.Callback;

public interface IHttpEngine {
    //post请求
    public void post(Context context, String url,
                     Map<String, Object> params,
                     Callback callback,
                     boolean cache);

    //get请求
    public void get(Context context, String url,
                    Map<String, Object> params,
                    Callback callback,
                    boolean cache);

    //取消下载

    //上传文件

    //下载文件

    //http添加证书


}
