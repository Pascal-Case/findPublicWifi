package dao;

import model.History;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {

    /**
     * 히스토리 가져오기
     *
     * @param conn 컨넥션 객체
     * @return 히스토리 리스트
     * @throws SQLException SQL 예외
     */
    public List<History> getHistory(Connection conn) throws SQLException {
        String sql = "SELECT * from HISTORY ORDER BY HISTORY_ID desc";
        List<History> historyList = new ArrayList<>();
        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                int historyId = rs.getInt("HISTORY_ID");
                double lat = rs.getDouble("LAT");
                double lnt = rs.getDouble("LNT");
                String searchDate = rs.getString("SEARCH_DATE");

                historyList.add(new History(historyId, lat, lnt, searchDate));
            }

        }
        return historyList;
    }

    /**
     * 히스토리 등록
     *
     * @param conn    컨넥션 객체
     * @param history 히스토리 객체
     * @throws SQLException SQL 예외
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
     * 히스토리 삭제
     *
     * @param conn      컨넥션 객체
     * @param historyId 히스토리 아이디
     * @throws SQLException SQL 예외
     */
    public void deleteHistory(Connection conn, int historyId) throws SQLException {
        String sql = "DELETE FROM HISTORY WHERE HISTORY_ID = ?";

        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
            pStatement.setInt(1, historyId);
            pStatement.executeUpdate();
        }
    }
}
