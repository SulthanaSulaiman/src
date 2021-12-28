/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import connection.DBconnection;
import java.sql.*;
import java.util.*;

import java.io.Serializable;

public class OpenInvoicesDetails implements Serializable {
    
   
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
    private List invoiceDispNumList = new ArrayList();
    private List invoiceClosedDate = new ArrayList();
    private List invoicePartNoList = new ArrayList();
    private String invoiceClosedBy = "";

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

    public List getInvoiceDispNumList() {
        return invoiceDispNumList;
    }

    public void setInvoiceDispNumList(List invoiceDispNumList) {
        this.invoiceDispNumList = invoiceDispNumList;
    }

    public List getInvoiceClosedDate() {
        return invoiceClosedDate;
    }

    public void setInvoiceClosedDate(List invoiceClosedDate) {
        this.invoiceClosedDate = invoiceClosedDate;
    }
    
//    
    private List selectedInvoiceList = new ArrayList();
    private List selectedInvoiceDate = new ArrayList();

    public List getSelectedInvoiceDate() {
        return selectedInvoiceDate;
    }

    public void setSelectedInvoiceDate(List selectedInvoiceDate) {
        this.selectedInvoiceDate = selectedInvoiceDate;
    }

    public String getInvoiceClosedBy() {
        return invoiceClosedBy;
    }

    public void setInvoiceClosedBy(String invoiceClosedBy) {
        this.invoiceClosedBy = invoiceClosedBy;
    }

    public List getSelectedInvoiceList() {
        return selectedInvoiceList;
    }

    public void setSelectedInvoiceList(List selectedInvoiceList) {
        this.selectedInvoiceList = selectedInvoiceList;
    }
    
   /* private List invoiceNumberList = new ArrayList();
    private List invoiceNumberList = new ArrayList();*/
    
    public OpenInvoicesDetails() {
       
    }
    
    public void collectOpenInvDetails(){
        try{
           con = connection.getSampleProperty();
            stmt = con.createStatement();
          
            
             String sql_select="select date_format(inv.invoice_date,'%d/%m/%y'),inv.invoice_number,"
                     + "inv.invoice_id,pr.proj_name,inv.invoice_value,inv.part_no,inv.invoice_number_disp "
                    + "from invoices inv,projects pr ";
                   
            String  sql_where = "where inv.proj_id=pr.proj_id and inv.payment_received_flag=0 order by inv.invoice_date";
            
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
                    invoiceDispNumList.add(rsCollectDetails.getString("inv.invoice_number_disp"));
                    if(rsCollectDetails.wasNull()){
                        queryData="";
                    }
                    invoicePartNoList.add(queryData);                    
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

    public void collectClosedInvDetails(){
        try{
           con = connection.getSampleProperty();
            stmt = con.createStatement();


             String sql_select="select date_format(inv.invoice_date,'%d/%m/%y'),inv.invoice_number,inv.invoice_id,pr.proj_name,inv.invoice_value,"
                     + " inv.part_no,inv.invoice_number_disp,DATE_FORMAT(inv.payment_received_date,'%d/%m/%y') from invoices inv,projects pr  ";

            String  sql_where = "where inv.proj_id=pr.proj_id AND inv.payment_received_flag=1 order by inv.payment_received_date";

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
                    invoiceDispNumList.add(rsCollectDetails.getString("inv.invoice_number_disp"));
                    invoiceClosedDate.add(rsCollectDetails.getString("date_format(inv.payment_received_date,'%d/%m/%y')"));
                    if(rsCollectDetails.wasNull()){
                        queryData="";
                    }
                    invoicePartNoList.add(queryData);
            }

             }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }
   
    public int closeSelectedInvoice(){
        
          int chkInsert = 0;
            int returnInsert = 1;
        try{
           con = connection.getSampleProperty();
            stmt = con.createStatement();
            
            int totalInvSize = selectedInvoiceList.size();
            //System.out.println("totalInvSize:"+totalInvSize);
            String loopInvoiceId = "";
          
          for(int idx=0;idx<totalInvSize;idx++){
              //System.out.println("total for:"+idx);
              loopInvoiceId = selectedInvoiceList.get(idx).toString();
               //System.out.println("selectedInvoiceList:"+selectedInvoiceList.get(idx).toString());
               //System.out.println("update invoices set payment_received_flag='1', payment_received_date='"+selectedInvoiceDate.get(idx).toString()+"' where invoice_id='"+selectedInvoiceList.get(idx).toString()+"'");
              chkInsert=stmt.executeUpdate("update invoices set payment_received_flag='1',"
                      + " payment_received_date='"+selectedInvoiceDate.get(idx).toString()+"',invoice_closed_by='"+invoiceClosedBy+"' where invoice_id='"+selectedInvoiceList.get(idx).toString()+"' ");
              if(chkInsert<0){
                  returnInsert=0;
              }
          }
             }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
        
        return returnInsert;
    }
 
    
}
