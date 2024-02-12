package controller.command.Bookmark;

import controller.command.Command;
import model.BookMark;
import service.BookmarkService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookmarkDetailCommand implements Command {

    private final BookmarkService bookmarkService;

    public BookmarkDetailCommand() {
        bookmarkService = new BookmarkService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookmarkId = Integer.parseInt(request.getParameter("bookmarkId"));
        BookMark bookMark = null;
        try {
            // 북마크 리스트
            bookMark = bookmarkService.getBookmarkDetail(bookmarkId);
            request.setAttribute("success", true);
            request.setAttribute("message", "북마크 상세정보를 성공적으로 가져왔습니다.");
        } catch (RuntimeException e) {
            request.setAttribute("success", false);
            request.setAttribute("message", e.getMessage());
        }
        request.setAttribute("bookMark", bookMark);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark/bookmark-del.jsp");
        dispatcher.forward(request, response);
    }
}
