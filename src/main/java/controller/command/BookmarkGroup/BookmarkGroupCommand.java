package controller.command.BookmarkGroup;

import controller.command.Command;
import model.BookmarkGroup;
import service.BookmarkGroupService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookmarkGroupCommand implements Command {
    private final BookmarkGroupService bookmarkGroupService;

    public BookmarkGroupCommand() {
        this.bookmarkGroupService = new BookmarkGroupService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // wifi 데이터 가져오기
        List<BookmarkGroup> bookmarkGroupList = null;

        try {
            bookmarkGroupList = bookmarkGroupService.getBookmarkGroupList();
            request.setAttribute("success", true);
            request.setAttribute("message", "북마크 그룹 리스트를 성공적으로 가져왔습니다.");
        } catch (RuntimeException e) {
            request.setAttribute("success", false);
            request.setAttribute("message", e.getMessage());
        }
        
        request.setAttribute("bookmarkGroupList", bookmarkGroupList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmarkGroup/bookmarkGroup.jsp");
        dispatcher.forward(request, response);
    }
}
