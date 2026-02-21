package FACTORY;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConn {
 static Connection con;
 public static Connection getConn() {
  try {
   if(con==null) {
	   Class.forName("com.mysql.cj.jdbc.Driver");
       con = DriverManager.getConnection(
           "jdbc:mysql://localhost:3306/todolist", "root", "");
   }
  } catch (ClassNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (SQLException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  return con;
 }
}