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
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <c:forEach var="book" items="${books}">
      <tr>
        <td>${book.user.firstName} ${book.user.lastName}</td>
        <td>${book.user.email}</td>
        <td>${book.title}</td>
        <td><fmt:formatDate value="${book.dueDate.time}" type="date" /></td>
        <td>${book.overdue}</td>
        <td>
          <form action="library" method="post">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="title" value="${book.title}">
            <input type="submit" value="Check in">
          </form>
        </td>
      </tr>
      </c:forEach>
    </table>
    <a href="library?action=front">Return to front page</a>
<%@ include file="/includes/footer.html" %>