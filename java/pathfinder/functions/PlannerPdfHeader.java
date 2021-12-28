/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions;

/**
 *
 * @author Parameshwarant
 */


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import connection.DBconnection;
import java.sql.Connection;
import pathfinder.functions.projects.TitlesToPrinterRpt;
import java.util.List;

public class PlannerPdfHeader {

    
    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 10,Font.UNDERLINE|Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 9);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 9);

    TitlesToPrinterRpt proj = new TitlesToPrinterRpt();
    PdfWriter writer;

    DBconnection db = new DBconnection();
    Connection con = db.getSampleProperty();

    PdfPTable plannerHeader;
    PdfPTable topMostheader;
    PdfPTable footer;
    PdfPTable headerDisp;
    PdfPTable projectsFooter;
    PdfPTable projectsHeaderDisp;
    PdfPTable projectsHeader;
    Phrase ph1;
    
    float [] columnParams={2f,7f,16f,22f,10f,3f,18f,6f,6f,6f,12f,12f,6f};
    float [] columnParams1={24f};
    float [] columnParams2={6f, 13f, 36f, 22f, 29f, 20f, 17f, 12f};
    float [] columnParams3 = {5f, 25f, 12f, 10f, 8f, 6f, 20f, 7f, 7f};
    float [] columnParams4 = {40f, 25f, 20f, 15f, 15f, 15f};
    float [] columnParams5 = {30f, 23f, 18f, 18f, 10f, 10f, 8f, 10f, 8f};
    float [] columnParams6 = {30f, 23f, 18f, 18f, 10f, 10f, 10f};
    float [] columnParams7 = {30f, 30f, 30f};
    float [] columnParams8 = {10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f};
    float [] columnParams9 = {20f, 10f, 15f, 10f, 10f, 10f};
    int count=0;



