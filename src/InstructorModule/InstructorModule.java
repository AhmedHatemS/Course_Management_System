package InstructorModule;

import AdminModule.*;
import MainDriver.*;
import static MainDriver.CourseManagementSystem.*;
import java.util.*;
import java.sql.*;

public class InstructorModule {

    private static Connection c;
    private static Statement ss;
    private static ResultSet rs;
    private static String query;
    private int studentID;
    private float grade;
    private String courseID;
    private String instSSN;

    private String returnInstCourseID() throws ClassNotFoundException, SQLException {
        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            query = "SELECT CourseID AS cid FROM instructor WHERE instructor.SSN LIKE '" + instSSN + "'";
            rs = ss.executeQuery(query);
            rs.next();
            return rs.getString("cid");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "..";
    }

    public void addGrade(int studID, float grade) throws ClassNotFoundException, SQLException {
        this.studentID = studID;
        this.grade = grade;
        if (!"instructor".equals(CourseManagementSystem.loginRole)) {
            System.out.println("Not instructor or SSN not found.");
        } else {
            c = DBconnect.connect();
            ss = c.createStatement();
            try {
                instSSN = MainDriver.CourseManagementSystem.loginSSN;
                courseID = returnInstCourseID();
                query = "UPDATE grades SET  " + courseID + " = '" + this.grade + "' WHERE grades.studID LIKE '" + this.studentID + "'";
                ss.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void publish() throws ClassNotFoundException, SQLException {
        if (!"instructor".equals(CourseManagementSystem.loginRole)) {
            System.out.println("Not instructor.");
        } else {
            c = DBconnect.connect();
            ss = c.createStatement();
            try {
                instSSN = MainDriver.CourseManagementSystem.loginSSN;
                courseID = returnInstCourseID();
                query = "SELECT grades.studID, studFirstName, studLastName, " + courseID + " FROM student INNER JOIN grades ON student.studID = grades.studID";
                ResultSet rs = ss.executeQuery(query);
                if (rs.next()) {
                    System.out.println("_______________________________________________________");
                    System.out.printf("|%-5s|%-15s|%-15s|%-15s|\n", "ID", "first name", "last name", "Grade of " + courseID);
                    do {
                        System.out.println("-------------------------------------------------------");
                        System.out.printf("|%-5s|%-15s|%-15s|%-15s|\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));

                    } while (rs.next());
                    System.out.println("-------------------------------------------------------");
                } else {
                    System.out.println("Record Not Found...");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
