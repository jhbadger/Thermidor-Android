package com.ttaxus.thermidor;

import android.app.Application;
import org.joda.time.LocalDate;

public class Thermidor extends Application {
	String months[];
	String symbols[];
	int checked = R.id.radioFrench;
	LocalDate d = new LocalDate();
	
	public void onCreate() {
		months = getResources().getStringArray(R.array.French_months);
		symbols =  getResources().getStringArray(R.array.Day_Names);
        super.onCreate();
    }
	
	public String[] getSymbols() {
		return symbols;
	}
	
	public String[] getMonths() {
		return months;
	}
		
	void setMonths(String [] months) {
		this.months = months;
	}
	
	public int getChecked() {
		return checked;
	}
	
	public void setChecked(int checked) {
		this.checked = checked;
	}
	
	public LocalDate getDate() {
		return d;
	}
	
	public void setDate(LocalDate d) {
		this.d = d;
	}
}
