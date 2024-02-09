package model;

import com.google.gson.annotations.SerializedName;

public class WifiInfo {
    @SerializedName("X_SWIFI_MGR_NO")
    private final String xSwifiMgrNo;
    @SerializedName("X_SWIFI_WRDOFC")
    private final String xSwifiWrdofc;
    @SerializedName("X_SWIFI_MAIN_NM")
    private final String xSwifiMainNm;
    @SerializedName("X_SWIFI_ADRES1")
    private final String xSwifiAdres1;
    @SerializedName("X_SWIFI_ADRES2")
    private final String xSwifiAdres2;
    @SerializedName("X_SWIFI_INSTL_FLOOR")
    private final String xSwifiInstlFloor;
    @SerializedName("X_SWIFI_INSTL_TY")
    private final String xSwifiInstlTy;
    @SerializedName("X_SWIFI_INSTL_MBY")
    private final String xSwifiInstlMby;
    @SerializedName("X_SWIFI_SVC_SE")
    private final String xSwifiSvcSe;
    @SerializedName("X_SWIFI_CMCWR")
    private final String xSwifiCmcwr;
    @SerializedName("X_SWIFI_CNSTC_YEAR")
    private final String xSwifiCnstcYear;
    @SerializedName("X_SWIFI_INOUT_DOOR")
    private final String xSwifiInOutDoor;
    @SerializedName("X_SWIFI_REMARS3")
    private final String xSwifiRemars3;
    @SerializedName("LAT")
    private final double lat;
    @SerializedName("LNT")
    private final double lnt;
    @SerializedName("WORK_DTTM")
    private final String workDttm;

    public WifiInfo(String xSwifiMgrNo, String xSwifiWrdofc, String xSwifiMainNm, String xSwifiAdres1, String xSwifiAdres2, String xSwifiInstlFloor, String xSwifiInstlTy, String xSwifiInstlMby, String xSwifiSvcSe, String xSwifiCmcwr, String xSwifiCnstcYear, String xSwifiInOutDoor, String xSwifiRemars3, double lat, double lnt, String workDttm) {
        this.xSwifiMgrNo = xSwifiMgrNo;
        this.xSwifiWrdofc = xSwifiWrdofc;
        this.xSwifiMainNm = xSwifiMainNm;
        this.xSwifiAdres1 = xSwifiAdres1;
        this.xSwifiAdres2 = xSwifiAdres2;
        this.xSwifiInstlFloor = xSwifiInstlFloor;
        this.xSwifiInstlTy = xSwifiInstlTy;
        this.xSwifiInstlMby = xSwifiInstlMby;
        this.xSwifiSvcSe = xSwifiSvcSe;
        this.xSwifiCmcwr = xSwifiCmcwr;
        this.xSwifiCnstcYear = xSwifiCnstcYear;
        this.xSwifiInOutDoor = xSwifiInOutDoor;
        this.xSwifiRemars3 = xSwifiRemars3;
        this.lat = lat;
        this.lnt = lnt;
        this.workDttm = workDttm;
    }

    public String getXSwifiMgrNo() {
        return xSwifiMgrNo;
    }

    public String getXSwifiWrdofc() {
        return xSwifiWrdofc;
    }

    public String getXSwifiMainNm() {
        return xSwifiMainNm;
    }

    public String getXSwifiAdres1() {
        return xSwifiAdres1;
    }

    public String getXSwifiAdres2() {
        return xSwifiAdres2;
    }

    public String getXSwifiInstlFloor() {
        return xSwifiInstlFloor;
    }

    public String getXSwifiInstlTy() {
        return xSwifiInstlTy;
    }

    public String getXSwifiInstlMby() {
        return xSwifiInstlMby;
    }

    public String getXSwifiSvcSe() {
        return xSwifiSvcSe;
    }

    public String getXSwifiCmcwr() {
        return xSwifiCmcwr;
    }

    public String getXSwifiCnstcYear() {
        return xSwifiCnstcYear;
    }

    public String getXSwifiInOutDoor() {
        return xSwifiInOutDoor;
    }

    public String getXSwifiRemars3() {
        return xSwifiRemars3;
    }

    public double getLat() {
        return lat;
    }

    public double getLnt() {
        return lnt;
    }

    public String getWorkDttm() {
        return workDttm;
    }
}
