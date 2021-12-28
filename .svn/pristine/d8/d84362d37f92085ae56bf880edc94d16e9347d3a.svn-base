/*
 * BillingInfo.java
 *
 * Created on March 3, 2010, 3:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;
/**
 *
 * @author ramyamaims
 */
public class BillingInfo {
    
//billing information for the passed project id.
    
    connection.DBconnection conProp = new connection.DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String temp_val = "";
    
    private String bill_number = "";
    
    private String proj_id = "";
    private String proj_name = "";
    private String status_id = "";
    private String bill_status = "";
    private String bill_value = "";
    private String bill_by = "";
    
    public String getProjId(){
        return proj_id;
    }
    public String getProjName(){
        return proj_name;
    }
    public String getBillStatus(){
        return bill_status;
    }
    public String getBillValue(){
        return bill_value;
    }
    public String getBillStatusId(){
        return status_id;
    }
    
    public String getBillBy(){
        return bill_by;
    }
    
    private List item_id = new ArrayList();
    private List item_name = new ArrayList();
    private List item_rate = new ArrayList();
    private List item_quantity = new ArrayList();
    private List item_desc = new ArrayList();
    private List item_value = new ArrayList();
    
    public List getItemId(){
        return item_id;
    }
    public List getItemName(){
        return item_name;
    }
    public List getItemRate(){
        return item_rate;
    }
    public List getItemQuantity(){
        return item_quantity;
    }
    public List getItemDesc(){
        return item_desc;
    }
    public List getItemValue(){
        return item_value;
    }
    
    public void setBillNumber(String bill_number){
       // System.out.println("bill_number setting in BillingInfo pathfinder.functions.revenue : "+bill_number);
        this.bill_number = bill_number;
    }
    
    public void getBillingInfo(){
        
        try {
            con = conProp.getSampleProperty();
            stmt =con.createStatement();
            
            sql_select = "";
            sql_from = "";
            sql_where = "";
            
            sql_select = "select bill.proj_id,p.proj_name ";
            sql_from = "from billing bill, projects p ";
            sql_where = "bill.proj_id = p.proj_id ";
                        
            if(!bill_number.equals("")){
                sql_where+="and bill_number ='"+bill_number+"' ";
            }
            
            sql_select +=sql_from+"where "+sql_where;
          //  System.out.println("sql_select for display table 1 in billInfo.java of pathfinder.functions.revenue : "+sql_select);
            
            rs = stmt.executeQuery(sql_select);
            
            if(rs.next()){                
                rs = stmt.executeQuery(sql_select);
                while(rs.next()){                
                    temp_val = rs.getString(1);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    proj_id = temp_val;
                
                    temp_val = rs.getString(2);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    proj_name = temp_val;
                }
            }else{
                proj_id = "";
                proj_name = "";
            }
            
            sql_select = "";
            sql_from = "";
            sql_where = "";
            
            sql_select = "select bill.status,s.status ";
            sql_from = "from billing bill, status s ";
            sql_where = "bill.status = s.status_id ";
                        
            if(!bill_number.equals("")){
                sql_where+="and bill_number ='"+bill_number+"' ";
            }
            
            sql_select +=sql_from+"where "+sql_where;
           // System.out.println("sql_select for display table 1 in billInfo.java of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            
            if(rs.next()){
                
                rs = stmt.executeQuery(sql_select);
                while(rs.next()){
                                
                    temp_val = rs.getString(1);                
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    status_id = temp_val;
                
                    temp_val = rs.getString(2);                
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    bill_status = temp_val;
                }
            }else{
                status_id = "";
                bill_status = "";
            }
            
            sql_select = "";
            sql_from = "";
            sql_where = "";
            
            sql_select = "select bill.bill_value,bill.bill_createdby ";
            sql_from = "from billing bill ";
            
            if(!bill_number.equals("")){
                if(!sql_where.equals("")){;
                sql_where+="and ";
                }
                sql_where+="bill_number ='"+bill_number+"' ";
            }

            if(!sql_where.equals(""))
            {
                    sql_select +=sql_from+"where "+sql_where;
            }
            else
            {
                sql_select +=sql_from;
            }


           
           // System.out.println("sql_select for display table 1 in billInfo.java of pathfinder.functions.revenue.billInfo.java : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            if(rs.next()){
                rs = stmt.executeQuery(sql_select);
                while(rs.next()){                
                    temp_val = rs.getString(1);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    bill_value = temp_val;
                                    
                    temp_val = rs.getString(2);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    bill_by  = temp_val;
                }                
            }else{
                bill_value = "";
                bill_by = "";
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }
}
