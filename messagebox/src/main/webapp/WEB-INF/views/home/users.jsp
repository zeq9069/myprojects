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
<title>messageBox</title>
	<link href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
	<link href="${webRoot}/${initParam.resourceRoot}/css/jBootsrapPage.css" rel="stylesheet" />
	<script src="${webRoot}/${initParam.resourceRoot}/js/jquery.min.js"></script>
	<script src="${webRoot}/${initParam.resourceRoot}/js/bootstrap.min.js"></script>
	<script src="${webRoot}/${initParam.resourceRoot}/js/jBootstrapPage.js"></script>
<style type="text/css">
.container {
	margin-left: 0px;
	padding-left: 150px;
	width: 90%;
}
#users{
	background-color: #eeeeee;
}
#content {
	margin-top: 0px;
}

#content-right {
	padding-top: 20px;
	padding-left: 30px
}

.user_table {
	margin-top: 40px; line-height : 30px;
	text-align: center;
	width: 100%;
	line-height: 30px;
}

.user_table>thead {
	background-color: #eeeeee;
}

.user_table>tr>td {
	padding-top: 5px;
	width: 20px;
}

.user_table span {
	border:1px solid #eeeeee;
	border-radius:5px;
	margin-left:3px;
	padding:2px 2px 2px 2px;
}
form>span{
	margin-left:30px;
}
tr:hover{
	background-color: #eeeeee;

}
 
</style>

</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">messageBox</span> <span class="icon-bar"></span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand " href="${webRoot}/">messageBox</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">admin
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">基本信息</a></li>
							<li><a href="#">通知</a></li>
							<li class="divider"></li>
							<li><a href="#">退出</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>










	<div id="content" class="container"
		style="padding-top: 50px; height: 100%; width: 100%; padding-left: 0px">
		<div class="row" style="width: 100%; padding-left: 0px">
			<div class="col-md-2"
				style="border: 2px solid #eeeeee; height: 800px; border-bottom: 0px;">
				<div class="list-group"
					style="text-align: center; padding-top: 30px; height: 50%;">
					<span class="list-group-item active" style="font-size: 25px">控制台操作</span>
					<a href="${webRoot}/home/groups" class="list-group-item"
						id="groups">群组操作</a> <a href="${webRoot}/home/users"
						class="list-group-item" id="users">用户操作</a> <a
						href="${webRoot}/home/send" class="list-group-item"
						id="announ-send">发布公告</a> <a href="${webRoot}/home/announs"
						class="list-group-item" id="announs">已发布公告</a>
				</div>
			</div>
			<div id="content-right" class="col-md-10">
			
			
			
				<div class="body">
					筛选：<select name="groups" id="group-select" >
						<option id="all" >全部</option>
						<c:forEach items="${resultMap.groups}" var="group">
							<option id="${group.key}" value="${group.value}">${group.value}</option>
						</c:forEach>
					</select>
					
					<table class="user_table">
						<thead>
							<tr>
								<td>用户名</td>
								<td>姓名</td>
								<td>工作单位</td>
								<td>部门</td>
								<td>职务</td>
								<td>邮箱</td>
								<td>手机</td>
								<td>电话</td>
								<td>所属分组</td>
							</tr>
						</thead>
						<c:forEach items="${resultMap.users}" var="user">
							<tr>
								<td>${user.username}</td>
								<td>${user.realName}</td>
								<td>${user.orgName}</td>
								<td>${user.department}</td>
								<td>${user.jobTitle}</td>
								<td>${user.email}</td>
								<td>${user.mobilePhone}</td>
								<td>${user.officePhone}</td>
								<td class="group-td">
								<c:if test="${!empty user.relations}" >
										<c:forEach items="${user.relations}" var="relation">
											<span data-id="group_label" data-user="${user.id}" id="${relation.group.id}">${relation.group.name}</span>
										</c:forEach>
								</c:if>
								<c:if test="${empty user.relations}" >
									<span>未分组</span>
								</c:if>
								<span id="${user.id}" class="group_plus glyphicon glyphicon-plus" data-toggle="modal"
						data-target="#groupModel"></span>
								</td>
							</tr>
						</c:forEach>
					</table>
				<div style="padding-left:100px;text-align:center">
					<ul class="pagination pagination-sm"></ul>
    			</div>

				</div>
			</div>
		</div>
		
		<!-- model 登录模态框-->
				<div class="modal fade" id="groupModel" tabindex="-1" role="dialog"
					aria-labelledby="groupLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="groupLabel">选择分组</h4>
							</div>
							<div class="modal-body">
								<form class="group-form">
									<p>
										<c:forEach items="${resultMap.groups}" var="group">
											<input type="checkbox" name="groupName" id="${group.key}"  value="${group.value}">${group.value}
										</c:forEach>
									</p>
									<hr>
									<p>
										<button type="button" id="group_submit" class="btn btn-primary" data-dismiss="modal">确认</button>
									</p>
									<input type="hidden" name="u_id" id="u_id" value="">
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
							</div>
						</div>
					</div>
				</div>
		
		<hr>
		<jsp:include page="../common/footer.jsp" />
	</div>
</body>

<script type="text/javascript">

$(function(){
/*
 *pageSize:每页多少条记录
 *buttons:显示多少个按钮
 *total:总页数
 */
 var psize=${resultMap.pageSize<=0?1:resultMap.pageSize};
 var item=5;
 var count=${resultMap.count<=0?1:resultMap.count};
 createPage(psize, item, count);

function createPage(pageSize, buttons, total) {
    $(".pagination").jBootstrapPage({
        pageSize : pageSize,
        total : total,
        maxPageButton:buttons,
        onPageClicked: function(obj, pageIndex) {
        	
        },
    });
}
});

$(document).ready(function(){
	var u_id;//user id
	$(".group_plus").click(function(){
		//初始化
		u_id=this.id;
		$("#u_id").attr("value",u_id);
	});
	//model模态框提交
	$("#group_submit").click(function(){
		if(u_id==null){
			return ;
		}
		var groupArray= new Array();
		var i=0;
		 $('input[name="groupName"]:checked').each(function(){
			 groupArray[i++]=$(this).val();
				}); 
		 
		 $.post("/messagebox/system/users/group/add",$(".group-form").serialize(),function(data){
			 alert(data);
			 if(data.status=="success"){
				 alert("添加成功");
				 for(var i=0;i<groupArray.length;i++){
					 $(".group-td").html("<span>"+groupArray[i]+"</span>"+ $(".group-td").html());
				 }
			 }else{
				 alert(data.message);
			 }
		 });
	});
	
	$("span[data-id='group_label']").dblclick(function(){
		var u_id=$(this).attr("data-user");
		var groupName=$(this).text().trim();
		alert(u_id+groupName);
		$.post("/messagebox/system/users/group/delete",{groupName:groupName,u_id:u_id},function(data){
			if(data.status=="success"){
				alert("删除成功");
				$(this).remove();
			}else{
				alert("删除失败");
			}
		});
	});
	
	
});
</script>

</html>



