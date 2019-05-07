package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model
{
    public String firstname;
    public String lastname;
    public String gender;
    public String email;
    public String password;
    public String address;
    public float height;
    public float startWeight;
    public String comment = "";

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessment = new ArrayList<Assessment>();

    public Member (Long id, String comment)
    {
       this.comment=comment;
    }

    public Member (String firstname, String lastname, String gender, String email, String password, String address, float height, float startWeight)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.height = height;
        this.startWeight = startWeight;
    }

    public static Member findByEmail(String email)
    {
        return find("email", email).first();
    }



    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }

    public float getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(float startWeight) {
        this.startWeight = startWeight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
