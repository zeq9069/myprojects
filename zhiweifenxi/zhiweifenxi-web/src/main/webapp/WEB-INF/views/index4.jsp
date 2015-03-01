<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="webRoot"  value="${pageContext.request.contextPath}"/>
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
<link href="${webRoot}/${initParam.resourceRoot}/css/dynamic.css" rel="stylesheet">

<style type="text/css">

</style>
</head>
<body>
	<jsp:include page="common/head.jsp" />

	<div class="container" style="margin-top: 100px">
		<div class="row">
			<div class="col-md-12" >
				<div class="row" >
					<div class="list-group">
						<div class="list-group-item" id="dynamic-head">
						网站动态
						</div>
				 		<div class="list-group-item">
				 			<div class="row">
								<div class="row featurette">
									<div class="col-md-10">
										<h2 class="featurette-heading" id="topic-title">
											主机
										</h2>
										<p id="topic-date">2014-12-12 12:05</p>
										<p class="lead" id="topic-content">
											支持创建、删除、启动、关机、重启、重置系统、更换系统、调整配置等功能，便于用户在web端远程操控服务器。
										</p>							
									</div>
								</div>
				 			</div>
				 		</div>
				 		<div class="list-group-item">
				 			<div class="row">
								<div class="row featurette">
									<div class="col-md-10">
										<h2 class="featurette-heading" id="topic-title">
											主机
										</h2>
										<p id="topic-date">2014-12-12 12:05</p>
										<p class="lead" id="topic-content">
											支持创建、删除、启动、关机、重启、重置系统、更换系统、调整配置等功能，便于用户在web端远程操控服务器。
										</p>							
									</div>
								</div>
				 			</div>
				 		</div>
				 		<div class="list-group-item">
				 			<div class="row">
								<div class="row featurette">
									<div class="col-md-10">
										<h2 class="featurette-heading" id="topic-title">
											主机
										</h2>
										<p id="topic-date">2014-12-12 12:05</p>
										<p class="lead" id="topic-content">
											支持创建、删除、启动、关机、重启、重置系统、更换系统、调整配置等功能，便于用户在web端远程操控服务器。
										</p>							
									</div>
								</div>
				 			</div>
				 		</div>
				 		<a href="#" class="list-group-item glyphicon glyphicon-menu-down" style="text-align:center ;"></a>
 					</div>
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
