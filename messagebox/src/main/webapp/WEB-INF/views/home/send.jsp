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
<link href="${webRoot}/${initParam.resourceRoot}/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
.container {
	margin-left: 0px;
	padding-left: 150px;
	width: 90%;
}

#announ-send {
	background-color: #eeeeee;
}

#content {
	margin-top: 0px;
}

#content-right {
	padding-top: 20px;
	padding-left: 30px
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
						class="list-group-item" id="announs">已发布公告</a> <a
						href="${webRoot}/home/main" class="list-group-item" id="main">统计</a>
				</div>
			</div>
			<div id="content-right" class="col-md-10">
				<div class="body">
					<form id="announ_form" name="announ_form">
						<p>
							<input type="text" name="title" id="title" placeholder="标题名">
						</p>
						<p>
							<c:forEach items="${groups}" var="group">
								<input type="checkbox" name="group" id="${group.key}"
									value="${group.value}">${group.value}
</c:forEach>
						</p>
						<p>
							<select id="type" name="type">
								<option value="1">未知类型</option>
							</select>
						</p>
						<p>
							<input type="hidden" name="publisher" id="publisher"
								value="admin">
						</p>
						<p>
							<input type="radio" name="online" id="online" value="true"
								checked="checked">上线 <input type="radio" name="online"
								id="online" value="false">暂不上线
						</p>
						<p>
							<input type="hidden" name="content" id="content" />
							  <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
						</p>
						<p>
							<button type="button" id="announ_submit" name="announ_submit">发布</button>
						</p>
					</form>
				</div>
			</div>
		</div>
		<hr>
		<jsp:include page="../common/footer.jsp" />
	</div>
	
	
	<div id="btns">
    <div>
         <button onclick="getContent()">获得内容</button>
         <button onclick="getContentTxt()">获得纯文本</button>
        <button onclick="getPlainTxt()">获得带格式的纯文本</button>
        <button onclick="hasContent()">判断是否有内容</button>
 
    </div>
    <div>
        <button onclick="getText()">获得当前选中的文本</button>
     </div>

</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<script src="${webRoot}/${initParam.resourceRoot}/js/jquery.min.js"></script>
	<script src="${webRoot}/${initParam.resourceRoot}/js/bootstrap.min.js"></script>
	<script src="${webRoot}/${initParam.resourceRoot}/js/send.js"></script>

	<script type="text/javascript" charset="utf-8"
		src="${webRoot}/${initParam.resourceRoot}/js/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="${webRoot}/${initParam.resourceRoot}/js/ueditor/ueditor.all.min.js">
		
	</script>
	
	
	
	
<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
  
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    
   
 

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    
  
    
</script>
</body>
</html>