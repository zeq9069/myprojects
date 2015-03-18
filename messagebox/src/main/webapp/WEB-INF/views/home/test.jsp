<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="webRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh">
<head>
    <c:import url="../common/meta.jsp"><c:param name="title">毕业生-学生列表</c:param></c:import>
    <link rel="stylesheet" href="${initParam.resourceRoot}/css/graduate.css">
    <script type="text/x-jsrender" id="listWrapTemp">
		<li data-ksh="{{:ksh.value}}">
			<div>
				<h5><span class="list-xm">{{:xm.value}}</span> <small>(<span class="list-ksh">{{:ksh.value}}</span>) <span class="list-byqx">{{:employmentInfo.byqx}}</span> <span class="list-jydw" title="就业单位">{{:employmentInfo.dwmc}}</span></small></h5>
				<shiro:hasRole name="province"><span>{{:yxmc}}</span>|<span>{{:fxmc}}</span>|</shiro:hasRole><span class="list-xb">{{:xb}}</span> | <span class="list-mz">{{:mz}}</span> | <span class="list-xl">{{:xl}}</span> | {{:szyx}} | <span class="list-zy">{{:zy}}</span> | <span class="list-pyfs">{{:pyfs}}</span> | <span class="list-sfslb">{{:sfslb}}</span> | <span class="list-knslb">{{:knslb}}</span>
			</div>
		</li>
	</script>
	<style type="text/css">
	.pagination>li.goPage{padding-left:15px;}
	.pagination>li.goPage>span{border:0px;float:inherit;background-color: inherit;padding:0px;line-height:28px;}
	.pagination>li.goPage>span.text{color:#666;}
	.pagination>li.goPage>input.num,.pagination>li.goPage>span.btn{width:40px;height:24px;padding:4px 8px;border:1px solid #CCC;line-height:16px;}
	</style>
</head>
<body>
	<c:import url="../common/top.jsp"><c:param name="active">graduate</c:param></c:import>
	<c:import url="header.jsp"><c:param name="subactive">graduate.stulist</c:param></c:import>
	
	<div class="container">
		<div class="itemWrap">
			<ul class="itemListWrap">
			
			</ul>
			<ul class="itemPageWrap pagination pagination-sm">
			
			</ul>
		</div>
		<div class="itemWrap">
			<ul class="itemListWrap1">
			
			</ul>
			<ul class="itemPageWrap1 pagination pagination-sm">
			
			</ul>
		</div>
	</div>
	
	<script type="text/javascript" src="${initParam.resourceRoot}/js/lib/requirejs/requirejs.min.js"></script>
	<script type="text/javascript">
	require.config({
		shim:{
			bootstrap :{
				deps : ['jquery']
			},
			jsrender : {
				deps : ['jquery']
			}
		},
		paths:{
			jquery : "../../resources/js/lib/jquery/jquery.min",
			bootstrap : "../../resources/js/lib/bootstrap/js/bootstrap.min",
			jsrender : "../../resources/js/lib/jsrender.min",
			page: "../../resources/js/common/page"
		}
	});

	require(["jquery",'page','bootstrap','jsrender'],function($,Page){
		$(function(){
			var listPage = Page.setting({
				'itemListUrl': '2015/get/list',
				'itemCountUrl': '2015/get/count',
				'par': {},
				'type': 'post',
				'listWrap': '.itemListWrap',
				'listWrapTemp': '#listWrapTemp',
				'pageWrap': '.itemPageWrap'
			});
			var listPage2 = Page.setting({
				'itemListUrl': '2015/get/list',
				'itemCountUrl': '2015/get/count',
				'par': {},
				'type': 'post',
				'listWrap': '.itemListWrap1',
				'listWrapTemp': '#listWrapTemp',
				'pageWrap': '.itemPageWrap1'
			});
		});
	});
	</script>
	<c:import url="../common/footer.jsp"></c:import>
</body>
</html>