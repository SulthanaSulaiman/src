/*
 * POSummaryInfo.java
 *
 * Created on February 18, 2010, 2:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
/**
 *
 * @author ramyamaims
 */
public class POSummaryInfo implements Serializable{

    connection.DBconnection conProp = new connection.DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String temp_val = "";

    private List po_number = new ArrayList();
    private List po_proj_id = new ArrayList();
    private List po_proj_name = new ArrayList();
    private List po_date = new ArrayList();
    private List po_seller = new ArrayList();
    private List po_buyer = new ArrayList();
    private List po_value = new ArrayList();
    private List po_cur_name = new ArrayList();
    private List po_status = new ArrayList();
    private List vendorNamehasOpnpo =new ArrayList();
    private List vendorName= new ArrayList();
    private List proj_po_name= new ArrayList();
    private List proj_po_number= new ArrayList();
    private List proj_po_itemcode= new ArrayList();
    private List proj_po_duedate= new ArrayList();
    private List proj_po_remarks= new ArrayList();
    private List proj_po_date= new ArrayList();
    private List proj_po_description= new ArrayList();
    private List proj_po_rate= new ArrayList();
    private List proj_po_unit= new ArrayList();
    private List proj_po_quantity= new ArrayList();
    private List proj_po_total= new ArrayList();
    private List proj_po_vendorID= new ArrayList();
    private List proj_po_status = new ArrayList();
    private List projPOAlteration = new ArrayList();
    private List projPOReceived = new ArrayList();
    private List projPOReceivedDate = new ArrayList();
    private List vendorhasopenpoList = new ArrayList();
    private List vendorNumbhasopenpoList = new ArrayList();
    private List proj_po_createdDate = new ArrayList();
    private List clientname = new ArrayList();
    private String po_num = "";
    private String proj_id = "";
    private String from_date = "";
    private String to_date = "";
    private String buyer = "";
    private String seller = "";
    private String value = "";
    private String equality = "";
    private String status= "";
    private String searchKey = "";
    private String around = "";
    private String vendorId = "";
    private String vendor_name = "";
     String isSuggest="";

    public List getCreatedDate() {
        return proj_po_createdDate;
    }
    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public void setPOStatus(String status)
    {
        this.status=status;
    }

     public void setIsSuggest(String isSuggest)
    {
        this.isSuggest = isSuggest;
    }


