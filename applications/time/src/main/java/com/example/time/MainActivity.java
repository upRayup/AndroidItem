package com.example.time;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private  int year,month,day,hour,minute;
    private TimePicker timePicker;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linearlayoutsample);

        Calendar calendar= Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        hour=calendar.get(Calendar.HOUR_OF_DAY);
        minute=calendar.get(Calendar.MINUTE);

        textView=(TextView)findViewById(R.id.userInfo);
        timePicker=(TimePicker)findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener((new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int i, int i1) {
                MainActivity.this.hour=i;
                MainActivity.this.minute=i1;
                showDate(year,month,day,hour,minute);
            }
        }));
    }
    public void showDate(int year,int month,int day,int hour,int minute){
        textView.setText(String.valueOf(year)+"年"+String.valueOf(month+1)+"月"+String.valueOf(day)+"日 "+String.valueOf(hour)+":"+String.valueOf(minute));
    }
}
