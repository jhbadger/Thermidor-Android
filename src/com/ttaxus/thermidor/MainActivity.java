package com.ttaxus.thermidor;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.DatePicker;
import android.app.Activity;
import android.os.Handler;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import org.joda.time.LocalDate;
import org.joda.time.DateTime;
import android.util.SparseArray;

public class MainActivity extends Activity {
	Handler handler = new Handler();
	DatePicker dp;
	TextView clockText, dateText, symbolText;
	String[] symbols;
	SparseArray<String[]> months;
	Runnable clock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dp = (DatePicker) findViewById(R.id.dp);
        clockText = (TextView)findViewById(R.id.clockText);
        clockText.setText(new DecimalTime(new DateTime()).toString());
        dateText = (TextView)findViewById(R.id.dateText);
        symbolText = (TextView)findViewById(R.id.symbolText);
        months = new SparseArray<String []>();
        months.put(R.id.radioFrench, getResources().getStringArray(R.array.French_months));
        months.put(R.id.radioCarlyle, getResources().getStringArray(R.array.Carlyle_months));
        months.put(R.id.radioSatire, getResources().getStringArray(R.array.English_satirical_months));
        symbols = getResources().getStringArray(R.array.Day_Names);
        // update revolutionary date when DatePicker updated
        dp.getCalendarView().setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
          public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
            update(new LocalDate(year, month + 1, day));
          }
        });
        clock = new Runnable()
        {
            public void run() 
            {
            	clockText.setText(new DecimalTime(new DateTime()).toString());
            	handler.postDelayed(this, 100);
            }
        }; 
        LocalDate d = ((Thermidor)getApplication()).getDate();
        update(d);
    }
    
    // today button
    public void todayButtonClick(View v) {
    	today();
    }
    
    // goto today
    private void today() {
    	LocalDate d =  new LocalDate();
    	dp.updateDate(d.getYear(), d.getMonthOfYear() - 1, 
    			d.getDayOfMonth());
    	update(d);
    }
    
    // stop clock when application in background
    protected void onPause() {
        handler.removeCallbacks(clock);
        super.onPause();
    }
    
    protected void onDestroy() {
        handler.removeCallbacks(clock);
        super.onDestroy();
    }
    
    // on resuming
    protected void onResume() {
        handler.postDelayed(clock, 100);
        today();
        super.onResume();
    }
    
    // update date field with currently selected date
    private void update(LocalDate d) {
    	RevDate revdate = RevDate.fromGregorian(d);
    	int checked = ((Thermidor)getApplication()).getChecked();
        dateText.setText(revdate.toString(months.get(checked)));
        symbolText.setText(revdate.symbol(symbols));
        ((Thermidor)getApplication()).setDate(d);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_settings:
            Intent i = new Intent(this, Settings.class);
            startActivity(i);
            break;
        case R.id.ABOUT:
        	About about = new About(this);
        	about.setTitle("about this app");
        	about.show();
        }
 
        return true;
    }

}
