package dao;

import model.BookmarkGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookmarkGroupDao {

    /**
     * 북마크 등록
     *
     * @param conn          컨넥션 객체
     * @param bookmarkGroup 북마크 그룹
     * @throws SQLException SQL 예외
     */
    public void registerBookmarkGroup(Connection conn, BookmarkGroup bookmarkGroup) throws SQLException {
        String sql = "INSERT INTO BOOKMARK_GROUP (BOOKMARK_GROUP_NAME, BOOKMARK_ORDER, POST_DATE) " +
                " VALUES (?, ?, ?)";
        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
            pStatement.setString(1, bookmarkGroup.getGroupName());
            pStatement.setInt(2, bookmarkGroup.getGroupOrder());
            pStatement.setString(3, bookmarkGroup.getPostDate());
            int result = pStatement.executeUpdate();
            if (result == 1) {

            } else {

            }
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

            int result = pStatement.executeUpdate();
            if (result == 1) {

            } else {

            }
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
}
