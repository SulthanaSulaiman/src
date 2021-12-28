/*
 * InvoiceInfo.java
 *
 * Created on March 5, 2010, 2:32 PM
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
public class InvoiceInfo {
// to get the detail of the invoice generated for the selected projcet id and invoice number.    
    connection.DBconnection conProp = new connection.DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String temp_val = "";
    
    private String invoice_number = "";
    
    private String proj_id = "";
    private String proj_name = "";
    private String currency_id = "";
    private String currency_name= "";
    private String currency_shortName= "";
    private String currency_symbol= "";
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
    private String invoice_value = "";
    private String part_flag="";
    private String part_no="";
    private String invoice_display_date="";
    private String invoice_display_date_in_pdf ="";
    private String termsConditions = "";
    private String invoice_by = "";
    private String pay_name = "";
    private String pay_id = "";
    private String buyer_type="";
    private String seller_type="";
    private String getBuyerMappingParam="";
    private String getBuyerParam="";
    
    public String getProjId(){
        return proj_id;
    }

    public String getPartFlag(){
        return part_flag;
    }

        public String getPartNo(){
        return part_no;
    }

    public String getInvoiceDisplayDate() {
        return invoice_display_date;
    }

    public String getInvoiceDisplayDateInPDF() {
        return invoice_display_date_in_pdf;
    }

    public String getProjName(){
        return proj_name;
    }
    public String getCurrencyId(){
        return currency_id;
    }
    public String getCurrencyName(){
        return currency_name;
    }
    public String getCurrencyShortName(){
        return currency_shortName;
    }

    public String getCurrencySymbol() {
        return currency_symbol;
    }

    public String getTermsID(){
        return terms_id;
    }
    public String getTermsName(){
        return terms_name;
    }
    public String getStatusID(){
        return status_id;
    }
    public String getStatusName(){
        return status_name;
    }
    public String getBuyerID(){
        return buyer_id;
    }
    public String getBuyerName(){
        return buyer_name;
    }
    public String getBuyerAdd(){
        return buyer_add;
    }
    public String getBuyerCity(){
        return buyer_city;
    }
    public String getBuyerState(){
        return buyer_state;
    }
    public String getBuyerZip(){
        return buyer_zip;
    }
    public String getBuyerCountry(){
        return buyer_country;
    }
    public String getBuyerPhone(){
        return buyer_phone;
    }
    public String getBuyerFax(){
        return buyer_fax;
    }
    public String getInvoiceValue(){
        return invoice_value;
    }
    public String getInvoiceBy(){
        return invoice_by;
    }
    public String getInvoicePay(){
        return pay_name;
    }
    public String getInvoicePayId(){
        return pay_id;
    }
    
    private List item_id = new ArrayList();
    private List item_name = new ArrayList();
    private List item_rate = new ArrayList();
    private List item_quantity = new ArrayList();
    private List item_desc = new ArrayList();
    private List item_value = new ArrayList();
    
    public List getItemId(){
        return item_id;
    }
    public List getItemName(){
        return item_name;
    }
    public List getItemRate(){
        return item_rate;
    }
    public List getItemQuantity(){
        return item_quantity;
    }
    public List getItemDesc(){
        return item_desc;
    }

     public String getBuyerType() {

        return buyer_type;
    }
    public List getItemValue(){
        return item_value;
    }

    public String getTermsConditions()
    {
        return termsConditions;
    }
    private List tax_id = new ArrayList();
    private List tax_name = new ArrayList();
    private List tax_value = new ArrayList();
    private List tax_total = new ArrayList();
    
    
    public List getTaxId(){
        return tax_id;
    }
    public List getTaxName(){
        return tax_name;
    }
    public List getTaxValue(){
        return tax_value;
    }
    public List getTaxTotal(){
        return tax_total;
    }
    
    public void setInvoiceNumber(String invoice_number){
        //System.out.println("invoice_number setting in invoiceInfo pathfinder.functions.revenue : "+invoice_number);
        this.invoice_number = invoice_number;
    }
    public void getInvoiceInfo(){
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try {

             con = conProp.getSampleProperty();
            stmt = con.createStatement();

         rs = stmt.executeQuery("select buyer_type from invoices where invoice_number = '" + invoice_number + "'");

           //  System.out.println("invoice_number : "+invoice_number);
            while (rs.next()) {
                buyer_type = rs.getString(1);
                if (rs.wasNull()) {
                    buyer_type = "";
                }

            }

            // System.out.println("buyer_type : "+buyer_type);
            

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


           
            sql_select = "select inv.proj_id,p.proj_name ";
            sql_from = "from invoices inv, projects p ";
            sql_where = "inv.proj_id = p.proj_id ";
                        
            if(!invoice_number.equals("")){
                sql_where+="and inv.invoice_number ='"+invoice_number+"' ";
            }
            
            sql_select +=sql_from+"where "+sql_where;
           // System.out.println("sql_select for display table 1 in invoiceInfo.java of pathfinder.functions.revenue : "+sql_select);
            
            rs = stmt.executeQuery(sql_select);
            
            if(rs.next()){                
                rs = stmt.executeQuery(sql_select);
                while(rs.next()){                
                    temp_val = rs.getString(1);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    proj_id = temp_val;
                
                    temp_val = rs.getString(2);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    proj_name = temp_val;
                }
            }else{
                proj_id = "";
                proj_name = "";
            }
            
            sql_select = "select inv.invoice_currency,cur.long_name,cur.short_name,cur.symbol ";
            sql_from = "from invoices inv, currency cur ";
            sql_where = "cur.currency_id = inv.invoice_currency ";
                        
            if(!invoice_number.equals("")){
                sql_where+="and inv.invoice_number ='"+invoice_number+"' ";
            }
            
            sql_select +=sql_from+"where "+sql_where;
           // System.out.println("sql_select for display table 1 in invoiceInfo.java of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            
            if(rs.next()){
                rs = stmt.executeQuery(sql_select);
                while(rs.next()){
                    
                    temp_val = rs.getString(1);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    currency_id = temp_val;
                    
                    temp_val = rs.getString(2);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    currency_name = temp_val;
                    
                    temp_val = rs.getString(3);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    currency_shortName = temp_val;

                    temp_val = rs.getString(4);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    currency_symbol = temp_val;
                }
            }else{
                currency_id = "";
                currency_name = "";
                currency_shortName = "";
            }
            
            sql_select = "select inv.terms_and_condition,tcm.name ";
            sql_from = "from invoices inv, t_and_c_master tcm ";
            sql_where = "tcm.t_and_c_id = inv.terms_and_condition ";
                        
            if(!invoice_number.equals("")){
                sql_where+="and inv.invoice_number ='"+invoice_number+"' ";
            }
            
            sql_select +=sql_from+"where "+sql_where;
           // System.out.println("sql_select for display table 1 in invoiceInfo.java of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            
            if(rs.next()){
                rs = stmt.executeQuery(sql_select);
                while(rs.next()){
                
                    temp_val = rs.getString(1);                
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    terms_id = temp_val;
                
                    temp_val = rs.getString(2);                
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    terms_name = temp_val;                
                }
            }else{
                terms_id = "";
                terms_name = "";
            }
            sql_select = "select inv.status,s.status ";
            sql_from = "from invoices inv, status s ";
            sql_where = "inv.status = s.status_id ";
                        
            if(!invoice_number.equals("")){
                sql_where+="and inv.invoice_number ='"+invoice_number+"' ";
            }
            
            sql_select +=sql_from+"where "+sql_where;
          //  System.out.println("sql_select for display table 1 in invoiceInfo.java of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            
            if(rs.next()){
                
                rs = stmt.executeQuery(sql_select);
                while(rs.next()){
                                
                    temp_val = rs.getString(1);                
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    status_id = temp_val;
                
                    temp_val = rs.getString(2);                
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    status_name = temp_val;
                }
            }else{
                status_id = "";
                status_name = "";
            }
            
            sql_select = "select inv.mode_of_payment,pmm.name ";
            sql_from = "from invoices inv,payment_modes_master pmm ";
            sql_where = "pmm.mode = inv.mode_of_payment ";
                        
            if(!invoice_number.equals("")){
                sql_where+="and inv.invoice_number ='"+invoice_number+"' ";
            }
            
            sql_select +=sql_from+"where "+sql_where;
          //  System.out.println("sql_select for display table 1 in invoiceInfo.java of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            
            if(rs.next()){
                
                rs = stmt.executeQuery(sql_select);
                while(rs.next()){
                
                    temp_val = rs.getString(1);                
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    pay_id = temp_val;
                                
                    temp_val = rs.getString(2);                
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    pay_name = temp_val;
                }
            }else{
                pay_id = "";
                pay_name = "";
            }
            
             if (!getBuyerMappingParam.equals("") && !buyer_type.equals("")) {
//System.out.println("ifbuyer");
                String isPerson = "";
                if(buyer_type.equals("contacts"))
                {
                    sql_select = "select concat(firstname,' ', surname),concat(address_1,',', address_2),city,state,zipcode,country,concat(phone_primary,'(primary), ', phone_secondary,'(secondary)'),concat(fax1,', ', fax2,', ',fax3),'contacts',c.contact_id,company,c.is_person ";
                    sql_from = "from contacts c, contacttype_map cm, contacts_type_master ctm,invoices po ";
                    sql_where = "c.contact_id = cm.contact_id and cm.type_id = ctm.type_id and cm.type_id = 3 AND (c.is_person='1' OR c.is_person='2') and c.contact_id=po.buyer_id ";

                }
                else
                {
                sql_select = "select bt." + getBuyerParam + ",po.shipping_address,po.shipping_city,po.shipping_state,po.shipping_zipcode,po.shipping_country,po.shipping_phone,po.shipping_fax,po.buyer_type,po.buyer_id ";
                sql_from = "from invoices po," + buyer_type + " bt ";
                sql_where = "bt." + getBuyerMappingParam + " = po.buyer_id ";



                }
            if(!invoice_number.equals("")){
                sql_where+="and po.invoice_number ='"+invoice_number+"' ";
            }
            
            sql_select +=sql_from+"where "+sql_where;
            //System.out.println("sql_select for display table 1 in invoiceInfo.java of pathfinder.functions.revenue : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            
            if(rs.next()){
                
                rs = stmt.executeQuery(sql_select);                
                while(rs.next()){


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
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    buyer_add = temp_val;
                    
                    temp_val = rs.getString(3);                
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    buyer_city = temp_val;
                    
                    temp_val = rs.getString(4);                
                    if(rs.wasNull()){
                        temp_val = "";                        
                    }
                    buyer_state = temp_val;
                    
                    temp_val = rs.getString(5);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    buyer_zip = temp_val;
                    
                    temp_val = rs.getString(6);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    buyer_country = temp_val;
                    
                    temp_val = rs.getString(7);                
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    buyer_phone = temp_val;
                    
                    temp_val = rs.getString(8);                
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    buyer_fax = temp_val;
                    
                    temp_val = rs.getString(9);                
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    buyer_id = temp_val;                
                }
            }else {                
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
             }
            
            sql_from = "";
            sql_where = "";
            
            sql_select = "select inv.invoice_value,inv.invoice_by,inv.part_invoice_flag,inv.part_no,inv.invoice_display_date,date_format(inv.invoice_display_date,'%d-%b-%Y'),inv.terms_and_condition ";
            sql_from = "from invoices inv ";
            
            if(!invoice_number.equals("")){
                if(!sql_where.equals("")){;
                sql_where+="and ";
                }
                sql_where+="inv.invoice_number ='"+invoice_number+"' ";
            }
            sql_select +=sql_from+"where "+sql_where;
            //System.out.println("sql_select for display table 1 in invoiceInfo.java of pathfinder.functions.revenue.invoiceInfo.java : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            if(rs.next()){
                rs = stmt.executeQuery(sql_select);
                while(rs.next()){                
                    temp_val = rs.getString(1);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    invoice_value = temp_val;
                    
                    temp_val = rs.getString(2);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    invoice_by = temp_val;

                    temp_val = rs.getString(3);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    part_flag = temp_val;

                    temp_val = rs.getString(4);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    part_no = temp_val;

                    temp_val = rs.getString(5);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    invoice_display_date = temp_val;

                    temp_val = rs.getString(6);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    invoice_display_date_in_pdf = temp_val;

                    temp_val = rs.getString(7);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    termsConditions = temp_val;
                }                
            }else{                
                invoice_value = "";
            }
            
            sql_select = "";
            sql_from = "";
            sql_where = "";
            
            sql_select = "select ilt.item_id,blm.name,ilt.rate,ilt.quantity,ilt.description,ilt.total ";
            sql_from = "from billing_lineitems_master blm, invoice_lineitems ilt ";
            sql_where = "blm.billing_item_id = ilt.item_id ";
            
            if(!invoice_number.equals("")){
                sql_where+="and ilt.invoice_number ='"+invoice_number+"' ";
            }
            sql_select += sql_from+"where "+sql_where;
            
            //System.out.println("sql_select for display table 2 in POInfo.java of pathfinder.functions.revenue.EstimationInfo.java : "+sql_select);
            
            rs = stmt.executeQuery(sql_select);
            item_id.clear();
            item_name.clear();
            item_rate.clear();
            item_quantity.clear();
            item_desc.clear();
            item_value.clear();
            
            while(rs.next()){
                temp_val = "";
                temp_val = rs.getString(1);
                if(rs.wasNull()){
                    temp_val = "";
                }
                item_id.add(temp_val);
                
                temp_val = "";
                temp_val = rs.getString(2);
                if(rs.wasNull()){
                    temp_val = "";
                }
                item_name.add(temp_val);
                
                temp_val = "";
                temp_val = rs.getString(3);
                if(rs.wasNull()){
                    temp_val = "";
                }
                item_rate.add(temp_val);
                
                temp_val = "";
                temp_val = rs.getString(4);
                if(rs.wasNull()){
                    temp_val = "";
                }
                item_quantity.add(temp_val);
                
                temp_val = "";
                temp_val = rs.getString(5);
                if(rs.wasNull()){
                    temp_val = "";
                }
                item_desc.add(temp_val);
                
                temp_val = "";
                temp_val = rs.getString(6);
                if(rs.wasNull()){
                    temp_val = "";
                }
                item_value.add(temp_val);
            }
            
            sql_select = "";
            sql_from = "";
            sql_where = "";
            
            sql_select = "select it.tax_id,taa.name,it.value,it.total ";
            sql_from = "from tax_and_addons taa, invoice_taxes it ";
            sql_where = "taa.tax_id = it.tax_id ";
                      
            if(!invoice_number.equals("")){
                sql_where+="and it.invoice_number ='"+invoice_number+"' ";
            }
            sql_select += sql_from+"where "+sql_where;
            //System.out.println("sql_select for display table 2 in POInfo.java of pathfinder.functions.revenue : "+sql_select);
            
            rs = stmt.executeQuery(sql_select);
            tax_id.clear();
            tax_name.clear();
            tax_value.clear();
            tax_total.clear();
            
            while(rs.next()){
                
                temp_val = "";
                temp_val = rs.getString(1);
                if(rs.wasNull()){
                    temp_val = "";
                }
                tax_id.add(temp_val);
                                
                temp_val = "";
                temp_val = rs.getString(2);
                if(rs.wasNull()){
                    temp_val = "";
                }
                tax_name.add(temp_val);
                                
                temp_val = "";
                temp_val = rs.getString(3);
                if(rs.wasNull()){
                    temp_val = "";
                }
                tax_value.add(temp_val);
                
                temp_val = "";
                temp_val = rs.getString(4);
                if(rs.wasNull()){
                    temp_val = "";
                }
                tax_total.add(temp_val);               
            }
            
            sql_select = "";
            sql_from = "";
            sql_where = "";
            
            sql_select = "select il.item_id,blm.name,il.rate,il.quantity,il.description,il.total ";
            sql_from = "from billing_lineitems_master blm, invoice_lineitems il ";
            sql_where = "blm.billing_item_id = il.item_id ";
                        
            if(!invoice_number.equals("")){
                sql_where+="and il.invoice_number ='"+invoice_number+"' ";
            }
            sql_select += sql_from+"where "+sql_where;
            //System.out.println("sql_select for display table 3 in POInfo.java of pathfinder.functions.revenue : "+sql_select);
            
            rs = stmt.executeQuery(sql_select);
            item_id.clear();
            item_name.clear();
            item_rate.clear();
            item_quantity.clear();
            item_desc.clear();
            item_value.clear();
            while(rs.next()){
                temp_val = "";
                temp_val = rs.getString(1);
                if(rs.wasNull()){
                    temp_val = "";
                }
                item_id.add(temp_val);
                                
                temp_val = "";
                temp_val = rs.getString(2);
                if(rs.wasNull()){
                    temp_val = "";
                }
                item_name.add(temp_val);
                                
                temp_val = "";
                temp_val = rs.getString(3);
                if(rs.wasNull()){
                    temp_val = "";
                }
                item_rate.add(temp_val);
                
                temp_val = "";
                temp_val = rs.getString(4);
                if(rs.wasNull()){
                    temp_val = "";
                }
                item_quantity.add(temp_val);
                                
                temp_val = "";
                temp_val = rs.getString(5);
                if(rs.wasNull()){
                    temp_val = "";
                }
                item_desc.add(temp_val);
                
                temp_val = "";
                temp_val = rs.getString(6);
                if(rs.wasNull()){
                    temp_val = "";
                }
                item_value.add(temp_val);
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }    
    
}
