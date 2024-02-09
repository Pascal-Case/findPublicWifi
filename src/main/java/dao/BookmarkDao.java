package dao;

import model.BookMark;

import java.sql.*;

public class BookmarkDao {
    public void insertBookmark(Connection conn, BookMark bookmark) throws SQLException {
        String sql = "INSERT INTO BOOKMARK (X_SWIFI_MGR_NO, BOOKMARK_GROUP_ID, X_SWIFI_MAIN_NM, POST_DATE) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pStatement.setString(1, bookmark.getMgrNo());
            pStatement.setInt(2, bookmark.getGroupId());
            pStatement.setString(3, bookmark.getWifiName());
            pStatement.setString(4, bookmark.getPostDate());


            int affectedRows = pStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        bookmark.setBookmarkId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating bookmark failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBookmark(Connection conn, BookMark bookmark) throws SQLException {
        String sql = "DELETE FROM BOOKMARK WHERE BOOKMARK_ID = ? ";

        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
            pStatement.setInt(1, bookmark.getBookmarkId());
            int result = pStatement.executeUpdate();
        }

    }
}
