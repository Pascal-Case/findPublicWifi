package controller;

import controller.command.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontControllerServlet", urlPatterns = {"/seoul-wifi/*"})
public class FrontControllerServlet extends HttpServlet {
    private final Map<String, Command> commandMap = new HashMap<>();

    public void init() {
        System.out.println("FRONT CONTROLLER SERVLET !!");
        // 커맨드 매핑
        commandMap.put("/home", new HomeCommand());
        commandMap.put("/history", new HistoryCommand());
        commandMap.put("/bookmark", new BookmarkCommand());
        commandMap.put("/bookmarkGroup", new BookmarkGroupCommand());

        commandMap.put("/getWifiData", new SeoulWifiCommand());
        commandMap.put("/searchWifi", new SearchWifiCommand());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DO GET!");
        //String path = request.getServletPath();
        String path = request.getPathInfo();
        Command command = commandMap.getOrDefault(path, new DefaultCommand());
        System.out.println("path = " + path);
        command.execute(request, response);
    }
}
