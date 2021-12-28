/*
 * ModeOfDeliveryList.java
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
public class ModeOfDeliveryList {

//to get the list of all kinds of delivery mode from transport_modes_master table
    
    private List delivery_mode_id = new ArrayList();
    private List delivery_mode_name = new ArrayList();
    private List delivery_mode_desc = new ArrayList();
    
    private String temp_val = "";
    
    DBconnection dbcon = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    
    public List getDelModID(){
        //System.out.println("delivery_mode_id in poStatusList of pathfinder.functions.revenue : "+delivery_mode_id);
        return delivery_mode_id;
    }

     public void setDelModeId(List delivery_mode_id)
    {
        this.delivery_mode_id = delivery_mode_id;
    }

    public List getDelModeName(){
        return delivery_mode_name;
    }

    public void setDelModeName(List delivery_mode_name)
    {
        this.delivery_mode_name=delivery_mode_name;
    }

    public List getDelModeDes(){
        return delivery_mode_desc;
    }

     public void setDelModeDesc(List delivery_mode_desc)
    {
        this.delivery_mode_desc=delivery_mode_desc;
    }

    
    public void collectDeliveryModeList(){

        try{
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();
            
            sql_select = "select mode,name,description ";
            sql_from = "from transport_modes_master ";
            
            String where="";
            String condition="";
                        
            delivery_mode_id.clear();
            delivery_mode_name.clear();
            delivery_mode_desc.clear();
            
            sql_select += sql_from;
            //System.out.println("sql_select in poStatusList of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            
            while(rs.next()){
                
                temp_val =  rs.getString(1);
                if(rs.wasNull()) {
                    temp_val="";
                }
                delivery_mode_id.add(temp_val);
                
                temp_val =  rs.getString(2);
                if(rs.wasNull()) {
                    temp_val="";
                }
                delivery_mode_name.add(temp_val);      
                
                temp_val =  rs.getString(3);
                if(rs.wasNull()) {
                    temp_val="";
                }
                delivery_mode_desc.add(temp_val);       
            }          
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in collecting ModeOfDeliveryList:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in collecting ModeOfDeliveryList:"+e);
        }
    }

     public int saveModeOfDelivery()
     {
         //System.out.println("saveModeOfDelivery");

          Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql="";
        int recordsUpdated = 0;

        try
        {


            con = dbcon.getSampleProperty();


        for(int i=0;i<delivery_mode_name.size();i++)
        {
         sql="INSERT INTO transport_modes_master(NAME,description) VALUES(?,?)";


           stmt = con.prepareStatement(sql);
           stmt.setString(1,delivery_mode_name.get(i).toString());
           stmt.setString(2,delivery_mode_desc.get(i).toString());

         recordsUpdated = stmt.executeUpdate();
//System.out.println("----recordsUpdated:"+recordsUpdated);
        }
          } catch (SQLException sqle) {
            System.out.println("SQLException in saveModeOfDelivery:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in saveModeOfDelivery:" + e);
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


      public int updateModeOfDelivery() {

        //System.out.println("updateModeOfDelivery");

        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql = "";
        int recordsUpdated = 0;

        try {


            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            //System.out.println("delivery_mode_id size:" + delivery_mode_id.size());
            for (int i = 0; i < delivery_mode_id.size(); i++) {
                sql = "UPDATE transport_modes_master "
                        + "SET NAME='" + delivery_mode_name.get(i) + "', "
                        + "description='" + delivery_mode_desc.get(i) + "' "
                        + "WHERE mode=" + delivery_mode_id.get(i) + "";
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
            System.out.println("SQLException in updateModeOfDelivery:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in updateModeOfDelivery:" + e);
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
