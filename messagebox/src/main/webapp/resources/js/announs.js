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
					'itemListUrl': '/messagebox/system/announs',
					'itemCountUrl': '/messagebox/system/announs/count',
					'par': {
						'online':'true'
					},
					'type': 'get',
					'listWrap': '.itemListWrap',
					'listWrapTemp': '#listWrapTemp',
					'pageWrap': '.itemPageWrap',
					'pageSize':20
				});
				
				
				$("select[name='online']").on("change",function(){
						listPage=null;
						listPage = Page.setting({
							'itemListUrl': '/messagebox/system/announs',
							'itemCountUrl': '/messagebox/system/announs/count',
							'par': {
								'online':$(this).val()
							},
							'type': 'get',
							'listWrap': '.itemListWrap',
							'listWrapTemp': '#listWrapTemp',
							'pageWrap': '.itemPageWrap',
							'pageSize':20
						});
				});
				
				
		
		$("tbody").on("dblclick","span#announ_status",function(){
			var announ_id=$(this).attr("data-user");
			var online=$(this).attr("data-value");
			$.ajax({
				type:"POST",
				url:"/messagebox/system/announs/"+announ_id+"?_method=put",
				data:{"online":online=="true"?"false":"true"},
				success:function(data){
					$("span[data-user='"+announ_id+"']").parent().parent().remove();
				},
				error:function(request, textStatus){
					$("span[data-user='"+announ_id+"']").parent().parent().remove();
				}
			});
			
		});
		
	});
		});
	});
	