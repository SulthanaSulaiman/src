/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.simpleparser.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.*;
import pathfinder.functions.timesheet.*;

/**
 *
 * @author Parameshwarant
 */
public class ProductivityHoursPdfServlet extends HttpServlet {

    protected PdfPTable prjoectsHeader, prjoectsFooter, footerStatus, headerDisp, pageNumber;
    protected Phrase phrase;
    float[] columnParams = {30f, 30f, 30f};
    float[] columnParams1 = {10f, 15f, 10f, 10f, 10f};
    private static Font bold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static Font smallNote = new Font(Font.FontFamily.HELVETICA, 10);
    PlannerPdfHeader headerClass = new PlannerPdfHeader();
    List projNameList = new ArrayList();
    List empIdList = new ArrayList();
    List empNameList = new ArrayList();
    List activityIdList = new ArrayList();
    List activityNameList = new ArrayList();
    List secondsSpentList = new ArrayList();
    List empShiftStartTimeList = new ArrayList();
    List empShiftEndTimeList = new ArrayList();
    List isChargeableFlagList = new ArrayList();
    List chnErrSecondsList = new ArrayList();
    List dbqErrSecondsList = new ArrayList();
    List outErrSecondsList = new ArrayList();
    List emp_idList = new ArrayList();
    List emp_nameList = new ArrayList();
    List shiftSecondsList = new ArrayList();
    List facilityIdList = new ArrayList();
    List shiftEmpIdList = new ArrayList();
    List facilityWorkHourList = new ArrayList();
    List shiftDaysdiff = new ArrayList();
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
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

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", " inline; filename=ProfitReport.pdf");

        response.setContentType("application/pdf");
        Document document = new Document();

        HTMLWorker htmlWorker = new HTMLWorker(document);

        String SourcePage = request.getRequestURI().toString();
        SourcePage = SourcePage.substring(SourcePage.lastIndexOf("/") + 1);

        String getShiftStartDate = request.getParameter("shiftStartDate") != null ? request.getParameter("shiftStartDate") : "";
        //String getShiftEndDate = request.getParameter("shiftEndDate") != null ? request.getParameter("shiftEndDate") : "";
        String getEmployeeID = request.getParameter("employeeId") != null ? request.getParameter("employeeId") : "";
        String getNumOfWeek = request.getParameter("numOfWeek") != null ? request.getParameter("numOfWeek") : "";

        int shiftDaysDiff = 0;
        int empCount = 0;
        int numberOfWeek = 0;
        if(!getNumOfWeek.equals("")) {
            numberOfWeek = Integer.parseInt(getNumOfWeek);
        }

        try {
            ProductionByHoursDAO setGetProductivityValues = new ProductionByHoursDAO();
            ProductionByHoursVO getSetProductivityValues = new ProductionByHoursVO();

//            if (!getShiftStartDate.equals("") && !getShiftEndDate.equals("")) {
//                getSetProductivityValues.setShiftDateChk("1");
//                getSetProductivityValues.setEmpShiftStart(getShiftStartDate);
//                getSetProductivityValues.setEmpShiftEnd(getShiftEndDate);
//            }
            if (!getShiftStartDate.equals("")) {
                getSetProductivityValues.setEmpShiftStart(getShiftStartDate);
            }
            if (!getEmployeeID.equals("")) {
                getSetProductivityValues.setEmpId(getEmployeeID);
            }
            if(numberOfWeek!=0) {
                getSetProductivityValues.setNumberOfWeek(numberOfWeek);
            }
            setGetProductivityValues.getProductionHours(getSetProductivityValues);

            empIdList = getSetProductivityValues.getEmpIdList();
            empNameList = getSetProductivityValues.getEmpNameList();
            activityIdList = getSetProductivityValues.getActivityIdList();
            activityNameList = getSetProductivityValues.getActivityNameList();
            empShiftStartTimeList = getSetProductivityValues.getShiftStartTimeList();
            empShiftEndTimeList = getSetProductivityValues.getShiftEndTimeList();
            isChargeableFlagList = getSetProductivityValues.getIsChargeableFlagList();
            chnErrSecondsList = getSetProductivityValues.getChnErrorList();
            dbqErrSecondsList = getSetProductivityValues.getDbqErrorList();
            outErrSecondsList = getSetProductivityValues.getOutErrorList();
            secondsSpentList = getSetProductivityValues.getSecondsSpentListList();
            shiftSecondsList = getSetProductivityValues.getShiftSecondsList();
            shiftDaysDiff = getSetProductivityValues.getShiftDaysDiff();
            facilityIdList = getSetProductivityValues.getFacilityIdList();
            shiftEmpIdList = getSetProductivityValues.getShiftEmpIdList();
            facilityWorkHourList = getSetProductivityValues.getFacilityWorkList();
            shiftDaysdiff = getSetProductivityValues.getShiftDayDiff();

            PdfWriter writer = PdfWriter.getInstance(document,
                    response.getOutputStream());

            writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage);

