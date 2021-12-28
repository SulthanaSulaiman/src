/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects.chapters;

import com.itextpdf.text.pdf.PdfContentByte;
import java.util.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author Aravindhanj
 */
public class ProductionOnTimeVO extends PdfPageEventHelper {

    static Font bold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(String.format("%d", writer.getCurrentPageNumber()), bold),
                (document.right() - document.left() + 530) / 2 + document.leftMargin(), document.top() - 780, 0);

        }


    private String dueStartDate = "";
    private String dueEndDate = "";
    private String plannerId = "";
    private String hybridPlannerId = "";
    private String customerId = "";
    private String projectId = "";

    private List chapterIdList = new ArrayList();
    private List projectIdList = new ArrayList();
    private List projectNameList = new ArrayList();
    private List customerCompanyList = new ArrayList();
    private List projPlannerList = new ArrayList();
    private List projHybridPlannerList = new ArrayList();
    private List chapterStageList = new ArrayList();
    private List chapterNameList = new ArrayList();
    private List chapterDueDateList = new ArrayList();
    private List chapterActualDateList = new ArrayList();
    private List dayDiffList = new ArrayList();

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getHybridPlannerId() {
        return hybridPlannerId;
    }

    public void setHybridPlannerId(String hybridPlannerId) {
        this.hybridPlannerId = hybridPlannerId;
    }

    public String getPlannerId() {
        return plannerId;
    }

    public void setPlannerId(String plannerId) {
        this.plannerId = plannerId;
    }

    public String getDueEndDate() {
        return dueEndDate;
    }

    public void setDueEndDate(String dueEndDate) {
        this.dueEndDate = dueEndDate;
    }

    public String getDueStartDate() {
        return dueStartDate;
    }

    public void setDueStartDate(String dueStartDate) {
        this.dueStartDate = dueStartDate;
    }

    public List getChapterActualDateList() {
        return chapterActualDateList;
    }

    public void setChapterActualDateList(List chapterActualDateList) {
        this.chapterActualDateList = chapterActualDateList;
    }

    public List getChapterDueDateList() {
        return chapterDueDateList;
    }

    public void setChapterDueDateList(List chapterDueDateList) {
        this.chapterDueDateList = chapterDueDateList;
    }

    public List getChapterIdList() {
        return chapterIdList;
    }

    public void setChapterIdList(List chapterIdList) {
        this.chapterIdList = chapterIdList;
    }

    public List getChapterNameList() {
        return chapterNameList;
    }

    public void setChapterNameList(List chapterNameList) {
        this.chapterNameList = chapterNameList;
    }

    public List getChapterStageList() {
        return chapterStageList;
    }

    public void setChapterStageList(List chapterStageList) {
        this.chapterStageList = chapterStageList;
    }

    public List getCustomerCompanyList() {
        return customerCompanyList;
    }

    public void setCustomerCompanyList(List customerCompanyList) {
        this.customerCompanyList = customerCompanyList;
    }

    public List getDayDiffList() {
        return dayDiffList;
    }

    public void setDayDiffList(List dayDiffList) {
        this.dayDiffList = dayDiffList;
    }

    public List getProjPlannerList() {
        return projPlannerList;
    }

    public void setProjPlannerList(List projPlannerList) {
        this.projPlannerList = projPlannerList;
    }

    public List getProjHybridPlannerList() {
        return projHybridPlannerList;
    }

    public void setProjHybridPlannerList(List projHybridPlannerList) {
        this.projHybridPlannerList = projHybridPlannerList;
    }

    public List getProjectIdList() {
        return projectIdList;
    }

    public void setProjectIdList(List projectIdList) {
        this.projectIdList = projectIdList;
    }

    public List getProjectNameList() {
        return projectNameList;
    }

    public void setProjectNameList(List projectNameList) {
        this.projectNameList = projectNameList;
    }

}
