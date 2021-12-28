/*
 * EqualityCaseList.java
 *
 * Created on February 22, 2010, 5:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;

import connection.DBconnection;
import java.io.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author ramyamaims
 */
public class EqualityCaseList {
    
    DBconnection conProp = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    
    private List equality_id = new ArrayList();
    private List equality_desc = new ArrayList();
    private List equality_case = new ArrayList();
    
    public List getEqualityId(){
        return equality_id;
    }
    
    public List getEqualityCase(){
        return equality_case;
    }
    
    public List getEqualityDesc(){
        return equality_desc;
    }
    
    public void getEqualityType(){
    
//Values from look up table for the equality_case table and fmmap_datatype_equality table
        try{
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
        
            sql_select = "select ec.equality_case_id,ec.equality_case_desc,ec.equality_case ";
            sql_from = "from equality_case ec, fmmap_datatype_equality fde ";
            sql_where = "ec.equality_case_id = fde.equality_case_id and fde.datatype_id = '1' ";
            sql_select +=sql_from+"where "+sql_where;
            
            //System.out.println("sql_select in EqualityCaseList of pathfinder.functions.revenue.EqualityCaseList : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            equality_id.clear();
            equality_desc.clear();
            equality_case.clear();
            
            while(rs.next()){                
                equality_id.add(rs.getString(1));
                equality_desc.add(rs.getString(2));
                equality_case.add(rs.getString(3));
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
