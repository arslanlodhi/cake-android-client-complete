package com.waracle.androidtest.di.modules;

import com.waracle.androidtest.views.CakeListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract CakeListFragment contributeCakeListFragment();


}