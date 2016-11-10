package com.example.daksha.myapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import static com.example.daksha.myapp.BenchmarkTaskActivity.mergeSort;
/**
 * Created by Daksha on 11/7/2016.
 */

public class MergeSortTask extends AsyncTask<int[], String, String> {

    private Activity activity;
    private TextView tvMergeSort;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        tvMergeSort = (TextView) activity.findViewById(R.id.tvMergeSort);

    }

    public MergeSortTask(Activity activity){
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
        mergeSort(var[0]);
        Long end = System.currentTimeMillis();
        return "" + (end-start) + " ms";
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        tvMergeSort.setText(values[0]);
    }

    @Override
    protected void onPostExecute(String result) {

        tvMergeSort.setText(result);
    }
}
