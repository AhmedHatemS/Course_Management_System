package CourseModule;
import MainDriver.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;
public class ShowCourseTime {
    private static Connection c;
    private static Statement ss;
    private static String query;
    private static ResultSet r;
    private DBconnect c1 = new DBconnect();
    private String Date;
    private String CourseName;
    
     public ShowCourseTime(String Date, String CourseName) {
        this.Date = Date;
        this.CourseName = CourseName;
    }
      public ShowCourseTime() {
    }
      Calendar calendar = Calendar.getInstance();

    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void ShowNearToEnd() throws ClassNotFoundException {
        try {
            ArrayList<ShowCourseTime> List = new ArrayList();

            c = c1.connect();
            ss = c.createStatement();

            query = "select * from courses";
            r = ss.executeQuery(query);
            System.out.println("Courses near to end are:");
            while (r.next()) {
                List.add(new ShowCourseTime(r.getString("endDate"), r.getString("courseName")));
            }

            for (int i = 0; i < List.size(); i++) {

                LocalDate firstDate;
                firstDate = LocalDate.parse(List.get(i).Date, formatter);

                long days = ChronoUnit.DAYS.between(date, firstDate);
                if (date.compareTo(firstDate) < 0) {
                    if (days <= 7) {
                        System.out.println("-" + List.get(i).CourseName);
                    }
                }

            }

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
        public void ShowNearToStart() throws ClassNotFoundException {
        try {
            ArrayList<ShowCourseTime> List = new ArrayList();

            c = c1.connect();
            ss = c.createStatement();

            query = "select * from courses";
            r = ss.executeQuery(query);
            System.out.println("Courses near to start are:");
            while (r.next()) {
                List.add(new ShowCourseTime(r.getString("startDate"), r.getString("courseName")));
            }

            for (int i = 0; i < List.size(); i++) {

                LocalDate firstDate;
                firstDate = LocalDate.parse(List.get(i).Date, formatter);

                long days = ChronoUnit.DAYS.between(firstDate, date);
                if (date.compareTo(firstDate) < 0) {
                    if (days <= 7) {
                        System.out.println("-" + List.get(i).CourseName);
                    }
                }

            }

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




