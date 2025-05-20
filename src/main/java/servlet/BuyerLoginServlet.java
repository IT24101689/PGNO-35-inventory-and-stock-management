package servlet;

import model.User;
import services.BuyerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "BuyerLoginServlet", value = "/buyerLogin")
public class BuyerLoginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("buyerLogin.jsp");
        HttpSession session = request.getSession();
        session.invalidate();
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = request.getRequestDispatcher("buyerLogin.jsp");

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (name.isEmpty() || password.isEmpty()) {
            session.setAttribute("status", "failed");
            session.setAttribute("validation", "Username and Password cannot be empty.");
            dispatcher.forward(request, response);
            return;
        }

        BuyerService userService = new BuyerService();
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        boolean isValidUser = userService.loginUser(user);

        if (isValidUser) {
            user = userService.getUserByName(name);
            session.setAttribute("user", user);
            session.setAttribute("role", "buyer");
            response.sendRedirect("buyerDashboard.jsp");
        } else {
            session.setAttribute("status", "failed");
            session.setAttribute("validation", "Invalid username or password.");
            dispatcher.forward(request, response);
        }
    }
}