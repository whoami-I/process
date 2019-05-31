package com.example.processcommunicate.mvp2.mvp.base;

import android.app.Activity;
import android.os.Bundle;

import com.example.processcommunicate.mvp2.mvp.inject.InjectPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends Activity implements BaseView {

    private List<BasePresenter> presenterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectPresenter injectPresenter = field
                    .getAnnotation(InjectPresenter.class);
            if (injectPresenter != null) {
                Class<? extends BasePresenter> presenterClazz =
                        (Class<? extends BasePresenter>) field.getType();
                //在这里加一下判断，如果field.getType不满足条件，那么就
                //抛出异常
                try {
                    //生成实例对象
                    BasePresenter p = presenterClazz.newInstance();
                    //注入
                    field.setAccessible(true);
                    field.set(this, p);
                    //presenter attach住当前Activity
                    p.attach(this);
                    presenterList.add(p);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        for (BasePresenter presenter : presenterList) {
            presenter.detach();
        }


    }
}
