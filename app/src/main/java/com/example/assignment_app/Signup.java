package com.example.assignment_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {

    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +
                    //"(?=.*[a-z])" +
                    //"(?=.*[A-Z])" +
                    "(?=.*[a-zA-Z])" +
                    "(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{6,}" +
                    "$");

    TextView textInputEmail;
    TextView textInputUsername;
    TextView textInputPassword;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_signup);

        textInputEmail = findViewById(R.id.text_input_email);
        textInputUsername = findViewById(R.id.text_input_uname);
        textInputPassword = findViewById(R.id.text_input_pswd);
    }

    private boolean validateEmail(){
        String emailInput = textInputEmail.getEditableText().toString().trim();

        if (emailInput.isEmpty()){
            textInputEmail.setError("Fields can't be empty");
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            textInputEmail.setError("Enter a valid email address");
            return false;

        }
        else{
            textInputEmail.setError(null);
            return true;
        }

    }
    private boolean validateUsername(){
        String usernameInput = textInputUsername.getEditableText().toString().trim();

        if (usernameInput.isEmpty()){
            textInputUsername.setError("Fields can't be empty");
            return false;
        } else if (usernameInput.length() > 15){
            textInputUsername.setError("username is too long");
            return false;
        }else{
            textInputUsername.setError(null);
            return true;

        }
    }
    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditableText().toString().trim();
        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Fields can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.setError("Password is too weak");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }

    public void confirmInput(View v){
        if (!validateEmail() | !validateUsername() | !validatePassword()){
            return;
        }
        String input = "Email:" + textInputEmail.getEditableText().toString();
        input += "\n";
        input = "Username:" + textInputUsername.getEditableText().toString();
        input += "\n";
        input = "Password:" + textInputPassword.getEditableText().toString();

        Toast.makeText(this,input,Toast.LENGTH_SHORT).show();


    }

    public void OpenViewImage(View view){
        startActivity(new Intent(Signup.this, Viewimg.class ));
    }
    public void OpenLogin(View view){

        startActivity(new Intent(Signup.this, Login.class));
    }
}
