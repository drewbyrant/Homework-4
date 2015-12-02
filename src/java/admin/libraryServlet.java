/*
 * Copyright 2015 Andrew Bryant & Patrick Lathan
 */
package admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import business.Book;
import business.User;
import data.BookDB;
import data.UserDB;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * Servlet for the whole project
 * Handles links between pages and form inputs
 */
@WebServlet(name = "libraryServlet", urlPatterns = {"/library"})
public class libraryServlet extends HttpServlet {

  
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String action = request.getParameter("action");
    String url = "/index.jsp";
    
    if(action == null){
      action = "front";
    }
    else if(action.equals("front")){
      url = "/index.jsp";
    }
    else if(action.equals("to_checkout")){
      url = "/checkout.jsp";
    }
    else if(action.equals("to_manage")){
      ArrayList<Book> books = BookDB.selectBooks();
      
      request.setAttribute("books", books);
      url = "/manage.jsp";
    }
    else if(action.equals("checkout")){
      String firstName = request.getParameter("firstname");
      String lastName = request.getParameter("lastname");
      String email = request.getParameter("email");
      String title = request.getParameter("title");
      Book book = new Book(title);
      User user = new User(firstName, lastName, email);
      user.addBook(book);
      if(!UserDB.emailExists(user.getEmail())){
        UserDB.insert(user);
      }
      if(!BookDB.titleExists(book.getTitle())){
        book.setUser(user.getEmail());
        BookDB.insert(book);
      }
      GregorianCalendar dueDate = book.getDueDate();
      request.setAttribute("book", book);
      request.setAttribute("title", title);
      request.setAttribute("dueDate", dueDate);
      
      url = "/confirm.jsp";
    }
    else if(action.equals("delete")){
      String title = request.getParameter("title");
      Book b = BookDB.selectBook(title);
      BookDB.delete(b);
      url = "/index.jsp";
    }
    getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
  }
}
