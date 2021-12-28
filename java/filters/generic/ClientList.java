/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.generic;

import filters.*;
import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramesh
 */
public class ClientList implements Serializable{

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();

    private List clientNameList = new ArrayList();
    private List clientCodeList = new ArrayList();

    private Map client = new TreeMap();
    private Map clientDivision = new TreeMap();

    private String sql_select = "";
    private String sql_from = "";

    public void collectClientList(){
        sql_select = "";
        sql_from = "";

        sql_select = " SELECT CONCAT(c.company,'/',d.company),CONCAT(c.contact_id,'/',d.contact_id) " +
                        "FROM contacts d,contacttype_map cm,belongs_to b,contacts c " +
                        "WHERE d.contact_id = cm.contact_id AND cm.type_id = 2 AND c.contact_id = b.parent_contact " +
                        "AND b.contact_id = d.contact_id ORDER BY c.company ";

        sql_select +=sql_from;

        //System.out.println("sql_select:"+sql_select);

        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            clientCodeList.clear();
            clientNameList.clear();

            String getQueryData="";
            while(rs.next()){
                getQueryData=splChar.decoding(rs.getString(1));
                if(rs.wasNull()){
                    getQueryData="";
                }
                clientDivision.put(getQueryData,rs.getString(2));
                /*clientNameList.add(rs.getString(3));
                clientCodeList.add(rs.getString(4));*/
            }

            //the below part collects the client name alone seperately without is division names

        /*sql_select = " select cnt.company,cnt.contact_id ";
        sql_from = "  from contacts cnt,contacttype_map cntm,contacts_type_master ctpm " +
                        " where  " +
                        " cntm.type_id=ctpm.type_id and  ctpm.type_name='Customer'"
                        + " order by cnt.company ";

        sql_select +=sql_from;

       // System.out.println("sql_select:"+sql_select);


            rs = st.executeQuery(sql_select);
            clientCodeList.clear();
            clientNameList.clear();

             getQueryData="";
            while(rs.next()){
                getQueryData=rs.getString(1);
                if(rs.wasNull()){
                    getQueryData="";
                }

                clientNameList.add(rs.getString(1));
                clientCodeList.add(rs.getString(2));
            }*/




            //System.out.println("clientDivision:"+clientDivision);
            //System.out.println("clientNameList:"+clientNameList);
            //System.out.println("clientCodeList:"+clientCodeList);

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
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


   public Map  getClientName(){
        return clientDivision;
    }

       public List getClientCode() {
        return clientCodeList;
    }

    public List getClientNameList() {
        return clientNameList;
    }

}
