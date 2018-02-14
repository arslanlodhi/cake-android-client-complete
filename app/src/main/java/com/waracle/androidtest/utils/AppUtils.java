package com.waracle.androidtest.utils;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.waracle.androidtest.models.CakeModel;

import org.json.JSONArray;

import java.util.ArrayList;

public class AppUtils {

    public static String BASE_URL="https://gist.githubusercontent.com";





    public static ArrayList<CakeModel> parseCakeListJsonArray(String jsonText) throws Exception {
        JSONArray array = new JSONArray(jsonText);
        ArrayList<CakeModel> cakes=new ArrayList<>();
        for(int x=0;x<array.length();x++)
        {
            CakeModel model=new CakeModel(array.getJSONObject(x).getString("title"),
                    array.getJSONObject(x).getString("desc"),
                    array.getJSONObject(x).getString("image"));

            cakes.add(model);

        }
        return cakes;
    }


}
