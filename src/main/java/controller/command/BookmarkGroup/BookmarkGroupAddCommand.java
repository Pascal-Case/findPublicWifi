package controller.command.BookmarkGroup;

import com.google.gson.JsonObject;
import controller.command.Command;
import service.BookmarkGroupService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookmarkGroupAddCommand implements Command {

    private final BookmarkGroupService bookmarkGroupService;

    public BookmarkGroupAddCommand() {
        bookmarkGroupService = new BookmarkGroupService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestMethod = request.getMethod();
        if (requestMethod.equals("GET")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmarkGroup/bookmarkGroup-add.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setCharacterEncoding("UTF-8");
            JsonObject jsonResponse = new JsonObject();
            try {
                String bookmarkGroupName = request.getParameter("bookmarkGroupName");
                int bookmarkGroupOrder = Integer.parseInt(request.getParameter("bookmarkGroupOrder"));

                bookmarkGroupService.addBookmarkGroup(bookmarkGroupName, bookmarkGroupOrder);

                jsonResponse.addProperty("success", true);
                jsonResponse.addProperty("message", "북마크 그룹을 등록했습니다.");
            } catch (RuntimeException e) {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", e.getMessage());
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse.toString());
        }


    }
}
