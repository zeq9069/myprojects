<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
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
<title>messageBox</title>
<link href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
.container{ 
	margin-left:0px;
	padding-left:150px;
	width: 90%;
}

#content{
	margin-top:0px;
}

#content-right{
	padding-top:20px;
	padding-left:30px
}

.online_table {
	margin-top: 40px; line-height : 30px;
	text-align: center;
	width: 100%;
	line-height: 30px;
}

.online_table>thead {
	background-color: #eeeeee;
}

.online_table>tr>td {
	padding-top: 5px;
	width: 20px;
}

.online_table span {
	border:1px solid #eeeeee;
	border-radius:5px;
	margin-left:3px;
	padding:2px 2px 2px 2px;
}

tr:hover{
	background-color: #eeeeee;

}
 
</style>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header" >
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">messageBox</span> 
					<span class="icon-bar"></span>
                    <span class="icon-bar"></span> 
                    <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand " href="${webRoot}/">messageBox</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right" >
					<li class="dropdown">
						<a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">admin
								<span class="caret"></span>
						</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">基本信息</a></li>
							<li><a href="#">通知</a></li>
							<li class="divider"></li>
							<li><a href="#">退出</a></li>
						</ul>
				   	</li>
				</ul>
			</div>
		</div>
	</nav>


	<div id="content" class="container" style="padding-top:50px;height:100%;width: 100%;padding-left:0px">
		<div class="row" style="width: 100%;padding-left:0px" >
			<div class="col-md-2" style="border:2px solid #eeeeee;height:800px;border-bottom:0px;">
 				<div class="list-group" style="text-align:center;padding-top:30px;height:50%;">
					<span  class="list-group-item active" style="font-size: 25px">控制台操作</span>
					<a href="${webRoot}/home/groups" class="list-group-item" id="groups">群组操作</a> 
					<a href="${webRoot}/home/users" class="list-group-item" id="users">用户操作</a> 
					<a href="${webRoot}/home/send" class="list-group-item" id="announ-send">发布公告</a> 
					<a href="${webRoot}/home/announs" class="list-group-item" id="announs">已发布公告</a>
 				</div>
			</div>
			<div id="content-right" class="col-md-10" >
					<div class="body">
					
					<table class="online_table">
						<thead>
							<tr>
								<td>标题</td>
								<td>发布者</td>
								<td>发布时间</td>
								<td>类型</td>
								<td>内容</td>
								<td>群组</td>
								<td>是否上线</td>
							</tr>
						</thead>
						<c:forEach items="${announs_online}" var="announ">
							<tr>
								<td>${announ.title}</td>
								<td>${announ.publisher}</td>
								<td>${announ.date}</td>
								<td>${announ.type}</td>
								<td>${announ.content}</td>
								<td>
									<c:forEach items="${announ.group_announs}" var="group_announ">
										<c:if test="${!empty group_announ}">
											<c:if test="${!empty group_announ.group}">
												<span id="${group_announ.group.id}">${group_announ.group.name}</span>
											</c:if>	
										</c:if>									
									</c:forEach>
								</td>
								<td>${announ.online}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<hr>
		<jsp:include page="../common/footer.jsp" />
	</div>

	<script src="${webRoot}/${initParam.resourceRoot}/js/jquery.min.js"></script>
	<script src="${webRoot}/${initParam.resourceRoot}/js/bootstrap.min.js"></script>
</body>
</html>
