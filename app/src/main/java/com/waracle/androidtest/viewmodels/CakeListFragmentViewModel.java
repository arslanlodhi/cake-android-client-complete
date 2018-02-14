package com.waracle.androidtest.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.waracle.androidtest.network.ImageLoader;
import com.waracle.androidtest.network.NetworkCallBack;
import com.waracle.androidtest.base.BaseViewModel;
import com.waracle.androidtest.enums.ViewModelEventsEnum;
import com.waracle.androidtest.models.CakeModel;
import com.waracle.androidtest.repositories.CakeRepository;
import com.waracle.androidtest.utils.AppUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.inject.Inject;


public class CakeListFragmentViewModel extends BaseViewModel {

    private MutableLiveData<ArrayList<CakeModel>> cakeList = new MutableLiveData<>();

    @Inject
    CakeRepository repository;

    @Inject
    ImageLoader mImageLoader;


    @Inject
    CakeListFragmentViewModel() {

    }

    public MutableLiveData<ArrayList<CakeModel>> processServerRequest() {
        repository.fetchCakeListFromServer(new NetworkCallBack() {
            @Override
            public void onResponseReceived(String url, byte[] data, String charset) {

                String jsonText = null;
                ArrayList<CakeModel> cakes=null;
                try {
                    jsonText = new String(data, charset);
                    cakes= AppUtils.parseCakeListJsonArray(jsonText);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    if (callBackObserver != null)
                        callBackObserver.onObserve(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, "Server Issue");
                } catch (Exception e) {
                    e.printStackTrace();
                    if (callBackObserver != null)
                        callBackObserver.onObserve(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, "Response Parsing issue");
                }
                cakeList.postValue(cakes);
            }

            @Override
            public void onFailure(String url, String message) {
                if (callBackObserver != null)
                    callBackObserver.onObserve(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, message);
            }

            @Override
            public void onStart(String url) {
                if (callBackObserver != null)
                    callBackObserver.onObserve(ViewModelEventsEnum.ON_API_CALL_START, null);
            }

            @Override
            public void onStop(String url) {
                if (callBackObserver != null)
                    callBackObserver.onObserve(ViewModelEventsEnum.ON_API_CALL_STOP, null);
            }
        });


        return cakeList;

    }

    public void loadImage(String image, ImageView image1) {
        mImageLoader.load(image,image1);

    }
}
