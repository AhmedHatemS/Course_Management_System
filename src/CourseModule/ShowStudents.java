package CourseModule;

import MainDriver.DBconnect;
import java.sql.*;
import java.util.*;

public class ShowStudents {

    static Connection c;
    static Statement ss;
    static String query;
    static ResultSet r;
    DBconnect c1 = new DBconnect();
    private String StudentFName;
    private String StudentLName;

    ShowStudents() {

    }

    ShowStudents(String StuFName, String StuLName) {
        this.StudentFName = StuFName;
        this.StudentLName = StuLName;

    }

    public void ShowData() throws ClassNotFoundException {
        try {
            ArrayList<ShowStudents> List = new ArrayList();
            c = c1.connect();
            ss = c.createStatement();
            query = "select * from student";
            r = ss.executeQuery(query);
            while (r.next()) {
                List.add(new ShowStudents(r.getString("studFirstName"), r.getString("studLastName")));
            }
            System.out.println("Students:");
            for (int i = 0; i < List.size(); i++) {
                System.out.println("-" + List.get(i).StudentFName + " " + List.get(i).StudentLName);
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
