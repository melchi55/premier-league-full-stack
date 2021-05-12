package com.example.premierLeague.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Date implements Serializable{

    private int day;
    private int month;
    private int year;

    public Date(@JsonProperty("day") int day,@JsonProperty("month") int month,@JsonProperty("year") int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }


    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString(){
        return day+ "/" + month+ "/"+year;
    }
    
}
