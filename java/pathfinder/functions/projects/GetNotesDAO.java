/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

import connection.DBconnection;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Pattany
 */
public class GetNotesDAO {

    public List getNotesForProjectId(GetNotesVO getNotesVO) {

        DBconnection dbconnection = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List getNotesList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();
            String projIdParam = getNotesVO.getProjId();

            String get_notes_query = "SELECT note_id, created_by, proj_id, content, created_at FROM notes WHERE proj_id='" + projIdParam + "' ";

            rs = st.executeQuery(get_notes_query);

            while (rs.next()) {
                GetNotesVO getNotes = new GetNotesVO();

                if (rs.getString(1) != null) {
                    getNotes.setNotesId(rs.getString(1));
                } else {
                    getNotes.setNotesId("");
                }

                if (rs.getString(2)  != null) {
                    getNotes.setEmpId(rs.getString(2));
                } else {
                    getNotes.setEmpId("");
                }

                if (rs.getString(3) != null) {
                    getNotes.setProjId(rs.getString(3));
                } else {
                    getNotes.setProjId("");
                }

                if (rs.getString(4) != null) {
                    getNotes.setNotes(rs.getString(4));
                } else {
                    getNotes.setNotes("");
                }

                if (rs.getString(5) != null) {
                    getNotes.setDate(rs.getString(5));
                } else {
                    getNotes.setDate("");
                }

                getNotesList.add(getNotes);

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

        return getNotesList;
    }

}
