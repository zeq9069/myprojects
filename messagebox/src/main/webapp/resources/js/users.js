$(document).ready(function(){
//	require.config({
//		shim:{
//			bootstrap :{
//				deps : ['jquery']
//			},
//			jsrender : {
//				deps : ['jquery']
//			}
//		},
//		paths:{
//			jquery : "/messagebox/resources/js/jquery.min",
//			bootstrap : "/messagebox/resources/js/bootstrap.min",
//			jsrender : "/messagebox/resources/js/page/jsrender.min",
//			page: "/messagebox/resources/js/page/page"
//		}
//	});
//
//	/*******************用户分页***********************************/
//	require(["jquery",'page','bootstrap','jsrender'],function($,Page){
//		$(function(){
//			var listPage = Page.setting({
//				'itemListUrl': '/messagebox/system/receives',
//				'itemCountUrl': '/messagebox/system/receives/count',
//				'par': {
//					'isLook':'all'
//				},
//				'type': 'get',
//				'listWrap': '.itemListWrap',
//				'listWrapTemp': '#listWrapTemp',
//				'pageWrap': '.itemPageWrap',
//				'pageSize':20
//			});
//			
			
			/*******************群组删除***********************************/
//			$(".itemListWrap").on("dblclick","tr>td>span",function(){
//				var u_id=$(this).parent().attr("data-user").trim();
//				var groupName=$(this).text().trim();
//				var groupId=$(this).attr("data-key").trim();
//				$.post("../system/relations/"+u_id,{
//					_method:"DELETE",
//					groupName:groupName
//				},function(data){
//					if(data.status=="success"){
//						$("td[data-user='"+u_id+"']>span[data-key='"+groupId+"']").remove();
//					}else{
//						alert(data.message);
//					}
//				});
//				
//			});
			
			
			/*******************群组添加***********************************/
//			var u_id;
//			
//			$(".itemListWrap").on("click",".group_plus",function(){
//				 u_id=$(this).parent().attr("data-user").trim();
//			});
//			
//			$("#group_submit").on("click",function(){
//				
//				//获取选中的group拼接成要展示的样式的数组和groupID数组
//				var groupSpan=new Array();
//				var groupID=new Array();
//				$('input[name="groupName"]:checked').each(function(){
//					groupID.push($(this).attr("id"));
//					groupSpan.push("<span data-key='"+$(this).attr("id")+"'>"+$(this).val()+"</span>");
//				});
//				
//				
//				//$("#u_id").attr("value",u_id);
//				$.post("../system/relations/"+u_id,$(".group-form").serialize(),function(data){
//					if(data.status=="success"){
//						//删除重复group，追加新group
//						for(var i=0;i<groupID.length;i++){
//							$("td[data-user='"+u_id+"']>span[data-key='"+groupID[i]+"']").remove();
//							$("td[data-user='"+u_id+"']").html(groupSpan[i]+$("td[data-user='"+u_id+"']").html());
//						}
//					}else{
//						alert(data.message);
//					}
//				});
//			});
	
	
	
	
	
			
			
			/************************下拉框选择********************************/
			$("#group-select").on("change",function(){
				listPage=null;
				listPage = Page.setting({
					'itemListUrl': '/messagebox/system/receives',
					'itemCountUrl': '/messagebox/system/receives/count',
					'par': {
						'isLook':$("#group-select").val().trim()
					},
					'type': 'get',
					'listWrap': '.itemListWrap',
					'listWrapTemp': '#listWrapTemp',
					'pageWrap': '.itemPageWrap',
					'pageSize':20
				});
			});
			
			
		});
