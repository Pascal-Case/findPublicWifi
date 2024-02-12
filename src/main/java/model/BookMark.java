package model;

public class BookMark {
    private Integer bookmarkId;
    private final String mgrNo;
    private final int groupId;
    private final String groupName;
    private final int groupOrder;
    private final String wifiName;
    private final String postDate;

    public BookMark(String mgrNo, int groupId, String groupName, int groupOrder, String wifiName, String postDate) {
        this.mgrNo = mgrNo;
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupOrder = groupOrder;
        this.wifiName = wifiName;
        this.postDate = postDate;
    }


    public BookMark(Integer bookmarkId, String mgrNo, int groupId, String groupName, int groupOrder, String wifiName, String postDate) {
        this.bookmarkId = bookmarkId;
        this.mgrNo = mgrNo;
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupOrder = groupOrder;
        this.wifiName = wifiName;
        this.postDate = postDate;
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

    public String getGroupName() {
        return groupName;
    }

    public int getGroupOrder() {
        return groupOrder;
    }

    public String getWifiName() {
        return wifiName;
    }

    public String getPostDate() {
        return postDate;
    }
}
