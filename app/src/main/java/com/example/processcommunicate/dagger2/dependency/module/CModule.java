package com.example.processcommunicate.dagger2.dependency.module;

import com.example.processcommunicate.dagger2.dependency.clazz.B;
import com.example.processcommunicate.dagger2.dependency.clazz.C;
import com.example.processcommunicate.dagger2.dependency.clazz.D;
import com.example.processcommunicate.dagger2.dependency.clazz.E;
import com.example.processcommunicate.dagger2.dependency.clazz.F;
import com.example.processcommunicate.log.Log;

import dagger.Module;
import dagger.Provides;

@Module(includes = {BModule.class,EModule.class})
public class CModule {

    @Provides
    public C getC(B b, E e) {
        return new C(b, e);
    }

}
