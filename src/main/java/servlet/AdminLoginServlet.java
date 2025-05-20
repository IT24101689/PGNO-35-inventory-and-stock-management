package servlet;

import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminLoginServlet", value = "/adminLogin")
public class AdminLoginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminLogin.jsp");
        HttpSession session = request.getSession();

        session.invalidate();
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminLogin.jsp");

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (name.isEmpty() || password.isEmpty()) {
            session.setAttribute("status", "failed");
            session.setAttribute("validation", "Username and Password cannot be empty.");
            dispatcher.forward(request, response);
            return;
        }

        boolean isValidUser = false;

        if (name.equals("admin") && password.equals("12345678")) {
            isValidUser = true;
        }

        if (isValidUser) {
            User user = new User();
            user.setName(name);
            user.setPassword(password);

            session.setAttribute("user", user);
            session.setAttribute("role", "admin");
            response.sendRedirect("adminDashboard");
        } else {
            session.setAttribute("status", "failed");
            session.setAttribute("validation", "Invalid username or password.");
            dispatcher.forward(request, response);
        }
    }
}