package StudentModule;
import MainDriver.DBconnect;
import static MainDriver.DBconnect.connect;
import static java.lang.System.in;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class Update  {
    static Connection c;
    static Statement ss;
    static String query;
    static ResultSet rs;
    public void UpdatePhone() throws ClassNotFoundException
    {
   
      DBconnect c1=new  DBconnect();
        try {
            c=c1.connect();
             ss=c.createStatement();
    Scanner s=new Scanner(System.in);
    System.out.println("StudentID:");
       int id =s.nextInt();
     System.out.println("Phone:");
       String Phone=s.next();
      query ="update student set Phone='"+Phone+"' where studID='"+id+ "'";
        ss.execute(query);
        System.out.println("Updated");
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }
        finally{
            try{
                c.close();
                ss.close();
            }catch(SQLException s){}
           
            }
    }
    //Function Update Name
   public void UpdateName() throws ClassNotFoundException
    {
   
        DBconnect c1=new  DBconnect();
        try {
            c=c1.connect();
             ss=c.createStatement();
    Scanner s=new Scanner(System.in);
     System.out.println("StudentID:");
       int id =s.nextInt();
     System.out.println("Name:");
       String FName=s.next();
       String LName=s.next();
      query ="update student set studFirstName='"+FName+"',studLastName='"+LName+"'where studID='"+id+ "'";
        ss.execute(query);
        System.out.println("Updated");
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }
        finally{
            try{
                c.close();
                ss.close();
            }catch(SQLException s){}
           
            }
        }
   //Function Update Emails
     public void UpdateEmail() throws ClassNotFoundException
    {
   
        DBconnect c1=new  DBconnect();
        try {
            c=c1.connect();
             ss=c.createStatement();
    Scanner s=new Scanner(System.in);
    System.out.println("StudentID:");
       int id =s.nextInt();
     System.out.println("Email:");
       String Email=s.next();
      query ="update student set Email='"+Email+"' where studID='"+id+ "'";
       //query ="update student set SSN=345788996791 where studID=1";
        ss.execute(query);
        System.out.println("Updated");
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }
        finally{
            try{
                c.close();
                ss.close();
            }catch(SQLException s){}
           
            }
    }
     //Function to update address
         public void UpdateAddress() throws ClassNotFoundException
    {
   
       DBconnect c1=new  DBconnect();
        try {
            c=c1.connect();
             ss=c.createStatement();
    Scanner s=new Scanner(System.in);
   System.out.println("StudentID:");
       int id =s.nextInt();
     System.out.println("Address:");
  String Address=s.next();  
      query ="update student set Address='"+Address+"' where studID='"+id+ "'";
      
        ss.execute(query);
        System.out.println("Updated");
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }
        finally{
            try{
                c.close();
                ss.close();
            }catch(SQLException s){}
           
            }
    }
         //Function to update Nationality
             public void UpdateNationality() throws ClassNotFoundException
    {
   
        DBconnect c1=new  DBconnect();
        try {
            c=c1.connect();
             ss=c.createStatement();
    Scanner s=new Scanner(System.in);
     System.out.println("StudentID:");
       int id =s.nextInt();
     System.out.println("Nationality:");
       String n=s.next();
      query ="update student set Nationality='"+n+"' where studID='"+id+ "'";
        ss.execute(query);
        System.out.println("Updated");
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }
        finally{
            try{
                c.close();
                ss.close();
            }catch(SQLException s){}
           
            }
    }
      public void UpdateAll() throws SQLException, SQLException, SQLException, ClassNotFoundException
    {
   
        DBconnect c1=new  DBconnect();
        try {
            c=c1.connect();
             ss=c.createStatement();
    Scanner s=new Scanner(System.in);
     System.out.println("StudentID:");
       int id =s.nextInt();
     System.out.println("Name:");
       String FName=s.next();
       String LName=s.next();
        System.out.println("Nationality:");
       String Nat=s.next();
        System.out.println("Address:");
       String Address=s.next();
         System.out.println("Email:");
       String Email=s.next();
         System.out.println("Phone:");
       String Phone=s.next();
      query ="update student set studFirstName='"+FName+"',studLastName='"+LName+"',Nationality='"+Nat+"',Address='"+Address+"',Phone='"+Phone+"',Email='"+Email+"'where studID='"+id+ "'";
        ss.execute(query);
        System.out.println("Updated");
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }
        finally{
            try{
                c.close();
                ss.close();
            }catch(SQLException s){}
           
            }
        }  
      
 
}
