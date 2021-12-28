/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.pdf;

import java.io.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.contacts.ProjAuthorInfo;
import pathfinder.functions.revenue.JobCostOverViewDAO;
import pathfinder.functions.revenue.JobCostOverViewVO;
import pathfinder.functions.revenue.TransmittalDAO;
import pathfinder.functions.revenue.TransmittalVO;

/**
 *
 * @author Aravindhanj
 */
public class JCOPdfServlet extends HttpServlet {

    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 10,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 7,
            Font.NORMAL, BaseColor.RED);
    private static Font blueFont = new Font(Font.FontFamily.HELVETICA, 10,
            Font.BOLD, BaseColor.BLUE);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 10,
            Font.BOLD);
    private static Font normalFont = new Font(Font.FontFamily.HELVETICA, 8);
    private static Font normalHeaderFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 7,
            Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 7);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 7);
    private static Font smallBoldNote = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD);
    private static Font makeDiff = new Font();

    DecimalFormat decimalFormat = new DecimalFormat("#.00");
    DecimalFormat df = new DecimalFormat("0.###");

    BaseColor headerColor = WebColors.getRGBColor("#D0D0D0");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        javax.servlet.http.HttpSession session = request.getSession();
        String showTimeStamp = "";
        String emp_fcy = session.getAttribute("empfacility").toString();

        java.util.Date date= new java.util.Date();
        SimpleDateFormat format =new SimpleDateFormat("dd-MMM-yyyy");
        if(emp_fcy == "1" || emp_fcy.equals("1")) {
            showTimeStamp = format.format(date);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, -11);
            calendar.add(Calendar.MINUTE, -30);
            date = calendar.getTime();
            showTimeStamp = format.format(date);
        }

        response.setContentType("application/pdf");
        String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";
        String project_name = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";

        Double estVal = 0.0;
        Double invVal = 0.0;
        Double totalamountForProfitcal =0.0;
        Double openPO = 0.0;
        Double unPosted = 0.0;
        Double postageValue = 0.0;
        String invMailedDate = "";
        List authorName = new ArrayList();

        List projAssignedFacility = new ArrayList();
        List projAssignedDepartment = new ArrayList();
        List projAssignedCell = new ArrayList();
        List projAssignedEmpName = new ArrayList();
        List projAssignedEmpDept = new ArrayList();
        List projAssignedEmpDesig = new ArrayList();
        List projAssignedEmpFacName = new ArrayList();
        List projAssignedEmpRoleName = new ArrayList();
        List projAssignedEmpIncharge = new ArrayList();

        String fileName = project_name + "_JCO.pdf";
        fileName = fileName.replaceAll(" ", "-");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", " inline; filename=" + fileName);

        response.setContentType("application/pdf");

        try {

            //System.out.println("inside JCO generatePDf");

            Document document = new Document();

            //PdfWriter.getInstance(document, file);
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.setPageSize(PageSize.A4.rotate());
            document.setMargins(5, 5, 5, 5);
            
            document.open();

            Paragraph numberPara = new Paragraph();

            //Get the project properties
            pathfinder.functions.projects.ProjIdGeneralInfo projInfo = new pathfinder.functions.projects.ProjIdGeneralInfo();
            projInfo.setProjId(getProjIdParam);
            projInfo.collectProjectName();
            projInfo.projectDetails();

            // Get Project Team Details
            pathfinder.functions.projects.ProjTeamValues projTeamDet = new pathfinder.functions.projects.ProjTeamValues();
            projAssignedFacility.clear();
            projAssignedDepartment.clear();
            projAssignedCell.clear();
            projAssignedEmpName.clear();
            projAssignedEmpDept.clear();
            projAssignedEmpDesig.clear();
            projAssignedEmpFacName.clear();
            projAssignedEmpRoleName.clear();
            projAssignedEmpIncharge.clear();
            projTeamDet.setProjId(getProjIdParam);
            projTeamDet.projTeamValue();
            projAssignedFacility = projTeamDet.getProjCellFacilName();
            projAssignedDepartment = projTeamDet.getProjCellDeptName();
            projAssignedCell = projTeamDet.getProjCellCellName();
            projAssignedEmpName = projTeamDet.getProjAssignedEmpName();
            projAssignedEmpDept = projTeamDet.getProjAssignedEmpDept();
            projAssignedEmpDesig = projTeamDet.getProjAssignedEmpDesig();
            projAssignedEmpFacName = projTeamDet.getProjAssignedEmpFacName();
            projAssignedEmpRoleName = projTeamDet.getProjAssignedEmpRoleName();
            projAssignedEmpIncharge = projTeamDet.getProjAssignedEmpPrimaryInch();

            //Get Estimation Details
            pathfinder.functions.revenue.EstimationInfo estInfo = new pathfinder.functions.revenue.EstimationInfo();
            estInfo.setProjId(getProjIdParam);
            estInfo.collectEstimationLineItems();

            // Get Current Invoice Details
            pathfinder.functions.revenue.InvoiceLineItem invoiceInfo = new pathfinder.functions.revenue.InvoiceLineItem();
            invoiceInfo.setProjID(getProjIdParam);
            invoiceInfo.collectInvoiceLineItem();

            // Get Purchase Order
            pathfinder.functions.revenue.POInfo poInfo = new pathfinder.functions.revenue.POInfo();
            poInfo.setProjId(getProjIdParam);
            poInfo.GetOpenPO();
            openPO = poInfo.getOpenPO();


            // Get Project Postage Detail (which Tranmittal Type is 'Outbound' and Billing Information/Option is 'Sender')
            pathfinder.functions.revenue.TransmittalDAO transmittalDAO = new pathfinder.functions.revenue.TransmittalDAO();
            pathfinder.functions.revenue.TransmittalVO transmittalVO = new pathfinder.functions.revenue.TransmittalVO();
            transmittalVO.setProjId(getProjIdParam);
            transmittalVO = transmittalDAO.GetShippingForReports(transmittalVO);
            postageValue = Double.parseDouble(transmittalVO.getTotalCarrierCost().toString());
            
            // Manupulate list of Authors into to a String
            authorName = projInfo.getAuthorsList();
            String authors = "";
            for (int index = 0; index < authorName.size(); index++) {
                if (index > 0) {
                    authors += "/ ";
                }
                authors += authorName.get(index) + " ";
            }

            // Compute total Estimation Value
            List estValueList = new ArrayList();
            estValueList = estInfo.getItemValue();
            for(int index=0 ; index < estValueList.size(); index++ ) {
                if(!estValueList.get(index).equals("")) {
                    estVal += Double.parseDouble(estValueList.get(index).toString());
                }
            }

            // Get total Invoice Value
            if(projInfo.getStatusCode().toString().equals("23") && invoiceInfo.getFinalInvoiceTotal().toString().equals("0")) {
                if(!invoiceInfo.getPartialInvoiceTotal().toString().equals("0")) {
                    invVal = Double.parseDouble(invoiceInfo.getPartialInvoiceTotal());
                }
            } else {
                if(!invoiceInfo.getFinalInvoiceTotal().toString().equals("0")) {
                    invVal = Double.parseDouble(invoiceInfo.getFinalInvoiceTotal());
                }
            }
totalamountForProfitcal = Double.parseDouble(invoiceInfo.getTotalamountForprofitcal().toString());
            // Get Invoice Mailed Date
            invMailedDate = invoiceInfo.getFinalInvoiceMailed() != null ? invoiceInfo.getFinalInvoiceMailed().toString() : "";

            PdfPCell cell;
            PdfPTable headerTable = new PdfPTable(4);
            float[] headerTableWidth = {20f, 60f, 10f, 10f};
            headerTable.setWidths(headerTableWidth);

            cell = new PdfPCell(new Paragraph(projInfo.getClient(), headerFont));
            disableBorders(cell);
            headerTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Job - " + projInfo.getAuthor() + " - " + authors + " - "+ projInfo.getTitle() , headerFont));
            disableBorders(cell);
            headerTable.addCell(cell);

            //cell = new PdfPCell(new Paragraph("Job Type : ", smallBold));
            cell = new PdfPCell(new Paragraph("", smallBold));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(cell);
            headerTable.addCell(cell);

            //cell = new PdfPCell(new Paragraph(projInfo.getType(), smallBold));
            cell = new PdfPCell(new Paragraph("", smallBold));
            disableBorders(cell);
            headerTable.addCell(cell);

            //Add empty line
            document.add(Chunk.NEWLINE);
            // Add empty line
            document.add(Chunk.NEWLINE);

            document.add(headerTable);

            //Add empty line
            document.add(new Paragraph("\n"));

            PdfPTable propertyTable = new PdfPTable(12);
            float[] propertyTableWidth = {12f, 5f, 10f, 6f, 7f, 3f, 10f, 5f, 7f, 5f, 7f, 5f};
            propertyTable.setWidths(propertyTableWidth);

            cell = new PdfPCell(new Paragraph("Estimated Selling Value:", smallBoldNote));
            disableBorders(cell);
            propertyTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(estVal != 0.0 ? decimalFormat.format(estVal) : "", smallNote));
            disableBorders(cell);
            propertyTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Actual Ship Date : ", smallBoldNote));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(cell);
            propertyTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(projInfo.getActualShipDate(), smallNote));
            disableBorders(cell);
            propertyTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Actual Pgs : ", smallBoldNote));
            disableBorders(cell);
            propertyTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(projInfo.getActual(), smallNote));
            disableBorders(cell);
            propertyTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Final Invoice Mailed:", smallBoldNote));
            disableBorders(cell);
            propertyTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(invMailedDate, smallNote));
            disableBorders(cell);
            propertyTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Final Invoice : ", smallBoldNote));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(cell);
            propertyTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(invVal != 0.0 ? decimalFormat.format(invVal) : "", smallNote));
            disableBorders(cell);
            propertyTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Total Invoice : ", smallBoldNote));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            disableBorders(cell);
            propertyTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(totalamountForProfitcal != 0.0 ? decimalFormat.format(totalamountForProfitcal) : "", smallNote));
            disableBorders(cell);
            propertyTable.addCell(cell);
            document.add(propertyTable);

            //Add empty line
            document.add(new Paragraph("\n"));
            
             PdfPTable propertyTable1 = new PdfPTable(2);
            float[] propertyTableWidth1 = {100f, 100f};
            propertyTable1.setWidths(propertyTableWidth1);
            cell = new PdfPCell(new Paragraph("Job Status : "+ projInfo.getStatus(), smallBoldNote));
            disableBorders(cell);
            propertyTable1.addCell(cell);

            cell = new PdfPCell(new Paragraph("", smallNote));
            disableBorders(cell);
            propertyTable1.addCell(cell);
            document.add(propertyTable1);

            document.add(new Paragraph("\n"));
            
            final LineSeparator lineSeparator = new LineSeparator();
            lineSeparator.setPercentage(85);
            document.add(lineSeparator);

            //Add empty line
            document.add(new Paragraph("\n"));

            JobCostOverViewDAO job_cost_over_view_DAO = new pathfinder.functions.revenue.JobCostOverViewDAO();
            JobCostOverViewVO job_cost_over_view_VO = new pathfinder.functions.revenue.JobCostOverViewVO();

            ArrayList dispProjDetailList = new ArrayList();
            ArrayList dispProjNameList = new ArrayList();
            ArrayList dispProjFPBHList = new ArrayList();
            ArrayList dispProjFPBCList = new ArrayList();
            ArrayList dispProjRPBHList = new ArrayList();
            ArrayList dispProjRPBCList = new ArrayList();
            ArrayList dispProjChnErrHrList = new ArrayList();
            ArrayList dispProjChnErrCtList = new ArrayList();
            ArrayList dispProjDbqErrHrList = new ArrayList();
            ArrayList dispProjDbqErrCtList = new ArrayList();
            ArrayList dispProjOthErrHrList = new ArrayList();
            ArrayList dispProjOthErrCtList = new ArrayList();
            ArrayList dispProjTOTBHList = new ArrayList();
            ArrayList dispProjTOTBCList = new ArrayList();
            ArrayList dispActivityList = new ArrayList();
            ArrayList dispMilestoneList = new ArrayList();
            ArrayList dispMilestoneCatList = new ArrayList();

            ArrayList jobCostCategoryIdList = new ArrayList();
            ArrayList jobCostCategoryValueList = new ArrayList();
            ArrayList jobCostCategoryAltIdList = new ArrayList();
            ArrayList jobCostCategoryAltValueList = new ArrayList();

            double FPBHTotal = 0.0;
            double FPBCTotal = 0.0;
            double RPBHTotal = 0.0;
            double RPBCTotal = 0.0;
            double DbqErrHrTotal = 0.0;
            double DbqErrCtTotal = 0.0;
            double ChnErrHrTotal = 0.0;
            double ChnErrCtTotal = 0.0;
            double OthErrHrTotal = 0.0;
            double OthErrCtTotal = 0.0;
            double GrandTotalBH = 0.0;
            double GrandTotalBC = 0.0;
            double totalCost = 0.0;

            job_cost_over_view_VO.setProjId(getProjIdParam);
            job_cost_over_view_VO = job_cost_over_view_DAO.GetUnPosted(job_cost_over_view_VO);
            unPosted = job_cost_over_view_VO.getUnPosted();
            dispProjDetailList = (ArrayList) job_cost_over_view_DAO.getProjWIPList(job_cost_over_view_VO);

            Iterator dispProjWIPItr = dispProjDetailList.iterator();

            while (dispProjWIPItr.hasNext()) {
                job_cost_over_view_VO = (pathfinder.functions.revenue.JobCostOverViewVO) dispProjWIPItr.next();
                //dispProjFunctionList.add(job_cost_over_view_VO.getDeptFunction());
                dispMilestoneCatList.add(job_cost_over_view_VO.getCategory());
                dispMilestoneList.add(job_cost_over_view_VO.getMilestone());
                dispProjNameList.add(job_cost_over_view_VO.getProjName());
                dispProjFPBHList.add(job_cost_over_view_VO.getFPBH());
                dispProjFPBCList.add(job_cost_over_view_VO.getFPBC());
                dispProjRPBHList.add(job_cost_over_view_VO.getRPBH());
                dispProjRPBCList.add(job_cost_over_view_VO.getRPBC());
                dispProjChnErrHrList.add(job_cost_over_view_VO.getCHNERRHR());
                dispProjChnErrCtList.add(job_cost_over_view_VO.getCHNERRCT());
                dispProjDbqErrHrList.add(job_cost_over_view_VO.getDBQERRHR());
                dispProjDbqErrCtList.add(job_cost_over_view_VO.getDBQERRCT());
                dispProjOthErrHrList.add(job_cost_over_view_VO.getOUTERRHR());
                dispProjOthErrCtList.add(job_cost_over_view_VO.getOUTERRCT());
                dispProjTOTBHList.add(job_cost_over_view_VO.getTOTBH());
                dispProjTOTBCList.add(job_cost_over_view_VO.getTOTBC());
                dispActivityList.add(job_cost_over_view_VO.getActivity());
            }

            // HashSet to remove duplicated categories from jobCostCategory to appropriate estimate value
            HashSet hash = new HashSet();
            hash.addAll(dispMilestoneCatList);
            jobCostCategoryIdList.clear();
            jobCostCategoryAltIdList.clear();
            jobCostCategoryIdList.addAll(hash);
            jobCostCategoryAltIdList.addAll(hash);

            // Get Estimated Value for every cateogies in JobCostOverView List
            job_cost_over_view_VO.setProjId(getProjIdParam);
            job_cost_over_view_VO.setJobcostCategoryIDList(jobCostCategoryIdList);
            // Get Category Estimate Value
            job_cost_over_view_VO = job_cost_over_view_DAO.CollectEstimateValForJCOV(job_cost_over_view_VO);
            jobCostCategoryValueList = job_cost_over_view_VO.getJobcostCategoryValueList();
            // Get Category Alteration Value
            job_cost_over_view_VO.setJobcostCategoryAltIDList(jobCostCategoryAltIdList);
            job_cost_over_view_VO = job_cost_over_view_DAO.CollectAlterationValueForJCOV(job_cost_over_view_VO);
            jobCostCategoryAltValueList = job_cost_over_view_VO.getJobcostCategoryAltValueList();

            DecimalFormat df = new DecimalFormat("0.00");
            String previousMilestone = "";
            String currentMilestone = "";
            String currentCategory = "";
            String previousCategory = "";
            String dollar = "$ ";
            String categoryEstVal = "";
            String categoryAltVal = "";
            double categoryHours = 0.0;
            double alterationPer = 0.0;
            Boolean dispCategoryTotal = false;

            PdfPTable table = new PdfPTable(14);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            for (int disp = 0; disp < dispProjNameList.size(); disp++) {
                //For Category Separation
                currentCategory = dispMilestoneCatList.get(disp).toString();
                if (!currentCategory.equals(previousCategory)) {
                    categoryEstVal = "";
                    categoryAltVal = "";
                    if (jobCostCategoryIdList.contains(currentCategory)) {
                        categoryEstVal = jobCostCategoryValueList.get(jobCostCategoryIdList.indexOf(currentCategory)).toString();
                    }
                    if (jobCostCategoryAltIdList.contains(currentCategory)) {
                        categoryAltVal = jobCostCategoryAltValueList.get(jobCostCategoryAltIdList.indexOf(currentCategory)).toString();
                    }
                    previousCategory = currentCategory;

                    float[] tableWidth = {10f, 5f, 5f, 5f, 5f, 5f, 5f, 5f, 5f, 5f, 5f, 5f, 5f, 5f};
                    table.setWidths(tableWidth);

                    // Include the Table Header
                    table = GetHeaderContents(table, currentCategory, categoryEstVal, dollar, 1);
                }

                currentMilestone = dispMilestoneList.get(disp).toString();
                if (!currentMilestone.equals(previousMilestone)) {
                    previousMilestone = currentMilestone;

                    cell = new PdfPCell(new Paragraph(currentMilestone, small));
                    disableTopBottomRightBorders(cell);
                    cell.setBorderWidth(0.5f);
                    cell.setColspan(2);
                    cell.setPaddingLeft(10f);
                    table.addCell(cell);

                    //cell = new PdfPCell(new Paragraph("", small));
                    //cell.setBorderWidth(0.5f);
                    //table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(Double.parseDouble(dispProjFPBHList.get(disp).toString()) == 0 ? "" : df.format(Double.parseDouble(dispProjFPBHList.get(disp).toString())), small));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    disableTopBottomRightBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(Double.parseDouble(dispProjFPBCList.get(disp).toString()) == 0 ? "" : dollar + df.format(Double.parseDouble(dispProjFPBCList.get(disp).toString())), small));
                    disableTopBottomLeftBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(Double.parseDouble(dispProjRPBHList.get(disp).toString()) == 0 ? "" : df.format(Double.parseDouble(dispProjRPBHList.get(disp).toString())), small));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    disableTopBottomRightBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(Double.parseDouble(dispProjRPBCList.get(disp).toString()) == 0 ? "" : dollar + df.format(Double.parseDouble(dispProjRPBCList.get(disp).toString())), small));
                    disableTopBottomLeftBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(Double.parseDouble(dispProjDbqErrHrList.get(disp).toString()) == 0 ? "" : df.format(Double.parseDouble(dispProjDbqErrHrList.get(disp).toString())), small));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    disableTopBottomRightBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(Double.parseDouble(dispProjDbqErrCtList.get(disp).toString()) == 0 ? "" : dollar + df.format(Double.parseDouble(dispProjDbqErrCtList.get(disp).toString())), small));
                    disableTopBottomLeftBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(Double.parseDouble(dispProjChnErrHrList.get(disp).toString()) == 0 ? "" : df.format(Double.parseDouble(dispProjChnErrHrList.get(disp).toString())), small));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    disableTopBottomRightBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(Double.parseDouble(dispProjChnErrCtList.get(disp).toString()) == 0 ? "" : dollar + df.format(Double.parseDouble(dispProjChnErrCtList.get(disp).toString())), small));
                    disableTopBottomLeftBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(Double.parseDouble(dispProjOthErrHrList.get(disp).toString()) == 0 ? "" : df.format(Double.parseDouble(dispProjOthErrHrList.get(disp).toString())), small));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    disableTopBottomRightBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(Double.parseDouble(dispProjOthErrCtList.get(disp).toString()) == 0 ? "" : dollar + df.format(Double.parseDouble(dispProjOthErrCtList.get(disp).toString())), small));
                    disableTopBottomLeftBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(Double.parseDouble(dispProjTOTBHList.get(disp).toString()) == 0 ? "" : df.format(Double.parseDouble(dispProjTOTBHList.get(disp).toString())), small));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    disableTopBottomRightBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(Double.parseDouble(dispProjTOTBCList.get(disp).toString()) == 0 ? "" : dollar + df.format(Double.parseDouble(dispProjTOTBCList.get(disp).toString())), small));
                    disableTopBottomLeftBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);
                }

                FPBHTotal += Double.parseDouble(dispProjFPBHList.get(disp).toString());
                FPBCTotal += Double.parseDouble(dispProjFPBCList.get(disp).toString());
                RPBHTotal += Double.parseDouble(dispProjRPBHList.get(disp).toString());
                RPBCTotal += Double.parseDouble(dispProjRPBCList.get(disp).toString());
                DbqErrHrTotal += Double.parseDouble(dispProjDbqErrHrList.get(disp).toString());
                DbqErrCtTotal += Double.parseDouble(dispProjDbqErrCtList.get(disp).toString());
                ChnErrHrTotal += Double.parseDouble(dispProjChnErrHrList.get(disp).toString());
                ChnErrCtTotal += Double.parseDouble(dispProjChnErrCtList.get(disp).toString());
                OthErrHrTotal += Double.parseDouble(dispProjOthErrHrList.get(disp).toString());
                OthErrCtTotal += Double.parseDouble(dispProjOthErrCtList.get(disp).toString());
                GrandTotalBH += Double.parseDouble(dispProjTOTBHList.get(disp).toString());
                GrandTotalBC += Double.parseDouble(dispProjTOTBCList.get(disp).toString());

                categoryHours += Double.parseDouble(dispProjRPBHList.get(disp).toString());

                dispCategoryTotal = false;
                if (disp < dispMilestoneCatList.size() - 1) {
                    if (!currentCategory.equals(dispMilestoneCatList.get(disp + 1).toString())) {
                        dispCategoryTotal = true;
                    }
                } else if (disp == dispMilestoneCatList.size() - 1) {
                    dispCategoryTotal = true;
                } else {
                    dispCategoryTotal = false;
                }
                if (dispCategoryTotal) {

                    if(!categoryEstVal.toString().equals("0")) {
                        alterationPer = (categoryHours*Double.parseDouble(categoryAltVal.toString())) / Double.parseDouble(categoryEstVal.toString());
                    } else {
                        alterationPer = 0.0;
                    }
                  
                    if(categoryEstVal.toString().equals("0")) {
                        cell = new PdfPCell(new Paragraph("No Estimated Selling Value", redFont));
                        setHeaderProperty(cell);
                        disableRightBorders(cell);
                        cell.setBorderWidth(0.5f);
                        cell.setColspan(2);
                        table.addCell(cell);
                    } else if(categoryHours == 0.0) {
                        cell = new PdfPCell(new Paragraph("No Alteration Hours", redFont));
                        setHeaderProperty(cell);
                        disableRightBorders(cell);
                        cell.setBorderWidth(0.5f);
                        cell.setColspan(2);
                        table.addCell(cell);
                   } else if (categoryAltVal.toString().equals("0")) {
                        cell = new PdfPCell(new Paragraph("No Alteration Cost", redFont));
                        setHeaderProperty(cell);
                        disableRightBorders(cell);
                        cell.setBorderWidth(0.5f);
                        cell.setColspan(2);
                        table.addCell(cell);
                   } else {
                        cell = new PdfPCell(new Paragraph("Alterations", smallBold));
                        disableRightBorders(cell);
                        cell.setBorderWidth(0.5f);
                        table.addCell(cell);

                        cell = new PdfPCell(new Paragraph(df.format(alterationPer)+" %", smallBold));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        disableLeftBorders(cell);
                        cell.setBorderWidth(0.5f);
                        table.addCell(cell);
                    }
                    categoryHours = 0.0;

                    cell = new PdfPCell(new Paragraph(FPBHTotal == 0.0 ? "" : df.format(FPBHTotal), smallBold));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    disableRightBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(FPBCTotal == 0.0 ? "" : dollar + df.format(FPBCTotal), smallBold));
                    disableLeftBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(RPBHTotal == 0.0 ? "" : df.format(RPBHTotal), smallBold));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    disableRightBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(RPBCTotal == 0.0 ? "" : dollar + df.format(RPBCTotal), smallBold));
                    disableLeftBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(DbqErrHrTotal == 0 ? "" : df.format(DbqErrHrTotal), smallBold));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    disableRightBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(DbqErrCtTotal == 0.0 ? "" : dollar + df.format(DbqErrCtTotal), smallBold));
                    disableLeftBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(ChnErrHrTotal == 0.0 ? "" : df.format(ChnErrHrTotal), smallBold));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    disableRightBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(ChnErrCtTotal == 0.0 ? "" : dollar + df.format(ChnErrCtTotal), smallBold));
                    disableLeftBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(OthErrHrTotal == 0.0 ? "" : df.format(OthErrHrTotal), smallBold));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    disableRightBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(OthErrCtTotal == 0.0 ? "" : dollar + df.format(OthErrCtTotal), smallBold));
                    disableLeftBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(GrandTotalBH == 0.0 ? "" : df.format(GrandTotalBH), smallBold));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    disableRightBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(GrandTotalBC == 0.0 ? "" : dollar + df.format(GrandTotalBC), smallBold));
                    disableLeftBorders(cell);
                    cell.setBorderWidth(0.5f);
                    table.addCell(cell);

                    totalCost += GrandTotalBC;
                    document.add(table);
                    table.flushContent();

                    FPBHTotal = 0.0;
                    FPBCTotal = 0.0;
                    RPBHTotal = 0.0;
                    RPBCTotal = 0.0;
                    DbqErrHrTotal = 0.0;
                    DbqErrCtTotal = 0.0;
                    ChnErrHrTotal = 0.0;
                    ChnErrCtTotal = 0.0;
                    OthErrHrTotal = 0.0;
                    OthErrCtTotal = 0.0;
                    GrandTotalBH = 0.0;
                    GrandTotalBC = 0.0;

                    //Add empty line
                    document.add(new Paragraph("\n"));

                }
            }

            poInfo.getPOforReport();

            ArrayList functionList = new ArrayList();
            ArrayList totalList = new ArrayList();
            List poRecvdFlagList = new ArrayList();
            
            functionList = (ArrayList) poInfo.getFunctionList();
            totalList = (ArrayList) poInfo.getTotalList();
            poRecvdFlagList = poInfo.getPOReceivedFlagList();

            Double PoTotal= 0.0;
            Double receivedPOTotal= 0.0;

            float[] tableWidth = {10f, 5f, 5f, 5f, 5f, 5f, 5f, 5f, 5f, 5f, 5f, 5f, 5f, 5f};
            table.setWidths(tableWidth);

            // Include the Table Header
            table = GetHeaderContents(table, "Purchase Orders", "", dollar, 0);

            for(int index=0; index < functionList.size(); index++) {
                //make PO received items are bold and unreceived items are normal
                if ("0".equals(poRecvdFlagList.get(index))) {
                    makeDiff = small;
                }
                else {
                    makeDiff = smallBold;
                    receivedPOTotal = Double.parseDouble(totalList.get(index).toString());
                }

                cell = new PdfPCell(new Paragraph(functionList.get(index).toString(), makeDiff));
                disableTopBottomRightBorders(cell);
                cell.setBorderWidth(0.5f);
                cell.setColspan(2);
                cell.setPaddingLeft(10f);
                table.addCell(cell);

                //cell = new PdfPCell(new Paragraph("", small));
                //cell.setBorderWidth(0.5f);
                //table.addCell(cell);

                cell = new PdfPCell(new Paragraph("", makeDiff));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                disableTopBottomRightBorders(cell);
                cell.setBorderWidth(0.5f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(dollar+df.format(Double.parseDouble(totalList.get(index).toString())), makeDiff));
                disableTopBottomLeftBorders(cell);
                cell.setBorderWidth(0.5f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("", makeDiff));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                disableTopBottomRightBorders(cell);
                cell.setBorderWidth(0.5f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("", makeDiff));
                disableTopBottomLeftBorders(cell);
                cell.setBorderWidth(0.5f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("", makeDiff));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                disableTopBottomRightBorders(cell);
                cell.setBorderWidth(0.5f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("", makeDiff));
                disableTopBottomLeftBorders(cell);
                cell.setBorderWidth(0.5f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("", makeDiff));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                disableTopBottomRightBorders(cell);
                cell.setBorderWidth(0.5f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("", makeDiff));
                disableTopBottomLeftBorders(cell);
                cell.setBorderWidth(0.5f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("", makeDiff));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                disableTopBottomRightBorders(cell);
                cell.setBorderWidth(0.5f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("", makeDiff));
                disableTopBottomLeftBorders(cell);
                cell.setBorderWidth(0.5f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("", makeDiff));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                disableTopBottomRightBorders(cell);
                cell.setBorderWidth(0.5f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(dollar+df.format(Double.parseDouble(totalList.get(index).toString())), makeDiff));
                disableTopBottomLeftBorders(cell);
                cell.setBorderWidth(0.5f);
                table.addCell(cell);

                PoTotal += Double.parseDouble(String.valueOf(receivedPOTotal));
            }

            cell = new PdfPCell(new Paragraph("No Estimated Selling Value", redFont));
            setHeaderProperty(cell);
            disableRightBorders(cell);
            cell.setBorderWidth(0.5f);
            cell.setColspan(2);
            //cell.disableBorderSide(PdfPCell.TOP);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("", smallBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            disableRightBorders(cell);
            cell.setBorderWidth(0.5f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(dollar+df.format(PoTotal), smallBold));
            disableLeftBorders(cell);
            cell.setBorderWidth(0.5f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("", smallBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            disableRightBorders(cell);
            cell.setBorderWidth(0.5f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("", smallBold));
            disableLeftBorders(cell);
            cell.setBorderWidth(0.5f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("", smallBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            disableRightBorders(cell);
            cell.setBorderWidth(0.5f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("", smallBold));
            disableLeftBorders(cell);
            cell.setBorderWidth(0.5f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("", smallBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            disableRightBorders(cell);
            cell.setBorderWidth(0.5f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("", smallBold));
            disableLeftBorders(cell);
            cell.setBorderWidth(0.5f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("", smallBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            disableRightBorders(cell);
            cell.setBorderWidth(0.5f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("", smallBold));
            disableLeftBorders(cell);
            cell.setBorderWidth(0.5f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("", smallBold));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            disableRightBorders(cell);
            cell.setBorderWidth(0.5f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(dollar+df.format(PoTotal), smallBold));
            disableLeftBorders(cell);
            cell.setBorderWidth(0.5f);
            table.addCell(cell);

            // Adding received Purchase Order cost with JCO Cost.
            totalCost += PoTotal;

            document.add(table);

            table.flushContent();

            //Add empty line
            document.add(Chunk.NEWLINE);

            PdfContentByte cbVertical = writer.getDirectContent();
            float c = writer.getVerticalPosition(true);

            cbVertical.roundRectangle(88f,c-25,665f,25f,5f);
            //cbVertical.roundRectangle(LEFT,TOP,WIDTH,HEIGHT,RADIOUS);

            float[] totalTableWidth = {5f, 1f, 5f, 1f, 5f, 1f, 5f, 1f, 5f, 5f, 5f};
            PdfPTable totalTable = new PdfPTable(11);
            totalTable.setWidths(totalTableWidth);

            cell = new PdfPCell(new Paragraph("Cost To Date", smallBold));
            //disableBottomRighttBorders(cell);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            //cell.disableBorderSide(PdfPCell.LEFT);
            cell.disableBorderSide(PdfPCell.TOP);
            cell.disableBorderSide(PdfPCell.BOTTOM);
            cell.disableBorderSide(PdfPCell.RIGHT);

            cell.setBorderWidth(0.5f);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("+", smallBold));
            //disableLeftRightBorders(cell);
            disableBorders(cell);
            cell.setRowspan(2);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Open PO $", smallBold));
            //disableBottomLeftRightBorders(cell);
            disableBorders(cell);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("+", smallBold));
            //disableLeftRightBorders(cell);
            disableBorders(cell);
            cell.setRowspan(2);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Unposted $", smallBold));
            //disableBottomLeftRightBorders(cell);
            disableBorders(cell);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("+", smallBold));
            //disableLeftRightBorders(cell);
            disableBorders(cell);
            cell.setRowspan(2);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Postage $", smallBold));
            //disableBottomLeftRightBorders(cell);
            disableBorders(cell);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("=", smallBold));
            //disableLeftRightBorders(cell);
            disableBorders(cell);
            cell.setRowspan(2);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Total Cost to Date", smallBold));
            //disableBottomLeftRightBorders(cell);
            disableBorders(cell);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Estimated % Profit", smallBold));
            //disableBottomLeftRightBorders(cell);
            disableBorders(cell);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Actual % Profit", smallBold));
            //disableBottomLeftBorders(cell);
            disableBorders(cell);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(decimalFormat.format(totalCost), small));
            //disableTopRightBorders(cell);
            disableBorders(cell);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(df.format(openPO), small));
            //disableTopLeftRightBorders(cell);
            disableBorders(cell);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(df.format(unPosted), small));
            //disableTopLeftRightBorders(cell);
            disableBorders(cell);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(df.format(postageValue), small));
            //disableTopLeftRightBorders(cell);
            disableBorders(cell);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(df.format(totalCost + openPO + unPosted + postageValue), small));
            //disableTopLeftRightBorders(cell);
            disableBorders(cell);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(estVal != 0.0 ? df.format(100 * (1 - ((totalCost + openPO + unPosted)/estVal))) : "", small));
            //disableTopLeftRightBorders(cell);
            disableBorders(cell);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            cell = new PdfPCell(new Paragraph(totalamountForProfitcal != 0.0 ? df.format(100 * (1 - ((totalCost + openPO + unPosted)/totalamountForProfitcal))) : "", small));
            //disableTopLeftBorders(cell);
            disableBorders(cell);
            setHeaderProperty(cell);
            totalTable.addCell(cell);

            document.add(totalTable);

            //Add new Page
            document.newPage();

            //Add empty line
            document.add(Chunk.NEWLINE);

            float[] teamTableWidth = {8f, 8f, 8f};
            PdfPTable teamTable = new PdfPTable(3);
            teamTable.setWidths(teamTableWidth);

            cell = new PdfPCell(new Paragraph("Team Allocation", subFont));
            cell.setBackgroundColor(headerColor);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            NormalHeaderProperty(cell);
            teamTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Facility", normalHeaderFont));
            cell.setBackgroundColor(headerColor);
            NormalHeaderProperty(cell);
            teamTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Department", normalHeaderFont));
            cell.setBackgroundColor(headerColor);
            NormalHeaderProperty(cell);
            teamTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Cell", normalHeaderFont));
            cell.setBackgroundColor(headerColor);
            NormalHeaderProperty(cell);
            teamTable.addCell(cell);

            if (projAssignedFacility.size() != 0) {
                for (int projTeamIdx = 0; projTeamIdx < projAssignedFacility.size(); projTeamIdx++) {
                    cell = new PdfPCell(new Paragraph(projAssignedFacility.get(projTeamIdx).toString(), normalFont));
                    NormalProperty(cell);
                    teamTable.addCell(cell);

                    cell = new PdfPCell(new Paragraph(projAssignedDepartment.get(projTeamIdx).toString(), normalFont));
                    NormalProperty(cell);
                    teamTable.addCell(cell);

                    cell = new PdfPCell(new Paragraph(projAssignedCell.get(projTeamIdx).toString(), normalFont));
                    NormalProperty(cell);
                    teamTable.addCell(cell);
                }
            } else {
                cell = new PdfPCell(new Paragraph("Yet to allocate team for this project.", redFont));
                cell.setColspan(3);
                NormalProperty(cell);
                teamTable.addCell(cell);
            }

            document.add(teamTable);

            // Add empty line
            document.add(Chunk.NEWLINE);

            float[] empTableWidth = {12f, 6f, 10f, 6f, 6f};
            PdfPTable empTable = new PdfPTable(5);
            empTable.setWidths(empTableWidth);

            cell = new PdfPCell(new Paragraph("Employee Allocation", subFont));
            cell.setBackgroundColor(headerColor);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            NormalHeaderProperty(cell);
            cell.setColspan(5);
            empTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Facility", normalHeaderFont));
            cell.setBackgroundColor(headerColor);
            NormalHeaderProperty(cell);
            empTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Role", normalHeaderFont));
            cell.setBackgroundColor(headerColor);
            NormalHeaderProperty(cell);
            empTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Department", normalHeaderFont));
            cell.setBackgroundColor(headerColor);
            NormalHeaderProperty(cell);
            empTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Designation", normalHeaderFont));
            cell.setBackgroundColor(headerColor);
            NormalHeaderProperty(cell);
            empTable.addCell(cell);

            cell = new PdfPCell(new Paragraph("Employee Name", normalHeaderFont));
            cell.setBackgroundColor(headerColor);
            NormalHeaderProperty(cell);
            empTable.addCell(cell);

            Font fontStyle;

            if (projAssignedEmpName.size() != 0) {
                for (int projEmpdx = 0; projEmpdx < projAssignedEmpName.size(); projEmpdx++) {
                    if(projAssignedEmpIncharge.get(projEmpdx).equals("1")) {
                        fontStyle = normalHeaderFont;
                    } else {
                        fontStyle = normalFont;
                    }
                    cell = new PdfPCell(new Paragraph(projAssignedEmpFacName.get(projEmpdx).toString(), fontStyle));
                    NormalProperty(cell);
                    //disableLeftRightBorders(cell);
                    empTable.addCell(cell);

                    cell = new PdfPCell(new Paragraph(projAssignedEmpRoleName.get(projEmpdx).toString(), fontStyle));
                    NormalProperty(cell);
                    //disableBottomLeftRightBorders(cell);
                    empTable.addCell(cell);

                    cell = new PdfPCell(new Paragraph(projAssignedEmpDept.get(projEmpdx).toString(), fontStyle));
                    NormalProperty(cell);
                    //disableLeftRightBorders(cell);
                    empTable.addCell(cell);

                    cell = new PdfPCell(new Paragraph(projAssignedEmpDesig.get(projEmpdx).toString(), fontStyle));
                    NormalProperty(cell);
                    //disableBottomLeftRightBorders(cell);
                    empTable.addCell(cell);

                    cell = new PdfPCell(new Paragraph(projAssignedEmpName.get(projEmpdx).toString(), fontStyle));
                    NormalProperty(cell);
                    //disableLeftRightBorders(cell);
                    empTable.addCell(cell);
                }
            } else {
                cell = new PdfPCell(new Paragraph("Yet to allocate employee(s) for this project.", redFont));
                cell.setColspan(5);
                //disableLeftRightBorders(cell);
                NormalProperty(cell);
                empTable.addCell(cell);
            }

            document.add(empTable);

            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static PdfPTable GetHeaderContents(PdfPTable table, String category, String estValue, String dollar, int flag) {
            PdfPCell cell;
            cell = new PdfPCell(new Paragraph(category, headerFont));
            disableRightBorders(cell);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorderWidth(0.5f);
            cell.setRowspan(2);
            table.addCell(cell);

            if(flag == 1 & !estValue.equals("0")) {
                estValue = dollar+estValue;
                cell = new PdfPCell(new Paragraph("Est SV", small));
            } else {
                estValue = "";
                cell = new PdfPCell(new Paragraph("", smallBold));
            }
            disableBottomLeftBorders(cell);
            setHeaderProperty(cell);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("First Pass Pages", smallBold));
            setHeaderProperty(cell);
            disableBottomBorders(cell);
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Cust or Author Alts", smallBold));
            setHeaderProperty(cell);
            disableBottomBorders(cell);
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Dubuque Errors", smallBold));
            setHeaderProperty(cell);
            disableBottomBorders(cell);
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Chennai Errors", smallBold));
            setHeaderProperty(cell);
            disableBottomBorders(cell);
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Outside Errors", smallBold));
            setHeaderProperty(cell);
            disableBottomBorders(cell);
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Total", smallBold));
            setHeaderProperty(cell);
            disableBottomBorders(cell);
            cell.setColspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(estValue, small));
            disableTopLeftBorders(cell);
            setHeaderProperty(cell);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Act Hrs", smallBold));
            setHeaderProperty(cell);
            disableTopRightBorders(cell);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Act Cost", smallBold));
            setHeaderProperty(cell);
            disableTopLeftBorders(cell);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Hours", smallBold));
            setHeaderProperty(cell);
            disableTopRightBorders(cell);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Cost", smallBold));
            setHeaderProperty(cell);
            disableTopLeftBorders(cell);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Hours", smallBold));
            setHeaderProperty(cell);
            disableTopRightBorders(cell);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Cost", smallBold));
            setHeaderProperty(cell);
            disableTopLeftBorders(cell);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Hours", smallBold));
            setHeaderProperty(cell);
            disableTopRightBorders(cell);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Cost", smallBold));
            setHeaderProperty(cell);
            disableTopLeftBorders(cell);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Hours", smallBold));
            setHeaderProperty(cell);
            disableTopRightBorders(cell);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Cost", smallBold));
            setHeaderProperty(cell);
            disableTopLeftBorders(cell);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Hours", smallBold));
            setHeaderProperty(cell);
            disableTopRightBorders(cell);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Cost", smallBold));
            setHeaderProperty(cell);
            disableTopLeftBorders(cell);
            table.addCell(cell);

            return table;
    }
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private static void disableBorders(PdfPCell cell) {
        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.RIGHT);
        cell.disableBorderSide(PdfPCell.TOP);
        cell.disableBorderSide(PdfPCell.BOTTOM);
    }

    private static void disableTopBottomRightBorders(PdfPCell cell) {
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.disableBorderSide(PdfPCell.TOP);
        cell.disableBorderSide(PdfPCell.BOTTOM);
        cell.disableBorderSide(PdfPCell.RIGHT);
    }

    private static void disableTopBottomLeftBorders(PdfPCell cell) {
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.disableBorderSide(PdfPCell.TOP);
        cell.disableBorderSide(PdfPCell.BOTTOM);
        cell.disableBorderSide(PdfPCell.LEFT);
    }

    private static void disableBottomLeftRightBorders(PdfPCell cell) {
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.disableBorderSide(PdfPCell.BOTTOM);
        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.RIGHT);
    }

    private static void disableTopLeftRightBorders(PdfPCell cell) {
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.disableBorderSide(PdfPCell.TOP);
        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.RIGHT);
    }

    private static void disableBottomLeftBorders(PdfPCell cell) {
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.disableBorderSide(PdfPCell.BOTTOM);
        cell.disableBorderSide(PdfPCell.LEFT);
    }

    private static void disableTopLeftBorders(PdfPCell cell) {
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.disableBorderSide(PdfPCell.TOP);
        cell.disableBorderSide(PdfPCell.LEFT);
    }

    private static void disableBottomRighttBorders(PdfPCell cell) {
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.disableBorderSide(PdfPCell.BOTTOM);
        cell.disableBorderSide(PdfPCell.RIGHT);
    }

    private static void disableTopRightBorders(PdfPCell cell) {
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.disableBorderSide(PdfPCell.TOP);
        cell.disableBorderSide(PdfPCell.RIGHT);
    }

    private static void disableLeftRightBorders(PdfPCell cell) {
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.disableBorderSide(PdfPCell.LEFT);
        cell.disableBorderSide(PdfPCell.RIGHT);
    }

    private static void disableLeftBorders(PdfPCell cell) {
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.disableBorderSide(PdfPCell.LEFT);
    }

    private static void disableRightBorders(PdfPCell cell) {
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.disableBorderSide(PdfPCell.RIGHT);
    }

    private static void disableBottomBorders(PdfPCell cell) {
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.disableBorderSide(PdfPCell.BOTTOM);
    }

    private static void setHeaderProperty(PdfPCell cell) {
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderWidth(0.5f);
    }

    private static void NormalProperty(PdfPCell cell) {
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setMinimumHeight(20f);
        cell.setBorderWidth(0.5f);
    }

    private static void NormalHeaderProperty(PdfPCell cell) {
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setMinimumHeight(20f);
        cell.setBorderWidth(0.5f);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
