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
public class ProjPropertiesList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List propertyID = new ArrayList();
    private List propertyName = new ArrayList();
    private List propertyListBox = new ArrayList();
    private List propertyCheckBox = new ArrayList();

    private List xmpPropID = new ArrayList();
    private List xmpPropName = new ArrayList();

    private String categoryID = "";
    private String categoryName = "";

    private String tempResult = "";

    private String sql_select = "";
    private String sql_from = "";
    private String sql_where="";

    public void setPropCategoryId(String categoryID){
        this.categoryID=categoryID;
    }


    public void setPropCategoryName(String categoryName){
        this.categoryName=categoryName;
    }


    public void collectPropertyList(){

                propertyID.clear();
                propertyName.clear();
                propertyListBox.clear();
                propertyCheckBox.clear();

        sql_select = "";
        sql_from = "";
        sql_where = "";

        sql_select = " select ppm.property_id,ppm.name,ppm.listbox,ppm.checkbox  ";
        sql_from = "from project_property_category_master ppcm,project_properties_master ppm ";
        sql_where = "where ppcm.name='"+categoryName+"' and ppcm.category_id=ppm.category_id ";
        sql_select +=sql_from+sql_where;

      //System.out.println("sql_select:"+sql_select);

        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            propertyID.clear();
            propertyName.clear();
            while(rs.next()){
                propertyID.add(rs.getString(1));
                propertyName.add(rs.getString(2));
                propertyListBox.add(rs.getString(3));
                propertyCheckBox.add(rs.getString(4));
            }

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
            rs.close();
            st.close();
            con.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
        }

        }

    }

    public void collectXMLPriorityList(){
        sql_select = "";
        sql_from = "";
        sql_select = "select type_id, type_name ";
        sql_from = "from proj_xml_prop ";
        sql_select +=sql_from;
        String temp="";
        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            xmpPropID.clear();
            xmpPropName.clear();
            while(rs.next()){
		temp = rs.getString(1) != null ? rs.getString(1) : "";
                xmpPropID.add(temp);
                temp = rs.getString(2) != null ? rs.getString(2) : "";
                xmpPropName.add(temp);
            }

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
            rs.close();
            st.close();
            con.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
        }

        }

    }

    public List getXmpPropID() {
        return xmpPropID;
    }

    public List getXmpPropName() {
        return xmpPropName;
    }

    public List getPropertyId() {
        return propertyID;
    }

    public List getPropertyName() {
        return propertyName;
    }

    public List getPropertyListBox() {
        return propertyListBox;
    }

    public List getPropertyCheckBox() {
        return propertyCheckBox;
    }

}
