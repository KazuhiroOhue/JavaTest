<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>読書記録一覧</title>
</head>
<body>
	<c:choose>
		<c:when test="${fn:length(rRecordList) == 0}">
		まだ記録はありません<br>
		</c:when>
		<c:when test="${fn:length(rRecordList) != 0}">
			<table border="1">
				<caption>読書記録一覧</caption>
				<tr>
					<th>読書した日</th>
					<th>読んだ本</th>
					<th>ページ数</th>
					<th>メモ</th>
				</tr>
				<c:forEach var="r" items="${rRecordList}">
					<tr>
						<td><a>${r.readingDate}</a></td>
						<td><a>${r.bookId}</a></td>
<%-- 						<td><a>${r.bookname}ページ</a></td> --%>
						<td><a>${r.readPages}ページ</a></td>
						<td><a>${r.readingMemos}</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>
	
	<form action="RRecordList" method="POST">
		<input type="submit" value="一覧にもどる">	
	</form>
</body>
</html>