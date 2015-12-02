/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import java.sql.*;
import business.Book;
import java.util.GregorianCalendar;
/**
 *
 * @author drewbryant
 */
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
            //String sDate = book.getDueDate().toString();
            GregorianCalendar cal = book.getDueDate();
           // Date date = new Date(cal.getY)
            java.util.Date date = cal.getTime();
            java.sql.Date sDate = new java.sql.Date(date.getTime());
            ps.setDate(2, sDate);
            if(book.getIsOverdue() == true){
              ps.setString(3, "overdue");
            }else{
              ps.setString(3, "");
            }
            ps.setString(4, book.getUser().getEmail());
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
                book.setIsOverdue(rs.getBoolean("Overdue"));
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
}
