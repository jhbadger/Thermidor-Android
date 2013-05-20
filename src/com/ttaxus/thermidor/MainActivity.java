package com.ttaxus.thermidor;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.DatePicker;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import org.joda.time.LocalDate;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatePicker dp = (DatePicker) findViewById(R.id.dp);
        // update revolutionary date when DatePicker updated
        dp.getCalendarView().setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
          public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
            update(new LocalDate(year, month + 1, day));
          }
        });
        Button button = (Button) findViewById(R.id.button1);
        // go to today when button is pressed,
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	DatePicker dp = (DatePicker) findViewById(R.id.dp);
            	LocalDate d =  new LocalDate();
            	dp.updateDate(d.getYear(), d.getMonthOfYear() - 1, 
            			d.getDayOfMonth());
            	update(d);
            }
        });
        LocalDate d = ((Thermidor)getApplication()).getDate();
        update(d);
    }
    
    // update date field with currently selected date
    public void update(LocalDate d) {
    	RevDate revdate = RevDate.fromGregorian(d);
        TextView date_field = (TextView)findViewById(R.id.date_field);
        TextView symbol_field = (TextView)findViewById(R.id.symbol_field);
        String[] months = ((Thermidor)getApplication()).getMonths();
        String[] symbols = ((Thermidor)getApplication()).getSymbols();
        date_field.setText(revdate.toString(months));
        symbol_field.setText(revdate.symbol(symbols));
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
