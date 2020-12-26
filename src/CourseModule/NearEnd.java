package CourseModule;

import MainDriver.DBconnect;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class NearEnd {

    static Connection c;
    static Statement ss;
    static String query;
    static ResultSet r;
    DBconnect c1 = new DBconnect();
    private String EndDate;
    private String CourseName;

    public NearEnd(String EndDate, String CourseName) {
        this.EndDate = EndDate;
        this.CourseName = CourseName;
    }

    public NearEnd() {
    }
    Calendar calendar = Calendar.getInstance();

    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void ShowData() throws ClassNotFoundException {
        try {
            ArrayList<NearEnd> List = new ArrayList();

            c = c1.connect();
            ss = c.createStatement();

            query = "select * from courses";
            r = ss.executeQuery(query);
            System.out.println("Courses near to end are:");
            while (r.next()) {
                List.add(new NearEnd(r.getString("endDate"), r.getString("courseName")));
            }

            for (int i = 0; i < List.size(); i++) {

                LocalDate firstDate;
                firstDate = LocalDate.parse(List.get(i).EndDate, formatter);

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

}

