/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.projects;

import java.sql.*;
import java.util.*;
import connection.DBconnection;
/**
 *
 * @author Parameshwarant
 */
public class DelayedProjectReportByDays {
    
    DBconnection dbconnection=new DBconnection();
    
    
    private List dept_name = new ArrayList();
    private List dept_code = new ArrayList();
    private List plannerName = new ArrayList();
    private List plannerId = new ArrayList();
    
    private List projMagmtCountsToday = new ArrayList();
    private List projMagmtCountsDelay1 = new ArrayList();
    private List projMagmtCountsDelay2 = new ArrayList();
    private List projMagmtCountsDelay3 = new ArrayList();
    private List projMagmtCountsDelay4 = new ArrayList();
    private List projMagmtCountsDelay5 = new ArrayList();

    private List chapterCountsToday = new ArrayList();
    private List chapterCountsDelay1 = new ArrayList();
    private List chapterCountsDelay2 = new ArrayList();
    private List chapterCountsDelay3 = new ArrayList();
    private List chapterCountsDelay4 = new ArrayList();
    private List chapterCountsDelay5 = new ArrayList();

    private List departmentName = new ArrayList();
    private List departmentCode = new ArrayList();
    private List mss_counts = new ArrayList();
    private List chapterHoldCounts = new ArrayList();
    
    private List stageNamesToday = new ArrayList();
    private List stageNamesDelay1 = new ArrayList();
    private List stageNamesDelay2 = new ArrayList();
    private List stageNamesDelay3 = new ArrayList();
    private List stageNamesDelay4 = new ArrayList();
    private List stageNamesDelay5 = new ArrayList();

    private List cpdstageNamesToday = new ArrayList();
    private List cpdstageNamesDelay1 = new ArrayList();
    private List cpdstageNamesDelay2 = new ArrayList();
    private List cpdstageNamesDelay3 = new ArrayList();
    private List cpdstageNamesDelay4 = new ArrayList();
    private List cpdstageNamesDelay5 = new ArrayList();
    private List cpdstageNames = new ArrayList();
    private List projectsToday = new ArrayList();
    private List projectsDelay1 = new ArrayList();
    private List projectsDelay2 = new ArrayList();
    private List projectsDelay3 = new ArrayList();
    private List projectsDelay4 = new ArrayList();
    private List projectsDelay5 = new ArrayList();

    
    private List chaptersDeptToday = new ArrayList();
    private List chaptersDeptDelay1 = new ArrayList();
    private List chaptersDeptDelay2 = new ArrayList();
    private List chaptersDeptDelay3 = new ArrayList();
    private List chaptersDeptDelay4 = new ArrayList();
    private List chaptersDeptDelay5 = new ArrayList();
    private List plannerMssCount = new ArrayList();
    private List plannerHoldChapter = new ArrayList();
    private List deptCode = new ArrayList();
    private List deptName = new ArrayList();
    private List projectsDelayCounts = new ArrayList();



    public List getProjHoldCounts() {
        return chapterHoldCounts;
    }
    public List getProjMssCounts() {
        return mss_counts;
    }

    public List getProjMagmtCountsToday() {
        return projMagmtCountsToday;
    }

    
    public List getProjMagmtCountsDelay1() {
        return projMagmtCountsDelay1;
    }
    public List getProjMagmtCountsDelay2() {
        return projMagmtCountsDelay2;
    }
    public List getProjMagmtCountsDelay3() {
        return projMagmtCountsDelay3;
    }
    public List getProjMagmtCountsDelay4() {
        return projMagmtCountsDelay4;
    }
    public List getProjMagmtCountsDelay5() {
        return projMagmtCountsDelay5;
    }

    public List getChapterCountsToday() {
        return chapterCountsToday;
    }
    public List getChapterCountsDelay1() {
        return chapterCountsDelay1;
    }
    public List getChapterCountsDelay2() {
        return chapterCountsDelay2;
    }
    public List getChapterCountsDelay3() {
        return chapterCountsDelay3;
    }
    public List getChapterCountsDelay4() {
        return chapterCountsDelay4;
    }
    public List getChapterCountsDelay5() {
        return chapterCountsDelay5;
    }

    public List getStageCountsToday() {
        return stageNamesToday;
    }
    public List getStageCountsDelay1() {
        return stageNamesDelay1;
    }
    public List getStageCountsDelay2() {
        return stageNamesDelay2;
    }
    public List getStageCountsDelay3() {
        return stageNamesDelay3;
    }
    public List getStageCountsDelay4() {
        return stageNamesDelay4;
    }
    public List getStageCountsDelay5() {
        return stageNamesDelay5;
    }

    public List getcpdStageCountsToday() {
        return cpdstageNamesToday;
    }
    public List getcpdStageCountsDelay1() {
        return cpdstageNamesDelay1;
    }
    public List getcpdStageCountsDelay2() {
        return cpdstageNamesDelay2;
    }
    public List getcpdStageCountsDelay3() {
        return cpdstageNamesDelay3;
    }
    public List getcpdStageCountsDelay4() {
        return cpdstageNamesDelay4;
    }
    public List getcpdStageCountsDelay5() {
        return cpdstageNamesDelay5;
    }
    public List getcpdStageNames(){
    return cpdstageNames;
    }

