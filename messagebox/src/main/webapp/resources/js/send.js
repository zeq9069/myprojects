$(document).ready(function(){
	
	
	
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
  
    $("#announ_form").validate({
    	submitHandler:function(form){

    		var value=UE.getEditor('editor').getContent();
    		
    		if(value.trim()==''){
    			alert("内容不能为空");
    			return false;
    		}
    		
    		$.post("/messagebox/system/announs",$("form").serialize(),function(data){
    			if(data.status=="success"){
    				alert("添加成功!");
    			}else{
    				alert(data.message);
    			}
    		});
    		
    		
    		
        }    
    });
    
    
    /*------------------------
     * 
     * 本省院校查询
     * 
     *------------------------
     */
    $("#targetYxdm").select2({
    	placeholder:"请选择输入",
    	minimumInputLength:2,//最小输入长度
    	ajax:{
    		url:"http://localhost:8080/messagebox/system/list",
    		dataType:"json",
    		quietMillis:20,
    		data:function (term,page){
    			return {q:term};
    		},
    		results:function(data,page){
    			return {results:data};
    		}
    	}
    	
    });
    
    $("#add_targetYxdm").on("click",function(){
    	if($("#targetYxdm").select2("data")==null){
    		alert("请输入学校名称");
    		return false;
    	}
    	var yxdm=$("#targetYxdm").select2("data").id;
    	var yx=$("#targetYxdm").select2("data").text;
    	
    	if($("#result_targetYxdm > span[data-code='"+yxdm+"']").length<=0){
    		$("#result_targetYxdm").append("<span data-code='"+yxdm+"'>"+yx+"</span>");
    	}else{
    		alert("您已经选择");
    	}
    	
    	
    });
    
    
    /*
     * ------------------------------------
     * 
     * 省选择操作
     *
     * ------------------------------------
     */
    
    $("#targetProvinceCode").on("change",function(){
    	var value=$("#targetProvinceCode").val();
    	if(value=="0"){
    		$("#add_targetProvinceCode").hide();
    	}else{
    		$("#add_targetProvinceCode").show();
    	}
    });
    
    $("#add_targetProvinceCode").on("click",function(){
    	var provinceName=$("#targetProvinceCode")[0].options[$("#targetProvinceCode")[0].selectedIndex].text;//省名字
    	var provinceCode=$("#targetProvinceCode").val();//省代码
    	//判断是否已经添加
    	if($("#result_targetProvinceCode > span[data-code='"+provinceCode+"']").length<=0){
    		$("#result_targetProvinceCode").append("<span data-code='"+provinceCode+"'>"+provinceName+"</span>");
    	}else{
    		alert("您已经选择");
    	}
    });
    
    
    /*---------------------------
     *  
     *   学校类型选择操作
     * 
     * --------------------------
     */
    
    $("#targetYxlx").on("change",function(){
    	var value=$("#targetYxlx").val();
    	if(value=="0"){
    		$("#add_targetYxlx").hide();
    	}else{
    		$("#add_targetYxlx").show();
    	}
    });
    
    $("#add_targetYxlx").on("click",function(){
    	var yxlxName=$("#targetYxlx")[0].options[$("#targetYxlx")[0].selectedIndex].text;
    	var yxlxCode=$("#targetYxlx").val();
    	
    	if(yxlxCode=="school_all"){
    		$("#result_targetYxlx").empty();
    		$("#result_targetYxlx").append("<span data-code='"+yxlxCode+"'>"+yxlxName+"</span>");
    	}else if($("#result_targetYxlx > span[data-code='"+yxlxCode+"']").length<=0){
    		$("#result_targetYxlx").append("<span data-code='"+yxlxCode+"'>"+yxlxName+"</span>");
    	}else{
    		alert("您已经选择");
    	}
    });
    
    /*----------------------------------
     * 
     *  公告类型选择操作
     * 
     * ---------------------------------
     */
   
    //输入关键字
    $("#targetType").on("keyup",function(){
    	var text=$("#targetType").val();
    	if(text.trim()==''){
    		$("#search_type").css("display","none");
    		return;
    	}
    	$("#search_type").css("display","block");
    });
    
    //输入框获取焦点
    $("#targetType").on("focus",function(){
    	var value=$("#targetType").val();
    	if(value.trim()!=''){
    		$("#search_type").css("display","block");
    	}
    });
    
    //选中结果
    $("#search_type").on("click","span",function(){
    	var text=$(this).text();
    	$("#targetType").val(text);
    	$("#search_type").css("display","none");
    });
    
    //已经选中的type数组
    var typeArray=new Array();
    
    $("#add_type").on("click",function(){
    	var type=$("#targetType").val().trim();
    	if(type!=''){
    		if($.inArray(type,typeArray)==-1){
    			typeArray.push(type);
    			$("#result_type").append("<span>"+type+"</span>");
    		}else{
    			alert("已经选择");
    		}
    	}
    });
    
    
    /*-------------------------
     * jquery 冒泡机制  
     *  删除事件
     *-------------------------
     */
    $(".result_list").on("click","div > span",function(){
    	$(this).remove();
    });
    
    
	
//	$("#announ_submit").click(function(){
//		
//		var value=UE.getEditor('editor').getContent();
//		
//		$.post("/messagebox/system/announs/add",$("form").serialize(),function(data){
//			if(data.status=="success"){
//				alert("添加成功!");
//			}else{
//				alert(data.message);
//			}
//		});
//	});
	
});

