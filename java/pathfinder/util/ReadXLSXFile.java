/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.util;
 import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.io.InputStream;
	import java.text.DateFormat;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	//apache poi imports
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.DateUtil;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Gandhimathidevic
 */

       

	public class ReadXLSXFile {

	    /**
	     * @param args
	     */
	    public String readingXcel(String fname, String rowfrom, String rowTo) {
                 String inputtagV = "";
	        try {
                    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
	            InputStream is = new FileInputStream(fname);
                    System.out.println("fname"+is);
	            XSSFWorkbook wb = new XSSFWorkbook(is);
	 	    XSSFSheet sheet1 = wb.getSheetAt(0);
                   int rowFrom1 = Integer.parseInt(rowfrom);
                   int rowTo1 = Integer.parseInt(rowTo);
	            //System.out.println("Reading Sheet: " + sheet1.getSheetName());

	            //XSSFRow r0 = sheet1.getRow(3);
	            //XSSFRow r1 = sheet1.getRow(9);

	            // reading cells
	            //System.out.println("Cell r1c1: " + r0.getCell(0));
	            //System.out.println("Cell r1c2: " + r0.getCell(1));
	            //System.out.println("Cell r2c1: " + r1.getCell(0));

	            //XSSFRow row = sheet1.getRow(3);
                    inputtagV +="<table>";
                    int cellv = 0;
                    int rowv=0;
	 for (int i = rowFrom1-1; i<=rowTo1-1; i++){
             rowv++;
             inputtagV +="<tr>";
		 XSSFRow row = sheet1.getRow(i);
		 for(int j=0; j<24; j++){
		  XSSFCell cell = row.getCell(j);
                  cellv = j+1;
	            //Iterator<Cell> it = row.cellIterator();

	            //while (it.hasNext()) {

	                //Cell cell = it.next();
	                switch (cell.getCellType()) {
	                case Cell.CELL_TYPE_NUMERIC:
	                    if (DateUtil.isCellDateFormatted(cell))
                           //System.out.println("Date cell value at "+ cell.getColumnIndex() + ", "+ cell.getRowIndex() + ": "+ formatter1.format(cell.getDateCellValue()));
                            inputtagV += "<td><input type=\"text\" id=\"outputList"+rowv+"."+cellv+"\" name=\"outputList"+rowv+"."+cellv+"\" value=\""+formatter1.format(cell.getDateCellValue())+"\"></td>";
	                    else
	                        inputtagV += "<td><input type=\"text\" id=\"outputList"+rowv+"."+cellv+"\" name=\"outputList"+rowv+"."+cellv+"\" value=\""+(int)Math.round(cell.getNumericCellValue())+"\"></td>";
	                    break;
	                case Cell.CELL_TYPE_BOOLEAN:
	                    inputtagV += "<td><input type=\"text\" id=\"outputList"+rowv+"."+cellv+"\" name=\"outputList"+rowv+"."+cellv+"\" value=\""+cell.getBooleanCellValue()+"\"></td>";
	                    break;

	                case Cell.CELL_TYPE_STRING:
	                    inputtagV += "<td><input type=\"text\" id=\"outputList"+rowv+"."+cellv+"\" name=\"outputList"+rowv+"."+cellv+"\" value=\""+cell.getStringCellValue()+"\"></td>";

	                    break;
	                case Cell.CELL_TYPE_FORMULA:

	                	if (DateUtil.isCellDateFormatted(cell))
	                    inputtagV += "<td><input type=\"text\" id=\"outputList"+rowv+"."+cellv+"\" name=\"outputList"+rowv+"."+cellv+"\" value=\""+formatter1.format(cell.getDateCellValue())+"\"></td>";
	                    break;
	                case Cell.CELL_TYPE_BLANK:
	                	inputtagV += "<td><input type=\"text\" id=\"outputList"+rowv+"."+cellv+"\" name=\"outputList"+rowv+"."+cellv+"\" value=\"0\"></td>";
	                }
	            //}
	            }
                 inputtagV +="</tr>";
         }
                    inputtagV +="</table>";
                     inputtagV +="<input type=\"hidden\" id=\"cellvalue1\" name=\"cellvalue1\" value=\"23\">";
                    inputtagV +="<input type=\"hidden\" id=\"cellvalue\" name=\"cellvalue\" value=\"1\">";
                    inputtagV +="<input type=\"hidden\" id=\"xlsxF\" name=\"xlsxF\" value=\"yes\">";
              
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
  return inputtagV;
	    }
}
