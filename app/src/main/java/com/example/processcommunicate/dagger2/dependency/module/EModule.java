package com.example.processcommunicate.dagger2.dependency.module;

import com.example.processcommunicate.dagger2.dependency.clazz.B;
import com.example.processcommunicate.dagger2.dependency.clazz.D;
import com.example.processcommunicate.dagger2.dependency.clazz.E;
import com.example.processcommunicate.dagger2.dependency.clazz.F;

import dagger.Module;
import dagger.Provides;

@Module
public class EModule {

    @Provides
    public E getE() {
        return new E();
    }
}
