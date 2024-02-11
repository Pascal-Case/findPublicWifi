package controller.command.WifiInfo;

import com.example.findpublicwifi.Api_demo;
import controller.command.Command;
import model.WifiData;
import service.WifiDataService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
//        while (hasMore) {
//            String apiUrl = "http://openapi.seoul.go.kr:8088/" + apiKey + "/json/TbPublicWifiInfo/" + start + "/" + end;
//            URL url = new URL(apiUrl);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            int responseCode = conn.getResponseCode();
//            if (responseCode != 200) {
//                throw new RuntimeException("HttpResponseCode: " + responseCode);
//            } else {
//                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
//                String inputLine;
//                StringBuilder responseBuffer = new StringBuilder();
//
//                while ((inputLine = in.readLine()) != null) {
//                    responseBuffer.append(inputLine);
//                }
//                in.close();
//
//                // json 파싱
//                WifiData wifiData = JsonParser.parseWifiData(responseBuffer.toString());
//                // 데이터 갯수
//                int listTotalCount = wifiData.getTbPublicWifiInfo().getTotalListCount();
//                // DB에 저장
//                wifiDataService.saveWifiData(wifiData.getTbPublicWifiInfo().getRow());
//                // 다음 인덱스
//                start += limit;
//                end += limit;
//                // 가져온 갯수가 limit 보다 작으면 마지막 쿼리
//                if (listTotalCount < limit) {
//                    hasMore = false;
//                }
//            }
//        }

        int totalCount = (int) (Math.random() * 10001) + 1;

//        while (hasMore) {
//
//
//            start += limit;
//            end += limit;
//        }

        String responseData = Api_demo.getJson(totalCount);

        WifiData wifiData = util.JsonParser.parseWifiData(responseData);

        wifiDataService.saveWifiData(wifiData.getTbPublicWifiInfo().getRow());

        // 요청을 JSP 전달하기 위해 request 객체에 데이터 설정
        request.setAttribute("wifiData", wifiData);

        // JSP 페이지로 forward
        RequestDispatcher dispatcher = request.getRequestDispatcher("/getWifiData.jsp");
        dispatcher.forward(request, response);


//        String apiUrl = "https://api.odcloud.kr/api/15074279/v1/uddi:a019eab8-7146-4443-9c79-b0c9816e4a77?page=1&perPage=10&serviceKey=H9aeTjSqNe%2F4XWnKCPLlFVfiT4KnjC59fcFdZw21inyTgjq68tBT4Lso4MWHuV1ZxJC9XZYyb%2FCyvhTE1F1TvA%3D%3D";
//        URL url = new URL(apiUrl);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        int responseCode = conn.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
//            String inputLine;
//            StringBuilder responseBuffer = new StringBuilder();
//
//            while ((inputLine = in.readLine()) != null) {
//                responseBuffer.append(inputLine);
//            }
//            in.close();
//
//            // JSON 응답을 String 받음
//            //String responseData = responseBuffer.toString();
//
//            String responseData = Api_demo.getJson();
//
//            WifiData wifiData = util.JsonParser.parseWifiData(responseData);
//
//            wifiDataService.saveWifiData(wifiData.getTbPublicWifiInfo().getRow());
//
//
//            // 요청을 JSP 전달하기 위해 request 객체에 데이터 설정
//            request.setAttribute("wifiData", wifiData);
//
//            // JSP 페이지로 forward
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/getWifiData.jsp");
//            dispatcher.forward(request, response);
//        } else {
//            PrintWriter out = response.getWriter();
//            out.println("API 요청 실패: " + responseCode);
//        }
    }
}
