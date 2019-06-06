package com.example.processcommunicate.dagger2.scope;

import dagger.Module;

@Module(includes = {StudentModule.class})
public class SecondActivityModule {
    Student getStudent(Student student) {
        return student;
    }
}
