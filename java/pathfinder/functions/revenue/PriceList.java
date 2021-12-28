/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import java.sql.*;
import java.util.*;
import connection.DBconnection;
import java.util.HashMap;

/**
 *
 * @author Thanujas
 * To get the price list for the given customer
 */
public class PriceList {

    DBconnection conProp = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String sql_order = "";
    private String contactId = "";
    private String categoryName = "";
    private String year = "";
    private String priceListId = "";
    private String enableFlag = "";
    private String lineItem = "";
    private String itemtype = "";
    private String temp = "";
    private String trim_val = "";
    private String searchKey = "";
    private String isSuggest = "";
    private Map trimSizes = new HashMap();
    private Map item_type = new HashMap();
    List type = new ArrayList();
    List trim = new ArrayList();
    List priceId = new ArrayList();
    List price_listid = new ArrayList();
    List category = new ArrayList();
    List categoryID = new ArrayList();
    List itemName = new ArrayList();
    List itemCode = new ArrayList();
    List unit = new ArrayList();
    List itemType = new ArrayList();
    List trimSize = new ArrayList();
    List rate = new ArrayList();
    List priceDate = new ArrayList();
    List priceEnable = new ArrayList();
    List priceOrder = new ArrayList();
    List contact_id = new ArrayList();

    List mappedCategoryID = new ArrayList();
    List mappedCategory = new ArrayList();

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setIsSuggest(String isSuggest) {
        this.isSuggest = isSuggest;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public void setItemType(String itemtype) {
        this.itemtype = itemtype;
    }

    public void setTrimVal(String trim_val) {
        this.trim_val = trim_val;
    }

    public void setLineItem(String lineItem) {
        this.lineItem = lineItem;
    }

    public void setPriceListId(String priceListId) {
        this.priceListId = priceListId;
    }

    public List getPriceId() {
        return priceId;
    }

    public void setPriceId(List priceId) {
        this.priceId = priceId;
    }

    public List getPriceListId() {
        return price_listid;
    }

    public List getCategory() {
        return category;
    }

    public List getCategoryID() {
        return categoryID;
    }

    public void setCategory(List category) {
        this.category = category;
    }

    public List getItemName() {
        return itemName;
    }

    public void setItemName(List itemName) {
        this.itemName = itemName;
    }

    public List getItemCode() {
        return itemCode;
    }

    public void setItemCode(List itemCode) {
        this.itemCode = itemCode;
    }

    public List getUnit() {
        return unit;
    }

    public void setUnit(List unit) {
        this.unit = unit;
    }

    public List getItemType() {
        return itemType;
    }

    /*public void setItemType(List itemType) {
    this.itemType = itemType;
    }*/
    public List getTrimSize() {
        return trimSize;
    }

    public void setTrimSize(List trimSize) {
        this.trimSize = trimSize;
    }

    public List getRate() {
        return rate;
    }

    public void setRate(List rate) {
        this.rate = rate;
    }

    public List getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(List priceDate) {
        this.priceDate = priceDate;
    }

    public List getPriceEnable() {
        return priceEnable;
    }

    public void setPriceEnable(List priceEnable) {
        this.priceEnable = priceEnable;
    }

    public List getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(List priceOrder) {
        this.priceOrder = priceOrder;
    }

    public List getContactIdList() {
        return contact_id;
    }

    public void setContactIdList(List contact_id) {
        this.contact_id = contact_id;
    }

    public List getMappedCategoryID()
    {
        return mappedCategoryID;
    }

    public List getMappedCategory()
    {
        return mappedCategory;
    }

    public void collectPriceList() {
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "";
            sql_from = "";
            sql_where = "";
            sql_order = "";

            sql_select = "select price_id,price_list_id,category,item_name,item_code,unit,item_type,trim_size,price_value,year(price_date),price_enable,price_order,contact_id ";
            sql_from = " from billing_pricelist ";
            sql_where = " ";
            sql_order = " order by category,item_name,item_type,trim_size";

            if (!contactId.equals("")) {
                sql_where += " contact_id = " + contactId + " ";
            }

            if (!year.equals("")) {
                sql_where += "and price_date = " + year + " ";
            }

            if (!priceListId.equals("")) {
                sql_where += "and price_list_id = " + priceListId + " ";
            }

            sql_select += sql_from + "where " + sql_where + sql_order;
            //System.out.println("collectPriceList sql:" + sql_select);

            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {
                temp = rs.getString(1);
                if (rs.wasNull()) {
                    temp = " ";
                }
                priceId.add(temp);


                temp = rs.getString(2);
                if (rs.wasNull()) {
                    temp = " ";
                }
                price_listid.add(temp);


                temp = rs.getString(3);
                if (rs.wasNull()) {
                    temp = " ";
                }
                category.add(temp);

                temp = rs.getString(4);
                if (rs.wasNull()) {
                    temp = " ";
                }
                itemName.add(temp);


                temp = rs.getString(5);
                if (rs.wasNull()) {
                    temp = " ";
                }
                itemCode.add(temp);

                temp = rs.getString(6);
                if (rs.wasNull()) {
                    temp = " ";
                }
                unit.add(temp);

                temp = rs.getString(7);
                if (rs.wasNull()) {
                    temp = " ";
                }
                itemType.add(temp);

                temp = rs.getString(8);
                if (rs.wasNull()) {
                    temp = " ";
                }
                trimSize.add(temp);

                temp = rs.getString(9);
                if (rs.wasNull()) {
                    temp = " ";
                }
                rate.add(temp);

                temp = rs.getString(10);
                if (rs.wasNull()) {
                    temp = " ";
                } else {
                    temp = temp.substring(0, 4);
                }
                priceDate.add(temp);

                temp = rs.getString(11);
                if (rs.wasNull()) {
                    temp = " ";
                }
                priceEnable.add(temp);

                temp = rs.getString(12);
                if (rs.wasNull()) {
                    temp = " ";
                }
                priceOrder.add(temp);

                temp = rs.getString(13);
                if (rs.wasNull()) {
                    temp = " ";
                }
                contact_id.add(temp);



            }



        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    public int savePriceList() {


        PreparedStatement statement = null;
        int addList = 0;
        String saveQry = "";
        String price_Id = "";
        String price = "";


        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            for (int i = 0; i < priceId.size(); i++) {
                saveQry = " update billing_pricelist set price_value=?"
                        + " where price_id=? ";

                statement = con.prepareStatement(saveQry);

                price_Id = priceId.get(i).toString();
                price = rate.get(i).toString();


                statement.setString(1, price);
                statement.setString(2, price_Id);

                statement.executeUpdate();
                addList++;


                //System.out.println("saveQry:" + saveQry);

            }


        } catch (SQLException sqle) {
            System.out.println("SQLException in adding castOff details:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in adding castOff details:" + e);
        } finally {
            //Close statement
            if (statement != null) {
                try {
                    statement.close();
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

        return addList;
    }

    //Get the unique line items
    public void collectLineItems() {
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "";
            sql_from = "";
            sql_where = "";
            sql_order = "";

            sql_select = "select DISTINCT(item_name) ";
            sql_from = " from billing_pricelist ";
            sql_where = "";
            sql_order = " order by item_name";


            if (!contactId.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " contact_id = " + contactId + " ";
            }

            if (!categoryName.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " category like '%" + categoryName + "%' ";
            }

            if (!searchKey.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " item_name like '" + searchKey + "%' ";
            }

            if (!year.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " price_date = " + year + " ";
            }

            if (!priceListId.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " price_list_id = " + priceListId + " ";
            }

            //enable enableFlag  lineItem
            if (!enableFlag.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " price_enable = " + enableFlag + " ";
            }


            if (sql_where.length() > 0) {
                // System.out.println("if:"+sql_where);

                sql_select += sql_from + " where " + sql_where + sql_order;
            } else {

                sql_select += sql_from + sql_order;
            }


            if (!isSuggest.equals("")) {
                sql_select += " limit 10";
            }
            //System.out.println("collectLineItemType sql:" + sql_select);
            String next = "";
            String previous = "";
            String item = "";
            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {
                temp = rs.getString(1);

                if (rs.wasNull()) {
                    temp = "";
                }
                itemName.add(temp);



            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

    }
    
    //Get the unique Category
    public void collectCategory() {
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "";
            sql_from = "";
            sql_where = "";
            sql_order = "";

            sql_select = "select DISTINCT(category) ";
            sql_from = " from billing_pricelist ";
            sql_where = "";
            sql_order = " order by category";


            if (!contactId.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " contact_id = " + contactId + " ";
            }

            if (!searchKey.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " category like '" + searchKey + "%' ";
            }

            if (!year.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " price_date = " + year + " ";
            }

            if (!priceListId.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " price_list_id = " + priceListId + " ";
            }

            //enable enableFlag  lineItem
            if (!enableFlag.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " price_enable = " + enableFlag + " ";
            }


            if (sql_where.length() > 0) {
                // System.out.println("if:"+sql_where);

                sql_select += sql_from + " where " + sql_where + sql_order;
            } else {

                sql_select += sql_from + sql_order;
            }


            if (!isSuggest.equals("")) {
                sql_select += " limit 10";
            }
            //System.out.println("collect Category sql:" + sql_select);
            String next = "";
            String previous = "";
            String item = "";
            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {
                temp = rs.getString(1);

                if (rs.wasNull()) {
                    temp = "";
                }
                category.add(temp);
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

    }

    //Get the item code for line item
    public void collectItemcode() {
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "";
            sql_from = "";
            sql_where = "";
            sql_order = "";

            sql_select = "SELECT DISTINCT(item_code) ";
            sql_from = " from billing_pricelist ";
            sql_where = " where ";
            sql_order = " order by item_name";


            if (!contactId.equals("")) {
                sql_where += " contact_id = " + contactId + " ";
            }

            if (!year.equals("")) {
                sql_where += "and price_date = " + year + " ";
            }

            if (!priceListId.equals("")) {
                sql_where += "and price_list_id = " + priceListId + " ";
            }

            //enable enableFlag  lineItem
            if (!enableFlag.equals("")) {
                sql_where += "and price_enable = " + enableFlag + " ";
            }


            //lineitem
            if (!lineItem.equals("")) {
                sql_where += "and item_name = '" + lineItem + "' ";
            }



            sql_select += sql_from + sql_where + sql_order;
            //System.out.println("collectLineItemType sql:" + sql_select);
            String next = "";
            String previous = "";
            String item = "";
            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {
                temp = rs.getString(1);

                if (rs.wasNull()) {
                    temp = "";
                }
                itemCode.add(temp);



            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

    }

    //Get the unit for line item
    public void collectItemUnit() {
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "";
            sql_from = "";
            sql_where = "";
            sql_order = "";

            sql_select = "SELECT DISTINCT(unit) ";
            sql_from = " from billing_pricelist ";
            //sql_where = " where";
            sql_order = " order by item_name";


            if (!contactId.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " contact_id = " + contactId + " ";
            }

            if (!categoryName.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " category like '%" + categoryName + "%' ";
            }

            if (!year.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " price_date = " + year + " ";
            }

            if (!priceListId.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " price_list_id = " + priceListId + " ";
            }

            //enable enableFlag  lineItem
            if (!enableFlag.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " price_enable = " + enableFlag + " ";
            }


            //lineitem
            if (!lineItem.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " item_name = '" + lineItem + "' ";
            }

            //type
            if (!itemtype.equals("")) {
                // System.out.println("itemType............"+itemtype);
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " item_type =  '" + itemtype + "' ";
            }
            /* //trim
            if (!trim_val.equals("")) {
            sql_where += "and trim_size = '"  + trim_val + "' ";
            }*/
            if (sql_where.length() > 0) {
                sql_select += sql_from + " where " + sql_where + sql_order;
            } else {

                sql_select += sql_from + sql_order;
            }
            //System.out.println("collectLineItemType sql:" + sql_select);
            String next = "";
            String previous = "";
            String item = "";
            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {
                temp = rs.getString(1);

                if (rs.wasNull()) {
                    temp = "";
                }
                unit.add(temp);



            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

    }

    //Get the price for line item
    public void collectItemPrice() {
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "";
            sql_from = "";
            sql_where = "";
            sql_order = "";

            sql_select = "SELECT DISTINCT price_value ";
            sql_from = " from billing_pricelist ";
            sql_where = " where ";
            sql_order = " order by item_name";


            if (!contactId.equals("")) {
                sql_where += " contact_id = " + contactId + " ";
            }

            if (!categoryName.equals("")) {
                sql_where += "and category like '%" + categoryName + "%' ";
            }
            
            if (!year.equals("")) {
                sql_where += "and price_date = " + year + " ";
            }

            if (!priceListId.equals("")) {
                sql_where += "and price_list_id = " + priceListId + " ";
            }

            //enable enableFlag  lineItem
            if (!enableFlag.equals("")) {
                sql_where += "and price_enable = " + enableFlag + " ";
            }


            //lineitem
            if (!lineItem.equals("")) {
                sql_where += "and item_name =  '" + lineItem + "' ";
            }

            //type
            if (!itemtype.equals("")) {
                sql_where += "and item_type =  '" + itemtype + "' ";
            }

            //trim
            if (!trim_val.equals("")) {
                sql_where += "and trim_size =  '" + trim_val + "' ";
            }


            sql_select += sql_from + sql_where + sql_order;
            //System.out.println("collectLineItemType sql:" + sql_select);
            String next = "";
            String previous = "";
            String item = "";
            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {
                temp = rs.getString(1);

                if (rs.wasNull()) {
                    temp = "";
                }
                rate.add(temp);



            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

    }

    //Get the type for each lineitem
    public void collectLineItemType() {
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "";
            sql_from = "";
            sql_where = "";
            sql_order = "";

            sql_select = "select DISTINCT(item_type) ";
            sql_from = " from billing_pricelist ";
            //sql_where = " where ";
            sql_order = " order by item_name";


            if (!contactId.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " contact_id = " + contactId + " ";
            }

            if (!categoryName.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " category like '%" + categoryName + "%' ";
            }

            if (!year.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " price_date = " + year + " ";
            }

            if (!priceListId.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " price_list_id = " + priceListId + " ";
            }

            //enable enableFlag  lineItem
            if (!enableFlag.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " price_enable = " + enableFlag + " ";
            }

            //lineitem
            if (!lineItem.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " item_name = '" + lineItem + "' ";
            }

            if (!searchKey.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += "  item_type like '" + searchKey + "%' ";
            }



            if (sql_where.length() > 0) {
                // System.out.println("if:"+sql_where);

                sql_select += sql_from + " where " + sql_where + sql_order;
            } else {

                sql_select += sql_from + sql_order;
            }


            if (!isSuggest.equals("")) {
                sql_select += " limit 10";
            }

            //System.out.println("collectLineItemType sql:" + sql_select);
            String next = "";
            String previous = "";
            String item = "";
            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {


                temp = rs.getString(1);
                if (rs.wasNull()) {
                    temp = "";
                }
                itemType.add(temp);



            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

    }

    //Get the trim for line item and type
    public void collectItemTypeTrim() {
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "";
            sql_from = "";
            sql_where = "";
            sql_order = "";

            sql_select = "select DISTINCT(trim_size) ";
            sql_from = " from billing_pricelist ";
            // sql_where = " where ";
            sql_order = " ORDER BY item_name,item_type,trim_size";


            if (!contactId.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " contact_id = " + contactId + " ";
            }

            if (!year.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " price_date = " + year + " ";
            }

            if (!priceListId.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " price_list_id = " + priceListId + " ";
            }

            //enable enableFlag  lineItem
            if (!enableFlag.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " price_enable = " + enableFlag + " ";
            }

            //lineitem
            if (!lineItem.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " item_name = '" + lineItem + "' ";
            }

            //type
            if (!itemtype.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " item_type =  '" + itemtype + "' ";

            }

            if (!itemtype.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " trim_size IS NOT NULL ";

            }

            if (!searchKey.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " trim_size like '" + searchKey + "%' ";
            }



            if (sql_where.length() > 0) {
                // System.out.println("if:"+sql_where);

                sql_select += sql_from + " where " + sql_where + sql_order;
            } else {

                sql_select += sql_from + sql_order;
            }

            if (!isSuggest.equals("")) {
                sql_select += " limit 10";
            }

            //System.out.println("collectItemTypeTrim sql:" + sql_select);
            String next = "";
            String previous = "";
            String item = "";
            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {
                temp = rs.getString(1);

                if (rs.wasNull()) {
                    temp = "";
                }
                trimSize.add(temp);



            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

    }

    //Get the type for each category
    public void collectCategoryType() {
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "";
            sql_from = "";
            sql_where = "";
            sql_order = "";

            sql_select = "select distinct(category),item_type ";
            sql_from = " from billing_pricelist ";
            sql_where = " where ";
            sql_order = " order by category,item_type";


            if (!contactId.equals("")) {
                sql_where += "and contact_id = " + contactId + " ";
            }

            if (!year.equals("")) {
                sql_where += "and price_date = " + year + " ";
            }

            if (!priceListId.equals("")) {
                sql_where += "and price_list_id = " + priceListId + " ";
            }

            sql_select += sql_from + sql_where + sql_order;
            //System.out.println("collectCategoryType sql:" + sql_select);
            String next = "";
            String previous = "";
            String item = "";
            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {
                temp = rs.getString(1);
                if (!temp.equals(previous) && !previous.equals("")) {
                    item = temp;
                    item_type.put(previous, type);
                    type.clear();
                }

                if (!temp.equals(previous)) {
                    item = temp;

                }

                if (temp.equals(previous)) {
                    //add
                }

                previous = temp;

                temp = rs.getString(2);
                if (rs.wasNull()) {
                    temp = "";
                }
                type.add(temp);


                if (!rs.next()) {
                    item_type.put(previous, type);
                }
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

    }

  public void getMappedCategoryName() {
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT category_id, category FROM estimate_category WHERE super_category!=0 AND category_id>=157 ORDER BY category");
            while (rs.next()) {
                temp = rs.getString(1);

                if (rs.wasNull()) {
                    temp = "";
                }
                mappedCategoryID.add(temp);

            temp = rs.getString(2);

                if (rs.wasNull()) {
                    temp = "";
                }
                mappedCategory.add(temp);
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

    }
    //Get type flag
   /* public void collectTypeFlag() {
    try {
    con = conProp.getSampleProperty();
    stmt = con.createStatement();

    sql_select = "";
    sql_from = "";
    sql_where = "";
    sql_order = "";

    sql_select = "select DISTINCT(item_type) ";
    sql_from = " from billing_pricelist ";
    sql_where = " where ";
    sql_order = " order by category,item_type";


    if (!contactId.equals("")) {
    sql_where += "and contact_id = " + contactId + " ";
    }

    if (!year.equals("")) {
    sql_where += "and price_date = " + year + " ";
    }
    
    if (!priceListId.equals("")) {
    sql_where += "and price_list_id = " + priceListId + " ";
    }

    sql_select += sql_from + "where " + sql_where + sql_order;
    System.out.println("collectCatTrimSize sql:" + sql_select);

    rs = stmt.executeQuery(sql_select);
    while (rs.next()) {
    temp = rs.getString(1);
    if(rs.wasNull())
    {
    temp="";
    }
    if(!temp.equals(""))
    {

    }
    }

    } catch (SQLException sqle) {
    sqle.printStackTrace();
    } catch (NullPointerException npe) {
    npe.printStackTrace();
    }







    }*/
}
