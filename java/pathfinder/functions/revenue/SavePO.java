/*
 * SavePO.java
 *
 * Created on February 17, 2010, 6:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import java.io.Serializable;
import pathfinder.functions.*;
import java.sql.*;
import java.util.*;
import pathfinder.functions.generic.DBLog;

/**
 *
 * @author ramyamaims
 */
public class SavePO implements Serializable {

    connection.DBconnection conProp = new connection.DBconnection();
    POInfo poInfo = new POInfo();
    DBLog dbLog = new DBLog();
    pathfinder.functions.generic.PFUtil pfUtil = new pathfinder.functions.generic.PFUtil();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql_select = "";
    private int intAddRes = 0;
    private int intDBAddRes = 0;
    private int intTaxRes = 0;
    private int intItemRes = 0;
    private int intRes = 0;
    private int countTaxesVal = 0;
    private int counter_taxes = 0;
    private int countItemsVal = 0;
    private int counter_items = 0;
    private List log_table_name = new ArrayList();
    private List log_field_name = new ArrayList();
    private List log_linked_field_name = new ArrayList();
    private List log_old_value = new ArrayList();
    private List log_new_value = new ArrayList();
    private List log_changed_by = new ArrayList();
    private List log_linked_field_value = new ArrayList();

    
    private List log_reference_table = new ArrayList();
    private List log_reference_field = new ArrayList();
    private List log_reference_value = new ArrayList();
    private List list_tax = new ArrayList();
    private List list_item = new ArrayList();
    private static int po_number = 0;
    private String proj_id = "";
    private String text = "";
    private String buyer = "";
    private String shipping_address = "";
    private String shipping_city = "";
    private String shipping_country = "";
    private String shipping_state = "";
    private String shipping_zipcode = "";
    private String shipping_phone = "";
    private String shipping_fax = "";
    private String buyer_type = "";
    private String mode_of_payment = "";
    private String seller = "";
    private String seller_address = "";
    private String seller_city = "";
    private String seller_country = "";
    private String seller_state = "";
    private String seller_zipcode = "";
    private String seller_phone = "";
    private String seller_fax = "";
    private String seller_type = "";
    private String mode_of_transport = "";
    private String po_currency = "";
    private String terms_and_condition = "";
    private String status = "";
    private String po_value = "";
    private String sesEmp = "";
    private String mod_proj_id = "";
    private String mod_text = "";
    private String mod_buyer = "";
    private String mod_shipping_address = "";
    private String mod_shipping_city = "";
    private String mod_shipping_country = "";
    private String mod_shipping_state = "";
    private String mod_shipping_zipcode = "";
    private String mod_shipping_phone = "";
    private String mod_shipping_fax = "";
    private String mod_buyer_type = "";
    private String mod_mode_of_payment = "";
    private String mod_seller = "";
    private String mod_seller_address = "";
    private String mod_seller_city = "";
    private String mod_seller_country = "";
    private String mod_seller_state = "";
    private String mod_seller_zipcode = "";
    private String mod_seller_phone = "";
    private String mod_seller_fax = "";
    private String mod_seller_type = "";
    private String mod_mode_of_transport = "";
    private String mod_po_currency = "";
    private String mod_terms_and_condition = "";
    private String mod_status = "";
    private String mod_po_value = "";
    private String mod_getUnit="";
    private List list_getTaxParam = new ArrayList();
    private List list_getHidTaxParam = new ArrayList();
    private List list_getTaxValueParam = new ArrayList();
    private List list_getTaxPriceParam = new ArrayList();
    private List list_getItemParam = new ArrayList();
    private List list_getHidItemParam = new ArrayList();
    private List list_getUnitParam = new ArrayList();
    private List list_getUnitPriceParam = new ArrayList();
    private List list_getQuantityParam = new ArrayList();
    private List list_getDescParam = new ArrayList();
    private List list_getTotalParam = new ArrayList();
    private String getTaxParam = "";
    private String getHidTaxParam = "";
    private String getTaxValueParam = "";
    private String getTaxPriceParam = "";
    private String mod_getTaxParam = "";
    private String mod_getHidTaxParam = "";
    private String mod_getTaxValueParam = "";
    private String mod_getTaxPriceParam = "";
    private String getItemParam = "";
    private String getHidItemParam = "";
    private String getUnitPriceParam = "";
    private String getQuantityParam = "";

    private String getDescParam = "";
    private String getTotalParam = "";
    private String mod_getItemParam = "";
    private String mod_getHidItemParam = "";
    private String mod_getUnitPriceParam = "";
    private String mod_getQuantityParam = "";
    private String mod_getDescParam = "";
    private String mod_getTotalParam = "";
    private String getUnit="";

    public void setSesEmp(String sesEmp) {
        this.sesEmp = sesEmp;
    }