    public List getChapCountsToday() {
        return chaptersDeptToday;
    }
    public List getChapCountsDelay1() {
        return chaptersDeptDelay1;
    }
    public List getChapCountsDelay2() {
        return chaptersDeptDelay2;
    }
    public List getChapCountsDelay3() {
        return chaptersDeptDelay3;
    }
    public List getChapCountsDelay4() {
        return chaptersDeptDelay4;
    }
    public List getChapCountsDelay5() {
        return chaptersDeptDelay5;
    }

    public List getProjectCountToday() {
        return projectsToday;
    }
    public List getProjectCountDelay1() {
        return projectsDelay1;
    }
    public List getProjectCountDelay2() {
        return projectsDelay2;
    }
    public List getProjectCountDelay3() {
        return projectsDelay3;
    }
    public List getProjectCountDelay4() {
        return projectsDelay4;
    }
    public List getProjectCountDelay5() {
        return projectsDelay5;
    }

    public List getDeptCode() {
        return departmentCode;
    }

    public List getDeptName() {
        return departmentName;
    }

    public List getDept_Code() {
        return dept_code;
    }
    
    public List getDept_Name() {
        return dept_name;
    }

    public List getPlannerId() {
        return plannerId;
    }

    public List getPlannerName() {
        return plannerName;
    }

    public List getPlannerHoldChapter() {
        return plannerHoldChapter;
    }

    public List getPlannerMssCount() {
        return plannerMssCount;
    }


