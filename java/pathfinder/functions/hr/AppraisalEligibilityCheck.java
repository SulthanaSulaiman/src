/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.hr;

import java.sql.*;
import java.sql.SQLException;
import connection.DBconnection;
/**
 *
 * @author Parameshwarant
 */
public class AppraisalEligibilityCheck {

    String empID="";
    String queryForElgblChk="";
    String eligibleFlag="";
    String queryForEligblUpdate="";
    String alertMessage="";
    String queryForAppFlagChk="";
    String appFlagChk="";
    String empEncryptKey = "";
    String empEncryptKeyChk = "";

    int eligblUpdateChk;
    int daysDiff;

    public void setEmployeeID(String empID)
    {
        this.empID=empID;
    }

    public String getAlertMessage()
    {
        return alertMessage;
    }

    public String getEligibleFlag()
    {
        return eligibleFlag;
    }

    public void checkEligiblity()
    {
        try
        {
            DBconnection conntToDB=new DBconnection();
            Connection con=conntToDB.getSampleProperty();
            Statement st=con.createStatement();
            queryForElgblChk="SELECT DATEDIFF((SELECT CONCAT(DATE_FORMAT(CURRENT_TIMESTAMP(),'%Y'),'-04-01') FROM DUAL),(SELECT doj FROM USER WHERE emp_id='"+empID+"')) AS days FROM DUAL";
            ResultSet rs=st.executeQuery(queryForElgblChk);
            if(!rs.wasNull())
            {
                while(rs.next())
                {
                    if(rs.getString(1)!=null)
                    {
                       daysDiff=Integer.parseInt(rs.getString(1));

                    if(daysDiff>=365)
                    {
                        eligibleFlag="1";
                    }   else    {
                        eligibleFlag="0";
                        alertMessage="You are not eligible for this appraisal year";
                    }

                    }   else    {
                    alertMessage="Your date of join is not found. Please contact HR.";
                                }
                }
            }
            if(eligibleFlag.equals("1"))
            {

            queryForAppFlagChk="SELECT annual_appraisal_status FROM USER WHERE emp_id='"+empID+"'";
            ResultSet forAppFlagChk=st.executeQuery(queryForAppFlagChk);

            while(forAppFlagChk.next())
            {
            appFlagChk=forAppFlagChk.getString(1);
            }

            if(appFlagChk.equals("0"))
            {
                 System.out.println("Inside updating annual_appraisal_status ");
                 queryForEligblUpdate="update user set annual_appraisal_status=1 where emp_id='"+empID+"'";
                 eligblUpdateChk=st.executeUpdate(queryForEligblUpdate);
            }
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

}
