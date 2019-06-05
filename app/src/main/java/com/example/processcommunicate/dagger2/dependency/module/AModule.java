package com.example.processcommunicate.dagger2.dependency.module;

import com.example.processcommunicate.dagger2.dependency.clazz.A;
import com.example.processcommunicate.dagger2.dependency.clazz.B;
import com.example.processcommunicate.dagger2.dependency.clazz.C;
import com.example.processcommunicate.dagger2.dependency.clazz.F;

import dagger.Module;
import dagger.Provides;

@Module(includes = {BModule.class,CModule.class,FModule.class})
public class AModule {
    @Provides
    public A getA(B b, C c, F f){
        return new A(b,c,f);
    }
}
