package CourseModule;

import MainDriver.*;
import java.sql.*;
import java.util.*;

public class ShowCourseData extends GlobalVars{

    private String CourseName;
    private String EndDate;
    private String StartDate;
    private String FName;
    private String LName;

    ShowCourseData(String FName1, String LName1) {
        this.FName = FName1;
        this.LName = LName1;

    }

    public ShowCourseData() {
    }

    public void ShowStudents() {
        try {
            ArrayList<ShowCourseData> List = new ArrayList();
            c = c1.connect();
            ss = c.createStatement();
            query = "select * from student";
            rs = ss.executeQuery(query);
            while (rs.next()) {
                List.add(new ShowCourseData(rs.getString("studFirstName"), rs.getString("studLastName")));
            }
            System.out.println("Students:");
            int counter=0;
            for (int i = 0; i < List.size(); i++) {
                System.out.println((++counter) +"- " + List.get(i).FName + " " + List.get(i).LName);
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

    public void ShowInstructors() {
        try {
            ArrayList<ShowCourseData> List = new ArrayList();
            c = c1.connect();
            ss = c.createStatement();

            query = "select * from instructor";
            rs = ss.executeQuery(query);
            while (rs.next()) {
                List.add(new ShowCourseData(rs.getString("instFName"), rs.getString("instlName")));
            }
            System.out.println("Instructors:");
            int counter = 0;
            for (int i = 0; i < List.size(); i++) {
                System.out.println((++counter) +"- " + List.get(i).FName + " " + List.get(i).LName);
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
