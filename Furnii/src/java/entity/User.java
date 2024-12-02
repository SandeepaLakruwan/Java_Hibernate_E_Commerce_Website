package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author sande
 */
@Entity
@Table(name = "user")
public class User implements Serializable{
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    
    @Column(name = "fname", length = 50, nullable = false)
    private String fname;
    
    @Column(name = "lname", length = 45, nullable = false)
    private String lname;
    
    @Column(name = "password", length = 20, nullable = false)
    private String password;
    
    @Column(name = "joined_date", nullable = false)
    private Date joined_date;
    
    @Column(name = "verification_code", length = 20, nullable = false)
    private String verification_code;
    
    @ManyToOne
    @JoinColumn(name="status_id")
    private Product_Status status;
    
    public User(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getJoined_date() {
        return joined_date;
    }

    public void setJoined_date(Date joined_date) {
        this.joined_date = joined_date;
    }

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code;
    }

    public Product_Status getStatus() {
        return status;
    }

    public void setStatus(Product_Status status) {
        this.status = status;
    }


}
