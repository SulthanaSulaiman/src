/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.projects;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;
import pathfinder.functions.contacts.*;
import pathfinder.functions.projects.chapters.ProductionOnTimeVO;

/**
 *
 * @author ramesh
 */
public class ProjIdGeneralInfo implements Serializable {

    private String clientAddress = "";
    private String revisionCount = "";
    private String clientName = "";
    private String clientDivision = "";
    private String clientCode = "";
    private String division = "";
    private String buyer = "";
    private String salesperson = "";
    private String contact = "";
    private String printer = "";
    private String status = "";
    private String projtype = "";
    private String projcategory = "";
    private String projectLevel = "";
    private String discipline = "";
    private String title = "";
    private String edition = "";
    private String name = "";
    private String prjid = "";
    private String chapter_no = "";
    private String addedDate = "";
    private String projectStatus = "";
    private String printedDate = "";
    private String prjEISBNFieldParam = "";
    private String prjEISBNCatValue = "";
    private String priority = "";
    private String copyright = "";
    private String mss = "";
    private String estimated = "";
    private String actual = "";
    private String isbn10 = "";
    private String isbn13 = "";
    private String billLocationId = "";
    private String billLocationName = "";
    private String divisionID = "";
    private String buyerID = "";
    private String salespersonID = "";
    private String contactID = "";
    private String printerID = "";
    private String statusCode = "";
    private String jobLostReasonCode = "";
    private String jobLostReason = "";
    private String projtypeID = "";
    private String projcategoryID = "";
    private String projectLevelID = "";
    private String disciplineID = "";
    private String priorityID = "";
    private String colorID = "";
    private String colorName = "";
    private String workFlowId = "";
    private String workFlowName = "";
    private String stageName = "";
    private String customerPO = "";
    private String customerPODate = "";
     private String eFtpDate = "";
    private String actualShipDate = "";
    private String pageHeight = "";
    private String pageWidth = "";
    private String buyerAddress = "";
    private String buyerCountry = "";
    private String buyerCountryNme = "";
    private String buyerPhone = "";
    private String buyerFax = "";
    private String tempResult = "";
    private String primaryAuthorId = "";
    private String projPrimaryAuthorName = "";
    private String noOfChapters="";
    private String mssFormat="";
    private String paperInfoDisp="";
    private String fstPresent="";
    private String xmlProp="";
    String existing_Server_Id = "";
    String existing_Server_Name = "";
    String existing_Server_Ip = "";
    String existing_Server_uname = "";
    String existing_Server_pword = "";
    String existing_Server_path = "";
    private String vendorNumber = "";
    private List authorCompany = new ArrayList();
    private List authorState = new ArrayList();
    private List authorCountry = new ArrayList();
    private List primaryAuthor = new ArrayList();
    private List authorId = new ArrayList();
    private List authorFirstName = new ArrayList();
    private List projDeliverableId = new ArrayList();
    private List projDeliverable = new ArrayList();
    private List vendorIdList = new ArrayList();
    private List authorsList = new ArrayList();
    private String plannerFacilityId;
    private String currencyCode;
    private String htmlCode;
    private String unicode;
    private String unicodeFormatFlag;
    private String empId = "";
    private String projReopen = "";

    private List plannerIdList = new ArrayList();
    private List plannerNameList = new ArrayList();
    private List hybridPlannerIdList = new ArrayList();
    private List hybridPlannerNameList = new ArrayList();
    private List customerIdList = new ArrayList();
    private List customerNameList = new ArrayList();
    private List paperinfo = new ArrayList();

    public String getCustomerPODate() {
        return customerPODate;
    }

    public void setCustomerPODate(String customerPODate) {
        this.customerPODate = customerPODate;
    }

    public String geteFtpDate() {
        return eFtpDate;
    }

    public void seteFtpDate(String eFtpDate) {
        this.eFtpDate = eFtpDate;
    }

    
    public List getCustomerIdList() {
        return customerIdList;
    }

    public void setCustomerIdList(List customerIdList) {
        this.customerIdList = customerIdList;
    }

    public List getCustomerNameList() {
        return customerNameList;
    }

    public void setCustomerNameList(List customerNameList) {
        this.customerNameList = customerNameList;
    }

    public List getHybridPlannerIdList() {
        return hybridPlannerIdList;
    }

    public void setHybridPlannerIdList(List hybridPlannerIdList) {
        this.hybridPlannerIdList = hybridPlannerIdList;
    }

    public List getHybridPlannerNameList() {
        return hybridPlannerNameList;
    }

    public void setHybridPlannerNameList(List hybridPlannerNameList) {
        this.hybridPlannerNameList = hybridPlannerNameList;
    }

    public List getPlannerIdList() {
        return plannerIdList;
    }

    public void setPlannerIdList(List plannerIdList) {
        this.plannerIdList = plannerIdList;
    }

    public List getPlannerNameList() {
        return plannerNameList;
    }

    public void setPlannerNameList(List plannerNameList) {
        this.plannerNameList = plannerNameList;
    }
    
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getProjReopen() {
        return projReopen;
    }

    public void setProjReopen(String projReopen) {
        this.projReopen = projReopen;
    }
    
    public List getAuthorsList() {
        return authorsList;
    }

