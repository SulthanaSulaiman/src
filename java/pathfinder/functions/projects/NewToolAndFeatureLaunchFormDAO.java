/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

import java.sql.*;
import connection.DBconnection;
import java.util.*;
/**
 *
 * @author Parameshwarant
 */
public class NewToolAndFeatureLaunchFormDAO {

    private String queryToSaveMessage = "";
    private String queryToUpdateMessage = "";
    private String queryToGetMessage = "";
    private String queryToGetEditFlag = "";

    private String message_title = "";
    private String message_content= "";
    private String message_to= "";
    private String message_by= "";
    private String message_id= ""; 
    private String message_date= "";

    List message_id_list = new ArrayList();
    List message_date_list = new ArrayList();
    List message_title_list = new ArrayList();
    List message_content_list = new ArrayList();
    List message_to_list = new ArrayList();
    List message_by_list = new ArrayList();

    private int saveOrModifyStatus = 0;
    
    NewToolAndFeatureLaunchFormVO getLaunchInfo = new NewToolAndFeatureLaunchFormVO();
    
    

    Connection con = null;
    DBconnection conToDB = new DBconnection();

    public void saveOrModifyToolLaunchMessage(NewToolAndFeatureLaunchFormVO getLaunchInfo) {

        String messageTitle = getLaunchInfo.getMessageTitle();
        String messageContent = getLaunchInfo.getMessageContent();
        String messageTo = getLaunchInfo.getMessageTo();
        String messageBy = getLaunchInfo.getMessageBy();
        String messageId = getLaunchInfo.getMessageId();
        String saveOrModify = getLaunchInfo.getSaveOrModify();

        try {
            con=conToDB.getSampleProperty();
            Statement stToSaveOrModifyMessage=con.createStatement();
            Statement stToGetMaxMsgId=con.createStatement();
            if(saveOrModify.equals("saveNew")) {

                queryToSaveMessage = "INSERT INTO feature_launch_info(msg_title, msg_content, msg_to, msg_by, msg_date) "
                        + " VALUES ('"+messageTitle+"', '"+messageContent+"', '"+messageTo+"', '"+messageBy+"', CURRENT_DATE) ";
                //System.out.println(" queryToSaveMessage"+queryToSaveMessage);
                saveOrModifyStatus = stToSaveOrModifyMessage.executeUpdate(queryToSaveMessage);
                
                ResultSet rsToGetMaxMsgId=stToGetMaxMsgId.executeQuery("SELECT MAX(msg_id) from feature_launch_info");
                while(rsToGetMaxMsgId.next()){
                messageId = rsToGetMaxMsgId.getString(1);
                }
                rsToGetMaxMsgId.close();
            }
            
            if(saveOrModify.equals("modify")) {
                queryToUpdateMessage = " UPDATE feature_launch_info SET msg_title='"+messageTitle+"', msg_content='"+messageContent+"', "
                        + "msg_to='"+messageTo+"', msg_modify_by='"+messageBy+"', msg_modify_date=CURRENT_DATE WHERE msg_id = '"+messageId+"'";
                //System.out.println(" queryToUpdateMessage"+queryToUpdateMessage);
                saveOrModifyStatus = stToSaveOrModifyMessage.executeUpdate(queryToUpdateMessage);
            }
            
            getLaunchInfo.setSaveOrModifyStatus(saveOrModifyStatus);
            getLaunchInfo.setMessageId(messageId);
            stToGetMaxMsgId.close();
            stToSaveOrModifyMessage.close();
            con.close();
        }
        
            catch(SQLException e) {
                e.printStackTrace();

        }

    }

