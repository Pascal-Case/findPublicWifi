package controller.command.Bookmark;

import com.google.gson.JsonObject;
import controller.command.Command;
import service.BookmarkService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookmarkDeleteCommand implements Command {
    private final BookmarkService bookmarkService;

    public BookmarkDeleteCommand() {
        bookmarkService = new BookmarkService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        int bookmarkId = Integer.parseInt(request.getParameter("bookmarkId"));

        JsonObject jsonResponse = new JsonObject();
        try {
            // 북마크 리스트
            bookmarkService.deleteBookmark(bookmarkId);
            jsonResponse.addProperty("success", true);
            jsonResponse.addProperty("message", "북마크를 성공적으로 삭제했습니다.");

        } catch (Exception e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", e.getMessage());
        }

        response.getWriter().write(jsonResponse.toString());
    }
}
