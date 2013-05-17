package com.ttaxus.thermidor;

import android.os.Bundle;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.DatePicker;
import android.widget.Button;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import org.joda.time.LocalDate;

public class MainActivity extends Activity {
	LocalDate date = new LocalDate();
	int tYear = date.getYear();
	int tMonth = date.getMonthOfYear();
	int tDay = date.getDayOfMonth();
	int mYear = tYear;
	int mMonth = tMonth;
	int mDay = tDay;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatePicker dp = (DatePicker) findViewById(R.id.dp);
        OnDateChangedListener listener = new OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear + 1;
                mDay = dayOfMonth;
                update(mYear, mMonth, mDay);
            }
        };
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	mYear = tYear;
            	mMonth = tMonth;
            	mDay = tDay;
            	DatePicker dp = (DatePicker) findViewById(R.id.dp);
            	dp.updateDate(tYear, tMonth -1 , tDay);
            	update(mYear, mMonth, mDay);
            }
        });
        dp.init(mYear, mMonth - 1, mDay, listener);
        update(mYear, mMonth, mDay);
    }
    public void update(int mYear, int mMonth, int mDay) {
    	RevDate revdate = RevDate.fromGregorian(mYear, mMonth, mDay);
        TextView date_field = (TextView)findViewById(R.id.date_field);
        date_field.setText(revdate.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
  
}
