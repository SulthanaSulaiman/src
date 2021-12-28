/*
 * EstimateLineItems.java
 *
 * Created on March 3, 2010, 10:12 AM
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
public class EstimateLineItems {
// to get the estimated line item values and other info for the passed project id.
    DBconnection conProp = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private String proj_id = "";
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
    private List invoice_est_item_id = new ArrayList();
    private List line_item_no = new ArrayList();
    private String est_total = "";
    private List item_unit = new ArrayList();
      private List item_unit_id = new ArrayList();
         private List category_id = new ArrayList();
    private List category_name = new ArrayList();

    public List getInvoice_est_item_id() {
        return invoice_est_item_id;
    }

    public void setInvoice_est_item_id(List invoice_est_item_id) {
        this.invoice_est_item_id = invoice_est_item_id;
    }

     public List getCategoryId() {
        return category_id;
    }

      public List getCategoryName() {
        return category_name;
    }
    public void setProjID(String proj_id){
        this.proj_id = proj_id;
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

       public List getItemUnit()
    {
        return item_unit;
    }

    public List getItemUnitId()
    {
             return item_unit_id;
    }

    public List getLineItemNo()
    {
        return line_item_no;
    }
    
    public void collectEstLineItem(){
        
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            
            //System.out.println("Proj ID : "+proj_id);
            
            sql_select = "";
            sql_from = "";
            sql_where  = "";
            
            sql_select = "select el.item_id,blm.name,el.quantity,el.rate,el.description,el.total,u.unit_name,el.unit_id,c.category_id,cc.category ";
           
             sql_from = "from estimate e,units_master u,billing_lineitems_master blm,cost_center c "+
                       "  RIGHT JOIN estimate_lineitems el  ON c.item_id=el.item_id AND c.item_type='lineitem' "+
                       " LEFT JOIN  costcenter_category cc ON c.category_id=cc.category_id ";
            sql_where = "blm.billing_item_id = el.item_id AND u.unit_id=el.unit_id and e.est_number = el.est_number  ";


            
            if(!proj_id.equals("")){
                sql_where +="and e.proj_id = '"+proj_id+"' ";
            }
            
            sql_select +=sql_from+"where "+sql_where+ "  order by c.category_id";
            //System.out.println("sql_select in estimateLineItems of pathfinder.functions.revenue : "+sql_select);
            
            //System.out.println("SELECT : "+sql_select);
            
            rs = stmt.executeQuery(sql_select);
            
            line_item_id.clear();
            line_item.clear();
            line_item_quantity.clear();
            line_item_rate.clear();
            line_item_desc.clear();
            line_item_total.clear();
            
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
                
            }
            
            rs = stmt.executeQuery("select est_value from estimate where proj_id = '"+proj_id+"'");
            est_total = "";
            while(rs.next()){
                est_total = rs.getString(1);
                if(rs.wasNull()){
                    est_total = "";
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
    
    public void getEstimationForInvoice() {
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            sql_select = "";
            sql_select = "SELECT trim(el.item_id), el.quantity, el.total, el.est_lineitem_id,el.po_number "
                            + "FROM estimate e, estimate_lineitems el, invoice_lineitems il "
                            + "WHERE e.est_number=el.est_number "
                            + "AND el.item_id = il.item_id "
                            + "AND el.category_id = il.category_id "
                            //+ "AND el.rate = il.rate "
                            + "AND e.proj_id = '"+proj_id+"' "
                            + "GROUP BY el.est_lineitem_id "
                            + "ORDER BY el.sort_order";
            //System.out.println("Estimation Line Items for Invoice : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            line_item_quantity.clear();
            line_item_total.clear();
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
                line_item_quantity.add(temp_val);
                
                temp_val = "";
                temp_val = rs.getString(3);
                if(rs.wasNull()){
                    temp_val = "";
                }
                line_item_total.add(temp_val);
                
                temp_val = "";
                temp_val = rs.getString(4);
                if(rs.wasNull()){
                    temp_val = "";
                }
                invoice_est_item_id.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(5);
                if(rs.wasNull()){
                    temp_val = "";
                }
                line_item_no.add(temp_val);
            }
            
            rs = stmt.executeQuery("SELECT SUM(total) FROM estimate e, estimate_lineitems el WHERE e.est_number=el.est_number AND proj_id = '"+proj_id+"'");
            est_total = "";
            while(rs.next()){
                est_total = rs.getString(1);
                if(rs.wasNull()){
                    est_total = "";
                }
            }
            rs.close();
            stmt.close();
            con.close();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }
}
