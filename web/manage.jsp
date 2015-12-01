<!--
Copyright 2015 Drew Bryant and Patrick Lathan
-->
<%@ include file="/includes/header.html" %>
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
<%@ include file="/includes/footer.html" %>