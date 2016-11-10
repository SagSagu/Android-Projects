package com.example.daksha.myapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import static com.example.daksha.myapp.BenchmarkTaskActivity.bubbleSort;

/**
 * Created by Daksha on 11/7/2016.
 */

public class BubbleSortTask extends AsyncTask<int[], String, String> {

    private Activity activity;
    private TextView tvBubbleSort;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        tvBubbleSort = (TextView) activity.findViewById(R.id.tvBubbleSort);

    }

    public BubbleSortTask(Activity activity){
        this.activity = activity;
    }

    @Override
    protected String doInBackground(int[]... var) {
        for(int i=0; i<=100; i++){
            try{
                Thread.sleep(i);
                publishProgress(i + " % completed ");
                i++;
            } catch (InterruptedException e){
            }
        }
        Long start = System.currentTimeMillis();
        bubbleSort(var[0]);
        Long end = System.currentTimeMillis();
        return "" + (end-start) + " ms";
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        tvBubbleSort.setText(values[0]);
    }

    @Override
    protected void onPostExecute(String result) {

        tvBubbleSort.setText(result);
    }
}
