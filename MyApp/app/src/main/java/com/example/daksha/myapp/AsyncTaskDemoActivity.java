package com.example.daksha.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AsyncTaskDemoActivity extends AppCompatActivity {

    public void doSomething(View view){

        MyTask myTask = new MyTask(this);
        myTask.execute(1000,2000,3000,1000,5000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_task_demo_layout);
    }
}
