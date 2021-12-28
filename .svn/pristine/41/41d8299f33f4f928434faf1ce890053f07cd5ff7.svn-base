package pathfinder.functions.projects.chaptersold;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 * This file contains methods to get the chapter details and plan details.
 * @author Thanu
 */
public class ChapterDetails_1 implements Serializable {

    /**
     * This function is used to get the chapter details for the given project id.
     *
     * @param chapRqst value object for {@link ChapterDetailsVO} <br>
     * @return <code> chapterDet </code> -  List of ChapterDetailsVO objects which consist of list of chapter details.
     *
     * @exception SQLException
     * @see ChapterDetailsVO
     */
    public List getChapterDetails(ChapterDetailsVO chapRqst) {

        Connection con = null;
        DBconnection dbcon = new DBconnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String projectId = "";
        List chapterDet = new ArrayList();

        //Create a response object and set the response for the request.
        ChapterDetailsVO response = null;

        try {

            con = dbcon.getSampleProperty();

            String chapterDetQry = " SELECT a.chapter_id,a.chapter_no,a.mss_count,a.ship_date,b.stage,a.added_date "
                    + " FROM chapter a,project_stage b "
                    + " WHERE a.proj_id=? AND a.stage = b.stage_code";

            projectId = chapRqst.getProjectId();
            stmt = con.prepareStatement(chapterDetQry);
            stmt.setString(1, projectId);
            System.out.println("chapterDetQry:"+chapterDetQry);

            rs = stmt.executeQuery();
            while (rs.next()) {
                response = new ChapterDetailsVO();
                response.setChapterId(rs.getString(1));
                response.setChapterName(rs.getString(2));
                response.setMssPages(rs.getString(3));

                if(rs.getString(4)!=null)
                  response.setShipDate(rs.getString(4));
                else
                  response.setShipDate("-");

                response.setStage(rs.getString(5));

                if(rs.getString(6)!=null)
                    response.setCreateDate(rs.getString(6));
                else
                    response.setCreateDate("-");

                chapterDet.add(response);

            }

        } catch (SQLException sqle) {
            System.out.println("SQLException in getChapterDetails:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in getChapterDetails:" + e);
        } finally {

            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing resultset:se.getMessage()");
                }

            }

            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection:se.getMessage()");
                }
            }

        }

        return chapterDet;

    }
}
