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
public class ActualEstimationDAO {

    public ActualEstimationVO ActualEstimationDetails(ActualEstimationVO actualEstimationVO) {

        List component = new ArrayList();
        List actual = new ArrayList();
        List componentIds = new ArrayList();
        String projId = "";

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String workflowId = "";
        List actualEstimation = new ArrayList();

        //Create a response object and set the response for the request.
        ChapterAddDetailsVO stageresponse = null;

        try {

            con = dbcon.getSampleProperty();
            projId = actualEstimationVO.getProjId();

            String actualEst = " SELECT DISTINCT(chapter_no),chapter_id,actual,proof_page FROM "
                    + "chapter WHERE proj_id='"+projId+"' AND chapter_deleted='0' AND stage='FP' AND ship_date IS NOT NULL GROUP BY chapter_no ORDER BY chapter_no";

            stmt = con.prepareStatement(actualEst);
            //System.out.println("Actual Estimation Query : " + actualEst);
            rs = stmt.executeQuery();

            actualEstimationVO = new ActualEstimationVO();

            while (rs.next()) {

                if (rs.getString(1) != null) {
                    component.add(rs.getString(1));
                } else {
                    component.add("");
                }

                if (rs.getString(2) != null) {
                    componentIds.add(rs.getString(2));
                } else {
                    componentIds.add("0");
                }

                if (rs.getString(3) != null) {
                    actual.add(rs.getString(3));
                } else {
                    if (rs.getString(4) != null) {
                        actual.add(rs.getString(4));
                    }
                    else {
                        actual.add("0");
                    }
                    //System.out.println("Est Val 1 : " + rs.getString(4)+"_"+ActualEstimationVO.getEstValue());
                }

                actualEstimation.add(actualEstimationVO);
            }
            ///System.out.println("component : "+component);
            ///System.out.println("actual : "+actual);
            ///System.out.println("componentId : "+componentIds);
            actualEstimationVO.setComponent(component);
            actualEstimationVO.setActual(actual);
            actualEstimationVO.setComponentId(componentIds);
            rs.close();
            stmt.close();

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
        return actualEstimationVO;
    }

    public void SetActualEstimationDetails(ActualEstimationVO actualEstimationVO) {

        List component = new ArrayList();
        List actual = new ArrayList();
        List componentId = new ArrayList();
        String projId = "";
        int count = 0;
        int result = 0;
        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rsl = null;
        String workflowId = "";
        List actualEstimation = new ArrayList();

        //Create a response object and set the response for the request.
        ChapterAddDetailsVO stageresponse = null;

        try {

            con = dbcon.getSampleProperty();
            //projId = actualEstimationVO.getProjId();
            projId = actualEstimationVO.getProjId();
            count = actualEstimationVO.getActualEstCount();
            actual = actualEstimationVO.getActual();
            componentId = actualEstimationVO.getComponentId();

//            System.out.println("projId --- : "+projId);
//            System.out.println("count --- : "+count);
//            System.out.println("actual --- : "+actual);

            for(int index=0; index < count; index++) {
                //System.out.println(index);
                String actualEst = "UPDATE chapter SET actual="+Integer.parseInt(actual.get(index).toString())+" WHERE chapter_id="+Integer.parseInt(componentId.get(index).toString())+" AND proj_id='"+projId+"'";
                stmt = con.prepareStatement(actualEst);
                //stmt.setInt(1, Integer.parseInt(actual.get(index).toString()));
                //stmt.setInt(2, Integer.parseInt(componentId.get(index).toString()));
                //stmt.setString(3, projId);

                //System.out.println("Actual Estimation Query : " + stmt.toString());
                result = stmt.executeUpdate(actualEst);
            }
            stmt.close();
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
    }
}
