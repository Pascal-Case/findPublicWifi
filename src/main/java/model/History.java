package model;

public class History {
    private Integer historyId;
    private double lat;
    private double lnt;
    private String searchDate;
    
    public History(double lat, double lnt, String searchDate) {
        this.lat = lat;
        this.lnt = lnt;
        this.searchDate = searchDate;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public double getLat() {
        return lat;
    }

    public double getLnt() {
        return lnt;
    }

    public String getSearchDate() {
        return searchDate;
    }
}
