/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.generic;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramesh
 */
public class ProjTypeList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List projType = new ArrayList();
    private List projTypeCode= new ArrayList();
    private String tempResult = "";
    
    private String sql_select = "";
    private String sql_from = "";


    public void collectProjType(){
        sql_select = "";
        sql_from = "";

        sql_select = "select prt.type_code, prt.type ";
        sql_from = "from project_type prt ";

        sql_select +=sql_from;


        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            projType.clear();
            projTypeCode.clear();
            while(rs.next()){
		tempResult = rs.getString(1);
                projTypeCode.add(tempResult);
                 tempResult = rs.getString(2);
                projType.add(tempResult);
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

    public List getProjTypeCode() {
        return projTypeCode;
    }

    public List getProjType() {
        return projType;
    }

}
