/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;

import java.util.ArrayList;

/**
 *
 * @author Aravindhanj
 */
public class TransmittalVO {
    String projId = "";
    String projName = "";
    String shipId = "";
    String mailDate = "";
    String addressType = "";
    String carrier = "";
    String shipWithAcct = "";
    String job = "";
    String author = "";
    String title = "";
    String isbn10 = "";
    String isbn13 = "";
    String option = "";
    String reference = "";
    String remarks = "";
    String contentNote = "";
    String packageContent = "";
    String empId = "";
    String receiver = "";
    String transmittalId = "";
    String shippingId = "";
    String noteId = "";
    String shipMethod = "";
    String shipMethodId = "";
    String boundType = "";
    String inBoundFlag = "";
    String carrierTrackNo = "";
    String carrierCost = "";
    String totalCarrierCost = "";
    String shipToLocation = "";
    String shipFromLocation = "";
    String requestDate = "";
    String shipTo = "";
    String shipToAttention = "";
    String shipDivision = "";
    String shipDivisionName = "";
    String shipFrom = "";
    String shipToName = "";
    String shipFromName = "";

    String Contact = "";
    String dispEmpName = "";
    String dispEmpDesig = "";
    String dispContact = "";
    String dispContactRef = "";
    String dispContactAdd = "";
    String dispContactCity = "";
    String dispContactCountry = "";
    String dispContactCityCode = "";
    String dispShipToAttention = "";

    int result = 0;
    ArrayList dispShippingId = new ArrayList();
    ArrayList dispTransmittalDate = new ArrayList();
    ArrayList dispShipFrom = new ArrayList();
    ArrayList dispShipTo = new ArrayList();
    ArrayList dispCarrier = new ArrayList();
    ArrayList dispShipMethod = new ArrayList();
    ArrayList dispCarrierId = new ArrayList();
    ArrayList dispCarrierName = new ArrayList();
    ArrayList dispTransmittalType = new ArrayList();

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public ArrayList getDispCarrier() {
        return dispCarrier;
    }

    public String getShipDivision() {
        return shipDivision;
    }

    public void setShipDivision(String shipDivision) {
        this.shipDivision = shipDivision;
    }

    public String getShipDivisionName() {
        return shipDivisionName;
    }

    public void setShipDivisionName(String shipDivisionName) {
        this.shipDivisionName = shipDivisionName;
    }

    public void setDispCarrier(ArrayList dispCarrier) {
        this.dispCarrier = dispCarrier;
    }

    public ArrayList getDispShipFrom() {
        return dispShipFrom;
    }

    public void setDispShipFrom(ArrayList dispShipFrom) {
        this.dispShipFrom = dispShipFrom;
    }

    public ArrayList getDispShipMethod() {
        return dispShipMethod;
    }

    public void setDispShipMethod(ArrayList dispShipMethod) {
        this.dispShipMethod = dispShipMethod;
    }

    public ArrayList getDispShipTo() {
        return dispShipTo;
    }

    public void setDispShipTo(ArrayList dispShipTo) {
        this.dispShipTo = dispShipTo;
    }

    public ArrayList getDispShippingId() {
        return dispShippingId;
    }

    public void setDispShippingId(ArrayList dispShippingId) {
        this.dispShippingId = dispShippingId;
    }

    public ArrayList getDispTransmittalDate() {
        return dispTransmittalDate;
    }

    public void setDispTransmittalDate(ArrayList dispTransmittalDate) {
        this.dispTransmittalDate = dispTransmittalDate;
    }

    public String getContentNote() {
        return contentNote;
    }

    public void setContentNote(String contentNote) {
        this.contentNote = contentNote;
    }

    public String getPackageContent() {
        return packageContent;
    }

    public void setPackageContent(String packageContent) {
        this.packageContent = packageContent;
    }

    public String getBoundType() {
        return boundType;
    }

    public void setBoundType(String boundType) {
        this.boundType = boundType;
    }

    public String getCarrierCost() {
        return carrierCost;
    }

    public void setCarrierCost(String carrierCost) {
        this.carrierCost = carrierCost;
    }

    public String getTotalCarrierCost() {
        return totalCarrierCost;
    }

    public void setTotalCarrierCost(String totalCarrierCost) {
        this.totalCarrierCost = totalCarrierCost;
    }

    public String getCarrierTrackNo() {
        return carrierTrackNo;
    }