    public void getDelayedProjectListByChapterDuedate()
    {

        String generalQuery = "";
        String deptName = "";
        String deptCodes= "";
        String stageTableStart = "<table>";
        String stageTableEnd = "</table>";
        String chapStageName = "";

        String chapStageNameToday = "";
        String chapStageNameDelay1 = "";
        String chapStageNameDelay2 = "";
        String chapStageNameDelay3 = "";
        String chapStageNameDelay4 = "";
        String chapStageNameDelay5 = "";

        int chapTodayCount = 0;
        int chapDelay1Count = 0;
        int chapDelay2Count = 0;
        int chapDelay3Count = 0;
        int chapDelay4Count = 0;
        int chapDelay5Count = 0;

        List chapterCountToday= new ArrayList();
        List chapterCountDelay1= new ArrayList();
        List chapterCountDelay2= new ArrayList();
        List chapterCountDelay3= new ArrayList();
        List chapterCountDelay4= new ArrayList();
        List chapterCountDelay5= new ArrayList();
        List cpdchapterCountToday= new ArrayList();
        List cpdchapterCountDelay1= new ArrayList();
        List cpdchapterCountDelay2= new ArrayList();
        List cpdchapterCountDelay3= new ArrayList();
        List cpdchapterCountDelay4= new ArrayList();
        List cpdchapterCountDelay5= new ArrayList();
        List deptNameChk = new ArrayList();
        List deptCodeChk = new ArrayList();
        List stageName = new ArrayList();
        List cpdstageName = new ArrayList();
        List days_diff = new ArrayList();
        List cpddays_diff = new ArrayList();

        
        
        try {

            Connection con = dbconnection.getSampleProperty();
            Statement stToGetDelayedProjList = con.createStatement();

            
            String queryToGetAllChapterCount = "SELECT b.dept_code,d.department,sum(CASE WHEN a.chapter_process=1 and DATEDIFF(CURRENT_DATE,a.due_date)>0 and a.chapter_no not in ('Complete Book','Project Management','Full service') THEN a.mss_count ELSE 0 END) AS ChapterMssCount, "
                    + " SUM(CASE WHEN a.chapter_process=0 and DATEDIFF(CURRENT_DATE,a.due_date)>0 THEN 1 ELSE 0 END) AS HoldChapCount, SUM(CASE WHEN a.chapter_process=1 and DATEDIFF(CURRENT_DATE,a.due_date)=0 and a.chapter_no not in ('Complete Book','Project Management','Full service') THEN 1 ELSE 0 END) AS PrsChapCount0, "
                    + " SUM(CASE WHEN a.chapter_process=1 and DATEDIFF(CURRENT_DATE,a.due_date)=1 and a.chapter_no not in ('Complete Book','Project Management','Full service') THEN 1 ELSE 0 END) AS PrsChapCount1, "
                    + " SUM(CASE WHEN a.chapter_process=1 and DATEDIFF(CURRENT_DATE,a.due_date)=2 and a.chapter_no not in ('Complete Book','Project Management','Full service') THEN 1 ELSE 0 END) AS PrsChapCount2, "
                    + " SUM(CASE WHEN a.chapter_process=1 AND DATEDIFF(CURRENT_DATE,a.due_date)=3 and a.chapter_no not in ('Complete Book','Project Management','Full service') THEN 1 ELSE 0 END) AS PrsChapCount3, "
                    + " SUM(CASE WHEN a.chapter_process=1 AND DATEDIFF(CURRENT_DATE,a.due_date)=4 and a.chapter_no not in ('Complete Book','Project Management','Full service') THEN 1 ELSE 0 END) AS PrsChapCount4, "
                    + " SUM(CASE WHEN a.chapter_process=1 AND DATEDIFF(CURRENT_DATE,a.due_date)>=5 and a.chapter_no not in ('Complete Book','Project Management','Full service') THEN 1 ELSE 0 END) AS PrsChapCount5, "
                    + " SUM(CASE WHEN a.chapter_process=1 and DATEDIFF(CURRENT_DATE,a.due_date)=0 and a.chapter_no in ('Complete Book','Project Management','Full service') THEN 1 ELSE 0 END) AS OtherPrsChapCnt0, "
                    + " SUM(CASE WHEN a.chapter_process=1 and DATEDIFF(CURRENT_DATE,a.due_date)=1 AND a.chapter_no IN ('Complete Book','Project Management','Full service') THEN 1 ELSE 0 END) AS OtherPrsChapCnt1, "
                    + " SUM(CASE WHEN a.chapter_process=1 AND DATEDIFF(CURRENT_DATE,a.due_date)=2 AND a.chapter_no IN ('Complete Book','Project Management','Full service') THEN 1 ELSE 0 END) AS OtherPrsChapCnt2, "
                    + " SUM(CASE WHEN a.chapter_process=1 and DATEDIFF(CURRENT_DATE,a.due_date)=3 AND a.chapter_no IN ('Complete Book','Project Management','Full service') THEN 1 ELSE 0 END) AS OtherPrsChapCnt3, "
                    + " SUM(CASE WHEN a.chapter_process=1 AND DATEDIFF(CURRENT_DATE,a.due_date)=4 AND a.chapter_no IN ('Complete Book','Project Management','Full service') THEN 1 ELSE 0 END) AS OtherPrsChapCnt4, "
                    + " SUM(CASE WHEN a.chapter_process=1 AND DATEDIFF(CURRENT_DATE,a.due_date)>=5 AND a.chapter_no IN ('Complete Book','Project Management','Full service') THEN 1 ELSE 0 END) AS OtherPrsChapCnt5 "
                    + " FROM chapter a, projects b, project_stage c,contacts g,contacts divi, department d WHERE a.ship_date IS NULL AND c.stage_code=a.stage AND a.proj_id=b.proj_id  AND b.client_name=g.contact_id AND "
                    + " a.due_date IS NOT NULL AND b.division_id=divi.contact_id AND a.stage NOT IN ('INTART') AND ((b.project_status <> '24' and b.dept_code='CHN-WK') OR (b.project_status <> '2' and b.dept_code !='CHN-WK')) AND a.chapter_deleted='0' AND b.dept_code=d.dept_code "
                    + " GROUP BY b.dept_code ORDER BY b.dept_code ";
                   

            ResultSet rsToGetDoneOnFutureProject = stToGetDelayedProjList.executeQuery(queryToGetAllChapterCount);
            
            while(rsToGetDoneOnFutureProject.next()) {
                departmentCode.add(rsToGetDoneOnFutureProject.getString(1));
                departmentName.add(rsToGetDoneOnFutureProject.getString(2));
                mss_counts.add(rsToGetDoneOnFutureProject.getString(3));
                chapterHoldCounts.add(rsToGetDoneOnFutureProject.getString(4));
                chapterCountsToday.add(rsToGetDoneOnFutureProject.getString(5));
                chapterCountsDelay1.add(rsToGetDoneOnFutureProject.getString(6));
                chapterCountsDelay2.add(rsToGetDoneOnFutureProject.getString(7));
                chapterCountsDelay3.add(rsToGetDoneOnFutureProject.getString(8));
                chapterCountsDelay4.add(rsToGetDoneOnFutureProject.getString(9));
                chapterCountsDelay5.add(rsToGetDoneOnFutureProject.getString(10));
                projMagmtCountsToday.add(rsToGetDoneOnFutureProject.getString(11));
                projMagmtCountsDelay1.add(rsToGetDoneOnFutureProject.getString(12));
                projMagmtCountsDelay2.add(rsToGetDoneOnFutureProject.getString(13));
                projMagmtCountsDelay3.add(rsToGetDoneOnFutureProject.getString(14));
                projMagmtCountsDelay4.add(rsToGetDoneOnFutureProject.getString(15));
                projMagmtCountsDelay5.add(rsToGetDoneOnFutureProject.getString(16));
            }


            String queryForCpd=("SELECT b.dept_code,d.department,(DATEDIFF(CURRENT_DATE,a.due_date)) AS DayDelay,c.stage, "+
                                "SUM(CASE WHEN DATEDIFF(CURRENT_DATE,a.due_date)=0 THEN 1 ELSE 0 END) AS count0, SUM(CASE WHEN DATEDIFF(CURRENT_DATE,a.due_date)=1 THEN 1 ELSE 0 END) AS count1, "+
                                "SUM(CASE WHEN DATEDIFF(CURRENT_DATE,a.due_date)=2 THEN 1 ELSE 0 END) AS count2, SUM(CASE WHEN DATEDIFF(CURRENT_DATE,a.due_date)=3 THEN 1 ELSE 0 END) AS count3, "+
                                "SUM(CASE WHEN DATEDIFF(CURRENT_DATE,a.due_date)=4 THEN 1 ELSE 0 END) AS count4, SUM(CASE WHEN DATEDIFF(CURRENT_DATE,a.due_date)>=5 THEN 1 ELSE 0 END) AS count5 "+
                                "FROM chapter a, projects b, project_stage c,contacts g,contacts divi, department d "+
                                "WHERE a.ship_date IS NULL AND c.stage_code=a.stage AND a.proj_id=b.proj_id AND b.client_name=g.contact_id  AND a.due_date IS NOT NULL "+
                                "AND b.division_id=divi.contact_id AND a.stage NOT IN ('INTART') AND "+
                                "((b.project_status <> '24' AND b.dept_code='CHN-WK') OR (b.project_status <> '2' AND b.dept_code !='CHN-WK')) "+
                                "AND (b.dept_code IN('CPD','CHN-CEN','CHN-MGH','CHN-PEA','CHN-DES','OUP')) AND a.chapter_deleted='0' AND b.dept_code=d.dept_code "+
                                "AND a.chapter_no NOT IN ('Complete Book','Project Management','Full service') AND a.chapter_process='1' AND DATEDIFF(CURRENT_DATE,a.due_date)>=0 "+
                                "GROUP BY a.stage ORDER BY b.dept_code,a.stage");
            
             ResultSet rsToGetDaysDiffcpd = stToGetDelayedProjList.executeQuery(queryForCpd);

            if (!rsToGetDaysDiffcpd.wasNull()) {
                while (rsToGetDaysDiffcpd.next()) {
                    cpddays_diff.add(rsToGetDaysDiffcpd.getString(3));
                    cpdstageNames.add(rsToGetDaysDiffcpd.getString(4));
                    cpdstageNamesToday.add(rsToGetDaysDiffcpd.getString(5));
                    cpdstageNamesDelay1.add(rsToGetDaysDiffcpd.getString(6));
                    cpdstageNamesDelay2.add(rsToGetDaysDiffcpd.getString(7));
                    cpdstageNamesDelay3.add(rsToGetDaysDiffcpd.getString(8));
                    cpdstageNamesDelay4.add(rsToGetDaysDiffcpd.getString(9));
                    cpdstageNamesDelay5.add(rsToGetDaysDiffcpd.getString(10));

                }
            }
             
             generalQuery = " SELECT b.dept_code,d.department,(DATEDIFF(CURRENT_DATE,a.due_date)) AS DayDelay,c.stage, "
                    + " SUM(CASE WHEN DATEDIFF(CURRENT_DATE,a.due_date)=0 THEN 1 ELSE 0 END) AS count0, sum(CASE WHEN DATEDIFF(CURRENT_DATE,a.due_date)=1 THEN 1 ELSE 0 END) AS count1, "
                    + " SUM(CASE WHEN DATEDIFF(CURRENT_DATE,a.due_date)=2 THEN 1 ELSE 0 END) AS count2, sum(CASE WHEN DATEDIFF(CURRENT_DATE,a.due_date)=3 THEN 1 ELSE 0 END) AS count3, "
                    + " SUM(CASE WHEN DATEDIFF(CURRENT_DATE,a.due_date)=4 THEN 1 ELSE 0 END) AS count4, sum(CASE WHEN DATEDIFF(CURRENT_DATE,a.due_date)>=5 THEN 1 ELSE 0 END) AS count5 "
                    + " FROM chapter a, projects b, project_stage c,contacts g,contacts divi, department d "
                    + " WHERE a.ship_date IS NULL AND c.stage_code=a.stage AND a.proj_id=b.proj_id AND b.client_name=g.contact_id  AND a.due_date IS NOT NULL "
                    + " AND b.division_id=divi.contact_id AND a.stage NOT IN ('INTART') AND ((b.project_status <> '24' and b.dept_code='CHN-WK') OR (b.project_status <> '2' and b.dept_code !='CHN-WK')) AND  b.dept_code NOT IN('CPD','CHN-CEN','CHN-MGH','CHN-PEA','OUP','CHN-DES') AND a.chapter_deleted='0' AND b.dept_code=d.dept_code "
                    + " and a.chapter_no not in ('Complete Book','Project Management','Full service') and a.chapter_process='1' and DATEDIFF(CURRENT_DATE,a.due_date)>=0 "
                    + " GROUP BY b.dept_code,a.stage ORDER BY b.dept_code,a.stage ";



            ResultSet rsToGetDaysDiff = stToGetDelayedProjList.executeQuery(generalQuery);

            if (!rsToGetDaysDiff.wasNull()) {
                while (rsToGetDaysDiff.next()) {
                    deptCodeChk.add(rsToGetDaysDiff.getString(1));
                    deptNameChk.add(rsToGetDaysDiff.getString(2));
                    days_diff.add(rsToGetDaysDiff.getString(3));
                    stageName.add(rsToGetDaysDiff.getString(4));
                    chapterCountToday.add(rsToGetDaysDiff.getString(5));
                    chapterCountDelay1.add(rsToGetDaysDiff.getString(6));
                    chapterCountDelay2.add(rsToGetDaysDiff.getString(7));
                    chapterCountDelay3.add(rsToGetDaysDiff.getString(8));
                    chapterCountDelay4.add(rsToGetDaysDiff.getString(9));
                    chapterCountDelay5.add(rsToGetDaysDiff.getString(10));

                }
            }
            

             for (int count = 0; count < deptCodeChk.size(); count++) {
                deptCodes = deptCodeChk.get(count).toString();
                deptName = deptNameChk.get(count).toString();
                chapStageName = stageName.get(count).toString();
                if (!dept_code.contains(deptCodes)) {
                    dept_code.add(deptCodes);
                    dept_name.add(deptName);
                    if (count != 0) {

                        stageNamesToday.add(stageTableStart + chapStageNameToday + stageTableEnd);
                        stageNamesDelay1.add(stageTableStart + chapStageNameDelay1 + stageTableEnd);
                        stageNamesDelay2.add(stageTableStart + chapStageNameDelay2 + stageTableEnd);
                        stageNamesDelay3.add(stageTableStart + chapStageNameDelay3 + stageTableEnd);
                        stageNamesDelay4.add(stageTableStart + chapStageNameDelay4 + stageTableEnd);
                        stageNamesDelay5.add(stageTableStart + chapStageNameDelay5 + stageTableEnd);
                    }


                    chapStageNameToday = "";
                    chapStageNameDelay1 = "";
                    chapStageNameDelay2 = "";
                    chapStageNameDelay3 = "";
                    chapStageNameDelay4 = "";
                    chapStageNameDelay5 = "";
                }
                if (dept_code.contains(deptCodes)) {

                    chapTodayCount = Integer.parseInt(chapterCountToday.get(count).toString());
                    chapDelay1Count = Integer.parseInt(chapterCountDelay1.get(count).toString());
                    chapDelay2Count = Integer.parseInt(chapterCountDelay2.get(count).toString());
                    chapDelay3Count = Integer.parseInt(chapterCountDelay3.get(count).toString());
                    chapDelay4Count = Integer.parseInt(chapterCountDelay4.get(count).toString());
                    chapDelay5Count = Integer.parseInt(chapterCountDelay5.get(count).toString());

                    if (chapTodayCount != 0) {

                        chapStageNameToday += " <tr><td> " + chapStageName + " </td> <td> " + chapTodayCount + "</td></tr>";
                    }
                    if (chapDelay1Count != 0) {

                        chapStageNameDelay1 += " <tr><td> " + chapStageName + " </td> <td> " + chapDelay1Count + "</td></tr>";
                    }
                    if (chapDelay2Count != 0) {

                        chapStageNameDelay2 += " <tr><td> " + chapStageName + " </td> <td> " + chapDelay2Count + "</td></tr>";
                    }
                    if (chapDelay3Count != 0) {

                        chapStageNameDelay3 += " <tr><td> " + chapStageName + " </td> <td> " + chapDelay3Count + "</td></tr>";
                    }
                    if (chapDelay4Count != 0) {

                        chapStageNameDelay4 += " <tr><td> " + chapStageName + " </td> <td> " + chapDelay4Count + "</td><br>";
                    }
                    if (chapDelay5Count != 0) {

                        chapStageNameDelay5 += " <tr><td> " + chapStageName + " </td> <td> " + chapDelay5Count + "</td></tr>";
                    }
                }
            }

            stageNamesToday.add(stageTableStart + chapStageNameToday + stageTableEnd);
            stageNamesDelay1.add(stageTableStart + chapStageNameDelay1 + stageTableEnd);
            stageNamesDelay2.add(stageTableStart + chapStageNameDelay2 + stageTableEnd);
            stageNamesDelay3.add(stageTableStart + chapStageNameDelay3 + stageTableEnd);
            stageNamesDelay4.add(stageTableStart + chapStageNameDelay4 + stageTableEnd);
            stageNamesDelay5.add(stageTableStart + chapStageNameDelay5 + stageTableEnd);

            rsToGetDoneOnFutureProject.close();
            stToGetDelayedProjList.close();
            con.close();
            } catch (SQLException sqle) {
                System.out.println("Class:DelayedProjectReportByDays, Function:getDelayedProjectListByChapterDuedate"+sqle);
            } catch (NullPointerException npe) {
                System.out.println("Class:DelayedProjectReportByDays, Function:getDelayedProjectListByChapterDuedate"+npe);
            }
    }

    
    public void getDelayedProjectListByChapterMailKillShipDate() {
        String generalQuery = "";
        String projectTableStart = "<table id=\"projectTable\" border=\"1\">";
        String projectTableRowOpen = "<tr><td><label style=\"color: black;\">";
        String projectTableRowClose = "</label></td></tr>";
        String projectTableDataOpenclose = "</label></td><td><label style=\"color: black;\">";
        String projectTableEnd = "</table>";
        String chapProjName = "";
        String chapter = "";
        String projectToday = "";
        String projectDelay1 = "";
        String projectDelay2 = "";
        String projectDelay3 = "";
        String projectDelay4 = "";
        String projectDelay5 = "";

        int projectTodayCount = 0;
        int projectDelay1Count = 0;
        int projectDelay2Count = 0;
        int projectDelay3Count = 0;
        int projectDelay4Count = 0;
        int projectDelay5Count = 0;

        int projectChapTodayCount = 0;
        int projectChapDelay1Count = 0;
        int projectChapDelay2Count = 0;
        int projectChapDelay3Count = 0;
        int projectChapDelay4Count = 0;
        int projectChapDelay5Count = 0;
        int plannerHoldChpaterCount = 0;
        int plannerHoldChpaterCountChk = 0;
        int delayChpaterCountChk = 0;
        int plannerChpaterMssCount = 0;

        List projectCountTodayChk = new ArrayList();
        List projectCountDelay1Chk = new ArrayList();
        List projectCountDelay2Chk = new ArrayList();
        List projectCountDelay3Chk = new ArrayList();
        List projectCountDelay4Chk = new ArrayList();
        List projectCountDelay5Chk = new ArrayList();
        List projectNames = new ArrayList();
        List plannerHoldChapterCountChk = new ArrayList();
        List plannerChapterMssCountChk = new ArrayList();
        List projectNamesChk = new ArrayList();
        List projectChapterDelayCount = new ArrayList();
        List plannerNameChk = new ArrayList();
        List plannerIdChk = new ArrayList();



        try {

            Connection con = dbconnection.getSampleProperty();
            Statement stToGetDelayedProjList = con.createStatement();
/*
            String getDept = "SELECT IFNULL(u.emp_id,''), IFNULL(u.emp_name,'') "
                    + " FROM chapter_plan cp, chapter c,projects p,project_stage cc,contacts g,contacts divi, department d, user u "
                    + " WHERE cp.milestone_id=74 AND cc.stage_code=c.stage AND p.client_name=g.contact_id AND p.division_id=divi.contact_id AND p.dept_code=d.dept_code "
                    + " AND c.chapter_id=cp.chapter_id AND c.chapter_deleted=0 AND c.batch_end_date IS NULL AND c.chapter_process=1 and p.project_status not in('2','20','21','23') AND p.planner=u.emp_id AND u.status='1' "
                    + " AND cp.end_date IS NOT NULL AND c.proj_id=p.proj_id GROUP BY p.planner ORDER BY u.emp_name,p.proj_name ";
*/
            String getDept = "SELECT u.emp_id, u.emp_name FROM projects p, USER u WHERE p.planner=u.emp_id AND p.project_status NOT IN('2','20','21','23','24') and u.status='1' GROUP BY u.emp_id ORDER BY u.emp_name";
            ResultSet rsToGetDaysDiff = stToGetDelayedProjList.executeQuery(getDept);

            if (!rsToGetDaysDiff.wasNull()) {
                while (rsToGetDaysDiff.next()) {
                    plannerId.add(rsToGetDaysDiff.getString(1));
                    plannerName.add(rsToGetDaysDiff.getString(2));
                }
            }

            // Dummy id to check the projects which are all not yet assigned with planner
            plannerId.add("0");
            plannerName.add("Unassigned");

            for (int dept = 0; dept < plannerId.size(); dept++) {

                generalQuery = "SELECT p.proj_name,DATEDIFF(CURRENT_DATE,DATE(MAX(end_date))) AS DaysDelay, "
                        + " CASE WHEN c.chapter_process=1 AND DATEDIFF(CURRENT_DATE,DATE(MAX(end_date)))=0 THEN 1 ELSE 0 END AS TodayCount, CASE WHEN c.chapter_process=1 AND DATEDIFF(CURRENT_DATE,DATE(MAX(end_date)))=1 THEN 1 ELSE 0 END AS OnedayCount, "
                        + " CASE WHEN c.chapter_process=1 AND DATEDIFF(CURRENT_DATE,DATE(MAX(end_date)))=2 THEN 1 ELSE 0 END AS TwodayDelayCount, CASE WHEN c.chapter_process=1 AND DATEDIFF(CURRENT_DATE,DATE(MAX(end_date)))=3 THEN 1 ELSE 0 END AS ThreedayDelayCount, "
                        + " CASE WHEN c.chapter_process=1 AND DATEDIFF(CURRENT_DATE,DATE(MAX(end_date)))=4 THEN 1 ELSE 0 END AS FourdayDelayCount, CASE WHEN c.chapter_process=1 AND DATEDIFF(CURRENT_DATE,DATE(MAX(end_date)))>=5 THEN 1 ELSE 0 END AS FiveAndAbovedayDelayCount,d.dept_code,d.department,"
                        + " CASE WHEN c.chapter_process=0 AND DATEDIFF(CURRENT_DATE,DATE(MAX(end_date)))>0 THEN 1 ELSE 0 END AS HoldChapterCount,"
                        + " CASE WHEN c.chapter_process=1 AND DATEDIFF(CURRENT_DATE,DATE(MAX(end_date)))>0 THEN c.mss_count ELSE 0 END AS ChapterMSSCount"
                        + " FROM chapter_plan cp, chapter c,projects p,project_stage cc,contacts g,contacts divi, department d "
                        + " WHERE cp.milestone_id='74' AND cc.stage_code=c.stage AND p.client_name=g.contact_id AND p.division_id=divi.contact_id AND p.dept_code=d.dept_code "
                        + " AND c.chapter_id=cp.chapter_id AND c.chapter_deleted='0' AND c.batch_end_date IS NULL AND cp.end_date IS NOT NULL and p.project_status not in('2','20','21','23',24) ";
                        // + " AND p.dept_code='" + department_code.get(dept).toString() + "' AND c.proj_id=p.proj_id GROUP BY c.chapter_id  ORDER BY p.dept_code,p.proj_name ";
                if(plannerId.get(dept).toString().equals("0")) {
                    generalQuery += " AND p.planner IS NULL ";
                } else {
                    generalQuery += " AND p.planner='" + plannerId.get(dept).toString() + "' ";
                }

                generalQuery += " AND c.proj_id=p.proj_id GROUP BY c.chapter_id  ORDER BY p.dept_code,p.proj_name ";


                plannerIdChk.clear();
                plannerNameChk.clear();
                projectToday = "";
                projectDelay1 = "";
                projectDelay2 = "";
                projectDelay3 = "";
                projectDelay4 = "";
                projectDelay5 = "";
                projectTodayCount = 0;
                projectDelay1Count = 0;
                projectDelay2Count = 0;
                projectDelay3Count = 0;
                projectDelay4Count = 0;
                projectDelay5Count = 0;
                projectChapTodayCount = 0;
                projectChapDelay1Count = 0;
                projectChapDelay2Count = 0;
                projectChapDelay3Count = 0;
                projectChapDelay4Count = 0;
                projectChapDelay5Count = 0;
                plannerHoldChpaterCount = 0;
                plannerChpaterMssCount = 0;
                projectCountTodayChk.clear();
                projectCountDelay1Chk.clear();
                projectCountDelay2Chk.clear();
                projectCountDelay3Chk.clear();
                projectCountDelay4Chk.clear();
                projectCountDelay5Chk.clear();
                plannerHoldChapterCountChk.clear();
                plannerChapterMssCountChk.clear();
                projectNames.clear();
                projectNamesChk.clear();
                projectChapterDelayCount.clear();

                rsToGetDaysDiff = stToGetDelayedProjList.executeQuery(generalQuery);

                if (!rsToGetDaysDiff.wasNull()) {
                    while (rsToGetDaysDiff.next()) {
                        projectNames.add(rsToGetDaysDiff.getString(1));
                        projectChapterDelayCount.add(rsToGetDaysDiff.getString(2));
                        projectCountTodayChk.add(rsToGetDaysDiff.getString(3));
                        projectCountDelay1Chk.add(rsToGetDaysDiff.getString(4));
                        projectCountDelay2Chk.add(rsToGetDaysDiff.getString(5));
                        projectCountDelay3Chk.add(rsToGetDaysDiff.getString(6));
                        projectCountDelay4Chk.add(rsToGetDaysDiff.getString(7));
                        projectCountDelay5Chk.add(rsToGetDaysDiff.getString(8));
                        plannerIdChk.add(rsToGetDaysDiff.getString(9));
                        plannerNameChk.add(rsToGetDaysDiff.getString(10));
                        plannerHoldChapterCountChk.add(rsToGetDaysDiff.getString(11));
                        plannerChapterMssCountChk.add(rsToGetDaysDiff.getString(12));
                    }
                }
                for (int count = 0; count < projectNames.size(); count++) {
                    chapter = chapProjName;
                    chapProjName = projectNames.get(count).toString();
                    if (!projectNamesChk.contains(chapProjName)) {

                        projectNamesChk.add(chapProjName);
                        if (count != 0) {
                            if (projectTodayCount != 0) {
                                projectToday += projectTableRowOpen + chapter + projectTableDataOpenclose + projectTodayCount + projectTableRowClose;
                            }
                            if (projectDelay1Count != 0) {
                                projectDelay1 += projectTableRowOpen + chapter + projectTableDataOpenclose + projectDelay1Count + projectTableRowClose;
                            }
                            if (projectDelay2Count != 0) {
                                projectDelay2 += projectTableRowOpen + chapter + projectTableDataOpenclose + projectDelay2Count + projectTableRowClose;
                            }
                            if (projectDelay3Count != 0) {
                                projectDelay3 += projectTableRowOpen + chapter + projectTableDataOpenclose + projectDelay3Count + projectTableRowClose;
                            }
                            if (projectDelay4Count != 0) {
                                projectDelay4 += projectTableRowOpen + chapter + projectTableDataOpenclose + projectDelay4Count + projectTableRowClose;
                            }
                            if (projectDelay5Count != 0) {
                                projectDelay5 += projectTableRowOpen + chapter + projectTableDataOpenclose + projectDelay5Count + projectTableRowClose;
                            }
                        }

                        projectChapTodayCount += projectTodayCount;
                        projectChapDelay1Count += projectDelay1Count;
                        projectChapDelay2Count += projectDelay2Count;
                        projectChapDelay3Count += projectDelay3Count;
                        projectChapDelay4Count += projectDelay4Count;
                        projectChapDelay5Count += projectDelay5Count;

                        projectTodayCount = 0;
                        projectDelay1Count = 0;
                        projectDelay2Count = 0;
                        projectDelay3Count = 0;
                        projectDelay4Count = 0;
                        projectDelay5Count = 0;
                    }
                    if (projectNamesChk.contains(chapProjName)) {
                        plannerHoldChpaterCountChk = Integer.parseInt(plannerHoldChapterCountChk.get(count).toString());
                        plannerHoldChpaterCount += plannerHoldChpaterCountChk;
                        if(plannerHoldChpaterCountChk==0) {
                            projectTodayCount += Integer.parseInt(projectCountTodayChk.get(count).toString());
                            projectDelay1Count += Integer.parseInt(projectCountDelay1Chk.get(count).toString());
                            projectDelay2Count += Integer.parseInt(projectCountDelay2Chk.get(count).toString());
                            projectDelay3Count += Integer.parseInt(projectCountDelay3Chk.get(count).toString());
                            projectDelay4Count += Integer.parseInt(projectCountDelay4Chk.get(count).toString());
                            projectDelay5Count += Integer.parseInt(projectCountDelay5Chk.get(count).toString());
                            plannerChpaterMssCount += Integer.parseInt(plannerChapterMssCountChk.get(count).toString());
                        }

                    }
                }

                if (projectTodayCount != 0) {
                    projectToday += projectTableRowOpen + chapProjName + projectTableDataOpenclose + projectTodayCount + projectTableRowClose;

                }
                if (projectDelay1Count != 0) {
                    projectDelay1 += projectTableRowOpen + chapProjName + projectTableDataOpenclose + projectDelay1Count + projectTableRowClose;

                }
                if (projectDelay2Count != 0) {
                    projectDelay2 += projectTableRowOpen + chapProjName + projectTableDataOpenclose + projectDelay2Count + projectTableRowClose;

                }
                if (projectDelay3Count != 0) {
                    projectDelay3 += projectTableRowOpen + chapProjName + projectTableDataOpenclose + projectDelay3Count + projectTableRowClose;

                }
                if (projectDelay4Count != 0) {
                    projectDelay4 += projectTableRowOpen + chapProjName + projectTableDataOpenclose + projectDelay4Count + projectTableRowClose;

                }
                if (projectDelay5Count != 0) {
                    projectDelay5 += projectTableRowOpen + chapProjName + projectTableDataOpenclose + projectDelay5Count + projectTableRowClose;

                }
                projectChapTodayCount += projectTodayCount;
                projectChapDelay1Count += projectDelay1Count;
                projectChapDelay2Count += projectDelay2Count;
                projectChapDelay3Count += projectDelay3Count;
                projectChapDelay4Count += projectDelay4Count;
                projectChapDelay5Count += projectDelay5Count;
                projectsToday.add(projectTableStart + projectToday + projectTableEnd);
                projectsDelay1.add(projectTableStart + projectDelay1 + projectTableEnd);
                projectsDelay2.add(projectTableStart + projectDelay2 + projectTableEnd);
                projectsDelay3.add(projectTableStart + projectDelay3 + projectTableEnd);
                projectsDelay4.add(projectTableStart + projectDelay4 + projectTableEnd);
                projectsDelay5.add(projectTableStart + projectDelay5 + projectTableEnd);

                chaptersDeptToday.add(projectChapTodayCount);
                chaptersDeptDelay1.add(projectChapDelay1Count);
                chaptersDeptDelay2.add(projectChapDelay2Count);
                chaptersDeptDelay3.add(projectChapDelay3Count);
                chaptersDeptDelay4.add(projectChapDelay4Count);
                chaptersDeptDelay5.add(projectChapDelay5Count);
                plannerHoldChapter.add(plannerHoldChpaterCount);
                plannerMssCount.add(plannerChpaterMssCount);

            }
            rsToGetDaysDiff.close();
            stToGetDelayedProjList.close();
            con.close();

        } catch (SQLException sqle) {
            System.out.println("Class:DelayedProjectReportByDays, Function:getDelayedProjectListByChapterMailKillShipdate" + sqle);
        } catch (NullPointerException npe) {
            System.out.println("Class:DelayedProjectReportByDays, Function:getDelayedProjectListByChapterMailKillShipdate" + npe);
        }

    }
}
