/*
 * TermsList.java
 *
 * Created on February 17, 2010, 12:15 PM
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
public class TermsList implements Serializable {
    
    private List termsId = new ArrayList();
    private List termsName = new ArrayList();
    private List termsDesc = new ArrayList();
    
    private String temp_val = "";
    
    DBconnection dbcon = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";

    public List getTermsID(){
        //System.out.println("termsId in termsList of pathfinder.functions.revenue : "+termsId);
        return termsId;
    }

    public void setTermsID(List termsId)
    {
        this.termsId = termsId;
    }
    
    public List getTermsName(){
        return termsName;
    }

    public void setTermsName(List termsName)
    {
        this.termsName = termsName;
    }

    public List getTermsDesc(){
        return termsDesc;
    }

     public void setTermsDesc(List termsDesc)
    {
        this.termsDesc = termsDesc;
    }


    public void collectTermsList(){

        try{
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();
            
            sql_select = "select t_and_c_id,name,description ";
            sql_from = "from t_and_c_master ";
            
            String where="";
            String condition="";
            
            termsId.clear();
            termsName.clear();
            termsDesc.clear();
            
            sql_select += sql_from;
            //System.out.println("sql_select in termsList of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            while(rs.next()){
                
                temp_val =  rs.getString(1);
                if(rs.wasNull()) {
                    temp_val="";
                }
                termsId.add(temp_val);
                
                temp_val =  rs.getString(2);
                if(rs.wasNull()) {
                    temp_val="";
                }
                termsName.add(temp_val);
                
                temp_val =  rs.getString(3);
                if(rs.wasNull()) {
                    temp_val="";
                }
                termsDesc.add(temp_val);                
            }          
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in collecting termsList:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in collecting termsList:"+e);
        }
    }

      public int saveTerms()
     {
         //System.out.println("saveTerms");

          Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql="";
        int recordsUpdated = 0;

        try
        {


            con = dbcon.getSampleProperty();


        for(int i=0;i<termsName.size();i++)
        {
         sql="INSERT INTO t_and_c_master(NAME,description) VALUES(?,?)";


           stmt = con.prepareStatement(sql);
           stmt.setString(1,termsName.get(i).toString());
           stmt.setString(2,termsDesc.get(i).toString());

         recordsUpdated = stmt.executeUpdate();
//System.out.println("----recordsUpdated:"+recordsUpdated);
        }
          } catch (SQLException sqle) {
            System.out.println("SQLException in saveTerms:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in saveTerms:" + e);
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

      public int updateTerms() {

        //System.out.println("updateTerms");

        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql = "";
        int recordsUpdated = 0;

        try {


            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            //System.out.println("termsId:" + termsId.size());
            for (int i = 0; i < termsId.size(); i++) {
                sql = "UPDATE t_and_c_master "
                        + "SET NAME='" + termsName.get(i) + "', "
                        + "description='" + termsDesc.get(i) + "' "
                        + "WHERE t_and_c_id=" + termsId.get(i) + "";
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
            System.out.println("SQLException in updateTerms:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in updateTerms:" + e);
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
