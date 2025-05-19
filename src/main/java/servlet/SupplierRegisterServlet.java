package servlet;

import model.User;
import services.SupplierService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SupplierRegisterServlet", value = "/supplierRegister")
public class SupplierRegisterServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("supplierRegister.jsp");

        HttpSession session = request.getSession();

        session.invalidate();

        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = request.getRequestDispatcher("supplierRegister.jsp");

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String companyName = request.getParameter("companyName");
        String category = request.getParameter("category");

        if (name.isEmpty() || name.length() > 45) {
            session.setAttribute("status", "failed");
            session.setAttribute("validation", "Name must be between 1 and 45 characters.");
            dispatcher.forward(request, response);
            return;
        }

        if (password.isEmpty() || password.length() > 45) {
            session.setAttribute("status", "failed");
            session.setAttribute("validation", "Password must be between 1 and 45 characters.");
            dispatcher.forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            session.setAttribute("status", "failed");
            session.setAttribute("validation", "Password and Confirm Password do not match.");
            dispatcher.forward(request, response);
            return;
        }

        if (companyName.isEmpty() || companyName.length() > 100) {
            session.setAttribute("status", "failed");
            session.setAttribute("validation", "Company name must be between 1 and 100 characters.");
            dispatcher.forward(request, response);
            return;
        }

        if (category.isEmpty() || category.length() > 50) {
            session.setAttribute("status", "failed");
            session.setAttribute("validation", "Category must be between 1 and 50 characters.");
            dispatcher.forward(request, response);
            return;
        }

        SupplierService userService = new SupplierService();
        if (userService.validateName(name)) {
            session.setAttribute("status", "failed");
            session.setAttribute("validation", "Username already exists.");
            dispatcher.forward(request, response);
            return;
        }

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setCompanyName(companyName);
        user.setCategory(category);

        boolean isRegistered = userService.createUser(user);

        if (isRegistered) {
            session.setAttribute("status", "success");
            session.setAttribute("validation", "Registration successful. Please log in.");
            response.sendRedirect("supplierLogin.jsp");
        } else {
            session.setAttribute("status", "failed");
            session.setAttribute("validation", "Registration failed. Please try again.");
            dispatcher.forward(request, response);
        }
    }
}