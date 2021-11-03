<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.User, model.Mutter, java.util.List" %>
<%
	// セッションスコープに保存されたユーザ情報を取得
	// User loginUser = (User) session.getAttribute("loginUser");
	// アプリケーションスコープに保存されたつぶやきリストを取得
	// List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
	// Integer count = (Integer) application.getAttribute("count");
	// リクエストスコープに保存されたエラーメッセージを取得
	// String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
	<h1>どこつぶメイン</h1>
	<p>
		${loginUser.name} さん、ログイン中 
		<a href="/example/Logout">ログアウト</a>
		<p>訪問回数：${count}</p>
	</p>
	<p><a href="/example/Main">更新</a></p>
	<form action="/example/Main" method="post">
		<input type="text" name="text">
		<input type="submit" value="つぶやく">
	</form>
	<c:if test="${not empty errorMsg }">
		<p>${errorMsg }</p>
	</c:if>
	<c:forEach var="mutter" items="${mutterList }">
		<p>
			<c:out value="${mutter.userName }" />：
			<c:out value="${mutter.text }" />
		</p>
	</c:forEach>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>