    public void setProjId(String proj_id) {
        this.proj_id = proj_id;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public void setShipAdd(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public void setShipCity(String shipping_city) {
        this.shipping_city = shipping_city;
    }

    public void setShipCountry(String shipping_country) {
        this.shipping_country = shipping_country;
    }

    public void setShipState(String shipping_state) {
        this.shipping_state = shipping_state;
    }

    public void setShipZipcode(String shipping_zipcode) {
        this.shipping_zipcode = shipping_zipcode;
    }

    public void setShipPhone(String shipping_phone) {
        this.shipping_phone = shipping_phone;
    }

    public void setShipFax(String shipping_fax) {
        this.shipping_fax = shipping_fax;
    }

    public void setPayMode(String mode_of_payment) {
        this.mode_of_payment = mode_of_payment;
    }

    public void setBuyerType(String buyer_type) {
        this.buyer_type = buyer_type;
    }

    public void setSellerType(String seller_type) {
        this.seller_type = seller_type;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void setSellerAdd(String seller_address) {
        this.seller_address = seller_address;
    }

    public void setSellerCity(String seller_city) {
        this.seller_city = seller_city;
    }

    public void setSellerCountry(String seller_country) {
        this.seller_country = seller_country;
    }

    public void setSellerState(String seller_state) {
        this.seller_state = seller_state;
    }

    public void setSellerZipcode(String seller_zipcode) {
        this.seller_zipcode = seller_zipcode;
    }

    public void setSellerPhone(String seller_phone) {
        this.seller_phone = seller_phone;
    }

    public void setSellerFax(String seller_fax) {
        this.seller_fax = seller_fax;
    }

    public void setTransMode(String mode_of_transport) {
        this.mode_of_transport = mode_of_transport;
    }

    public void setPOCurrency(String po_currency) {
        this.po_currency = po_currency;
    }

    public void setTermsAndCond(String terms_and_condition) {
        this.terms_and_condition = terms_and_condition;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPOValue(String po_value) {
        this.po_value = po_value;
    }

    public void setPONumber(int po_number) {
        this.po_number = po_number;
    }

    public void setType(String text) {
        this.text = text;
    }

    public void setTaxType(List list_tax) {
        this.list_tax = list_tax;
    }

    public void setLineType(List list_item) {
        this.list_item = list_item;
    }

    public void setTaxId(List list_getTaxParam) {
        this.list_getTaxParam = list_getTaxParam;
    }

    public void setHidTaxId(List list_getHidTaxParam) {
        this.list_getHidTaxParam = list_getHidTaxParam;
    }

    public void setTaxValue(List list_getTaxValueParam) {
        this.list_getTaxValueParam = list_getTaxValueParam;
    }

    public void setTaxPrice(List list_getTaxPriceParam) {
        this.list_getTaxPriceParam = list_getTaxPriceParam;
    }

    public void str_setTaxId(String getTaxParam) {
        this.getTaxParam = getTaxParam;
    }

    public void str_setHidTaxId(String getHidTaxParam) {
        this.getHidTaxParam = getHidTaxParam;
    }

    public void str_setTaxValue(String getTaxValueParam) {
        this.getTaxValueParam = getTaxValueParam;
    }

    public void str_setTaxPrice(String getTaxPriceParam) {
        this.getTaxPriceParam = getTaxPriceParam;
    }

    public void setItemId(List list_getItemParam) {
        //System.out.println("getItemParam : " + getItemParam);
        this.list_getItemParam = list_getItemParam;
    }

    public void setHidItemId(List list_getHidItemParam) {
        this.list_getHidItemParam = list_getHidItemParam;
    }

    public void setUnitPrice(List list_getUnitPriceParam) {
        this.list_getUnitPriceParam = list_getUnitPriceParam;
    }

    public void setUnit(List list_getUnitParam)
    {
        this.list_getUnitParam = list_getUnitParam;
    }

    public void setQuantity(List list_getQuantityParam) {
        this.list_getQuantityParam = list_getQuantityParam;
    }

    public void setDesc(List list_getDescParam) {

        this.list_getDescParam = list_getDescParam;
    }

    public void setTotal(List list_getTotalParam) {
        this.list_getTotalParam = list_getTotalParam;
    }

    public void str_setItemId(String getItemParam) {
        //System.out.println("getItemParam : " + getItemParam);
        this.getItemParam = getItemParam;
    }

    public void str_setHidItemId(String getHidItemParam) {
        this.getHidItemParam = getHidItemParam;
    }

    public void str_setUnitPrice(String getUnitPriceParam) {
        this.getUnitPriceParam = getUnitPriceParam;
    }

     public void str_setUnitParam(String getUnit) {
        this.getUnit = getUnit;
    }

    public void str_setQuantity(String getQuantityParam) {
        this.getQuantityParam = getQuantityParam;
    }

    public void str_setDesc(String getDescParam) {
        //encode special characters
        getDescParam = pfUtil.encodeSpecialCharacter(getDescParam);
        this.getDescParam = getDescParam;

    }

    public void str_setTotal(String getTotalParam) {
        this.getTotalParam = getTotalParam;
    }

    public int getAddPO() {
        return intRes;
    }

    public int getAddPOLineItem() {
        return intItemRes;
    }

    public int getAddPOTaxes() {
        return intTaxRes;
    }

    public int getPONumber() {
        return po_number;
    }

    public Connection getConProp() {

        try {

            con = conProp.getSampleProperty();
            con.setAutoCommit(false);


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        return con;
    }

    public void addPO() {

        log_table_name.clear();
        log_field_name.clear();
        log_linked_field_name.clear();

        log_old_value.clear();
        log_new_value.clear();
        log_changed_by.clear();
        log_linked_field_value.clear();
        log_reference_table.clear();
        log_reference_field.clear();
        log_reference_value.clear();

        try {
            con = getConProp();
            stmt = con.createStatement();


            String updateBuyer_Sql = "";
            String updateField = "";
            updateBuyer_Sql = " update purchase_order set ";
            String where = " where po_number=" + po_number + " ";

            if (text.equals("modify")) {
                poInfo.setPONumber(String.valueOf(po_number));
                poInfo.getPOInfo();
                mod_proj_id = " '" + poInfo.getProjId() + "' ";
                mod_buyer = " '" + poInfo.getBuyerID() + "' ";
                mod_shipping_address = " '" + poInfo.getBuyerAdd() + "' ";
                mod_shipping_city = " '" + poInfo.getBuyerCity() + "' ";
                mod_shipping_country = " '" + poInfo.getBuyerCountry() + "' ";
                mod_shipping_state = " '" + poInfo.getBuyerState() + "' ";
                mod_shipping_zipcode = " '" + poInfo.getBuyerZip() + "' ";
                mod_shipping_phone = " '" + poInfo.getBuyerPhone() + "' ";
                mod_shipping_fax = " '" + poInfo.getBuyerFax() + "' ";
                mod_buyer_type = " '" + poInfo.getBuyerType() + "' ";
                mod_mode_of_payment = " '" + poInfo.getPayID() + "' ";

                mod_seller = " '" + poInfo.getSellerID() + "' ";
                mod_seller_address = " '" + poInfo.getSellerAdd() + "' ";
                mod_seller_city = " '" + poInfo.getSellerCity() + "' ";
                mod_seller_country = " '" + poInfo.getSellerCountry() + "' ";
                mod_seller_state = " '" + poInfo.getSellerState() + "' ";
                mod_seller_zipcode = " '" + poInfo.getSellerZip() + "' ";
                mod_seller_phone = " '" + poInfo.getSellerPhone() + "' ";
                mod_seller_fax = " '" + poInfo.getSellerFax() + "' ";
                mod_seller_type = " '" + poInfo.getSellerType() + "' ";
                mod_mode_of_transport = " '" + poInfo.getDelID() + "' ";
                mod_po_currency = " '" + poInfo.getCurrencyId() + "' ";
                mod_terms_and_condition = " '" + poInfo.getTermsID() + "' ";
                mod_status = " '" + poInfo.getStatusID() + "' ";
                mod_po_value = " '" + poInfo.getPOValue() + "' ";
            }

            if (text.equals("")) {
                rs = stmt.executeQuery("select max(po_number) from purchase_order");

                while (rs.next()) {
                    po_number = rs.getInt(1);
                    if (rs.wasNull()) {
                        po_number = 0;
                    }
                }

                po_number++;
            }
            //System.out.println("po_number : " + po_number);


            if (proj_id.equals("") || proj_id.equals("All")) {
                proj_id = null;
            } else {
                proj_id = " '" + proj_id + "' ";
                if (text.equals("modify")) {


                    if (proj_id != null && !proj_id.equals(mod_proj_id)) {
                        log_table_name.add("purchase_order");
                        log_field_name.add("proj_id");
                        log_linked_field_name.add("po_number");
                        log_linked_field_value.add(String.valueOf(po_number));
                        log_reference_table.add("projects");
                        log_reference_field.add("proj_id");
                        log_reference_value.add("");
                        log_old_value.add(mod_proj_id);
                        log_new_value.add(proj_id);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", proj_id=" + proj_id + " ";
                    } else {
                        updateField += " proj_id=" + proj_id + " ";
                    }
                }
            }

            //System.out.println("updateField : " + updateField.length());

            //System.out.println("proj_id : " + proj_id);
            //System.out.println("updateField : " + updateField);



            if (buyer_type.equals("") || buyer_type.equals("All")) {
                buyer_type = null;
            } else {
                buyer_type = " '" + buyer_type + "' ";
            }

            if (shipping_address.equals("") || shipping_address.equals("All")) {
                shipping_address = null;
            } else {
                shipping_address = " '" + shipping_address + "' ";
            }

            if (shipping_city.equals("") || shipping_city.equals("All")) {
                shipping_city = null;
            } else {
                shipping_city = " '" + shipping_city + "' ";
            }

            if (shipping_country.equals("") || shipping_country.equals("All")) {
                shipping_country = null;
            } else {
                shipping_country = " '" + shipping_country + "' ";
            }

            if (shipping_state.equals("") || shipping_state.equals("All")) {
                shipping_state = null;
            } else {
                shipping_state = " '" + shipping_state + "' ";
            }

            if (shipping_zipcode.equals("") || shipping_zipcode.equals("All")) {
                shipping_zipcode = null;
            } else {
                shipping_zipcode = " '" + shipping_zipcode + "' ";
            }

            if (shipping_phone.equals("") || shipping_phone.equals("All")) {
                shipping_phone = null;
            } else {
                shipping_phone = " '" + shipping_phone + "' ";
            }

            if (shipping_fax.equals("") || shipping_fax.equals("All")) {
                shipping_fax = null;
            } else {
                shipping_fax = " '" + shipping_fax + "' ";
            }

            if (buyer.equals("") || buyer.equals("All")) {
                buyer = null;
            } else {
                buyer = " '" + buyer + "' ";
            }
            if (text.equals("modify")) {

                if (buyer != null && !buyer.equals(mod_buyer)) {
                    log_table_name.add("purchase_order");
                    log_field_name.add("buyer_id");
                    log_linked_field_name.add("po_number");
                    log_linked_field_value.add(String.valueOf(po_number));
                    log_reference_table.add(" ");
                    log_reference_field.add(" ");
                    log_reference_value.add(" ");
                    log_old_value.add(mod_buyer);
                    log_new_value.add(buyer);
                    log_changed_by.add(sesEmp);
                }
            }
            if (updateField.length() > 0) {
                updateField += ", buyer_id=" + buyer + " ";
            } else {
                updateField += " buyer_id=" + buyer + " ";
            }
            //System.out.println("shipping_address : " + shipping_address);
            //System.out.println("mod_shipping_address : " + mod_shipping_address);
            if (shipping_address != null && !shipping_address.equals(mod_shipping_address)) {
                log_table_name.add("purchase_order");
                log_field_name.add("shipping_address");
                log_linked_field_name.add("po_number");
                log_linked_field_value.add(String.valueOf(po_number));
                log_reference_table.add(" ");
                log_reference_field.add(" ");
                log_reference_value.add(" ");
                log_old_value.add(mod_shipping_address);
                log_new_value.add(shipping_address);
                log_changed_by.add(sesEmp);
            }

            if (updateField.length() > 0) {
                updateField += ", shipping_address=" + shipping_address + " ";
            } else {
                updateField += " shipping_address=" + shipping_address + " ";
            }

            if (shipping_city != null && !shipping_city.equals(mod_shipping_city)) {
                log_table_name.add("purchase_order");
                log_field_name.add("shipping_city");
                log_linked_field_name.add("po_number");
                log_linked_field_value.add(String.valueOf(po_number));
                log_reference_table.add(" ");
                log_reference_field.add(" ");
                log_reference_value.add(" ");
                log_old_value.add(mod_shipping_city);
                log_new_value.add(shipping_city);
                log_changed_by.add(sesEmp);
            }

            if (updateField.length() > 0) {
                updateField += ", shipping_city=" + shipping_city + " ";
            } else {
                updateField += " shipping_city=" + shipping_city + " ";
            }

            if (shipping_country != null && !shipping_country.equals(mod_shipping_country)) {
                log_table_name.add("purchase_order");
                log_field_name.add("shipping_country");

                log_linked_field_name.add("po_number");
                log_linked_field_value.add(String.valueOf(po_number));
                log_old_value.add(mod_shipping_country);
                log_reference_table.add(" ");
                log_reference_field.add(" ");
                log_reference_value.add(" ");
                log_new_value.add(shipping_country);
                log_changed_by.add(sesEmp);
            }

            if (updateField.length() > 0) {
                updateField += ", shipping_country=" + shipping_country + " ";
            } else {
                updateField += " shipping_country=" + shipping_country + " ";
            }

            if (shipping_state != null && !shipping_state.equals(mod_shipping_state)) {
                log_table_name.add("purchase_order");
                log_field_name.add("shipping_state");

                log_linked_field_name.add("po_number");
                log_linked_field_value.add(String.valueOf(po_number));
                log_reference_table.add(" ");
                log_reference_field.add(" ");
                log_reference_value.add(" ");
                log_old_value.add(mod_shipping_state);
                log_new_value.add(shipping_state);
                log_changed_by.add(sesEmp);
            }

            if (updateField.length() > 0) {
                updateField += ", shipping_state=" + shipping_state + " ";
            } else {
                updateField += " shipping_state=" + shipping_state + " ";
            }

            if (shipping_zipcode != null && !shipping_zipcode.equals(mod_shipping_zipcode)) {
                log_table_name.add("purchase_order");
                log_field_name.add("shipping_zipcode");

                log_linked_field_name.add("po_number");
                log_linked_field_value.add(String.valueOf(po_number));
                log_reference_table.add(" ");
                log_reference_field.add(" ");
                log_reference_value.add(" ");
                log_old_value.add(mod_shipping_zipcode);
                log_new_value.add(shipping_zipcode);
                log_changed_by.add(sesEmp);
            }

            if (updateField.length() > 0) {
                updateField += ", shipping_zipcode=" + shipping_zipcode + " ";
            } else {
                updateField += " shipping_zipcode=" + shipping_zipcode + " ";
            }

            if (shipping_phone != null && !shipping_phone.equals(mod_shipping_phone)) {
                log_table_name.add("purchase_order");
                log_field_name.add("shipping_phone");

                log_linked_field_name.add("po_number");
                log_linked_field_value.add(String.valueOf(po_number));
                log_old_value.add(mod_shipping_phone);
                log_new_value.add(shipping_phone);
                log_reference_table.add(" ");
                log_reference_field.add(" ");
                log_reference_value.add(" ");
                log_changed_by.add(sesEmp);
            }

            if (updateField.length() > 0) {
                updateField += ", shipping_phone=" + shipping_phone + " ";
            } else {
                updateField += " shipping_phone=" + shipping_phone + " ";
            }

            if (shipping_fax != null && !shipping_fax.equals(mod_shipping_fax)) {
                log_table_name.add("purchase_order");
                log_field_name.add("shipping_fax");

                log_linked_field_name.add("po_number");
                log_linked_field_value.add(String.valueOf(po_number));
                log_reference_table.add(" ");
                log_reference_field.add(" ");
                log_reference_value.add(" ");
                log_old_value.add(mod_shipping_fax);
                log_new_value.add(shipping_fax);
                log_changed_by.add(sesEmp);
            }

            if (updateField.length() > 0) {
                updateField += ", shipping_fax=" + shipping_fax + " ";
            } else {
                updateField += " shipping_fax=" + shipping_fax + " ";
            }

            if (buyer_type != null && !buyer_type.equals(mod_buyer_type)) {
                log_table_name.add("purchase_order");
                log_field_name.add("buyer_type");

                log_linked_field_name.add("po_number");
                log_linked_field_value.add(String.valueOf(po_number));
                log_reference_table.add(" ");
                log_reference_field.add(" ");
                log_reference_value.add(" ");
                log_old_value.add(mod_buyer_type);
                log_new_value.add(buyer_type);
                log_changed_by.add(sesEmp);
            }

            if (updateField.length() > 0) {
                updateField += ", buyer_type=" + buyer_type + " ";
            } else {
                updateField += " buyer_type=" + buyer_type + " ";
            }



            //System.out.println("buyer : " + buyer);
            //System.out.println("updateField : " + updateField);

            if (mode_of_payment.equals("") || mode_of_payment.equals("All")) {
                mode_of_payment = null;
            } else {
                mode_of_payment = " '" + mode_of_payment + "' ";
                if (text.equals("modify")) {

                    if (mode_of_payment != null && !mode_of_payment.equals(mod_mode_of_payment)) {
                        log_table_name.add("purchase_order");
                        log_field_name.add("mode_of_payment");

                        log_linked_field_name.add("po_number");
                        log_reference_table.add("payment_modes_master");
                        log_reference_field.add("mode_of_payment");
                        log_reference_value.add(" ");
                        log_linked_field_value.add(String.valueOf(po_number));
                        log_old_value.add(mod_mode_of_payment);
                        log_new_value.add(mode_of_payment);
                        log_changed_by.add(sesEmp);
                    }

                    if (updateField.length() > 0) {
                        updateField += ", mode_of_payment=" + mode_of_payment + " ";
                    } else {
                        updateField += " mode_of_payment=" + mode_of_payment + " ";
                    }
                }
            }

            if (seller_type.equals("") || seller_type.equals("All")) {
                seller_type = null;
            } else {
                seller_type = " '" + seller_type + "' ";
            }

            if (seller_address.equals("") || seller_address.equals("All")) {
                seller_address = null;
            } else {
                seller_address = " '" + seller_address + "' ";
            }

            //System.out.println("seller_address : " + seller_address);
            if (seller_city.equals("") || seller_city.equals("All")) {
                seller_city = null;
            } else {
                seller_city = " '" + seller_city + "' ";
            }

            if (seller_country.equals("") || seller_country.equals("All")) {
                seller_country = null;
            } else {
                seller_country = " '" + seller_country + "' ";
            }

            if (seller_state.equals("") || seller_state.equals("All")) {
                seller_state = null;
            } else {
                seller_state = " '" + seller_state + "' ";
            }

            if (seller_zipcode.equals("") || seller_zipcode.equals("All")) {
                seller_zipcode = null;
            } else {
                seller_zipcode = " '" + seller_zipcode + "' ";
            }

            if (seller_phone.equals("") || seller_phone.equals("All")) {
                seller_phone = null;
            } else {
                seller_phone = " '" + seller_phone + "' ";
            }

            if (seller_fax.equals("") || seller_fax.equals("All")) {
                seller_fax = null;
            } else {
                seller_fax = " '" + seller_fax + "' ";
            }

            if (seller.equals("") || seller.equals("All")) {
                seller = null;
            } else {
                seller = " '" + seller + "' ";
            }
            if (text.equals("modify")) {

                if (seller != null && !seller.equals(mod_seller)) {
                    log_table_name.add("purchase_order");
                    log_field_name.add("seller_id");

                    log_linked_field_name.add("po_number");
                    log_linked_field_value.add(String.valueOf(po_number));
                    log_reference_table.add("contacts");
                    log_reference_field.add("contact_id");
                    log_reference_value.add(" ");
                    log_old_value.add(mod_seller);
                    log_new_value.add(seller);
                    log_changed_by.add(sesEmp);
                }
            }
            if (updateField.length() > 0) {
                updateField += ", seller_id=" + seller + " ";
            } else {
                updateField += " seller_id=" + seller + " ";
            }

            if (seller_address != null && !seller_address.equals(mod_seller_address)) {
                log_table_name.add("purchase_order");
                log_field_name.add("seller_address");

                log_linked_field_name.add("po_number");
                log_linked_field_value.add(String.valueOf(po_number));
                log_reference_table.add(" ");
                log_reference_field.add(" ");
                log_reference_value.add(" ");
                log_old_value.add(mod_seller_address);
                log_new_value.add(seller_address);
                log_changed_by.add(sesEmp);
            }

            if (updateField.length() > 0) {
                updateField += ", seller_address=" + seller_address + " ";
            } else {
                updateField += " seller_address=" + seller_address + " ";
            }

            if (seller_city != null && !seller_city.equals(mod_seller_city)) {
                log_table_name.add("purchase_order");
                log_field_name.add("seller_city");

                log_linked_field_name.add("po_number");
                log_linked_field_value.add(String.valueOf(po_number));
                log_reference_table.add(" ");
                log_reference_field.add(" ");
                log_reference_value.add(" ");
                log_old_value.add(mod_seller_city);
                log_new_value.add(seller_city);
                log_changed_by.add(sesEmp);
            }

            if (updateField.length() > 0) {
                updateField += ", seller_city=" + seller_city + " ";
            } else {
                updateField += " seller_city=" + seller_city + " ";
            }

            if (seller_country != null && !seller_country.equals(mod_seller_country)) {
                log_table_name.add("purchase_order");
                log_field_name.add("seller_country");

                log_linked_field_name.add("po_number");
                log_linked_field_value.add(String.valueOf(po_number));
                log_reference_table.add(" ");
                log_reference_field.add(" ");
                log_reference_value.add(" ");
                log_old_value.add(mod_seller_country);
                log_new_value.add(seller_country);
                log_changed_by.add(sesEmp);
            }

            if (updateField.length() > 0) {
                updateField += ", seller_country=" + seller_country + " ";
            } else {
                updateField += " seller_country=" + seller_country + " ";
            }

            if (seller_type != null && !seller_type.equals(mod_seller_type)) {
                log_table_name.add("purchase_order");
                log_field_name.add("seller_type");

                log_linked_field_name.add("po_number");
                log_linked_field_value.add(String.valueOf(po_number));
                log_reference_table.add(" ");
                log_reference_field.add(" ");
                log_reference_value.add(" ");
                log_old_value.add(mod_seller_type);
                log_new_value.add(seller_type);
                log_changed_by.add(sesEmp);
            }

            if (updateField.length() > 0) {
                updateField += ", seller_type=" + seller_type + " ";
            } else {
                updateField += " seller_type=" + seller_type + " ";
            }

            if (seller_state != null && !seller_state.equals(mod_seller_state)) {
                log_table_name.add("purchase_order");
                log_field_name.add("seller_state");

                log_linked_field_name.add("po_number");
                log_linked_field_value.add(String.valueOf(po_number));
                log_old_value.add(mod_seller_state);
                log_reference_table.add(" ");
                log_reference_field.add(" ");
                log_reference_value.add(" ");
                log_new_value.add(seller_state);
                log_changed_by.add(sesEmp);
            }

            if (updateField.length() > 0) {
                updateField += ", seller_state=" + seller_state + " ";
            } else {
                updateField += " seller_state=" + seller_state + " ";
            }

            if (seller_zipcode != null && !seller_zipcode.equals(mod_seller_zipcode)) {
                log_table_name.add("purchase_order");
                log_field_name.add("seller_zipcode");

                log_linked_field_name.add("po_number");
                log_linked_field_value.add(String.valueOf(po_number));
                log_old_value.add(mod_seller_zipcode);
                log_reference_table.add(" ");
                log_reference_field.add(" ");
                log_reference_value.add(" ");
                log_new_value.add(seller_zipcode);
                log_changed_by.add(sesEmp);
            }

            if (updateField.length() > 0) {
                updateField += ", seller_zipcode=" + seller_zipcode + " ";
            } else {
                updateField += " seller_zipcode=" + seller_zipcode + " ";
            }

            if (seller_phone != null && !seller_phone.equals(mod_seller_phone)) {
                log_table_name.add("purchase_order");
                log_field_name.add("seller_phone");

                log_linked_field_name.add("po_number");
                log_linked_field_value.add(String.valueOf(po_number));
                log_old_value.add(mod_seller_phone);
                log_reference_table.add(" ");
                log_reference_field.add(" ");
                log_reference_value.add(" ");
                log_new_value.add(seller_phone);
                log_changed_by.add(sesEmp);
            }

            if (updateField.length() > 0) {
                updateField += ", seller_phone=" + seller_phone + " ";
            } else {
                updateField += " seller_phone=" + seller_phone + " ";
            }

            if (seller_fax != null && !seller_fax.equals(mod_seller_fax)) {
                log_table_name.add("purchase_order");
                log_field_name.add("seller_fax");

                log_linked_field_name.add("po_number");
                log_linked_field_value.add(String.valueOf(po_number));
                log_old_value.add(mod_seller_fax);
                log_reference_table.add(" ");
                log_reference_field.add(" ");
                log_reference_value.add(" ");
                log_new_value.add(seller_fax);
                log_changed_by.add(sesEmp);
            }

            if (updateField.length() > 0) {
                updateField += ", seller_fax=" + seller_fax + " ";
            } else {
                updateField += " seller_fax=" + seller_fax + " ";
            }



            //System.out.println("seller_address : " + seller_address);


            if (mode_of_transport.equals("") || mode_of_transport.equals("All")) {
                mode_of_transport = null;
            } else {
                mode_of_transport = " '" + mode_of_transport + "' ";
                if (text.equals("modify")) {

                    if (mode_of_transport != null && !mode_of_transport.equals(mod_mode_of_transport)) {
                        log_table_name.add("purchase_order");
                        log_field_name.add("mode_of_transport");

                        log_linked_field_name.add("po_number");
                        log_linked_field_value.add(String.valueOf(po_number));
                        log_reference_table.add("transport_modes_master");
                        log_reference_field.add("mode");
                        log_reference_value.add(" ");
                        log_old_value.add(mod_mode_of_transport);
                        log_new_value.add(mode_of_transport);
                        log_changed_by.add(sesEmp);
                    }

                    if (updateField.length() > 0) {
                        updateField += ", mode_of_transport=" + mode_of_transport + " ";
                    } else {
                        updateField += " mode_of_transport=" + mode_of_transport + " ";
                    }
                }
            }

            if (po_currency.equals("") || po_currency.equals("All")) {
                po_currency = null;
            } else {
                po_currency = " '" + po_currency + "' ";
                if (text.equals("modify")) {

                    if (po_currency != null && !po_currency.equals(mod_po_currency)) {
                        log_table_name.add("purchase_order");
                        log_field_name.add("po_currency");

                        log_linked_field_name.add("po_number");
                        log_linked_field_value.add(String.valueOf(po_number));
                        log_old_value.add(mod_po_currency);
                        log_reference_table.add("currency");
                        log_reference_field.add("currency_id");
                        log_reference_value.add(" ");
                        log_new_value.add(po_currency);
                        log_changed_by.add(sesEmp);
                    }

                    if (updateField.length() > 0) {
                        updateField += ", po_currency=" + po_currency + " ";
                    } else {
                        updateField += " po_currency=" + po_currency + " ";
                    }
                }
            }

            if (status.equals("") || status.equals("All")) {
                status = null;
            } else {
                status = " '" + status + "' ";
                if (text.equals("modify")) {

                    if (status != null && !status.equals(mod_status)) {
                        log_table_name.add("purchase_order");
                        log_field_name.add("status");

                        log_linked_field_name.add("po_number");
                        log_linked_field_value.add(String.valueOf(po_number));
                        log_reference_table.add("status");
                        log_reference_field.add("status_id");
                        log_reference_value.add(" ");
                        log_old_value.add(mod_status);
                        log_new_value.add(status);
                        log_changed_by.add(sesEmp);
                    }

                    if (updateField.length() > 0) {
                        updateField += ", status=" + status + " ";
                    } else {
                        updateField += " status=" + status + " ";
                    }
                }
            }

            if (terms_and_condition.equals("") || terms_and_condition.equals("All")) {
                terms_and_condition = null;
            } else {
                terms_and_condition = " '" + terms_and_condition + "' ";
                if (text.equals("modify")) {

                    if (terms_and_condition != null && !terms_and_condition.equals(mod_terms_and_condition)) {
                        log_table_name.add("purchase_order");
                        log_field_name.add("terms_and_condition");

                        log_linked_field_name.add("po_number");
                        log_linked_field_value.add(String.valueOf(po_number));
                        log_reference_table.add("t_and_c_master");
                        log_reference_field.add("t_and_c_id");
                        log_reference_value.add(" ");
                        log_old_value.add(mod_terms_and_condition);
                        log_new_value.add(terms_and_condition);
                        log_changed_by.add(sesEmp);
                    }

                    if (updateField.length() > 0) {
                        updateField += ", terms_and_condition=" + terms_and_condition + " ";
                    } else {
                        updateField += " terms_and_condition=" + terms_and_condition + " ";
                    }
                }
            }

            if (po_value.equals("") || po_value.equals("All")) {
                po_value = null;
            } else {
                po_value = " '" + po_value + "' ";
                if (text.equals("modify")) {

                    if (po_value != null && !po_value.equals(mod_po_value)) {
                        log_table_name.add("purchase_order");
                        log_field_name.add("po_value");

                        log_linked_field_name.add("po_number");
                        log_linked_field_value.add(String.valueOf(po_number));
                        log_reference_table.add(" ");
                        log_reference_field.add(" ");
                        log_reference_value.add(" ");
                        log_old_value.add(mod_po_value);
                        log_new_value.add(po_value);
                        log_changed_by.add(sesEmp);
                    }

                    if (updateField.length() > 0) {
                        updateField += ", po_value=" + po_value + " ";
                    } else {
                        updateField += " po_value=" + po_value + " ";
                    }
                }
            }

            intAddRes = 0;
            if (text.equals("modify")) {
                updateBuyer_Sql = updateBuyer_Sql + updateField + where;
                //System.out.println("updateBuyer_Sql : " + updateBuyer_Sql);
                intAddRes = stmt.executeUpdate(updateBuyer_Sql);
                //System.out.println("UpdateStatus:" + intAddRes);
            } else {
                /*System.out.println("insert into purchase_order(po_number,po_date,proj_id,buyer_type,buyer_id,shipping_address,shipping_city,shipping_country,"
                        + "shipping_state,shipping_zipcode,shipping_phone,shipping_fax,mode_of_payment,seller_type,seller_id,seller_address,seller_city,"
                        + "seller_country,seller_state,seller_zipcode,seller_phone,seller_fax,mode_of_transport,po_currency,terms_and_condition,"
                        + "status,po_value) "
                        + "values('" + po_number + "',current_timestamp()," + proj_id + "," + buyer_type + "," + buyer + "," + shipping_address + "," + shipping_city + "," + shipping_country + ","
                        + "" + shipping_state + "," + shipping_zipcode + "," + shipping_phone + "," + shipping_fax + "," + mode_of_payment + "," + seller_type + "," + seller + "," + seller_address + "," + seller_city + ","
                        + "" + seller_country + "," + seller_state + "," + seller_zipcode + "," + seller_phone + "," + seller_fax + "," + mode_of_transport + "," + po_currency + "," + terms_and_condition + ","
                        + "" + status + "," + po_value + ")");*/
                intAddRes = stmt.executeUpdate("insert into purchase_order(po_number,po_date,proj_id,buyer_type,buyer_id,shipping_address,shipping_city,shipping_country,"
                        + "shipping_state,shipping_zipcode,shipping_phone,shipping_fax,mode_of_payment,seller_type,seller_id,seller_address,seller_city,"
                        + "seller_country,seller_state,seller_zipcode,seller_phone,seller_fax,mode_of_transport,po_currency,terms_and_condition,"
                        + "status,po_value) "
                        + "values('" + po_number + "',current_timestamp()," + proj_id + "," + buyer_type + "," + buyer + "," + shipping_address + "," + shipping_city + "," + shipping_country + ","
                        + "" + shipping_state + "," + shipping_zipcode + "," + shipping_phone + "," + shipping_fax + "," + mode_of_payment + "," + seller_type + "," + seller + "," + seller_address + "," + seller_city + ","
                        + "" + seller_country + "," + seller_state + "," + seller_zipcode + "," + seller_phone + "," + seller_fax + "," + mode_of_transport + "," + po_currency + "," + terms_and_condition + ","
                        + "" + status + "," + po_value + ")");
            }

            //if(!text.equals("modify")){
            rs = stmt.executeQuery("select buyer_type,seller_type from purchase_order where po_number=" + po_number + " ");

            while (rs.next()) {
                buyer_type = rs.getString(1);
                seller_type = rs.getString(2);
            }
            //}
            //con.close();
            //System.out.println("getTaxParam : " + getTaxParam);
            //System.out.println("getItemParam : " + getItemParam);
            //System.out.println("list_getTaxValueParam : " + list_getTaxValueParam);
            if (list_getTaxParam.size() > 0 || list_getItemParam.size() > 0) {
                if (list_getTaxParam.size() > 0) {
                    countTaxesVal = addPOTaxes(con);
                    //System.out.println("list_getTaxValueParam : " + list_getTaxValueParam);
                }

                if (list_getItemParam.size() > 0) {
                    countItemsVal = addPOLineItem(con);
                    //System.out.println("list_getItemParam : " + list_getItemParam);

                }
            }

            if (list_getTaxParam.size() > 0 && list_getItemParam.size() > 0) {

                if ((list_getTaxParam.size() == countTaxesVal && countTaxesVal != 0) && (list_getItemParam.size() == countItemsVal && countItemsVal != 0)) {
                    intRes = endConnection(con);
                }
            } else if (list_getTaxParam.size() > 0 && list_getItemParam.size() == 0) {
                if (list_getTaxParam.size() == countTaxesVal && countTaxesVal != 0) {
                    intRes = endConnection(con);
                }
            } else if (list_getTaxParam.size() == 0 && list_getItemParam.size() > 0) {
                if (list_getItemParam.size() == countItemsVal && countItemsVal != 0) {
                    intRes = endConnection(con);
                }
            } else if (list_getTaxParam.size() == 0 && list_getItemParam.size() == 0) {
                intRes = endConnection(con);
            }

        } catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException sqleRollBack) {
                sqleRollBack.printStackTrace();
            }
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            try {
                con.rollback();
            } catch (SQLException sqleRollBack) {
                sqleRollBack.printStackTrace();
            }
            npe.printStackTrace();
        }
    }

    public int addPOTaxes(Connection con) {

        //countTaxes = new int[list_getTaxParam.size()];
        counter_taxes = 0;

        //System.out.println("list_getTaxValueParam in addPOTaxes : " + list_getTaxValueParam);

        try {
            String updatePOTaxesSql = "";
            String updateField = "";
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            for (int loop = 0; loop < list_getTaxParam.size(); loop++) {

                mod_getTaxParam = "";
                mod_getHidTaxParam = "";
                mod_getTaxValueParam = "";
                mod_getTaxPriceParam = "";

                str_setTaxId(list_getTaxParam.get(loop).toString());
                str_setHidTaxId(list_getHidTaxParam.get(loop).toString());
                str_setTaxValue(list_getTaxValueParam.get(loop).toString());
                str_setTaxPrice(list_getTaxPriceParam.get(loop).toString());






                if (list_tax.get(loop).toString().equals("modify")) {
                    sql_select = "Select tax_id,value,total from po_taxes where po_number=" + po_number + " and tax_id = '" + getHidTaxParam + "' ";
                    //System.out.println("sql_select for tax  : " + sql_select);
                    rs = stmt.executeQuery(sql_select);

                    while (rs.next()) {
                        mod_getTaxParam = " '" + rs.getString("tax_id") + "' ";
                        //mod_getHidTaxParam = "";
                        mod_getTaxValueParam = " '" + rs.getString("value") + "' ";
                        mod_getTaxPriceParam = " '" + rs.getString("total") + "' ";
                    }
                }

                updatePOTaxesSql = " update po_taxes set ";
                String where = " where po_number=" + po_number + " and tax_id = '" + getHidTaxParam + "' ";

                if (getTaxParam.equals("") || getTaxParam.equals("All")) {
                    getTaxParam = null;
                } else {
                    getTaxParam = " '" + getTaxParam + "' ";
                }
                //System.out.println("getTaxParam : " + getTaxParam);

                //System.out.println("getHidTaxParam : " + getHidTaxParam);
                if (getHidTaxParam.equals("") || getHidTaxParam.equals("All")) {
                    getHidTaxParam = null;
                } else {
                    getHidTaxParam = " '" + getHidTaxParam + "' ";
                }
                //System.out.println("getHidTaxParam : " + getHidTaxParam);


                if (list_tax.get(loop).toString().equals("modify")) {

                    if (getTaxParam != null && !getTaxParam.equals(mod_getTaxParam)) {
                        log_table_name.add("po_taxes");
                        log_field_name.add("tax_id");

                        log_linked_field_name.add("po_number");
                        log_linked_field_value.add(String.valueOf(po_number));
                        log_reference_table.add("tax_and_addons");
                        log_reference_field.add("tax_id");
                        log_reference_value.add("");
                        log_old_value.add(mod_getTaxParam);
                        log_new_value.add(getTaxParam);
                        log_changed_by.add(sesEmp);
                    }

                    if (updateField.length() > 0) {
                        updateField += ", tax_id=" + getTaxParam + " ";
                    } else {
                        updateField += " tax_id=" + getTaxParam + " ";
                    }
                }



                if (getTaxValueParam.equals("") || getTaxValueParam.equals("All")) {
                    getTaxValueParam = null;
                } else {
                    getTaxValueParam = " '" + getTaxValueParam + "' ";
                }

                //System.out.println("getTaxValueParam : " + getTaxValueParam);

                if (list_tax.get(loop).toString().equals("modify")) {

                    if (getTaxValueParam != null && !getTaxValueParam.equals(mod_getTaxValueParam)) {
                        log_table_name.add("po_taxes");
                        log_field_name.add("value");

                        log_linked_field_name.add("po_number");
                        log_linked_field_value.add(String.valueOf(po_number));
                        log_old_value.add(mod_getTaxValueParam);
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
                        log_new_value.add(getTaxValueParam);
                        log_changed_by.add(sesEmp);
                    }

                    if (updateField.length() > 0) {
                        updateField += ", value=" + getTaxValueParam + " ";
                    } else {
                        updateField += " value=" + getTaxValueParam + " ";
                    }
                }



                if (getTaxPriceParam.equals("") || getTaxPriceParam.equals("All")) {
                    getTaxPriceParam = null;
                } else {
                    getTaxPriceParam = " '" + getTaxPriceParam + "' ";
                }


                if (list_tax.get(loop).toString().equals("modify")) {

                    if (getTaxPriceParam != null && !getTaxPriceParam.equals(mod_getTaxPriceParam)) {
                        log_table_name.add("po_taxes");
                        log_field_name.add("total");

                        log_linked_field_name.add("po_number");
                        log_linked_field_value.add(String.valueOf(po_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
                        log_old_value.add(mod_getTaxPriceParam);
                        log_new_value.add(getTaxPriceParam);
                        log_changed_by.add(sesEmp);
                    }

                    if (updateField.length() > 0) {
                        updateField += ", total=" + getTaxPriceParam + " ";
                    } else {
                        updateField += " total=" + getTaxPriceParam + " ";
                    }
                }

                //System.out.println("getTaxPriceParam : " + getTaxPriceParam);
                //System.out.println("po_number : " + po_number);
                //System.out.println("list_tax : " + list_tax.get(loop));

                intTaxRes = 0;
                //System.out.println("taxType:" + list_tax.get(loop).toString());
                if (list_tax.get(loop).toString().equals("delete")) {

                    stmt.addBatch("DELETE FROM po_taxes WHERE po_number=" + po_number + " and tax_id = " + getHidTaxParam + " ");
                    //System.out.println("DELETE FROM po_taxes WHERE po_number=" + po_number + " and tax_id = " + getHidTaxParam + "");
                } else if (list_tax.get(loop).toString().equals("modify")) {
                    updatePOTaxesSql = updatePOTaxesSql + updateField + where;
                    //System.out.println("updatePOTaxesSql : " + updatePOTaxesSql);
                    stmt.addBatch(updatePOTaxesSql);
                } else {
                    //System.out.println("insert into po_taxes(po_number,tax_id,value,total) "
                            //+ "values('" + po_number + "'," + getTaxParam + "," + getTaxValueParam + "," + getTaxPriceParam + ")");
                    stmt.addBatch("insert into po_taxes(po_number,tax_id,value,total) "
                            + "values('" + po_number + "'," + getTaxParam + "," + getTaxValueParam + "," + getTaxPriceParam + ")");
                }

            }
            int[] countTaxes = stmt.executeBatch();
            //System.out.println("countTaxes : " + countTaxes.length);
            for (int i = 0; i < countTaxes.length; i++) {
                //System.out.println(countTaxes[i]);
                if (countTaxes[i] == 1) {
                    counter_taxes++;
                }
            }
            //System.out.println("counter_taxes : " + counter_taxes);
            //con.close();
        } catch (BatchUpdateException b) {
            System.err.println("SQLException: " + b.getMessage());
            System.err.println("SQLState: " + b.getSQLState());
            System.err.println("Message: " + b.getMessage());
            System.err.println("Vendor: " + b.getErrorCode());
            try {
                con.rollback();
            } catch (SQLException sqleRollBack) {
                sqleRollBack.printStackTrace();
            }

        } catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException sqleRollBack) {
                sqleRollBack.printStackTrace();
            }
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            try {
                con.rollback();
            } catch (SQLException sqleRollBack) {
                sqleRollBack.printStackTrace();
            }
            npe.printStackTrace();
        }
        return counter_taxes;
    }

    public int addPOLineItem(Connection con) {
        //System.out.println("getItemParam :" + getItemParam);

        try {
            //con = conProp.getSampleProperty();            
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String updatePOLineItems_Sql = "";
            String updateField = "";
            //System.out.println("list_getItemParam in save PO time 1 :" + list_getItemParam);

            for (int loop = 0; loop < list_getItemParam.size(); loop++) {

                mod_getItemParam = "";
                mod_getHidItemParam = "";
                mod_getUnitPriceParam = "";
                mod_getQuantityParam = "";
                mod_getDescParam = "";
                mod_getTotalParam = "";

                str_setItemId(list_getItemParam.get(loop).toString());
                str_setUnitPrice(list_getUnitPriceParam.get(loop).toString());
                str_setUnitParam(list_getUnitParam.get(loop).toString());
                str_setQuantity(list_getQuantityParam.get(loop).toString());
                str_setDesc(list_getDescParam.get(loop).toString());
                str_setTotal(list_getTotalParam.get(loop).toString());
                str_setHidItemId(list_getHidItemParam.get(loop).toString());

                if (list_item.get(loop).toString().equals("modify")) {
                    sql_select = "select item_id,quantity,rate,description,total,unit_id from po_lineitems where po_number = " + po_number + " and item_id = '" + getHidItemParam + "' ";
                    //System.out.println("sql_select for line item  : " + sql_select);
                    rs = stmt.executeQuery("select item_id,quantity,rate,description,total,unit_id from po_lineitems where po_number = " + po_number + " and item_id = '" + getHidItemParam + "' ");
                    while (rs.next()) {
                        mod_getItemParam = " '" + rs.getString("item_id") + "' ";
                        mod_getUnitPriceParam = " '" + rs.getString("rate") + "' ";
                        mod_getUnit= " '" + rs.getString("unit_id") + "' ";
                        mod_getQuantityParam = " '" + rs.getString("quantity") + "' ";
                        mod_getDescParam = " '" + rs.getString("description") + "' ";
                        mod_getTotalParam = " '" + rs.getString("total") + "' ";
                    }
                }

                updatePOLineItems_Sql = " update po_lineitems set ";
                String where = " where po_number=" + po_number + "  and item_id = '" + getHidItemParam + "' ";

                if (getItemParam.equals("") || getItemParam.equals("All")) {
                    getItemParam = null;
                } else {
                    getItemParam = " '" + getItemParam + "' ";
                }

                if (getHidItemParam.equals("") || getHidItemParam.equals("All")) {
                    getHidItemParam = null;
                } else {
                    getHidItemParam = " '" + getHidItemParam + "' ";
                    ;
                }

                if (list_item.get(loop).toString().equals("modify")) {
                    if (getItemParam != null && !getItemParam.equals(mod_getItemParam)) {
                        log_table_name.add("po_lineitems");
                        log_field_name.add("item_id");

                        log_linked_field_name.add("po_number");
                        log_linked_field_value.add(String.valueOf(po_number));
                        log_reference_table.add("billing_lineitems_master");
                        log_reference_field.add("billing_item_id");
                        log_reference_value.add("");
                        log_old_value.add(mod_getItemParam);
                        log_new_value.add(getItemParam);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", item_id=" + getItemParam + " ";
                    } else {
                        updateField += " item_id=" + getItemParam + " ";
                    }
                }


                if (getUnitPriceParam.equals("") || getUnitPriceParam.equals("All")) {
                    getUnitPriceParam = null;
                } else {
                    getUnitPriceParam = " '" + getUnitPriceParam + "' ";
                }
                if (list_item.get(loop).toString().equals("modify")) {
                    if (getUnitPriceParam != null && !getUnitPriceParam.equals(mod_getUnitPriceParam)) {
                        log_table_name.add("po_lineitems");
                        log_field_name.add("rate");

                        log_linked_field_name.add("po_number");
                        log_linked_field_value.add(String.valueOf(po_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
                        log_old_value.add(mod_getUnitPriceParam);
                        log_new_value.add(getUnitPriceParam);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", rate=" + getUnitPriceParam + " ";
                    } else {
                        updateField += " rate=" + getUnitPriceParam + " ";
                    }
                }


                 if (getUnit.equals("") || getUnit.equals("All")) {
                    getUnit = null;
                } else {
                    getUnit = " '" + getUnit + "' ";
                }
                if (list_item.get(loop).toString().equals("modify")) {
                    if (getUnit != null && !getUnit.equals(mod_getUnit)) {
                        log_table_name.add("po_lineitems");
                        log_field_name.add("unit");

                        log_linked_field_name.add("po_number");
                        log_linked_field_value.add(String.valueOf(po_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
                        log_old_value.add(mod_getUnit);
                        log_new_value.add(getUnit);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", unit_id=" + getUnit + " ";
                    } else {
                        updateField += " unit_id=" + getUnit + " ";
                    }
                }




                if (getQuantityParam.equals("") || getQuantityParam.equals("All")) {
                    getQuantityParam = null;
                } else {
                    getQuantityParam = " '" + getQuantityParam + "' ";
                }
                if (list_item.get(loop).toString().equals("modify")) {
                    if (getQuantityParam != null && !getQuantityParam.equals(mod_getQuantityParam)) {
                        log_table_name.add("po_lineitems");
                        log_field_name.add("quantity");

                        log_linked_field_name.add("po_number");
                        log_linked_field_value.add(String.valueOf(po_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
                        log_old_value.add(mod_getQuantityParam);
                        log_new_value.add(getQuantityParam);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", quantity=" + getQuantityParam + " ";
                    } else {
                        updateField += " quantity=" + getQuantityParam + " ";
                    }
                }


                if (getDescParam.equals("") || getDescParam.equals("All")) {
                    getDescParam = null;
                    //System.out.println("s4:" + getDescParam);
                } else {
                    getDescParam = " '" + getDescParam + "' ";
                }
                //System.out.println("s4 else:" + getDescParam);

                if (list_item.get(loop).toString().equals("modify")) {
                    if (getDescParam != null && !getDescParam.equals(mod_getDescParam)) {
                        log_table_name.add("po_lineitems");
                        log_field_name.add("description");

                        log_linked_field_name.add("po_number");
                        log_linked_field_value.add(String.valueOf(po_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
                        log_old_value.add(mod_getDescParam);
                        log_new_value.add(getDescParam);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", description=" + getDescParam + " ";
                    } else {
                        updateField += " description=" + getDescParam + " ";
                    }
                }


                if (getTotalParam.equals("") || getTotalParam.equals("All")) {
                    getTotalParam = null;
                } else {
                    getTotalParam = " '" + getTotalParam + "' ";
                }
                if (list_item.get(loop).toString().equals("modify")) {
                    if (getTotalParam != null && !getTotalParam.equals(mod_getTotalParam)) {
                        log_table_name.add("po_lineitems");
                        log_field_name.add("total");

                        log_linked_field_name.add("po_number");
                        log_linked_field_value.add(String.valueOf(po_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
                        log_old_value.add(mod_getTotalParam);
                        log_new_value.add(getTotalParam);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", total=" + getTotalParam + " ";
                    } else {
                        updateField += " total=" + getTotalParam + " ";
                    }
                }


                intItemRes = 0;
                //System.out.println("text : " + text);
                if (list_item.get(loop).toString().equals("delete")) {

                    stmt.addBatch("DELETE FROM po_lineitems WHERE po_number=" + po_number + "  and item_id = " + getHidItemParam + " ");
                    //System.out.println("DELETE FROM po_lineitems WHERE po_number=" + po_number + "  and item_id = " + getHidItemParam + "");
                } else if (list_item.get(loop).toString().equals("modify")) {
                    updatePOLineItems_Sql = updatePOLineItems_Sql + updateField + where;
                    //System.out.println("updatePOLineItems_Sql : " + updatePOLineItems_Sql);
                    stmt.addBatch(updatePOLineItems_Sql);
                } else {

                    //System.out.println("insert into po_lineitems(po_number,item_id,rate,quantity,description,total,unit_id) "
                            //+ "values('" + po_number + "'," + getItemParam + "," + getUnitPriceParam + "," + getQuantityParam + "," + getDescParam + "," + getTotalParam + "," + getUnit + ")");
                    stmt.addBatch("insert into po_lineitems(po_number,item_id,rate,quantity,description,total,unit_id) "
                            + "values('" + po_number + "'," + getItemParam + "," + getUnitPriceParam + "," + getQuantityParam + "," + getDescParam + "," + getTotalParam + "," + getUnit + ")");
                    /**intItemRes = stmt.executeUpdate("insert into po_lineitems(po_number,item_id,rate,quantity,description,total) " +
                    "values('"+po_number+"',"+getItemParam+","+getUnitPriceParam+","+getQuantityParam+","+getDescParam+","+getTotalParam+")");
                     */
                }
                //stmt.executeBatch();
            }
            int[] countItems = stmt.executeBatch();
            //System.out.println("countItems : " + countItems.length);
            for (int i = 0; i < countItems.length; i++) {
                //System.out.println(countItems[i]);
                if (countItems[i] == 1) {
                    counter_items++;
                }
            }
            //System.out.println("counter_items : " + counter_items);
            //con.close();
        } catch (BatchUpdateException b) {
            System.err.println("SQLException: " + b.getMessage());
            System.err.println("SQLState: " + b.getSQLState());
            System.err.println("Message: " + b.getMessage());
            System.err.println("Vendor: " + b.getErrorCode());
            try {
                con.rollback();
            } catch (SQLException sqleRollBack) {
                sqleRollBack.printStackTrace();
            }

        } catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException sqleRollBack) {
                sqleRollBack.printStackTrace();
            }
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            try {
                con.rollback();
            } catch (SQLException sqleRollBack) {
                sqleRollBack.printStackTrace();
            }
            npe.printStackTrace();
        }
        return counter_items;
    }

    public int endConnection(Connection con) {
        //System.out.println("endConnection calling");
        try {
            con.commit();
            con.setAutoCommit(true);
            intAddRes = 1;
            intDBAddRes = 0;
            if (intAddRes == 1) {



                DBLog log = new DBLog(log_table_name, log_field_name, log_linked_field_name, log_linked_field_value, log_old_value, log_new_value, log_changed_by, log_reference_table, log_reference_field, log_reference_value);

                //Log the records only if the flag is on
                log.setModuleName("PO");
                log.DBLogOption();
                String logOption = log.getLogOption();

                if ("1".equals(logOption)) {
                    intDBAddRes = log.createLog();
                } else {
                    //System.out.println("Log flag off");
                }

                /* dbLog.setTableNameList(log_table_name);
                dbLog.setFieldNameList(log_field_name);
                dbLog.setLinkFieldNameList(log_linked_field_name);
                dbLog.setLinkFieldValueList(log_linked_field_value);
                dbLog.setOldValueList(log_old_value);
                dbLog.setNewValueList(log_new_value);
                dbLog.setChangedByList(log_changed_by);
                dbLog.setReferenceTableName(log_reference_table);
                dbLog.setReferenceTableField(log_reference_field);
                dbLog.setRefFieldValueList(log_reference_value);

                intDBAddRes = dbLog.createLog();*/
            }

            con.close();
        } catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException sqleRollBack) {
                sqleRollBack.printStackTrace();
            }
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            try {
                con.rollback();
            } catch (SQLException sqleRollBack) {
                sqleRollBack.printStackTrace();
            }
            npe.printStackTrace();
        }
        return intAddRes;
    }

   
}
