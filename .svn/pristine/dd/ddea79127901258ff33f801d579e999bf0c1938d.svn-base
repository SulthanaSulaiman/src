/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.projects;
import java.sql.*;
import java.util.*;
import connection.DBconnection;
/**
 *
 * @author Raghuramanm
 */
public class AwaitingForFinalsDAO {

    public AwaitingForFinalsVO getAwaitingProjReport(AwaitingForFinalsVO awaitingForFinalsVO){
        String project="";
        String client="";
        String dept="";
        List projectList = new ArrayList();
        List projectIdList = new ArrayList();
        List deptList = new ArrayList();
        List clientList = new ArrayList();
        List statusList = new ArrayList();
        List totalPageCountList = new ArrayList();
        try{
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();

            project = awaitingForFinalsVO.getProject();
            client = awaitingForFinalsVO.getClient();
            dept = awaitingForFinalsVO.getDept();
            
            String query = "SELECT p.proj_id,p.proj_name,d.department,con.company,s.status"
                            +" FROM projects p, department d, chapter c, contacts con, status s"
                            +" WHERE p.proj_id NOT IN (SELECT pr.proj_id FROM projects pr,chapter ch WHERE ch.stage IN ('PDF','DERQ') AND ch.proj_id=pr.proj_id) "
                            +" AND c.proj_id=p.proj_id AND d.dept_code=p.dept_code AND p.client_name=con.contact_id AND p.project_status=s.status_id"; 
                            
            if(!project.equals("")){
                query += " AND p.proj_id='"+project+"'";
            }
            if(!client.equals("")){
                query += " AND con.contact_id='"+client+"'";
            }
            if(!dept.equals("ALL")){
                query += " AND d.dept_code='"+dept+"'";
            }
            
            query += " GROUP BY p.proj_id";
            
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                if(rs.getString(1)!=null){
                    projectIdList.add(rs.getString(1));
                }else{
                    projectIdList.add("");
                }
                if(rs.getString(2)!=null){
                    projectList.add(rs.getString(2));
                }else{
                    projectList.add("");
                }
                if(rs.getString(3)!=null){
                    deptList.add(rs.getString(3));
                }else{
                    deptList.add("");
                }
                if(rs.getString(4)!=null){
                    clientList.add(rs.getString(4));
                }else{
                    clientList.add("");
                }
                if(rs.getString(5)!=null){
                    statusList.add(rs.getString(5));
                }else{
                    statusList.add("");
                }
            }
            String pageCountQuery ="";
            for(int idx=0;idx<projectIdList.size();idx++){
                pageCountQuery="Select sum(mss_page_count) from project_bookmap where proj_id='"+projectIdList.get(idx)+"'";
                rs = st.executeQuery(pageCountQuery);
                while(rs.next()){
                    if(rs.getString(1)!=null){
                        totalPageCountList.add(rs.getString(1));
                    }else{
                        totalPageCountList.add("");
                    }
                }
            }
            awaitingForFinalsVO.setProjectList(projectList);
            awaitingForFinalsVO.setDeptList(deptList);
            awaitingForFinalsVO.setClientList(clientList);
            awaitingForFinalsVO.setStatusList(statusList);
            awaitingForFinalsVO.setTotalPageCountList(totalPageCountList);
        }catch(Exception e){
            System.out.println("Exception in getAwaitingProjReport of AwaitingForFinalsDAO "+e);
        }
        
        return awaitingForFinalsVO;
    }
}
