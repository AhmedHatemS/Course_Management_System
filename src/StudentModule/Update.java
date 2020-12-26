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
     private int id;
    private String password;
    private String FName;
    private String LName;
    private String phone;
    private String Email;
    private String Address;
    private String Nationality;
    public void UpdatePhone(int id,String phone) throws ClassNotFoundException
    {
        this.id=id;
        this.phone = phone;
      DBconnect c1=new  DBconnect();
        try {
            c=c1.connect();
             ss=c.createStatement();
      query ="update student set Phone='"+this.phone+"' where studID='"+this.id+ "'";
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
   public void UpdateName(int ID,String FName,String LName) throws ClassNotFoundException
    {   
        this.id=id;
        this.FName=FName;
        this.LName=LName;
   
        DBconnect c1=new  DBconnect();
        try {
            c=c1.connect();
             ss=c.createStatement();
      query ="update student set studFirstName='"+this.FName+"',studLastName='"+this.LName+"'where studID='"+this.id+ "'";
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
     public void UpdateEmail(int id,String Email) throws ClassNotFoundException
    {   this.id=id;
        this.Email=Email;
   
        DBconnect c1=new  DBconnect();
        try {
            c=c1.connect();
             ss=c.createStatement();
         query ="update student set Email='"+this.Email+"' where studID='"+this.id+ "'";
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
         public void UpdateAddress(int id,String Address) throws ClassNotFoundException
    {
       this.id=id;
       this.Address=Address;
       DBconnect c1=new  DBconnect();
        try {
            c=c1.connect();
             ss=c.createStatement(); 
      query ="update student set Address='"+this.Address+"' where studID='"+this.id+ "'";
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
             public void UpdateNationality(int id,String Nationality) throws ClassNotFoundException
    {
        this.id=id;
        this.Nationality=Nationality;
        DBconnect c1=new  DBconnect();
        try {
            c=c1.connect();
             ss=c.createStatement();
      query ="update student set Nationality='"+this.Nationality+"' where studID='"+this.id+ "'";
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
      public void UpdateAll(int id,String Address,String Email,String FName,String LName,String Nationality,String phone  ) throws SQLException, SQLException, SQLException, ClassNotFoundException
    {
        this.id=id;
        this.Address=Address;
        this.Email=Email;
        this.FName=FName;
        this.LName=LName;
        this.Nationality=Nationality;
        this.phone=phone;
        
        DBconnect c1=new  DBconnect();
        try {
            c=c1.connect();
             ss=c.createStatement();
      query ="update student set studFirstName='"+this.FName+"',studLastName='"+this.LName+"',Nationality='"+this.Nationality+"',Address='"+this.Address+"',Phone='"+this.phone+"',Email='"+this.Email+"'where studID='"+this.id+ "'";
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
          public void updatePassword(int id, String password) throws ClassNotFoundException, SQLException {
        this.id=id;
        this.password = password;
       
            c = DBconnect.connect();
            ss = c.createStatement();
            try {
                 c = DBconnect.connect();
            ss = c.createStatement();
               query ="update mainInfo set mainInfo.Password='"+this.password +"' where UserID='"+this.id+ "'";
                ss.execute(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
 }
}
