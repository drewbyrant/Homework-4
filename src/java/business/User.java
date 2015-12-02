/*
 * Copyright 2015 Andrew Bryant & Patrick Lathan
 */
package business;

import java.util.ArrayList;

public class User {
  private String firstName;
  private String lastName;
  private String email;
  private ArrayList<Book> books;
  
  public User(){
    this.firstName = "";
    this.lastName = "";
    this.email = "";
    this.books = new ArrayList(0);
  }
  public User(String newFirst, String newLast, String newEmail, ArrayList<Book>
          newBooks){
    this.firstName = newFirst;
    this.lastName = newLast;
    this.email = newEmail;
    this.books = newBooks;
  }
  public User(String newFirst, String newLast, String newEmail){
    this.firstName = newFirst;
    this.lastName = newLast;
    this.email = newEmail;
    this.books = new ArrayList(0);
  }
  
  public String getFirstName(){
    return this.firstName;
  }
  public void setFirstName(String newFirst){
    this.firstName = newFirst;
  }
  public String getLastName(){
    return this.lastName;
  }
  public void setLastName(String newLast){
    this.lastName = newLast;
  }
  public String getEmail(){
    return this.email;
  }
  public void setEmail(String newEmail){
    this.email = newEmail;
  }
  public ArrayList<Book> getBooks(){
    return this.books;
  }
  public void setBooks(ArrayList<Book> newBooks){
    this.books = newBooks;
  }
  
  public void addBook(Book book){
    this.books.add(book);
  }
}
