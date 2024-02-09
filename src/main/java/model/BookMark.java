package model;

import java.util.Date;

public class BookMark {
    private Integer bookmarkId;
    private String mgrNo;
    private int groupId;
    private String wifiName;
    private String postDate;

    public BookMark(String mgrNo, int groupId, String wifiName, Date postDate) {
        this.mgrNo = mgrNo;
        this.groupId = groupId;
        this.wifiName = wifiName;
        this.postDate = postDate.toString();
    }

    public void setBookmarkId(Integer bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public Integer getBookmarkId() {
        return bookmarkId;
    }

    public String getMgrNo() {
        return mgrNo;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getWifiName() {
        return wifiName;
    }

    public String getPostDate() {
        return postDate;
    }
}
