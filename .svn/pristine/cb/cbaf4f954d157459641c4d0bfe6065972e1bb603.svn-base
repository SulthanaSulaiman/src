/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import java.io.*;
import java.sql.*;
import java.util.*;
import connection.DBconnection;

/**
 *
 * @author Administrator
 */
public class InvoiceLineItem {
// to get the line item details of any selected project whose invoice status is not cancelled.

    DBconnection conProp = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private Statement stmtSub = null;
    private ResultSet rsSub = null;
    private String proj_id = "";
    private String status_invoice_number = "";
    private String set_type = "";
    private String invoice_number = "";
    private String temp_val = "";
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String sql_group = "";
    private List line_item_id = new ArrayList();
    private List est_line_item_id = new ArrayList();
    private List line_item = new ArrayList();
    private List line_item_rate = new ArrayList();
    private List line_item_desc = new ArrayList();
    private List line_item_quantity = new ArrayList();
    private List line_item_total = new ArrayList();
    private List invoice_line_item_Id = new ArrayList();
    private List line_item_trim = new ArrayList();
    private List line_item_type = new ArrayList();
    private List line_item_code = new ArrayList();
    private List line_item_num = new ArrayList();
    private List line_item_no = new ArrayList();
    private String invoice_total = "";
    private String finalInvoiceTotal = "";
    private String totalamountForprofitcal = "";
    private String partialInvoiceTotal = "";
    private String finalInvoiceMailed = "";
    String lineItemId = "";
    Map invoiceQty = new HashMap();
    private List item_unit = new ArrayList();
    private List item_unit_id = new ArrayList();
    private List category_id = new ArrayList();
    private List category_name = new ArrayList();
    //Variable for WIP invoice
    private List invoice_number_list = new ArrayList();
    private List part_no_list = new ArrayList();
    private List invoice_date_list = new ArrayList();
    private List invoice_value_list = new ArrayList();
    private List proj_id_list = new ArrayList();
    private List invoice_flag_list = new ArrayList();
    private List wip_proj_id_list = new ArrayList();
    private List epaceId = new ArrayList();
    private List projName = new ArrayList();
    private String toDate;
    private String billingLocation;
    private List superCategory = new ArrayList();
    private List superCategoryId = new ArrayList();
    
    //Seter and Geter Method for WIP Invoice
    public List getSuperCategory() {
        return superCategory;
    }

    public void setSuperCategory(List superCategory) {
        this.superCategory = superCategory;
    }

    public List getSuperCategoryId() {
        return superCategoryId;
    }

    public void setSuperCategoryId(List superCategoryId) {
        this.superCategoryId = superCategoryId;
    }
    
    public List getProjName() {
        return projName;
    }

    public void setProjName(List projName) {
        this.projName = projName;
    }

    public String getFinalInvoiceTotal() {
        return finalInvoiceTotal;
    }

    public void setFinalInvoiceTotal(String finalInvoiceTotal) {
        this.finalInvoiceTotal = finalInvoiceTotal;
    }

     public String getTotalamountForprofitcal() {
        return totalamountForprofitcal;
    }

    public void setTotalamountForprofitcal(String totalamountForprofitcal) {
        this.totalamountForprofitcal = totalamountForprofitcal;
    }

    public String getFinalInvoiceMailed() {
        return finalInvoiceMailed;
    }

    public void setFinalInvoiceMailed(String finalInvoiceMailed) {
        this.finalInvoiceMailed = finalInvoiceMailed;
    }

    public String getPartialInvoiceTotal() {
        return partialInvoiceTotal;
    }

    public void setPartialInvoiceTotal(String partialInvoiceTotal) {
        this.partialInvoiceTotal = partialInvoiceTotal;
    }
    
    
    public List getEpaceId() {
        return epaceId;
    }

    public void setEpaceId(List epaceId) {
        this.epaceId = epaceId;
    }
    
    public String getBillingLocation() {
        return billingLocation;
    }

    public void setBillingLocation(String billingLocation) {
        this.billingLocation = billingLocation;
    }
    
    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    
    public List getWip_proj_id_list() {
        return wip_proj_id_list;
    }

    public void setWip_proj_id_list(List wip_proj_id_list) {
        this.wip_proj_id_list = wip_proj_id_list;
    }
    
    public List getInvoice_number_list() {
        return invoice_number_list;
    }

    public void setInvoice_number_list(List invoice_number_list) {
        this.invoice_number_list = invoice_number_list;
    }

    public List getPart_no_list() {
        return part_no_list;
    }

