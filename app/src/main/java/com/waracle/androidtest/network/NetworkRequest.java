package com.waracle.androidtest.network;

import android.graphics.Bitmap;

import com.waracle.androidtest.enums.NetworkCallType;
import com.waracle.androidtest.models.CakeModel;

import java.util.ArrayList;

/**
 * Created by arslanlodhi on 2/14/18.
 */

public class NetworkRequest {

    ArrayList<NetworkCallBack> networkCallBacks = new ArrayList<>();
    NetworkCallType networkCallType;
    String url;
    String charset;



    public NetworkRequest(ArrayList<NetworkCallBack> networkCallBacks, NetworkCallType networkCallType) {
        this.networkCallBacks = networkCallBacks;
        this.networkCallType = networkCallType;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public NetworkCallType getNetworkCallType() {
        return networkCallType;
    }

    public String getUrl() {
        return url;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void addNetworkCallBack(NetworkCallBack callBack) {
        if (networkCallBacks != null)
            networkCallBacks.add(callBack);
    }

    public void callBackOnFailure(String message){
        if (networkCallBacks != null)
            for(int x=0;x<networkCallBacks.size();x++) {
                if(networkCallBacks.get(x)!=null)
                    networkCallBacks.get(x).onFailure(url,message);
            }

        callBackOnStop();
    }

    public void callBackOnStop(){
        if (networkCallBacks != null)
            for(int x=0;x<networkCallBacks.size();x++) {
                if(networkCallBacks.get(x)!=null)
                    networkCallBacks.get(x).onStop(url);
            }

    }

    public void callBackOnStart(){
        if (networkCallBacks != null)
            for(int x=0;x<networkCallBacks.size();x++) {
                if(networkCallBacks.get(x)!=null)
                    networkCallBacks.get(x).onStart(url);
            }

    }

    public void callBackOnResponseReceived(byte[] response,String charset){
        if (networkCallBacks != null)
            for(int x=0;x<networkCallBacks.size();x++) {
                if(networkCallBacks.get(x)!=null)
                    networkCallBacks.get(x).onResponseReceived(url,response,charset);
            }

    }

    public void clear(){
        networkCallBacks.clear();
    }

}
