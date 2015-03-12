<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="webRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>职位分析</title>
<link href="${webRoot}/${initParam.resourceRoot}/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${webRoot}/${initParam.resourceRoot}/css/home.css"
	rel="stylesheet">
<link href="${webRoot}/${initParam.resourceRoot}/css/service.css"
	rel="stylesheet">
<style type="text/css">
.login-container {
	align: center;
	padding-left: 30%;
}

.login-body {
	text-align: center;
	border: 1px solid #eeeeee;
	border-radius: 10px;
	width: 50%;
	padding-top: 20px;
}

.reg-form{
	display:none;

}
</style>
</head>
<body>
	<jsp:include page="common/head.jsp" />

	<div class="container" style="margin-top: 100px; height: 800px">
		<div class="row">
			<div class="login-container">
				<div class="login-body">
					<form class="login-form">
						<p>
							用户名：<input type="text" name="name" placeholder="用户名">
						</p>
						<p>
							密 &nbsp; 码：<input type="password" name="password"
								placeholder="密码">
						</p>
						<p>
							<button type="button" class="btn btn-primary">登录</button>
							<button type="reset" class="btn btn-default">撤销</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)">注册？</a>
						</p>
					</form>
					<form class="reg-form">
						<p>
							用户名：<input type="text" name="name" placeholder="用户名">
						</p>
						<p>
							密 &nbsp; 码：<input type="password" name="password"
								placeholder="密码">
						</p>
						<p>
							密 &nbsp; 码：<input type="password" name="confirmpassword"
								placeholder="确认密码">
						</p>
						<p>
							<button type="button" class="btn btn-primary">注册</button>
							<button type="reset" class="btn btn-default">撤销</button>
						</p>
					</form>
				</div>
			</div>
		</div>
		<hr>
		<jsp:include page="common/footer.jsp" />
	</div>

	<script src="${webRoot}/${initParam.resourceRoot}/js/jquery.min.js"></script>
	<script src="${webRoot}/${initParam.resourceRoot}/js/bootstrap.min.js"></script>
</body>
</html>
