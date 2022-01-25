package com.example.android.firebaseapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.firebaseapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPActivity extends AppCompatActivity {

    private TextInputEditText mOTP;
    Button mVerify;
    private FirebaseAuth mFirebaseAuth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        String phoneNumber = getIntent().getStringExtra("phoneNumber");

        mOTP = findViewById(R.id.edit_otp);
        mVerify = findViewById(R.id.btn_verify);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Processing...");

        mVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.show();
                FirebaseAuthSettings firebaseAuthSettings = mFirebaseAuth.getFirebaseAuthSettings();
                firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phoneNumber, mOTP.getText().toString());
                PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mFirebaseAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(OTPActivity.this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                mDialog.dismiss();
                                moveToNext();
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                mDialog.dismiss();
                            }
                        })
                        .build();
                PhoneAuthProvider.verifyPhoneNumber(options);
            }
        });
    }

    private void moveToNext() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
 }