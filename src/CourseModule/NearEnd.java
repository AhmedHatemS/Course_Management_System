import MainDriver.DBconnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java. util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class NearEnd {
    static Connection connection;
    static Statement ss;
    static String query;
    static ResultSet r;
    DBconnect c1 = new DBconnect();
    private String EndDate;
    private String CourseName;

    public NearEnd(String EndDate, String CourseName) {
        this.EndDate = EndDate;
        this.CourseName = CourseName;
    }

    public NearEnd() {
    }
    Calendar calendar = Calendar.getInstance();
        

   LocalDate date = LocalDate.now();
   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void ShowData() throws ClassNotFoundException{
 try {
            ArrayList<NearEnd> List =new ArrayList();
           
            connection = c1.connect();
            ss = connection.createStatement();
           
           query="select * from courses";
           r=ss.executeQuery(query);
           System.out.println("Courses near to end are:");
            while(r.next()){
                List.add(new NearEnd(r.getString("endDate"),r.getString("courseName")));
            }
            
            
           for(int i=0;i<List.size();i++){
                
                LocalDate firstDate ;
                firstDate = LocalDate.parse(List.get(i).EndDate, formatter);
              
                
               
               long days = ChronoUnit.DAYS.between(date, firstDate);
            if(date.compareTo(firstDate)  <0) {
               if(days<=7){
                   System.out.println("-"+List.get(i).CourseName);
               }}
              
           }
       

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                connection.close();
                ss.close();
            } catch (SQLException se) {

            }
        }
    }

    
}
