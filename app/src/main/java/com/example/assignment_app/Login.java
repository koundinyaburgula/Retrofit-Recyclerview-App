package com.example.assignment_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;



public class Login extends AppCompatActivity {

    TextView textInputEmail;
    TextView textInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEmail = findViewById(R.id.text_email1);
        textInputPassword = findViewById(R.id.text_pswd1);


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
    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditableText().toString().trim();
        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Fields can't be empty");
            return false;
        }else {
            textInputPassword.setError(null);
            return true;
        }
    }

    public void confirmLogin(View v){
        if (!validateEmail() | !validatePassword()){
            return;

        }

    }
    public void OpenSignUpPage(View view){
        startActivity(new Intent(Login.this, Signup.class));
        finish();


    }
}
