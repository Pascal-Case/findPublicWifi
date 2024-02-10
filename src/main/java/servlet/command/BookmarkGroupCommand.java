package servlet.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookmarkGroupCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // wifi 데이터 가져오기

        request.setAttribute("message", "Hello from bookmarkGroup");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmarkGroup/bookmarkGroup.jsp");
        dispatcher.forward(request, response);
    }
}