    public void setPONumber(String po_num){
        this.po_num = po_num;
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

    public void setEqualityAround(String around){
        this.around = around;
    }

    public void setSearchKey(String searchKey){
        this.searchKey = searchKey;
    }

     public void setVendorName(String vendor_name){
        this.vendor_name = vendor_name;
    }

    public List getPONumber(){
        //System.out.println("po_number in poSummary of pathfinder.functions.revenue.POSummaryInfo : "+po_number);
        return po_number;
    }

    public List getPOProjID(){
        return po_proj_id;
    }

    public List getPOProjName(){
        return po_proj_name;
    }

    public List getPODate(){
        return po_date;
    }

    public List getPOValue(){
        return po_value;
    }

    public List getPOCurName(){
        return po_cur_name;
    }

    public List getPOStatus(){
        return po_status;
    }

    public List getPOSeller(){
        //System.out.println("po_seller in poSummary of pathfinder.functions.revenue.POSummaryInfo : "+po_seller);
        return po_seller;
    }

    public List getPOBuyer()
    {
        return this.po_buyer;
    }

    public List getVendorName()
    {
        return this.vendorName;
    }

    public List getProjPOName()
    {
        return this.proj_po_name;
    }

    public List getProjPONumber()
    {
        return this.proj_po_number;
    }

    public List getProjPOItemcode()
    {
        return this.proj_po_itemcode;
    }
    public List getProjPOduedate()
    {
        return this.proj_po_duedate;
    }
    public List getProjPOremarks()
    {
        return this.proj_po_remarks;
    }

    public List getProjPODate()
    {
        return this.proj_po_date;
    }

    public List getProjPODesc()
    {
        return this.proj_po_description;
    }

    public List getProjPORate()
    {
        return this.proj_po_rate;
    }
    public List getProjPOUnit()
    {
        return this.proj_po_unit;
    }

    public List getProjPOQuantity()
    {
        return this.proj_po_quantity;
    }

    public List getProjPOTotal()
    {
        return this.proj_po_total;
    }

    public List getProjPOVendorID()
    {
        return this.proj_po_vendorID;
    }

    public List getProjPOStatus()
    {
        return this.proj_po_status;
    }

    public List getProjPOAlteration() {
        return projPOAlteration;
    }

    public void setProjPOAlteration(List projPOAlteration) {
        this.projPOAlteration = projPOAlteration;
    }

    public List getProjPOReceived() {
        return projPOReceived;
    }

    public void setProjPOReceived(List projPOReceived) {
        this.projPOReceived = projPOReceived;
    }

    public List getProjPOReceivedDate() {
        return projPOReceivedDate;
    }

    public void setProjPOReceivedDate(List projPOReceivedDate) {
        this.projPOReceivedDate = projPOReceivedDate;
    }
    public void setVendorHasopenPo(List vendorhasopenpoList) {
        this.vendorhasopenpoList = vendorhasopenpoList;
    }
    public List getVendorHasopenPo() {
        return vendorhasopenpoList;
    }
        public List getVendorNumbrHasopenPo() {
        return vendorNumbhasopenpoList;
    }
    public List getClientname() {
        return clientname;
    }
   public void setClientname(List clientname) {
       this.clientname = clientname;
    }

    public void getPOSummary() {

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            /**sql_select = "select po.po_number, po.proj_id,p.proj_name,concat(c.firstname,' ',c.surname)," +
                    "date_format(po.po_date,'%d-%b-%Y %H:%i %p'),po.po_value,s.status,cr.short_name ";
            sql_from = "from purchase_order po,status s, projects p,contacts c, currency cr ";
            sql_where = "s.status_id = po.status and po.proj_id = p.proj_id and c.contact_id = po.seller_id " +
                    "and po.po_currency = cr.currency_id ";
            */

            sql_select = "";
            sql_from = "";
            sql_where = "";

            sql_select = "select po.po_number,date_format(po.po_date,'%d-%b-%Y'),po.po_value ";
            sql_from = "from purchase_order po ";


            if(!proj_id.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="po.proj_id = '"+proj_id+"' ";
            }

            if(!from_date.equals("") && !to_date.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="date(po.po_date) between '"+from_date+"' and '"+to_date+"' ";
            }

            if(!from_date.equals("") && to_date.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="date(po.po_date) between '"+from_date+"' and CURRENT_DATE ";
            }

            if(from_date.equals("") && !to_date.equals("")){
              if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="date(po.po_date)<='"+to_date+"' ";
            }


            if(!searchKey.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="po_number LIKE '"+searchKey+"%' ";
            }


            if(!status.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                 sql_where +="po.status = '"+status+"' ";
            }


            if(!po_num.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="po.po_number = '"+po_num+"' ";
            }

            if(!buyer.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="po.buyer_id = '"+buyer+"' ";
            }

            if(!seller.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                sql_where +="po.seller_id = '"+seller+"' ";
            }

            if(!value.equals("") && !equality.equals("")){
                if(!sql_where.equals("")){
                    sql_where +="and ";
                }
                //If equality is = then

                if(equality.equals("between"))
                {
                    sql_where +="po.po_value "+equality+" '"+value+"' and '"+around+"' ";
                }
                else
                    sql_where +="po.po_value "+equality+" '"+value+"' ";


            }

            sql_select+=sql_from;

            if(!sql_where.equals("")){
                sql_select +="where "+sql_where;
            }

            sql_select = sql_select + " Order By po.po_date DESC";
            if (isSuggest.equals("1")) {
                sql_select = sql_select + " LIMIT 10";
            }
            //System.out.println("sql_select in poSummary of pathfinder.functions.revenue.POSummaryInfo : "+sql_select);



            po_number.clear();
            po_date.clear();
            po_value.clear();

           /* if(rs.next()){

                rs = stmt.executeQuery(sql_select);*/
                rs = stmt.executeQuery(sql_select);
                while(rs.next()){
                    temp_val = "";
                    temp_val = rs.getString(1);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    po_number.add(temp_val);

                    temp_val = "";
                    temp_val = rs.getString(2);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    po_date.add(temp_val);

                    temp_val = "";
                    temp_val = rs.getString(3);
                    if(rs.wasNull()){
                        temp_val = "";
                    }
                    po_value.add(temp_val);
                }
           /* }else{
                po_number.add("");
                po_date.add("");
                po_value.add("");
            }*/

            po_proj_id.clear();
            po_proj_name.clear();
            po_cur_name.clear();
            po_status.clear();
            po_seller.clear();

            POInfo poInfo = new POInfo();

            for(int loop=0;loop<po_number.size();loop++){

                poInfo.setPONumber(po_number.get(loop).toString());
                poInfo.getPOInfo();

                //System.out.println("po_number : "+po_number.get(loop));
                //System.out.println("getProjId : "+poInfo.getProjId());
                //System.out.println("getProjName : "+poInfo.getProjName());
                //System.out.println("getCurrencyShortName : "+poInfo.getCurrencyShortName());
                //System.out.println("getStatusName : "+poInfo.getStatusName());
                //System.out.println("getSellerName : "+poInfo.getSellerName());
                //System.out.println("getbuyername : "+poInfo.getBuyerName());


                po_proj_id.add(poInfo.getProjId());
                po_proj_name.add(poInfo.getProjName());
                po_cur_name.add(poInfo.getCurrencyShortName());
                po_status.add(poInfo.getStatusName());
                po_seller.add(poInfo.getSellerName());
                po_buyer.add(poInfo.getBuyerName());
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
        finally{
if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement2:se.getMessage()");

                }
            }

            //Close connection
            if (con != null) {
                try {
                    rs.close();
                    con.close();

                } catch (SQLException se) {
                    System.out.println("Exception while closing connection2:se.getMessage()");

                }
            }
}
    }

