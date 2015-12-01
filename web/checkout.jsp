<%-- 
    Document   : checkout
    Created on : Nov 30, 2015, 12:30:26 PM
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
    <h2>Checkout a book</h2>
    <form action="/library" method="post">
      <input type="hidden" name="action" value="checkout">
      First Name:
      <input type="text" name="firstname" size="50" required>
      <br>
      Last Name:
      <input type =" text" name ="lastname" size="50" required>
      <br>
      Email Address:
      <input type="text" name="email" size="50" required>
      <br>
      Book Title:
      <input type="text" name="title" size="75" required>
      <br>
      <input type="submit" value="Checkout">
    </form>
  </body>
</html>
