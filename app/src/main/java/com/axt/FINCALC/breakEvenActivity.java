package com.axt.FINCALC;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

public class breakEvenActivity extends AppCompatActivity {
    private Button clear, calculate;
    private TextView fixedVar,revenueVar,variableVar,brOutputVar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break_even);

        fixedVar=findViewById(R.id.fixedCostInput);
        revenueVar=findViewById(R.id.revenueInput);
        variableVar=findViewById(R.id.variableInput);
        brOutputVar=findViewById(R.id.breakEvenOutput);
        clear=findViewById(R.id.clear_buttonBr);
        calculate=findViewById(R.id.calculateButtonBr);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fixedVar.setText("");
                revenueVar.setText("");
                variableVar.setText("");
                brOutputVar.setText("");
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(calculate.getWindowToken(), 0);
                double fixedCost,revenue, variableCost;
                int breakEvenPoint=0;

                if(fixedVar.getText().toString().equals(""))
                    fixedVar.setError("Enter Value");
                else if(revenueVar.getText().toString().equals(""))
                    revenueVar.setError("Enter Value");
                else if(variableVar.getText().toString().equals(""))
                    variableVar.setError("Enter Value");
                else
                {

                    fixedCost=Double.parseDouble(fixedVar.getText().toString());
                    revenue=Double.parseDouble(revenueVar.getText().toString());
                    variableCost=Double.parseDouble(variableVar.getText().toString());

                    if(revenue<variableCost)
                        revenueVar.setError("should be higher than variable cost");
                    else
                    {
                        breakEvenPoint= (int) Math.ceil(fixedCost/(revenue-variableCost));
                        brOutputVar.setText(String.valueOf(breakEvenPoint));
                    }
                }
            }
        });
    }
}