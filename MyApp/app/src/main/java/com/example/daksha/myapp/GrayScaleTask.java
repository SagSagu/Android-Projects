package com.example.daksha.myapp;

/**
 * Created by Daksha on 11/7/2016.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.daksha.myapp.ImageEditTaskActivity.toGrayScale;

/**
 * Created by vinaynoah on 03/11/16.
 */
public class GrayScaleTask extends AsyncTask<Bitmap, String, Bitmap> {

    private Activity activity;
    private ImageView ivGoku;
    private TextView tvStatus;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ivGoku = (ImageView) activity.findViewById(R.id.ivGoku);
        tvStatus = (TextView) activity.findViewById(R.id.tvStatus);
    }

    public GrayScaleTask(Activity activity){
        this.activity = activity;
    }

    @Override
    protected Bitmap doInBackground(Bitmap... image) {
        for(int i=0; i<=100; i++){
            try{
                Thread.sleep(i);
                publishProgress(i + " % completed ");
                i++;
            } catch (InterruptedException e){
            }
        }
        Bitmap grayImage = toGrayScale(image[0]);
        return grayImage;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        tvStatus.setText(values[0]);
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        ivGoku.setImageBitmap(result);
    }
}

