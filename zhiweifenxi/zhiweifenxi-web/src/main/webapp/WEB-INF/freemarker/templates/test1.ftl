<html>
 <head>
 	<title>freemarker test</title>
 </head>
 <body>
 <#if  user.age?exists >您已经30而立了！</#if>
 	<table>
 		<tr>
 			<td>姓名</td><td>年龄</td><td>出生日期</td>
 		</tr>
 		<tr>
 			<td>${user.name}</td><td>${user.age}</td><td>${user.birthday}</td>
 		</tr>
 	</table>
 </body>
</html