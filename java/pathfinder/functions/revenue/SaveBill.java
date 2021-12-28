/*
 * SaveBill.java
 *
 * Created on March 3, 2010, 10:12 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import connection.DBconnection;
import pathfinder.functions.*;
import pathfinder.functions.generic.*;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramyamaims
 */
public class SaveBill {

    DBconnection conProp = new DBconnection();
    BillingInfo billInfo = new BillingInfo();
    DBLog dbLog = new DBLog();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql_select = "";
    private int intAddRes = 0;
    private int intDBAddRes = 0;
    private int intItemRes = 0;
    private int countItemsVal = 0;
    private int counter_items = 0;
    private int intRes = 0;
    private List log_table_name = new ArrayList();
    private List log_field_name = new ArrayList();
    private List log_linked_field_name = new ArrayList();
    private List log_old_value = new ArrayList();
    private List log_new_value = new ArrayList();
    private List log_changed_by = new ArrayList();
    private List log_linked_field_value = new ArrayList();

    ;
    private List log_reference_table = new ArrayList();
    private List log_reference_field = new ArrayList();
    private List log_reference_value = new ArrayList();
    private int bill_number = 0;
    private String text = "";
    private List line_text = new ArrayList();
    private String mod_proj_id = "";
    private String proj_id = "";
    private String invoice_proj_id = "";
    private String bill_status = "";
    private String mod_status = "";
    private String status = "";
    private String mod_bill_value = "";
    private String bill_value = "";
    private String mod_sesEmp = "";
    private String sesEmp = "";
    private String temp_val = "";
    private List getItemList = new ArrayList();
    private List getHidItemList = new ArrayList();
    private List getUnitPriceList = new ArrayList();
    private List getQuantityList = new ArrayList();
    private List getInvoiceQuantityList = new ArrayList();
    private List getDescList = new ArrayList();
    private List getTotalList = new ArrayList();
    private String mod_getItemParam = "";
    private String mod_getHidItemParam = "";
    private String mod_getUnitPriceParam = "";
    private String mod_getQuantityParam = "";
    private String mod_getDescParam = "";
    private String mod_getTotalParam = "";
    private String getItemParam = "";
    private String getHidItemParam = "";
    private String getUnitPriceParam = "";
    private String getQuantityParam = "";
    private String getDescParam = "";
    private String getTotalParam = "";
    private int intAddBillRes = 0;
    private int intAddItemRes = 0;
    private int intBillStatusRes = 0;
      private String getUnit="";
      private String mod_getUnit="";

   private List list_getUnitParam = new ArrayList();

       public void setUnit(List list_getUnitParam)
    {
        this.list_getUnitParam = list_getUnitParam;
    }

      public void str_setUnitParam(String getUnit) {
        this.getUnit = getUnit;
    }

