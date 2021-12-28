/*
 * UnitsList.java
 *
 * Created on February 17, 2010, 1:04 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;
import pathfinder.functions.generic.ReturnCodes;

import java.io.Serializable;
import java.util.*;
import connection.DBconnection;
import java.sql.*;
/**
 *
 * @author ramyamaims
 */
public class CostCenter extends ReturnCodes {

//to get the list of Units from units_master table

    private List category_id = new ArrayList();
    private List category_name = new ArrayList();
    private List costCenter_id = new ArrayList();
    private List itemName = new ArrayList();
    private List itemId = new ArrayList();
    private List itemType = new ArrayList();
    private String categoryId="";

   
    private String temp_val = "";

    DBconnection dbcon = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private int returnCode = SUCCESS;
    private String returnMessage = "";


    public void setCategoryId(String categoryId)
    {
        this.categoryId = categoryId;
    }
    
     public int getReturnCode() {
        return returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

      public List getCostCenterID(){

        return costCenter_id;
    }

      public void setCostCenterId(List costCenter_id)
      {
          this.costCenter_id=costCenter_id;
      }
    public List getCategoryID(){

        return category_id;
    }

 public List getItemName(){
        return itemName;
    }

 public void setItemId(List itemId)
 {
     this.itemId = itemId;
 }

 public List getItemType()
 {
     return itemType;
 }

 public void setItemType(List itemType)
 {
     this.itemType = itemType;
 }

    public List getCategoryName(){
        return category_name;
    }



    public void setCategoryName(List category_name)
    {
        this.category_name = category_name;
    }

    public void setCategoryId(List category_id)
    {
        this.category_id = category_id;
    }

    

    public void collectCategoryList(){

        try{
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "SELECT category_id,category ";
            sql_from = "from costcenter_category ";

            String where="";
            String condition="";

            category_id.clear();
            category_name.clear();
           
            sql_select += sql_from;

            rs = stmt.executeQuery(sql_select);

            while(rs.next()){

                temp_val =  rs.getString(1);
                if(rs.wasNull()) {
                    temp_val="";
                }
                category_id.add(temp_val);

                temp_val =  rs.getString(2);
                if(rs.wasNull()) {
                    temp_val="";
                }
                category_name.add(temp_val);

                
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in collecting collectCategoryList:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in collecting collectCategoryList:"+e);
        }
    }

public int updateCategoryList() {

        //System.out.println("updateCategoryList");

        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql = "";
        int recordsUpdated = 0;

        try {


            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            //System.out.println("lineItemId:" + category_id.size());
            for (int i = 0; i < category_id.size(); i++) {
                sql = "UPDATE costcenter_category "
                        + "SET category='" + category_name.get(i) + "'"
                        + "WHERE category_id=" + category_id.get(i) + "";
                //System.out.println("sql:" + sql);
                stmt.addBatch(sql);



            }
            int[] count = stmt.executeBatch();

            for (int i = 0; i < count.length; i++) {

                if (count[i] == 1) {
                    recordsUpdated++;
                }
            }
            //System.out.println("----recordsUpdated:" + recordsUpdated);

        } catch (SQLException sqle) {
            System.out.println("SQLException in updateCategoryList:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in updateCategoryList:" + e);
        } finally {



            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection:se.getMessage()");
                }
            }

        }

        return recordsUpdated;
    }

      public int saveCategory()
     {
         //System.out.println("saveCategory");

          Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql="";
        int recordsUpdated = 0;

        try
        {


            con = dbcon.getSampleProperty();


        for(int i=0;i<category_name.size();i++)
        {
         sql="INSERT INTO costcenter_category(category) VALUES(?)";


           stmt = con.prepareStatement(sql);
           stmt.setString(1,category_name.get(i).toString());
           

         recordsUpdated = stmt.executeUpdate();
//System.out.println("----recordsUpdated:"+recordsUpdated);
        }
          } catch (SQLException sqle) {
            System.out.println("SQLException in saveCategory:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in saveCategory:" + e);
        } finally {



            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection:se.getMessage()");
                }
            }

        }

