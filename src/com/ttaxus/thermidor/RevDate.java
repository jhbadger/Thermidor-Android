package com.ttaxus.thermidor;

import org.joda.time.LocalDate;
import org.joda.time.Days;

public class RevDate {
	private int year;
	private int month;
	private int day;
	
	// return number of days in given revolutionary year
	private static int yearLength(int year) {
		if (year == 3 || year == 7 || year == 11 || 
				year == 15 || year == 20)
			return 366;
		else if (year > 20 && (year % 4 == 0 && (year % 100 > 0) || 
				year % 400 == 0))
		    return 366;
		else
			return 365;
	}
	// create new RevDate with given year, month, day 
	// (numeric, using revolutionary year, month, day)
	public RevDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	// convert the despised reactionary date of the ancien regime 
	// to our glorious rational format
	public static RevDate fromGregorian(LocalDate d) {
		LocalDate start = new LocalDate(1792,9,22);
		int days = Days.daysBetween(start, d).getDays();
		int year = 1;
		int yearLen = yearLength(year);
		while (days >= yearLen) {
			days -= yearLen;
			year += 1;
			yearLen = yearLength(year);
		}
		int month = (1 + (days / 30));
		int day = (1 + (days % 30));
		return new RevDate(year, month, day);
	}
	public String toString(String[] months) {
	  String s = "";
	  s += day +" " + months[month - 1] + " " + year;
	  return s;
	}
	public String symbol(String[] symbols)  {
		int dayNum = 30*(month - 1) + (day - 1);
		return symbols[dayNum];
	}
}
