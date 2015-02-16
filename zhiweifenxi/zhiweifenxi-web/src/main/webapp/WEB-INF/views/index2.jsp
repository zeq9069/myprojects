<%@page contentType="text/html; charset=utf-8"%>
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
			<h1>职位分析</h1>
			<p>这是一个为您提供职位分析的站点,通过对各大招聘网站职位和公司的抓取与分析，为您提供实时的职位选择方案！让您在选择职位的时候不在盲目海投，为双方节省时间！</p>
			<p>
				<a class="btn btn-primary btn-lg" href="/${webRoot}/page/index5" role="button">控制台 &raquo;</a>
			</p>
		</div>
	</div>

	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
			<div class="col-md-4">
				<h2>实时</h2>
				<p>我们会在每天、每周、每月为您提供实时的职位的动态变化，并提供过去的职位变化的趋势图，为您找到 一个满意的工作而提供决策！</p>
				<p>
					<a class="btn btn-default" href="#" role="button">详情 &raquo;</a>
				</p>
			</div>
			<div class="col-md-4">
				<h2>个性化</h2>
				<p>为了能为用户提供良好的服务，我们会针对不同的用户提供个性的职位分析数据，尽最大努力让实现个性
					化，让用户早日找到自己心仪的职位。</p>
				<p>
					<a class="btn btn-default" href="#" role="button">详情 &raquo;</a>
				</p>
			</div>
			<div class="col-md-4">
				<h2>专注</h2>
				<p>本站专注于职位的分析，为广大用户提供最专业的数据！</p>
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
