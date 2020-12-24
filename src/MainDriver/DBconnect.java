/*Source codes of DBconnect.java*/
package MainDriver;

import java.sql.*;

public class DBconnect {

    public static Connection connect() throws ClassNotFoundException {
        Connection con = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=CourseManagementSystem;user=admin;password=admin";
        try {
            con = DriverManager.getConnection(connectionURL);
            //System.out.println("Connection stablished successfully.");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return con;
    }
}
