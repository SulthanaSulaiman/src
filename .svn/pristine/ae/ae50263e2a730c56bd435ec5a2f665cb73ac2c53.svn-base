/*
 * UnitsList.java
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
public class UnitsList {

//to get the list of Units from units_master table

    private List unit_id = new ArrayList();
    private List unit_name = new ArrayList();
    private List unit_conversion = new ArrayList();
    private List unit_type = new ArrayList();

    private String temp_val = "";

    DBconnection dbcon = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";


    public List getUnitID(){
       
        return unit_id;
    }

    public void setUnitID(List unit_id)
    {
        this.unit_id = unit_id;
    }


    public List getUnitName(){
        return unit_name;
    }

    

    public void setUnitName(List unit_name)
    {
        this.unit_name = unit_name;
    }

    public List getUnitConversion(){
        return unit_conversion;
    }

    public void setUnitConversion(List unit_conversion)
    {
        this.unit_conversion = unit_conversion;
    }

    public List getUnitType()
    {
        return unit_type;
    }

    public void setUnitType(List unit_type)
    {
        this.unit_type = unit_type;
    }

    public void collectUnitsList(){

        try{
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "SELECT unit_id,unit_name,unit_conversion,unit_type ";
            sql_from = "from units_master ";

            String where="";
            String condition="";

            unit_id.clear();
            unit_name.clear();
            unit_conversion.clear();
            unit_type.clear();

            sql_select += sql_from;
            
            rs = stmt.executeQuery(sql_select);

            while(rs.next()){

                temp_val =  rs.getString(1);
                if(rs.wasNull()) {
                    temp_val="";
                }
                unit_id.add(temp_val);

                temp_val =  rs.getString(2);
                if(rs.wasNull()) {
                    temp_val="";
                }
                unit_name.add(temp_val);

                temp_val =  rs.getString(3);
                if(rs.wasNull()) {
                    temp_val="";
                }
                else if(temp_val.equals("0"))
                {
                    temp_val="";
                }
                unit_conversion.add(temp_val);

                 temp_val =  rs.getString(4);
                if(rs.wasNull()) {
                    temp_val="";
                }
                unit_type.add(temp_val);
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in collecting collectUnitsList:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in collecting collectUnitsList:"+e);
        }
    }


      public int saveUnitsList()
     {
         //System.out.println("saveUnitsList");

          Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql="";
        int recordsUpdated = 0;

        try
        {


            con = dbcon.getSampleProperty();


        for(int i=0;i<unit_name.size();i++)
        {
         sql="INSERT INTO units_master(unit_name,unit_conversion,unit_type) VALUES(?,?,?)";

String unitConversion = unit_conversion.get(i).toString();
if(unitConversion.equals(""))
{
    unitConversion = "0";
}
           stmt = con.prepareStatement(sql);
           stmt.setString(1,unit_name.get(i).toString());
           stmt.setString(2,unitConversion);
           stmt.setString(3,unit_type.get(i).toString());

         recordsUpdated = stmt.executeUpdate();
//System.out.println("----recordsUpdated:"+recordsUpdated);
        }
          } catch (SQLException sqle) {
            System.out.println("SQLException in saveUnitsList:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in saveUnitsList:" + e);
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


      public int updateUnitsList() {

        //System.out.println("updateUnitsList");

        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql = "";
        int recordsUpdated = 0;

        try {


            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            //System.out.println("lineItemId:" + unit_id.size());
            for (int i = 0; i < unit_id.size(); i++) {

                String unitConversion = unit_conversion.get(i).toString();
if(unitConversion.equals(""))
{
    unitConversion = "0";
}
                sql = "UPDATE units_master "
                        + "SET unit_name='" + unit_name.get(i) + "', "
                        + "unit_conversion='" + unitConversion + "', "
                        + "unit_type='" + unit_type.get(i) + "' "
                        + "WHERE unit_id=" + unit_id.get(i) + "";
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
            System.out.println("SQLException in updateUnitsList:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in updateUnitsList:" + e);
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
