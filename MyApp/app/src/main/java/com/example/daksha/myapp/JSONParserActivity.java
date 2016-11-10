package com.example.daksha.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParserActivity extends AppCompatActivity {

    private TextView tvMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsonparser_layout);

        tvMovieList = (TextView) findViewById(R.id.tvMovieList);

        String strJson="{ \"Movie\" :[{\"name\":\"Iron Man\",\"actor\":\"Robert Dowrey\",\"genre\":\"Action\"},{\"name\":\"Lights Out\",\"actor\":\"Teresa Palmer\",\"genre\":\"Horror\"}] }";

        String data = "";
        try {
            // Create the root JSONObject from the JSON string.
            JSONObject jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("Movie");

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String name = jsonObject.optString("name").toString();
                String actor = jsonObject.optString("actor").toString();
                String genre = jsonObject.optString("genre").toString();

                data += "\n\n\nMovie Details\n" + "\n Name : "+ name +" \n Lead Actor : "+ actor +" \n Genre : "+ genre +" \n ";
            }
            tvMovieList.setText(data);
        } catch (JSONException e) {e.printStackTrace();}
    }
}