     public void getPODetails() {
        int len=0;
        String temp="";

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();


            sql_select = "";
            sql_from = "";
            sql_where = "";

            sql_select = "SELECT CASE WHEN (d.is_person='1') THEN d.surname ELSE d.company END, c.proj_name, b.po_number,b.item_code, a.created_date, b.activity_description, b.rate,b.quantity, b.total, a.vendor_id, a.po_to, b.alteration, b.received, b.received_date, b.unit, b.duedate, b.remarks "
                    +" FROM po_lineitems b, projects c, purchase_orders a, contacts d WHERE a.vendor_id=d.contact_id ";
            sql_where = " AND a.po_number=b.po_number AND c.proj_id=b.proj_id ";


            if(!proj_id.equals("")){

                sql_where +=" AND c.proj_id= '"+proj_id+"' ";
            }
//System.out.println("from_date"+from_date);
            if(!from_date.equals("") && !to_date.equals("")){

                sql_where +=" AND a.created_date BETWEEN '"+from_date+"' AND '"+to_date+"' ";
            }

            if(!vendorId.equals("")) {
                sql_where += " AND d.contact_id="+vendorId;
            } else if(!vendor_name.equals("")){
                len=vendor_name.length();
                len=len-12;
                vendor_name=vendor_name.trim();
               vendor_name=vendor_name.substring(0, len);
                sql_where +=" AND d.surname LIKE '%"+vendor_name+"%' OR d.firstname LIKE '%"+vendor_name+"%' ";
            }

            sql_select +=sql_where;
            sql_select = sql_select + " ORDER BY b.po_number, b.entered_date DESC";


            //System.out.println(len+vendor_name+"sql_select in poSummary of pathfinder.functions.revenue.POSummaryInfo : "+sql_select);

//System.out.println("sql_select"+sql_select);




            vendorName.clear();
            proj_po_name.clear();
            proj_po_number.clear();
            proj_po_itemcode.clear();
            proj_po_duedate.clear();
            proj_po_remarks.clear();
            proj_po_date.clear();
            proj_po_description.clear();
            proj_po_rate.clear();
            proj_po_unit.clear();
            proj_po_quantity.clear();
            proj_po_total.clear();
            proj_po_vendorID.clear();
            proj_po_status.clear();



            /*rs = stmt.executeQuery(sql_select);
            if(rs.next()){

                rs = stmt.executeQuery(sql_select);*/
                rs = stmt.executeQuery(sql_select);
                while(rs.next()){
                    temp = "";
                    temp= splChar.decoding(rs.getString(1));
                    if(rs.wasNull()){
                        temp = "";
                    }
                    vendorName.add(temp);

                    temp = "";
                    temp = rs.getString(2);
                    if(rs.wasNull()){
                        temp = "";
                    }
                    proj_po_name.add(temp);

                    temp = "";
                    temp = rs.getString(3);
                    if(rs.wasNull()){
                        temp = "";
                    }
                    proj_po_number.add(temp);

                    temp = "";
                    temp = rs.getString(4);
                    if(rs.wasNull()){
                        temp = "";
                    }
                    proj_po_itemcode.add(temp);

                    temp = "";
                    temp = rs.getString(5);
                    if(rs.wasNull()){
                        temp = "";
                    }
                    //System.out.println("temp"+temp);
                    proj_po_date.add(temp);

                    temp = "";
                    temp = rs.getString(6);
                    if(rs.wasNull()){
                        temp = "";
                    }
                    proj_po_description.add(temp);

                    temp = "";
                    temp = rs.getString(7);
                    if(rs.wasNull()){
                        temp = "";
                    }
                    proj_po_rate.add(temp);

                    temp = "";
                    temp = rs.getString(8);
                    if(rs.wasNull()){
                        temp = "";
                    }
                    proj_po_quantity.add(temp);

                    temp = "";
                    temp= rs.getString(9);
                    if(rs.wasNull()){
                        temp = "";
                    }
                    proj_po_total.add(temp);

                    temp = "";
                    temp= rs.getString(10);
                    if(rs.wasNull()){
                        temp = "";
                    }
                    proj_po_vendorID.add(temp);

                    temp = "";
                    temp= rs.getString(11);
                    if(rs.wasNull()){
                        temp = "";
                    }
                    proj_po_status.add(temp);

                    temp = "";
                    temp= rs.getString(12);
                    if(rs.wasNull()){
                        temp = "";
                    }
                    projPOAlteration.add(temp);

                    temp = "";
                    temp= rs.getString(13);
                    if(rs.wasNull()){
                        temp = "";
                    }
                    projPOReceived.add(temp);

                    temp = "";
                    temp= rs.getString(14);
                    if(rs.wasNull()){
                        temp = "";
                    }
                    projPOReceivedDate.add(temp);
                    temp = "";
                    temp = rs.getString(15);
                    if(rs.wasNull()){
                        temp = "";
                    }
                    proj_po_unit.add(temp);
if(rs.getString(16)==null){
    proj_po_duedate.add("N/A");
}
 else{
   proj_po_duedate.add(rs.getString(16));
 }
  if(rs.getString(17)==null){
    proj_po_remarks.add("N/A");
}
 else{
   proj_po_remarks.add(rs.getString(17));
 }
                }

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
        finally{
if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement2:se.getMessage()");

                }
            }

