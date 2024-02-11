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

        // wifi 데이터 가져오기
        List<History> historyList = historyService.getHistory();

        request.setAttribute("historyList", historyList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/history/history.jsp");
        dispatcher.forward(request, response);
    }
}
