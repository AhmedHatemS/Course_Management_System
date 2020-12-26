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

public class ShowInst {
    static Connection connection;
    static Statement ss;
    static String query;
    static ResultSet r;
    DBconnect c1 = new DBconnect();
private String InstructorFName;
private String InstructorLName;
  public ShowInst(String InstFname,String InstLname){
    this.InstructorFName=InstFname;
    this.InstructorLName=InstLname;

    }

    ShowInst() {

    }

   

 public void ShowData(){
 try {
            ArrayList<ShowInst> List =new ArrayList();
            connection = c1.connect();
            ss = connection.createStatement();
           
           query="select * from instructor";
           r=ss.executeQuery(query);          
            while(r.next()){
                List.add(new ShowInst(r.getString("instFName"),r.getString("instlName")));
            }
        System.out.println("Instructors:");
        for(int i=0;i<List.size();i++){
        System.out.println("-"+List.get(i).InstructorFName+" "+List.get(i).InstructorLName);
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