            //Close connection
            if (con != null) {
                try {
                    rs.close();
                    con.close();

                } catch (SQLException se) {
                    System.out.println("Exception while closing connection2:se.getMessage()");

                }
            }
}
    }
     public void vendorhasopenPO(){
pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
String temp="";
String temp1="";
String temp2="";
String temp3="";
String temp4="";
String temp5="";
vendorhasopenpoList.clear();
 vendorNumbhasopenpoList.clear();
     try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT distinct (c.contact_id), c.firstname, c.surname, c.company, is_person" +
" FROM purchase_orders p, po_lineitems pl,contacts c WHERE p.po_to IS NULL AND pl.received_date IS NOT NULL"+
" AND p.po_number=pl.po_number AND c.contact_id=p.vendor_id");
 while(rs.next()){
      temp= splChar.decoding(rs.getString(2));
      temp1= splChar.decoding(rs.getString(3));
      temp2= splChar.decoding(rs.getString(4));
      temp5= rs.getString(5);
      if(temp==null) temp = "";
      if(temp1==null) temp1 = "";
      if(temp2==null) temp2 = "";
     if (temp5.equals("1")) {
      temp3=temp+" "+temp1;
      vendorhasopenpoList.add(temp3);
       }


      //if (temp5.equals("1")){
        //  vendorhasopenpoList.add(temp2);
      //}
 else{
    vendorhasopenpoList.add(temp2);
 }
     vendorNumbhasopenpoList.add(rs.getString(1));
  }

            }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
