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
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import connection.DBconnection;
import java.sql.Connection;
import pathfinder.functions.projects.TitlesToPrinterRpt;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class ProjShipDateHeaderFooter {
    
    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 10, Font.UNDERLINE | Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 9);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 9);
    TitlesToPrinterRpt proj = new TitlesToPrinterRpt();
    DBconnection db = new DBconnection();
    Connection con = db.getSampleProperty();
    PdfPTable footer;
    PdfPTable headerDisp;
    PdfPTable plannerHeader;
    Phrase ph1;
    
    float[] columnParams1 = {24f};
    float[] columnParams2 = {5f, 25f, 12f, 10f, 8f, 6f, 20f, 7f, 7f};
    int count = 0;

    public PdfPTable projectsHeaderStyle() {



        plannerHeader = new PdfPTable(columnParams2);

        plannerHeader.setWidthPercentage(100f);
        plannerHeader.setTotalWidth(800f);


        PdfPCell c = new PdfPCell();
        c.setFixedHeight(40f);


        Paragraph para1 = new Paragraph(new Phrase("Job ID", smallBold));
        para1.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
        PdfPCell c1 = new PdfPCell(para1);
        c1.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
        c1.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
        c1.setFixedHeight(40f);
        plannerHeader.addCell(c1);

        Paragraph para2 = new Paragraph(new Phrase("Book Title", smallBold));
        para2.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
        PdfPCell c2 = new PdfPCell(para2);
        c2.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        c2.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
        plannerHeader.addCell(c2);

        Paragraph para5 = new Paragraph(new Phrase("Planner", smallBold));
        para5.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
        PdfPCell c5 = new PdfPCell(para5);
        c5.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        c5.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
        plannerHeader.addCell(c5);

        Paragraph para6 = new Paragraph(new Phrase("Category", smallBold));
        para6.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
        PdfPCell c6 = new PdfPCell(para6);
        c6.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        c6.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
        plannerHeader.addCell(c6);

        Paragraph para3 = new Paragraph(new Phrase("Type", smallBold));
        para3.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
        PdfPCell c3 = new PdfPCell(para3);
        c3.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        c3.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
        plannerHeader.addCell(c3);

        Paragraph para4 = new Paragraph(new Phrase("Estd. Pages", smallBold));
        para4.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
        PdfPCell c4 = new PdfPCell(para4);
        c4.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        c4.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
        plannerHeader.addCell(c4);

        Paragraph para7 = new Paragraph(new Phrase("Customer/State/Division", smallBold));
        para7.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
        PdfPCell c7 = new PdfPCell(para7);
        c7.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        c7.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
        plannerHeader.addCell(c7);


        Paragraph para8 = new Paragraph(new Phrase("Award Date", smallBold));
        para8.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
        PdfPCell c8 = new PdfPCell(para8);
        c8.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
        c8.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
        plannerHeader.addCell(c8);

        Paragraph para9 = new Paragraph(new Phrase("Due Date", smallBold));
        para9.setAlignment(Phrase.ALIGN_CENTER | Phrase.ALIGN_MIDDLE);
        PdfPCell c9 = new PdfPCell(para9);
        c9.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);
        c9.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
        plannerHeader.addCell(c9);

        return plannerHeader;
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

    public PdfPTable overallHeaderStyle() {
        headerDisp = new PdfPTable(columnParams1);
        headerDisp.setTotalWidth(800);
        headerDisp.setSpacingBefore(10f);
        Phrase catPhrase1 = new Phrase("Projects By Ship Date", headerFont);
        Paragraph plannerPara1 = new Paragraph(catPhrase1);
        PdfPCell c = new PdfPCell(plannerPara1);
        c.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        c.setBorder(Rectangle.NO_BORDER);
        headerDisp.addCell(c);
        return headerDisp;

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
}
