package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;


public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        if(request.getParameter("logout") == "") {

            HttpSession session = request.getSession();
            session.invalidate();

            request.setAttribute("loggedOut", "");
            request.setAttribute("message", "You have successfully logged out.");
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {

            getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        } 
        else {

            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }

         return;
    } // end of doGet


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        session.setAttribute("username", username);
        session.setAttribute("password", password);


        
        if (username == null  username.equals("")  
                password == null || password.equals("") ) {

            session.setAttribute("invalid", "");
            session.setAttribute("message", "Missing login entry(s).");

            response.sendRedirect("login");
            return;
        } 


     
        User validateUser = new AccountService().login(username, password);

        if (validateUser == null) {

            session.setAttribute("invalid", "");
            session.setAttribute("message", "Incorrect login details.");

            response.sendRedirect("login");
            return;
        } 
        else {

            session.removeAttribute("username");
            session.removeAttribute("password");

            session.setAttribute("user", validateUser.getUsername());
            response.sendRedirect("home");
            return;
        }
    } 
}