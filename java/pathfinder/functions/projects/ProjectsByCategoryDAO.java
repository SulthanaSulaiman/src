/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Parameshwarant
 */
public class ProjectsByCategoryDAO {

    public ProjectsByCategoryVO getProjectByCategory(ProjectsByCategoryVO projectsByCategoryVO) {

        connection.DBconnection dbCon = new connection.DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "";

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        String categoryId = projectsByCategoryVO.getCategoryID();
        String fromDate = projectsByCategoryVO.getFromDate();
        String toDate = projectsByCategoryVO.getToDate();

        List projIDList = new ArrayList();
        List projNameList = new ArrayList();
        List clientNameList = new ArrayList();
        List projDateList = new ArrayList();
        List statusNameList = new ArrayList();
        try {
            con = dbCon.getSampleProperty();
            st = con.createStatement();
            query = " select p.proj_id, p.proj_name, cc.company AS client_name, p.proj_date, s.status, c.projcategory_id "
                    + " from status s , contacts cc, contacttype_map cm, contacts_type_master ct,  proj_category c RIGHT JOIN  projects p ON c.projcategory_id = p.projcategory_id "
                    + " where cc.contact_id = cm.contact_id and cm.type_id = ct.type_id "
                    + " and cc.contact_id = p.client_name and p.project_status = s.status_id ";
            if(categoryId.equals("none")) {
                query += " AND c.projcategory_id is null";
            } else if(categoryId.equals("0")) {
                query += " AND c.projcategory_id is not null";
            } else {
                query += " AND c.projcategory_id='" + categoryId + "'";
            }
            if (!fromDate.equals("") && !toDate.equals("")) {
                query += " AND p.proj_date BETWEEN DATE('" + fromDate + "') AND DATE('" + toDate + "')+1";
            }
            rs = st.executeQuery(query);

            while (rs.next()) {
                projIDList.add(rs.getString(1));
                projNameList.add(rs.getString(2));
                clientNameList.add(splChar.decoding(rs.getString(3)));
                projDateList.add(rs.getString(4));
                statusNameList.add(rs.getString(5));
            }
            projectsByCategoryVO.setProjIDList(projIDList);
            projectsByCategoryVO.setProjNameList(projNameList);
            projectsByCategoryVO.setClientNameList(clientNameList);
            projectsByCategoryVO.setProjDateList(projDateList);
            projectsByCategoryVO.setStatusNameList(statusNameList);
            
        } catch (SQLException e) {
            System.out.println("SQLException : ProjectByCategoryDAO - getProjectByCategory()" + e);
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : ProjectByCategoryDAO - getProjectByCategory()" + ex);
            }
        }
        return projectsByCategoryVO;
    }
}
