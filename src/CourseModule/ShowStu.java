import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java. util.Scanner;

public class ShowStu {
    static Connection connection;
    static Statement ss;
    static String query;
    static ResultSet r;
    DBconnect c1 = new DBconnect();
    private String StudentFName;
    private String StudentLName;


 ShowStu(){

}
ShowStu(String StuFName,String StuLName){
    this.StudentFName=StuFName;
    this.StudentLName=StuLName;

    }

public void ShowData(){
    try{
ArrayList<ShowStu> List =new ArrayList();
            connection = c1.connect();
            ss = connection.createStatement();
           query="select * from student";
           r=ss.executeQuery(query);
            while(r.next()){
                List.add(new ShowStu(r.getString("studFirstName"),r.getString("studLastName")));
            }
       System.out.println("Students:");
        for(int i=0;i<List.size();i++){
        System.out.println("-"+List.get(i).StudentFName +" "+List.get(i).StudentLName);
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

    }}
