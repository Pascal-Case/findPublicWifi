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
    private final Map<String, Command> getCommandMap = new HashMap<>();
    private final Map<String, Command> postCommandMap = new HashMap<>();

    public void init() {
        System.out.println("FRONT CONTROLLER SERVLET !!");
        // get 커맨드 매핑
        getCommandMap.put("/home", new HomeCommand());
        getCommandMap.put("/history", new HistoryCommand());
        getCommandMap.put("/bookmark", new BookmarkCommand());
        getCommandMap.put("/bookmarkGroup", new BookmarkGroupCommand());
        getCommandMap.put("/getWifiData", new SeoulWifiCommand());
        getCommandMap.put("/searchWifi", new SearchWifiCommand());

        // post 커맨드 매핑
        postCommandMap.put("/history-del", new HistoryDeleteCommand());
        postCommandMap.put("/bookmark-add", new BookmarkCommand());
        postCommandMap.put("/bookmark-del", new BookmarkCommand());
        postCommandMap.put("/bookmark-edit", new BookmarkCommand());
        postCommandMap.put("/bookmarkGroup-add", new BookmarkGroupCommand());
        postCommandMap.put("/bookmarkGroup-del", new BookmarkGroupCommand());
        postCommandMap.put("/bookmarkGroup-edit", new BookmarkGroupCommand());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DO GET!");
        //String path = request.getServletPath();
        String path = request.getPathInfo();
        Command command = getCommandMap.getOrDefault(path, new DefaultCommand());
        System.out.println("path = " + path);
        command.execute(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DO POST!");
        //String path = request.getServletPath();
        String path = request.getPathInfo();
        Command command = postCommandMap.getOrDefault(path, new DefaultCommand());
        System.out.println("path = " + path);
        command.execute(request, response);
    }
}
