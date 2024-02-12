package controller.command.Bookmark;

import com.google.gson.JsonObject;
import controller.command.Command;
import service.BookmarkService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookmarkAddCommand implements Command {
    private final BookmarkService bookmarkService;

    public BookmarkAddCommand() {
        bookmarkService = new BookmarkService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String bookmarkGroupId = request.getParameter("bookmarkGroupId");
        String wifiMgrNo = request.getParameter("wifiMgrNo");

        System.out.println(wifiMgrNo);

        JsonObject jsonResponse = new JsonObject();
        try {
            bookmarkService.addBookmark(wifiMgrNo, Integer.parseInt(bookmarkGroupId));
            jsonResponse.addProperty("success", true);
            jsonResponse.addProperty("message", "북마크가 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", e.getMessage());
        }

        response.getWriter().write(jsonResponse.toString());
    }
}
