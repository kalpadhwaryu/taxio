package com.example.taxio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalculateTaxAct extends AppCompatActivity {
    Button itcbtn,emibtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_tax);

        itcbtn=findViewById(R.id.itcbtn);
        emibtn=findViewById(R.id.emibtn);

        itcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),IncomeActivity.class);
                startActivity(intent);
            }
        });

        emibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),EMIActivity.class);
                startActivity(intent);
            }
        });
    }
}