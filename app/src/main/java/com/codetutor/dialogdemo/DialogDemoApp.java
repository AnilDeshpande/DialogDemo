package com.codetutor.dialogdemo;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by anildeshpande on 4/11/18.
 */

public class DialogDemoApp extends Application {

    static List<String> hobbies;

    @Override
    public void onCreate() {
        super.onCreate();
        String [] hobbyStrings = getApplicationContext().getResources().getStringArray(R.array.hobbies);
        hobbies = new ArrayList<>(Arrays.asList(hobbyStrings));
    }

    public static List<String> getHobbies(){
        return hobbies;
    }

    public static void addHobby(String hobby){
        if(!hobbies.contains(hobby)){
            hobbies.add(0,hobby);
        }
    }


}
