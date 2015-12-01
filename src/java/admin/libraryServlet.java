/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import business.Book;
import business.User;

/**
 *
 * @author drewbryant
 */
@WebServlet(name = "libraryServlet", urlPatterns = {"/library"})
public class libraryServlet extends HttpServlet {

  
  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
      url = "/manage.jsp";
    }
    else if(action.equals("checkout")){
      String firstName = (String)request.getAttribute("firstname");
      String lastName = (String)request.getAttribute("lastname");
      String email = (String)request.getAttribute("email");
      String title = (String)request.getAttribute("title");
      Book book = new Book(title);
      User user = new User(firstName, lastName, email);
      user.addBook(book);
      
      url = "/confirm.jsp";
    }
    getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
