/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.generic;

import java.io.Serializable;
import connection.DBconnection;
import java.io.File;
import java.sql.*;
import java.util.*;

/**
 *
 * @author rajac
 */
public class FolderDriveInsertion implements Serializable {

    connection.DBconnection conProp = new connection.DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String proj_id = "";
    private String proj_drive_id = "";
    private String loop_file_type_id = "";
    private String loop_file_type_path = "";
    private int insertDriveVal = 0;
    private int insertFolderPathVal = 0;
    private int updateFolderPathVal = 0;


    public void setProjDriveId(String proj_drive_id) {
        this.proj_drive_id = proj_drive_id;
    }

    public void setProjId(String proj_id) {
        this.proj_id = proj_id;
    }

    public void setFileTypeId(String loop_file_type_id) {
        this.loop_file_type_id = loop_file_type_id;
    }

    public void setFileTypePath(String loop_file_type_path) {
        this.loop_file_type_path = loop_file_type_path;
    }

    public int getInsertDriveVal() {
        return insertDriveVal;
    }

    public int getInsertFolderPathVal() {
        return insertFolderPathVal;
    }

    public int getUpdateFolderPathVal() {
        return updateFolderPathVal;
    }


    public void DriveValInsertion() {

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            insertDriveVal = stmt.executeUpdate(" insert into fmproj_drive (proj_id,drive_id) values('"+proj_id+"', '"+proj_drive_id+"') ");

            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    public void ProjFolderTypePathInsertion() {

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            insertFolderPathVal = stmt.executeUpdate(" insert into proj_filetypepath (proj_id,filetype_id,filetype_path) values ('"+proj_id+"', '"+loop_file_type_id+"', '"+loop_file_type_path+"') ");

            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    public void ProjFolderTypePathUpdation() {

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            updateFolderPathVal = stmt.executeUpdate(" update proj_filetypepath set filetype_path='"+loop_file_type_path+"' where proj_id='"+proj_id+"' and filetype_id='"+loop_file_type_id+"' ");

            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }
    
    public int ProjFolderUpdation(String getOldProjName, String getNewProjName, String projId) {

        List proj_filetype_path = new ArrayList();
        List proj_filetype_id = new ArrayList();
        List proj_folder_name = new ArrayList();
        int updateFolderPath = 0;

        getOldProjName = getOldProjName.replace("\'", "");
        getNewProjName = getNewProjName.replace("\'", "");
        try {
            con = conProp.getSampleProperty();
            PreparedStatement pst = con.prepareStatement("update proj_filetypepath set filetype_path=? where proj_id=? and filetype_id=? ");
            filters.generic.FolderDriveList getProjFolder = new filters.generic.FolderDriveList();
            String loopFileTypePath = "";
            String loopFileTypeId = "";
            boolean projRenamed = false;
            getProjFolder.getDriveInfo();
            getProjFolder.getFolderInfo();

            String driveValue = getProjFolder.getDriveValue();
            String projPath = driveValue + "/" + getOldProjName;
            String projPathNew = driveValue + "/" + getNewProjName;

            File projFolder = new File(projPath.trim());
            File projFolderNew = new File(projPathNew.trim());

            getProjFolder.setProjId(projId);
            getProjFolder.getProjFolderPathInfo();
            proj_filetype_path = getProjFolder.getProjFileTypePath();
            proj_filetype_id = getProjFolder.getProjFileTypeId();
            proj_folder_name = getProjFolder.getProjFolderName();
          
            if (projFolder.exists()) {
                if (getOldProjName.equals(getNewProjName)) {
                    System.out.println(getOldProjName + "Name is already Exsist" + getNewProjName);
                } else {
                    projRenamed = projFolder.renameTo(projFolderNew);
                    System.out.println("Project renamed."+projRenamed);
                }
            } else {
                System.out.println("Selected project is not exist in project share.");
            }
            
            if (projRenamed) {
                for (int i = 0; i < proj_filetype_id.size(); i++) {

                    loopFileTypePath = proj_filetype_path.get(i).toString();
                    loopFileTypeId = proj_filetype_id.get(i).toString();
                    loopFileTypePath = "/" + proj_folder_name.get(i).toString() + "//";
                    loopFileTypePath = "/" + getNewProjName + loopFileTypePath;
                    pst.setString(1, loopFileTypePath);
                    pst.setString(2, projId);
                    pst.setString(3, loopFileTypeId);

                    updateFolderPath = pst.executeUpdate();
                }

            } else {
                System.out.println("Project rename failed and " + projRenamed);
                System.out.println(driveValue + "/" + getOldProjName + " or file/s of the folder is/are using by someone. ");
            }
            pst.close();
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        return updateFolderPath;
    }
}
