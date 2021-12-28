/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects.chapters;

import connection.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aravindhanj
 */
public class ProductionOnTimeDAO {
    public ProductionOnTimeVO getProductionOnTime(ProductionOnTimeVO productionOnTimeVO) {

        String sqlWhere = "";
        List chapterIdList = new ArrayList();
        List projectIdList = new ArrayList();
        List projectNameList = new ArrayList();
        List customerCompanyList = new ArrayList();
        List projPlannerList = new ArrayList();
        List projHybridPlannerList = new ArrayList();
        List chapterStageList = new ArrayList();
        List chapterNameList = new ArrayList();
        List chapterDueDateList = new ArrayList();
        List chapterActualDateList = new ArrayList();
        List dayDiffList = new ArrayList();

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        
        Connection con = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;

        String getStartDate = productionOnTimeVO.getDueStartDate();
        String getEndDate = productionOnTimeVO.getDueEndDate();
        String getCustomerId = productionOnTimeVO.getCustomerId();
        String getPlannerId = productionOnTimeVO.getPlannerId();
        String getHybridPlannerId = productionOnTimeVO.getHybridPlannerId();
        String getProjectId = productionOnTimeVO.getProjectId();

        if(!getStartDate.equals("") && !getEndDate.equals("")) {
            sqlWhere += " AND cp.end_date BETWEEN '" + getStartDate + "' AND '" + getEndDate + "' ";;
        }
        if(!getCustomerId.equals("")) {
            sqlWhere += " AND ct.contact_id='" + getCustomerId + "' ";
        }
        if(!getPlannerId.equals("")) {
            sqlWhere += " AND p.planner='" + getPlannerId + "' ";
        }
        if(!getHybridPlannerId.equals("")) {
            sqlWhere += " AND p.hybrid_planner='" + getHybridPlannerId + "' ";
        }
        if(!getProjectId.equals("")) {
            sqlWhere += " AND p.proj_id='" + getProjectId + "' ";
        }

        try {

            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            
            String productionFilesQuery = "SELECT c.chapter_id, p.proj_id, p.proj_name, "
                    + "ct.company, u.emp_name, hp.emp_name, ps.stage, c.chapter_no, DATE(cp.end_date), DATE(c.batch_end_date), "
                    + " DATEDIFF(IFNULL(DATE(c.batch_end_date),CURRENT_DATE()),DATE(cp.end_date)) AS diff "
                    + " FROM project_stage ps, USER u, chapter c, chapter_plan cp, contacts ct, department d, "
                    + " projects p LEFT JOIN USER hp ON p.hybrid_planner=hp.emp_id "
                    + " WHERE p.proj_id=c.proj_id AND c.stage=ps.stage_code AND p.planner=u.emp_id AND cp.chapter_id=c.chapter_id AND "
                    + " p.client_name=ct.contact_id AND d.dept_code=p.dept_code AND c.due_date IS NOT NULL AND "
                    + " cp.milestone_id='74' AND c.chapter_deleted='0' AND c.chapter_status='1' AND c.chapter_process='1'"  + sqlWhere
                    + " ORDER BY diff DESC, cp.end_date, ct.company, p.proj_name, u.emp_name, ps.stage";
System.out.println(productionFilesQuery);
            preStmt = con.prepareStatement(productionFilesQuery);
            rs = preStmt.executeQuery();

            while (rs.next()) {

                if (rs.getString(1) != null) {
                    chapterIdList.add(rs.getString(1));
                } else {
                    chapterIdList.add("");
                }
                if (rs.getString(2) != null) {
                    projectIdList.add(rs.getString(2));
                } else {
                    projectIdList.add("");
                }
                if (rs.getString(3) != null) {
                    projectNameList.add(rs.getString(3));
                } else {
                    projectNameList.add("");
                }
                if (rs.getString(4) != null) {
                    customerCompanyList.add(splChar.decoding(rs.getString(4)));
                } else {
                    customerCompanyList.add("");
                }
                if (rs.getString(5) != null) {
                    projPlannerList.add(rs.getString(5));
                } else {
                    projPlannerList.add("");
                }
                if (rs.getString(6) != null) {
                    projHybridPlannerList.add(rs.getString(6));
                } else {
                    projHybridPlannerList.add("");
                }
                if (rs.getString(7) != null) {
                    chapterStageList.add(rs.getString(7));
                } else {
                    chapterStageList.add("");
                }
                if (rs.getString(8) != null) {
                    chapterNameList.add(rs.getString(8));
                } else {
                    chapterNameList.add("");
                }
                if (rs.getString(9) != null) {
                    chapterDueDateList.add(rs.getString(9));
                } else {
                    chapterDueDateList.add("");
                }
                if (rs.getString(10) != null) {
                    chapterActualDateList.add(rs.getString(10));
                } else {
                    chapterActualDateList.add("");
                }
                if (rs.getString(11) != null) {
                    dayDiffList.add(rs.getString(11));
                  
                } else {
                    dayDiffList.add("0");
                }
            }
            productionOnTimeVO.setChapterIdList(chapterIdList);
            productionOnTimeVO.setChapterNameList(chapterNameList);
            productionOnTimeVO.setProjectIdList(projectIdList);
            productionOnTimeVO.setProjectNameList(projectNameList);
            productionOnTimeVO.setCustomerCompanyList(customerCompanyList);
            productionOnTimeVO.setProjPlannerList(projPlannerList);
            productionOnTimeVO.setProjHybridPlannerList(projHybridPlannerList);
            productionOnTimeVO.setChapterStageList(chapterStageList);
            productionOnTimeVO.setChapterDueDateList(chapterDueDateList);
            productionOnTimeVO.setChapterActualDateList(chapterActualDateList);
            productionOnTimeVO.setDayDiffList(dayDiffList);
        } catch (SQLException sqle) {
            System.out.println("SQLException ProductionOnTimeDAO - getProductionOnTime() : "+sqle);
        } catch (NullPointerException npe) {
            System.out.println("NullPointerException : ProductionOnTimeDAO - getProductionOnTime() : "+npe);
        } finally {
            try {
                preStmt.close();
                rs.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException ProductionOnTimeDAO - getProductionOnTime() : "+sqle);
            } catch (NullPointerException npe) {
                System.out.println("NullPointerException : ProductionOnTimeDAO - getProductionOnTime() : "+npe);
            }
        }
        return productionOnTimeVO;
    }
}
