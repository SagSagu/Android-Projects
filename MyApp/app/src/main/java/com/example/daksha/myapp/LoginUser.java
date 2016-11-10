package com.example.daksha.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daksha.myapp.utils.Util;

import static com.example.daksha.myapp.UserRegistrationActivity.UE_KEY;
import static com.example.daksha.myapp.UserRegistrationActivity.UP_KEY;
import static com.example.daksha.myapp.UserRegistrationActivity.password;

public class LoginUser extends AppCompatActivity {

    private EditText etLoginEmail;
    private EditText etLoginPassword;

    private SharedPreferences sharedPreferences;

    public static final String LOGOUT = "LOGOUT";
    public static final String NREG = "NREG";

    private String lEmail;
    private String lPass;
    //public static String password;

    public UserRegistrationActivity user = new UserRegistrationActivity();

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user_layout);

        etLoginEmail = (EditText) findViewById(R.id.etLoginEmail);
        etLoginPassword = (EditText) findViewById(R.id.etLoginPassword);

        sharedPreferences = getSharedPreferences(UserRegistrationActivity.UD_PREF,MODE_PRIVATE);

        lPass = sharedPreferences.getString(UP_KEY,null);
        lEmail = sharedPreferences.getString(UE_KEY,null);

        Toast.makeText(getBaseContext(),lEmail,Toast.LENGTH_SHORT).show();
        Toast.makeText(getBaseContext(),lPass,Toast.LENGTH_SHORT).show();
    }

    public void registerNow(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NREG,"REG");
        editor.commit();
        startActivity(new Intent(getBaseContext(),UserRegistrationActivity.class));
    }

    public void forgotPassword(View view){
        if(TextUtils.isEmpty(etLoginEmail.getText().toString()))
            etLoginEmail.setError("Please Enter Your e-mail id");
        else if (!(etLoginEmail.getText().toString().equals(lEmail)))
            etLoginEmail.setError("Please Use Your Registered e-mail id");
        else {
            reSendMail();
            startActivity(new Intent(getBaseContext(),LoginUser.class));
            Toast.makeText(getBaseContext(),"Check Your New Password in Your Mail",Toast.LENGTH_SHORT).show();
        }
    }

    protected void reSendMail(){
        //Getting content for email
        String email = etLoginEmail.getText().toString();
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

    public void loginSubmit(View view){

        if(TextUtils.isEmpty(etLoginEmail.getText().toString()))
            etLoginEmail.setError("Please Enter Your e-mail id");
        else if(TextUtils.isEmpty(etLoginPassword.getText().toString()))
            etLoginPassword.setError("Please Enter Your Password");
        else if (!(etLoginEmail.getText().toString().equals(lEmail)))
            etLoginEmail.setError("e-mail id is Wrong");
        else if (!(etLoginPassword.getText().toString().equals(lPass)))
                etLoginPassword.setError("Password is Wrong");
            else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(LOGOUT,"LOGIN");
                editor.commit();
                startActivity(new Intent(getBaseContext(), Greet_User.class));
                //progressDialog = ProgressDialog.show(getBaseContext(),"Loading","Please wait...",false,false);
            }
    }
}
