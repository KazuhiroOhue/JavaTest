<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/reading_books/css/style.css"
	type="text/css">
<title>編集中</title>
</head>
<body>
	<form action="EditBookInfo" method="GET">
		タイトル：<br> <input type="text" name="eBookName" class="title"
			value="${eBookName}" maxlength="50" required="required"><br>
		購入日：<br> <input type="date" name="ePurchaseDate"
			value="${ePurchaseDate}"> ジャンル：<br> <select
			name="eBookCategory">
			<option value="学習参考書">学習参考書</option>
			<option value="専門書">専門書</option>
			<option value="文学・文芸">文学・文芸</option>
			<option value="ビジネス">ビジネス</option>
			<option value="趣味">趣味</option>
			<option value="マンガ">マンガ</option>
			<option value="その他">その他</option>
		</select> <br> ページ数：<br> <input type="number" name="eTotalPages"
			value="${eTotalPages}"> 本についてのメモ：<br>
		<textarea name="eBookMemos" maxlength="100" required="required"
			placeholder="100文字以内でお願いします！">${eBookMemos}</textarea>
		<br> <input type="submit" name="action" value="変更する">
	</form>
	<form action=EditBookInfo method="POST">
		<input type="submit" name="action" value="やめる">
	</form>
</body>
</html>