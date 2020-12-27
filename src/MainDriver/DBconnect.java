package MainDriver;

import java.sql.*;

public class DBconnect {

    private final String connectionUrl = "jdbc:sqlserver://localhost:1433;user=project;password=1234;"
            + "databaseName=CourseManagementSystem;";

    public Connection connect() throws SQLException {

        return DriverManager.getConnection(connectionUrl);
    }

}
