package com.example.weather4you.model;

public class Coord {
    private double lon;
    private double lat;

    public Coord() {
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(lat);
        builder.append(",");
        builder.append(lon);
        builder.append("]");
        return builder.toString();
    }
}