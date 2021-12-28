/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;
import pathfinder.functions.mysqlinjection;

import connection.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aravindhanj
 */
public class PODetailsDAO {

    DBconnection dbconnection = new DBconnection();
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private Statement st1 = null;
    private Statement st2 = null;
    private ResultSet rs1 = null;
    private String poList = "";
    private String currencyList = "";
    private String vendorId = "";
    private String vendorFirstName = "";
    private String vendorSurName = "";
    private String vendorAddr1 = "";
    private String vendorAddr2 = "";
    private String vendorCity = "";
    private String vendorCountry = "";
    private String vendorZipCode = "";
    private String vendorPhone = "";
    private String vendorFax = "";
    private String vendorEmail = "";
    private String vendorNumber = "";

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorAddr1() {
        return vendorAddr1;
    }

    public void setVendorAddr1(String vendorAddr1) {
        this.vendorAddr1 = vendorAddr1;
    }

    public String getVendorAddr2() {
        return vendorAddr2;
    }

    public void setVendorAddr2(String vendorAddr2) {
        this.vendorAddr2 = vendorAddr2;
    }

    public String getVendorCity() {
        return vendorCity;
    }

    public void setVendorCity(String vendorCity) {
        this.vendorCity = vendorCity;
    }

    public String getVendorCountry() {
        return vendorCountry;
    }

    public void setVendorCountry(String vendorCountry) {
        this.vendorCountry = vendorCountry;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getVendorFax() {
        return vendorFax;
    }

    public void setVendorFax(String vendorFax) {
        this.vendorFax = vendorFax;
    }

    public String getVendorFirstName() {
        return vendorFirstName;
    }

    public void setVendorFirstName(String vendorFirstName) {
        this.vendorFirstName = vendorFirstName;
    }

    public String getVendorNumber() {
        return vendorNumber;
    }

    public void setVendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
    }

    public String getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    public String getVendorSurName() {
        return vendorSurName;
    }

    public void setVendorSurName(String vendorSurName) {
        this.vendorSurName = vendorSurName;
    }

    public String getVendorZipCode() {
        return vendorZipCode;
    }

    public void setVendorZipCode(String vendorZipCode) {
        this.vendorZipCode = vendorZipCode;
    }

public PODetailsVO getCurrencyDetails(PODetailsVO poDetailsVO){
try {
  ArrayList currencycodeList = new ArrayList();
  ArrayList currencySymbolList = new ArrayList();
  poDetailsVO = new PODetailsVO();
  con = dbconnection.getSampleProperty();
  st = con.createStatement();
  currencyList = "SELECT currency_code,symbol FROM currency_symbols";
  rs = st.executeQuery(currencyList);
  while (rs.next()) {
    if (rs.getString(1) != null) {
      currencycodeList.add(rs.getString(1));
    } else {
      currencycodeList.add("");
    }
    if (rs.getString(2) != null) {
      currencySymbolList.add(rs.getString(2));
    } else {
      currencySymbolList.add("");
    }
   }
  poDetailsVO.setCurrencyCodeList1(currencycodeList);
  poDetailsVO.setCurrencySymbolList1(currencySymbolList);
 } catch (SQLException sqle) {
  sqle.printStackTrace();
} catch (NullPointerException npe) {
  npe.printStackTrace();
} finally {
  try {
    rs.close();
    st.close();
    con.close();
  } catch (SQLException sqle) {
    sqle.printStackTrace();
  } catch (NullPointerException npe) {
    npe.printStackTrace();
  }
}
        return poDetailsVO;
    }

