package com.example.daksha.myapp;

import android.app.Activity;

/**
 * Created by Daksha on 10/25/2016.
 */
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;


public class MyProfile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_my_profile);
        checkFirstLogin();
        prepareForm();
    }
    private void prepareForm() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        // If value for key not exist then return second param value - In this case "..."
    }
    private void checkFirstLogin() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        // If value for key not exist then return second param value - In this case true
        if (preferences.getBoolean("firstLogin", true)) {
            initProfile();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstLogin", false);
            editor.commit();
        }
    }
    private void initProfile() {
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username","Demo TryTest");
        editor.putString("userphone","01234567890");
        editor.putString("useremail","demotrytest@gmail.com");
        editor.commit();
    }
    public void setProfileImage(View view){
        ImageView ivphoto = (ImageView)findViewById(R.id.ivGoku);
        //code image to string
        ivphoto.buildDrawingCache();
        Bitmap bitmap = ivphoto.getDrawingCache();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] image=stream.toByteArray();
        //System.out.println("byte array:"+image);
        //final String img_str = "data:image/png;base64,"+ Base64.encodeToString(image, 0);
        //System.out.println("string:"+img_str);
        String img_str = Base64.encodeToString(image, 0);
        //decode string to image
        String base=img_str;
        byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);

        ImageView ivsavedphoto = (ImageView)this.findViewById(R.id.ivGoku);
        ivsavedphoto.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );
        //save in sharedpreferences
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userphoto",img_str);
        editor.commit();
    }
}