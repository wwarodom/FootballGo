package com.thanachat.myfootball;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;



import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class CommitActivity extends Activity {

    CountDownTimer cdt;
    TextView tvTimer;
    ToggleButton btnCount;
    TextView homescore;
    TextView awayscore;

    int setstatus = 0;
    int csstatus = 0;
    int time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit);

        tvTimer = (TextView)findViewById(R.id.tvTimer);

        btnCount = (ToggleButton)findViewById(R.id.btnCount);
        btnCount.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton view
                    , boolean isChecked) {
                TextView chpop = (TextView)findViewById(R.id.chpopup);
                if(isChecked) {
                    csstatus = 1;
                    chpop.setVisibility(View.GONE);
                    cdt = new CountDownTimer(time, 1000) {
                        public void onTick(long millisUntilFinished) {
                            String strTime = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                                    , (double)millisUntilFinished / 1000);
                            tvTimer.setText(String.valueOf(strTime));
                        }

                        public void onFinish() {
                            tvTimer.setText("00:00:00");
                            btnCount.setChecked(false);
                        }
                    }.start();
                } else {
                    cdt.cancel();
                    homescore = (TextView)findViewById(R.id.homescore);
                    awayscore = (TextView)findViewById(R.id.awayscore);
                    tvTimer.setText("00:00:00");
                    csstatus = 0;
                    homescore.setText("0");
                    awayscore.setText("0");
                }
            }
        });
    }

    public void backCommit (View v){
        finish();
    }

    public void setting (View v){
        LinearLayout settingbar = (LinearLayout)findViewById(R.id.settingwindows);
        if (setstatus == 0){
            settingbar.setVisibility(View.VISIBLE);
            setstatus = 1;
        }
        else if(setstatus == 1){
            settingbar.setVisibility(View.GONE);
            setstatus = 0;
        }

        }

    public void save (View v){
        EditText timeinput = (EditText)findViewById(R.id.ts);
        EditText homeinput = (EditText)findViewById(R.id.homename);
        EditText awayinput = (EditText)findViewById(R.id.awayname);

        tvTimer = (TextView)findViewById(R.id.tvTimer);
        TextView hname = (TextView)findViewById(R.id.hname);
        TextView aname = (TextView)findViewById(R.id.aname);

        ScrollView homeview = (ScrollView) findViewById(R.id.htview);
        ScrollView awayview = (ScrollView) findViewById(R.id.atview);

        LinearLayout settingbar = (LinearLayout)findViewById(R.id.settingwindows);
        LinearLayout timebar = (LinearLayout)findViewById(R.id.timebar);

        if (timeinput.getText().toString().length() > 0) {
            if (homeinput.getText().toString().length() > 0){
                if (awayinput.getText().toString().length() > 0){
                    //time
                    time = Integer.parseInt(timeinput.getText().toString());
                    time = time*60000;
                    setstatus = 0;
                    int showtime;
                    showtime = time/60000;
                    tvTimer.setText(showtime + " Min");
                    //home name
                    String homename;
                    homename = homeinput.getText().toString();
                    hname.setText("Team: " + homename);
                    //Away name
                    String awayname;
                    awayname = awayinput.getText().toString();
                    aname.setText("Team: " + awayname);
                    //Layout
                    settingbar.setVisibility(View.GONE);
                    homeview.setVisibility(View.VISIBLE);
                    awayview.setVisibility(View.VISIBLE);
                    timebar.setVisibility(View.VISIBLE);
                    //Clear
                    timeinput.setText(null);
                    homeinput.setText(null);
                    awayinput.setText(null);
                }
                else{
                    awayinput.setError("กรุณากรอกชื่อทีม");
                    return;
                }
            }
            else{
                homeinput.setError("กรุณากรอกชื่อทีม");
                return;
            }

        }
        else
        {
            timeinput.setError("กรุณากรอกเวลา");
            return;
        }
    }
    //add and sub home score
    public void homeaddscore(View v){
        TextView homechaddpop = (TextView)findViewById(R.id.chpopup);
        homescore = (TextView)findViewById(R.id.homescore);
        int hscore = Integer.parseInt(homescore.getText().toString());
        if(csstatus == 1){
            hscore = hscore+1;
            homescore.setText("" + hscore);
        }
        else{
            homechaddpop.setVisibility(View.VISIBLE);
        }
    }
    public void homesubscore(View v){
        TextView homechsubpop = (TextView)findViewById(R.id.chpopup);
        homescore = (TextView)findViewById(R.id.homescore);
        int hscore = Integer.parseInt(homescore.getText().toString());
        if(csstatus == 1){
            if(hscore > 0){
                hscore = hscore-1;
                homescore.setText("" + hscore);
            }
            else{
                homescore.setText("" + hscore);
            }
        }
        else{
            homechsubpop.setVisibility(View.VISIBLE);
        }
    }

    //add and sub away score
    public void awayaddscore(View v){
        TextView awaychaddpop = (TextView)findViewById(R.id.chpopup);
        awayscore = (TextView)findViewById(R.id.awayscore);
        int ascore = Integer.parseInt(awayscore.getText().toString());
        if(csstatus == 1){
            ascore = ascore+1;
            awayscore.setText("" + ascore);
        }
        else{
            awaychaddpop.setVisibility(View.VISIBLE);
        }
    }
    public void awaysubscore(View v){
        TextView awaychsubpop = (TextView)findViewById(R.id.chpopup);
        awayscore = (TextView)findViewById(R.id.awayscore);
        int ascore = Integer.parseInt(awayscore.getText().toString());
        if(csstatus == 1){
            if(ascore > 0){
                ascore = ascore-1;
                awayscore.setText("" + ascore);
            }
            else{
                awayscore.setText("" + ascore);
            }
        }
        else{
            awaychsubpop.setVisibility(View.VISIBLE);
        }
    }
}

