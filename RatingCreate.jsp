<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Rating</title>
</head>
<body>
	<form action="RatingCreate" method="post">
		<h1>Write down your Id and the movie you want to rate</h1>
		<p>
			<label for="userId">userId</label>
			<input id="userId" name="userId" value="${fn:escapeXml(param.userId)}">
		</p>
				<p>
			<label for="Title">Title</label>
			<input id="Title" name="Title" value="${fn:escapeXml(param.Title)}">
		</p>
		<p>
			<label for="score">score</label>
			<input id="score" name="score" value="${fn:escapeXml(param.score)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="RatingCreate"><a href="RatingCreate">Create Ratings</a></div>
	<br/>
        <table border="1">
            <tr>
                <th>ratingId</th>
                <th>score</th>
                <th>userId</th>
                <th>movieName</th>
                <th>Date</th>
                <th>Delete Rating</th>
                <th>Update Rating</th>
            </tr>
            <c:forEach items="${Ratings}" var="Ratings" >
                <tr>
                    <td><c:out value="${Ratings.getRatingId()}" /></td>
                    <td><c:out value="${Ratings.getScore()}" /></td>
                    <td><c:out value="${Ratings.getUser().getUserId()}" /></td>
                    <td><c:out value="${Ratings.getMovie().getTitle()}" /></td>
                    <td><fmt:formatDate value="${Ratings.getTimes()}" pattern="yyyy-MM-dd"/></td>
                    <td><a href="Delete Rating?">Delete</a></td>
                    <td><a href="Update Rating?">Update</a></td>
                </tr>
            </c:forEach>
       </table>

</body>
</html>