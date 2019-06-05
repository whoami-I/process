package com.example.processcommunicate.dagger2.dependency.module;

import com.example.processcommunicate.dagger2.dependency.clazz.B;
import com.example.processcommunicate.dagger2.dependency.clazz.D;
import com.example.processcommunicate.dagger2.dependency.clazz.E;
import com.example.processcommunicate.dagger2.dependency.clazz.F;
import com.example.processcommunicate.log.Log;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DModule.class,EModule.class,FModule.class})
public class BModule {

    @Provides
    public B getB(D d, E e, F f) {
        return new B(d, e, f);
    }

}
