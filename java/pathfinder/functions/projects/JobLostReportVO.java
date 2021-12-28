/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.projects;

import java.util.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import pathfinder.functions.PlannerPdfHeader;

/**
 *
 * @author Parameshwarant
 */
public class JobLostReportVO extends PdfPageEventHelper {

    PdfPTable header;
    PdfPTable footer;
    PdfPTable headerDisp;
    PlannerPdfHeader headerClass = new PlannerPdfHeader();
    int n = 0;
    static Font bold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);

    List jobIdList = new ArrayList();
    List jobTypeList = new ArrayList();
    List customerList = new ArrayList();
    List jobLostReasonList = new ArrayList();
    List jobEstCostList = new ArrayList();
    List jobInvCostList = new ArrayList();
    String projSearchFlag = "";
    String projCreateDateFrom = "";
    String projCreateDateTo = "";
    String projType = "";

    public JobLostReportVO() {

        header = headerClass.jobLostReportHeaderStyle();
        footer = headerClass.jobLostReportFooterStyle();
        headerDisp = headerClass.overallJobLostReportHeaderStyle();

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

    public void setProjSearchFlag(String projSearchFlag) {
        this.projSearchFlag = projSearchFlag;
    }

    public String getProjSearchFlag() {
        return projSearchFlag;
    }

    public void setProjCreateDateFrom(String projCreateDateFrom) {
        this.projCreateDateFrom = projCreateDateFrom;
    }

    public String getProjCreateDateFrom() {
        return projCreateDateFrom;
    }

    public void setProjCreateDateTo(String projCreateDateTo) {
        this.projCreateDateTo = projCreateDateTo;
    }

    public String getProjCreateDateTo() {
        return projCreateDateTo;
    }

    public void setProjType(String projType) {
        this.projType = projType;
    }

    public String getProjType() {
        return projType;
    }

    public void setJobIdList(List jobIdList) {
        this.jobIdList = jobIdList;
    }

    public List getJobIdList() {
        return jobIdList;
    }

    public void setJobTypeList(List jobTypeList) {
        this.jobTypeList = jobTypeList;
    }

    public List getJobTypeList() {
        return jobTypeList;
    }

    public void setCustomerList(List customerList) {
        this.customerList = customerList;
    }

    public List getCustomerList() {
        return customerList;
    }

    public void setJobLostReasonList(List jobLostReasonList) {
        this.jobLostReasonList = jobLostReasonList;
    }

    public List getJobLostReasonList() {
        return jobLostReasonList;
    }

    public void setJobEstCostList(List jobEstCostList) {
        this.jobEstCostList = jobEstCostList;
    }

    public List getJobEstCostList() {
        return jobEstCostList;
    }

    public void setJobInvCostList(List jobInvCostList) {
        this.jobInvCostList = jobInvCostList;
    }

    public List getJobInvCostList() {
        return jobInvCostList;
    }
}
