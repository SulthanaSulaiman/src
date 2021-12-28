/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.projects;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Raghuramanm
 */
public class ProjectsReportDAO {

    public ProjectsReportVO getProjectsReport (ProjectsReportVO projectsReportVO){
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        connection.DBconnection dbCon = new connection.DBconnection();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        String sql_select = "";
        String sql_from = "";
        String sql_where = "";

        List projID = new ArrayList();
        List projName = new ArrayList();
        List company = new ArrayList();
        List division = new ArrayList();
        List contactsName = new ArrayList();
        List buyerName = new ArrayList();
        List projPrinterName = new ArrayList();
        List salesPersonName = new ArrayList();
        List projCategory = new ArrayList();
        List projDiscipline = new ArrayList();
        List projWorkflow = new ArrayList();
        List status = new ArrayList();
        List priorityValue = new ArrayList();
        List projBookTitle = new ArrayList();
        List Edition = new ArrayList();
        List color = new ArrayList();
        List trimSize = new ArrayList();
        List copyrightYear = new ArrayList();
        List mssPages = new ArrayList();
        List estimatedPages = new ArrayList();
        List actualPages = new ArrayList();
        List isbn13 = new ArrayList();
        List isbn10 = new ArrayList();
        List facilityName = new ArrayList();
        List projLevel = new ArrayList();
        List empName = new ArrayList();
        List projectdPrinterDate = new ArrayList();
        List estimatedSentDate = new ArrayList();
        List hybridPlannerEmpName = new ArrayList();
        List customerPO = new ArrayList();
        List actualShipDate = new ArrayList();
        List noOfChapters = new ArrayList();
        List mssFormat = new ArrayList();
        List fstPresent = new ArrayList();
        List xmlTypeName = new ArrayList();
        try{

            con = dbCon.getSampleProperty();
            stmt = con.createStatement();

            sql_select = " SELECT IFNULL(p.proj_id,''), IFNULL(p.proj_name,''), IFNULL(c.company,''), IFNULL(d.company,''), trim(CONCAT(IFNULL(cc.firstname,''),' ',IFNULL(cc.surname,''))), "
                        + " trim(CONCAT(IFNULL(b.firstname,''),' ',IFNULL(b.surname,''))), trim(CONCAT(IFNULL(pr.firstname,''),' ',IFNULL(pr.surname,''))), "
                        + " IFNULL(pc.proj_category,''), IFNULL(pd.proj_discipline,''), IFNULL(pwm.workflow_name,''), IFNULL(st.status,''), trim(CONCAT(IFNULL(s.firstname,''),' ',IFNULL(s.surname,''))), "
                        + " IFNULL(pty.priority_value,''), IFNULL(p.proj_bktitle,''), IFNULL(p.edition,''), IFNULL(pcl.color,''), IFNULL(p.trim_size,''), IFNULL(p.copyright_year,''), IFNULL(p.mss_pages,''), IFNULL(p.estimated_pages,''), IFNULL(p.actual_pages,''), "
                        + " IFNULL(p.proj_isbn_10,''), IFNULL(p.proj_isbn_13,''), IFNULL(f.facility_name,''), IFNULL(pl.proj_level,''), IFNULL(pnr.emp_name,''), IFNULL(p.projected_printer_date,''), IFNULL(p.est_sent_date,''), IFNULL(hpnr.emp_name,''), "
                        + " IFNULL(p.customer_po,''), IFNULL(p.act_ship_date,''), IFNULL(p.no_of_chapters,''), IFNULL(p.mss_format,''), CASE WHEN (p.fst_present=0) THEN 'Yes' ELSE 'No' END, IFNULL(pxml.type_name,'')";

            sql_from = " FROM projects p LEFT JOIN contacts c ON p.client_name=c.contact_id left join contacts d on p.division_id=d.contact_id "
                    + "left join contacts cc on p.contact_id=cc.contact_id left join contacts b on p.buyer_id=b.contact_id "
                    + " LEFT JOIN contacts pr ON p.proj_printer=pr.contact_id LEFT JOIN proj_category pc ON p.projcategory_id=pc.projcategory_id "
                    + " LEFT JOIN proj_discipline pd ON p.projdiscipline_id=pd.projdiscipline_id LEFT JOIN project_workflow_master pwm ON p.proj_workflow=pwm.workflow_id "
                    + " LEFT JOIN STATUS st ON p.project_status=st.status_id LEFT JOIN contacts s ON p.salesperson_id=s.contact_id LEFT JOIN proj_priority pty ON p.priority_id=pty.priority_id "
                    + " LEFT JOIN proj_color pcl ON p.color_id=pcl.color_id LEFT JOIN facility f ON p.facility_id=f.facility_id "
                    + " LEFT JOIN project_level pl ON p.proj_level=pl.level_id LEFT JOIN USER pnr ON p.planner=pnr.emp_id LEFT JOIN USER hpnr ON p.hybrid_planner=hpnr.emp_id "
                    + " LEFT JOIN proj_xml_prop pxml ON p.xml_prop=pxml.type_id ";

            sql_where = " WHERE st.status_id NOT IN (2,21) ";

            sql_select = sql_select+sql_from+sql_where;
            rs =  stmt.executeQuery(sql_select);
            
            while(rs.next()){

                projID.add(rs.getString(1));
                projName.add(rs.getString(2));
                company.add(splChar.decoding(rs.getString(3)));
                division.add(splChar.decoding(rs.getString(4)));
                contactsName.add(splChar.decoding(rs.getString(5)));
                buyerName.add(splChar.decoding(rs.getString(6)));
                projPrinterName.add(splChar.decoding(rs.getString(7)));
                projCategory.add(rs.getString(8));
                projDiscipline.add(rs.getString(9));
                projWorkflow.add(rs.getString(10));
                status.add(rs.getString(11));
                salesPersonName.add(splChar.decoding(rs.getString(12)));
                priorityValue.add(rs.getString(13));
                projBookTitle.add(rs.getString(14));
                Edition.add(rs.getString(15));
                color.add(rs.getString(16));
                trimSize.add(rs.getString(17));
                copyrightYear.add(rs.getString(18));
                mssPages.add(rs.getString(19));
                estimatedPages.add(rs.getString(20));
                actualPages.add(rs.getString(21));
                isbn10.add(rs.getString(22));
                isbn13.add(rs.getString(23));
                facilityName.add(rs.getString(24));
                projLevel.add(rs.getString(25));
                empName.add(rs.getString(26));
                projectdPrinterDate.add(rs.getString(27));
                estimatedSentDate.add(rs.getString(28));
                hybridPlannerEmpName.add(rs.getString(29));
                customerPO.add(rs.getString(30));
                actualShipDate.add(rs.getString(31));
                noOfChapters.add(rs.getString(32));
                mssFormat.add(rs.getString(33));
                fstPresent.add(rs.getString(34));
                xmlTypeName.add(rs.getString(35));
            }
            projectsReportVO.setProjID(projID);
            projectsReportVO.setProjName(projName);
            projectsReportVO.setCompany(company);
            projectsReportVO.setDivision(division);
            projectsReportVO.setContactsName(contactsName);
            projectsReportVO.setBuyerName(buyerName);
            projectsReportVO.setProjPrinterName(projPrinterName);
            projectsReportVO.setProjCategory(projCategory);
            projectsReportVO.setProjDiscipline(projDiscipline);
            projectsReportVO.setProjWorkflow(projWorkflow);
            projectsReportVO.setStatus(status);
            projectsReportVO.setSalesPersonName(salesPersonName);
            projectsReportVO.setPriorityValue(priorityValue);
            projectsReportVO.setProjBookTitle(projBookTitle);
            projectsReportVO.setEdition(Edition);
            projectsReportVO.setColor(color);
            projectsReportVO.setTrimSize(trimSize);
            projectsReportVO.setCopyrightYear(copyrightYear);
            projectsReportVO.setMssPages(mssPages);
            projectsReportVO.setEstimatedPages(estimatedPages);
            projectsReportVO.setActualPages(actualPages);
            projectsReportVO.setIsbn10(isbn10);
            projectsReportVO.setIsbn13(isbn13);
            projectsReportVO.setFacilityName(facilityName);
            projectsReportVO.setProjLevel(projLevel);
            projectsReportVO.setEmpName(empName);
            projectsReportVO.setProjectdPrinterDate(projectdPrinterDate);
            projectsReportVO.setEstimatedSentDate(estimatedSentDate);
            projectsReportVO.setHybridPlannerEmpName(hybridPlannerEmpName);
            projectsReportVO.setCustomerPO(customerPO);
            projectsReportVO.setActualShipDate(actualShipDate);
            projectsReportVO.setNoOfChapters(noOfChapters);
            projectsReportVO.setMssFormat(mssFormat);
            projectsReportVO.setFstPresent(fstPresent);
            projectsReportVO.setXmlTypeName(xmlTypeName);
        } catch (SQLException e) {
            System.out.println("SQLException : ProjectsReportDAO - ProjectsReportDAO()" + e);
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : ProjectsReportDAO - ProjectsReportDAO()" + ex);
        }
    }
        return projectsReportVO;
    }
}
