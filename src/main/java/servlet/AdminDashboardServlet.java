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
import java.util.ArrayList;

@WebServlet(name = "AdminDashboardServlet", value = "/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminDashboard.jsp");
        HttpSession session = request.getSession();

        BuyerService buyerService = new BuyerService();
        SupplierService supplierService = new SupplierService();

        ArrayList<User> buyers = buyerService.getAllUsers();
        ArrayList<User> suppliers = supplierService.getAllUsers();

        request.setAttribute("buyers", buyers);
        request.setAttribute("suppliers", suppliers);
        dispatcher.forward(request, response);
    }

}