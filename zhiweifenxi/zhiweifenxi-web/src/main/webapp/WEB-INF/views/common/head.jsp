<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="webRoot"   value="${pageContext.request.contextPath}"/>

 <link href="${webRoot}/${initParam.resourceRoot}/css/home.css" rel="stylesheet">
 
<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">职位分析</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav ">
					<li class="active"><a href="#">首页 <span class="sr-only">(current)</span></a></li>
					<li><a href="#">服务介绍</a></li>
					<li><a href="#">动态</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="#">关于 <span class="sr-only">(current)</span></a></li>
					<li><a href="#">加入我们</a></li>
					<li><span>&nbsp;&nbsp;</span></li>
					<li><a type="button" class="btn  btn-lg" data-toggle="modal"
						data-target="#regModel"> 注册 </a></li>
					<li><a type="button" class="btn  btn-lg" data-toggle="modal"
						data-target="#loginModel"> 登录 </a></li>
					<!-- 
					下拉导航
					<li class="dropdown">
						<a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">admin
								<span class="caret"></span>
						</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">基本信息</a></li>
							<li><a href="#">消息</a></li>
							<li><a href="#">职位</a></li>
							<li class="divider"></li>
							<li><a href="#">退出</a></li>
						</ul>
				   	</li> -->
				</ul>

				<!-- model 登录模态框-->
				<div class="modal fade" id="loginModel" tabindex="-1" role="dialog"
					aria-labelledby="loginLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="loginLabel">登录</h4>
							</div>
							<div class="modal-body">
								<form class="login-form">
									<p>
										用户名：<input type="text" placeholder="Email">
									</p>
									<p>
										密 &nbsp; 码：<input type="password" placeholder="Password">
									</p>
									<p>
										<button type="submit" class="btn btn-primary ">确认</button>
									</p>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
							</div>
						</div>
					</div>
				</div>

				<!-- model 注册模态框-->
				<div class="modal fade" id="regModel" tabindex="-1" role="dialog"
					aria-labelledby="regLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="regLabel">注册</h4>
							</div>
							<div class="modal-body">
								<form class="reg-form">
									<p>
										用户名：<input type="text" placeholder="Email">
									</p>
									<p>
										密 &nbsp; 码：<input type="password" placeholder="Password">
									</p>
									<p>
										确 &nbsp; 认：<input type="password" placeholder="Password">
									</p>
									<p>
										<button type="submit" class="btn btn-primary ">注册</button>
										<button type="reset" class="btn btn-default">撤销</button>
									</p>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</nav>
