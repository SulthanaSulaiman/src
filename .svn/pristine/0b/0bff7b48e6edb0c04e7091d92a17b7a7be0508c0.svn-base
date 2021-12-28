/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.generic;

import java.sql.*;
import java.util.*;
import connection.DBconnection;
/**
 *
 * @author ramesh
 */
public class PFKeyHandler {

    DBconnection conProp = new DBconnection();
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    private String countSql = "";
    private String intSql = "";
    private int recordAdded = 0;
    private String insertField="";
    private String preInsertSql="";
    private String postInsertSql="";
    private String createdId = "";
    private int newId=0;
    private String confirmProjAdd = "";
    private String midInsertSql="";
    private String InsertSql="";



    public void setCountSql(String countSql){
        this.countSql = countSql;
    }
    
   public void setInsertField(String insertField){
        this.insertField = insertField;
    }
    
    public void setPreInsertSql(String preInsertSql){
        this.preInsertSql = preInsertSql;//this varaible forms the insert query first part. Next append the Insertfield variable. The incremented ID value in the CountSql will then have to be appended with the sql and then the postInsertSQl should be added with this
    }

    public void setMidInsertSql(String midInsertSql){
        this.midInsertSql = midInsertSql;
    }

    public void setPostInsertSql(String postInsertSql){
        this.postInsertSql = postInsertSql;
    }

    public void setProjAdd(String confirmProjAdd){
        this.confirmProjAdd = confirmProjAdd;
    }



    public int getRecordadded(){
        return recordAdded;
    }
    
    public String getcreatedId(){
        return createdId;
    }

    public void createNewId(){
         
         

      try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
//execute the next count of the primary key in a table
            rs = stmt.executeQuery(countSql);

            while(rs.next()){
                createdId = rs.getString(1);
                if(rs.wasNull()){
                    createdId = "0";
                }
            }
           newId=Integer.parseInt(createdId);
           newId++;
            //System.out.println("nextId in PFKeyHandler : "+newId);
            
// insert all the values using the insert statement
            InsertSql=preInsertSql+insertField+midInsertSql;
            if(confirmProjAdd.equals("1"))
            {
               InsertSql+=" '"+newId+"','"+newId+"', " ;
            }
            else{
               InsertSql+=" '"+newId+"', ";
            }
            InsertSql+=postInsertSql;
            //System.out.println("InsertSql:"+InsertSql);
            recordAdded = stmt.executeUpdate(InsertSql);

            if(recordAdded>0){
                createdId=String.valueOf(newId);
            }
            else{
                createdId="";
            }

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
      
    }



}
