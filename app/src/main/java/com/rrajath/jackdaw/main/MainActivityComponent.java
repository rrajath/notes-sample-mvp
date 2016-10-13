package com.rrajath.jackdaw.main;

import com.rrajath.jackdaw.util.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
        modules = {
                MainActivityModule.class
        }

)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
