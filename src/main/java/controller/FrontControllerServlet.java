package controller;

import controller.command.Bookmark.BookmarkAddCommand;
import controller.command.Bookmark.BookmarkCommand;
import controller.command.Bookmark.BookmarkDeleteCommand;
import controller.command.Bookmark.BookmarkDetailCommand;
import controller.command.BookmarkGroup.BookmarkGroupAddCommand;
import controller.command.BookmarkGroup.BookmarkGroupCommand;
import controller.command.BookmarkGroup.BookmarkGroupDeleteCommand;
import controller.command.BookmarkGroup.BookmarkGroupEditCommand;
import controller.command.Command;
import controller.command.DefaultCommand;
import controller.command.History.HistoryCommand;
import controller.command.History.HistoryDeleteCommand;
import controller.command.HomeCommand;
import controller.command.WifiInfo.SearchWifiCommand;
import controller.command.WifiInfo.SeoulWifiCommand;
import controller.command.WifiInfo.WifiDetailCommand;

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
        /* get 커맨드 매핑 */
        // 홈
        getCommandMap.put("/home", new HomeCommand());
        // 히스토리 페이지
        getCommandMap.put("/history", new HistoryCommand());
        // 북마크 페이지
        getCommandMap.put("/bookmark", new BookmarkCommand());
        // 북마크 삭제 페이지
        getCommandMap.put("/bookmark-del", new BookmarkDetailCommand());
        // 북마크 그룹 리스트 페이지
        getCommandMap.put("/bookmarkGroup", new BookmarkGroupCommand());
        // 북마크 그룹 추가 페이지
        getCommandMap.put("/bookmarkGroup-add", new BookmarkGroupAddCommand());
        // 북마크 그룹 수정 페이지
        getCommandMap.put("/bookmarkGroup-edit", new BookmarkGroupEditCommand());
        // open-api 데이터 가져오기
        getCommandMap.put("/getWifiData", new SeoulWifiCommand());
        // 북마크 검색
        getCommandMap.put("/searchWifi", new SearchWifiCommand());
        // 와이파이 상세 페이지
        getCommandMap.put("/wifiDetail", new WifiDetailCommand());

        /* post 커맨드 매핑 */
        // 히스토리 삭제
        postCommandMap.put("/history-del", new HistoryDeleteCommand());
        // 북마크 추가
        postCommandMap.put("/bookmark-add", new BookmarkAddCommand());
        // 북마크 삭제
        postCommandMap.put("/bookmark-del", new BookmarkDeleteCommand());
        // 북마크 그룹 추가
        postCommandMap.put("/bookmarkGroup-add", new BookmarkGroupAddCommand());
        // 북마크 그룹 수정
        postCommandMap.put("/bookmarkGroup-del", new BookmarkGroupDeleteCommand());
        // 북마크 그룹 삭제
        postCommandMap.put("/bookmarkGroup-edit", new BookmarkGroupEditCommand());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getPathInfo();
        Command command = getCommandMap.getOrDefault(path, new DefaultCommand());

        command.execute(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getPathInfo();
        Command command = postCommandMap.getOrDefault(path, new DefaultCommand());
        
        command.execute(request, response);
    }
}