public PdfPTable  plannerHeaderStyle(int headertype){



    plannerHeader = new PdfPTable(columnParams2);
    if(headertype==1)
    {
        plannerHeader.setWidthPercentage(100f);
        plannerHeader.setTotalWidth(800f);
        
    }
    else
    {
        plannerHeader.setWidthPercentage(100f);
        plannerHeader.setTotalWidth(800f);
    }
   
    PdfPCell c = new PdfPCell();
   
    
    c=new PdfPCell(new Phrase("Status",small));
    c.setBorder(Rectangle.TOP|Rectangle.LEFT|Rectangle.BOTTOM);
    c.setRotation(90);
    plannerHeader.addCell(c);
    
    c=new PdfPCell(new Phrase("Job",smallBold));
    c.setBorder(Rectangle.TOP|Rectangle.BOTTOM);
    plannerHeader.addCell(c);
    
    c=new PdfPCell(new Phrase("Customer",smallBold));
    c.setBorder(Rectangle.TOP|Rectangle.BOTTOM);
    plannerHeader.addCell(c);
    
    c=new PdfPCell(new Phrase("Author",smallBold));
    c.setBorder(Rectangle.TOP|Rectangle.BOTTOM);
    plannerHeader.addCell(c);
    
    c=new PdfPCell(new Phrase("Project Name",smallBold));
    c.setBorder(Rectangle.TOP|Rectangle.BOTTOM);
    plannerHeader.addCell(c);
    
    c=new PdfPCell(new Phrase("Planner",smallBold));
    c.setBorder(Rectangle.TOP|Rectangle.BOTTOM);
    plannerHeader.addCell(c);
    
    c=new PdfPCell(new Phrase("Category",smallBold));
    c.setBorder(Rectangle.TOP|Rectangle.BOTTOM);
    plannerHeader.addCell(c);
    
    c=new PdfPCell(new Phrase("Priority",smallBold));
    c.setBorder(Rectangle.TOP|Rectangle.RIGHT|Rectangle.BOTTOM);
    plannerHeader.addCell(c);

    
       
    /*Paragraph  p=new Paragraph(new Chunk(new DottedLineSeparator()));
    p.setFont(small);p.setAlignment(Paragraph.ALIGN_TOP);
    //p.add(new Chunk(new DottedLineSeparator()));
    c = new PdfPCell(new Phrase(p));
    c.setHorizontalAlignment(PdfPCell.ALIGN_TOP);
    //c.setBackgroundColor(new BaseColor(0, 0, 255));
    c.setBorder(Rectangle.RIGHT|Rectangle.LEFT);
    c.setFixedHeight(1.5f);
    c.setColspan(8);
    plannerHeader.addCell(c);*/
    
    
    c=new PdfPCell(new Phrase("Color",small));
    c.setBorder(Rectangle.BOTTOM|Rectangle.LEFT|Rectangle.TOP);
    c.setRotation(90);
    c.setBorderColorBottom(BaseColor.RED);
    plannerHeader.addCell(c);
    
    c=new PdfPCell(new Phrase("Trim Size",smallBold));
    c.setBorder(Rectangle.TOP|Rectangle.BOTTOM);
    plannerHeader.addCell(c);
    
    c=new PdfPCell(new Phrase("Level",smallBold));
    c.setBorder(Rectangle.TOP|Rectangle.BOTTOM);
    plannerHeader.addCell(c);
    
    c=new PdfPCell(new Phrase("Coding Type",smallBold));
    c.setBorder(Rectangle.TOP|Rectangle.BOTTOM);
    plannerHeader.addCell(c);
    
    c=new PdfPCell(new Phrase("Est'd Pages",smallBold));
    c.setBorder(Rectangle.TOP|Rectangle.BOTTOM);
    plannerHeader.addCell(c);
    
    c=new PdfPCell(new Phrase("Due Date",smallBold));
    c.setBorder(Rectangle.TOP|Rectangle.BOTTOM);
    plannerHeader.addCell(c);

    c=new PdfPCell(new Phrase("Pages Start To Mail",smallBold));
    c.setBorderColorBottom(BaseColor.RED);
    c.setBorder(Rectangle.TOP|Rectangle.BOTTOM);

    plannerHeader.addCell(c);
    
    c=new PdfPCell(new Phrase("Pages End Mailing",smallBold));
    c.setBorder(Rectangle.BOTTOM|Rectangle.RIGHT|Rectangle.TOP);
    plannerHeader.addCell(c);

        return plannerHeader;
     }

public PdfPTable  plannerFooterStyle()
{
    footer = new PdfPTable(columnParams1);

    footer.setWidthPercentage(100f);
    footer.setTotalWidth(800f);
    Date today = new Date();
    Format formatter = new SimpleDateFormat("E, MMM dd, yyyy hh:mm:ss aaa");
    ph1 = new Phrase(formatter.format(today), smallBold);
    PdfPCell dateFooter = new PdfPCell(ph1);
    dateFooter.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
    dateFooter.setBorder(0);
    footer.addCell(dateFooter);
    return footer;
}
public PdfPTable  overallHeaderStyle()
{
    headerDisp = new PdfPTable(columnParams1);
    headerDisp.setTotalWidth(800);
    headerDisp.setSpacingBefore(10f);
    Phrase catPhrase1 = new Phrase("Planner's Project Report", headerFont);
    Paragraph plannerPara1 = new Paragraph(catPhrase1);
    PdfPCell c = new PdfPCell(plannerPara1);
    c.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
    c.setBorder(Rectangle.NO_BORDER);
    headerDisp.addCell(c);
    return headerDisp;

}
public PdfPTable  statusHeaderStyle() throws SQLException
{
    topMostheader = new PdfPTable(columnParams1);
    topMostheader.setSpacingBefore(10f);
    topMostheader.setSpacingAfter(10f);
    topMostheader = createFirstTable(con);

    return topMostheader;

}


public void commonStylingTop(PdfPCell cell) {
        cell.setBorder(Rectangle.TOP);
    }

