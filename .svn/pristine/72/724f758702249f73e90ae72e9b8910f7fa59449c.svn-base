/*
 * EstimationTypeList.java
 *
 * Created on February 24, 2010, 12:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;

import java.io.*;
import java.util.*;
import java.sql.*;
import connection.DBconnection;

/**
 *
 * @author ramyamaims
 */
public class EstimationTypeList {
//To get the type of estimation from look up table of estimation_type
    DBconnection conProp = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    
    private List est_typeId = new ArrayList();
    private List est_type = new ArrayList();
    
    public List getEstId(){
        return est_typeId;
    }
    
    public List getEstName(){
        return est_type;
    }
    
    public void collectEstimationType(){
        
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            
            sql_select = "select estimation_typeid,estimation_name ";
            sql_from = "from estimate_type ";
            
            sql_select +=sql_from;
            //System.out.println("sql_select in pathfinder.functions.revenue.EstimationTypeList : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            est_typeId.clear();
            est_type.clear();
            while(rs.next()){
                est_typeId.add(rs.getString(1));
                est_type.add(rs.getString(2));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }
    
}
