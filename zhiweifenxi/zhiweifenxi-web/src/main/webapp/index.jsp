<%@page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- <link rel="icon" href="../../favicon.ico"> -->

<title>职位分析</title>

<link href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
<script src="resources/js/ie-emulation-modes-warning.js"></script>
<link href="resources/css/carousel.css" rel="stylesheet">
</head>

<body>
<!-- 导航栏
================================================== -->
<jsp:include page="head.jsp" />

	<!--轮播位
    ================================================== -->
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img src="resources/images/background.jpg" alt="First slide">
				<div class="container">
					<div class="carousel-caption">
						<!-- <h1>Example headline.</h1>
						<p>
							Note: If you're viewing this page via a
							<code>file://</code>
							URL, the "next" and "previous" Glyphicon buttons on the left and
							right might not load/display properly due to web browser security
							rules.
						</p>
						<p>
							<a class="btn btn-lg btn-default" href="#" role="button">查看</a>
						</p> -->
					</div>
				</div>
			</div>
			<div class="item">
				<img src="resources/images/background2.jpg" alt="Second slide">
				<div class="container">
					<div class="carousel-caption">
						<!-- <h1>Another example headline.</h1>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>
						<p>
							<a class="btn btn-lg btn-default" href="#" role="button">查看</a>
						</p> -->
					</div>
				</div>
			</div>
			<div class="item">
				<img src="resources/images/background3.jpg" alt="Third slide" >
				<div class="container">
					<div class="carousel-caption">
					<!-- 	<h1>One more for good measure.</h1>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>
						<p>
							<a class="btn btn-lg btn-default" href="#" role="button">查看</a>
						</p> -->
					</div>
				</div>
			</div>
		</div>
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	 <!-- 轮播位结束 -->


	<!-- 信息展示位
    ================================================== -->

	<div class="container marketing">

 		<div class="row">
			<div class="col-lg-4">
				<img class="img-circle" src="resources/images/icon_01.jpg"
					alt="Generic placeholder image" style="width: 176px; height: 151px;">
				<h2>实时</h2>
				<p>我们会在每天、每周、每月为您提供实时的职位的动态变化，并提供过去的职位变化的趋势图，为您找到 一个满意的工作而提供决策！</p>
				<p>
					<a class="btn btn-default" href="#" role="button">详情&raquo;</a>
				</p>
			</div>
			
 			<div class="col-lg-4">
				<img class="img-circle" src="resources/images/icon_02.png"
					alt="Generic placeholder image"
					style="width: 140px; height: 140px;">
				<h2>个性化</h2>
				<p>为了能为用户提供良好的服务，我们会针对不同的用户提供个性的职位分析数据，尽最大努力让实现个性 化，让用户早日找到自己心仪的职位。</p>
				<p>
					<a class="btn btn-default" href="#" role="button">详情&raquo;</a>
				</p>
			</div>
			
			<div class="col-lg-4">
				<img class="img-circle" src="resources/images/icon_03.png"
					alt="Generic placeholder image"
					style="width: 140px; height: 140px;">
				<h2>专注</h2>
				<p>本站专注于职位的分析，为广大用户提供最专业的数据！</p>
				<p>
					<a class="btn btn-default" href="#" role="button">详情&raquo;</a>
				</p>
			</div>
		</div>


		<!-- 花絮 -->
		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading">
					数据库的可靠性
				</h2>
				<p class="lead"><blockquote>数据全部来自于对各大招聘网站的数据抓取和分析，对数据进行筛选和过滤，得到真正对我们有价值的信息，以此来为用户提供真实可靠的信息，帮助用户提供正确的决策！</blockquote> 
			</div>
			<div class="col-md-5">
				<img class="featurette-image img-responsive" src="resources/images/stat_1.jpg"
					alt="Generic placeholder image" style="width:300px;height:300px;">
			</div>
		</div>

		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-5">
				<img class="featurette-image img-responsive" src="resources/images/stat_2.jpg"
					alt="Generic placeholder image" style="width:300px;height:300px;">
			</div>
			<div class="col-md-7">
				<h2 class="featurette-heading">
					数据的多样化
				</h2>
				<p class="lead"><blockquote>我们对职位的分析是针对广大上班族的，数据的来源比较广，争取覆盖各个年龄段的用户</blockquote>

			</div>
		</div>

		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading">
					优质的服务
				</h2>
				<p class="lead"><blockquote>优质的服务是我们不变的承诺！你在使用过程中遇到任何问题都可以随时跟我们进行沟通，我们会尽最大努力帮您解决！</blockquote>
			</div>
			<div class="col-md-5">
				<img class="featurette-image img-responsive" src="resources/images/stat_3.jpg"
					alt="Generic placeholder image" >
			</div>
		</div>

		<hr class="featurette-divider">
		<!--花絮结束 -->
		<jsp:include page="footer.jsp" />
	</div>

	<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/docs.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="resources/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
