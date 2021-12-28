/*
 * BillingSummaryInfo.java
 *
 * Created on March 3, 2010, 3:33 PM
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
public class BillingSummaryInfo implements Serializable {
// to get the summary informations required for the billing summary,
// the values are listed down from BillingInfo.java
   
    connection.DBconnection conProp = new connection.DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String temp_val = "";
    
    private List bill_number = new ArrayList();
    private List bill_proj_id = new ArrayList();
    private List bill_proj_name = new ArrayList();
    private List bill_date = new ArrayList();
    private List bill_value = new ArrayList();
    private List bill_status = new ArrayList();
    
    private String bill_num = "";
    private String proj_id = "";
    private String from_date = "";
    private String to_date = "";
    private String value = "";
    private String equality = "";
    private String billStatus="";
    private String searchKey = "";
    private String status = "";
    private String around = "";
     String isSuggest="";

      public void setIsSuggest(String isSuggest)
    {
        this.isSuggest = isSuggest;
    }


     public void setEqualityAround(String around){
        this.around = around;
    }


     public void setStatus(String status)
    {
        this.status=status;
    }

     public void setSearchKey(String searchKey){
        this.searchKey = searchKey;
    }
    

    public void setBillStatus(String billStatus){
        this.billStatus = billStatus;
    }

    public void setBillNumber(String bill_num){
        this.bill_num = bill_num;
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
    
    public void setValue(String value){
        this.value = value;
    }
    
    public void setEquality(String equality){
        this.equality = equality;
    }
    
    public List getBillNumber(){
        //System.out.println("bill_number in billSummary of pathfinder.functions.revenue.BillingSummaryInfo : "+bill_number);
        return bill_number;
    }
    
    public List getBillProjID(){
        return bill_proj_id;
    }
    
    public List getBillProjName(){
        return bill_proj_name;
    }
    
    public List getBillDate(){
        return bill_date;
    }
    
    public List getBillValue(){
        return bill_value;
    }
    
    public List getBillStatus(){
        return bill_status;
    }
    
    public void getBillSummary() {
        
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            sql_select = "";
            sql_from = "";
            sql_where = "";
            
            sql_select = "select bill.bill_number, bill.bill_date," +
                    "date_format(bill.bill_date,'%d-%b-%Y'),bill.bill_value ";
            sql_from = "from billing bill ";
            
            if(!proj_id.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="bill.proj_id = '"+proj_id+"' ";
            }
            
            if(!from_date.equals("") && !to_date.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="date(bill.bill_date) between '"+from_date+"' and '"+to_date+"' ";
            }

              if(!from_date.equals("") && to_date.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="date(bill.bill_date) between '"+from_date+"' and CURRENT_DATE ";
            }

            if(from_date.equals("") && !to_date.equals("")){
              if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="date(bill.bill_date)<='"+to_date+"' ";
            }
            


             if(!searchKey.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="bill.bill_num LIKE '"+searchKey+"%' ";
            }


            if(!status.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                 sql_where +="bill.status = '"+status+"' ";
            }
            
            if(!bill_num.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="bill.bill_number = '"+bill_num+"' ";
            }
            
            if(!value.equals("") && !equality.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                 if(equality.equals("between"))
                {
                    sql_where +="bill.bill_value "+equality+" '"+value+"' and '"+around+"' ";
                }
                else
                   sql_where +="bill.bill_value "+equality+" '"+value+"' ";
            }

            if(!billStatus.equals("") && !billStatus.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }

                sql_where +="bill.status = '"+billStatus+"' ";
            }
            
            sql_select+=sql_from;
            
            if(!sql_where.equals("")){
                sql_select +="where "+sql_where;
            }

             sql_select = sql_select + " Order By bill.bill_date DESC";
             if(isSuggest.equals("1"))
        {
            sql_select = sql_select + " LIMIT 10";
        }
            //System.out.println("sql_select in billSummary of pathfinder.functions.revenue.BillingSummaryInfo : "+sql_select);
            
            rs = stmt.executeQuery(sql_select);
            bill_number.clear();
            bill_date.clear();
            bill_value.clear();
            if(rs.next()){
                rs = stmt.executeQuery(sql_select);
                
                while(rs.next()){
                    temp_val = "";
                    temp_val = rs.getString(1);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    bill_number.add(temp_val);
                    
                    temp_val = "";
                    temp_val = rs.getString(3);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    bill_date.add(temp_val);
                    
                    temp_val = "";
                    temp_val = rs.getString(4);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    bill_value.add(temp_val);
                }
            }/*else {
                bill_number.add("");
                bill_date.add("");
                bill_value.add("");
            }     */
            
            bill_proj_id.clear();
            bill_proj_name.clear();
            bill_status.clear();
            
            BillingInfo billInfo = new BillingInfo();
// get the billing proj details and status forthe collected bill number.            
            for(int loop=0;loop<bill_number.size();loop++){
                billInfo.setBillNumber(bill_number.get(loop).toString());
                billInfo.getBillingInfo();
                
               // System.out.println("bill_number : "+bill_number.get(loop));
               // System.out.println("getProjId : "+billInfo.getProjId());
               // System.out.println("getProjName : "+billInfo.getProjName());
               // System.out.println("getStatusName : "+billInfo.getBillStatus());
                
                bill_proj_id.add(billInfo.getProjId());
                bill_proj_name.add(billInfo.getProjName());
                bill_status.add(billInfo.getBillStatus());
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
