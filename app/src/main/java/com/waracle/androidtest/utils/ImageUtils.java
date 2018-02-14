package com.waracle.androidtest.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by arslanlodhi on 2/14/18.
 */

public class ImageUtils {

    public  static Bitmap convertToBitmap(byte[] byteArray) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = 100;
        options.outHeight = 100;
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        return bitmap;
    }
    public static void clearImageViewBackground(ImageView imageView){
        if(imageView!=null)
            imageView.setVisibility(View.INVISIBLE);

    }
    public  static void setImageView(ImageView imageView, Bitmap bitmap) {
        if(imageView!=null) {
            imageView.setVisibility(View.VISIBLE);
            if (bitmap != null)
                imageView.setImageBitmap(bitmap);
            else
                clearImageViewBackground(imageView);
        }
    }
}
