/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

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
public class JobInfoSheetDAO {

    public JobInfoSheetVO insertJISDetails(JobInfoSheetVO jobInfoSheetVO) {
        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        PreparedStatement prepStmt = null;

        String projId = jobInfoSheetVO.getProjId();
        String platform = jobInfoSheetVO.getPlatform();
        String linesPerPage = jobInfoSheetVO.getLinesPerPage();
        String columnsPerPage = jobInfoSheetVO.getColumnsPerPage();
        String typeface = jobInfoSheetVO.getTypeface();
        String sTypeFace = jobInfoSheetVO.getsTypeFace();
        String pointSize = jobInfoSheetVO.getPointSize();
        String typeOfDesign = jobInfoSheetVO.getTypeOfDesign();
        String slAllowed = jobInfoSheetVO.getSlAllowed();
        String partStart = jobInfoSheetVO.getPartStart();
        String sectionStart = jobInfoSheetVO.getSectionStart();
        String chapterStart = jobInfoSheetVO.getChapterStart();
        String depthAlignment = jobInfoSheetVO.getDepthAlignment();
        String rhStyle = jobInfoSheetVO.getRhStyle();
        String blankAllowed = jobInfoSheetVO.getBlankAllowed();
        String namingConv = jobInfoSheetVO.getNamingConv();
        String coFolio = jobInfoSheetVO.getCoFolio();
        String designApprovedAt = jobInfoSheetVO.getDesignApprovedAt();
        String estHours = jobInfoSheetVO.getEstHours();

        int result = 0;

        try {
            con = dbconnection.getSampleProperty();
            prepStmt = con.prepareStatement("insert into project_jis "
                    + "	(proj_id, platform_id, lines_per_page, columns_per_page, type_face, sec_type_face, point_size, design_type, sl_allowed, part_start, "
                    + "section_start, chapter_start, depth_align, rh_style, blank_allowed, naming_conv, co_folio, design_approved_date, est_hours) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            prepStmt.setString(1, projId);
            prepStmt.setString(2, platform);
            prepStmt.setString(3, linesPerPage);
            prepStmt.setString(4, columnsPerPage);
            prepStmt.setString(5, typeface);
            prepStmt.setString(6, sTypeFace);
            prepStmt.setString(7, pointSize);
            prepStmt.setString(8, typeOfDesign);
            prepStmt.setString(9, slAllowed);
            prepStmt.setString(10, partStart);
            prepStmt.setString(11, sectionStart);
            prepStmt.setString(12, chapterStart);
            prepStmt.setString(13, depthAlignment);
            prepStmt.setString(14, rhStyle);
            prepStmt.setString(15, blankAllowed);
            prepStmt.setString(16, namingConv);
            prepStmt.setString(17, coFolio);
            prepStmt.setString(18, designApprovedAt);
            prepStmt.setString(19, estHours);

            result = prepStmt.executeUpdate();
            jobInfoSheetVO.setResult(result);
            
        } catch (SQLException sql) {
            System.out.println("Exception in JobInfoSheetDAO :"+sql);
        } catch (NullPointerException npe) {
            System.out.println("Exception in JobInfoSheetDAO :"+npe);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Exception in JobInfoSheetlDAO :"+e);
            }
        }
        return jobInfoSheetVO;
    }

