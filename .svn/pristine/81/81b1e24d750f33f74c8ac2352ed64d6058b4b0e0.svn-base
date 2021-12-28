/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramesh
 * class to retrieve the Project Category List
 */
public class ProjCategoryList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List projCategory = new ArrayList();
    private List projCategoryId = new ArrayList();
    private String tempResult = "";

    private String sql_select = "";
    private String sql_from = "";


    public void collectProjType(){
        sql_select = "";
        sql_from = "";

        sql_select = "select prc.projcategory_id , prc.proj_category ";
        sql_from = "from proj_category prc ";

        sql_select +=sql_from;


        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            projCategoryId.clear();
            projCategory.clear();
            while(rs.next()){
		tempResult = rs.getString(1);
                projCategoryId.add(tempResult);
                 tempResult = rs.getString(2);
                projCategory.add(tempResult);
            }
            con.close();
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

    public List getProjCatID() {
        return projCategoryId;
    }

    public List getProjCategory() {
        return projCategory;
    }

}
