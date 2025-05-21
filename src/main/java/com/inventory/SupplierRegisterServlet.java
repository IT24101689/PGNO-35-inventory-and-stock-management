package com.inventory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet("/SupplierRegisterServlet")
public class SupplierRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String companyName = request.getParameter("company-name");
        String username = request.getParameter("supplier-username");
        String password = request.getParameter("supplier-password");
        String confirmPassword = request.getParameter("confirm-supplier-password");
        String itemCategory = request.getParameter("item-category");

        if (!password.equals(confirmPassword)) {
            // Redirect back with error in URL query string
            response.sendRedirect("supplierRegister.jsp?error=Passwords do not match!");
            return;
        }

        String filePath = "C:\\Users\\USER\\Desktop\\inventory\\Supplier_Management\\src\\main\\webapp\\SupplierLoginCredentials.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write("name:" + username + "\n");
            bw.write("password:" + password + "\n");
            bw.write("companyName:" + companyName + "\n");
            bw.write("category:" + itemCategory + "\n");
            bw.write("------------------------------------------------------\n\n");

            response.sendRedirect("supplierLogin.jsp?message=Registration successful. Please log in.");
        } catch (IOException e) {
            e.printStackTrace();
            response.getWriter().println("Server error: " + e.getMessage());
        }
    }
}
