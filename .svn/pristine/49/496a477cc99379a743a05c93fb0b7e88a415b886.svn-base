/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import connection.DBconnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aravindhanj
 */
public class TransmittalDAO {

    DBconnection dbconnection = new DBconnection();
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private String searchKey ="";
    private ArrayList shippingMethodId = new ArrayList();
    private ArrayList shippingMethodName = new ArrayList();
    pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public ArrayList getShippingMethodId() {
        return shippingMethodId;
    }

    public void setShippingMethodId(ArrayList shippingMethodId) {
        this.shippingMethodId = shippingMethodId;
    }

    public ArrayList getShippingMethodName() {
        return shippingMethodName;
    }

    public void setShippingMethodName(ArrayList shippingMethodName) {
        this.shippingMethodName = shippingMethodName;
    }

    public TransmittalVO GetProjectDetailsForTransmit(TransmittalVO transmittalVO) {
        
        try {
            con = (Connection) dbconnection.getSampleProperty();
            st = (Statement) con.createStatement();
            String projId = transmittalVO.getProjId();

            ArrayList dispCarrierId = new ArrayList();
            ArrayList dispCarrierName = new ArrayList();

            String transmittalQuery = "SELECT p.proj_bktitle, p.proj_isbn_10, p.proj_isbn_13, GROUP_CONCAT(c.surname SEPARATOR ',') AUTHORS, p.proj_name "
                    + "FROM projects p, contacts c, project_authors pa "
                    + "WHERE pa.author_id = c.contact_id AND p.proj_id = pa.proj_id AND p.proj_id='"+projId+"'";

            rs = st.executeQuery(transmittalQuery);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    transmittalVO.setTitle(rs.getString(1));
                } else {
                    transmittalVO.setTitle("");
                }
                if (rs.getString(2) != null) {
                    transmittalVO.setIsbn10(rs.getString(2));
                } else {
                    transmittalVO.setIsbn10("");
                }
                if (rs.getString(3) != null) {
                    transmittalVO.setIsbn13(rs.getString(3));
                } else {
                    transmittalVO.setIsbn13("");
                }
                if (rs.getString(4) != null) {
                    transmittalVO.setAuthor(splChar.decoding(rs.getString(4)));
                } else {
                    transmittalVO.setAuthor("");
                }
                if (rs.getString(5) != null) {
                    transmittalVO.setProjName(rs.getString(5));
                } else {
                    transmittalVO.setProjName("");
                }
            }

            transmittalQuery = "SELECT querier_id, querier_name FROM shipping_querier WHERE querier_status = '1'";