    public void setAuthorsList(List authorsList) {
        this.authorsList = authorsList;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getHtmlCode() {
        return htmlCode;
    }

    public void setHtmlCode(String htmlCode) {
        this.htmlCode = htmlCode;
    }

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    public String getUnicodeFormatFlag() {
        return unicodeFormatFlag;
    }

    public void setUnicodeFormatFlag(String unicodeFormatFlag) {
        this.unicodeFormatFlag = unicodeFormatFlag;
    }

    public List getAuthorId() {
        return authorId;
    }

    public void setAuthorId(List authorId) {
        this.authorId = authorId;
    }
    
    public String getPlannerFacilityId() {
        return plannerFacilityId;
    }

    public void setPlannerFacilityId(String plannerFacilityId) {
        this.plannerFacilityId = plannerFacilityId;
    }
    
    

    public List getVendorIdList() {
        return vendorIdList;
    }

    public void setVendorIdList(List vendorIdList) {
        this.vendorIdList = vendorIdList;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getPrintedDate() {
        return printedDate;
    }

    public void setPrintedDate(String printedDate) {
        this.printedDate = printedDate;
    }

    public String getRevisionCount() {
        return revisionCount;
    }

    public void setRevisionCount(String revisionCount) {
        this.revisionCount = revisionCount;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getClientDivision() {
        return clientDivision;
    }

    public void setClientDivision(String clientDivision) {
        this.clientDivision = clientDivision;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }
public String getBuyerAddressCountry() {
        return buyerCountry;
    }
public String getBuyerAddressCountryNme() {
        return buyerCountryNme;
    }

    public String getBuyerFax() {
        return buyerFax;
    }

    public String getCustomerPO() {
        return customerPO;
    }

    public void setCustomerPO(String customerPO) {
        this.customerPO = customerPO;
    }

    public String getActualShipDate() {
        return actualShipDate;
    }

    public void setActualShipDate(String actualShipDate) {
        this.actualShipDate = actualShipDate;
    }

    public List getProjDeliverable() {
        return projDeliverable;
    }

    public void setProjDeliverable(List projDeliverable) {
        this.projDeliverable = projDeliverable;
    }

    public List getProjDeliverableId() {
        return projDeliverableId;
    }

    public void setProjDeliverableId(List projDeliverableId) {
        this.projDeliverableId = projDeliverableId;
    }

    public String getPlanner() {
        return planner;
    }
      public String getHybridPlanner() {
        return hybridPlanner;
    }

    public void setPlanner(String planner) {
        //System.out.println("setplanner called");
        this.planner = planner;
    }
     public void setHybridPlanner(String hybridplanner) {
        //System.out.println("setplanner called");
        this.hybridPlanner = hybridplanner;
    }

    public List getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(List authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public List getAuthorSurName() {
        return authorSurName;
    }

    public String getExisting_Server_Id() {
        return existing_Server_Id;
    }

    public void setExisting_Server_Id(String existing_Server_Id) {
        this.existing_Server_Id = existing_Server_Id;
    }

    public String getExisting_Server_Ip() {
        return existing_Server_Ip;
    }

    public void setExisting_Server_Ip(String existing_Server_Ip) {
        this.existing_Server_Ip = existing_Server_Ip;
    }

    public String getExisting_Server_Name() {
        return existing_Server_Name;
    }

    public void setExisting_Server_Name(String existing_Server_Name) {
        this.existing_Server_Name = existing_Server_Name;
    }

    public String getExisting_Server_path() {
        return existing_Server_path;
    }

    public void setExisting_Server_path(String existing_Server_path) {
        this.existing_Server_path = existing_Server_path;
    }

    public String getExisting_Server_pword() {
        return existing_Server_pword;
    }

    public void setExisting_Server_pword(String existing_Server_pword) {
        this.existing_Server_pword = existing_Server_pword;
    }

    public String getExisting_Server_uname() {
        return existing_Server_uname;
    }

    public void setExisting_Server_uname(String existing_Server_uname) {
        this.existing_Server_uname = existing_Server_uname;
    }

    public void setAuthorSurName(List authorSurName) {
        this.authorSurName = authorSurName;
    }
    private List authorSurName = new ArrayList();
    private List EISBNVal = new ArrayList();
    private List EISBNCategoryName = new ArrayList();
    private String planner = "";
     private String hybridPlanner = "";
    private String projectedPrinterDt = "";
        private String eFtpDt = "";
    private String projectedEstSentDt = "";
    private String chapterId = "";//this has been added to get the proj details of qchapter in the BatchTicketReport
    private String getServer_Id = "";

    public String getFstPresent() {
        return fstPresent;
    }

    public void setFstPresent(String fstPresent) {
        this.fstPresent = fstPresent;
    }

    public String getMssFormat() {
        return mssFormat;
    }
public String getpaperInfoDisp(){
    return paperInfoDisp;
}
public void setpaperInfoDisp(String paperInfoDisp){
    this.paperInfoDisp=paperInfoDisp;
}

    public void setMssFormat(String mssFormat) {
        this.mssFormat = mssFormat;
    }

    public String getNoOfChapters() {
        return noOfChapters;
    }

    public void setNoOfChapters(String noOfChapters) {
        this.noOfChapters = noOfChapters;
    }

    public String getXmlProp() {
        return xmlProp;
    }

    public void setXmlProp(String xmlProp) {
        this.xmlProp = xmlProp;
    }

    public String getProjPrimaryAuthorName() {
        return projPrimaryAuthorName;
    }

    public void setProjPrimaryAuthorName(String projPrimaryAuthorName) {
        this.projPrimaryAuthorName = projPrimaryAuthorName;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }
    private String plannerName = "";
      private String hybridPlannerName = "";
    private int addProj = 0;

    public ProjIdGeneralInfo() {
    }

    public String getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
    }

    public String getWorkFlowName() {
        return workFlowName;
    }

    public void setWorkFlowName(String workFlowName) {
        this.workFlowName = workFlowName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getClient() {
        return clientName;
    }

    public String getVendorNumber() {
        return vendorNumber;
    }

    public String getPlannerId() {
        return planner;
    }

    public String getPlannerName() {
        return plannerName;
    }
      public String getHybridPlannerId() {
        return hybridPlanner;
    }

    public String getHybridPlannerName() {
        return hybridPlannerName;
    }

    public String getProjectedPrinterDt() {
        return projectedPrinterDt;
    }
       public String geteFtpDt() {
        return eFtpDt;
    }

    public String getProjectedEstSentDt() {
        return projectedEstSentDt;
    }

    public void setProjectedEstSentDt(String projectedEstSentDt) {
        this.projectedEstSentDt = projectedEstSentDt;
    }

    public String getDivision() {
        return division;
    }

    public String getBuyer() {
        return buyer;
    }

    public String getSalesPerson() {
        return salesperson;
    }

    public String getContact() {
        return contact;
    }

    public String getPrinter() {
        return printer;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return projtype;
    }

    public String getPrimaryAuthorID() {
        return primaryAuthorId;
    }

    public String getCategory() {
        return projcategory;
    }

    public String getProjectLevel() {
        return projectLevel;
    }

    public List getEISBNCategoryName() {
        return EISBNCategoryName;
    }

    public List getEISBNVal() {
        return EISBNVal;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getTitle() {
        return title;
    }

    public String getEdition() {
        return edition;
    }

    public String getAuthor() {
        return name;
    }

    public String getPriority() {
        return priority;
    }

    public String getPageHeight() {
        return pageHeight;
    }

    public String getPageWidth() {
        return pageWidth;
    }

    public String getCopyRight() {
        return copyright;
    }

    public String getMss() {
        return mss;
    }

    public String getEstimated() {
        return estimated;
    }

    public String getActual() {
        return actual;
    }

    public String getISBN10() {
        return isbn10;
    }

    public String getISBN13() {
        return isbn13;
    }

    public String getBillLocationId() {
        return billLocationId;
    }

    public String getBillLocationName() {
        return billLocationName;
    }

    public String getClientCode() {
        return clientCode;
    }

    public String getDivisionID() {
        return divisionID;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public String getSalespersonID() {
        return salespersonID;
    }

    public String getContactID() {
        return contactID;
    }

    public String getPrinterID() {
        return printerID;
    }

    public String getStatusCode() {
        return statusCode;
    }
    public String getJobLostReasonCode() {
        return jobLostReasonCode;
    }
    public String getJobLostReason() {
        return jobLostReason;
    }

    public String getprojtypeID() {
        return projtypeID;
    }

    public String getProjcategoryID() {
        return projcategoryID;
    }

    public String getProjectLevelID() {
        return projectLevelID;
    }

    public String getDisciplineID() {
        return disciplineID;
    }

    public String getPriorityID() {
        return priorityID;
    }

    public String getColorID() {
        return colorID;
    }

    public String getColorName() {
        return colorName;
    }

    public String getPrjEISBNCatValue() {
        return prjEISBNCatValue;
    }

    public void setProjId(String prjid) {
        this.prjid = prjid;
        
    }

    public String getProjId() {
        return prjid;
      
    }

    public String getChapter_no() {
        return chapter_no;
    }

    public void setChapter_no(String chapter_no) {
        this.chapter_no = chapter_no;
    }

    public void setPrjEISBNFieldParam(String prjEISBNFieldParam) {
        this.prjEISBNFieldParam = prjEISBNFieldParam;
    }

    //Getting project Details alone
    public void projectDetails() {
        try{
            Connection con = null;
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            projectDetails(con);
            con.close();
        }catch(Exception e){
            
        }
    }
    
    public void projectDetails(Connection con) {
        try {
            pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
            Statement statement = con.createStatement();
            String sql = "SELECT p.proj_bktitle,u.facility_id FROM projects p LEFT JOIN user u ON u.emp_id = p.planner WHERE p.proj_id = '" + prjid + "'";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(1) == null) {
                    title = "";
                } else {
                    title = rs.getString(1);
                }
                if (rs.getString(2) == null) {
                    plannerFacilityId = "";
                } else {
                    plannerFacilityId = rs.getString(2);
                }

            }
            PreparedStatement ps = con.prepareStatement("SELECT a.author_id,c.firstname,c.surname FROM project_authors a,contacts c WHERE a.proj_id = ? AND a.author_id = c.contact_id");
            ps.setString(1, prjid);
            rs = ps.executeQuery();
            while(rs.next()){
                String temp = "";
                if(rs.getString("a.author_id")!=null)
                    temp = rs.getString("a.author_id");
                authorId.add(temp);
                temp = "";
               /* if(rs.getString("c.firstname")!=null)
                    temp = rs.getString("c.firstname") + " ";*/
                if(rs.getString("c.surname")!=null)
                    temp += splChar.decoding(rs.getString("c.surname"));
                authorsList.add(temp);
            }
        } catch (Exception e) {
            System.out.println("Error on getting project details"+e);
        }
    }

    public void collectProjectName() {

        String contactCategory = "";
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        Connection con = null;
        try {
            //the fields are retrieved in parts so taht the resultset
            //doesnot get affected while referencing null values in joining the tables
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();

            Statement statement = con.createStatement();

            if (!chapterId.equals("") && chapterId != null) {
                if (prjid.equals("") || prjid == null) {
                    ResultSet rsChapterProjId = statement.executeQuery("select proj_id, chapter_no, date_format(added_date,'%m/%d/%Y') as added_date, DATE_FORMAT(ship_date,'%m/%d/%Y') as ship_date from chapter where chapter_id='" + chapterId + "' ");
                    while (rsChapterProjId.next()) {
                        prjid = rsChapterProjId.getString("proj_id");
                        chapter_no = rsChapterProjId.getString("chapter_no");
                        if (rsChapterProjId.getString("added_date") != null) {
                            addedDate = rsChapterProjId.getString("added_date");
                        } else {
                            addedDate = "";
                        }
                    }
                    rsChapterProjId = statement.executeQuery("SELECT DATE_FORMAT(projected_printer_date,'%m/%d/%Y') AS printed_date FROM projects WHERE proj_id='" + prjid + "'");
                    while (rsChapterProjId.next()) {
                        if (rsChapterProjId.getString("printed_date") != null) {
                            printedDate = rsChapterProjId.getString("printed_date");
                        } else {
                            printedDate = "";
                        }
                    }
                }
            }

           
            ProjAuthorInfo pai = new ProjAuthorInfo();
            pai.setPrjId(prjid);
            pai.collectAuthorInfo();

            primaryAuthor = pai.getPrimaryAuthor();
            authorId = pai.getAuthorId();
            authorFirstName = pai.getContactFirstName();
            authorSurName = pai.getContactSecondName();

           

            int getPrimaryAuthorIdx = primaryAuthor.indexOf("1");


            if (getPrimaryAuthorIdx > -1) {
                primaryAuthorId = authorId.get(getPrimaryAuthorIdx).toString();
                projPrimaryAuthorName = authorFirstName.get(getPrimaryAuthorIdx).toString() + " " + authorSurName.get(getPrimaryAuthorIdx).toString();
            } else {
                projPrimaryAuthorName = "";
            }

           
            String projNameSQL = " select pr.proj_name, pr.project_status "
                    + " from projects pr "
                    + " where pr.proj_id='" + prjid + "' ";

          

            //the below block is for retreiving the information for the projectId passed to display in the Add Projects interface once the project has been addded

            ResultSet rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = rsGetPrjName.getString("pr.proj_name");
                name = tempResult;
                if (rsGetPrjName.getString("pr.project_status") != null) {
                    projectStatus = rsGetPrjName.getString("pr.project_status");
                } else {
                    projectStatus = "";
                }
            }

            projNameSQL = " select cl.company,cl.contact_id,cl.vendor_number"
                    + " from projects pr,contacts cl "
                    + " where pr.proj_id='" + prjid + "' and cl.contact_id=pr.client_name";

            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = splChar.decoding(rsGetPrjName.getString("cl.company"));
                clientName = tempResult;
                tempResult = rsGetPrjName.getString("cl.contact_id");
                clientCode = tempResult;
                vendorNumber = rsGetPrjName.getString("cl.vendor_number");
                /*tempResult=rsGetPrjName.getString("cl.division");
                 if(tempResult == "null" || tempResult.equals("null")) {
                 clientDivision = "";
                 } else {
                 clientDivision = tempResult;
                 }*/
            }

           
            /**
             * ******************** WorkFlow Query **************************
             */
            rsGetPrjName = statement.executeQuery("select pwm.workflow_id,pwm.workflow_name from project_workflow_master pwm,projects pr where "
                    + "pr.proj_workflow=pwm.workflow_id and pr.proj_id='" + prjid + "' ");
            if (rsGetPrjName.next()) {
                rsGetPrjName = statement.executeQuery("select pwm.workflow_id,pwm.workflow_name from project_workflow_master pwm,projects pr where "
                        + "pr.proj_workflow=pwm.workflow_id and pr.proj_id='" + prjid + "' ");

                while (rsGetPrjName.next()) {
                    tempResult = rsGetPrjName.getString("pwm.workflow_id");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    }
                    workFlowId = tempResult;

                    tempResult = rsGetPrjName.getString("pwm.workflow_name");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    }
                    workFlowName = tempResult;
                }


            } else {
                workFlowId = "";
                workFlowName = "";
            }
           
            /**
             * ******************** Query Complete **************************
             */
            /**
             * ******************** Stage Query **************************
             */
            rsGetPrjName = statement.executeQuery("SELECT ps.stage FROM chapter c,projects pr,project_stage ps WHERE "
                    + " c.proj_id=pr.proj_id AND c.stage=ps.stage_code AND c.chapter_id='" + chapterId + "' AND pr.proj_id='" + prjid + "' ");
            
            if (rsGetPrjName.next()) {
                rsGetPrjName = statement.executeQuery("SELECT ps.stage FROM chapter c,projects pr, project_stage ps WHERE "
                        + " c.proj_id=pr.proj_id AND c.stage=ps.stage_code AND c.chapter_id='" + chapterId + "' AND pr.proj_id='" + prjid + "' ");

                while (rsGetPrjName.next()) {
                    tempResult = rsGetPrjName.getString("ps.stage");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    }
                    stageName = tempResult;


                }


            } else {
                stageName = "";
            }
            

            /**
             * ******************** Query Complete **************************
             */
            /**
             * ******************** Actual Ship Date Query
             * **************************
             */
            rsGetPrjName = statement.executeQuery("SELECT act_ship_date FROM projects pr WHERE pr.proj_id='" + prjid + "' ");
            
            if (rsGetPrjName.next()) {
                rsGetPrjName = statement.executeQuery("SELECT act_ship_date FROM projects pr WHERE pr.proj_id='" + prjid + "' ");

                while (rsGetPrjName.next()) {
                    if (rsGetPrjName.getString("pr.act_ship_date") != null) {
                        tempResult = rsGetPrjName.getString("pr.act_ship_date");
                    } else {
                        tempResult = "";
                    }
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    }
                    actualShipDate = tempResult;


                }


            } else {
                stageName = "";
            }
            
