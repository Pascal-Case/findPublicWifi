package service;

import dao.HistoryDao;
import model.History;
import util.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HistoryService {

    /**
     * 히스토리 가져오기
     *
     * @return 히스토리 리스트
     */
    public List<History> getHistory() {
        List<History> historyList;

        try (Connection conn = DbUtil.getConnection()) {
            HistoryDao historyDao = new HistoryDao();
            historyList = historyDao.getHistory(conn);
        } catch (SQLException e) {
            throw new RuntimeException("히스토리를 가져오는 중 오류가 발생했습니다.", e);
        }
        return historyList;
    }

    /**
     * 히스토리 저장
     *
     * @param userLat 위도
     * @param userLnt 경도
     */
    public void saveHistory(double userLat, double userLnt) {
        History history = null;

        try (Connection conn = DbUtil.getConnection()) {
            HistoryDao historyDao = new HistoryDao();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime now = LocalDateTime.now();
            String formattedDateTime = now.format(formatter);
            history = new History(userLat, userLnt, formattedDateTime);
            historyDao.insertHistory(conn, history);
        } catch (SQLException e) {
            throw new RuntimeException("히스토리 저장 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 히스토리 삭제
     *
     * @param historyId 히스토리 아이디
     */
    public void deleteHistory(int historyId) {
        try (Connection conn = DbUtil.getConnection()) {
            HistoryDao historyDao = new HistoryDao();
            historyDao.deleteHistory(conn, historyId);
        } catch (SQLException e) {
            throw new RuntimeException("히스토리 삭제 중 오류가 발생했습니다.", e);
        }
    }
}
