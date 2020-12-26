package StudentModule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conn {
      private String uri="jdbc:sqlserver://localhost:1433;databaseName=CourseManagementSystem;user=Omnia;password=omnia";
    public Connection connect() throws SQLException{
      
        Connection r=DriverManager.getConnection(uri);
        return r;
    
}
}
