/*
 * EstimationInfo.java
 *
 * Created on February 24, 2010, 7:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramyamaims
 */
public class EstimationInfo {
// to get the estimation details for the passed project id.

    connection.DBconnection conProp = new connection.DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String temp_val = "";
    private String est_number = "";
    private String proj_id = "";
    private String proj_name = "";
    private String proj_status = "";
    private String currency_id = "";
    private String currency_symbol = "";
    private String currency_name = "";
    private String currency_shortName = "";
    private String terms_id = "";
    private String terms_name = "";
    private String status_id = "";
    private String status_name = "";
    private String buyer_id = "";
    private String buyer_name = "";
    private String buyer_add = "";
    private String buyer_city = "";
    private String buyer_state = "";
    private String buyer_zip = "";
    private String buyer_country = "";
    private String buyer_phone = "";
    private String buyer_fax = "";
    private String seller_id = "";
    private String seller_name = "";
    private String est_value = "";
    private String est_type = "";
    private String est_type_id = "";
    private String pay_name = "";
    private String pay_id = "";
    private String est_date = "";
    private String estimateDisplayDate = "";
    
    //Variable and Method for getting super category values
    private ArrayList  cat_total = new ArrayList();
    private ArrayList  cat_name = new ArrayList();
    private ArrayList  cat_id = new ArrayList();

    public ArrayList getCat_total() {
        return cat_total;
    }

    public void setCat_total(ArrayList cat_total) {
        this.cat_total = cat_total;
    }

    public ArrayList getCat_name() {
        return cat_name;
    }

    public void setCat_name(ArrayList cat_name) {
        this.cat_name = cat_name;
    }

    public ArrayList getCat_id() {
        return cat_id;
    }

    public void setCat_id(ArrayList cat_id) {
        this.cat_id = cat_id;
    }
    
    

    //Seter and Geter Method for WIP Estimate.
    public String getEst_number() {
        return est_number;
    }

    public void setEst_number(String est_number) {
        this.est_number = est_number;
    }

    public String getEst_value() {
        return est_value;
    }

    public void setEst_value(String est_value) {
        this.est_value = est_value;
    }

    public String getEstimateDisplayDate() {
        return estimateDisplayDate;
    }

    public void setEstimateDisplayDate(String estimateDisplayDate) {
        this.estimateDisplayDate = estimateDisplayDate;
    }

    public String getEst_date() {
        return est_date;
    }

    public void setEst_date(String est_date) {
        this.est_date = est_date;
    }

    public String getProjId() {
        return proj_id;
    }

    public String getProjName() {
        return proj_name;
    }

    public String getProjStatus() {
        return proj_status;
    }

    public String getCurrencyId() {
        return currency_id;
    }

    public String getCurrencyName() {
        return currency_name;
    }

    public String getCurrencySymbol() {
        return currency_symbol;
    }

    public String getCurrencyShortName() {
        return currency_shortName;
    }

    public String getTermsID() {
        return terms_id;
    }

    public String getTermsName() {
        return terms_name;
    }

    public String getStatusID() {
        return status_id;
    }

    public String getStatusName() {
        return status_name;
    }

    public String getBuyerID() {
        return buyer_id;
    }

    public String getBuyerName() {
        return buyer_name;
    }

    public String getBuyerAdd() {
        return buyer_add;
    }

    public String getBuyerCity() {
        return buyer_city;
    }

    public String getBuyerState() {
        return buyer_state;
    }

    public String getBuyerZip() {
        return buyer_zip;
    }

    public String getBuyerCountry() {
        return buyer_country;
    }

    public String getBuyerPhone() {
        return buyer_phone;
    }

    public String getBuyerFax() {
        return buyer_fax;
    }

    public String getSellerID() {
        return seller_id;
    }

    public String getSellerName() {
        return seller_name;
    }

    public String getEstValue() {
        return est_value;
    }

    public String getEstType() {
        return est_type;
    }

    public String getEstTypeId() {
        return est_type_id;
    }

    public String getEstPay() {
        return pay_name;
    }

    public String getEstPayId() {
        return pay_id;
    }
    private List item_id = new ArrayList();
    private List item_name = new ArrayList();
    private List item_rate = new ArrayList();
    private List item_quantity = new ArrayList();
    private List item_desc = new ArrayList();
    private List item_value = new ArrayList();
    private List item_unit = new ArrayList();
    private List item_unit_id = new ArrayList();
    private List category_id = new ArrayList();
    private List line_item_desc = new ArrayList();
    private List category_name = new ArrayList();
    private List item_code = new ArrayList();
    private List item_type = new ArrayList();
    private List item_trim = new ArrayList();
    private List est_line_item_Id = new ArrayList();
    private List line_item_no = new ArrayList();
    private List billing_type_id = new ArrayList();
    private List billing_type_name = new ArrayList();

