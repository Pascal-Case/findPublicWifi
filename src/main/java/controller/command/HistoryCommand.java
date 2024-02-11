package controller.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HistoryCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // wifi 데이터 가져오기

        request.setAttribute("message", "Hello from history!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/history/history.jsp");
        dispatcher.forward(request, response);
    }
}