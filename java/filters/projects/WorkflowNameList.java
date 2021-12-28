/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.projects;


import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramesh
 */
public class WorkflowNameList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List workFlowNameList = new ArrayList();
    private List workflowIdList = new ArrayList();
    private String testcode = "";
    private String testname = "";


    private String sql_select = "";
    private String sql_from = "";


    public void collectWorkflowList(){
        sql_select = "";
        sql_from = "";

        sql_select = "select workflow_id, workflow_name ";
        sql_from = "from project_workflow_master ";

        sql_select +=sql_from;


        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            workflowIdList.clear();
            workFlowNameList.clear();
            while(rs.next()){
		testcode = rs.getString(1);
                testname = rs.getString(2);
                workflowIdList.add(testcode);
                workFlowNameList.add(testname);
            }

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
            rs.close();
            st.close();
            con.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
        }

        }

    }

    public List getWorkflowId() {
        return workflowIdList;
    }

    public List getWorkflowName() {
        return workFlowNameList;
    }

}
