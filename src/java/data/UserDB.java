/*
 * Copyright 2015 Andrew Bryant & Patrick Lathan
 */
package data;

import java.sql.*;
import business.User;
import java.util.ArrayList;

public class UserDB {
  public static int insert(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO Users (Email, FirstName, LastName) "
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail().trim());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
  public static int delete(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM Users "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
  public static boolean emailExists(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        connection = pool.getConnection();
        if(connection == null){
          System.out.println("connection is null");
        }
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT Email FROM Users "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
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
  public static User selectUser(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Users "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
  public static ArrayList<User> selectUsers() {
      ArrayList<User> users = new ArrayList();
      User user;
      ConnectionPool pool = ConnectionPool.getInstance();
      Connection connection = pool.getConnection();
      ResultSet resultSet = null;
      try{
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM Users");
        while(resultSet.next()){
          user = new User(resultSet.getString("Title"), resultSet.getString("LastName"),
          resultSet.getString("Email"));
          users.add(user);
        }
        return users;
      } catch (SQLException e){
          System.out.println(e);
          return null;
      } finally{
        DBUtil.closeResultSet(resultSet);
        pool.freeConnection(connection);
      }
    }    
}
