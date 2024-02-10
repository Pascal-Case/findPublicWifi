package service;

import dao.WifiInfoDao;
import model.WifiInfo;
import model.WifiSpot;
import util.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class WifiDataService {

    /**
     * 와이파이 데이터 저장
     *
     * @param wifiInfoList wifi 데이터
     */
    public void saveWifiData(List<WifiInfo> wifiInfoList) {
        try (Connection conn = DbUtil.getConnection()) {
            WifiInfoDao wifiInfoDao = new WifiInfoDao();
            conn.setAutoCommit(false);

            for (WifiInfo wifiInfo : wifiInfoList) {
                wifiInfoDao.upsertWifiInfo(conn, wifiInfo);
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 사용자의 위치를 기반으로 가까운 와이파이 스팟을 가져옴
     *
     * @param userLat 위도
     * @param userLnt 경도
     * @param limit   가져올 와이파이 스팟의 최대 개수
     * @return 가까운 와이파이 스팟 리스트
     */
    public List<WifiSpot> getWifiSpotData(double userLat, double userLnt, int limit) {
        List<WifiSpot> wifiSpots = null;

        try (Connection conn = DbUtil.getConnection()) {
            WifiInfoDao wifiInfoDao = new WifiInfoDao();
            wifiSpots = wifiInfoDao.getWifiSpotList(conn, userLat, userLnt, limit);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wifiSpots;
    }
}
