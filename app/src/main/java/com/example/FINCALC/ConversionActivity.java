package com.example.FINCALC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConversionActivity extends AppCompatActivity {
    Button time,temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);


        time=findViewById(R.id.button_time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();
            }
        });

        temp=findViewById(R.id.button_Temp);
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTempActivity();
            }
        });
    }
    public void openNewActivity(){
        Intent intent=new Intent(this, timeActivity.class);
        startActivity(intent);
    }
    public void openLengthActivity(View View){
        Intent intent=new Intent(this,lengthActivity.class);
        startActivity(intent);
    }
    public void openTempActivity(){
        Intent intent=new Intent(this, temperatureActivity.class);
        startActivity(intent);
    }
    public void openPressActivity(View View){
        Intent intent=new Intent(this, PressureActivity.class);
        startActivity(intent);
    }
    public void openMassActivity(View View){
        Intent intent=new Intent(this, MassActivity.class);
        startActivity(intent);
    }
    public void openForceActivity(View View){
        Intent intent=new Intent(this, ForceActivity.class);
        startActivity(intent);
    }
}