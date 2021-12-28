/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters.projects;

import connection.DBconnection;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.util.ArrayList;

/**
 *
 * @author rajac
 */
public class DashBoardViewDAO implements Serializable {

    DBconnection dbconnection = new DBconnection();
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private String projList = "";
    private String chapList = "";
    private String batchDetList = "";
    private String whereCondition = "";

    pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();

    public List getProjList(DashBoardViewVO ProjDetLlst) {

        DashBoardViewVO VODash = null;

        List packProjList = new ArrayList();

        String deptCode = "";
        String desigCode = "";
        String toDayDate = "";
        String toDate = "";
        String frmMenuFlag = "";
        String sesEmpIdParam="";
        String displayPageTitle="";
        String dashboardLimit = ProjDetLlst.getDashboardReportLimit();
        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            deptCode = ProjDetLlst.getSesEmpDeptCode();
            desigCode = ProjDetLlst.getSesEmpDesigCode();
            toDayDate = ProjDetLlst.getTodayDate();
            toDate = ProjDetLlst.getToDate();
            frmMenuFlag = ProjDetLlst.getFrmMenuParam();
            sesEmpIdParam=ProjDetLlst.getSesEmpId();
            displayPageTitle=ProjDetLlst.getDisplayPageTitle();

            projList = " SELECT concat(g.company,'/',divi.company),b.proj_name,DATE_FORMAT(a.due_date,'%d-%b-%Y'),SUM(a.mss_count),c.stage,b.proj_id,a.stage,DATE(a.due_date),g.contact_id "
                    + " FROM chapter a, projects b, project_stage c,contacts g, contacts divi "
                    + " WHERE (a.ship_date IS NULL OR a.ship_date = '0000-00-00 00:00:00') AND c.stage_code=a.stage AND a.proj_id=b.proj_id AND "
                    + " b.client_name=g.contact_id AND b.division_id=divi.contact_id AND a.stage NOT IN ('INTART') AND ((b.project_status NOT IN ('24','2') and b.dept_code='CHN-WK') OR (b.project_status <> '2' and b.dept_code !='CHN-WK') OR (b.project_status <> '2' and b.dept_code is Null)) AND a.chapter_deleted='0' AND a.chapter_process='1' AND ";
            if(!toDate.equals("")) {
                projList += " DATE(a.due_date) BETWEEN DATE('" + toDayDate + "') AND DATE('" + toDate + "')  ";
            } else {
                projList += " a.due_date='" + toDayDate + "' ";
            }

            if (!frmMenuFlag.equals("")) {
                whereCondition = " AND b.dept_code is not null GROUP BY a.stage,a.proj_id,a.due_date ORDER BY a.due_date,g.company,a.stage ";
            } else {
                if (desigCode.equals("SNMGR")) {
                    whereCondition = " AND b.dept_code in ('CPD','CHN-CEN','CHN-MGH','CHN-PEA','CHN-PEACUS','CHN-WKH','OUP') GROUP BY a.stage,a.proj_id,a.due_date ORDER BY a.due_date,g.company,a.stage ";
                } else if (displayPageTitle.equals("7")){
                    whereCondition = " AND (b.planner='"+sesEmpIdParam+"' OR b.hybrid_planner='"+sesEmpIdParam+"') GROUP BY a.stage,a.proj_id,a.due_date ORDER BY a.due_date,g.company,a.stage ";
                }
                else {
                    whereCondition = " AND b.dept_code = '" + deptCode + "' GROUP BY a.stage,a.proj_id,a.due_date ORDER BY a.due_date,g.company,a.stage ";
                }
            }

            projList += whereCondition;

            //System.out.println("projList: " + projList);
            if(dashboardLimit.equals("1")) {
                        projList += " LIMIT 5";
            }
            rs = st.executeQuery(projList);

            while (rs.next()) {

                VODash = new DashBoardViewVO();

                if (rs.getString(1) != null) {
                    VODash.setClientName(splChar.decoding(rs.getString(1)));
                } else {
                    VODash.setClientName("");
                }

                if (rs.getString(2) != null) {
                    VODash.setProjName(rs.getString(2));
                } else {
                    VODash.setProjName("");
                }

                if (rs.getString(3) != null) {
                    VODash.setDispDueDate(rs.getString(3));
                } else {
                    VODash.setDispDueDate("");
                }

                if (rs.getString(4) != null) {
                    VODash.setTotMSS(rs.getString(4));
                } else {
                    VODash.setTotMSS("");
                }

                if (rs.getString(5) != null) {
                    VODash.setStageName(rs.getString(5));
                } else {
                    VODash.setStageName("");
                }

                if (rs.getString(6) != null) {
                    VODash.setProjectID(rs.getString(6));
                } else {
                    VODash.setProjectID("");
                }

                if (rs.getString(7) != null) {
                    VODash.setStageCode(rs.getString(7));
                } else {
                    VODash.setStageCode("");
                }

                if (rs.getString(8) != null) {
                    VODash.setDueDate(rs.getString(8));
                } else {
                    VODash.setDueDate("");
                }

                if (rs.getString(9) != null) {
                    VODash.setClientCode(rs.getString(9));
                } else {
                    VODash.setClientCode("");
                }

                packProjList.add(VODash);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return packProjList;
    }
public DashBoardViewVO getDelayedByKillDate(DashBoardViewVO dashBoardViewVO) {

        List clientNameList = new ArrayList();
        List projIdList = new ArrayList();
        List projNameList = new ArrayList();
        List todayList = new ArrayList();
        List chapList = new ArrayList();
        List dueDateList = new ArrayList();
        List chapIDList = new ArrayList();
        List stageList = new ArrayList();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String empId = dashBoardViewVO.getSesEmpId();
        String delay = dashBoardViewVO.getDelayConf();
        String dashboardLimit = dashBoardViewVO.getDashboardReportLimit();
        try {
            DBconnection dbconnection = new DBconnection();
            connection = dbconnection.getSampleProperty();
            statement = connection.createStatement();
            String query="";
           if (delay.equals("yes")){
            query = " SELECT CONCAT(g.company,'/',divi.company) AS Client_name, p.proj_id, p.proj_name, "
                           + " CASE WHEN DATEDIFF(CURRENT_DATE,DATE(MAX(cp.end_date))) = 0 THEN 1 ELSE 0 END AS TodayCount, cp.end_date, "
                           + " GROUP_CONCAT(DISTINCT c.stage), c.chapter_id, "
                           + " GROUP_CONCAT(DISTINCT c.chapter_no) AS Chapters "
                           + " FROM chapter_plan cp, chapter c,projects p,project_stage cc,contacts g,contacts divi, department d "
                           + " WHERE cp.milestone_id='74' AND cc.stage_code=c.stage AND p.client_name=g.contact_id AND p.division_id=divi.contact_id "
                           + " AND c.chapter_process=1 AND (p.dept_code=d.dept_code) AND c.chapter_id=cp.chapter_id AND c.chapter_deleted='0' AND c.batch_end_date IS NULL "
                           + " AND cp.end_date IS NOT NULL AND p.project_status NOT IN('2','20','21','23','24') "
                           + " AND c.proj_id=p.proj_id AND p.planner='"+empId+"' AND "
                           + "cp.end_date <= CURRENT_DATE GROUP BY c.proj_id, p.proj_name "
                           + " ORDER BY p.proj_name, cp.end_date ";


                  if(dashboardLimit.equals("1")) {
                      query += " LIMIT 5";
            }
            }
            if (delay.equals("no")){
            query = "SELECT CONCAT(g.company,'/',divi.company) AS Client_name, "
                    + "p.proj_id, p.proj_name, CASE WHEN DATEDIFF(CURRENT_DATE,DATE(MAX(cp.end_date))) = 0 THEN 1 ELSE 0 END AS TodayCount, "
                    + "DATE(cp.end_date), GROUP_CONCAT(DISTINCT c.stage ), c.chapter_id, GROUP_CONCAT(DISTINCT c.chapter_no) AS Chapters FROM chapter_plan cp, "
                    + "chapter c,projects p,project_stage cc,contacts g,contacts divi, department d WHERE cp.milestone_id='74' "
                    + "AND cc.stage_code=c.stage AND p.client_name=g.contact_id AND p.division_id=divi.contact_id AND "
                    + "c.chapter_process=1 AND (p.dept_code=d.dept_code) AND "
                    + "c.chapter_id=cp.chapter_id AND c.chapter_deleted='0' AND "
                    + "c.batch_end_date IS NULL AND cp.end_date IS NOT NULL AND "
                    + "p.project_status NOT IN('2','20','21','23','24') AND c.proj_id=p.proj_id AND p.planner='"+empId+"' "
                    + "AND cp.end_date BETWEEN CURRENT_DATE AND DATE_ADD(CURRENT_DATE(),INTERVAL 7 DAY) "
                    + "GROUP BY c.proj_id, p.proj_name ORDER BY cp.end_date";
            }
            //System.out.println("query"+query);
            if (!query.equals("")||query!=null){
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.getString(1) != null) {
                    clientNameList.add(splChar.decoding(resultSet.getString(1)));
                } else {
                    clientNameList.add("");
                }
                if (resultSet.getString(2) != null) {
                    projIdList.add(resultSet.getString(2));
                } else {
                    projIdList.add("");
                }
                if (resultSet.getString(3) != null) {
                    projNameList.add(resultSet.getString(3));
                } else {
                    projNameList.add("");
                }

                if (resultSet.getString(4) != null) {
                    todayList.add(resultSet.getString(4));
                } else {
                    todayList.add("");
                }

                if(resultSet.getString(5) != null) {
                    dueDateList.add(resultSet.getString(5));
                } else {
                    dueDateList.add("");
                }

                if(resultSet.getString(6) != null) {
                    stageList.add(resultSet.getString(6));
                } else {
                    stageList.add("");
                }

                if (resultSet.getString(7) != null) {
                    chapIDList.add(resultSet.getString(7));
                } else {
                    chapIDList.add("");
                }

                if (resultSet.getString(8) != null) {
                    chapList.add(resultSet.getString(8));
                } else {
                    chapList.add("");
                }

            }

            dashBoardViewVO.setProjIdList(projIdList);
            dashBoardViewVO.setProjNameList(projNameList);
            dashBoardViewVO.setTodayList(todayList);
            dashBoardViewVO.setChapList(chapList);
            dashBoardViewVO.setDispdueList(dueDateList);
            dashBoardViewVO.setClientNameList(clientNameList);
            dashBoardViewVO.setChapIDList(chapIDList);
            dashBoardViewVO.setStageList(stageList);
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException in DashBoardViewDAO - getDelayedByKillDate() : " + sqle);
        } catch (NullPointerException npe) {
            System.out.println("NullPointerException in DashBoardViewDAO - getDelayedByKillDate() : " + npe);
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException in DashBoardViewDAO - getDelayedByKillDate() : " + sqle);
            } catch (NullPointerException npe) {
                System.out.println("NullPointerException in DashBoardViewDAO - getDelayedByKillDate() : " + npe);
            }
        }
        return dashBoardViewVO;
    }
    public List getDelayedProjList(DashBoardViewVO ProjDelayedtLlst) {

        DashBoardViewVO VODash = null;

        List packProjList = new ArrayList();

        String deptCode = "";
        String desigCode = "";
        String toDayDate = "";
        String frmMenuFlag = "";
        String sesEmpIdParam = "";
        String dashboardLimit = ProjDelayedtLlst.getDashboardReportLimit();
        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            deptCode = ProjDelayedtLlst.getSesEmpDeptCode();
            desigCode = ProjDelayedtLlst.getSesEmpDesigCode();
            //System.out.println("Designation:"+desigCode);
            toDayDate = ProjDelayedtLlst.getTodayDate();
            frmMenuFlag = ProjDelayedtLlst.getFrmMenuParam();
            sesEmpIdParam = ProjDelayedtLlst.getSesEmpId();

            projList = " SELECT concat(g.company,'/',divi.company),b.proj_name,DATE_FORMAT(a.due_date,'%d-%b-%Y'),SUM(a.mss_count),c.stage,b.proj_id,a.stage,DATE(a.due_date),g.contact_id "
                    + " FROM chapter a, project_stage c,contacts g, contacts divi,projects b left join project_team pt on b.proj_id = pt.proj_id "
                    + " WHERE (a.ship_date IS NULL OR a.ship_date = '0000-00-00 00:00:00') AND c.stage_code=a.stage AND a.proj_id=b.proj_id AND "
                    + " b.client_name=g.contact_id AND b.division_id=divi.contact_id AND a.stage NOT IN ('INTART') AND ((b.project_status NOT IN ('24','2') and b.dept_code='CHN-WK') OR (b.project_status <> '2' and b.dept_code !='CHN-WK')) AND a.chapter_deleted='0' AND a.chapter_process='1' AND "
                    + " (a.due_date < '" + toDayDate + "') ";

            if (!frmMenuFlag.equals("")) {
                whereCondition = " AND b.dept_code is not null GROUP BY a.stage,a.proj_id,a.due_date ORDER BY a.due_date,g.company,a.stage ";
            } else {
                if (desigCode.equals("SNMGR")) {
                    whereCondition = " AND b.dept_code in ('CPD','CHN-CEN','CHN-MGH','CHN-PEA','CHN-PEACUS','CHN-WKH','OUP') GROUP BY a.stage,a.proj_id,a.due_date ORDER BY a.due_date,g.company,a.stage ";
                }
                else if(desigCode.equals("PM")||desigCode.equals("ACNMGR")||desigCode.equals("DEVEDT")||desigCode.equals("SNPM")||desigCode.equals("TRG-PRMG"))
                {
                    whereCondition = " AND (b.planner='"+sesEmpIdParam+"' OR b.hybrid_planner='"+sesEmpIdParam+"' OR pt.emp_id = '"+sesEmpIdParam+"') GROUP BY a.stage,a.proj_id,a.due_date ORDER BY a.due_date,g.company,a.stage ";
                    }
                else {
                    whereCondition = " AND b.dept_code = '" + deptCode + "' GROUP BY a.stage,a.proj_id,a.due_date ORDER BY a.due_date,g.company,a.stage ";
                }
            }

            projList += whereCondition;
            if(dashboardLimit.equals("1")) {
                        projList += " LIMIT 5";
            }
            //System.out.println("Delayed projList: " + projList);

            rs = st.executeQuery(projList);

            while (rs.next()) {

                VODash = new DashBoardViewVO();

                if (rs.getString(1) != null) {
                    VODash.setClientName(splChar.decoding(rs.getString(1)));
                } else {
                    VODash.setClientName("");
                }

                if (rs.getString(2) != null) {
                    VODash.setProjName(rs.getString(2));
                } else {
                    VODash.setProjName("");
                }

                if (rs.getString(3) != null) {
                    VODash.setDispDueDate(rs.getString(3));
                } else {
                    VODash.setDispDueDate("");
                }

                if (rs.getString(4) != null) {
                    VODash.setTotMSS(rs.getString(4));
                } else {
                    VODash.setTotMSS("");
                }

                if (rs.getString(5) != null) {
                    VODash.setStageName(rs.getString(5));
                } else {
                    VODash.setStageName("");
                }

                if (rs.getString(6) != null) {
                    VODash.setProjectID(rs.getString(6));
                } else {
                    VODash.setProjectID("");
                }

                if (rs.getString(7) != null) {
                    VODash.setStageCode(rs.getString(7));
                } else {
                    VODash.setStageCode("");
                }

                if (rs.getString(8) != null) {
                    VODash.setDueDate(rs.getString(8));
                } else {
                    VODash.setDueDate("");
                }

                if (rs.getString(9) != null) {
                    VODash.setClientCode(rs.getString(9));
                } else {
                    VODash.setClientCode("");
                }

                packProjList.add(VODash);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return packProjList;
    }

    public List getHoldProjList(DashBoardViewVO HoldAssignDetLlst) {

        DashBoardViewVO VODash = null;

        List packHoldProjAssignList = new ArrayList();

        String sesEmpIdParam = "";
        String dashboardLimit = HoldAssignDetLlst.getDashboardReportLimit();
        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            sesEmpIdParam = HoldAssignDetLlst.getSesEmpId();

            projList = "SELECT CONCAT(g.company,'/',divi.company),b.proj_name,DATE_FORMAT(a.due_date,'%d-%b-%Y'),SUM(a.mss_count),c.stage,b.proj_id,a.stage, "
                    + "DATE(a.due_date),g.contact_id  FROM chapter a, project_stage c,contacts g, contacts divi,projects b LEFT JOIN project_team pt ON b.proj_id = pt.proj_id  "
                    + "WHERE (a.ship_date IS NULL OR a.ship_date = '0000-00-00 00:00:00') AND c.stage_code=a.stage AND a.proj_id=b.proj_id AND "
                    + "b.client_name=g.contact_id AND b.division_id=divi.contact_id AND a.stage NOT IN ('INTART') AND ((b.project_status NOT IN ('24','2') and b.dept_code='CHN-WK') OR (b.project_status <> '2' and b.dept_code !='CHN-WK')) AND "
                    + "a.chapter_deleted='0' AND a.chapter_process='0' AND (b.planner='"+sesEmpIdParam+"' OR b.hybrid_planner='"+sesEmpIdParam+"' OR pt.emp_id = '"+sesEmpIdParam+"') "
                    + "GROUP BY a.stage,a.proj_id,a.due_date ORDER BY a.due_date,g.company,a.stage ";

          /*  projList = " SELECT CONCAT(g.company,'/',divi.company),b.proj_name,b.proj_id,DATE_FORMAT(b.proj_date,'%d-%b-%Y'),b.planner,u.emp_name "
                    + " FROM projects b,contacts g,contacts divi,user u "
                    + " WHERE b.client_name=g.contact_id AND b.division_id=divi.contact_id AND b.proj_id != 106002 "
                    + "  AND b.project_status='0' and b.planner='"+sesEmpIdParam+"' and u.emp_id=b.planner ORDER BY b.proj_date ";*/

            //System.out.println("Hold projList: " + projList);
            if(dashboardLimit.equals("1")) {
                        projList += " LIMIT 5";
            }
            rs = st.executeQuery(projList);

           while (rs.next()) {

                VODash = new DashBoardViewVO();

                if (rs.getString(1) != null) {
                    VODash.setClientName(splChar.decoding(rs.getString(1)));
                } else {
                    VODash.setClientName("");
                }

                if (rs.getString(2) != null) {
                    VODash.setProjName(rs.getString(2));
                } else {
                    VODash.setProjName("");
                }

                if (rs.getString(3) != null) {
                    VODash.setDispDueDate(rs.getString(3));
                } else {
                    VODash.setDispDueDate("");
                }

                if (rs.getString(4) != null) {
                    VODash.setTotMSS(rs.getString(4));
                } else {
                    VODash.setTotMSS("");
                }

                if (rs.getString(5) != null) {
                    VODash.setStageName(rs.getString(5));
                } else {
                    VODash.setStageName("");
                }

                if (rs.getString(6) != null) {
                    VODash.setProjectID(rs.getString(6));
                } else {
                    VODash.setProjectID("");
                }

                if (rs.getString(7) != null) {
                    VODash.setStageCode(rs.getString(7));
                } else {
                    VODash.setStageCode("");
                }

                if (rs.getString(8) != null) {
                    VODash.setDueDate(rs.getString(8));
                } else {
                    VODash.setDueDate("");
                }

                if (rs.getString(9) != null) {
                    VODash.setClientCode(rs.getString(9));
                } else {
                    VODash.setClientCode("");
                }

                packHoldProjAssignList.add(VODash);
            }
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return packHoldProjAssignList;
    }

    public List getPrinterProjDetList(DashBoardViewVO ProjPrnLlst) {

        DashBoardViewVO VODash = null;

        List packProjList = new ArrayList();

        String deptCode = "";
        String desigCode = "";
        String curWeekStart = "";
        String curWeekEnd = "";
        String frmMenuFlag = "";
        String sesEmpIdParam="";
        String displayPageTitle="";
        String dashboardLimit = ProjPrnLlst.getDashboardReportLimit();
        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            deptCode = ProjPrnLlst.getSesEmpDeptCode();
            desigCode = ProjPrnLlst.getSesEmpDesigCode();
            curWeekStart = ProjPrnLlst.getCurWeekStart();
            curWeekEnd = ProjPrnLlst.getCurWeekEnd();
            frmMenuFlag = ProjPrnLlst.getFrmMenuParam();
            sesEmpIdParam = ProjPrnLlst.getSesEmpId();
            displayPageTitle=ProjPrnLlst.getDisplayPageTitle();
            //System.out.println("frmMenuFlag - in java: " + frmMenuFlag);

            projList = " SELECT b.proj_id,b.proj_name,a.stage,DATE_FORMAT(a.due_date,'%d-%b-%Y'),SUM(a.mss_count),c.stage,CONCAT(g.company,'/',divi.company)"
                    + " FROM chapter a,projects b,project_stage c,contacts g,contacts divi "
                    + " WHERE (a.ship_date IS NULL OR a.ship_date = '0000-00-00 00:00:00') AND c.stage_code=a.stage AND a.proj_id=b.proj_id "
                    + " AND b.client_name=g.contact_id AND b.division_id=divi.contact_id "
                    + " AND a.stage='PDF' AND ((b.project_status NOT IN ('24','2') and b.dept_code='CHN-WK') OR (b.project_status <> '2' and b.dept_code !='CHN-WK')) AND a.chapter_deleted='0' AND a.chapter_process='1' AND a.due_date BETWEEN '" + curWeekStart + "' AND '" + curWeekEnd + "' ";

            if (!frmMenuFlag.equals("")) {
                whereCondition = " AND b.dept_code is not null GROUP BY a.due_date,b.proj_id ORDER BY a.due_date ";
            } else {
                if (desigCode.equals("SNMGR")) {
                    whereCondition = " AND b.dept_code in ('CPD','CHN-CEN','CHN-MGH','CHN-PEA','CHN-PEACUS','CHN-WKH','OUP') GROUP BY a.due_date,b.proj_id ORDER BY a.due_date ";
                } else if (displayPageTitle.equals("7")){
                    whereCondition = " AND (b.planner='"+sesEmpIdParam+"' OR b.hybrid_planner='"+sesEmpIdParam+"') GROUP BY a.due_date,b.proj_id ORDER BY a.due_date ";
                } else
                {
                    whereCondition = " AND b.dept_code = '" + deptCode + "' GROUP BY a.due_date,b.proj_id ORDER BY a.due_date ";
                }
            }

            projList += whereCondition;

            if(dashboardLimit.equals("1")) {
                        projList += " LIMIT 5";
            }
            //System.out.println("projList: " + projList);

            rs = st.executeQuery(projList);

            while (rs.next()) {

                VODash = new DashBoardViewVO();

                if (rs.getString(1) != null) {
                    VODash.setProjectID(rs.getString(1));
                } else {
                    VODash.setProjectID("");
                }

                if (rs.getString(2) != null) {
                    VODash.setProjName(rs.getString(2));
                } else {
                    VODash.setProjName("");
                }

                if (rs.getString(3) != null) {
                    VODash.setStageCode(rs.getString(3));
                } else {
                    VODash.setStageCode("");
                }

                if (rs.getString(4) != null) {
                    VODash.setDueDate(rs.getString(4));
                } else {
                    VODash.setDueDate("");
                }

                if (rs.getString(5) != null) {
                    VODash.setTotMSS(rs.getString(5));
                } else {
                    VODash.setTotMSS("");
                }

                if (rs.getString(6) != null) {
                    VODash.setStageName(rs.getString(6));
                } else {
                    VODash.setStageName("");
                }

                if (rs.getString(7) != null) {
                    VODash.setClientName(splChar.decoding(rs.getString(7)));
                } else {
                    VODash.setClientName("");
                }

                packProjList.add(VODash);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return packProjList;
    }

    public List getChapList(DashBoardViewVO ChapDetLlst) {

        DashBoardViewVO VODash = null;

        List packChapList = new ArrayList();

        String projID = "";
        String stageCode = "";
        String dueDate = "";
        String dashboardLimit = ChapDetLlst.getDashboardReportLimit();


        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            projID = ChapDetLlst.getProjectID();
            stageCode = ChapDetLlst.getStageCode();
            dueDate = ChapDetLlst.getDispDueDate();

            chapList = " select a.chapter_no,a.chapter_id,a.chapter_process,CASE WHEN a.batch_end_date IS NOT NULL THEN '(yet to ship)' END AS DelayChapter "
                    + " from chapter a, projects b, project_stage c "
                    + " where (a.ship_date is null or a.ship_date = '0000-00-00 00:00:00') and c.stage_code=a.stage and a.proj_id=b.proj_id "
                    + " and a.proj_id='" + projID + "' and a.stage='" + stageCode + "' and date_format(a.due_date,'%d-%b-%Y')='" + dueDate + "' "
                    + " and ((b.project_status NOT IN ('24','2') and b.dept_code='CHN-WK') OR (b.project_status <> '2' and b.dept_code !='CHN-WK') OR (b.project_status <> '2' and b.dept_code IS NULL)) AND a.chapter_deleted='0' AND a.chapter_process='1' ORDER BY a.due_date ";

            if(dashboardLimit.equals("1")) {
                        chapList += " LIMIT 5";
            }
            rs = st.executeQuery(chapList);

            while (rs.next()) {

                VODash = new DashBoardViewVO();

                if (rs.getString(1) != null) {
                    VODash.setChapName(rs.getString(1));
                } else {
                    VODash.setChapName("");
                }

                if (rs.getString(2) != null) {
                    VODash.setChapID(rs.getString(2));
                } else {
                    VODash.setChapID("");
                }

                if (rs.getString(3) != null) {
                    VODash.setChapProcess(rs.getString(3));
                } else {
                    VODash.setChapProcess("");
                }
                if (rs.getString(4) != null) {
                    VODash.setBatchEndDate(rs.getString(4));
                } else {
                    VODash.setBatchEndDate("");
                }

                packChapList.add(VODash);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return packChapList;
    }

    
    public List getHoldChapList(DashBoardViewVO ChapDetLlst) {

        DashBoardViewVO VODash = null;

        List packChapList = new ArrayList();

        String projID = "";
        String stageCode = "";
        String dueDate = "";
        String dashboardLimit = ChapDetLlst.getDashboardReportLimit();


        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            projID = ChapDetLlst.getProjectID();
            stageCode = ChapDetLlst.getStageCode();
            dueDate = ChapDetLlst.getDispDueDate();

            chapList = " select a.chapter_no,a.chapter_id,a.chapter_process "
                    + " from chapter a, projects b, project_stage c "
                    + " where (a.ship_date is null or a.ship_date = '0000-00-00 00:00:00') and c.stage_code=a.stage and a.proj_id=b.proj_id "
                    + " and a.proj_id='" + projID + "' and a.stage='" + stageCode + "' and date_format(a.due_date,'%d-%b-%Y')='" + dueDate + "' "
                    + " and ((b.project_status NOT IN ('24','2') and b.dept_code='CHN-WK') OR (b.project_status <> '2' and b.dept_code !='CHN-WK') OR (b.project_status <> '2' and b.dept_code IS NULL)) AND a.chapter_deleted='0' AND a.chapter_process='0' ORDER BY a.due_date ";

            if(dashboardLimit.equals("1")) {
                        chapList += " LIMIT 5";
            }
            rs = st.executeQuery(chapList);

            while (rs.next()) {

                VODash = new DashBoardViewVO();

                if (rs.getString(1) != null) {
                    VODash.setChapName(rs.getString(1));
                } else {
                    VODash.setChapName("");
                }

                if (rs.getString(2) != null) {
                    VODash.setChapID(rs.getString(2));
                } else {
                    VODash.setChapID("");
                }

                if (rs.getString(3) != null) {
                    VODash.setChapProcess(rs.getString(3));
                } else {
                    VODash.setChapProcess("");
                }

                packChapList.add(VODash);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return packChapList;
    }

      
    public List getWorkInHandTotCount(DashBoardViewVO totCountLlst) {

        DashBoardViewVO VODash = null; 

        List packBatchList = new ArrayList();

        String deptCode = "";
        String dashboardLimit = totCountLlst.getDashboardReportLimit();
        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            deptCode = totCountLlst.getDeptCodeParam();

            batchDetList = " SELECT d.department,ps.stage,ps.stage_code,SUM(c.mss_count) FROM chapter c,project_stage ps,projects p,department d "
                    + " WHERE ps.stage_code=c.stage AND c.ship_date IS NULL AND p.proj_id=c.proj_id AND ((p.project_status NOT IN ('24','2') and p.dept_code='CHN-WK') OR (p.project_status <> '2' and p.dept_code !='CHN-WK')) AND p.dept_code='" + deptCode + "' "
                    + " AND p.dept_code=d.dept_code GROUP BY c.stage ORDER BY c.stage ";

            if(dashboardLimit.equals("1")) {
                        batchDetList += " LIMIT 5";
            }
            rs = st.executeQuery(batchDetList);

            while (rs.next()) {

                VODash = new DashBoardViewVO();

                if (rs.getString(1) != null) {
                    VODash.setTotCountDept(rs.getString(1));
                } else {
                    VODash.setTotCountDept("");
                }

                if (rs.getString(2) != null) {
                    VODash.setTotCountStage(rs.getString(2));
                } else {
                    VODash.setTotCountStage("");
                }

                if (rs.getString(3) != null) {
                    VODash.setTotCountStageCode(rs.getString(3));
                } else {
                    VODash.setTotCountStageCode("");
                }

                if (rs.getString(4) != null) {
                    VODash.setTotCountMSS(rs.getString(4));
                } else {
                    VODash.setTotCountMSS("");
                }

                packBatchList.add(VODash);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return packBatchList;
    }

    public List getWorkInHandHoldCount(DashBoardViewVO holdCountLlst) {

        DashBoardViewVO VODash = null;

        List packHoldList = new ArrayList();

        String deptCode = "";
        String dashboardLimit = holdCountLlst.getDashboardReportLimit();
        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            deptCode = holdCountLlst.getDeptCodeParam();

            batchDetList = " SELECT d.department,ps.stage,ps.stage_code,SUM(c.mss_count) FROM chapter c,project_stage ps,projects p,department d "
                    + " WHERE ps.stage_code=c.stage AND c.ship_date IS NULL AND p.proj_id=c.proj_id AND ((p.project_status NOT IN ('24','2') and p.dept_code='CHN-WK') OR (p.project_status <> '2' and p.dept_code !='CHN-WK')) AND c.chapter_process='0' "
                    + " AND p.dept_code='" + deptCode + "' AND p.dept_code=d.dept_code GROUP BY c.stage ORDER BY c.stage ";

            if(dashboardLimit.equals("1")) {
                        batchDetList += " LIMIT 5";
            }
            rs = st.executeQuery(batchDetList);

            while (rs.next()) {

                VODash = new DashBoardViewVO();

                if (rs.getString(1) != null) {
                    VODash.setHoldCountDept(rs.getString(1));
                } else {
                    VODash.setHoldCountDept("");
                }

                if (rs.getString(2) != null) {
                    VODash.setHoldCountStage(rs.getString(2));
                } else {
                    VODash.setHoldCountStage("");
                }

                if (rs.getString(3) != null) {
                    VODash.setHoldCountStageCode(rs.getString(3));
                } else {
                    VODash.setHoldCountStageCode("");
                }

                if (rs.getString(4) != null) {
                    VODash.setHoldCountMSS(rs.getString(4));
                } else {
                    VODash.setHoldCountMSS("");
                }

                packHoldList.add(VODash);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return packHoldList;
    }

    public List getWorkInHandDelayedCount(DashBoardViewVO delayedCountLlst) {

        DashBoardViewVO VODash = null;

        List packDelayedList = new ArrayList();

        String deptCode = "";
        String dashboardLimit = delayedCountLlst.getDashboardReportLimit();
        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            deptCode = delayedCountLlst.getDeptCodeParam();

            batchDetList = " SELECT d.department,ps.stage,ps.stage_code,SUM(c.mss_count) FROM chapter c,project_stage ps,projects p,department d "
                    + " WHERE ps.stage_code=c.stage AND c.ship_date IS NULL AND p.proj_id=c.proj_id AND p.dept_code='" + deptCode + "' "
                    + " AND ((p.project_status NOT IN ('24','2') and p.dept_code='CHN-WK') OR (p.project_status <> '2' and p.dept_code !='CHN-WK')) AND c.chapter_process='1' AND c.due_date<DATE(CURRENT_TIMESTAMP()) AND p.dept_code=d.dept_code "
                    + " GROUP BY c.stage ORDER BY c.stage ";

            if(dashboardLimit.equals("1")) {
                        batchDetList += " LIMIT 5";
            }
            rs = st.executeQuery(batchDetList);

            while (rs.next()) {

                VODash = new DashBoardViewVO();

                if (rs.getString(1) != null) {
                    VODash.setDelayCountDept(rs.getString(1));
                } else {
                    VODash.setDelayCountDept("");
                }

                if (rs.getString(2) != null) {
                    VODash.setDelayCountStage(rs.getString(2));
                } else {
                    VODash.setDelayCountStage("");
                }

                if (rs.getString(3) != null) {
                    VODash.setDelayCountStageCode(rs.getString(3));
                } else {
                    VODash.setDelayCountStageCode("");
                }

                if (rs.getString(4) != null) {
                    VODash.setDelayCountMSS(rs.getString(4));
                } else {
                    VODash.setDelayCountMSS("");
                }

                packDelayedList.add(VODash);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return packDelayedList;
    }

    public List getDashBoardBatchView(DashBoardViewVO ProjBatchDetLlst) {

        DashBoardViewVO VODash = null;

        List packBatchProcessList = new ArrayList();

        String deptCode = "";
        String desigCode = "";
        String dashboardLimit = ProjBatchDetLlst.getDashboardReportLimit();
        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            deptCode = ProjBatchDetLlst.getSesEmpDeptCode();
            desigCode = ProjBatchDetLlst.getSesEmpDesigCode();

            projList = " select ps.stage,ps.stage_code,sum(c.mss_count) from chapter c,project_stage ps,projects p,department d "
                    + " where ps.stage_code=c.stage and c.ship_date is null and p.proj_id=c.proj_id and ((p.project_status NOT IN('24','2') and p.dept_code='CHN-WK') OR (p.project_status <> '2' and p.dept_code !='CHN-WK')) and p.dept_code=d.dept_code ";


            if (desigCode.equals("SNMGR")) {
                whereCondition = " and p.dept_code in ('CPD','CHN-CEN','CHN-MGH','CHN-PEA','CHN-PEACUS','CHN-WKH','OUP') group by c.stage order by c.stage ";
            } else {
                whereCondition = " and p.dept_code= '" + deptCode + "' group by c.stage order by c.stage ";
            }

            projList += whereCondition;
            if(dashboardLimit.equals("1")) {
                        projList += " LIMIT 5";
            }
            //System.out.println("projList: " + projList);

            rs = st.executeQuery(projList);

            while (rs.next()) {

                VODash = new DashBoardViewVO();

                if (rs.getString(1) != null) {
                    VODash.setTotCountStage(rs.getString(1));
                } else {
                    VODash.setTotCountStage("");
                }

                if (rs.getString(2) != null) {
                    VODash.setTotCountStageCode(rs.getString(2));
                } else {
                    VODash.setTotCountStageCode("");
                }

                if (rs.getString(3) != null) {
                    VODash.setTotCountMSS(rs.getString(3));
                } else {
                    VODash.setTotCountMSS("");
                }

                packBatchProcessList.add(VODash);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return packBatchProcessList;
    }

    public List getDeptCompProductivity(DashBoardViewVO DeptProdDetLlst) {

        DashBoardViewVO VODash = null;

        List packDeptCompProdList = new ArrayList();

        String deptCode = "";
        String CurMonthStartDate = "";
        String CurMonthEndDate = "";
        String dashboardLimit = DeptProdDetLlst.getDashboardReportLimit();
        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            deptCode = DeptProdDetLlst.getDeptCodeParam();
            CurMonthStartDate = DeptProdDetLlst.getCurMonthStart();
            CurMonthEndDate = DeptProdDetLlst.getCurMonthEnd();

            projList = " select f.dept_code,ROUND((((sum(e.proof_pages)/SUM(TIME_TO_SEC(TIMEDIFF(e.end_time,e.begin_time))))*3600)),2) as pageshr "
                    + " from user a,activity e,department f where a.emp_id=e.emp_id and a.dept_code=f.dept_code and e.approval_status is not null and "
                    + " e.activity_code in ('33') and date(e.begin_time) between '"+CurMonthStartDate+"' and '"+CurMonthEndDate+"' and a.dept_code='"+deptCode+"' "
                    + " group by a.dept_code order by a.dept_code ";

            //System.out.println("projList: " + projList);
            if(dashboardLimit.equals("1")) {
                        projList += " LIMIT 5";
            }
            rs = st.executeQuery(projList);

            while (rs.next()) {

                VODash = new DashBoardViewVO();

                if (rs.getString(2) != null) {
                    VODash.setDeptCompProd(rs.getString(2));
                } else {
                    VODash.setDeptCompProd("");
                }

                packDeptCompProdList.add(VODash);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return packDeptCompProdList;
    }

    public List getDeptArtProductivity(DashBoardViewVO DeptProdDetLlst) {

        DashBoardViewVO VODash = null;

        List packDeptArtProdList = new ArrayList();

        String deptCode = "";
        String CurMonthStartDate = "";
        String CurMonthEndDate = "";
        String dashboardLimit = DeptProdDetLlst.getDashboardReportLimit();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            deptCode = DeptProdDetLlst.getDeptCodeParam();
            CurMonthStartDate = DeptProdDetLlst.getCurMonthStart();
            CurMonthEndDate = DeptProdDetLlst.getCurMonthEnd();

            projList = " select f.dept_code,ROUND((((sum(e.proof_pages)/SUM(TIME_TO_SEC(TIMEDIFF(e.end_time,e.begin_time))))*3600)),2) as pageshr "
                            + " from user a,activity e,department f where a.emp_id=e.emp_id and a.dept_code=f.dept_code and e.approval_status is not null "
                            + " and e.activity_code in ('21') and date(e.begin_time) between '"+CurMonthStartDate+"' and '"+CurMonthEndDate+"' and "
                            + " a.dept_code='"+deptCode+"' group by a.dept_code order by a.dept_code ";

            //System.out.println("projList: " + projList);
            if(dashboardLimit.equals("1")) {
                        projList += " LIMIT 5";
            }
            rs = st.executeQuery(projList);

            while (rs.next()) {

                VODash = new DashBoardViewVO();

                if (rs.getString(2) != null) {
                    VODash.setDeptArtProd(rs.getString(2));
                } else {
                    VODash.setDeptArtProd("");
                }

                packDeptArtProdList.add(VODash);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return packDeptArtProdList;
    }

    public List getDeptCodingProductivity(DashBoardViewVO DeptProdDetLlst) {

        DashBoardViewVO VODash = null;

        List packDeptCodingProdList = new ArrayList();

        String deptCode = "";
        String CurMonthStartDate = "";
        String CurMonthEndDate = "";
        String dashboardLimit = DeptProdDetLlst.getDashboardReportLimit();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            deptCode = DeptProdDetLlst.getDeptCodeParam();
            CurMonthStartDate = DeptProdDetLlst.getCurMonthStart();
            CurMonthEndDate = DeptProdDetLlst.getCurMonthEnd();

            projList = " select f.dept_code,ROUND((((sum(e.proof_pages)/SUM(TIME_TO_SEC(TIMEDIFF(e.end_time,e.begin_time))))*3600)),2) as pageshr "
                    + " from user a,activity e,department f where a.emp_id=e.emp_id and a.dept_code=f.dept_code and e.approval_status is not null and "
                    + " e.activity_code in ('35') and date(e.begin_time) between '"+CurMonthStartDate+"' and '"+CurMonthEndDate+"' and a.dept_code='"+deptCode+"' "
                    + " group by a.dept_code order by a.dept_code ";

            //System.out.println("projList: " + projList);
            if(dashboardLimit.equals("1")) {
                        projList += " LIMIT 5";
            }
            rs = st.executeQuery(projList);

            while (rs.next()) {

                VODash = new DashBoardViewVO();

                if (rs.getString(2) != null) {
                    VODash.setDeptCodingProd(rs.getString(2));
                } else {
                    VODash.setDeptCodingProd("");
                }

                packDeptCodingProdList.add(VODash);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return packDeptCodingProdList;
    }

    public List getEstInvProjectDet(DashBoardViewVO EstInvDetLlst) {

        DashBoardViewVO VODash = null;

        List packEstInvProjList = new ArrayList();

        String estInvParam = "";
        String where = "";
        String dashboardLimit = EstInvDetLlst.getDashboardReportLimit();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            estInvParam = EstInvDetLlst.getEstInvParam();

            projList = " SELECT CONCAT(g.company,'/',divi.company),b.proj_name,b.proj_id,DATE_FORMAT(b.proj_date,'%d-%b-%Y') "
                    + " FROM projects b,contacts g,contacts divi WHERE b.client_name=g.contact_id AND b.division_id=divi.contact_id "
                    + " AND b.proj_id != 106002 AND b.proj_date > '2013-02-01 00:00:00' ";

            if (estInvParam.equals("Estimation")){
                where = " and ((b.project_status NOT IN ('24','2') and b.dept_code='CHN-WK') OR (b.project_status <> '2' and b.dept_code !='CHN-WK')) AND b.proj_id NOT IN (SELECT CONCAT(proj_id,'') FROM estimate "
                        +"WHERE est_process_flag = '1') ORDER BY b.proj_date ";
            } else {
                where = " and b.project_status='22' and b.project_status <> 23 ORDER BY b.proj_date ";
            }

            projList += where;
            if(dashboardLimit.equals("1")) {
                projList += " LIMIT 5";
            }
            //System.out.println("projList: " + projList);

            rs = st.executeQuery(projList);

            while (rs.next()) {

                VODash = new DashBoardViewVO();

                if (rs.getString(1) != null) {
                    VODash.setEstInvProjClientName(splChar.decoding(rs.getString(1)));
                } else {
                    VODash.setEstInvProjClientName("");
                }

                if (rs.getString(2) != null) {
                    VODash.setEstInvProjName(rs.getString(2));
                } else {
                    VODash.setEstInvProjName("");
                }

                if (rs.getString(3) != null) {
                    VODash.setEstInvProjId(rs.getString(3));
                } else {
                    VODash.setEstInvProjId("");
                }

                if (rs.getString(4) != null) {
                    VODash.setEstInvProjAddedDate(rs.getString(4));
                } else {
                    VODash.setEstInvProjAddedDate("");
                }

                packEstInvProjList.add(VODash);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return packEstInvProjList;
    }

    public List getProjectAssignmentDet(DashBoardViewVO ProjAssignDetLlst) {

        DashBoardViewVO VODash = null;

        List packProjAssignList = new ArrayList();

        String sesEmpIdParam = "";
        String dashboardLimit = ProjAssignDetLlst.getDashboardReportLimit();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            sesEmpIdParam = ProjAssignDetLlst.getSesEmpId();

            projList = " SELECT CONCAT(g.company,'/',divi.company),b.proj_name,b.proj_id,DATE_FORMAT(b.proj_date,'%d-%b-%Y'),b.planner,u.emp_name "
                    + " FROM user u,contacts g,contacts divi,projects b LEFT JOIN project_team pt ON b.proj_id = pt.proj_id "
                    + " WHERE b.client_name=g.contact_id AND b.division_id=divi.contact_id AND b.proj_id != 106002  and u.emp_id=b.planner "
                    + "  AND ((b.project_status NOT IN ('24','2','21','22','23') and b.dept_code='CHN-WK') OR (b.project_status NOT IN ('24','2','21','22','23') and b.dept_code !='CHN-WK')) and (b.planner='"+sesEmpIdParam+"' OR b.hybrid_planner='"+sesEmpIdParam+"' OR pt.emp_id = '"+sesEmpIdParam+"') GROUP BY b.proj_id ORDER BY b.proj_date ";

            if(dashboardLimit.equals("1")) {
                //projList += " LIMIT 5";
            }
            //System.out.println("projList: " + projList);

            rs = st.executeQuery(projList);

            while (rs.next()) {

                VODash = new DashBoardViewVO();

                if (rs.getString(1) != null) {
                    VODash.setClientName(splChar.decoding(rs.getString(1)));
                } else {
                    VODash.setClientName("");
                }

                if (rs.getString(2) != null) {
                    VODash.setProjName(rs.getString(2));
                } else {
                    VODash.setProjName("");
                }

                if (rs.getString(3) != null) {
                    VODash.setProjectID(rs.getString(3));
                } else {
                    VODash.setProjectID("");
                }

                if (rs.getString(4) != null) {
                    VODash.setProjBookedDate(rs.getString(4));
                } else {
                    VODash.setProjBookedDate("");
                }

                if (rs.getString(5) != null) {
                    VODash.setProjPlannerId(rs.getString(5));
                } else {
                    VODash.setProjPlannerId("");
                }
                
                if (rs.getString(6) != null) {
                    VODash.setProjPlanner(rs.getString(6));
                } else {
                    VODash.setProjPlanner("");
                }

                packProjAssignList.add(VODash);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return packProjAssignList;
    }

    
}
