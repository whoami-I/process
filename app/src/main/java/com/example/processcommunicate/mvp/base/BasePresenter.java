package com.example.processcommunicate.mvp.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BasePresenter {
    private BaseView mView;
    private BaseModel mModel;
    private BaseView mViewProxy;

    public void attach(BaseView view, BaseModel model) {
        mView = view;
        mModel = model;

        mViewProxy = (BaseView) Proxy.newProxyInstance(BaseView.class.getClassLoader(),
                new Class[]{BaseView.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (mView == null)
                            return null;
                        return method.invoke(mView, args);
                    }
                });
    }

    public void detach() {
        mView = null;
        mViewProxy = null;
    }

    //返回的是代理对象
    public BaseView getView() {
        return mViewProxy;
    }

    public BaseModel getModel() {
        return mModel;
    }
}
