package com.example.taxio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    private TextInputLayout username_signup,password_signup,confirm_password_signup;
    private Button callLogin,signup_btn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        callLogin=findViewById(R.id.callLogin);

        username_signup=findViewById(R.id.username_signup);
        password_signup=findViewById(R.id.password_signup);
        confirm_password_signup=findViewById(R.id.confirm_password_signup);
        signup_btn=findViewById(R.id.signup_btn);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode =FirebaseDatabase.getInstance();
                reference=rootNode.getReference("users");

                //Get all the values
                String username = username_signup.getEditText().getText().toString();
                String password = password_signup.getEditText().getText().toString();
                String confirmPassword = confirm_password_signup.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(username,password);
                reference.child(username).setValue(helperClass);

                Toast.makeText(Signup.this, "Signup Successful", Toast.LENGTH_SHORT).show();
            }
        });

        callLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this,Login.class);
                startActivity(intent);
            }
        });
    }
}