/*
 * DBconnection.java
 *
 * Created on November 1, 2006, 5:24 PM
 */

package connection;

import java.beans.*;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.awt.Toolkit;
import java.io.*;
import java.net.*;
import java.awt.*;

/**
 * @author Thanuja
 */
public class chkDBconnection extends Object implements Serializable {
   
   public Connection con;
   public Connection value;
    
    
   public Connection getSampleProperty() {
       connection();
        return con;
    }
  
 public void connection()
        {
             try
             {        
               Class.forName("com.mysql.jdbc.Driver");
               con = DriverManager.getConnection("jdbc:mysql://10.1.1.39:3306/secdbbkup?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "root", "root");
             		//con = DriverManager.getConnection("jdbc:mysql://localhost:3307/s4", "pathfinder", "apple");
   
             }
             catch(SQLException e)
                 {
                   System.out.println(e);
                 }
                 catch(ClassNotFoundException e){
                   System.out.println("unable to load"+ e);
            }
    }
    
    
}
