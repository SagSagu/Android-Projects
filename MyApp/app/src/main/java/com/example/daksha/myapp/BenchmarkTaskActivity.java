package com.example.daksha.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Daksha on 10/14/2016.
 */

public class BenchmarkTaskActivity extends AppCompatActivity {

    private EditText etArraySize;
    private RadioGroup rGroup;
    private TextView tvGeneratedArray;
    private RadioButton rBtnBest, rBtnAvg, rBtnWorst;
    private Button btnGenerateArray, btnBenchmarkAll;
    private int arraySize=0;
    private int[] array = new int[arraySize];
    private TextView tvBubbleSort, tvMergeSort, tvSelectionSort;
    private int[] cloneArray = new int[arraySize];
    private LinearLayout linearLayout;
    private Button btnBubbleSort,btnSelectionSort,btnMergeSort;

    public static void bubbleSort(int[] array){
        int temp;
        for(int i=0;i<array.length-1;i++){
            for(int j=1;j<array.length-i;j++){
                if(array[j]<array[j-1]){
                    //Swapping the both number.
                    temp=array[j-1];
                    array[j-1]=array[j];
                    array[j]=temp;
                }
            }
        }
        return;
    }

    public static void selectionSort(int[] array){
        int temp,min;
        for(int i=0;i<array.length-1;i++){
            min=i;
            for(int j=i+1;j<array.length;j++){
                if(array[j]<array[min])
                    min=j;
            }
            //Swapping min value at right position.
            temp=array[min];
            array[min]=array[i];
            array[i]=temp;
        }
        return;
    }

    public static int[] merge(int[] left,int[] right, int[] array){
        int nl=left.length;
        int nr=right.length;
        int i=0,j=0,k=0;
        while(i<nl && j<nr){
            if(left[i]<=right[j]){
                array[k]=left[i];
                i++;
            }
            else{
                array[k]=right[j];
                j++;
            }
            k++;
        }
        while(i<nl){
            array[k]=left[i];
            i++;
            k++;
        }
        while(j<nr){
            array[k]=right[j];
            j++;
            k++;
        }
        return array;
    }

    public static void mergeSort(int[] array){
        int n=array.length;
        if(n<2)
            return;
        else{
            int mid=n/2;
            int[] left=new int[mid];
            int[] right=new int[n-mid];
            for(int i=0;i<mid;i++)
                left[i]=array[i];
            for(int j=mid;j<n;j++)
                right[j-mid]=array[j];

            mergeSort(left);
            mergeSort(right);
            merge(left,right,array);
        }
        return;
    }

    public void sort(View view){
        long start, end;
        switch (view.getId()){
            case R.id.btnBubbleSort:
                btnSelectionSort.setEnabled(false);
                btnMergeSort.setEnabled(false);
                btnBenchmarkAll.setEnabled(false);
                BubbleSortTask bubbleSortTask = new BubbleSortTask(this);
                bubbleSortTask.execute(cloneArray);
                /*start = System.currentTimeMillis();
                bubbleSort(cloneArray);
                end = System.currentTimeMillis();
                tvBubbleSort.setText("" + (end-start) + " ms");*/
                break;
            case R.id.btnSelectionSort:
                btnBubbleSort.setEnabled(false);
                btnMergeSort.setEnabled(false);
                btnBenchmarkAll.setEnabled(false);
                SelectionSortTask selectionSortTask = new SelectionSortTask(this);
                selectionSortTask.execute(cloneArray);
                /*start = System.currentTimeMillis();
                selectionSort(cloneArray);
                end = System.currentTimeMillis();
                tvSelectionSort.setText("" + (end-start) + " ms");*/
                break;
            case R.id.btnMergeSort:
                btnBubbleSort.setEnabled(false);
                btnSelectionSort.setEnabled(false);
                btnBenchmarkAll.setEnabled(false);
                MergeSortTask mergeSortTask = new MergeSortTask(this);
                mergeSortTask.execute(cloneArray);
                /*start = System.currentTimeMillis();
                mergeSort(cloneArray);
                end = System.currentTimeMillis();
                tvMergeSort.setText("" + (end-start) + " ms");*/
                break;
            case R.id.btnBenchmarkAll:
                btnBubbleSort.setEnabled(false);
                btnSelectionSort.setEnabled(false);
                btnMergeSort.setEnabled(false);
                BubbleSortTask bbubbleSortTask = new BubbleSortTask(this);
                bbubbleSortTask.execute(cloneArray);
                MergeSortTask bmergeSortTask = new MergeSortTask(this);
                bmergeSortTask.execute(cloneArray);
                SelectionSortTask bselectionSortTask = new SelectionSortTask(this);
                bselectionSortTask.execute(cloneArray);
                /*start = System.currentTimeMillis();
                bubbleSort(cloneArray);
                end = System.currentTimeMillis();
                tvBubbleSort.setText("" + (end-start) + " ms");
                start = System.currentTimeMillis();
                selectionSort(cloneArray);
                end = System.currentTimeMillis();
                tvSelectionSort.setText("" + (end-start) + " ms");
                start = System.currentTimeMillis();
                mergeSort(cloneArray);
                end = System.currentTimeMillis();
                tvMergeSort.setText("" + (end-start) + " ms");*/
                break;
        }
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.algorithm_benchmarking_layout);

