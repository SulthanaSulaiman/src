/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

/**
 *
 * @author rajac
 */
public class InvoiceLine {

    String estimateQuantity;
    String invoiceQuantity;
    String category;
    String description;
    String unitPrice;
    String estimatedValue;
    String actualValue;
    String previouslyInvoicedValue;
    String invoiceTotal;
    String estimateId;
    String noteDescription;
    String lineItemNo;

    public String getEstimateId() {
        return estimateId;
    }

    public void setEstimateId(String estimateId) {
        this.estimateId = estimateId;
    }

    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEstimateQuantity() {
        return estimateQuantity;
    }

    public void setEstimateQuantity(String estimateQuantity) {
        this.estimateQuantity = estimateQuantity;
    }

    public String getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(String estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public String getInvoiceQuantity() {
        return invoiceQuantity;
    }

    public void setInvoiceQuantity(String invoiceQuantity) {
        this.invoiceQuantity = invoiceQuantity;
    }

    public String getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(String invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public String getPreviouslyInvoicedValue() {
        return previouslyInvoicedValue;
    }

    public void setPreviouslyInvoicedValue(String previouslyInvoicedValue) {
        this.previouslyInvoicedValue = previouslyInvoicedValue;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public void setLineItemNo(String lineItemNo)
    {
        this.lineItemNo=lineItemNo;
    }

    public String getLineItemNo()
    {
        return lineItemNo;
    }
}
