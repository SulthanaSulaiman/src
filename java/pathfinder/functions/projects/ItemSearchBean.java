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
public class ItemSearchBean {

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
    private String itemId = "";
    private String itemName = "";
    
    private List itemIdList = new ArrayList();
    private List itemNameList = new ArrayList();

    public List getItemIdList() {
        return itemIdList;
    }

    public void setItemIdList(List itemIdList) {
        this.itemIdList = itemIdList;
    }

    public List getItemNameList() {
        return itemNameList;
    }

    public void setItemNameList(List itemNameList) {
        this.itemNameList = itemNameList;
    }
   
    String isSuggest = "";
    private StringTokenizer stk;
    private List searchWordList = new ArrayList();

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

    public void getItemList() {
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
        sql_select = "SELECT billing_item_id,NAME ";
        //sql_from = "from projects proj,client cli ";
        sql_from = "FROM billing_lineitems_master blm ";
        //sql_where = "where (proj_name like '"+searchWordList.get(0)+"' or proj_bktitle like '"+searchWordList.get(0)+"') and cli.client_code=proj.client_name ";
        sql_where = "where (blm.name like '" + searchWordList.get(0) + "')";

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

        //System.out.println("sql_selectItemSearchBean03042013 :::::::::::::::::::::: " + sql_select);

        try {
            con = connection.getSampleProperty();
            //System.out.println("con : "+con);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql_select);
            itemIdList.clear();
            itemNameList.clear();
           

            while (rs.next()) {
                itemId = rs.getString(1);
                itemName = rs.getString(2);
                // testCliName = rs.getString(3);
               

                itemIdList.add(itemId);
                itemNameList.add(itemName);
                
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

    public List getItemId(){
        return itemIdList;
    }

    public List getItemName(){
        return itemNameList;
    }

   
}