finally{
if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement2:se.getMessage()");

                }
            }

            //Close connection
            if (con != null) {
                try {
                    rs.close();
                    con.close();

                } catch (SQLException se) {
                    System.out.println("Exception while closing connection2:se.getMessage()");

                }
            }
}
     }

     public void vendorBillingReport(){
pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
String temp="";
String query="";
String whereCon="";
proj_po_number.clear();
proj_po_createdDate.clear();
proj_po_name.clear();
proj_po_itemcode.clear();
proj_po_description.clear();
proj_po_rate.clear();
proj_po_unit.clear();
proj_po_quantity.clear();
proj_po_total.clear();
projPOReceivedDate.clear();
     try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            query="SELECT b.po_number,a.po_from,c.proj_name, b.activity_description, b.item_code, b.quantity, b.rate, b.total, b.received_date, b.unit "+
"FROM po_lineitems b, projects c, purchase_orders a, contacts d WHERE a.vendor_id='"+vendorId+"' and a.vendor_id=d.contact_id "+
"AND a.po_number=b.po_number AND c.proj_id=b.proj_id AND b.received_date IS NOT NULL and a.po_to IS NULL";

if (!from_date.equals("")&& !to_date.equals("")){
    whereCon = " and b.received_date BETWEEN '"+from_date+"' and '"+ to_date +"'";
}
            rs = stmt.executeQuery(query+whereCon);
 while(rs.next()){
temp= splChar.decoding(rs.getString(2));
 if(temp==null){
     temp = "N/A";
 }
proj_po_number.add(rs.getString(1));
proj_po_createdDate.add(temp);
proj_po_name.add(rs.getString(3));
proj_po_itemcode.add(rs.getString(4));
proj_po_description.add(rs.getString(5));
proj_po_rate.add(rs.getString(6));
proj_po_quantity.add(rs.getString(7));
proj_po_total.add(rs.getString(8));
projPOReceivedDate.add(rs.getString(9));
proj_po_unit.add(rs.getString(10));
  }

            }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
