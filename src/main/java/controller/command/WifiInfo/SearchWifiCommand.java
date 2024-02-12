package controller.command.WifiInfo;

import controller.command.Command;
import model.WifiSpot;
import service.HistoryService;
import service.WifiDataService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchWifiCommand implements Command {

    private final WifiDataService wifiDataService;
    private final HistoryService historyService;

    public SearchWifiCommand() {
        wifiDataService = new WifiDataService();
        historyService = new HistoryService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 위치정보 기반 와이파이 스팟 검색
        List<WifiSpot> wifiSpots = new ArrayList<>();
        String latStr = request.getParameter("lat");
        String lntStr = request.getParameter("lnt");

        try {
            // 위도 경도 검증
            double lat = Double.parseDouble(latStr);
            double lnt = Double.parseDouble(lntStr);

            // wifi 데이터 가져오기
            wifiSpots = wifiDataService.getWifiSpotData(lat, lnt, 20);
            // history 저장
            historyService.saveHistory(lat, lnt);
            request.setAttribute("success", true);
            request.setAttribute("message", "와이파이 데이터를 성공적으로 가져왔습니다.");
        } catch (NumberFormatException e) {
            request.setAttribute("success", false);
            request.setAttribute("message", "위도와 경도값이 유효하지 않습니다.");
        } catch (RuntimeException e) {
            request.setAttribute("success", false);
            request.setAttribute("message", e.getMessage());
        }

        request.setAttribute("wifiSpots", wifiSpots);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
        dispatcher.forward(request, response);
    }
}
