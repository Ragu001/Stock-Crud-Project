package com.Stock;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/UpdateStockServlet")
public class UpdateStockServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateStockServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the parameters from the request
        int stockId = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        double stockprice = Double.parseDouble(request.getParameter("stockprice"));  // Fixed the name

        try {
            // Load the MySQL driver and establish a connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/portfolio", "root", "Ragu@2002");

            // SQL query to update the stock
            String query = "UPDATE stocks SET quantity = ?, purchase_price = ?, stock_price = ? WHERE stock_id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            // Set the values for the query
            ps.setInt(1, quantity);
            ps.setDouble(2, price);
            ps.setDouble(3, stockprice);  // Ensure price is stored correctly
            ps.setInt(4, stockId);

            // Execute the update
            ps.executeUpdate();

            // Close resources
            ps.close();
            con.close();

            // Redirect to the view stocks page
            response.sendRedirect("ViewStocksServlet");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
