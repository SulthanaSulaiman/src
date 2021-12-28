/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.projects;

import java.util.*;
import connection.DBconnection;
import java.sql.*;

/**
 *
 * @author satyanarayanar
 */
public class VendorSearchBean {

    DBconnection connection = new DBconnection();
    Connection con = null;
    java.sql.Statement stmt = null;
    ResultSet rs = null;
    public String setSearchKey = "";
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String nextToken = "";
    private String setClientName = "";
    private String vendorNo = "";
    private String firstName = "";
    private String lastName = "";
    private String vendorId = "";
    private String vendorListMeg="";
    private String company = "";
    private String isPerson = "";
    private List firstNameList = new ArrayList();
    private List lasttNameList = new ArrayList();
    private List vendorIdList = new ArrayList();
     private List vendorMList = new ArrayList();
    String isSuggest = "";
    private StringTokenizer stk;
    private List searchWordList = new ArrayList();

    pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();

    public void setSearchKey(String setSearchKey) {
        this.setSearchKey = setSearchKey;

        //System.out.println("setSearchKey-Bean:" + setSearchKey);

    }

    public void setClientName(String setClientName) {
        this.setClientName = setClientName;
    }

    public void setIsSuggest(String isSuggest) {
        this.isSuggest = isSuggest;
    }

    public void getVendorList() {
        sql_select = "";
        sql_from = "";
        sql_where = "";

        searchWordList.clear();
        //System.out.println("setSearchKey : " + setSearchKey);
        stk = new StringTokenizer(setSearchKey, " ");
        nextToken = "%";
        while (stk.hasMoreTokens()) {
            nextToken += stk.nextToken() + "%";
        }

        searchWordList.add(nextToken);

        //System.out.println("sql_select VendorSearchBean : " + sql_select);
        //System.out.println("searchWordList : "+searchWordList);
        sql_select = "SELECT cnt.firstname,cnt.surname,cnt.vendor_number,cnt.contact_id, cnt.company, cnt.is_person ";
        //sql_from = "from projects proj,client cli ";
        sql_from = "from contacts cnt, contacttype_map ctyp ";
        //sql_where = "where (proj_name like '"+searchWordList.get(0)+"' or proj_bktitle like '"+searchWordList.get(0)+"') and cli.client_code=proj.client_name ";
        sql_where = "where (cnt.firstname like '" + searchWordList.get(0) + "' "
                + " or cnt.surname like '" + searchWordList.get(0) + "' "
                + " or cnt.company like '" + searchWordList.get(0) + "' "
                + " or cnt.vendor_number like '" + searchWordList.get(0) + "') "
                + "AND cnt.contact_id = ctyp.contact_id "
                + "AND ctyp.type_id='9' "
                + "AND cnt.vendor_number IS NOT NULL";

        if (!setClientName.equals("")) {

            if (!sql_where.equals("")) {
                sql_where += " and ";
            }
            //sql_where +=" cli.client_code = '"+setClientName+"'";
        }



        sql_select += sql_from + sql_where;

        if (isSuggest.equals("1")) {
            sql_select = sql_select + " LIMIT 10";
        }

        //System.out.println("sql_select VendorSearchBean : " + sql_select);

        try {
            con = connection.getSampleProperty();
            //System.out.println("con : "+con);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql_select);
            firstNameList.clear();
            lasttNameList.clear();
            vendorIdList.clear();
            vendorMList.clear();
            // projTitleList.clear();

            while (rs.next()) {
                firstName = splChar.decoding(rs.getString(1));
                lastName = splChar.decoding(rs.getString(2));
                vendorNo = rs.getString(3);
                //vendorId = rs.getString(3);
                vendorId = rs.getString(4);
                if(rs.getString(5) != null) {
                    company = splChar.decoding(rs.getString(5));
                } else {
                    company = "";
                }
                if(rs.getString(6) != null) {
                    isPerson = rs.getString(6).toString();
                } else {
                    isPerson = "";
                }
                if(firstName==null) firstName = "";
                if(lastName==null) lastName = "";
                if(vendorNo==null) vendorNo = "";
                if(isPerson.equals("1")) {
                    vendorListMeg=firstName+" "+lastName+ " ("+ vendorNo +")";
                } else {
                    vendorListMeg = company + " ("+ vendorNo +")";
                }
                firstNameList.add(firstName);
                lasttNameList.add(lastName);
                vendorIdList.add(vendorId);
                vendorMList.add(vendorListMeg);
            }
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
}


    }

    public List getFirstName(){
        return firstNameList;
    }

    public List getLastName(){
        return lasttNameList;
    }

    public List getVendorId(){
        return vendorIdList;
    }

    public List getVendorMList(){
        return vendorMList;
    }
}
