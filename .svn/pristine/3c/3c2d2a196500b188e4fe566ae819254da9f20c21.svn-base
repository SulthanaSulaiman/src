/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters.generic;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author rajac
 */
public class FolderDriveList implements Serializable {

    connection.DBconnection conProp = new connection.DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String drive_value = "";
    private String drive_id = "";
    private String drive_name = "";
    private String proj_id = "";
    private String folderloc_drive = "";
    private String folderloc_drive_value = "";
    private List filetype_id = new ArrayList();
    private List foldertype_name = new ArrayList();
    private List folder_name = new ArrayList();
    private List proj_filetype_path = new ArrayList();
    private List proj_filetype_id = new ArrayList();
    private List proj_folder_name = new ArrayList();
    

    public String getDriveId() {
        return drive_id;
    }

    public String getDriveName() {
        return drive_name;
    }

    public String getDriveValue() {
        return drive_value;
    }

    public List getFileTypeId() {
        return filetype_id;
    }

    public List getFolderName() {
        return folder_name;
    }

    public List getFolderTypeName() {
        return foldertype_name;
    }

    public String getFolderLocDrive() {
        return folderloc_drive;
    }

    public String getFolderLocDriveValue() {
        return folderloc_drive_value;
    }

    public List getProjFileTypeId() {
        return proj_filetype_id;
    }

    public List getProjFileTypePath() {
        return proj_filetype_path;
    }

    public List getProjFolderName() {
        return proj_folder_name;
    }

    public void setProjId(String proj_id) {
        this.proj_id = proj_id;
    }


    public void getDriveInfo() {

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "select drive_value,drive_id,drive_name ";
            sql_from = "from fm_drive ";
            sql_where = "drive_status='1' and drive_value='//s4csrvin001//PROJECTS' order by drive_id ";

            sql_select += sql_from + "where " + sql_where;

            rs = stmt.executeQuery(sql_select);

            while (rs.next()) {
                drive_value = rs.getString(1);
                if (rs.wasNull()) {
                    drive_value = "";
                }
                
                drive_id = rs.getString(2);

                drive_name = rs.getString(3);
                // hardcoded temporarily
               // drive_name = "//10.1.1.2//projects";
                 drive_name = "//s4csrvin001//PROJECTS";
                if (rs.wasNull()) {
                    drive_name = "";
                }
            }
            
            //System.out.println("drive_value - in java : "+drive_value);
            //System.out.println("drive_id - in java : "+drive_id);
            //System.out.println("drive_name - in java : "+drive_name);

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    public void getFolderInfo() {

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "select DISTINCT(filetype_id),foldertype_name,folder_name from filetype_name ";
            
            rs = stmt.executeQuery(sql_select);

            filetype_id.clear();
            foldertype_name.clear();
            folder_name.clear();
            
            while (rs.next()) {
                filetype_id.add(rs.getString(1));
                foldertype_name.add(rs.getString(2));
                folder_name.add(rs.getString(3));
            }

            //System.out.println("filetype_id - in java : "+filetype_id);
            //System.out.println("foldertype_name - in java : "+foldertype_name);
            //System.out.println("folder_name - in java : "+folder_name);

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    public void getFolderLocationInfo() {

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "select dr.drive_value,concat(dr.drive_value,'//',pr.proj_name) from projects pr,fm_drive dr,fmproj_drive fmpr "
                    + " where dr.drive_id=fmpr.drive_id and fmpr.proj_id=pr.proj_id and pr.proj_id ='"+proj_id+"'";

            rs = stmt.executeQuery(sql_select);

            folderloc_drive = "";
            folderloc_drive_value = "";

            while (rs.next()) {
                folderloc_drive = rs.getString(1);
                folderloc_drive_value = rs.getString(2);
            }

            //System.out.println("filetype_id - in java : "+filetype_id);
            //System.out.println("foldertype_name - in java : "+foldertype_name);
            //System.out.println("folder_name - in java : "+folder_name);

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    public void getProjFolderPathInfo() {

        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "select pjf.filetype_path,pjf.filetype_id,ftn.folder_name "+
		" from proj_filetypepath pjf,filetype_name ftn where pjf.proj_id='"+proj_id+"' and ftn.filetype_id=pjf.filetype_id";

            rs = stmt.executeQuery(sql_select);

            proj_filetype_path.clear();
            proj_filetype_id.clear();
            proj_folder_name.clear();

            while (rs.next()) {
                proj_filetype_path.add(rs.getString(1));
                proj_filetype_id.add(rs.getString(2));
                proj_folder_name.add(rs.getString(3));
            }

            //System.out.println("filetype_id - in java : "+filetype_id);
            //System.out.println("foldertype_name - in java : "+foldertype_name);
            //System.out.println("folder_name - in java : "+folder_name);

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }
}
