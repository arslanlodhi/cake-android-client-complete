package com.waracle.androidtest.di;


import android.app.Application;

import com.waracle.androidtest.AppApplication;
import com.waracle.androidtest.di.modules.ActivityBuilderModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;


@Singleton
@Component(modules={AppModule.class,AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface NetComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        NetComponent build();
    }

    void inject(AppApplication app);

}