        etArraySize = (EditText)findViewById(R.id.etArraySize);

        rGroup = (RadioGroup)findViewById(R.id.rGroup);

        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        linearLayout.setVisibility(View.GONE);

        // Just to Toast Radio Button Messages
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rBtnBest) {
                    Toast.makeText(getBaseContext(), "You selected Best Case", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.rBtnWorst) {
                    Toast.makeText(getBaseContext(), "You selected Worst Case", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "You selected Average Case", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rBtnBest = (RadioButton)findViewById(R.id.rBtnBest);
        rBtnAvg = (RadioButton)findViewById(R.id.rBtnAvg);
        rBtnWorst = (RadioButton)findViewById(R.id.rBtnWorst);
        tvGeneratedArray = (TextView)findViewById(R.id.tvGeneratedArray);

        btnGenerateArray = (Button)findViewById(R.id.btnGenerateArray);
        btnBenchmarkAll = (Button)findViewById(R.id.btnBenchmarkAll);

        btnBubbleSort = (Button)findViewById(R.id.btnBubbleSort);
        btnSelectionSort = (Button)findViewById(R.id.btnSelectionSort);
        btnMergeSort = (Button)findViewById(R.id.btnMergeSort);

        tvBubbleSort = (TextView)findViewById(R.id.tvBubbleSort);
        tvSelectionSort = (TextView)findViewById(R.id.tvSelectionSort);
        tvMergeSort = (TextView)findViewById(R.id.tvMergeSort);

        //Choosing Complexity and Generating an Array for Given ArraySize
        btnGenerateArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rGroup.getCheckedRadioButtonId();

                btnBubbleSort.setEnabled(true);
                btnSelectionSort.setEnabled(true);
                btnMergeSort.setEnabled(true);
                btnBenchmarkAll.setEnabled(true);

                try {
                    arraySize = Integer.parseInt(etArraySize.getText().toString());
                    linearLayout.setVisibility(View.VISIBLE);
                } catch (NumberFormatException e){
                    tvGeneratedArray.setText("Please Enter an Array Size");
                    linearLayout.setVisibility(View.GONE);
                    return;
                }
                array = new int[arraySize];
                cloneArray = new int[arraySize];

                if (selectedId == rBtnBest.getId()) {
                    for (int i = 0; i<arraySize; i++) {
                        array[i] = i + 1;
                    }
                    for(int i: array){
                        cloneArray=array;
                    }
                    tvGeneratedArray.setText("Array of Size " + arraySize + " is Generated for Best Case");
                } else if (selectedId == rBtnWorst.getId()) {
                    int j=0;
                    for(int i=arraySize; i>0; i--){
                        array[j++]=i;
                    }
                    for(int i: array){
                        cloneArray=array;
                    }
                    tvGeneratedArray.setText("Array of Size " + arraySize + " is Generated for Worst Case");
                } else {
                    for(int i=0; i<arraySize; i++){
                        array[i]=(int)(Math.random()*100);
                    }
                    for(int i: array){
                        cloneArray=array;
                    }
                    tvGeneratedArray.setText("Array of Size " + arraySize + " is Generated for Average Case");
                }
            }
        });
    }
}

