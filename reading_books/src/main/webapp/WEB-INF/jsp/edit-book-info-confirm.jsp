<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>変更内容の確認</title>
</head>
<body>
	<p>${eBookName}</p>
	<br>
	<p>購入日：${ePurchaseDate}</p>
	<br>
	<p>ジャンル：${eBookCategory}</p>
	<br>
	<p>ページ数’${eTotalPages}</p>
	<br>
	<p>${eBookMemos}</p>
	<br>


	<form action="EditBookInfo" method="POST">
		<input type="submit" name="action" value="編集画面に戻る">
	</form>
</body>
</html>