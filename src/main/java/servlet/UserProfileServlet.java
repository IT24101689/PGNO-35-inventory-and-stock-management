package servlet;

import model.User;
import services.BuyerService;
import services.SupplierService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserProfileServlet", value = "/profile")
public class UserProfileServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String role = (String) session.getAttribute("role");

        if (request.getParameter("name") == null || request.getParameter("name").isEmpty()) {
            session.setAttribute("status", "failed");
            session.setAttribute("validation", "Please provide a valid name.");

            if ("admin".equals(role)) {
                response.sendRedirect("adminDashboard");
            } else if ("supplier".equals(role)) {
                response.sendRedirect("supplierLogin.jsp");
            } else if ("buyer".equals(role)) {
                response.sendRedirect("buyerLogin.jsp");
            } else {
                response.sendRedirect("index.jsp"); // Redirect to home if role is not recognized
            }
            return;
        }

        if (request.getParameter("userRole") == null || request.getParameter("userRole").isEmpty()) {

            session.setAttribute("status", "failed");
            session.setAttribute("validation", "Please provide a valid user role.");

            if ("admin".equals(role)) {
                response.sendRedirect("adminDashboard");
            } else if ("supplier".equals(role)) {
                response.sendRedirect("supplierLogin.jsp");
            } else if ("buyer".equals(role)) {
                response.sendRedirect("buyerLogin.jsp");
            } else {
                response.sendRedirect("index.jsp"); // Redirect to home if role is not recognized
            }
            return;
        }

        String userRole = request.getParameter("userRole");

        User user = new User();

        if (userRole.equals("supplier")) {
            SupplierService supplierService = new SupplierService();

            user = supplierService.getUserByName(request.getParameter("name"));

            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("supplierProfile.jsp");
            dispatcher.forward(request, response);
        } else if (userRole.equals("buyer")) {
            BuyerService buyerService = new BuyerService();

            user = buyerService.getUserByName(request.getParameter("name"));

            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("buyerProfile.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String role = (String) session.getAttribute("role");

        if (request.getParameter("name") == null || request.getParameter("name").isEmpty()) {
            session.setAttribute("status", "failed");
            session.setAttribute("validation", "Please provide a valid name.");

            if ("admin".equals(role)) {
                response.sendRedirect("adminDashboard");
            } else if ("supplier".equals(role)) {
                response.sendRedirect("supplierLogin.jsp");
            } else if ("buyer".equals(role)) {
                response.sendRedirect("buyerLogin.jsp");
            } else {
                response.sendRedirect("index.jsp"); // Redirect to home if role is not recognized
            }
            return;
        }

        if (request.getParameter("userRole") == null || request.getParameter("userRole").isEmpty()) {

            session.setAttribute("status", "failed");
            session.setAttribute("validation", "Please provide a valid user role.");

            if ("admin".equals(role)) {
                response.sendRedirect("adminDashboard");
            } else if ("supplier".equals(role)) {
                response.sendRedirect("supplierLogin.jsp");
            } else if ("buyer".equals(role)) {
                response.sendRedirect("buyerLogin.jsp");
            } else {
                response.sendRedirect("index.jsp"); // Redirect to home if role is not recognized
            }
            return;
        }

        String userRole = request.getParameter("userRole");

        User user = new User();

        if (userRole.equals("supplier")) {

            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String companyName = request.getParameter("companyName");
            String category = request.getParameter("category");

            if (name.isEmpty() || name.length() > 45) {
                session.setAttribute("status", "failed");
                session.setAttribute("validation", "Name must be between 1 and 45 characters.");

                response.sendRedirect("profile?userRole=supplier&name=" + name);
                return;
            }

            if (password.isEmpty() || password.length() > 45) {
                session.setAttribute("status", "failed");
                session.setAttribute("validation", "Password must be between 1 and 45 characters.");

                response.sendRedirect("profile?userRole=supplier&name=" + name);
                return;
            }

            if (companyName.isEmpty() || companyName.length() > 100) {
                session.setAttribute("status", "failed");
                session.setAttribute("validation", "Company name must be between 1 and 100 characters.");

                response.sendRedirect("profile?userRole=supplier&name=" + name);
                return;
            }

            if (category.isEmpty() || category.length() > 50) {
                session.setAttribute("status", "failed");
                session.setAttribute("validation", "Category must be between 1 and 50 characters.");

                response.sendRedirect("profile?userRole=supplier&name=" + name);
                return;
            }

            SupplierService userService = new SupplierService();

            user.setName(name);
            user.setPassword(password);
            user.setCompanyName(companyName);
            user.setCategory(category);

            boolean isUpdated = userService.updateUser(user);

            if (isUpdated) {
                session.setAttribute("status", "success");
                session.setAttribute("validation", "Profile updated successfully.");
            } else {
                session.setAttribute("status", "failed");
                session.setAttribute("validation", "Failed to update profile.");
            }

            response.sendRedirect("profile?userRole=supplier&name=" + name);
            return;
        } else if (userRole.equals("buyer")) {

            String name = request.getParameter("name");
            String password = request.getParameter("password");

            if (name.isEmpty() || name.length() > 45) {
                session.setAttribute("status", "failed");
                session.setAttribute("validation", "Name must be between 1 and 45 characters.");

                response.sendRedirect("profile?userRole=buyer&name=" + name);
                return;
            }

            if (password.isEmpty() || password.length() > 45) {
                session.setAttribute("status", "failed");
                session.setAttribute("validation", "Password must be between 1 and 45 characters.");

                response.sendRedirect("profile?userRole=buyer&name=" + name);
                return;
            }


            BuyerService userService = new BuyerService();

            user.setName(name);
            user.setPassword(password);

            boolean isUpdated = userService.updateUser(user);

            if (isUpdated) {
                session.setAttribute("status", "success");
                session.setAttribute("validation", "Profile updated successfully.");
            } else {
                session.setAttribute("status", "failed");
                session.setAttribute("validation", "Failed to update profile.");
            }

            response.sendRedirect("profile?userRole=buyer&name=" + name);
            return;
        }
    }
}