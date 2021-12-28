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
public class DashBoardDAO implements Serializable {

    DBconnection dbconnection = new DBconnection();
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private String projList = "";
    private String chapList = "";
    private String prodChapList = "";
    private String cellList = "";
    private String cellProjIDList = "";
    private String EstProjList = "";
    

    public List getProjList(DashBoardVO ProjDetLlst) {

        DashBoardVO VODash = null;

        List packProjList = new ArrayList();
        
        String deptCode = "";
        String toDayDate = "";
        

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            deptCode = ProjDetLlst.getMgrDeptCode();
            toDayDate = ProjDetLlst.getTodayDate();

            projList = " SELECT concat(g.company,'/',divi.company),b.proj_name,DATE_FORMAT(a.due_date,'%d-%b-%Y'),SUM(a.mss_count),c.stage,b.proj_id,a.stage,DATE(a.due_date),g.contact_id "
                            + " FROM chapter a, projects b, project_stage c,contacts g, contacts divi "
                            + " WHERE (a.ship_date IS NULL OR a.ship_date = '0000-00-00 00:00:00') AND c.stage_code=a.stage AND a.proj_id=b.proj_id AND "
                            + " b.client_name=g.contact_id AND b.division_id=divi.contact_id AND a.stage NOT IN ('INTART') AND b.project_status='1' AND a.chapter_deleted='0' AND "
                            + " a.due_date='" + toDayDate + "' AND b.dept_code = '" + deptCode + "' GROUP BY a.stage,a.proj_id,a.due_date ORDER BY a.due_date,g.company,a.stage ";

            rs = st.executeQuery(projList);

            while (rs.next()) {

                VODash = new DashBoardVO();

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
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return packProjList;
    }

    public List getChapList(DashBoardVO ChapDetLlst) {

        DashBoardVO VODash = null;

        List packChapList = new ArrayList();

        String projID = "";
        String stageCode = "";
        String dueDate = "";


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
                            + " and b.project_status='1' AND a.chapter_deleted='0' ORDER BY a.due_date ";

            rs = st.executeQuery(chapList);

            while (rs.next()) {

                VODash = new DashBoardVO();

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

    public List getCellInfo(DashBoardVO CellDetList) {

        DashBoardVO VODash = null;

        List packCellList = new ArrayList();

        String empID = "";
        
        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            empID = CellDetList.getEmpID();
            
            cellList = " SELECT ecm.emp_id,u.emp_name,ecm.dept_code,d.department,ecm.cell_code,c.cell_name "
                            + " FROM emp_cell_map ecm,cell c,USER u,department d "
                            + " WHERE u.emp_id=ecm.emp_id AND ecm.cell_code=c.cell_code AND ecm.dept_code=c.dept_code AND c.dept_code=d.dept_code AND u.emp_id='" + empID + "' ";

            rs = st.executeQuery(cellList);

            while (rs.next()) {

                VODash = new DashBoardVO();

                if (rs.getString(1) != null) {
                    VODash.setEmpID(rs.getString(1));
                } else {
                    VODash.setEmpID("");
                }

                if (rs.getString(5) != null) {
                    VODash.setCellCode(rs.getString(5));
                } else {
                    VODash.setCellCode("");
                }

                packCellList.add(VODash);
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

        return packCellList;
    }

    public List getCellProjID(DashBoardVO CellProjIDList) {

        DashBoardVO VODash = null;

        List packCellProjIDList = new ArrayList();

        String empID = "";
        String cellCode = "";

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            empID = CellProjIDList.getEmpID();
            cellCode = CellProjIDList.getCellCode();

            cellProjIDList = " SELECT ptm.proj_id,p.proj_name FROM project_team ptm,projects p,cell c,USER u "
                                + " WHERE (ptm.proj_id=p.proj_id AND p.project_status='1' AND ptm.cell_code=c.cell_code AND ptm.role_id IS NULL AND "
                                + " ptm.emp_id IS NULL AND ptm.cell_code='" + cellCode + "') OR (ptm.proj_id=p.proj_id AND p.project_status='1' AND ptm.emp_id=u.emp_id "
                                + " AND ptm.cell_code IS NULL AND ptm.emp_id='" + empID + "') GROUP BY ptm.proj_id ";

            rs = st.executeQuery(cellProjIDList);

            while (rs.next()) {

                VODash = new DashBoardVO();

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

                packCellProjIDList.add(VODash);
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

        return packCellProjIDList;
    }

    public List getProdChapList(DashBoardVO ProdChapDetLlst) {

        DashBoardVO VODash = null;

        List packProdChapList = new ArrayList();

        String prodprojID = "";
        String prodstageCode = "";
        String proddueDate = "";


        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            prodprojID = ProdChapDetLlst.getProdProjID();
            prodstageCode = ProdChapDetLlst.getProdStageCode();
            proddueDate = ProdChapDetLlst.getProdDueDate();

            prodChapList = " select a.chapter_no,a.chapter_id,a.chapter_process "
                            + " from chapter a, projects b, project_stage c "
                            + " where (a.ship_date is null or a.ship_date = '0000-00-00 00:00:00') and c.stage_code=a.stage and a.proj_id=b.proj_id "
                            + " and a.proj_id='" + prodprojID + "' and a.stage='" + prodstageCode + "' and date_format(a.due_date,'%d-%b-%Y')='" + proddueDate + "' "
                            + " and b.project_status='1' AND a.chapter_deleted='0' ORDER BY a.due_date ";

            rs = st.executeQuery(prodChapList);

            while (rs.next()) {

                VODash = new DashBoardVO();

                if (rs.getString(1) != null) {
                    VODash.setProdchapName(rs.getString(1));
                } else {
                    VODash.setProdchapName("");
                }

                if (rs.getString(2) != null) {
                    VODash.setProdchapID(rs.getString(2));
                } else {
                    VODash.setProdchapID("");
                }

                if (rs.getString(3) != null) {
                    VODash.setProdchapProcess(rs.getString(3));
                } else {
                    VODash.setProdchapProcess("");
                }

                packProdChapList.add(VODash);
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

        return packProdChapList;
    }

    public List getEstDashBoard(DashBoardVO EstProjDetLlst) {

        DashBoardVO VODash = null;

        List packEstDashBoardList = new ArrayList();
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            EstProjList = " SELECT CONCAT(g.company,'/',divi.company),b.proj_name,b.proj_id,g.contact_id,b.project_status,s.status "
                            + " FROM projects b,contacts g,contacts divi,status s "
                            + " WHERE b.client_name=g.contact_id AND b.division_id=divi.contact_id AND b.project_status = s.status_id "
                            + " AND (b.project_status='3' OR b.project_status='20') ORDER BY g.company ";

            rs = st.executeQuery(EstProjList);

            while (rs.next()) {

                VODash = new DashBoardVO();

                if (rs.getString(1) != null) {
                    VODash.setEstClientName(splChar.decoding(rs.getString(1)));
                } else {
                    VODash.setEstClientName("");
                }

                if (rs.getString(2) != null) {
                    VODash.setEstProjName(rs.getString(2));
                } else {
                    VODash.setEstProjName("");
                }

                if (rs.getString(3) != null) {
                    VODash.setEstProjID(rs.getString(3));
                } else {
                    VODash.setEstProjID("");
                }

                if (rs.getString(4) != null) {
                    VODash.setEstContactID(rs.getString(4));
                } else {
                    VODash.setEstContactID("");
                }

                if (rs.getString(5) != null) {
                    VODash.setEstProjStatusCode(rs.getString(5));
                } else {
                    VODash.setEstProjStatusCode("");
                }

                if (rs.getString(6) != null) {
                    VODash.setEstProjStatus(rs.getString(6));
                } else {
                    VODash.setEstProjStatus("");
                }

                packEstDashBoardList.add(VODash);
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

        return packEstDashBoardList;
    }
}
