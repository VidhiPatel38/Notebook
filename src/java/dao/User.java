package dao;

import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;


@ManagedBean
@RequestScoped 
public class User {
    private String uname,password, fullname, email,phoneno, message, newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    private Date   joinedon;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public Date getJoinedon() {
        return joinedon;
    }

    public void setJoinedon(Date joinedon) {
        this.joinedon = joinedon;
    }
    
    
    public String register() {
        
        this.joinedon = new Date(); // system date for joinedon
        boolean done = UserDAO.register(this);
        if ( done )
             return "login?faces-redirect=true";
        else {
             message = "User already exist";
             return "register";
        }
    }

     public String login() {
        User u = UserDAO.login(uname,password);
        if ( u != null ) {
            Util.addToSession("uname", uname);
            Util.addToSession("fullname", u.getFullname());
            return "/enterNote?faces-redirect=true";
        }
        else {
             message = "Invalid Username or password";
             return "login";
        }
    }
 
    public String logout() {
        Util.terminateSession();
        return "/all/login?faces-redirect=true";
    }
   
    
    @Override
    public String toString() {
        return "User{" + "uname=" + uname + ", password=" + password + ", fullname=" + fullname + ", email=" + email + ", phoneno=" + phoneno + ", message=" + message + ", newPassword=" + newPassword + ", joinedon=" + joinedon + '}';
    }
    
    
}
