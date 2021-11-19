package com.example.taxio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class HRAExemption extends AppCompatActivity {
    TextView head, ans1, ans2, ans3, ans4, result;
    RadioButton button1, button2;
    RadioGroup radioGroup;
    Button Reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hraexemption);

//        head = findViewById(R.id.header);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        button1 = findViewById(R.id.b1);
        button2 = findViewById(R.id.b2);
        Reset = findViewById(R.id.res);
        result = findViewById(R.id.result);
        radioGroup = findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(checkedId);
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                         ans1.setText("");
                                         ans2.setText("");
                                         ans3.setText("");
                                         ans4.setText("");
                                         result.setText("");
                                         radioGroup.clearCheck();

                                     }
                                 }
        );
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        double BS = Double.parseDouble(ans1.getText().toString());
        double DA = Double.parseDouble(ans2.getText().toString());
        double HR = Double.parseDouble(ans3.getText().toString());
        double TR = Double.parseDouble(ans4.getText().toString());


        switch (view.getId()) {
            case R.id.b1: {
                double HRA1 = ((BS + DA) * 50) / 100;
                if (checked)
                    result.setText(Double.toString(HRA1));
                break;
            }
            case R.id.b2: {
                double HRA2 = ((BS + DA) * 40) / 100;
                if (checked)
                    result.setText(Double.toString(HRA2));
                break;
            }

        }
    }
}