package com.baraq.merchantsystem.dao;

public class BoundingBox {
    private double minLat;
    private double minLong;
    private double maxLat;
    private double maxLong;

    public BoundingBox(double minLat, double minLong, double maxLat, double maxLong) {
        this.minLat = minLat;
        this.minLong = minLong;
        this.maxLat = maxLat;
        this.maxLong = maxLong;
    }

    public boolean isInside(double lat, double longitude) {
        return minLat <= lat && lat <= maxLat && minLong <= longitude && longitude <= maxLong;
    }

    public static void main(String[] args) {
        BoundingBox bb = new BoundingBox(12.789125787044203,77.40371682952565, 13.146486077981745,77.81267491024114);
        System.out.println(bb.isInside(12.933730866469547, 77.5818433807342));
    }
}
