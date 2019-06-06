package com.example.processcommunicate.dagger2.scope;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StudentModule {

    @Provides
    public int getAge() {
        return 10;
    }

    @Provides
    @Singleton
    @Named("null")
    Student getStudent() {
        return new Student();
    }

    @Provides
    @Singleton
    @Named("int")
    Student getStudent1(int a) {
        return new Student();
    }

    @Provides
    Data getData() {
        return new Data();
    }


}
