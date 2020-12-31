package InstructorModule;

import AdminModule.*;
import MainDriver.*;
import java.util.*;
import java.sql.*;

public class InstructorModule extends GlobalVars{
    
    private int studentID;
    private float grade;
    private String courseID;
    private String instSSN;

    private String returnInstCourseID() throws ClassNotFoundException, SQLException {
        c = c1.connect();
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

    public void addGrade(int studID, int grade) throws ClassNotFoundException, SQLException {
        this.studentID = studID;
        this.grade = grade;
        if (!"instructor".equals(CourseManagementSystem.loginRole)) {
            System.out.println("Not instructor or SSN not found.");
        } else {
            c = c1.connect();
            ss = c.createStatement();
            try {
                instSSN = MainDriver.CourseManagementSystem.loginSSN;
                courseID = returnInstCourseID();
                query = "UPDATE grades SET  " + courseID + " = '" +grade + "' WHERE grades.studID LIKE '" + this.studentID + "'";
                ss.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                try {
                    c.close();
                    ss.close();
                    rs.close();
                } catch (SQLException s) {
                    System.out.println(s);
                }
            }
        }
    }

    public void publish() throws ClassNotFoundException, SQLException {
        if (!"instructor".equals(CourseManagementSystem.loginRole)) {
            System.out.println("Not instructor.");
        } else {
            c = c1.connect();
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
            } finally {
                try {
                    c.close();
                    ss.close();
                    rs.close();
                } catch (SQLException s) {
                    System.out.println(s);
                }
            }
        }
    }
}