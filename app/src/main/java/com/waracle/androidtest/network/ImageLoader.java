package com.waracle.androidtest.network;

import android.graphics.Bitmap;
import android.media.Image;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.waracle.androidtest.enums.NetworkCallType;
import com.waracle.androidtest.models.CakeModel;
import com.waracle.androidtest.utils.AppUtils;
import com.waracle.androidtest.utils.ImageUtils;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Riad on 20/05/2015.
 */
public class ImageLoader {

    private static final String TAG = ImageLoader.class.getSimpleName();
    private LruCache<String, Bitmap> mMemoryCache;

    NetworkManager networkManager;

    public ImageLoader( NetworkManager networkManager) { /**/

        this.networkManager=networkManager;

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if(key!=null && bitmap !=null) {
            if (getBitmapFromMemCache(key) == null) {
                mMemoryCache.put(key, bitmap);
            }
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        if(key!=null)
        return mMemoryCache.get(key);
        return null;
    }




    /**
     * Simple function for loading a bitmap image from the web
     *
     * @param url       image url
     * @param imageView view to set image too.
     */

    public void load(final String url, final ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            throw new InvalidParameterException("URL is empty!");
        }
        // Can you think of a way to improve loading of bitmaps
        // that have already been loaded previously??
        //?? i have implemented LRU Cache to serve them from memory if Already Loaded
        Bitmap cachedBitmap=getBitmapFromMemCache(url);
        if (cachedBitmap == null) {
            ImageUtils.clearImageViewBackground(imageView);
            networkManager.enqueue(url, NetworkCallType.FETCH_IMAGE, new NetworkCallBack() {


                @Override
                public void onResponseReceived(String url, byte[] data, String charset) {
                    Bitmap bitmap= ImageUtils.convertToBitmap(data);
                    addBitmapToMemoryCache(url, bitmap);
                    ImageUtils.setImageView(imageView, bitmap);
                }

                @Override
                public void onFailure(String url, String message) {

                }

                @Override
                public void onStart(String url) {
                }

                @Override
                public void onStop(String url) {

                }
            });

        }
        else
        {
            ImageUtils.setImageView(imageView, cachedBitmap);
        }
    }
}
