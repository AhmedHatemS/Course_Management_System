package StudentModule;

import MainDriver.DBconnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class showcourses {

    static Connection c;
    static Statement ss;
    static String query;
    static ResultSet rs;
    //Function show All courses

    public void showcourse() throws ClassNotFoundException {
        DBconnect c1 = new DBconnect();

        try {
            ArrayList<elem> list = new ArrayList<elem>();

            c = c1.connect();
            ss = c.createStatement();
            query = "select * from courses";
            rs = ss.executeQuery(query);

            System.out.println("CourseID\t\tCourseName\t\tParentCourse");
            while (rs.next()) {
                elem a = new elem(rs.getString("courseID"), rs.getString("courseName"), rs.getString("parentCourse"));
                list.add(a);
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i).id);
                System.out.print("\t\t\t");
                System.out.print(list.get(i).Namecourse);
                System.out.print("\t\t\t");
                System.out.print(list.get(i).parentcourse);
                System.out.println();
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}