            /**
             * ******************** Query Complete **************************
             */
            projNameSQL = " select pr.trim_size"
                    + " from projects pr "
                    + " where pr.proj_id='" + prjid + "' ";

            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = rsGetPrjName.getString("pr.trim_size");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                pageHeight = tempResult;

                /*tempResult=rsGetPrjName.getString("pr.page_width");
                 if(rsGetPrjName.wasNull()){
                 tempResult="";
                 }
                 pageWidth=tempResult;*/
            }
           
            projNameSQL = " select concat(divi.firstname,' ',divi.surname) as division,divi.contact_id, "
                    + " divi.is_person,divi.company"
                    + " from contacts divi,projects pr "
                    + " where pr.proj_id='" + prjid + "' and divi.contact_id=pr.division_id ";

            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = splChar.decoding(rsGetPrjName.getString("division"));
                division = tempResult;
                tempResult = rsGetPrjName.getString("divi.contact_id");
                divisionID = tempResult;

                contactCategory = rsGetPrjName.getString("divi.is_person");
                if (contactCategory.equals("2")) {
                    tempResult = splChar.decoding(rsGetPrjName.getString("divi.company"));
                    division = tempResult;
                }
            }
//To get customer details
            projNameSQL = " select concat(byr.firstname,' ',byr.surname) as buyer,byr.contact_id, "
                    + "  byr.is_person,byr.company,byr.address_1,byr.phone_primary,byr.fax1,byr.address_2,byr.city,byr.state,byr.country,byr.zipcode,ctry.name"
                    + " from contacts byr,projects pr, country ctry "
                    + " where pr.proj_id='" + prjid + "' and byr.contact_id=pr.division_id and ctry.code=byr.country";
           
            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = splChar.decoding(rsGetPrjName.getString("byr.company"));
               // clientAddress = tempResult;
                if (rsGetPrjName.getString("byr.address_1") != null && !rsGetPrjName.getString("byr.address_1").trim().equals("")) {
                    clientAddress += "\n" + rsGetPrjName.getString("byr.address_1");
                }
                if (rsGetPrjName.getString("byr.address_2") != null && !rsGetPrjName.getString("byr.address_2").trim().equals("")) {
                    clientAddress += "\n" + rsGetPrjName.getString("byr.address_2");
                }
                if (rsGetPrjName.getString("byr.city") != null && !rsGetPrjName.getString("byr.city").trim().equals("")) {
                    clientAddress += "\n" + rsGetPrjName.getString("byr.city");
                }
                if (rsGetPrjName.getString("byr.state") != null && !rsGetPrjName.getString("byr.state").trim().equals("")) {
                    clientAddress += ", " + rsGetPrjName.getString("byr.state");
                }
                if (rsGetPrjName.getString("byr.country") != null && !rsGetPrjName.getString("byr.country").trim().equals("")) {
                    buyerCountry = rsGetPrjName.getString("byr.country");
                    buyerCountryNme= rsGetPrjName.getString("ctry.name");
                    //System.out.println(buyerCountry);
                }
                if (rsGetPrjName.getString("byr.zipcode") != null && !rsGetPrjName.getString("byr.zipcode").trim().equals("")) {
                    clientAddress += "  " + rsGetPrjName.getString("byr.zipcode");
                }
                /*if (rsGetPrjName.getString("byr.phone_primary") != null) {
                    clientAddress += "\nTelephone: " + rsGetPrjName.getString("byr.phone_primary");
                }
                if (rsGetPrjName.getString("byr.fax1") != null) {
                    clientAddress += "\nFax: " + rsGetPrjName.getString("byr.fax1");
                }*/
            }

            projNameSQL = " select concat(byr.firstname,' ',byr.surname) as buyer,byr.contact_id, "
                    + "  byr.is_person,byr.company,byr.address_1,byr.phone_primary,byr.fax1,byr.address_2,byr.city,byr.state,byr.country,byr.zipcode,ctry.name"
                    + " from contacts byr,projects pr,country ctry "
                    + " where pr.proj_id='" + prjid + "' and byr.contact_id=pr.buyer_id and byr.country=ctry.code";
           
            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = splChar.decoding(rsGetPrjName.getString("buyer"));
                buyer = tempResult;
                tempResult = rsGetPrjName.getString("byr.contact_id");
                buyerID = tempResult;
                contactCategory = rsGetPrjName.getString("byr.is_person");
                if (contactCategory.equals("2")) {
                    tempResult = splChar.decoding(rsGetPrjName.getString("byr.company"));
                    buyer = tempResult;
                }
                
                if (rsGetPrjName.getString("byr.address_1") != null && !rsGetPrjName.getString("byr.address_1").trim().equals("")) {
                    buyerAddress = rsGetPrjName.getString("byr.address_1");
                }
                if (rsGetPrjName.getString("byr.address_2") != null && !rsGetPrjName.getString("byr.address_2").trim().equals("")) {
                    buyerAddress += ",\n" + rsGetPrjName.getString("byr.address_2");
                }
                if (rsGetPrjName.getString("byr.city") != null && !rsGetPrjName.getString("byr.city").trim().equals("")) {
                    buyerAddress += ",\n" + rsGetPrjName.getString("byr.city");
                }
                if (rsGetPrjName.getString("byr.state") != null && !rsGetPrjName.getString("byr.state").trim().equals("")) {
                    buyerAddress += ", " + rsGetPrjName.getString("byr.state");
                }
                if (rsGetPrjName.getString("byr.country") != null && !rsGetPrjName.getString("byr.country").trim().equals("")) {
                    buyerAddress += ",\n" + rsGetPrjName.getString("byr.country");
                    buyerCountry = rsGetPrjName.getString("byr.country");
                    buyerCountryNme= rsGetPrjName.getString("ctry.name");
                    //System.out.println(buyerCountry);
                }
                if (rsGetPrjName.getString("byr.zipcode") != null && !rsGetPrjName.getString("byr.zipcode").trim().equals("")) {
                    buyerAddress += " - " + rsGetPrjName.getString("byr.zipcode");
                }
                if (rsGetPrjName.getString("byr.phone_primary") != null) {
                    buyerPhone = rsGetPrjName.getString("byr.phone_primary");
                }
                if (rsGetPrjName.getString("byr.fax1") != null) {
                    buyerFax = rsGetPrjName.getString("byr.fax1");
                }
            }

            projNameSQL = " select concat(sp.firstname,' ',sp.surname) as salesperson,sp.contact_id, "
                    + "  sp.is_person,sp.company "
                    + " from contacts sp,projects pr "
                    + " where pr.proj_id='" + prjid + "' and sp.contact_id=pr.salesperson_id ";
            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = splChar.decoding(rsGetPrjName.getString("salesperson"));
                salesperson = tempResult;
                tempResult = rsGetPrjName.getString("sp.contact_id");
                salespersonID = tempResult;
                contactCategory = rsGetPrjName.getString("sp.is_person");
                if (contactCategory.equals("2")) {
                    tempResult = splChar.decoding(rsGetPrjName.getString("sp.company"));
                    salesperson = tempResult;
                }
            }
            

            projNameSQL = " select trim(concat(IFNULL(cnt.firstname,''),' ',IFNULL(cnt.surname,''))) as contact,cnt.contact_id, "
                    + "  cnt.is_person,cnt.company"
                    + " from contacts cnt,projects pr "
                    + " where pr.proj_id='" + prjid + "' and cnt.contact_id=pr.contact_id ";

            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = splChar.decoding(rsGetPrjName.getString("contact"));
                contact = tempResult;
                tempResult = rsGetPrjName.getString("cnt.contact_id");
                contactID = tempResult;
                contactCategory = rsGetPrjName.getString("cnt.is_person");
                if (contactCategory.equals("2")) {
                    tempResult = splChar.decoding(rsGetPrjName.getString("cnt.company"));
                    contact = tempResult;
                }
            }

            projNameSQL = "select ppm.property_id,ppm.name "
                    + "from project_property_category_master ppcm,project_properties_master ppm,project_properties ppr "
                    + "where ppcm.name='Deliverables' and ppcm.category_id=ppm.category_id and ppr.proj_id='" + prjid + "'"
                    + " and ppr.property_id=ppm.property_id and ppr.value='True' ";

            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = rsGetPrjName.getString("ppm.property_id");
                projDeliverableId.add(tempResult);
                tempResult = rsGetPrjName.getString("ppm.name");
                projDeliverable.add(tempResult);
            }

            projNameSQL = " select concat(prtr.firstname,' ',prtr.surname) as printer,prtr.contact_id,"
                    + " prtr.is_person,prtr.company "
                    + " from contacts prtr,projects pr "
                    + " where pr.proj_id='" + prjid + "' "
                    + "and prtr.contact_id=pr.proj_printer ";

            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = splChar.decoding(rsGetPrjName.getString("printer"));
                printer = tempResult;
                tempResult = rsGetPrjName.getString("prtr.contact_id");
                printerID = tempResult;
                contactCategory = rsGetPrjName.getString("prtr.is_person");
                if (contactCategory.equals("2")) {
                    tempResult = splChar.decoding(rsGetPrjName.getString("prtr.company"));
                    printer = tempResult;
                }

            }

            /* the below block of code gets the ftp info for the proj */
            ResultSet rsGetProjServer_Info = statement.executeQuery("select ftp_serverid  from projects "
                    + "where proj_id='" + prjid + "' ");
            while (rsGetProjServer_Info.next()) {
                getServer_Id = rsGetProjServer_Info.getString(1);
                if (rsGetProjServer_Info.wasNull()) {
                    getServer_Id = "";
                }
            }

            if (!getServer_Id.equals("")) {
                ResultSet rsGetServer_Info = statement.executeQuery("select * from ftpserver where"
                        + " ftp_serverid='" + getServer_Id + "' ");
                while (rsGetServer_Info.next()) {
                    existing_Server_Name = rsGetServer_Info.getString(2);
                    if (rsGetServer_Info.wasNull()) {
                        existing_Server_Name = "";
                    } else {
                        existing_Server_Name = rsGetServer_Info.getString(2);
                    }


                    existing_Server_Ip = rsGetServer_Info.getString(3);
                    if (rsGetServer_Info.wasNull()) {
                        existing_Server_Ip = "";
                    } else {
                        existing_Server_Ip = rsGetServer_Info.getString(3);
                    }

                    existing_Server_uname = rsGetServer_Info.getString(4);
                    if (rsGetServer_Info.wasNull()) {
                        existing_Server_uname = "";
                    } else {
                        existing_Server_uname = rsGetServer_Info.getString(4);
                    }

                    existing_Server_pword = rsGetServer_Info.getString(5);
                    if (rsGetServer_Info.wasNull()) {
                        existing_Server_pword = "";
                    } else {
                        existing_Server_pword = rsGetServer_Info.getString(5);
                    }

                    existing_Server_path = rsGetServer_Info.getString(6);
                    if (rsGetServer_Info.wasNull()) {
                        existing_Server_path = "";
                    } else {
                        existing_Server_path = rsGetServer_Info.getString(6);
                    }
                }//close of while(rsGetServer_Info.next())

            }

            /* the above block of code gets the ftp info for the proj */

            /*projNameSQL =  " select pjst.status_name,pjst.proj_status_id " +
             " from project_status pjst,projects pr " +
             " where pr.proj_id='"+prjid+"' " +
             "and pjst.proj_status_id=pr.project_status ";*/

            projNameSQL = " select pjst.status,pjst.status_id,pr.job_lost_reason_code "
                    + " from status pjst,projects pr "
                    + " where pr.proj_id='" + prjid + "' "
                    + "and pjst.status_id=pr.project_status ";

            

            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = rsGetPrjName.getString("pjst.status");
                status = tempResult;
                tempResult = rsGetPrjName.getString("pjst.status_id");
                statusCode = tempResult;
                jobLostReasonCode = rsGetPrjName.getString("pr.job_lost_reason_code") !=null ? rsGetPrjName.getString("pr.job_lost_reason_code") : "";
            }
            if(!jobLostReasonCode.equals("")&&statusCode.equals("21")) {
                rsGetPrjName = statement.executeQuery("select job_lost_reason from job_lost_reason where job_lost_reason_code = '"+jobLostReasonCode+"'");
                while (rsGetPrjName.next()) {
                    jobLostReason = rsGetPrjName.getString(1);
                }
            }


            projNameSQL = " select u.emp_id,u.emp_name "
                    + " from projects pr,user u "
                    + " where pr.proj_id='" + prjid + "'"
                    + " and pr.planner=u.emp_id ";


            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {

                tempResult = rsGetPrjName.getString("u.emp_id");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                planner = tempResult;

                tempResult = rsGetPrjName.getString("u.emp_name");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                plannerName = tempResult;

            }

