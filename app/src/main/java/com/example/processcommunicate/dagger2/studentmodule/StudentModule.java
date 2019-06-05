package com.example.processcommunicate.dagger2.studentmodule;

import dagger.Module;
import dagger.Provides;

@Module
public class StudentModule {

    @Provides
    public int getAge() {
        return 10;
    }

    @Provides
    Student getStudent(int a) {
        return new Student(a);
    }
}
