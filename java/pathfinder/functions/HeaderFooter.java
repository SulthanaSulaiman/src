/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author Aravindhanj
 */
public class HeaderFooter extends PdfPageEventHelper{
    
    //font
 
     PdfPTable header;
PdfHeader headerClass=new PdfHeader();
static Font bold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);

public HeaderFooter() {
    
header=headerClass.headerStyle(1);
}
@Override
public void onEndPage(PdfWriter writer, Document document) {
PdfContentByte cb = writer.getDirectContent();
if (document.getPageNumber() > 1) {
header.writeSelectedRows(0, -1,
(document.right() - document.left() - 810) /2
+ document.leftMargin(), document.top()+60, cb);
}
ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(String.format("%d", writer.getCurrentPageNumber()),bold),
        (document.right() - document.left()+755)/ 2 + document.leftMargin(), document.top()-490, 0);
/*
footer.writeSelectedRows(0, -1,
(document.right() - document.left() - 300) /2
+ document.leftMargin(), document.bottom() - 10, cb);
* */
}


}