package CourseModule;

import MainDriver.*;
import static java.lang.System.exit;
import java.sql.*;
import java.util.*;

public class manageCourses {
    

    dbConn c1 = new dbConn();
    static Connection c;
    static Statement ss;
    ResultSet rs=null;
    static String query;
    String courseID;
    String courseName;
    String parentCourse;
    String branch;
    int price;
    int room;
    int instID;
    String startDate;
    String endDate;
    int startDay;
    int startMonth;
    int startYear;
    int endDay;
    int endMonth;
    int endYear;
    int daysOfCourse;
    Scanner input = new Scanner(System.in);
    public boolean check;
//no arg constructor
    public manageCourses() {
    }
//arg constructor
    manageCourses(String courseID, String courseName, String parentCourse, int price,
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

    public String getStartdate(int startDay, int startMonth, int startYear) {
        String s;
        s = "" + startYear + "-" + startMonth + "-" + startDay;
        return s;
    }
    //method to return end date in the form of string

    
    public String getenddate() {
        String x;
        x = "" + endYear + "-" + endMonth + "-" + endDay;
        return x;
    }

    // method to check if the entered course exist or not return false if it is existed and true if not 
    public boolean checkCourses(String courseID, String courseName) throws SQLException {
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

    public boolean checkinstructor() throws SQLException {
        c = c1.connect();
        ss = c.createStatement();
        try {
            query = "SELECT COUNT (instructor.instID) AS rowsCount FROM instructor where instructor.instID LIKE '" + this.instID + "'" ;
            rs = ss.executeQuery(query);
            rs.next();
            if (rs.getInt("rowsCount") == 1 || this.instID==0 ) {

                return false;
            } else {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }
//    public boolean checkinstructorCourse(String courseID) throws SQLException {
//        c = c1.connect();
//        String x;
//        ss = c.createStatement();
//        try {
//            query = "SELECT CourseID FROM instructor where instID LIKE '" +this.instID + "'";
//            rs=ss.executeQuery(query);
//            x=rs.getString("CourseID");
//            if (x==courseID) {
//
//                return false;
//            } else {
//                return true;
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return true;
//    }
        // method to change the parent course with null in case of deleting the course
    public void checkparent(String parentCourse) throws SQLException {
        c = c1.connect();
        ss = c.createStatement();
        try {
            query = "update courses set parentCourse='null' where parentCourse='" + parentCourse + "'";
            ss.execute(query);
            System.out.println("updated");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException se) {

            }
        }}
        // method to add course
    public void addcourse(String courseID, String courseName) throws SQLException {
        try {
            System.out.println("Enter parent Course ID: ");
            parentCourse = input.nextLine();
            System.out.println("Enter Branch: ");
            branch = input.nextLine();
            System.out.println("Enter Course Price: ");
            price = input.nextInt();
            System.out.println("Enter Course room: ");
            room = input.nextInt();
            System.out.println("Enter Course Start day: ");
            startDay = input.nextInt();
            System.out.println("Enter Course Start month: ");
            startMonth = input.nextInt();
            System.out.println("Enter Course Start year: ");
            startYear = input.nextInt();
            System.out.println("Enter Course End day: ");
            endDay = input.nextInt();
            System.out.println("Enter Course End month: ");
            endMonth = input.nextInt();
            System.out.println("Enter Course End year: ");
            endYear = input.nextInt();
            System.out.println("Enter Days of course: ");
            daysOfCourse = input.nextInt();
            System.out.println("Enter Instructor ID: ");
            instID = input.nextInt();
            while (checkinstructor()== true ) {
            System.out.println("invalid instructor id enter another one");
                instID = input.nextInt();  
            }
            startDate = getStartdate(startDay, startMonth, startYear);
            endDate = getStartdate(endDay, endMonth, endYear);
            c = c1.connect();
            ss = c.createStatement();
            query = "insert into courses values('" + courseID + "', '" + courseName + "','" + parentCourse + "','" + startDate + "','" + endDate + "','" + daysOfCourse + "','" + price + "','" + room + "','" + branch + "','" + instID + "')";
            ss.execute(query);
            query = "alter table grades add "+courseID+" int";
            ss.execute(query);    
            query = "alter table regesteredCourses add "+courseID+" varchar(100)";
            ss.execute(query);
            System.out.println("inserted");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException se) {

            }
        }

    }
        // method to update  parent course

    public void updateParentCourse(String courseID, String parentCourse) {
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
            } catch (SQLException se) {

            }
        }
    }
            // method to update price of course

    public void updatePriceOfCourse(String courseID, int price) {
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
            } catch (SQLException se) {

            }
        }
    }
        // method to update room 

    public void updateRoom(String courseID, int room) {
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
            } catch (SQLException se) {

            }
        }
    }
        // method to update branch

    public void updateBranch(String courseID, String branch) {
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
            } catch (SQLException se) {

            }
        }
    }
        // method to update no of days of the course

    public void updateDaysOfCourse(String courseID, int daysOfCourse) {
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
            } catch (SQLException se) {

            }
        }
    }
        // method to update start date of course

    public void updatestartDate(String courseID, String startDate) {
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
            } catch (SQLException se) {

            }
        }
    }
// method to update end date of course
    public void updateendDate(String courseID, String endDate) {
        try {
            c = c1.connect();
            ss = c.createStatement();
            query = "update courses set endDate='" + endDate + "' where courseID='" + courseID + "'";
            ss.execute(query);
            System.out.println("updated");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException se) {

            }
        }
    }
// method to delete course
    public void deleteCourse(String courseID) {
        try {
            c = c1.connect();
            ss = c.createStatement();
            query = "delete from courses where courseID='" + courseID + "'";
            ss.execute(query);
            query = "alter table regesteredCourses drop column "+courseID+" ";
            ss.execute(query);
            query = "alter table grades drop column "+courseID+" ";
            ss.execute(query);
            System.out.println("deleted");
            checkparent(courseID);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException se) {

            }
        }

    }

}
