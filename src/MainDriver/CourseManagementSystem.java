package MainDriver;

import AdminModule.*;
import CourseModule.*;
import InstructorModule.*;
import StudentModule.*;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;

public class CourseManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        GetAccess g = new GetAccess();
//        g.login("Omnia Sayed", "admin");
//        System.out.println(g.returnSSN());
//        System.out.println(g.returnRole());
        ManageData m = new ManageData();
        m.deleteInstructor("30172871911784");
    }

}
