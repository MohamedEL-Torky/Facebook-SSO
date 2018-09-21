package com.example.android.ssotestfacebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

public class MainActivity extends AppCompatActivity {

    TextView textStatus;
    Button loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        initializeControls();
        loginFB();

    }

    private void initializeControls(){
        callbackManager = CallbackManager.Factory.create();
        textStatus = (TextView) findViewById(R.id.login_status);
        loginButton = (Button) findViewById(R.id.login_button);
    }

    private void loginFB(){
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        textStatus.setText("Login Sucess");
                    }

                    @Override
                    public void onCancel() {
                        textStatus.setText("Login process Canceled");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        textStatus.setText("Error occurred");
                        Log.e("MainActivity",  exception.toString());
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
