package com.example.task1_spider;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SpiFactor extends AppCompatActivity {

    Button btn_calculateSpiFactor, btn_Menu;
    TextView tv_spiFactorResult;
    double hour,minute,second,spiFactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psi_factor);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        this.setTitle("SPI FACTOR CALCULATOR");

        btn_calculateSpiFactor=findViewById(R.id.btn_calculateSpiFactor);
        btn_Menu=findViewById(R.id.btn_Menu);

        tv_spiFactorResult=findViewById(R.id.tv_spiFactorResult);

        btn_calculateSpiFactor.setText("Calculate Spi Factor");
        btn_Menu.setText("Menu");

        btn_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SpiFactor.this, MainActivity.class));
            }
        });

        btn_calculateSpiFactor.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                updatespiFactor();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void spiFactorCalculator(){
        Calendar calendar = GregorianCalendar.getInstance();
        hour=calendar.get(Calendar.HOUR);
        if(hour==0){
            hour=12;
        }
        minute=calendar.get(Calendar.MINUTE);
        second=calendar.get(Calendar.SECOND);

        spiFactor=factorial(hour)/(Math.pow(minute,3)+second);
        tv_spiFactorResult.setText("SPI FACTOR = "+String.format("%.6f",spiFactor));

    }

    double factorial(double n)
    {
        if (n == 0)
            return 1;
        return n*factorial(n-1);
    }

    Runnable updater;
    void updatespiFactor() {
        Handler timerHandler = new Handler();
        updater = new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                spiFactorCalculator();
                timerHandler.postDelayed(updater,1000);
            }
        };
        timerHandler.post(updater);
    }
}