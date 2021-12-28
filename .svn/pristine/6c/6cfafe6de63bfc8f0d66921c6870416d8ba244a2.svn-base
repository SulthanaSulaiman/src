
package pathfinder.functions.generic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connection.DBconnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aravindhanj
 */
public class FTPClientDAO {
    public void saveClientFTP(FTPClientVO ftpClientVO) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        DBconnection dbConnection = new DBconnection();
        connection = dbConnection.getSampleProperty();
        boolean newFTP = false;
        int count = 0;

        try {
            for(int idx=0; idx<ftpClientVO.getFtpIdList().size(); idx++) {
                newFTP = ftpClientVO.getFtpIdList().get(idx).toString().equals("0") ? true : false;
                if(newFTP) {
                    preparedStatement = connection.prepareStatement("INSERT INTO ftp_client_server (proj_id, ftp_server, ftp_username, ftp_password, ftp_path)" + " VALUES (?,?,?,?,?)");
                } else {
                    preparedStatement = connection.prepareStatement("UPDATE ftp_client_server "
                            + " SET proj_id = ? ,  ftp_server = ? , ftp_username = ? , ftp_password = ? , ftp_path = ? WHERE ftp_id = ?");
                }
                preparedStatement.setString(1, ftpClientVO.getProjectId());
                preparedStatement.setString(2, ftpClientVO.getFtpServerList().get(idx).toString());
                preparedStatement.setString(3, ftpClientVO.getFtpPathList().get(idx).toString());
                preparedStatement.setString(4, ftpClientVO.getUsernameList().get(idx).toString());
                preparedStatement.setString(5, ftpClientVO.getPasswordList().get(idx).toString());
                if(!newFTP) {
                    preparedStatement.setInt(6, Integer.parseInt(ftpClientVO.getFtpIdList().get(idx).toString()));
                }
                count += preparedStatement.executeUpdate();
            }
            if(count == ftpClientVO.getFtpIdList().size()) {
                ftpClientVO.setResult(1);
            } else {
                ftpClientVO.setResult(0);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: FTPClientDAO-saveClientFTP() : " + ex);
        }
    }

    public void deleteClientFTP(FTPClientVO ftpClientVO) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        DBconnection dbConnection = new DBconnection();
        connection = dbConnection.getSampleProperty();
        int count = 0;
        System.out.println(ftpClientVO.getFtpIdList());
        try {
            for(int idx=0; idx<ftpClientVO.getFtpIdList().size(); idx++) {
                
                preparedStatement = connection.prepareStatement("UPDATE ftp_client_server "
                        + " SET status = 0 WHERE ftp_id = ?");
                preparedStatement.setInt(1, Integer.parseInt(ftpClientVO.getFtpIdList().get(idx).toString()));
                count += preparedStatement.executeUpdate();
            }
            if(count == ftpClientVO.getFtpIdList().size()) {
                ftpClientVO.setResult(1);
            } else {
                ftpClientVO.setResult(0);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: FTPClientDAO-deleteClientFTP() : " + ex);
        }
    }

    public FTPClientVO collectProjectClientFTP(FTPClientVO ftpClientVO) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List ftpIdList = new ArrayList();
        List ftpServerList = new ArrayList();
        List ftpPathList = new ArrayList();
        List usernameList = new ArrayList();
        List passwordList = new ArrayList();

        DBconnection dbConnection = new DBconnection();
        connection = dbConnection.getSampleProperty();
        try {
            preparedStatement = connection.prepareStatement("SELECT ftp_id, ftp_server, ftp_username, ftp_password, ftp_path "
                    + " FROM ftp_client_server WHERE proj_id=? and status=1");
            preparedStatement.setString(1, ftpClientVO.getProjectId());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                ftpIdList.add(resultSet.getString(1)!=null?resultSet.getString(1):"0");
                ftpServerList.add(resultSet.getString(2)!=null?resultSet.getString(2):"");
                usernameList.add(resultSet.getString(3)!=null?resultSet.getString(3):"");
                passwordList.add(resultSet.getString(4)!=null?resultSet.getString(4):"");
                ftpPathList.add(resultSet.getString(5)!=null?resultSet.getString(5):"");
            }
            ftpClientVO.setFtpIdList(ftpIdList);
            ftpClientVO.setFtpServerList(ftpServerList);
            ftpClientVO.setFtpPathList(ftpPathList);
            ftpClientVO.setUsernameList(usernameList);
            ftpClientVO.setPasswordList(passwordList);
        } catch (SQLException ex) {
            System.out.println("SQLException: FTPClientDAO-collectProjectClientFTP() : " + ex);
        }
        return ftpClientVO;
    }
}
