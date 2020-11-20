package com.example.FINCALC;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
                float inputBillVar=Float.parseFloat(billA.getText().toString());
                float tipVar=Float.parseFloat(tip.getText().toString());
                if(tipVar<=0)
                {
                    tipVar=1;
                    splitOut.setText("1");
                }
                float numVar=Float.parseFloat(people.getText().toString());

                float totalAmount=0,tipsAmount=0,splitAmount=0;

                totalAmount=inputBillVar+((tipVar/100)*inputBillVar);
                tipsAmount=(tipVar/100)*inputBillVar;
                splitAmount=totalAmount/numVar;

                //==============Output=====================//
                totalOut.setText(String.valueOf(totalAmount));
                tipsOut.setText(String.valueOf(tipsAmount));
                splitOut.setText(String.valueOf(splitAmount));

            }
        });
    }
}