    public List getBilling_type_id() {
        return billing_type_id;
    }

    public void setBilling_type_id(List billing_type_id) {
        this.billing_type_id = billing_type_id;
    }

    public List getBilling_type_name() {
        return billing_type_name;
    }

    public void setBilling_type_name(List billing_type_name) {
        this.billing_type_name = billing_type_name;
    }

    public List getItemCode() {
        return item_code;
    }

    public List getItemType() {
        return item_type;
    }

    public List getItemTrim() {
        return item_trim;
    }

    public List getEstLineItemId() {
        return est_line_item_Id;
    }

    public List getCategoryId() {
        return category_id;
    }

    public List getCategoryName() {
        return category_name;
    }

    public List getLineItemDesc() {
        return line_item_desc;
    }

    public List getItemId() {
        return item_id;
    }

    public List getItemName() {
        return item_name;
    }

    public List getItemUnit() {
        return item_unit;
    }

    public List getItemUnitId() {
        return item_unit_id;
    }

    public List getItemRate() {
        return item_rate;
    }

    public List getItemQuantity() {
        return item_quantity;
    }

    public List getItemDesc() {
        return item_desc;
    }

    public List getItemValue() {
        return item_value;
    }

    public List getlineItemNo()
    {
        return line_item_no;
    }

    public void setEstNumber(String est_number) {
        //System.out.println("est_number setting in POInfo pathfinder.functions.revenue : "+est_number);
        this.est_number = est_number;
    }

    public void setProjId(String proj_id) {

        this.proj_id = proj_id;
    }