    public void setPart_no_list(List part_no_list) {
        this.part_no_list = part_no_list;
    }

    public List getInvoice_date_list() {
        return invoice_date_list;
    }

    public void setInvoice_date_list(List invoice_date_list) {
        this.invoice_date_list = invoice_date_list;
    }

    public List getInvoice_value_list() {
        return invoice_value_list;
    }

    public void setInvoice_value_list(List invoice_value_list) {
        this.invoice_value_list = invoice_value_list;
    }

    public List getProj_id_list() {
        return proj_id_list;
    }

    public void setProj_id_list(List proj_id_list) {
        this.proj_id_list = proj_id_list;
    }

    public List getInvoice_flag_list() {
        return invoice_flag_list;
    }

    public void setInvoice_flag_list(List invoice_flag_list) {
        this.invoice_flag_list = invoice_flag_list;
    }
    

    public List getEst_line_item_id() {
        return est_line_item_id;
    }

    public void setEst_line_item_id(List est_line_item_id) {
        this.est_line_item_id = est_line_item_id;
    }

    public List getCategoryId() {
        return category_id;
    }

    public List getCategoryName() {
        return category_name;
    }

    public Map getInvoicedQty() {
        return invoiceQty;
    }

    public void setProjID(String proj_id) {
        this.proj_id = proj_id;
    }

