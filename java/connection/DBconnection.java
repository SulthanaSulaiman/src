/*
 * DBconnection.java
 *
 * Created on April 7, 2009, 11:32 AM
 */

package connection;

import java.sql.*;
import java.util.*;
import java.io.*;


public class DBconnection extends Object implements Serializable {

   private Connection con;
   private Connection value;

   private String for_name = "";
   private String localhost_archive = "";
   private String dbName = "";
   private String localhost = "";
   private String name= "";
   private String password = "";

   public Connection getSampleProperty(){
        connection();
        return con;
   }

    public void connection(){

        /*try {

            Properties prop = new Properties();
	    FileInputStream propsInput = new FileInputStream("//MyResources//pfdb.properties");
            prop.load(propsInput);
	    propsInput.close();
            for_name = prop.getProperty("driver");
            localhost= prop.getProperty("host");
            dbName = prop.getProperty("db");
            name = prop.getProperty("username");
            password = prop.getProperty("password");
            System.out.println("for_name : "+for_name);
            System.out.println("for_name : "+for_name);
            System.out.println("localhost : "+localhost);
            System.out.println("dbName : "+dbName);
               System.out.println("name : "+name);
            System.out.println("password : "+password);

        }catch(IOException ioe){
            ioe.printStackTrace();
        }*/
        try {
          //  con = null;
            //Class.forName(for_name);
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/s4chkdb?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "pathfinder", "apple");
            //con = DriverManager.getConnection("jdbc:mysql://"+localhost+":3306/"+dbName+"?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull",name,password);
   Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pathfinderv2?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "root", "root");
        //con = DriverManager.getConnection("jdbc:mysql://10.1.1.9:3306/pathfinderv2?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "root", "root");
        //con = DriverManager.getConnection("jdbc:mysql://10.1.1.6:3306/pathfinderv2?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "root", "root");

    /*  con = DriverManager.getConnection("jdbc:mysql://10.1.6.185:3306/pathfinderv2?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "root", "root");
        con = DriverManager.getConnection("jdbc:mysql://172.16.1.9:3306/pathfinderv2?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "pfv2", "pfv2");
        con = DriverManager.getConnection("jdbc:mysql://10.1.1.9:3306/s4?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "pftest", "pftest");
        con = DriverManager.getConnection("jdbc:mysql://10.1.1.9:3306/pathfinderv2_qbend?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "pfqbend", "pfqbend");
        con = DriverManager.getConnection("jdbc:mysql://10.1.1.9:3306/qbend?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "qbend", "qbend");
        con = DriverManager.getConnection("jdbc:mysql://10.1.8.60:3306/pathfinderv2?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "root", "root");
        con = DriverManager.getConnection("jdbc:mysql://10.1.51.16:3306/apps1_s4csrvus15?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "apps1_s4csrvus15", "m^j&4op3RsZ4");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/targetdb?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "root", "root");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/s4?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "root", "root");
        con = DriverManager.getConnection("jdbc:mysql://10.1.1.239:3306/S4C_New?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull", "qbend", "magicgreen");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3307/s4", "pathfinder", "apple");
       * 
       */
        }catch (SQLException e) {
            System.out.println(e);
        }catch (ClassNotFoundException e) {
            System.out.println("unable to load"+ e);
        }
    }
}
