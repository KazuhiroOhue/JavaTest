<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインメニュー</title>
<script src="/reading_books/js/sample.js"></script>
</head>
<body>
	<p>
		<c:out value="${msg}" />
	</p>
	<h2>メインメニュー</h2>
	<form action="Create" method="GET">
		<input type="submit" name="action" value="新しい本の登録（まだ！！）">
	</form>
	<form action="Create" method="GET">
		<input type="submit" name="action" value="説明画面（まだ！！）">
	</form>
	<c:choose>
		<c:when test="${fn:length(bookList) == 0}">
		ここでは、持っている本を登録して、読書記録をつけることができます。<br>
		"本を登録する"ボタンをクリックしてみて下さい！<br>
		</c:when>
		<c:when test="${fn:length(bookList) != 0}">
			<form action="RRecordList" method="GET">
				<input type="hidden" name="userId" value="${userId}"> <input
					type="submit" value="読書記録一覧へ">
			</form>
			<table border="1">
				<caption>メモ一覧</caption>
				<tr>
					<th></th>
					<th>タイトル</th>
					<th>タイトル</th>

					<th>総ページ数</th>
					<th>読んだページ数</th>
					<th>達成率</th>
				</tr>
				<c:forEach var="b" items="${bookList}">
					<tr>
						<td><a href="BookInfo?bookId=${b.bookId}">${b.bookName}</a></td>

						<td><a>${b.bookCategory}</a></td>
						<td><img src="images/${b.imagePath}" height="50"></td>
						<td><a>${b.totalPages}ページ</a></td>
						<td><a>${b.readPages}ページ</a></td>
						<td><a>${b.percentage}%</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>

	<form action="Login" method="GET">
		<input type="submit" value="ログアウトする">
	</form>
</body>
</html>