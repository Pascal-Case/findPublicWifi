package dao;

import model.BookMark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkDao {
    /**
     * 북마크 리스트 가져오기
     *
     * @param conn 컨넥션 객체
     * @return 북마크 리스트
     * @throws SQLException SQL 예외
     */
    public List<BookMark> getBookmarkList(Connection conn) throws SQLException {
        String sql = "SELECT BM.*, BG.BOOKMARK_GROUP_NAME, BG.BOOKMARK_ORDER, WI.X_SWIFI_MAIN_NM " +
                " FROM BOOKMARK BM LEFT JOIN BOOKMARK_GROUP BG on BG.BOOKMARK_GROUP_ID = BM.BOOKMARK_GROUP_ID " +
                " LEFT JOIN main.WIFI_INFO WI on WI.X_SWIFI_MGR_NO = BM.X_SWIFI_MGR_NO " +
                " ORDER BY BG.BOOKMARK_ORDER ";
        List<BookMark> bookMarkList = new ArrayList<>();
        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
            ResultSet rs = pStatement.executeQuery();
            System.out.println(sql);
            while (rs.next()) {
                bookMarkList.add(generateBookmarkObj(rs));
            }

        }
        
        return bookMarkList;
    }

    /**
     * 북마크 상세 정보
     *
     * @param conn       컨넥션 객체
     * @param bookmarkId 북마크 아이디
     * @return 북마크 상세 정보
     * @throws SQLException SQL 예외
     */
    public BookMark getBookmarkDetail(Connection conn, int bookmarkId) throws SQLException {
        BookMark bookMark = null;
        String sql = "SELECT BM.*, BG.BOOKMARK_GROUP_NAME, BG.BOOKMARK_ORDER, WI.X_SWIFI_MAIN_NM " +
                " FROM BOOKMARK BM LEFT JOIN BOOKMARK_GROUP BG on BG.BOOKMARK_GROUP_ID = BM.BOOKMARK_GROUP_ID " +
                " LEFT JOIN main.WIFI_INFO WI on WI.X_SWIFI_MGR_NO = BM.X_SWIFI_MGR_NO " +
                " WHERE BOOKMARK_ID = ?";

        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
            pStatement.setInt(1, bookmarkId);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                bookMark = generateBookmarkObj(rs);
            }

        }
        return bookMark;
    }

    /**
     * 북마크 생성
     *
     * @param conn              컨넥션 객체
     * @param wifiMgrNo         와이파이 관리번호
     * @param bookmarkGroupId   북마크 그룹 아이디
     * @param formattedDateTime 등록일
     * @throws SQLException SQL 예외
     */


    public void insertBookmark(Connection conn, String wifiMgrNo, int bookmarkGroupId, String formattedDateTime) throws SQLException {
        String sql = "INSERT INTO BOOKMARK (X_SWIFI_MGR_NO, BOOKMARK_GROUP_ID, POST_DATE) VALUES (?, ?, ?)";
        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
            pStatement.setString(1, wifiMgrNo);
            pStatement.setInt(2, bookmarkGroupId);
            pStatement.setString(3, formattedDateTime);

            pStatement.executeUpdate();
        }
    }

    /**
     * 북마크 삭제
     *
     * @param conn       컨넥션 객체
     * @param bookmarkId 북마크 아이디
     * @throws SQLException SQL 예외
     */
    public void deleteBookmark(Connection conn, int bookmarkId) throws SQLException {
        String sql = "DELETE FROM BOOKMARK WHERE BOOKMARK_ID = ? ";

        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
            pStatement.setInt(1, bookmarkId);
            pStatement.executeUpdate();
        }

    }

    /**
     * 중복되는 북마크인지 체크
     *
     * @param conn            컨넥션 객체
     * @param wifiMgrNo       와이파이 관리번호
     * @param bookmarkGroupId 북마크 그룹 번호
     * @return 북마크 중복 여부
     * @throws SQLException SQL 예외
     */
    public boolean isDuplicateBookmark(Connection conn, String wifiMgrNo, int bookmarkGroupId) throws SQLException {
        String sql = "SELECT COUNT(*) as cnt FROM BOOKMARK WHERE X_SWIFI_MGR_NO = ? AND BOOKMARK_GROUP_ID = ?";
        boolean isDuplicateBookmark;
        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
            pStatement.setString(1, wifiMgrNo);
            pStatement.setInt(2, bookmarkGroupId);
            int count = pStatement.executeQuery().getInt("cnt");
            isDuplicateBookmark = count > 0;
        }
        return isDuplicateBookmark;
    }

    private BookMark generateBookmarkObj(ResultSet rs) throws SQLException {
        int bookmarkId = rs.getInt("BOOKMARK_ID");
        String wifiMgrNo = rs.getString("X_SWIFI_MGR_NO");
        int bookmarkGroupId = rs.getInt("BOOKMARK_GROUP_ID");
        String bookmarkGroupName = rs.getString("BOOKMARK_GROUP_NAME");
        int bookmarkGroupOrder = rs.getInt("BOOKMARK_ORDER");
        String wifiName = rs.getString("X_SWIFI_MAIN_NM");
        String postDate = rs.getString("POST_DATE");
        return new BookMark(bookmarkId, wifiMgrNo, bookmarkGroupId, bookmarkGroupName, bookmarkGroupOrder, wifiName, postDate);
    }
}
