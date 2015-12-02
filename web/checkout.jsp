<!--
Copyright 2015 Drew Bryant and Patrick Lathan
-->
<%@ include file="/includes/header.html" %>
    <h2>Checkout a book</h2>
    <form action="library" method="post">
      <p>
      <input type="hidden" name="action" value="checkout">
      <label>First Name:</label>
      <input type="text" name="firstname" size="50" required>
      </p>
      <br>
      <p>
      <label>Last Name:</label>
      <input type =" text" name ="lastname" size="50" required>
      </p>
      <br>
      <p>
      <label>Email Address:</label>
      <input type="text" name="email" size="50" required>
      </p>
      <br>
      <p>
      <label>Book Title:</label>
      <input type="text" name="title" size="75" required>
      </p>
      <br>
      <input type="submit" value="Checkout">
    </form>
 <%@ include file="/includes/footer.html" %>