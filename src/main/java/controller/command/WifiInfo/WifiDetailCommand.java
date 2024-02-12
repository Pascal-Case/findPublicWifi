package controller.command.WifiInfo;

import controller.command.Command;
import model.BookmarkGroup;
import model.WifiInfo;
import service.BookmarkGroupService;
import service.WifiDataService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WifiDetailCommand implements Command {
    private final WifiDataService wifiDataService;
    public final BookmarkGroupService bookmarkGroupService;

    public WifiDetailCommand() {
        wifiDataService = new WifiDataService();
        bookmarkGroupService = new BookmarkGroupService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mgrNo = request.getParameter("mgrNo");
        WifiInfo wifiDetail = null;
        List<BookmarkGroup> bookmarkGroupList = null;
        try {
            // wifi 데이터 가져오기
            wifiDetail = wifiDataService.getWifiDetail(mgrNo);

            // 북마크 그룹 데이터
            bookmarkGroupList = bookmarkGroupService.getBookmarkGroupList();

            request.setAttribute("success", true);
            request.setAttribute("message", "데이터를 성공적으로 가져왔습니다.");
        } catch (RuntimeException e) {
            request.setAttribute("success", false);
            request.setAttribute("message", e.getMessage());
        }

        request.setAttribute("wifiDetail", wifiDetail);
        request.setAttribute("bookmarkGroupList", bookmarkGroupList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/wifiDetail.jsp");
        dispatcher.forward(request, response);
    }
}
