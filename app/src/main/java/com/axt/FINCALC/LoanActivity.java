package com.axt.FINCALC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoanActivity extends AppCompatActivity {

    private EditText loan_amount; // creating a variable of type EditText.
    private EditText loan_time;
    private EditText loan_rate; // creating a variable of type EditText.
    private Button calc, clear; // creating a calc of button type.
    private TextView result_principle;
    private TextView result_interest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        // find view by id
        loan_amount = findViewById(R.id.loan_amt); // saves the value of loan amt in loan_amount.
        loan_time = findViewById(R.id.loan_term); // saves the value of loan term in loan_time
        loan_rate = findViewById(R.id.rpy); // saves the value of loan rate in loan_rate.
        calc = findViewById(R.id.calculate_loan);
        result_principle = findViewById(R.id.total_principle);
        result_interest = findViewById(R.id.total_rate);
        clear = findViewById(R.id.clc);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loan_rate.setText("");
                loan_time.setText("");
                loan_amount.setText("");
                result_interest.setText("");
                result_principle.setText("");
            }
        });


        // when user clicks
        calc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(calc.getWindowToken(), 0);

                double payment =0, interest_paid=0,temp_payment=0,temp_interest_paid=0;
                double amt = 0, time=0,rate=0;
                double rate_per_month=0,num_of_months=0,temp=0;
                double power_variable=0;

                if (loan_amount.getText().toString().equals("")) {
                    loan_amount.setError("Enter value");
                }
                else if (loan_time.getText().toString().equals("")) {
                    loan_time.setError("Enter value");
                }
                else if (loan_rate.getText().toString().equals("")) {
                    loan_rate.setError("Enter value");
                }
                else {
                    if(Double.parseDouble(loan_time.getText().toString())>100)
                        loan_time.setError("Loan time must be less than 100 years.");
                    else if(Double.parseDouble(loan_rate.getText().toString())>100)
                        loan_rate.setError("Loan rate must be less tha 100%");
                    else{
                        try {
                            amt = Double.parseDouble(loan_amount.getText().toString());
                            time = Double.parseDouble(loan_time.getText().toString());
                            rate = Double.parseDouble(loan_rate.getText().toString());
                            rate_per_month = rate / 1200;
                            num_of_months = time * 12;
                            temp = rate_per_month + 1;
                            power_variable = (double) Math.pow(temp, num_of_months);
                            temp_payment = (amt * rate_per_month * power_variable) / (power_variable - 1);
                            payment = (double) (Math.round(temp_payment * 100.00) / 100.00);
                            temp_interest_paid = (num_of_months * payment) - amt;
                            interest_paid = (double) (Math.round(temp_interest_paid * 100.00) / 100.00);

                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }

                        result_principle.setText(String.valueOf(payment));
                        result_interest.setText(String.valueOf(interest_paid));
                    }

                }

            }

        });


    }
}