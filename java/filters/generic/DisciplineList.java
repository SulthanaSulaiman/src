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
public class DisciplineList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List projdiscipline = new ArrayList();
    private List projdiscipline_id = new ArrayList();
    private String tempResult = "";
    
    private String sql_select = "";
    private String sql_from = "";


    public void collectDisciplineList(){
        sql_select = "";
        sql_from = "";

        sql_select = "select prdc.projdiscipline_id   , prdc.proj_discipline  ";
        sql_from = "from proj_discipline prdc ";

        sql_select +=sql_from;

//System.out.println("sql_select:"+sql_select);

        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            projdiscipline.clear();
            projdiscipline_id.clear();
            while(rs.next()){
		tempResult = rs.getString(1);
                projdiscipline_id.add(tempResult);
                 tempResult = rs.getString(2);
                projdiscipline.add(tempResult);
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

    public List getDisciplineID() {
        return projdiscipline_id;
    }

    public List getDiscipline() {
        return projdiscipline;
    }

}
