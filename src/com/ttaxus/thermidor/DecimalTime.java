package com.ttaxus.thermidor;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Duration;

public class DecimalTime {
	double dectime;
	DecimalTime(DateTime time) {
		DateMidnight midnight = new DateMidnight();
		Duration duration = new Duration(midnight, time);
		long millis = duration.getMillis();
		dectime = millis / 86400000.0;
	}
	public String toString() {
		int hours = (int)(dectime*10);
		int minutes = (int)(dectime*1000) - hours*100;
		int seconds = (int)(dectime*100000) - hours*10000 - minutes*100;
		String hstring = Integer.toString(hours);
		String mstring = Integer.toString(minutes);
		if (minutes < 10)
			mstring = "0" + mstring;
		String sstring = Integer.toString(seconds);
		if (seconds < 10)
			sstring = "0" + sstring;
		
		return hstring + ":" + mstring + ":" + sstring;
	}
}