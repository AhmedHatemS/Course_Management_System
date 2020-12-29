package MainDriver;

//import AdminModule.*;
import CourseModule.*;
import static java.lang.System.exit;
//import InstructorModule.*;
//import StudentModule.*;
import java.util.*;
import java.sql.*;

public class CourseModuleMain {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        manageCourses m = new manageCourses();
        String courseID;
        String courseName;
        String parentCourse;
        String branch;
        int price;
        int room;
        int instID;
        String startDate;
        String endDate;
        int startDay;
        int startMonth;
        int startYear;
        int endDay;
        int endMonth;
        int endYear;
        int daysOfCourse;
        String ch;
        String sh = "yes";
        int menuNo;
        System.out.println("Welcome in Course module you can choose an operation from this list to do:\n1-Create new course or add parent course\n2-Delete course.\n3-Update parent course.\n"
                + "4-Update course room number.\n5-Update Branch.\n6-Update price of course.\n7-Update number of days.\n8-Update start date.\n9-Update end date.\n10-Show all students."
                + "\n11-Show all Instructors.\n12-Display courses that near to start.\n13-Display courses that near to end.");
        menuNo = input.nextInt();
        switch (menuNo) {
            case 1: {
                System.out.println("You choose operation 1 (Create new course or add parent course)");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                do {
                    System.out.println("Enter Course ID: ");
                    courseID = input.nextLine();
                    System.out.println("Enter Course Name: ");
                    courseName = input.nextLine();
                    if (m.checkCourses(courseID, courseName) == false) {
                        System.out.println("This course already exists.If you want to enter another course press yes");
                        ch = input.nextLine();
                    } else {
                        System.out.println("Enter parent Course ID: ");
                        parentCourse = input.nextLine();
                        System.out.println("Enter Branch: ");
                        branch = input.nextLine();
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
                        while (m.checkInstructor() == true) {
                            System.out.println("invalid instructor id enter another one");
                            instID = input.nextInt();
                        }
                        m.addCourse(courseID, courseName, parentCourse, price,
                                room, branch, instID, startDay,
                                startMonth, startYear, endDay, endMonth, endYear, daysOfCourse);
                        System.out.println("If you want to enter another course press yes");
                        ch = input.nextLine();
                    }
                } while (ch.compareTo(sh) == 0);

                break;
            }
            
            case 2: {
                System.out.println("You choose operation 2 (Delete course)");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("Enter Course ID: ");
                courseID = input.nextLine();
                m.deleteCourse(courseID);
                break;
            }
            case 3: {
                System.out.println("You choose operation 3 (Update parent course)");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("Enter Parent Course ID: ");
                courseID = input.nextLine();
                System.out.println("Enter the new parent Course: ");
                parentCourse = input.nextLine();
                m.updateParentCourse(courseID, parentCourse);
                break;
            }
            case 4: {
                System.out.println("You choose operation 4 (Update course room number)");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("Enter Course ID: ");
                courseID = input.nextLine();
                System.out.println("Enter new room number: ");
                room = input.nextInt();
                m.updateRoom(courseID, room);
                break;
            }
            case 5: {
                System.out.println("You choose operation 5 (Update Branch)");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("Enter Course ID: ");
                courseID = input.nextLine();
                System.out.println("Enter new branch: ");
                branch = input.nextLine();
                m.updateBranch(courseID, branch);
                break;
            }
            case 6: {
                System.out.println("You choose operation 6 (Update price of course)");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("Enter Course ID: ");
                courseID = input.nextLine();
                System.out.println("Enter new price: ");
                price = input.nextInt();
                m.updatePriceOfCourse(courseID, price);
                break;
            }
            case 7: {
                System.out.println("You choose operation 7 (Update number of days)");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("Enter Course ID: ");
                courseID = input.nextLine();
                System.out.println("Enter days of course: ");
                daysOfCourse = input.nextInt();
                m.updateDaysOfCourse(courseID, daysOfCourse);
                break;
            }
            case 8: {
                System.out.println("You choose operation 8 (Update start date)");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("Enter Course ID: ");
                courseID = input.nextLine();
                System.out.println("Enter new Start in the form of date yyyy-mm-dd: ");
                startDate = input.nextLine();
                m.updateStartDate(courseID, startDate);
                break;
            }
            case 9: {
                System.out.println("You choose operation 9 (Update end date)");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("Enter Course ID: ");
                courseID = input.nextLine();
                System.out.println("Enter new end date in the form of yyyy-mm-dd: ");
                endDate = input.nextLine();
                m.updateEndDate(courseID, endDate);
                break;
            }
            case 10: {
                System.out.println("You choose operation 10 (Show all students)");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");

                break;
            }
            case 11: {
                System.out.println("You choose operation 11 (Show all Instructors)");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                break;
            }
            case 12: {
                System.out.println("You choose operation 12 (Display courses that near to start)");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                break;
            }
            case 13: {
                System.out.println("You choose operation 13 (Display courses that near to end)");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                break;
            }

            default:;
        }
    }

}
