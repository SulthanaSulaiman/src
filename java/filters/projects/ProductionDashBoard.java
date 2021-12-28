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
public class ProductionDashBoard implements Serializable {

    DBconnection dbconnection = new DBconnection();
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List ProjID = new ArrayList();
    private List DispProjID = new ArrayList();

    private List clientName = new ArrayList();
    private List projName = new ArrayList();
    private List dispDue = new ArrayList();
    private List totalMS = new ArrayList();
    private List stageName = new ArrayList();
    private List projectID = new ArrayList();
    private List stageCode = new ArrayList();
    private List dueDate = new ArrayList();
    private List clientCode = new ArrayList();

    private String projList = "";
    private String projID = "";
    private String projVal = "";

    
    public void setProjID(List ProjID) {
        this.ProjID = ProjID;
        //System.out.println("ProjID: "+ProjID.size());
        //System.out.println("ProjID: "+ProjID);
    }

    public List getDispProjID() {
        return DispProjID;
    }

    public String getProjID() {
        return projID;
    }

    public List getClientCode() {
        return clientCode;
    }

    public List getClientName() {
        return clientName;
    }

    public List getDispDue() {
        return dispDue;
    }

    public List getDueDate() {
        return dueDate;
    }

    public List getProjName() {
        return projName;
    }

    public List getProjectID() {
        return projectID;
    }

    public List getStageCode() {
        return stageCode;
    }

    public List getStageName() {
        return stageName;
    }

    public List getTotalMS() {
        return totalMS;
    }
    

    public ProductionDashBoard() {
    }
    

    public void getProductionProj() { 

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            clientName.clear();
            projName.clear();
            dispDue.clear();
            totalMS.clear();
            stageName.clear();
            projectID.clear();
            stageCode.clear();
            dueDate.clear();
            clientCode.clear();
            
            for (int i=0; i < ProjID.size(); i++) {
                
            projID = ProjID.get(i).toString();
            DispProjID.add(projID);

            projList = " SELECT concat(g.company,'/',divi.company),b.proj_name,DATE_FORMAT(a.due_date,'%d-%b-%Y'),SUM(a.mss_count),c.stage,b.proj_id,a.stage,DATE(a.due_date),g.contact_id "
                            + " FROM chapter a, projects b, project_stage c,contacts g, contacts divi "
                            + " WHERE (a.ship_date IS NULL OR a.ship_date = '0000-00-00 00:00:00') AND c.stage_code=a.stage AND a.proj_id=b.proj_id AND "
                            + " b.client_name=g.contact_id AND b.division_id=divi.contact_id AND a.stage NOT IN ('INTART') AND b.project_status='1' AND a.chapter_deleted='0' AND "
                            + " b.proj_id='" + projID + "' GROUP BY a.stage,a.proj_id,a.due_date ORDER BY a.due_date ";

            rs = st.executeQuery(projList);
            
            while (rs.next()) {

                projVal = splChar.decoding(rs.getString(1));
                if(rs.wasNull()){
                    projVal = "";
                }
                clientName.add(projVal);

                projVal = rs.getString(2);
                if(rs.wasNull()){
                    projVal = "";
                }
                projName.add(projVal);

                projVal = rs.getString(3);
                if(rs.wasNull()){
                    projVal = "";
                }
                dispDue.add(projVal);

                projVal = rs.getString(4);
                if(rs.wasNull()){
                    projVal = "";
                }
                totalMS.add(projVal);

                projVal = rs.getString(5);
                if(rs.wasNull()){
                    projVal = "";
                }
                stageName.add(projVal);

                projVal = rs.getString(6);
                if(rs.wasNull()){
                    projVal = "";
                }
                projectID.add(projVal);

                projVal = rs.getString(7);
                if(rs.wasNull()){
                    projVal = "";
                }
                stageCode.add(projVal);

                projVal = rs.getString(8);
                if(rs.wasNull()){
                    projVal = "";
                }
                dueDate.add(projVal);

                projVal = rs.getString(9);
                if(rs.wasNull()){
                    projVal = "";
                }
                clientCode.add(projVal);
                
            }

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
    }
}
