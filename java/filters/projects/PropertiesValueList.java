/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.projects;

import filters.generic.*;
import filters.*;
import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramesh
 */
public class PropertiesValueList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private String propertyID = "";

    private List propertyValue = new ArrayList();
    private List propertyValue_id = new ArrayList();



    private String sql_select = "";
    private String sql_from = "";
    private String sql_where="";

    public void setPropertyId(String propertyID){
        this.propertyID=propertyID;
    }



    public void collectValueList(){
        sql_select = "";
        sql_from = "";
        sql_where = "";

        sql_select = " select ppvm.value ";
        sql_from = "  from project_properties_value_master ppvm,project_properties_master ppm ";
        sql_where = " where ppvm.property_id=ppm.property_id and ppm.property_id='"+propertyID+"'  ";
        sql_select +=sql_from+sql_where;

       // System.out.println("sql_select:"+sql_select);


        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            propertyValue_id.clear();
            propertyValue.clear();
            while(rs.next()){
                //propertyValue_id.add(rs.getString(1));
                propertyValue.add(rs.getString("ppvm.value"));
            }

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
            rs.close();
            st.close();
            con.close();
            }catch(SQLException sqle){
           // sqle.printStackTrace();
            }catch(Exception sqle){
            //sqle.printStackTrace();
            }

        }

    }

    public List getPropertyValueId() {
        return propertyValue_id;
    }

    public List getPropertyValue() {
        return propertyValue;
    }

}
