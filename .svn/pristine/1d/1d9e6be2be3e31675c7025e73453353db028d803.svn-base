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
public class DivisionList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List divisionID = new ArrayList();
    private List divisionName = new ArrayList();

    pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
    
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where="";

    private String customerId = "";
      private String isSuggest="";

      public void setIsSuggest(String isSuggest)
    {
        this.isSuggest = isSuggest;
    }
      

    public void setClientId(String customerId){
        this.customerId=customerId;
    }

    public void collectDivisionList(){

                divisionID.clear();
                divisionName.clear();

        sql_select = "";
        sql_from = "";
        sql_where = "";

        sql_select = " select cts.contact_id,cts.company  ";
        sql_from = " from contacts cts,contacttype_map ctmp  ";
        sql_where = " where cts.contact_id=ctmp.contact_id and ctmp.type_id='2' ";
       
        
        if(!customerId.equals("")){
            sql_from += " ,belongs_to blt ";
            sql_where += " and blt.parent_contact='"+customerId+"' and blt.contact_id=cts.contact_id ";
        }
 
        sql_select +=sql_from+sql_where;
         if(isSuggest.equals("1"))
        {
            sql_select = sql_select + " LIMIT 10";
        }

      //System.out.println("sql_select:"+sql_select);


        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            String divisionName1="";
            divisionID.clear();
            divisionName.clear();
            while(rs.next()){
                divisionName1=rs.getString(2);
                if(divisionName1==null){
                    divisionName1="";
                    
                }
                divisionID.add(rs.getString(1));
                divisionName.add(splChar.decoding(divisionName1));
                
            }

            //System.out.println("divisionID:"+divisionID);

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

    public List getDivisionId() {
        return divisionID;
    }

    public List getDivisionName() {
        return divisionName;
    }

  

}
