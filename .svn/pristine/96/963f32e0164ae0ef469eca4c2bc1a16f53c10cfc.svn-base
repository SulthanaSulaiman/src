/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import connection.DBconnection;
import java.sql.*;
import java.util.*;

import java.io.Serializable;

public class OpenInvoicesDetailsReport implements Serializable {
    
   
    DBconnection connection = new DBconnection();
    Connection con = null;
    java.sql.Statement stmt = null;
    ResultSet rs = null;
    private String queryData="";
    
    private List invoiceNumberList = new ArrayList();
    private List invoiceIdList = new ArrayList();
    private List invoiceDateList = new ArrayList();
    private List invoiceProjList = new ArrayList();
    private List invoiceValueList = new ArrayList();
    private List invoicePartNoList = new ArrayList();
    private List invoiceClosedDateList = new ArrayList();

    public List getInvoiceClosedDateList() {
        return invoiceClosedDateList;
    }

    public void setInvoiceClosedDateList(List invoiceClosedDateList) {
        this.invoiceClosedDateList = invoiceClosedDateList;
    }
  
    
    public List getInvoiceDateList() {
        return invoiceDateList;
    }

    public void setInvoiceDateList(List invoiceDateList) {
        this.invoiceDateList = invoiceDateList;
    }

    public List getInvoiceIdList() {
        return invoiceIdList;
    }

    public void setInvoiceIdList(List invoiceIdList) {
        this.invoiceIdList = invoiceIdList;
    }

    public List getInvoiceNumberList() {
        return invoiceNumberList;
    }

    public void setInvoiceNumberList(List invoiceNumberList) {
        this.invoiceNumberList = invoiceNumberList;
    }

    public List getInvoicePartNoList() {
        return invoicePartNoList;
    }

    public void setInvoicePartNoList(List invoicePartNoList) {
        this.invoicePartNoList = invoicePartNoList;
    }

    public List getInvoiceProjList() {
        return invoiceProjList;
    }

    public void setInvoiceProjList(List invoiceProjList) {
        this.invoiceProjList = invoiceProjList;
    }

    public List getInvoiceValueList() {
        return invoiceValueList;
    }

    public void setInvoiceValueList(List invoiceValueList) {
        this.invoiceValueList = invoiceValueList;
    }
    
//    
  
    
   /* private List invoiceNumberList = new ArrayList();
    private List invoiceNumberList = new ArrayList();*/
    
    public OpenInvoicesDetailsReport() {
       
    }
    
    public void collectOpenInvDetails(){
        try{
           con = connection.getSampleProperty();
            stmt = con.createStatement();
          
            
             String sql_select="select date_format(inv.invoice_date,'%d/%m/%y'),inv.invoice_number,"
                     + "inv.invoice_id,pr.proj_name,inv.invoice_value,inv.part_no,date_format(inv.payment_received_date,'%d/%m/%y') "
                    + "from invoices inv,projects pr ";
                   
            String  sql_where = "where inv.proj_id=pr.proj_id and inv.payment_received_flag=0";
            
            sql_select=sql_select+sql_where;
            ResultSet rsCollectDetails = stmt.executeQuery(sql_select);
            while(rsCollectDetails.next()){
                      queryData = rsCollectDetails.getString("date_format(inv.invoice_date,'%d/%m/%y')");                
                //if(!departmentNameList.contains(queryData)){
                    invoiceDateList.add(queryData);
                    invoiceNumberList.add(rsCollectDetails.getString("inv.invoice_number"));
                    invoiceIdList.add(rsCollectDetails.getString("inv.invoice_id"));
                    invoiceProjList.add(rsCollectDetails.getString("pr.proj_name"));
                    invoiceValueList.add(rsCollectDetails.getString("inv.invoice_value"));
                    queryData = rsCollectDetails.getString("inv.part_no");    
                    if(rsCollectDetails.wasNull()){
                        queryData="";
                    }
                    invoicePartNoList.add(queryData); 
                    
                    queryData = rsCollectDetails.getString("date_format(inv.payment_received_date,'%d/%m/%y')");    
                    if(rsCollectDetails.wasNull()){
                        queryData="";
                    }
                                       
                    invoiceClosedDateList.add(queryData);
            }
            rsCollectDetails.close();
            stmt.close();
            con.close();
            
             }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }
   
   
 
    
}
