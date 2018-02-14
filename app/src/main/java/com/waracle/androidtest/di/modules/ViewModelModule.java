package com.waracle.androidtest.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.waracle.androidtest.di.ViewModelFactory;
import com.waracle.androidtest.viewmodels.CakeListFragmentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(CakeListFragmentViewModel.class)
    abstract ViewModel cakeViewModel(CakeListFragmentViewModel cakeListFragmentViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);


}