/*
 * SaveEstimate.java
 *
 * Created on February 24, 2010, 3:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import java.io.Serializable;
import connection.DBconnection;
import pathfinder.functions.*;
import pathfinder.functions.generic.*;
import pathfinder.functions.mysqlinjection;
import pathfinder.functions.projects.ManageCategoryMilestonesDAO;
import pathfinder.functions.projects.ManageCategoryMilestonesVO;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramyamaims
 */
public class SaveEstimate implements Serializable {

    connection.DBconnection conProp = new connection.DBconnection();
    EstimationInfo estInfo = new EstimationInfo();
    DBLog dbLog = new DBLog();
    private Connection con = null;
    private Statement stmt = null;
    private Statement statement = null;
    private ResultSet rs = null;
    private String sql_select = "";
    private int intAddRes = 0;
    private int intDelRes = 0;
    private int intDelEstRes = 0;
    private int intDBAddRes = 0;
    private int intItemRes = 0;
    private int countItemsVal = 0;
    private int counter_items = 0;
    private int intRes = 0;
    private List log_table_name = new ArrayList();
    private List log_field_name = new ArrayList();
    private List log_linked_field_name = new ArrayList();
    private List log_linked_field_value = new ArrayList();
     private String mod_getUnit="";

   private List list_getUnitParam = new ArrayList();

           private List list_getTypeParam = new ArrayList();
   private List list_getTrimParam = new ArrayList();
   private List list_getCodeParam = new ArrayList();

    private List log_old_value = new ArrayList();
    private List log_new_value = new ArrayList();
    private List log_changed_by = new ArrayList();
    private List log_reference_table = new ArrayList();
    private List log_reference_field = new ArrayList();
    private List log_reference_value = new ArrayList();
    private int est_number = 0;
    private String text = "";
    private String sesEmp = "";
    private String proj_id = "";
    private String proj_est_process_id = "";
    private String proj_est_disp_number = "";
    private String getProjStatusParam = "";
    private String buyer = "";
    private String shipping_address = "";
    private String shipping_city = "";
    private String shipping_country = "";
    private String shipping_state = "";
    private String shipping_zipcode = "";
    private String shipping_phone = "";
    private String shipping_fax = "";
    private String pay = "";
    private String seller = "";
    private String est_currency = "";
    private String terms_and_condition = "";
    private String status = "";
    private String est_value = "";
    private String type = "";
    private String mod_proj_id = "";
    private String mod_getProjStatusParam = "";
    private String mod_buyer = "";
    private String mod_shipping_address = "";
    private String mod_shipping_city = "";
    private String mod_shipping_country = "";
    private String mod_shipping_state = "";
    private String mod_shipping_zipcode = "";
    private String mod_shipping_phone = "";
    private String mod_shipping_fax = "";
    private String mod_pay = "";
    private String mod_seller = "";
    private String mod_est_currency = "";
    private String mod_terms_and_condition = "";
    private String mod_status = "";
    private String mod_est_value = "";
    private String mod_estimateDisplayDate = "";
    private String mod_type = "";
    private String mod_currencyCode = "";
    private List getLineItemList = new ArrayList();
    private List getItemList = new ArrayList();
    private List getCategoryList = new ArrayList();
    private List getItemDescList = new ArrayList();
    private List getHidItemList = new ArrayList();
    private List getUnitPriceList = new ArrayList();
    private List getQuantityList = new ArrayList();
    private List getDescList = new ArrayList();
    private List getTotalList = new ArrayList();
    private List getTypeList = new ArrayList();
    private List getTrimList = new ArrayList();
    private List getCodeList=new ArrayList();
    private String mod_getItemParam = "";
    private String mod_getHidItemParam = "";
    private String mod_getUnitPriceParam = "";
    private String mod_getQuantityParam = "";
    private String mod_getDescParam = "";
      private String mod_getTypeParam = "";
        private String mod_getTrimParam = "";
          private String mod_getCodeParam = "";

    private String mod_getTotalParam = "";
    private List line_text = new ArrayList();
    private String getItemParam = "";
    private String getCategoryParam = "";
    private String getItemDescParam = "";
    private String getHidItemParam = "";
    private String getUnitPriceParam = "";
    private String getQuantityParam = "";
    private String getDescParam = "";
    private String getTotalParam = "";
    private String getLineItem = "";

    private String getTypeParam = "";
    private String getTrimParam = "";
    private String getCodeParam = "";

    private String sql_cntQuery = "";
    private String sql_intQuery = "";
    private String getUnit="";
    private String currencyCode = "";
    private String estimateDisplayDate = "";

    private List estLineItemIdList = new ArrayList();

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getEstimateDisplayDate() {
        return estimateDisplayDate;
    }

