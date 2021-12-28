/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author Parameshwarant
 */
public class ProjErrorCostHeaderFooterDisp extends PdfPageEventHelper {

    PdfPTable header;
    PdfPTable footer;
    PdfPTable headerDisp;
    PlannerPdfHeader headerClass = new PlannerPdfHeader();
    int n = 0;
    static Font bold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);

    public ProjErrorCostHeaderFooterDisp() {

        header = headerClass.projErrorCostReportHeaderStyle();
        footer = headerClass.projErrorCostReportFooterStyle();
        headerDisp = headerClass.overallProjErrorCostReportHeaderStyle();

    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        n = writer.getCurrentPageNumber();

        PdfContentByte cb = writer.getDirectContent();
        if (document.getPageNumber() > 1) {
            header.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() + 50, cb);
            footer.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() - 470, cb);
            headerDisp.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() + 75, cb);


            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(String.format("%d", n), bold),
                    (document.right() - document.left() + 755) / 2 + document.leftMargin(), document.top() - 490, 0);


        }
    }
}