    public void collectEstimationLineItems() {
        item_id.clear();
        item_name.clear();
        item_rate.clear();
        item_quantity.clear();
        line_item_desc.clear();
        item_value.clear();
        category_id.clear();
        category_name.clear();
        line_item_no.clear();

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            sql_select = "select el.est_lineitem_id,el.item_id,el.trim_id,el.type_id,el.item_code,el.quantity,el.rate,el.unit_id,el.total,el.category_id,el.description,el.po_number ";
            sql_from = "from estimate_lineitems el,estimate e ";
            sql_where = " el.est_number=e.est_number ";


            if (!est_number.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " el.est_number = " + est_number + " ";
            }

            if (!proj_id.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " e.proj_id = " + proj_id + " ";
            }


            if (sql_where.length() > 0) {
                ///sql_select += sql_from + " where " + sql_where +" order by el.category_id,el.item_id,el.type_id,el.trim_id" ;
                ///sql_select += sql_from + " where " + sql_where;
                sql_select += sql_from + " where " + sql_where + " order by el.sort_order";
            } else {
                ///sql_select += sql_from + " order by el.category_id,el.item_id,el.type_id,el.trim_id" ;
                sql_select += sql_from + " order by el.sort_order";
            }

            //System.out.println("collectEstimationLineItems sql:" + sql_select);

            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {
                temp_val = "";
                temp_val = rs.getString(1);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                est_line_item_Id.add(temp_val);


                temp_val = "";
                temp_val = rs.getString(2);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_id.add(temp_val);
                item_name.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(3);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_trim.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(4);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_type.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(5);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_code.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(6);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_quantity.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(7);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_rate.add(temp_val);


                temp_val = "";
                temp_val = rs.getString(8);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_unit.add(temp_val);
                item_unit_id.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(9);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_value.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(10);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                category_id.add(temp_val);
                category_name.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(11);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_desc.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(12);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_no.add(temp_val);

            }


            sql_select = "";
            sql_from = "";
            sql_where = "";

            sql_select = "select est.est_value, est.estimate_display_date ";
            sql_from = "from estimate est ";

            if (!est_number.equals("")) {
                if (!sql_where.equals("")) {;
                    sql_where += "and ";
                }
                sql_where += "est_number ='" + est_number + "' ";
            }

            if (!proj_id.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " proj_id = " + proj_id + " ";
            }


            sql_select += sql_from + "where " + sql_where;
            //System.out.println("sql_select for display table 1 in POInfo.java of pathfinder.functions.revenue.EstimationInfo.java : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            if (rs.next()) {
                rs = stmt.executeQuery(sql_select);
                while (rs.next()) {
                    temp_val = rs.getString(1);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    est_value = temp_val;

                    temp_val = rs.getString(2);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    estimateDisplayDate = temp_val;
                    System.out.println(estimateDisplayDate);
                }
            } else {
                est_value = "";
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

    public void getBillingType(){
        try{
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            sql_select = "SELECT billing_type_id,billing_type_name from billing_type_master ";
            rs = stmt.executeQuery(sql_select);
            while(rs.next()){
                if(rs.getString(1)!=null){
                    billing_type_id.add(rs.getString(1));
                }else{
                    billing_type_id.add("");
                }
                if(rs.getString(2)!=null){
                    billing_type_name.add(rs.getString(2));
                }else{
                    billing_type_name.add("");
                }
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void getEstCatgoryDetails(Connection con) {
        try{
        stmt = con.createStatement();

//        sql_select = "SELECT SUM(esl.total),est.proj_id,sc.super_category,sc.super_cat_id FROM " +
//        "estimate_lineitems esl,estimate est,estimate_super_category sc,estimate_category c " +
//        "WHERE est.est_number = esl.est_number AND est.proj_id = '"+proj_id+"' AND  lcase(REPLACE(c.category,' ','')) = lcase(REPLACE(esl.category_id,' ','')) " +
//        "AND sc.super_cat_id = c.super_category GROUP BY sc.super_cat_id ORDER BY sc.super_cat_id";

        sql_select = " SELECT SUM(esl.total),est.proj_id,(CASE WHEN sc.super_cat_id IS NULL THEN '9' ELSE sc.super_cat_id END) CATY_ID,"
                + "(CASE WHEN sc.super_category  IS NULL THEN 'Uncategorized' ELSE sc.super_category  END) CATY_NAME "
                + "FROM estimate est,estimate_lineitems esl "
                + "LEFT JOIN estimate_category es ON (esl.category_id=es.category) "
                + "LEFT JOIN estimate_super_category sc ON (es.super_category = sc.super_cat_id) "
                + "WHERE est.est_number = esl.est_number AND est.proj_id = '"+proj_id+"' GROUP BY sc.super_cat_id ORDER BY sc.super_cat_id";

        rs = stmt.executeQuery(sql_select);
        while(rs.next()){
            cat_total.add(rs.getString(1));
            cat_id.add(rs.getString(3));
            cat_name.add(rs.getString(4));
        }
        rs.close();
        stmt.close();
        }catch(Exception e){
            System.out.println("Error on getting estimate details based on the category"+e.toString());
        }
    }

    public void getEstDetails(Connection con) {
        try {
            stmt = con.createStatement();
            sql_select = "SELECT est_number_disp,date_format(est_date,'%m-%d-%Y'),est_value,proj_id FROM estimate WHERE proj_id = '" + proj_id + "'";
            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {
                temp_val = rs.getString(1);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                est_number = temp_val;
                temp_val = rs.getString(2);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                est_date = temp_val;
                temp_val = rs.getString(3);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                est_value = temp_val;
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Exception on WIP Estimation:" + e.toString());
        }
    }

    public void getEstInfo() {

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            sql_select = "select est.proj_id,p.proj_name,p.project_status, date_format(est.estimate_display_date,'%d-%M-%Y') ";
            sql_from = "from estimate est, projects p ";
            sql_where = "est.proj_id = p.proj_id ";

            if (!est_number.equals("")) {
                sql_where += "and est_number ='" + est_number + "' ";
            }

            sql_select += sql_from + "where " + sql_where;
            // System.out.println("sql_select for display table 1 in POInfo.java of pathfinder.functions.revenue : "+sql_select);

            rs = stmt.executeQuery(sql_select);

            if (rs.next()) {
                rs = stmt.executeQuery(sql_select);
                while (rs.next()) {
                    temp_val = rs.getString(1);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    proj_id = temp_val;

                    temp_val = rs.getString(2);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    proj_name = temp_val;


                    temp_val = rs.getString(3);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    proj_status = temp_val;

                    temp_val = rs.getString(4);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    estimateDisplayDate = temp_val;

                }
            } else {
                proj_id = "";
                proj_name = "";
                proj_status = "";
            }

            sql_select = "select est.est_currency,cur.long_name,cur.short_name,cur.symbol ";
            sql_from = "from estimate est, currency cur ";
            sql_where = "cur.currency_id = est.est_currency ";

            if (!est_number.equals("")) {
                sql_where += "and est_number ='" + est_number + "' ";
            }

            sql_select += sql_from + "where " + sql_where;
            // System.out.println("sql_select for display table 1 in POInfo.java of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);

            if (rs.next()) {
                rs = stmt.executeQuery(sql_select);
                while (rs.next()) {

                    temp_val = rs.getString(1);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    currency_id = temp_val;

                    temp_val = rs.getString(2);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    currency_name = temp_val;

                    temp_val = rs.getString(3);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    currency_shortName = temp_val;

                    temp_val = rs.getString(4);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    currency_symbol = temp_val;
                }
            } else {
                currency_id = "";
                currency_name = "";
                currency_shortName = "";
            }

            sql_select = "select est.terms_and_condition,tcm.name ";
            sql_from = "from estimate est, t_and_c_master tcm ";
            sql_where = "tcm.t_and_c_id = est.terms_and_condition ";

            if (!est_number.equals("")) {
                sql_where += "and est_number ='" + est_number + "' ";
            }

            sql_select += sql_from + "where " + sql_where;
            //System.out.println("sql_select for display table 1 in POInfo.java of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);

            if (rs.next()) {
                rs = stmt.executeQuery(sql_select);
                while (rs.next()) {

                    temp_val = rs.getString(1);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    terms_id = temp_val;

                    temp_val = rs.getString(2);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    terms_name = temp_val;
                }
            } else {
                terms_id = "";
                terms_name = "";
            }
            sql_select = "select est.status,s.status ";
            sql_from = "from estimate est, status s ";
            sql_where = "est.status = s.status_id ";

            if (!est_number.equals("")) {
                sql_where += "and est_number ='" + est_number + "' ";
            }

            sql_select += sql_from + "where " + sql_where;
            // System.out.println("sql_select for display table 1 in POInfo.java of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);

            if (rs.next()) {

                rs = stmt.executeQuery(sql_select);
                while (rs.next()) {

                    temp_val = rs.getString(1);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    status_id = temp_val;

                    temp_val = rs.getString(2);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    status_name = temp_val;
                }
            } else {
                status_id = "";
                status_name = "";
            }

            sql_select = "select est.mode_of_payment,pmm.name ";
            sql_from = "from estimate est,payment_modes_master pmm ";
            sql_where = "pmm.mode = est.mode_of_payment ";

            if (!est_number.equals("")) {
                sql_where += "and est_number ='" + est_number + "' ";
            }

            sql_select += sql_from + "where " + sql_where;
            //System.out.println("sql_select for display table 1 in POInfo.java of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);

            if (rs.next()) {

                rs = stmt.executeQuery(sql_select);
                while (rs.next()) {

                    temp_val = rs.getString(1);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    pay_id = temp_val;

                    temp_val = rs.getString(2);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    pay_name = temp_val;
                }
            } else {
                pay_id = "";
                pay_name = "";
            }

            sql_select = "select concat(cnt.firstname,' ',cnt.surname),est.shipping_address,est.shipping_city,est.shipping_state,est.shipping_zipcode,est.shipping_country,est.shipping_phone,est.shipping_fax,est.buyer_id,cnt.company ";
            sql_from = "from estimate est,contacts cnt ";
            sql_where = "cnt.contact_id = est.buyer_id ";

            if (!est_number.equals("")) {
                sql_where += "and est_number ='" + est_number + "' ";
            }

            sql_select += sql_from + "where " + sql_where;
            // System.out.println("sql_select for display table 1 in POInfo.java of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);

            if (rs.next()) {

                rs = stmt.executeQuery(sql_select);
                while (rs.next()) {

                    temp_val = splChar.decoding(rs.getString(1));
                    if (rs.wasNull()) {
                        temp_val = splChar.decoding(rs.getString(10));
                    }
                    buyer_name = temp_val;

                    temp_val = rs.getString(2);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    buyer_add = temp_val;

                    temp_val = rs.getString(3);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    buyer_city = temp_val;

                    temp_val = rs.getString(4);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    buyer_state = temp_val;

                    temp_val = rs.getString(5);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    buyer_zip = temp_val;

                    temp_val = rs.getString(6);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    buyer_country = temp_val;

                    temp_val = rs.getString(7);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    buyer_phone = temp_val;

                    temp_val = rs.getString(8);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    buyer_fax = temp_val;

                    temp_val = rs.getString(9);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    buyer_id = temp_val;
                }
            } else {
                buyer_name = "";
                buyer_add = "";
                buyer_city = "";
                buyer_state = "";
                buyer_zip = "";
                buyer_country = "";
                buyer_phone = "";
                buyer_fax = "";
                buyer_id = "";
            }

            sql_select = "select est.seller_id,f.facility_name ";
            sql_from = "from estimate est,facility f ";
            sql_where = "f.facility_id = est.seller_id ";

            if (!est_number.equals("")) {
                sql_where += "and est_number ='" + est_number + "' ";
            }

            sql_select += sql_from + "where " + sql_where;
            //System.out.println("sql_select for display table 1 in POInfo.java of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);

            if (rs.next()) {
                rs = stmt.executeQuery(sql_select);
                while (rs.next()) {
                    temp_val = rs.getString(1);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    seller_id = temp_val;

                    temp_val = rs.getString(2);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    seller_name = temp_val;
                }
            } else {
                seller_id = "";
                seller_name = "";
            }

            sql_select = "";
            sql_from = "";
            sql_where = "";

            sql_select = "select est.est_value, est.estimate_display_date ";
            sql_from = "from estimate est ";

            if (!est_number.equals("")) {
                if (!sql_where.equals("")) {;
                    sql_where += "and ";
                }
                sql_where += "est_number ='" + est_number + "' ";
            }
            sql_select += sql_from + "where " + sql_where;
            //System.out.println("sql_select for display table 1 in POInfo.java of pathfinder.functions.revenue.EstimationInfo.java : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            if (rs.next()) {
                rs = stmt.executeQuery(sql_select);
                while (rs.next()) {
                    temp_val = rs.getString(1);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    est_value = temp_val;

                    temp_val = rs.getString(2);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    estimateDisplayDate = temp_val;
                }
            } else {
                est_value = "";
            }

            sql_select = "";
            sql_from = "";
            sql_where = "";

            sql_select = "select est.type,et.estimation_name ";
            sql_from = "from estimate est,estimate_type et ";
            sql_where = "est.type = et.estimation_typeid ";

            if (!est_number.equals("")) {
                if (!sql_where.equals("")) {;
                    sql_where += "and ";
                }
                sql_where += "est.est_number ='" + est_number + "' ";
            }
            sql_select += sql_from + "where " + sql_where;
            // System.out.println("sql_select for display table 1 in POInfo.java of pathfinder.functions.revenue.EstimationInfo.java : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            if (rs.next()) {
                rs = stmt.executeQuery(sql_select);
                while (rs.next()) {
                    temp_val = rs.getString(1);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    est_type_id = temp_val;


                    temp_val = rs.getString(2);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    est_type = temp_val;
                }
            } else {
                est_type_id = "";
                est_type = "";
            }

            sql_select = "";
            sql_from = "";
            sql_where = "";

            sql_select = "select elt.item_id,blm.name,elt.rate,elt.quantity,elt.description,elt.total,u.unit_name,elt.unit_id ,c.category_id,cc.category ";

            sql_from = "from units_master u,billing_lineitems_master blm,cost_center c "
                    + "  RIGHT JOIN  estimate_lineitems elt ON c.item_id=elt.item_id AND c.item_type='lineitem' "
                    + " LEFT JOIN  costcenter_category cc ON c.category_id=cc.category_id ";
            sql_where = "blm.billing_item_id = elt.item_id AND u.unit_id=elt.unit_id  ";


            if (!est_number.equals("")) {
                sql_where += "and elt.est_number ='" + est_number + "' ";
            }
            sql_select += sql_from + "where " + sql_where + "  order by c.category_id";
            //System.out.println("sql_select for display table 2 in POInfo.java of pathfinder.functions.revenue.EstimationInfo.java : "+sql_select);

            rs = stmt.executeQuery(sql_select);
            item_id.clear();
            item_name.clear();
            item_rate.clear();
            item_quantity.clear();
            item_desc.clear();
            item_value.clear();
            category_id.clear();
            category_name.clear();
            line_item_desc.clear();

            while (rs.next()) {
                temp_val = "";
                temp_val = rs.getString(1);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_id.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(2);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_name.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(3);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_rate.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(4);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_quantity.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(5);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_desc.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(6);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_value.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(7);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_unit.add(temp_val);


                temp_val = "";
                temp_val = rs.getString(8);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                item_unit_id.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(9);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                category_id.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(10);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                category_name.add(temp_val);



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

    public void estimationTotal() {
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            sql_select = "SELECT SUM(li.total) FROM estimate e, estimate_lineitems li WHERE e.est_number=li.est_number AND e.proj_id='" + proj_id + "'";
            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {
                if (!rs.getString(1).equals("")) {
                    est_value = rs.getString(1);
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
}
