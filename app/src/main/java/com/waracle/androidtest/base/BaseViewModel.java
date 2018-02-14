package com.waracle.androidtest.base;

import android.arch.lifecycle.ViewModel;

import com.waracle.androidtest.enums.ViewModelEventsEnum;
import com.waracle.androidtest.interfaces.ViewModelCallBackObserver;


public class BaseViewModel extends ViewModel {

    public ViewModelCallBackObserver callBackObserver;

    public void addObserver(ViewModelCallBackObserver callBackObserver) {
        this.callBackObserver = callBackObserver;
    }

    public void removeObserver() {
        callBackObserver = null;
    }

    public void notifyObserver(ViewModelEventsEnum eventType, String message){
        if(callBackObserver != null)
        callBackObserver.onObserve(eventType, message);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        removeObserver();
    }
}
