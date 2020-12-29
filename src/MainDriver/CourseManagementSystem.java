package MainDriver;

import AdminModule.*;
import CourseModule.*;
import InstructorModule.*;
import StudentModule.*;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;

public class CourseManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static String loginSSN;
    public static String loginRole;
    public static int loginID;
    //create object of clases
    private static Scanner input = new Scanner(System.in);
    private static GetAccess ga = new GetAccess();
    private static ManageData md = new ManageData();
    private static InstructorModule instructor = new InstructorModule();
    private static ShowCourseData scd = new ShowCourseData();
    private static ShowCourseTime sct = new ShowCourseTime();
    private static ManageCourses mc = new ManageCourses();
    private static StudentShow sShow = new StudentShow();
    private static StudentUpdate sUpdate = new StudentUpdate();
    private static RegesterCourse rc = new RegesterCourse();
    

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //connection
        DBconnect c = new DBconnect();

        //main variables
        String userName = "", password = "", role = "", SSN = "", firstName = "", lastName = "",
                phone = "", email = "", address = "", nationality = "", courseID = "";
                   
        int day, month, year, ID;
        String DoB = "";
        //main driver code
        System.out.println("Connection stablished successfully.");
        System.out.println("Hello, Please enter the username and password to login\nIf don't want to login, enter 0 in both.");
        System.out.print("Username: ");
        userName = input.nextLine();
        System.out.print("Password: ");
        password = input.nextLine();
        if (!ga.login(userName, password)) {
            System.out.println("Didn't login, will comtinue as guest.\n"
                    + "Choose operation to do\n"
                    + "1- show all courses.\n2- show main details of courses.");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    sShow.showAllCourse();
                    break;
                case 2:
                    sShow.showMDCourse();
                    break;
                default:
                    System.out.println("Unavailable choice.");
            }
        } else {
            loginSSN = ga.returnSSN();
            loginRole = ga.returnRole();
            loginID = ga.returnUserID();

            if ("admin".equals(loginRole)) {
                System.out.println("Welcome as admin ♥");
                admin();
            } else if ("student".equals(loginRole)) {
                System.out.println("Welcome as student ♥");
                student();
            } else if ("instructor".equals(loginRole)) {
                System.out.println("Welcome as instructor ♥");
                instructor();
            }
        }
    }

    private static void admin() throws ClassNotFoundException, SQLException {
        String SSN, username, password, role, firstName, lastName, DoB, phone, email, address, nationality, courseID,courseName,parentCourse,branch,
                 startDate,endDate, ch,sh = "yes";
         
        int con = 1,price,room,instID,startDay,startMonth,startYear,endDay,endMonth,endYear,daysOfCourse;
        do {
            System.out.println("Choose an operation to do\n"
                    + "1- add user.\n2- update password.\n"
                    + "3- add instructor.\n4- update instructor's phone.\n5- update instructor's email.\n6- update instructor's address."
                    + "7- update instructor's courseID.\n8- update instructor's all data."
                    + "9- add student.\n10- update student's phone.\n11- update student's email.\n12- update student's address."
                    + "13- update student's all data.\n"
                    + "14- delete user.\n15- delete instructor.\n16- delete student.\n"
                    + "17- create new course or add parent course.\n"
                    + "18- delete course.\n19- update parent course\n20- update course room number.\n "
                    + "21- update branch.\n22- update price of course.\n23- update number of daos.\n"
                    + "24- update start date.\n25- update end date.\n"
                    + "26- logout.");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter UserName: ");
                    username = input.next();
                    System.out.print("Enter password: ");
                    password = input.next();
                    System.out.print("Enter role: ");
                    role = input.next();
                    md.addUser(SSN, username, password, role);
                    break;
                case 2:
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter password: ");
                    password = input.next();
                    md.updatePassword(SSN, password);
                    break;
                case 3:
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter UserName: ");
                    username = input.next();
                    System.out.print("Enter password: ");
                    password = input.next();
                    System.out.print("Enter role: ");
                    role = input.next();
                    System.out.print("Enter first name: ");
                    firstName = input.next();
                    System.out.print("Enter last name: ");
                    lastName = input.next();
                    System.out.print("Enter date of birth as yy-mm-dd: ");
                    DoB = input.next();
                    System.out.print("Enter phone number: ");
                    phone = input.next();
                    System.out.print("Enter email: ");
                    email = input.next();
                    System.out.print("Enter address: ");
                    address = input.next();
                    System.out.print("Enter nationality: ");
                    nationality = input.next();
                    System.out.println("Enter courseID or enter null:");
                    courseID = input.next();
                    md.addInstructor(username, password, firstName, lastName, SSN, DoB, phone, email, address, nationality, courseID);
                    break;
                case 4:
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter phone number: ");
                    phone = input.next();
                    md.updateInstructorPhone(SSN, phone);
                    break;
                case 5:
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter email: ");
                    email = input.next();
                    md.updateInstructorEmail(SSN, email);
                    break;
                case 6:
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter address: ");
                    address = input.next();
                    md.updateInstructorAddress(SSN, address);
                    break;
                case 7:
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter courseID: ");
                    courseID = input.next();
                    md.updateInstructorCourseID(SSN, courseID);
                    break;
                case 8:
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter phone number: ");
                    phone = input.next();
                    System.out.print("Enter email: ");
                    email = input.next();
                    System.out.print("Enter address: ");
                    address = input.next();
                    System.out.print("Enter courseID: ");
                    courseID = input.next();
                    md.updateInstructor(SSN, phone, email, address, courseID);
                    break;
                case 9:
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter UserName: ");
                    username = input.next();
                    System.out.print("Enter password: ");
                    password = input.next();
                    System.out.print("Enter role: ");
                    role = input.next();
                    System.out.print("Enter first name: ");
                    firstName = input.next();
                    System.out.print("Enter last name: ");
                    lastName = input.next();
                    System.out.print("Enter date of birth as yy-mm-dd: ");
                    DoB = input.next();
                    System.out.print("Enter phone number: ");
                    phone = input.next();
                    System.out.print("Enter email: ");
                    email = input.next();
                    System.out.print("Enter address: ");
                    address = input.next();
                    System.out.print("Enter nationality: ");
                    nationality = input.next();
                    md.addStudent(username, password, firstName, lastName, SSN, DoB, phone, email, address, nationality);
                    break;
                case 10:
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter phone number: ");
                    phone = input.next();
                    md.updateStudentPhone(SSN, phone);
                    break;
                case 11:
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter email: ");
                    email = input.next();
                    md.updateStudentEmail(SSN, email);
                    break;
                case 12:
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter address: ");
                    address = input.next();
                    md.updateStudentAddress(SSN, address);
                    break;
                case 13:
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter phone number: ");
                    phone = input.next();
                    System.out.print("Enter email: ");
                    email = input.next();
                    System.out.print("Enter address: ");
                    address = input.next();
                    md.updateStudent(SSN, phone, email, address);
                    break;
                case 14:
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    md.deleteUser(SSN);
                    break;
                case 15:
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    md.deleteInstructor(SSN);
                    break;
                case 16:
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    md.deleteUser(SSN);
                    break;
                case 17:
                    do {
                    System.out.println("Enter Course ID: ");
                    courseID = input.next();
                    System.out.println("Enter Course Name: ");
                    courseName = input.next();
                    if (mc.checkCourses(courseID, courseName) == false) {
                        System.out.println("This course already exists.If you want to enter another course press yes");
                        ch = input.next();
                    } else {
                        System.out.println("Enter parent Course ID: ");
                        parentCourse = input.next();
                        System.out.println("Enter Branch: ");
                        branch = input.next();
                        System.out.println("Enter Course Price: ");
                        price = input.nextInt();
                        System.out.println("Enter Course room: ");
                        room = input.nextInt();
                        System.out.println("Enter Course Start day: ");
                        startDay = input.nextInt();
                        System.out.println("Enter Course Start month: ");
                        startMonth = input.nextInt();
                        System.out.println("Enter Course Start year: ");
                        startYear = input.nextInt();
                        System.out.println("Enter Course End day: ");
                        endDay = input.nextInt();
                        System.out.println("Enter Course End month: ");
                        endMonth = input.nextInt();
                        System.out.println("Enter Course End year: ");
                        endYear = input.nextInt();
                        System.out.println("Enter Days of course: ");
                        daysOfCourse = input.nextInt();
                        System.out.println("Enter Instructor ID: ");
                        instID = input.nextInt();
                        while (mc.checkInstructor() == true) {
                            System.out.println("invalid instructor id enter another one");
                            instID = input.nextInt();
                        }
                        mc.addCourse(courseID, courseName, parentCourse, price,
                                room, branch, instID, startDay,
                                startMonth, startYear, endDay, endMonth, endYear, daysOfCourse);
                        System.out.println("If you want to enter another course press yes");
                        ch = input.next();
                    }
                } while (ch.compareTo(sh) == 0);

                break; 
                case 18:
                    System.out.println("Enter Course ID: ");
                courseID = input.next();
                mc.deleteCourse(courseID);
                break;
                case 19:
                    System.out.println("Enter Parent Course ID: ");
                courseID = input.next();
                System.out.println("Enter the new parent Course: ");
                parentCourse = input.next();
                mc.updateParentCourse(courseID, parentCourse);
                    break;
                case 20:
                    System.out.println("Enter Course ID: ");
                courseID = input.next();
                System.out.println("Enter new room number: ");
                room = input.nextInt();
                mc.updateRoom(courseID, room);
                break;
                case 21:
                    System.out.println("Enter Course ID: ");
                courseID = input.next();
                System.out.println("Enter new branch: ");
                branch = input.next();
                mc.updateBranch(courseID, branch);
                break;
                case 22:
                    System.out.println("Enter Course ID: ");
                courseID = input.next();
                System.out.println("Enter new price: ");
                price = input.nextInt();
                mc.updatePriceOfCourse(courseID, price);
                break;
                case 23:
                    System.out.println("Enter Course ID: ");
                courseID = input.next();
                System.out.println("Enter days of course: ");
                daysOfCourse = input.nextInt();
                mc.updateDaysOfCourse(courseID, daysOfCourse);
                    break;
                case 24:
                    System.out.println("Enter Course ID: ");
                courseID = input.next();
                System.out.println("Enter new Start in the form of date yyyy-mm-dd: ");
                startDate = input.next();
                mc.updateStartDate(courseID, startDate);
                break;
                case 25:
                    System.out.println("Enter Course ID: ");
                courseID = input.next();
                System.out.println("Enter new end date in the form of yyyy-mm-dd: ");
                endDate = input.next();
                mc.updateEndDate(courseID, endDate);
                break;
                case 26:
                    con = 0;
                    break;
                default:
                    System.out.println("Unavailable choice.");
            }
        } while (con == 1);
    }

    private static void instructor() throws ClassNotFoundException, SQLException {
        int studentID;
        float grade;
        int con = 1;
        System.out.println("You will be able to control only the course you lectures.");
        do {
            System.out.println("Choose an operation to do\n"
                    + "1- add grade.\n2- publish grades.\n"
                    + "3- show all students.\n"         
                    + "a- logout.");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    studentID = input.nextInt();
                    System.out.print("Enter grade as digits: ");
                    grade = input.nextInt();
                    instructor.addGrade(studentID, grade);
                    break;
                case 2:
                    instructor.publish();
                    break;
                case 3:
                    scd.ShowStudents();
                    break;
                case 4:
                    con = 0;
                    break;
                default:
                    System.out.println("Unavailable choice.");
            }
        } while (con == 1);
    }

    private static void student() throws ClassNotFoundException, SQLException {
        int ID = loginID;
        String phone, email, address, password, courseID;
        int con = 1;
        do {
            System.out.println("Choose an operation to do\n"
                    + "1- update phone.\n2- update email.\n3- update address\n4- update password.\n5- update all data.\n"
                    + "6- register a course.\n"
                    + "7- show all courses.\n8- show main details of courses.\n9- show registered courses.\n10- show course grade.\n 11-show all instructors.\n"
                    + "12- show courses that are near to start.\n 13-show courses that near to end.\n" 
                    + "14- Take A Survey.\n15- logout.");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter phone number: ");
                    phone = input.next();
                    sUpdate.UpdatePhone(ID, phone);
                    break;
                case 2:
                    System.out.print("Enter email: ");
                    email = input.next();
                    sUpdate.UpdateEmail(ID, email);
                    break;
                case 3:
                    System.out.print("Enter address: ");
                    address = input.next();
                    sUpdate.UpdateAddress(ID, address);
                    break;
                case 4:
                    System.out.print("Enter password: ");
                    password = input.next();
                    sUpdate.updatePassword(ID, password);
                    break;
                case 5:
                    System.out.print("Enter phone number: ");
                    phone = input.next();
                    System.out.print("Enter email: ");
                    email = input.next();
                    System.out.print("Enter address: ");
                    address = input.next();
                    sUpdate.UpdateAll(ID, address, email, phone);
                    break;
                case 6:
                    System.out.print("Enter course ID: ");
                    courseID = input.next();
                    rc.regesterCourse(ID, courseID);
                    break;
                case 7:
                    sShow.showAllCourse();
                    break;
                case 8:
                    sShow.showMDCourse();
                    break;
                case 9:
                    sShow.showRegesteredCourses(ID);
                    break;
                case 10:
                    System.out.print("Enter course ID: ");
                    courseID = input.next();
                    sShow.showGrade(courseID);
                    break;
                case 11:
                    scd.ShowInstructors();
                    break;
                case 12:
                    sct.ShowNearToStart();
                    break;
                case 13:
                    sct.ShowNearToEnd();
                    break;
                case 14:
                    System.out.println("Enter Course ID: ");
                    courseID = input.next();
                    sShow.TakeASurvey(courseID);
                    break;
                case 15:
                    con = 0;
                    break;
                default:
                    System.out.println("Unavailable choice.");
            }
        } while (con == 1);
    }
}
