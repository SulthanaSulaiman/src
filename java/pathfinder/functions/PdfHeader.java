/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 *
 * @author Aravindhanj
 */
public class PdfHeader {

    PdfPTable header;
     private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 15,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font blueFont = new Font(Font.FontFamily.HELVETICA, 10,
            Font.BOLD, BaseColor.BLUE);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 10,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 9
            );
    private static Font small = new Font(Font.FontFamily.HELVETICA, 12);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 9);
    private static Font smallBoldNote = new Font(Font.FontFamily.HELVETICA, 4);
    
    

 PdfPTable footer;
Phrase phrase;
Chunk chunk;
float [] columnParams={2f,7f,6f,22f,10f,3f,8f,8f,8f,8f,8f,8f,6f};

    
    public PdfPTable  headerStyle(int headertype) {
   
    
    
    header = new PdfPTable(columnParams);
    if(headertype==1)
header.setTotalWidth(800);
    else 
    {
        header.setWidthPercentage(100f);
header.setSpacingBefore(40f);
    }
        



 phrase=new Phrase("status",smallBold);

 PdfPCell statusCell=new PdfPCell(phrase);
 statusCell.setRotation(90);
 cellStyling(statusCell);
 statusCell.setBorderWidthLeft(1f);
 

statusCell.setBorderWidthRight(0);
 
phrase=new Phrase("Job", smallBold);
 PdfPCell jobCell=new PdfPCell(phrase);
cellStyling(jobCell);
jobCell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
 
 phrase=new Phrase("Customer Name/Division ID", smallBold);

 PdfPCell customerCell=new PdfPCell(phrase);
cellStyling(customerCell );
customerCell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
 

 phrase=new Phrase("Authors/Title",smallBold);
phrase.setFont(smallBoldNote);
 PdfPCell authorCell=new PdfPCell(phrase);
cellStyling(authorCell);
authorCell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
 
 phrase=new Phrase("Planner",smallBold);
phrase.setFont(headerFont);
 PdfPCell plannerCell=new PdfPCell(phrase);
 cellStyling(plannerCell);
 plannerCell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
 
 
 phrase=new Phrase("colors",smallBold);
phrase.setFont(smallBoldNote);
 PdfPCell colorCell=new PdfPCell(phrase);
 colorCell.setRotation(90);
 cellStyling(colorCell);
 
 phrase=new Phrase("TrimSize",smallBold);
phrase.setFont(smallBoldNote);
 PdfPCell trimCell=new PdfPCell(phrase);
 cellStyling(trimCell);
 trimCell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
 
 phrase=new Phrase("Award Date",smallBold);
phrase.setFont(smallBoldNote);
 PdfPCell awarddateCell=new PdfPCell(phrase);
 awarddateCell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
cellStyling(awarddateCell);
 
 phrase=new Phrase("Due  Date",smallBold);
phrase.setFont(smallBoldNote);
 PdfPCell duedateCell=new PdfPCell(phrase);
 duedateCell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
 cellStyling(duedateCell);
 
 phrase=new Phrase("Date Shipped",smallBold);
phrase.setFont(smallBoldNote);
 PdfPCell dateshippedCell=new PdfPCell(phrase);
 dateshippedCell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
cellStyling(dateshippedCell);
 
 phrase=new Phrase("Est'd Selling Value",smallBold);
phrase.setFont(smallBoldNote);
 PdfPCell EstsellvalCell=new PdfPCell(phrase);
 EstsellvalCell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
 cellStyling(EstsellvalCell);
 
 phrase=new Phrase("Partialed $",smallBold);
phrase.setFont(smallBoldNote);
 PdfPCell partialCell=new PdfPCell(phrase);
 partialCell.setPaddingLeft(4f);
 partialCell.setRotation(90);
 cellStyling(partialCell);
 
 phrase=new Phrase("Est'd / Actual Pages",smallBold);
phrase.setFont(smallBoldNote);
 PdfPCell actualpageCell=new PdfPCell(phrase);
 actualpageCell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
 cellStyling(actualpageCell);
 actualpageCell.setBorderWidthRight(1f);
//adding cell to header table
 
 header.addCell(statusCell);
 header.addCell(jobCell);
 header.addCell(customerCell);
 header.addCell(authorCell);
 header.addCell(plannerCell);
 header.addCell(colorCell);
 header.addCell(trimCell);
 header.addCell(awarddateCell);
 header.addCell(duedateCell);
 header.addCell(dateshippedCell);
 header.addCell(EstsellvalCell);
 header.addCell(partialCell);
 header.addCell(actualpageCell);
        return header;
     }
    
    
    public void cellStyling(PdfPCell cell)
{
   cell.setBorderWidthRight(0);
   cell.setBorderWidthLeft(0);
   cell.setFixedHeight(55f);
   cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
    
}
}
