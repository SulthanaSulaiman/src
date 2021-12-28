/*
 * InvoiceSummaryInfo.java
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
public class InvoiceSummaryInfo {
// to get the summary details of the invoice for the selected project and invoice number
    connection.DBconnection conProp = new connection.DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String temp_val = "";
    
    private List invoice_number = new ArrayList();
    private List invoice_proj_id = new ArrayList();
    private List invoice_proj_name = new ArrayList();
    private List invoice_date = new ArrayList();
    private List invoice_cur_name = new ArrayList();
    private List invoice_cur_symbol = new ArrayList();
    private List invoice_value = new ArrayList();
    private List part_flag = new ArrayList();
    private List part_no = new ArrayList();
    private List proj_inv_disp_num = new ArrayList();
    private List termsAndCondition = new ArrayList();
    private List invoice_status = new ArrayList();
    private List invoice_buyer = new ArrayList();
    private List invoice_seller = new ArrayList();
    
    private String invoice_num = "";
    private String proj_id = "";
    private String from_date = "";
    private String to_date = "";
    private String buyer = "";
    private String value = "";
    private String equality = "";
    private String around = "";
    private String terms_condition = "";

     public void setEqualityAround(String around){
        this.around = around;
    }
    
    public void setInvoiceNumber(String invoice_num){
        this.invoice_num = invoice_num;
    }
    
    public void setProjID(String proj_id){
        this.proj_id = proj_id;
    }
    
    public void setFromDate(String from_date){
        this.from_date = from_date;
    }
    
    public void setToDate(String to_date){
        this.to_date = to_date;
    }
    
    public void setBuyer(String buyer){
        this.buyer = buyer;
    }
    
    public void setValue(String value){
        this.value = value;
    }
    
    public void setEquality(String equality){
        this.equality = equality;
    }
    
    public List getInvoiceNumber(){
        //System.out.println("invoice_number in invoiceSummary of pathfinder.functions.revenue.InvoiceSummaryInfo : "+invoice_number);
        return invoice_number;
    }
    
    public List getInvoiceProjID(){
        return invoice_proj_id;
    }
    
    public List getInvoiceProjName(){
        return invoice_proj_name;
    }
    
    public List getInvoiceDate(){
        return invoice_date;
    }
    
    public List getInvoiceValue(){
        return invoice_value;
    }

    public List getPartFlag(){
        return part_flag;
    }

    public List getPartNo(){
        return part_no;
    }

    public List getProjInvDispNum() {
        return proj_inv_disp_num;
    }

    public List getInvoiceCurName(){
        return invoice_cur_name;
    }

    public List getInvoiceCurSymbol() {
        return invoice_cur_symbol;
    }

    public List getInvoiceStatus(){
        return invoice_status;
    }

    public List getInvoiceBuyer()
    {
        return invoice_buyer;
    }

    public List getInvoiceSeller()
    {
        return invoice_seller;
    }
    
    public void getInvoiceSummary() {
        
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            sql_select = "";
            sql_from = "";
            sql_where = "";
            
            sql_select = "select inv.invoice_number, inv.invoice_date," +
                    "date_format(inv.invoice_date,'%d-%b-%Y'),inv.invoice_value,inv.part_invoice_flag,inv.part_no,inv.invoice_number_disp,inv.terms_and_condition ";
            sql_from = "from invoices inv ";
            
            if(!proj_id.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="inv.proj_id = '"+proj_id+"' ";
            }
            
            if(!from_date.equals("") && !to_date.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="date(inv.invoice_date) between '"+from_date+"' and '"+to_date+"' ";
            }

             if(!from_date.equals("") && to_date.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="date(inv.invoice_date) between '"+from_date+"' and CURRENT_DATE ";
            }

            if(from_date.equals("") && !to_date.equals("")){
              if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="date(inv.invoice_date)<='"+to_date+"' ";
            }
            
            if(!invoice_num.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="inv.invoice_number = '"+invoice_num+"' ";
            }
            
            if(!buyer.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="inv.buyer_id = '"+buyer+"' ";
            }
            
            if(!value.equals("") && !equality.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }

                 if(equality.equals("between"))
                {
                    sql_where +="inv.invoice_value  "+equality+" '"+value+"' and '"+around+"' ";
                }
                else
                sql_where +="inv.invoice_value "+equality+" '"+value+"' ";
            }
            
            sql_select+=sql_from;
            
            if(!sql_where.equals("")){
                sql_select +="where "+sql_where;
            }

             sql_select = sql_select + " Order By inv.invoice_number";
            
            //System.out.println("sql_select in invoiceSummary of pathfinder.functions.revenue.InvoiceSummaryInfo : "+sql_select);
            
            rs = stmt.executeQuery(sql_select);
            invoice_number.clear();
            invoice_date.clear();
            invoice_value.clear();
            if(rs.next()){
                rs = stmt.executeQuery(sql_select);
                
                while(rs.next()){
                    temp_val = "";
                    temp_val = rs.getString(1);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    invoice_number.add(temp_val);
                    
                    temp_val = "";
                    temp_val = rs.getString(3);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    invoice_date.add(temp_val);
                    
                    temp_val = "";
                    temp_val = rs.getString(4);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    invoice_value.add(temp_val);

                    temp_val = "";
                    temp_val = rs.getString(5);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    part_flag.add(temp_val);


                    temp_val = "";
                    temp_val = rs.getString(6);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    part_no.add(temp_val);

                    temp_val = "";
                    temp_val = rs.getString(7);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    proj_inv_disp_num.add(temp_val);

                    temp_val = "";
                    temp_val = rs.getString(8);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    termsAndCondition.add(temp_val);
                }
            }/*else {
                invoice_number.add("");
                invoice_date.add("");
                invoice_value.add("");
            }    */
            
            invoice_proj_id.clear();
            invoice_proj_name.clear();
            invoice_status.clear();
            
            InvoiceInfo invoiceInfo = new InvoiceInfo();
            
            for(int loop=0;loop<invoice_number.size();loop++){
                invoiceInfo.setInvoiceNumber(invoice_number.get(loop).toString());
                invoiceInfo.getInvoiceInfo();
                
                //System.out.println("po_number : "+invoice_number.get(loop));
                //System.out.println("getProjId : "+invoiceInfo.getProjId());
                //System.out.println("getProjName : "+invoiceInfo.getProjName());
                //System.out.println("getCurrencyShortName : "+invoiceInfo.getCurrencyShortName());
                //System.out.println("getStatusName : "+invoiceInfo.getStatusName());
                
                invoice_proj_id.add(invoiceInfo.getProjId());
                invoice_proj_name.add(invoiceInfo.getProjName());
                invoice_cur_name.add(invoiceInfo.getCurrencyShortName());
                invoice_cur_symbol.add(invoiceInfo.getCurrencySymbol());
                invoice_status.add(invoiceInfo.getStatusName());
                invoice_buyer.add(invoiceInfo.getBuyerName());
                invoice_seller.add(invoiceInfo.getStatusName());
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