    public void collectLaunchMessage(NewToolAndFeatureLaunchFormVO getLaunchInfo) {
        
        message_id = "";
        message_title = "";
        message_content = "";
        message_to = "";
        message_by = "";
        message_date = "";

        

        String messageId = getLaunchInfo.getMessageId();
        String messageTo = getLaunchInfo.getMessageTo();
        String dashBoardViewFlag = getLaunchInfo.getDashBoardFlag();

        try {
            con=conToDB.getSampleProperty();
            Statement stToGetMessage=con.createStatement();
            ResultSet rsToGetMessage=null;

            if(!messageId.equals("")){
            queryToGetMessage = " SELECT a.msg_id, a.msg_title, a.msg_content, a.msg_to, b.emp_name, a.msg_date FROM feature_launch_info a, USER b WHERE msg_id = '"+messageId+"' AND a.msg_by=b.emp_id";

            rsToGetMessage = stToGetMessage.executeQuery(queryToGetMessage);
            while(rsToGetMessage.next()){
                message_id = rsToGetMessage.getString(1);
                message_title = rsToGetMessage.getString(2);
                message_content = rsToGetMessage.getString(3);
                message_to = rsToGetMessage.getString(4);
                message_by = rsToGetMessage.getString(5);
                message_date = rsToGetMessage.getString(6);
            }
            getLaunchInfo.setMessageTitle(message_title);
            getLaunchInfo.setMessageContent(message_content);
            getLaunchInfo.setMessageTo(message_to);
            getLaunchInfo.setMessageBy(message_by);
            getLaunchInfo.setMessageId(message_id);
            getLaunchInfo.setMessageDate(message_date);
            } else {

            message_id_list.clear();
            message_title_list.clear();
            message_date_list.clear();

            queryToGetMessage = " SELECT msg_id, msg_title, msg_content, msg_to, msg_by, msg_date FROM feature_launch_info ";
            if(!messageTo.equals("")){
                queryToGetMessage += " WHERE msg_to LIKE '%"+messageTo+"%' ";
            }
            if(dashBoardViewFlag.equals("1")){
                if(queryToGetMessage.contains("WHERE"))
                    queryToGetMessage += " AND msg_date=current_date ";
                if(!queryToGetMessage.contains("WHERE"))
                    queryToGetMessage += " WHERE msg_date=current_date ";
            }
            //System.out.println(" queryToGetEditFlag:\n "+queryToGetMessage);
            rsToGetMessage = stToGetMessage.executeQuery(queryToGetMessage+" ORDER BY msg_date DESC");
            while(rsToGetMessage.next()){
                message_id_list.add(rsToGetMessage.getString(1));
                message_title_list.add(rsToGetMessage.getString(2));
                message_content_list.add(rsToGetMessage.getString(3));
                message_to_list.add(rsToGetMessage.getString(4));
                message_by_list.add(rsToGetMessage.getString(5));
                message_date_list.add(rsToGetMessage.getString(6));
            }
            getLaunchInfo.setMessageIdList(message_id_list);
            getLaunchInfo.setMessageTitleList(message_title_list);
            getLaunchInfo.setMessageContentList(message_content_list);
            getLaunchInfo.setMessageDateList(message_date_list);
            getLaunchInfo.setMessageByList(message_by_list);
            getLaunchInfo.setMessageToList(message_to_list);
            }

            rsToGetMessage.close();
            stToGetMessage.close();
            con.close();
        }

            catch(SQLException e) {
                e.printStackTrace();

        }

    }

    public void checkToEditLaunchInfo(NewToolAndFeatureLaunchFormVO getLaunchInfo, String pageName, String empId){
         try {
            con=conToDB.getSampleProperty();
            Statement stToGetEditFlag=con.createStatement();

            queryToGetEditFlag="SELECT CASE WHEN b.pgprivilege_id=1 THEN 1 ELSE 0 END AS flagCheck FROM pagename a, pages b, groups c, USER d "
                    + " WHERE a.page_name='"+pageName+"' AND a.pages_id=b.pages_id AND b.group_id=c.group_id "
                    + " AND c.emp_id=d.emp_id AND c.emp_id='"+empId+"' ";

            ResultSet rsToGetEditFlag = stToGetEditFlag.executeQuery(queryToGetEditFlag);
            while(rsToGetEditFlag.next()){
                getLaunchInfo.setEditFlag(rsToGetEditFlag.getString(1));
                //System.out.println(queryToGetEditFlag+" queryToGetEditFlag:\n "+rsToGetEditFlag.getString(1));
            }
            rsToGetEditFlag.close();
            stToGetEditFlag.close();
            con.close();
        }
            catch(SQLException e) {
                e.printStackTrace();

        }

    }

    public String getMutipleDeptName(String deptCode){
        String department = "";
         try {
            con=conToDB.getSampleProperty();
            Statement stToGetEditFlag=con.createStatement();


            ResultSet rsToGetEditFlag = stToGetEditFlag.executeQuery("SELECT department FROM department WHERE dept_active=1 AND dept_code IN ('"+deptCode+"')");
            while(rsToGetEditFlag.next()){
                if(department.length()==0){
                    department = rsToGetEditFlag.getString(1);
                } else {
                    department += ", "+ rsToGetEditFlag.getString(1);
                }
            }
            rsToGetEditFlag.close();
            stToGetEditFlag.close();
            con.close();
        }
            catch(SQLException e) {
                e.printStackTrace();
        }
        return department;
    }

}
