package com.example.abhishek.movies.asyncTask;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.abhishek.movies._interface.RestCallResponseInterface;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


/**
 * Created by abhishek on 22/08/17.
 */

public class CustomRequest extends Request<JSONObject> {

    private Response.Listener<JSONObject> listener;
    private String type;


    private RestCallResponseInterface restCallResponseInterface;
    private Context context;

    public CustomRequest(String url,
                         Response.Listener<JSONObject> reponseListener, Response.ErrorListener errorListener) {
        super(Request.Method.GET, url, errorListener);
        this.listener = reponseListener;
    }



    public void setRestCallResponseInterface(RestCallResponseInterface restCallResponseInterface) {
        this.restCallResponseInterface = restCallResponseInterface;
    }


    public CustomRequest(int method, String url,
                         Response.Listener<JSONObject> reponseListener, Response.ErrorListener errorListener, String type, Context context) {
        super(method, url, errorListener);
        this.listener = reponseListener;
        this.type = type;
        this.context = context;

    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        // TODO Auto-generated method stub
//        listener.onResponse(response);
        if(restCallResponseInterface!=null){
            restCallResponseInterface.onResponse(type,response);
        }
    }
}
