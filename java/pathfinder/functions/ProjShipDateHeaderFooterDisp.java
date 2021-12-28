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
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.SQLException;


public class ProjShipDateHeaderFooterDisp extends PdfPageEventHelper {

PdfPTable header;
PdfPTable footer;
PdfPTable headerDisp;
PdfPTable footerStatus;
PdfPTable table;
PdfPTable plannerName;

PlannerPdfHeader headerClass=new PlannerPdfHeader();
PdfWriter writer;
PdfTemplate total;
int n=0;
 static Font smallBold = new Font(Font.FontFamily.HELVETICA, 9);
 static Font bold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);

public ProjShipDateHeaderFooterDisp() throws SQLException {

header=headerClass.projectsHeaderStyle();
footer=headerClass.projectsFooterStyle();
headerDisp=headerClass.overallProjectsHeaderStyle();


}

@Override
public void onEndPage(PdfWriter writer, Document document) {
n=writer.getCurrentPageNumber();

PdfContentByte cb = writer.getDirectContent();
if (document.getPageNumber() > 1) {
header.writeSelectedRows(0, -1,
(document.right() - document.left() - 800) /2
+ document.leftMargin(), document.top()+50, cb);
footer.writeSelectedRows(0, -1,
(document.right() - document.left() - 800) /2
+ document.leftMargin(), document.top()-470, cb);
headerDisp.writeSelectedRows(0, -1,
(document.right() - document.left() - 800) /2
+ document.leftMargin(), document.top()+75, cb);


ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(String.format("%d", n),bold),
        (document.right() - document.left()+755)/ 2 + document.leftMargin(), document.top()-490, 0);


}
}

}
