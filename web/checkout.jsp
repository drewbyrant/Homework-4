<!--
Copyright 2015 Drew Bryant and Patrick Lathan
-->
<%@ include file="/includes/header.html" %>
    <h2>Checkout a book</h2>
    <form action="library" method="post">
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
 <%@ include file="/includes/footer.html" %>