    public PODetailsVO getPoCategoryWIP(PODetailsVO poDetailsVO,Connection con) {
        try {
            ArrayList categoryId = new ArrayList();
            ArrayList categoryName = new ArrayList();
            ArrayList categoryTotal = new ArrayList();
//            PreparedStatement ps = con.prepareStatement("SELECT sc.super_cat_id,sc.super_category,SUM(po.total) FROM "
//                    +"estimate_super_category sc,estimate_category es,po_lineitems po "
//                    +"WHERE po.proj_id = ? AND lcase(REPLACE(po.activity_description,' ','')) = lcase(REPLACE(es.category,' ','')) AND es.super_category = sc.super_cat_id "
//                    +"GROUP BY sc.super_cat_id");
            PreparedStatement ps = con.prepareStatement("SELECT (CASE WHEN sc.super_cat_id IS NULL THEN '9' ELSE sc.super_cat_id END) CATY_ID ,"
                    + "(CASE WHEN sc.super_category  IS NULL THEN 'Uncategorized' ELSE sc.super_category END) CATY_NAME,"
                    + "SUM(po.total),po.activity_description "
                    + "FROM po_lineitems po "
                    + "LEFT JOIN billing_lineitems_master blm ON (po.activity_description=blm.name) "
                    + "LEFT JOIN estimate_super_category sc ON (blm.super_cat_id = sc.super_cat_id) "
                    + "WHERE po.proj_id = ? AND po.received='1' GROUP BY sc.super_cat_id");

            ps.setString(1, poDetailsVO.getWip_proj_id());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString(1) == null) {
                    categoryId.add("0");
                } else {
                    categoryId.add(rs.getString(1));
                }
                if (rs.getString(2) == null) {
                    categoryName.add(rs.getString(2));
                } else {
                    categoryName.add(rs.getString(2));
                }
                if (rs.getString(3) == null) {
                    categoryTotal.add(rs.getString(3));
                } else {
                    categoryTotal.add(rs.getString(3));
                }
            }
            poDetailsVO.setCategoryId(categoryId);
            poDetailsVO.setCategoryName(categoryName);
            poDetailsVO.setCategoryTotal(categoryTotal);
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error while geting po details for WIP:" + e.toString());
        }
        return poDetailsVO;
    }

    public PODetailsVO getPoofWIP(PODetailsVO poDetailsVO,Connection con) {
        try {
            ArrayList activity = new ArrayList();
            ArrayList total = new ArrayList();
            PreparedStatement ps = con.prepareStatement("SELECT SUM(total),activity_description FROM po_lineitems where proj_id = ? AND received='1' GROUP BY activity_code");
            ps.setString(1, poDetailsVO.getWip_proj_id());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString(1) == null) {
                    total.add(rs.getString(1));
                } else {
                    total.add(rs.getString(1));
                }
                if (rs.getString(2) == null) {
                    total.add(rs.getString(2));
                } else {
                    activity.add(rs.getString(2));
                }
            }
            poDetailsVO.setActivityName(activity);
            poDetailsVO.setTotal(total);
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error while geting po details for WIP:" + e.toString());
        }
        return poDetailsVO;
    }

    public PODetailsVO getPOofVendors(PODetailsVO poDetailsVO) {
        try {
            String vendorId = poDetailsVO.getVendorId();
            ArrayList POIDList = new ArrayList();
            ArrayList POFromList = new ArrayList();
            ArrayList POToList = new ArrayList();
            ArrayList POTotalList = new ArrayList();

            poDetailsVO = new PODetailsVO();
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            poList = "SELECT po_number, po_from, po_to, total FROM purchase_orders WHERE vendor_id = '" + vendorId + "'";

            rs = st.executeQuery(poList);
            while (rs.next()) {

                if (rs.getString(1) != null) {
                    poDetailsVO.setPoNum(rs.getString(1).toString());
                } else {
                    poDetailsVO.setPoNum("");
                }

                if (rs.getString(2) != null) {
                    poDetailsVO.setFromDate(rs.getString(2).toString());
                } else {
                    poDetailsVO.setFromDate("");
                }

                if (rs.getString(3) != null) {
                    poDetailsVO.setToDate(rs.getString(3).toString());
                } else {
                    poDetailsVO.setToDate("");
                }

                if (rs.getString(4) != null) {
                    poDetailsVO.setGrandTotal(rs.getString(4).toString());
                } else {
                    poDetailsVO.setGrandTotal("");
                }
            }

            poList = "SELECT po_number, po_from, po_to, total from purchase_orders where vendor_id = '" + vendorId + "' ORDER BY po_to IS NOT NULL, po_from DESC";

            rs = st.executeQuery(poList);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    POIDList.add(rs.getString(1));
                } else {
                    POIDList.add("");
                }
                if (rs.getString(2) != null) {
                    POFromList.add(rs.getString(2));
                } else {
                    POFromList.add("");
                }
                if (rs.getString(3) != null && !rs.getString(3).equalsIgnoreCase("0000-00-00")) {
                    POToList.add(rs.getString(3));
                } else {
                    POToList.add("");
                }
                if (rs.getString(4) != null) {
                    POTotalList.add(rs.getString(4));
                } else {
                    POTotalList.add("");
                }
            }
            poDetailsVO.setPOList(POIDList);
            poDetailsVO.setFromDateList(POFromList);
            poDetailsVO.setToDateList(POToList);
            poDetailsVO.setTotal(POTotalList);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
        return poDetailsVO;
    }

    public PODetailsVO getVendorDetails(PODetailsVO poDetailsVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String firstName = "";
        String surName = "";
        String companyName = "";
        String displayVendorName = "";
        String stateName="";
        List vendorList = new ArrayList();

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();
            //String vendorId = poDetailsVO.getVendorId();

            String vendor_detail_query = " SELECT contact_id, firstname, surname, address_1, address_2, city, country, zipcode, vendor_number, email, phone_primary, fax1, "
                                       + " company, is_person, state"
                                       + " FROM contacts WHERE contact_id='"+vendorId+"' ";

            rs = st.executeQuery(vendor_detail_query);

            while (rs.next()) {
                PODetailsVO vendorDetail = new PODetailsVO();

                if (rs.getString(1) != null) {
                    poDetailsVO.setVendorId(rs.getString(1));
                } else {
                    poDetailsVO.setVendorId("");
                }

                if (rs.getString(2)  != null) {
                    poDetailsVO.setVendorFirstName(splChar.decoding(rs.getString(2)));
                    firstName = splChar.decoding(rs.getString(2).toString());
                } else {
                    poDetailsVO.setVendorFirstName("");
                }

                if (rs.getString(3) != null) {
                    poDetailsVO.setVendorSurName(splChar.decoding(rs.getString(3)));
                    surName = splChar.decoding(rs.getString(3).toString());
                } else {
                    poDetailsVO.setVendorSurName("");
                }

                if (rs.getString(4) != null) {
                    poDetailsVO.setVendorAddr1(rs.getString(4));
                } else {
                    poDetailsVO.setVendorAddr1("");
                }

                if (rs.getString(5) != null) {
                    poDetailsVO.setVendorAddr2(rs.getString(5));
                } else {
                    poDetailsVO.setVendorAddr2("");
                }

                if (rs.getString(6) != null) {
                    poDetailsVO.setVendorCity(rs.getString(6));
                } else {
                    poDetailsVO.setVendorCity("");
                }

                if (rs.getString(7) != null) {
                    poDetailsVO.setVendorCountry(rs.getString(7));
                } else {
                    poDetailsVO.setVendorCountry("");
                }

                if (rs.getString(8) != null) {
                    poDetailsVO.setVendorZipCode(rs.getString(8));
                } else {
                    poDetailsVO.setVendorZipCode("");
                }

                if (rs.getString(9) != null) {
                    poDetailsVO.setVendorNumber(rs.getString(9));
                } else {
                    poDetailsVO.setVendorNumber("");
                }

                if (rs.getString(10) != null) {
                    poDetailsVO.setVendorEmail(rs.getString(10));
                } else {
                    poDetailsVO.setVendorEmail("");
                }

                if (rs.getString(11) != null) {
                    poDetailsVO.setVendorPhone(rs.getString(11));
                } else {
                    poDetailsVO.setVendorPhone("");
                }

                if (rs.getString(12) != null) {
                    poDetailsVO.setVendorFax(rs.getString(12));
                } else {
                    poDetailsVO.setVendorFax("");
                }

                if (rs.getString(13) != null) {
                    companyName = splChar.decoding(rs.getString(13).toString());
                } else {
                    
                }
                if (rs.getString(15) != null) {
                    poDetailsVO.setVendorState(rs.getString(15));
                } else {
                    poDetailsVO.setVendorState("");
                }
                  

                if (rs.getString(14) != null) {
                    if(rs.getString(14).toString().equals("1")) {
                        displayVendorName = firstName + " " + surName;
                    } else {
                        displayVendorName = companyName;
                    }
                    poDetailsVO.setDisplayVendoerName(displayVendorName);
                } else {
                    poDetailsVO.setDisplayVendoerName("");
                }
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

//        return vendorList;
        return poDetailsVO;
    }

    public PODetailsVO getPODetails(PODetailsVO poDetailsVO) {
      

        try {

            ArrayList LineItemId = new ArrayList();
            ArrayList POId = new ArrayList();
            ArrayList ProjId = new ArrayList();
            ArrayList ProjDispName = new ArrayList();
            ArrayList activityId = new ArrayList();
            ArrayList dept_code = new ArrayList();
            ArrayList duedate = new ArrayList();
            ArrayList remarks = new ArrayList();
            ArrayList activityDescription = new ArrayList();
            ArrayList CategoryId = new ArrayList();
            ArrayList ItemId = new ArrayList();
            ArrayList UnitPrice = new ArrayList();
            ArrayList Quantity = new ArrayList();
            ArrayList Total = new ArrayList();
            ArrayList unit = new ArrayList();
            ArrayList alteration = new ArrayList();
            ArrayList received = new ArrayList();
            ArrayList receivedDate = new ArrayList();

            List packProjList = new ArrayList();

            String vendorId = poDetailsVO.getVendorId();
            String PONumber = poDetailsVO.getPoNum();

            poDetailsVO = new PODetailsVO();
            pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
            con = dbconnection.getSampleProperty();
            st = con.createStatement();
            st1 = con.createStatement();

            /*
            poList = "SELECT pl.line_item_id, pl.po_number, pl.proj_id, pl.item_code, pl.category, pl.rate, "
                    + "pl.quantity, pl.total, p.proj_name, pl.activity_code, pl.activity_description, "
                    + "pl.unit, pl.alteration, pl.received, date_format(pl.received_date,'%d-%b-%Y') "
                    + "FROM po_lineitems pl, purchase_orders po, projects p "
                    + "WHERE po.po_number = pl.po_number AND po.po_number='" + PONumber + "' AND po.vendor_id = '" + vendorId + "' AND  p.proj_id = pl.proj_id";
             *
             */
            poList = "SELECT pl.line_item_id, pl.po_number, pl.proj_id, pl.item_code, pl.category, pl.rate, "
                    + "    pl.quantity, pl.total, p.proj_name, pl.activity_code, pl.activity_description, "
                    + "    pl.unit, pl.alteration, pl.received, DATE_FORMAT(pl.received_date,'%d-%b-%Y'), pl.dept_code, pl.duedate, pl.remarks"
                    + "    FROM purchase_orders po, po_lineitems pl LEFT JOIN projects p ON p.proj_id = pl.proj_id "
                    + "    WHERE po.po_number = pl.po_number AND po.po_number='" + PONumber + "' AND po.vendor_id = '" + vendorId + "'";
            rs = st.executeQuery(poList);
            //System.out.println(poList);
            while (rs.next()) {

                if (rs.getString(1) != null) {
                    LineItemId.add(rs.getString(1));
                } else {
                    LineItemId.add("");
                }

                if (rs.getString(2) != null) {
                    POId.add(rs.getString(2));
                } else {
                    POId.add("");
                }

                if (rs.getString(3) != null) {
                    ProjId.add(rs.getString(3));
                } else {
                    ProjId.add("");
                }

                if (rs.getString(4) != null) {
                    ItemId.add(splChar.decoding(rs.getString(4)));
                } else {
                    ItemId.add("");
                }

                if (rs.getString(5) != null) {
                    CategoryId.add(rs.getString(5));
                } else {
                    CategoryId.add("");
                }

                if (rs.getString(6) != null) {
                    UnitPrice.add(rs.getString(6));
                } else {
                    UnitPrice.add("0");
                }

                if (rs.getString(7) != null) {
                    Quantity.add(rs.getString(7));
                } else {
                    Quantity.add("0");
                }

                if (rs.getString(8) != null) {
                    Total.add(rs.getString(8));
                } else {
                    Total.add("0");
                }

                if (rs.getString(9) != null) {
                    ProjDispName.add(rs.getString(9));
                } else {
                    ProjDispName.add("");
                }

                if (rs.getString(10) != null) {
                    activityId.add(rs.getString(10));
                } else {
                    activityId.add("");
                }

                if (rs.getString(11) != null) {
                    activityDescription.add(rs.getString(11));
                } else {
                    activityDescription.add("");
                }

                if (rs.getString(12) != null) {
                    unit.add(rs.getString(12));
                } else {
                    unit.add("");
                }

                if (rs.getString(13) != null) {
                    alteration.add(rs.getString(13));
                } else {
                    alteration.add("0");
                }

                if (rs.getString(14) != null) {
                    received.add(rs.getString(14));
                } else {
                    received.add("0");
                }

                if (rs.getString(15) != null) {
                    receivedDate.add(rs.getString(15));
                } else {
                    receivedDate.add("");
                }
                if (rs.getString(16) != null) {
                    dept_code.add(rs.getString(16));
                } else {
                    dept_code.add("N/A");
                }
                if (rs.getString(17) != null) {
                    duedate.add(rs.getString(17));
                } else {
                    //System.out.println("null");
                    duedate.add("0000-00-00");
                }
                if (rs.getString(18) != null) {
                    remarks.add(rs.getString(18));
                } else {
                    remarks.add("N/A");
                }
            }

            poList = "SELECT date_format(po_from,'%d-%b-%Y %h-%i %p'), date_format(po_to,'%d-%b-%Y %h-%i %p'), total, currency_code,contact_nameforShippAddress, po_notes FROM purchase_orders WHERE po_number = '" + PONumber + "' AND vendor_id = '" + vendorId + "'";

            rs1 = st1.executeQuery(poList);

            while (rs1.next()) {

                if (rs1.getString(1) != null) {
                    poDetailsVO.setFromDate(rs1.getString(1));
                } else {
                    poDetailsVO.setFromDate("");
                }

                if (rs1.getString(2) != null) {
                    poDetailsVO.setToDate(rs1.getString(2));
                } else {
                    poDetailsVO.setToDate("");
                }

                if (rs1.getString(3) != null) {
                    poDetailsVO.setGrandTotal(rs1.getString(3));
                } else {
                    poDetailsVO.setGrandTotal("");
                }
                 if (rs1.getString(4) != null) {
                  //   System.out.println("inset"+rs1.getString(4));
                    poDetailsVO.setCurrencyCodeStrng(rs1.getString(4));
                } else {
                    poDetailsVO.setCurrencyCodeStrng("");
                }
                if (rs1.getString(5) != null) {
                  //   System.out.println("inset"+rs1.getString(4));
                    poDetailsVO.setcontactNameforShipaddToDisp(rs1.getString(5));
                } else {
                    poDetailsVO.setcontactNameforShipaddToDisp("");
                }
                if (rs1.getString(6) != null) {
                  //   System.out.println("inset"+rs1.getString(4));
                    poDetailsVO.setlimitedtextareaPoNotesToDisp(rs1.getString(6));
                } else {
                    poDetailsVO.setlimitedtextareaPoNotesToDisp("");
                }
            }

            poDetailsVO.setLineItemId(LineItemId);
            poDetailsVO.setPOId(POId);
            poDetailsVO.setProjId(ProjId);
            poDetailsVO.setActivityId(activityId);
            poDetailsVO.setdeptCode(dept_code);
            poDetailsVO.setActivityName(activityDescription);
            poDetailsVO.setProjDispName(ProjDispName);
            poDetailsVO.setCategoryId(CategoryId);
            poDetailsVO.setItemId(ItemId);
            poDetailsVO.setUnitPrice(UnitPrice);
            poDetailsVO.setQuantity(Quantity);
            poDetailsVO.setTotal(Total);
            poDetailsVO.setUnit(unit);
            poDetailsVO.setAlteration(alteration);
            poDetailsVO.setReceived(received);
            poDetailsVO.setReceivedDate(receivedDate);
            poDetailsVO.setdueDate(duedate);
            poDetailsVO.setremarks(remarks);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                rs1.close();
                st1.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
        return poDetailsVO;
    }

    public void SavePO(PODetailsVO poDetailsVO) {
        int result = 0;
        ArrayList LineItemId = new ArrayList();
        //ArrayList ProjId = new ArrayList();
        ArrayList ProjName = new ArrayList();
        ArrayList dept_code = new ArrayList();
        ArrayList activityId = new ArrayList();
        ArrayList dueDate = new ArrayList();
        ArrayList remarks = new ArrayList();
        ArrayList CategoryId = new ArrayList();
        ArrayList ItemId = new ArrayList();
        ArrayList UnitPrice = new ArrayList();
        ArrayList Unit = new ArrayList();
        ArrayList Alteration = new ArrayList();
        ArrayList Received = new ArrayList();
        ArrayList Quantity = new ArrayList();
        ArrayList Total = new ArrayList();

        String empId = poDetailsVO.getEmpId();
        String CurrencyInsert = "";
        String ContactnameforShippadd = "";
        String insertPoNotes = "";
        String PONumber = "";
        String VendorId = "";
        String POId = "";
        String FromDate = "";
        String ToDate = "";
        String grandTotal = "";
        String Query = "";

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;

        ResultSet rsl = null;
        try {

            con = dbcon.getSampleProperty();
            //LineItemId = poDetailsVO.getLineItemId();
            VendorId = poDetailsVO.getVendorId();
            //ProjId = poDetailsVO.getProjId();
            ProjName = poDetailsVO.getProjDispName();
            dept_code =  poDetailsVO.getdeptCode();
            dueDate =  poDetailsVO.getdueDate();
            remarks =  poDetailsVO.getremarks();
            activityId = poDetailsVO.getActivityId();
            CategoryId = poDetailsVO.getCategoryId();
            ItemId = poDetailsVO.getItemId();
            UnitPrice = poDetailsVO.getUnitPrice();
            Unit = poDetailsVO.getUnit();
            Alteration = poDetailsVO.getAlteration();
            Received = poDetailsVO.getReceived();
            Quantity = poDetailsVO.getQuantity();
            Total = poDetailsVO.getTotal();

            VendorId = poDetailsVO.getVendorId();
            POId = poDetailsVO.getPoNum();

            FromDate = poDetailsVO.getFromDate();
            CurrencyInsert = poDetailsVO.getInsertedCurrency();
            ContactnameforShippadd = poDetailsVO.getInsertedContactName();
            insertPoNotes = poDetailsVO.getInsertedPoNotes();
            //System.out.println("insertPoNotes"+insertPoNotes);
            ToDate = poDetailsVO.getToDate();
            grandTotal = poDetailsVO.getGrandTotal();
            if (FromDate.equals("")) {
                FromDate = null;
            }
            con = dbconnection.getSampleProperty();
            st = con.createStatement();
            st1 = con.createStatement();
              st2 = con.createStatement();

            //Query = "SELECT count(*), po_number FROM purchase_orders WHERE vendor_id='" + VendorId + "' and po_number='" + POId + "'";
            Query = "SELECT count(*), po_number FROM purchase_orders WHERE vendor_id='" + VendorId + "' and po_number='" + POId + "'";

            rs = st.executeQuery(Query);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    String temp = rs.getString(1).toString() != "" && rs.getString(1).toString() != null ? rs.getString(1).toString() : "0";
                    if (temp == "0" || temp.equals("0")) {
                        String InsertPO;
                        if (ToDate.equals("")) {
                            InsertPO = "INSERT INTO purchase_orders (vendor_id, po_from, total, created_date, created_by, currency_code, contact_nameforShippAddress, po_notes) VALUES ("
                                    + "'" + VendorId + "','" + FromDate + "','" + grandTotal + "', CURRENT_TIMESTAMP(), '" + empId + "','" + CurrencyInsert + "','" + ContactnameforShippadd + "','" + insertPoNotes + "')";
                        } else {
                            InsertPO = "INSERT INTO purchase_orders (vendor_id, po_from, po_to, total, created_date, created_by, currency_code, contact_nameforShippAddress) VALUES ("
                                    + "'" + VendorId + "','" + FromDate + "','" + ToDate + "','" + grandTotal + "', CURRENT_TIMESTAMP(), '" + empId + "', '" + CurrencyInsert + "','" + ContactnameforShippadd + "','" + insertPoNotes + "')";
                        }
                        result = st1.executeUpdate(InsertPO);

                        poList = "SELECT MAX(po_number) FROM purchase_orders";

                        rs1 = st2.executeQuery(poList);
                        while (rs1.next()) {
                            if (rs1.getString(1) != null) {
                                PONumber = rs1.getString(1);
                            } else {
                                PONumber = "";
                            }
                        }
                    } else {
                        String InsertPO;
                        if (ToDate.equals("")) {
                            InsertPO = "UPDATE purchase_orders SET po_from='" + FromDate + "', total='" + grandTotal + "', currency_code='" + CurrencyInsert + "', contact_nameforShippAddress='" + ContactnameforShippadd + "', po_notes='" + insertPoNotes + "' where vendor_id='" + VendorId + "' AND po_number='" + POId + "'";
                        } else {
                            InsertPO = "UPDATE purchase_orders SET po_from='" + FromDate + "', po_to='" + ToDate + "', total='" + grandTotal + "', currency_code='" + CurrencyInsert + "', contact_nameforShippAddress='" + ContactnameforShippadd + "', po_notes='" + insertPoNotes + "' where vendor_id='" + VendorId + "' AND po_number='" + POId + "'";
                        }
                        result = st1.executeUpdate(InsertPO);
                        if (rs.getString(2).toString() != null) {
                            PONumber = rs.getString(2).toString();
                        } else {
                            PONumber = rs.getString(2).toString();
                        }
                    }
                } else {
                    //LineItemId.add("");
                }
            }

            for (int index = 0; index < ProjName.size(); index++) {
                //ItemId.set(index, splChar.decoding((ItemId.get(index).toString())));

                String InsertPO = "";
                if(Received.get(index).equals("1")) {
                    if (empId.equals("VCD01")){
InsertPO = "INSERT INTO po_lineItems (po_number, proj_id, activity_code, activity_description, item_code, category, unit, rate, quantity, alteration, received, received_date, total, dept_code, duedate, remarks) VALUES ("
                            + "'" + PONumber + "',(SELECT p.proj_id FROM projects p WHERE p.proj_name='" + ProjName.get(index) + "'),'" + activityId.get(index) + "', "
                            + "(SELECT name FROM billing_lineitems_master WHERE billing_item_id='" + activityId.get(index) + "'),'" + ItemId.get(index) + "','" + CategoryId.get(index) + "',"
                            + "'" + Unit.get(index) + "','" + UnitPrice.get(index) + "','" + Quantity.get(index) + "','" + Alteration.get(index) + "', '" + Received.get(index) + "', CURRENT_TIMESTAMP(), '" + Total.get(index) + "', '"+dept_code.get(index)+"', '"+dueDate.get(index)+"', '"+remarks.get(index)+"')";
                    }
 else{
                     InsertPO = "INSERT INTO po_lineItems (po_number, proj_id, activity_code, activity_description, item_code, category, unit, rate, quantity, alteration, received, received_date, total) VALUES ("
                            + "'" + PONumber + "',(SELECT p.proj_id FROM projects p WHERE p.proj_name='" + ProjName.get(index) + "'),'" + activityId.get(index) + "', "
                            + "(SELECT name FROM billing_lineitems_master WHERE billing_item_id='" + activityId.get(index) + "'),'" + ItemId.get(index) + "','" + CategoryId.get(index) + "',"
                            + "'" + Unit.get(index) + "','" + UnitPrice.get(index) + "','" + Quantity.get(index) + "','" + Alteration.get(index) + "', '" + Received.get(index) + "', CURRENT_TIMESTAMP(), '" + Total.get(index) + "')";
                    }
                } else {
                    if (empId.equals("VCD01")){
                    InsertPO = "INSERT INTO po_lineItems (po_number, proj_id, activity_code, activity_description, item_code, category, unit, rate, quantity, alteration, total, dept_code, duedate, remarks) VALUES ("
                            + "'" + PONumber + "',(SELECT p.proj_id FROM projects p WHERE p.proj_name='" + ProjName.get(index) + "'),'" + activityId.get(index) + "', "
                            + "(SELECT name FROM billing_lineitems_master WHERE billing_item_id='" + activityId.get(index) + "'),'" + ItemId.get(index) + "','" + CategoryId.get(index) + "',"
                            + "'" + Unit.get(index) + "','" + UnitPrice.get(index) + "','" + Quantity.get(index) + "','" + Alteration.get(index) + "', '" + Total.get(index) + "', '"+dept_code.get(index)+"', '"+dueDate.get(index)+"', '"+remarks.get(index)+"')";
                    }
 else{
                     InsertPO = "INSERT INTO po_lineItems (po_number, proj_id, activity_code, activity_description, item_code, category, unit, rate, quantity, alteration, total) VALUES ("
                            + "'" + PONumber + "',(SELECT p.proj_id FROM projects p WHERE p.proj_name='" + ProjName.get(index) + "'),'" + activityId.get(index) + "', "
                            + "(SELECT name FROM billing_lineitems_master WHERE billing_item_id='" + activityId.get(index) + "'),'" + ItemId.get(index) + "','" + CategoryId.get(index) + "',"
                            + "'" + Unit.get(index) + "','" + UnitPrice.get(index) + "','" + Quantity.get(index) + "','" + Alteration.get(index) + "', '" + Total.get(index) + "')";
                }}
System.out.println(InsertPO);
                result = st.executeUpdate(InsertPO);
            }
            poDetailsVO.setPoNum(PONumber);
            poDetailsVO.setInsResult(result);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
         catch (NullPointerException npe) {
       
       npe.printStackTrace();
        }
           finally {
               
            try {
              //  rs1.close();
                st1.close();
                rs.close();
                st.close();
           con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
    }

    public void UpdatePO(PODetailsVO poDetailsVO) {
        int result = 0;
        ArrayList updateLineItemId = new ArrayList();
        ArrayList updatePOId = new ArrayList();
        //ArrayList updateProjId = new ArrayList();
        ArrayList updatedept_code = new ArrayList();
        ArrayList updateremarks = new ArrayList();
        ArrayList updatedueDate = new ArrayList();
        ArrayList updateActivityId = new ArrayList();
        ArrayList updateProjName = new ArrayList();
        ArrayList updateCategoryId = new ArrayList();
        ArrayList updateItemId = new ArrayList();
        ArrayList updateUnitPrice = new ArrayList();
        ArrayList updateUnit = new ArrayList();
        ArrayList updateAlteration = new ArrayList();
        ArrayList updateReceived = new ArrayList();
        ArrayList updateQuantity = new ArrayList();
        ArrayList updateTotal = new ArrayList();

        String VendorId = "";
        String POId = "";
        String FromDate = "";
        String ToDate = "";
        String grandTotal = "";
        String Query = "";

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rsl = null;
        try {

            con = dbcon.getSampleProperty();
            //projId = actualEstimationVO.getProjId();
            updateLineItemId = poDetailsVO.getUpdateLineItemId();
            updatePOId = poDetailsVO.getUpdatePOId();
            //updateProjId = poDetailsVO.getUpdateProjId();
            updatedept_code =  poDetailsVO.getUpdatedeptCode();
            updateremarks =  poDetailsVO.getUpdateremarks();
            updatedueDate = poDetailsVO.getUpdatedueDate();
            updateActivityId = poDetailsVO.getUpdateActivityId();
            updateProjName = poDetailsVO.getUpdateProjDispName();
            updateCategoryId = poDetailsVO.getUpdateCategoryId();
            updateItemId = poDetailsVO.getUpdateItemId();
            updateUnitPrice = poDetailsVO.getUpdateUnitPrice();
            updateUnit = poDetailsVO.getUpdateUnit();
            updateAlteration = poDetailsVO.getUpdateAlteration();
            updateReceived = poDetailsVO.getUpdateReceived();
            updateQuantity = poDetailsVO.getUpdateQuantity();
            updateTotal = poDetailsVO.getUpdateTotal();

            VendorId = poDetailsVO.getVendorId();
            POId = poDetailsVO.getPoNum();
            FromDate = poDetailsVO.getFromDate();
            ToDate = poDetailsVO.getToDate();
            grandTotal = poDetailsVO.getGrandTotal();

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            for (int index = 0; index < updateLineItemId.size(); index++) {

                updateItemId.set(index, splChar.decoding((updateItemId.get(index).toString())));

                String UpdatePO = "UPDATE po_lineItems SET po_number = '" + updatePOId.get(index) + "', proj_id = (SELECT p.proj_id FROM projects p WHERE p.proj_name='" + updateProjName.get(index) + "'), "
                        + "item_code = '" + updateItemId.get(index) + "', "
                        + "category = '" + updateCategoryId.get(index) + "', rate = '" + updateUnitPrice.get(index) + "', "
                        + "unit = '" + updateUnit.get(index) + "', "
                        + "quantity = '" + updateQuantity.get(index) + "', "
                        + "alteration = '" + updateAlteration.get(index) + "', "
                        + "total='" + updateTotal.get(index) + "', "
                        + "activity_code = '" + updateActivityId.get(index) + "', "
                        + "dept_code='" + updatedept_code.get(index) + "', "
                        + "activity_description = (SELECT name FROM billing_lineitems_master WHERE billing_item_id='" + updateActivityId.get(index) + "'), duedate='"+ updatedueDate.get(index) +"', remarks='"+ updateremarks.get(index) +"'";

                if(updateReceived.get(index).equals("1")) {
                    UpdatePO +=  ", received = '" + updateReceived.get(index) + "', received_date = CURRENT_TIMESTAMP() ";
                }

                UpdatePO += "WHERE line_item_id='" + updateLineItemId.get(index) + "'";
//System.out.println(UpdatePO);
                result = st.executeUpdate(UpdatePO);
            }
            //Update the Purchase Order
            String InsertPO;
            if (ToDate.equals("")) {
                //InsertPO = "UPDATE purchase_orders SET po_from='" + FromDate + "', po_to='0000-00-00', total='" + grandTotal + "' where vendor_id='" + VendorId + "' AND po_number='" + POId + "'";
                InsertPO = "UPDATE purchase_orders SET po_from='" + FromDate + "', po_to=NULL, total='" + grandTotal + "' where vendor_id='" + VendorId + "' AND po_number='" + POId + "'";
            } else {
                InsertPO = "UPDATE purchase_orders SET po_from='" + FromDate + "', po_to='" + ToDate + "', total='" + grandTotal + "' where vendor_id='" + VendorId + "' AND po_number='" + POId + "'";
            }
            result = st.executeUpdate(InsertPO);

            poDetailsVO.setInsResult(result);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
    }

    public void DeletePO(PODetailsVO poDetailsVO) {

        int result = 0;
        int deleteLineItemId = 0;
        int deleteVendorId = 0;
        int updateVendorTot = 0;
        String vendorId = poDetailsVO.getVendorId();
        String currentTotal = poDetailsVO.getGrandTotal();
        String poNumber = poDetailsVO.getPoNum();
        String Query = "";

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList LineItemIdList = poDetailsVO.getLineItemId();

        //Create a response object and set the response for the request.
        try {

            con = dbcon.getSampleProperty();
            for (int index = 0; index < LineItemIdList.size(); index++) {
                Query = " DELETE FROM po_lineitems WHERE line_item_id=" + LineItemIdList.get(index);

                stmt = con.prepareStatement(Query);
                deleteLineItemId = stmt.executeUpdate();
            }

            Query = "SELECT COUNT(*) FROM po_lineitems pl, purchase_orders po WHERE po.vendor_id='" + vendorId + "' AND po.po_number = pl.po_number AND po.po_number='" + poNumber + "'";
            stmt = con.prepareStatement(Query);
            rs = stmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            if (count == 0) {
                Query = "DELETE FROM purchase_orders WHERE vendor_id='" + vendorId + "' AND po_number='" + poNumber + "'";
                stmt = con.prepareStatement(Query);
                deleteVendorId = stmt.executeUpdate();
                if (deleteVendorId == 1) {
                    poDetailsVO.setPoNum("");
                }
            } else {
                Query = "UPDATE purchase_orders SET total='" + currentTotal + "' WHERE vendor_id='" + vendorId + "' AND po_number='" + poNumber + "'";
                stmt = con.prepareStatement(Query);
                updateVendorTot = stmt.executeUpdate();
            }
            if (deleteVendorId == 1 || updateVendorTot == 1 || deleteLineItemId == 1) {
                result = 1;
            }
            poDetailsVO.setDeleteResult(result);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
    }

    public PODetailsVO getPOLineItems(PODetailsVO poDetailsVO) {

        ArrayList billingItemId = new ArrayList();
        ArrayList billingItem = new ArrayList();
        ArrayList billingCode = new ArrayList();
        try {
            poDetailsVO = new PODetailsVO();
            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            poList = "SELECT billing_item_id, name, code FROM billing_lineitems_master";

            rs = st.executeQuery(poList);
            while (rs.next()) {

                if (rs.getString(1) != null) {
                    billingItemId.add(rs.getString(1));
                } else {
                    billingItemId.add("");
                }

                if (rs.getString(2) != null) {
                    billingItem.add(rs.getString(2));
                } else {
                    billingItem.add("");
                }

                if (rs.getString(3) != null) {
                    billingCode.add(rs.getString(3));
                } else {
                    billingCode.add("");
                }
            }
            poDetailsVO.setBillingItemId(billingItemId);
            poDetailsVO.setBillingItem(billingItem);
            poDetailsVO.setBillingItemCode(billingCode);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
        return poDetailsVO;
    }
    public PODetailsVO contactInformationForshipped(PODetailsVO poDetailsVO){
        ArrayList contactNameforShipped = new ArrayList();
        ArrayList contactIdforShipped = new ArrayList();
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rst = null;
        DBconnection dbcon = new DBconnection();
        try {
        con = dbcon.getSampleProperty();
        stmt = con.createStatement();
        //rst = stmt.executeQuery("SELECT emp_name,emp_id FROM USER WHERE (emp_name <> 'wkproduction' AND (dept_code='BGEST' and facility_id='1')) OR (dept_code IN ('DUBQ-DEV','DUBQ-PMT','DUBQ-COMP','DUBQ-DES','MGMT','PM') and facility_id='2')) and STATUS='1' order by emp_name");
          rst = stmt.executeQuery("SELECT emp_name, emp_id FROM USER WHERE (((dept_code='BGEST' and desig_code<>'TRNE' OR dept_code='CHN-PMT' OR (dept_code='MGMT' and desig_code='AGM-PMS') OR desig_code='VNCOD') and facility_id='1') OR (dept_code IN ('DUBQ-DEV','DUBQ-PMT','DUBQ-COMP','DUBQ-DES','MGMT','PM') and facility_id='2')) and STATUS='1' order by emp_name");
        while (rst.next()){
            contactNameforShipped.add(rst.getString(1));
            contactIdforShipped.add(rst.getString(2));
            //contactEmailIdforShipped.add(rst.getString(3));
        }
        poDetailsVO.setcontactNameforShipdList(contactNameforShipped);
        poDetailsVO.setcontactEmpIdforShipTo(contactIdforShipped);
        }
        
        catch (SQLException sqle){
            sqle.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                rst.close();
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
        return poDetailsVO;
    }
   public PODetailsVO gettingMailaddress(PODetailsVO poDetailsVO){
        String getContactIdFormailAdd = poDetailsVO.getEmpIdForgetMailAddress();

        String contactEmailIdforShipped = "";
        Connection con = null;
        Statement stmt = null;
        ResultSet rst = null;
        DBconnection dbcon = new DBconnection();
        try {
        con = dbcon.getSampleProperty();
        stmt = con.createStatement();
        //System.out.println("SELECT mailid FROM USER WHERE STATUS='1' AND emp_id='"+getContactIdFormailAdd+"'");
        rst = stmt.executeQuery("SELECT mailid FROM USER WHERE STATUS='1' AND emp_id='"+getContactIdFormailAdd+"'");
        while (rst.next()){
            
            contactEmailIdforShipped = (rst.getString(1));
        }
        
       poDetailsVO.setmailadd(contactEmailIdforShipped);
        }

        catch (SQLException sqle){
            sqle.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                rst.close();
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
   return poDetailsVO;
   }


}
