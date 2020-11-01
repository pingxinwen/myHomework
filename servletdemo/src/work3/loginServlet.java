package work3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginServlet",urlPatterns = {"/login"})
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = (String)request.getParameter("username");
        String password = (String)request.getParameter("password");
        if(name==null||name.compareTo("user")!=0||password==null||password.compareTo("123456")!=0){
            request.getRequestDispatcher("login.html").forward(request,response);
        }
        else{
            HttpSession session = request.getSession();
            session.setAttribute("login",true);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<p>Access succeed!</p>");
            out.println("<p>Username:"+name+"</p>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
