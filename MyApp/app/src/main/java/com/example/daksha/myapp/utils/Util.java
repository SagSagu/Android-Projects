package com.example.daksha.myapp.utils;

import android.content.SharedPreferences;

import com.example.daksha.myapp.R;

import java.util.Random;

import static com.example.daksha.myapp.UserRegistrationActivity.UP_KEY;
import static com.example.daksha.myapp.UserRegistrationActivity.selectedId;
import static com.example.daksha.myapp.UserRegistrationActivity.sharedPreferences;

/**
 * Created by Daksha on 11/5/2016.
 */

public class Util {

    public static String generateRandomPassword(){

        String password;
        int passwordSize = 7;
        char[] chars = "0123456789".toCharArray(); //abcdefghijklmnopqrstuvwxyz
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < passwordSize; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        password = sb.toString();
        return password;
    }

    public static void savePreferences(String key, String value){
        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);

        //int selectedId = rgGender.getCheckedRadioButtonId();
        if(selectedId == R.id.rbMale)
            editor.putBoolean("MALE", true);
        else
            editor.putBoolean("MALE",false);
        if(selectedId == R.id.rbFeMale)
            editor.putBoolean("FEMALE", true);
        else
            editor.putBoolean("FEMALE",false);
        if(selectedId == R.id.rbOthers)
            editor.putBoolean("OTHERS", true);
        else
            editor.putBoolean("OTHERS",false);
        editor.commit();
    }

}
