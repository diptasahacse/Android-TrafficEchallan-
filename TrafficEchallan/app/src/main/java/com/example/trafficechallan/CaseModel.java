package com.example.trafficechallan;

public class CaseModel {
    String case_name,case_amount,date,time;


    public CaseModel() {
    }

    public CaseModel(String case_name, String case_amount, String date, String time) {
        this.case_name = case_name;
        this.case_amount = case_amount;
        this.date = date;
        this.time = time;
    }

    public String getCase_name() {
        return case_name;
    }

    public void setCase_name(String case_name) {
        this.case_name = case_name;
    }

    public String getCase_amount() {
        return case_amount;
    }

    public void setCase_amount(String case_amount) {
        this.case_amount = case_amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
