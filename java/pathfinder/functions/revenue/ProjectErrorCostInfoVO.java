/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import java.util.*;

/**
 *
 * @author Parameshwarant
 */
public class ProjectErrorCostInfoVO {

    List invoiceMonthList = new ArrayList();
    List invoiceYearList = new ArrayList();
    List monthInvoiceValueList = new ArrayList();
    List chennaiErrorList = new ArrayList();
    List dubuqueErrorList = new ArrayList();
    List vendorErrorList = new ArrayList();
    List invoiceMonthAndYearList = new ArrayList();
    List invoiceDate = new ArrayList();
    String invoiceDateTo = "";
    String invoiceDateFrom = "";
    String invoiceDateChk = "";
    String facilityId = "";
    String invoiceMonth = "";
    String invoiceYear = "";
    String fromServletPdfChk = "";

    public void setInvoiceMonthList(List invoiceMonthList) {
        this.invoiceMonthList = invoiceMonthList;
    }

    public List getInvoiceMonthList() {
        return invoiceMonthList;
    }

    public void setInvoiceYearList(List invoiceYearList) {
        this.invoiceYearList = invoiceYearList;
    }

    public List getInvoiceYearList() {
        return invoiceYearList;
    }

    public void setInvoiceValueList(List monthInvoiceValueList) {
        this.monthInvoiceValueList = monthInvoiceValueList;
    }

    public List getInvoiceValueList() {
        return monthInvoiceValueList;
    }

    public void setInvoiceDateFrom(String invoiceDateFrom) {
        this.invoiceDateFrom = invoiceDateFrom;
    }

    public String getInvoiceDateFrom() {
        return invoiceDateFrom;
    }

    public void setInvoiceDateTo(String invoiceDateTo) {
        this.invoiceDateTo = invoiceDateTo;
    }

    public String getInvoiceDateTo() {
        return invoiceDateTo;
    }

    public void setInvoiceDateChk(String invoiceDateChk) {
        this.invoiceDateChk = invoiceDateChk;
    }

    public String getInvoiceDateChk() {
        return invoiceDateChk;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public void setChnErrorList(List chennaiErrorList) {
        this.chennaiErrorList = chennaiErrorList;
    }

    public List getChnErrorList() {
        return chennaiErrorList;
    }

    public void setDbqErrorList(List dubuqueErrorList) {
        this.dubuqueErrorList = dubuqueErrorList;
    }

    public List getDbqErrorList() {
        return dubuqueErrorList;
    }

    public void setVendorErrorList(List vendorErrorList) {
        this.vendorErrorList = vendorErrorList;
    }

    public List getVendorErrorList() {
        return vendorErrorList;
    }

    public void setInvoiceMonth(String invoiceMonth) {
        this.invoiceMonth = invoiceMonth;
    }

    public String getInvoiceMonth() {
        return invoiceMonth;
    }

    public void setInvoiceYear(String invoiceYear) {
        this.invoiceYear = invoiceYear;
    }

    public String getInvoiceYear() {
        return invoiceYear;
    }

    public void setFromServletPdf(String fromServletPdfChk) {
        this.fromServletPdfChk = fromServletPdfChk;
    }

    public String getFromServletPdf() {
        return fromServletPdfChk;
    }

    public void setInvoiceMonthYearList(List invoiceMonthAndYearList) {
        this.invoiceMonthAndYearList = invoiceMonthAndYearList;
    }

    public List getInvoiceMonthYearList() {
        return invoiceMonthAndYearList;
    }

    public void setInvoiceDateList(List invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public List getInvoiceDateList() {
        return invoiceDate;
    }
}
