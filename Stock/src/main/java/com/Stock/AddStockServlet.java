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


@WebServlet("/AddStockServlet")
public class AddStockServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    public AddStockServlet() {
        super();
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data from the request
        String symbol = request.getParameter("symbol");
        String company = request.getParameter("company");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double stockPrice = Double.parseDouble(request.getParameter("stockprice"));
        double purchasePrice = Double.parseDouble(request.getParameter("price"));
        String date = request.getParameter("date");

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/portfolio", "root", "Ragu@2002");

            
            String query = "INSERT INTO stock (stock_symbol, company_name, quantity, stock_price, purchase_price, purchase_date) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            
            ps.setString(1, symbol);
            ps.setString(2, company);
            ps.setInt(3, quantity);
            ps.setDouble(4, stockPrice); 
            ps.setDouble(5, purchasePrice); 
            ps.setString(6, date); 

           
            ps.executeUpdate();

            
            ps.close();
            con.close();

           
            response.sendRedirect("ViewStocksServlet");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
