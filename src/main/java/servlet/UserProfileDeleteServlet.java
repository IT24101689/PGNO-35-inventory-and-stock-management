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

@WebServlet(name = "UserProfileDeleteServlet", value = "/deleteProfile")
public class UserProfileDeleteServlet extends HttpServlet {

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


            if (name.isEmpty() || name.length() > 45) {
                session.setAttribute("status", "failed");
                session.setAttribute("validation", "Name must be between 1 and 45 characters.");

                response.sendRedirect("profile?userRole=supplier&name=" + name);
                return;
            }

            SupplierService userService = new SupplierService();

            user.setName(name);

            boolean isDeleted = userService.deleteUser(user);

            if (isDeleted) {
                session.setAttribute("status", "success");
                session.setAttribute("validation", "Profile deleted successfully.");
            } else {
                session.setAttribute("status", "failed");
                session.setAttribute("validation", "Failed to delete profile.");
            }

            if ("admin".equals(role)) {
                response.sendRedirect("adminDashboard");
            } else if ("supplier".equals(role)) {
                response.sendRedirect("supplierLogin.jsp");
            } else if ("buyer".equals(role)) {
                response.sendRedirect("buyerLogin.jsp");
            } else {
                response.sendRedirect("index.jsp"); // Redirect to home if role is not recognized
            }

        } else if (userRole.equals("buyer")) {

            String name = request.getParameter("name");

            if (name.isEmpty() || name.length() > 45) {
                session.setAttribute("status", "failed");
                session.setAttribute("validation", "Name must be between 1 and 45 characters.");

                response.sendRedirect("profile?userRole=buyer&name=" + name);
                return;
            }


            BuyerService userService = new BuyerService();

            user.setName(name);

            boolean isDeleted = userService.deleteUser(user);

            if (isDeleted) {
                session.setAttribute("status", "success");
                session.setAttribute("validation", "Profile deleted successfully.");
            } else {
                session.setAttribute("status", "failed");
                session.setAttribute("validation", "Failed to delete profile.");
            }

            if ("admin".equals(role)) {
                response.sendRedirect("adminDashboard");
            } else if ("supplier".equals(role)) {
                response.sendRedirect("supplierLogin.jsp");
            } else if ("buyer".equals(role)) {
                response.sendRedirect("buyerLogin.jsp");
            } else {
                response.sendRedirect("index.jsp"); // Redirect to home if role is not recognized
            }
        }
    }
}