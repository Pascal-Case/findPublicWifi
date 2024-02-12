package controller.command.BookmarkGroup;

import com.google.gson.JsonObject;
import controller.command.Command;
import model.BookmarkGroup;
import service.BookmarkGroupService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookmarkGroupEditCommand implements Command {
    private final BookmarkGroupService bookmarkGroupService;

    public BookmarkGroupEditCommand() {
        this.bookmarkGroupService = new BookmarkGroupService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestMethod = request.getMethod();

        if (requestMethod.equals("GET")) {
            BookmarkGroup bookmarkGroup = null;
            String bookmarkGroupIdStr = request.getParameter("bookmarkGroupId");
            try {
                int bookmarkGroupId = Integer.parseInt(bookmarkGroupIdStr);

                bookmarkGroup = bookmarkGroupService.getBookmarkGroupDetail(bookmarkGroupId);

                request.setAttribute("success", true);
                request.setAttribute("message", "북마크 그룹을 가져왔습니다.");
            } catch (NumberFormatException e) {
                request.setAttribute("success", false);
                request.setAttribute("message", "유효한 입력값이 아닙니다.");
            } catch (RuntimeException e) {
                request.setAttribute("success", false);
                request.setAttribute("message", e.getMessage());
            }

            request.setAttribute("bookmarkGroup", bookmarkGroup);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmarkGroup/bookmarkGroup-edit.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setCharacterEncoding("UTF-8");
            JsonObject jsonResponse = new JsonObject();
            try {
                int bookmarkGroupId = Integer.parseInt(request.getParameter("bookmarkGroupId"));
                String bookmarkGroupName = request.getParameter("bookmarkGroupName");
                int bookmarkGroupOrder = Integer.parseInt(request.getParameter("bookmarkGroupOrder"));
                String postDate = request.getParameter("postDate");

                bookmarkGroupService.updateBookmarkGroup(bookmarkGroupId, bookmarkGroupName, bookmarkGroupOrder, postDate);

                jsonResponse.addProperty("success", true);
                jsonResponse.addProperty("message", "북마크 그룹을 수정했습니다.");
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
