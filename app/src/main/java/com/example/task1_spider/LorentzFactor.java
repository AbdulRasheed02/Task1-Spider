package com.example.task1_spider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class LorentzFactor extends AppCompatActivity {

    Button btn_menu, btn_findLorentzFactor,btn_checkAnswer;
    TextView tv_lorentzFactorResult, tv_practice, tv_practiceResult;
    View screenView;
    Vibrator vibrator;
    EditText et_inputSpeed, et_inputSpeedPractice, et_inputLorentzFactorPractice;
    Double inputSpeed, lorentzFactor, inputSpeedPractice,oldInputSpeedPractice,inputLorentzFactorPractice;
    int attemptsRemaining;
    final double c=300_000_000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lorentz_factor);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        this.setTitle("LORENTZ FACTOR CALCULATOR");

        btn_menu=findViewById(R.id.btn_menu);
        btn_findLorentzFactor=findViewById(R.id.btn_findLorentzFactor);
        btn_checkAnswer=findViewById(R.id.btn_checkAnswer);

        tv_lorentzFactorResult=findViewById(R.id.tv_lorentzFactorResult);
        tv_practice=findViewById(R.id.tv_practice);
        tv_practiceResult=findViewById(R.id.tv_practiceResult);

        et_inputSpeed=findViewById(R.id.et_inputSpeed);
        et_inputSpeedPractice=findViewById(R.id.et_inputSpeedPractice);
        et_inputLorentzFactorPractice=findViewById(R.id.et_inputLorentzFactorPractice);

        screenView=findViewById(R.id.rview);

        btn_menu.setText("MENU");
        btn_findLorentzFactor.setText("FIND LORENTZ FACTOR");
        btn_checkAnswer.setText("CHECK LORENTZ FACTOR");

        tv_practice.setText("PRACTICE");

        et_inputSpeed.setHint("Enter Speed in m/s");
        et_inputSpeedPractice.setHint("Enter Speed in m/s");
        et_inputLorentzFactorPractice.setHint("Enter Lorentz Factor(upto four decimals)");

        oldInputSpeedPractice=0.0;

        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LorentzFactor.this, MainActivity.class));
            }
        });

        btn_findLorentzFactor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_inputSpeed.getText().toString().isEmpty()){
                    Toast.makeText(LorentzFactor.this,"Enter Speed Value",Toast.LENGTH_SHORT).show();
                    tv_lorentzFactorResult.setVisibility(View.INVISIBLE);
                }
                else {
                    inputSpeed = Double.parseDouble(et_inputSpeed.getText().toString());
                    lorentzCalculator(inputSpeed);
                }
            }
        });

        btn_checkAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_inputSpeedPractice.getText().toString().isEmpty()||et_inputLorentzFactorPractice.getText().toString().isEmpty()) {
                    Toast.makeText(LorentzFactor.this,"Enter both the values",Toast.LENGTH_LONG).show();
                    tv_practiceResult.setVisibility(View.INVISIBLE);
                }
                else {
                    inputSpeedPractice=Double.parseDouble(et_inputSpeedPractice.getText().toString());
                    inputLorentzFactorPractice=Double.parseDouble(et_inputLorentzFactorPractice.getText().toString());
                    if(!(inputSpeedPractice.equals(oldInputSpeedPractice))){
                        oldInputSpeedPractice = inputSpeedPractice;
                        attemptsRemaining=3;
                    }
                    checkAnswer(inputSpeedPractice, inputLorentzFactorPractice);
                }
            }
        });
    }

    void lorentzCalculator(double speed){
        if(speed<0 || speed>=c){
            String text = "Invalid Input. Enter speed value between 0 and 3x10\u2078";
            Spannable centeredText = new SpannableString(text);
            centeredText.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, text.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            Toast.makeText(this,centeredText, Toast.LENGTH_LONG).show();
            tv_lorentzFactorResult.setVisibility(View.INVISIBLE);
        }
        else {
            lorentzFactor = 1 / (Math.sqrt(1 - (Math.pow(speed,2) / Math.pow(c,2))));
            tv_lorentzFactorResult.setVisibility(View.VISIBLE);
            tv_lorentzFactorResult.setText("Lorentz Factor = "+lorentzFactor);
        }

    }

    void checkAnswer(double speedPractice,double lorentzFactorPractice){
        if(speedPractice<0 || speedPractice>=c){
            String text = "Invalid Input. Enter speed value between 0 and 3x10\u2078";
            Spannable centeredText = new SpannableString(text);
            centeredText.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, text.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            Toast.makeText(LorentzFactor.this, centeredText, Toast.LENGTH_LONG).show();
            tv_practiceResult.setVisibility(View.INVISIBLE);
        }
        else if(lorentzFactorPractice<1){
            String text = "Invalid Input. Value of Lorentz Factor must be >=1";
            Spannable centeredText = new SpannableString(text);
            centeredText.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, text.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            Toast.makeText(LorentzFactor.this, centeredText, Toast.LENGTH_LONG).show();
            tv_practiceResult.setVisibility(View.INVISIBLE);
        }
        else{
            lorentzFactor = 1 / (Math.sqrt(1 - (Math.pow(speedPractice, 2) / Math.pow(c, 2))));
            DecimalFormat decimalFormat = new DecimalFormat("###.####");
            Double lorentzFactorRounded=Double.parseDouble(decimalFormat.format(lorentzFactor));
            tv_practiceResult.setVisibility(View.VISIBLE);
            if(lorentzFactorPractice==lorentzFactorRounded){
                tv_practiceResult.setText("Lorentz Factor is Correct!");
                screenView.setBackgroundColor(Color.parseColor("#dcfcf7"));
                Handler handler=new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        screenView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                },500);
                attemptsRemaining=3;
            }
            else if(attemptsRemaining>1){
                attemptsRemaining--;
                tv_practiceResult.setText("Lorentz Factor is Incorrect!\n"+"Remaining Attempts: "+attemptsRemaining);
                vibrator.vibrate(75);
                screenView.setBackgroundColor(Color.parseColor("#fac8c9"));
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        screenView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                },500);
            }
            else{
                tv_practiceResult.setText("Lorentz Factor is Incorrect!\n"+"Correct Answer: "+lorentzFactorRounded);
                vibrator.vibrate(75);
                screenView.setBackgroundColor(Color.parseColor("#fac8c9"));
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        screenView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                },500);
            }
        }
    }
}