/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminModule;

import MainDriver.DBconnect;
import java.sql.*;

public class GetAccess {

    String userName;
    String password;
    String SSN;
    String role;

    static Connection c;
    static Statement ss;
    static ResultSet rs;
    static String query;

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
            if (rs.getInt("rowsCount") == 1) {
                System.out.println("Login with given username and password has been succeeded.");
                return true;
            } else {
                System.out.println("Login with given username and password has been failed.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public String returnSSN() throws SQLException, ClassNotFoundException {
        //connection
        c = DBconnect.connect();
        //code
        ss = c.createStatement();
        try {
            query = "SELECT mainInfo.SSN AS SSN FROM mainInfo where mainInfo.UserName LIKE '" + this.userName + "' and mainInfo.Password LIKE '" + this.password + "'";
            rs = ss.executeQuery(query);
            rs.next();
            SSN = rs.getString("SSN");
        } catch (SQLException e) {
            System.out.println(e);
        }

        return SSN;
    }

    public String returnRole() throws SQLException, ClassNotFoundException {
        //connection
        c = DBconnect.connect();
        //code
        ss = c.createStatement();
        try {
            query = "SELECT mainInfo.role AS role FROM mainInfo where mainInfo.UserName LIKE '" + this.userName + "'";
            rs = ss.executeQuery(query);
            rs.next();
            role = rs.getString("role");
        } catch (SQLException e) {
            System.out.println(e);
        }

        return role;
    }
}
