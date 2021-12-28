
package pathfinder.functions.projects;

import connection.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Aravindhanj
 */
public class ProjectsBySalesPersonDAO {

    public ProjectsBySalesPersonVO getProjectsBySalesPerson(ProjectsBySalesPersonVO projectsBySalesmanVO) {

        ArrayList clientName = new ArrayList();
        ArrayList projId = new ArrayList();
        ArrayList projName = new ArrayList();
        ArrayList projCategory = new ArrayList();
        ArrayList estimatedPageCount = new ArrayList();
        ArrayList estimatedAmount = new ArrayList();
        ArrayList invoicedPageCount = new ArrayList();
        ArrayList invoicedAmount = new ArrayList();
        ArrayList proInvoicedAmount = new ArrayList();
        ArrayList projectStatus = new ArrayList();
        ArrayList projectCreatedDate = new ArrayList();

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        String projLogFromDate = projectsBySalesmanVO.getProjLogFromDate();
        String projLogToDate = projectsBySalesmanVO.getProjLogToDate();
        String salesmanId = projectsBySalesmanVO.getSalesmanId();
        String categoryId = projectsBySalesmanVO.getCategoryId();
        String clientId = projectsBySalesmanVO.getClientId();

        DBconnection dbconnection = new DBconnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String queryWhere = "";
        String queryString = "";

        try {

            connection = dbconnection.getSampleProperty();

            /*
            queryString = "SELECT cl.company, p.proj_id, p.proj_name, SUM(pb.cast_off_pages), SUM(pb.total_pages), "
                    + " et.est_value, (SELECT SUM(invoice_value) FROM invoices WHERE proj_id=p.proj_id), st.status "
                    + " FROM projects p "
                    + " LEFT JOIN contacts ct ON p.salesperson_id=ct.contact_id "
                    + " LEFT JOIN contacts cl ON p.client_name=cl.contact_id "
                    + " LEFT JOIN project_bookmap pb ON pb.proj_id=p.proj_id "
                    + " LEFT JOIN STATUS st ON p.project_status=st.status "
                    + " LEFT JOIN estimate et ON et.proj_id=p.proj_id ";
*/
            queryString = "SELECT cl.company, p.proj_id, p.proj_name, p.estimated_pages, "
                    //+ "SUM(pb.cast_off_pages), "
                    + "SUM(pb.total_pages), et.est_value, st.status,"
                    + "(SELECT SUM(invoice_value) FROM invoices WHERE proj_id=p.proj_id) as inv,"
                    + "(SELECT "
                    + "	case when (MIN(part_invoice_flag)='0' and Max(part_invoice_flag)='2') "
                    + "		then "
                    + "			sum(case when (part_invoice_flag='2') then invoice_value end) "
                    + "	end "
                    + " FROM invoices WHERE proj_id=p.proj_id) AS prt, "
                    + " pc.proj_category, DATE_FORMAT(p.proj_date,'%d-%b-%Y') "
                    + "FROM projects p "
                    + "LEFT JOIN contacts ct ON p.salesperson_id=ct.contact_id "
                    + "LEFT JOIN proj_category pc ON pc.projcategory_id=p.projcategory_id "
                    + "LEFT JOIN contacts c ON c.contact_id=p.client_name "
                    + "LEFT JOIN contacts cl ON p.client_name=cl.contact_id "
                    + "LEFT JOIN project_bookmap pb ON pb.proj_id=p.proj_id "
                    + "LEFT JOIN STATUS st ON p.project_status=st.status_id "
                    + "LEFT JOIN estimate et ON et.proj_id=p.proj_id ";

            if(!salesmanId.equals("")) {
                if(queryWhere.equals("")) { queryWhere += " WHERE "; } else { queryWhere += " AND "; }
                queryWhere += " ct.contact_id='" + salesmanId + "' ";
            }

            if(!categoryId.equals("")) {
                if(queryWhere.equals("")) { queryWhere += " WHERE "; } else { queryWhere += " AND "; }
                queryWhere += " p.projcategory_id='" + categoryId + "' ";
            }

            if(!clientId.equals("")) {
                if(queryWhere.equals("")) { queryWhere += " WHERE "; } else { queryWhere += " AND "; }
                queryWhere += " c.contact_id='" + clientId + "' ";
            }

            if(!projLogFromDate.equals("") && !projLogToDate.equals("")) {
                if(!queryWhere.equals("")) { queryWhere += " AND "; }
                queryWhere += " p.proj_date BETWEEN date('" + projLogFromDate + "') AND date('" + projLogToDate + "') ";
            }

            queryString += queryWhere + " GROUP BY p.proj_id";

            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                if (resultSet.getString(1) != null) {
                    clientName.add(splChar.decoding(resultSet.getString(1)));
                } else {
                    clientName.add("");
                }

                if (resultSet.getString(2) != null) {
                    projId.add(resultSet.getString(2));
                } else {
                    projId.add("");
                }

                if (resultSet.getString(3) != null) {
                    projName.add(resultSet.getString(3));
                } else {
                    projName.add("");
                }

                if (resultSet.getString(4) != null) {
                    estimatedPageCount.add(resultSet.getString(4));
                } else {
                    estimatedPageCount.add("");
                }

                if (resultSet.getString(5) != null) {
                    invoicedPageCount.add(resultSet.getString(5));
                } else {
                    invoicedPageCount.add("");
                }

                if (resultSet.getString(6) != null) {
                    estimatedAmount.add(resultSet.getString(6));
                } else {
                    estimatedAmount.add("");
                }

                if (resultSet.getString(7) != null) {
                    projectStatus.add(resultSet.getString(7));
                } else {
                    projectStatus.add("");
                }

                if (resultSet.getString(8) != null) {
                    invoicedAmount.add(resultSet.getString(8));
                } else {
                    invoicedAmount.add("");
                }

                if (resultSet.getString(9) != null) {
                    proInvoicedAmount.add(resultSet.getString(9));
                } else {
                    proInvoicedAmount.add("");
                }

                if (resultSet.getString(10) != null) {
                    projCategory.add(resultSet.getString(10));
                } else {
                    projCategory.add("");
                }

                if (resultSet.getString(11) != null) {
                    projectCreatedDate.add(resultSet.getString(11));
                } else {
                    projectCreatedDate.add("");
                }
            }

            projectsBySalesmanVO.setProjId(projId);
            projectsBySalesmanVO.setProjName(projName);
            projectsBySalesmanVO.setClientName(clientName);
            projectsBySalesmanVO.setEstimatedPageCount(estimatedPageCount);
            projectsBySalesmanVO.setEstimatedAmount(estimatedAmount);
            projectsBySalesmanVO.setInvoicedPageCount(invoicedPageCount);
            projectsBySalesmanVO.setInvoicedAmount(invoicedAmount);
            projectsBySalesmanVO.setProInvoicedAmount(proInvoicedAmount);
            projectsBySalesmanVO.setProjectStatus(projectStatus);
            projectsBySalesmanVO.setProjCategory(projCategory);
            projectsBySalesmanVO.setProjectCreatedDate(projectCreatedDate);

        } catch (SQLException sqle) {
            System.out.println("SQLException : ProjectsBySalesPersonDAO - getProjectsBySalesPerson() : " + sqle);
        } catch (NullPointerException npe) {
            System.out.println("NullPointerException : ProjectsBySalesPersonDAO - getProjectsBySalesPerson() : " + npe);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException : ProjectsBySalesPersonDAO - getProjectsBySalesPerson() : " + sqle);
            } catch (NullPointerException npe) {
                System.out.println("NullPointerException : ProjectsBySalesPersonDAO - getProjectsBySalesPerson() : " + npe);
            }
        }
        return projectsBySalesmanVO;
    }

}