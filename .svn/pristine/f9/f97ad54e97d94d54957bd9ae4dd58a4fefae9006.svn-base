package pathfinder.util.importexcel;

import connection.DBconnection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;



import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.DataFormatter;

public class ImportFrmExlPriceList {

    static Properties props;
    private String contactId = "";
    private String year = "";
    private String priceListId = "";
    private String orderId = "";
    private String empId = "";
    private int result = 0;
    private String excelFilename = "";
    private String curDate = "";
    private String priceEnable = "";

    public String getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(String priceListId) {

        this.priceListId = priceListId;
    }

    public String getPriceEnable() {
        return priceEnable;
    }

    public int getResult() {
        return result;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setExcelFilename(String excelFilename) {

        this.excelFilename = excelFilename;
    }

    public void setPriceEnable(String priceEnable) {

        this.priceEnable = priceEnable;
    }

    public void setContactId(String contactId) {

        this.contactId = contactId;
    }

    public void setCurDate(String curDate) {

        this.curDate = curDate;
    }

    public void setEmpId(String empId) {

        this.empId = empId;
    }

    public void setYear(String year) {

        this.year = year;
    }

    public void importexcel() {

        try {

            // props = new Properties();
            if (props == null) {
                props = new Properties();
                InputStream is = getClass().getResourceAsStream("/properties/importexcel/pricelist.properties");
                // System.out.println("getclass:" + getClass().getResourceAsStream("/properties/importexcel/pricelist.properties"));
                props.load(is);
                is.close();

            }

            Connection con = null;
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();

            String sql = null;
            PreparedStatement stmt = null;


            sql = "SELECT " + props.getProperty("columns").trim() + " from " + props.getProperty("tableName").trim();
            //System.out.println("sql:" + sql);
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int colCount = metaData.getColumnCount();

            //get pricelistid and order id
            collectPriceListId();
            collectPriceListOrderId();

            //disable old pricelist if enable=1
            if(priceEnable!=null && priceEnable.equals("1"))
            {
              disableOldPriceList();
            }

            String q = "";
            for (int c = 0; c < colCount; c++) {
                if (q.equalsIgnoreCase("")) {
                    q = "?";
                } else {
                    q = q + ",?";
                }
            }
            sql = "INSERT into "
                    + props.getProperty("tableName").trim()
                    + " (" + props.getProperty("columns").trim() + ") VALUES(" + q + ")";
            stmt = con.prepareStatement(sql);
            boolean insertData = true;

            FileInputStream fileInputStream = new FileInputStream(props.getProperty("excelPath").trim() + excelFilename.trim());
//InputStream inputStream = new InputStream(fileInputStream,"UTF8");
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);

            int rows = sheet.getPhysicalNumberOfRows();
            // System.out.println(workbook.getSheetName(0) + "\" has " + rows + " row(s).");
            for (int r = 0; r < rows; r++) {

                HSSFRow row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                // Skip Header Row
                if (r == 0) {
                    continue;
                }


                int cells = row.getPhysicalNumberOfCells();
                //   System.out.println("\nROW " + row.getRowNum() + " has " + cells + " cell(s).");

                // Create a formatter, do this once
                DataFormatter formatter = new DataFormatter(Locale.US);


                int columnIndex = 0;

                for (int c = 0; c < cells; c++) {

                    HSSFCell cell = row.getCell(c);
                    String value = null, printValue = null;

                    // System.out.println("The value of  is " + formatter.formatCellValue(cell));


                    printValue = "STRING value=" + formatter.formatCellValue(cell);
                    value = formatter.formatCellValue(cell);

//                    System.out.println("Column=" + cell.getColumnIndex() + " VALUE=" + printValue);

                    columnIndex = cell.getColumnIndex() + 1;
                    stmt.setString(columnIndex, value);
                }

                //get the values for 
                //price_date,contact_id,emp_id,price_list_id,price_order,added_date

                stmt.setString(columnIndex + 1, year);
                stmt.setString(columnIndex + 2, contactId);
                stmt.setString(columnIndex + 3, empId);
                stmt.setString(columnIndex + 4, priceListId);
                stmt.setString(columnIndex + 5, orderId);
                stmt.setString(columnIndex + 6, curDate);
                if(priceEnable==null)
                {
                    priceEnable = "0";
                }
                stmt.setString(columnIndex + 7, priceEnable);



                if (insertData) {
                    result = stmt.executeUpdate();
                }

                // System.out.println("excel sql:" + sql);

            }

            rs.close();
            stmt.close();
            con.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void collectPriceListId() {


        Connection con = null;
        DBconnection connection = new DBconnection();
        con = connection.getSampleProperty();

        String sql = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            sql = " SELECT MAX(price_list_id)+1 FROM billing_pricelist";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                priceListId = rs.getString(1);
                if (rs.wasNull()) {
                    priceListId = "1";
                }

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

    public void collectPriceListOrderId() {


        Connection con = null;
        DBconnection connection = new DBconnection();
        con = connection.getSampleProperty();

        String sql = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            sql = " SELECT MAX(price_order)+1,curdate() FROM billing_pricelist WHERE contact_id=" + contactId + "";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                orderId = rs.getString(1);
                if (rs.wasNull()) {
                    orderId = "1";
                }
                curDate = rs.getString(2);

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

    public void disableOldPriceList() {

//System.out.println("java disable");
        Connection con = null;
        DBconnection connection = new DBconnection();
        con = connection.getSampleProperty();

        String sql = null;

        PreparedStatement stmt = null;

        try {
            sql = "UPDATE billing_pricelist SET price_enable=0 WHERE price_enable=1 AND contact_id=?";


            stmt = con.prepareStatement(sql);
            

            stmt.setString(1, contactId);

             stmt.executeUpdate();

            stmt.close();
            con.close();


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }


    }

    public void enableOldPriceList() {

//System.out.println("java enable");
        Connection con = null;
        DBconnection connection = new DBconnection();
        con = connection.getSampleProperty();

        String sql = null;

        PreparedStatement stmt = null;

        try {
            sql = "UPDATE billing_pricelist SET price_enable=1  WHERE price_list_id=? AND contact_id=?";


            stmt = con.prepareStatement(sql);

stmt.setString(1, priceListId);
            stmt.setString(2, contactId);

             stmt.executeUpdate();

            stmt.close();
            con.close();


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }


    }

    public void disableEnable()
    {
        disableOldPriceList();
        enableOldPriceList();

    }
}





































































































































































































































