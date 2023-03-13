package com.mahindra.annualsurvey.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;

@Entity
@Table(name = "mt_timer")
public class MTTimer implements Serializable
{ 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;	

	@Column(name="seconds")
	private int  seconds;
    
	@Column(name="minutes")
	private int  minutes;
	
	@Column(name="hours")
	private int  hours;
    
	@Column(name="dayOfMonths")
	private int  dayOfMonths;
	
	@Column(name="months")
	private int  months;
    
	@Column(name="dayofweek")
	private int  dayofweek;
	
	@Column(name="year")
	private int  year;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getDayOfMonths() {
		return dayOfMonths;
	}

	public void setDayOfMonths(int dayOfMonths) {
		this.dayOfMonths = dayOfMonths;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public int getDayofweek() {
		return dayofweek;
	}

	public void setDayofweek(int dayofweek) {
		this.dayofweek = dayofweek;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	

}
