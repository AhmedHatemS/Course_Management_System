package CourseModule;

import MainDriver.DBconnect;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ShowNear {

    private static Connection c;
    private static Statement ss;
    private static String query;
    private static ResultSet r;
    private DBconnect c1 = new DBconnect();
    private String StartDate;
    private String CourseName;

    public ShowNear(String StartDate, String CourseName) {
        this.StartDate = StartDate;
        this.CourseName = CourseName;
    }

    Calendar calendar = Calendar.getInstance();

    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ShowNear() {

    }

    public void ShowData() throws ClassNotFoundException {
        try {
            ArrayList<ShowNear> List = new ArrayList();

            c = c1.connect();
            ss = c.createStatement();

            query = "select * from courses";
            r = ss.executeQuery(query);
            System.out.println("Courses near to start are:");
            while (r.next()) {
                List.add(new ShowNear(r.getString("startDate"), r.getString("courseName")));
            }

            for (int i = 0; i < List.size(); i++) {

                LocalDate firstDate;
                firstDate = LocalDate.parse(List.get(i).StartDate, formatter);

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
