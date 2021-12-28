/*
 * BillingLineItem.java
 *
 * Created on March 3, 2010, 3:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;

import java.io.*;
import java.sql.*;
import java.util.*;
import connection.DBconnection;
/**
 *
 * @author ramyamaims
 */
public class BillingLineItem {
//to get the billing line item information for the passed project id string whose status is not cancelled
    DBconnection conProp = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private String proj_id = "";
    private String status_bill_number = "";
    private String bill_number = "";
    private String temp_val = "";
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    
    private List line_item_id = new ArrayList();
    private List line_item = new ArrayList();
    private List line_item_rate = new ArrayList();
    private List line_item_desc = new ArrayList();
    private List line_item_quantity = new ArrayList();
    private List line_item_total = new ArrayList();
     private List item_unit = new ArrayList();
    private List item_unit_id = new ArrayList();
     private List category_id = new ArrayList();
    private List category_name = new ArrayList();


     public List getCategoryId() {
        return category_id;
    }

      public List getCategoryName() {
        return category_name;
    }

    private String est_total = "";
        
    public void setProjID(String proj_id){
        this.proj_id = proj_id;
    }
    
    public void setBillNumber(String bill_number){
        this.bill_number = bill_number;
    }
    
    public List getLineItemId(){
        return line_item_id;
    }
    
    public List getLineItemName(){
        return line_item;
    }
    
    public List getLineItemRate(){
        return line_item_rate;
    }

     public List getItemUnit()
    {
        return item_unit;
    }

    public List getItemUnitId()
    {
             return item_unit_id;
    }
    
    public List getLineItemDesc(){
        return line_item_desc;
    }
    
    public List getLineItemQuantity(){
        return line_item_quantity;
    }
    
    public List getLineItemTotal(){
        return line_item_total;
    }
    
    public String getEstTotal(){
        return est_total;
    }
    
    public void collectBillLineItem(){
        
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            
            sql_select = "";
            sql_from = "";
            sql_where  = "";
            
            sql_select = "select b.bill_number ";
            sql_from = "from billing b, status s ";
            sql_where = "b.status= s.status_id and s.status !='Cancelled' ";                  
            //System.out.println("proj_id in BillingLineItem 1 display : "+proj_id);

            if(!proj_id.equals("")){
                sql_where +="and b.proj_id = '"+proj_id+"' ";
            }
            
            if(!bill_number.equals("")){
                sql_where+="and b.bill_number = '"+bill_number+"' ";
            }
            
            sql_select +=sql_from+"where "+sql_where;// + " order by bill_date asc";
            status_bill_number = "";
            rs = stmt.executeQuery(sql_select);
            
            while(rs.next()){
                status_bill_number = rs.getString(1);
                if(rs.wasNull()){
                    status_bill_number = "";
                }
            }
            
            //System.out.println("status_bill_number : "+status_bill_number);
            if(!status_bill_number.equals("")){
            
                sql_select = "";
                sql_from = "";
                sql_where  = "";
            
                sql_select = "select bl.item_id,blm.name,bl.quantity,bl.rate,bl.description,bl.total,u.unit_name,bl.unit_id,c.category_id,cc.category ";

                sql_from = "from billing b,units_master u,billing_lineitems_master blm,cost_center c "+
                       "  RIGHT JOIN billing_lineitems bl ON c.item_id=bl.item_id AND c.item_type='lineitem' "+
                       " LEFT JOIN  costcenter_category cc ON c.category_id=cc.category_id ";
            sql_where = "blm.billing_item_id = bl.item_id AND u.unit_id=bl.unit_id and b.bill_number = bl.bill_number ";

            

                //System.out.println("proj_id in BillingLineItem 2 display : "+proj_id);
                if(!proj_id.equals("")){
                    sql_where +="and b.proj_id = '"+proj_id+"' ";
                }
            
                if(!bill_number.equals("")){
                    sql_where+="and bl.bill_number = '"+bill_number+"' ";
                }
            
                sql_select +=sql_from+"where "+sql_where+ "  order by c.category_id,bl.item_id";
                //System.out.println("sql_select in billingLineItems of pathfinder.functions.revenue : "+sql_select);
            
                rs = stmt.executeQuery(sql_select);
            
                line_item_id.clear();
                line_item.clear();
                line_item_quantity.clear();
                line_item_rate.clear();
                line_item_desc.clear();
                line_item_total.clear();
                 category_id.clear();
            category_name.clear();
            
                while(rs.next()){
                    temp_val = "";
                    temp_val = rs.getString(1);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    line_item_id.add(temp_val);
                
                    temp_val = "";
                    temp_val = rs.getString(2);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    line_item.add(temp_val);
                
                    temp_val = "";
                    temp_val = rs.getString(3);
                    if(rs.wasNull()){
                        temp_val = "";
                    }                
                    line_item_quantity.add(temp_val);
                
                    temp_val = "";
                    temp_val = rs.getString(4);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    line_item_rate.add(temp_val);
                
                    temp_val = "";
                    temp_val = rs.getString(5);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    line_item_desc.add(temp_val);
                
                    temp_val = "";
                    temp_val = rs.getString(6);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    line_item_total.add(temp_val);


                          temp_val = "";
                temp_val = rs.getString(7);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_unit.add(temp_val);


                  temp_val = "";
                temp_val = rs.getString(8);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_unit_id.add(temp_val);


                temp_val = "";
                temp_val = rs.getString(9);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                category_id.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(10);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                category_name.add(temp_val);

                
                }
            
                rs = stmt.executeQuery("select bill_value from billing where proj_id = '"+proj_id+"'");
                est_total = "";
                while(rs.next()){
                    est_total = rs.getString(1);
                    if(rs.wasNull()){
                        est_total = "";
                    }
                }
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException sqle) {
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }
}
