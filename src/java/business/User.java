/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;

/**
 *
 * @author drewbryant
 */
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
  
  public String getFirst(){
    return this.firstName;
  }
  public void setFirst(String newFirst){
    this.firstName = newFirst;
  }
  public String getLast(){
    return this.lastName;
  }
  public void setLast(String newLast){
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
