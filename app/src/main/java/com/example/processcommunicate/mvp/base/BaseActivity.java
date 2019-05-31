package com.example.processcommunicate.mvp.base;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity<P, M,V> extends Activity implements BaseView<V>{

    private BasePresenter presenter;
    private BaseModel model;
    public P getPresenter() {
        return (P) presenter;
    }



    public M getModel() {
        return (M) model;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        model = createModel();
        presenter.attach(this, model);



    }

    protected abstract BasePresenter createPresenter();

    protected abstract BaseModel createModel();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