    public void setInvoiceNumber(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public void setType(String set_type) {
        this.set_type = set_type;
    }

    public List getLineItemId() {
        return line_item_id;
    }

    public List getInvoiceLineItemId() {
        return invoice_line_item_Id;
    }

    public List getLineItemTrim() {
        return line_item_trim;
    }

    public List getLineItemType() {
        return line_item_type;
    }

    public List getLineItemCode() {
        return line_item_code;
    }

    public List getItemUnit() {
        return item_unit;
    }

    public List getItemUnitId() {
        return item_unit_id;
    }

    public void setLineItemId(String lineItemId) {
        this.lineItemId = lineItemId;
    }

    public void setLineItemId(List line_item_id) {
        this.line_item_id = line_item_id;
    }

    public List getLineItemName() {
        return line_item;
    }

    public List getLineItemRate() {
        return line_item_rate;
    }

    public List getLineItemDesc() {
        return line_item_desc;
    }

    public List getLineItemQuantity() {
        return line_item_quantity;
    }

    public List getLineItemTotal() {
        return line_item_total;
    }

    public String getInvoiceTotal() {
        return invoice_total;
    }

    public List getLineItemNum()
    {
        return line_item_num;
    }

    public void getInvoiceLineItems() {
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            sql_select = "select el.invoice_lineitem_id,el.item_id,el.trim_id,el.type_id,el.item_code,el.quantity,el.rate,el.unit_id,el.total ";
            sql_from = "from invoice_lineitems el,invoices e ";
            sql_where = " el.invoice_number=e.invoice_number ";


            if (!invoice_number.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " el.invoice_number = " + invoice_number + " ";
            }

            if (!proj_id.equals("")) {
                if (sql_where.length() > 0) {
                    sql_where += " and ";
                }
                sql_where += " e.proj_id = " + proj_id + " ";
            }


            if (sql_where.length() > 0) {
                sql_select += sql_from + " where " + sql_where;
            } else {

                sql_select += sql_from;
            }

            //System.out.println("getInvoiceLineItems sql:" + sql_select);

            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {
                temp_val = "";
                temp_val = rs.getString(1);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                invoice_line_item_Id.add(temp_val);


                temp_val = "";
                temp_val = rs.getString(2);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_id.add(temp_val);
                line_item.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(3);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_trim.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(4);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_type.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(5);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_code.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(6);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_quantity.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(7);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_rate.add(temp_val);


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
                line_item_total.add(temp_val);

                
            }


            sql_select = "";
            sql_from = "";
            sql_where = "";

            sql_select = "select est.invoice_value ";
            sql_from = "from invoices est ";

            if (!invoice_number.equals("")) {
                if (!sql_where.equals("")) {;
                    sql_where += "and ";
                }
                sql_where += "invoice_number ='" + invoice_number + "' ";
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
                    invoice_total = temp_val;
                }
            } else {
                invoice_total = "";
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

    public void collectInvoiceLineItem() {
        //System.out.println("collectInvoiceLineItem............");

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "";
            sql_from = "";
            sql_where = "";

            sql_select = "select i.invoice_number ";
            sql_from = "from invoices i, status s ";
            sql_where = "i.status= s.status_id and s.status !='Cancelled' ";

            if (!proj_id.equals("")) {
                sql_where += "and i.proj_id = '" + proj_id + "' ";
            }

            if (!invoice_number.equals("")) {
                sql_where += "and i.invoice_number = '" + invoice_number + "' ";
            }

            sql_select += sql_from + "where " + sql_where;//+ " order by i.invoice_itemId asc";
            status_invoice_number = "";
            rs = stmt.executeQuery(sql_select);

            while (rs.next()) {
                status_invoice_number = rs.getString(1);
                if (rs.wasNull()) {
                    status_invoice_number = "";
                }
            }
            //System.out.println("status_invoice_number : "+status_invoice_number);
            // if(!status_invoice_number.equals("")){
            sql_select = "";
            sql_from = "";
            sql_where = "";
            sql_group = "";

            ///sql_select = "select el.item_id,el.item_id,sum(el.quantity),sum(el.rate),el.description,sum(el.total),el.unit_id,el.unit_id,el.type_id,el.trim_id,el.item_code,el.invoice_lineitem_id,el.category_id ";
            sql_select = "select trim(el.item_id),trim(el.item_id),sum(el.quantity),sum(el.rate),el.description,sum(el.total),el.unit_id,el.unit_id,el.type_id,el.trim_id,el.item_code,el.invoice_lineitem_id,el.category_id,el.est_lineitem_id,e.po_number ";
            /*  sql_from = "from invoice_lineitems il,billing_lineitems_master blm,invoices i, status s ,units_master u ";
             sql_where = "il.item_id = blm.billing_item_id and i.invoice_number = il.invoice_number and u.unit_id=il.unit_id "+
             "and s.status!='Cancelled' and i.status= s.status_id ";*/
            sql_from = " FROM invoices i,invoice_lineitems el  LEFT JOIN estimate_lineitems e ON el.est_lineitem_id = e.est_lineitem_id  ";

            sql_where = " i.invoice_number = el.invoice_number ";

            //sql_group = "group by el.category_id,el.item_id,el.type_id,el.trim_id,el.rate ";
            sql_group = "group by el.invoice_lineitem_id";


            if (!proj_id.equals("")) {
                sql_where += "and i.proj_id = '" + proj_id + "' ";
            }

            if (set_type.equals("")) {
                //System.out.println("set_type in invoice line item if : "+set_type);
                if (!invoice_number.equals("")) {
                    sql_where += "and i.invoice_number = '" + invoice_number + "' ";
                }
            } else {
                //System.out.println("set_type in invoice line item else : "+set_type);
                if (!invoice_number.equals("")) {
                    sql_where += "and i.invoice_number != '" + invoice_number + "' ";
                }
            }

            sql_select += sql_from + "where " + sql_where + sql_group + " ORDER BY el.est_lineitem_id IS NOT NULL, e.sort_order IS NULL, e.sort_order";

          //System.out.println("sql_select in billingLineItems of pathfinder.functions.revenue : - " + sql_select);

            rs = stmt.executeQuery(sql_select);

            line_item_id.clear();
            line_item.clear();
            line_item_quantity.clear();
            line_item_rate.clear();
            line_item_desc.clear();
            line_item_total.clear();
            category_id.clear();
            category_name.clear();
            line_item_trim.clear();
            line_item_type.clear();
            line_item_code.clear();
            line_item_num.clear();

            while (rs.next()) {
                temp_val = "";
                temp_val = rs.getString(1);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_id.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(2);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(3);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_quantity.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(4);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_rate.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(5);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                if (temp_val != null) {
                    line_item_desc.add(temp_val);
                }

                temp_val = "";
                temp_val = rs.getString(6);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_total.add(temp_val);

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
                line_item_type.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(10);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_trim.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(11);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_code.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(12);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                invoice_line_item_Id.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(13);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                category_id.add(temp_val);
                category_name.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(14);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                est_line_item_id.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(15);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_num.add(temp_val);

                /*
                 if(temp_val.equals("") || temp_val=="") {
                 String itemId = rs.getString(1);
                 String CatId = rs.getString(13);
                 rsSub = stmtSub.executeQuery("SELECT el.est_lineitem_id FROM estimate e, estimate_lineitems el, invoices i, invoice_lineitems il " +
                 "WHERE e.est_number=el.est_number AND e.proj_id=i.proj_id AND i.invoice_number=il.invoice_number AND i.proj_id='"+proj_id+"' " +
                 "AND el.item_id = '"+itemId+"' AND el.category_id='"+CatId+"'" +
                 "GROUP BY el.est_lineitem_id");
                 String S = "";
                 while(rsSub.next()){
                 S = rsSub.getString(1);
                 if(rsSub.wasNull()){
                 S = "";
                 }
                 System.out.println("Est : "+S);
                 }
                 } else {
                 System.out.println("Esti Avail");
                 }
                 */
            }

            rs = stmt.executeQuery("select sum(invoice_value) from invoices b, status s "
                    + "where b.status= s.status_id and s.status!='Cancelled' "
                    + "and b.proj_id = '" + proj_id + "'");
            invoice_total = "";
            while (rs.next()) {
                invoice_total = rs.getString(1);
                if (rs.wasNull()) {
                    invoice_total = "";
                }
            }

            //rs = stmt.executeQuery("SELECT SUM(invoice_value), (SELECT invoice_display_date FROM invoices i WHERE i.part_invoice_flag='0' AND i.proj_id='" + proj_id + "') FROM invoices WHERE proj_id='" + proj_id + "' AND part_invoice_flag='0' ");
            rs = stmt.executeQuery(" SELECT SUM(CASE WHEN part_invoice_flag='0' THEN invoice_value ELSE 0 END), "
                    + " (SELECT invoice_display_date FROM invoices i WHERE i.part_invoice_flag='0' AND i.proj_id='" + proj_id + "'), "
                    + " SUM(CASE WHEN part_invoice_flag='1' THEN invoice_value ELSE 0 END) "
                    + " FROM invoices WHERE proj_id='" + proj_id + "'");
            finalInvoiceTotal = "";
            while (rs.next()) {
                finalInvoiceTotal = rs.getString(1);
                if (rs.wasNull()) {
                    finalInvoiceTotal = "0.0";
                }
                finalInvoiceMailed = rs.getString(2);
                if (rs.wasNull()) {
                    finalInvoiceMailed = "";
                }
                
                partialInvoiceTotal = rs.getString(3) == null ? "" : rs.getString(3).toString();

            }
            rs=stmt.executeQuery("SELECT SUM(CASE WHEN part_invoice_flag='0' OR part_invoice_flag='1' THEN invoice_value ELSE 0 END), "
  + " (SELECT invoice_display_date FROM invoices i WHERE i.part_invoice_flag='0' AND i.proj_id='" + proj_id + "') "
    + " FROM invoices WHERE proj_id='" + proj_id + "'");

            while (rs.next()) {
                totalamountForprofitcal = rs.getString(1);
                if (rs.wasNull()) {
                    totalamountForprofitcal = "0.0";
                }}
            //}
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    /*
     * This method is used to check whether the given line items are invoiced or not and it returns the invoiced line item.
     * This method is mainly used in billing when user clicks remove, we need to check whether that particular line item is invoiced
     * or not. If not invoiced the line item can be removed. If invoiced then the line item cannot be removed.
     *
     * @return invoiceNum  -  List of invoiced line item id.
     */
    public void isInvoicedLineItem() {

        try {

            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            String sql = "SELECT distinct(b.item_id) FROM invoices a,invoice_lineitems b WHERE a.invoice_number = b.invoice_number AND a.proj_id='" + proj_id + "' AND item_id IN(" + lineItemId + ")";

            //System.out.println("checkInvoice:"+sql);
            rs = stmt.executeQuery("SELECT distinct(b.item_id) FROM invoices a,invoice_lineitems b WHERE a.invoice_number = b.invoice_number AND a.proj_id='" + proj_id + "' AND item_id IN(" + lineItemId + ")");

            while (rs.next()) {
                line_item_id.add(rs.getString(1));
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


    /*
     * This method is used get the invoiced quantity for all line items.
     *
     * @return invoiceQty  -  Map which contains lineitem,invoiced qty.
     */
    public void invoiceQty() {

        try {

            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            String sql = "SELECT b.item_id,SUM(b.quantity) FROM invoices a, invoice_lineitems b WHERE a.invoice_number=b.invoice_number AND a.proj_id='" + proj_id + "' GROUP BY b.item_id";

            //System.out.println("getInvoiceQty:"+sql);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                //System.out.println("rs.getString1:"+rs.getString(1));
                //System.out.println("rs.getString2:"+rs.getString(2));
                //line_item_id.add(rs.getString(1));
                invoiceQty.put(rs.getString(1), rs.getString(2));

            }
            rs.close();
            stmt.close();
            con.close();
            //System.out.println("invoiceQtyMap:"+invoiceQty);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();

        }
    }
    
    //Getting super category and Id
    
    public void getSuperCategoryValues(Connection con){
        try{
            stmt = con.createStatement();
            String query = "SELECT super_cat_id,super_category FROM estimate_super_category";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                superCategory.add(rs.getString("super_category"));
                superCategoryId.add(rs.getString("super_cat_id"));
            }
            rs.close();
            stmt.close();
        }catch(Exception e){
            System.out.println("Error on getting super category values : "+e);
        }
        
    }
    //Invoice Detials for WIP Report
    
    public void getWIPProjList(Connection con){
        try {
            stmt = con.createStatement();
            String sql = "SELECT DISTINCT(proj_id),ePace_id,proj_name FROM projects WHERE project_status NOT IN (2,21,23) AND proj_id IN (SELECT CONCAT(proj_id,'') FROM estimate) AND proj_id NOT IN (SELECT concat(proj_id,'') FROM invoices WHERE part_invoice_flag=0 AND invoice_display_date <= '"+toDate+"')";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                temp_val = "";
                temp_val = rs.getString(1);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                wip_proj_id_list.add(temp_val);
                temp_val = "";
                temp_val = rs.getString(2);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                epaceId.add(temp_val);
                temp_val = "";
                temp_val = rs.getString(3);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                projName.add(temp_val);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Exception on WIP Report:" + e.toString());
        }
    }

    public void getInvoiceDetails(Connection con) {
        try {
            stmt = con.createStatement();
            String sql = "SELECT invoice_number_disp,part_no,date_format(invoice_display_date,'%m-%d-%Y'),invoice_value,proj_id,part_invoice_flag FROM invoices where part_invoice_flag <> '2' and proj_id = '"+proj_id+"' ORDER BY proj_id,part_no";
            //System.out.println("InvoiceDetails For WIP :" + sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                temp_val = "";
                temp_val = rs.getString(1);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                invoice_number_list.add(temp_val);
                temp_val = "";
                temp_val = rs.getString(2);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                part_no_list.add(temp_val);
                temp_val = "";
                temp_val = rs.getString(3);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                invoice_date_list.add(temp_val);
                temp_val = "";
                temp_val = rs.getString(4);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                invoice_value_list.add(temp_val);
                temp_val = "";
                temp_val = rs.getString(5);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                proj_id_list.add(temp_val);
                temp_val = "";
                temp_val = rs.getString(6);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                invoice_flag_list.add(temp_val);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Exception on WIP Report:" + e.toString());
        }
    }
    
    public void getPartialInvoiceLineItems() {

        try {
            con = conProp.getSampleProperty();
            getPartialInvoiceLineItems(con);
            //con.close();
        }catch(Exception e){
            System.out.println("Error while getting invoice partial total : "+e.toString());
        }
    }

    public void getPartialInvoiceLineItems(Connection con) {

        try {
            stmt = con.createStatement();
            String sql = "SELECT trim(li.item_id), sum(li.total), sum(li.quantity), li.category_id, li.rate,li.est_lineitem_id, li.description,e.po_number FROM invoices i, invoice_lineitems li  LEFT JOIN estimate_lineitems e ON li.est_lineitem_id = e.est_lineitem_id  "
                    + " WHERE i.invoice_number=li.invoice_number "
                    + " AND part_invoice_flag NOT IN (0,2) "
                    + " AND i.proj_id='" + proj_id + "' "
                    //+ "GROUP BY li.item_id "
                    //+ "ORDER BY li.item_id";
                    //+ "GROUP BY li.est_lineitem_id "
                    + " GROUP BY li.item_id, li.category_id, li.rate, li.est_lineitem_id "
                    + " ORDER BY li.est_lineitem_id IS NOT NULL, e.sort_order IS NULL, e.sort_order";
           // System.out.println("Previously Invoiced Line Items for Final Invoice :" + sql);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                temp_val = "";
                temp_val = rs.getString(1);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_id.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(2);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_total.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(3);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_quantity.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(4);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                category_id.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(5);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_rate.add(temp_val);
                temp_val = "";
                temp_val = rs.getString(6);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                est_line_item_id.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(7);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_desc.add(temp_val);

                temp_val = "";
                temp_val = rs.getString(8);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                line_item_num.add(temp_val);
            }
            rs.close();
            stmt.close();
            //System.out.println("invoiceQtyMap:"+invoiceQty);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();

        }
    }
}
