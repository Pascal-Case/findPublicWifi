package controller.command.WifiInfo;

import controller.command.Command;
import model.WifiData;
import service.WifiDataService;
import util.JsonParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SeoulWifiCommand implements Command {
    private final String apiKey;
    private final WifiDataService wifiDataService;

    public SeoulWifiCommand() {
        apiKey = System.getProperty("API_KEY");
        wifiDataService = new WifiDataService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int start = 1;
        int end = 1000;
        final int limit = 1000;
        boolean hasMore = true;
        int totalCount = 0;
        boolean success = true;

        while (hasMore) {
            String apiUrl = "http://openapi.seoul.go.kr:8088/" + apiKey + "/json/TbPublicWifiInfo/" + start + "/" + (start + limit - 1);
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                        String inputLine;
                        StringBuilder responseBuffer = new StringBuilder();

                        while ((inputLine = in.readLine()) != null) {
                            responseBuffer.append(inputLine);
                        }

                        // json 파싱
                        WifiData wifiData = JsonParser.parseWifiData(responseBuffer.toString());
                        // 데이터 갯수
                        int count = wifiData.getTbPublicWifiInfo().getRow().size();

                        totalCount += count;

                        // DB에 저장
                        wifiDataService.saveWifiData(wifiData.getTbPublicWifiInfo().getRow());

                        // 가져온 갯수가 limit 보다 작으면 마지막 쿼리
                        hasMore = count >= limit;
                        start += limit;
                    }
                } else {
                    success = false;
                    throw new RuntimeException("HttpResponseCode: " + responseCode);
                }
            } catch (IOException e) {
                success = false;
                request.setAttribute("success", false);
                request.setAttribute("message", e.getMessage());
                break;
            }
        }

        if (success) {
            request.setAttribute("success", true);
            request.setAttribute("message", "공공 와이파이 정보를 성공적으로 가져왔습니다.");
        }

        request.setAttribute("totalCount", totalCount);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/getWifiData.jsp");
        dispatcher.forward(request, response);

    }
}
