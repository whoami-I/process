package com.example.processcommunicate.dagger_mvp;

import dagger.Module;

@Module
public class MainActivityModule {

    Student getStudent() {
        return new Student();
    }
}
