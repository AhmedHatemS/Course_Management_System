package StudentModule;

import AdminModule.GetAccess;
import MainDriver.DBconnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentShow {

    private static Connection c;
    private static Statement ss;
    private static String query;
    private static ResultSet rs;
    private String id;
    private String Namecourse;
    private String parentcourse;
    private int price;
    private int daysOfCourses;
    private String StartDate;
    private String EndDate;
    private DBconnect c1 = new DBconnect();
    private String[] questions = {"The course was helpful.", "The instructor is enthusiastic and knowledgable about the course material.", "The instructor cleared up points of confusions.", "The course was effictively organized.", "The course developed my ability to read and think critically."};
    private String choice1 = "1) Strongly Agree";
    private String choice2 = "2) Argee";
    private String choice3 = "3) No Opinion";
    private String choice4 = "4) Disagree";
    private String choice5 = "5) Strongly Disagree";
    private String Response1 = "";
    private String Response2 = "";
    private String Response3 = "";
    private String Response4 = "";
    private String Response5 = "";
    private int surveyID;
    private int studentID;
    private String role;
    private String courseID;
    private static ResultSet r;
    private static String[] colNames = new String[1000];
    private GetAccess X = new GetAccess();

    public StudentShow(String x, String n, String p) {
        this.id = x;
        this.Namecourse = n;
        this.parentcourse = p;
    }

    public StudentShow(String x, String n, String p, String SD, String ED, int pr, int DOC) {
        this.id = x;
        this.Namecourse = n;
        this.parentcourse = p;
        this.StartDate = SD;
        this.daysOfCourses = DOC;
        this.price = pr;
        this.EndDate = ED;
    }

    public StudentShow() {
    }

    public void showGrade(String CourseID) throws SQLException, ClassNotFoundException {
        if (checkInfo(CourseID)) {
            try {
                DBconnect c1 = new DBconnect();
                c = c1.connect();
                query = "select " + courseID + " from grades where studID ='" + studentID + "'";
                r = ss.executeQuery(query);
                r.next();
                System.out.println(r.getInt("" + courseID + ""));
            } catch (SQLException e) {

            } finally {
                try {
                    c.close();
                    ss.close();
                } catch (SQLException s) {

                }
            }
        }
    }

    public void showRegesteredCourses(int studentID) throws SQLException, ClassNotFoundException {
        this.studentID = studentID;
        if (foundStudentID()) {
            try {
                c = c1.connect();
                ss = c.createStatement();
                saveCoursesNames();
                for (int i = 0; colNames[i] != null; i++) {
                    query = "SELECT COUNT (regesteredCourses." + colNames[i] + ") AS f FROM regesteredCourses WHERE studID = '" + this.studentID + "' AND regesteredCourses." + colNames[i] + " = '1'";
                    rs = ss.executeQuery(query);
                    rs.next();
                    if (rs.getInt("f") == 1) {
                        System.out.print(colNames[i] + " ");
                    }
                }
                System.out.println("");
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void showMDCourse() throws ClassNotFoundException {
        try {
            ArrayList<StudentShow> list = new ArrayList<StudentShow>();

            c = c1.connect();
            ss = c.createStatement();
            query = "select * from courses";
            rs = ss.executeQuery(query);

            System.out.println("CourseID\t\tCourseName\t\tParentCourse");
            while (rs.next()) {
                StudentShow a = new StudentShow(rs.getString("courseID"), rs.getString("courseName"), rs.getString("parentCourse"));
                list.add(a);
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i).id);
                System.out.print("\t\t\t");
                System.out.print(list.get(i).Namecourse);
                System.out.print("\t\t\t");
                System.out.print(list.get(i).parentcourse);
                System.out.println();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException ex) {
            }
        }

    }

    public void showAllCourse() throws ClassNotFoundException {
        try {
            ArrayList<StudentShow> list = new ArrayList<StudentShow>();

            c = c1.connect();
            ss = c.createStatement();
            query = "select * from courses";
            rs = ss.executeQuery(query);

            System.out.println("CourseID\t\tCourseName\t\tParentCourse\t\tStartDate\t\tEndDate\t\tDays OF Course\t\tPrice");
            while (rs.next()) {
                StudentShow b = new StudentShow(rs.getString("courseID"), rs.getString("courseName"), rs.getString("parentCourse"), rs.getString("startDate"), rs.getString("endDate"), rs.getInt("daysOfCourse"), rs.getInt("price"));
                list.add(b);
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i).id);
                System.out.print("\t\t\t");
                System.out.print(list.get(i).Namecourse);
                System.out.print("\t\t\t");
                System.out.print(list.get(i).parentcourse);
                System.out.print("\t\t\t");
                System.out.print(list.get(i).StartDate);
                System.out.print("\t\t");
                System.out.print(list.get(i).EndDate);
                System.out.print("\t\t");
                System.out.print(list.get(i).price);
                System.out.print("\t\t");
                System.out.print(list.get(i).daysOfCourses);
                System.out.print("\t");

                System.out.println();
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException s) {
            }

        }

    }

    private void returnStudentID() {
        studentID = X.returnUserID();
    }

    private void returnR() throws SQLException, ClassNotFoundException {
        role = X.returnRole();
    }

    private boolean foundStudentID() throws ClassNotFoundException, SQLException {
        DBconnect c1 = new DBconnect();
        c = c1.connect();
        ss = c.createStatement();
        try {
            query = "SELECT COUNT (mainInfo.UserId) AS id FROM mainInfo WHERE mainInfo.UserId LIKE '" + this.studentID + "'";
            r = ss.executeQuery(query);
            r.next();
            if (r.getInt("id") == 1) {
                return true;
            }
        } catch (SQLException e) {

        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException e) {
            }
        }
        return false;
    }

    private static void saveCoursesNames() throws SQLException, ClassNotFoundException {
        DBconnect c1 = new DBconnect();
        c = c1.connect();
        ss = c.createStatement();
        try {
            query = "SELECT INFORMATION_SCHEMA.COLUMNS.COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE 'regesteredCourses'";
            r = ss.executeQuery(query);
            if (r.next()) {
                do {
                    for (int i = 0; i < 1000; i++) {
                        colNames[i] = r.getString(1);
                        if (!r.next()) {
                            break;
                        }
                    }
                } while (r.next());
            }
            for (int i = 0; colNames[i] != null; i++) {
                colNames[i] = colNames[i + 1];
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private boolean checkRegesteredCourses(String CourseID) throws SQLException, ClassNotFoundException {

        if (foundStudentID()) {
            saveCoursesNames();
            DBconnect c1 = new DBconnect();
            c = c1.connect();
            ss = c.createStatement();
            for (int i = 0; colNames[i] != null; i++) {
                query = "SELECT COUNT (regesteredCourses." + colNames[i] + ") AS f FROM regesteredCourses WHERE studID = '" + this.studentID + "' AND regesteredCourses." + colNames[i] + " = '1'";
                r = ss.executeQuery(query);
                r.next();
                if ((r.getInt("f") == 1) && (colNames[i].equals(CourseID))) {
                    return true;
                }
            }
        }
        return false;
    }

    StudentShow(String CourseID) {
        courseID = CourseID;
    }

    public void setQuestion1(String s) {
        questions[0] = s;
    }

    public void setQuestion2(String s) {
        questions[1] = s;
    }

    public void setQuestion3(String s) {
        questions[2] = s;
    }

    public void setQuestion4(String s) {
        questions[3] = s;
    }

    public void setQuestion5(String s) {
        questions[4] = s;
    }

    public String getQuestion1() {
        return questions[0];
    }

    public String getQuestion2() {
        return questions[1];
    }

    public String getQuestion3() {
        return questions[2];
    }

    public String getQuestion4() {
        return questions[3];
    }

    public String getQuestion5() {
        return questions[4];
    }

    public void setChoice1(String s) {
        choice1 = s;
    }

    public void setChoice2(String s) {
        choice2 = s;
    }

    public void setChoice3(String s) {
        choice3 = s;
    }

    public void setChoice4(String s) {
        choice4 = s;
    }

    public void setChoice5(String s) {
        choice5 = s;
    }

    public String getChoice1() {
        return choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public String getChoice5() {
        return choice5;
    }

    public void printChoices() {
        System.out.println(choice1);
        System.out.println(choice2);
        System.out.println(choice3);
        System.out.println(choice4);
        System.out.println(choice5);
    }

    public void setResponse1(int x) {
        if (x == 1) {
            Response1 = "Strongly Agree";
        } else if (x == 2) {
            Response1 = "Agree";
        } else if (x == 3) {
            Response1 = "No Opinion";
        } else if (x == 4) {
            Response1 = "Disagree";
        } else if (x == 5) {
            Response1 = "Strongly Disagree";
        } else {
            System.out.println("INVALID CHOICE!");
        }
    }

    public void setResponse2(int x) {
        if (x == 1) {
            Response2 = "Strongly Agree";
        } else if (x == 2) {
            Response2 = "Agree";
        } else if (x == 3) {
            Response2 = "No Opinion";
        } else if (x == 4) {
            Response2 = "Disagree";
        } else if (x == 5) {
            Response2 = "Strongly Disagree";
        } else {
            System.out.println("INVALID CHOICE!");
        }
    }

    public void setResponse3(int x) {
        if (x == 1) {
            Response3 = "Strongly Agree";
        } else if (x == 2) {
            Response3 = "Agree";
        } else if (x == 3) {
            Response3 = "No Opinion";
        } else if (x == 4) {
            Response3 = "Disagree";
        } else if (x == 5) {
            Response3 = "Strongly Disagree";
        } else {
            System.out.println("INVALID CHOICE!");
        }
    }

    public void setResponse4(int x) {
        if (x == 1) {
            Response4 = "Strongly Agree";
        } else if (x == 2) {
            Response4 = "Agree";
        } else if (x == 3) {
            Response4 = "No Opinion";
        } else if (x == 4) {
            Response4 = "Disagree";
        } else if (x == 5) {
            Response4 = "Strongly Disagree";
        } else {
            System.out.println("INVALID CHOICE!");
        }
    }

    public void setResponse5(int x) {
        if (x == 1) {
            Response5 = "Strongly Agree";
        } else if (x == 2) {
            Response5 = "Agree";
        } else if (x == 3) {
            Response5 = "No Opinion";
        } else if (x == 4) {
            Response5 = "Disagree";
        } else if (x == 5) {
            Response5 = "Strongly Disagree";
        } else {
            System.out.println("INVALID CHOICE!");
        }
    }

    public String getResponse1() {
        return Response1;
    }

    public String getResponse2() {
        return Response2;
    }

    public String getResponse3() {
        return Response3;
    }

    public String getResponse4() {
        return Response4;
    }

    public String getResponse5() {
        return Response5;
    }

    private void insertResponses() throws ClassNotFoundException {

        try {
            DBconnect c1 = new DBconnect();
            c = c1.connect();
            ss = c.createStatement();
            query = "insert into surveyMainTable values('" + studentID + "','" + courseID + "')";
            ss.execute(query);
        } catch (SQLException e) {

        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException s) {
            }
        }

        try {
            DBconnect c1 = new DBconnect();
            c = c1.connect();
            ss = c.createStatement();
            query = "select surveyID from surveyMainTable where studID = '" + studentID + "' AND courseID = '" + courseID + "'";
            r = ss.executeQuery(query);
            r.next();
            surveyID = r.getInt("surveyID");
        } catch (SQLException e) {

        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException s) {
            }
        }

        try {
            DBconnect c1 = new DBconnect();
            c = c1.connect();
            ss = c.createStatement();
            query = "insert into surveyResponses values('" + surveyID + "','" + Response1 + "','" + Response2 + "','" + Response3 + "','" + Response4 + "','" + Response5 + "')";
            ss.execute(query);
        } catch (SQLException e) {

        } finally {
            try {
                c.close();
                ss.close();
            } catch (SQLException s) {
            }
        }
    }

    public void TakeASurvey(String courseID) throws ClassNotFoundException, SQLException {
        Scanner input = new Scanner(System.in);
        StudentShow s = new StudentShow(courseID);
        s.returnStudentID();
        s.returnR();
        if (!s.foundStudentID()) {
            System.out.println("StudentID Not Found!");
            return;
        } else if (!"student".equals(s.role)) {
            System.out.println("Not Student.");
            return;
        } else if (!s.checkRegesteredCourses(courseID)) {
            System.out.println("CourseID Not Found!");
            return;
        } else {
            System.out.println("Survey Started!");
            System.out.println("~~~~~~");
            System.out.println();

            System.out.println(s.getQuestion1());
            s.printChoices();
            System.out.println();
            System.out.println("Enter your Response: ");
            s.setResponse1(input.nextInt());
            System.out.println();

            System.out.println(s.getQuestion2());
            s.printChoices();
            System.out.println();
            System.out.println("Enter your Response: ");
            s.setResponse2(input.nextInt());
            System.out.println();

            System.out.println(s.getQuestion3());
            s.printChoices();
            System.out.println();
            System.out.println("Enter your Response: ");
            s.setResponse3(input.nextInt());
            System.out.println();

            System.out.println(s.getQuestion4());
            s.printChoices();
            System.out.println();
            System.out.println("Enter your Response: ");
            s.setResponse4(input.nextInt());
            System.out.println();

            System.out.println(s.getQuestion5());
            s.printChoices();
            System.out.println();
            System.out.println("Enter your Response: ");
            s.setResponse5(input.nextInt());
            System.out.println();

            System.out.println("~~~~~~~");
            System.out.println("SURVEY ENDED!");
            System.out.println("THANK YOU! ^.^");

            s.insertResponses();
        }
    }

    private boolean checkInfo(String CourseID) throws SQLException, ClassNotFoundException {
        returnStudentID();
        returnR();
        if (!foundStudentID()) {
            System.out.println("StudentID not found!");
            return false;
        } else if (!"student".equals(role)) {
            System.out.println("Not Student!");
            return false;
        } else if (!checkRegesteredCourses(CourseID)) {
            System.out.println("INVALID COURSEID!");
            return false;
        } else {
            courseID = CourseID;
            return true;
        }
    }
}
