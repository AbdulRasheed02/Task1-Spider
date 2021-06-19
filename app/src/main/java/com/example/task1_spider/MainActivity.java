package com.example.task1_spider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_lorentzFactor, btn_spiFactor, btn_quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        this.setTitle("MENU");

        btn_lorentzFactor=findViewById(R.id.btn_loretnzFactor);
        btn_spiFactor=findViewById(R.id.btn_spiFactor);
        btn_quit=findViewById(R.id.btn_quit);

        btn_lorentzFactor.setText("LORENTZ FACTOR");
        btn_spiFactor.setText("SPI FACTOR");
        btn_quit.setText("QUIT");

        btn_lorentzFactor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LorentzFactor.class));
            }
        });

        btn_spiFactor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SpiFactor.class));
            }
        });

        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
    public void onBackPressed() {
        finishAffinity();
    }
}