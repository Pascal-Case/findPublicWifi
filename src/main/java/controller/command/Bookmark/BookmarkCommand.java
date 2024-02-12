package controller.command.Bookmark;

import controller.command.Command;
import model.BookMark;
import service.BookmarkService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookmarkCommand implements Command {

    private final BookmarkService bookmarkService;

    public BookmarkCommand() {
        bookmarkService = new BookmarkService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookMark> bookmarkList = null;
        try {
            // 북마크 리스트
            bookmarkList = bookmarkService.getBookmarkList();
            request.setAttribute("success", true);

            request.setAttribute("message", "북마크를 성공적으로 가져왔습니다.");
        } catch (RuntimeException e) {
            request.setAttribute("success", false);
            request.setAttribute("message", e.getMessage());
        }
        request.setAttribute("bookmarkList", bookmarkList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark/bookmark.jsp");
        dispatcher.forward(request, response);
    }
}
