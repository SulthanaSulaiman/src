/*
 * POInfo.java
 *
 * Created on February 18, 2010, 2:40 PM
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
public class POInfo implements Serializable {

    connection.DBconnection conProp = new connection.DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String temp_val = "";
    private String po_number = "";
    private String proj_id = "";
    private String proj_name = "";
    private String currency_id = "";
    private String currency_name = "";
    private String currency_shortName = "";
    private String terms_id = "";
    private String terms_name = "";
    private String status_id = "";
    private String status_name = "";
    private String del_id = "";
    private String del_name = "";
    private String pay_id = "";
    private String pay_name = "";
    private String buyer_id = "";
    private String buyer_name = "";
    private String buyer_add = "";
    private String buyer_city = "";
    private String buyer_state = "";
    private String buyer_zip = "";
    private String buyer_country = "";
    private String buyer_phone = "";
    private String buyer_fax = "";
    private String buyer_type = "";
    private String seller_id = "";
    private String seller_name = "";
    private String seller_add = "";
    private String seller_city = "";
    private String seller_state = "";
    private String seller_zip = "";
    private String seller_country = "";
    private String seller_phone = "";
    private String seller_fax = "";
    private String seller_type = "";
    private String po_value = "";
    private String getBuyerMappingParam = "";
    private String getSellerMappingParam = "";
    private String getBuyerParam = "";
    private String getSellerParam = "";
    private String getUnit = "";
    private Double openPO = 0.0;

    private List categoryList = new ArrayList();
    private List functionList = new ArrayList();
    private List rateList = new ArrayList();
    private List quantityList = new ArrayList();
    private List totalList = new ArrayList();
    private List poReceivedFlagList = new ArrayList();

    private List taxIdVal= new ArrayList();
    private List taxNameVal= new ArrayList();
    private List taxRate= new ArrayList();
    private List taxAmt= new ArrayList();

    public List getTaxNameVal() {
        return taxNameVal;
    }

    public void setTaxNameVal(List taxNameVal) {
        this.taxNameVal = taxNameVal;
    }

    public List getTaxIdVal() {
        return taxIdVal;
    }

    public void setTaxIdVal(List taxIdVal) {
        this.taxIdVal = taxIdVal;
    }

    public List getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(List taxRate) {
        this.taxRate = taxRate;
    }

    public List getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(List taxAmt) {
        this.taxAmt = taxAmt;
    }

    public List getTax_id() {
        return tax_id;
    }

    public void setTax_id(List tax_id) {
        this.tax_id = tax_id;
    }

    public List getTax_name() {
        return tax_name;
    }

    public void setTax_name(List tax_name) {
        this.tax_name = tax_name;
    }

    public List getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List categoryList) {
        this.categoryList = categoryList;
    }

    public List getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List functionList) {
        this.functionList = functionList;
    }

    public List getQuantityList() {
        return quantityList;
    }

    public void setQuantityList(List quantityList) {
        this.quantityList = quantityList;
    }

    public List getRateList() {
        return rateList;
    }

    public void setRateList(List rateList) {
        this.rateList = rateList;
    }

    public List getTotalList() {
        return totalList;
    }

    public void setTotalList(List totalList) {
        this.totalList = totalList;
    }

    public List getPOReceivedFlagList() {
        return poReceivedFlagList;
    }

    public void setPOReceivedFlagList(List poReceivedFlagList) {
        this.poReceivedFlagList = poReceivedFlagList;
    }

    public String getProjId() {
        return proj_id;
    }

    public void setProjId(String proj_id) {
        this.proj_id = proj_id;
    }
    public String getProjName() {
        return proj_name;
    }

    public String getCurrencyId() {
        return currency_id;
    }

    public String getCurrencyName() {
        return currency_name;
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

    public String getDelID() {
        return del_id;
    }

    public String getDelName() {
        return del_name;
    }

    public String getPayID() {
        return pay_id;
    }

    public String getPayName() {
        return pay_name;
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

    public String getBuyerType() {

        return buyer_type;
    }

    public String getSellerID() {
        return seller_id;
    }

    public String getSellerName() {
        return seller_name;
    }

    public String getSellerAdd() {
        return seller_add;
    }

    public String getSellerCity() {
        return seller_city;
    }

    public String getSellerState() {
        return seller_state;
    }

    public String getSellerZip() {
        return seller_zip;
    }

    public String getSellerCountry() {
        return seller_country;
    }

    public String getSellerPhone() {
        return seller_phone;
    }

    public String getSellerFax() {
        return seller_fax;
    }

    public String getSellerType() {
        return seller_type;
    }

    public String getPOValue() {
        return po_value;
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
    private List category_name = new ArrayList();


     public List getCategoryId() {
        return category_id;
    }

      public List getCategoryName() {
        return category_name;
    }

    public List getItemId() {
        return item_id;
    }

      public List getItemName() {
        return item_name;
    }


    public List getItemUnit()
    {
        return item_unit;
    }

    public List getItemUnitId()
    {
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
    private List tax_id = new ArrayList();
    private List tax_name = new ArrayList();
    private List tax_value = new ArrayList();
    private List tax_total = new ArrayList();

    public List getTaxId() {
        return tax_id;
    }

    public List getTaxName() {
        return tax_name;
    }

    public List getTaxValue() {
        return tax_value;
    }

    public List getTaxTotal() {
        return tax_total;
    }

    public void setPONumber(String po_number) {
        //System.out.println("po_number setting in POInfo pathfinder.functions.revenue : "+po_number);
        this.po_number = po_number;
    }

    public Double getOpenPO() {
        return openPO;
    }

    public void setOpenPO(Double openPO) {
        this.openPO = openPO;
    }

    //To get the Purchase Order ID by the Project ID
       
    public void getPOInfo() {
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            rs = stmt.executeQuery("select buyer_type, seller_type from purchase_order where po_number = '" + po_number + "'");

             //System.out.println("po_number : "+po_number);
            while (rs.next()) {
                buyer_type = rs.getString(1);
                if (rs.wasNull()) {
                    buyer_type = "";
                }
                seller_type = rs.getString(2);
                if (rs.wasNull()) {
                    seller_type = "";
                }
            }

//             System.out.println("buyer_type : "+buyer_type);
//             System.out.println("seller_type : "+seller_type);

            if (buyer_type.equals("facility")) {
                getBuyerMappingParam = "facility_id";
                getBuyerParam = "facility_name";
            } else if (buyer_type.equals("department")) {
                getBuyerMappingParam = "dept_code";
                getBuyerParam = "department";
            } else if (buyer_type.equals("user")) {
                getBuyerMappingParam = "emp_id";
                getBuyerParam = "emp_name";
            }
             else if (buyer_type.equals("contacts")) {
                getBuyerMappingParam = "c.contact_id";

            }
            

            getSellerMappingParam = "";
            if (seller_type.equals("contacts")) {
                getSellerMappingParam = "contact_id";
            }

            /**sql_select = "select po.proj_id,p.proj_name,po.po_currency,cur.long_name,po.terms_and_condition,tcm.name,po.status,s.status," +
            "po.mode_of_transport,tmm.name,pmm.name,po.mode_of_payment,bt."+getBuyerParam+",po.shipping_address,po.shipping_city," +
            "po.shipping_state,po.shipping_zipcode,po.shipping_country,po.shipping_phone,po.shipping_fax," +
            "st."+getSellerMappingParam+",concat(st.firstname,' ',st.surname),po.seller_address,po.seller_city," +
            "po.seller_state,po.seller_zipcode,po.seller_country,po.seller_phone,po.seller_fax,po.po_value," +
            "po.buyer_type,po.seller_type,po.buyer_id ";
            sql_from = "from purchase_order po, projects p,payment_modes_master pmm," +
            "transport_modes_master tmm,currency cur,t_and_c_master tcm,status s,"+seller_type+" st,"+buyer_type+" bt ";
            sql_where = "po.proj_id = p.proj_id " +
            "and pmm.mode = po.mode_of_payment and tmm.mode = po.mode_of_transport and cur.currency_id = po.po_currency " +
            "and tcm.t_and_c_id = po.terms_and_condition and po.status = s.status_id " +
            "and bt."+getBuyerMappingParam+" = po.buyer_id and st."+getSellerMappingParam+" = po.seller_id ";
             */
            sql_select = "select po.proj_id,p.proj_name ";
            sql_from = "from purchase_order po, projects p ";
            sql_where = "po.proj_id = p.proj_id ";

            if (!po_number.equals("")) {
                sql_where += "and po_number ='" + po_number + "' ";
            }

            sql_select += sql_from;

            if (!sql_where.equals("")) {
                sql_select += "where " + sql_where;
            }
             //System.out.println("sql_select for display table 1 in POInfo.java of pathfinder.functions.revenue : "+sql_select);

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
                }
            } else {
                proj_id = "";
                proj_name = "";
            }

            sql_select = "select po.po_currency,cur.long_name,cur.short_name ";
            sql_from = "from purchase_order po, currency cur ";
            sql_where = "cur.currency_id = po.po_currency ";

            if (!po_number.equals("")) {
                sql_where += "and po_number ='" + po_number + "' ";
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
                }
            } else {
                currency_id = "";
                currency_name = "";
                currency_shortName = "";
            }

            sql_select = "select po.terms_and_condition,tcm.name ";
            sql_from = "from purchase_order po, t_and_c_master tcm ";
            sql_where = "tcm.t_and_c_id = po.terms_and_condition ";

            if (!po_number.equals("")) {
                sql_where += "and po_number ='" + po_number + "' ";
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

            sql_select = "select po.status,s.status ";
            sql_from = "from purchase_order po, status s ";
            sql_where = "po.status = s.status_id ";

            if (!po_number.equals("")) {
                sql_where += "and po_number ='" + po_number + "' ";
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

            sql_select = "select po.mode_of_transport,tmm.name ";
            sql_from = "from purchase_order po,transport_modes_master tmm ";
            sql_where = "tmm.mode = po.mode_of_transport ";

            if (!po_number.equals("")) {
                sql_where += "and po_number ='" + po_number + "' ";
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
                    del_id = temp_val;

                    temp_val = rs.getString(2);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    del_name = temp_val;
                }
            } else {
                del_id = "";
                del_name = "";
            }

            sql_select = "select po.mode_of_payment,pmm.name ";
            sql_from = "from purchase_order po,payment_modes_master pmm ";
            sql_where = "pmm.mode = po.mode_of_payment ";

            if (!po_number.equals("")) {
                sql_where += "and po_number ='" + po_number + "' ";
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


            if (!getBuyerMappingParam.equals("") && !buyer_type.equals("")) {
//System.out.println("ifbuyer");
                String isPerson = "";
                if(buyer_type.equals("contacts"))
                {
                    sql_select = "select concat(firstname,' ', surname),concat(address_1,',', address_2),city,state,zipcode,country,concat(phone_primary,'(primary), ', phone_secondary,'(secondary)'),concat(fax1,', ', fax2,', ',fax3),'contacts',c.contact_id,company,c.is_person ";
                    sql_from = "from contacts c, contacttype_map cm, contacts_type_master ctm,purchase_order po ";
                    sql_where = "c.contact_id = cm.contact_id and cm.type_id = ctm.type_id and cm.type_id = 3 AND (c.is_person='1' OR c.is_person='2') and c.contact_id=po.buyer_id ";

                }
                else
                {
                sql_select = "select bt." + getBuyerParam + ",po.shipping_address,po.shipping_city,po.shipping_state,po.shipping_zipcode,po.shipping_country,po.shipping_phone,po.shipping_fax,po.buyer_type,po.buyer_id ";
                sql_from = "from purchase_order po," + buyer_type + " bt ";
                sql_where = "bt." + getBuyerMappingParam + " = po.buyer_id ";


                
                }

                 if (!po_number.equals("")) {
                      sql_where += "and po_number ='" + po_number + "' ";
                    }

                sql_select += sql_from + "where " + sql_where;
                 //System.out.println("sql_select for display table 1 in POInfo.java of pathfinder.functions.revenue : "+sql_select);
                rs = stmt.executeQuery(sql_select);

                if (rs.next()) {

                    rs = stmt.executeQuery(sql_select);
                    while (rs.next()) {

                        if(buyer_type.equals("contacts"))
                        {
                            temp_val = rs.getString(12);
                            if(rs.wasNull()) {
                             temp_val="";
                               }
                             isPerson = temp_val;


                        

                        if (isPerson.equals("1")) //person
                        {
                            temp_val = splChar.decoding(rs.getString(1));
                            if (rs.wasNull()) {
                                temp_val = "";
                            }
                            buyer_name = temp_val;
                        } else if (isPerson.equals("2")) //company
                        {
                            temp_val = splChar.decoding(rs.getString(11));
                            if (rs.wasNull()) {
                                temp_val = "";
                            }
                            buyer_name = (temp_val);
                        }
                        }
                        else
                        {
                             temp_val = rs.getString(1);
                            if (rs.wasNull()) {
                                temp_val = "";
                            }
                            buyer_name = temp_val;
                        }

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
                        buyer_type = temp_val;

                        temp_val = rs.getString(10);
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
                    buyer_type = "";
                    buyer_id = "";
                }
            } else {
                //System.out.println("elsebuyer");
                buyer_name = "";
                buyer_add = "";
                buyer_city = "";
                buyer_state = "";
                buyer_zip = "";
                buyer_country = "";
                buyer_phone = "";
                buyer_fax = "";
                buyer_type = "";
                buyer_id = "";
            }

            // System.out.println("getSellerMappingParam : "+getSellerMappingParam);
            if (!getSellerMappingParam.equals("") && !seller_type.equals("")) {

                sql_select = "select st." + getSellerMappingParam + ",concat(st.firstname,' ',st.surname),po.seller_address,po.seller_city,po.seller_state,po.seller_zipcode,po.seller_country,po.seller_phone,po.seller_fax,po.seller_type,st.company ";
                sql_from = "from purchase_order po," + seller_type + " st ";
                sql_where = "st." + getSellerMappingParam + " = po.seller_id ";

                if (!po_number.equals("")) {
                    sql_where += "and po_number ='" + po_number + "' ";
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
                        seller_id = temp_val;

                        temp_val = splChar.decoding(rs.getString(2));
                        if (rs.wasNull()) {
                            temp_val = rs.getString(11);
                        }
                        seller_name = temp_val;

                        temp_val = rs.getString(3);
                        if (rs.wasNull()) {
                            temp_val = "";
                        }
                        seller_add = temp_val;

                        temp_val = rs.getString(4);
                        if (rs.wasNull()) {
                            temp_val = "";
                        }
                        seller_city = temp_val;

                        temp_val = rs.getString(5);
                        if (rs.wasNull()) {
                            temp_val = "";
                        }
                        seller_state = temp_val;

                        temp_val = rs.getString(6);
                        if (rs.wasNull()) {
                            temp_val = "";
                        }
                        seller_zip = temp_val;

                        temp_val = rs.getString(7);
                        if (rs.wasNull()) {
                            temp_val = "";
                        }
                        seller_country = temp_val;

                        temp_val = rs.getString(8);
                        if (rs.wasNull()) {
                            temp_val = "";
                        }
                        seller_phone = temp_val;

                        temp_val = rs.getString(9);
                        if (rs.wasNull()) {
                            temp_val = "";
                        }
                        seller_fax = temp_val;

                        temp_val = rs.getString(10);
                        if (rs.wasNull()) {
                            temp_val = "";
                        }
                        seller_type = temp_val;
                    }
                } else {
                    seller_id = "";
                    seller_name = "";
                    seller_add = "";
                    seller_city = "";
                    seller_state = "";
                    seller_zip = "";
                    seller_country = "";
                    seller_phone = "";
                    seller_fax = "";
                    seller_type = "";
                }
            } else {
                seller_id = "";
                seller_name = "";
                seller_add = "";
                seller_city = "";
                seller_state = "";
                seller_zip = "";
                seller_country = "";
                seller_phone = "";
                seller_fax = "";
                seller_type = "";
            }

            sql_select = "";
            sql_from = "";
            sql_where = "";

            sql_select = "select po.po_value ";
            sql_from = "from purchase_order po ";

            if (!po_number.equals("")) {
                if (!sql_where.equals("")) {
                    sql_where += "and ";
                }
                sql_where += "po_number ='" + po_number + "' ";
            }

            if (!sql_where.equals("")) {
                sql_select += sql_from + "where " + sql_where;
            } else {
                sql_select += sql_from;
            }


            // System.out.println("sql_select for display table 1 in POInfo.java of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            if (rs.next()) {
                rs = stmt.executeQuery(sql_select);
                while (rs.next()) {
                    temp_val = rs.getString(1);
                    if (rs.wasNull()) {
                        temp_val = "";
                    }
                    po_value = temp_val;
                }
            } else {
                po_value = "";
            }

            sql_select = "";
            sql_from = "";
            sql_where = "";

            sql_select = "select pot.tax_id,taa.name,pot.value,pot.total ";
            sql_from = "from tax_and_addons taa, po_taxes pot ";
            sql_where = "taa.tax_id = pot.tax_id ";

            if (!po_number.equals("")) {
                sql_where += "and pot.po_number ='" + po_number + "' ";
            }
            sql_select += sql_from + "where " + sql_where;
            // System.out.println("sql_select for display table 2 in POInfo.java of pathfinder.functions.revenue : "+sql_select);

            rs = stmt.executeQuery(sql_select);
            tax_id.clear();
            tax_name.clear();
            tax_value.clear();
            tax_total.clear();

            while (rs.next()) {

                temp_val = "";
                temp_val = rs.getString(1);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                tax_id.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(2);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                tax_name.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(3);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                tax_value.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(4);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                tax_total.add(temp_val);
            }

            sql_select = "";
            sql_from = "";
            sql_where = "";

            sql_select = "select pol.item_id,blm.name,pol.rate,pol.quantity,pol.description,pol.total,u.unit_name,pol.unit_id,c.category_id,cc.category ";
            sql_from = "from units_master u,billing_lineitems_master blm,cost_center c "+
                       "  RIGHT JOIN po_lineitems pol  ON c.item_id=pol.item_id AND c.item_type='lineitem' "+
                       " LEFT JOIN  costcenter_category cc ON c.category_id=cc.category_id ";
            sql_where = "blm.billing_item_id = pol.item_id AND u.unit_id=pol.unit_id  ";

            if (!po_number.equals("")) {
                sql_where += "and pol.po_number ='" + po_number + "' ";
            }
            sql_select += sql_from + "where " + sql_where + "  order by c.category_id";

            //System.out.println("sql_select for display table 3 in POInfo.java of pathfinder.functions.revenue : "+sql_select);

            rs = stmt.executeQuery(sql_select);
            item_id.clear();
            item_name.clear();
            item_rate.clear();
            item_quantity.clear();
            item_unit.clear();
            item_desc.clear();
            item_value.clear();
            category_id.clear();
            category_name.clear();
            
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

        public void getPOforReport() {
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            //System.out.println("PROJ ID: "+proj_id);
//            String query = "SELECT ccc.category, it.name, li.rate, SUM(li.quantity), SUM(li.total) "
//                            +"FROM po_lineitems li, purchase_order po, billing_lineitems_master it, projects p, cost_center cc, costcenter_category ccc "
//                            + "WHERE po.po_number=li.po_number AND it.billing_item_id=li.line_item_id AND p.proj_id=po.proj_id AND li.line_item_id=cc.item_id "
//                            + "AND cc.category_id=ccc.category_id AND po.proj_id='"+ proj_id +"' GROUP BY ccc.category, it.name";


            /* Madan: changing the query and removing the line_item_id. we have to group by activity
             *          and all we need is activity and sum(total).
             *          However, the other variables are used (in a rather confusing way) in
             *          the caller. Hence retaining the cols with an aggregate function
             */
            String query ="SELECT pl.line_item_id, pl.activity_description, pl.rate, pl.quantity, SUM(pl.total),pl.received "
                            + "FROM po_lineitems pl, projects p "
                            + "WHERE p.proj_id='"+proj_id+"' AND p.proj_id = pl.proj_id "
                            + "GROUP BY pl.activity_description, pl.received ORDER BY pl.received ";

            //System.out.println("SQL :"+query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    categoryList.add(rs.getString(1).toString());
                } else {
                    categoryList.add("");
                }
                if (rs.getString(2) != null) {
                    functionList.add(rs.getString(2).toString());
                } else {
                    functionList.add("");
                }
                if (rs.getString(3) != null) {
                    rateList.add(rs.getString(3).toString());
                } else {
                    rateList.add("");
                }
                if (rs.getString(4) != null) {
                    quantityList.add(rs.getString(4).toString());
                } else {
                    quantityList.add("");
                }
                if (rs.getString(5) != null) {
                    totalList.add(rs.getString(5).toString());
                } else {
                    totalList.add("");
                }
                if (rs.getString(6) != null) {
                    poReceivedFlagList.add(rs.getString(6).toString());
                } else {
                    poReceivedFlagList.add("");
                }
            }
            
            query = "SELECT pt.tax_id, ta.name, pt.value, SUM(pt.total) " +
                        "FROM po_taxes pt, tax_and_addons ta, purchase_order po, projects p " +
                        "WHERE p.proj_id=po.proj_id AND po.po_number=pt.po_number AND pt.tax_id = ta.tax_id AND p.proj_id='"+proj_id+"' " +
                        "GROUP BY pt.tax_id";
            //System.out.println("Query for PO Tax : "+query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    taxIdVal.add(rs.getString(1).toString());
                } else {
                    taxIdVal.add("");
                }
                if (rs.getString(2) != null) {
                    taxNameVal.add(rs.getString(2).toString());
                } else {
                    taxNameVal.add("");
                }
                if (rs.getString(3) != null) {
                    taxRate.add(rs.getString(3).toString());
                } else {
                    taxRate.add("");
                }
                if (rs.getString(4) != null) {
                    taxAmt.add(rs.getString(4).toString());
                } else {
                    taxAmt.add("");
                }
            }
            rs.close();
            stmt.close();
            con.close();
//            System.out.println(">"+tax_id);
//            System.out.println(">"+taxName);
//            System.out.println(">"+taxRate);
//            System.out.println(">"+taxAmt);
//            System.out.println(">"+categoryList);
//            System.out.println(">"+functionList);
//            System.out.println(">"+rateList);
//            System.out.println(">"+quantityList);
//            System.out.println(">"+totalList);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    public void GetOpenPO() {
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            String query ="SELECT SUM(pl.total) FROM po_lineitems pl,projects p "
                    + "WHERE p.proj_id = pl.proj_id AND pl.received = '0' AND pl.proj_id = '"+proj_id+"' GROUP BY pl.proj_id";

            //System.out.println("SQL :"+query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    openPO = Double.parseDouble(rs.getString(1));
                } else {
                    openPO = Double.parseDouble("0.0");
                }
            }
            rs.close();
            stmt.close();
            con.close();
            //System.out.println("PO VAL VAL : "+openPO);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }
}
