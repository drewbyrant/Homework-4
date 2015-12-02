/*
 * Copyright 2015 Andrew Bryant & Patrick Lathan
 */
package business;

import data.UserDB;
import java.util.GregorianCalendar;


public class Book {
  private String title;
  private GregorianCalendar dueDate;
  private String overdue;
  private User user = new User();
  
  public Book(){
    this.title = "";
    this.dueDate = new GregorianCalendar();
    this.dueDate.add(GregorianCalendar.WEEK_OF_YEAR, 2);
    this.overdue = "";
  }
  public Book(String newTitle, GregorianCalendar newDate, String overdue){
    this.title = newTitle;
    this.dueDate = newDate;
    this.overdue = overdue;
  }
  public Book(String newTitle){
    this.title = newTitle;
    this.dueDate = new GregorianCalendar();
    this.dueDate.add(GregorianCalendar.WEEK_OF_YEAR, 2);
    this.overdue = "";
  }
  
  public String getTitle(){
    return this.title;
  }
  public void setTitle(String newTitle){
    this.title = newTitle;
  }
  public GregorianCalendar getDueDate(){
    return this.dueDate;
  }
  public void setDueDate(GregorianCalendar newDate){
    this.dueDate = newDate;
  }
  public String getOverdue(){
    return this.overdue;
  }
  public void setOverdue(String overdue){
    this.overdue = overdue;
  }
  public void setUser(String email){
    this.user = UserDB.selectUser(email);
  }
  public User getUser(){
    return user;
  }
  
}
