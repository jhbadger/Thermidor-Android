package com.ttaxus.thermidor;

import android.app.Application;
import org.joda.time.LocalDate;

public class Thermidor extends Application {
	int checked = R.id.radioFrench;
	LocalDate d = new LocalDate();
	
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
