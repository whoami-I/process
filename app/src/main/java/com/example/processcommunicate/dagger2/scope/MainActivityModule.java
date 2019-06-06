package com.example.processcommunicate.dagger2.scope;

import javax.inject.Singleton;

import dagger.Module;
@Module(includes = {StudentModule.class})
public class MainActivityModule {
    Student getStudent(Student student) {
        return student;
    }
}
