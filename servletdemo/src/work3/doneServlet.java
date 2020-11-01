package work3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "doneServlet",urlPatterns = {"/done"})
public class doneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if(request.getAttribute("addInfo")==null){
            out.println("No info!");
        }
        else{
            addInfo info = (addInfo) request.getAttribute("addInfo");
            int sum = info.getSum();
            out.println("<p>a is "+info.getA()+"</p>");
            out.println("<p>b is "+info.getB()+"</p>");
            out.println("<p>sum of a and b is "+sum+"!</p>");
        }

    }
}
