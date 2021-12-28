/*
 * SaveInvoice.java
 *
 * Created on March 4, 2010, 12:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import connection.DBconnection;
import pathfinder.functions.*;
import pathfinder.functions.generic.*;
import pathfinder.functions.mysqlinjection;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramyamaims
 */
public class SaveInvoice {

    DBconnection conProp = new DBconnection();
    DBLog dbLog = new DBLog();
    InvoiceInfo invoiceInfo = new InvoiceInfo();
    private Connection con = null;
    private Statement stmt = null;
    private Statement statement = null;
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
    private int invoice_number = 0;
    private int invoiceNobsedonLoc1 = 0;
    private int intDelInvRes = 0;
    private String sesEmp = "";
    private String text = "";
    private String proj_id = "";
    private String invoice_by = "";
    private String invoice_status = "";
    private String invoice_display_date = "";
    private String buyer = "";
    private String shipping_address = "";
    private String shipping_city = "";
    private String shipping_country = "";
    private String shipping_state = "";
    private String shipping_zipcode = "";
    private String shipping_phone = "";
    private String shipping_fax = "";
    private String pay = "";
    private String invoice_currency = "";
    private String terms_and_condition = "";
    private String invoice_value = "";
    private String mod_buyer_type = "";
    private String mod_proj_id = "";
    private String mod_invoice_by = "";
    private String mod_invoice_status = "";
    private String mod_buyer = "";
    private String mod_shipping_address = "";
    private String mod_shipping_city = "";
    private String mod_shipping_country = "";
    private String mod_shipping_state = "";
    private String mod_shipping_zipcode = "";
    private String mod_shipping_phone = "";
    private String mod_shipping_fax = "";
    private String mod_pay = "";
    private String mod_invoice_currency = "";
    private String mod_terms_and_condition = "";
    private String mod_invoice_value = "";
    private String getCategoryParam = "";
    private String getItemParam = "";
    private String getHidItemParam = "";
    private String getUnitPriceParam = "";
    private String getQuantityParam = "";
    private String getLineItemDescParam = "";
    private String getDescParam = "";
    private String getTotalParam = "";
    private String mod_getItemParam = "";
    private String mod_getHidItemParam = "";
    private String mod_getUnitPriceParam = "";
    private String mod_getQuantityParam = "";
    private String mod_getDescParam = "";
    private String mod_getTotalParam = "";
    private String mod_getEstLineItemIdParam = "";
    private String getTaxParam = "";
    private String getHidTaxParam = "";
    private String getTaxValueParam = "";
    private String getTaxPriceParam = "";
    private String mod_getTaxParam = "";
    private String mod_getHidTaxParam = "";
    private String mod_getTaxValueParam = "";
    private String mod_getTaxPriceParam = "";
    private List list_mod_itemList = new ArrayList();
    private List list_mod_taxList = new ArrayList();
    private List log_table_name = new ArrayList();
    private List log_field_name = new ArrayList();
    private List log_linked_field_name = new ArrayList();
    private List log_old_value = new ArrayList();
    private List log_new_value = new ArrayList();
    private List log_changed_by = new ArrayList();
    private List log_linked_field_value = new ArrayList();
    private String mod_getUnit = "";
    private String mod_getLineItemNo = "";
    private List list_getUnitParam = new ArrayList();
    private List log_reference_table = new ArrayList();
    private List log_reference_field = new ArrayList();
    private List log_reference_value = new ArrayList();
    private List getCategoryList = new ArrayList();
    private List getItemList = new ArrayList();
    private List getHidItemList = new ArrayList();
    private List getUnitPriceList = new ArrayList();
    private List getQuantityList = new ArrayList();
    private List getLineItemDescList = new ArrayList();
    private List getInvoiceQuantityList = new ArrayList();
    private List getDescList = new ArrayList();
    private List getTotalList = new ArrayList();
    private List getTaxList = new ArrayList();
    private List getHidTaxList = new ArrayList();
    private List getTaxValueList = new ArrayList();
    private List getTaxPriceList = new ArrayList();
    private List getTypeList = new ArrayList();
    private List getTrimList = new ArrayList();
    private List getCodeList = new ArrayList();
    private List estLineItemIdList = new ArrayList();
    private List lineItemNum = new ArrayList();
    private String getEstLineItemId = "";
    private String getTypeParam = "";
    private String getTrimParam = "";
    private String getCodeParam = "";
    private String getUnit = "";
    private String partialFlag = "";
    private String proj_inv_disp_number = "";
    private String buyer_type;
    private String lineItemNo = "";
    private int part_number = 0;
    private String partNumber = "";
    private String oldPartialFlag = "";
    private String invoiceFlag = "";
    private String billlocatn = "";
    private List<String> alphabets = new ArrayList<String>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
    private String currencyCode = "";

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }


    
    private List invoiceLineItemIdList = new ArrayList();

    public String getGetEstLineItemId() {
        return getEstLineItemId;
    }

    public void setGetEstLineItemId(String getEstLineItemId) {
        this.getEstLineItemId = getEstLineItemId;
    }

    public List getEstLineItemIdList() {
        return estLineItemIdList;
    }

     /*public List getLineItemNum() {
        return lineItemNum;
    }

   public void setLineItemNum(List lineItemNum) {
        this.lineItemNum=lineItemNum;
    }

    public void setLineItemNo(String lineItemNo)
    {
        this.lineItemNo=lineItemNo;
    }*/

    public void setEstLineItemIdList(List estLineItemIdList) {
        this.estLineItemIdList = estLineItemIdList;
    }

    public int getIntDelInvRes() {
        return intDelInvRes;
    }

    public void setIntDelInvRes(int intDelInvRes) {
        this.intDelInvRes = intDelInvRes;
    }

    public List getInvoiceLineItemIdList() {
        return invoiceLineItemIdList;
    }

    public void setInvoiceLineItemIdList(List invoiceLineItemIdList) {
        this.invoiceLineItemIdList = invoiceLineItemIdList;
    }
    
    public String getInvoiceFlag() {
        return invoiceFlag;
    }

    public void setInvoiceFlag(String invoiceFlag) {
        this.invoiceFlag = invoiceFlag;
    }

    public void setPart_no(String partNumber) {
        this.partNumber = partNumber;
    }

    public void setOldPartialFlag(String oldPartialFlag) {
        this.oldPartialFlag = oldPartialFlag;
    }

    public void setPartialFlag(String partialFlag) {
        this.partialFlag = partialFlag;
    }
   public String getPartialFlag() {
        return partialFlag;
    }
    public void setProjInvDispNumber(String proj_inv_disp_number) {
        this.proj_inv_disp_number = proj_inv_disp_number;
    }

    public void setTypeList(List getTypeList) {
        this.getTypeList = getTypeList;
    }

    public void setTrimList(List getTrimList) {
        this.getTrimList = getTrimList;
    }

    public void setCodeList(List getCodeList) {
        this.getCodeList = getCodeList;
    }

    public void setUnit(List list_getUnitParam) {
        this.list_getUnitParam = list_getUnitParam;
    }

    public void str_setUnitParam(String getUnit) {
        this.getUnit = getUnit;
    }

    public void setItemList(List list_mod_itemList) {
        this.list_mod_itemList = list_mod_itemList;
    }

    public void setTaxList(List list_mod_taxList) {
        this.list_mod_taxList = list_mod_taxList;
    }

    public void setProjId(String proj_id) {
        this.proj_id = proj_id;
    }

    public void setInvoiceDispDate(String invoice_display_date) {
        this.invoice_display_date = invoice_display_date;
    }

    public void setInvoiceEmp(String invoice_by) {
        this.invoice_by = invoice_by;
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

    public void setInvoiceCurrency(String invoice_currency) {
        this.invoice_currency = invoice_currency;
    }

    public void setTermsAndCond(String terms_and_condition) {
        this.terms_and_condition = terms_and_condition;
    }

    public void setInvoiceStatus(String invoice_status) {
        this.invoice_status = invoice_status;
    }

    public void setPayId(String pay) {
        this.pay = pay;
    }

    public void setInvoiceValue(String invoice_value) {
        this.invoice_value = invoice_value;
    }

    public void setInvoiceNumber(int invoice_number) {
        this.invoice_number = invoice_number;
    }

    public void setTaxId(String getTaxParam) {
        this.getTaxParam = getTaxParam;
    }

    public void setHidTaxId(String getHidTaxParam) {
        this.getHidTaxParam = getHidTaxParam;
    }

    public void setTaxValue(String getTaxValueParam) {
        this.getTaxValueParam = getTaxValueParam;
    }

    public void setTaxPrice(String getTaxPriceParam) {
        this.getTaxPriceParam = getTaxPriceParam;
    }

    public void setTaxIdList(List getTaxList) {
        this.getTaxList = getTaxList;
    }

    public void setHidTaxIdList(List getHidTaxList) {
        this.getHidTaxList = getHidTaxList;
    }

    public void setTaxValueList(List getTaxValueList) {
        this.getTaxValueList = getTaxValueList;
    }

    public void setTaxPriceList(List getTaxPriceList) {
        this.getTaxPriceList = getTaxPriceList;
    }

    public void setItemId(String getItemParam) {
        this.getItemParam = getItemParam;
    }

    public void setCategoryParam(String getCategoryParam) {
        this.getCategoryParam = getCategoryParam;
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

    public void setLineItemDesc(String getLineItemDescParam) {
        this.getLineItemDescParam = getLineItemDescParam;
    }

    public void setDesc(String getDescParam) {
        this.getDescParam = getDescParam;
    }

    public void setTotal(String getTotalParam) {
        this.getTotalParam = getTotalParam;
    }

    public void setItemType(String getTypeParam) {
        this.getTypeParam = getTypeParam;
    }

    public void setTrim(String getTrimParam) {
        this.getTrimParam = getTrimParam;
    }

    public void setCode(String getCodeParam) {
        this.getCodeParam = getCodeParam;
    }

    public void setCategoryList(List getCategoryList) {
        this.getCategoryList = getCategoryList;
    }

    public void setItemIdList(List getItemList) {
        this.getItemList = getItemList;
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

    public void setLineItemDescList(List getLineItemDescList) {
        this.getLineItemDescList = getLineItemDescList;
    }

    public void setDescList(List getDescList) {
        this.getDescList = getDescList;
    }

    public void setTotalList(List getTotalList) {
        this.getTotalList = getTotalList;
    }

    public void setType(String text) {
        // System.out.println("setQueryType : " + text);
        this.text = text;
    }

    public void setSesEmp(String sesEmp) {
        this.sesEmp = sesEmp;
    }

    public int getAddInvoice() {
        return intRes;
    }

    public int getAddInvoiceLineItem() {
        return intItemRes;
    }

    public int getInvoiceNumber() {
        return invoice_number;
    }

    public int getAddInvoiceTaxes() {
        return intTaxRes;
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
        public void addInvoice() {
        //System.out.println("Add Invoice - Estimation Line Item IDS : "+estLineItemIdList);
        //System.out.println(alphabets);
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

        String invoiceNobsedonLoc = maxInvoiceNumber(billlocatn);
        String tablecolumnName = "";
        
        invoiceNobsedonLoc1 = Integer.parseInt(invoiceNobsedonLoc)+1;
        //System.out.println("partialFlag"+partialFlag);
        
        if(invoiceFlag.equals("2")){
            invoiceNobsedonLoc1=0;

        }
       
        if (billlocatn.equals("1")){
        tablecolumnName = "invoice_disp_number_chennai";
        }
           else if(billlocatn.equals("3"))
            {
        tablecolumnName = "invoice_disp_number_singapore";
        }
        else if(billlocatn.equals("4"))
            {
        tablecolumnName = "invoice_disp_number_tm";
        }
  else if(billlocatn.equals("5"))
            {
        tablecolumnName = "invoice_disp_number_SG2";
        }
        try {
            con = getConProp();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String updateInvoice_Sql = "";
            String updateField = "";

            mod_proj_id = "";
            mod_invoice_by = "";
            mod_invoice_status = "";
            mod_buyer = "";
            mod_shipping_address = "";
            mod_shipping_city = "";
            mod_shipping_country = "";
            mod_shipping_state = "";
            mod_shipping_zipcode = "";
            mod_shipping_phone = "";
            mod_shipping_fax = "";
            mod_pay = "";
            mod_invoice_currency = "";
            mod_terms_and_condition = "";
            mod_invoice_value = "";

            int updateProj = 0;

            updateInvoice_Sql = " update invoices set ";
            String where = " where invoice_number=" + invoice_number + " ";

            if (text.equals("modify")) {
                invoiceInfo.setInvoiceNumber(String.valueOf(invoice_number));
                invoiceInfo.getInvoiceInfo();

                mod_proj_id = " '" + invoiceInfo.getProjId() + "' ";
                mod_invoice_by = " '" + invoiceInfo.getInvoiceBy() + "' ";
                mod_invoice_status = " '" + invoiceInfo.getStatusID() + "' ";
                mod_buyer = " '" + invoiceInfo.getBuyerID() + "' ";
                mod_shipping_address = " '" + invoiceInfo.getBuyerAdd() + "' ";
                mod_shipping_city = " '" + invoiceInfo.getBuyerCity() + "' ";
                mod_shipping_country = " '" + invoiceInfo.getBuyerCountry() + "' ";
                mod_shipping_state = " '" + invoiceInfo.getBuyerState() + "' ";
                mod_shipping_zipcode = " '" + invoiceInfo.getBuyerZip() + "' ";
                mod_shipping_phone = " '" + invoiceInfo.getBuyerPhone() + "' ";
                mod_shipping_fax = " '" + invoiceInfo.getBuyerFax() + "' ";
                mod_pay = " '" + invoiceInfo.getInvoicePayId() + "' ";
                mod_invoice_currency = " '" + invoiceInfo.getCurrencyId() + "' ";
                mod_terms_and_condition = " '" + invoiceInfo.getTermsID() + "' ";
                mod_invoice_value = " '" + invoiceInfo.getInvoiceValue() + "' ";
                mod_buyer_type = " '" + invoiceInfo.getBuyerType() + "' ";
            }

            if (text.equals("")) {
                rs = stmt.executeQuery("select max(invoice_number) from invoices");

                while (rs.next()) {
                    invoice_number = rs.getInt(1);

                    if (rs.wasNull()) {
                        invoice_number = 0;
                    }
                }
                invoice_number++;

                //generate part number if partial flag is set.
                if (!partialFlag.equals("") && !partialFlag.equals("0")) {
                    // System.out.println("vish:"+part_number);
                    rs = stmt.executeQuery("select max(part_no) from invoices where proj_id=  '" + proj_id + "' ");

                    while (rs.next()) {
                        part_number = rs.getInt(1);

                        if (rs.wasNull()) {
                            part_number = 0;
                        }
                    }
                    part_number++;


                }
            }

            if (part_number == 0) {
                //  System.out.println("inside partnumber:"+part_number);
                partNumber = null;
            } else {
                //  System.out.println("inside elsee:"+part_number);
                partNumber = String.valueOf(part_number);
            }

            // in case of modifying fully invoiced to part invoice this should be done
            //if (!partialFlag.equals("") && !partialFlag.equals("0")) {
            if (oldPartialFlag.equals("0") && !partialFlag.equals("") && !partialFlag.equals("0")) {
                // System.out.println("thanu:"+part_number);
                if (part_number == 0) {
                    rs = stmt.executeQuery("select max(part_no) from invoices where proj_id=  '" + proj_id + "' ");

                    while (rs.next()) {
                        part_number = rs.getInt(1);

                        if (rs.wasNull()) {
                            part_number = 0;
                        }
                    }
                    part_number++;

                }

                if (part_number == 0) {
                    // System.out.println("inside partnumber modify:"+part_number);
                    partNumber = null;
                } else {
                    // System.out.println("inside else modify:"+part_number);
                    partNumber = String.valueOf(part_number);
                }
            }

            //Replace the partial flag if Proforma is selected
            if (invoiceFlag.equals("2")) {
                partialFlag = "2";
                //proj_inv_disp_number += alphabets.get(15);
            }

            if (!partialFlag.equals("") && partialFlag.equals("1")) {
                int index = 0;
                //System.out.println("PART NUMBER : "+partNumber);
                int temp = 0;
                if(partNumber != null) {
                    temp = Integer.parseInt(partNumber);
                }
                if(temp >= 1) {
                    index = Integer.parseInt(partNumber)-1;
                } else {
                    index = 0;
                }
               // proj_inv_disp_number = proj_inv_disp_number + alphabets.get(index);
            } else {
                proj_inv_disp_number = proj_inv_disp_number;
            }

            // System.out.println("invoice_numbers : " + invoice_number);
            // System.out.println("partial:"+partialFlag);

            if (proj_inv_disp_number.equals("") || proj_inv_disp_number.equals("All")) {
                proj_inv_disp_number = null;
            } else {
                proj_inv_disp_number = " '" + proj_inv_disp_number + "' ";
                if (text.equals("modify") && !partialFlag.equals("1")) {

                    if (updateField.length() > 0) {
                        updateField += ", invoice_number_disp=" + proj_inv_disp_number + " ";
                    } else {
                        updateField += " invoice_number_disp=" + proj_inv_disp_number + " ";
                    }
                }
            }

            if (invoice_display_date.equals("") || invoice_display_date.equals("All")) {
                invoice_display_date = null;
            } else {
                invoice_display_date = " '" + invoice_display_date + "' ";
                if (text.equals("modify")) {

                    if (updateField.length() > 0) {
                        updateField += ", invoice_display_date=" + invoice_display_date + " ";
                    } else {
                        updateField += " invoice_display_date=" + invoice_display_date + " ";
                    }
                }
            }

            if (proj_id.equals("") || proj_id.equals("All")) {
                proj_id = null;
            } else {
                proj_id = " '" + proj_id + "' ";
                if (text.equals("modify")) {

                    if (updateField.length() > 0) {
                        updateField += ", proj_id=" + proj_id + " ";
                    } else {
                        updateField += " proj_id=" + proj_id + " ";
                    }
                }
            }

            if (partialFlag.equals("")) {
                partialFlag = "0";
            } else {
                partialFlag = "" + partialFlag + "";

                if (text.equals("modify")) {

                    if (updateField.length() > 0) {
                        updateField += ", part_invoice_flag=" + partialFlag + " ";
                    } else {
                        updateField += " part_invoice_flag=" + partialFlag + " ";
                    }
                }
            }
            // System.out.println("PartialFlag inside saveinvoices:"+partialFlag);
            //   System.out.println("partNumber:"+partNumber);
            //   System.out.println("oldPartialFlag:"+oldPartialFlag);



            /*if(oldPartialFlag.equals("1") && !partialFlag.equals("") && !partialFlag.equals("0")){
            //partialFlag = "0";
            System.out.println("inside ifffff");
            partNumber =  "" + partNumber +" " ;
            }
            else if(oldPartialFlag.equals("1") && partialFlag.equals("") && partialFlag.equals("0")){
            System.out.println("inside else if");
            }  */
            if (oldPartialFlag.equals(partialFlag)) {
                //dont update
                //System.out.println("inside ifffff");
            } else {
                //  System.out.println("PartialFlags :"+partialFlag);

                //  System.out.println("oldPartialFlags:"+oldPartialFlag);
                //   System.out.println("inside else");
                partNumber = "" + partNumber + " ";
                //  System.out.println("updateField else:"+updateField);

                if (text.equals("modify")) {
                    //  System.out.println("updateField modfy:"+updateField);

                    if (updateField.length() > 0) {
                        updateField += ", part_no=" + partNumber + " ";
                    } else {
                        updateField += " part_no=" + partNumber + " ";
                    }
                }

            }

//System.out.println("updateField:"+updateField);




            if (invoice_by.equals("") || invoice_by.equals("All")) {
                invoice_by = null;
            } else {
                invoice_by = " '" + invoice_by + "' ";
                if (text.equals("modify")) {
                    if (invoice_by != null && !invoice_by.equals(mod_invoice_by)) {
                        log_table_name.add("invoices");
                        log_field_name.add("invoice_by");
                        log_linked_field_name.add("invoice_number");
                        log_linked_field_value.add(String.valueOf(invoice_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
                        log_old_value.add(mod_invoice_by);
                        log_new_value.add(invoice_by);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", invoice_by=" + invoice_by + " ";
                    } else {
                        updateField += " invoice_by=" + invoice_by + " ";
                    }
                }
            }

            if (invoice_value.equals("") || invoice_value.equals("All")) {
                invoice_value = null;
            } else {
                invoice_value = " '" + invoice_value + "' ";
                if (text.equals("modify")) {
                    if (invoice_value != null && !invoice_value.equals(mod_invoice_value)) {
                        log_table_name.add("invoices");
                        log_field_name.add("invoice_value");
                        log_linked_field_name.add("invoice_number");
                        log_linked_field_value.add(String.valueOf(invoice_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
                        log_old_value.add(mod_invoice_value);
                        log_new_value.add(invoice_value);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", invoice_value=" + invoice_value + " ";
                    } else {
                        updateField += " invoice_value=" + invoice_value + " ";
                    }
                }
            }

            if (pay.equals("") || pay.equals("All")) {
                pay = null;
            } else {
                pay = " '" + pay + "' ";
                if (text.equals("modify")) {
                    if (pay != null && !pay.equals(mod_pay)) {
                        log_table_name.add("invoices");
                        log_field_name.add("mode_of_payment");
                        log_linked_field_name.add("invoice_number");
                        log_linked_field_value.add(String.valueOf(invoice_number));
                        log_reference_table.add("payment_modes_master");
                        log_reference_field.add("mode_of_payment");
                        log_reference_value.add(" ");
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

                if (text.equals("modify")) {

                    if (buyer != null && !buyer.equals(mod_buyer)) {
                        log_table_name.add("invoices");
                        log_field_name.add("buyer_id");
                        log_linked_field_name.add("invoice_number");
                        log_linked_field_value.add(String.valueOf(invoice_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
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
                        log_table_name.add("invoices");
                        log_field_name.add("shipping_address");
                        log_linked_field_name.add("invoice_number");
                        log_linked_field_value.add(String.valueOf(invoice_number));
                        log_reference_table.add("contacs");
                        log_reference_field.add("contact_id");
                        log_reference_value.add("");
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
                        log_table_name.add("invoices");
                        log_field_name.add("shipping_city");
                        log_linked_field_name.add("invoice_number");
                        log_linked_field_value.add(String.valueOf(invoice_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
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
                        log_table_name.add("invoices");
                        log_field_name.add("shipping_country");
                        log_linked_field_name.add("invoice_number");
                        log_linked_field_value.add(String.valueOf(invoice_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
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
                        log_table_name.add("invoices");
                        log_field_name.add("shipping_state");
                        log_linked_field_name.add("invoice_number");
                        log_linked_field_value.add(String.valueOf(invoice_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
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
                        log_table_name.add("invoices");
                        log_field_name.add("shipping_zipcode");
                        log_linked_field_name.add("invoice_number");
                        log_linked_field_value.add(String.valueOf(invoice_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
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
                        log_table_name.add("invoices");
                        log_field_name.add("shipping_phone");
                        log_linked_field_name.add("invoice_number");
                        log_linked_field_value.add(String.valueOf(invoice_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
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
                        log_table_name.add("invoices");
                        log_field_name.add("shipping_fax");
                        log_linked_field_name.add("invoice_number");
                        log_linked_field_value.add(String.valueOf(invoice_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
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

            if (updateField.length() > 0) {
                updateField += ", buyer_type=" + buyer_type + " ";
            } else {
                updateField += " buyer_type=" + buyer_type + " ";
            }

            if (invoice_currency.equals("") || invoice_currency.equals("All")) {
                invoice_currency = null;
            } else {
                invoice_currency = " '" + invoice_currency + "' ";
                if (text.equals("modify")) {
                    if (invoice_currency != null && !invoice_currency.equals(mod_invoice_currency)) {
                        log_table_name.add("invoices");
                        log_field_name.add("invoice_currency");
                        log_linked_field_name.add("invoice_number");
                        log_linked_field_value.add(String.valueOf(invoice_number));
                        log_reference_table.add("currency");
                        log_reference_field.add("currency_id");
                        log_reference_value.add(" ");
                        log_old_value.add(mod_invoice_currency);
                        log_new_value.add(invoice_currency);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", invoice_currency=" + invoice_currency + " ";
                    } else {
                        updateField += " invoice_currency=" + invoice_currency + " ";
                    }
                }
            }

            if (invoice_status.equals("") || invoice_status.equals("All")) {
                invoice_status = null;
            } else {
                invoice_status = " '" + invoice_status + "' ";
                if (text.equals("modify")) {
                    if (invoice_status != null && !invoice_status.equals(mod_invoice_status)) {
                        log_table_name.add("invoices");
                        log_field_name.add("status");
                        log_linked_field_name.add("invoice_number");
                        log_linked_field_value.add(String.valueOf(invoice_number));
                        log_reference_table.add("status");
                        log_reference_field.add("status_id");
                        log_reference_value.add(" ");
                        log_old_value.add(mod_invoice_status);
                        log_new_value.add(invoice_status);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", status=" + invoice_status + " ";
                    } else {
                        updateField += " status=" + invoice_status + " ";
                    }
                }
            }

            if (terms_and_condition.equals("")||terms_and_condition.equals("All")) {
                terms_and_condition = null;
            } else {
                terms_and_condition = " '" + terms_and_condition + "' ";
                if (text.equals("modify")) {
                    if (terms_and_condition != null && !terms_and_condition.equals(mod_terms_and_condition)) {
                        log_table_name.add("invoices");
                        log_field_name.add("terms_and_condition");
                        log_linked_field_name.add("invoice_number");
                        log_linked_field_value.add(String.valueOf(invoice_number));
                        log_reference_table.add("t_and_c_master");
                        log_reference_field.add("t_and_c_id");
                        log_reference_value.add("");
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

            intAddRes = 0;
            if (text.equals("modify")) {

                updateInvoice_Sql = updateInvoice_Sql + updateField + where;
                //System.out.println("updateInvoice_Sql : " + updateInvoice_Sql);
                intAddRes = stmt.executeUpdate(updateInvoice_Sql);
            } else {

                /* System.out.println("insert into invoices(invoice_number,invoice_date,proj_id,invoice_by,buyer_id,shipping_address,shipping_city,shipping_country,"
                + "shipping_state,shipping_zipcode,shipping_phone,shipping_fax,invoice_currency,terms_and_condition,"
                + "status,invoice_value,mode_of_payment,buyer_type,part_invoice_flag,part_no) "
                + "values('" + invoice_number + "',current_timestamp()," + proj_id + "," + invoice_by + "," + buyer + "," + shipping_address + "," + shipping_city + "," + shipping_country + ","
                + "" + shipping_state + "," + shipping_zipcode + "," + shipping_phone + "," + shipping_fax + "," + invoice_currency + "," + terms_and_condition + ","
                + "" + invoice_status + "," + invoice_value + "," + pay + "," + buyer_type + "," + partialFlag + "," + partNumber + ")");*/
                intAddRes = stmt.executeUpdate("insert into invoices(invoice_number,invoice_date,proj_id,invoice_by,buyer_id,shipping_address,shipping_city,shipping_country,"
                        + "shipping_state,shipping_zipcode,shipping_phone,shipping_fax,invoice_currency,terms_and_condition,"
                        + "status,invoice_value,mode_of_payment,buyer_type,part_invoice_flag,part_no,invoice_number_disp,invoice_display_date,"+tablecolumnName+") "
                        + "values('" + invoice_number + "',current_timestamp()," + proj_id + "," + invoice_by + "," + buyer + "," + shipping_address + "," + shipping_city + "," + shipping_country + ","
                        + "" + shipping_state + "," + shipping_zipcode + "," + shipping_phone + "," + shipping_fax + "," + invoice_currency + "," + terms_and_condition + ","
                        + "" + invoice_status + "," + invoice_value + "," + pay + "," + buyer_type + "," + partialFlag + "," + partNumber + "," + proj_inv_disp_number + ",current_timestamp(),"+invoiceNobsedonLoc1+")");
            }

            //   System.out.println("getTaxList : " + getTaxList);
            // System.out.println("getItemList : " + getItemList);

            if (getTaxList.size() > 0 || getItemList.size() > 0) {

                if (getTaxList.size() > 0) {
                    countTaxesVal = addInvoiceTaxes(con);
                    // System.out.println("getTaxList : " + getTaxList);
                }

                if (getItemList.size() > 0) {
                    countItemsVal = addInvoiceLineItem(con);
                    //  System.out.println("getItemList : " + getItemList);
                }
            }
            //  System.out.println("getTaxList : " + getTaxList.size());
            // System.out.println("countTaxesVal : " + countTaxesVal);
            //System.out.println("getItemList : " + getItemList.size());
            // System.out.println("countItemsVal : " + countItemsVal);

            if(!currencyCode.equals("")) {
                String updateProjCurrency = "UPDATE projects SET currency_code='"+ currencyCode +"' WHERE proj_id="+ proj_id;
                statement = con.createStatement();
                intAddRes = statement.executeUpdate(updateProjCurrency);
            }

            if (partialFlag.equals("0")) {
                String updateProjBilledDate = "";
                String readyToBillStatusChk = "";
                ResultSet rsToChkReadyToBillStatus = statement.executeQuery("select ifnull(ready_to_bill_date,0) from projects WHERE proj_id=" + proj_id + " ");
                while (rsToChkReadyToBillStatus.next()) {
                    readyToBillStatusChk = rsToChkReadyToBillStatus.getString(1);
                }
                if (readyToBillStatusChk.equals("0")) {
                    updateProjBilledDate = "UPDATE projects SET project_status= '23', ready_to_bill_date=current_timestamp(), billed_date=current_timestamp() WHERE proj_id=" + proj_id;
                } else {
                    updateProjBilledDate = "UPDATE projects SET project_status= '23', billed_date=current_timestamp() WHERE proj_id=" + proj_id;
                }
                statement = con.createStatement();
                intAddRes = statement.executeUpdate(updateProjBilledDate);
            }

            if (getTaxList.size() > 0 && getItemList.size() > 0) {

                if ((getTaxList.size() == countTaxesVal && countTaxesVal != 0) && (getItemList.size() == countItemsVal && countItemsVal != 0)) {
                    intRes = endConnection(con);
                }
            } else if (getTaxList.size() > 0 && getItemList.size() == 0) {
                if (getTaxList.size() == countTaxesVal && countTaxesVal != 0) {
                    intRes = endConnection(con);
                }
            } else if (getTaxList.size() == 0 && getItemList.size() > 0) {
                if (getItemList.size() == countItemsVal && countItemsVal != 0) {
                    intRes = endConnection(con);
                }
            } else if (getTaxList.size() == 0 && getItemList.size() == 0) {
                intRes = endConnection(con);
            }
            //System.out.println("intRes in save invoice : " + intRes);
        } catch (SQLException sqle) {
            System.out.println("Invoice Exception:1 "+sqle.toString());
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            System.out.println("Invoice Exception:"+npe.toString());
            npe.printStackTrace();
        }
    }

    public int addInvoiceTaxes(Connection con) {

        try {

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String updateInvoiceTaxesSql = "";
            String updateField = "";

            // System.out.println("getTaxList in SaveInvoice : " + getTaxList);
            //System.out.println("getTaxValueList in SaveInvoice : " + getTaxValueList);
            // System.out.println("getTaxPriceList in SaveInvoice : " + getTaxPriceList);

            for (int loop = 0; loop < getTaxList.size(); loop++) {

                updateInvoiceTaxesSql = "";
                updateField = "";

                mod_getTaxParam = "";
                mod_getHidTaxParam = "";
                mod_getTaxValueParam = "";
                mod_getTaxPriceParam = "";

                setTaxId(getTaxList.get(loop).toString());
                setHidTaxId(getHidTaxList.get(loop).toString());
                setTaxValue(getTaxValueList.get(loop).toString());
                setTaxPrice(getTaxPriceList.get(loop).toString());

                if (list_mod_taxList.get(loop).toString().equals("modify")) {
                    sql_select = "select tax_id,value,total from invoice_taxes "
                            + "where invoice_number = " + invoice_number + " and tax_id = '" + getHidTaxParam + "' ";
                    //  System.out.println("sql_select for line item  : " + sql_select);
                    rs = stmt.executeQuery(sql_select);
                    while (rs.next()) {
                        mod_getTaxParam = " '" + rs.getString("tax_id") + "' ";
                        mod_getTaxValueParam = " '" + rs.getString("value") + "' ";
                        mod_getTaxPriceParam = " '" + rs.getString("total") + "' ";
                    }
                }

                updateInvoiceTaxesSql = " update invoice_taxes set ";
                String where = " where invoice_number=" + invoice_number + " and tax_id = '" + getTaxParam + "' ";

                if (getTaxParam.equals("") || getTaxParam.equals("All")) {
                    getTaxParam = null;
                } else {
                    getTaxParam = " '" + getTaxParam + "' ";
                }
                // System.out.println("getTaxParam : " + getTaxParam);

                // System.out.println("getHidTaxParam : " + getHidTaxParam);
                if (getHidTaxParam.equals("") || getHidTaxParam.equals("All")) {
                    getHidTaxParam = null;
                } else {
                    getHidTaxParam = getHidTaxParam;

                    if (list_mod_taxList.get(loop).toString().equals("modify")) {

                        if (getTaxParam != null && !getTaxParam.equals(mod_getTaxParam)) {
                            log_table_name.add("invoice_taxes");
                            log_field_name.add("tax_id");
                            log_linked_field_name.add("invoice_number");
                            log_linked_field_value.add(String.valueOf(invoice_number));
                            log_reference_table.add("tax_and_addons");
                            log_reference_field.add("tax_id");
                            log_reference_value.add("");
                            log_old_value.add(mod_getTaxParam);
                            log_new_value.add(getTaxParam);
                            log_changed_by.add(sesEmp);
                        }
                        if (updateField.length() > 0) {
                            updateField += ", tax_id=" + getHidTaxParam + " ";
                        } else {
                            updateField += " tax_id=" + getHidTaxParam + " ";
                        }
                    }
                }

                //  System.out.println("getHidTaxParam : " + getHidTaxParam);
                if (getTaxValueParam.equals("") || getTaxValueParam.equals("All")) {
                    getTaxValueParam = null;
                } else {
                    getTaxValueParam = " '" + getTaxValueParam + "' ";

                    if (list_mod_taxList.get(loop).toString().equals("modify")) {

                        if (getTaxValueParam != null && !getTaxValueParam.equals(mod_getTaxValueParam)) {
                            log_table_name.add("invoice_taxes");
                            log_field_name.add("value");
                            log_linked_field_name.add("invoice_number");
                            log_linked_field_value.add(String.valueOf(invoice_number));
                            log_reference_table.add("");
                            log_reference_field.add("");
                            log_reference_value.add("");
                            log_old_value.add(mod_getTaxValueParam);
                            log_new_value.add(getTaxValueParam);
                            log_changed_by.add(sesEmp);
                        }
                        if (updateField.length() > 0) {
                            updateField += ", value=" + getTaxValueParam + " ";
                        } else {
                            updateField += " value=" + getTaxValueParam + " ";
                        }
                    }
                }
                // System.out.println("getTaxValueParam : " + getTaxValueParam);

                if (getTaxPriceParam.equals("") || getTaxPriceParam.equals("All")) {
                    getTaxPriceParam = null;
                } else {
                    getTaxPriceParam = " '" + getTaxPriceParam + "' ";

                    if (list_mod_taxList.get(loop).toString().equals("modify")) {

                        if (getTaxPriceParam != null && !getTaxPriceParam.equals(mod_getTaxPriceParam)) {
                            log_table_name.add("invoice_taxes");
                            log_field_name.add("total");
                            log_linked_field_name.add("invoice_number");
                            log_linked_field_value.add(String.valueOf(invoice_number));
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
                }
                // System.out.println("getTaxPricePara : " + getTaxPriceParam);
                // System.out.println("invoice_numberr : " + invoice_number);
                // System.out.println("partiall:"+partialFlag);
                // System.out.println("text : " + text);

                intTaxRes = 0;
                if (list_mod_taxList.get(loop).toString().equals("delete")) {

                    stmt.addBatch("DELETE FROM invoice_taxes WHERE invoice_number=" + invoice_number + " and tax_id = " + getHidTaxParam + " ");
                    // System.out.println("DELETE FROM invoice_taxes WHERE invoice_number=" + invoice_number + " and tax_id = " + getHidTaxParam + "");
                } else if (list_mod_taxList.get(loop).toString().equals("modify")) {
                    updateInvoiceTaxesSql = updateInvoiceTaxesSql + updateField + where;
                    // System.out.println("updateInvoiceTaxesSql : " + updateInvoiceTaxesSql);
                    // intTaxRes = stmt.executeUpdate(updateInvoiceTaxesSql);
                    stmt.addBatch(updateInvoiceTaxesSql);
                } else {

                    /* System.out.println("insert into invoice_taxes(invoice_number,tax_id,value,total) "
                    + "values('" + invoice_number + "'," + getTaxParam + "," + getTaxValueParam + "," + getTaxPriceParam + ")");
                    stmt.addBatch("insert into invoice_taxes(invoice_number,tax_id,value,total) "
                    + "values('" + invoice_number + "'," + getTaxParam + "," + getTaxValueParam + "," + getTaxPriceParam + ")");*/
                    /**intTaxRes = stmt.executeUpdate("insert into invoice_taxes(invoice_number,tax_id,value,total) " +
                    "values('"+invoice_number+"',"+getTaxParam+","+getTaxValueParam+","+getTaxPriceParam+")");
                     */
                }
            }
            int[] countTaxes = stmt.executeBatch();
            //  System.out.println("countTaxes : " + countTaxes.length);
            for (int i = 0; i < countTaxes.length; i++) {
                //  System.out.println(countTaxes[i]);
                if (countTaxes[i] == 1) {
                    counter_taxes++;
                }
            }
            // System.out.println("counter_taxes : " + counter_taxes);
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

    public int addInvoiceLineItem(Connection con) {
        //System.out.println("Add Invoice Line Item - Estimation Line Item IDS : "+estLineItemIdList);
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
            String updateInvoiceLineItems_Sql = "";
            String updateField = "";
            for (int loop = 0; loop < getItemList.size(); loop++) {

                updateInvoiceLineItems_Sql = "";
                updateField = "";
                mod_getItemParam = "";
                mod_getHidItemParam = "";
                mod_getUnitPriceParam = "";
                mod_getQuantityParam = "";
                mod_getDescParam = "";
                mod_getTotalParam = "";

                setCategoryParam(getCategoryList.get(loop).toString());
                setItemId(getItemList.get(loop).toString());
                setHidItemId(getHidItemList.get(loop).toString());
                setUnitPrice(getUnitPriceList.get(loop).toString());
                setQuantity(getQuantityList.get(loop).toString());
                setLineItemDesc(getLineItemDescList.get(loop).toString());
                setDesc(getDescList.get(loop).toString());
                setTotal(getTotalList.get(loop).toString());
                setQuantity(getQuantityList.get(loop).toString());
                str_setUnitParam(list_getUnitParam.get(loop).toString());
                setItemType(getTypeList.get(loop).toString());
                setTrim(getTrimList.get(loop).toString());
                setCode(getCodeList.get(loop).toString());
                setGetEstLineItemId(estLineItemIdList.get(loop).toString());
                //setLineItemNo(lineItemNum.get(loop).toString());

                getCategoryParam = splChar.decoding(getCategoryParam);
                getItemParam = splChar.decoding(getItemParam);
                getLineItemDescParam = splChar.decoding(getLineItemDescParam);

                if (list_mod_itemList.get(loop).toString().equals("modify")) {
                    sql_select = "select item_id,quantity,rate,description,total,unit_id from invoice_lineitems where invoice_number = " + invoice_number + " and item_id = '" + getHidItemParam + "' ";
                    //  System.out.println("sql_select for line item  : " + sql_select);
                    ///rs = stmt.executeQuery("select item_id,quantity,rate,description,total,unit_id from invoice_lineitems where invoice_number = " + invoice_number + " and item_id = '" + getHidItemParam + "' ");
                    rs = stmt.executeQuery("select item_id,quantity,rate,description,total,unit_id,est_lineitem_id from invoice_lineitems where invoice_number = " + invoice_number + " and item_id = '" + getHidItemParam + "' ");
                    while (rs.next()) {
                        mod_getItemParam = " '" + rs.getString("item_id") + "' ";
                        mod_getUnitPriceParam = " '" + rs.getString("rate") + "' ";
                        mod_getQuantityParam = " '" + rs.getString("quantity") + "' ";
                        mod_getUnit = " '" + rs.getString("unit_id") + "' ";
                        //mod_getLineItemNo = " '" + rs.getString("PO_number") + "' ";
                        mod_getDescParam = " '" + rs.getString("description") + "' ";
                        mod_getTotalParam = " '" + rs.getString("total") + "' ";
                        if(rs.getString("est_lineitem_id") !=  null) {
                            mod_getEstLineItemIdParam = " '"+rs.getString("est_lineitem_id")+"' ";
                        } else {
                            mod_getEstLineItemIdParam = "";
                        }
                    }
                }

                updateInvoiceLineItems_Sql = " update invoice_lineitems set ";
                String where = " where invoice_number=" + invoice_number + "  and invoice_lineitem_id = '" + getHidItemParam + "' ";

                if (getItemParam.equals("") || getItemParam.equals("All")) {
                    getItemParam = null;
                } else {
                    getItemParam = " '" + getItemParam + "' ";
                }

                if (getHidItemParam.equals("") || getHidItemParam.equals("All")) {
                    getHidItemParam = null;
                } else {
                    getHidItemParam = " '" + getHidItemParam + "' ";
                    if (list_mod_itemList.get(loop).toString().equals("modify")) {

                        if (getItemParam != null && !getItemParam.equals(mod_getItemParam)) {
                            log_table_name.add("invoice_lineitems");
                            log_field_name.add("item_id");
                            log_linked_field_name.add("invoice_number");
                            log_linked_field_value.add(String.valueOf(invoice_number));
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
                }

                if (getCategoryParam.equals("") || getCategoryParam.equals("All")) {
                    getCategoryParam = null;
                } else {
                    getCategoryParam = " '" + getCategoryParam + "' ";
                    if (list_mod_itemList.get(loop).toString().equals("modify")) {
                        if (updateField.length() > 0) {
                            updateField += ", category_id=" + getCategoryParam + " ";
                        } else {
                            updateField += " category_id=" + getCategoryParam + " ";
                        }
                    }
                }

                if (getUnit.equals("") || getUnit.equals("All")) {
                    getUnit = null;
                } else {
                    getUnit = " '" + getUnit + "' ";
                }
                if (list_mod_itemList.get(loop).toString().equals("modify")) {
                    if (getUnit != null && !getUnit.equals(mod_getUnit)) {
                        log_table_name.add("invoice_lineitems");
                        log_field_name.add("unit");

                        log_linked_field_name.add("invoice_number");
                        log_linked_field_value.add(String.valueOf(invoice_number));
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

                /*if (lineItemNo.equals("") || lineItemNo.equals("All")) {
                    lineItemNo = null;
                } else {
                    lineItemNo = " '" + lineItemNo + "' ";
                }
                if (list_mod_itemList.get(loop).toString().equals("modify")) {
                    if (lineItemNo != null && !lineItemNo.equals(mod_getLineItemNo)) {
                        log_table_name.add("invoice_lineitems");
                        log_field_name.add("PO_number");

                        log_linked_field_name.add("invoice_number");
                        log_linked_field_value.add(String.valueOf(invoice_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
                        log_old_value.add(mod_getLineItemNo);
                        log_new_value.add(lineItemNo);
                        log_changed_by.add(sesEmp);
                    }
                    if (updateField.length() > 0) {
                        updateField += ", PO_number=" + lineItemNo + " ";
                    } else {
                        updateField += " PO_number=" + lineItemNo + " ";
                    }
                }*/



                if (getUnitPriceParam.equals("") || getUnitPriceParam.equals("All")) {
                    getUnitPriceParam = null;
                } else {
                    getUnitPriceParam = " '" + getUnitPriceParam + "' ";

                    if (list_mod_itemList.get(loop).toString().equals("modify")) {

                        if (getUnitPriceParam != null && !getUnitPriceParam.equals(mod_getUnitPriceParam)) {
                            log_table_name.add("invoice_lineitems");
                            log_field_name.add("rate");
                            log_linked_field_name.add("invoice_number");
                            log_linked_field_value.add(String.valueOf(invoice_number));
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
                }

                if (getQuantityParam.equals("") || getQuantityParam.equals("All")) {
                    getQuantityParam = null;
                } else {
                    getQuantityParam = " '" + getQuantityParam + "' ";

                    if (list_mod_itemList.get(loop).toString().equals("modify")) {

                        if (getQuantityParam != null && !getQuantityParam.equals(mod_getQuantityParam)) {
                            log_table_name.add("invoice_lineitems");
                            log_field_name.add("quantity");
                            log_linked_field_name.add("invoice_number");
                            log_linked_field_value.add(String.valueOf(invoice_number));
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
                }

                if (getTypeParam.equals("")){
                    getTypeParam = null;
                } else {
                    getTypeParam = " '" + getTypeParam + "' ";
                    if(list_mod_itemList.get(loop).toString().equals("modify")){
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




                if (getLineItemDescParam.equals("") || getLineItemDescParam.equals("All")) {
                    if (list_mod_itemList.get(loop).toString().equals("modify")) {
                        if (updateField.length() > 0) {
                            updateField += ", description=" + null + " ";
                        } else {
                            updateField += " description=" + null + " ";
                        }
                    }
                    getLineItemDescParam = null;
                } else {
                    getLineItemDescParam = " '" + getLineItemDescParam + "' ";

                    if (list_mod_itemList.get(loop).toString().equals("modify")) {

                        /*if (getLineItemDescParam != null && !getLineItemDescParam.equals(mod_getDescParam)) {
                            log_table_name.add("invoice_lineitems");
                            log_field_name.add("description");
                            log_linked_field_name.add("invoice_number");
                            log_linked_field_value.add(String.valueOf(invoice_number));
                            log_reference_table.add("");
                            log_reference_field.add("");
                            log_reference_value.add("");
                            log_old_value.add(mod_getDescParam);
                            log_new_value.add(getLineItemDescParam);
                            log_changed_by.add(sesEmp);
                        }*/
                        if (updateField.length() > 0) {
                            updateField += ", description=" + getLineItemDescParam + " ";
                        } else {
                            updateField += " description=" + getLineItemDescParam + " ";
                        }
                    }
                }

                if (getTotalParam.equals("") || getTotalParam.equals("All")) {
                    getTotalParam = null;
                } else {
                    getTotalParam = " '" + getTotalParam + "' ";

                    if (list_mod_itemList.get(loop).toString().equals("modify")) {

                        if (getTotalParam != null && !getTotalParam.equals(mod_getTotalParam)) {
                            log_table_name.add("invoice_lineitems");
                            log_field_name.add("total");
                            log_linked_field_name.add("invoice_number");
                            log_linked_field_value.add(String.valueOf(invoice_number));
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
                }

                if (getEstLineItemId.equals("") || getEstLineItemId.equals("All")) {
                    getEstLineItemId = null;
                } else {
                    getEstLineItemId = " '" + getEstLineItemId + "' ";

                    if (list_mod_itemList.get(loop).toString().equals("modify")) {

                        if (getEstLineItemId != null && !getEstLineItemId.equals(mod_getEstLineItemIdParam)) {
                            log_table_name.add("invoice_lineitems");
                            log_field_name.add("total");
                            log_linked_field_name.add("invoice_number");
                            log_linked_field_value.add(String.valueOf(invoice_number));
                            log_reference_table.add("");
                            log_reference_field.add("");
                            log_reference_value.add("");
                            log_old_value.add(mod_getEstLineItemIdParam);
                            log_new_value.add(getEstLineItemId);
                            log_changed_by.add(sesEmp);
                        }
                        if (updateField.length() > 0) {
                            updateField += ", est_lineitem_id=" + getEstLineItemId + " ";
                        } else {
                            updateField += " total=" + getEstLineItemId + " ";
                        }
                    }
                }
                
                intItemRes = 0;
                // System.out.println("text : " + text);
                if (list_mod_itemList.get(loop).toString().equals("delete")) {

                    stmt.addBatch("DELETE FROM invoice_lineitems WHERE invoice_number=" + invoice_number + " and item_id = " + getHidItemParam + " ");
                    // System.out.println("DELETE FROM invoice_lineitems WHERE invoice_number=" + invoice_number + " and item_id = " + getHidItemParam + "");
                } else if (list_mod_itemList.get(loop).toString().equals("modify")) {
                    updateInvoiceLineItems_Sql = updateInvoiceLineItems_Sql + updateField + where;
                    // System.out.println("updateInvoiceLineItems_Sql : " + updateInvoiceLineItems_Sql);
                    stmt.addBatch(updateInvoiceLineItems_Sql);
                    //intItemRes = stmt.executeUpdate(updateInvoiceLineItems_Sql);
                } else {

                    /* System.out.println("insert into invoice_lineitems(invoice_number,item_id,rate,quantity,description,total,unit_id,type_id,trim_id,item_code) "
                    + "values('" + invoice_number + "'," + getItemParam + "," + getUnitPriceParam + "," + getQuantityParam + "," + getDescParam + "," + getTotalParam +"," + getUnit +" ," + getTypeParam  + "," + getTrimParam  + "," + getCodeParam  + ")");*/
                    stmt.addBatch("insert into invoice_lineitems(invoice_number,category_id,item_id,rate,quantity,description,total,unit_id,type_id,trim_id,item_code,est_lineitem_id) "
                            + "values('" + invoice_number + "'," + getCategoryParam + "," + getItemParam + "," + getUnitPriceParam + "," + getQuantityParam + "," + getLineItemDescParam + "," + getTotalParam + "," + getUnit + " ," + getTypeParam + "," + getTrimParam + "," + getCodeParam +","+ getEstLineItemId + ")");
                    /**intItemRes = stmt.executeUpdate("insert into invoice_lineitems(invoice_number,item_id,rate,quantity,description,total) " +
                    "values('"+invoice_number+"',"+getItemParam+","+getUnitPriceParam+","+getQuantityParam+","+getDescParam+","+getTotalParam+")");
                     */
                }
            }
            int[] countItems = stmt.executeBatch();
            //System.out.println("countItems : " + countItems.length);
            for (int i = 0; i < countItems.length; i++) {
                // System.out.println(countItems[i]);
                if (countItems[i] == 1) {
                    counter_items++;
                }
            }
            // System.out.println("counter_items : " + counter_items);

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
    
    public void deleteInvoiceLineItem() {
        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String temp = "";
        String invoiceNum = "";

        //Create a response object and set the response for the request.
        //System.out.println("Received Item - : "+proj_id);
        try {

            con = dbcon.getSampleProperty();
            
            //Get Invoice Number using Invoice Line Item Id and Proj Id
            if(invoiceLineItemIdList.size() > 0) {
                temp = invoiceLineItemIdList.get(0).toString();
            }
            String getInvoiceNum = "SELECT i.invoice_number FROM invoices i, invoice_lineitems il "
                                + "WHERE il.invoice_lineitem_id='"+temp+"' AND i.proj_id='"+proj_id+"' AND i.invoice_number = il.invoice_number";
            stmt = con.prepareStatement(getInvoiceNum);
            //System.out.println("Delete Invoice Lineitems Query : " + getInvoiceNum);
            rs = stmt.executeQuery();
            while(rs.next()) {
                if(rs.getString(1) != null){
                    invoiceNum = rs.getString(1);
                }
            }
            
            for(int index=0; index < invoiceLineItemIdList.size(); index++) {
                temp = invoiceLineItemIdList.get(index).toString();
                String deleteInv = " DELETE FROM invoice_lineitems WHERE invoice_lineitem_id="+invoiceLineItemIdList.get(index);

                stmt = con.prepareStatement(deleteInv);
                //System.out.println("Delete Invoice Lineitems Query : " + deleteInv);
                intDelInvRes = stmt.executeUpdate();
            }
            
            String checkExisting = "SELECT COUNT(*) FROM invoices i, invoice_lineitems il WHERE i.proj_id='"+proj_id+"' AND i.invoice_number = il.invoice_number AND i.invoice_number='"+invoiceNum+"'";
            stmt = con.prepareStatement(checkExisting);
            //System.out.println("Check for Existing Lineitems : " + checkExisting);
            rs = stmt.executeQuery();
            int count = 0;
            while(rs.next()) {
                count = rs.getInt(1);
            }
            if(count==0) {
                String deleteInv = "DELETE FROM invoices WHERE proj_id='"+proj_id+"' AND invoice_number='"+invoiceNum+"'";
                stmt = con.prepareStatement(deleteInv);
                //System.out.println("Delete Invoice Query : " + deleteInv);
                intDelInvRes = stmt.executeUpdate();
            } else {
                String updateInv = "UPDATE invoices SET invoice_value='"+invoice_value+"' WHERE proj_id='"+proj_id+"' AND invoice_number='"+invoiceNum+"'";
                stmt = con.prepareStatement(updateInv);
                //System.out.println("Update Invoice Grand Total Query : " + updateInv);
                intDelInvRes = stmt.executeUpdate();
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
                log.setModuleName("Invoice");
                log.DBLogOption();
                String logOption = log.getLogOption();

                if ("1".equals(logOption)) {
                    intDBAddRes = log.createLog();
                } else {
                    // System.out.println("Log flag off");
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
        return intAddRes;
    }
 public String maxInvoiceNumber(String billingLoc) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String maxInvoiceId = "";
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();

            stmt = con.createStatement();
            //System.out.println("billingLocation"+billlocatn);
            if (billlocatn.equals("1")){
                rs = stmt.executeQuery("select max(invoice_disp_number_chennai) from invoices");
            }
            else if(billlocatn.equals("3"))
            {
                rs = stmt.executeQuery("select max(invoice_disp_number_singapore) from invoices");
            }
            else if(billlocatn.equals("4"))
            {
                rs = stmt.executeQuery("select max(invoice_disp_number_tm) from invoices");
            }
             else if(billlocatn.equals("5"))
            {
                rs = stmt.executeQuery("select max(invoice_disp_number_SG2) from invoices");
            }
            //rs = stmt.executeQuery("select max(invoice_disp_number_singapore) from invoices");
            //rs = stmt.executeQuery("select max(invoice_disp_number_chennai) from invoices");
            if (rs.next()) {
               maxInvoiceId = rs.getString(1);
              if (rs.wasNull()) {
                 maxInvoiceId = "0";
             }
            }


        } catch (SQLException sqle) {
            System.out.println("SQLException in maxPlanId:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in maxPlanId:" + e);
        } finally {
            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing resultset:se.getMessage()");
                }

            }

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
        return maxInvoiceId;
    }
 public String projectBillingLoction(){
        String billingLoction ="";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
                try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select facility_id from projects where proj_id='"+proj_id+"'");
            if (rs.next()) {
                billingLoction = rs.getString(1);
}


        } catch (SQLException sqle) {
            System.out.println("SQLException in maxPlanId:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in maxPlanId:" + e);
        } finally {
            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing resultset:se.getMessage()");
                }

            }

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
 return billingLoction;
 }
  public String getInvoiceNumberforDisp() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String InvoiceIdforDisp = "";
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();

            stmt = con.createStatement();
            //System.out.println("billingLocation"+billlocatn);
            if (billlocatn.equals("1")){
                rs = stmt.executeQuery("select invoice_disp_number_chennai,invoice_number_disp from invoices where proj_id='"+proj_id+"' and invoice_number='"+invoice_number+"'");
            }
            else if(billlocatn.equals("3"))
            {
                rs = stmt.executeQuery("select invoice_disp_number_singapore,invoice_number_disp from invoices where proj_id='"+proj_id+"' and invoice_number='"+invoice_number+"'");
            }
            else if(billlocatn.equals("4"))
            {
                rs = stmt.executeQuery("select invoice_disp_number_tm,invoice_number_disp from invoices where proj_id='"+proj_id+"' and invoice_number='"+invoice_number+"'");
            }
            else if(billlocatn.equals("5"))
            {
                rs = stmt.executeQuery("select invoice_disp_number_SG2,invoice_number_disp from invoices where proj_id='"+proj_id+"' and invoice_number='"+invoice_number+"'");
            }
            //rs = stmt.executeQuery("select max(invoice_disp_number_singapore) from invoices");
            //rs = stmt.executeQuery("select max(invoice_disp_number_chennai) from invoices");
            if (rs.next()) {
               InvoiceIdforDisp = rs.getString(2);
               //System.out.println("dispv"+rs.getString(2));
              if (rs.wasNull()) {
                 InvoiceIdforDisp = "0";
             }
            }


        } catch (SQLException sqle) {
            System.out.println("SQLException in maxPlanId:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in maxPlanId:" + e);
        } finally {
            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing resultset:se.getMessage()");
                }

            }

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
        return InvoiceIdforDisp;
    }
    public void setBuyerType(String buyer_type) {
        this.buyer_type = buyer_type;
    }
     public void setBillLocation(String billlocatn) {
        this.billlocatn = billlocatn;
    }
}
