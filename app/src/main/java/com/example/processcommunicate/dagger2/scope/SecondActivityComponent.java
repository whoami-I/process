package com.example.processcommunicate.dagger2.scope;


import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

//@Component(modules = StudentModule.class)
//@Singleton
@Subcomponent
public interface SecondActivityComponent {
    //mmp,这个参数里面必须是要绑定的类的名称，如果使用父类名称，就不行
    void inject(SecondActivity activity);
}