public void commonStylingBottom(PdfPCell cell) {
        cell.setBorder(Rectangle.BOTTOM);
    }
public void commonStylingLeft(PdfPCell cell) {
        cell.setBorder(Rectangle.LEFT);
    }
public void commonStylingRight(PdfPCell cell) {
        cell.setBorder(Rectangle.RIGHT);
    }
public void cellStyling(PdfPCell cell)
{
   cell.setBorderWidthRight(0);
   cell.setBorderWidthLeft(0);
   cell.setFixedHeight(45f);
   cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

}

    public PdfPTable createFirstTable(Connection con) throws SQLException {
        // a table with three columns
        List<String> statusList = proj.getStatusAliasList(con);
        String status, status_alais;
        PdfPTable table = new PdfPTable(6);
        table.setTotalWidth(800f);
        table.setWidthPercentage(100f);
        PdfPCell cell;
        Phrase phrase;
        phrase = new Phrase("Status", subFont);
        cell = new PdfPCell(phrase);
        cell.setBorder(0);
        commonStylingTop(cell);
        cell.setColspan(6);
        table.addCell(cell);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        for (String str : statusList) {
            phrase = new Phrase(str, smallNote);
            cell = new PdfPCell(phrase);
            cell.setBorder(0);
            table.addCell(cell);
        }
        for (int i = 1; i <= (statusList.size() % 3); i++) {
            cell = new PdfPCell(new Phrase(""));
            cell.setBorder(0);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            table.addCell(cell);
        }
        //con.close();

        return table;
    }
    
    
    
    
    
public PdfPTable projectsHeaderStyle() {

    projectsHeader = new PdfPTable(columnParams3);
    projectsHeader.setWidthPercentage(100f);
    projectsHeader.setTotalWidth(800f);


    PdfPCell c = new PdfPCell();
    c.setFixedHeight(40f);


    Paragraph para1 = new Paragraph(new Phrase("Job ID", smallBold));
    para1.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c1 = new PdfPCell(para1);
    c1.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
    c1.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
    c1.setFixedHeight(40f);
    projectsHeader.addCell(c1);

    Paragraph para2 = new Paragraph(new Phrase("Book Title", smallBold));
    para2.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c2 = new PdfPCell(para2);
    c2.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c2.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
    projectsHeader.addCell(c2);

    Paragraph para5 = new Paragraph(new Phrase("Planner", smallBold));
    para5.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c5 = new PdfPCell(para5);
    c5.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c5.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
    projectsHeader.addCell(c5);

    Paragraph para6 = new Paragraph(new Phrase("Category", smallBold));
    para6.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c6 = new PdfPCell(para6);
    c6.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c6.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
    projectsHeader.addCell(c6);

    Paragraph para3 = new Paragraph(new Phrase("Type", smallBold));
    para3.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c3 = new PdfPCell(para3);
    c3.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c3.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
    projectsHeader.addCell(c3);

    Paragraph para4 = new Paragraph(new Phrase("Estd. Pages", smallBold));
    para4.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c4 = new PdfPCell(para4);
    c4.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c4.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
    projectsHeader.addCell(c4);

    Paragraph para7 = new Paragraph(new Phrase("Customer/State/Division", smallBold));
    para7.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c7 = new PdfPCell(para7);
    c7.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c7.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
    projectsHeader.addCell(c7);


    Paragraph para8 = new Paragraph(new Phrase("Award Date", smallBold));
    para8.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c8 = new PdfPCell(para8);
    c8.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
    c8.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
    projectsHeader.addCell(c8);

    Paragraph para9 = new Paragraph(new Phrase("Due Date", smallBold));
    para9.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c9 = new PdfPCell(para9);
    c9.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);
    c9.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
    projectsHeader.addCell(c9);

    return projectsHeader;
}

