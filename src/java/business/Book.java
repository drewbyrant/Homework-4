/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.GregorianCalendar;

/**
 *
 * @author drewbryant
 */
public class Book {
  private String title;
  private GregorianCalendar dueDate;
  private boolean isOverdue;
  
  public Book(){
    this.title = "";
    this.dueDate = new GregorianCalendar();
    this.dueDate.add(GregorianCalendar.WEEK_OF_YEAR, 2);
    this.isOverdue = false;
  }
  public Book(String newTitle, GregorianCalendar newDate, boolean overdue){
    this.title = newTitle;
    this.dueDate = newDate;
    this.isOverdue = overdue;
  }
  public Book(String newTitle){
    this.title = newTitle;
    this.dueDate = new GregorianCalendar();
    this.dueDate.add(GregorianCalendar.WEEK_OF_YEAR, 2);
    this.isOverdue = false;
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
  public boolean getIsOverdue(){
    return this.isOverdue;
  }
  public void setIsOverdue(boolean overdue){
    this.isOverdue = overdue;
  }
  
}