package dao;

import model.WifiInfo;
import model.WifiSpot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 와이파이 객체
 */
public class WifiInfoDao {
    /**
     * 입력받은 좌표 기준 가까운 와이파이 스팟 반환
     *
     * @param conn    컨넥션 객체
     * @param userLat 사용자의 위도
     * @param userLnt 사용자의 경도
     * @param limit   가져올 와이파이 스팟의 최대 개수
     * @return 와이파이 스팟 리스트
     */
    public List<WifiSpot> getWifiSpotList(Connection conn, double userLat, double userLnt, int limit) throws SQLException {
        List<WifiSpot> wifiSpotList = new ArrayList<>();

        String query = "SELECT " +
                "X_SWIFI_MGR_NO, " +
                "X_SWIFI_WRDOFC, " +
                "X_SWIFI_MAIN_NM, " +
                "X_SWIFI_ADRES1, " +
                "X_SWIFI_ADRES2, " +
                "X_SWIFI_INSTL_FLOOR, " +
                "X_SWIFI_INSTL_TY, " +
                "X_SWIFI_INSTL_MBY, " +
                "X_SWIFI_SVC_SE, " +
                "X_SWIFI_CMCWR, " +
                "X_SWIFI_CNSTC_YEAR, " +
                "X_SWIFI_INOUT_DOOR, " +
                "X_SWIFI_REMARS3, " +
                "LAT, " +
                "LNT, " +
                "WORK_DTTM, " +
                "SQRT(POW((LAT - ?), 2) + POW((LNT - ?), 2)) AS distance " +
                "FROM wifi_info " +
                "ORDER BY distance " +
                "LIMIT ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, userLat);
            stmt.setDouble(2, userLnt);
            stmt.setInt(3, limit);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String mgrNo = rs.getString("X_SWIFI_MGR_NO");
                String wrdofc = rs.getString("X_SWIFI_WRDOFC");
                String mainNm = rs.getString("X_SWIFI_MAIN_NM");
                String adres1 = rs.getString("X_SWIFI_ADRES1");
                String adres2 = rs.getString("X_SWIFI_ADRES2");
                String instlFloor = rs.getString("X_SWIFI_INSTL_FLOOR");
                String instlTy = rs.getString("X_SWIFI_INSTL_TY");
                String instlMby = rs.getString("X_SWIFI_INSTL_MBY");
                String svcSe = rs.getString("X_SWIFI_SVC_SE");
                String cmcwr = rs.getString("X_SWIFI_CMCWR");
                String cnstcYear = rs.getString("X_SWIFI_CNSTC_YEAR");
                String inoutDoor = rs.getString("X_SWIFI_INOUT_DOOR");
                String remars3 = rs.getString("X_SWIFI_REMARS3");
                double lat = rs.getDouble("LAT");
                double lnt = rs.getDouble("LNT");
                String workDttm = rs.getString("WORK_DTTM");
                double distance = rs.getDouble("distance");

                // WifiInfo 객체 생성
                WifiInfo wifiInfo = new WifiInfo(mgrNo, wrdofc, mainNm, adres1, adres2, instlFloor, instlTy,
                        instlMby, svcSe, cmcwr, cnstcYear, inoutDoor, remars3, lat, lnt, workDttm);

                // WifiSpot 객체 생성
                WifiSpot wifiSpot = new WifiSpot(wifiInfo, distance);

                // 리스트에 추가
                wifiSpotList.add(wifiSpot);
            }
        }

        return wifiSpotList;
    }

    /**
     * 와이파이 정보 전체 삭제 (갱신시 실행)
     */
    public void deleteAllWifiInfo(Connection conn) throws SQLException {
        String sql = "DELETE FROM WIFI_INFO";

        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
            pStatement.executeUpdate();
        }
    }

    /**
     * 와이파이 정보 등록 (이미 등록된 와이파이는 정보 갱신)
     *
     * @param conn     컨넥션 객체
     * @param wifiInfo 와이파이 정보 객체
     */
    public void upsertWifiInfo(Connection conn, WifiInfo wifiInfo) throws SQLException {
        String sql = "INSERT OR REPLACE INTO WIFI_INFO (" +
                "X_SWIFI_MGR_NO, " +
                "X_SWIFI_WRDOFC, " +
                "X_SWIFI_MAIN_NM, " +
                "X_SWIFI_ADRES1, " +
                "X_SWIFI_ADRES2, " +
                "X_SWIFI_INSTL_FLOOR, " +
                "X_SWIFI_INSTL_TY, " +
                "X_SWIFI_INSTL_MBY, " +
                "X_SWIFI_SVC_SE, " +
                "X_SWIFI_CMCWR, " +
                "X_SWIFI_CNSTC_YEAR, " +
                "X_SWIFI_INOUT_DOOR, " +
                "X_SWIFI_REMARS3, " +
                "LAT, " +
                "LNT," +
                " WORK_DTTM" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try (PreparedStatement pStatement = conn.prepareStatement(sql)) {
            pStatement.setString(1, wifiInfo.getXSwifiMgrNo());
            pStatement.setString(2, wifiInfo.getXSwifiWrdofc());
            pStatement.setString(3, wifiInfo.getXSwifiMainNm());
            pStatement.setString(4, wifiInfo.getXSwifiAdres1());
            pStatement.setString(5, wifiInfo.getXSwifiAdres2());
            pStatement.setString(6, wifiInfo.getXSwifiInstlFloor());
            pStatement.setString(7, wifiInfo.getXSwifiInstlTy());
            pStatement.setString(8, wifiInfo.getXSwifiInstlMby());
            pStatement.setString(9, wifiInfo.getXSwifiSvcSe());
            pStatement.setString(10, wifiInfo.getXSwifiCmcwr());
            pStatement.setString(11, wifiInfo.getXSwifiCnstcYear());
            pStatement.setString(12, wifiInfo.getXSwifiInOutDoor());
            pStatement.setString(13, wifiInfo.getXSwifiRemars3());
            pStatement.setDouble(14, wifiInfo.getLat());
            pStatement.setDouble(15, wifiInfo.getLnt());
            pStatement.setString(16, wifiInfo.getWorkDttm());
            pStatement.executeUpdate();
        }
    }


}
