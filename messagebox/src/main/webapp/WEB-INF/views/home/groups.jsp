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
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="${webRoot}/${initParam.resourceRoot}/css/bootstrap.min.css">

<style type="text/css">

.container{ 
	margin-left:0px;
	padding-left:150px;
	width: 90%;
}
#groups{
	background-color: #eeeeee;
}

#content{
	margin-top:0px;
}

#content-right{
	margin-top:20px;
	padding-left:30px
}
.group-list{
	
	border:1px solid #eeeeee;
	border-radius:5px;
	word-wrap:break-word; 
	word-break:break-all; 
	width:100%;
	padding-left:5px;

}
.group-list>p>span{
 margin-left:15px;
 margin-top:10px;
 border:1px solid #eeeeee;
 padding:5px 10px 5px 10px;
 border-radius:2px;
 display:inline-block;
}
.group-list>p>span:hover{
	cursor:default;
	border:2px solid #009966;
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
					<a href="${webRoot}/home/receives" class="list-group-item" id="receives">接收公告</a> 
					<a href="${webRoot}/home/send" class="list-group-item" id="announ-send">发布公告</a> 
					<a href="${webRoot}/home/announs" class="list-group-item" id="announs">已发布公告</a>
					<a href="${webRoot}/home/main" class="list-group-item" id="main">统计</a>
 				</div>
			</div>
			<div id="content-right" class="col-md-10" >
					<div class="add-group">
						<input type="text" name="groupName" id="groupName" placeholder="群组名"></input> <button type="button" id="btn-add" class="btn btn-default">添加</button>
					</div> 
					<hr>
					<div class="group-list">
						<p>
							<c:forEach items="${groups}" var="group">
								<span id="${group.key}">${group.value}</span>
							</c:forEach>
						</p>
					</div>
			</div>
		</div>
		<hr>
		<jsp:include page="../common/footer.jsp" />
	</div>

	<script src="${webRoot}/${initParam.resourceRoot}/js/jquery.min.js"></script>
	<script src="${webRoot}/${initParam.resourceRoot}/js/bootstrap.min.js"></script>
	<script src="${webRoot}/${initParam.resourceRoot}/js/groups.js"></script>
</body>

</html>
