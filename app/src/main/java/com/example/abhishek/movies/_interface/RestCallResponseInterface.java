package com.example.abhishek.movies._interface;

import com.android.volley.Response;

import org.json.JSONObject;

/**
 * Created by abhishek on 22/08/17.
 */

public interface RestCallResponseInterface {
   void onResponse(String type, JSONObject response);
}
