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
        String SSN, username, password, role, firstName, lastName, DoB, phone, email, address, nationality, courseID, courseName, parentCourse, branch,
                startDate, endDate, ch, sh = "yes";

        int con = 1, price, room, instID, startDay, startMonth, startYear, endDay, endMonth, endYear, daysOfCourse;
        do {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("Choose an operation to do\n"
                    + "1- add user or admin.\n2- update password.\n"
                    + "3- add instructor.\n4- update instructor's phone.\n5- update instructor's email.\n6- update instructor's address."
                    + "\n7- update instructor's courseID.\n8- update instructor's all data."
                    + "\n9- add student.\n10- update student's phone.\n11- update student's email.\n12- update student's address."
                    + "\n13- update student's all data.\n"
                    + "14- delete user.\n15- delete instructor.\n16- delete student.\n"
                    + "17- create new course or add parent course.\n"
                    + "18- delete course.\n19- update parent course\n20- update course room number.\n"
                    + "21- update branch.\n22- update price of course.\n23- update number of days of course.\n"
                    + "24- update start date.\n25- update end date.\n"
                    + "26- logout.");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 1 (To add user or admin)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
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
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 2 (To update user or admin password)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter password: ");
                    password = input.next();
                    md.updatePassword(SSN, password);
                    break;
                case 3:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 3 (To add instructor)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
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
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 4 (To update instructor's phone.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter phone number: ");
                    phone = input.next();
                    md.updateInstructorPhone(SSN, phone);
                    break;
                case 5:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 5 (To update instructor's email.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter email: ");
                    email = input.next();
                    md.updateInstructorEmail(SSN, email);
                    break;
                case 6:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 6 (To update instructor's address.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter address: ");
                    address = input.next();
                    md.updateInstructorAddress(SSN, address);
                    break;
                case 7:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 7 (To update instructor's courseID.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter courseID: ");
                    courseID = input.next();
                    md.updateInstructorCourseID(SSN, courseID);
                    break;
                case 8:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 8 (To update instructor's all data.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
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
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 9 (To add student.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
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
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 10 (To update student's phone.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter phone number: ");
                    phone = input.next();
                    md.updateStudentPhone(SSN, phone);
                    break;
                case 11:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 11 (To update student's email.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter email: ");
                    email = input.next();
                    md.updateStudentEmail(SSN, email);
                    break;
                case 12:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 12 (To update student's address.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    System.out.print("Enter address: ");
                    address = input.next();
                    md.updateStudentAddress(SSN, address);
                    break;
                case 13:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 13 (To update student's all data.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
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
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 14 (To delete user.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    md.deleteUser(SSN);
                    break;
                case 15:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 15 (To delete instructor.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    md.deleteInstructor(SSN);
                    break;
                case 16:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 16 (To delete student.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter SSN: ");
                    SSN = input.next();
                    md.deleteUser(SSN);
                    break;
                case 17:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 17 (To create new course or add parent course)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
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
                            while (mc.checkInstructor(instID) == true) {
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
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 18 (To delete course)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("Enter Course ID: ");
                    courseID = input.next();
                    mc.deleteCourse(courseID);
                    break;
                case 19:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 19 (To update parent course)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("Enter Parent Course ID: ");
                    courseID = input.next();
                    System.out.println("Enter the new parent Course: ");
                    parentCourse = input.next();
                    mc.updateParentCourse(courseID, parentCourse);
                    break;
                case 20:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 20 (To update course room number)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("Enter Course ID: ");
                    courseID = input.next();
                    System.out.println("Enter new room number: ");
                    room = input.nextInt();
                    mc.updateRoom(courseID, room);
                    break;
                case 21:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 21 (To update branch)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("Enter Course ID: ");
                    courseID = input.next();
                    System.out.println("Enter new branch: ");
                    branch = input.next();
                    mc.updateBranch(courseID, branch);
                    break;
                case 22:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 22 (To update price of course)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("Enter Course ID: ");
                    courseID = input.next();
                    System.out.println("Enter new price: ");
                    price = input.nextInt();
                    mc.updatePriceOfCourse(courseID, price);
                    break;
                case 23:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 23 (To update number of days of course)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("Enter Course ID: ");
                    courseID = input.next();
                    System.out.println("Enter days of course: ");
                    daysOfCourse = input.nextInt();
                    mc.updateDaysOfCourse(courseID, daysOfCourse);
                    break;
                case 24:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 24 (To update start date)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("Enter Course ID: ");
                    courseID = input.next();
                    System.out.println("Enter new Start in the form of date yyyy-mm-dd: ");
                    startDate = input.next();
                    mc.updateStartDate(courseID, startDate);
                    break;
                case 25:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 25 (To update end date)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("Enter Course ID: ");
                    courseID = input.next();
                    System.out.println("Enter new end date in the form of yyyy-mm-dd: ");
                    endDate = input.next();
                    mc.updateEndDate(courseID, endDate);
                    break;
                case 26:
                     System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 26 (To logout goodbye ♥♥♥)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    con = 0;
                    break;
                default:
                    System.out.println("Unavailable choice.");
            }
        } while (con == 1);
    }

    private static void instructor() throws ClassNotFoundException, SQLException {
        int studentID;
        int grade;
        int con = 1;
        System.out.println("You will be able to control only the course you lectures.");
        do {
            System.out.println("Choose an operation to do\n"
                    + "1- add grade.\n2- publish grades.\n"
                    + "3- show all students.\n"
                    + "4- logout.");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 1 (To add grade)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter student ID: ");
                    studentID = input.nextInt();
                    System.out.print("Enter grade as digits: ");
                    grade = input.nextInt();
                    instructor.addGrade(studentID, grade);
                    break;
                case 2:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 2 (To publish grades)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    instructor.publish();
                    break;
                case 3:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 3 (To show all student)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    scd.ShowStudents();
                    break;
                case 4:
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 4 (To logout goodbye ♥♥♥)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
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
                    + "7- show all courses.\n8- show main details of courses.\n9- show registered courses.\n10- show course grade.\n11-show all instructors.\n"
                    + "12- show courses that are near to start.\n13-show courses that near to end.\n"
                    + "14- Take A Survey.\n15- logout.");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                     System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 1 (To update phone number)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter phone number: ");
                    phone = input.next();
                    sUpdate.UpdatePhone(ID, phone);
                    break;
                case 2:
                     System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 2 (To update email)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter email: ");
                    email = input.next();
                    sUpdate.UpdateEmail(ID, email);
                    break;
                case 3:
                     System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 3 (To update address)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter address: ");
                    address = input.next();
                    sUpdate.UpdateAddress(ID, address);
                    break;
                case 4:
                     System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 4 (To update password)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter password: ");
                    password = input.next();
                    sUpdate.updatePassword(ID, password);
                    break;
                case 5:
                     System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 5 (To update all data)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter phone number: ");
                    phone = input.next();
                    System.out.print("Enter email: ");
                    email = input.next();
                    System.out.print("Enter address: ");
                    address = input.next();
                    sUpdate.UpdateAll(ID, address, email, phone);
                    break;
                case 6:
                     System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 6 (To register a course.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter course ID: ");
                    courseID = input.next();
                    rc.regesterCourse(ID, courseID);
                    break;
                case 7:
                     System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 7 (To show all courses)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    sShow.showAllCourse();
                    break;
                case 8:
                     System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 8 (To show main details of courses.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    sShow.showMDCourse();
                    break;
                case 9:
                     System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 9 (To show registered courses.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println(loginID);
                    sShow.showRegesteredCourses(loginID);
                    break;
                case 10:
                     System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 10 (To show course grade.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.print("Enter course ID: ");
                    courseID = input.next();
                    sShow.showGrade(courseID);
                    break;
                case 11:
                     System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 11 (To show all instructors.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    scd.ShowInstructors();
                    break;
                case 12:
                     System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 12 (To show courses that are near to start.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    sct.ShowNearToStart();
                    break;
                case 13:
                     System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 13 (To show courses that near to end.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    sct.ShowNearToEnd();
                    break;
                case 14:
                     System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 14 (To Take A Survey.)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("Enter Course ID: ");
                    courseID = input.next();
                    sShow.TakeASurvey(courseID);
                    break;
                case 15:
                     System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("You choose operation 15 (To logout goodbye ♥♥♥)");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    con = 0;
                    break;
                default:
                    System.out.println("Unavailable choice.");
            }
        } while (con == 1);
    }
}
