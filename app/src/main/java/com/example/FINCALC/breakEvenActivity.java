package com.example.FINCALC;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
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
                float fixedCost,revenue, variableCost;
                int breakEvenPoint=0;

                fixedCost=Float.parseFloat(fixedVar.getText().toString());
                revenue=Float.parseFloat(revenueVar.getText().toString());
                variableCost=Float.parseFloat(variableVar.getText().toString());

                breakEvenPoint= (int)Math.ceil(fixedCost/(revenue-variableCost));
                if(revenue<variableCost)
                {
                    revenueVar.setError("should be higher than variable cost");
                    AlertDialog.Builder builder= new AlertDialog.Builder(breakEvenActivity.this);
                    builder.setMessage("Invalid Revenue")
                            .setNegativeButton("Cancel",null);

                    AlertDialog alert =builder.create();
                    alert.show();
                }
                else
                {
                    brOutputVar.setText(String.valueOf(breakEvenPoint));
                }

            }
        });
    }
}