    public void setEstimateDisplayDate(String estimateDisplayDate) {
        this.estimateDisplayDate = estimateDisplayDate;
    }

    public int getIntDelEstRes() {
        return intDelEstRes;
    }

    public void setIntDelEstRes(int intDelEstRes) {
        this.intDelEstRes = intDelEstRes;
    }

    public int getIntDelRes() {
        return intDelRes;
    }

    public void setIntDelRes(int intDelRes) {
        this.intDelRes = intDelRes;
    }

    public List getEstLineItemIdList() {
        return estLineItemIdList;
    }

    public void setEstLineItemIdList(List estLineItemIdList) {
        this.estLineItemIdList = estLineItemIdList;
    }
        
    public void setProjId(String proj_id) {
        // System.out.println("proj_id in save estimation : "+proj_id);
        this.proj_id = proj_id;
    }

    public void setProjEstProcessId(String proj_est_process_id) {
        this.proj_est_process_id = proj_est_process_id;
    }

    public void setProjEstDispNumber(String proj_est_disp_number) {
        this.proj_est_disp_number = proj_est_disp_number;
    }

    public void setUnit(List list_getUnitParam)
    {
        this.list_getUnitParam = list_getUnitParam;
    }

      public void str_setUnitParam(String getUnit) {
        this.getUnit = getUnit;
    }

