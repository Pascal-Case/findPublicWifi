package dao;

import model.History;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistoryDao {

    /**
     * 히스토리 삭제
     *
     * @param conn    컨넥션 객체
     * @param history 히스토리 객체
     */
    public void insertHistory(Connection conn, History history) throws SQLException {
        String sql = "INSERT INTO HISTORY (LAT, LNT, SEARCH_DATE) VALUES (?, ?, ?)";

        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
            pStatement.setDouble(1, history.getLat());
            pStatement.setDouble(2, history.getLnt());
            pStatement.setString(3, history.getSearchDate());
            pStatement.executeUpdate();
        }
    }

    /**
     * 히스토리 등록
     *
     * @param conn    컨넥션 객체
     * @param history 히스토리 객체
     */
    public void deleteHistory(Connection conn, History history) throws SQLException {
        String sql = "DELETE FROM HISTORY WHERE HISTORY_ID = ?";

        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
            pStatement.setInt(1, history.getHistoryId());
            int result = pStatement.executeUpdate();

            if (result == 1) {
                // success
            } else {
                // fail
            }

        }
    }
}
