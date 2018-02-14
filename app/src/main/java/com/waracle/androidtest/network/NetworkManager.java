package com.waracle.androidtest.network;
import com.waracle.androidtest.enums.NetworkCallType;
import java.util.ArrayList;
import java.util.HashMap;


public class NetworkManager implements NetworkCallBack{
    private static final String TAG = NetworkManager.class.getSimpleName();




    HashMap<String,NetworkCallProcess> queue=new HashMap<>();

    public synchronized  void enqueue(String url,NetworkCallType callType, NetworkCallBack callBack){

        if(!queue.containsKey(url))
        {
            ArrayList<NetworkCallBack> callBacks=new ArrayList<>();
            callBacks.add(this);
            callBacks.add(callBack);

            NetworkCallProcess process=new NetworkCallProcess(callType,callBacks);
            queue.put(url,process);
            process.execute(url);


        }else{

            queue.get(url).addNetworkCallBack(callBack);
        }

    }

    public void dequeueRequest(String url){
        if(queue.containsKey(url))
        {
            queue.get(url).clear();
            queue.remove(url);

        }
    }


    @Override
    public void onResponseReceived(String url, byte[] data, String charset) {

    }

    @Override
    public void onFailure(String url, String message) {

    }

    @Override
    public void onStart(String url) {

    }

    @Override
    public void onStop(String url) {
        dequeueRequest(url);
    }
}
