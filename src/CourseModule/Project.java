package MainDriver;

import AdminModule.*;
import CourseModule.*;
import static java.lang.System.exit;
//import InstructorModule.*;
//import StudentModule.*;
import java.util.*;
import java.sql.*;
public class Project {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        manageCourses m = new manageCourses();
        String courseID;
        String courseName;
        String parentCourse;
        String ch;
        String sh="yes";
        //String start="2002-6-1";
        do{
            System.out.println("Enter Course ID: ");
        courseID = input.nextLine();
        System.out.println("Enter Course Name: ");
        courseName = input.nextLine();
    if (m.checkCourses(courseID,courseName) == false) {
            System.out.println("This course already exists.If you want to enter another course press yes");
            ch = input.nextLine();}
    else{
           // m.updatestartDate(courseID, start);
            //m.updateParentCourse(courseID, parentCourse);
           m.addcourse(courseID,courseName);
//            m.deleteCourse(courseID);
//            m.checkparent(courseID);
           System.out.println("If you want to enter another course press yes");
           ch = input.nextLine();
                
    }}while(ch.compareTo(sh)==0);
        
    } 
    }

