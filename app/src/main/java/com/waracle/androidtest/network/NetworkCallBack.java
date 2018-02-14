package com.waracle.androidtest.network;

import android.graphics.Bitmap;

import com.waracle.androidtest.models.CakeModel;

import java.util.ArrayList;

/**
 * Created by arslanlodhi on 2/13/18.
 */

public interface NetworkCallBack {

    public void onResponseReceived(String url,byte[] data,String charset);
    public  void onFailure(String url, String message);
    public  void onStart(String url);
    public  void onStop(String url);


}
