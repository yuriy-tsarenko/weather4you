package com.example.weather4you.model;

import java.util.List;

public class WeatherForecastResponse {
    private String cod;
    private double message;
    private int cnt;
    private List<DataList> list;
    private City city;

    public WeatherForecastResponse() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<DataList> getList() {
        return list;
    }

    public void setList(List<DataList> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