public PdfPTable projectsFooterStyle() {
    footer = new PdfPTable(columnParams1);

    footer.setWidthPercentage(100f);
    footer.setTotalWidth(800f);
    Date today = new Date();
    Format formatter = new SimpleDateFormat("E, MMM dd, yyyy hh:mm:ss aaa");
    ph1 = new Phrase(formatter.format(today), smallBold);
    PdfPCell dateFooter = new PdfPCell(ph1);
    dateFooter.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
    dateFooter.setBorder(0);
    footer.addCell(dateFooter);
    return footer;
}

public PdfPTable overallProjectsHeaderStyle() {
    headerDisp = new PdfPTable(columnParams1);
    headerDisp.setTotalWidth(800);
    headerDisp.setSpacingBefore(10f);
    Phrase catPhrase1 = new Phrase("Projects By Projected Ship Date", headerFont);
    Paragraph plannerPara1 = new Paragraph(catPhrase1);
    PdfPCell c = new PdfPCell(plannerPara1);
    c.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
    c.setBorder(Rectangle.NO_BORDER);
    headerDisp.addCell(c);
    return headerDisp;

}

public PdfPTable printerFilesOnTimeHeaderStyle() {

    projectsHeader = new PdfPTable(columnParams4);
    projectsHeader.setWidthPercentage(100f);
    projectsHeader.setTotalWidth(800f);


    PdfPCell c = new PdfPCell();
    c.setFixedHeight(40f);


    Paragraph para0 = new Paragraph(new Phrase("Job ID / Name", smallBold));
    para0.setAlignment(Element.ALIGN_CENTER | Element.ALIGN_MIDDLE);
    PdfPCell c0 = new PdfPCell(para0);
    c0.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
    c0.setPaddingTop(15f);
    c0.setFixedHeight(40f);
    projectsHeader.addCell(c0);

    Paragraph para1 = new Paragraph(new Phrase("Customer Name", smallBold));
    para1.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c1 = new PdfPCell(para1);
    c1.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c1.setPaddingTop(15f);
    projectsHeader.addCell(c1);



    Paragraph para2 = new Paragraph(new Phrase("Planner Name", smallBold));
    para2.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c2 = new PdfPCell(para2);
    c2.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c2.setPaddingTop(15f);
    projectsHeader.addCell(c2);

    Paragraph para3 = new Paragraph(new Phrase("Estimate Ship Date", smallBold));
    para3.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c3 = new PdfPCell(para3);
    c3.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    
    c3.setPaddingTop(15f);
    projectsHeader.addCell(c3);

    Paragraph para4 = new Paragraph(new Phrase("Actual Ship Date", smallBold));
    para4.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c4 = new PdfPCell(para4);
    c4.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c4.setPaddingTop(15f);
    projectsHeader.addCell(c4);

    Paragraph para5 = new Paragraph(new Phrase("No. of Days Past Due", smallBold));
    para5.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c5 = new PdfPCell(para5);
    c5.setBorder(Rectangle.TOP | Rectangle.BOTTOM| Rectangle.RIGHT);
    c5.setPaddingTop(10f);
    projectsHeader.addCell(c5);


    return projectsHeader;
}

public PdfPTable printerFilesOnTimeFooterStyle() {
    footer = new PdfPTable(columnParams1);

    footer.setWidthPercentage(100f);
    footer.setTotalWidth(800f);
    Date today = new Date();
    Format formatter = new SimpleDateFormat("E, MMM dd, yyyy hh:mm:ss aaa");
    ph1 = new Phrase(formatter.format(today), smallBold);
    PdfPCell dateFooter = new PdfPCell(ph1);
    dateFooter.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
    dateFooter.setBorder(0);
    footer.addCell(dateFooter);
    return footer;
}

public PdfPTable overallPrinterFilesOnTimeHeaderStyle() {
    headerDisp = new PdfPTable(columnParams1);
    headerDisp.setTotalWidth(800);
    headerDisp.setSpacingBefore(10f);
    Phrase catPhrase1 = new Phrase("Printer Files On Time Report", headerFont);
    Paragraph plannerPara1 = new Paragraph(catPhrase1);
    PdfPCell c = new PdfPCell(plannerPara1);
    c.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
    c.setBorder(Rectangle.NO_BORDER);
    headerDisp.addCell(c);
    return headerDisp;

}

