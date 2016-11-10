package com.example.daksha.myapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daksha.myapp.utils.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.daksha.myapp.UserRegistrationActivity.UD_KEY;
import static com.example.daksha.myapp.UserRegistrationActivity.UD_PREF;
import static com.example.daksha.myapp.UserRegistrationActivity.UE_KEY;
import static com.example.daksha.myapp.UserRegistrationActivity.UM_KEY;
import static com.example.daksha.myapp.UserRegistrationActivity.UN_KEY;
import static com.example.daksha.myapp.UserRegistrationActivity.UP_KEY;
import static com.example.daksha.myapp.UserRegistrationActivity.password;

public class MyAccountActivity extends AppCompatActivity {

    private EditText etFName;
    private EditText etEMail;
    private EditText etMNo;
    private TextView tvSSex;
    private EditText etDob;
    private EditText etPass;

    private String mFName, mEMail, mMNo, mDob, mPass;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account_layout);

        sharedPreferences = getSharedPreferences(UD_PREF,MODE_PRIVATE);

        etFName = (EditText) findViewById(R.id.etFName);
        etEMail = (EditText) findViewById(R.id.etEMail);
        etMNo = (EditText) findViewById(R.id.etMNo);
        tvSSex = (TextView) findViewById(R.id.tvSSex);
        etDob = (EditText) findViewById(R.id.etDob);
        etPass = (EditText) findViewById(R.id.etPas);

        loadPreferences();

        loadValues();

    }

    public void loadValues(){

        etFName.setText(mFName);
        etEMail.setText(mEMail);
        etMNo.setText(mMNo);
        etDob.setText(mDob);
        etPass.setText(mPass);

    }

    public void loadPreferences(){
        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        mFName = sharedPreferences.getString(UN_KEY,null);
        mEMail = sharedPreferences.getString(UE_KEY,null);
        mMNo = sharedPreferences.getString(UM_KEY,null);
        mDob = sharedPreferences.getString(UD_KEY,null);
        mPass = sharedPreferences.getString(UP_KEY,null);

        if(sharedPreferences.getBoolean("MALE", false))
            tvSSex.setText("MALE");
        else if(sharedPreferences.getBoolean("FEMALE",false))
            tvSSex.setText("FEMALE");
        else if(sharedPreferences.getBoolean("OTHERS",false))
            tvSSex.setText("OTHERS");
    }

    public void saveProfile(View view){

        if(!(TextUtils.isEmpty(etEMail.getText().toString()) && TextUtils.isEmpty(etEMail.getText().toString()) &&
            TextUtils.isEmpty(etMNo.getText().toString()) && TextUtils.isEmpty(etDob.getText().toString()) &&
                TextUtils.isEmpty(etPass.getText().toString()))) {

            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            Pattern patternEmail = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
            Matcher matcherEmail = patternEmail.matcher(etEMail.getText().toString());
            if (!matcherEmail.find()) {
                etEMail.setError("Please Enter Your Valid e-mail id");
                Toast.makeText(getBaseContext(),"Please Enter Your Valid e-mail id",Toast.LENGTH_SHORT).show();
            } else if(etPass.length() < 6){
                Toast.makeText(getBaseContext(),"Please Enter 6 or More Characters",Toast.LENGTH_SHORT).show();
            } else {

                if (!(etEMail.getText().toString().equals(mEMail))) {
                    reSendMail();
                    Toast.makeText(getBaseContext(), "Check Your New Password in Your Mail", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(UP_KEY, etPass.getText().toString());
                    editor.commit();
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(UN_KEY, etFName.getText().toString());
                editor.putString(UM_KEY, etMNo.getText().toString());
                editor.putString(UD_KEY, etDob.getText().toString());
                editor.putString(UE_KEY, etEMail.getText().toString());
                editor.commit();

                loadPreferences();
                loadValues();

                Toast.makeText(getBaseContext(),"Saved Successfully",Toast.LENGTH_SHORT).show();
            }
        }

    }

    protected void reSendMail(){
        //Getting content for email
        String email = etEMail.getText().toString();
        String subject = "Re-Sending Password";
        String message = "Your New Password is ";
        String pas = Util.generateRandomPassword();

        password = pas;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(UP_KEY,password);
        editor.commit();
        Toast.makeText(getBaseContext(),password,Toast.LENGTH_SHORT).show();

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message, pas);

        //Executing sendmail to send email
        sm.execute();
    }
}
