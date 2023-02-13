<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>変更内容の確認</title>
</head>
<body>
	<h2>この内容でよろしいでしょうか？</h2>
	<p>タイトル：${eBookName}</p>
	<br>
	<p>
		購入日：
		<c:choose>
			<c:when test="${empty ePurchaseDate}">
				不明
			</c:when>
			<c:otherwise>
	        	${ePurchaseDate}
	    	</c:otherwise>
		</c:choose>
	</p>
	<br>
	<p>ジャンル：${eBookCategory}</p>
	<br>
	<p>ページ数：${eTotalPages}</p>
	<br>
	<p>${eBookMemos}</p>
	<br>
	<form action="EditBookInfo" method="GET">
		<input type="submit" name="action" value="この内容で確定">
	</form>
	<form action="EditBookInfo" method="POST">
		<input type="submit" name="action" value="編集画面に戻る">
	</form>
</body>
</html>