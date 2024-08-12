package com.example.task3callapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etPhoneNumber;
    private Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etPhoneNumber = findViewById(R.id.et_phone_number);
        btnCall = findViewById(R.id.btn_call);

        // Set click listener for the call button
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });
    }

    private void makeCall() {
        String phoneNumber = etPhoneNumber.getText().toString();

        if (!phoneNumber.isEmpty() && PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // Request CALL_PHONE permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            } else {
                startActivity(callIntent);
            }
        } else {
            Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
        }
    }
}
