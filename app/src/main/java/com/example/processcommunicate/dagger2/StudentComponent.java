package com.example.processcommunicate.dagger2;

import android.app.Activity;

import dagger.Component;

@Component()
public interface StudentComponent {
    //mmp,这个参数里面必须是要绑定的类的名称，如果使用父类名称，就不行
    void inject(MainActivity activity);
}
