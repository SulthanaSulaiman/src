/*
 * PFKeyHandeller.java
 *
 * Created on March 17, 2010, 11:33 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pathfinder.functions;

import java.sql.*;
import java.io.*;
import java.util.*;
import connection.DBconnection;

/**
 *
 * @author ramyamaims
 */
public class PFKeyHandeller {
// to handel the lock aqusition for a db table to allow the users in queue.    
    connection.DBconnection conProp = new connection.DBconnection();
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    private String cntSql = "";
    private String intSql = "";
    private int intResVal = 0;
    
    private int nextId = 0;
    
    public void setCntSql(String cntSql){
        this.cntSql = cntSql;
    }
    
    public void setIntSql(String intSql){
        this.intSql = intSql;
    }
    
    public int getResVal(){
        return intResVal;
    }
    
    public int getNextId(){
        return nextId;
    }
    
    public void getVal(){
        
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
//execute the next count of the primary key in a table            
            rs = stmt.executeQuery(cntSql);
            
            while(rs.next()){
                nextId = rs.getInt(1);
                if(rs.wasNull()){
                    nextId = 0;
                }
            }
            nextId++;
            
            System.out.println("nextId in PFKeyHandeller : "+nextId);
            System.out.println("intSql in PFKeyHandeller : "+intSql);
// insert all the values using the insert statement
            intResVal = stmt.executeUpdate(intSql);
            
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
         finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
}
    }
    
}
