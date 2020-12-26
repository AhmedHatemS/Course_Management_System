package AdminModule;

import MainDriver.DBconnect;
import java.util.*;
import java.sql.*;
import java.sql.Date;

/**
 *
 * @author devah
 */
public class ManageData {

    private int ID;
    private String SSN;
    private String userName;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private Date DoB;
    private String phone;
    private String email;
    private String address;
    private String nationality;
    private String courseID;

    private static Connection c;
    private static Statement ss;
    private static ResultSet rs;
    private static String query;

    private int assignUserID() throws ClassNotFoundException, SQLException {

        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            query = "SELECT mainInfo.UserId AS id FROM mainInfo WHERE mainInfo.SSN LIKE '" + this.SSN + "'";
            rs = ss.executeQuery(query);
            rs.next();
            this.ID = rs.getInt("id");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.ID;
    }

    private boolean foundSSN() throws ClassNotFoundException, SQLException {
        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            query = "SELECT COUNT(SSN) AS countValSSN FROM mainInfo where mainInfo.SSN LIKE '" + this.SSN + "'";
            rs = ss.executeQuery(query);
            rs.next();
            if (rs.getInt("countValSSN") == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
private boolean foundUserName() throws ClassNotFoundException, SQLException {
        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            query = "SELECT COUNT(UserName) AS countValUN FROM mainInfo where mainInfo.UserName LIKE '" + this.userName + "'";
            rs = ss.executeQuery(query);
            rs.next();
            if (rs.getInt("countValUN") == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    private boolean foundPhone() throws ClassNotFoundException, SQLException {
        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            if ("student".equals(checkRole())) {
                query = "SELECT COUNT(Phone) AS countValPhone FROM student where student.Phone LIKE '" + this.phone + "'";
            } else if ("instructor".equals(checkRole())) {
                query = "SELECT COUNT(Phone) AS countValPhone FROM instructor where instructor.Phone LIKE '" + this.phone + "'";
            }
            rs = ss.executeQuery(query);
            rs.next();
            if (rs.getInt("countValPhone") == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    private boolean foundEmail() throws ClassNotFoundException, SQLException {
        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            if ("student".equals(checkRole())) {
                query = "SELECT COUNT(Email) AS countValEmail FROM student where student.Email LIKE '" + this.email + "'";
            } else if ("instructor".equals(checkRole())) {
                query = "SELECT COUNT(Email) AS countValEmail FROM instructor where instructor.Email LIKE '" + this.email + "'";
            }
            rs = ss.executeQuery(query);
            rs.next();
            if (rs.getInt("countValEmail") == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
private boolean foundCourseID() throws ClassNotFoundException, SQLException {
        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            if ("student".equals(checkRole())) {
                query = "SELECT COUNT(CourseID) AS countValCID FROM instructor where instructor.Courseid LIKE '" + this.courseID + "'";
            }
            rs = ss.executeQuery(query);
            rs.next();
            if (rs.getInt("countValCID") == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    private String checkRole() throws ClassNotFoundException, SQLException {
        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            query = "SELECT mainInfo.role AS countValRole FROM mainInfo where mainInfo.SSN LIKE '" + this.SSN + "'";
            rs = ss.executeQuery(query);
            rs.next();
            return rs.getString("countValRole");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "";
    }

    public void addUser(String SSN, String userName, String password, String role) throws ClassNotFoundException, SQLException {
        this.SSN = SSN;
        this.userName = userName;
        this.password = password;
        this.role = role.toLowerCase();

        if ((this.SSN).length() != 14 || foundSSN() || foundUserName()) {
            System.out.println("Check the SSN and userName, then try again.");
        } else if (!"admin".equals(this.role) && !"user".equals(this.role)) {
            System.out.println("This role can't be inserted, check the role and try again.");
        } else {
            c = DBconnect.connect();
            ss = c.createStatement();
            try {
                query = "INSERT INTO mainInfo(SSN, UserName, Password, role) VALUES ('" + this.SSN + "' , '" + this.userName + "' , '" + this.password + "' , '" + this.role + "')";
                ss.execute(query);
                assignUserID();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void addStudent(String userName, String password, String firstName, String lastName, String SSN, String DoB, String phone, String email, String address, String nationality) throws ClassNotFoundException, SQLException {
        this.SSN = SSN;
        this.userName = userName;
        this.password = password;
        this.role = "student";
        this.firstName = firstName;
        this.lastName = lastName;
        this.DoB = Date.valueOf(DoB);
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.nationality = nationality;

        if ((this.SSN).length() != 14 || foundSSN() || foundUserName()) {
            System.out.println("Check the SSN and userName, then try again.");
        } else if (foundPhone() || foundEmail()) {
            System.out.println("Phone number or Email is previously added, Enter another one or login.");
        } else {
            c = DBconnect.connect();
            ss = c.createStatement();
            try {
                query = "INSERT INTO mainInfo(SSN, UserName, Password, role) VALUES ('"
                        + this.SSN + "' , '" + this.userName + "' , '" + this.password + "' , '" + this.role + "') ";
                ss.execute(query);
                query = " INSERT INTO student(studID, studFirstName, studLastName, SSN, DOB, Phone, Email, Address, Nationality) "
                        + "VALUES ('" + assignUserID() + "' , '" + this.firstName + "' , '" + this.lastName + "' , '"
                        + this.SSN + "' , '" + this.DoB + "' , '" + this.phone + "' , '" + this.email
                        + "' , '" + this.address + "' , '" + this.nationality + "')";
                ss.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void addInstructor(String userName, String password, String firstName, String lastName, String SSN, String DoB, String phone, String email, String address, String nationality, String courseID) throws ClassNotFoundException, SQLException {
        this.SSN = SSN;
        this.userName = userName;
        this.password = password;
        this.role = "instructor";
        this.firstName = firstName;
        this.lastName = lastName;
        this.DoB = Date.valueOf(DoB);
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.nationality = nationality;
        this.courseID = courseID;

        if ((this.SSN).length() != 14 || foundSSN() || foundUserName()) {
            System.out.println("Check the SSN and userName, then try again.");
        } else if (foundPhone() || foundEmail()) {
            System.out.println("Phone number or Email is previously added, Enter another one or login.");
        } else if(foundCourseID()){
            System.out.println("Another instructor lecturer the same course, please enter another one.");
        }else {
            c = DBconnect.connect();
            ss = c.createStatement();
            try {
                query = "INSERT INTO mainInfo(SSN, UserName, Password, role) VALUES ('"
                        + this.SSN + "' , '" + this.userName + "' , '" + this.password + "' , '" + this.role + "') ";

                ss.execute(query);
                query = "INSERT INTO instructor(instID, instFName, instlName, SSN, DOB, Phone, Email, Address, Nationality, CourseID) "
                        + "VALUES ('" + assignUserID() + "' , '" + this.firstName + "' , '" + this.lastName
                        + "' , '" + this.SSN + "' , '" + this.DoB + "' , '" + this.phone + "' , '" + this.email
                        + "' , '" + this.address + "' , '" + this.nationality + "' , '" + this.courseID + "')";
                ss.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void updatePassword(String SSN, String password) throws ClassNotFoundException, SQLException {
        this.SSN = SSN;
        this.password = password;
        if (!foundSSN()) {
            System.out.println("SSN not found, can't update password for non-valid SSN.");
        } else {
            c = DBconnect.connect();
            ss = c.createStatement();
            try {
                query = "UPDATE mainInfo SET mainInfo.Password = '" + this.password + "' WHERE SSN = '" + this.SSN + "'";
                ss.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void updateStudentPhone(String SSN, String phone) throws ClassNotFoundException, SQLException {
        this.SSN = SSN;
        this.phone = phone;
        if (!foundSSN() || !"student".equals(checkRole())) {
            System.out.println("SSN in wrong or isn't student.");
        } else {
            c = DBconnect.connect();
            ss = c.createStatement();
            try {
                query = "UPDATE student SET student.Phone = '" + this.phone + "' WHERE SSN = '" + this.SSN + "'";
                ss.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void updateStudentEmail(String SSN, String email) throws ClassNotFoundException, SQLException {
        this.SSN = SSN;
        this.email = email;
        if (!foundSSN() || !"student".equals(checkRole())) {
            System.out.println("SSN in wrong or isn't student.");
        } else {
            c = DBconnect.connect();
            ss = c.createStatement();
            try {
                query = "UPDATE student SET student.Email = '" + this.email + "' WHERE SSN = '" + this.SSN + "'";
                ss.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void updateStudentAddress(String SSN, String address) throws ClassNotFoundException, SQLException {
        this.SSN = SSN;
        this.address = address;
        if (!foundSSN() || !"student".equals(checkRole())) {
            System.out.println("SSN in wrong or isn't student.");
        } else {
            c = DBconnect.connect();
            ss = c.createStatement();
            try {
                query = "UPDATE student SET student.Address = '" + this.address + "' WHERE SSN = '" + this.SSN + "'";
                ss.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void updateStudent(String SSN, String phone, String email, String address) throws ClassNotFoundException, SQLException {
        this.SSN = SSN;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (!foundSSN() || !"student".equals(checkRole())) {
            System.out.println("SSN in wrong or isn't student.");
        } else {
            c = DBconnect.connect();
            ss = c.createStatement();
            try {
                query = "UPDATE student SET student.Phone = '" + this.phone + "' , student.Email = '" + this.email + "' ,  student.Address = '" + this.address + "' WHERE SSN = '" + this.SSN + "'";
                ss.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void updateInstructorPhone(String SSN, String phone) throws ClassNotFoundException, SQLException {
        this.SSN = SSN;
        this.phone = phone;
        if (!foundSSN() || !"instructor".equals(checkRole())) {
            System.out.println("SSN in wrong or isn't instructor.");
        } else {
            c = DBconnect.connect();
            ss = c.createStatement();
            try {
                query = "UPDATE instructor SET instructor.Phone = '" + this.phone + "' WHERE SSN = '" + this.SSN + "'";
                ss.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void updateInstructorEmail(String SSN, String email) throws ClassNotFoundException, SQLException {
        this.SSN = SSN;
        this.email = email;
        if (!foundSSN() || !"instructor".equals(checkRole())) {
            System.out.println("SSN in wrong or isn't instructor.");
        } else {
            c = DBconnect.connect();
            ss = c.createStatement();
            try {
                query = "UPDATE instructor SET instructor.Email = '" + this.email + "' WHERE SSN = '" + this.SSN + "'";
                ss.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void updateInstructorAddress(String SSN, String address) throws ClassNotFoundException, SQLException {
        this.SSN = SSN;
        this.address = address;
        if (!foundSSN() || !"instructor".equals(checkRole())) {
            System.out.println("SSN in wrong or isn't instructor.");
        } else {
            c = DBconnect.connect();
            ss = c.createStatement();
            try {
                query = "UPDATE instructor SET instructor.Address = '" + this.address + "' WHERE SSN = '" + this.SSN + "'";
                ss.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void updateInstructorCourseID(String SSN, String courseID) throws ClassNotFoundException, SQLException {
        this.SSN = SSN;
        this.courseID = courseID;
        if (!foundSSN() || !"instructor".equals(checkRole())) {
            System.out.println("SSN in wrong or isn't instructor.");
        } else {
            c = DBconnect.connect();
            ss = c.createStatement();
            try {
                query = "UPDATE instructor SET instructor.CourseID = '" + this.courseID + "' WHERE SSN = '" + this.SSN + "'";
                ss.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void updateInstructor(String SSN, String phone, String email, String address, String courseID) throws ClassNotFoundException, SQLException {
        this.SSN = SSN;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.courseID = courseID;
        if (!foundSSN() || !"instructor".equals(checkRole())) {
            System.out.println("SSN in wrong or isn't instructor.");
        } else {
            c = DBconnect.connect();
            ss = c.createStatement();
            try {
                query = "UPDATE instructor SET instructor.Phone = '" + this.phone + "' , instructor.Email = '" + this.email + "' ,  instructor.Address = '" + this.address + "' , instructor.CourseID = '" + this.courseID + "' WHERE SSN = '" + this.SSN + "'";
                ss.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void deleteUser(String SSN) throws ClassNotFoundException, SQLException {
        this.SSN = SSN;
        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            if (foundSSN() && !"student".equals(checkRole()) && !"instructor".equals(checkRole())) {
                query = "DELETE mainInfo WHERE SSN = '" + this.SSN + "'";
                ss.execute(query);
            } else {
                System.out.println("Record with this SSN not found or can't be deleted.");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteStudent(String SSN) throws ClassNotFoundException, SQLException {
        this.SSN = SSN;
        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            if (foundSSN() && "student".equals(checkRole())) {
                query = "DELETE mainInfo WHERE SSN = '" + this.SSN + "'";
                ss.execute(query);
                query = "delete student where SSN = '" + this.SSN + "'";
                ss.execute(query);
            } else {
                System.out.println("Record with this SSN not found or can't be deleted.");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteInstructor(String SSN) throws ClassNotFoundException, SQLException {
        this.SSN = SSN;
        c = DBconnect.connect();
        ss = c.createStatement();
        try {
            if (foundSSN() && "instructor".equals(checkRole())) {
                query = "DELETE mainInfo WHERE SSN = '" + this.SSN + "'";
                ss.execute(query);
                query = "delete instructor where SSN = '" + this.SSN + "'";
                ss.execute(query);
            } else {
                System.out.println("Record with this SSN not found or can't be deleted.");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}