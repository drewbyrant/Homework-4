<!--
Copyright 2015 Drew Bryant and Patrick Lathan
-->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/includes/header.html" %>
    <p>Thank you for your patronage of the Belk Library.  You've successfully 
    checked out the book, ${book.title}.  Please note that this book is due back 
    on <fmt:formatDate value="${book.dueDate.time}" type="date" />.  A friendly 
    email reminder will be sent to you if your book 
    becomes overdue</p>
    
    <a href="library?action=front">Return to front page</a>
 <%@ include file="/includes/footer.html" %>