public PdfPTable billingReportProjectsFilesHeaderStyle() {

    projectsHeader = new PdfPTable(columnParams5);
    projectsHeader.setWidthPercentage(100f);
    projectsHeader.setTotalWidth(800f);


    PdfPCell c = new PdfPCell();
    c.setFixedHeight(40f);


    Paragraph para1 = new Paragraph(new Phrase("Job ID / Name", smallBold));
    para1.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c1 = new PdfPCell(para1);
    c1.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
    c1.setFixedHeight(40f);
    c1.setPaddingTop(15f);
    projectsHeader.addCell(c1);

    Paragraph para2 = new Paragraph(new Phrase("Customer Name", smallBold));
    para2.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c2 = new PdfPCell(para2);
    c2.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c2.setPaddingTop(15f);
    projectsHeader.addCell(c2);

    Paragraph para5 = new Paragraph(new Phrase("Planner", smallBold));
    para5.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c5 = new PdfPCell(para5);
    c5.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c5.setPaddingTop(15f);
    projectsHeader.addCell(c5);

    Paragraph para6 = new Paragraph(new Phrase("Hybrid PM", smallBold));
    para6.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c6 = new PdfPCell(para6);
    c6.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c6.setPaddingTop(15f);
    projectsHeader.addCell(c6);

    Paragraph para3 = new Paragraph(new Phrase("Act. Ship Date", smallBold));
    para3.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c3 = new PdfPCell(para3);
    c3.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c3.setPaddingTop(8f);
    projectsHeader.addCell(c3);

    Paragraph para4 = new Paragraph(new Phrase("Ready To Bill Date", smallBold));
    para4.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c4 = new PdfPCell(para4);
    c4.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c4.setPaddingTop(8f);
    projectsHeader.addCell(c4);

    Paragraph para7 = new Paragraph(new Phrase("No. Days Past Due", smallBold));
    para7.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c7 = new PdfPCell(para7);
    c7.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c7.setPaddingTop(8f);
    projectsHeader.addCell(c7);


    Paragraph para8 = new Paragraph(new Phrase("Billed Date", smallBold));
    para8.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c8 = new PdfPCell(para8);
    c8.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
    c8.setPaddingTop(15f);
    projectsHeader.addCell(c8);

    Paragraph para9 = new Paragraph(new Phrase("No. Days Past Due", smallBold));
    para9.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c9 = new PdfPCell(para9);
    c9.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);
    c9.setPaddingTop(8f);
    projectsHeader.addCell(c9);

    return projectsHeader;
}

public PdfPTable billingReportProjectsFooterStyle() {
    footer = new PdfPTable(columnParams1);

    footer.setWidthPercentage(100f);
    footer.setTotalWidth(800f);
    Date today = new Date();
    Format formatter = new SimpleDateFormat("E, MMM dd, yyyy hh:mm:ss aaa");
    ph1 = new Phrase(formatter.format(today), smallBold);
    PdfPCell dateFooter = new PdfPCell(ph1);
    dateFooter.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
    dateFooter.setBorder(0);
    footer.addCell(dateFooter);
    return footer;
}

public PdfPTable overallBillingReportProjectsHeaderStyle() {
    headerDisp = new PdfPTable(columnParams1);
    headerDisp.setTotalWidth(800);
    headerDisp.setSpacingBefore(10f);
    Phrase catPhrase1 = new Phrase("Billing Report", headerFont);
    Paragraph plannerPara1 = new Paragraph(catPhrase1);
    PdfPCell c = new PdfPCell(plannerPara1);
    c.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
    c.setBorder(Rectangle.NO_BORDER);
    headerDisp.addCell(c);
    return headerDisp;

}

