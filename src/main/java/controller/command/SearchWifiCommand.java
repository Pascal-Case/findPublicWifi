package controller.command;

import model.WifiSpot;
import service.WifiDataService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchWifiCommand implements Command {

    private final WifiDataService wifiDataService;

    public SearchWifiCommand() {
        wifiDataService = new WifiDataService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String latStr = request.getParameter("lat");
        String lntStr = request.getParameter("lnt");
        String errorMessage = null;

        double lat = 0;
        double lnt = 0;
        try {
            lat = Double.parseDouble(latStr);
            lnt = Double.parseDouble(lntStr);
        } catch (NumberFormatException e) {
            errorMessage = "위도와 경도값이 유효하지 않습니다.";
        }


        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);
        }

        // wifi 데이터 가져오기
        List<WifiSpot> wifiSpots = wifiDataService.getWifiSpotData(lat, lnt, 20);

        request.setAttribute("wifiSpots", wifiSpots);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
        dispatcher.forward(request, response);
    }
}
