package com.example.daksha.myapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;
import android.graphics.Bitmap;

/**
 * Created by Daksha on 11/7/2016.
 */

public class MyTask extends AsyncTask<Integer, String, String> {

    private Activity activity;
    private TextView tvStatus;



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        tvStatus = (TextView) activity.findViewById(R.id.tvStatus);

    }

    public MyTask(Activity activity){
        this.activity = activity;
    }

    @Override
    protected String doInBackground(Integer... array) {

        int var=10;

        for(Integer num:array){

            try{
                Thread.sleep(num);
                publishProgress(var + " % completed ");
                var += 10;
            } catch (InterruptedException e){

            }
        }

        return "Task completed";
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        tvStatus.setText(values[0]);
    }

    @Override
    protected void onPostExecute(String result) {

        tvStatus.setText(result);
    }
}