    public JobInfoSheetVO updateJISDetails(JobInfoSheetVO jobInfoSheetVO) {
        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        PreparedStatement prepStmt = null;

        String projId = jobInfoSheetVO.getProjId();
        String platform = jobInfoSheetVO.getPlatform();
        String linesPerPage = jobInfoSheetVO.getLinesPerPage();
        String columnsPerPage = jobInfoSheetVO.getColumnsPerPage();
        String typeface = jobInfoSheetVO.getTypeface();
        String sTypeFace = jobInfoSheetVO.getsTypeFace();
        String pointSize = jobInfoSheetVO.getPointSize();
        String typeOfDesign = jobInfoSheetVO.getTypeOfDesign();
        String slAllowed = jobInfoSheetVO.getSlAllowed();
        String partStart = jobInfoSheetVO.getPartStart();
        String sectionStart = jobInfoSheetVO.getSectionStart();
        String chapterStart = jobInfoSheetVO.getChapterStart();
        String depthAlignment = jobInfoSheetVO.getDepthAlignment();
        String rhStyle = jobInfoSheetVO.getRhStyle();
        String blankAllowed = jobInfoSheetVO.getBlankAllowed();
        String namingConv = jobInfoSheetVO.getNamingConv();
        String coFolio = jobInfoSheetVO.getCoFolio();
        String designApprovedAt = jobInfoSheetVO.getDesignApprovedAt();
        String estHours = jobInfoSheetVO.getEstHours();
        int result = 0;

        try {
            con = dbconnection.getSampleProperty();
            prepStmt = con.prepareStatement("update project_jis set "
                    + "	platform_id = ? , "
                    + "	lines_per_page = ? , "
                    + "	columns_per_page = ? , "
                    + " type_face = ? , "
                    + "	sec_type_face = ? , "
                    + "	point_size = ? , "
                    + "	design_type = ? , "
                    + "	sl_allowed = ? , "
                    + "	part_start = ? , "
                    + "	section_start = ? , "
                    + "	chapter_start = ? , "
                    + "	depth_align = ? , "
                    + "	depth_align = ? , "
                    + "	rh_style = ? , "
                    + "	blank_allowed = ? , "
                    + "	naming_conv = ? , "
                    + "	co_folio = ? , "
                    + "	design_approved_date = ? , "
                    + "	est_hours = ?"
                    + " where proj_id = ?");


            prepStmt.setString(1, platform);
            prepStmt.setString(2, linesPerPage);
            prepStmt.setString(3, columnsPerPage);
            prepStmt.setString(4, typeface);
            prepStmt.setString(5, sTypeFace);
            prepStmt.setString(6, pointSize);
            prepStmt.setString(7, typeOfDesign);
            prepStmt.setString(8, slAllowed);
            prepStmt.setString(9, partStart);
            prepStmt.setString(10, sectionStart);
            prepStmt.setString(11, chapterStart);
            prepStmt.setString(12, depthAlignment);
            prepStmt.setString(13, rhStyle);
            prepStmt.setString(14, blankAllowed);
            prepStmt.setString(15, namingConv);
            prepStmt.setString(16, coFolio);
            prepStmt.setString(17, designApprovedAt);
            prepStmt.setString(18, estHours);
            prepStmt.setString(19, projId);

            result = prepStmt.executeUpdate();
            jobInfoSheetVO.setResult(result);

        } catch (SQLException sql) {
            System.out.println("Exception in JobInfoSheetlDAO :"+sql);
        } catch (NullPointerException npe) {
            System.out.println("Exception in JobInfoSheetlDAO :"+npe);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Exception in JobInfoSheetlDAO :"+e);
            }
        }
        return jobInfoSheetVO;
    }

    public JobInfoSheetVO getJISDetails(JobInfoSheetVO jobInfoSheetVO) {
        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        String projId = jobInfoSheetVO.getProjId();
        String jisId = "";
        String platform = "";
        String linesPerPage = "";
        String columnsPerPage = "";
        String typeface = "";
        String sTypeFace = "";
        String pointSize = "";
        String typeOfDesign = "";
        String slAllowed = "";
        String partStart = "";
        String sectionStart = "";
        String chapterStart = "";
        String depthAlignment = "";
        String rhStyle = "";
        String blankAllowed = "";
        String namingConv = "";
        String coFolio = "";
        String designApprovedAt = "";
        String estHours = "";
        int result = 0;

        try {
            con = dbconnection.getSampleProperty();
            prepStmt = con.prepareStatement("SELECT jis_id, platform_id, lines_per_page, columns_per_page, type_face, sec_type_face, point_size, "
                    + " design_type, sl_allowed, part_start, section_start, chapter_start, depth_align, rh_style, blank_allowed, naming_conv, co_folio, "
                    + "	DATE_FORMAT(design_approved_date, \"%Y-%m-%d\"), est_hours	FROM project_jis where proj_id=?");
            prepStmt.setString(1, projId);
            resultSet = prepStmt.executeQuery();
            while(resultSet.next()) {
                jisId = resultSet.getString(1) != null ? resultSet.getString(1) : "";
                platform = resultSet.getString(2) != null ? resultSet.getString(2) : "";
                linesPerPage = resultSet.getString(3) != null ? resultSet.getString(3) : "";
                columnsPerPage = resultSet.getString(4) != null ? resultSet.getString(4) : "";
                typeface = resultSet.getString(5) != null ? resultSet.getString(5) : "";
                sTypeFace = resultSet.getString(6) != null ? resultSet.getString(6) : "";
                pointSize = resultSet.getString(7) != null ? resultSet.getString(7) : "";
                typeOfDesign = resultSet.getString(8) != null ? resultSet.getString(8) : "";
                slAllowed = resultSet.getString(9) != null ? resultSet.getString(9) : "";
                partStart = resultSet.getString(10) != null ? resultSet.getString(10) : "";
                sectionStart = resultSet.getString(11) != null ? resultSet.getString(11) : "";
                chapterStart = resultSet.getString(12) != null ? resultSet.getString(12) : "";
                depthAlignment = resultSet.getString(13) != null ? resultSet.getString(13) : "";
                rhStyle = resultSet.getString(14) != null ? resultSet.getString(14) : "";
                blankAllowed = resultSet.getString(15) != null ? resultSet.getString(15) : "";
                namingConv = resultSet.getString(16) != null ? resultSet.getString(16) : "";
                coFolio = resultSet.getString(17) != null ? resultSet.getString(17) : "";
                designApprovedAt = resultSet.getString(18) != null ? resultSet.getString(18) : "";
                estHours = resultSet.getString(19) != null ? resultSet.getString(19) : "";
            }
            jobInfoSheetVO.setJobInfoId(jisId);
            jobInfoSheetVO.setPlatform(platform);
            jobInfoSheetVO.setLinesPerPage(linesPerPage);
            jobInfoSheetVO.setColumnsPerPage(columnsPerPage);
            jobInfoSheetVO.setTypeface(typeface);
            jobInfoSheetVO.setsTypeFace(sTypeFace);
            jobInfoSheetVO.setPointSize(pointSize);
            jobInfoSheetVO.setTypeOfDesign(typeOfDesign);
            jobInfoSheetVO.setSlAllowed(slAllowed);
            jobInfoSheetVO.setPartStart(partStart);
            jobInfoSheetVO.setSectionStart(sectionStart);
            jobInfoSheetVO.setChapterStart(chapterStart);
            jobInfoSheetVO.setDepthAlignment(depthAlignment);
            jobInfoSheetVO.setRhStyle(rhStyle);
            jobInfoSheetVO.setBlankAllowed(blankAllowed);
            jobInfoSheetVO.setNamingConv(namingConv);
            jobInfoSheetVO.setCoFolio(coFolio);
            jobInfoSheetVO.setDesignApprovedAt(designApprovedAt);
            jobInfoSheetVO.setEstHours(estHours);
        } catch (SQLException sql) {
            System.out.println("Exception in JobInfoSheetlDAO :"+sql);
        } catch (NullPointerException npe) {
            System.out.println("Exception in JobInfoSheetlDAO :"+npe);
        } finally {
            try {
                con.close();
                prepStmt.close();
                resultSet.close();
            } catch (Exception e) {
                System.out.println("Exception in JobInfoSheetDAO :"+e);
            }
        }
        return jobInfoSheetVO;
    }

    public JobInfoSheetVO getJISGroupDetails() {
        JobInfoSheetVO jobInfoSheetVO = new JobInfoSheetVO();
        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        List jisGroupId = new ArrayList();
        List jisGroupName = new ArrayList();
        int result = 0;

        try {
            con = dbconnection.getSampleProperty();
            prepStmt = con.prepareStatement("SELECT project_jis_group_id, jis_group_name FROM project_jis_group ");
            resultSet = prepStmt.executeQuery();
            while(resultSet.next()) {
                jisGroupId.add(resultSet.getString(1) != null ? resultSet.getString(1) : "");
                jisGroupName.add(resultSet.getString(2) != null ? resultSet.getString(2) : "");
            }
            jobInfoSheetVO.setJisGroupIdList(jisGroupId);
            jobInfoSheetVO.setJisGroupNameList(jisGroupName);
        } catch (SQLException sql) {
            System.out.println("Exception in JobInfoSheetDAO :"+sql);
        } catch (NullPointerException npe) {
            System.out.println("Exception in JobInfoSheetDAO :"+npe);
        } finally {
            try {
                con.close();
                prepStmt.close();
                resultSet.close();
            } catch (Exception e) {
                System.out.println("Exception in JobInfoSheetDAO :"+e);
            }
        }
        return jobInfoSheetVO;
    }

    public JobInfoSheetVO insertJISNote(JobInfoSheetVO jobInfoSheetVO) {
        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        PreparedStatement prepStmt = null;

        String projId = jobInfoSheetVO.getProjId();
        String empId = jobInfoSheetVO.getEmpId();
        String jisGroupId = jobInfoSheetVO.getJisGroupId();
        String noteContent = jobInfoSheetVO.getNoteContent();
        int result = 0;

        try {
            con = dbconnection.getSampleProperty();
            prepStmt = con.prepareStatement("INSERT INTO project_jis_note (proj_id, emp_id, project_jis_group_id, note_content, added_date, modified_date) "
                    + " VALUES(?, ?, ?, ?, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP())");
            prepStmt.setString(1, projId);
            prepStmt.setString(2, empId);
            prepStmt.setString(3, jisGroupId);
            prepStmt.setString(4, noteContent);

            result = prepStmt.executeUpdate();
            jobInfoSheetVO.setResult(result);

        } catch (SQLException sql) {
            System.out.println("Exception in JobInfoSheetDAO :"+sql);
        } catch (NullPointerException npe) {
            System.out.println("Exception in JobInfoSheetDAO :"+npe);
        } finally {
            try {
                con.close();
                prepStmt.close();
            } catch (Exception e) {
                System.out.println("Exception in JobInfoSheetlDAO :"+e);
            }
        }
        return jobInfoSheetVO;
    }

    public JobInfoSheetVO updateJISNote(JobInfoSheetVO jobInfoSheetVO) {
        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        PreparedStatement prepStmt = null;

        List noteIdList = new ArrayList();
        List noteGroupIdList = new ArrayList();
        List noteContentList = new ArrayList();

        noteIdList = jobInfoSheetVO.getNoteIdList();
        noteGroupIdList = jobInfoSheetVO.getNoteGroupIdList();
        noteContentList = jobInfoSheetVO.getNoteContentList();

        int result = 0;

        try {
            con = dbconnection.getSampleProperty();
            for(int i=0; i < noteIdList.size(); i++) {
                prepStmt = con.prepareStatement("UPDATE project_jis_note SET project_jis_group_id = ?, note_content = ?,  modified_date = CURRENT_TIMESTAMP() "
                        + "WHERE jis_note_id = ?");
                prepStmt.setString(1, noteGroupIdList.get(i).toString());
                prepStmt.setString(2, noteContentList.get(i).toString());
                prepStmt.setString(3, noteIdList.get(i).toString());

                result += prepStmt.executeUpdate();
            }
            if(result == noteIdList.size()) {
                jobInfoSheetVO.setResult(1);
            } else {
                jobInfoSheetVO.setResult(0);
            }
        } catch (SQLException sql) {
            System.out.println("Exception in JobInfoSheetDAO :"+sql);
        } catch (NullPointerException npe) {
            System.out.println("Exception in JobInfoSheetDAO :"+npe);
        } finally {
            try {
                con.close();
                prepStmt.close();
            } catch (Exception e) {
                System.out.println("Exception in JobInfoSheetlDAO :"+e);
            }
        }
        return jobInfoSheetVO;
    }

    public JobInfoSheetVO getJISNotes(JobInfoSheetVO jobInfoSheetVO) {
        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        List jisNoteIdList = new ArrayList();
        List jisNoteEmpIdList = new ArrayList();
        List jisNoteEmpNameList = new ArrayList();
        List jisNoteEmpDesigList = new ArrayList();
        List jisNoteGroupIdList = new ArrayList();
        List jisNoteGroupNameList = new ArrayList();
        List jisNoteCntList = new ArrayList();
        List jisNoteCreatedList = new ArrayList();

        try {
            String getProjId = jobInfoSheetVO.getProjId();
            con = dbconnection.getSampleProperty();
            prepStmt = con.prepareStatement("select j.jis_note_id, u.emp_id, u.emp_name, d.designation, g.project_jis_group_id, g.jis_group_name, "
                    + "j.note_content, DATE_FORMAT(j.added_date, \"%d/%m/%Y %H:%i:%s\") "
                    + " from project_jis_note j, user u, project_jis_group g , designation d "
                    + " where j.emp_id=u.emp_id and j.project_jis_group_id=g.project_jis_group_id "
                    + " and j.proj_id=? and u.desig_code=d.desig_code "
                    + " order by j.project_jis_group_id");
            prepStmt.setString(1, getProjId);
            resultSet = prepStmt.executeQuery();

            while(resultSet.next()) {
                jisNoteIdList.add(resultSet.getString(1) != null ? resultSet.getString(1) : "");
                jisNoteEmpIdList.add(resultSet.getString(2) != null ? resultSet.getString(2) : "");
                jisNoteEmpNameList.add(resultSet.getString(3) != null ? resultSet.getString(3) : "");
                jisNoteEmpDesigList.add(resultSet.getString(4) != null ? resultSet.getString(4) : "");
                jisNoteGroupIdList.add(resultSet.getString(5) != null ? resultSet.getString(5) : "");
                jisNoteGroupNameList.add(resultSet.getString(6) != null ? resultSet.getString(6) : "");
                jisNoteCntList.add(resultSet.getString(7) != null ? resultSet.getString(7) : "");
                jisNoteCreatedList.add(resultSet.getString(8) != null ? resultSet.getString(8) : "");
            }

            jobInfoSheetVO.setJisNoteIdList(jisNoteIdList);
            jobInfoSheetVO.setJisNoteEmpIdList(jisNoteEmpIdList);
            jobInfoSheetVO.setJisNoteEmpNameList(jisNoteEmpNameList);
            jobInfoSheetVO.setJisNoteEmpDesigList(jisNoteEmpDesigList);
            jobInfoSheetVO.setJisNoteGroupIdList(jisNoteGroupIdList);
            jobInfoSheetVO.setJisNoteGroupNameList(jisNoteGroupNameList);
            jobInfoSheetVO.setJisNoteCntList(jisNoteCntList);
            jobInfoSheetVO.setJisNoteCreatedList(jisNoteCreatedList);
        } catch (SQLException sql) {
            System.out.println("Exception in JobInfoSheetDAO :"+sql);
        } catch (NullPointerException npe) {
            System.out.println("Exception in JobInfoSheetDAO :"+npe);
        } finally {
            try {
                con.close();
                prepStmt.close();
                resultSet.close();
            } catch (Exception e) {
                System.out.println("Exception in JobInfoSheetDAO :"+e);
            }
        }
        return jobInfoSheetVO;
    }
    public JobInfoSheetVO getPlatformDetail() {
        JobInfoSheetVO jobInfoSheetVO = new JobInfoSheetVO();
        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        List platformCode = new ArrayList();
        List platformName = new ArrayList();
        
        try {
            con = dbconnection.getSampleProperty();
            prepStmt = con.prepareStatement("select platform_code,platform from project_platform");
            resultSet = prepStmt.executeQuery();

            while(resultSet.next()) {
                platformCode.add(resultSet.getString(1) != null ? resultSet.getString(1) : "");
                platformName.add(resultSet.getString(2) != null ? resultSet.getString(2) : "");
            }
            jobInfoSheetVO.setPlatformCode(platformCode);
            jobInfoSheetVO.setPlatformName(platformName);
        } catch (SQLException sql) {
            System.out.println("Exception in JobInfoSheetDAO :"+sql);
        } catch (NullPointerException npe) {
            System.out.println("Exception in JobInfoSheetDAO :"+npe);
        } finally {
            try {
                con.close();
                prepStmt.close();
                resultSet.close();
            } catch (Exception e) {
                System.out.println("Exception in JobInfoSheetDAO :"+e);
            }
        }
        return jobInfoSheetVO;
    }

    public JobInfoSheetVO getProjectDetailsForJIS(JobInfoSheetVO jobInfoSheetVO) {
        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        String projId = jobInfoSheetVO.getProjId();
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();

        String client = "";
        String division = "";
        String planner = "";
        String hybridPlanner = "";
        String projCategory = "";
        String discipline = "";
        String projPriority = "";
        String salesPerson = "";
        String projStatus = "";
        String copyRightYear = "";
        String noOfChapters = "";
        String mssPages = "";
        String estimatedPages = "";
        String isbn10 = "";
        String isbn13 = "";
        String projLevel = "";
        String projectedPrinterDate = "";
        String mssFormat = "";
        String fstPresent = "";
        String xmlProp = "";
        String trimSize = "";
        String color = "";
        String projName = "";
        String projTitle = "";
        String edition = "";

        try {
            con = dbconnection.getSampleProperty();
            prepStmt = con.prepareStatement("SELECT c.company AS CLIENT, (SELECT company FROM contacts WHERE contact_id=p.division_id) AS Division, "
                    + "(SELECT emp_name FROM USER WHERE emp_id=p.planner) AS Planner, (SELECT emp_name FROM USER WHERE emp_id=p.hybrid_planner) AS Hybrid_Planner, "
                    + "(SELECT proj_category FROM proj_category WHERE projcategory_id=p.projcategory_id) AS Proj_Category, "
                    + "(SELECT proj_discipline FROM proj_discipline WHERE projdiscipline_id=p.projdiscipline_id) AS Discipline, "
                    + "(SELECT priority_value FROM proj_priority WHERE priority_id=p.priority_id) AS Project_Priority, "
                    + "(SELECT CONCAT(firstname,' ',surname) FROM contacts WHERE contact_id=p.salesperson_id) AS Sales_Person, "
                    + "(SELECT STATUS FROM STATUS WHERE status_type='projects' AND status_id=p.project_status) AS Project_Status, "
                    + "p.copyright_year, p.no_of_chapters, p.mss_pages, p.estimated_pages, p.proj_isbn_10, p.proj_isbn_13, "
                    + "(SELECT proj_level FROM project_level WHERE level_id=p.priority_id) AS Project_Level, "
                    + "p.projected_printer_date, p.mss_format, p.fst_present, (SELECT type_name FROM proj_xml_prop WHERE type_id=p.xml_prop), p.trim_size, "
                    + "(SELECT color FROM proj_color WHERE color_id=p.color_id) AS color, "
                    + "p.proj_name, p.proj_bktitle, p.edition "
                    + "FROM projects p LEFT JOIN contacts c ON c.contact_id=p.client_name WHERE p.proj_id=?");
            prepStmt.setString(1, projId);
            resultSet = prepStmt.executeQuery();
            while(resultSet.next()) {
                client = resultSet.getString(1) != null ? splChar.decoding(resultSet.getString(1)) : "";
                division = resultSet.getString(2) != null ? splChar.decoding(resultSet.getString(2)) : "";
                planner = resultSet.getString(3) != null ? resultSet.getString(3) : "";
                hybridPlanner = resultSet.getString(4) != null ? resultSet.getString(4) : "";
                projCategory = resultSet.getString(5) != null ? resultSet.getString(5) : "";
                discipline = resultSet.getString(6) != null ? resultSet.getString(6) : "";
                projPriority = resultSet.getString(7) != null ? resultSet.getString(7) : "";
                salesPerson = resultSet.getString(8) != null ? splChar.decoding(resultSet.getString(8)) : "";
                projStatus = resultSet.getString(9) != null ? resultSet.getString(9) : "";
                copyRightYear = resultSet.getString(10) != null ? resultSet.getString(10) : "";
                noOfChapters = resultSet.getString(11) != null ? resultSet.getString(11) : "";
                mssPages = resultSet.getString(12) != null ? resultSet.getString(12) : "";
                estimatedPages = resultSet.getString(13) != null ? resultSet.getString(13) : "";
                isbn10 = resultSet.getString(14) != null ? resultSet.getString(14) : "";
                isbn13 = resultSet.getString(15) != null ? resultSet.getString(15) : "";
                projLevel = resultSet.getString(16) != null ? resultSet.getString(16) : "";
                projectedPrinterDate = resultSet.getString(17) != null ? resultSet.getString(17) : "";
                mssFormat = resultSet.getString(18) != null ? resultSet.getString(18) : "";
                fstPresent = resultSet.getString(19) != null ? resultSet.getString(19) : "";
                xmlProp = resultSet.getString(20) != null ? resultSet.getString(20) : "";
                trimSize = resultSet.getString(21) != null ? resultSet.getString(21) : "";
                color = resultSet.getString(22) != null ? resultSet.getString(22) : "";
                projName = resultSet.getString(23) != null ? resultSet.getString(23) : "";
                projTitle = resultSet.getString(24) != null ? resultSet.getString(24) : "";
                edition = resultSet.getString(25) != null ? resultSet.getString(25) : "";
            }
            jobInfoSheetVO.setClient(splChar.encoding(client));
            jobInfoSheetVO.setDivision(splChar.encoding(division));
            jobInfoSheetVO.setPlanner(planner);
            jobInfoSheetVO.setHybridPlanner(hybridPlanner);
            jobInfoSheetVO.setProjCategory(projCategory);
            jobInfoSheetVO.setDiscipline(discipline);
            jobInfoSheetVO.setProjPriority(projPriority);
            jobInfoSheetVO.setSalesPerson(splChar.encoding(salesPerson));
            jobInfoSheetVO.setProjStatus(projStatus);
            jobInfoSheetVO.setCopyRightYear(copyRightYear);
            jobInfoSheetVO.setNoOfChapters(noOfChapters);
            jobInfoSheetVO.setMssPages(mssPages);
            jobInfoSheetVO.setEstimatedPages(estimatedPages);
            jobInfoSheetVO.setIsbn10(isbn10);
            jobInfoSheetVO.setIsbn13(isbn13);
            jobInfoSheetVO.setProjLevel(projLevel);
            jobInfoSheetVO.setProjectedPrinterDate(projectedPrinterDate);
            jobInfoSheetVO.setMssFormat(mssFormat);
            jobInfoSheetVO.setFstPresent(fstPresent);
            jobInfoSheetVO.setXmlProp(xmlProp);
            jobInfoSheetVO.setTrimSize(trimSize);
            jobInfoSheetVO.setColor(color);
            jobInfoSheetVO.setProjName(projName);
            jobInfoSheetVO.setProjTitle(projTitle);
            jobInfoSheetVO.setEdition(edition);
        } catch (SQLException sql) {
            System.out.println("Exception in JobInfoSheetlDAO :"+sql);
        } catch (NullPointerException npe) {
            System.out.println("Exception in JobInfoSheetlDAO :"+npe);
        } finally {
            try {
                con.close();
                prepStmt.close();
                resultSet.close();
            } catch (Exception e) {
                System.out.println("Exception in JobInfoSheetDAO :"+e);
            }
        }
        return jobInfoSheetVO;
    }
}
