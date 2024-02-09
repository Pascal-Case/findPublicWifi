package model;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class WifiData {
    @SerializedName("TbPublicWifiInfo")
    private final TbPublicWifiInfo tbPublicWifiInfo;

    public WifiData(TbPublicWifiInfo tbPublicWifiInfo) {
        this.tbPublicWifiInfo = tbPublicWifiInfo;
    }


    public TbPublicWifiInfo getTbPublicWifiInfo() {
        return tbPublicWifiInfo;
    }

    static class Result {
        @SerializedName("CODE")
        private final String code;
        @SerializedName("MESSAGE")
        private final String message;

        public Result(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class TbPublicWifiInfo {
        @SerializedName("list_total_count")
        private final int totalListCount;
        @SerializedName("RESULT")
        private final Result result;
        @SerializedName("row")
        private final List<WifiInfo> row;

        public TbPublicWifiInfo(int totalListCount, Result result, List<WifiInfo> row) {
            this.totalListCount = totalListCount;
            this.result = result;
            this.row = row == null ? Collections.emptyList() : row;
        }

        public int getTotalListCount() {
            return totalListCount;
        }

        public Result getResult() {
            return result;
        }

        public List<WifiInfo> getRow() {
            return row;
        }
    }
}
