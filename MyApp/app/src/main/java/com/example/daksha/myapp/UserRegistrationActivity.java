package com.example.daksha.myapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.media.tv.TvInputService;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UserRegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etFullName, etemail, etPassword, etReEnterPassword, etMobileNumber;
    private RadioButton rbMale, rbFeMale, rbOthers;
    private RadioGroup rgGender;
    private static ImageButton ibDOB;
    private Button btnSubmit;
    private static EditText etDOB;

    private Calendar cal;
    private int day;
    private int month;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_registration_layout);

        etFullName=(EditText)findViewById(R.id.etFullName);
        etemail=(EditText)findViewById(R.id.etemail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etReEnterPassword=(EditText)findViewById(R.id.etReEnterPassword);
        etMobileNumber=(EditText)findViewById(R.id.etMobileNumber);
        etDOB=(EditText) findViewById(R.id.etDOB);

        rgGender=(RadioGroup)findViewById(R.id.rgGender);
        rbMale=(RadioButton)findViewById(R.id.rbMale);
        rbFeMale=(RadioButton)findViewById(R.id.rbFeMale);
        rbOthers=(RadioButton)findViewById(R.id.rbOthers);

        ibDOB = (ImageButton) findViewById(R.id.ibDOB);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        ibDOB.setOnClickListener(this);

    }

    @Override
    public void onClick (View v){
        showDialog(0);
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog ( int id){
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            Calendar age =new GregorianCalendar(selectedYear,selectedMonth,selectedDay);
            Calendar minAge = new GregorianCalendar();
            minAge.add(Calendar.YEAR, -16);
            if(minAge.before(age)){
                Toast.makeText(getBaseContext(),"Your age Must Be Above 16",Toast.LENGTH_SHORT).show();
                final Animation animShake= AnimationUtils.loadAnimation(getBaseContext(),R.anim.anim_shake);
                etDOB.startAnimation(animShake);
            } else {
                etDOB.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                        + selectedYear);
            }
        }
    };

    public void submit(View view) {

        if(validates()==true) {
            sendEmail();
            if (sendEmail()==true) {
                clearAll();
            }
        }

    }

    protected boolean sendEmail() {
        //Getting content for email
        String email = etemail.getText().toString();
        String subject = "Registration Successful";
        String message = "Successfully Registered in User Registration";

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();

        return true;
    }

    public boolean validates(){

        if (TextUtils.isEmpty(etFullName.getText().toString())) {
            etFullName.setError("Please Enter Your Full Name");
            final Animation animShake= AnimationUtils.loadAnimation(this,R.anim.anim_shake);
            etFullName.startAnimation(animShake);
            return false;
        } else {
            String regx = "^[\\p{L} .'-]+$";
            Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(etFullName.getText().toString());
            if (!matcher.find()) {
                etFullName.setError("Please Enter Your Valid Full Name");
                final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.anim_shake);
                etFullName.startAnimation(animShake);
                return false;
            }
        }

        if (TextUtils.isEmpty(etemail.getText().toString())) {
            etemail.setError("Please Enter Your Valid e-mail id");
            final Animation animShake= AnimationUtils.loadAnimation(this,R.anim.anim_shake);
            etemail.startAnimation(animShake);
            return false;
        } else {
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            Pattern patternEmail = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
            Matcher matcherEmail = patternEmail.matcher(etemail.getText().toString());
            if (!matcherEmail.find()) {
                etemail.setError("Please Enter Your Valid e-mail id");
                final Animation animShake= AnimationUtils.loadAnimation(this,R.anim.anim_shake);
                etemail.startAnimation(animShake);
                return false;
            }
        }

        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError("Please Enter Your New Password");
            final Animation animShake= AnimationUtils.loadAnimation(this,R.anim.anim_shake);
            etPassword.startAnimation(animShake);
            return false;
        } else if (etPassword.length() < 6) {
            etPassword.setError("Please Enter 6 OR More Characters");
            final Animation animShake= AnimationUtils.loadAnimation(this,R.anim.anim_shake);
            etPassword.startAnimation(animShake);
                return false;
        }

        if (etReEnterPassword.getText().length() != etPassword.getText().length() || etReEnterPassword.getText().equals(etPassword.getText().toString())) {
            etReEnterPassword.setError("Please Re-Enter Your Password");
            final Animation animShake= AnimationUtils.loadAnimation(this,R.anim.anim_shake);
            etReEnterPassword.startAnimation(animShake);
            return false;
        }

        if (TextUtils.isEmpty(etMobileNumber.getText().toString())) {
            etMobileNumber.setError("Enter Your Mobile Number");
            final Animation animShake= AnimationUtils.loadAnimation(this,R.anim.anim_shake);
            etMobileNumber.startAnimation(animShake);
            return false;
        } else if (etMobileNumber.length() != 10) {
                etMobileNumber.setError("Enter A Valid Mobile Number");
                final Animation animShake= AnimationUtils.loadAnimation(this,R.anim.anim_shake);
                etMobileNumber.startAnimation(animShake);
                return false;
        }

        if (rgGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getBaseContext(), "Please Select Your Gender", Toast.LENGTH_SHORT).show();
            final Animation animShake= AnimationUtils.loadAnimation(this,R.anim.anim_shake);
            rgGender.startAnimation(animShake);
            return false;
        }

        if(TextUtils.isEmpty(etDOB.getText().toString())){
            etDOB.setError("Enter Your DOB");
            final Animation animShake= AnimationUtils.loadAnimation(this,R.anim.anim_shake);
            etDOB.startAnimation(animShake);
            return false;
        }

        return true;
    }

    public void clearAll(){
        etFullName.setText("");
        etemail.setText("");
        etPassword.setText("");
        etReEnterPassword.setText("");
        etMobileNumber.setText("");
        rgGender.clearCheck();
        etDOB.setText("");

    }

}