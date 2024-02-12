package controller.command.History;

import controller.command.Command;
import model.History;
import service.HistoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HistoryCommand implements Command {

    private final HistoryService historyService;

    public HistoryCommand() {
        this.historyService = new HistoryService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<History> historyList = null;
        try {
            historyList = historyService.getHistory();
            request.setAttribute("success", true);
            request.setAttribute("message", "히스토리 리스트를 성공적으로 가져왔습니다.");
        } catch (RuntimeException e) {
            request.setAttribute("success", false);
            request.setAttribute("message", e.getMessage());
        }

        request.setAttribute("historyList", historyList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/history/history.jsp");
        dispatcher.forward(request, response);
    }
}