// for hybrid planner
            
             projNameSQL = " select u.emp_id,u.emp_name "
                    + " from projects pr,user u "
                    + " where pr.proj_id='" + prjid + "'"
                    + " and pr.hybrid_planner=u.emp_id ";
             
             
               rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {

                tempResult = rsGetPrjName.getString("u.emp_id");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                hybridPlanner = tempResult;

                tempResult = rsGetPrjName.getString("u.emp_name");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                hybridPlannerName = tempResult;

            }
            


            projNameSQL = " select pty.workflow_id,pty.workflow_name "
                    + " from project_workflow_master pty,projects pr "
                    + " where pr.proj_id='" + prjid + "' "
                    + "and pty.workflow_id=pr.proj_workflow ";

            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = rsGetPrjName.getString("pty.workflow_name");
                projtype = tempResult;
                tempResult = rsGetPrjName.getString("pty.workflow_id");
                projtypeID = tempResult;
            }
            
            projNameSQL = " select pcy.proj_category,pcy.projcategory_id "
                    + " from proj_category pcy,projects pr "
                    + " where pr.proj_id='" + prjid + "' "
                    + "and pcy.projcategory_id =pr.projcategory_id ";

            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = rsGetPrjName.getString("pcy.proj_category");
                projcategory = tempResult;
                tempResult = rsGetPrjName.getString("pcy.projcategory_id");
                projcategoryID = tempResult;
            }

            projNameSQL = " select pl.proj_level,pl.level_id "
                    + " from project_level pl,projects pr "
                    + " where pr.proj_id='" + prjid + "' "
                    + "and pl.level_id=pr.proj_level ";

            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = rsGetPrjName.getString("pl.proj_level");
                projectLevel = tempResult;
                tempResult = rsGetPrjName.getString("pl.level_id");
                projectLevelID = tempResult;
            }

            projNameSQL = " select proj_isbn_CSXML,proj_isbn_EPUB2_0,proj_isbn_EPUB3_0,proj_isbn_EPUBFL,proj_isbn_ESTR,proj_isbn_ESTRKNDL,proj_isbn_ESTRKPAG,"
                    + " proj_isbn_ETXT,proj_isbn_ETXTHSPT,proj_isbn_KF8,proj_isbn_KNDL,proj_isbn_KNDLPAG,proj_isbn_LC,proj_isbn_NC,proj_isbn_PR,"
                    + " proj_isbn_SIMPUB,proj_isbn_SITB,proj_isbn_SMRTBK,proj_isbn_VST,proj_isbn_WBPDF,proj_isbn_WKR, customer_po, customer_PODate,eFTP_date, no_of_chapters, mss_format, fst_present, xml_prop, paper_info "
                    + " from projects "
                    + " where proj_id='" + prjid + "' ";

            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                //Customer PO
                tempResult = rsGetPrjName.getString("customer_po");
                if (rsGetPrjName.wasNull()) {
                    this.customerPO = "";
                } else {
                    this.customerPO = rsGetPrjName.getString("customer_po");
                }
        tempResult = rsGetPrjName.getString("customer_PODate");
                if (rsGetPrjName.wasNull()) {
                    this.customerPODate = "";
                } else {
                    this.customerPODate = rsGetPrjName.getString("customer_PODate");
                }
        //eftp_date

         tempResult = rsGetPrjName.getString("eFTP_date");
                if (rsGetPrjName.wasNull()) {
                    this.eFtpDate = "";
                } else {
                    this.eFtpDate = rsGetPrjName.getString("eFTP_date");
                }

                //Number of Chapters
                tempResult = rsGetPrjName.getString("no_of_chapters");
                if (rsGetPrjName.wasNull()) {
                    this.noOfChapters = "";
                } else {
                    this.noOfChapters = rsGetPrjName.getString("no_of_chapters");
                }

                //MSS Format
                tempResult = rsGetPrjName.getString("mss_format");
                if (rsGetPrjName.wasNull()) {
                    this.mssFormat = "";
                } else {
                    this.mssFormat = rsGetPrjName.getString("mss_format");
                }
