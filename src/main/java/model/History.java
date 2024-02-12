package model;

public class History {
    private Integer historyId;
    private final double lat;
    private final double lnt;
    private final String searchDate;

    public History(double lat, double lnt, String searchDate) {
        this.lat = lat;
        this.lnt = lnt;
        this.searchDate = searchDate;
    }

    public History(int historyId, double lat, double lnt, String searchDate) {
        this.historyId = historyId;
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
