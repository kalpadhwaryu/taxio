package com.example.taxio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button callSignUp,login_btn;
    TextInputLayout username_login,password_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        callSignUp =findViewById(R.id.callSignup_btn);

        username_login=findViewById(R.id.username_login);
        password_login=findViewById(R.id.password_login);
        login_btn=findViewById(R.id.login_btn);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });

    }
    private Boolean validateUsername(){
        String val = username_login.getEditText().getText().toString();

        if(val.isEmpty()){
            username_login.setError("Field cannot be empty");
            return false;
        }

        else{
            username_login.setError(null);
            username_login.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword(){
        String val = password_login.getEditText().getText().toString();
        if(val.isEmpty()){
            password_login.setError("Field cannot be empty");
            return false;
        }
        else{
            password_login.setError(null);
            password_login.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view){
        if (!validateUsername() | !validatePassword()){
            return;
        }
        else {
            isUser();
        }
    }

    private void isUser() {
        final String userEnteredUsername = username_login.getEditText().getText().toString().trim();
        final String userEnteredPassword = password_login.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    username_login.setError(null);
                    username_login.setErrorEnabled(false);

                    final String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userEnteredPassword)){
                        Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                        username_login.setError(null);
                        username_login.setErrorEnabled(false);

                        String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String phonenoFromDB = snapshot.child(userEnteredUsername).child("phoneno").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),UserProfile.class);
                        intent.putExtra("username",usernameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("phoneno",phonenoFromDB);
                        intent.putExtra("password",passwordFromDB);

                        startActivity(intent);
//                        Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        password_login.setError("Wrong password");
                        password_login.requestFocus();
                    }
                }
                else {
                    username_login.setError("No such user exists");
                    username_login.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}