//paperInfoDisp
                tempResult = rsGetPrjName.getString("paper_info");
                if (rsGetPrjName.wasNull()) {
                    this.paperInfoDisp = "";
                } else {
                    this.paperInfoDisp = rsGetPrjName.getString("paper_info");
                }
                //FST Present
                tempResult = rsGetPrjName.getString("fst_present");
                if (rsGetPrjName.wasNull()) {
                    this.fstPresent = "";
                } else {
                    this.fstPresent = rsGetPrjName.getString("fst_present");
                }

                //XML Type
                tempResult = rsGetPrjName.getString("xml_prop");
                if (rsGetPrjName.wasNull()) {
                    this.xmlProp = "";
                } else {
                    this.xmlProp = rsGetPrjName.getString("xml_prop");
                }


                tempResult = rsGetPrjName.getString("proj_isbn_CSXML");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("Course Smart-XML");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_EPUB2_0");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("e-PUB 2.0");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_EPUB3_0");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("e-PUB 3.0");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_EPUBFL");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("e-PUB-Fixed Layout");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_ESTR");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("E-store");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_ESTRKNDL");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("E-Store Kindle");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_ESTRKPAG");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("E-Store Kindle-Paging");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_ETXT");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("e-Text");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_ETXTHSPT");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("e-Text Hotspot");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_KF8");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("KF8");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_KNDL");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("Kindle");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_KNDLPAG");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("Kindle-Paging");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_LC");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("Loislaw Conversion");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_NC");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("Nook Conversion");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_PR");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("Print Replica");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_SIMPUB");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("SIMPUB");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_SITB");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("SITB");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_SMRTBK");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("Smart Book");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_VST");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("VST");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_WBPDF");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("Web PDF_UPDF");
                }

                tempResult = rsGetPrjName.getString("proj_isbn_WKR");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                } else {
                    EISBNVal.add(tempResult);
                    EISBNCategoryName.add("WK-Reader");
                }
            }

           

            projNameSQL = " select pdc.proj_discipline,pdc.projdiscipline_id "
                    + " from proj_discipline pdc,projects pr "
                    + " where pr.proj_id='" + prjid + "' and "
                    + "pr.projdiscipline_id=pdc.projdiscipline_id ";

            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {

                tempResult = rsGetPrjName.getString("pdc.proj_discipline");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                discipline = tempResult;
                tempResult = rsGetPrjName.getString("pdc.projdiscipline_id");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                disciplineID = tempResult;//pdc.proj_discipline
            }

          

            projNameSQL = " select pr.proj_bktitle "
                    + " from projects pr "
                    + " where pr.proj_id='" + prjid + "' ";

            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {

                tempResult = rsGetPrjName.getString("pr.proj_bktitle");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                title = tempResult;//pdc.proj_discipline
            }

           


            projNameSQL = " select prty.priority_value,prty.priority_id "
                    + " from projects pr,proj_priority prty "
                    + " where pr.proj_id='" + prjid + "'"
                    + " and pr.priority_id=prty.priority_id ";


            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = rsGetPrjName.getString("prty.priority_value");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                priority = tempResult;
                tempResult = rsGetPrjName.getString("prty.priority_id");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                priorityID = tempResult;

            }


            projNameSQL = " select clr.color_id,clr.color "
                    + " from projects pr,proj_color clr "
                    + " where pr.proj_id='" + prjid + "'"
                    + " and pr.color_id=clr.color_id ";


            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = rsGetPrjName.getString("clr.color_id");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                colorID = tempResult;
                tempResult = rsGetPrjName.getString("clr.color");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                colorName = tempResult;

            }

            //
            projNameSQL = " select pr.edition,pr.copyright_year,pr.mss_pages,"
                    + "pr.estimated_pages,pr.actual_pages,pr.proj_isbn_13,pr.proj_isbn_10,pr.facility_id,DATE_FORMAT(pr.projected_printer_date,'%d-%b-%Y'),pr.planner,DATE_FORMAT(pr.est_sent_date,'%d-%b-%Y') "
                    + " from projects pr "
                    + " where pr.proj_id='" + prjid + "'";

           

            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {
                tempResult = rsGetPrjName.getString("pr.edition");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                edition = tempResult;
                tempResult = rsGetPrjName.getString("pr.copyright_year");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                copyright = tempResult;
                tempResult = rsGetPrjName.getString("pr.mss_pages");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                mss = tempResult;
                tempResult = rsGetPrjName.getString("pr.estimated_pages");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                estimated = tempResult;
                tempResult = rsGetPrjName.getString("pr.actual_pages");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                actual = tempResult;
                tempResult = rsGetPrjName.getString("pr.proj_isbn_13");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                isbn13 = tempResult;



                tempResult = rsGetPrjName.getString("pr.proj_isbn_10");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                isbn10 = tempResult;//

                tempResult = rsGetPrjName.getString("DATE_FORMAT(pr.projected_printer_date,'%d-%b-%Y')");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                projectedPrinterDt = tempResult;

                tempResult = rsGetPrjName.getString("DATE_FORMAT(pr.est_sent_date,'%d-%b-%Y')");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                projectedEstSentDt = tempResult;

            }


            projNameSQL = " select fcl.facility_id,fcl.facility_name "
                    + " from projects pr,facility fcl "
                    + " where pr.proj_id='" + prjid + "'"
                    + " and pr.facility_id=fcl.facility_id ";


            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {

                tempResult = rsGetPrjName.getString("fcl.facility_id");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                billLocationId = tempResult;

                tempResult = rsGetPrjName.getString("fcl.facility_name");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                billLocationName = tempResult;

            }

            projNameSQL = " select u.emp_id,u.emp_name "
                    + " from projects pr,user u "
                    + " where pr.proj_id='" + prjid + "'"
                    + " and pr.planner=u.emp_id ";


            rsGetPrjName = statement.executeQuery(projNameSQL);
            while (rsGetPrjName.next()) {

                tempResult = rsGetPrjName.getString("u.emp_id");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                planner = tempResult;

                tempResult = rsGetPrjName.getString("u.emp_name");
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                plannerName = tempResult;

            }


          




            if (!prjEISBNFieldParam.equals("")) {
                projNameSQL = " select " + prjEISBNFieldParam + " "
                        + " from projects "
                        + " where proj_id='" + prjid + "' ";

                rsGetPrjName = statement.executeQuery(projNameSQL);
                while (rsGetPrjName.next()) {

                    tempResult = rsGetPrjName.getString(1);
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    }
                    prjEISBNCatValue = tempResult;//pdc.proj_discipline
                }
            }

            String projCurrency = "SELECT p.currency_code, c.html_code, c.unicode, c.unicode_format FROM projects p, currency_symbols c WHERE p.currency_code = c.currency_code AND proj_id="+prjid;
            //System.out.println("> : "+projCurrency);
            rsGetPrjName = statement.executeQuery(projCurrency);
            while (rsGetPrjName.next()) {
                tempResult = rsGetPrjName.getString(1);
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                currencyCode = tempResult;

                tempResult = rsGetPrjName.getString(2);
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                htmlCode = tempResult;

                tempResult = rsGetPrjName.getString(3);
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                unicode = tempResult;

                tempResult = rsGetPrjName.getString(4);
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                unicodeFormatFlag = tempResult;
            }

            String projReopenQuery = "SELECT EXISTS(SELECT u.emp_id FROM USER u, groups g WHERE u.emp_id=g.emp_id AND g.group_id = '39' and u.emp_id='"+empId+"') as Flag";
            rsGetPrjName = statement.executeQuery(projReopenQuery);
            while (rsGetPrjName.next()) {
                tempResult = rsGetPrjName.getString(1);
                if (rsGetPrjName.wasNull()) {
                    tempResult = "";
                }
                projReopen = tempResult;
            }
            rsGetPrjName.close();
            statement.close();
           
        } catch (SQLException sqle) {
            System.out.println("SQLException in ProjIdGeneralInfo:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in ProjIdGeneralInfo:" + e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }

        }


    }

    public void GetVendorIdList() {
        try {
            DBconnection dbcon = new DBconnection();
            Connection con = dbcon.getSampleProperty();

            Statement statement = con.createStatement();

            //String contactsInfo_Sql = "SELECT po.vendor_id FROM purchase_orders po, po_lineitems pl WHERE po.po_number = pl.po_number AND pl.proj_id='" + prjid + "' GROUP BY po.vendor_id";
            String contactsInfo_Sql = "SELECT vendor_number FROM contacts WHERE contact_id = (SELECT client_name FROM projects WHERE proj_id = '" + prjid + "')";

           
            ResultSet rsGetContactInfo = statement.executeQuery(contactsInfo_Sql);
            while (rsGetContactInfo.next()) {
               
                if (rsGetContactInfo.getString("vendor_number") != null) {
                    vendorIdList.add(rsGetContactInfo.getString("vendor_number"));
                }
            }
            rsGetContactInfo.close();
            statement.close();
            con.close();
          
        } catch (SQLException sqle) {
            System.out.println("SQLException in collecting ContactInfo:" + sqle);
        } catch (Exception e) {

            System.out.println("Exception in collecting ContactInfo:" + e);
        }
    }

    public void GetRevisionCount() {
        try {
            DBconnection dbcon = new DBconnection();
            Connection con = dbcon.getSampleProperty();

            Statement statement = con.createStatement();

            String contactsInfo_Sql = " SELECT COUNT(*) AS RevisionCount FROM chapter WHERE proj_id='" + prjid + "' AND stage='RP' AND chapter_no IN (SELECT c.chapter_no FROM chapter c WHERE chapter_id='" + chapterId + "')";


            ResultSet rsGetContactInfo = statement.executeQuery(contactsInfo_Sql);
            while (rsGetContactInfo.next()) {
               
                if (rsGetContactInfo.getString("RevisionCount") != null) {
                    revisionCount = rsGetContactInfo.getString("RevisionCount");
                } else {
                    revisionCount = "";
                }
            }
            rsGetContactInfo.close();
            statement.close();
            con.close();

        } catch (SQLException sqle) {
            System.out.println("SQLException in collecting ContactInfo:" + sqle);
        } catch (Exception e) {

            System.out.println("Exception in collecting ContactInfo:" + e);
        }
    }
    public ProjIdGeneralInfo getProductionOnTimeInit(ProjIdGeneralInfo projIdGeneralInfo) {

        List plannerIdList = new ArrayList();
        List plannerNameList = new ArrayList();
        List hybridPlannerIdList = new ArrayList();
        List hybridPlannerNameList = new ArrayList();
        List customerIdList = new ArrayList();
        List customerNameList = new ArrayList();
        
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();

        Connection con = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;

        try {

            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();

            String productionFilesInitQuery = "SELECT u.emp_id, u.emp_name FROM projects p, USER u WHERE p.planner=u.emp_id GROUP BY p.planner ORDER BY u.emp_name";
            preStmt = con.prepareStatement(productionFilesInitQuery);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    plannerIdList.add(rs.getString(1));
                } else {
                    plannerIdList.add("");
                }
                if (rs.getString(2) != null) {
                    plannerNameList.add(rs.getString(2));
                } else {
                    plannerNameList.add("");
                }
            }

            productionFilesInitQuery = "SELECT u.emp_id, u.emp_name FROM projects p, USER u WHERE p.hybrid_planner=u.emp_id GROUP BY p.hybrid_planner ORDER BY u.emp_name";
            preStmt = con.prepareStatement(productionFilesInitQuery);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    hybridPlannerIdList.add(rs.getString(1));
                } else {
                    hybridPlannerIdList.add("");
                }
                if (rs.getString(2) != null) {
                    hybridPlannerNameList.add(rs.getString(2));
                } else {
                    hybridPlannerNameList.add("");
                }
            }

            productionFilesInitQuery = "SELECT c.contact_id, c.company FROM projects p, contacts c WHERE p.client_name=c.contact_id GROUP BY p.client_name order by c.company";
            preStmt = con.prepareStatement(productionFilesInitQuery);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    customerIdList.add(rs.getString(1));
                } else {
                    customerIdList.add("");
                }
                if (rs.getString(2) != null) {
                    customerNameList.add(splChar.decoding(rs.getString(2)));
                } else {
                    customerNameList.add("");
                }
            }

            projIdGeneralInfo.setPlannerIdList(plannerIdList);
            projIdGeneralInfo.setPlannerNameList(plannerNameList);
            projIdGeneralInfo.setHybridPlannerIdList(hybridPlannerIdList);
            projIdGeneralInfo.setHybridPlannerNameList(hybridPlannerNameList);
            projIdGeneralInfo.setCustomerIdList(customerIdList);
            projIdGeneralInfo.setCustomerNameList(customerNameList);
        } catch (SQLException sqle) {
            System.out.println("SQLException ProductionOnTimeDAO - getProductionOnTimeInit() : "+sqle);
        } catch (NullPointerException npe) {
            System.out.println("NullPointerException : ProductionOnTimeDAO - getProductionOnTimeInit() : "+npe);
        } finally {
            try {
                preStmt.close();
                rs.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException ProductionOnTimeDAO - getProductionOnTimeInit() : "+sqle);
            } catch (NullPointerException npe) {
                System.out.println("NullPointerException : ProductionOnTimeDAO - getProductionOnTimeInit() : "+npe);
            }
        }
        return projIdGeneralInfo;
    }
