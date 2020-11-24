package com.example.FINCALC;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoanActivity extends AppCompatActivity {

    private EditText loan_amount; // creating a variable of type EditText.
    private EditText loan_time;
    private EditText loan_rate; // creating a variable of type EditText.
    private Button calc; // creating a calc of button type.
    private EditText result_principle;
    private EditText result_interest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        // find view by id
        loan_amount = findViewById(R.id.loan_amt); // saves the value of loan amt in loan_amount.
        loan_time = findViewById(R.id.loan_term); // saves the value of loaan term in loan_time
        loan_rate = findViewById(R.id.rpy); // saves the value of loan rate in loan_rate.
        calc = findViewById(R.id.calculate_loan);
        result_principle = findViewById(R.id.total_principle);
        result_interest = findViewById(R.id.total_rate);
        // when user clicks
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float payment =0, interest_paid=0,temp_payment=0,temp_interest_paid=0;
                float amt = 0, time=0,rate=0;
                float rate_per_month=0,num_of_months=0,temp=0;
                float power_variable=0;
                try {
                    amt = Float.parseFloat(loan_amount.getText().toString());
                    time = Float.parseFloat(loan_time.getText().toString());
                    rate = Float.parseFloat(loan_rate.getText().toString());
                    rate_per_month = rate/1200;
                    num_of_months = time*12;
                    temp = rate_per_month+1;
                    power_variable = (float)Math.pow(temp,num_of_months);
                    temp_payment = (amt*rate_per_month*power_variable)/(power_variable-1);
                    payment = (float) (Math.round(temp_payment*100.00)/100.00);
                    temp_interest_paid = (num_of_months*payment)-amt;
                    interest_paid = (float) (Math.round(temp_interest_paid*100.00)/100.00);

                } catch (NumberFormatException e){
                    e.printStackTrace();
                }
                result_principle.setText(String.valueOf(payment));
                result_interest.setText(String.valueOf(interest_paid));

            }
        });
    }
}