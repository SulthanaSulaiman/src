/*
 * EstimateSummaryInfo.java
 *
 * Created on February 24, 2010, 5:52 PM
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
public class EstimateSummaryInfo implements Serializable{

// to get the summary info from the estimate table for the passed project id, 
// and other estimation details from EstimationInfo.java
    
    connection.DBconnection conProp = new connection.DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String temp_val = "";
    
    private List est_number = new ArrayList();
    private List est_proj_id = new ArrayList();
    private List est_proj_name = new ArrayList();
    private List est_date = new ArrayList();
    private List est_seller = new ArrayList();
    private List est_cur_name = new ArrayList();
    private List est_cur_symbol = new ArrayList();
    private List est_value = new ArrayList();
    private List est_process_flag = new ArrayList();
    private List est_num_disp = new ArrayList();
    private List est_status = new ArrayList();
    
    private String est_num = "";
    private String est_number_disp = "";
    private String proj_id = "";
    private String from_date = "";
    private String to_date = "";
    private String buyer = "";
    private String seller = "";
    private String value = "";
    private String equality = "";
    private String searchKey = "";
    private String status = "";
    private String around = "";
     String isSuggest="";

    public void setEqualityAround(String around){
        this.around = around;
    }

     public void setIsSuggest(String isSuggest)
    {
        this.isSuggest = isSuggest;
    }


    public void setEstNumber(String est_num){
        this.est_num = est_num;
    }

    public void setEstNumberDisp(String est_number_disp) {
        this.est_number_disp = est_number_disp;
    }

    public void setStatus(String status)
    {
        this.status=status;
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
    
    public void setSeller(String seller){
        this.seller = seller;
    }
    
    public void setValue(String value){
        this.value = value;
    }
    
    public void setEquality(String equality){
        this.equality = equality;
    }

    public void setSearchKey(String searchKey){
        this.searchKey = searchKey;
    }
    
    public List getEstNumber(){
        //System.out.println("est_number in poSummary of pathfinder.functions.revenue.EstSummaryInfo : "+est_number);
        return est_number;
    }
    
    public List getEstProjID(){
        return est_proj_id;
    }
    
    public List getEstProjName(){
        return est_proj_name;
    }
    
    public List getEstDate(){
        return est_date;
    }
    
    public List getEstValue(){
        return est_value;
    }

    public List getEstProcessFlag() {
        return est_process_flag;
    }

    public List getEstNumDisp() {
        return est_num_disp;
    }

    public List getEstCurName(){
        return est_cur_name;
    }

    public List getEstCurSymbol() {
        return est_cur_symbol;
    }

    public List getEstStatus(){
        return est_status;
    }
    
    public List getEstSeller(){
        return est_seller;
    }


    public void getEstSummary() {
        
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            sql_select = "";
            sql_from = "";
            sql_where = " WHERE est.status=s.status_id  ";
            
            sql_select = "select est.est_number, est.est_date," +
                    "date_format(est.est_date,'%d-%b-%Y'),est.est_value,est.est_process_flag,est.est_number_disp,p.proj_name, proj_id,s.status,cs.html_code ";
            sql_from = "from projects p LEFT JOIN estimate est USING (proj_id) LEFT JOIN currency_symbols cs USING(currency_code), STATUS s ";
            
            if(!proj_id.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="est.proj_id = '"+proj_id+"' ";
            }
            
            if(!from_date.equals("") && !to_date.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="date(est.est_date) between '"+from_date+"' and '"+to_date+"' ";
            }

              if(!from_date.equals("") && to_date.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="date(est.est_date) between '"+from_date+"' and CURRENT_DATE ";
            }

            if(from_date.equals("") && !to_date.equals("")){
              if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="date(est.est_date)<='"+to_date+"' ";
            }
            

             if(!searchKey.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="est.est_number_disp LIKE '"+searchKey+"%' ";
            }


            if(!status.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                 sql_where +="est.status = '"+status+"' ";
            }

            if(!est_num.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="est.est_number = '"+est_num+"' ";
            }
            
            if(!est_number_disp.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="est.est_number_disp = '"+est_number_disp+"' ";
            }

            if(!buyer.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="est.buyer_id = '"+buyer+"' ";
            }
            
            if(!seller.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="est.seller_id = '"+seller+"' ";
            }
            
            if(!value.equals("") && !equality.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                 if(equality.equals("between"))
                {
                    sql_where +="est.est_value "+equality+" '"+value+"' and '"+around+"' ";
                }
                else
                     sql_where +="est.est_value "+equality+" '"+value+"' ";
            }
            
            sql_select+=sql_from;
            
            if(!sql_where.equals("")){
                sql_select += sql_where;
            }

              sql_select = sql_select + " GROUP BY est.proj_id ORDER BY est.est_date DESC";

            if (isSuggest.equals("1")) {
                sql_select = sql_select + " LIMIT 10";
            }

            //System.out.println("estSummary : "+sql_select);
            
            rs = stmt.executeQuery(sql_select);
            est_number.clear();
            est_date.clear();
            est_value.clear();
            est_process_flag.clear();
            est_num_disp.clear();
            if(rs.next()){
                rs = stmt.executeQuery(sql_select);
                
                while(rs.next()){
                    temp_val = "";
                    temp_val = rs.getString(1);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    est_number.add(temp_val);
                    
                    temp_val = "";
                    temp_val = rs.getString(3);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    est_date.add(temp_val);
                    
                    temp_val = "";
                    temp_val = rs.getString(4);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    est_value.add(temp_val);

                    temp_val = "";
                    temp_val = rs.getString(5);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    est_process_flag.add(temp_val);

                    temp_val = "";
                    temp_val = rs.getString(6);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    est_num_disp.add(temp_val);
                    temp_val = rs.getString(7);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    est_proj_name.add(temp_val);
                    temp_val = rs.getString(8);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    est_proj_id.add(temp_val);
                    temp_val = rs.getString(9);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    est_status.add(temp_val);
                    temp_val = rs.getString(10);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    est_cur_name.add(temp_val);
                }
            }/*else {
                est_number.add("");
                est_date.add("");
                est_value.add("");
            }  */
            
//            est_proj_id.clear();
//            est_proj_name.clear();
//            est_status.clear();
//            est_seller.clear();
            
            //EstimationInfo estInfo = new EstimationInfo();
            
            /*for(int loop=0;loop<est_number.size();loop++){
                estInfo.setEstNumber(est_number.get(loop).toString());
                estInfo.getEstInfo();
                
                //System.out.println("po_number : "+est_number.get(loop));
                //System.out.println("getProjId : "+estInfo.getProjId());
                //System.out.println("getProjName : "+estInfo.getProjName());
                //System.out.println("getCurrencyShortName : "+estInfo.getCurrencyShortName());
                //System.out.println("getStatusName : "+estInfo.getStatusName());
                //System.out.println("getSellerName : "+estInfo.getSellerName());
                
                est_proj_id.add(estInfo.getProjId());
                est_proj_name.add(estInfo.getProjName());
                est_cur_name.add(estInfo.getCurrencyShortName());
                est_cur_symbol.add(estInfo.getCurrencySymbol());
                est_status.add(estInfo.getStatusName());
                est_seller.add(estInfo.getSellerName());
            }*/
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
