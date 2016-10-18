package com.example.daksha.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SimpleCalculatorActivity extends AppCompatActivity {

    private EditText etFirstNumber;
    private EditText etSecondNumber;
    private TextView tvResult;

    public void doCalculation(View view){

        double num1=0;
        try {
            num1=Double.parseDouble(etFirstNumber.getText().toString());
        } catch (NumberFormatException e){
            tvResult.setText("Please Enter Valid First Number");
            return;
        }
        double num2=0;
        try{
            num2=Double.parseDouble(etSecondNumber.getText().toString());
        } catch (NumberFormatException e){
            tvResult.setText("Please Enter Valid Second Number");
            return;
        }

        switch (view.getId()){
            case R.id.btnAdd:
                tvResult.setText("" + (num1+num2));
                break;
            case R.id.btnSub:
                tvResult.setText("" + (num1-num2));
                break;
            case R.id.btnMul:
                tvResult.setText(""+ (num1*num2));
                break;
            case R.id.btnDiv:
                tvResult.setText(""+ (num1/num2));
                break;
            case R.id.btnMod:
                tvResult.setText(""+ (num1%num2));
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_calculator_layout);

        etFirstNumber = (EditText) findViewById(R.id.etFirstNumber);
        etSecondNumber = (EditText) findViewById(R.id.etSecondNumber);
        tvResult = (TextView) findViewById(R.id.tvResult);
    }
}
