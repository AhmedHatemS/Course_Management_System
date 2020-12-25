package InstructorModule;

import MainDriver.DBconnect;
import java.util.*;
import java.sql.*;

public class InstructorModule {

    private static Connection c;
    private static Statement ss;
    private static ResultSet rs;
    private static String query;
    private int ID;
    private int INStID;
    public int id;

    private int assignINSID() throws ClassNotFoundException, SQLException {
        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            query = "SELECT instructor.CourseID AS id FROM instructor WHERE instID LIKE '" + INStID + "'";
            rs = ss.executeQuery(query);
            rs.next();
            this.ID = rs.getInt("id");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.ID;
    }

    public void addInstructor() throws ClassNotFoundException, SQLException {
        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            Scanner x = new Scanner(System.in);
            System.out.println("Degree:");
            int d = x.nextInt();
            System.out.println("Student ID:");
            id = x.nextInt();
            query = "INSERT INTO grades(" + assignINSID() + ") VALUES (" + d + ") WHERE studID LIKE '" + id + "'";
            ss.execute(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void publish() {
        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            ArrayList<elem> list = new ArrayList<elem>();
            query = "select * from grades";
            rs = ss.executeQuery(query);

            System.out.println("ID\tDegree");
            while (rs.next()) {
                elem a = new elem(rs.getString("studID"), rs.getString("courseName"))
                );
                list.add(a);
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i).id);
                System.out.print("\t\t");
                System.out.print(list.get(i).Degree);
                System.out.println();
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}
