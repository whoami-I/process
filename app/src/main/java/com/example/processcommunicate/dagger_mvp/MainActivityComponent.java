package com.example.processcommunicate.dagger_mvp;

import dagger.Component;

@Component(modules = {MainActivityModule.class}, dependencies = {MyApplicationComponent.class})
public interface MainActivityComponent {

    void inject(MainActivity activity);
}
