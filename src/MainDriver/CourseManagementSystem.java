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
    public static String loginSSN;
    public static String loginRole;
    public static int loginID;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //create objectof clases
        GetAccess access = new GetAccess();
        ManageData manage = new ManageData();
        InstructorModule instructor = new InstructorModule();
        //main variables
        String userName = "", password = "", role = "", SSN = "", firstName = "", lastName = "",
                phone = "", email = "", address = "", nationality = "", courseID = "";
        int day, month, year, ID;
        String DoB = "";

        //main driver code
        /*
        .
        .
        .
        .
         */
        if (!access.login("UserTeest", "passs")) {
            System.out.println("Failed to login, will comtinue as guest.");
        } else {
            loginSSN = access.returnSSN();
            loginRole = access.returnRole();
            loginID = access.returnUserID();
            instructor.addGrade(31, 200);
            /*do {
                //code to execute
            } while (access.login(userName, password));*/
        }
    }

}