public PdfPTable profitReportHeaderStyle() {

    projectsHeader = new PdfPTable(columnParams6);
    projectsHeader.setWidthPercentage(100f);
    projectsHeader.setTotalWidth(800f);


    PdfPCell c = new PdfPCell();
    c.setFixedHeight(40f);


    Paragraph para1 = new Paragraph(new Phrase("Job ID / Name", smallBold));
    para1.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c1 = new PdfPCell(para1);
    c1.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
    c1.setFixedHeight(40f);
    c1.setPaddingTop(15f);
    projectsHeader.addCell(c1);

    Paragraph para2 = new Paragraph(new Phrase("Customer Name", smallBold));
    para2.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c2 = new PdfPCell(para2);
    c2.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c2.setPaddingTop(15f);
    projectsHeader.addCell(c2);

    Paragraph para5 = new Paragraph(new Phrase("Planner", smallBold));
    para5.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c5 = new PdfPCell(para5);
    c5.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c5.setPaddingTop(15f);
    projectsHeader.addCell(c5);

    Paragraph para6 = new Paragraph(new Phrase("Hybrid PM", smallBold));
    para6.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c6 = new PdfPCell(para6);
    c6.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c6.setPaddingTop(15f);
    projectsHeader.addCell(c6);

    Paragraph para3 = new Paragraph(new Phrase("Invoice Amount", smallBold));
    para3.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c3 = new PdfPCell(para3);
    c3.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c3.setPaddingTop(8f);
    projectsHeader.addCell(c3);

    Paragraph para4 = new Paragraph(new Phrase("Actual Cost", smallBold));
    para4.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c4 = new PdfPCell(para4);
    c4.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c4.setPaddingTop(15f);
    projectsHeader.addCell(c4);

    Paragraph para7 = new Paragraph(new Phrase("Profit %", smallBold));
    para7.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c7 = new PdfPCell(para7);
    c7.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);
    c7.setPaddingTop(15f);
    projectsHeader.addCell(c7);

    return projectsHeader;
}

public PdfPTable profitReportFooterStyle() {
    footer = new PdfPTable(columnParams1);

    footer.setWidthPercentage(100f);
    footer.setTotalWidth(800f);
    Date today = new Date();
    Format formatter = new SimpleDateFormat("E, MMM dd, yyyy hh:mm:ss aaa");
    ph1 = new Phrase(formatter.format(today), smallBold);
    PdfPCell dateFooter = new PdfPCell(ph1);
    dateFooter.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
    dateFooter.setBorder(0);
    footer.addCell(dateFooter);
    return footer;
}

public PdfPTable overallProfitReportHeaderStyle() {
    headerDisp = new PdfPTable(columnParams1);
    headerDisp.setTotalWidth(800);
    headerDisp.setSpacingBefore(10f);
    Phrase catPhrase1 = new Phrase("Profit Report", headerFont);
    Paragraph plannerPara1 = new Paragraph(catPhrase1);
    PdfPCell c = new PdfPCell(plannerPara1);
    c.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
    c.setBorder(Rectangle.NO_BORDER);
    headerDisp.addCell(c);
    return headerDisp;

}

public PdfPTable productivityReportHeaderStyle() {

    projectsHeader = new PdfPTable(columnParams7);
    projectsHeader.setWidthPercentage(100f);
    projectsHeader.setTotalWidth(800f);


    PdfPCell c = new PdfPCell();
    c.setFixedHeight(40f);


    Paragraph para1 = new Paragraph(new Phrase("Emp Name / ID", smallBold));
    para1.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c1 = new PdfPCell(para1);
    c1.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
    c1.setFixedHeight(40f);
    c1.setPaddingTop(15f);
    projectsHeader.addCell(c1);

    Paragraph para2 = new Paragraph(new Phrase("Project Activity", smallBold));
    para2.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c2 = new PdfPCell(para2);
    c2.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c2.setPaddingTop(15f);
    projectsHeader.addCell(c2);


    Paragraph para3 = new Paragraph(new Phrase("General Activity", smallBold));
    para3.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c3 = new PdfPCell(para3);
    c3.setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM);
    c3.setPaddingTop(15f);
    projectsHeader.addCell(c3);
    

    return projectsHeader;
}

