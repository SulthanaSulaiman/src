/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import java.io.Serializable;
import java.util.*;
import connection.DBconnection;
import java.sql.*;

/**
 *
 * @author ramesh
 * class to retrieve the line item List
 */
public class LineItemList implements Serializable {

//to get the list of all defined line items from billing_lineitems_master table
    private List lineItemId = new ArrayList();
    private List lineItemName = new ArrayList();
    private List lineItemDesc = new ArrayList();
    private List lineItemCat = new ArrayList();
    private List lineItem_id = new ArrayList();
    private List price_listId = new ArrayList();
    private String temp_val = "";
    DBconnection dbcon = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String sql_order = "";
    private String isCostCenter = "";
    private List categoryId = new ArrayList();
    private List categoryName = new ArrayList();
    private List priceValue = new ArrayList();
    private String priceListId = "";
    private List priceDate = new ArrayList();
     private List priceListEnable = new ArrayList();
    String priceYear = "";
    String customerId = "";
    String priceListStatus = "";
    String price_listid = "";

    public void setPriceYear(String priceYear) {
        this.priceYear = priceYear;
    }

    public void setPrice_listId(String price_listid) {
        this.price_listid = price_listid;
    }

    public String getPriceYear() {
        return priceYear;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setPriceListStatus(String priceListStatus) {
        this.priceListStatus = priceListStatus;
    }

    public void setIsCostCenterLineItem(String isCostCenter) {
        this.isCostCenter = isCostCenter;
    }

    public String getIsCostCenterLineItem() {
        return isCostCenter;
    }

    public String getPriceListId() {
        return priceListId;
    }

    public List getLineItemID() {
        //System.out.println("lineItemId in currencyList of pathfinder.functions.revenue : " + lineItemId);
        return lineItemId;
    }

    public List getLineItem_id() {
        return lineItem_id;
    }

    public void setLineItemID(List lineItemId) {
        this.lineItemId = lineItemId;
    }

    public List getPriceDate() {
        return priceDate;
    }

    public List getPriceListEnable() {
        return priceListEnable;
    }

    
    public List getPriceList_Id() //to store the id of the price list itself
    {
        return price_listId;
    }

    public void setPriceDate(List priceDate) {
        this.priceDate = priceDate;
    }

    public List getLineItemName() {
        return lineItemName;
    }

    public List getCategoryId() {
        return categoryId;
    }

    public List getCategoryName() {
        return categoryName;
    }

    public void setLineItemName(List lineItemName) {
        this.lineItemName = lineItemName;
    }

    public List getLineItemDesc() {
        return lineItemDesc;
    }

    public void seLineItemDesc(List lineItemDesc) {
        this.lineItemDesc = lineItemDesc;
    }

    public List getLineItemCat() {
        return lineItemCat;
    }

    public List getPriceValue() {
        return priceValue;
    }

    public void collectPriceListDetPo()
    {

          try{
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "select p.price_id,p.price_value,p.price_date,p.price_enable,p.price_order,b.billing_item_id,b.name ";
            sql_from = "from billing_lineitems_master b,billing_pricelist_po p ";
            sql_where = " where b.billing_item_id=p.item_id AND p.price_enable=1 ";


            lineItem_id.clear();
            priceValue.clear();
            priceListId="";

            sql_select += sql_from + sql_where;

            //System.out.println("sql_select in price list of revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            while(rs.next()){

                temp_val =  rs.getString(6);
                if(rs.wasNull()) {
                    temp_val="";
                }
                lineItem_id.add(temp_val);

                temp_val =  rs.getString(2);
                if(rs.wasNull()) {
                    temp_val="";
                }
                priceValue.add(temp_val);

                temp_val =  rs.getString(5);
                if(rs.wasNull()) {
                    temp_val="";
                }
                priceListId = temp_val;


            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in collecting pricelist:"+sqle);
        }catch(Exception e){
            System.out.println("Exception  in collecting pricelist:"+e);
        }
    }


    public void collectPriceListDet() {

        try {
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "select p.price_id,p.price_value,p.price_date,p.price_enable,p.price_order,b.billing_item_id,b.name,b.item_code, ";
            sql_select+=" (SELECT t.bill_lineitem_type FROM bill_lineitem_type t WHERE t.bill_lineitem_type_id=p.bill_lineitem_type_id) typeName,";
            sql_select+="(SELECT s.trim_size FROM bill_lineitem_trimsize s WHERE s.trim_id=p.trim_id) AS trimname ";
            sql_from = "from billing_lineitems_master b,billing_pricelist p ";
            sql_where = " where b.billing_item_id=p.item_id ";
            sql_order=" ORDER BY b.name,typeName,trimname";

            if(!price_listid.equals(""))
            {
                sql_where+=" and p.price_list_id="+price_listid+"";
            }


            lineItem_id.clear();
            priceValue.clear();
            priceListId = "";

            sql_select += sql_from + sql_where + sql_order;

            //System.out.println("sql_select in price list of revenue : " + sql_select);
            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {

                temp_val = rs.getString(6);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                lineItem_id.add(temp_val);

                temp_val = rs.getString(2);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                priceValue.add(temp_val);

                temp_val = rs.getString(5);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                priceListId = temp_val;


            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException in collecting pricelist:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception  in collecting pricelist:" + e);
        }
    }

    public void addPriceList() {

        Connection con = null;
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            Statement statement = con.createStatement();
            String saveQry = "";
            int recordsUpdated = 0;

            for (int i = 0; i < lineItemId.size(); i++) {

                saveQry = "insert into billing_pricelist (item_id,price_value,price_date)  values('" + lineItem_id.get(i) + "'," + priceValue.get(i) + "','" + priceDate.get(i) + "') ";

                stmt.addBatch(saveQry);

            }

            int[] count = stmt.executeBatch();


            for (int i = 0; i < count.length; i++) {

                if (count[i] == 1) {
                    recordsUpdated++;
                }
            }



//System.out.println("addContact:"+addContact);
        } catch (BatchUpdateException b) {

            System.out.println("SQLException in adding Price list Creation:" + b);

        } catch (SQLException sqle) {
            System.out.println("SQLException in creating price list Creation:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in creating price list Creation:" + e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }


    }

    public void collectLineItemList() {

        try {
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "SELECT b.billing_item_id,b.name,b.description,b.category,cc.category_id,cc.category ";
            sql_from = "FROM cost_center c RIGHT JOIN billing_lineitems_master b ON b.billing_item_id=c.item_id LEFT JOIN costcenter_category cc ON  cc.category_id=c.category_id ";


            lineItemId.clear();
            lineItemName.clear();
            lineItemDesc.clear();
            lineItemCat.clear();
            categoryId.clear();


            sql_select += sql_from + " ORDER BY cc.category_id";

            if (isCostCenter.equals("1")) //this is to get the line items which are not mapped to cost center in the cost center mapping iterface
            {
                sql_select = "select billing_item_id,name,description,category ";
                sql_from = "from billing_lineitems_master ";

                sql_where = " WHERE billing_item_id NOT IN (SELECT item_id FROM cost_center WHERE item_type='lineitem')";
                sql_select += sql_from + sql_where;
            }


            //System.out.println("sql_select in LineItemList of revenue : " + sql_select);
            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {

                temp_val = rs.getString(1);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                lineItemId.add(temp_val);

                temp_val = rs.getString(2);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                lineItemName.add(temp_val);

                temp_val = rs.getString(3);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                lineItemDesc.add(temp_val);

                temp_val = rs.getString(4);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                lineItemCat.add(temp_val);

                temp_val = rs.getString(5);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                categoryId.add(temp_val);

                temp_val = rs.getString(6);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                categoryName.add(temp_val);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException in collecting LineItemList:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in collecting LineItemList:" + e);
        }
    }

    public int saveLineItems() {
        //System.out.println("saveLineItems");

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql = "";
        int recordsUpdated = 0;

        try {


            con = dbcon.getSampleProperty();


            for (int i = 0; i < lineItemName.size(); i++) {
                sql = "INSERT INTO billing_lineitems_master(NAME,description) VALUES(?,?)";


                stmt = con.prepareStatement(sql);
                stmt.setString(1, lineItemName.get(i).toString());
                stmt.setString(2, lineItemDesc.get(i).toString());

                recordsUpdated = stmt.executeUpdate();
                //System.out.println("----recordsUpdated:" + recordsUpdated);
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException in saveLineItems:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in saveLineItems:" + e);
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

    public int updateItems() {

        //System.out.println("updateItems");

        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql = "";
        int recordsUpdated = 0;

        try {


            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            //System.out.println("lineItemId:" + lineItemId.size());
            for (int i = 0; i < lineItemId.size(); i++) {
                sql = "UPDATE billing_lineitems_master "
                        + "SET NAME='" + lineItemName.get(i) + "', "
                        + "description='" + lineItemDesc.get(i) + "' "
                        + "WHERE billing_item_id=" + lineItemId.get(i) + "";
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
            System.out.println("SQLException in updateItems:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in updateItems:" + e);
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

//Collect pricelist for the customer
    public List collectPriceList() {
        PreparedStatement preStmt = null;

        try {
            con = dbcon.getSampleProperty();


            sql_select = "select DISTINCT(b.price_date),price_list_id,b.contact_id,price_enable ";
            sql_from = "from billing_pricelist b ";

            priceDate.clear();
            price_listId.clear();


            if ((customerId != null) || (!customerId.equals(""))) {
                sql_where = sql_where + "b.contact_id=" + customerId + " ";
            }

            if ((priceYear != null) && (!priceYear.equals(""))) {
                sql_where = sql_where + "b.price_date=" + priceYear + " ";
            }

            if ((priceListStatus != null) && (!priceListStatus.equals(""))) {
                sql_where = sql_where + "b.price_enable=" + priceListStatus + " ";
            }



            if ((price_listid != null) && (!price_listid.equals(""))) {
                sql_where = sql_where + "b.price_list_id=" + price_listid + " ";
            }

            if (sql_where != null && !sql_where.equals("")) {
                sql_select += sql_from + "where " + sql_where;
            } else {
                sql_select += sql_from;
            }

            //System.out.println("sql_select in price list of revenue : " + sql_select);
            preStmt = con.prepareStatement(sql_select);
            rs = preStmt.executeQuery(sql_select);
            while (rs.next()) {

                temp_val = rs.getString(1);

                if (rs.wasNull()) {
                    temp_val = "";
                }
                else
                {
                    temp_val = temp_val.substring(0,4);
                }
                priceDate.add(temp_val);


                temp_val = rs.getString(2);

                if (rs.wasNull()) {
                    temp_val = "";
                }
                price_listId.add(temp_val);
                
                temp_val = rs.getString(4);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                priceListEnable.add(temp_val);



            }


        } catch (SQLException sqle) {
            System.out.println("SQLException in collectPriceList:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in collectPriceList:" + e);
        } finally {
            //Close statement
            if (preStmt != null) {
                try {
                    preStmt.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement collectPriceList:se.getMessage()");
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing rs collectPriceList:se.getMessage()");
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection collectPriceList:se.getMessage()");
                }
            }

        }
        return priceDate;
    }
}