public void collectPaperInformation(){
Connection con = null;
Statement stmt = null;
ResultSet rst = null;
try{
    DBconnection dbcon = new DBconnection();
    con = dbcon.getSampleProperty();
    stmt = con.createStatement();
    rst = stmt.executeQuery("select * from paper_info");
    while (rst.next()){
    paperinfo.add(rst.getString("paper_info"));
    }
   // System.out.println("paperinfo"+paperinfo);
  ProjIdGeneralInfo projGenInf = new ProjIdGeneralInfo();
  projGenInf.setCollectPaperInfo(paperinfo);
}
catch(SQLException sqle){
    System.out.println("SQLException PaperInformation:" + sqle);
}
}
public void setCollectPaperInfo(List paperinfo){
this.paperinfo = paperinfo;
}
public List getCollectPaperInfo(){
return paperinfo;
}
public void collectProjectDetails() {

        String contactCategory = "";
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        Connection con = null;
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            Statement statement = con.createStatement();
            ProjAuthorInfo pai = new ProjAuthorInfo();
            pai.setPrjId(prjid);
            pai.collectAuthorInfo();
            primaryAuthor = pai.getPrimaryAuthor();
            authorId = pai.getAuthorId();
            authorFirstName = pai.getContactFirstName();
            authorSurName = pai.getContactSecondName();
            int getPrimaryAuthorIdx = primaryAuthor.indexOf("1");

            if (getPrimaryAuthorIdx > -1) {
                primaryAuthorId = authorId.get(getPrimaryAuthorIdx).toString();
                projPrimaryAuthorName = authorFirstName.get(getPrimaryAuthorIdx).toString() + " " + authorSurName.get(getPrimaryAuthorIdx).toString();
            } else {
                projPrimaryAuthorName = "";
            }


            String query = "SELECT pr.proj_name,pr.proj_id,g.company,g.vendor_number,g.contact_id, pr.client_name, pwm.workflow_id,pwm.workflow_name, "
                    + " pr.division_id, CONCAT_WS('',divi.firstname,' ',divi.surname) AS division, divi.is_person, divi.company, "
                    + " pr.trim_size,pr.buyer_id,trim(CONCAT_WS('',byr.firstname,' ',byr.surname)) AS buyer, byr.is_person, byr.company,"
                    + " pr.salesperson_id,CONCAT_WS('',sp.firstname,' ',sp.surname) AS salesperson, sp.is_person, sp.company, "
                    + " pr.contact_id,trim(CONCAT_WS('',cnt.firstname,' ',cnt.surname)) AS contactperson, cnt.is_person, cnt.company, "
                    + " pr.proj_printer,CONCAT(prtr.firstname,' ',prtr.surname) AS printer, prtr.is_person, prtr.company, "
                    + " s.status,pr.project_status,pr.job_lost_reason_code, jlr.job_lost_reason, "
                    + " pr.planner,pln.emp_name,pr.hybrid_planner,hypln.emp_name,pr.proj_bktitle, "
                    + " pcy.proj_category,pcy.projcategory_id,pl.proj_level,pl.level_id, "
                    + " pr.edition,pr.copyright_year,pr.mss_pages,clr.color_id,clr.color, "
                    + " prty.priority_value,prty.priority_id, "
                    + " pr.estimated_pages,pr.actual_pages,pr.proj_isbn_13,pr.proj_isbn_10,pr.facility_id, f.facility_name, "
                    + " pr.projected_printer_date, pr.est_sent_date, "
                    + " pr.act_ship_date, pr.no_of_chapters,pr.mss_format,pr.fst_present,pr.xml_prop, "
                    + " pr.proj_isbn_CSXML,pr.proj_isbn_EPUB2_0,pr.proj_isbn_EPUB3_0,pr.proj_isbn_EPUBFL,pr.proj_isbn_ESTR,"
                    + " pr.proj_isbn_ESTRKNDL,pr.proj_isbn_ESTRKPAG,pr.proj_isbn_ETXT,pr.proj_isbn_ETXTHSPT, "
                    + " pr.proj_isbn_KF8,pr.proj_isbn_KNDL,pr.proj_isbn_KNDLPAG,pr.proj_isbn_LC,pr.proj_isbn_NC,pr.proj_isbn_PR, "
                    + " pr.proj_isbn_SIMPUB,pr.proj_isbn_SITB,pr.proj_isbn_SMRTBK,pr.proj_isbn_VST,pr.proj_isbn_WBPDF,pr.proj_isbn_WKR, "
                    + " pr.customer_po,pr.customer_PODate, pr.eFTP_date,pr.no_of_chapters,pr.mss_format,pr.fst_present,pr.xml_prop,pd.projdiscipline_id,pd.proj_discipline,pr.paper_info "
                    + " FROM projects pr LEFT JOIN contacts g ON g.contact_id=pr.client_name "
                    + " LEFT JOIN contacts divi ON divi.contact_id=pr.division_id "
                    + " LEFT JOIN facility f USING(facility_id) "
                    + " LEFT JOIN proj_discipline pd ON pr.projdiscipline_id=pd.projdiscipline_id "
                    + " LEFT JOIN project_workflow_master pwm ON pr.proj_workflow=pwm.workflow_id "
                    + " LEFT JOIN STATUS s ON pr.project_status=s.status_id "
                    + " LEFT JOIN job_lost_reason jlr ON jlr.job_lost_reason_code=pr.job_lost_reason_code "
                    + " LEFT JOIN contacts byr ON byr.contact_id=pr.buyer_id "
                    + " LEFT JOIN contacts sp ON sp.contact_id=pr.salesperson_id "
                    + " LEFT JOIN contacts cnt ON cnt.contact_id=pr.contact_id "
                    + " LEFT JOIN contacts prtr ON prtr.contact_id=pr.proj_printer "
                    + " LEFT JOIN USER pln ON pr.planner=pln.emp_id "
                    + " LEFT JOIN USER hypln ON pr.hybrid_planner=hypln.emp_id "
                    + " LEFT JOIN proj_category pcy ON pcy.projcategory_id=pr.projcategory_id  "
                    + " LEFT JOIN project_level pl ON pl.level_id=pr.proj_level "
                    + " LEFT JOIN proj_priority prty ON pr.priority_id=prty.priority_id "
                    + " LEFT JOIN proj_color clr ON pr.color_id=clr.color_id  "
                    + " WHERE pr.proj_id='"+prjid+"'"
                    + " GROUP BY pr.proj_id ";

            ResultSet rsGetPrjName = statement.executeQuery(query);

                while (rsGetPrjName.next()) {
                    name = rsGetPrjName.getString(1);
                    title = rsGetPrjName.getString("pr.proj_bktitle");
                    
                    clientName = rsGetPrjName.getString("g.company") !=null ? splChar.decoding(rsGetPrjName.getString("g.company")) : "";
                    vendorNumber = rsGetPrjName.getString("g.vendor_number") !=null ? rsGetPrjName.getString("g.vendor_number") : "";
                    clientCode = rsGetPrjName.getString("pr.client_name") !=null ? rsGetPrjName.getString("pr.client_name") : "";
                    
                    projtypeID = rsGetPrjName.getString("pwm.workflow_id") !=null ? rsGetPrjName.getString("pwm.workflow_id") : "";
                    projtype = rsGetPrjName.getString("pwm.workflow_name") !=null ? rsGetPrjName.getString("pwm.workflow_name") : "";
                    
                    division = rsGetPrjName.getString("division") !=null ? splChar.decoding(rsGetPrjName.getString("division")) : "";
                    divisionID = rsGetPrjName.getString("pr.division_id") !=null ? rsGetPrjName.getString("pr.division_id") : "";
                    contactCategory = rsGetPrjName.getString("divi.is_person") !=null ? rsGetPrjName.getString("divi.is_person") : "";
                    if (contactCategory.equals("2")) {
                        division = splChar.decoding(rsGetPrjName.getString("divi.company"));
                    }

                    buyer = rsGetPrjName.getString("buyer") !=null ? splChar.decoding(rsGetPrjName.getString("buyer")) : "";
                    buyerID = rsGetPrjName.getString("pr.buyer_id") !=null ? rsGetPrjName.getString("pr.buyer_id") : "";
                    contactCategory = rsGetPrjName.getString("byr.is_person") !=null ? splChar.decoding(rsGetPrjName.getString("byr.is_person")) : "";
                    if (contactCategory.equals("2")) {
                        buyer = splChar.decoding(rsGetPrjName.getString("byr.company"));
                    }

                    salesperson = rsGetPrjName.getString("salesperson") != null ? splChar.decoding(rsGetPrjName.getString("salesperson")) : "";
                    salespersonID = rsGetPrjName.getString("pr.salesperson_id") != null ? rsGetPrjName.getString("pr.salesperson_id") : "";
                    contactCategory = rsGetPrjName.getString("sp.is_person") != null ? rsGetPrjName.getString("sp.is_person") : "";
                    if (contactCategory.equals("2")) {
                        salesperson = rsGetPrjName.getString("sp.company");
                    }

                    contact = rsGetPrjName.getString("contactperson") != null ? splChar.decoding(rsGetPrjName.getString("contactperson")) : "";
                    contactID = rsGetPrjName.getString("pr.contact_id") != null ? rsGetPrjName.getString("pr.contact_id") : "";
                    contactCategory = rsGetPrjName.getString("cnt.is_person") != null ? rsGetPrjName.getString("cnt.is_person") : "";
                    if (contactCategory.equals("2")) {
                        contact = splChar.decoding(rsGetPrjName.getString("cnt.company"));
                    }

                    printer = rsGetPrjName.getString("printer") != null ? splChar.decoding(rsGetPrjName.getString("printer")) : "";
                    printerID = rsGetPrjName.getString("pr.proj_printer") != null ? rsGetPrjName.getString("pr.proj_printer") : "";
                    contactCategory = rsGetPrjName.getString("prtr.is_person") != null ? rsGetPrjName.getString("prtr.is_person") : "";
                    if (contactCategory.equals("2")) {
                        printer = splChar.decoding(rsGetPrjName.getString("prtr.company"));
                    }

                    status = rsGetPrjName.getString("s.status") != null ? rsGetPrjName.getString("s.status") : "";
                    statusCode = rsGetPrjName.getString("pr.project_status") != null ? rsGetPrjName.getString("pr.project_status") : "";
                    jobLostReasonCode = rsGetPrjName.getString("pr.job_lost_reason_code") != null ? rsGetPrjName.getString("pr.job_lost_reason_code") : "";
                    jobLostReason = rsGetPrjName.getString("jlr.job_lost_reason") != null ? rsGetPrjName.getString("jlr.job_lost_reason") : "";
                    
                    pageHeight = rsGetPrjName.getString("pr.trim_size") != null ? rsGetPrjName.getString("pr.trim_size") : "";
                    
                    plannerName = rsGetPrjName.getString("pln.emp_name") != null ? rsGetPrjName.getString("pln.emp_name") : "";
                    planner = rsGetPrjName.getString("pr.planner") != null ? rsGetPrjName.getString("pr.planner") : "";

                    hybridPlannerName = rsGetPrjName.getString("hypln.emp_name") != null ? rsGetPrjName.getString("hypln.emp_name") : "";
                    hybridPlanner = rsGetPrjName.getString("pr.hybrid_planner") != null ? rsGetPrjName.getString("pr.hybrid_planner") : "";
                    
                    projcategory = rsGetPrjName.getString("pcy.proj_category") != null ? rsGetPrjName.getString("pcy.proj_category") : "";
                    projcategoryID = rsGetPrjName.getString("pcy.projcategory_id") != null ? rsGetPrjName.getString("pcy.projcategory_id") : "";
                    
                    projectLevel = rsGetPrjName.getString("pl.proj_level") != null ? rsGetPrjName.getString("pl.proj_level") : "";
                    projectLevelID = rsGetPrjName.getString("pl.level_id") != null ? rsGetPrjName.getString("pl.level_id") : "";

                    tempResult = rsGetPrjName.getString("pr.customer_po");
                    if (rsGetPrjName.wasNull()) {
                        this.customerPO = "";
                    } else {
                        this.customerPO = rsGetPrjName.getString("pr.customer_po");
                    }
                    tempResult = rsGetPrjName.getString("pr.customer_PODate");
                    if (rsGetPrjName.wasNull()) {
                        this.customerPODate = "";
                    } else {
                        this.customerPODate = rsGetPrjName.getString("pr.customer_PODate");
                    }
                     tempResult = rsGetPrjName.getString("pr.eFTP_date");
                    if (rsGetPrjName.wasNull()) {
                        this.eFtpDate = "";
                    } else {
                        this.eFtpDate = rsGetPrjName.getString("pr.eFTP_date");
                    }
//
                    tempResult = rsGetPrjName.getString("pr.no_of_chapters");
                    if (rsGetPrjName.wasNull()) {
                        this.noOfChapters = "";
                    } else {
                        this.noOfChapters = rsGetPrjName.getString("pr.no_of_chapters");
                    }

                    tempResult = rsGetPrjName.getString("pr.mss_format");
                    if (rsGetPrjName.wasNull()) {
                        this.mssFormat = "";
                    } else {
                        this.mssFormat = rsGetPrjName.getString("pr.mss_format");
                    }

                     tempResult = rsGetPrjName.getString("paper_info");
                if (rsGetPrjName.wasNull()) {
                    this.paperInfoDisp = "";
                } else {
                    this.paperInfoDisp = rsGetPrjName.getString("paper_info");
                }
                    tempResult = rsGetPrjName.getString("pr.fst_present");
                    if (rsGetPrjName.wasNull()) {
                        this.fstPresent = "";
                    } else {
                        this.fstPresent = rsGetPrjName.getString("pr.fst_present");
                    }

                    tempResult = rsGetPrjName.getString("pr.xml_prop");
                    if (rsGetPrjName.wasNull()) {
                        this.xmlProp = "";
                    } else {
                        this.xmlProp = rsGetPrjName.getString("pr.xml_prop");
                    }


                    tempResult = rsGetPrjName.getString("pr.proj_isbn_CSXML");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("Course Smart-XML");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_EPUB2_0");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("e-PUB 2.0");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_EPUB3_0");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("e-PUB 3.0");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_EPUBFL");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("e-PUB-Fixed Layout");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_ESTR");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("E-store");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_ESTRKNDL");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("E-Store Kindle");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_ESTRKPAG");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("E-Store Kindle-Paging");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_ETXT");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("e-Text");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_ETXTHSPT");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("e-Text Hotspot");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_KF8");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("KF8");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_KNDL");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("Kindle");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_KNDLPAG");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("Kindle-Paging");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_LC");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("Loislaw Conversion");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_NC");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("Nook Conversion");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_PR");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("Print Replica");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_SIMPUB");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("SIMPUB");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_SITB");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("SITB");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_SMRTBK");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("Smart Book");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_VST");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("VST");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_WBPDF");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("pr.Web PDF_UPDF");
                    }

                    tempResult = rsGetPrjName.getString("pr.proj_isbn_WKR");
                    if (rsGetPrjName.wasNull()) {
                        tempResult = "";
                    } else {
                        EISBNVal.add(tempResult);
                        EISBNCategoryName.add("WK-Reader");
                    }
                    discipline = rsGetPrjName.getString("pd.proj_discipline") != null ? rsGetPrjName.getString("pd.proj_discipline") : "";
                    disciplineID = rsGetPrjName.getString("pd.projdiscipline_id") != null ? rsGetPrjName.getString("pd.projdiscipline_id") : "";

                    priority = rsGetPrjName.getString("prty.priority_value") != null ? rsGetPrjName.getString("prty.priority_value") : "";
                    priorityID = rsGetPrjName.getString("prty.priority_id") != null ? rsGetPrjName.getString("prty.priority_id") : "";

                    colorID = rsGetPrjName.getString("clr.color_id") != null ? rsGetPrjName.getString("clr.color_id") : "";
                    colorName = rsGetPrjName.getString("clr.color") != null ? rsGetPrjName.getString("clr.color") : "";

                    edition = rsGetPrjName.getString("pr.edition") != null ? rsGetPrjName.getString("pr.edition") : "";
                    copyright = rsGetPrjName.getString("pr.copyright_year") != null ? rsGetPrjName.getString("pr.copyright_year") : "";

                    mss = rsGetPrjName.getString("pr.mss_pages") != null ? rsGetPrjName.getString("pr.mss_pages") : "";
                    estimated = rsGetPrjName.getString("pr.estimated_pages") != null ? rsGetPrjName.getString("pr.estimated_pages") : "";
                    actual = rsGetPrjName.getString("pr.actual_pages") != null ? rsGetPrjName.getString("pr.actual_pages") : "";

                    isbn13 = rsGetPrjName.getString("pr.proj_isbn_13") != null ? rsGetPrjName.getString("pr.proj_isbn_13") : "";
                    isbn10 = rsGetPrjName.getString("pr.proj_isbn_10") != null ? rsGetPrjName.getString("pr.proj_isbn_10") : "";

                    projectedPrinterDt = rsGetPrjName.getString("pr.projected_printer_date") != null ? rsGetPrjName.getString("pr.projected_printer_date") : "";
                    projectedEstSentDt = rsGetPrjName.getString("pr.est_sent_date") != null ? rsGetPrjName.getString("pr.est_sent_date") : "";
                    actualShipDate = rsGetPrjName.getString("pr.act_ship_date") != null ? rsGetPrjName.getString("pr.act_ship_date") : "";

                    billLocationId = rsGetPrjName.getString("pr.facility_id") != null ? rsGetPrjName.getString("pr.facility_id") : "";
                    billLocationName = rsGetPrjName.getString("f.facility_name") != null ? rsGetPrjName.getString("f.facility_name") : "";

                }


            String projReopenQuery = "SELECT EXISTS(SELECT u.emp_id FROM USER u, groups g WHERE u.emp_id=g.emp_id AND g.group_id = '39' and u.emp_id='" + empId + "') as Flag";
            rsGetPrjName = statement.executeQuery(projReopenQuery);

            while (rsGetPrjName.next()) {
                projReopen = rsGetPrjName.getString(1);
                if (rsGetPrjName.wasNull()) {
                    projReopen = "";
                }
            }
            rsGetPrjName.close();
            statement.close();

        } catch (SQLException sqle) {
            System.out.println("SQLException in ProjIdGeneralInfo:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in ProjIdGeneralInfo:" + e);
        } finally {
            try {

                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }

        }


    }
}
