package com.demo.ssdi;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class QueryWithoutContext {

    public static void query(PrintWriter out) throws NamingException {
        MysqlDataSource ds = null;
        Connection connect = null;
        Statement statement = null;

        try {
            // Create a new DataSource (MySQL specifically)
            // and provide the relevant information to be used by Tomcat.
            ds = new MysqlDataSource();
            ds.setUrl("jdbc:mysql://localhost:3306/test");
            ds.setUser("root");
            ds.setPassword("password");
            
            connect = ds.getConnection();

            // Create the statement to be used to get the results.
            statement = connect.createStatement();
            String query = "SELECT * FROM mytable";

            // Execute the query and get the result set.
            ResultSet resultSet = statement.executeQuery(query);
            //out.println("<strong>Printing result using DataSource...</strong><br>");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                //int year = resultSet.getInt("year");

                out.println("<br>Name: " + name + "<br>"+
                        "Address Showed: " + address + 
                        "<br>");
            }
        } catch (SQLException e) { e.printStackTrace(out);
        } finally {
            // Close the connection and release the resources used.
            try { statement.close(); } catch (SQLException e) { e.printStackTrace(out); }
            try { connect.close(); } catch (SQLException e) { e.printStackTrace(out); }
        }
    }
}
