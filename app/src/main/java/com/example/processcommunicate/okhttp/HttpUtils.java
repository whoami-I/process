package com.example.processcommunicate.okhttp;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.widget.Switch;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;

public class HttpUtils {
    private static final int GET = 0x11;
    private static final int POST = 0x12;
    private static final int DOWNLOAD_FILE = 0x13;
    private static final int UPLOAD_FILE = 0x14;
    private Context mContext;
    //默认使用okHttp的引擎
    private IHttpEngine mHttpEngine = new OkHttpEngine();

    //备用的引擎,优先使用这种
    private IHttpEngine mHttpEngine2;
    private String url;
    //默认为get请求
    private int requestCode = GET;
    private boolean mCache;
    private Map<String, Object> mParams;

    private static HttpUtils mInstance;

    public static HttpUtils getmInstance(Context context) {
        if (mInstance == null) {
            synchronized (HttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new HttpUtils(context);
                }
            }
        }
        return mInstance;
    }

    private HttpUtils(Context context) {
        this.mContext = context.getApplicationContext();
        mParams = new HashMap<>();
    }

    public HttpUtils initEngine(IHttpEngine httpEngine) {
        this.mHttpEngine = httpEngine;
        return this;
    }


    public HttpUtils withHttpEngine(IHttpEngine httpEngine, boolean replace) {
        if (replace) {
            mHttpEngine = httpEngine;
            mHttpEngine2 = null;

        } else {
            //一次性的使用，即每次使用完之后，一定要置null
            mHttpEngine2 = httpEngine;
        }
        return this;
    }

    public HttpUtils url(String url) {
        this.url = url;
        return this;
    }

    public void execute(Callback callback) {
        IHttpEngine httpEngine = getEngine();
        if (TextUtils.isEmpty(url)) {
            throw new AndroidRuntimeException("url can not be empty");
        }
        switch (requestCode) {
            case GET:
                httpEngine.get(mContext, url, mParams, callback, mCache);
                break;
            case POST:
                httpEngine.post(mContext, url, mParams, callback, mCache);
                break;
            case DOWNLOAD_FILE:
                break;
            case UPLOAD_FILE:
                break;
        }

        mHttpEngine2 = null;
    }

    private IHttpEngine getEngine() {
        if (mHttpEngine2 != null) {
            return mHttpEngine2;
        }
        return mHttpEngine;
    }


}
