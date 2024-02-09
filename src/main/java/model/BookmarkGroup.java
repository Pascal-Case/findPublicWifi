package model;

public class BookmarkGroup {
    private Integer groupId;
    private String groupName;
    private int groupOrder;
    private String postDate;
    private String editDate;

    public BookmarkGroup(String groupName, int groupOrder, String postDate, String editDate) {
        this.groupName = groupName;
        this.groupOrder = groupOrder;
        this.postDate = postDate;
        this.editDate = editDate;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getGroupOrder() {
        return groupOrder;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getEditDate() {
        return editDate;
    }
}