public PdfPTable productivityReportFooterStyle() {
    footer = new PdfPTable(columnParams1);

    footer.setWidthPercentage(100f);
    footer.setTotalWidth(800f);
    Date today = new Date();
    Format formatter = new SimpleDateFormat("E, MMM dd, yyyy hh:mm:ss aaa");
    ph1 = new Phrase(formatter.format(today), smallBold);
    PdfPCell dateFooter = new PdfPCell(ph1);
    dateFooter.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
    dateFooter.setBorder(0);
    footer.addCell(dateFooter);
    return footer;
}

public PdfPTable overallProductivityReportHeaderStyle() {
    headerDisp = new PdfPTable(columnParams1);
    headerDisp.setTotalWidth(800);
    headerDisp.setSpacingBefore(10f);
    Phrase catPhrase1 = new Phrase("Productivity Report", headerFont);
    Paragraph plannerPara1 = new Paragraph(catPhrase1);
    PdfPCell c = new PdfPCell(plannerPara1);
    c.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
    c.setBorder(Rectangle.NO_BORDER);
    headerDisp.addCell(c);
    return headerDisp;

}

public PdfPTable projErrorCostReportHeaderStyle() {

    projectsHeader = new PdfPTable(columnParams8);
    projectsHeader.setWidthPercentage(100f);
    projectsHeader.setTotalWidth(800f);


    PdfPCell c = new PdfPCell();
    c.setFixedHeight(40f);

    Paragraph para0 = new Paragraph(new Phrase("Invoiced Month Year", smallBold));
    para0.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c0 = new PdfPCell(para0);
    c0.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
    c0.setFixedHeight(40f);
    c0.setPaddingTop(15f);
    projectsHeader.addCell(c0);


    Paragraph para1 = new Paragraph(new Phrase("Invoiced Amount", smallBold));
    para1.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c1 = new PdfPCell(para1);
    c1.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c1.setFixedHeight(40f);
    c1.setPaddingTop(15f);
    projectsHeader.addCell(c1);

    Paragraph para2 = new Paragraph(new Phrase("Dubuque ($)", smallBold));
    para2.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c2 = new PdfPCell(para2);
    c2.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c2.setPaddingTop(15f);
    projectsHeader.addCell(c2);

    Paragraph para5 = new Paragraph(new Phrase("Dubuque (%)", smallBold));
    para5.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c5 = new PdfPCell(para5);
    c5.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c5.setPaddingTop(15f);
    projectsHeader.addCell(c5);

    Paragraph para6 = new Paragraph(new Phrase("Chennai ($)", smallBold));
    para6.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c6 = new PdfPCell(para6);
    c6.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c6.setPaddingTop(15f);
    projectsHeader.addCell(c6);

    Paragraph para3 = new Paragraph(new Phrase("Chennai (%)", smallBold));
    para3.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c3 = new PdfPCell(para3);
    c3.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c3.setPaddingTop(15f);
    projectsHeader.addCell(c3);

    Paragraph para4 = new Paragraph(new Phrase("Vendor ($)", smallBold));
    para4.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c4 = new PdfPCell(para4);
    c4.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c4.setPaddingTop(15f);
    projectsHeader.addCell(c4);

    Paragraph para7 = new Paragraph(new Phrase("Vendor (%)", smallBold));
    para7.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c7 = new PdfPCell(para7);
    c7.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);
    c7.setPaddingTop(15f);
    projectsHeader.addCell(c7);

    return projectsHeader;
}

