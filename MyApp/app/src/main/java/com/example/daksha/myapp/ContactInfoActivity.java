package com.example.daksha.myapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ContactInfoActivity extends AppCompatActivity {

    private TextView tvConatct;
    private Button btnContactInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info_layout);

        tvConatct=(TextView)findViewById(R.id.tvContact);
        btnContactInformation=(Button)findViewById(R.id.btnConcatInformation);
    }

    public void getContactInformation(){
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri contact = data.getData();
        ContentResolver cr = getContentResolver();

        Cursor c = managedQuery(contact, null, null, null, null);
        //      c.moveToFirst();


        while(c.moveToNext()){
            String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));

            String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            if (Integer.parseInt(c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", new String[]{id}, null);

                while(pCur.moveToNext()){
                    String phone = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    //tvConatct.setText(name+" Added " + phone);
                    Toast.makeText(getBaseContext(),name + " Added " + phone,Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
