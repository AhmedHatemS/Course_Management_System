package StudentModule;

import MainDriver.DBconnect;
import java.sql.*;
import java.util.*;

public class StudentUpdate {

    private static Connection c;
    private static Statement ss;
    private static String query;
    private static ResultSet rs;
    private DBconnect c1 = new DBconnect();

    private int id;
    private String password;
    private String FName;
    private String LName;
    private String phone;
    private String Email;
    private String Address;
    private String Nationality;

    public void UpdatePhone(int id, String phone) throws ClassNotFoundException {
        this.id = id;
        this.phone = phone;
        try {
            c = c1.connect();
            ss = c.createStatement();
            query = "update student set Phone='" + this.phone + "' where studID='" + this.id + "'";
            ss.execute(query);
            System.out.println("Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException s) {
            }

        }
    }
    //Function Update Emails

    public void UpdateEmail(int id, String Email) throws ClassNotFoundException {
        this.id = id;
        this.Email = Email;

        try {
            c = c1.connect();
            ss = c.createStatement();
            query = "update student set Email='" + this.Email + "' where studID='" + this.id + "'";
            //query ="update student set SSN=345788996791 where studID=1";
            ss.execute(query);
            System.out.println("Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException s) {
            }

        }
    }
    //Function to update address

    public void UpdateAddress(int id, String Address) throws ClassNotFoundException {
        this.id = id;
        this.Address = Address;
        try {
            c = c1.connect();
            ss = c.createStatement();
            query = "update student set Address='" + this.Address + "' where studID='" + this.id + "'";
            ss.execute(query);
            System.out.println("Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException s) {
            }

        }
    }
    
    public void UpdateAll(int id, String Address, String Email, String phone) throws SQLException, SQLException, SQLException, ClassNotFoundException {
        this.id = id;
        this.Address = Address;
        this.Email = Email;
        this.FName = FName;
        this.LName = LName;
        this.Nationality = Nationality;
        this.phone = phone;

        try {
            c = c1.connect();
            ss = c.createStatement();
            query = "update student set studFirstName='" + this.FName + "',studLastName='" + this.LName + "',Nationality='" + this.Nationality + "',Address='" + this.Address + "',Phone='" + this.phone + "',Email='" + this.Email + "'where studID='" + this.id + "'";
            ss.execute(query);
            System.out.println("Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException s) {
            }

        }
    }

    public void updatePassword(int id, String password) throws ClassNotFoundException, SQLException {
        this.id = id;
        this.password = password;

        c = c1.connect();
        ss = c.createStatement();
        try {
            query = "update mainInfo set mainInfo.Password='" + this.password + "' where UserID='" + this.id + "'";
            ss.execute(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}