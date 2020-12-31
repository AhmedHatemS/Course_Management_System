package CourseModule;

import MainDriver.*;
import static java.lang.System.exit;
import java.sql.*;
import java.util.*;

public class ManageCourses {

    private DBconnect c1 = new DBconnect();
    private static Connection c;
    private static Statement ss;
    private ResultSet rs=null;
    private static String query;
    private String courseID;
    private String courseName;
    private String parentCourse;
    private String branch;
    private int price;
    private int room;
    private int instID;
    private String startDate;
    private String endDate;
    private int startDay;
    private int startMonth;
    private int startYear;
    private int endDay;
    private int endMonth;
    private int endYear;
    private int daysOfCourse;
//no arg constructor

    public ManageCourses() {
    }
//arg constructor

    ManageCourses(String courseID, String courseName, String parentCourse, int price,
            int room, String branch, int instID, String startDate, String endDate, int startDay,
            int startMonth, int startYear, int endDay, int endMonth, int endYear, int daysOfCourse) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.parentCourse = parentCourse;
        this.price = price;
        this.room = room;
        this.branch = branch;
        this.instID = instID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDay = startDay;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endDay = endDay;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.daysOfCourse = daysOfCourse;
    }
    //method to return start date in the form of string

    private String getStartDate(int startDay, int startMonth, int startYear) {
        String s;
        s = "" + startYear + "-" + startMonth + "-" + startDay;
        return s;
    }
    //method to return end date in the form of string

    private String getEndDate(int endDay, int endMonth, int endYear) {
        String x;
        x = "" + endYear + "-" + endMonth + "-" + endDay;
        return x;
    }

    // method to check if the entered course exist or not return false if it is existed and true if not 
    public boolean checkCourses(String courseID, String courseName) throws SQLException, ClassNotFoundException {
        c = c1.connect();
        ss = c.createStatement();
        try {
            query = "SELECT COUNT (courses.courseID) AS rowsCount FROM courses where courses.courseName LIKE '" + courseName + "'";
            rs = ss.executeQuery(query);
            rs.next();
            if (rs.getInt("rowsCount") == 1) {

                return false;
            } else {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }

    // method to check if the entered instructor id exist or not in the table of instructor return false if it is existed and true if not 
    public boolean checkInstructor(int instID) throws SQLException, ClassNotFoundException {
        c = c1.connect();
        ss = c.createStatement();
        try {
            query = "SELECT COUNT (instructor.instID) AS rowsCount FROM instructor where instructor.instID LIKE '" + instID + "'";
            rs = ss.executeQuery(query);
            rs.next();
            if (rs.getInt("rowsCount") == 1 || instID == 0) {

                return false;
            } else {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }

    
    // method to change the parent course with null in case of deleting the course
    private void checkParent(String parentCourse) throws SQLException, ClassNotFoundException {
        c = c1.connect();
        ss = c.createStatement();
        try {
            query = "update courses set parentCourse='null' where parentCourse='" + parentCourse + "'";
            ss.execute(query);
            System.out.println("updated");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } 
    }
    // method to add course

    public void addCourse(String courseID, String courseName, String parentCourse, int price,
            int room, String branch, int instID, int startDay,
            int startMonth, int startYear, int endDay, int endMonth, int endYear, int daysOfCourse) throws SQLException, ClassNotFoundException {
        try {
            startDate = getStartDate(startDay, startMonth, startYear);
            endDate = getEndDate(endDay, endMonth, endYear);
            c = c1.connect();
            ss = c.createStatement();
            query = "insert into courses values('" + courseID + "', '" + courseName + "','" + parentCourse + "','" + startDate + "','" + endDate + "','" + daysOfCourse + "','" + price + "','" + room + "','" + branch + "','" + instID + "')";
            ss.execute(query);
            query = "alter table grades add " + courseID + " int";
            ss.execute(query);
            query = "alter table regesteredCourses add " + courseID + " int";
            ss.execute(query);
            System.out.println("inserted");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException se) {
                System.out.println(se);
            }
        }

    }
    // method to update  parent course

    public void updateParentCourse(String courseID, String parentCourse) throws ClassNotFoundException {
        try {
            c = c1.connect();
            ss = c.createStatement();
            query = "update courses set parentCourse='" + parentCourse + "' where courseID='" + courseID + "'";
            ss.execute(query);
            System.out.println("updated");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    // method to update price of course

    public void updatePriceOfCourse(String courseID, int price) throws ClassNotFoundException {
        try {
            c = c1.connect();
            ss = c.createStatement();
            query = "update courses set price='" + price + "' where courseID='" + courseID + "'";
            ss.execute(query);
            System.out.println("updated");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    // method to update room 

    public void updateRoom(String courseID, int room) throws ClassNotFoundException {
        try {
            c = c1.connect();
            ss = c.createStatement();
            query = "update courses set room='" + room + "' where courseID='" + courseID + "'";
            ss.execute(query);
            System.out.println("updated");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    // method to update branch

    public void updateBranch(String courseID, String branch) throws ClassNotFoundException {
        try {
            c = c1.connect();
            ss = c.createStatement();
            query = "update courses set branch='" + branch + "' where courseID='" + courseID + "'";
            ss.execute(query);
            System.out.println("updated");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    // method to update no of days of the course

    public void updateDaysOfCourse(String courseID, int daysOfCourse) throws ClassNotFoundException {
        try {
            c = c1.connect();
            ss = c.createStatement();
            query = "update courses set daysOfcourse='" + daysOfCourse + "' where courseID='" + courseID + "'";
            ss.execute(query);
            System.out.println("updated");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    // method to update start date of course

    public void updateStartDate(String courseID, String startDate) throws ClassNotFoundException {
        try {
            c = c1.connect();
            ss = c.createStatement();
            query = "update courses set startDate='" + startDate + "' where courseID='" + courseID + "'";
            ss.execute(query);
            System.out.println("updated");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
// method to update end date of course

    public void updateEndDate(String courseID, String endDate) throws ClassNotFoundException {
        try {
            c = c1.connect();
            ss = c.createStatement();
            query = "update courses set endDate='" + endDate + "' where courseID='" + courseID + "'";
            ss.execute(query);
            System.out.println("updated");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } 
        
        finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
// method to delete course

    public void deleteCourse(String courseID) throws ClassNotFoundException {
        try {
            c = c1.connect();
            ss = c.createStatement();
            query = "delete from courses where courseID='" + courseID + "'";
            ss.execute(query);
            query = "alter table regesteredCourses drop column " + courseID + " ";
            ss.execute(query);
            query = "alter table grades drop column " + courseID + " ";
            ss.execute(query);
            System.out.println("deleted");
            checkParent(courseID);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

    }

}
