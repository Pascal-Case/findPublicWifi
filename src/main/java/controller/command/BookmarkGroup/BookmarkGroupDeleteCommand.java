package controller.command.BookmarkGroup;

import com.google.gson.JsonObject;
import controller.command.Command;
import service.BookmarkGroupService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookmarkGroupDeleteCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonResponse = new JsonObject();
        try {
            int bookmarkGroupId = Integer.parseInt(request.getParameter("bookmarkGroupId"));
            BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
            bookmarkGroupService.deleteBookmarkGroup(bookmarkGroupId);

            jsonResponse.addProperty("success", true);
            jsonResponse.addProperty("message", "북마크를 성공적으로 삭제했습니다.");
        } catch (RuntimeException e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", e.getMessage());
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
    }
}
