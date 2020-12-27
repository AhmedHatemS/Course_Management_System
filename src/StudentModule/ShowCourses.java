package StudentModule;

import MainDriver.DBconnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class ShowCourses {
   static Connection c;
    static Statement ss;
    static String query;
      static ResultSet rs;
     private String id;
   private String Namecourse;
   private String parentcourse;
    private DBconnect c1 = new DBconnect();
       public ShowCourses (String x,String n,String p)
    {
     this.id=x;
     this.Namecourse=n;
     this.parentcourse=p;
    }

    public ShowCourses() {
      
    }

      public void ShowCourse() throws ClassNotFoundException
      {
 
     
          try {
              ArrayList<ShowCourses> list=new ArrayList<ShowCourses>();
          
            c=c1.connect();
            ss=c.createStatement(); 
            query="select * from courses";
            rs=ss.executeQuery(query);
           
             System.out.println("CourseID\t\tCourseName\t\tParentCourse");
            while(rs.next())
            {    
              ShowCourses a= new ShowCourses(rs.getString("courseID"),rs.getString("courseName"),rs.getString("parentCourse"));
                list.add(a); 
            }
         for(int i=0;i<list.size();i++)
         {
          System.out.print(list.get(i).id);
           System.out.print("\t\t\t");
           System.out.print(list.get(i).Namecourse);
            System.out.print("\t\t\t");
            System.out.print(list.get(i).parentcourse);
             System.out.println();
         }
                 
        } catch (SQLException ex){ 
           System.out.println( ex);
        }
           finally{
            try{
                c.close();
                ss.close();
            }catch(SQLException s){}
           
            }
          
}
}
