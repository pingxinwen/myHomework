package work3;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addServlet",urlPatterns = {"/add"})
public class addServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int a = Integer.parseInt(request.getParameter("a"));
            int b = Integer.parseInt(request.getParameter("b"));
            addInfo info = new addInfo(a,b);
            request.setAttribute("addInfo",info);
            RequestDispatcher rd = request.getRequestDispatcher("/done");
            rd.forward(request,response);
        }
        catch (NumberFormatException n){
            PrintWriter writer = response.getWriter();
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<h1>Wrong numeral!</h1>");
            writer.println("</body>");
            writer.println("</html>");
        }

    }
}
