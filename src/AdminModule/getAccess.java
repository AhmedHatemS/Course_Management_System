package AdminModule;

import MainDriver.DBconnect;
import java.sql.*;

public class GetAccess {

    private String userName;
    private String password;
    private int ID;
    private String SSN;
    private String role;
    private static Connection c;
    private static Statement ss;
    private static ResultSet rs;
    private static String query;

    public boolean login() {
        return false;
    }

    public boolean login(String userName, String password) throws SQLException, ClassNotFoundException {
        this.userName = userName;
        this.password = password;

        //connection
        c = DBconnect.connect();
        System.out.println("Connection stablished successfully.");
        //code
        ss = c.createStatement();
        try {
            query = "SELECT COUNT (mainInfo.UserId) AS rowsCount FROM mainInfo where mainInfo.UserName LIKE '" + this.userName + "' and mainInfo.Password LIKE '" + this.password + "'";
            rs = ss.executeQuery(query);
            rs.next();
            if (rs.getInt("rowsCount") == 1) {//succeeded
                UserID();
                SSN();
                role();
                return true;
            } else {//failed
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean logout() {
        this.userName = "";
        this.password ="";
        return true;
    }

    private void UserID() throws ClassNotFoundException, SQLException {

        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            query = "SELECT mainInfo.UserId AS id FROM mainInfo WHERE mainInfo.UserName LIKE '" + this.userName + "' and mainInfo.Password LIKE '" + this.password + "'";
            rs = ss.executeQuery(query);
            rs.next();
            this.ID = rs.getInt("id");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void SSN() throws ClassNotFoundException, SQLException {
        //connection
        c = DBconnect.connect();
        //code
        ss = c.createStatement();
        try {
            query = "SELECT mainInfo.SSN AS SSN FROM mainInfo where mainInfo.UserName LIKE '" + this.userName + "' and mainInfo.Password LIKE '" + this.password + "'";
            rs = ss.executeQuery(query);
            rs.next();
            this.SSN = rs.getString("SSN");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void role() throws ClassNotFoundException, SQLException {
        //connection
        c = DBconnect.connect();
        //code
        ss = c.createStatement();
        try {
            query = "SELECT mainInfo.role AS role FROM mainInfo where mainInfo.UserName LIKE '" + this.userName + "' and mainInfo.Password LIKE '" + this.password + "'";
            rs = ss.executeQuery(query);
            rs.next();
            this.role = rs.getString("role");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int returnUserID() {
        return this.ID;
    }

    public String returnSSN() throws SQLException, ClassNotFoundException {
        return this.SSN;
    }

    public String returnRole() throws SQLException, ClassNotFoundException {
        return this.role;
    }

}
