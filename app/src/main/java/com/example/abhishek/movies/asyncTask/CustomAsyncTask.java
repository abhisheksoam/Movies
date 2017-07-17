package com.example.abhishek.movies.asyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.abhishek.movies._interface.CustomAsyncInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by abhishek on 02/11/16.
 */

public class CustomAsyncTask extends AsyncTask<String,Void,String> {

    /**
     * Variable for storing to make calculations
     */

    private  String TYPE = "DEFAULT";
    private String TAG = "CUSTOM_ASYNC_TASK";

    /**
     * Android Widgets
     */
    private CustomAsyncInterface customAsyncInterface;
    private Context context;


    @Override
    protected String doInBackground(String... strings) {
        Log.e(TAG,"DO IN BACKGROUND FUNCTION");
        String urlPath = strings[0];
        TYPE = strings[1];

        Log.e("URL_PATH",urlPath);
        Log.e("TYPE",TYPE);
        try {
            URL url = new URL(urlPath);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            StringBuffer output = new StringBuffer();
            while (scanner.hasNext()) {
                output.append(scanner.nextLine());
            }
            scanner.close();
            httpURLConnection.disconnect();
            return output.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e(TAG,"ON PRE EXECUTE FUNCTION");
    }

    @Override
    protected void onPostExecute(String jsonString) {
        super.onPostExecute(jsonString);
        Log.e(TAG,"ON POST EXECUTE FUNCTION");
        if(customAsyncInterface!=null){
            customAsyncInterface.onDataReceived(jsonString,TYPE);
        }
    }

    public void setCustomAsyncInterface(CustomAsyncInterface customAsyncInterface) {
        this.customAsyncInterface = customAsyncInterface;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
