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

/**
 * Servlet implementation class DeleteStockServlet
 */
@WebServlet("/DeleteStockServlet")
public class DeleteStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStockServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int stockId = Integer.parseInt(request.getParameter("id"));

	        try {
	           
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/portfolio", "root", "Ragu@2002");

	            
	            String query = "DELETE FROM stocks WHERE stock_id = ?";
	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setInt(1, stockId);

	            ps.executeUpdate();
	            ps.close();
	            con.close();

	            
	            response.sendRedirect("ViewStocksServlet");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    
	}

}
