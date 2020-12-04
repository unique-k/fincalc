package com.axt.FINCALC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

public class TipActivity extends AppCompatActivity {
    private Button clear, calculate;
    private TextView billA, tip, people, totalOut, tipsOut, splitOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        //=============Find button=====================//
        clear=findViewById(R.id.clear_buttonTip);
        calculate=findViewById(R.id.calculateButtonTip);
        //============Find input======================//
        billA=findViewById(R.id.billAmount);
        tip=findViewById(R.id.tipPercent);
        people=findViewById(R.id.numOfPeople);
        //============Find output======================//
        totalOut=findViewById(R.id.totalAmount);
        tipsOut=findViewById(R.id.tipsAmount);
        splitOut=findViewById(R.id.splittedAmount);

        //=========== on Clear Button========================//
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //==========Output============//
                billA.setText("");
                tip.setText("");
                people.setText("");
                totalOut.setText("");
                tipsOut.setText("");
                splitOut.setText("");
            }
        });

        //==================== on Calculate Button==============================//
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(calculate.getWindowToken(), 0);

                if (billA.getText().toString().equals(""))
                    billA.setError("Enter Value");
                else if (tip.getText().toString().equals(""))
                    tip.setError("Enter Value");
                else if (people.getText().toString().equals(""))
                    people.setError("Enter Value");
                else
                {
                    double inputBillVar = Double.parseDouble(billA.getText().toString());
                    double tipVar = Double.parseDouble(tip.getText().toString());
                    if (tipVar <= 0) {
                        tipVar = 1;
                        splitOut.setText("1");
                    }
                    double numVar = Double.parseDouble(people.getText().toString());

                    double totalAmount = 0, tipsAmount = 0, splitAmount = 0;

                    totalAmount = inputBillVar + ((tipVar / 100) * inputBillVar);
                    tipsAmount = (tipVar / 100) * inputBillVar;
                    splitAmount = totalAmount / numVar;

                    //==============Output=====================//
                    totalOut.setText(String.valueOf(totalAmount));
                    tipsOut.setText(String.valueOf(tipsAmount));
                    splitOut.setText(String.valueOf(splitAmount));
            }

            }
        });
    }
}