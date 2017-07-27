package com.example.abhishek.movies.asyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.abhishek.movies._interface.ReceiveBitmapInterface;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by abhishek on 27/07/17.
 */

public class DownloadImageTask extends AsyncTask<String,Void,Bitmap> {
    private String TAG = "IMAGE_ASYNC_TASK";

    private ReceiveBitmapInterface receiveBitmapInterface;
    @Override
    protected Bitmap doInBackground(String... params) {
        Log.e(TAG,"DO IN BACKGROUND FUNCTION");
        String urlPath = params[0];


        Log.e("URL_PATH",urlPath);
        Bitmap bitmap = null;
        try {
            // Download Image from URL
            InputStream input = new java.net.URL(urlPath).openStream();
            // Decode Bitmap
            bitmap = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e(TAG,bitmap.getHeight()+" ");
        return bitmap;
    }


    public void setReceiveBitmapInterface(ReceiveBitmapInterface receiveBitmapInterface) {
        this.receiveBitmapInterface = receiveBitmapInterface;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(receiveBitmapInterface!=null){
            receiveBitmapInterface.onReceiveBitmap(bitmap);
        }
    }
}