            writer.setPageEvent(new ProductivityReportHeaderFooterDisp());

            document.setPageSize(PageSize.A4.rotate());
            document.setMargins(18, 20, 88, 50);
            document.open();

            addHeader();
            addFooter();
            addOverallHeader();


            PdfContentByte cb = writer.getDirectContent();

            prjoectsHeader.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() + 60, cb);
            prjoectsFooter.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() - 470, cb);
            headerDisp.writeSelectedRows(0, -1,
                    (document.right() - document.left() - 800) / 2
                    + document.leftMargin(), document.top() + 84, cb);


            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(String.format("%d", writer.getPageNumber()), bold),
                    (document.right() - document.left() + 755) / 2 + document.leftMargin(), document.top() - 490, 0);

            PdfPTable contenPdfPTable = new PdfPTable(columnParams);


            List shiftSecondschk = new ArrayList();
            List empIdDupList = new ArrayList();
            List shiftHourChkList = new ArrayList();
            List empNamesList = new ArrayList();
            List genralActTotalList = new ArrayList();
            List projActTotalList = new ArrayList();
            List projActWithoutErrList = new ArrayList();
            List projActivityList = new ArrayList();
            List teaBreakList = new ArrayList();
            List lunchBreakList = new ArrayList();
            List trainingList = new ArrayList();
            List meetingAndCoordinationList = new ArrayList();
            List idleList = new ArrayList();

            float teaBreak = 0;
            float idleAct = 0;
            float lunch = 0;
            float training = 0;
            float meetingAndCoordination = 0;
            float totalGeneralActivity = 0;
            float projectActivity = 0;
            float totalProjectActivity = 0;
            float chnErr = 0;
            float dbqErr = 0;
            float outErr = 0;
            float error = 0;
            float productivityPercentage = 0;
            float teaLunchBreak = 0;
            float projAndGeneralActTime = 0;
            float shiftSeconds = 0;
            float overTime = 0;
            float overTimeChk = 0;
            float idleTime = 0;
            float workWithoutErr = 0;
            float actualProjActTime = 0;

            String chnErrs = "";
            String dbqErrs = "";
            String outErrs = "";
            String activityNames = "";
            String actName = "";
            String isChargeable = "";
            String activityID = "";
            String currEmpId = "";
            String currEmpName = "";
            String name = "";
            String facilityId = "";
            String timeCalculation = "";
            int idxs = 0;
            int regularWorkHours = 0;
            int facilityWorkHour = 0;
            float regularHour = 0;
            float workedHour = 0;
            float projectActTotal = 0;
            for (int index = 0; index < activityIdList.size(); index++) {


                timeCalculation = secondsSpentList.get(index).toString();
                activityID = activityIdList.get(index).toString();

                name = currEmpName + "/" + currEmpId;
                
                if (shiftEmpIdList.contains(currEmpId)) {
                    idxs = shiftEmpIdList.indexOf(currEmpId);
                    shiftSeconds = Float.parseFloat(shiftSecondsList.get(idxs).toString());
                    facilityId = facilityIdList.get(idxs).toString();
                    facilityWorkHour = Integer.parseInt(facilityWorkHourList.get(idxs).toString());
                    regularWorkHours = Integer.parseInt(shiftDaysdiff.get(idxs).toString());
                } else {
                    shiftSeconds = totalGeneralActivity + totalProjectActivity;
                }
                currEmpName = empNameList.get(index).toString();
                currEmpId = empIdList.get(index).toString();

                if (!empIdDupList.contains(currEmpId)) {

                    if (index != 0) {
                        empNamesList.add(name);
                        genralActTotalList.add(decimalFormat.format(totalGeneralActivity));
                        projActTotalList.add(decimalFormat.format(totalProjectActivity));
                        projActivityList.add(activityNames);
                        teaBreakList.add(teaBreak);
                        lunchBreakList.add(lunch);
                        trainingList.add(training);
                        projActWithoutErrList.add(decimalFormat.format(totalProjectActivity - error));
                        shiftSecondschk.add(shiftSeconds);
                        shiftHourChkList.add(regularWorkHours);
                        idleList.add(idleAct);
                        meetingAndCoordinationList.add(meetingAndCoordination);
                    }
                    empIdDupList.add(currEmpId);
                    chnErr = 0;
                    dbqErr = 0;
                    outErr = 0;
                    teaBreak = 0;
                    idleAct = 0;
                    lunch = 0;
                    training = 0;
                    shiftSeconds = 0;
                    projectActivity = 0;
                    totalProjectActivity = 0;
                    totalGeneralActivity = 0;
                    activityNames = "";

                    if (activityID.equals("170")) {
                        teaBreak = Float.parseFloat(timeCalculation);
                    } else if (activityID.equals("75")) {
                        lunch = Float.parseFloat(timeCalculation);
                    } else if (activityID.equals("168")) {
                        training = Float.parseFloat(timeCalculation);
                    } else if (activityID.equals("73")) {
                        idleAct += Float.parseFloat(timeCalculation);
                    } else if (activityID.equals("74")) {
                        meetingAndCoordination += Float.parseFloat(timeCalculation);
                    } else {
                        projectActivity = Float.parseFloat(timeCalculation);
                        totalProjectActivity += projectActivity;
                        actName = activityNameList.get(index).toString();
                        isChargeable = isChargeableFlagList.get(index).toString();
                        chnErrs = chnErrSecondsList.get(index).toString();
                        dbqErrs = dbqErrSecondsList.get(index).toString();
                        outErrs = outErrSecondsList.get(index).toString();
                        if (isChargeable.equals("Chargeable")) {
                            activityNames += actName+" -- "+isChargeable+" = "+projectActivity+"\n";

                        } else if (!chnErrs.equals("NO ERR")) {
                            chnErr += Float.parseFloat(chnErrs);
                            activityNames += actName+" -- Chennai Error = "+projectActivity+"\n";

                        } else if (!dbqErrs.equals("NO ERR")) {
                            dbqErr += Float.parseFloat(dbqErrs);
                            activityNames += actName+" -- Dubuque Error = "+projectActivity+"\n";

                        } else if (!outErrs.equals("NO ERR")) {
                            outErr += Float.parseFloat(outErrs);
                            activityNames += actName+" -- Outside Error = "+projectActivity+"\n";

                        } else {
                            activityNames += actName+" = "+projectActivity+"\n";
                        }
                    }

                    
                } else {
                    if (activityID.equals("170")) {
                        teaBreak = Float.parseFloat(timeCalculation);
                    } else if (activityID.equals("75")) {
                        lunch = Float.parseFloat(timeCalculation);
                    } else if (activityID.equals("168")) {
                        training = Float.parseFloat(timeCalculation);
                    } else if (activityID.equals("73")) {
                        idleAct += Float.parseFloat(timeCalculation);
                    } else if (activityID.equals("74")) {
                        meetingAndCoordination += Float.parseFloat(timeCalculation);
                    } else {
                        projectActivity = Float.parseFloat(timeCalculation);
                        totalProjectActivity += projectActivity;
                        actName = activityNameList.get(index).toString();
                        isChargeable = isChargeableFlagList.get(index).toString();
                        chnErrs = chnErrSecondsList.get(index).toString();
                        dbqErrs = dbqErrSecondsList.get(index).toString();
                        outErrs = outErrSecondsList.get(index).toString();
                        if (isChargeable.equals("Chargeable")) {
                            activityNames += actName+" -- "+isChargeable+" = "+projectActivity+"\n";

                        } else if (!chnErrs.equals("NO ERR")) {
                            chnErr += Float.parseFloat(chnErrs);
                            activityNames += actName+" -- Chennai Error = "+projectActivity+"\n";

                        } else if (!dbqErrs.equals("NO ERR")) {
                            dbqErr += Float.parseFloat(dbqErrs);
                            activityNames += actName+" -- Dubuque Error = "+projectActivity+"\n";

                        } else if (!outErrs.equals("NO ERR")) {
                            outErr += Float.parseFloat(outErrs);
                            activityNames += actName+" -- Outside Error = "+projectActivity+"\n";

                        } else {
                            activityNames += actName+" = "+projectActivity+"\n";
                        }
                    }

                }
                totalGeneralActivity = training + lunch + teaBreak + meetingAndCoordination;
                error = chnErr + dbqErr + outErr;
            }

            empNamesList.add(currEmpName + "/" + currEmpId);
            genralActTotalList.add(decimalFormat.format(totalGeneralActivity));
            projActTotalList.add(decimalFormat.format(totalProjectActivity));
            projActivityList.add(activityNames);
            teaBreakList.add(teaBreak);
            lunchBreakList.add(lunch);
            trainingList.add(training);
            projActWithoutErrList.add(decimalFormat.format(totalProjectActivity - error));
            shiftSecondschk.add(shiftSeconds);
            shiftHourChkList.add(regularWorkHours);
            idleList.add(idleAct);
            meetingAndCoordinationList.add(meetingAndCoordination);
            idleTime = 0;
            overTimeChk = 0;
            float idleHour = 0;
            float idleActivity = 0;
            for (int i = 0; i < empNamesList.size(); i++) {
                idleTime = 0 ;
                workWithoutErr = Float.parseFloat(projActWithoutErrList.get(i).toString());
                teaLunchBreak = Float.parseFloat(lunchBreakList.get(i).toString()) + Float.parseFloat(teaBreakList.get(i).toString());
                projectActTotal = Float.parseFloat(projActTotalList.get(i).toString());
                projAndGeneralActTime = projectActTotal + Float.parseFloat(genralActTotalList.get(i).toString());
                //projAndGeneralActTime = Float.parseFloat(projActTotalList.get(i).toString()) + Float.parseFloat(genralActTotalList.get(i).toString());
                regularHour = Float.parseFloat(shiftHourChkList.get(i).toString());
                workedHour = Float.parseFloat(shiftSecondschk.get(i).toString());
                idleActivity = Float.parseFloat(idleList.get(i).toString());

                //overTimeChk = workedHour - regularHour;
                idleHour = workedHour - (projAndGeneralActTime + idleActivity);
                if (idleHour > 0) {
                    idleTime = idleHour + idleActivity;
                } else {
                    idleTime = Float.parseFloat(idleList.get(i).toString());
                }

                // Compute Actual Productivity
                actualProjActTime = projAndGeneralActTime - teaLunchBreak;

                // Compute Productivity Percentage
                //productivityPercentage = (workWithoutErr/actualProjActTime)*100;
                //productivityPercentage = (actualProjActTime*100)/(actualProjActTime + idleTime);
                productivityPercentage = (projectActTotal*100)/actualProjActTime;

                // Compute Overtime
                overTimeChk = actualProjActTime - regularHour;
                if (overTimeChk > 0) {
                    overTime = overTimeChk;
                } else {
                    overTime = 0;
                }

                contenPdfPTable.setWidthPercentage(100f);
                contenPdfPTable.setTotalWidth(800f);

                PdfPCell cellstat = new PdfPCell(new Phrase(empNamesList.get(i).toString(), bold));
                cellstat.setBorder(Rectangle.TOP | Rectangle.LEFT);
                contenPdfPTable.addCell(cellstat);

                PdfPCell projectActcell = new PdfPCell();
                PdfPTable activityTable = new PdfPTable(1);

                PdfPCell actCell = new PdfPCell();
                Paragraph plannerPara = new Paragraph(new Phrase(projActivityList.get(i).toString(), smallNote));
                actCell.addElement(plannerPara);
                actCell.setBorder(0);
                activityTable.addCell(actCell);

                actCell = new PdfPCell(new Phrase("\n\nTotal = " + projActTotalList.get(i).toString() + "\n\nWithout Errors = " + projActWithoutErrList.get(i).toString() + "\n\n", smallNote));
                actCell.setBorder(0);
                activityTable.addCell(actCell);
                projectActcell.setBorder(Rectangle.TOP);
                projectActcell.addElement(activityTable);
                contenPdfPTable.addCell(projectActcell);
               
                PdfPCell generalActcell1 = new PdfPCell();
                PdfPTable generalActTable = new PdfPTable(2);

                PdfPCell generalActcell = new PdfPCell(new Phrase("Training", smallNote));
                generalActcell.setBorder(0);
                generalActTable.addCell(generalActcell);
                
                PdfPCell generalActcell2 = new PdfPCell(new Phrase(trainingList.get(i).toString(), smallNote));
                generalActcell2.setBorder(0);
                generalActTable.addCell(generalActcell2);
                
                generalActcell.setColspan(2);

                generalActcell = new PdfPCell(new Phrase("Lunch", smallNote));
                generalActcell.setBorder(0);
                generalActTable.addCell(generalActcell);
                
                generalActcell2 = new PdfPCell(new Phrase(lunchBreakList.get(i).toString(), smallNote));
                generalActcell2.setBorder(0);
                generalActTable.addCell(generalActcell2);
                
                generalActcell.setColspan(2);

                generalActcell = new PdfPCell(new Phrase("Tea Break", smallNote));
                generalActcell.setBorder(0);
                generalActTable.addCell(generalActcell);
                
                generalActcell2 = new PdfPCell(new Phrase(teaBreakList.get(i).toString(), smallNote));
                generalActcell2.setBorder(0);
                generalActTable.addCell(generalActcell2);
                
                generalActcell.setColspan(2);

                generalActcell = new PdfPCell(new Phrase("Meeting/Coordination", smallNote));
                generalActcell.setBorder(0);
                generalActTable.addCell(generalActcell);

                generalActcell2 = new PdfPCell(new Phrase(meetingAndCoordinationList.get(i).toString(), smallNote));
                generalActcell2.setBorder(0);
                generalActTable.addCell(generalActcell2);

                generalActcell.setColspan(2);

                generalActcell = new PdfPCell(new Phrase("Total = ", bold));
                generalActcell.setBorder(0);
                generalActTable.addCell(generalActcell);

                generalActcell2 = new PdfPCell(new Phrase(genralActTotalList.get(i).toString(), smallNote));
                generalActcell2.setBorder(0);
                generalActTable.addCell(generalActcell2);

                
                generalActcell1.setBorder(Rectangle.TOP | Rectangle.RIGHT);
                generalActcell1.addElement(generalActTable);
                contenPdfPTable.addCell(generalActcell1);


                cellstat.setColspan(3);


                PdfPTable tableChk = new PdfPTable(5);

                PdfPCell cell1 = new PdfPCell(new Phrase("Regular Hours", bold));
                cell1.setBorder(0);
                tableChk.addCell(cell1);
                cell1 = new PdfPCell(new Phrase("Overtime", bold));
                cell1.setBorder(0);
                tableChk.addCell(cell1);
                cell1 = new PdfPCell(new Phrase("Hours Worked", bold));
                cell1.setBorder(0);
                tableChk.addCell(cell1);
                cell1 = new PdfPCell(new Phrase("Idle Time", bold));
                cell1.setBorder(0);
                tableChk.addCell(cell1);
                cell1 = new PdfPCell(new Phrase("Productivity", bold));
                cell1.setBorder(0);
                tableChk.addCell(cell1);

                cell1.setColspan(5);

                PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(Math.round(regularHour)), smallNote));
                cell2.setPaddingLeft(25f);
                cell2.setBorder(0);
                cellstat.setBorder(Rectangle.BOTTOM | Rectangle.LEFT);
                tableChk.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(decimalFormat.format(overTime), smallNote));
                cell2.setPaddingLeft(10f);
                cell2.setBorder(0);
                cellstat.setBorder(Rectangle.BOTTOM);
                tableChk.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(decimalFormat.format(actualProjActTime), smallNote));
                cell2.setPaddingLeft(25f);
                cell2.setBorder(0);
                cellstat.setBorder(Rectangle.BOTTOM);
                tableChk.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(decimalFormat.format(idleTime), smallNote));
                cell2.setPaddingLeft(15f);
                cell2.setBorder(0);
                cellstat.setBorder(Rectangle.BOTTOM);
                tableChk.addCell(cell2);

                cell2 = new PdfPCell(new Phrase(decimalFormat.format(productivityPercentage) + "%", smallNote));
                cell2.setPaddingLeft(15f);
                cell2.setBorder(0);
                cellstat.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
                tableChk.addCell(cell2);


                cellstat.addElement(tableChk);

                contenPdfPTable.addCell(cellstat);

                cellstat.setColspan(3);


                cellstat = new PdfPCell(new Phrase("\n\n"));
                cellstat.setBorder(0);
                contenPdfPTable.addCell(cellstat);
                cellstat = new PdfPCell(new Phrase("\n\n"));
                cellstat.setBorder(0);
                contenPdfPTable.addCell(cellstat);
                cellstat = new PdfPCell(new Phrase("\n\n"));
                cellstat.setBorder(0);

                contenPdfPTable.addCell(cellstat);

                empCount++;
            }

            PdfPCell grandToalCell = new PdfPCell(new Phrase("\n\n Total = " + empCount + " employee/s records", small));
            grandToalCell.setBorder(0);
            contenPdfPTable.addCell(grandToalCell);

            document.add(contenPdfPTable);

            document.close();


        } catch (Exception e) {
            System.out.println("Productivity Servlet Exception" + e);
        }
    }

    public void addHeader() {
        prjoectsHeader = headerClass.productivityReportHeaderStyle();
    }

    public void addFooter() {
        prjoectsFooter = headerClass.productivityReportFooterStyle();
    }

    public void addOverallHeader() {
        headerDisp = headerClass.overallProductivityReportHeaderStyle();
    }

}
