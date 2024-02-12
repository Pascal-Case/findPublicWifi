package dao;

import model.BookmarkGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupDao {
    /**
     * 북마크 그룹 리스트
     *
     * @param conn 컨넥션 객체
     * @return 북마크 그룹 리스트
     * @throws SQLException SQL 예외
     */
    public List<BookmarkGroup> getBookmarkGroupList(Connection conn) throws SQLException {
        List<BookmarkGroup> bookmarkGroupList = new ArrayList<>();
        String sql = "SELECT * FROM BOOKMARK_GROUP ORDER BY BOOKMARK_ORDER";
        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {

            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                bookmarkGroupList.add(getBookmarkGroupObj(rs));
            }
        }
        return bookmarkGroupList;
    }


    /**
     * 북마크 그룹 등록
     *
     * @param conn          컨넥션 객체
     * @param bookmarkGroup 북마크 그룹
     * @throws SQLException SQL 예외
     */
    public void addBookmarkGroup(Connection conn, BookmarkGroup bookmarkGroup) throws SQLException {
        String sql = "INSERT INTO BOOKMARK_GROUP (BOOKMARK_GROUP_NAME, BOOKMARK_ORDER, POST_DATE) " +
                " VALUES (?, ?, ?)";
        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
            pStatement.setString(1, bookmarkGroup.getGroupName());
            pStatement.setInt(2, bookmarkGroup.getGroupOrder());
            pStatement.setString(3, bookmarkGroup.getPostDate());
            pStatement.executeUpdate();
        }
    }

    /**
     * 북마크 업데이트
     *
     * @param conn          컨넥션 객체
     * @param bookmarkGroup 북마크 그룹
     * @throws SQLException SQL 예외
     */
    public void updateBookmarkGroup(Connection conn, BookmarkGroup bookmarkGroup) throws SQLException {

        String sql = "UPDATE BOOKMARK_GROUP " +
                " SET BOOKMARK_GROUP_NAME = ?, " +
                " BOOKMARK_ORDER = ?, " +
                " EDIT_DATE = ? " +
                " WHERE BOOKMARK_GROUP_ID = ?";
        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
            pStatement.setString(1, bookmarkGroup.getGroupName());
            pStatement.setInt(2, bookmarkGroup.getGroupOrder());
            pStatement.setString(3, bookmarkGroup.getEditDate());
            pStatement.setInt(4, bookmarkGroup.getGroupId());
            pStatement.executeUpdate();
        }
    }

    /**
     * 북마크 그룹 삭제 및 해당 그룹의 북마크 삭제
     *
     * @param conn            컨넥션 객체
     * @param bookMarkGroupId 삭제 대상 북마크 그룹 id
     * @throws SQLException SQL 예외
     */
    public void deleteBookmarkGroup(Connection conn, int bookMarkGroupId) throws SQLException {
        String bookmarkDeleteQuery = "DELETE FROM BOOKMARK WHERE BOOKMARK_GROUP_ID = ?";
        String bookmarkGroupDeleteQuery = "DELETE FROM BOOKMARK_GROUP WHERE BOOKMARK_GROUP_ID = ?";

        try (PreparedStatement bookmarkStatement = conn.prepareStatement(bookmarkDeleteQuery);
             PreparedStatement groupStatement = conn.prepareStatement(bookmarkGroupDeleteQuery)) {

            // 북마크를 먼저 삭제
            bookmarkStatement.setInt(1, bookMarkGroupId);
            bookmarkStatement.executeUpdate();

            // 그룹 삭제
            groupStatement.setInt(1, bookMarkGroupId);
            groupStatement.executeUpdate();
        }
    }

    /**
     * 특정 북마크 그룹 가져오기
     *
     * @param conn                  컨넥션 객체
     * @param targetBookmarkGroupId 북마크 그룹 아이디
     * @return 묵마크 그룹
     */
    public BookmarkGroup getBookmarkGroupDetail(Connection conn, int targetBookmarkGroupId) throws SQLException {
        BookmarkGroup bookmarkGroup = null;
        String sql = "SELECT * FROM BOOKMARK_GROUP WHERE BOOKMARK_GROUP_ID = ?";
        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
            pStatement.setInt(1, targetBookmarkGroupId);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                bookmarkGroup = getBookmarkGroupObj(rs);
            }
        }
        return bookmarkGroup;
    }

    private BookmarkGroup getBookmarkGroupObj(ResultSet rs) throws SQLException {
        int bookmarkGroupId = rs.getInt("BOOKMARK_GROUP_ID");
        String bookmarkGroupName = rs.getString("BOOKMARK_GROUP_NAME");
        int bookmarkGroupOrder = rs.getInt("BOOKMARK_ORDER");
        String postDate = rs.getString("POST_DATE");
        String editDate = rs.getString("EDIT_DATE");
        if (editDate == null) {
            editDate = "";
        }

        return new BookmarkGroup(bookmarkGroupId, bookmarkGroupName, bookmarkGroupOrder, postDate, editDate);
    }
}