    public void setSesEmp(String sesEmp) {
        this.sesEmp = sesEmp;
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

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void setEstCurrency(String est_currency) {
        this.est_currency = est_currency;
    }

    public void setTermsAndCond(String terms_and_condition) {
        this.terms_and_condition = terms_and_condition;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPayId(String pay) {
        this.pay = pay;
    }

    public void setEstValue(String est_value) {
        this.est_value = est_value;
    }

    public void setEstNumber(int est_number) {
        this.est_number = est_number;
    }

    public void setType(String type) {
        //System.out.println("proj_id in save estimation : "+proj_id);
        this.type = type;
    }

    public void setTextList(List line_text) {
        //System.out.println("line_text in save estimation : "+line_text);
        this.line_text = line_text;
    }

    public void setItemIdList(List getItemList) {
        this.getItemList = getItemList;
    }

    public void setCategoryList(List getCategoryList) {
        this.getCategoryList = getCategoryList;
    }

    public void setItemDescList(List getItemDescList) {
        this.getItemDescList = getItemDescList;
    }

    public void setHidItemIdList(List getHidItemList) {
        this.getHidItemList = getHidItemList;
    }

    public void setUnitPriceList(List getUnitPriceList) {
        this.getUnitPriceList = getUnitPriceList;
    }

    public void setQuantityList(List getQuantityList) {
        this.getQuantityList = getQuantityList;
    }

    public void setTypeList(List getTypeList ) {
        this.getTypeList  = getTypeList ;
    }

    public void setTrimList(List getTrimList ) {
        this.getTrimList  = getTrimList ;
    }

    public void setCodeList(List getCodeList) {
        this.getCodeList = getCodeList;
    }



    public void setDescList(List getDescList) {
        this.getDescList = getDescList;
    }

    public void setTotalList(List getTotalList) {
        this.getTotalList = getTotalList;
    }

    public void setItemId(String getItemParam) {
        this.getItemParam = getItemParam;
    }

    public void setCategoryParam(String getCategoryParam) {
        this.getCategoryParam = getCategoryParam;
    }

    public void setItemDescParam(String getItemDescParam) {
        this.getItemDescParam = getItemDescParam;
    }

    public void setHidItemId(String getHidItemParam) {
        this.getHidItemParam = getHidItemParam;
    }

    public void setUnitPrice(String getUnitPriceParam) {
        this.getUnitPriceParam = getUnitPriceParam;
    }

    public void setQuantity(String getQuantityParam) {
        this.getQuantityParam = getQuantityParam;
    }

     public void setItemType(String getTypeParam ) {
        this.getTypeParam  = getTypeParam ;
    }

      public void setTrim(String getTrimParam ) {
        this.getTrimParam  = getTrimParam ;
    }

       public void setCode(String getCodeParam ) {
        this.getCodeParam  = getCodeParam ;
    }

    public void setDesc(String getDescParam) {
        this.getDescParam = getDescParam;
    }

    public void setTotal(String getTotalParam) {
        this.getTotalParam = getTotalParam;
    }

    public void setQueryType(String text) {
        //System.out.println("setQueryType : "+text);
        this.text = text;
    }

    public int getAddEst() {
        return intRes;
    }

    public int getAddEstLineItem() {
        return intItemRes;
    }

    public int getEstNumber() {
        return est_number;
    }

    public void setLineItems(List getLineItems)
    {
        this.getLineItemList = getLineItems;
    }

    public void setLineItem(String getLineItems)
    {
        this.getLineItem=getLineItems;
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

    public void addEst() {
        //System.out.println("I am in addEst method : **********************************");
        log_table_name.clear();
        log_field_name.clear();
        log_linked_field_name.clear();
        log_old_value.clear();
        log_new_value.clear();
        log_changed_by.clear();
        log_linked_field_value.clear();
        log_reference_field.clear();
        log_reference_value.clear();

        try {
            con = getConProp();
            stmt = con.createStatement();

            mod_proj_id = "";
            mod_getProjStatusParam = "";
            mod_buyer = "";
            mod_shipping_address = "";
            mod_shipping_city = "";
            mod_shipping_country = "";
            mod_shipping_state = "";
            mod_shipping_zipcode = "";
            mod_shipping_phone = "";
            mod_shipping_fax = "";
            mod_pay = "";
            mod_seller = "";
            mod_est_currency = "";
            mod_terms_and_condition = "";
            mod_status = "";
            mod_type = "";
            mod_currencyCode = "";

            String updateEst_Sql = "";
            String updateField = "";

            String getProjStatusIdParam = "";
            String getStatusIdParam = "";
            int updateProj = 0;

            updateEst_Sql = " update estimate set ";
            String where = " where est_number=" + est_number + " ";

            if (text.equals("modify")) {

                estInfo.setEstNumber(String.valueOf(est_number));
                estInfo.getEstInfo();

                mod_proj_id = " '" + estInfo.getProjId() + "' ";
                mod_getProjStatusParam = " '" + estInfo.getProjStatus() + "' ";
                mod_buyer = " '" + estInfo.getBuyerID() + "' ";
                mod_shipping_address = " '" + estInfo.getBuyerAdd() + "' ";
                mod_shipping_city = " '" + estInfo.getBuyerCity() + "' ";
                mod_shipping_country = " '" + estInfo.getBuyerCountry() + "' ";
                mod_shipping_state = " '" + estInfo.getBuyerState() + "' ";
                mod_shipping_zipcode = " '" + estInfo.getBuyerZip() + "' ";
                mod_shipping_phone = " '" + estInfo.getBuyerPhone() + "' ";
                mod_shipping_fax = " '" + estInfo.getBuyerFax() + "' ";
                mod_pay = " '" + estInfo.getEstPayId() + "' ";
                mod_seller = " '" + estInfo.getSellerID() + "' ";
                mod_est_currency = " '" + estInfo.getCurrencyId() + "' ";
                mod_terms_and_condition = " '" + estInfo.getTermsID() + "' ";
                mod_status = " '" + estInfo.getStatusID() + "' ";
                mod_type = " '" + estInfo.getEstTypeId() + "' ";
                mod_est_value = " '" + estInfo.getEstValue() + "' ";
                mod_estimateDisplayDate = " '" + estInfo.getEstimateDisplayDate() + "' ";
            }

            if (text.equals("")) {
                sql_cntQuery = "select max(est_number) from estimate";
                rs = stmt.executeQuery(sql_cntQuery);

                while (rs.next()) {
                    est_number = rs.getInt(1);

                    if (rs.wasNull()) {
                        est_number = 0;
                    }
                }
                est_number++;
            }

            //System.out.println("est_number : "+est_number);

            if (proj_id.equals("") || proj_id.equals("All")) {
                proj_id = null;
            } else {
                proj_id = " '" + proj_id + "' ";
                if (text.equals("modify")) {

                    if (proj_id != null && !proj_id.equals(mod_proj_id)) {
                        log_table_name.add("estimate");
                        log_field_name.add("proj_id");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
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

            if (proj_est_process_id.equals("") || proj_est_process_id.equals("All")) {
                proj_est_process_id = null;
            } else {
                proj_est_process_id = " '" + proj_est_process_id + "' ";
                if (text.equals("modify")) {
                    if (updateField.length() > 0) {
                        updateField += ", est_process_flag=" + proj_est_process_id + " ";
                    } else {
                        updateField += " est_process_flag=" + proj_est_process_id + " ";
                    }
                }
            }

            if (proj_est_disp_number.equals("") || proj_est_disp_number.equals("All")) {
                proj_est_disp_number = null;
            } else {
                proj_est_disp_number = " '" + proj_est_disp_number + "' ";
                if (text.equals("modify")) {
                    if (updateField.length() > 0) {
                        updateField += ", est_number_disp=" + proj_est_disp_number + " ";
                    } else {
                        updateField += " est_number_disp=" + proj_est_disp_number + " ";
                    }
                }
            }

            if (pay.equals("") || pay.equals("All")) {
                pay = null;
            } else {
                pay = " '" + pay + "' ";
                if (text.equals("modify")) {

                    if (pay != null && !pay.equals(mod_pay)) {
                        log_table_name.add("estimate");
                        log_field_name.add("mode_of_payment");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
                        log_reference_table.add("payment_modes_master");
                        log_reference_field.add("mode_of_payment");
                        log_reference_value.add(" ");
                        log_old_value.add(mod_pay);
                        log_new_value.add(pay);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", mode_of_payment=" + pay + " ";
                    } else {
                        updateField += " mode_of_payment=" + pay + " ";
                    }
                }
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

                if (text.equals("modify")) {

                    if (buyer != null && !buyer.equals(mod_buyer)) {
                        log_table_name.add("estimate");
                        log_field_name.add("buyer_id");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
                        log_reference_table.add(" ");
                        log_reference_field.add(" ");
                        log_reference_value.add(" ");
                        log_old_value.add(mod_buyer);
                        log_new_value.add(buyer);
                        log_changed_by.add(sesEmp);
                    }

                    if (updateField.length() > 0) {
                        updateField += ", buyer_id=" + buyer + " ";
                    } else {
                        updateField += " buyer_id=" + buyer + " ";
                    }

                    if (shipping_address != null && !shipping_address.equals(mod_shipping_address)) {
                        log_table_name.add("estimate");
                        log_field_name.add("shipping_address");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
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
                        log_table_name.add("estimate");
                        log_field_name.add("shipping_city");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
                        log_old_value.add(mod_shipping_city);
                        log_reference_table.add(" ");
                        log_reference_field.add(" ");
                        log_reference_value.add(" ");
                        log_new_value.add(shipping_city);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", shipping_city=" + shipping_city + " ";
                    } else {
                        updateField += " shipping_city=" + shipping_city + " ";
                    }

                    if (shipping_country != null && !shipping_country.equals(mod_shipping_country)) {
                        log_table_name.add("estimate");
                        log_field_name.add("shipping_country");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
                        log_reference_table.add(" ");
                        log_reference_field.add(" ");
                        log_reference_value.add(" ");
                        log_old_value.add(mod_shipping_country);
                        log_new_value.add(shipping_country);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", shipping_country=" + shipping_country + " ";
                    } else {
                        updateField += " shipping_country=" + shipping_country + " ";
                    }

                    if (shipping_state != null && !shipping_state.equals(mod_shipping_state)) {
                        log_table_name.add("estimate");
                        log_field_name.add("shipping_state");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
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
                        log_table_name.add("estimate");
                        log_field_name.add("shipping_zipcode");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
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
                        log_table_name.add("estimate");
                        log_field_name.add("shipping_phone");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
                        log_reference_table.add(" ");
                        log_reference_field.add(" ");
                        log_reference_value.add(" ");
                        log_old_value.add(mod_shipping_phone);
                        log_new_value.add(shipping_phone);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", shipping_phone=" + shipping_phone + " ";
                    } else {
                        updateField += " shipping_phone=" + shipping_phone + " ";
                    }

                    if (shipping_fax != null && !shipping_fax.equals(mod_shipping_fax)) {
                        log_table_name.add("estimate");
                        log_field_name.add("shipping_fax");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
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
                }
            }

            if (seller.equals("") || seller.equals("All")) {
                seller = null;
            } else {
                seller = " '" + seller + "' ";
                if (text.equals("modify")) {

                    if (seller != null && !seller.equals(mod_seller)) {
                        log_table_name.add("estimate");
                        log_field_name.add("seller_id");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
                        log_reference_table.add(" ");
                        log_reference_field.add(" ");
                        log_reference_value.add(" ");
                        log_old_value.add(mod_seller);
                        log_new_value.add(seller);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", seller_id=" + seller + " ";
                    } else {
                        updateField += " seller_id=" + seller + " ";
                    }
                }
            }

            if (est_currency.equals("") || est_currency.equals("All")) {
                est_currency = null;
            } else {
                est_currency = " '" + est_currency + "' ";
                if (text.equals("modify")) {

                    if (est_currency != null && !est_currency.equals(mod_est_currency)) {
                        log_table_name.add("estimate");
                        log_field_name.add("est_currency");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
                        log_reference_table.add("currency");
                        log_reference_field.add("currency_id");
                        log_reference_value.add(" ");
                        log_old_value.add(mod_est_currency);
                        log_new_value.add(est_currency);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", est_currency=" + est_currency + " ";
                    } else {
                        updateField += " est_currency=" + est_currency + " ";
                    }
                }
            }

            if (status.equals("") || status.equals("All")) {
                status = null;
            } else {
                status = " '" + status + "' ";
                if (text.equals("modify")) {

                    if (status != null && !status.equals(mod_status)) {
                        log_table_name.add("estimate");
                        log_field_name.add("status");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
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
                        log_table_name.add("estimate");
                        log_field_name.add("terms_and_condition");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
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

            if (est_value.equals("") || est_value.equals("All")) {
                est_value = null;
            } else {
                est_value = " '" + est_value + "' ";
                if (text.equals("modify")) {

                    if (est_value != null && !est_value.equals(mod_est_value)) {
                        //System.out.println("est_value :*"+est_value+"*");
                        //System.out.println("mod_est_value :*"+mod_est_value+"*");
                        log_table_name.add("estimate");
                        log_field_name.add("est_value");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
                        log_old_value.add(mod_est_value);
                        log_new_value.add(est_value);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", est_value=" + est_value + " ";
                    } else {
                        updateField += " est_value=" + est_value + " ";
                    }
                }
            }

            if (type.equals("") || type.equals("All")) {
                type = null;
            } else {
                type = " '" + type + "' ";
                if (text.equals("modify")) {

                    if (type != null && !type.equals(mod_type)) {
                        log_table_name.add("estimate");
                        log_field_name.add("type");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
                        log_old_value.add(mod_type);
                        log_new_value.add(type);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", type=" + type + " ";
                    } else {
                        updateField += " type=" + type + " ";
                    }
                }
            }

            if (estimateDisplayDate.equals("") || estimateDisplayDate.equals("All")) {
                estimateDisplayDate = null;
            } else {
                estimateDisplayDate = " '" + estimateDisplayDate + "' ";
                if (text.equals("modify")) {

                    if (estimateDisplayDate != null && !estimateDisplayDate.equals(mod_estimateDisplayDate)) {
                        //System.out.println("est_value :*"+est_value+"*");
                        //System.out.println("mod_est_value :*"+mod_est_value+"*");
                        log_table_name.add("estimate");
                        log_field_name.add("est_value");
                        log_linked_field_name.add("est_number");
                        log_linked_field_value.add(String.valueOf(est_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
                        log_old_value.add(mod_estimateDisplayDate);
                        log_new_value.add(estimateDisplayDate);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", estimate_display_date = " + estimateDisplayDate + " ";
                    } else {
                        updateField += " estimate_display_date = " + estimateDisplayDate + " ";
                    }
                }
            }

            sql_intQuery = "insert into estimate(est_number,est_date,proj_id,buyer_id,shipping_address,shipping_city,shipping_country,"
                    + "shipping_state,shipping_zipcode,shipping_phone,shipping_fax,seller_id,est_currency,terms_and_condition,"
                    + "status,est_value,type,mode_of_payment,est_process_flag,est_number_disp, estimate_display_date) "
                    + "values('" + est_number + "',current_timestamp()," + proj_id + "," + buyer + "," + shipping_address + "," + shipping_city + "," + shipping_country + ","
                    + "" + shipping_state + "," + shipping_zipcode + "," + shipping_phone + "," + shipping_fax + "," + seller + "," + est_currency + "," + terms_and_condition + ","
                    + "" + status + "," + est_value + "," + type + "," + pay + "," + proj_est_process_id + "," + proj_est_disp_number +"," + estimateDisplayDate + ")";
            intAddRes = 0;
            //System.out.println("text : "+text);
            if (text.equals("modify")) {
                updateEst_Sql = updateEst_Sql + updateField + where;
                //System.out.println("updateEst_Sql : "+updateEst_Sql);
                intAddRes = stmt.executeUpdate(updateEst_Sql);

                if (intAddRes != 0) {

                            //System.out.println("status : "+status);
                    if ( status != null && !status.equals("")) {
                        //System.out.println("select status from status where status_id = "+status+"");
                        rs = stmt.executeQuery("select status from status where status_id = " + status + "");

                        while (rs.next()) {
                            getProjStatusIdParam = rs.getString(1);
                            if (rs.wasNull()) {
                                getProjStatusIdParam = "";
                            }
                        }

                        //System.out.println("getProjStatusIdParam : "+getProjStatusIdParam);

                        if (getProjStatusIdParam.equals("Win") || getProjStatusIdParam.equals("Lose")) {

                            if (getProjStatusIdParam.equals("Win")) {
                                rs = stmt.executeQuery("select status_id from status where status = 'Open' and status_type = 'projects'");

                                while (rs.next()) {
                                    getStatusIdParam = rs.getString(1);
                                    if (rs.wasNull()) {
                                        getStatusIdParam = "";
                                    }
                                }
                            }

                            if (getProjStatusIdParam.equals("Lose")) {
                                rs = stmt.executeQuery("select status_id from status where status = 'Close' and status_type = 'projects'");

                                while (rs.next()) {
                                    getStatusIdParam = rs.getString(1);
                                    if (rs.wasNull()) {
                                        getStatusIdParam = "";
                                    }
                                }
                            }

                            if (!getStatusIdParam.equals("")) {
                                updateProj = stmt.executeUpdate("update projects set project_status = '" + getStatusIdParam + "' where proj_id = " + proj_id + " ");
                            }
                        }
                    }
                }

            } else {

                //System.out.println("Estimation Insert Query : "+sql_intQuery);
                intAddRes = stmt.executeUpdate(sql_intQuery);
            }
            //System.out.println("intAddRes : "+intAddRes);
            //System.out.println("getItemList : "+getItemList);

            if (getItemList.size() > 0 && getCategoryList.size() > 0) {
                countItemsVal = addEstLineItem(con);
                //System.out.println("getItemList : "+getItemList);
            }

            if(!currencyCode.equals("")) {
                String updateProjCurrency = "UPDATE projects SET currency_code='"+ currencyCode +"' WHERE proj_id="+ proj_id;
                statement = con.createStatement();
                intAddRes = statement.executeUpdate(updateProjCurrency);
            }

            if (getItemList.size() > 0) {

                if (getItemList.size() == countItemsVal && countItemsVal != 0) {
                    intRes = endConnection(con);
                }
            } else {
                intRes = endConnection(con);
            }
        } catch (SQLException sqle) {

            int x = sqle.getErrorCode();
            if (x == 1062) {
                if (text.equals("")) {
                    PFKeyHandeller pfKeyHandeller = new PFKeyHandeller();
                    pfKeyHandeller.setIntSql(sql_intQuery);
                    pfKeyHandeller.setCntSql(sql_cntQuery);
                    pfKeyHandeller.getVal();
                    intAddRes = pfKeyHandeller.getResVal();
                    est_number = pfKeyHandeller.getNextId();
                }

            } else {
                sqle.printStackTrace();
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    public int addEstLineItem(Connection con) {

        try {
            pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ManageCategoryMilestonesDAO manageCategoryMilestonesDAO = new ManageCategoryMilestonesDAO();
            ManageCategoryMilestonesVO manageCategoryMilestonesVO = new ManageCategoryMilestonesVO();
            
            mysqlinjection inject = new mysqlinjection();
            String updateEstLineItems_Sql = "";
            String updateField = "";
            int sortNum = 0;

            rs = stmt.executeQuery("SELECT max(el.sort_order) FROM estimate e, estimate_lineitems el WHERE e.est_number=el.est_number AND e.est_number='"+est_number+"'");
            while (rs.next()) {
                sortNum = rs.getInt(1);
            }

            //System.out.println("getItemList in save estimation : "+getItemList);
            for (int loop = 0; loop < getItemList.size(); loop++) {

                updateEstLineItems_Sql = "";
                updateField = "";
                mod_getItemParam = "";
                mod_getHidItemParam = "";
                mod_getUnitPriceParam = "";
                mod_getQuantityParam = "";
                mod_getDescParam = "";
                mod_getTotalParam = "";

                setItemId(getItemList.get(loop).toString());
                setCategoryParam(getCategoryList.get(loop).toString());
                setItemDescParam(getItemDescList.get(loop).toString());
                setHidItemId(getHidItemList.get(loop).toString());
                setUnitPrice(getUnitPriceList.get(loop).toString());
                setQuantity(getQuantityList.get(loop).toString());
                setItemType(getTypeList.get(loop).toString());
                setTrim(getTrimList.get(loop).toString());
                setCode(getCodeList.get(loop).toString());
                setDesc(getDescList.get(loop).toString());
                setTotal(getTotalList.get(loop).toString());
                str_setUnitParam(list_getUnitParam.get(loop).toString());
                setLineItem(getLineItemList.get(loop).toString());

                getItemParam = splChar.decoding(getItemParam);
                getCategoryParam = splChar.decoding(getCategoryParam);
                getDescParam = splChar.decoding(getDescParam);
                getItemDescParam = splChar.decoding(getItemDescParam);

                updateEstLineItems_Sql = " update estimate_lineitems set ";
                String where = " where est_number=" + est_number + "  and est_lineitem_id = '" + getHidItemParam + "' ";

                if (getItemParam.equals("") || getItemParam.equals("All")) {
                    getItemParam = null;
                } else {
                    getItemParam = " '" + getItemParam + "' ";
                }

                if (getHidItemParam.equals("") || getHidItemParam.equals("All")) {
                    getHidItemParam = null;
                } else {
                    getHidItemParam = " '" + getHidItemParam + "' ";
                    if (line_text.get(loop).toString().equals("modify")) {

                        
                        if (updateField.length() > 0) {
                            updateField += ", item_id=" + getItemParam + " ";
                        } else {
                            updateField += " item_id=" + getItemParam + " ";
                        }
                    }
                }

                if (getCategoryParam.equals("") || getCategoryParam.equals("All")) {
                    getCategoryParam = null;
                } else {
                    getCategoryParam = " '" + getCategoryParam + "' ";
                    if (line_text.get(loop).toString().equals("modify")) {


                        if (updateField.length() > 0) {
                            updateField += ", category_id=" + getCategoryParam + " ";
                        } else {
                            updateField += " category_id=" + getCategoryParam + " ";
                        }
                    }
                }

                if (getItemDescParam.equals("") || getItemDescParam.equals("All")) {
                    if(getItemDescParam.equals("")) {
                        if (line_text.get(loop).toString().equals("modify")) {
                            if (updateField.length() > 0) {
                                updateField += ", description=" + null + " ";
                            } else {
                                updateField += " description=" + null + " ";
                            }
                        }
                    }
                    getItemDescParam = null;
                } else {
                    getItemDescParam = " '" + getItemDescParam + "' ";
                    if (line_text.get(loop).toString().equals("modify")) {


                        if (updateField.length() > 0) {
                            updateField += ", description=" + getItemDescParam + " ";
                        } else {
                            updateField += " description=" + getItemDescParam + " ";
                        }
                    }
                }

                if (getUnitPriceParam.equals("") || getUnitPriceParam.equals("All")) {
                    getUnitPriceParam = null;
                } else {
                    getUnitPriceParam = " '" + getUnitPriceParam + "' ";
                    if (line_text.get(loop).toString().equals("modify")) {

                        
                        if (updateField.length() > 0) {
                            updateField += ", rate=" + getUnitPriceParam + " ";
                        } else {
                            updateField += " rate=" + getUnitPriceParam + " ";
                        }
                    }
                }


                 if (getUnit.equals("") || getUnit.equals("All")) {
                    getUnit = null;
                } else {
                    getUnit = " '" + getUnit + "' ";
                }
                if (line_text.get(loop).toString().equals("modify")) {
                    
                    if (updateField.length() > 0) {
                        updateField += ", unit_id=" + getUnit + " ";
                    } else {
                        updateField += " unit_id=" + getUnit + " ";
                    }
                }

                if (getLineItem.equals("") || getLineItem.equals("All")) {
                    getLineItem = null;
                } else {
                    getLineItem = " '" + getLineItem + "' ";
                }
                if (line_text.get(loop).toString().equals("modify")) {

                    if (updateField.length() > 0) {
                        updateField += ", po_number=" + getLineItem + " ";
                    } else {
                        updateField += " po_number=" + getLineItem + " ";
                    }
                }




                
                if (getQuantityParam.equals("") || getQuantityParam.equals("All")) {
                    getQuantityParam = null;
                } else {
                    getQuantityParam = " '" + getQuantityParam + "' ";
                    if (line_text.get(loop).toString().equals("modify")) {

                       
                        if (updateField.length() > 0) {
                            updateField += ", quantity=" + getQuantityParam + " ";
                        } else {
                            updateField += " quantity=" + getQuantityParam + " ";
                        }
                    }
                }


                 if (getTypeParam.equals("")){
                    getTypeParam = null;
                } else {
                    getTypeParam = " '" + getTypeParam + "' ";
                    if(line_text.get(loop).toString().equals("modify")){
                        if(updateField.length() > 0){
                            updateField += ", type_id=" +getTypeParam + " ";
                        }else{
                            updateField += " type_id=" +getTypeParam + " ";
                        }
                    }
                }

                 if (getTrimParam.equals("") || getTrimParam.equals("All")) {
                    getTrimParam = null;
                } else {
                    getTrimParam = " '" + getTrimParam + "' ";

                }

                if (getCodeParam.equals("") || getCodeParam.equals("All")) {
                    getCodeParam = null;
                } else {
                    getCodeParam = " '" + getCodeParam + "' ";

                }




                if (getDescParam.equals("") || getDescParam.equals("All")) {
                    getDescParam = null;
                } else {
                    getDescParam = " '" + getDescParam + "' ";
                    if (line_text.get(loop).toString().equals("modify")) {

                        if (updateField.length() > 0) {
                            updateField += ", description=" + getDescParam + " ";
                        } else {
                            updateField += " description=" + getDescParam + " ";
                        }
                    }
                }

                if (getTotalParam.equals("") || getTotalParam.equals("All")) {
                    getTotalParam = null;
                } else {
                    getTotalParam = " '" + getTotalParam + "' ";
                    if (line_text.get(loop).toString().equals("modify")) {
                        //System.out.println("getTotalParam :*"+getTotalParam+"*");
                        //System.out.println("mod_getTotalParam :*"+mod_getTotalParam+"*");
                        

                        if (updateField.length() > 0) {
                            updateField += ", total=" + getTotalParam + " ";
                        } else {
                            updateField += " total=" + getTotalParam + " ";
                        }
                    }
                }
            
                intItemRes = 0;
                //System.out.println("line_text : " + line_text.get(loop));

                if (line_text.get(loop).toString().equals("delete")) {
                    //System.out.println("delete");

                    stmt.addBatch("DELETE FROM estimate_lineitems WHERE est_number = " + est_number + " and est_lineitem_id = " + getHidItemParam + " ");
                    //System.out.println("DELETE FROM estimate_lineitems WHERE est_number = " + est_number + " and est_lineitem_id = " + getHidItemParam + " ");
                } else if (line_text.get(loop).toString().equals("modify")) {
                    updateEstLineItems_Sql = updateEstLineItems_Sql + updateField + where;
                    //System.out.println("updateEstLineItems_Sql : "+updateEstLineItems_Sql);
                    stmt.addBatch(updateEstLineItems_Sql);
                } else {

                    //System.out.println("insert into estimate_lineitems(est_number,item_id,rate,quantity,description,total) " +
                    //   "values('"+est_number+"',"+getItemParam+","+getUnitPriceParam+","+getQuantityParam+","+getDescParam+","+getTotalParam+")");
                    stmt.addBatch("insert into estimate_lineitems(est_number,category_id,item_id,rate,quantity,description,total,unit_id,type_id,trim_id,sort_order,po_number) "
                            + "values('" + est_number + "'," + getCategoryParam + "," + getItemParam + "," + getUnitPriceParam + "," + getQuantityParam + "," + getItemDescParam + "," + getTotalParam + "," + getUnit + "," + getTypeParam  + "," + getTrimParam  + ","+ ++sortNum +"," + getLineItem  + ")");
                    /**
                    intItemRes = stmt.executeUpdate("insert into estimate_lineitems(est_number,item_id,rate,quantity,description,total) " +
                    "values('"+est_number+"',"+getItemParam+","+getUnitPriceParam+","+getQuantityParam+","+getDescParam+","+getTotalParam+")");
                     */
                }
            }
            manageCategoryMilestonesVO.setNewCategory(getCategoryList);
            manageCategoryMilestonesDAO.addNewCategory(manageCategoryMilestonesVO);

            int[] countItems = stmt.executeBatch();
            //System.out.println("countItems : "+countItems.length);
            for (int i = 0; i < countItems.length; i++) {
                //System.out.println(countItems[i]);
                if (countItems[i] == 1) {
                    counter_items++;
                }
            }
            //System.out.println("counter_items : "+counter_items);

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

    public void DeleteEst() {
        
        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //System.out.println(" LIST : "+estLineItemIdList);
        //Create a response object and set the response for the request.
        //System.out.println("Received Item - : "+proj_id);
        try {

            con = dbcon.getSampleProperty();
            for(int index=0; index < estLineItemIdList.size(); index++) {
                String deleteEst = " DELETE FROM estimate_lineitems WHERE est_lineitem_id="+estLineItemIdList.get(index);

                stmt = con.prepareStatement(deleteEst);
                //System.out.println("Delete Estimation Lineitems Query : " + deleteEst);
                intDelRes = stmt.executeUpdate();
            }
            
            String checkExisting = "SELECT COUNT(*) FROM estimate e, estimate_lineitems el WHERE e.proj_id='"+proj_id+"' AND e.est_number = el.est_number ";
            stmt = con.prepareStatement(checkExisting);
            //System.out.println("Check for Existing Lineitems : " + checkExisting);
            rs = stmt.executeQuery();
            int count = 0;
            while(rs.next()) {
                count = rs.getInt(1);
            }
            if(count==0) {
                String deleteEst = "DELETE FROM estimate WHERE proj_id='"+proj_id+"'";
                stmt = con.prepareStatement(deleteEst);
                //System.out.println("Delete Estimation Query : " + deleteEst);
                intDelEstRes = stmt.executeUpdate();
            } else {
                String deleteEst = "UPDATE estimate SET est_value='"+est_value+"' WHERE proj_id='"+proj_id+"'";
                stmt = con.prepareStatement(deleteEst);
                //System.out.println("Update Estimation Grand Total Query : " + deleteEst);
                intDelRes = stmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
    }

    public int endConnection(Connection con) {

        try {
            con.commit();
            con.setAutoCommit(true);
            intAddRes = 1;
            intDBAddRes = 0;
            if (intAddRes == 1) {
                DBLog log = new DBLog(log_table_name, log_field_name, log_linked_field_name, log_linked_field_value, log_old_value, log_new_value, log_changed_by, log_reference_table, log_reference_field, log_reference_value);

                //Log the records only if the flag is on
                log.setModuleName("Estimation");
                log.DBLogOption();
                String logOption = log.getLogOption();

                if ("1".equals(logOption)) {
                    intDBAddRes = log.createLog();
                } else {
                    //System.out.println("Log flag off");
                }
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
        //System.out.println("intAddRes : " + intAddRes);
        return intAddRes;
    }
}