            rs = st.executeQuery(transmittalQuery);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    dispCarrierId.add(rs.getString(1));
                } else {
                    dispCarrierId.add("");
                }
                if (rs.getString(2) != null) {
                    dispCarrierName.add(rs.getString(2));
                } else {
                    dispCarrierName.add("");
                }
            }
            transmittalVO.setDispCarrierId(dispCarrierId);
            transmittalVO.setDispCarrierName(dispCarrierName);
            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Exception occured in TransmittalDAO : "+e);
        }
        return transmittalVO;
    }

    public TransmittalVO GetExistingTransmittal(TransmittalVO transmittalVO) {
        try {
            con = (Connection) dbconnection.getSampleProperty();
            st = (Statement) con.createStatement();

            String projId = transmittalVO.getProjId();
            ArrayList dispShippingId = new ArrayList();
            ArrayList dispTransmittalDate = new ArrayList();
            ArrayList dispShipFrom = new ArrayList();
            ArrayList dispShipTo = new ArrayList();
            ArrayList dispCarrier = new ArrayList();
            ArrayList dispShipMethod = new ArrayList();
            ArrayList dispBoundType = new ArrayList();

            String transmittalQuery = "SELECT shipping_id, transmittal_date, ship_from, ship_to, carrier, ship_method, "
                    + "case inbound_flag when '1' then 'Inbound' else 'Outbound' end BoundType FROM shipping WHERE proj_id='"+projId+"'";

            rs = st.executeQuery(transmittalQuery);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    dispShippingId.add(rs.getString(1));
                } else {
                    dispShippingId.add("");
                }
                if (rs.getString(2) != null) {
                    dispTransmittalDate.add(rs.getString(2));
                } else {
                    dispTransmittalDate.add("");
                }
                if (rs.getString(3) != null) {
                    dispShipFrom.add(rs.getString(3));
                } else {
                    dispShipFrom.add("");
                }
                if (rs.getString(4) != null) {
                    dispShipTo.add(rs.getString(4));
                } else {
                    dispShipTo.add("");
                }
                if (rs.getString(5) != null) {
                    dispCarrier.add(rs.getString(5));
                } else {
                    dispCarrier.add("");
                }
                if (rs.getString(6) != null) {
                    dispShipMethod.add(rs.getString(6));
                } else {
                    dispShipMethod.add("");
                }
                if (rs.getString(7) != null) {
                    dispBoundType.add(rs.getString(7));
                } else {
                    dispBoundType.add("");
                }
            }
            transmittalVO.setDispShippingId(dispShippingId);
            transmittalVO.setDispTransmittalDate(dispTransmittalDate);
            transmittalVO.setDispShipFrom(dispShipFrom);
            transmittalVO.setDispShipTo(dispShipTo);
            transmittalVO.setDispCarrier(dispCarrier);
            transmittalVO.setDispShipMethod(dispShipMethod);
            transmittalVO.setDispTransmittalType(dispBoundType);
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Exception occured in TransmittalDAO - GetExistingTransmittal() : "+e);
        }
        return transmittalVO;
    }

    public TransmittalVO GetTransmittalDetails(TransmittalVO transmittalVO) {
        try {
            con = (Connection) dbconnection.getSampleProperty();
            st = (Statement) con.createStatement();
            String projId = transmittalVO.getProjId();
            String shipId = transmittalVO.getShipId();
            String shipMethodVal = "";
            String carrierVal = "";

            String transmittalQuery = "SELECT s.note_id, s.carrier, s.remark, s.content_notes, s.package_content, s.ship_method, s.transmittal_date, "
                    + "s.transmittal_option, s.reference, s.inbound_flag, s.bound_type, s.carrier_tracking, s.carrier_cost, s.ship_to_location, "
                    + "s.ship_from_location, s.request_date, s.transmittal_number, s.ship_to_id, s.ship_from_id, s.ship_to, s.ship_from, s.attention_id, s.division_id, d.company "
                    + "FROM shipping s LEFT JOIN contacts d ON s.division_id=d.contact_id WHERE s.proj_id='"+ projId +"' AND s.shipping_id='"+ shipId +"'";

            rs = st.executeQuery(transmittalQuery);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    transmittalVO.setNoteId(rs.getString(1));
                } else {
                    transmittalVO.setNoteId("");
                }
                if (rs.getString(2) != null) {
                    transmittalVO.setCarrier(rs.getString(2));
                    carrierVal = rs.getString(2);
                } else {
                    transmittalVO.setCarrier("");
                }
                if (rs.getString(3) != null) {
                    transmittalVO.setRemarks(rs.getString(3));
                } else {
                    transmittalVO.setRemarks("");
                }
                if (rs.getString(4) != null) {
                    transmittalVO.setContentNote(rs.getString(4));
                } else {
                    transmittalVO.setContentNote("");
                }
                if (rs.getString(5) != null) {
                    transmittalVO.setPackageContent(rs.getString(5));
                } else {
                    transmittalVO.setPackageContent("");
                }
                if (rs.getString(6) != null) {
                    transmittalVO.setShipMethod(rs.getString(6));
                    shipMethodVal = rs.getString(6).toString();
                } else {
                    transmittalVO.setShipMethod("");
                }
                if (rs.getString(7) != null) {
                    transmittalVO.setMailDate(rs.getString(7));
                } else {
                    transmittalVO.setMailDate("");
                }
                if (rs.getString(8) != null) {
                    transmittalVO.setOption(rs.getString(8));
                } else {
                    transmittalVO.setOption("");
                }
                if (rs.getString(9) != null) {
                    transmittalVO.setReference(rs.getString(9));
                } else {
                    transmittalVO.setReference("");
                }
                if (rs.getString(10) != null) {
                    transmittalVO.setInBoundFlag(rs.getString(10));
                } else {
                    transmittalVO.setInBoundFlag("");
                }
                if (rs.getString(11) != null) {
                    transmittalVO.setBoundType(rs.getString(11));
                } else {
                    transmittalVO.setBoundType("");
                }
                if (rs.getString(12) != null) {
                    transmittalVO.setCarrierTrackNo(rs.getString(12));
                } else {
                    transmittalVO.setCarrierTrackNo("");
                }
                if (rs.getString(13) != null) {
                    transmittalVO.setCarrierCost(rs.getString(13));
                } else {
                    transmittalVO.setCarrierCost("");
                }
                if (rs.getString(14) != null) {
                    transmittalVO.setShipToLocation(rs.getString(14));
                } else {
                    transmittalVO.setShipToLocation("");
                }
                if (rs.getString(15) != null) {
                    transmittalVO.setShipFromLocation(rs.getString(15));
                } else {
                    transmittalVO.setShipFromLocation("");
                }
                if (rs.getString(16) != null) {
                    transmittalVO.setRequestDate(rs.getString(16));
                } else {
                    transmittalVO.setRequestDate("");
                }
                if (rs.getString(17) != null) {
                    transmittalVO.setTransmittalId(rs.getString(17));
                } else {
                    transmittalVO.setTransmittalId("");
                }
                if (rs.getString(18) != null) {
                    transmittalVO.setShipTo(rs.getString(18));
                } else {
                    transmittalVO.setShipTo("");
                }
                if (rs.getString(19) != null) {
                    transmittalVO.setShipFrom(rs.getString(19));
                } else {
                    transmittalVO.setShipFrom("");
                }
                if (rs.getString(20) != null) {
                    transmittalVO.setShipToName(rs.getString(20));
                } else {
                    transmittalVO.setShipToName("");
                }
                if (rs.getString(21) != null) {
                    transmittalVO.setShipFromName(rs.getString(21));
                } else {
                    transmittalVO.setShipFromName("");
                }
                if (rs.getString(22) != null) {
                    transmittalVO.setShipToAttention(rs.getString(22));
                } else {
                    transmittalVO.setShipToAttention("");
                }
                if (rs.getString(23) != null) {
                    transmittalVO.setShipDivision(rs.getString(23));
                } else {
                    transmittalVO.setShipDivision("");
                }
                if (rs.getString(24) != null) {
                    transmittalVO.setShipDivisionName(splChar.decoding(rs.getString(24)));
                } else {
                    transmittalVO.setShipDivisionName("");
                }
            }

            con = (Connection) dbconnection.getSampleProperty();
            st = (Statement) con.createStatement();
            transmittalQuery = "SELECT a.shipping_method_id FROM shipping_method a, shipping_querier b "
                    + "WHERE a.mode_of_shippment='"+shipMethodVal+"' AND a.querier_id=b.querier_id AND b.querier_name='"+carrierVal+"'";
            rs = st.executeQuery(transmittalQuery);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    transmittalVO.setShipMethodId(rs.getString(1));
                } else {
                    transmittalVO.setShipMethodId("");
                }
            }
            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Exception occured in TransmittalDAO - GetExistingTransmittal() : "+e);
        }
        return transmittalVO;
    }

    public String emptyToNull(String getString) {
        if(getString.equals("")) {
            return "NULL";
        } else {
            return "'"+getString+"'";
        }
    }

    public void SaveTransmittal(TransmittalVO transmittalVO) {
        try {
            con = (Connection) dbconnection.getSampleProperty();
            st = (Statement) con.createStatement();
            int result = 0;
            String InsertTransmittal = "";
            String projId = transmittalVO.getProjId();
            String mailDate = transmittalVO.getMailDate();
            String carrier = transmittalVO.getCarrier();
            String shipWithAcct = transmittalVO.getShipWithAcct();
            String billingOption = emptyToNull(transmittalVO.getOption());
            String billingReference = emptyToNull(transmittalVO.getReference());
            String contentNote = emptyToNull(transmittalVO.getContentNote());
            String packageContent = transmittalVO.getPackageContent();
            String remarks = emptyToNull(transmittalVO.getRemarks());
            String empId = transmittalVO.getEmpId();
            String receiver = transmittalVO.getReceiver();
            //String shippingId = transmittalVO.getShippingId();
            //String noteId = transmittalVO.getNoteId();
            String shipMethod = transmittalVO.getShipMethod();
            String boundType = transmittalVO.getBoundType();
            String inBoundFlag = "0";
            if(boundType.equals("Inbound")) {
                inBoundFlag = "1";
            }
            String carrierTrackNo = emptyToNull(transmittalVO.getCarrierTrackNo());
            String carrierCost = emptyToNull(transmittalVO.getCarrierCost());
            String shipToLocation = emptyToNull(transmittalVO.getShipToLocation());
            String shipFromLocation = emptyToNull(transmittalVO.getShipFromLocation());
            //String requestDate = transmittalVO.getRequestDate();
            String shipTo = emptyToNull(transmittalVO.getShipTo());
            String shipToAttention = emptyToNull(transmittalVO.getShipToAttention());
            String shipDivision = emptyToNull(transmittalVO.getShipDivision());
            String shipFrom = emptyToNull(transmittalVO.getShipFrom());
            String shipToName =emptyToNull( transmittalVO.getShipToName());
            String shipFromName = emptyToNull(transmittalVO.getShipFromName());

            if(inBoundFlag.equals("1")) {
                InsertTransmittal = "INSERT INTO shipping "
                        + "(note_id, proj_id, carrier, remark, content_notes, package_content, ship_method, transmittal_date, transmittal_option, reference, "
                        + "ship_from, ship_to, inbound_flag, bound_type, carrier_tracking, carrier_cost, ship_from_location, request_date, ship_from_id, ship_to_id, division_id) "
                        + "VALUES "
                        + "((SELECT MAX(s.note_id)+1 from shipping s),'"+projId+"',(SELECT querier_name FROM shipping_querier WHERE querier_id='"+carrier+"'),"+remarks+","
                        + ""+contentNote+",'"+packageContent+"',(select mode_of_shippment from shipping_method where shipping_method_id='"+shipMethod+"'),'"+mailDate+"',"+billingOption+","+billingReference+","
                        + ""+shipFromName+","+shipToName+",'"+inBoundFlag+"','"+boundType+"',"+carrierTrackNo+","+carrierCost+","+shipFromLocation+",CURRENT_TIMESTAMP(),"+shipFrom+","+shipTo+","+shipDivision+")";
            } else {
                InsertTransmittal = "INSERT INTO shipping "
                        + "(note_id, proj_id, carrier, remark, content_notes, package_content, ship_method, transmittal_date, transmittal_option, reference, "
                        + "ship_from, ship_to, inbound_flag, bound_type, carrier_tracking, carrier_cost, ship_to_location, request_date, ship_from_id, ship_to_id, attention_id, division_id) "
                        + "VALUES "
                        + "((SELECT MAX(s.note_id)+1 from shipping s),'"+projId+"',(SELECT querier_name FROM shipping_querier WHERE querier_id='"+carrier+"'),"+remarks+","
                        + ""+contentNote+",'"+packageContent+"',(select mode_of_shippment from shipping_method where shipping_method_id='"+shipMethod+"'),'"+mailDate+"',"+billingOption+","+billingReference+","
                        + ""+shipFromName+","+shipToName+",'"+inBoundFlag+"','"+boundType+"',"+carrierTrackNo+","+carrierCost+","+shipToLocation+",CURRENT_TIMESTAMP(),"+shipFrom+","+shipTo+","+shipToAttention+","+shipDivision+")";
            }
            
            result = st.executeUpdate(InsertTransmittal);
            transmittalVO.setProjId(projId);
            transmittalVO.setResult(result);
        } catch(Exception e) {
            System.out.println("Exception while inserting Outbound transmittal Insertion : "+e);
        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("SQLException :"+ex);
            }
        }
        try {
            con = (Connection) dbconnection.getSampleProperty();
            st = (Statement) con.createStatement();

            String transmittalQuery = "SELECT MAX(shipping_id) FROM shipping";

            rs = st.executeQuery(transmittalQuery);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    transmittalVO.setShipId(rs.getString(1));
                } else {
                    transmittalVO.setShipId("");
                }
            }
            rs.close();
            st.close();
            con.close();
        } catch(Exception e) {
            System.out.println("Exception while retrieving the Transmittal ID which is just inserted : "+e);
        }
    }


    public void UpdateTransmittal(TransmittalVO transmittalVO) {
        try {
            con = (Connection) dbconnection.getSampleProperty();
            st = (Statement) con.createStatement();
            int result = 0;
            String UpdateTransmittal = "";
            String shipId = transmittalVO.getShipId();
            String projId = transmittalVO.getProjId();
            String mailDate = transmittalVO.getMailDate();
            String carrier = transmittalVO.getCarrier();
            String shipWithAcct = transmittalVO.getShipWithAcct();
            String billingOption = emptyToNull(transmittalVO.getOption());
            String billingReference = emptyToNull(transmittalVO.getReference());
            String contentNote = emptyToNull(transmittalVO.getContentNote());
            String packageContent = transmittalVO.getPackageContent();
            String remarks = emptyToNull(transmittalVO.getRemarks());
            String empId = transmittalVO.getEmpId();
            String receiver = transmittalVO.getReceiver();
            //String shippingId = transmittalVO.getShippingId();
            //String noteId = transmittalVO.getNoteId();
            String shipMethod = transmittalVO.getShipMethod();
            String boundType = transmittalVO.getBoundType();
            String inBoundFlag = "0";
            if(boundType.equals("Inbound")) {
                inBoundFlag = "1";
            }
            String carrierTrackNo = emptyToNull(transmittalVO.getCarrierTrackNo());
            String carrierCost = emptyToNull(transmittalVO.getCarrierCost());
            String shipToLocation = emptyToNull(transmittalVO.getShipToLocation());
            String shipFromLocation = emptyToNull(transmittalVO.getShipFromLocation());
            //String requestDate = transmittalVO.getRequestDate();
            String shipTo = emptyToNull(transmittalVO.getShipTo());
            String shipToAttention = emptyToNull(transmittalVO.getShipToAttention());
            String shipFrom = emptyToNull(transmittalVO.getShipFrom());
            String shipToName =emptyToNull( transmittalVO.getShipToName());
            String shipFromName = emptyToNull(transmittalVO.getShipFromName());
            String shipDivision = emptyToNull(transmittalVO.getShipDivision());
            if(inBoundFlag.equals("1")) {
                UpdateTransmittal = "UPDATE shipping SET "
                        + "carrier=(SELECT querier_name FROM shipping_querier WHERE querier_id='"+carrier+"'), remark="+remarks+", content_notes="+contentNote+", package_content='"+packageContent+"', "
                        + "ship_method=(select mode_of_shippment from shipping_method where shipping_method_id='"+shipMethod+"'), "
                        + "transmittal_date='"+mailDate+"', transmittal_option="+billingOption+", reference="+billingReference+", "
                        + "ship_to="+shipToName+", ship_from="+shipFromName+", inbound_flag='"+inBoundFlag+"', bound_type='"+boundType+"', carrier_tracking="+carrierTrackNo+", "
                        + "carrier_cost="+carrierCost+", ship_from_location="+shipFromLocation+", ship_from_id="+shipFrom+", ship_to_id="+shipTo+", division_id="+shipDivision+" WHERE shipping_id='"+shipId+"'";
            } else {
                UpdateTransmittal = "UPDATE shipping SET "
                        + "carrier=(SELECT querier_name FROM shipping_querier WHERE querier_id='"+carrier+"'), remark="+remarks+", content_notes="+contentNote+", package_content='"+packageContent+"', "
                        + "ship_method=(select mode_of_shippment from shipping_method where shipping_method_id='"+shipMethod+"'), "
                        + "transmittal_date='"+mailDate+"', transmittal_option="+billingOption+", reference="+billingReference+", "
                        + "ship_to="+shipToName+", ship_from="+shipFromName+", inbound_flag='"+inBoundFlag+"', bound_type='"+boundType+"', carrier_tracking="+carrierTrackNo+", "
                        + "carrier_cost="+carrierCost+", ship_to_location="+shipToLocation+", ship_from_id="+shipFrom+", ship_to_id="+shipTo+", attention_id="+shipToAttention+", division_id="+shipDivision+" WHERE shipping_id='"+shipId+"'";
            }
            result = st.executeUpdate(UpdateTransmittal);
            transmittalVO.setProjId(projId);
            transmittalVO.setShipId(shipId);
            transmittalVO.setResult(result);
        } catch(Exception e) {
            System.out.println("Exception while inserting Outbound transmittal Insertion : "+e);
        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("SQLException :"+ex);
            }
        }
    }

    public void DeleteTransmittal(TransmittalVO transmittalVO) {
         try {
            con = (Connection) dbconnection.getSampleProperty();
            st = (Statement) con.createStatement();
            int result = 0;
            String DeleteTransmittal = "";
            String shipId = transmittalVO.getShipId();
            String projId = transmittalVO.getProjId();
            DeleteTransmittal = "DELETE FROM shipping WHERE shipping_id='"+shipId+"'";
            result = st.executeUpdate(DeleteTransmittal);
            transmittalVO.setProjId(projId);
            transmittalVO.setShipId(shipId);
            transmittalVO.setResult(result);
        } catch(Exception e) {
            System.out.println("Exception while inserting Outbound transmittal Insertion : "+e);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("SQLException :"+ex);
            }
        }
    }

    public void GetShippingMethods() {
        try {
            con = (Connection) dbconnection.getSampleProperty();
            st = (Statement) con.createStatement();
            String transmittalQuery = "SELECT shipping_method_id, mode_of_shippment FROM shipping_method WHERE querier_id='"+ searchKey +"'";
            rs = st.executeQuery(transmittalQuery);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    shippingMethodId.add(rs.getString(1).toString());
                } else {
                    shippingMethodId.add("");
                }
                if (rs.getString(2) != null) {
                    shippingMethodName.add(rs.getString(2).toString());
                } else {
                    shippingMethodName.add("");
                }
            }
        } catch(Exception e) {
            System.out.println("Exception while inserting Outbound transmittal Insertion : "+e);
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("SQLException :"+ex);
            }
        }
    }

    public TransmittalVO GetEmpAndContactDetails(TransmittalVO transmittalVO) {
        try {
            con = (Connection) dbconnection.getSampleProperty();
            st = (Statement) con.createStatement();

            String empId = transmittalVO.getEmpId();
            String contactId = transmittalVO.getContact();
            String attentionId = transmittalVO.getShipToAttention();
            String divisionId = transmittalVO.getShipDivision();
            con = (Connection) dbconnection.getSampleProperty();
            st = (Statement) con.createStatement();

            String transmittalQuery = "SELECT u.emp_name, d.designation FROM USER u, designation d WHERE u.desig_code=d.desig_code AND u.emp_id='"+empId+"'";

            rs = st.executeQuery(transmittalQuery);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    transmittalVO.setDispEmpName(rs.getString(1));
                } else {
                    transmittalVO.setDispEmpName("");
                }
                if (rs.getString(2) != null) {
                    transmittalVO.setDispEmpDesig(rs.getString(2));
                } else {
                    transmittalVO.setDispEmpDesig("");
                }
            }
            
            if(!divisionId.toString().equals("") && !divisionId.toString().equals("0")) {
                transmittalQuery = " SELECT c.contact_id AS company_id,c.company,d.company AS division,d.contact_id AS division_id, "
                        + " d.address_1, d.city, d.country, d.zipcode "
                        + " FROM contacts d,contacttype_map cm,belongs_to b,contacts c "
                        + " WHERE d.contact_id = cm.contact_id AND cm.type_id = 2 AND c.contact_id = b.parent_contact AND c.contact_id='" + contactId + "' "
                        + " AND b.contact_id = d.contact_id" ;
            } else {
                transmittalQuery = " SELECT c.contact_id, "
                        + " CASE "
                        + "     WHEN ctmr.type_id=1 OR ctmr.type_id=2 OR ctmr.type_id=5 THEN c.company "
                        + " ELSE TRIM(CONCAT(IFNULL(firstname,''),' ',IFNULL(surname,''))) "
                        + " END AS DispName, NULL, NULL, c.address_1, c.city, c.country, c.zipcode "
                        + " FROM contacts c, contacttype_map ctm, contacts_type_master ctmr "
                        + " WHERE c.contact_id=ctm.contact_id AND ctm.type_id=ctmr.type_id AND "
                        + " c.contact_id='" + contactId + "' GROUP BY c.contact_id ";
            }

            rs = st.executeQuery(transmittalQuery);
            while (rs.next()) {
                if (rs.getString(2) != null) {
                    transmittalVO.setDispContact(splChar.decoding(rs.getString(2)));
                } else {
                    transmittalVO.setDispContact("");
                }
                if (rs.getString(5) != null) {
                    transmittalVO.setDispContactAdd(rs.getString(5));
                } else {
                    transmittalVO.setDispContactAdd("");
                }
                if (rs.getString(6) != null) {
                    transmittalVO.setDispContactCity(rs.getString(6));
                } else {
                    transmittalVO.setDispContactCity("");
                }
                if (rs.getString(7) != null) {
                    transmittalVO.setDispContactCountry(rs.getString(7));
                } else {
                    transmittalVO.setDispContactCountry("");
                }
                if (rs.getString(8) != null) {
                    transmittalVO.setDispContactCityCode(rs.getString(8));
                } else {
                    transmittalVO.setDispContactCityCode("");
                }
            }

            transmittalQuery = "SELECT CONCAT(c.firstname, ' ',c.surname) FROM contacts c WHERE contact_id='"+attentionId+"'";

            rs = st.executeQuery(transmittalQuery);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    transmittalVO.setDispShipToAttention(splChar.decoding(rs.getString(1)));
                } else {
                    transmittalVO.setDispShipToAttention("");
                }
            }
            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Exception occured in TransmittalDAO - GetExistingTransmittal() : "+e);
        }
        return transmittalVO;
    }

    public TransmittalVO GetShippingForReports(TransmittalVO transmittalVO) {
        try {
            int count = 0;
            con = (Connection) dbconnection.getSampleProperty();
            st = (Statement) con.createStatement();
            String projId = transmittalVO.getProjId();
            String transmittalQuery = "SELECT SUM(carrier_cost) FROM shipping "
                    + "WHERE proj_id='" + projId + "' AND transmittal_option='Sender' AND inbound_flag='0' GROUP BY proj_id";
            rs = st.executeQuery(transmittalQuery);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    transmittalVO.setTotalCarrierCost(rs.getString(1).toString());
                } else {
                    transmittalVO.setTotalCarrierCost("0.0");
                }
                count++;
            }
            if(count==0) {
                transmittalVO.setTotalCarrierCost("0.0");
            }
        } catch(Exception e) {
            System.out.println("Exception on Transmittal DAO - GetShippingForReports() : "+e);
        } finally {
            try {
                con.close();
                st.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println("SQLException :"+ex);
            }
        }
        return transmittalVO;
    }
}
