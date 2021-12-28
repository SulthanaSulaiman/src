/*
 * ModeOfPaymentList.java
 *
 * Created on February 17, 2010, 1:04 PM
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
public class ModeOfPaymentList {

//to get the list of all kinds of pay mode from payment_modes_master table
    
    private List payment_mode_id = new ArrayList();
    private List payment_mode_name = new ArrayList();
    private List payment_mode_desc = new ArrayList();
    
    private String temp_val = "";
    
    DBconnection dbcon = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    
    
    public List getPayModID(){
        //System.out.println("payment_mode_id in poStatusList of pathfinder.functions.revenue : "+payment_mode_id);
        return payment_mode_id;
    }

     public void setPayModID(List payment_mode_id)
    {
        this.payment_mode_id = payment_mode_id;
    }

    public List getPayModeName(){
        return payment_mode_name;
    }

    public void setPayModeName(List payment_mode_name)
    {
        this.payment_mode_name = payment_mode_name;
    }

    public List getPayModeDes(){
        return payment_mode_desc;
    }

    public void setPayModeDes(List payment_mode_desc)
    {
        this.payment_mode_desc = payment_mode_desc;
    }
    
    public void collectPaymentModeList(){

        try{
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();
            
            sql_select = "select mode,name,description ";
            sql_from = "from payment_modes_master ";
            
            String where="";
            String condition="";
            
            payment_mode_id.clear();
            payment_mode_name.clear();
            payment_mode_desc.clear();
            
            sql_select += sql_from;
            //System.out.println("sql_select in poStatusList of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            
            while(rs.next()){
                
                temp_val =  rs.getString(1);
                if(rs.wasNull()) {
                    temp_val="";
                }
                payment_mode_id.add(temp_val);
                
                temp_val =  rs.getString(2);
                if(rs.wasNull()) {
                    temp_val="";
                }
                payment_mode_name.add(temp_val);     
                
                temp_val =  rs.getString(3);
                if(rs.wasNull()) {
                    temp_val="";
                }
                payment_mode_desc.add(temp_val);      
            }          
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in collecting ModeOfPaymentList:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in collecting ModeOfPaymentList:"+e);
        }
    }


      public int saveModeOfPayment()
     {
         //System.out.println("saveModeOfPayment");

          Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql="";
        int recordsUpdated = 0;

        try
        {


            con = dbcon.getSampleProperty();


        for(int i=0;i<payment_mode_name.size();i++)
        {
         sql="INSERT INTO payment_modes_master(NAME,description) VALUES(?,?)";


           stmt = con.prepareStatement(sql);
           stmt.setString(1,payment_mode_name.get(i).toString());
           stmt.setString(2,payment_mode_desc.get(i).toString());

         recordsUpdated = stmt.executeUpdate();
//System.out.println("----recordsUpdated:"+recordsUpdated);
        }
          } catch (SQLException sqle) {
            System.out.println("SQLException in saveModeOfPayment:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in saveModeOfPayment:" + e);
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


      public int updateModeOfPayment() {

        //System.out.println("updateModeOfPayment");

        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql = "";
        int recordsUpdated = 0;

        try {


            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            //System.out.println("payment_mode_id size:" + payment_mode_id.size());
            for (int i = 0; i < payment_mode_id.size(); i++) {
                sql = "UPDATE payment_modes_master "
                        + "SET NAME='" + payment_mode_name.get(i) + "', "
                        + "description='" + payment_mode_desc.get(i) + "' "
                        + "WHERE mode=" + payment_mode_id.get(i) + "";
                //System.out.println("sql:" + sql);
                stmt.addBatch(sql);



            }
            int[] count = stmt.executeBatch();

            for (int i = 0; i < count.length; i++) {

                if (count[i] == 1) {
                    recordsUpdated++;
                }
            }
            System.out.println("----recordsUpdated:" + recordsUpdated);

        } catch (SQLException sqle) {
            System.out.println("SQLException in updateModeOfPayment:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in updateModeOfPayment:" + e);
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
