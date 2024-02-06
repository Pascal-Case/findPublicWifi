package SeoulWifiServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "seoulWifiServlet", value = "/seoul-wifi")
public class SeoulWifiServlet extends HttpServlet {

    private String apiKey;

    public void init() {
        apiKey = System.getProperty("API_KEY");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String apiUrl = "http://openapi.seoul.go.kr:8088/" + apiKey + "/json/TbPublicWifiInfo/1/5/";
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String inputLine;
            StringBuilder responseBuffer = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                responseBuffer.append(inputLine);
            }
            in.close();

            // JSON 응답을 String 받음
            String responseData = responseBuffer.toString();

            System.out.println(responseData);

            // 요청을 JSP 전달하기 위해 request 객체에 데이터 설정
            request.setAttribute("wifiData", responseData);

            // JSP 페이지로 forward
            RequestDispatcher dispatcher = request.getRequestDispatcher("/showWifi.jsp");
            dispatcher.forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.println("API 요청 실패: " + responseCode);
        }
    }
}