    public void setProjId(String proj_id) {
        this.proj_id = proj_id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextList(List line_text) {
        this.line_text = line_text;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBillValue(String bill_value) {
        this.bill_value = bill_value;
    }

    public void setBillNumber(int bill_number) {
        this.bill_number = bill_number;
    }

    public void setItemId(String getItemParam) {
        this.getItemParam = getItemParam;
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

    public void setDesc(String getDescParam) {
        this.getDescParam = getDescParam;
    }

    public void setTotal(String getTotalParam) {
        this.getTotalParam = getTotalParam;
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

    public void setDescList(List getDescList) {
        this.getDescList = getDescList;
    }

    public void setTotalList(List getTotalList) {
        this.getTotalList = getTotalList;
    }

    public void setSesEmp(String sesEmp) {
        this.sesEmp = sesEmp;
    }

    public int getBillNumber() {
        return bill_number;
    }

    public int getAddBill() {
        return intRes;
    }

    public int getAddItem() {
        return intAddItemRes;
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

    public void addBill() {

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

            mod_proj_id = "";
            mod_status = "";
            mod_bill_value = "";
            mod_sesEmp = "";

            String updateBill_Sql = "";
            String updateField = "";
            updateBill_Sql = " update billing set ";
            String where = " where bill_number=" + bill_number + " ";
            if (text.equals("modify")) {
                billInfo.setBillNumber(String.valueOf(bill_number));
                billInfo.getBillingInfo();
                mod_proj_id = " '" + billInfo.getProjId() + "' ";
                mod_status = " '" + billInfo.getBillStatusId() + "' ";
                mod_bill_value = " '" + billInfo.getBillValue() + "' ";
                mod_sesEmp = " '" + billInfo.getBillBy() + "' ";
            }

            //System.out.println("mod_proj_id : " + mod_proj_id);
            //System.out.println("mod_status : " + mod_status);
            //System.out.println("mod_bill_value : " + mod_bill_value);
            //System.out.println("mod_sesEmp : " + mod_sesEmp);

            if (text.equals("")) {
                rs = stmt.executeQuery("select max(bill_number) from billing");

                while (rs.next()) {
                    bill_number = rs.getInt(1);

                    if (rs.wasNull()) {
                        bill_number = 0;
                    }
                }
                bill_number++;
            }
            //System.out.println("bill_number : " + bill_number);

            if (proj_id.equals("") || proj_id.equals("All")) {
                proj_id = null;
            } else {
                proj_id = " '" + proj_id + "' ";
                if (text.equals("modify")) {
                    if (proj_id != null && !proj_id.equals(mod_proj_id)) {
                        log_table_name.add("billing");
                        log_field_name.add("proj_id");
                        log_linked_field_name.add("bill_number");
                        log_linked_field_value.add(String.valueOf(bill_number));
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

            if (status.equals("") || status.equals("All")) {
                status = null;
            } else {
                status = " '" + status + "' ";
                if (text.equals("modify")) {

                    if (status != null && !status.equals(mod_status)) {
                        log_table_name.add("billing");
                        log_field_name.add("status");
                        log_linked_field_name.add("bill_number");
                        log_linked_field_value.add(String.valueOf(bill_number));
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

            if (bill_value.equals("") || bill_value.equals("All")) {
                bill_value = null;
            } else {
                bill_value = " '" + bill_value + "' ";
                if (text.equals("modify")) {

                    if (bill_value != null && !bill_value.equals(mod_bill_value)) {
                        log_table_name.add("billing");
                        log_field_name.add("bill_value");
                        log_linked_field_name.add("bill_number");
                        log_linked_field_value.add(String.valueOf(bill_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
                        log_old_value.add(mod_bill_value);
                        log_new_value.add(bill_value);
                        log_changed_by.add(sesEmp);
                    }

                    if (updateField.length() > 0) {
                        updateField += ", bill_value=" + bill_value + " ";
                    } else {
                        updateField += " bill_value=" + bill_value + " ";
                    }
                }
            }

            if (sesEmp.equals("") || sesEmp.equals("All")) {
                sesEmp = null;
            } else {
                sesEmp = " '" + sesEmp + "' ";
                if (text.equals("modify")) {
                    if (sesEmp != null && !sesEmp.equals(mod_sesEmp)) {
                        log_table_name.add("billing");
                        log_field_name.add("bill_createdby");
                        log_linked_field_name.add("bill_number");
                        log_linked_field_value.add(String.valueOf(bill_number));
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
                        log_old_value.add(mod_sesEmp);
                        log_new_value.add(sesEmp);
                        log_changed_by.add(sesEmp);
                    }

                    if (updateField.length() > 0) {
                        updateField += ", bill_createdby=" + sesEmp + " ";
                    } else {
                        updateField += " bill_createdby=" + sesEmp + " ";
                    }
                }
            }

            intAddBillRes = 0;

            if (text.equals("modify")) {
                updateBill_Sql = updateBill_Sql + updateField + where;
                //System.out.println("updateBill_Sql : " + updateBill_Sql);
                intAddBillRes = stmt.executeUpdate(updateBill_Sql);
            } else {

                //System.out.println("insert into billing(bill_number,bill_date,proj_id,status,bill_value,bill_createdby) "
                        //+ "values('" + bill_number + "',current_timestamp()," + proj_id + "," + status + "," + bill_value + "," + sesEmp + ")");
                intAddBillRes = stmt.executeUpdate("insert into billing(bill_number,bill_date,proj_id,status,bill_value,bill_createdby) "
                        + "values('" + bill_number + "',current_timestamp()," + proj_id + "," + status + "," + bill_value + "," + sesEmp + ")");
            }

            //System.out.println("getItemList.size() : " + getItemList.size());
            if (getItemList.size() > 0) {
                countItemsVal = addBillLineItem(con);
                //System.out.println("getItemList : " + getItemList);
            }

            if (getItemList.size() > 0) {

                if (getItemList.size() == countItemsVal && countItemsVal != 0) {
                    intRes = endConnection(con);
                }
            } else {
                intRes = endConnection(con);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    public int addBillLineItem(Connection con) {

        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String updateBillLineItems_Sql = "";
            String updateField = "";
            for (int loop = 0; loop < getItemList.size(); loop++) {

                updateBillLineItems_Sql = "";
                updateField = "";
                mod_getItemParam = "";
                mod_getHidItemParam = "";
                mod_getUnitPriceParam = "";
                mod_getQuantityParam = "";
                mod_getDescParam = "";
                mod_getTotalParam = "";

                setItemId(getItemList.get(loop).toString());
                setHidItemId(getHidItemList.get(loop).toString());
                setUnitPrice(getUnitPriceList.get(loop).toString());
                setQuantity(getQuantityList.get(loop).toString());
                setDesc(getDescList.get(loop).toString());
                setTotal(getTotalList.get(loop).toString());
                str_setUnitParam(list_getUnitParam.get(loop).toString());

                if (line_text.get(loop).toString().equals("modify")) {
                    sql_select = "select item_id,quantity,rate,description,total,unit_id from billing_lineitems where bill_number = " + bill_number + " and item_id = '" + getHidItemParam + "' ";
                    //System.out.println("sql_select for line item  : " + sql_select);
                    rs = stmt.executeQuery("select item_id,quantity,rate,description,total,unit_id from billing_lineitems where bill_number = " + bill_number + " and item_id = '" + getHidItemParam + "' ");
                    while (rs.next()) {
                        mod_getItemParam = " '" + rs.getString("item_id") + "' ";
                        mod_getUnitPriceParam = " '" + rs.getString("rate") + "' ";
                        mod_getQuantityParam = " '" + rs.getString("quantity") + "' ";
                        mod_getDescParam = " '" + rs.getString("description") + "' ";
                        mod_getTotalParam = " '" + rs.getString("total") + "' ";
                        mod_getUnit= " '" + rs.getString("unit_id") + "' ";
                    }
                }

                updateBillLineItems_Sql = " update billing_lineitems set ";
                String where = " where bill_number=" + bill_number + "  and item_id = '" + getHidItemParam + "' ";
                //System.out.println("line_text : " + line_text.get(loop).toString());
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

                        if (getItemParam != null && !getItemParam.equals(mod_getItemParam)) {
                            log_table_name.add("billing_lineitems");
                            log_field_name.add("item_id");
                            log_linked_field_name.add("bill_number");
                            log_linked_field_value.add(String.valueOf(bill_number));
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

                 if (getUnit.equals("") || getUnit.equals("All")) {
                    getUnit = null;
                } else {
                    getUnit = " '" + getUnit + "' ";
                }
                if (line_text.get(loop).toString().equals("modify")) {
                    if (getUnit != null && !getUnit.equals(mod_getUnit)) {
                        log_table_name.add("billing_lineitems");
                        log_field_name.add("unit_id");

                        log_linked_field_name.add("bill_number");
                        log_linked_field_value.add(String.valueOf(bill_number));
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


                
                if (getUnitPriceParam.equals("") || getUnitPriceParam.equals("All")) {
                    getUnitPriceParam = null;
                } else {
                    getUnitPriceParam = " '" + getUnitPriceParam + "' ";
                    if (line_text.get(loop).toString().equals("modify")) {

                        if (getUnitPriceParam != null && !getUnitPriceParam.equals(mod_getUnitPriceParam)) {
                            log_table_name.add("billing_lineitems");
                            log_field_name.add("rate");
                            log_linked_field_name.add("bill_number");
                            log_linked_field_value.add(String.valueOf(bill_number));
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
                    if (line_text.get(loop).toString().equals("modify")) {

                        if (getQuantityParam != null && !getQuantityParam.equals(mod_getQuantityParam)) {
                            log_table_name.add("billing_lineitems");
                            log_field_name.add("quantity");
                            log_linked_field_name.add("bill_number");
                            log_linked_field_value.add(String.valueOf(bill_number));
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

                if (getDescParam.equals("") || getDescParam.equals("All")) {
                    getDescParam = null;
                } else {
                    getDescParam = " '" + getDescParam + "' ";
                    if (line_text.get(loop).toString().equals("modify")) {

                        if (getDescParam != null && !getDescParam.equals(mod_getDescParam)) {
                            log_table_name.add("billing_lineitems");
                            log_field_name.add("description");
                            log_linked_field_name.add("bill_number");
                            log_linked_field_value.add(String.valueOf(bill_number));
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
                }

                if (getTotalParam.equals("") || getTotalParam.equals("All")) {
                    getTotalParam = null;
                } else {
                    getTotalParam = " '" + getTotalParam + "' ";
                    if (line_text.get(loop).toString().equals("modify")) {

                        if (getTotalParam != null && !getTotalParam.equals(mod_getTotalParam)) {
                            log_table_name.add("billing_lineitems");
                            log_field_name.add("total");
                            log_linked_field_name.add("bill_number");
                            log_linked_field_value.add(String.valueOf(bill_number));
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

                intAddItemRes = 0;
                //System.out.println("text : " + line_text.get(loop).toString());
                if (line_text.get(loop).toString().equals("delete")) {
                    //System.out.println("delete");

                    stmt.addBatch("DELETE FROM billing_lineitems WHERE bill_number = " + bill_number + " and item_id = " + getHidItemParam + " ");
                    //System.out.println("DELETE FROM billing_lineitems WHERE bill_number = " + bill_number + " and item_id = " + getHidItemParam + " ");
                } else if (line_text.get(loop).toString().equals("modify")) {
                    updateBillLineItems_Sql = updateBillLineItems_Sql + updateField + where;
                    //System.out.println("updateBillLineItems_Sql : " + updateBillLineItems_Sql);
                    stmt.addBatch(updateBillLineItems_Sql);
                } else {

                    //System.out.println("insert into billing_lineitems(bill_number,item_id,rate,quantity,description,total,unit_id) "
                            //+ "values('" + bill_number + "'," + getItemParam + "," + getUnitPriceParam + "," + getQuantityParam + "," + getDescParam + "," + getTotalParam +"," + getUnit + ")");
                    stmt.addBatch("insert into billing_lineitems(bill_number,item_id,rate,quantity,description,total,unit_id) "
                            + "values('" + bill_number + "'," + getItemParam + "," + getUnitPriceParam + "," + getQuantityParam + "," + getDescParam + "," + getTotalParam +"," + getUnit + ")");
                    /**intAddItemRes = stmt.executeUpdate("insert into billing_lineitems(bill_number,item_id,rate,quantity,description,total) " +
                    "values('"+bill_number+"',"+getItemParam+","+getUnitPriceParam+","+getQuantityParam+","+getDescParam+","+getTotalParam+")");
                     */
                }
            }

            //System.out.println("intAddItemRes : " + intAddItemRes);
            if (intAddItemRes != 0 && line_text.contains("modify")) {
                invoice_proj_id = "";
                rs = stmt.executeQuery("select proj_id from invoices where proj_id = " + proj_id + " ");
                if (rs.next()) {
                    invoice_proj_id = rs.getString("proj_id");
                    if (rs.wasNull()) {
                        invoice_proj_id = "";
                    }
                }
                //System.out.println("invoice_proj_id : " + invoice_proj_id);

                getInvoiceQuantityList.clear();

                if (!invoice_proj_id.equals("")) {
                    for (int loop = 0; loop < getItemList.size(); loop++) {
                        setItemId(getItemList.get(loop).toString());
                        //System.out.println("select sum(quantity) from invoices i, invoice_lineitems il where i.proj_id =  " + proj_id + "  "
                                //+ "and i.invoice_number = il.invoice_number and il.item_id = " + getItemParam + " ");
                        rs = stmt.executeQuery("select sum(quantity) from invoices i, invoice_lineitems il where i.proj_id =  " + proj_id + "  "
                                + "and i.invoice_number = il.invoice_number and il.item_id = " + getItemParam + "  ");

                        while (rs.next()) {
                            temp_val = rs.getString("sum(quantity)");
                            if (rs.wasNull()) {
                                temp_val = "0";
                            }
                            //System.out.println("temp_val : " + temp_val);
                            getInvoiceQuantityList.add(temp_val);
                        }
                    }
                    //System.out.println("getInvoiceQuantityList : " + getInvoiceQuantityList);
                    //System.out.println("getQuantityList : " + getQuantityList);
                    if (getInvoiceQuantityList.size() < getQuantityList.size()) {
                        rs = stmt.executeQuery("select status_id from status where status= 'Partial' and status_type='bill'");
                        bill_status = "";
                        while (rs.next()) {
                            bill_status = rs.getString("status_id");
                        }
                        //System.out.println("bill_status : " + bill_status);
                        int resVal = stmt.executeUpdate("update billing set status = '" + bill_status + "' where proj_id = " + proj_id + " ");

                    } else {
                        intBillStatusRes = 0;
                        for (int loop = 0; loop < getInvoiceQuantityList.size(); loop++) {

                            if (Integer.parseInt(getInvoiceQuantityList.get(loop).toString()) != Integer.parseInt(getQuantityList.get(loop).toString())) {
                                intBillStatusRes++;
                            }
                        }
                        //System.out.println("intBillStatusRes : " + intBillStatusRes);

                        if (intBillStatusRes == 0) {
                            rs = stmt.executeQuery("select status_id from status where status= 'Invoiced' and status_type='bill'");
                            bill_status = "";
                            while (rs.next()) {
                                bill_status = rs.getString("status_id");
                            }
                            //System.out.println("bill_status : " + bill_status);

                            int resVal = stmt.executeUpdate("update billing set status = '" + bill_status + "' where proj_id = " + proj_id + " ");
                        } else {
                            rs = stmt.executeQuery("select status_id from status where status= 'Partial' and status_type='bill'");
                            bill_status = "";
                            while (rs.next()) {
                                bill_status = rs.getString("status_id");
                            }
                            //System.out.println("bill_status : " + bill_status);

                            int resVal = stmt.executeUpdate("update billing set status = '" + bill_status + "' where proj_id = " + proj_id + " ");
                        }
                    }
                }
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

        try {
            con.commit();
            con.setAutoCommit(true);
            intAddRes = 1;
            intDBAddRes = 0;
            if (intAddRes == 1) {

                DBLog log = new DBLog(log_table_name, log_field_name, log_linked_field_name, log_linked_field_value, log_old_value, log_new_value, log_changed_by, log_reference_table, log_reference_field, log_reference_value);

                //Log the records only if the flag is on
                log.setModuleName("Bill");
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
