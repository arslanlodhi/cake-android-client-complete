package com.waracle.androidtest.di;



import com.waracle.androidtest.di.modules.ViewModelModule;
import com.waracle.androidtest.network.ImageLoader;
import com.waracle.androidtest.network.NetworkManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(includes = ViewModelModule.class)
public class AppModule {



    @Provides
    @Singleton
    public NetworkManager getNetworkManager(){
        NetworkManager networkManager=new NetworkManager();
        return networkManager;
    }


    @Provides
    @Singleton
    public ImageLoader getImageLoader(){
        ImageLoader imageLoad=new ImageLoader(getNetworkManager());
        return imageLoad;
    }


}
