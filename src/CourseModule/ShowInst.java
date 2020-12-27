package CourseModule;

import MainDriver.DBconnect;
import java.sql.*;
import java.util.*;

public class ShowInst {

    DBconnect c1 = new DBconnect();
    private static Connection c;
    private static Statement ss;
    private static String query;
    private static ResultSet r;
    private String InstructorFName;
    private String InstructorLName;

    public ShowInst(String InstFname, String InstLname) {
        this.InstructorFName = InstFname;
        this.InstructorLName = InstLname;

    }

    ShowInst() {

    }

    public void ShowData() throws ClassNotFoundException {
        try {
            ArrayList<ShowInst> List = new ArrayList();
            c = c1.connect();
            ss = c.createStatement();

            query = "select * from instructor";
            r = ss.executeQuery(query);
            while (r.next()) {
                List.add(new ShowInst(r.getString("instFName"), r.getString("instlName")));
            }
            System.out.println("Instructors:");
            for (int i = 0; i < List.size(); i++) {
                System.out.println("-" + List.get(i).InstructorFName + " " + List.get(i).InstructorLName);
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
