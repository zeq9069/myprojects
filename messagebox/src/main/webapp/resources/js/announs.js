$(document).ready(function(){
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
				jquery : "/messagebox/resources/js/jquery.min",
				bootstrap : "/messagebox/resources/js/bootstrap.min",
				jsrender : "/messagebox/resources/js/page/jsrender.min",
				page: "/messagebox/resources/js/page/page"
			}
		});

		/*******************分页***********************************/
		require(["jquery",'page','bootstrap','jsrender'],function($,Page){
			$(function(){
				var listPage = Page.setting({
					'itemListUrl': '/messagebox/system/announs/list',
					'itemCountUrl': '/messagebox/system/announs/count',
					'par': {
						'online':'true'
					},
					'type': 'post',
					'listWrap': '.itemListWrap',
					'listWrapTemp': '#listWrapTemp',
					'pageWrap': '.itemPageWrap',
					'pageSize':20
				});
				
				
				$("select[name='online']").on("change",function(){
						listPage=null;
						listPage = Page.setting({
							'itemListUrl': '/messagebox/system/announs/list',
							'itemCountUrl': '/messagebox/system/announs/count',
							'par': {
								'online':$(this).val()
							},
							'type': 'post',
							'listWrap': '.itemListWrap',
							'listWrapTemp': '#listWrapTemp',
							'pageWrap': '.itemPageWrap',
							'pageSize':20
						});
				});
				
				
				
				
				
				
				
		
		$("tbody").on("dblclick","span#announ_status",function(){
			var announ_id=$(this).attr("data-user");
			var online=$(this).attr("data-value");
			
			$.post("/messagebox/system/announs/online/update",{announ_id:announ_id,online:online=="true"?"false":"true"},function(data){
				if(data.status=="success"){
					alert("修改成功");
					if(online=="true"){
						$("span[data-user='"+announ_id+"']").text("下线");
						$("span[data-user='"+announ_id+"']").attr("data-value","false");
					}else if(online=="false"){
						$("span[data-user='"+announ_id+"']").text("上线");
						$("span[data-user='"+announ_id+"']").attr("data-value","true");
					}
				}else{
					alert(data.message);
				}
			});
			
		});
		
	});
		});
	});
	