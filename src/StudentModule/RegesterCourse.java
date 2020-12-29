/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentModule;

import MainDriver.DBconnect;
import java.sql.*;

/**
 *
 * @author devah
 */
public class RegesterCourse {

    private DBconnect c1 = new DBconnect();
    private static Connection c;
    private static Statement ss;
    private static ResultSet rs;
    private static String query;

    int ID;
    String courseID, parentCourse;

    private boolean foundCourseID() throws ClassNotFoundException, SQLException {
        c = c1.connect();
        ss = c.createStatement();
        try {
            query = "SELECT COUNT(CourseID) AS countValCID FROM courses where courseID LIKE '" + this.courseID + "'";
            rs = ss.executeQuery(query);
            rs.next();
            if (rs.getInt("countValCID") == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    private void parent() throws SQLException {
        c = c1.connect();
        ss = c.createStatement();
        try {
            query = "SELECT parentCourse FROM courses where courseID LIKE '" + this.courseID + "'";
            rs = ss.executeQuery(query);
            rs.next();
            this.parentCourse = rs.getString("parentCourse");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private boolean regesteredParent() throws SQLException, ClassNotFoundException {
        c = c1.connect();
        ss = c.createStatement();
        try {
            if (this.parentCourse != "null") {
                query = "SELECT " + this.parentCourse + " AS pc FROM regesteredCourses where studID LIKE '" + this.ID + "'";
            }
            rs = ss.executeQuery(query);
            rs.next();
            if (rs.getInt("pc") == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void regesterCourse(int ID, String courseID) throws ClassNotFoundException, SQLException {
        this.ID = ID;
        this.courseID = courseID;
        parent();
        if (foundCourseID() && ("null".equals(this.parentCourse) || regesteredParent())) {
            c = c1.connect();
            ss = c.createStatement();
            try {
                query = "UPDATE regesteredCourses SET " + this.courseID + " = '1' where studID LIKE '" + this.ID + "'";
                ss.execute(query);
                System.out.println("Registered successfully.");
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                try {
                    c.close();
                    ss.close();
                } catch (SQLException s) {
                }
            }
        } else {
            System.out.println("Register the parent course first, then register this course.\nThe parent course is " + this.parentCourse);
        }
    }
}
