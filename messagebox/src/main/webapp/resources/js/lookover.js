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

	/*******************用户分页***********************************/
	require(["jquery",'page','bootstrap','jsrender'],function($,Page){
		$(function(){
			var listPage = Page.setting({
				'itemListUrl': '/messagebox/system/receives/lookover/list',
				'itemCountUrl': '/messagebox/system/receives/lookover/count',
				'par': {
				},
				'type': 'get',
				'listWrap': '.itemListWrap',
				'listWrapTemp': '#listWrapTemp',
				'pageWrap': '.itemPageWrap',
				'pageSize':20
			});
			
			
	
			
//			
//			/************************下拉框选择********************************/
//			$("#group-select").on("change",function(){
//				listPage=null;
//				listPage = Page.setting({
//					'itemListUrl': '/messagebox/system/receives',
//					'itemCountUrl': '/messagebox/system/receives/count',
//					'par': {
//						'isLook':$("#group-select").val().trim()
//					},
//					'type': 'get',
//					'listWrap': '.itemListWrap',
//					'listWrapTemp': '#listWrapTemp',
//					'pageWrap': '.itemPageWrap',
//					'pageSize':20
//				});
//			});
//			
			
		});
});
	
});