package com.nahid1911.notepad;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.Executor;

public class security_lay extends AppCompatActivity {

    BiometricPrompt prompt;

    private static final int RC_SIGN_IN = 9001;
    private SignInButton signInButton;
    private GoogleSignInOptions gso;
    private int REQUEST_USE_BIOMETRIC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_lay);


        BiometricManager manager = BiometricManager.from(this);
        switch (manager.canAuthenticate()){
            case BiometricManager.BIOMETRIC_SUCCESS:
                break;

            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                break;

            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                break;


        }

            prompt =  new BiometricPrompt(this,
                ContextCompat.getMainExecutor(this),
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                        finger();

                    }

                    @Override
                    public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);

                        Intent getlayout = new Intent(security_lay.this,MainActivity.class);
                        MainActivity.secure_code=12;
                        Intent intent = new Intent(security_lay.this,MainActivity.class);
                        startActivity(getlayout);


                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        finger();





                    }
                });




        finger();



    }





    private void finger(){
        BiometricPrompt.PromptInfo info = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric")
                .setNegativeButtonText("cancel")
                .setSubtitle("Confirm Your identity")
                .build();


        prompt.authenticate(info);

    }

}