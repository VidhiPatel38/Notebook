package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.CachedRowSet;
import oracle.jdbc.rowset.OracleCachedRowSet;

public class RecordDAO {

    public static boolean add(Record e) {
        try (Connection con = Database.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "insert into RecordEntries values(entryid_seq.nextval,?,?,?,?)");
            ps.setString(1, e.getUname());
            ps.setString(2, e.getDate());
            ps.setString(3, e.getTime());
            ps.setString(4, e.getNotes());
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception ex) {
            return false;
        }
    }

    public static List<Record> getRecords(String uname) {
        try (Connection con = Database.getConnection()) {
            CachedRowSet crs = new OracleCachedRowSet();
            crs.setCommand(
                    "select * from (select * from RecordEntries where uname = ? order by recorddate desc, recordtime desc) where rownum <= 20");
            crs.setString(1, uname);
            crs.execute(con);
            ArrayList<Record> records = new ArrayList<>();
            while (crs.next()) {
                Record e = new Record();
                e.setId(crs.getString("recordid"));
                e.setDate(crs.getString("recorddate"));
                e.setTime(crs.getString("recordtime"));
                e.setNotes(crs.getString("recordnote"));
                records.add(e);
            }
            return records;
        } catch (Exception ex) {
            return null;
        }
    }


     public static Record getRecord(String id) {
       try (Connection con = Database.getConnection()) {
            CachedRowSet crs = new OracleCachedRowSet();
            crs.setCommand("select * from RecordEntries where recordid = ?");
            crs.setString(1,id);
            crs.execute(con);
            if ( crs.next()) {
                Record e = new Record();
                e.setId(crs.getString("recordid"));
                e.setDate(crs.getString("recorddate"));
                e.setTime(crs.getString("recordtime"));
                e.setNotes(crs.getString("recordnote"));
                return e;
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
     }

     
    public static boolean update(Record e) {
        try (Connection con = Database.getConnection()) {
            PreparedStatement ps = con.prepareStatement
                ("update RecordEntries set recordnote= ? , recorddate=? , recordtime=? where recordid=?");
            ps.setString(1, e.getNotes());
            ps.setString(2, e.getDate());
            ps.setString(3, e.getTime());
            ps.setString(4, e.getId());
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean delete(String id) {
        try (Connection con = Database.getConnection()) {
            PreparedStatement ps = con.prepareStatement
                 ("delete from RecordEntries where recordid=?");
            ps.setString(1,id);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception ex) {
            return false;
        }
    }

}
