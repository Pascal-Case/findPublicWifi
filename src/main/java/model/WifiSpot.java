package model;

public class WifiSpot {
    private WifiInfo wifiInfo;
    private double distance;

    public WifiSpot(WifiInfo wifiInfo, double distance) {
        this.wifiInfo = wifiInfo;
        this.distance = distance;
    }

    public WifiInfo getWifiInfo() {
        return wifiInfo;
    }

    public double getDistance() {
        return distance;
    }
}
