package com.example.daksha.myapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.media.tv.TvInputService;
import android.net.Uri;
import android.preference.PreferenceManager;
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
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daksha.myapp.utils.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static com.example.daksha.myapp.LoginUser.NREG;

public class UserRegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    public static SharedPreferences sharedPreferences;
    public static final String UN_KEY = "UN_KEY";
    public static final String UE_KEY = "UE_KEY";
    public static final String UM_KEY = "UM_KEY";
    public static final String UG_KEY = "UG_KEY";
    public static final String UD_KEY = "UD_KEY";
    public static final String UP_KEY = "UP_KEY";

    public static final String UD_PREF = "UD_PREF";

    public static String loginEmail;
    public static String password;

    private RelativeLayout user_registration_layout;

    private EditText etFullName, etemail, etPassword, etReEnterPassword, etMobileNumber;
    private RadioButton rbMale, rbFeMale, rbOthers;
    private RadioGroup rgGender;
    private static ImageButton ibDOB;
    private Button btnSubmit;
    private static TextView tvDOB;

    private boolean yearValidity;

    private Calendar cal;
    private int day;
    private int month;
    private int year;

    public static int selectedId;

    private String reg;
    //Util util = new Util();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_registration_layout);

        etFullName=(EditText)findViewById(R.id.etFullName);
        etemail=(EditText)findViewById(R.id.etemail);
        //etPassword=(EditText)findViewById(R.id.etPassword);
        /*etReEnterPassword=(EditText)findViewById(R.id.etReEnterPassword);*/
        etMobileNumber=(EditText)findViewById(R.id.etMobileNumber);
        tvDOB=(TextView) findViewById(R.id.tvDOB);

        rgGender=(RadioGroup)findViewById(R.id.rgGender);
        rbMale=(RadioButton)findViewById(R.id.rbMale);
        rbFeMale=(RadioButton)findViewById(R.id.rbFeMale);
        rbOthers=(RadioButton)findViewById(R.id.rbOthers);

        //rgGender.setOnCheckedChangeListener(radioGroupOnCheckedChangeListener);

        ibDOB = (ImageButton) findViewById(R.id.ibDOB);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        ibDOB.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(UD_PREF,MODE_PRIVATE);

        reg = sharedPreferences.getString(NREG,null);

        if(NREG.equals(reg))
            clearAll();

        loadPreferences();
        //LoadRbPreferences();
    }

    public void loadPreferences(){
        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        etFullName.setText(sharedPreferences.getString(UN_KEY,etFullName.getText().toString()));
        etemail.setText(sharedPreferences.getString(UE_KEY,etemail.getText().toString()));
        etMobileNumber.setText(sharedPreferences.getString(UM_KEY,etMobileNumber.getText().toString()));
        tvDOB.setText(sharedPreferences.getString(UD_KEY,tvDOB.getText().toString()));

        if(sharedPreferences.getBoolean("MALE", false))
            rbMale.setChecked(true);
        else if(sharedPreferences.getBoolean("FEMALE",false))
            rbFeMale.setChecked(true);
        else if(sharedPreferences.getBoolean("OTHERS",false))
            rbOthers.setChecked(true);
    }

    @Override
    protected void onResume() {
        loadPreferences();
        super.onResume();
    }

    @Override
    protected void onStart() {
        loadPreferences();
        super.onStart();
    }

    @Override
    protected void onPause() {
        selectedId = rgGender.getCheckedRadioButtonId();
        Util.savePreferences(UN_KEY,etFullName.getText().toString());
        Util.savePreferences(UE_KEY,etemail.getText().toString());
        Util.savePreferences(UM_KEY,etMobileNumber.getText().toString());
        Util.savePreferences(UD_KEY,tvDOB.getText().toString());
        Util.savePreferences(UP_KEY,password);
        super.onPause();
    }

    /*@Override
    protected void onDestroy() {
        savePreferences(UN_KEY,etFullName.getText().toString());
        savePreferences(UE_KEY,etemail.getText().toString());
        savePreferences(UM_KEY,etMobileNumber.getText().toString());
        super.onDestroy();
    }*/

    @Override
    protected void onRestart() {
        loadPreferences();
        super.onRestart();
    }

    @Override
    protected void onStop() {
        selectedId = rgGender.getCheckedRadioButtonId();
        Util.savePreferences(UN_KEY,etFullName.getText().toString());
        Util.savePreferences(UE_KEY,etemail.getText().toString());
        Util.savePreferences(UM_KEY,etMobileNumber.getText().toString());
        Util.savePreferences(UD_KEY,tvDOB.getText().toString());
        Util.savePreferences(UP_KEY,password);
        //clearAll();
        super.onStop();

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
            tvDOB.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
            if(minAge.before(age)){
                Toast.makeText(getBaseContext(),"Your age Must Be Above 16",Toast.LENGTH_SHORT).show();
                final Animation animShake= AnimationUtils.loadAnimation(getBaseContext(),R.anim.anim_shake);
                tvDOB.startAnimation(animShake);
                yearValidity = false;
            } else
                yearValidity = true;

        }
    };

    public void submit(View view) {

        selectedId = rgGender.getCheckedRadioButtonId();
        Util.savePreferences(UN_KEY,etFullName.getText().toString());
        Util.savePreferences(UE_KEY,etemail.getText().toString());
        Util.savePreferences(UM_KEY,etMobileNumber.getText().toString());
        Util.savePreferences(UD_KEY,tvDOB.getText().toString());
        Util.savePreferences(UP_KEY,password);

        if(validates()==true) {
            boolean val = sendEmail();
            if (val == true) {

                startActivity(new Intent(getBaseContext(),LoginUser.class));
                Toast.makeText(getBaseContext(),"Message Sent",Toast.LENGTH_SHORT).show();
                //clearAll();
            }
        }
    }

    protected boolean sendEmail() {
        //Getting content for email
        String email = etemail.getText().toString();
        String subject = "Registration Successful";
        String message = "Successfully Registered in User Registration";
        String pas = Util.generateRandomPassword();

        password = pas;

        Util.savePreferences(UP_KEY,password);

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message, pas);

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

        /*if (TextUtils.isEmpty(etPassword.getText().toString())) {
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
        }*/

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

        if(TextUtils.isEmpty(tvDOB.getText().toString())){
            tvDOB.setError("Enter Your DOB");
            final Animation animShake= AnimationUtils.loadAnimation(this,R.anim.anim_shake);
            tvDOB.startAnimation(animShake);
            return false;
        } /*else if(yearValidity == false){
            Toast.makeText(getBaseContext(),"Your age Must Be Above 16",Toast.LENGTH_SHORT).show();
            final Animation animShake= AnimationUtils.loadAnimation(getBaseContext(),R.anim.anim_shake);
            tvDOB.startAnimation(animShake);
            return false;
        }*/

        return true;
    }

    public void clearAll(){
        etFullName.setText("");
        etemail.setText("");
        etPassword.setText("");
        etReEnterPassword.setText("");
        etMobileNumber.setText("");
        rgGender.clearCheck();
        tvDOB.setText("");
    }

}