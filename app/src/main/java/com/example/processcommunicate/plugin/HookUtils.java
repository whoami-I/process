package com.example.processcommunicate.plugin;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.example.processcommunicate.log.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class HookUtils {
    private static final String TAG = "HookUtils";
    private static final String ORIGIN_INTENT = "real_intent";
    Class<? extends Activity> mProxyActivityClass;
    Context mContext;

    public HookUtils(Class<? extends Activity> proxyActivityClass
            , Context context) {
        mProxyActivityClass = proxyActivityClass;
        this.mContext = context;
    }

    public void hookStartActivity() throws Exception {
        Class<?> iamClass = Class.forName("android.app.IActivityManager");


        Class<?> activityManagerClass = ActivityManager.class;

        Field[] declaredFields = activityManagerClass.getDeclaredFields();
        Method[] declaredMethods = activityManagerClass.getDeclaredMethods();
        for (int i = 0; i < declaredFields.length; i++) {
            Log.e(TAG, "Field --> " + declaredFields[i]);
        }
        for (int i = 0; i < declaredMethods.length; i++) {
            Log.e(TAG, "Method --> " + declaredMethods[i]);
        }
        Field singletonField = activityManagerClass
                .getDeclaredField("IActivityManagerSingleton");
        Method getServiceMethod = activityManagerClass
                .getDeclaredMethod("getService");


        if (!getServiceMethod.isAccessible()) {
            getServiceMethod.setAccessible(true);
        }
        Object o = getServiceMethod.invoke(null, null);


        Object proxyInstance = Proxy.newProxyInstance(
                iamClass.getClassLoader(),
                new Class[]{iamClass.getClass()},
                new MInvocationHandler(o));
        //将代理对象注入
        Class<?> singletonClass = Class.forName("android.util.Singleton");
        Field mInstanceField = singletonClass.getDeclaredField("mInstance");
        if (!mInstanceField.isAccessible()) {
            mInstanceField.setAccessible(true);
        }
        mInstanceField.set(o, proxyInstance);

    }

    public void hookStartActivity2() throws Exception {
        Class<?> amnClass = Class.forName("android.app.ActivityManagerNative");
//        Method getDefaultMethod = amnClass
//                .getDeclaredMethod("getDefault");
//        if (!getDefaultMethod.isAccessible()) {
//            getDefaultMethod.setAccessible(true);
//        }
//        Object o = getDefaultMethod.invoke(null, null);
        Field gDefaultField = amnClass.getDeclaredField("gDefault");
        if (!gDefaultField.isAccessible()) {
            gDefaultField.setAccessible(true);
        }


        Class<?> singletonClass = Class.forName("android.util.Singleton");
        Field mInstanceField = singletonClass.getDeclaredField("mInstance");
        if (!mInstanceField.isAccessible()) {
            mInstanceField.setAccessible(true);
        }
        Object gDefault = gDefaultField.get(null);
        Object o = mInstanceField.get(gDefault);

        Class<?> iamClass = Class.forName("android.app.IActivityManager");


        Object proxyInstance = Proxy.newProxyInstance(
                iamClass.getClassLoader(),
                new Class[]{iamClass},
                new MInvocationHandler(o));

        //将代理对象注入


        mInstanceField.set(gDefault, proxyInstance);
    }

    public void hookHandlerLaunchActivity() throws Exception {
        Class<?> atClass = Class.forName("android.app.ActivityThread");
        Method currentActivityThreadMethod = atClass.getDeclaredMethod("currentActivityThread");
        Object atObject = currentActivityThreadMethod
                .invoke(null, null);
        Field mH = atClass.getDeclaredField("mH");
        if (!mH.isAccessible()) {
            mH.setAccessible(true);
        }
        Class<?> handlerClass = Handler.class;
        Object mHObject = mH.get(atObject);
        Field callbackField = handlerClass.getDeclaredField("mCallback");
        if (!callbackField.isAccessible()) {
            callbackField.setAccessible(true);
        }
        callbackField.set(mHObject, new HookCallback());

    }

    class HookCallback implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 100) {
                //替换成未注册真正的activity
                Log.e(TAG, "handleMessage --> ");
                try {
                    Class<?> ActivityClientRecordClass = Class.forName(
                            "android.app.ActivityThread$ActivityClientRecord");
                    Field intentField = ActivityClientRecordClass
                            .getDeclaredField("intent");
                    Object activityClientRecord = msg.obj;
                    if (!intentField.isAccessible()) {
                        intentField.setAccessible(true);
                    }
                    Intent proxyIntent = (Intent) intentField.get(activityClientRecord);
                    Intent originIntent = (Intent) proxyIntent.getParcelableExtra(ORIGIN_INTENT);
                    if (originIntent != null) {
                        intentField.set(activityClientRecord, originIntent);
                    } else {
                        Log.e(TAG, "intent null --> " + proxyIntent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            return false;
        }
    }

    class MInvocationHandler implements InvocationHandler {
        Object mObject;

        MInvocationHandler(Object object) {
            this.mObject = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Log.e(TAG, "method --> " + method.getName());
            //Log.e(TAG, "object --> " + proxy.toString());
            //替换掉intent
            if (method.getName().equals("startActivity")) {
                Intent originIntent = (Intent) args[2];
                Intent proxyIntent = new Intent(mContext, mProxyActivityClass);
                proxyIntent.putExtra(ORIGIN_INTENT, originIntent);
                args[2] = proxyIntent;
            }

            return method.invoke(mObject, args);
        }
    }


}
