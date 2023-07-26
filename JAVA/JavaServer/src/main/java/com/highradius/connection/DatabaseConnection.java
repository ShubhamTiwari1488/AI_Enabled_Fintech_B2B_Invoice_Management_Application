package com.highradius.connection;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.highradius.model.Invoice;

public class DatabaseConnection {
    List<Invoice> invoices = new ArrayList<>();

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public static Connection connect() {
    	Connection connection = null;
        String url = "jdbc:mysql://127.0.0.1:3300/h2h_2023";
        String username = "root";
        String password = "kiit";

        // Database Query
        String query = "SELECT * FROM h2hsql.h2h_oap";

        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish a connection to the database
            con = DriverManager.getConnection(url, username, password);

            // Step 3: Create a statement object
            statement = con.createStatement();

            // Step 4: Execute Query
            resultSet = statement.executeQuery(query);

            // Step 5: Process the result set
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            String dataType = null;
        } catch (Exception e) {
        	System.out.println("Connetion Not successful");
            e.printStackTrace();
        }
        
        return con;
    }
}


