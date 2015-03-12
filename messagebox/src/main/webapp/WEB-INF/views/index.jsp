<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="webRoot"   value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>职位分析</title>
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${webRoot}/${initParam.resourceRoot}/css/home.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="common/head.jsp" />
	
	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>信箱</h1>
			<p>方便快捷的通过接口整合到你的项目中，通过管理员向所有分组用户发送公告通知！</p>
			<p>
				<a class="btn btn-primary btn-lg" href="${webRoot}/home/main" role="button">控制台 &raquo;</a>
			</p>
		</div>
	</div>

	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
			<div class="col-md-4">
				<h2>实时</h2>
				<p>能够让你发布的消息在用户登录的时候看到提示！</p>
				<p>
					<a class="btn btn-default" href="#" role="button">详情 &raquo;</a>
				</p>
			</div>
			<div class="col-md-4">
				<h2>安全</h2>
				<p>提供有安全的用户接口，通过认证即可获取信息</p>
				<p>
					<a class="btn btn-default" href="#" role="button">详情 &raquo;</a>
				</p>
			</div>
			<div class="col-md-4">
				<h2>方便快捷</h2>
				<p>一切操作如此方便，简单几步即可发布自己的公告！</p>
				<p>
					<a class="btn btn-default" href="#" role="button">详情 &raquo;</a>
				</p>
			</div>
		</div>
		<hr>
		<jsp:include page="common/footer.jsp" />
	</div>
	<script src="${webRoot}/${initParam.resourceRoot}/jquery.min.js"></script>
	<script  src="${webRoot}/${initParam.resourceRoot}/js/bootstrap.min.js"></script>
</body>
</html>
