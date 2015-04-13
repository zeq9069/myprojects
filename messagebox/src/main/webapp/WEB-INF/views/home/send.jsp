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

<link href="${webRoot}/${initParam.resourceRoot}/css/bootstrap.min.css" rel="stylesheet">
<link href="${webRoot}/${initParam.resourceRoot}/css/select2.css" rel="stylesheet" />
<link href="${webRoot}/${initParam.resourceRoot}/css/select2-bootstrap.css" rel="stylesheet" />


	
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
#title-error{
color: red;
}
.result_list{
border:1px solid #eeeeee;
border-radius:5px;
display:block;
padding-left:10px;
line-height: normal;
padding-top:10px;
padding-bottom: 10px;

}

.result_list > div{
display:list-item;
margin-top:30px;
margin-bottom: 30px;
padding-left:0px;
line-height: normal;
padding-bottom: 10px;
margin-left:1px;
}


.result_list > div > span{
margin-left:15px;
 margin-top:10px;
 border:1px solid #eeeeee;
 padding:5px 10px 5px 10px;
 border-radius:2px;
 display:inline-block;
}
select > targetYxdm{
 width: 100px;
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
					<span  class="list-group-item active" style="font-size: 25px">控制台操作</span>
					<a href="${webRoot}/home/receives/notlookover" class="list-group-item" id="notlookover">待查看</a> 
					<a href="${webRoot}/home/receives/lookover" class="list-group-item" id="lookover">已查看</a> 
					<a href="${webRoot}/home/announs" class="list-group-item" id="announs">已发布公告</a>
					<a href="${webRoot}/home/send" class="list-group-item" id="announ-send">发布公告</a> 
					<a href="${webRoot}/home/main" class="list-group-item" id="main">统计</a>
				</div>
			</div>
			<div id="content-right" class="col-md-10">
				<div class="body">
					<form id="announ_form" name="announ_form">
						<p>
							<input type="text" name="title" id="title" placeholder="标题名" class="required" >
						</p>
						<p>
							<c:forEach items="${groups}" var="group">
								<input type="checkbox" name="group"  id="${group.key}"
									value="${group.value}">${group.value}
</c:forEach>
						</p>
						<p>
							省：
							<select name="targetProvinceCode">
								<option>请选择</option>
								<optgroup label="A">
									<option value="34">安徽省</option>
									<option value="82">澳门特别行政区</option>
								</optgroup>
								
								<optgroup label="B">
									<option value="11">北京市</option>
								</optgroup>
								<optgroup label="C">
									<option value="50">重庆市</option>
								</optgroup>
								<optgroup label="F">
									<option value="35">福建省</option>
								</optgroup>
								<optgroup label="G">
									<option value="62">甘肃省</option>
									<option value="44">广东省</option>
									<option value="52">贵州省</option>
									<option value="45">广西壮族自治区</option>
								</optgroup>
								
								<optgroup label="H">
									<option value="13">河北省</option>
									<option value="41">河南省</option>
									<option value="42">湖北省</option>
									<option value="43">湖南省</option>
									<option value="46">海南省</option>
									<option value="23">黑龙江</option>
								</optgroup>
								<optgroup label="J">
									<option value="22">吉林省</option>
									<option value="32">江苏省</option>
									<option value="36">江西省</option>
								</optgroup>
								<optgroup label="L">
									<option value="21">辽宁省</option>
								</optgroup>
								<optgroup label="N">
									<option value="15">内蒙古自治区</option>
									<option value="64">宁夏回族自治区</option>
								</optgroup>
								<optgroup label="Q">
									<option value="63">青海省</option>
								</optgroup>
								<optgroup label="S">
									<option value="31">上海市</option>
									<option value="14">山西省</option>
									<option value="37">山东省</option>
									<option value="51">四川省</option>
									<option value="61">陕西省</option>
								</optgroup>
								<optgroup label="T">
									<option value="12">天津市</option>
									<option value="71">台湾省</option>
								</optgroup>
								<optgroup label="X">
									<option value="81">香港特别行政区</option>
									<option value="54">西藏自治区</option>
									<option value="65">新疆维吾尔自治区</option>
								</optgroup>
								<optgroup label="Y">
									<option value="53">云南省</option>
								</optgroup>
								<optgroup label="Z">
									<option value="33">浙江省</option>
								</optgroup>
							</select>
							<button type="button" name="add_targetProvinceCode">添加</button>
						</p>
						<p>
							学校类型：
							<select name="targetYxlx">
								<option value="0">请选择</option>
								<option value="">211高校</option>
								<option value="">985高校</option>
								<option value="">省属高校</option>
								<option value="">部属高校</option>
							</select>
							<button type="button" name="add_targetYxlx">添加</button>
						</p>
						<p>
							学校：<input type="text" name=targetYxdm class="targetYxdm"><button type="button" name="add_targetYxdm">添加</button>
							<!-- <select class="targetYxdm" name="targetYxdm">
  								<option value="www" selected="selected">请选择</option>
							</select> -->
						</p>
						<p>
							公告类型：<input type="text" name="type" ><button type="button" name="add_type">添加</button>
						</p>
						<div class="result_type">
						</div>
						<div class="result_list">
							<p>已选择：</p>
							<div id="result_targetProvinceCode">
								<span>河南省</span><span>北京市</span>
								<span>河南省</span><span>北京市</span>
								<span>河南省</span><span>北京市</span>
								<span>河南省</span><span>北京市</span>
								<span>河南省</span><span>北京市</span>
								<span>河南省</span><span>北京市</span>
								<span>河南省</span><span>北京市</span>
								<span>河南省</span><span>北京市</span>
								<span>河南省</span><span>北京市</span>
								<span>河南省</span><span>北京市</span>
								<span>河南省</span><span>北京市</span>
							</div>
							<div id="result_targetYxdm">
								<span>郑州大学</span><span>北京大学</span>
							</div>
							<div id="result_targetYxlx">
								<span>公告</span><span>紧急</span>
							</div>
						</div>
						<p>
							  <script id="editor" type="text/plain" style="width:1024px;height:500px;" ></script>
						</p>
						<p>
							<button type="submit" id="announ_submit" name="announ_submit">发布</button>
						</p>
					</form>
				</div>
			</div>
		</div>
		<hr>
		<jsp:include page="../common/footer.jsp" />
	</div>
	<script src="${webRoot}/${initParam.resourceRoot}/js/jquery.min.js"></script>
	<script src="${webRoot}/${initParam.resourceRoot}/js/bootstrap.min.js"></script>
	<script src="${webRoot}/${initParam.resourceRoot}/js/validate/jquery.validate.js"></script>
		<script src="${webRoot}/${initParam.resourceRoot}/js/validate/message.js"></script>
		<script src="${webRoot}/${initParam.resourceRoot}/js/select2.min.js"></script>
	<script src="${webRoot}/${initParam.resourceRoot}/js/send.js"></script>
	
	
	<script type="text/javascript" charset="utf-8" src="${webRoot}/${initParam.resourceRoot}/js/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${webRoot}/${initParam.resourceRoot}/js/ueditor/ueditor.all.min.js"></script>
</body>
</html>