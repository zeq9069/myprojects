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
	href="${webRoot}/${initParam.resourceRoot}/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${webRoot}/${initParam.resourceRoot}/css/home.css" rel="stylesheet">
<link href="${webRoot}/${initParam.resourceRoot}/css/service.css" rel="stylesheet">
 
</head>
<body>
	<jsp:include page="common/head.jsp" />

	<div class="container" style="margin-top: 100px;height: 800px">
		<div class="row">
			<div class="col-md-3">
				<div class="list-group">
					<a href="#" class="list-group-item active">产品服务 </a> 
					<a href="#" class="list-group-item">服务一</a> 
					<a href="#" class="list-group-item">服务二</a> 
					<a href="#" class="list-group-item">服务三</a> 
					<a href="#" class="list-group-item">服务四</a>
				</div>
			</div>

			<div class="col-md-9" >
				 <div class="row">
					<div class="list-group">
				 		<div class="list-group-item">
				 		 	<div class="row"  >
								<div class="row featurette">
									<div class="col-md-7">
										<h2 class="featurette-heading">
											 云主机
										</h2>
										<p class="lead">
											美团云主机提供弹性的计算资源。在一两分钟内，可以快速部署开通一台或者多台云主机。根据应用访问的具体情况，用户能够在5分钟内完成主机相关资源以及带宽资源的在线动态调整
									</div>
									<div class="col-md-5">
										<img class="featurette-image img-responsive" src="${webRoot}/${initParam.resourceRoot}/images/stat_1.jpg"
											alt="Generic placeholder image" >
									</div>
								</div>
				 			</div>
							<div class="list-group-item bottom">	
					   			 配置方面，目前Web控制台支持最高12核CPU，64GB内存，100M带宽（有更高需求也可以联系美团云人工上调）；操作系统系统则支持Ubuntu、CentOS、Debian以及Windows Server。能够满足运行、开发、测试等多种使用场景。
							</div> 
 						</div>
					</div>
				</div>
				<div class="row" >
					<div class="list-group">
						<div class="list-group-item" id="product">
						产品特性
						</div>
				 		<div class="list-group-item">
				 			<div class="row">
								<div class="row featurette">
									<div class="col-md-2">
										<img class="featurette-image img-responsive" src="${webRoot}/${initParam.resourceRoot}/images/stat_1.jpg"
											alt="Generic placeholder image"  >
									</div>
									<div class="col-md-10">
										<h2 class="featurette-heading">
											主机
										</h2>
										<p class="lead">
											支持创建、删除、启动、关机、重启、重置系统、更换系统、调整配置等功能，便于用户在web端远程操控服务器。							
									</div>
								</div>
				 			</div>
				 		</div>
				 		<div class="list-group-item" >
				 			<div class="row"  >
								<div class="row featurette">
									<div class="col-md-2">
										<img class="featurette-image img-responsive" src="${webRoot}/${initParam.resourceRoot}/images/stat_1.jpg"
											alt="Generic placeholder image">
									</div>
									<div class="col-md-10">
										<h2 class="featurette-heading" >
											SSD主机
										</h2>
										<p class="lead">
											硬盘高吞吐量解决业务IO瓶颈，同时使用RAID10保障数据安全性。							
									</div>
								</div>
				 			</div>
				 		</div>
				 		<div class="list-group-item" style="border:0px">
				 			<div class="row"  >
								<div class="row featurette" style="padding:10px">
									<div class="col-md-2">
										<img class="featurette-image img-responsive" src="${webRoot}/${initParam.resourceRoot}/images/stat_1.jpg"
											alt="Generic placeholder image" >
									</div>
									<div class="col-md-10">
										<h2 class="featurette-heading">
											模板
										</h2>
										<p class="lead">
											可以针对主机系统分区创建镜像，以便快速批量创建主机，极大提高部署能力。						
									</div>
								</div>
				 			</div>
				 		</div>
						<div class="list-group-item" style="border:0px">	
						</div> 
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
