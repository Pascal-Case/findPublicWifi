package util;

import com.google.gson.Gson;
import model.WifiData;

public class JsonParser {
    private static final Gson gson = new Gson();

    public static Gson getGson() {
        return gson;
    }

    public static WifiData parseWifiData(String json) {
        return getGson().fromJson(json, WifiData.class);
    }
}