public PdfPTable projErrorCostReportFooterStyle() {
    footer = new PdfPTable(columnParams1);

    footer.setWidthPercentage(100f);
    footer.setTotalWidth(800f);
    Date today = new Date();
    Format formatter = new SimpleDateFormat("E, MMM dd, yyyy hh:mm:ss aaa");
    ph1 = new Phrase(formatter.format(today), smallBold);
    PdfPCell dateFooter = new PdfPCell(ph1);
    dateFooter.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
    dateFooter.setBorder(0);
    footer.addCell(dateFooter);
    return footer;
}

public PdfPTable overallProjErrorCostReportHeaderStyle() {
    headerDisp = new PdfPTable(columnParams1);
    headerDisp.setTotalWidth(800);
    headerDisp.setSpacingBefore(10f);
    Phrase catPhrase1 = new Phrase("Project Error Cost Report", headerFont);
    Paragraph plannerPara1 = new Paragraph(catPhrase1);
    PdfPCell c = new PdfPCell(plannerPara1);
    c.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
    c.setBorder(Rectangle.NO_BORDER);
    headerDisp.addCell(c);
    return headerDisp;
}

public PdfPTable jobLostReportHeaderStyle() {

    projectsHeader = new PdfPTable(columnParams9);
    projectsHeader.setWidthPercentage(100f);
    projectsHeader.setTotalWidth(800f);


    PdfPCell c = new PdfPCell();
    c.setFixedHeight(40f);

    Paragraph para0 = new Paragraph(new Phrase("Project ID / Name", smallBold));
    para0.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c0 = new PdfPCell(para0);
    c0.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
    c0.setFixedHeight(40f);
    c0.setPaddingTop(15f);
    projectsHeader.addCell(c0);


    Paragraph para1 = new Paragraph(new Phrase("Project Type", smallBold));
    para1.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c1 = new PdfPCell(para1);
    c1.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c1.setFixedHeight(40f);
    c1.setPaddingTop(15f);
    projectsHeader.addCell(c1);

    Paragraph para2 = new Paragraph(new Phrase("Customer", smallBold));
    para2.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c2 = new PdfPCell(para2);
    c2.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c2.setPaddingTop(15f);
    projectsHeader.addCell(c2);

    Paragraph para3 = new Paragraph(new Phrase("Estimate Cost", smallBold));
    para3.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c3 = new PdfPCell(para3);
    c3.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c3.setPaddingTop(15f);
    projectsHeader.addCell(c3);

    Paragraph para4 = new Paragraph(new Phrase("Invoice Cost", smallBold));
    para4.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c4 = new PdfPCell(para4);
    c4.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
    c4.setPaddingTop(15f);
    projectsHeader.addCell(c4);

    Paragraph para5 = new Paragraph(new Phrase("Job Lost Reason", smallBold));
    para5.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
    PdfPCell c5 = new PdfPCell(para5);
    c5.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);
    c5.setPaddingTop(15f);
    projectsHeader.addCell(c5);


    return projectsHeader;
}

public PdfPTable jobLostReportFooterStyle() {
    footer = new PdfPTable(columnParams1);
    footer.setWidthPercentage(100f);
    footer.setTotalWidth(800f);
    Date today = new Date();
    Format formatter = new SimpleDateFormat("E, MMM dd, yyyy hh:mm:ss aaa");
    ph1 = new Phrase(formatter.format(today), smallBold);
    PdfPCell dateFooter = new PdfPCell(ph1);
    dateFooter.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
    dateFooter.setBorder(0);
    footer.addCell(dateFooter);
    return footer;
}

public PdfPTable overallJobLostReportHeaderStyle() {
    headerDisp = new PdfPTable(columnParams1);
    headerDisp.setTotalWidth(800);
    headerDisp.setSpacingBefore(10f);
    Phrase catPhrase1 = new Phrase("Job Lost Report", headerFont);
    Paragraph plannerPara1 = new Paragraph(catPhrase1);
    PdfPCell c = new PdfPCell(plannerPara1);
    c.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
    c.setBorder(Rectangle.NO_BORDER);
    headerDisp.addCell(c);
    return headerDisp;
}


}
