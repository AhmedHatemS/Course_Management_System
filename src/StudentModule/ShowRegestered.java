package StudentModule;

import MainDriver.DBconnect;
import java.sql.*;

public class ShowRegestered {

    private static String[] colNames = new String[1000];
    int studentID;
    private static Connection c;
    private static Statement ss;
    private static ResultSet rs;
    private static String query;

    private boolean foundStudentID() throws ClassNotFoundException, SQLException {

        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            query = "SELECT COUNT (mainInfo.UserId) AS id FROM mainInfo WHERE mainInfo.UserId LIKE '" + this.studentID + "'";
            rs = ss.executeQuery(query);
            rs.next();
            if (rs.getInt("id") == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    private static void saveCoursesNames() throws SQLException, ClassNotFoundException {
        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            query = "SELECT INFORMATION_SCHEMA.COLUMNS.COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE 'regesteredCourses'";
            rs = ss.executeQuery(query);
            if (rs.next()) {
                do {
                    for (int i = 0; i < 1000; i++) {
                        colNames[i] = rs.getString(1);
                        if (!rs.next()) {
                            break;
                        }
                    }
                } while (rs.next());
            }
            for (int i = 0; colNames[i] != null; i++) {
                colNames[i] = colNames[i + 1];
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void showRegesteredCourses(int studentID) throws SQLException, ClassNotFoundException {
        this.studentID = studentID;
        if (foundStudentID()) {
            saveCoursesNames();
            c = DBconnect.connect();
            ss = c.createStatement();
            for (int i = 0; colNames[i] != null; i++) {
                query = "SELECT COUNT (regesteredCourses." + colNames[i] + ") AS f FROM regesteredCourses WHERE studID = '" + this.studentID + "' AND regesteredCourses." + colNames[i] + " = '1'";
                rs = ss.executeQuery(query);
                rs.next();
                if (rs.getInt("f") == 1) {
                    System.out.print(colNames[i] + " ");
                }
            }
            System.out.printLN("");
        }
    }
}