    public void setCarrierTrackNo(String carrierTrackNo) {
        this.carrierTrackNo = carrierTrackNo;
    }

    public String getInBoundFlag() {
        return inBoundFlag;
    }

    public void setInBoundFlag(String inBoundFlag) {
        this.inBoundFlag = inBoundFlag;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getShipFrom() {
        return shipFrom;
    }

    public void setShipFrom(String shipFrom) {
        this.shipFrom = shipFrom;
    }

    public String getShipFromLocation() {
        return shipFromLocation;
    }

    public void setShipFromLocation(String shipFromLocation) {
        this.shipFromLocation = shipFromLocation;
    }

    public String getShipMethod() {
        return shipMethod;
    }

    public void setShipMethod(String shipMethod) {
        this.shipMethod = shipMethod;
    }

    public String getShipMethodId() {
        return shipMethodId;
    }

    public void setShipMethodId(String shipMethodId) {
        this.shipMethodId = shipMethodId;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public String getShipToAttention() {
        return shipToAttention;
    }

    public void setShipToAttention(String shipToAttention) {
        this.shipToAttention = shipToAttention;
    }

    public String getShipToLocation() {
        return shipToLocation;
    }

    public void setShipToLocation(String shipToLocation) {
        this.shipToLocation = shipToLocation;
    }

    public String getShippingId() {
        return shippingId;
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
    }

    public String getTransmittalId() {
        return transmittalId;
    }

    public void setTransmittalId(String transmittalId) {
        this.transmittalId = transmittalId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
    
    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMailDate() {
        return mailDate;
    }

    public void setMailDate(String mailDate) {
        this.mailDate = mailDate;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getShipId() {
        return shipId;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getShipWithAcct() {
        return shipWithAcct;
    }

    public void setShipWithAcct(String shipWithAcct) {
        this.shipWithAcct = shipWithAcct;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList getDispCarrierId() {
        return dispCarrierId;
    }

    public void setDispCarrierId(ArrayList dispCarrierId) {
        this.dispCarrierId = dispCarrierId;
    }

    public ArrayList getDispCarrierName() {
        return dispCarrierName;
    }

    public void setDispCarrierName(ArrayList dispCarrierName) {
        this.dispCarrierName = dispCarrierName;
    }

    public ArrayList getDispTransmittalType() {
        return dispTransmittalType;
    }

    public void setDispTransmittalType(ArrayList dispTransmittalType) {
        this.dispTransmittalType = dispTransmittalType;
    }

    public String getShipFromName() {
        return shipFromName;
    }

    public void setShipFromName(String shipFromName) {
        this.shipFromName = shipFromName;
    }

    public String getShipToName() {
        return shipToName;
    }

    public void setShipToName(String shipToName) {
        this.shipToName = shipToName;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String Contact) {
        this.Contact = Contact;
    }

    public String getDispContact() {
        return dispContact;
    }

    public void setDispContact(String dispContact) {
        this.dispContact = dispContact;
    }

    public String getDispContactAdd() {
        return dispContactAdd;
    }

    public void setDispContactAdd(String dispContactAdd) {
        this.dispContactAdd = dispContactAdd;
    }

    public String getDispContactCity() {
        return dispContactCity;
    }

    public void setDispContactCity(String dispContactCity) {
        this.dispContactCity = dispContactCity;
    }

    public String getDispContactCityCode() {
        return dispContactCityCode;
    }

    public void setDispContactCityCode(String dispContactCityCode) {
        this.dispContactCityCode = dispContactCityCode;
    }

    public String getDispContactCountry() {
        return dispContactCountry;
    }

    public void setDispContactCountry(String dispContactCountry) {
        this.dispContactCountry = dispContactCountry;
    }

    public String getDispContactRef() {
        return dispContactRef;
    }

    public void setDispContactRef(String dispContactRef) {
        this.dispContactRef = dispContactRef;
    }

    public String getDispEmpDesig() {
        return dispEmpDesig;
    }

    public void setDispEmpDesig(String dispEmpDesig) {
        this.dispEmpDesig = dispEmpDesig;
    }

    public String getDispEmpName() {
        return dispEmpName;
    }

    public void setDispEmpName(String dispEmpName) {
        this.dispEmpName = dispEmpName;
    }

    public String getDispShipToAttention() {
        return dispShipToAttention;
    }

    public void setDispShipToAttention(String dispShipToAttention) {
        this.dispShipToAttention = dispShipToAttention;
    }
    
}
