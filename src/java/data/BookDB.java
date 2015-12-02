/*
 * Copyright 2015 Andrew Bryant & Patrick Lathan
 */
package data;
import java.sql.*;
import business.Book;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class BookDB {
  public static int insert(Book book) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO Books (Title, Due_Date, Overdue, UserEmail) "
                + "VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, book.getTitle());
            GregorianCalendar cal = book.getDueDate();
            java.util.Date date = cal.getTime();
            java.sql.Date sDate = new java.sql.Date(date.getTime());
            ps.setDate(2, sDate);
            ps.setString(3, book.getOverdue());
            String e = book.getUser().getEmail().trim();
            ps.setString(4, e);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
  public static int delete(Book book) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM Books "
                + "WHERE Title = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, book.getTitle());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
  public static boolean titleExists(String title) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT Title FROM Books "
                + "WHERE Title = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, title);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
  public static Book selectBook(String title) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Books "
                + "WHERE Title = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, title);
            rs = ps.executeQuery();
            Book book = null;
            if (rs.next()) {
                book = new Book();
                book.setTitle(rs.getString("Title"));
                Date date = rs.getDate("Due_Date");
                GregorianCalendar cal = new GregorianCalendar(date.getYear(),
                date.getMonth(), date.getDay());
                cal.add(GregorianCalendar.WEEK_OF_YEAR, 2);
                book.setDueDate(cal);
                book.setOverdue(rs.getString("Overdue"));
            }
            return book;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
  public static ArrayList<Book> selectBooks() {
        // add code that returns an ArrayList<User> object of all users in the User table
      ArrayList<Book> books = new ArrayList();
      Book book;
      ConnectionPool pool = ConnectionPool.getInstance();
      Connection connection = pool.getConnection();
      ResultSet resultSet = null;
      try{
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM Books");
        while(resultSet.next()){
          Date date = resultSet.getDate("Due_Date");
          GregorianCalendar cal = new GregorianCalendar();
          cal.setTime(date);
          GregorianCalendar c = new GregorianCalendar();
          boolean o = cal.before(c);
          String overdue = "";
          if(o){
            overdue = "overdue";
          }
          book = new Book(resultSet.getString("Title"), cal, 
          overdue);
          String u = resultSet.getString("UserEmail");
          book.setUser(u);
          books.add(book);
        }
        return books;
      } catch (SQLException e){
          System.out.println(e);
          return null;
      } finally{
        DBUtil.closeResultSet(resultSet);
        pool.freeConnection(connection);
      }
    }  
}
