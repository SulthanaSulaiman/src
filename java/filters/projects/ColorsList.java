/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.projects;


import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramesh
 */
public class ColorsList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List colorNameList = new ArrayList();
    private List colorIdList = new ArrayList();
    private String testcode = "";
    private String testname = "";


    private String sql_select = "";
    private String sql_from = "";


    public void collectColorList(){
        sql_select = "";
        sql_from = "";

        sql_select = "select color_id, color ";
        sql_from = "from proj_color ";

        sql_select +=sql_from;

        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            colorIdList.clear();
            colorNameList.clear();
            while(rs.next()){
		testcode = rs.getString(1);
                testname = rs.getString(2);
                colorIdList.add(testcode);
                colorNameList.add(testname);
            }

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
            rs.close();
            st.close();
            con.close();
            }catch(SQLException sqle){
           
        }catch(Exception sqle){

        }

        }

    }

    public List getColorId() {
        return colorIdList;
    }

    public List getColorName() {
        return colorNameList;
    }

}
