package com.berlin.testpad.user;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by Admin on 2018/1/29 0029.
 */

public class UserManager {

    public static void saveUser(Context context, User user){
        Gson gson  =  new Gson();
        String json = gson.toJson(user);
        SharedPreferences sharedPreferences = context.getSharedPreferences("renzhengtong", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userinfo",json);
        editor.commit();
    }

    public static User getUser(Context context){
        Gson gson  =  new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences("renzhengtong", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("userinfo","");
        if (json.equals("")){
            return null;
        }
        try {
            User info = gson.fromJson(json,User.class);
            return info;
        }catch (Exception e){
            return null;
        }

    }

    public static void clearUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("renzhengtong", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userinfo","");
        editor.commit();
    }

}
