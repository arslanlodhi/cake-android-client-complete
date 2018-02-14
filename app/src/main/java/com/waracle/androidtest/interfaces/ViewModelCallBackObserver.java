package com.waracle.androidtest.interfaces;


import com.waracle.androidtest.enums.ViewModelEventsEnum;

public interface ViewModelCallBackObserver<T> {

    void onObserve(ViewModelEventsEnum event, T eventMessage);

}