finally{
if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement2:se.getMessage()");

                }
            }

            //Close connection
            if (con != null) {
                try {
                    rs.close();
                    con.close();

                } catch (SQLException se) {
                    System.out.println("Exception while closing connection2:se.getMessage()");

                }
            }
}
     }
 public void vendorBillingReport1(){
pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
String temp="";
String query="";
String whereCon="";
proj_po_number.clear();
proj_po_createdDate.clear();
proj_po_name.clear();
proj_po_itemcode.clear();
proj_po_description.clear();
proj_po_rate.clear();
proj_po_unit.clear();
proj_po_quantity.clear();
proj_po_total.clear();
projPOReceivedDate.clear();
     try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            query= "SELECT b.po_number, a.po_from, c.proj_name, b.activity_description, b.item_code, b.quantity, b.rate, b.total, b.received_date, b.unit, CONCAT_WS(' ',NULL, d.firstname, ' ', d.surname), d.company, cc.company, b.duedate, b.remarks " +
"FROM po_lineitems b, projects c, purchase_orders a, contacts d, contacts cc WHERE a.vendor_id=d.contact_id "+
"AND a.po_number=b.po_number AND c.proj_id=b.proj_id AND a.po_to IS NULL AND cc.contact_id=c.client_name ";



            //System.out.println(query+whereCon);
            rs = stmt.executeQuery(query);
 while(rs.next()){
temp= splChar.decoding(rs.getString(2));
 if(temp==null){
     temp = "N/A";
 }
proj_po_number.add(rs.getString(1));
proj_po_createdDate.add(temp);
proj_po_name.add(rs.getString(3));
proj_po_itemcode.add(rs.getString(4));
proj_po_description.add(rs.getString(5));
proj_po_rate.add(rs.getString(6));
proj_po_quantity.add(rs.getString(7));
proj_po_total.add(rs.getString(8));
String temp3=rs.getString(9);
 if(temp3==null){
     temp3 = "N/A";
 }
projPOReceivedDate.add(temp3);
proj_po_unit.add(rs.getString(10));
String temp2=rs.getString(11);

 if(temp2.equals(" ")){
     temp2 = rs.getString(12);

 }
vendorName.add(temp2);
clientname.add(rs.getString(13));
if(rs.getString(14)==null){
proj_po_duedate.add("N/A");
}
 else{
 proj_po_duedate.add(rs.getString(14));
 }
if(rs.getString(15)==null){
    proj_po_remarks.add("N/A");
}
 else{
    proj_po_remarks.add(rs.getString(15));
 }
  }

            }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
finally{
if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement2:se.getMessage()");

                }
            }

            //Close connection
            if (con != null) {
                try {
                    rs.close();
                    con.close();

                } catch (SQLException se) {
                    System.out.println("Exception while closing connection2:se.getMessage()");

                }
            }
}

    }
    public void closebulkPos(String POId,String closedate){

Connection con = null;
DBconnection dbcon = new DBconnection();
PreparedStatement stmt = null;
ResultSet rs = null;
String Query= "UPDATE purchase_orders SET po_to='"+closedate+"' where po_number='" + POId + "'";
System.out.println(Query);
        try {
        con = dbcon.getSampleProperty();
        stmt = con.prepareStatement(Query);
       stmt.executeUpdate();
        }

        catch (SQLException sqle){
            sqle.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {

                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
}
    public void yetTocloseReceivedPO(){
pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
String temp="";
String query="";
String whereCon="";
proj_po_number.clear();
proj_po_createdDate.clear();
proj_po_name.clear();
proj_po_itemcode.clear();
proj_po_description.clear();
proj_po_rate.clear();
proj_po_unit.clear();
proj_po_quantity.clear();
proj_po_total.clear();
projPOReceivedDate.clear();
     try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            query= "SELECT a.po_number, a.po_from, a.total, b.received_date " +
"FROM po_lineitems b, projects c, purchase_orders a, contacts d, contacts cc WHERE a.vendor_id='"+vendorId+"' AND a.vendor_id=d.contact_id "+
"AND a.po_number=b.po_number AND c.proj_id=b.proj_id AND a.po_to IS NULL AND cc.contact_id=c.client_name AND b.received_date IS NOT NULL GROUP BY a.po_number";
   if (!from_date.equals("")&& !to_date.equals("")){
    whereCon = " and b.received_date BETWEEN '"+from_date+"' and '"+ to_date +"'";
}
            rs = stmt.executeQuery(query+whereCon);
 while(rs.next()){
    
temp= splChar.decoding(rs.getString(2));
 if(temp==null){
     temp = "N/A";
 }
proj_po_number.add(rs.getString(1));
proj_po_createdDate.add(temp);
proj_po_total.add(rs.getString(3));
String temp3=rs.getString(4);
 if(temp3==null){
     temp3 = "N/A";
 }
projPOReceivedDate.add(temp3);
 }

            }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
finally{
if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement2:se.getMessage()");

                }
            }

            //Close connection
            if (con != null) {
                try {
                    rs.close();
                    con.close();

                } catch (SQLException se) {
                    System.out.println("Exception while closing connection2:se.getMessage()");

                }
            }
}

    }
}
