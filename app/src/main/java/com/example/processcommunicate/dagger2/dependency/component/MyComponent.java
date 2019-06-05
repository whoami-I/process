package com.example.processcommunicate.dagger2.dependency.component;

import com.example.processcommunicate.dagger2.dependency.module.AModule;
import com.example.processcommunicate.dagger2.dependency.module.BModule;
import com.example.processcommunicate.dagger2.dependency.module.CModule;

import dagger.Component;
import dagger.Module;

@Component(modules = {AModule.class, BModule.class, CModule.class})
public interface MyComponent {
    void inject(MainActivity activity);
}
