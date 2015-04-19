$(document).ready(function(){
	
	//定义array.remove函数
	Array.prototype.indexOf = function(val) {
		for (var i = 0; i < this.length; i++) {
			var obj=this[i];
			if (obj.id==val.id && obj.text==val.text && obj.fxmc==val.fxmc) {
				return i;
			}
		}
		return -1;
	};
		
	Array.prototype.remove = function(val) {
		var index = this.indexOf(val);
		if (index > -1) {
			this.splice(index, 1);
		}
	};
	
	
	
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
    var yxdmArray=new Array();
    $("#targetYxdm").select2({
    	placeholder:"请选择输入",
    	minimumInputLength:2,//最小输入长度
    	ajax:{
    		url:"http://localhost:8080/messagebox/system/school/list",
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
    	var result=$("#targetYxdm").select2("data");
    	var yxdm=result.id;
    	var yxmcAndFxmc=result.text;
    	var fxmc=result.fxmc;
    	
    	if($.inArray(result,yxdmArray)==-1){
    		yxdmArray.push(result);
    		$("#result_targetYxdm").append("<span data-yxmcAndFxmc='"+yxmcAndFxmc+"' data-yxdm='"+yxdm+"' data-fxmc='"+fxmc+"'>"+yxmcAndFxmc+"</span>");
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
    var provinceCodeArray=new Array();
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
    	if($.inArray(provinceCode,provinceCodeArray)==-1){//不存在
    	//if($("#result_targetProvinceCode > span[data-code='"+provinceCode+"']").length<=0){
    		provinceCodeArray.push(provinceCode);
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
    var yxlxArray=new Array();
    
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
    	var yxlxCode=$("#targetYxlx").val().trim();
    	
    	if(yxlxCode=="school_all"){
    		yxlxArray.splice(0, yxlxArray.length);//清空
    		$("#result_targetYxlx").empty();
    		yxlxArray.push(yxlxCode);
    		$("#result_targetYxlx").append("<span data-code='"+yxlxCode+"'>"+yxlxName+"</span>");
    	}else if($.inArray(yxlxCode,yxlxArray)==-1){//如果元素不存在
    		//if($("#result_targetYxlx > span[data-code='"+yxlxCode+"']").length<=0){//如果元素不存在
    		yxlxArray.push(yxlxCode);
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
    	var text=$("#targetType").val().trim();
    	if(text==''){
    		$("#search_type").css("display","none");
    		return;
    	}
    	
    	$.get("http://localhost:8080/messagebox/system/announs/type/list?key="+text,function(data,status){
    		if(status=='success'){
    			var array=data;
    			var result="";
    			if(array.length>0){
    				for(var i=0;i<array.length;i++){
    					result+="<span>"+data[i].type+"</span>";
    				}
    			}
    		$("#search_type").empty();
    		$("#search_type").append(result);
    		$("#search_type").css("display","block");
    		}
    	});
    	
    });
    
    //输入框获取焦点
    $("#targetType").on("focus",function(){
    	var value=$("#targetType").val().trim();
    	if(value!=''){
    		$("#search_type").css("display","block");
    	}
    });
    
    //选中结果
    $("#search_type").on("click","span",function(){
    	var text=$(this).text().trim();
    	$("#targetType").val(text);
    	$("#search_type").css("display","none");
    });
    
    //已经选中的type数组
    var typeArray=new Array();
    
    $("#add_type").on("click",function(){
    	var type=$("#targetType").val().trim();
    	if(type==''){
    		alert("填写公告类型");
    		return;
    	}
    	//添加type到数据库
    	$.post("http://localhost:8080/messagebox/system/announs/type",{
    		type:type
    	},function(data,status){
    	});
    
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
    	
    	//从数组中删除type
    	if($(this).parent().attr("id")=='result_type'){
    		typeArray.remove($(this).text());
    	}
    	
    	//从数组中删除yxdm
    	if($(this).parent().attr("id")=='result_targetYxdm'){
    		//g构造json对象
    		var obj={"fxmc":$(this).attr("data-fxmc"),"id":$(this).attr("data-yxdm"),"text":$(this).attr("data-yxmcAndFxmc")};
    		yxdmArray.remove(obj);
    	}
    	
    	//从数组中删除provinceCode
    	if($(this).parent().attr("id")=='result_targetProvinceCode'){
    		provinceCodeArray.remove($(this).attr("data-code"));
    	}
    	
    	//从数组中删除yxlx
    	if($(this).parent().attr("id")=='result_targetYxlx'){
    		yxlxArray.remove($(this).attr("data-code"));
    	}
    	
    	//删除元素
    	$(this).remove();
    });
    
    
	
	$("#announ_submit").click(function(){
		var title=$("#title").val().trim();
		if(title==''){
			alert("标题不能为空");
			return false;
		}
		
		var value=UE.getEditor('editor').getContent();
		if(value==''){
			alert("内容不能为空");
			return false;
		}
		alert(yxdmArray.length);
		$.post("/messagebox/system/announs",{
			title:title,
			targetProvinceCode:provinceCodeArray.join(","),
			targetSchools:JSON.stringify(yxdmArray),
			targetYxlx:yxlxArray.join(","),
			type:typeArray.join(","),
			content:value
		},function(data,status){
			if(data.message=="success"){
				alert("添加成功!");
			}else{
				alert(data.message);
			}
		});
	});
	
});

