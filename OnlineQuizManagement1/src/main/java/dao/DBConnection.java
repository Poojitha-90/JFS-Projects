package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

 static Connection con;
 
 public static Connection getConnection() {
  try {
   if(con==null) {
    Class.forName("com.mysql.cj.jdbc.Driver");
       con = DriverManager.getConnection(
           "jdbc:mysql://localhost:3306/OnlineQuiz", "root", "");
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