package com.example.processcommunicate.dagger2.studentmodule;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class StudentModule {

    @Provides
    public int getAge() {
        return 10;
    }

    @Provides
    @Named("int")
    Student getStudent(int a) {
        return new Student(a);
    }
    @Named("null")
    @Provides
    Student getStudent1() {
        return new Student();
    }
}
