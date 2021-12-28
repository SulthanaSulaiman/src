/*
 * POStatusList.java
 *
 * Created on February 17, 2010, 12:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;

import java.io.Serializable;
import java.util.*;
import connection.DBconnection;
import java.sql.*;
/**
 *
 * @author ramyamaims
 */
public class POStatusList {
    
    private List po_statusId = new ArrayList();
    private List po_status = new ArrayList();
    
    private String temp_val = "";
    private String status_type = "";
    
    DBconnection dbcon = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    
    public void setPOStatusType(String status_type){
        this.status_type = status_type;
    }
    
    public List getStatusID(){
        //System.out.println("po_statusId in poStatusList of pathfinder.functions.revenue : "+po_statusId);
        return po_statusId;
    }

    public List getStatusName(){
        return po_status;
    }
    
    public void collectPOStatusList(){

        try{
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();
            
            sql_select = "select status_id,status ";
            sql_from = "from status ";            
            sql_where="status_type='"+status_type+"' ";
            
            po_statusId.clear();
            po_status.clear();
            
            sql_select += sql_from+"where "+sql_where;
            //System.out.println("sql_select in poStatusList of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            
            while(rs.next()){
                
                temp_val =  rs.getString(1);
                if(rs.wasNull()) {
                    temp_val="";
                }
                po_statusId.add(temp_val);
                
                temp_val =  rs.getString(2);
                if(rs.wasNull()) {
                    temp_val="";
                }
                po_status.add(temp_val);           
            }          
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in collecting poStatusList:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in collecting poStatusList:"+e);
        }
    }
}
