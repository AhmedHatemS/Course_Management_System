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
        //create object of clases
        GetAccess ga = new GetAccess();
        ManageData md = new ManageData();
        InstructorModule instructor = new InstructorModule();
        ShowCourseData scd = new ShowCourseData();
        ShowCourseTime sct = new ShowCourseTime();
        ManageCourses mc = new ManageCourses();
        StudentShow sShow = new StudentShow();
        StudentUpdate sUpdate = new StudentUpdate();
        //main variables
        String userName = "defaultadmin", password = "admin", role = "", SSN = "", firstName = "", lastName = "",
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
        if (!ga.login(userName, password)) {
            System.out.println("Failed to login, will comtinue as guest.");
            //code to display as guest.
        } else {
            loginSSN = ga.returnSSN();
            loginRole = ga.returnRole();
            loginID = ga.returnUserID();
            
            
            mc.addCourse("CS111", "Intro to CS", "null", 500, 1, "Cairo", 0, 18, 10, 2020, 8, 1, 2020, 81);
            mc.addCourse("IS111", "Intro to IS", "null", 500, 2, "Cairo", 0, 18, 10, 2020, 8, 1, 2020, 81);
            
            
            
            
            /*do {
                //code to execute
            } while (access.login(userName, password));*/
        }
    }

}
