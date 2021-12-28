/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.util.importexcel;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;




/**
 *
 * @author Admin
 */
public class ImportFrmExlContact {



    static Properties props;


    public static void main(String[] args) {

        String excelFilename = "contacts.xls";
        String propsFilename = "contacts.properties";

        ImportFrmExlContact readExcelWriteDb2 = new ImportFrmExlContact();
        if (args.length < 1)
        {
            System.err.println("Usage: java "+readExcelWriteDb2.getClass().getName()+
            " Excel_Filename  Properties_Filename");
            //System.exit(1);
        }


        readExcelWriteDb2.importexcel(excelFilename, propsFilename);

    }


    public void  importexcel(String excelFilename, String propsFilename){

        try {
            String category="";
            String type="";

           // props = new Properties();
            if(props==null){
                                props = new Properties();
                                InputStream is = getClass().getResourceAsStream("/properties/importexcel/contacts.properties");
                                System.out.println("getclass:"+getClass().getResourceAsStream("/properties/importexcel/contacts.properties"));
                                props.load(is);
                                is.close();
                            }

            FileInputStream fileInputStream = new FileInputStream(props.getProperty("excelPath").trim() + excelFilename.trim());

            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);

            int rows = sheet.getPhysicalNumberOfRows() + 5;
            System.out.println(workbook.getSheetName(0) + "\" has " + rows + " row(s).");

            pathfinder.functions.contacts.SaveContact svc = null;
             List cellVal = null;
            for (int r = 0; r < rows; r++) {

                HSSFRow row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                // Skip Header Row
                if (r == 0) {
                    continue;
                }

                //int cells = row.getPhysicalNumberOfCells();
                int cells =row.getLastCellNum();
                System.out.println("\nROW " + row.getRowNum() + " has " + cells + " cell(s).");
                svc = new pathfinder.functions.contacts.SaveContact();
                for (int c = 0; c < cells; c++) {
                    if(c==0)
                        cellVal = new ArrayList();
                    HSSFCell cell = row.getCell(c);
                    System.out.println("cell:"+cell);
                    if(cell!=null)
                    {
                        cellVal.add(cell.toString());
                    }
                    else
                    {
                        cellVal.add("");
                    }

                }

                    //set the values for one row
                    svc.setFName(cellVal.get(0).toString());
                svc.setSName(cellVal.get(1).toString());
                svc.setCntCompany(cellVal.get(2).toString());
                 svc.setCntDivision(cellVal.get(3).toString());
                 svc.setAddress1(cellVal.get(4).toString());
                svc.setAddress2(cellVal.get(5).toString());
                svc.setCity(cellVal.get(6).toString());
                svc.setState(cellVal.get(7).toString());
                 svc.setCountry(cellVal.get(8).toString());
                svc.setZipCode(cellVal.get(9).toString());
                svc.setCntWebsite(cellVal.get(10).toString());
                svc.setCntVendorNum(cellVal.get(11).toString());
                svc.setCntEmail(cellVal.get(12).toString());
                svc.setDepartment(cellVal.get(13).toString());
                svc.setCntDesignation(cellVal.get(14).toString());
                 svc.setPhone1(cellVal.get(15).toString());
                svc.setPhone2(cellVal.get(16).toString());
                svc.setMobile(cellVal.get(17).toString());
                svc.setFax1(cellVal.get(18).toString());
                svc.setFax2(cellVal.get(19).toString());
                svc.setFax3(cellVal.get(20).toString());
                svc.setSkype(cellVal.get(21).toString());
                svc.setMSN(cellVal.get(22).toString());
                svc.setAOL(cellVal.get(23).toString());
                svc.setGtalk(cellVal.get(24).toString());
                svc.setCntYahoo(cellVal.get(25).toString());
                 svc.setCntLinkedIn(cellVal.get(26).toString());
                svc.setCntFacebook(cellVal.get(27).toString());
                svc.setCntTwitter(cellVal.get(28).toString());
                svc.setDescription(cellVal.get(29).toString());
                if(cellVal.get(30).equals("1.0"))
                {
                    category="1";
                }
                else if(cellVal.get(30).equals("2.0"))
                {
                    category="2";
                }
                svc.setCntIsPerson(category);

                if(cellVal.get(31).equals("1.0"))
                {
                    type="1";
                }
                else if(cellVal.get(31).equals("2.0"))
                {
                    type="2";
                }
                else if(cellVal.get(31).equals("3.0"))
                {
                    type="3";
                }
                else if(cellVal.get(31).equals("4.0"))
                {
                    type="4";
                }
                else if(cellVal.get(31).equals("5.0"))
                {
                    type="5";
                }
                else if(cellVal.get(31).equals("6.0"))
                {
                    type="6";
                }
                else if(cellVal.get(31).equals("7.0"))
                {
                    type="7";
                }
                else if(cellVal.get(31).equals("8.0"))
                {
                    type="8";
                }
                svc.setContactType(type);




                //svc.setCntCompanyId(getCompanyIdParam);
                /* if(!getContactTypeParam.equals("1")){*/

                //}


               // svc.setContactId(getCntIdParam);





                svc.setEmpId("R&D");

                 svc.setAddOption("Add");//based on this value the bean will do the insert or update operations
                    System.out.println("In Add Param");

                        svc.saveContact();


                        int checkAddContact = Integer.parseInt(svc.getSaveContact());

                System.out.println("&&&&&&-chkaddcnt:"+checkAddContact);



            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
