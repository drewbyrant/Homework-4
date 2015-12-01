<%-- 
    Document   : manage
    Created on : Nov 30, 2015, 12:39:22 PM
    Author     : drewbryant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <h2>Currently checked out books</h2>
    <table>
      <tr>
        <th>Patron Name</th>
        <th>Email Address</th>
        <th>Book Title</th>
        <th>Due Date</th>
        <th>Overdue</th>
        <th> </th>
      </tr>
    </table>
    <a href="library?action=front">Return to front page</a>
  </body>
</html>
