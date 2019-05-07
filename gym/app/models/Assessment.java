package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


@Entity
public class Assessment extends Model
{
  @OneToMany(cascade = CascadeType.ALL)
  public List<Comment> comments = new ArrayList<Comment>();
  public float weight;
  public float chest;
  public float thigh;
  public float upperarm;
  public float waist;
  public float hips;
  public String comment = "";
  public String timeStamp = new SimpleDateFormat("dd MMM yyyy HH.mm.ss").format(new Date());
  public boolean trend;

  public Assessment(float weight, float chest, float thigh, float upperarm, float waist, float hips, boolean trend)
  {
    this.weight = weight;
    this.chest = chest;
    this.thigh = thigh;
    this.upperarm= upperarm;
    this.waist=waist;
    this.hips=hips;
    this.trend=trend;
  }

  public void addCommentList (String comment)
  {
    this.comment = comment;
  }

  public float getWeight() {
    return weight;
  }

  public void setWeight(float weight) {
    this.weight = weight;
  }
}