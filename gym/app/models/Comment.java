package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Comment extends Model
{
  public String comment;
  public Long id;


  public Comment(Long id, String comment)
  {
    this.comment = comment;
    this.id = id;
  }
}
