package controller.command;

import com.google.gson.JsonObject;
import service.HistoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HistoryDeleteCommand implements Command {

    private final HistoryService historyService;

    public HistoryDeleteCommand() {
        this.historyService = new HistoryService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonResponse = new JsonObject();
        try {
            int historyId = Integer.parseInt(request.getParameter("historyId"));
            historyService.deleteHistory(historyId);
            jsonResponse.addProperty("success", true);
            jsonResponse.addProperty("message", "히스토리가 성공적으로 삭제되었습니다.");
        } catch (RuntimeException e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", e.getMessage());
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
    }
}
