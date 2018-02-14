package com.waracle.androidtest.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.waracle.androidtest.enums.ViewModelEventsEnum;
import com.waracle.androidtest.interfaces.ViewModelCallBackObserver;
import javax.inject.Inject;
import dagger.android.support.AndroidSupportInjection;


public abstract class BaseFragment<VM extends BaseViewModel, DB extends ViewDataBinding> extends Fragment implements ViewModelCallBackObserver, LifecycleRegistryOwner {

    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public VM viewModel;
    public DB binding;

    @Override
    public abstract void onObserve(ViewModelEventsEnum event, Object eventMessage);

    public abstract Class<VM> getViewModel();

    @LayoutRes
    public abstract int getLayoutRes();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());
        viewModel.addObserver(this);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;

        if(binding instanceof ViewDataBinding)
        {
            binding = DataBindingUtil.inflate(inflater,
                    getLayoutRes(), container, false);
           return binding.getRoot();
        }else{
            return inflater.inflate( getLayoutRes(), container, false);
        }

    }


    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

}
