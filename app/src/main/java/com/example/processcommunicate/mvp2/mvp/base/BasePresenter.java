package com.example.processcommunicate.mvp2.mvp.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class BasePresenter {
    private BaseView mView;
    private BaseModel mModel;
    private BaseView mViewProxy;

    public BasePresenter() {
        mModel = createModel();
        //view和代理view放到attach中才会初始化
    }

    public void attach(BaseView view) {
        mView = view;

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

    /**
     * 返回的是代理对象,之所以返回代理对象的原因是，如果获取数据成功的话
     * 这时，如果view为null了，那么就不执行方法，直接返回。使用动态代理
     * 省去了每次都要判断view是否为null的步骤
     *
     * @return
     */
    public BaseView getView() {

        return mViewProxy;
    }

    public BaseModel getModel() {
        return mModel;
    }

    /**
     * 因为presenter使用的是在BaseActivity中动态创建，因此
     * 法传递model进来，因此使用抽象方法的形式，抛给子类创建
     * 然后再presenter构造方法中调用此方法
     *
     * @return
     */
    public abstract BaseModel createModel();
}