return recordsUpdated;
     }


     
      
      //This methhod returns list of line items allocated to cost category
    public void getAllocLineItems() {
        //System.out.println("getAllocLineItems");
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();

        try {

            con = dbcon.getSampleProperty();



            String sql = "SELECT c.costcenter_id,bm.name,bm.billing_item_id FROM billing_lineitems_master bm,cost_center c "
                    + " WHERE bm.billing_item_id=c.item_id and c.category_id='" +categoryId + "'";



          

            //System.out.println("alloc cost query:"+sql);

            stmt = con.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs != null) {
                    costCenter_id.add(rs.getString(1));
                    itemName.add(rs.getString(2));
                  
                }
            }

             //System.out.println("costCenter_id:"+costCenter_id.size());
        } catch (SQLException sqle) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("SQLException in getAllocLineItems:" + sqle);

        } catch (Exception e) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in getAllocLineItems:" + e);

        } finally {

            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {

                    returnCode = SYSTEM_PROBLEM;
                    returnMessage = SYSTEM_PROBLEM_DESC;
                    System.out.println("Exception while closing resultset:se.getMessage()");

                }

            }

            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {

                    returnCode = SYSTEM_PROBLEM;
                    returnMessage = SYSTEM_PROBLEM_DESC;
                    System.out.println("Exception while closing Statement:se.getMessage()");

                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {

                    returnCode = SYSTEM_PROBLEM;
                    returnMessage = SYSTEM_PROBLEM_DESC;
                    System.out.println("Exception while closing connection:se.getMessage()");

                }
            }

        }

        returnCode = SUCCESS;


    }


 //This method is used to allocate cost center to items like line items etc.
    public int saveCostCenter() {
        //System.out.println("saveCostCenter");
        Connection con = null;

        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        int size = 0;
          int recordsInserted = 0;

        if(itemId!=null)
             size = itemId.size();
        else
        {
            returnCode = BAD_REQUEST;
            returnMessage = "Select line item to allocate cost";
            return returnCode;
        }

        if(size==0)
        {
            returnCode = BAD_REQUEST;
            returnMessage = "Select line item to allocate cost";
            return returnCode;

        }

        try {

            con = dbcon.getSampleProperty();
            stmt = con.createStatement();


            for (int i = 0; i < size; i++) {
               /* stmt.setDouble(1, empOverheadVal);
                stmt.setString(2, empId.get(i).toString());*/

                stmt.addBatch( "INSERT INTO cost_center (category_id,item_id,item_type) VALUES(" + category_id.get(i) + ",'" + itemId.get(i) + "','" + itemType.get(i) + "')");

            }

            int[] count = stmt.executeBatch();


            for (int i = 0; i < count.length; i++) {

                if (count[i] == 1) {
                    recordsInserted++;
                }
            }


        } catch (BatchUpdateException b) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.err.println("SQLException: " + b.getMessage());
             return returnCode;
        } catch (SQLException sqle) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("SQLException in saveCostCenter:" + sqle);
             return returnCode;

        } catch (Exception e) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in saveCostCenter:" + e);
             return returnCode;

        } finally {

            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {

                    returnCode = SYSTEM_PROBLEM;
                    returnMessage = SYSTEM_PROBLEM_DESC;
                    System.out.println("Exception while closing Statement:se.getMessage()");
                     return returnCode;

                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {

                    returnCode = SYSTEM_PROBLEM;
                    returnMessage = SYSTEM_PROBLEM_DESC;
                    System.out.println("Exception while closing connection:se.getMessage()");
                     return returnCode;

                }
            }

        }
        returnCode = SUCCESS;
        returnMessage = "Allocated successfully";
         return returnCode;

    }



    //This method is used to deallocate cost center to items like line items etc.
    public int deleteCostCenter() {
        //System.out.println("deleteCostCenter");
        Connection con = null;

        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        int size = 0;
          int recordsDeleted = 0;

        if(costCenter_id!=null)
        {
             size = costCenter_id.size();

          //System.out.println("Size:"+size);
        }
        else
        {
            returnCode = BAD_REQUEST;
            returnMessage = "Select line item to deallocate cost";
            return returnCode;
        }

        if(size==0)
        {
            returnCode = BAD_REQUEST;
            returnMessage = "Select line item to allocate cost";
            return returnCode;

        }

        try {

            con = dbcon.getSampleProperty();
            stmt = con.createStatement();


            for (int i = 0; i < size; i++) {
               /* stmt.setDouble(1, empOverheadVal);
                stmt.setString(2, empId.get(i).toString());*/

                stmt.addBatch( "DELETE FROM cost_center WHERE costcenter_id="+ costCenter_id.get(i));

            }

            int[] count = stmt.executeBatch();


            for (int i = 0; i < count.length; i++) {

                if (count[i] == 1) {
                    recordsDeleted++;
                }
            }


        } catch (BatchUpdateException b) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.err.println("SQLException: " + b.getMessage());
             return returnCode;
        } catch (SQLException sqle) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("SQLException in deleteCostCenter:" + sqle);
             return returnCode;

        } catch (Exception e) {

            returnCode = SYSTEM_PROBLEM;
            returnMessage = SYSTEM_PROBLEM_DESC;
            System.out.println("Exception in deleteCostCenter:" + e);
             return returnCode;

        } finally {

            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {

                    returnCode = SYSTEM_PROBLEM;
                    returnMessage = SYSTEM_PROBLEM_DESC;
                    System.out.println("Exception while closing Statement:se.getMessage()");
                     return returnCode;

                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {

                    returnCode = SYSTEM_PROBLEM;
                    returnMessage = SYSTEM_PROBLEM_DESC;
                    System.out.println("Exception while closing connection:se.getMessage()");
                     return returnCode;

                }
            }

        }
        returnCode = SUCCESS;
        returnMessage = "Deallocated successfully";
         return returnCode;

    }

}
