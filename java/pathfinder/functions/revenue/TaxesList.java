/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;


import java.io.Serializable;
import java.util.*;
import connection.DBconnection;
import java.sql.*;
/**
 *
 * @author ramesh
 * class to retrieve the Contact Name List based on their type or whole list
 */
public class TaxesList implements Serializable {


    private List taxId = new ArrayList();
    private List taxName = new ArrayList();
    private List taxDesc = new ArrayList();
    private List taxCat = new ArrayList();
    
    private String temp_val = "";
    
    DBconnection dbcon = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";

        public List getTaxID(){
        //System.out.println("taxId in taxList of pathfinder.functions.revenue : "+taxId);
        return taxId;
    }

   public void setTaxID(List taxId)
    {
        this.taxId = taxId;
    }

    public List getTaxName(){
        return taxName;
    }

    public void setTaxName(List taxName)
    {
        this.taxName=taxName;
    }


    public List getTaxDesc(){
        return taxDesc;
    }

     public void setTaxDesc(List taxDesc)
    {
        this.taxDesc = taxDesc;
    }


    public List getTaxCat(){
        return taxCat;
    }


    public void collectTaxList(){

        try{
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();
            
            sql_select = "select tax_id,name,description,type ";
            sql_from = "from tax_and_addons ";
                        
            taxId.clear();
            taxName.clear();
            taxDesc.clear();
            taxCat.clear();
            
            sql_select += sql_from;
            //System.out.println("sql_select in taxList of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            while(rs.next()){
                
                temp_val =  rs.getString(1);
                if(rs.wasNull()) {
                    temp_val="";
                }
                taxId.add(temp_val);
                
                temp_val =  rs.getString(2);
                if(rs.wasNull()) {
                    temp_val="";
                }
                taxName.add(temp_val);
                
                temp_val =  rs.getString(3);
                if(rs.wasNull()) {
                    temp_val="";
                }
                taxDesc.add(temp_val);
                
                temp_val =  rs.getString(4);
                if(rs.wasNull()) {
                    temp_val="";
                }
                taxCat.add(temp_val);                
            }          
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in collecting taxList:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in collecting taxList:"+e);
        }
    }

     public int saveTaxes()
     {
         //System.out.println("saveTaxes");

          Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql="";
        int recordsUpdated = 0;

        try
        {


            con = dbcon.getSampleProperty();


        for(int i=0;i<taxName.size();i++)
        {
         sql="INSERT INTO tax_and_addons(NAME,description) VALUES(?,?)";


           stmt = con.prepareStatement(sql);
           stmt.setString(1,taxName.get(i).toString());
           stmt.setString(2,taxDesc.get(i).toString());

         recordsUpdated = stmt.executeUpdate();
//System.out.println("----recordsUpdated:"+recordsUpdated);
        }
          } catch (SQLException sqle) {
            System.out.println("SQLException in saveTaxes:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in saveTaxes:" + e);
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

      public int updateTaxList() {

        //System.out.println("updateTaxList");

        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql = "";
        int recordsUpdated = 0;

        try {


            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            //System.out.println("lineItemId:" + taxId.size());
            for (int i = 0; i < taxId.size(); i++) {
                sql = "UPDATE tax_and_addons "
                        + "SET NAME='" + taxName.get(i) + "', "
                        + "description='" + taxDesc.get(i) + "' "
                        + "WHERE tax_id=" + taxId.get(i) + "";
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
            System.out.println("SQLException in updateTaxList:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in updateTaxList:" + e);
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


}
