package dao;

import dao.RecordDAO;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@RequestScoped 
public class Record {

    private List<Record> records = null;
    private String id, notes, time, message, uname, date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
           
    
    public Record() {
       
       Calendar c  = Calendar.getInstance();
       time = String.format("%02d:%02d",c.get( Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
       date = String.format("%4d-%02d-%02d",c.get(Calendar.YEAR),c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH)) ;

    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public String toString() {
        return "Entry{" + "id=" + id + ", notes=" + notes + ", time=" + time + ", message=" + message + ", uname=" + uname + ", date=" + date + '}';
    }

    public String getDisplayRecord(){
        return   notes.length() < 50 ? notes :  notes.substring(0,50) + " [More ...]";
    }
    

     public String load(){
        Record e =  RecordDAO.getRecord(id);
        this.date = e.date;
        this.time  = e.time;
        this.id = e.id;
        this.notes = e.notes;
        this.uname = e.uname;
        return null; 
    }
    


    public void add(ActionEvent evt) {
        uname = Util.getUname();
        boolean done = RecordDAO.add(this);
        if ( done) {
            message = "Record Added!";
            notes = "";
        }
        else
            message = "could not Add Record";
    }
     
     
    
     public void update(ActionEvent evt) {
        uname = Util.getUname();
        boolean done = RecordDAO.update(this);
        if ( done) {
            message = "Record Updated";
        }
        else
            message = "Record cannot be updated";
    }
         
     public void delete(ActionEvent evt) {
 	RecordDAO.delete( Util.getParameter("id"));
     }
    
    public List<Record> getRecords(){
        if (records == null) {
            records = RecordDAO.getRecords( Util.getUname());
        }
        return records; 
    }

}
