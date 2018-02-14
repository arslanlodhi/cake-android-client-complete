package com.waracle.androidtest.repositories;

import com.waracle.androidtest.network.NetworkCallBack;
import com.waracle.androidtest.network.NetworkManager;
import com.waracle.androidtest.enums.NetworkCallType;
import com.waracle.androidtest.utils.AppUtils;

import javax.inject.Inject;


public class CakeRepository{

    public static String URL_PATH="/hart88/198f29ec5114a3ec3460/" +
            "raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";

    @Inject
    NetworkManager networkManager;


    @Inject
    public CakeRepository(){}


    public void fetchCakeListFromServer(NetworkCallBack networkCallBack){
        cancelRequest();
        networkManager.enqueue(AppUtils.BASE_URL+URL_PATH,NetworkCallType.FETCH_CAKE_LIST,networkCallBack);
    }




    public void cancelRequest(){
        networkManager.dequeueRequest(AppUtils.BASE_URL+URL_PATH);
    }

}
