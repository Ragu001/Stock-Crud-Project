<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Portfolio</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color:#33f3ff;
        }
        h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Your Stock Portfolio</h2>
    <table>
        <tr>
            <th>Stock ID</th>
            <th>Stock Symbol</th>
            <th>Company Name</th>
            <th>Quantity</th>
            <th>Stock Price</th>
            <th>Purchase Price</th>
            <th>Purchase Date</th>
        </tr>
        <%
            // Get the result set from the servlet
            ResultSet rs = (ResultSet) request.getAttribute("stockList");

            try {
                while (rs.next()) {
        %>
                    <tr>
                        <td><%= rs.getInt("stock_id") %></td>
                        <td><%= rs.getString("stock_symbol") %></td>
                        <td><%= rs.getString("company_name") %></td>
                        <td><%= rs.getInt("quantity") %></td>
                        <td><%= rs.getDouble("stock_price") %></td> 
                        <td><%= rs.getDouble("purchase_price") %></td>
                        <td><%= rs.getDate("purchase_date") %></td>
                    </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </table>
</body>
</html>
