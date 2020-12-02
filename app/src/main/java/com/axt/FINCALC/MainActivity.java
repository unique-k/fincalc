package com.axt.FINCALC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button unitConversion;
    Button loanCalculate;
    Button apr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        unitConversion=findViewById(R.id.buttonUC);
        loanCalculate=findViewById((R.id.loanButton));

        unitConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();
            }
        });

        loanCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoanActivity();
            }
        });
    }
    public void openNewActivity(){
        Intent intent=new Intent(this, ConversionActivity.class);
        startActivity(intent);
    }
    public void openLoanActivity(){
        Intent intent=new Intent(this, LoanActivity.class);
        startActivity(intent);
    }
    public void openAPRActivity(View View){
        Intent intent=new Intent(this, APRActivity.class);
        startActivity(intent);
    }
    public void openInterestActivity(View View){
        Intent intent=new Intent(this, InterestActivity.class);
        startActivity(intent);
    }
    public void openTipActivity(View View){
        Intent intent=new Intent(this, TipActivity.class);
        startActivity(intent);
    }
    public void openBreakEvenActivity(View View){
        Intent intent=new Intent(this, breakEvenActivity.class);
        startActivity(intent);
    }
}