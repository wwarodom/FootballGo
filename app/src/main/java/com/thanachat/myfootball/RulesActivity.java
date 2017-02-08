package com.thanachat.myfootball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
    }

    public void backRules (View view){
        finish();
    }
    public void r1click (View v){
        ((TextView)findViewById(R.id.r_topic)).setText("กติกา :");
        ((TextView)findViewById(R.id.r_info)).setText(R.string.r1text);
        ((TextView)findViewById(R.id.r_info)).setVisibility(View.VISIBLE);
    }
    public void r2click (View v){
        ((TextView)findViewById(R.id.r_topic)).setText("การทำฟาวล์ :");
        ((TextView)findViewById(R.id.r_info)).setText(R.string.r2text);
        ((TextView)findViewById(R.id.r_info)).setVisibility(View.VISIBLE);
    }
    public void r3click (View v){
        ((TextView)findViewById(R.id.r_topic)).setText("ข้อบังคับ :");
        ((TextView)findViewById(R.id.r_info)).setText(R.string.r3text);
        ((TextView)findViewById(R.id.r_info)).setVisibility(View.VISIBLE);
    }
}
